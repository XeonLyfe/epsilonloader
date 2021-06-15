/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import java.util.Iterator;
import java.util.List;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.util.ConstraintParser;
import org.spongepowered.asm.util.throwables.ConstraintViolationException;
import org.spongepowered.asm.util.throwables.InvalidConstraintException;
import org.spongepowered.tools.obfuscation.AnnotatedMixin;
import org.spongepowered.tools.obfuscation.Mappings;
import org.spongepowered.tools.obfuscation.ObfuscationData;
import org.spongepowered.tools.obfuscation.ObfuscationType;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IObfuscationManager;
import org.spongepowered.tools.obfuscation.mapping.IMappingConsumer;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.FieldHandle;
import org.spongepowered.tools.obfuscation.mirror.MethodHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;
import org.spongepowered.tools.obfuscation.mirror.Visibility;

abstract class AnnotatedMixinElementHandler {
    protected final AnnotatedMixin mixin;
    protected final String classRef;
    protected final IMixinAnnotationProcessor ap;
    protected final IObfuscationManager obf;
    private IMappingConsumer mappings;

    AnnotatedMixinElementHandler(IMixinAnnotationProcessor iMixinAnnotationProcessor, AnnotatedMixin annotatedMixin) {
        this.ap = iMixinAnnotationProcessor;
        this.mixin = annotatedMixin;
        this.classRef = annotatedMixin.getClassRef();
        this.obf = iMixinAnnotationProcessor.getObfuscationManager();
    }

    private IMappingConsumer getMappings() {
        if (this.mappings == null) {
            IMappingConsumer iMappingConsumer = this.mixin.getMappings();
            this.mappings = iMappingConsumer instanceof Mappings ? ((Mappings)iMappingConsumer).asUnique() : iMappingConsumer;
        }
        return this.mappings;
    }

    protected final void addFieldMappings(String string, String string2, ObfuscationData<MappingField> obfuscationData) {
        for (ObfuscationType obfuscationType : obfuscationData) {
            MappingField mappingField = obfuscationData.get(obfuscationType);
            this.addFieldMapping(obfuscationType, string, mappingField.getSimpleName(), string2, mappingField.getDesc());
        }
    }

    protected final void addFieldMapping(ObfuscationType obfuscationType, ShadowElementName shadowElementName, String string, String string2) {
        this.addFieldMapping(obfuscationType, shadowElementName.name(), shadowElementName.obfuscated(), string, string2);
    }

    protected final void addFieldMapping(ObfuscationType obfuscationType, String string, String string2, String string3, String string4) {
        MappingField mappingField = new MappingField(this.classRef, string, string3);
        MappingField mappingField2 = new MappingField(this.classRef, string2, string4);
        this.getMappings().addFieldMapping(obfuscationType, mappingField, mappingField2);
    }

    protected final void addMethodMappings(String string, String string2, ObfuscationData<MappingMethod> obfuscationData) {
        for (ObfuscationType obfuscationType : obfuscationData) {
            MappingMethod mappingMethod = obfuscationData.get(obfuscationType);
            this.addMethodMapping(obfuscationType, string, mappingMethod.getSimpleName(), string2, mappingMethod.getDesc());
        }
    }

    protected final void addMethodMapping(ObfuscationType obfuscationType, ShadowElementName shadowElementName, String string, String string2) {
        this.addMethodMapping(obfuscationType, shadowElementName.name(), shadowElementName.obfuscated(), string, string2);
    }

    protected final void addMethodMapping(ObfuscationType obfuscationType, String string, String string2, String string3, String string4) {
        MappingMethod mappingMethod = new MappingMethod(this.classRef, string, string3);
        MappingMethod mappingMethod2 = new MappingMethod(this.classRef, string2, string4);
        this.getMappings().addMethodMapping(obfuscationType, mappingMethod, mappingMethod2);
    }

    protected final void checkConstraints(ExecutableElement executableElement, AnnotationHandle annotationHandle) {
        try {
            ConstraintParser.Constraint constraint = ConstraintParser.parse((String)annotationHandle.getValue("constraints"));
            try {
                constraint.check(this.ap.getTokenProvider());
            }
            catch (ConstraintViolationException constraintViolationException) {
                this.ap.printMessage(Diagnostic.Kind.ERROR, constraintViolationException.getMessage(), executableElement, annotationHandle.asMirror());
            }
        }
        catch (InvalidConstraintException invalidConstraintException) {
            this.ap.printMessage(Diagnostic.Kind.WARNING, invalidConstraintException.getMessage(), executableElement, annotationHandle.asMirror());
        }
    }

    protected final void validateTarget(Element element, AnnotationHandle annotationHandle, AliasedElementName aliasedElementName, String string) {
        if (element instanceof ExecutableElement) {
            this.validateTargetMethod((ExecutableElement)element, annotationHandle, aliasedElementName, string, false, false);
        } else if (element instanceof VariableElement) {
            this.validateTargetField((VariableElement)element, annotationHandle, aliasedElementName, string);
        }
    }

    protected final void validateTargetMethod(ExecutableElement executableElement, AnnotationHandle annotationHandle, AliasedElementName aliasedElementName, String string, boolean bl, boolean bl2) {
        String string2 = TypeUtils.getJavaSignature(executableElement);
        for (TypeHandle typeHandle : this.mixin.getTargets()) {
            if (typeHandle.isImaginary()) continue;
            MethodHandle methodHandle = typeHandle.findMethod(executableElement);
            if (methodHandle == null && aliasedElementName.hasPrefix()) {
                methodHandle = typeHandle.findMethod(aliasedElementName.baseName(), string2);
            }
            if (methodHandle == null && aliasedElementName.hasAliases()) {
                String string3;
                Iterator<String> iterator2 = aliasedElementName.getAliases().iterator();
                while (iterator2.hasNext() && (methodHandle = typeHandle.findMethod(string3 = iterator2.next(), string2)) == null) {
                }
            }
            if (methodHandle != null) {
                if (!bl) continue;
                this.validateMethodVisibility(executableElement, annotationHandle, string, typeHandle, methodHandle);
                continue;
            }
            if (bl2) continue;
            this.printMessage(Diagnostic.Kind.WARNING, "Cannot find target for " + string + " method in " + typeHandle, executableElement, annotationHandle);
        }
    }

    private void validateMethodVisibility(ExecutableElement executableElement, AnnotationHandle annotationHandle, String string, TypeHandle typeHandle, MethodHandle methodHandle) {
        Visibility visibility = methodHandle.getVisibility();
        if (visibility == null) {
            return;
        }
        Visibility visibility2 = TypeUtils.getVisibility(executableElement);
        String string2 = "visibility of " + (Object)((Object)visibility) + " method in " + typeHandle;
        if (visibility.ordinal() > visibility2.ordinal()) {
            this.printMessage(Diagnostic.Kind.WARNING, (Object)((Object)visibility2) + " " + string + " method cannot reduce " + string2, executableElement, annotationHandle);
        } else if (visibility == Visibility.PRIVATE && visibility2.ordinal() > visibility.ordinal()) {
            this.printMessage(Diagnostic.Kind.WARNING, (Object)((Object)visibility2) + " " + string + " method will upgrade " + string2, executableElement, annotationHandle);
        }
    }

    protected final void validateTargetField(VariableElement variableElement, AnnotationHandle annotationHandle, AliasedElementName aliasedElementName, String string) {
        String string2 = variableElement.asType().toString();
        for (TypeHandle typeHandle : this.mixin.getTargets()) {
            String string3;
            FieldHandle fieldHandle;
            if (typeHandle.isImaginary() || (fieldHandle = typeHandle.findField(variableElement)) != null) continue;
            List<String> list = aliasedElementName.getAliases();
            Iterator<String> iterator2 = list.iterator();
            while (iterator2.hasNext() && (fieldHandle = typeHandle.findField(string3 = iterator2.next(), string2)) == null) {
            }
            if (fieldHandle != null) continue;
            this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find target for " + string + " field in " + typeHandle, variableElement, annotationHandle.asMirror());
        }
    }

    protected final void validateReferencedTarget(ExecutableElement executableElement, AnnotationHandle annotationHandle, MemberInfo memberInfo, String string) {
        String string2 = memberInfo.toDescriptor();
        for (TypeHandle typeHandle : this.mixin.getTargets()) {
            MethodHandle methodHandle;
            if (typeHandle.isImaginary() || (methodHandle = typeHandle.findMethod(memberInfo.name, string2)) != null) continue;
            this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find target method for " + string + " in " + typeHandle, executableElement, annotationHandle.asMirror());
        }
    }

    private void printMessage(Diagnostic.Kind kind, String string, Element element, AnnotationHandle annotationHandle) {
        if (annotationHandle == null) {
            this.ap.printMessage(kind, string, element);
        } else {
            this.ap.printMessage(kind, string, element, annotationHandle.asMirror());
        }
    }

    protected static <T extends IMapping<T>> ObfuscationData<T> stripOwnerData(ObfuscationData<T> obfuscationData) {
        ObfuscationData obfuscationData2 = new ObfuscationData();
        for (ObfuscationType obfuscationType : obfuscationData) {
            IMapping iMapping = (IMapping)obfuscationData.get(obfuscationType);
            obfuscationData2.put(obfuscationType, iMapping.move(null));
        }
        return obfuscationData2;
    }

    protected static <T extends IMapping<T>> ObfuscationData<T> stripDescriptors(ObfuscationData<T> obfuscationData) {
        ObfuscationData obfuscationData2 = new ObfuscationData();
        for (ObfuscationType obfuscationType : obfuscationData) {
            IMapping iMapping = (IMapping)obfuscationData.get(obfuscationType);
            obfuscationData2.put(obfuscationType, iMapping.transform(null));
        }
        return obfuscationData2;
    }

    static class ShadowElementName
    extends AliasedElementName {
        private final boolean hasPrefix;
        private final String prefix;
        private final String baseName;
        private String obfuscated;

        ShadowElementName(Element element, AnnotationHandle annotationHandle) {
            super(element, annotationHandle);
            this.prefix = annotationHandle.getValue("prefix", "shadow$");
            boolean bl = false;
            String string = this.originalName;
            if (string.startsWith(this.prefix)) {
                bl = true;
                string = string.substring(this.prefix.length());
            }
            this.hasPrefix = bl;
            this.obfuscated = this.baseName = string;
        }

        public String toString() {
            return this.baseName;
        }

        @Override
        public String baseName() {
            return this.baseName;
        }

        public ShadowElementName setObfuscatedName(IMapping<?> iMapping) {
            this.obfuscated = iMapping.getName();
            return this;
        }

        public ShadowElementName setObfuscatedName(String string) {
            this.obfuscated = string;
            return this;
        }

        @Override
        public boolean hasPrefix() {
            return this.hasPrefix;
        }

        public String prefix() {
            return this.hasPrefix ? this.prefix : "";
        }

        public String name() {
            return this.prefix(this.baseName);
        }

        public String obfuscated() {
            return this.prefix(this.obfuscated);
        }

        public String prefix(String string) {
            return this.hasPrefix ? this.prefix + string : string;
        }
    }

    static class AliasedElementName {
        protected final String originalName;
        private final List<String> aliases;
        private boolean caseSensitive;

        public AliasedElementName(Element element, AnnotationHandle annotationHandle) {
            this.originalName = element.getSimpleName().toString();
            this.aliases = annotationHandle.getList("aliases");
        }

        public AliasedElementName setCaseSensitive(boolean bl) {
            this.caseSensitive = bl;
            return this;
        }

        public boolean isCaseSensitive() {
            return this.caseSensitive;
        }

        public boolean hasAliases() {
            return this.aliases.size() > 0;
        }

        public List<String> getAliases() {
            return this.aliases;
        }

        public String elementName() {
            return this.originalName;
        }

        public String baseName() {
            return this.originalName;
        }

        public boolean hasPrefix() {
            return false;
        }
    }

    static abstract class AnnotatedElement<E extends Element> {
        protected final E element;
        protected final AnnotationHandle annotation;
        private final String desc;

        public AnnotatedElement(E e2, AnnotationHandle annotationHandle) {
            this.element = e2;
            this.annotation = annotationHandle;
            this.desc = TypeUtils.getDescriptor(e2);
        }

        public E getElement() {
            return this.element;
        }

        public AnnotationHandle getAnnotation() {
            return this.annotation;
        }

        public String getSimpleName() {
            return this.getElement().getSimpleName().toString();
        }

        public String getDesc() {
            return this.desc;
        }

        public final void printMessage(Messager messager, Diagnostic.Kind kind, CharSequence charSequence) {
            messager.printMessage(kind, charSequence, (Element)this.element, this.annotation.asMirror());
        }
    }
}

