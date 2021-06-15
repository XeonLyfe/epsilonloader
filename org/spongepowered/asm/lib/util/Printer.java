/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.TypePath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Printer {
    public static final String[] OPCODES;
    public static final String[] TYPES;
    public static final String[] HANDLE_TAG;
    protected final int api;
    protected final StringBuffer buf;
    public final List<Object> text;

    protected Printer(int n2) {
        this.api = n2;
        this.buf = new StringBuffer();
        this.text = new ArrayList<Object>();
    }

    public abstract void visit(int var1, int var2, String var3, String var4, String var5, String[] var6);

    public abstract void visitSource(String var1, String var2);

    public abstract void visitOuterClass(String var1, String var2, String var3);

    public abstract Printer visitClassAnnotation(String var1, boolean var2);

    public Printer visitClassTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        throw new RuntimeException("Must be overriden");
    }

    public abstract void visitClassAttribute(Attribute var1);

    public abstract void visitInnerClass(String var1, String var2, String var3, int var4);

    public abstract Printer visitField(int var1, String var2, String var3, String var4, Object var5);

    public abstract Printer visitMethod(int var1, String var2, String var3, String var4, String[] var5);

    public abstract void visitClassEnd();

    public abstract void visit(String var1, Object var2);

    public abstract void visitEnum(String var1, String var2, String var3);

    public abstract Printer visitAnnotation(String var1, String var2);

    public abstract Printer visitArray(String var1);

    public abstract void visitAnnotationEnd();

    public abstract Printer visitFieldAnnotation(String var1, boolean var2);

    public Printer visitFieldTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        throw new RuntimeException("Must be overriden");
    }

    public abstract void visitFieldAttribute(Attribute var1);

    public abstract void visitFieldEnd();

    public void visitParameter(String string, int n2) {
        throw new RuntimeException("Must be overriden");
    }

    public abstract Printer visitAnnotationDefault();

    public abstract Printer visitMethodAnnotation(String var1, boolean var2);

    public Printer visitMethodTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        throw new RuntimeException("Must be overriden");
    }

    public abstract Printer visitParameterAnnotation(int var1, String var2, boolean var3);

    public abstract void visitMethodAttribute(Attribute var1);

    public abstract void visitCode();

    public abstract void visitFrame(int var1, int var2, Object[] var3, int var4, Object[] var5);

    public abstract void visitInsn(int var1);

    public abstract void visitIntInsn(int var1, int var2);

    public abstract void visitVarInsn(int var1, int var2);

    public abstract void visitTypeInsn(int var1, String var2);

    public abstract void visitFieldInsn(int var1, String var2, String var3, String var4);

    @Deprecated
    public void visitMethodInsn(int n2, String string, String string2, String string3) {
        if (this.api >= 327680) {
            boolean bl = n2 == 185;
            this.visitMethodInsn(n2, string, string2, string3, bl);
            return;
        }
        throw new RuntimeException("Must be overriden");
    }

    public void visitMethodInsn(int n2, String string, String string2, String string3, boolean bl) {
        if (this.api < 327680) {
            if (bl != (n2 == 185)) {
                throw new IllegalArgumentException("INVOKESPECIAL/STATIC on interfaces require ASM 5");
            }
            this.visitMethodInsn(n2, string, string2, string3);
            return;
        }
        throw new RuntimeException("Must be overriden");
    }

    public abstract void visitInvokeDynamicInsn(String var1, String var2, Handle var3, Object ... var4);

    public abstract void visitJumpInsn(int var1, Label var2);

    public abstract void visitLabel(Label var1);

    public abstract void visitLdcInsn(Object var1);

    public abstract void visitIincInsn(int var1, int var2);

    public abstract void visitTableSwitchInsn(int var1, int var2, Label var3, Label ... var4);

    public abstract void visitLookupSwitchInsn(Label var1, int[] var2, Label[] var3);

    public abstract void visitMultiANewArrayInsn(String var1, int var2);

    public Printer visitInsnAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        throw new RuntimeException("Must be overriden");
    }

    public abstract void visitTryCatchBlock(Label var1, Label var2, Label var3, String var4);

    public Printer visitTryCatchAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        throw new RuntimeException("Must be overriden");
    }

    public abstract void visitLocalVariable(String var1, String var2, String var3, Label var4, Label var5, int var6);

    public Printer visitLocalVariableAnnotation(int n2, TypePath typePath, Label[] arrlabel, Label[] arrlabel2, int[] arrn, String string, boolean bl) {
        throw new RuntimeException("Must be overriden");
    }

    public abstract void visitLineNumber(int var1, Label var2);

    public abstract void visitMaxs(int var1, int var2);

    public abstract void visitMethodEnd();

    public List<Object> getText() {
        return this.text;
    }

    public void print(PrintWriter printWriter) {
        Printer.printList(printWriter, this.text);
    }

    public static void appendString(StringBuffer stringBuffer, String string) {
        stringBuffer.append('\"');
        for (int i2 = 0; i2 < string.length(); ++i2) {
            char c2 = string.charAt(i2);
            if (c2 == '\n') {
                stringBuffer.append("\\n");
                continue;
            }
            if (c2 == '\r') {
                stringBuffer.append("\\r");
                continue;
            }
            if (c2 == '\\') {
                stringBuffer.append("\\\\");
                continue;
            }
            if (c2 == '\"') {
                stringBuffer.append("\\\"");
                continue;
            }
            if (c2 < ' ' || c2 > '\u007f') {
                stringBuffer.append("\\u");
                if (c2 < '\u0010') {
                    stringBuffer.append("000");
                } else if (c2 < '\u0100') {
                    stringBuffer.append("00");
                } else if (c2 < '\u1000') {
                    stringBuffer.append('0');
                }
                stringBuffer.append(Integer.toString(c2, 16));
                continue;
            }
            stringBuffer.append(c2);
        }
        stringBuffer.append('\"');
    }

    static void printList(PrintWriter printWriter, List<?> list) {
        for (int i2 = 0; i2 < list.size(); ++i2) {
            Object obj = list.get(i2);
            if (obj instanceof List) {
                Printer.printList(printWriter, (List)obj);
                continue;
            }
            printWriter.print(obj.toString());
        }
    }

    static {
        int n2;
        String string = "NOP,ACONST_NULL,ICONST_M1,ICONST_0,ICONST_1,ICONST_2,ICONST_3,ICONST_4,ICONST_5,LCONST_0,LCONST_1,FCONST_0,FCONST_1,FCONST_2,DCONST_0,DCONST_1,BIPUSH,SIPUSH,LDC,,,ILOAD,LLOAD,FLOAD,DLOAD,ALOAD,,,,,,,,,,,,,,,,,,,,,IALOAD,LALOAD,FALOAD,DALOAD,AALOAD,BALOAD,CALOAD,SALOAD,ISTORE,LSTORE,FSTORE,DSTORE,ASTORE,,,,,,,,,,,,,,,,,,,,,IASTORE,LASTORE,FASTORE,DASTORE,AASTORE,BASTORE,CASTORE,SASTORE,POP,POP2,DUP,DUP_X1,DUP_X2,DUP2,DUP2_X1,DUP2_X2,SWAP,IADD,LADD,FADD,DADD,ISUB,LSUB,FSUB,DSUB,IMUL,LMUL,FMUL,DMUL,IDIV,LDIV,FDIV,DDIV,IREM,LREM,FREM,DREM,INEG,LNEG,FNEG,DNEG,ISHL,LSHL,ISHR,LSHR,IUSHR,LUSHR,IAND,LAND,IOR,LOR,IXOR,LXOR,IINC,I2L,I2F,I2D,L2I,L2F,L2D,F2I,F2L,F2D,D2I,D2L,D2F,I2B,I2C,I2S,LCMP,FCMPL,FCMPG,DCMPL,DCMPG,IFEQ,IFNE,IFLT,IFGE,IFGT,IFLE,IF_ICMPEQ,IF_ICMPNE,IF_ICMPLT,IF_ICMPGE,IF_ICMPGT,IF_ICMPLE,IF_ACMPEQ,IF_ACMPNE,GOTO,JSR,RET,TABLESWITCH,LOOKUPSWITCH,IRETURN,LRETURN,FRETURN,DRETURN,ARETURN,RETURN,GETSTATIC,PUTSTATIC,GETFIELD,PUTFIELD,INVOKEVIRTUAL,INVOKESPECIAL,INVOKESTATIC,INVOKEINTERFACE,INVOKEDYNAMIC,NEW,NEWARRAY,ANEWARRAY,ARRAYLENGTH,ATHROW,CHECKCAST,INSTANCEOF,MONITORENTER,MONITOREXIT,,MULTIANEWARRAY,IFNULL,IFNONNULL,";
        OPCODES = new String[200];
        int n3 = 0;
        int n4 = 0;
        while ((n2 = string.indexOf(44, n4)) > 0) {
            Printer.OPCODES[n3++] = n4 + 1 == n2 ? null : string.substring(n4, n2);
            n4 = n2 + 1;
        }
        string = "T_BOOLEAN,T_CHAR,T_FLOAT,T_DOUBLE,T_BYTE,T_SHORT,T_INT,T_LONG,";
        TYPES = new String[12];
        n4 = 0;
        n3 = 4;
        while ((n2 = string.indexOf(44, n4)) > 0) {
            Printer.TYPES[n3++] = string.substring(n4, n2);
            n4 = n2 + 1;
        }
        string = "H_GETFIELD,H_GETSTATIC,H_PUTFIELD,H_PUTSTATIC,H_INVOKEVIRTUAL,H_INVOKESTATIC,H_INVOKESPECIAL,H_NEWINVOKESPECIAL,H_INVOKEINTERFACE,";
        HANDLE_TAG = new String[10];
        n4 = 0;
        n3 = 1;
        while ((n2 = string.indexOf(44, n4)) > 0) {
            Printer.HANDLE_TAG[n3++] = string.substring(n4, n2);
            n4 = n2 + 1;
        }
    }
}

