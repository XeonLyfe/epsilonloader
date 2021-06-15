/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import java.util.Collection;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import org.spongepowered.tools.obfuscation.AnnotatedMixins;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IMixinValidator;
import org.spongepowered.tools.obfuscation.interfaces.IOptionProvider;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

public abstract class MixinValidator
implements IMixinValidator {
    protected final ProcessingEnvironment processingEnv;
    protected final Messager messager;
    protected final IOptionProvider options;
    protected final IMixinValidator.ValidationPass pass;

    public MixinValidator(IMixinAnnotationProcessor iMixinAnnotationProcessor, IMixinValidator.ValidationPass validationPass) {
        this.processingEnv = iMixinAnnotationProcessor.getProcessingEnvironment();
        this.messager = iMixinAnnotationProcessor;
        this.options = iMixinAnnotationProcessor;
        this.pass = validationPass;
    }

    @Override
    public final boolean validate(IMixinValidator.ValidationPass validationPass, TypeElement typeElement, AnnotationHandle annotationHandle, Collection<TypeHandle> collection) {
        if (validationPass != this.pass) {
            return true;
        }
        return this.validate(typeElement, annotationHandle, collection);
    }

    protected abstract boolean validate(TypeElement var1, AnnotationHandle var2, Collection<TypeHandle> var3);

    protected final void note(String string, Element element) {
        this.messager.printMessage(Diagnostic.Kind.NOTE, string, element);
    }

    protected final void warning(String string, Element element) {
        this.messager.printMessage(Diagnostic.Kind.WARNING, string, element);
    }

    protected final void error(String string, Element element) {
        this.messager.printMessage(Diagnostic.Kind.ERROR, string, element);
    }

    protected final Collection<TypeMirror> getMixinsTargeting(TypeMirror typeMirror) {
        return AnnotatedMixins.getMixinsForEnvironment(this.processingEnv).getMixinsTargeting(typeMirror);
    }
}

