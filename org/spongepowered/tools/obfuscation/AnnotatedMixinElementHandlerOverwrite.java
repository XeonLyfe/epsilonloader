/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import java.lang.reflect.Method;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.AnnotatedMixin;
import org.spongepowered.tools.obfuscation.AnnotatedMixinElementHandler;
import org.spongepowered.tools.obfuscation.Mappings;
import org.spongepowered.tools.obfuscation.ObfuscationData;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

class AnnotatedMixinElementHandlerOverwrite
extends AnnotatedMixinElementHandler {
    AnnotatedMixinElementHandlerOverwrite(IMixinAnnotationProcessor iMixinAnnotationProcessor, AnnotatedMixin annotatedMixin) {
        super(iMixinAnnotationProcessor, annotatedMixin);
    }

    public void registerMerge(ExecutableElement executableElement) {
        this.validateTargetMethod(executableElement, null, new AnnotatedMixinElementHandler.AliasedElementName(executableElement, AnnotationHandle.MISSING), "overwrite", true, true);
    }

    public void registerOverwrite(AnnotatedElementOverwrite annotatedElementOverwrite) {
        AnnotatedMixinElementHandler.AliasedElementName aliasedElementName = new AnnotatedMixinElementHandler.AliasedElementName((Element)annotatedElementOverwrite.getElement(), annotatedElementOverwrite.getAnnotation());
        this.validateTargetMethod((ExecutableElement)annotatedElementOverwrite.getElement(), annotatedElementOverwrite.getAnnotation(), aliasedElementName, "@Overwrite", true, false);
        this.checkConstraints((ExecutableElement)annotatedElementOverwrite.getElement(), annotatedElementOverwrite.getAnnotation());
        if (annotatedElementOverwrite.shouldRemap()) {
            for (TypeHandle object : this.mixin.getTargets()) {
                if (this.registerOverwriteForTarget(annotatedElementOverwrite, object)) continue;
                return;
            }
        }
        if (!"true".equalsIgnoreCase(this.ap.getOption("disableOverwriteChecker"))) {
            Object object2 = "error".equalsIgnoreCase(this.ap.getOption("overwriteErrorLevel")) ? Diagnostic.Kind.ERROR : Diagnostic.Kind.WARNING;
            String string = this.ap.getJavadocProvider().getJavadoc((Element)annotatedElementOverwrite.getElement());
            if (string == null) {
                this.ap.printMessage((Diagnostic.Kind)((Object)object2), "@Overwrite is missing javadoc comment", (Element)annotatedElementOverwrite.getElement());
                return;
            }
            if (!string.toLowerCase().contains("@author")) {
                this.ap.printMessage((Diagnostic.Kind)((Object)object2), "@Overwrite is missing an @author tag", (Element)annotatedElementOverwrite.getElement());
            }
            if (!string.toLowerCase().contains("@reason")) {
                this.ap.printMessage((Diagnostic.Kind)((Object)object2), "@Overwrite is missing an @reason tag", (Element)annotatedElementOverwrite.getElement());
            }
        }
    }

    private boolean registerOverwriteForTarget(AnnotatedElementOverwrite annotatedElementOverwrite, TypeHandle typeHandle) {
        MappingMethod mappingMethod = typeHandle.getMappingMethod(annotatedElementOverwrite.getSimpleName(), annotatedElementOverwrite.getDesc());
        ObfuscationData<MappingMethod> obfuscationData = this.obf.getDataProvider().getObfMethod(mappingMethod);
        if (obfuscationData.isEmpty()) {
            Diagnostic.Kind kind = Diagnostic.Kind.ERROR;
            try {
                Method method = ((ExecutableElement)annotatedElementOverwrite.getElement()).getClass().getMethod("isStatic", new Class[0]);
                if (((Boolean)method.invoke(annotatedElementOverwrite.getElement(), new Object[0])).booleanValue()) {
                    kind = Diagnostic.Kind.WARNING;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.ap.printMessage(kind, "No obfuscation mapping for @Overwrite method", (Element)annotatedElementOverwrite.getElement());
            return false;
        }
        try {
            this.addMethodMappings(annotatedElementOverwrite.getSimpleName(), annotatedElementOverwrite.getDesc(), obfuscationData);
        }
        catch (Mappings.MappingConflictException mappingConflictException) {
            annotatedElementOverwrite.printMessage(this.ap, Diagnostic.Kind.ERROR, "Mapping conflict for @Overwrite method: " + mappingConflictException.getNew().getSimpleName() + " for target " + typeHandle + " conflicts with existing mapping " + mappingConflictException.getOld().getSimpleName());
            return false;
        }
        return true;
    }

    static class AnnotatedElementOverwrite
    extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement> {
        private final boolean shouldRemap;

        public AnnotatedElementOverwrite(ExecutableElement executableElement, AnnotationHandle annotationHandle, boolean bl) {
            super(executableElement, annotationHandle);
            this.shouldRemap = bl;
        }

        public boolean shouldRemap() {
            return this.shouldRemap;
        }
    }
}

