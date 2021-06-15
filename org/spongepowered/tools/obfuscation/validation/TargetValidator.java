/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.validation;

import java.util.Collection;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.tools.obfuscation.MixinValidator;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IMixinValidator;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;

public class TargetValidator
extends MixinValidator {
    public TargetValidator(IMixinAnnotationProcessor iMixinAnnotationProcessor) {
        super(iMixinAnnotationProcessor, IMixinValidator.ValidationPass.LATE);
    }

    @Override
    public boolean validate(TypeElement typeElement, AnnotationHandle annotationHandle, Collection<TypeHandle> collection) {
        if ("true".equalsIgnoreCase(this.options.getOption("disableTargetValidator"))) {
            return true;
        }
        if (typeElement.getKind() == ElementKind.INTERFACE) {
            this.validateInterfaceMixin(typeElement, collection);
        } else {
            this.validateClassMixin(typeElement, collection);
        }
        return true;
    }

    private void validateInterfaceMixin(TypeElement typeElement, Collection<TypeHandle> collection) {
        boolean bl = false;
        for (Element object : typeElement.getEnclosedElements()) {
            if (object.getKind() != ElementKind.METHOD) continue;
            boolean bl2 = AnnotationHandle.of(object, Accessor.class).exists();
            boolean bl3 = AnnotationHandle.of(object, Invoker.class).exists();
            bl |= !bl2 && !bl3;
        }
        if (!bl) {
            return;
        }
        for (TypeHandle typeHandle : collection) {
            TypeElement typeElement2 = typeHandle.getElement();
            if (typeElement2 == null || typeElement2.getKind() == ElementKind.INTERFACE) continue;
            this.error("Targetted type '" + typeHandle + " of " + typeElement + " is not an interface", typeElement);
        }
    }

    private void validateClassMixin(TypeElement typeElement, Collection<TypeHandle> collection) {
        TypeMirror typeMirror = typeElement.getSuperclass();
        for (TypeHandle typeHandle : collection) {
            TypeMirror typeMirror2 = typeHandle.getType();
            if (typeMirror2 == null || this.validateSuperClass(typeMirror2, typeMirror)) continue;
            this.error("Superclass " + typeMirror + " of " + typeElement + " was not found in the hierarchy of target class " + typeMirror2, typeElement);
        }
    }

    private boolean validateSuperClass(TypeMirror typeMirror, TypeMirror typeMirror2) {
        if (TypeUtils.isAssignable(this.processingEnv, typeMirror, typeMirror2)) {
            return true;
        }
        return this.validateSuperClassRecursive(typeMirror, typeMirror2);
    }

    private boolean validateSuperClassRecursive(TypeMirror typeMirror, TypeMirror typeMirror2) {
        if (!(typeMirror instanceof DeclaredType)) {
            return false;
        }
        if (TypeUtils.isAssignable(this.processingEnv, typeMirror, typeMirror2)) {
            return true;
        }
        TypeElement typeElement = (TypeElement)((DeclaredType)typeMirror).asElement();
        TypeMirror typeMirror3 = typeElement.getSuperclass();
        if (typeMirror3.getKind() == TypeKind.NONE) {
            return false;
        }
        if (this.checkMixinsFor(typeMirror3, typeMirror2)) {
            return true;
        }
        return this.validateSuperClassRecursive(typeMirror3, typeMirror2);
    }

    private boolean checkMixinsFor(TypeMirror typeMirror, TypeMirror typeMirror2) {
        for (TypeMirror typeMirror3 : this.getMixinsTargeting(typeMirror)) {
            if (!TypeUtils.isAssignable(this.processingEnv, typeMirror3, typeMirror2)) continue;
            return true;
        }
        return false;
    }
}

