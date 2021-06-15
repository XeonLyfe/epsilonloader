/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.AnnotatedMixin;
import org.spongepowered.tools.obfuscation.AnnotatedMixinElementHandler;
import org.spongepowered.tools.obfuscation.Mappings;
import org.spongepowered.tools.obfuscation.ObfuscationData;
import org.spongepowered.tools.obfuscation.ObfuscationType;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IObfuscationDataProvider;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

class AnnotatedMixinElementHandlerShadow
extends AnnotatedMixinElementHandler {
    AnnotatedMixinElementHandlerShadow(IMixinAnnotationProcessor iMixinAnnotationProcessor, AnnotatedMixin annotatedMixin) {
        super(iMixinAnnotationProcessor, annotatedMixin);
    }

    public void registerShadow(AnnotatedElementShadow<?, ?> annotatedElementShadow) {
        this.validateTarget((Element)annotatedElementShadow.getElement(), annotatedElementShadow.getAnnotation(), annotatedElementShadow.getName(), "@Shadow");
        if (!annotatedElementShadow.shouldRemap()) {
            return;
        }
        for (TypeHandle typeHandle : this.mixin.getTargets()) {
            this.registerShadowForTarget(annotatedElementShadow, typeHandle);
        }
    }

    private void registerShadowForTarget(AnnotatedElementShadow<?, ?> annotatedElementShadow, TypeHandle typeHandle) {
        ObfuscationData<?> obfuscationData = annotatedElementShadow.getObfuscationData(this.obf.getDataProvider(), typeHandle);
        if (obfuscationData.isEmpty()) {
            String string;
            String string2 = string = this.mixin.isMultiTarget() ? " in target " + typeHandle : "";
            if (typeHandle.isSimulated()) {
                annotatedElementShadow.printMessage(this.ap, Diagnostic.Kind.WARNING, "Unable to locate obfuscation mapping" + string + " for @Shadow " + annotatedElementShadow);
            } else {
                annotatedElementShadow.printMessage(this.ap, Diagnostic.Kind.WARNING, "Unable to locate obfuscation mapping" + string + " for @Shadow " + annotatedElementShadow);
            }
            return;
        }
        for (ObfuscationType obfuscationType : obfuscationData) {
            try {
                annotatedElementShadow.addMapping(obfuscationType, (IMapping)obfuscationData.get(obfuscationType));
            }
            catch (Mappings.MappingConflictException mappingConflictException) {
                annotatedElementShadow.printMessage(this.ap, Diagnostic.Kind.ERROR, "Mapping conflict for @Shadow " + annotatedElementShadow + ": " + mappingConflictException.getNew().getSimpleName() + " for target " + typeHandle + " conflicts with existing mapping " + mappingConflictException.getOld().getSimpleName());
            }
        }
    }

    class AnnotatedElementShadowMethod
    extends AnnotatedElementShadow<ExecutableElement, MappingMethod> {
        public AnnotatedElementShadowMethod(ExecutableElement executableElement, AnnotationHandle annotationHandle, boolean bl) {
            super(executableElement, annotationHandle, bl, IMapping.Type.METHOD);
        }

        @Override
        public MappingMethod getMapping(TypeHandle typeHandle, String string, String string2) {
            return typeHandle.getMappingMethod(string, string2);
        }

        @Override
        public void addMapping(ObfuscationType obfuscationType, IMapping<?> iMapping) {
            AnnotatedMixinElementHandlerShadow.this.addMethodMapping(obfuscationType, this.setObfuscatedName(iMapping), this.getDesc(), iMapping.getDesc());
        }
    }

    class AnnotatedElementShadowField
    extends AnnotatedElementShadow<VariableElement, MappingField> {
        public AnnotatedElementShadowField(VariableElement variableElement, AnnotationHandle annotationHandle, boolean bl) {
            super(variableElement, annotationHandle, bl, IMapping.Type.FIELD);
        }

        @Override
        public MappingField getMapping(TypeHandle typeHandle, String string, String string2) {
            return new MappingField(typeHandle.getName(), string, string2);
        }

        @Override
        public void addMapping(ObfuscationType obfuscationType, IMapping<?> iMapping) {
            AnnotatedMixinElementHandlerShadow.this.addFieldMapping(obfuscationType, this.setObfuscatedName(iMapping), this.getDesc(), iMapping.getDesc());
        }
    }

    static abstract class AnnotatedElementShadow<E extends Element, M extends IMapping<M>>
    extends AnnotatedMixinElementHandler.AnnotatedElement<E> {
        private final boolean shouldRemap;
        private final AnnotatedMixinElementHandler.ShadowElementName name;
        private final IMapping.Type type;

        protected AnnotatedElementShadow(E e2, AnnotationHandle annotationHandle, boolean bl, IMapping.Type type) {
            super(e2, annotationHandle);
            this.shouldRemap = bl;
            this.name = new AnnotatedMixinElementHandler.ShadowElementName((Element)e2, annotationHandle);
            this.type = type;
        }

        public boolean shouldRemap() {
            return this.shouldRemap;
        }

        public AnnotatedMixinElementHandler.ShadowElementName getName() {
            return this.name;
        }

        public IMapping.Type getElementType() {
            return this.type;
        }

        public String toString() {
            return this.getElementType().name().toLowerCase();
        }

        public AnnotatedMixinElementHandler.ShadowElementName setObfuscatedName(IMapping<?> iMapping) {
            return this.setObfuscatedName(iMapping.getSimpleName());
        }

        public AnnotatedMixinElementHandler.ShadowElementName setObfuscatedName(String string) {
            return this.getName().setObfuscatedName(string);
        }

        public ObfuscationData<M> getObfuscationData(IObfuscationDataProvider iObfuscationDataProvider, TypeHandle typeHandle) {
            return iObfuscationDataProvider.getObfEntry(this.getMapping(typeHandle, this.getName().toString(), this.getDesc()));
        }

        public abstract M getMapping(TypeHandle var1, String var2, String var3);

        public abstract void addMapping(ObfuscationType var1, IMapping<?> var2);
    }
}

