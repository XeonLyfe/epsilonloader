/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.util;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.TypePath;
import org.spongepowered.asm.lib.util.ASMifiable;
import org.spongepowered.asm.lib.util.Printer;
import org.spongepowered.asm.lib.util.TraceClassVisitor;

public class ASMifier
extends Printer {
    protected final String name;
    protected final int id;
    protected Map<Label, String> labelNames;
    private static final int ACCESS_CLASS = 262144;
    private static final int ACCESS_FIELD = 524288;
    private static final int ACCESS_INNER = 0x100000;

    public ASMifier() {
        this(327680, "cw", 0);
        if (this.getClass() != ASMifier.class) {
            throw new IllegalStateException();
        }
    }

    protected ASMifier(int n2, String string, int n3) {
        super(n2);
        this.name = string;
        this.id = n3;
    }

    public static void main(String[] arrstring) throws Exception {
        int n2 = 0;
        int n3 = 2;
        boolean bl = true;
        if (arrstring.length < 1 || arrstring.length > 2) {
            bl = false;
        }
        if (bl && "-debug".equals(arrstring[0])) {
            n2 = 1;
            n3 = 0;
            if (arrstring.length != 2) {
                bl = false;
            }
        }
        if (!bl) {
            System.err.println("Prints the ASM code to generate the given class.");
            System.err.println("Usage: ASMifier [-debug] <fully qualified class name or class file name>");
            return;
        }
        ClassReader classReader = arrstring[n2].endsWith(".class") || arrstring[n2].indexOf(92) > -1 || arrstring[n2].indexOf(47) > -1 ? new ClassReader(new FileInputStream(arrstring[n2])) : new ClassReader(arrstring[n2]);
        classReader.accept(new TraceClassVisitor(null, new ASMifier(), new PrintWriter(System.out)), n3);
    }

    public void visit(int n2, int n3, String string, String string2, String string3, String[] arrstring) {
        String string4;
        int n4 = string.lastIndexOf(47);
        if (n4 == -1) {
            string4 = string;
        } else {
            this.text.add("package asm." + string.substring(0, n4).replace('/', '.') + ";\n");
            string4 = string.substring(n4 + 1);
        }
        this.text.add("import java.util.*;\n");
        this.text.add("import org.objectweb.asm.*;\n");
        this.text.add("public class " + string4 + "Dump implements Opcodes {\n\n");
        this.text.add("public static byte[] dump () throws Exception {\n\n");
        this.text.add("ClassWriter cw = new ClassWriter(0);\n");
        this.text.add("FieldVisitor fv;\n");
        this.text.add("MethodVisitor mv;\n");
        this.text.add("AnnotationVisitor av0;\n\n");
        this.buf.setLength(0);
        this.buf.append("cw.visit(");
        switch (n2) {
            case 196653: {
                this.buf.append("V1_1");
                break;
            }
            case 46: {
                this.buf.append("V1_2");
                break;
            }
            case 47: {
                this.buf.append("V1_3");
                break;
            }
            case 48: {
                this.buf.append("V1_4");
                break;
            }
            case 49: {
                this.buf.append("V1_5");
                break;
            }
            case 50: {
                this.buf.append("V1_6");
                break;
            }
            case 51: {
                this.buf.append("V1_7");
                break;
            }
            default: {
                this.buf.append(n2);
            }
        }
        this.buf.append(", ");
        this.appendAccess(n3 | 0x40000);
        this.buf.append(", ");
        this.appendConstant(string);
        this.buf.append(", ");
        this.appendConstant(string2);
        this.buf.append(", ");
        this.appendConstant(string3);
        this.buf.append(", ");
        if (arrstring != null && arrstring.length > 0) {
            this.buf.append("new String[] {");
            for (int i2 = 0; i2 < arrstring.length; ++i2) {
                this.buf.append(i2 == 0 ? " " : ", ");
                this.appendConstant(arrstring[i2]);
            }
            this.buf.append(" }");
        } else {
            this.buf.append("null");
        }
        this.buf.append(");\n\n");
        this.text.add(this.buf.toString());
    }

    public void visitSource(String string, String string2) {
        this.buf.setLength(0);
        this.buf.append("cw.visitSource(");
        this.appendConstant(string);
        this.buf.append(", ");
        this.appendConstant(string2);
        this.buf.append(");\n\n");
        this.text.add(this.buf.toString());
    }

    public void visitOuterClass(String string, String string2, String string3) {
        this.buf.setLength(0);
        this.buf.append("cw.visitOuterClass(");
        this.appendConstant(string);
        this.buf.append(", ");
        this.appendConstant(string2);
        this.buf.append(", ");
        this.appendConstant(string3);
        this.buf.append(");\n\n");
        this.text.add(this.buf.toString());
    }

    public ASMifier visitClassAnnotation(String string, boolean bl) {
        return this.visitAnnotation(string, bl);
    }

    public ASMifier visitClassTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        return this.visitTypeAnnotation(n2, typePath, string, bl);
    }

    public void visitClassAttribute(Attribute attribute) {
        this.visitAttribute(attribute);
    }

    public void visitInnerClass(String string, String string2, String string3, int n2) {
        this.buf.setLength(0);
        this.buf.append("cw.visitInnerClass(");
        this.appendConstant(string);
        this.buf.append(", ");
        this.appendConstant(string2);
        this.buf.append(", ");
        this.appendConstant(string3);
        this.buf.append(", ");
        this.appendAccess(n2 | 0x100000);
        this.buf.append(");\n\n");
        this.text.add(this.buf.toString());
    }

    public ASMifier visitField(int n2, String string, String string2, String string3, Object object) {
        this.buf.setLength(0);
        this.buf.append("{\n");
        this.buf.append("fv = cw.visitField(");
        this.appendAccess(n2 | 0x80000);
        this.buf.append(", ");
        this.appendConstant(string);
        this.buf.append(", ");
        this.appendConstant(string2);
        this.buf.append(", ");
        this.appendConstant(string3);
        this.buf.append(", ");
        this.appendConstant(object);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
        ASMifier aSMifier = this.createASMifier("fv", 0);
        this.text.add(aSMifier.getText());
        this.text.add("}\n");
        return aSMifier;
    }

    public ASMifier visitMethod(int n2, String string, String string2, String string3, String[] arrstring) {
        this.buf.setLength(0);
        this.buf.append("{\n");
        this.buf.append("mv = cw.visitMethod(");
        this.appendAccess(n2);
        this.buf.append(", ");
        this.appendConstant(string);
        this.buf.append(", ");
        this.appendConstant(string2);
        this.buf.append(", ");
        this.appendConstant(string3);
        this.buf.append(", ");
        if (arrstring != null && arrstring.length > 0) {
            this.buf.append("new String[] {");
            for (int i2 = 0; i2 < arrstring.length; ++i2) {
                this.buf.append(i2 == 0 ? " " : ", ");
                this.appendConstant(arrstring[i2]);
            }
            this.buf.append(" }");
        } else {
            this.buf.append("null");
        }
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
        ASMifier aSMifier = this.createASMifier("mv", 0);
        this.text.add(aSMifier.getText());
        this.text.add("}\n");
        return aSMifier;
    }

    public void visitClassEnd() {
        this.text.add("cw.visitEnd();\n\n");
        this.text.add("return cw.toByteArray();\n");
        this.text.add("}\n");
        this.text.add("}\n");
    }

    public void visit(String string, Object object) {
        this.buf.setLength(0);
        this.buf.append("av").append(this.id).append(".visit(");
        ASMifier.appendConstant(this.buf, string);
        this.buf.append(", ");
        ASMifier.appendConstant(this.buf, object);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitEnum(String string, String string2, String string3) {
        this.buf.setLength(0);
        this.buf.append("av").append(this.id).append(".visitEnum(");
        ASMifier.appendConstant(this.buf, string);
        this.buf.append(", ");
        ASMifier.appendConstant(this.buf, string2);
        this.buf.append(", ");
        ASMifier.appendConstant(this.buf, string3);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }

    public ASMifier visitAnnotation(String string, String string2) {
        this.buf.setLength(0);
        this.buf.append("{\n");
        this.buf.append("AnnotationVisitor av").append(this.id + 1).append(" = av");
        this.buf.append(this.id).append(".visitAnnotation(");
        ASMifier.appendConstant(this.buf, string);
        this.buf.append(", ");
        ASMifier.appendConstant(this.buf, string2);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
        ASMifier aSMifier = this.createASMifier("av", this.id + 1);
        this.text.add(aSMifier.getText());
        this.text.add("}\n");
        return aSMifier;
    }

    public ASMifier visitArray(String string) {
        this.buf.setLength(0);
        this.buf.append("{\n");
        this.buf.append("AnnotationVisitor av").append(this.id + 1).append(" = av");
        this.buf.append(this.id).append(".visitArray(");
        ASMifier.appendConstant(this.buf, string);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
        ASMifier aSMifier = this.createASMifier("av", this.id + 1);
        this.text.add(aSMifier.getText());
        this.text.add("}\n");
        return aSMifier;
    }

    public void visitAnnotationEnd() {
        this.buf.setLength(0);
        this.buf.append("av").append(this.id).append(".visitEnd();\n");
        this.text.add(this.buf.toString());
    }

    public ASMifier visitFieldAnnotation(String string, boolean bl) {
        return this.visitAnnotation(string, bl);
    }

    public ASMifier visitFieldTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        return this.visitTypeAnnotation(n2, typePath, string, bl);
    }

    public void visitFieldAttribute(Attribute attribute) {
        this.visitAttribute(attribute);
    }

    public void visitFieldEnd() {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitEnd();\n");
        this.text.add(this.buf.toString());
    }

    public void visitParameter(String string, int n2) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitParameter(");
        ASMifier.appendString(this.buf, string);
        this.buf.append(", ");
        this.appendAccess(n2);
        this.text.add(this.buf.append(");\n").toString());
    }

    public ASMifier visitAnnotationDefault() {
        this.buf.setLength(0);
        this.buf.append("{\n").append("av0 = ").append(this.name).append(".visitAnnotationDefault();\n");
        this.text.add(this.buf.toString());
        ASMifier aSMifier = this.createASMifier("av", 0);
        this.text.add(aSMifier.getText());
        this.text.add("}\n");
        return aSMifier;
    }

    public ASMifier visitMethodAnnotation(String string, boolean bl) {
        return this.visitAnnotation(string, bl);
    }

    public ASMifier visitMethodTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        return this.visitTypeAnnotation(n2, typePath, string, bl);
    }

    public ASMifier visitParameterAnnotation(int n2, String string, boolean bl) {
        this.buf.setLength(0);
        this.buf.append("{\n").append("av0 = ").append(this.name).append(".visitParameterAnnotation(").append(n2).append(", ");
        this.appendConstant(string);
        this.buf.append(", ").append(bl).append(");\n");
        this.text.add(this.buf.toString());
        ASMifier aSMifier = this.createASMifier("av", 0);
        this.text.add(aSMifier.getText());
        this.text.add("}\n");
        return aSMifier;
    }

    public void visitMethodAttribute(Attribute attribute) {
        this.visitAttribute(attribute);
    }

    public void visitCode() {
        this.text.add(this.name + ".visitCode();\n");
    }

    public void visitFrame(int n2, int n3, Object[] arrobject, int n4, Object[] arrobject2) {
        this.buf.setLength(0);
        switch (n2) {
            case -1: 
            case 0: {
                this.declareFrameTypes(n3, arrobject);
                this.declareFrameTypes(n4, arrobject2);
                if (n2 == -1) {
                    this.buf.append(this.name).append(".visitFrame(Opcodes.F_NEW, ");
                } else {
                    this.buf.append(this.name).append(".visitFrame(Opcodes.F_FULL, ");
                }
                this.buf.append(n3).append(", new Object[] {");
                this.appendFrameTypes(n3, arrobject);
                this.buf.append("}, ").append(n4).append(", new Object[] {");
                this.appendFrameTypes(n4, arrobject2);
                this.buf.append('}');
                break;
            }
            case 1: {
                this.declareFrameTypes(n3, arrobject);
                this.buf.append(this.name).append(".visitFrame(Opcodes.F_APPEND,").append(n3).append(", new Object[] {");
                this.appendFrameTypes(n3, arrobject);
                this.buf.append("}, 0, null");
                break;
            }
            case 2: {
                this.buf.append(this.name).append(".visitFrame(Opcodes.F_CHOP,").append(n3).append(", null, 0, null");
                break;
            }
            case 3: {
                this.buf.append(this.name).append(".visitFrame(Opcodes.F_SAME, 0, null, 0, null");
                break;
            }
            case 4: {
                this.declareFrameTypes(1, arrobject2);
                this.buf.append(this.name).append(".visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {");
                this.appendFrameTypes(1, arrobject2);
                this.buf.append('}');
            }
        }
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitInsn(int n2) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitInsn(").append(OPCODES[n2]).append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitIntInsn(int n2, int n3) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitIntInsn(").append(OPCODES[n2]).append(", ").append(n2 == 188 ? TYPES[n3] : Integer.toString(n3)).append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitVarInsn(int n2, int n3) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitVarInsn(").append(OPCODES[n2]).append(", ").append(n3).append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitTypeInsn(int n2, String string) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitTypeInsn(").append(OPCODES[n2]).append(", ");
        this.appendConstant(string);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitFieldInsn(int n2, String string, String string2, String string3) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitFieldInsn(").append(OPCODES[n2]).append(", ");
        this.appendConstant(string);
        this.buf.append(", ");
        this.appendConstant(string2);
        this.buf.append(", ");
        this.appendConstant(string3);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
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
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitMethodInsn(").append(OPCODES[n2]).append(", ");
        this.appendConstant(string);
        this.buf.append(", ");
        this.appendConstant(string2);
        this.buf.append(", ");
        this.appendConstant(string3);
        this.buf.append(", ");
        this.buf.append(bl ? "true" : "false");
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitInvokeDynamicInsn(String string, String string2, Handle handle, Object ... arrobject) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitInvokeDynamicInsn(");
        this.appendConstant(string);
        this.buf.append(", ");
        this.appendConstant(string2);
        this.buf.append(", ");
        this.appendConstant(handle);
        this.buf.append(", new Object[]{");
        for (int i2 = 0; i2 < arrobject.length; ++i2) {
            this.appendConstant(arrobject[i2]);
            if (i2 == arrobject.length - 1) continue;
            this.buf.append(", ");
        }
        this.buf.append("});\n");
        this.text.add(this.buf.toString());
    }

    public void visitJumpInsn(int n2, Label label) {
        this.buf.setLength(0);
        this.declareLabel(label);
        this.buf.append(this.name).append(".visitJumpInsn(").append(OPCODES[n2]).append(", ");
        this.appendLabel(label);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitLabel(Label label) {
        this.buf.setLength(0);
        this.declareLabel(label);
        this.buf.append(this.name).append(".visitLabel(");
        this.appendLabel(label);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitLdcInsn(Object object) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitLdcInsn(");
        this.appendConstant(object);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitIincInsn(int n2, int n3) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitIincInsn(").append(n2).append(", ").append(n3).append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitTableSwitchInsn(int n2, int n3, Label label, Label ... arrlabel) {
        int n4;
        this.buf.setLength(0);
        for (n4 = 0; n4 < arrlabel.length; ++n4) {
            this.declareLabel(arrlabel[n4]);
        }
        this.declareLabel(label);
        this.buf.append(this.name).append(".visitTableSwitchInsn(").append(n2).append(", ").append(n3).append(", ");
        this.appendLabel(label);
        this.buf.append(", new Label[] {");
        for (n4 = 0; n4 < arrlabel.length; ++n4) {
            this.buf.append(n4 == 0 ? " " : ", ");
            this.appendLabel(arrlabel[n4]);
        }
        this.buf.append(" });\n");
        this.text.add(this.buf.toString());
    }

    public void visitLookupSwitchInsn(Label label, int[] arrn, Label[] arrlabel) {
        int n2;
        this.buf.setLength(0);
        for (n2 = 0; n2 < arrlabel.length; ++n2) {
            this.declareLabel(arrlabel[n2]);
        }
        this.declareLabel(label);
        this.buf.append(this.name).append(".visitLookupSwitchInsn(");
        this.appendLabel(label);
        this.buf.append(", new int[] {");
        for (n2 = 0; n2 < arrn.length; ++n2) {
            this.buf.append(n2 == 0 ? " " : ", ").append(arrn[n2]);
        }
        this.buf.append(" }, new Label[] {");
        for (n2 = 0; n2 < arrlabel.length; ++n2) {
            this.buf.append(n2 == 0 ? " " : ", ");
            this.appendLabel(arrlabel[n2]);
        }
        this.buf.append(" });\n");
        this.text.add(this.buf.toString());
    }

    public void visitMultiANewArrayInsn(String string, int n2) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitMultiANewArrayInsn(");
        this.appendConstant(string);
        this.buf.append(", ").append(n2).append(");\n");
        this.text.add(this.buf.toString());
    }

    public ASMifier visitInsnAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        return this.visitTypeAnnotation("visitInsnAnnotation", n2, typePath, string, bl);
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String string) {
        this.buf.setLength(0);
        this.declareLabel(label);
        this.declareLabel(label2);
        this.declareLabel(label3);
        this.buf.append(this.name).append(".visitTryCatchBlock(");
        this.appendLabel(label);
        this.buf.append(", ");
        this.appendLabel(label2);
        this.buf.append(", ");
        this.appendLabel(label3);
        this.buf.append(", ");
        this.appendConstant(string);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }

    public ASMifier visitTryCatchAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        return this.visitTypeAnnotation("visitTryCatchAnnotation", n2, typePath, string, bl);
    }

    public void visitLocalVariable(String string, String string2, String string3, Label label, Label label2, int n2) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitLocalVariable(");
        this.appendConstant(string);
        this.buf.append(", ");
        this.appendConstant(string2);
        this.buf.append(", ");
        this.appendConstant(string3);
        this.buf.append(", ");
        this.appendLabel(label);
        this.buf.append(", ");
        this.appendLabel(label2);
        this.buf.append(", ").append(n2).append(");\n");
        this.text.add(this.buf.toString());
    }

    public Printer visitLocalVariableAnnotation(int n2, TypePath typePath, Label[] arrlabel, Label[] arrlabel2, int[] arrn, String string, boolean bl) {
        int n3;
        this.buf.setLength(0);
        this.buf.append("{\n").append("av0 = ").append(this.name).append(".visitLocalVariableAnnotation(");
        this.buf.append(n2);
        if (typePath == null) {
            this.buf.append(", null, ");
        } else {
            this.buf.append(", TypePath.fromString(\"").append(typePath).append("\"), ");
        }
        this.buf.append("new Label[] {");
        for (n3 = 0; n3 < arrlabel.length; ++n3) {
            this.buf.append(n3 == 0 ? " " : ", ");
            this.appendLabel(arrlabel[n3]);
        }
        this.buf.append(" }, new Label[] {");
        for (n3 = 0; n3 < arrlabel2.length; ++n3) {
            this.buf.append(n3 == 0 ? " " : ", ");
            this.appendLabel(arrlabel2[n3]);
        }
        this.buf.append(" }, new int[] {");
        for (n3 = 0; n3 < arrn.length; ++n3) {
            this.buf.append(n3 == 0 ? " " : ", ").append(arrn[n3]);
        }
        this.buf.append(" }, ");
        this.appendConstant(string);
        this.buf.append(", ").append(bl).append(");\n");
        this.text.add(this.buf.toString());
        ASMifier aSMifier = this.createASMifier("av", 0);
        this.text.add(aSMifier.getText());
        this.text.add("}\n");
        return aSMifier;
    }

    public void visitLineNumber(int n2, Label label) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitLineNumber(").append(n2).append(", ");
        this.appendLabel(label);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitMaxs(int n2, int n3) {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitMaxs(").append(n2).append(", ").append(n3).append(");\n");
        this.text.add(this.buf.toString());
    }

    public void visitMethodEnd() {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitEnd();\n");
        this.text.add(this.buf.toString());
    }

    public ASMifier visitAnnotation(String string, boolean bl) {
        this.buf.setLength(0);
        this.buf.append("{\n").append("av0 = ").append(this.name).append(".visitAnnotation(");
        this.appendConstant(string);
        this.buf.append(", ").append(bl).append(");\n");
        this.text.add(this.buf.toString());
        ASMifier aSMifier = this.createASMifier("av", 0);
        this.text.add(aSMifier.getText());
        this.text.add("}\n");
        return aSMifier;
    }

    public ASMifier visitTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        return this.visitTypeAnnotation("visitTypeAnnotation", n2, typePath, string, bl);
    }

    public ASMifier visitTypeAnnotation(String string, int n2, TypePath typePath, String string2, boolean bl) {
        this.buf.setLength(0);
        this.buf.append("{\n").append("av0 = ").append(this.name).append(".").append(string).append("(");
        this.buf.append(n2);
        if (typePath == null) {
            this.buf.append(", null, ");
        } else {
            this.buf.append(", TypePath.fromString(\"").append(typePath).append("\"), ");
        }
        this.appendConstant(string2);
        this.buf.append(", ").append(bl).append(");\n");
        this.text.add(this.buf.toString());
        ASMifier aSMifier = this.createASMifier("av", 0);
        this.text.add(aSMifier.getText());
        this.text.add("}\n");
        return aSMifier;
    }

    public void visitAttribute(Attribute attribute) {
        this.buf.setLength(0);
        this.buf.append("// ATTRIBUTE ").append(attribute.type).append('\n');
        if (attribute instanceof ASMifiable) {
            if (this.labelNames == null) {
                this.labelNames = new HashMap<Label, String>();
            }
            this.buf.append("{\n");
            ((ASMifiable)((Object)attribute)).asmify(this.buf, "attr", this.labelNames);
            this.buf.append(this.name).append(".visitAttribute(attr);\n");
            this.buf.append("}\n");
        }
        this.text.add(this.buf.toString());
    }

    protected ASMifier createASMifier(String string, int n2) {
        return new ASMifier(327680, string, n2);
    }

    void appendAccess(int n2) {
        boolean bl = true;
        if ((n2 & 1) != 0) {
            this.buf.append("ACC_PUBLIC");
            bl = false;
        }
        if ((n2 & 2) != 0) {
            this.buf.append("ACC_PRIVATE");
            bl = false;
        }
        if ((n2 & 4) != 0) {
            this.buf.append("ACC_PROTECTED");
            bl = false;
        }
        if ((n2 & 0x10) != 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_FINAL");
            bl = false;
        }
        if ((n2 & 8) != 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_STATIC");
            bl = false;
        }
        if ((n2 & 0x20) != 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            if ((n2 & 0x40000) == 0) {
                this.buf.append("ACC_SYNCHRONIZED");
            } else {
                this.buf.append("ACC_SUPER");
            }
            bl = false;
        }
        if ((n2 & 0x40) != 0 && (n2 & 0x80000) != 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_VOLATILE");
            bl = false;
        }
        if ((n2 & 0x40) != 0 && (n2 & 0x40000) == 0 && (n2 & 0x80000) == 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_BRIDGE");
            bl = false;
        }
        if ((n2 & 0x80) != 0 && (n2 & 0x40000) == 0 && (n2 & 0x80000) == 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_VARARGS");
            bl = false;
        }
        if ((n2 & 0x80) != 0 && (n2 & 0x80000) != 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_TRANSIENT");
            bl = false;
        }
        if ((n2 & 0x100) != 0 && (n2 & 0x40000) == 0 && (n2 & 0x80000) == 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_NATIVE");
            bl = false;
        }
        if ((n2 & 0x4000) != 0 && ((n2 & 0x40000) != 0 || (n2 & 0x80000) != 0 || (n2 & 0x100000) != 0)) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_ENUM");
            bl = false;
        }
        if ((n2 & 0x2000) != 0 && ((n2 & 0x40000) != 0 || (n2 & 0x100000) != 0)) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_ANNOTATION");
            bl = false;
        }
        if ((n2 & 0x400) != 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_ABSTRACT");
            bl = false;
        }
        if ((n2 & 0x200) != 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_INTERFACE");
            bl = false;
        }
        if ((n2 & 0x800) != 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_STRICT");
            bl = false;
        }
        if ((n2 & 0x1000) != 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_SYNTHETIC");
            bl = false;
        }
        if ((n2 & 0x20000) != 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_DEPRECATED");
            bl = false;
        }
        if ((n2 & 0x8000) != 0) {
            if (!bl) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_MANDATED");
            bl = false;
        }
        if (bl) {
            this.buf.append('0');
        }
    }

    protected void appendConstant(Object object) {
        ASMifier.appendConstant(this.buf, object);
    }

    static void appendConstant(StringBuffer stringBuffer, Object object) {
        if (object == null) {
            stringBuffer.append("null");
        } else if (object instanceof String) {
            ASMifier.appendString(stringBuffer, (String)object);
        } else if (object instanceof Type) {
            stringBuffer.append("Type.getType(\"");
            stringBuffer.append(((Type)object).getDescriptor());
            stringBuffer.append("\")");
        } else if (object instanceof Handle) {
            stringBuffer.append("new Handle(");
            Handle handle = (Handle)object;
            stringBuffer.append("Opcodes.").append(HANDLE_TAG[handle.getTag()]).append(", \"");
            stringBuffer.append(handle.getOwner()).append("\", \"");
            stringBuffer.append(handle.getName()).append("\", \"");
            stringBuffer.append(handle.getDesc()).append("\")");
        } else if (object instanceof Byte) {
            stringBuffer.append("new Byte((byte)").append(object).append(')');
        } else if (object instanceof Boolean) {
            stringBuffer.append((Boolean)object != false ? "Boolean.TRUE" : "Boolean.FALSE");
        } else if (object instanceof Short) {
            stringBuffer.append("new Short((short)").append(object).append(')');
        } else if (object instanceof Character) {
            char c2 = ((Character)object).charValue();
            stringBuffer.append("new Character((char)").append((int)c2).append(')');
        } else if (object instanceof Integer) {
            stringBuffer.append("new Integer(").append(object).append(')');
        } else if (object instanceof Float) {
            stringBuffer.append("new Float(\"").append(object).append("\")");
        } else if (object instanceof Long) {
            stringBuffer.append("new Long(").append(object).append("L)");
        } else if (object instanceof Double) {
            stringBuffer.append("new Double(\"").append(object).append("\")");
        } else if (object instanceof byte[]) {
            byte[] arrby = (byte[])object;
            stringBuffer.append("new byte[] {");
            for (int i2 = 0; i2 < arrby.length; ++i2) {
                stringBuffer.append(i2 == 0 ? "" : ",").append(arrby[i2]);
            }
            stringBuffer.append('}');
        } else if (object instanceof boolean[]) {
            boolean[] arrbl = (boolean[])object;
            stringBuffer.append("new boolean[] {");
            for (int i3 = 0; i3 < arrbl.length; ++i3) {
                stringBuffer.append(i3 == 0 ? "" : ",").append(arrbl[i3]);
            }
            stringBuffer.append('}');
        } else if (object instanceof short[]) {
            short[] arrs = (short[])object;
            stringBuffer.append("new short[] {");
            for (int i4 = 0; i4 < arrs.length; ++i4) {
                stringBuffer.append(i4 == 0 ? "" : ",").append("(short)").append(arrs[i4]);
            }
            stringBuffer.append('}');
        } else if (object instanceof char[]) {
            char[] arrc = (char[])object;
            stringBuffer.append("new char[] {");
            for (int i5 = 0; i5 < arrc.length; ++i5) {
                stringBuffer.append(i5 == 0 ? "" : ",").append("(char)").append((int)arrc[i5]);
            }
            stringBuffer.append('}');
        } else if (object instanceof int[]) {
            int[] arrn = (int[])object;
            stringBuffer.append("new int[] {");
            for (int i6 = 0; i6 < arrn.length; ++i6) {
                stringBuffer.append(i6 == 0 ? "" : ",").append(arrn[i6]);
            }
            stringBuffer.append('}');
        } else if (object instanceof long[]) {
            long[] arrl = (long[])object;
            stringBuffer.append("new long[] {");
            for (int i7 = 0; i7 < arrl.length; ++i7) {
                stringBuffer.append(i7 == 0 ? "" : ",").append(arrl[i7]).append('L');
            }
            stringBuffer.append('}');
        } else if (object instanceof float[]) {
            float[] arrf = (float[])object;
            stringBuffer.append("new float[] {");
            for (int i8 = 0; i8 < arrf.length; ++i8) {
                stringBuffer.append(i8 == 0 ? "" : ",").append(arrf[i8]).append('f');
            }
            stringBuffer.append('}');
        } else if (object instanceof double[]) {
            double[] arrd = (double[])object;
            stringBuffer.append("new double[] {");
            for (int i9 = 0; i9 < arrd.length; ++i9) {
                stringBuffer.append(i9 == 0 ? "" : ",").append(arrd[i9]).append('d');
            }
            stringBuffer.append('}');
        }
    }

    private void declareFrameTypes(int n2, Object[] arrobject) {
        for (int i2 = 0; i2 < n2; ++i2) {
            if (!(arrobject[i2] instanceof Label)) continue;
            this.declareLabel((Label)arrobject[i2]);
        }
    }

    private void appendFrameTypes(int n2, Object[] arrobject) {
        for (int i2 = 0; i2 < n2; ++i2) {
            if (i2 > 0) {
                this.buf.append(", ");
            }
            if (arrobject[i2] instanceof String) {
                this.appendConstant(arrobject[i2]);
                continue;
            }
            if (arrobject[i2] instanceof Integer) {
                switch ((Integer)arrobject[i2]) {
                    case 0: {
                        this.buf.append("Opcodes.TOP");
                        break;
                    }
                    case 1: {
                        this.buf.append("Opcodes.INTEGER");
                        break;
                    }
                    case 2: {
                        this.buf.append("Opcodes.FLOAT");
                        break;
                    }
                    case 3: {
                        this.buf.append("Opcodes.DOUBLE");
                        break;
                    }
                    case 4: {
                        this.buf.append("Opcodes.LONG");
                        break;
                    }
                    case 5: {
                        this.buf.append("Opcodes.NULL");
                        break;
                    }
                    case 6: {
                        this.buf.append("Opcodes.UNINITIALIZED_THIS");
                    }
                }
                continue;
            }
            this.appendLabel((Label)arrobject[i2]);
        }
    }

    protected void declareLabel(Label label) {
        String string;
        if (this.labelNames == null) {
            this.labelNames = new HashMap<Label, String>();
        }
        if ((string = this.labelNames.get(label)) == null) {
            string = "l" + this.labelNames.size();
            this.labelNames.put(label, string);
            this.buf.append("Label ").append(string).append(" = new Label();\n");
        }
    }

    protected void appendLabel(Label label) {
        this.buf.append(this.labelNames.get(label));
    }
}

