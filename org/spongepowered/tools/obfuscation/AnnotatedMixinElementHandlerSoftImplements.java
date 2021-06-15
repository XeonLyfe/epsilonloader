/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import java.util.List;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.AnnotatedMixin;
import org.spongepowered.tools.obfuscation.AnnotatedMixinElementHandler;
import org.spongepowered.tools.obfuscation.ObfuscationData;
import org.spongepowered.tools.obfuscation.ObfuscationType;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.MethodHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;

public class AnnotatedMixinElementHandlerSoftImplements
extends AnnotatedMixinElementHandler {
    AnnotatedMixinElementHandlerSoftImplements(IMixinAnnotationProcessor iMixinAnnotationProcessor, AnnotatedMixin annotatedMixin) {
        super(iMixinAnnotationProcessor, annotatedMixin);
    }

    public void process(AnnotationHandle annotationHandle) {
        if (!this.mixin.remap()) {
            return;
        }
        List<AnnotationHandle> list = annotationHandle.getAnnotationList("value");
        if (list.size() < 1) {
            this.ap.printMessage(Diagnostic.Kind.WARNING, "Empty @Implements annotation", this.mixin.getMixin(), annotationHandle.asMirror());
            return;
        }
        for (AnnotationHandle annotationHandle2 : list) {
            Interface.Remap remap = annotationHandle2.getValue("remap", Interface.Remap.ALL);
            if (remap == Interface.Remap.NONE) continue;
            try {
                TypeHandle typeHandle = new TypeHandle((DeclaredType)annotationHandle2.getValue("iface"));
                String string = (String)annotationHandle2.getValue("prefix");
                this.processSoftImplements(remap, typeHandle, string);
            }
            catch (Exception exception) {
                this.ap.printMessage(Diagnostic.Kind.ERROR, "Unexpected error: " + exception.getClass().getName() + ": " + exception.getMessage(), this.mixin.getMixin(), annotationHandle2.asMirror());
            }
        }
    }

    private void processSoftImplements(Interface.Remap remap, TypeHandle typeHandle, String string) {
        for (ExecutableElement object : typeHandle.getEnclosedElements(ElementKind.METHOD)) {
            this.processMethod(remap, typeHandle, string, object);
        }
        for (TypeHandle typeHandle2 : typeHandle.getInterfaces()) {
            this.processSoftImplements(remap, typeHandle2, string);
        }
    }

    private void processMethod(Interface.Remap remap, TypeHandle typeHandle, String string, ExecutableElement executableElement) {
        MethodHandle methodHandle;
        String string2 = executableElement.getSimpleName().toString();
        String string3 = TypeUtils.getJavaSignature(executableElement);
        String string4 = TypeUtils.getDescriptor(executableElement);
        if (remap != Interface.Remap.ONLY_PREFIXED && (methodHandle = this.mixin.getHandle().findMethod(string2, string3)) != null) {
            this.addInterfaceMethodMapping(remap, typeHandle, null, methodHandle, string2, string4);
        }
        if (string != null && (methodHandle = this.mixin.getHandle().findMethod(string + string2, string3)) != null) {
            this.addInterfaceMethodMapping(remap, typeHandle, string, methodHandle, string2, string4);
        }
    }

    private void addInterfaceMethodMapping(Interface.Remap remap, TypeHandle typeHandle, String string, MethodHandle methodHandle, String string2, String string3) {
        MappingMethod mappingMethod = new MappingMethod(typeHandle.getName(), string2, string3);
        ObfuscationData<MappingMethod> obfuscationData = this.obf.getDataProvider().getObfMethod(mappingMethod);
        if (obfuscationData.isEmpty()) {
            if (remap.forceRemap()) {
                this.ap.printMessage(Diagnostic.Kind.ERROR, "No obfuscation mapping for soft-implementing method", methodHandle.getElement());
            }
            return;
        }
        this.addMethodMappings(methodHandle.getName(), string3, this.applyPrefix(obfuscationData, string));
    }

    private ObfuscationData<MappingMethod> applyPrefix(ObfuscationData<MappingMethod> obfuscationData, String string) {
        if (string == null) {
            return obfuscationData;
        }
        ObfuscationData<MappingMethod> obfuscationData2 = new ObfuscationData<MappingMethod>();
        for (ObfuscationType obfuscationType : obfuscationData) {
            MappingMethod mappingMethod = obfuscationData.get(obfuscationType);
            obfuscationData2.put(obfuscationType, mappingMethod.addPrefix(string));
        }
        return obfuscationData2;
    }
}

