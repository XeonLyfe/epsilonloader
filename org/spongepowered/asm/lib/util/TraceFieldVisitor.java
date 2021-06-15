/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.util;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.TypePath;
import org.spongepowered.asm.lib.util.Printer;
import org.spongepowered.asm.lib.util.TraceAnnotationVisitor;

public final class TraceFieldVisitor
extends FieldVisitor {
    public final Printer p;

    public TraceFieldVisitor(Printer printer) {
        this(null, printer);
    }

    public TraceFieldVisitor(FieldVisitor fieldVisitor, Printer printer) {
        super(327680, fieldVisitor);
        this.p = printer;
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        Printer printer = this.p.visitFieldAnnotation(string, bl);
        AnnotationVisitor annotationVisitor = this.fv == null ? null : this.fv.visitAnnotation(string, bl);
        return new TraceAnnotationVisitor(annotationVisitor, printer);
    }

    public AnnotationVisitor visitTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        Printer printer = this.p.visitFieldTypeAnnotation(n2, typePath, string, bl);
        AnnotationVisitor annotationVisitor = this.fv == null ? null : this.fv.visitTypeAnnotation(n2, typePath, string, bl);
        return new TraceAnnotationVisitor(annotationVisitor, printer);
    }

    public void visitAttribute(Attribute attribute) {
        this.p.visitFieldAttribute(attribute);
        super.visitAttribute(attribute);
    }

    public void visitEnd() {
        this.p.visitFieldEnd();
        super.visitEnd();
    }
}

