/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import java.lang.annotation.Annotation;
import java.util.Set;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.tools.obfuscation.MixinObfuscationProcessor;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;

@SupportedAnnotationTypes(value={"org.spongepowered.asm.mixin.injection.Inject", "org.spongepowered.asm.mixin.injection.ModifyArg", "org.spongepowered.asm.mixin.injection.ModifyArgs", "org.spongepowered.asm.mixin.injection.Redirect", "org.spongepowered.asm.mixin.injection.At"})
public class MixinObfuscationProcessorInjection
extends MixinObfuscationProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (roundEnvironment.processingOver()) {
            this.postProcess(roundEnvironment);
            return true;
        }
        this.processMixins(roundEnvironment);
        this.processInjectors(roundEnvironment, Inject.class);
        this.processInjectors(roundEnvironment, ModifyArg.class);
        this.processInjectors(roundEnvironment, ModifyArgs.class);
        this.processInjectors(roundEnvironment, Redirect.class);
        this.processInjectors(roundEnvironment, ModifyVariable.class);
        this.processInjectors(roundEnvironment, ModifyConstant.class);
        this.postProcess(roundEnvironment);
        return true;
    }

    @Override
    protected void postProcess(RoundEnvironment roundEnvironment) {
        super.postProcess(roundEnvironment);
        try {
            this.mixins.writeReferences();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void processInjectors(RoundEnvironment roundEnvironment, Class<? extends Annotation> class_) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(class_)) {
            Element element2 = element.getEnclosingElement();
            if (!(element2 instanceof TypeElement)) {
                throw new IllegalStateException("@" + class_.getSimpleName() + " element has unexpected parent with type " + TypeUtils.getElementType(element2));
            }
            AnnotationHandle annotationHandle = AnnotationHandle.of(element, class_);
            if (element.getKind() == ElementKind.METHOD) {
                this.mixins.registerInjector((TypeElement)element2, (ExecutableElement)element, annotationHandle);
                continue;
            }
            this.mixins.printMessage(Diagnostic.Kind.WARNING, "Found an @" + class_.getSimpleName() + " annotation on an element which is not a method: " + element.toString());
        }
    }
}

