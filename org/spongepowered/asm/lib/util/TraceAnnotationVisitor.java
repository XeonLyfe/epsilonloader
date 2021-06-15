/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.util;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.util.Printer;

public final class TraceAnnotationVisitor
extends AnnotationVisitor {
    private final Printer p;

    public TraceAnnotationVisitor(Printer printer) {
        this(null, printer);
    }

    public TraceAnnotationVisitor(AnnotationVisitor annotationVisitor, Printer printer) {
        super(327680, annotationVisitor);
        this.p = printer;
    }

    public void visit(String string, Object object) {
        this.p.visit(string, object);
        super.visit(string, object);
    }

    public void visitEnum(String string, String string2, String string3) {
        this.p.visitEnum(string, string2, string3);
        super.visitEnum(string, string2, string3);
    }

    public AnnotationVisitor visitAnnotation(String string, String string2) {
        Printer printer = this.p.visitAnnotation(string, string2);
        AnnotationVisitor annotationVisitor = this.av == null ? null : this.av.visitAnnotation(string, string2);
        return new TraceAnnotationVisitor(annotationVisitor, printer);
    }

    public AnnotationVisitor visitArray(String string) {
        Printer printer = this.p.visitArray(string);
        AnnotationVisitor annotationVisitor = this.av == null ? null : this.av.visitArray(string);
        return new TraceAnnotationVisitor(annotationVisitor, printer);
    }

    public void visitEnd() {
        this.p.visitAnnotationEnd();
        super.visitEnd();
    }
}

