/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import java.util.Set;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.tools.obfuscation.MixinObfuscationProcessor;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;

@SupportedAnnotationTypes(value={"org.spongepowered.asm.mixin.Mixin", "org.spongepowered.asm.mixin.Shadow", "org.spongepowered.asm.mixin.Overwrite", "org.spongepowered.asm.mixin.gen.Accessor", "org.spongepowered.asm.mixin.Implements"})
public class MixinObfuscationProcessorTargets
extends MixinObfuscationProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (roundEnvironment.processingOver()) {
            this.postProcess(roundEnvironment);
            return true;
        }
        this.processMixins(roundEnvironment);
        this.processShadows(roundEnvironment);
        this.processOverwrites(roundEnvironment);
        this.processAccessors(roundEnvironment);
        this.processInvokers(roundEnvironment);
        this.processImplements(roundEnvironment);
        this.postProcess(roundEnvironment);
        return true;
    }

    @Override
    protected void postProcess(RoundEnvironment roundEnvironment) {
        super.postProcess(roundEnvironment);
        try {
            this.mixins.writeReferences();
            this.mixins.writeMappings();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void processShadows(RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Shadow.class)) {
            Element element2 = element.getEnclosingElement();
            if (!(element2 instanceof TypeElement)) {
                this.mixins.printMessage(Diagnostic.Kind.ERROR, "Unexpected parent with type " + TypeUtils.getElementType(element2), element);
                continue;
            }
            AnnotationHandle annotationHandle = AnnotationHandle.of(element, Shadow.class);
            if (element.getKind() == ElementKind.FIELD) {
                this.mixins.registerShadow((TypeElement)element2, (VariableElement)element, annotationHandle);
                continue;
            }
            if (element.getKind() == ElementKind.METHOD) {
                this.mixins.registerShadow((TypeElement)element2, (ExecutableElement)element, annotationHandle);
                continue;
            }
            this.mixins.printMessage(Diagnostic.Kind.ERROR, "Element is not a method or field", element);
        }
    }

    private void processOverwrites(RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Overwrite.class)) {
            Element element2 = element.getEnclosingElement();
            if (!(element2 instanceof TypeElement)) {
                this.mixins.printMessage(Diagnostic.Kind.ERROR, "Unexpected parent with type " + TypeUtils.getElementType(element2), element);
                continue;
            }
            if (element.getKind() == ElementKind.METHOD) {
                this.mixins.registerOverwrite((TypeElement)element2, (ExecutableElement)element);
                continue;
            }
            this.mixins.printMessage(Diagnostic.Kind.ERROR, "Element is not a method", element);
        }
    }

    private void processAccessors(RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Accessor.class)) {
            Element element2 = element.getEnclosingElement();
            if (!(element2 instanceof TypeElement)) {
                this.mixins.printMessage(Diagnostic.Kind.ERROR, "Unexpected parent with type " + TypeUtils.getElementType(element2), element);
                continue;
            }
            if (element.getKind() == ElementKind.METHOD) {
                this.mixins.registerAccessor((TypeElement)element2, (ExecutableElement)element);
                continue;
            }
            this.mixins.printMessage(Diagnostic.Kind.ERROR, "Element is not a method", element);
        }
    }

    private void processInvokers(RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Invoker.class)) {
            Element element2 = element.getEnclosingElement();
            if (!(element2 instanceof TypeElement)) {
                this.mixins.printMessage(Diagnostic.Kind.ERROR, "Unexpected parent with type " + TypeUtils.getElementType(element2), element);
                continue;
            }
            if (element.getKind() == ElementKind.METHOD) {
                this.mixins.registerInvoker((TypeElement)element2, (ExecutableElement)element);
                continue;
            }
            this.mixins.printMessage(Diagnostic.Kind.ERROR, "Element is not a method", element);
        }
    }

    private void processImplements(RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Implements.class)) {
            if (element.getKind() == ElementKind.CLASS || element.getKind() == ElementKind.INTERFACE) {
                AnnotationHandle annotationHandle = AnnotationHandle.of(element, Implements.class);
                this.mixins.registerSoftImplements((TypeElement)element, annotationHandle);
                continue;
            }
            this.mixins.printMessage(Diagnostic.Kind.ERROR, "Found an @Implements annotation on an element which is not a class or interface", element);
        }
    }
}

