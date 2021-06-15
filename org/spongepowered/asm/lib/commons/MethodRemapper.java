/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.commons;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.TypePath;
import org.spongepowered.asm.lib.commons.AnnotationRemapper;
import org.spongepowered.asm.lib.commons.Remapper;

public class MethodRemapper
extends MethodVisitor {
    protected final Remapper remapper;

    public MethodRemapper(MethodVisitor methodVisitor, Remapper remapper) {
        this(327680, methodVisitor, remapper);
    }

    protected MethodRemapper(int n2, MethodVisitor methodVisitor, Remapper remapper) {
        super(n2, methodVisitor);
        this.remapper = remapper;
    }

    public AnnotationVisitor visitAnnotationDefault() {
        AnnotationVisitor annotationVisitor = super.visitAnnotationDefault();
        return annotationVisitor == null ? annotationVisitor : new AnnotationRemapper(annotationVisitor, this.remapper);
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        AnnotationVisitor annotationVisitor = super.visitAnnotation(this.remapper.mapDesc(string), bl);
        return annotationVisitor == null ? annotationVisitor : new AnnotationRemapper(annotationVisitor, this.remapper);
    }

    public AnnotationVisitor visitTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        AnnotationVisitor annotationVisitor = super.visitTypeAnnotation(n2, typePath, this.remapper.mapDesc(string), bl);
        return annotationVisitor == null ? annotationVisitor : new AnnotationRemapper(annotationVisitor, this.remapper);
    }

    public AnnotationVisitor visitParameterAnnotation(int n2, String string, boolean bl) {
        AnnotationVisitor annotationVisitor = super.visitParameterAnnotation(n2, this.remapper.mapDesc(string), bl);
        return annotationVisitor == null ? annotationVisitor : new AnnotationRemapper(annotationVisitor, this.remapper);
    }

    public void visitFrame(int n2, int n3, Object[] arrobject, int n4, Object[] arrobject2) {
        super.visitFrame(n2, n3, this.remapEntries(n3, arrobject), n4, this.remapEntries(n4, arrobject2));
    }

    private Object[] remapEntries(int n2, Object[] arrobject) {
        for (int i2 = 0; i2 < n2; ++i2) {
            if (!(arrobject[i2] instanceof String)) continue;
            Object[] arrobject2 = new Object[n2];
            if (i2 > 0) {
                System.arraycopy(arrobject, 0, arrobject2, 0, i2);
            }
            do {
                Object object = arrobject[i2];
                Object object2 = arrobject2[i2++] = object instanceof String ? this.remapper.mapType((String)object) : object;
            } while (i2 < n2);
            return arrobject2;
        }
        return arrobject;
    }

    public void visitFieldInsn(int n2, String string, String string2, String string3) {
        super.visitFieldInsn(n2, this.remapper.mapType(string), this.remapper.mapFieldName(string, string2, string3), this.remapper.mapDesc(string3));
    }

    @Deprecated
    public void visitMethodInsn(int n2, String string, String string2, String string3) {
        if (this.api >= 327680) {
            super.visitMethodInsn(n2, string, string2, string3);
            return;
        }
        this.doVisitMethodInsn(n2, string, string2, string3, n2 == 185);
    }

    public void visitMethodInsn(int n2, String string, String string2, String string3, boolean bl) {
        if (this.api < 327680) {
            super.visitMethodInsn(n2, string, string2, string3, bl);
            return;
        }
        this.doVisitMethodInsn(n2, string, string2, string3, bl);
    }

    private void doVisitMethodInsn(int n2, String string, String string2, String string3, boolean bl) {
        if (this.mv != null) {
            this.mv.visitMethodInsn(n2, this.remapper.mapType(string), this.remapper.mapMethodName(string, string2, string3), this.remapper.mapMethodDesc(string3), bl);
        }
    }

    public void visitInvokeDynamicInsn(String string, String string2, Handle handle, Object ... arrobject) {
        for (int i2 = 0; i2 < arrobject.length; ++i2) {
            arrobject[i2] = this.remapper.mapValue(arrobject[i2]);
        }
        super.visitInvokeDynamicInsn(this.remapper.mapInvokeDynamicMethodName(string, string2), this.remapper.mapMethodDesc(string2), (Handle)this.remapper.mapValue(handle), arrobject);
    }

    public void visitTypeInsn(int n2, String string) {
        super.visitTypeInsn(n2, this.remapper.mapType(string));
    }

    public void visitLdcInsn(Object object) {
        super.visitLdcInsn(this.remapper.mapValue(object));
    }

    public void visitMultiANewArrayInsn(String string, int n2) {
        super.visitMultiANewArrayInsn(this.remapper.mapDesc(string), n2);
    }

    public AnnotationVisitor visitInsnAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        AnnotationVisitor annotationVisitor = super.visitInsnAnnotation(n2, typePath, this.remapper.mapDesc(string), bl);
        return annotationVisitor == null ? annotationVisitor : new AnnotationRemapper(annotationVisitor, this.remapper);
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String string) {
        super.visitTryCatchBlock(label, label2, label3, string == null ? null : this.remapper.mapType(string));
    }

    public AnnotationVisitor visitTryCatchAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        AnnotationVisitor annotationVisitor = super.visitTryCatchAnnotation(n2, typePath, this.remapper.mapDesc(string), bl);
        return annotationVisitor == null ? annotationVisitor : new AnnotationRemapper(annotationVisitor, this.remapper);
    }

    public void visitLocalVariable(String string, String string2, String string3, Label label, Label label2, int n2) {
        super.visitLocalVariable(string, this.remapper.mapDesc(string2), this.remapper.mapSignature(string3, true), label, label2, n2);
    }

    public AnnotationVisitor visitLocalVariableAnnotation(int n2, TypePath typePath, Label[] arrlabel, Label[] arrlabel2, int[] arrn, String string, boolean bl) {
        AnnotationVisitor annotationVisitor = super.visitLocalVariableAnnotation(n2, typePath, arrlabel, arrlabel2, arrn, this.remapper.mapDesc(string), bl);
        return annotationVisitor == null ? annotationVisitor : new AnnotationRemapper(annotationVisitor, this.remapper);
    }
}

