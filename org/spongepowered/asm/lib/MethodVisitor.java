/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.TypePath;

public abstract class MethodVisitor {
    protected final int api;
    protected MethodVisitor mv;

    public MethodVisitor(int n2) {
        this(n2, null);
    }

    public MethodVisitor(int n2, MethodVisitor methodVisitor) {
        if (n2 != 262144 && n2 != 327680) {
            throw new IllegalArgumentException();
        }
        this.api = n2;
        this.mv = methodVisitor;
    }

    public void visitParameter(String string, int n2) {
        if (this.api < 327680) {
            throw new RuntimeException();
        }
        if (this.mv != null) {
            this.mv.visitParameter(string, n2);
        }
    }

    public AnnotationVisitor visitAnnotationDefault() {
        if (this.mv != null) {
            return this.mv.visitAnnotationDefault();
        }
        return null;
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        if (this.mv != null) {
            return this.mv.visitAnnotation(string, bl);
        }
        return null;
    }

    public AnnotationVisitor visitTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        if (this.api < 327680) {
            throw new RuntimeException();
        }
        if (this.mv != null) {
            return this.mv.visitTypeAnnotation(n2, typePath, string, bl);
        }
        return null;
    }

    public AnnotationVisitor visitParameterAnnotation(int n2, String string, boolean bl) {
        if (this.mv != null) {
            return this.mv.visitParameterAnnotation(n2, string, bl);
        }
        return null;
    }

    public void visitAttribute(Attribute attribute) {
        if (this.mv != null) {
            this.mv.visitAttribute(attribute);
        }
    }

    public void visitCode() {
        if (this.mv != null) {
            this.mv.visitCode();
        }
    }

    public void visitFrame(int n2, int n3, Object[] arrobject, int n4, Object[] arrobject2) {
        if (this.mv != null) {
            this.mv.visitFrame(n2, n3, arrobject, n4, arrobject2);
        }
    }

    public void visitInsn(int n2) {
        if (this.mv != null) {
            this.mv.visitInsn(n2);
        }
    }

    public void visitIntInsn(int n2, int n3) {
        if (this.mv != null) {
            this.mv.visitIntInsn(n2, n3);
        }
    }

    public void visitVarInsn(int n2, int n3) {
        if (this.mv != null) {
            this.mv.visitVarInsn(n2, n3);
        }
    }

    public void visitTypeInsn(int n2, String string) {
        if (this.mv != null) {
            this.mv.visitTypeInsn(n2, string);
        }
    }

    public void visitFieldInsn(int n2, String string, String string2, String string3) {
        if (this.mv != null) {
            this.mv.visitFieldInsn(n2, string, string2, string3);
        }
    }

    @Deprecated
    public void visitMethodInsn(int n2, String string, String string2, String string3) {
        if (this.api >= 327680) {
            boolean bl = n2 == 185;
            this.visitMethodInsn(n2, string, string2, string3, bl);
            return;
        }
        if (this.mv != null) {
            this.mv.visitMethodInsn(n2, string, string2, string3);
        }
    }

    public void visitMethodInsn(int n2, String string, String string2, String string3, boolean bl) {
        if (this.api < 327680) {
            if (bl != (n2 == 185)) {
                throw new IllegalArgumentException("INVOKESPECIAL/STATIC on interfaces require ASM 5");
            }
            this.visitMethodInsn(n2, string, string2, string3);
            return;
        }
        if (this.mv != null) {
            this.mv.visitMethodInsn(n2, string, string2, string3, bl);
        }
    }

    public void visitInvokeDynamicInsn(String string, String string2, Handle handle, Object ... arrobject) {
        if (this.mv != null) {
            this.mv.visitInvokeDynamicInsn(string, string2, handle, arrobject);
        }
    }

    public void visitJumpInsn(int n2, Label label) {
        if (this.mv != null) {
            this.mv.visitJumpInsn(n2, label);
        }
    }

    public void visitLabel(Label label) {
        if (this.mv != null) {
            this.mv.visitLabel(label);
        }
    }

    public void visitLdcInsn(Object object) {
        if (this.mv != null) {
            this.mv.visitLdcInsn(object);
        }
    }

    public void visitIincInsn(int n2, int n3) {
        if (this.mv != null) {
            this.mv.visitIincInsn(n2, n3);
        }
    }

    public void visitTableSwitchInsn(int n2, int n3, Label label, Label ... arrlabel) {
        if (this.mv != null) {
            this.mv.visitTableSwitchInsn(n2, n3, label, arrlabel);
        }
    }

    public void visitLookupSwitchInsn(Label label, int[] arrn, Label[] arrlabel) {
        if (this.mv != null) {
            this.mv.visitLookupSwitchInsn(label, arrn, arrlabel);
        }
    }

    public void visitMultiANewArrayInsn(String string, int n2) {
        if (this.mv != null) {
            this.mv.visitMultiANewArrayInsn(string, n2);
        }
    }

    public AnnotationVisitor visitInsnAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        if (this.api < 327680) {
            throw new RuntimeException();
        }
        if (this.mv != null) {
            return this.mv.visitInsnAnnotation(n2, typePath, string, bl);
        }
        return null;
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String string) {
        if (this.mv != null) {
            this.mv.visitTryCatchBlock(label, label2, label3, string);
        }
    }

    public AnnotationVisitor visitTryCatchAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        if (this.api < 327680) {
            throw new RuntimeException();
        }
        if (this.mv != null) {
            return this.mv.visitTryCatchAnnotation(n2, typePath, string, bl);
        }
        return null;
    }

    public void visitLocalVariable(String string, String string2, String string3, Label label, Label label2, int n2) {
        if (this.mv != null) {
            this.mv.visitLocalVariable(string, string2, string3, label, label2, n2);
        }
    }

    public AnnotationVisitor visitLocalVariableAnnotation(int n2, TypePath typePath, Label[] arrlabel, Label[] arrlabel2, int[] arrn, String string, boolean bl) {
        if (this.api < 327680) {
            throw new RuntimeException();
        }
        if (this.mv != null) {
            return this.mv.visitLocalVariableAnnotation(n2, typePath, arrlabel, arrlabel2, arrn, string, bl);
        }
        return null;
    }

    public void visitLineNumber(int n2, Label label) {
        if (this.mv != null) {
            this.mv.visitLineNumber(n2, label);
        }
    }

    public void visitMaxs(int n2, int n3) {
        if (this.mv != null) {
            this.mv.visitMaxs(n2, n3);
        }
    }

    public void visitEnd() {
        if (this.mv != null) {
            this.mv.visitEnd();
        }
    }
}

