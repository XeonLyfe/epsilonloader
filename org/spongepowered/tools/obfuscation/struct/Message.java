/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.struct;

import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;

public class Message {
    private Diagnostic.Kind kind;
    private CharSequence msg;
    private final Element element;
    private final AnnotationMirror annotation;
    private final AnnotationValue value;

    public Message(Diagnostic.Kind kind, CharSequence charSequence) {
        this(kind, charSequence, null, (AnnotationMirror)null, null);
    }

    public Message(Diagnostic.Kind kind, CharSequence charSequence, Element element) {
        this(kind, charSequence, element, (AnnotationMirror)null, null);
    }

    public Message(Diagnostic.Kind kind, CharSequence charSequence, Element element, AnnotationHandle annotationHandle) {
        this(kind, charSequence, element, annotationHandle.asMirror(), null);
    }

    public Message(Diagnostic.Kind kind, CharSequence charSequence, Element element, AnnotationMirror annotationMirror) {
        this(kind, charSequence, element, annotationMirror, null);
    }

    public Message(Diagnostic.Kind kind, CharSequence charSequence, Element element, AnnotationHandle annotationHandle, AnnotationValue annotationValue) {
        this(kind, charSequence, element, annotationHandle.asMirror(), annotationValue);
    }

    public Message(Diagnostic.Kind kind, CharSequence charSequence, Element element, AnnotationMirror annotationMirror, AnnotationValue annotationValue) {
        this.kind = kind;
        this.msg = charSequence;
        this.element = element;
        this.annotation = annotationMirror;
        this.value = annotationValue;
    }

    public Message sendTo(Messager messager) {
        if (this.value != null) {
            messager.printMessage(this.kind, this.msg, this.element, this.annotation, this.value);
        } else if (this.annotation != null) {
            messager.printMessage(this.kind, this.msg, this.element, this.annotation);
        } else if (this.element != null) {
            messager.printMessage(this.kind, this.msg, this.element);
        } else {
            messager.printMessage(this.kind, this.msg);
        }
        return this;
    }

    public Diagnostic.Kind getKind() {
        return this.kind;
    }

    public Message setKind(Diagnostic.Kind kind) {
        this.kind = kind;
        return this;
    }

    public CharSequence getMsg() {
        return this.msg;
    }

    public Message setMsg(CharSequence charSequence) {
        this.msg = charSequence;
        return this;
    }

    public Element getElement() {
        return this.element;
    }

    public AnnotationMirror getAnnotation() {
        return this.annotation;
    }

    public AnnotationValue getValue() {
        return this.value;
    }
}

