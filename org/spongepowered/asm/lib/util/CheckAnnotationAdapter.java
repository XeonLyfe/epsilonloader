/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.util;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.util.CheckMethodAdapter;

public class CheckAnnotationAdapter
extends AnnotationVisitor {
    private final boolean named;
    private boolean end;

    public CheckAnnotationAdapter(AnnotationVisitor annotationVisitor) {
        this(annotationVisitor, true);
    }

    CheckAnnotationAdapter(AnnotationVisitor annotationVisitor, boolean bl) {
        super(327680, annotationVisitor);
        this.named = bl;
    }

    public void visit(String string, Object object) {
        int n2;
        this.checkEnd();
        this.checkName(string);
        if (!(object instanceof Byte || object instanceof Boolean || object instanceof Character || object instanceof Short || object instanceof Integer || object instanceof Long || object instanceof Float || object instanceof Double || object instanceof String || object instanceof Type || object instanceof byte[] || object instanceof boolean[] || object instanceof char[] || object instanceof short[] || object instanceof int[] || object instanceof long[] || object instanceof float[] || object instanceof double[])) {
            throw new IllegalArgumentException("Invalid annotation value");
        }
        if (object instanceof Type && (n2 = ((Type)object).getSort()) == 11) {
            throw new IllegalArgumentException("Invalid annotation value");
        }
        if (this.av != null) {
            this.av.visit(string, object);
        }
    }

    public void visitEnum(String string, String string2, String string3) {
        this.checkEnd();
        this.checkName(string);
        CheckMethodAdapter.checkDesc(string2, false);
        if (string3 == null) {
            throw new IllegalArgumentException("Invalid enum value");
        }
        if (this.av != null) {
            this.av.visitEnum(string, string2, string3);
        }
    }

    public AnnotationVisitor visitAnnotation(String string, String string2) {
        this.checkEnd();
        this.checkName(string);
        CheckMethodAdapter.checkDesc(string2, false);
        return new CheckAnnotationAdapter(this.av == null ? null : this.av.visitAnnotation(string, string2));
    }

    public AnnotationVisitor visitArray(String string) {
        this.checkEnd();
        this.checkName(string);
        return new CheckAnnotationAdapter(this.av == null ? null : this.av.visitArray(string), false);
    }

    public void visitEnd() {
        this.checkEnd();
        this.end = true;
        if (this.av != null) {
            this.av.visitEnd();
        }
    }

    private void checkEnd() {
        if (this.end) {
            throw new IllegalStateException("Cannot call a visit method after visitEnd has been called");
        }
    }

    private void checkName(String string) {
        if (this.named && string == null) {
            throw new IllegalArgumentException("Annotation value name must not be null");
        }
    }
}

