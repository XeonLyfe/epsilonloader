/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.struct.InvalidMemberDescriptorException;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.AnnotatedMixin;
import org.spongepowered.tools.obfuscation.AnnotatedMixinElementHandler;
import org.spongepowered.tools.obfuscation.ObfuscationData;
import org.spongepowered.tools.obfuscation.ObfuscationType;
import org.spongepowered.tools.obfuscation.ReferenceManager;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IReferenceManager;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.struct.InjectorRemap;

class AnnotatedMixinElementHandlerInjector
extends AnnotatedMixinElementHandler {
    AnnotatedMixinElementHandlerInjector(IMixinAnnotationProcessor iMixinAnnotationProcessor, AnnotatedMixin annotatedMixin) {
        super(iMixinAnnotationProcessor, annotatedMixin);
    }

    public void registerInjector(AnnotatedElementInjector annotatedElementInjector) {
        if (this.mixin.isInterface()) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Injector in interface is unsupported", (Element)annotatedElementInjector.getElement());
        }
        for (String string : annotatedElementInjector.getAnnotation().getList("method")) {
            MemberInfo memberInfo = MemberInfo.parse(string);
            if (memberInfo.name == null) continue;
            try {
                memberInfo.validate();
            }
            catch (InvalidMemberDescriptorException invalidMemberDescriptorException) {
                annotatedElementInjector.printMessage(this.ap, Diagnostic.Kind.ERROR, invalidMemberDescriptorException.getMessage());
            }
            if (memberInfo.desc != null) {
                this.validateReferencedTarget((ExecutableElement)annotatedElementInjector.getElement(), annotatedElementInjector.getAnnotation(), memberInfo, annotatedElementInjector.toString());
            }
            if (!annotatedElementInjector.shouldRemap()) continue;
            for (TypeHandle typeHandle : this.mixin.getTargets()) {
                if (!this.registerInjector(annotatedElementInjector, string, memberInfo, typeHandle)) break;
            }
        }
    }

    private boolean registerInjector(AnnotatedElementInjector annotatedElementInjector, String string, MemberInfo memberInfo, TypeHandle typeHandle) {
        String string2 = typeHandle.findDescriptor(memberInfo);
        if (string2 == null) {
            Diagnostic.Kind kind;
            Diagnostic.Kind kind2 = kind = this.mixin.isMultiTarget() ? Diagnostic.Kind.ERROR : Diagnostic.Kind.WARNING;
            if (typeHandle.isSimulated()) {
                annotatedElementInjector.printMessage(this.ap, Diagnostic.Kind.NOTE, annotatedElementInjector + " target '" + string + "' in @Pseudo mixin will not be obfuscated");
            } else if (typeHandle.isImaginary()) {
                annotatedElementInjector.printMessage(this.ap, kind, annotatedElementInjector + " target requires method signature because enclosing type information for " + typeHandle + " is unavailable");
            } else if (!memberInfo.isInitialiser()) {
                annotatedElementInjector.printMessage(this.ap, kind, "Unable to determine signature for " + annotatedElementInjector + " target method");
            }
            return true;
        }
        String string3 = annotatedElementInjector + " target " + memberInfo.name;
        MappingMethod mappingMethod = typeHandle.getMappingMethod(memberInfo.name, string2);
        ObfuscationData<MappingMethod> obfuscationData = this.obf.getDataProvider().getObfMethod(mappingMethod);
        if (obfuscationData.isEmpty()) {
            if (typeHandle.isSimulated()) {
                obfuscationData = this.obf.getDataProvider().getRemappedMethod(mappingMethod);
            } else {
                if (memberInfo.isClassInitialiser()) {
                    return true;
                }
                Diagnostic.Kind kind = memberInfo.isConstructor() ? Diagnostic.Kind.WARNING : Diagnostic.Kind.ERROR;
                annotatedElementInjector.addMessage(kind, "No obfuscation mapping for " + string3, (Element)annotatedElementInjector.getElement(), annotatedElementInjector.getAnnotation());
                return false;
            }
        }
        IReferenceManager iReferenceManager = this.obf.getReferenceManager();
        try {
            if (memberInfo.owner == null && this.mixin.isMultiTarget() || typeHandle.isSimulated()) {
                obfuscationData = AnnotatedMixinElementHandler.stripOwnerData(obfuscationData);
            }
            iReferenceManager.addMethodMapping(this.classRef, string, obfuscationData);
        }
        catch (ReferenceManager.ReferenceConflictException referenceConflictException) {
            String string4;
            String string5 = string4 = this.mixin.isMultiTarget() ? "Multi-target" : "Target";
            if (annotatedElementInjector.hasCoerceArgument() && memberInfo.owner == null && memberInfo.desc == null) {
                MemberInfo memberInfo2 = MemberInfo.parse(referenceConflictException.getOld());
                MemberInfo memberInfo3 = MemberInfo.parse(referenceConflictException.getNew());
                if (memberInfo2.name.equals(memberInfo3.name)) {
                    obfuscationData = AnnotatedMixinElementHandler.stripDescriptors(obfuscationData);
                    iReferenceManager.setAllowConflicts(true);
                    iReferenceManager.addMethodMapping(this.classRef, string, obfuscationData);
                    iReferenceManager.setAllowConflicts(false);
                    annotatedElementInjector.printMessage(this.ap, Diagnostic.Kind.WARNING, "Coerced " + string4 + " reference has conflicting descriptors for " + string3 + ": Storing bare references " + obfuscationData.values() + " in refMap");
                    return true;
                }
            }
            annotatedElementInjector.printMessage(this.ap, Diagnostic.Kind.ERROR, string4 + " reference conflict for " + string3 + ": " + string + " -> " + referenceConflictException.getNew() + " previously defined as " + referenceConflictException.getOld());
        }
        return true;
    }

    public void registerInjectionPoint(AnnotatedElementInjectionPoint annotatedElementInjectionPoint, String string) {
        if (this.mixin.isInterface()) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Injector in interface is unsupported", (Element)annotatedElementInjectionPoint.getElement());
        }
        if (!annotatedElementInjectionPoint.shouldRemap()) {
            return;
        }
        String string2 = InjectionPointData.parseType((String)annotatedElementInjectionPoint.getAt().getValue("value"));
        String string3 = (String)annotatedElementInjectionPoint.getAt().getValue("target");
        if ("NEW".equals(string2)) {
            this.remapNewTarget(String.format(string, string2 + ".<target>"), string3, annotatedElementInjectionPoint);
            this.remapNewTarget(String.format(string, string2 + ".args[class]"), annotatedElementInjectionPoint.getAtArg("class"), annotatedElementInjectionPoint);
        } else {
            this.remapReference(String.format(string, string2 + ".<target>"), string3, annotatedElementInjectionPoint);
        }
    }

    protected final void remapNewTarget(String string, String string2, AnnotatedElementInjectionPoint annotatedElementInjectionPoint) {
        if (string2 == null) {
            return;
        }
        MemberInfo memberInfo = MemberInfo.parse(string2);
        String string3 = memberInfo.toCtorType();
        if (string3 != null) {
            String string4 = memberInfo.toCtorDesc();
            MappingMethod mappingMethod = new MappingMethod(string3, ".", string4 != null ? string4 : "()V");
            ObfuscationData<MappingMethod> obfuscationData = this.obf.getDataProvider().getRemappedMethod(mappingMethod);
            if (obfuscationData.isEmpty()) {
                this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find class mapping for " + string + " '" + string3 + "'", (Element)annotatedElementInjectionPoint.getElement(), annotatedElementInjectionPoint.getAnnotation().asMirror());
                return;
            }
            ObfuscationData<String> obfuscationData2 = new ObfuscationData<String>();
            for (ObfuscationType obfuscationType : obfuscationData) {
                MappingMethod mappingMethod2 = obfuscationData.get(obfuscationType);
                if (string4 == null) {
                    obfuscationData2.put(obfuscationType, mappingMethod2.getOwner());
                    continue;
                }
                obfuscationData2.put(obfuscationType, mappingMethod2.getDesc().replace(")V", ")L" + mappingMethod2.getOwner() + ";"));
            }
            this.obf.getReferenceManager().addClassMapping(this.classRef, string2, obfuscationData2);
        }
        annotatedElementInjectionPoint.notifyRemapped();
    }

    protected final void remapReference(String string, String string2, AnnotatedElementInjectionPoint annotatedElementInjectionPoint) {
        if (string2 == null) {
            return;
        }
        AnnotationMirror annotationMirror = (this.ap.getCompilerEnvironment() == IMixinAnnotationProcessor.CompilerEnvironment.JDT ? annotatedElementInjectionPoint.getAt() : annotatedElementInjectionPoint.getAnnotation()).asMirror();
        MemberInfo memberInfo = MemberInfo.parse(string2);
        if (!memberInfo.isFullyQualified()) {
            String string3 = memberInfo.owner == null ? (memberInfo.desc == null ? "owner and signature" : "owner") : "signature";
            this.ap.printMessage(Diagnostic.Kind.ERROR, string + " is not fully qualified, missing " + string3, (Element)annotatedElementInjectionPoint.getElement(), annotationMirror);
            return;
        }
        try {
            memberInfo.validate();
        }
        catch (InvalidMemberDescriptorException invalidMemberDescriptorException) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, invalidMemberDescriptorException.getMessage(), (Element)annotatedElementInjectionPoint.getElement(), annotationMirror);
        }
        try {
            if (memberInfo.isField()) {
                ObfuscationData<MappingField> obfuscationData = this.obf.getDataProvider().getObfFieldRecursive(memberInfo);
                if (obfuscationData.isEmpty()) {
                    this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find field mapping for " + string + " '" + string2 + "'", (Element)annotatedElementInjectionPoint.getElement(), annotationMirror);
                    return;
                }
                this.obf.getReferenceManager().addFieldMapping(this.classRef, string2, memberInfo, obfuscationData);
            } else {
                ObfuscationData<MappingMethod> obfuscationData = this.obf.getDataProvider().getObfMethodRecursive(memberInfo);
                if (obfuscationData.isEmpty() && (memberInfo.owner == null || !memberInfo.owner.startsWith("java/lang/"))) {
                    this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find method mapping for " + string + " '" + string2 + "'", (Element)annotatedElementInjectionPoint.getElement(), annotationMirror);
                    return;
                }
                this.obf.getReferenceManager().addMethodMapping(this.classRef, string2, memberInfo, obfuscationData);
            }
        }
        catch (ReferenceManager.ReferenceConflictException referenceConflictException) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Unexpected reference conflict for " + string + ": " + string2 + " -> " + referenceConflictException.getNew() + " previously defined as " + referenceConflictException.getOld(), (Element)annotatedElementInjectionPoint.getElement(), annotationMirror);
            return;
        }
        annotatedElementInjectionPoint.notifyRemapped();
    }

    static class AnnotatedElementInjectionPoint
    extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement> {
        private final AnnotationHandle at;
        private Map<String, String> args;
        private final InjectorRemap state;

        public AnnotatedElementInjectionPoint(ExecutableElement executableElement, AnnotationHandle annotationHandle, AnnotationHandle annotationHandle2, InjectorRemap injectorRemap) {
            super(executableElement, annotationHandle);
            this.at = annotationHandle2;
            this.state = injectorRemap;
        }

        public boolean shouldRemap() {
            return this.at.getBoolean("remap", this.state.shouldRemap());
        }

        public AnnotationHandle getAt() {
            return this.at;
        }

        public String getAtArg(String string) {
            if (this.args == null) {
                this.args = new HashMap<String, String>();
                for (String string2 : this.at.getList("args")) {
                    if (string2 == null) continue;
                    int n2 = string2.indexOf(61);
                    if (n2 > -1) {
                        this.args.put(string2.substring(0, n2), string2.substring(n2 + 1));
                        continue;
                    }
                    this.args.put(string2, "");
                }
            }
            return this.args.get(string);
        }

        public void notifyRemapped() {
            this.state.notifyRemapped();
        }
    }

    static class AnnotatedElementInjector
    extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement> {
        private final InjectorRemap state;

        public AnnotatedElementInjector(ExecutableElement executableElement, AnnotationHandle annotationHandle, InjectorRemap injectorRemap) {
            super(executableElement, annotationHandle);
            this.state = injectorRemap;
        }

        public boolean shouldRemap() {
            return this.state.shouldRemap();
        }

        public boolean hasCoerceArgument() {
            if (!this.annotation.toString().equals("@Inject")) {
                return false;
            }
            Iterator<? extends VariableElement> iterator2 = ((ExecutableElement)this.element).getParameters().iterator();
            if (iterator2.hasNext()) {
                VariableElement variableElement = iterator2.next();
                return AnnotationHandle.of(variableElement, Coerce.class).exists();
            }
            return false;
        }

        public void addMessage(Diagnostic.Kind kind, CharSequence charSequence, Element element, AnnotationHandle annotationHandle) {
            this.state.addMessage(kind, charSequence, element, annotationHandle);
        }

        public String toString() {
            return this.getAnnotation().toString();
        }
    }
}

