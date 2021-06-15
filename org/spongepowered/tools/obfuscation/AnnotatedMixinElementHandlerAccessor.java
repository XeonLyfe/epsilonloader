/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import com.google.common.base.Strings;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.gen.AccessorInfo;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.refmap.ReferenceMapper;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.AnnotatedMixin;
import org.spongepowered.tools.obfuscation.AnnotatedMixinElementHandler;
import org.spongepowered.tools.obfuscation.ObfuscationData;
import org.spongepowered.tools.obfuscation.ReferenceManager;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.FieldHandle;
import org.spongepowered.tools.obfuscation.mirror.MethodHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;

public class AnnotatedMixinElementHandlerAccessor
extends AnnotatedMixinElementHandler
implements IMixinContext {
    public AnnotatedMixinElementHandlerAccessor(IMixinAnnotationProcessor iMixinAnnotationProcessor, AnnotatedMixin annotatedMixin) {
        super(iMixinAnnotationProcessor, annotatedMixin);
    }

    @Override
    public ReferenceMapper getReferenceMapper() {
        return null;
    }

    @Override
    public String getClassName() {
        return this.mixin.getClassRef().replace('/', '.');
    }

    @Override
    public String getClassRef() {
        return this.mixin.getClassRef();
    }

    @Override
    public String getTargetClassRef() {
        throw new UnsupportedOperationException("Target class not available at compile time");
    }

    @Override
    public IMixinInfo getMixin() {
        throw new UnsupportedOperationException("MixinInfo not available at compile time");
    }

    @Override
    public Extensions getExtensions() {
        throw new UnsupportedOperationException("Mixin Extensions not available at compile time");
    }

    @Override
    public boolean getOption(MixinEnvironment.Option option) {
        throw new UnsupportedOperationException("Options not available at compile time");
    }

    @Override
    public int getPriority() {
        throw new UnsupportedOperationException("Priority not available at compile time");
    }

    @Override
    public Target getTargetMethod(MethodNode methodNode) {
        throw new UnsupportedOperationException("Target not available at compile time");
    }

    public void registerAccessor(AnnotatedElementAccessor annotatedElementAccessor) {
        if (annotatedElementAccessor.getAccessorType() == null) {
            annotatedElementAccessor.printMessage(this.ap, Diagnostic.Kind.WARNING, "Unsupported accessor type");
            return;
        }
        String string = this.getAccessorTargetName(annotatedElementAccessor);
        if (string == null) {
            annotatedElementAccessor.printMessage(this.ap, Diagnostic.Kind.WARNING, "Cannot inflect accessor target name");
            return;
        }
        annotatedElementAccessor.setTargetName(string);
        for (TypeHandle typeHandle : this.mixin.getTargets()) {
            if (annotatedElementAccessor.getAccessorType() == AccessorInfo.AccessorType.METHOD_PROXY) {
                this.registerInvokerForTarget((AnnotatedElementInvoker)annotatedElementAccessor, typeHandle);
                continue;
            }
            this.registerAccessorForTarget(annotatedElementAccessor, typeHandle);
        }
    }

    private void registerAccessorForTarget(AnnotatedElementAccessor annotatedElementAccessor, TypeHandle typeHandle) {
        FieldHandle fieldHandle = typeHandle.findField(annotatedElementAccessor.getTargetName(), annotatedElementAccessor.getTargetTypeName(), false);
        if (fieldHandle == null) {
            if (!typeHandle.isImaginary()) {
                annotatedElementAccessor.printMessage(this.ap, Diagnostic.Kind.ERROR, "Could not locate @Accessor target " + annotatedElementAccessor + " in target " + typeHandle);
                return;
            }
            fieldHandle = new FieldHandle(typeHandle.getName(), annotatedElementAccessor.getTargetName(), annotatedElementAccessor.getDesc());
        }
        if (!annotatedElementAccessor.shouldRemap()) {
            return;
        }
        ObfuscationData<MappingField> obfuscationData = this.obf.getDataProvider().getObfField(fieldHandle.asMapping(false).move(typeHandle.getName()));
        if (obfuscationData.isEmpty()) {
            String string = this.mixin.isMultiTarget() ? " in target " + typeHandle : "";
            annotatedElementAccessor.printMessage(this.ap, Diagnostic.Kind.WARNING, "Unable to locate obfuscation mapping" + string + " for @Accessor target " + annotatedElementAccessor);
            return;
        }
        obfuscationData = AnnotatedMixinElementHandler.stripOwnerData(obfuscationData);
        try {
            this.obf.getReferenceManager().addFieldMapping(this.mixin.getClassRef(), annotatedElementAccessor.getTargetName(), annotatedElementAccessor.getContext(), obfuscationData);
        }
        catch (ReferenceManager.ReferenceConflictException referenceConflictException) {
            annotatedElementAccessor.printMessage(this.ap, Diagnostic.Kind.ERROR, "Mapping conflict for @Accessor target " + annotatedElementAccessor + ": " + referenceConflictException.getNew() + " for target " + typeHandle + " conflicts with existing mapping " + referenceConflictException.getOld());
        }
    }

    private void registerInvokerForTarget(AnnotatedElementInvoker annotatedElementInvoker, TypeHandle typeHandle) {
        MethodHandle methodHandle = typeHandle.findMethod(annotatedElementInvoker.getTargetName(), annotatedElementInvoker.getTargetTypeName(), false);
        if (methodHandle == null) {
            if (!typeHandle.isImaginary()) {
                annotatedElementInvoker.printMessage(this.ap, Diagnostic.Kind.ERROR, "Could not locate @Invoker target " + annotatedElementInvoker + " in target " + typeHandle);
                return;
            }
            methodHandle = new MethodHandle(typeHandle, annotatedElementInvoker.getTargetName(), annotatedElementInvoker.getDesc());
        }
        if (!annotatedElementInvoker.shouldRemap()) {
            return;
        }
        ObfuscationData<MappingMethod> obfuscationData = this.obf.getDataProvider().getObfMethod(methodHandle.asMapping(false).move(typeHandle.getName()));
        if (obfuscationData.isEmpty()) {
            String string = this.mixin.isMultiTarget() ? " in target " + typeHandle : "";
            annotatedElementInvoker.printMessage(this.ap, Diagnostic.Kind.WARNING, "Unable to locate obfuscation mapping" + string + " for @Accessor target " + annotatedElementInvoker);
            return;
        }
        obfuscationData = AnnotatedMixinElementHandler.stripOwnerData(obfuscationData);
        try {
            this.obf.getReferenceManager().addMethodMapping(this.mixin.getClassRef(), annotatedElementInvoker.getTargetName(), annotatedElementInvoker.getContext(), obfuscationData);
        }
        catch (ReferenceManager.ReferenceConflictException referenceConflictException) {
            annotatedElementInvoker.printMessage(this.ap, Diagnostic.Kind.ERROR, "Mapping conflict for @Invoker target " + annotatedElementInvoker + ": " + referenceConflictException.getNew() + " for target " + typeHandle + " conflicts with existing mapping " + referenceConflictException.getOld());
        }
    }

    private String getAccessorTargetName(AnnotatedElementAccessor annotatedElementAccessor) {
        String string = annotatedElementAccessor.getAnnotationValue();
        if (Strings.isNullOrEmpty(string)) {
            return this.inflectAccessorTarget(annotatedElementAccessor);
        }
        return string;
    }

    private String inflectAccessorTarget(AnnotatedElementAccessor annotatedElementAccessor) {
        return AccessorInfo.inflectTarget(annotatedElementAccessor.getSimpleName(), annotatedElementAccessor.getAccessorType(), "", this, false);
    }

    static class AnnotatedElementInvoker
    extends AnnotatedElementAccessor {
        public AnnotatedElementInvoker(ExecutableElement executableElement, AnnotationHandle annotationHandle, boolean bl) {
            super(executableElement, annotationHandle, bl);
        }

        @Override
        public String getAccessorDesc() {
            return TypeUtils.getDescriptor((ExecutableElement)this.getElement());
        }

        @Override
        public AccessorInfo.AccessorType getAccessorType() {
            return AccessorInfo.AccessorType.METHOD_PROXY;
        }

        @Override
        public String getTargetTypeName() {
            return TypeUtils.getJavaSignature(this.getElement());
        }
    }

    static class AnnotatedElementAccessor
    extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement> {
        private final boolean shouldRemap;
        private final TypeMirror returnType;
        private String targetName;

        public AnnotatedElementAccessor(ExecutableElement executableElement, AnnotationHandle annotationHandle, boolean bl) {
            super(executableElement, annotationHandle);
            this.shouldRemap = bl;
            this.returnType = ((ExecutableElement)this.getElement()).getReturnType();
        }

        public boolean shouldRemap() {
            return this.shouldRemap;
        }

        public String getAnnotationValue() {
            return (String)this.getAnnotation().getValue();
        }

        public TypeMirror getTargetType() {
            switch (this.getAccessorType()) {
                case FIELD_GETTER: {
                    return this.returnType;
                }
                case FIELD_SETTER: {
                    return ((ExecutableElement)this.getElement()).getParameters().get(0).asType();
                }
            }
            return null;
        }

        public String getTargetTypeName() {
            return TypeUtils.getTypeName(this.getTargetType());
        }

        public String getAccessorDesc() {
            return TypeUtils.getInternalName(this.getTargetType());
        }

        public MemberInfo getContext() {
            return new MemberInfo(this.getTargetName(), null, this.getAccessorDesc());
        }

        public AccessorInfo.AccessorType getAccessorType() {
            return this.returnType.getKind() == TypeKind.VOID ? AccessorInfo.AccessorType.FIELD_SETTER : AccessorInfo.AccessorType.FIELD_GETTER;
        }

        public void setTargetName(String string) {
            this.targetName = string;
        }

        public String getTargetName() {
            return this.targetName;
        }

        public String toString() {
            return this.targetName != null ? this.targetName : "<invalid>";
        }
    }
}

