/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.commons;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.commons.Remapper;

public class AnnotationRemapper
extends AnnotationVisitor {
    protected final Remapper remapper;

    public AnnotationRemapper(AnnotationVisitor annotationVisitor, Remapper remapper) {
        this(327680, annotationVisitor, remapper);
    }

    protected AnnotationRemapper(int n2, AnnotationVisitor annotationVisitor, Remapper remapper) {
        super(n2, annotationVisitor);
        this.remapper = remapper;
    }

    public void visit(String string, Object object) {
        this.av.visit(string, this.remapper.mapValue(object));
    }

    public void visitEnum(String string, String string2, String string3) {
        this.av.visitEnum(string, this.remapper.mapDesc(string2), string3);
    }

    public AnnotationVisitor visitAnnotation(String string, String string2) {
        AnnotationVisitor annotationVisitor = this.av.visitAnnotation(string, this.remapper.mapDesc(string2));
        return annotationVisitor == null ? null : (annotationVisitor == this.av ? this : new AnnotationRemapper(annotationVisitor, this.remapper));
    }

    public AnnotationVisitor visitArray(String string) {
        AnnotationVisitor annotationVisitor = this.av.visitArray(string);
        return annotationVisitor == null ? null : (annotationVisitor == this.av ? this : new AnnotationRemapper(annotationVisitor, this.remapper));
    }
}

