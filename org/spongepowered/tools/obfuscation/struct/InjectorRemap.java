/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.struct;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.struct.Message;

public class InjectorRemap {
    private final boolean remap;
    private Message message;
    private int remappedCount;

    public InjectorRemap(boolean bl) {
        this.remap = bl;
    }

    public boolean shouldRemap() {
        return this.remap;
    }

    public void notifyRemapped() {
        ++this.remappedCount;
        this.clearMessage();
    }

    public void addMessage(Diagnostic.Kind kind, CharSequence charSequence, Element element, AnnotationHandle annotationHandle) {
        this.message = new Message(kind, charSequence, element, annotationHandle);
    }

    public void clearMessage() {
        this.message = null;
    }

    public void dispatchPendingMessages(Messager messager) {
        if (this.remappedCount == 0 && this.message != null) {
            this.message.sendTo(messager);
        }
    }
}

