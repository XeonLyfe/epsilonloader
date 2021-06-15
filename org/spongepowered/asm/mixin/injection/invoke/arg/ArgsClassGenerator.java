/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.invoke.arg;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.util.CheckClassAdapter;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import org.spongepowered.asm.mixin.transformer.ext.IClassGenerator;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.SignaturePrinter;
import org.spongepowered.asm.util.asm.MethodVisitorEx;

public final class ArgsClassGenerator
implements IClassGenerator {
    public static final String ARGS_NAME = Args.class.getName();
    public static final String ARGS_REF = ARGS_NAME.replace('.', '/');
    public static final String GETTER_PREFIX = "$";
    private static final String CLASS_NAME_BASE = "org.spongepowered.asm.synthetic.args.Args$";
    private static final String OBJECT = "java/lang/Object";
    private static final String OBJECT_ARRAY = "[Ljava/lang/Object;";
    private static final String VALUES_FIELD = "values";
    private static final String CTOR_DESC = "([Ljava/lang/Object;)V";
    private static final String SET = "set";
    private static final String SET_DESC = "(ILjava/lang/Object;)V";
    private static final String SETALL = "setAll";
    private static final String SETALL_DESC = "([Ljava/lang/Object;)V";
    private static final String NPE = "java/lang/NullPointerException";
    private static final String NPE_CTOR_DESC = "(Ljava/lang/String;)V";
    private static final String AIOOBE = "org/spongepowered/asm/mixin/injection/invoke/arg/ArgumentIndexOutOfBoundsException";
    private static final String AIOOBE_CTOR_DESC = "(I)V";
    private static final String ACE = "org/spongepowered/asm/mixin/injection/invoke/arg/ArgumentCountException";
    private static final String ACE_CTOR_DESC = "(IILjava/lang/String;)V";
    private int nextIndex = 1;
    private final BiMap<String, String> classNames = HashBiMap.create();
    private final Map<String, byte[]> classBytes = new HashMap<String, byte[]>();

    public String getClassName(String string) {
        String string2 = Bytecode.changeDescriptorReturnType(string, "V");
        String string3 = (String)this.classNames.get(string2);
        if (string3 == null) {
            string3 = String.format("%s%d", CLASS_NAME_BASE, this.nextIndex++);
            this.classNames.put(string2, string3);
        }
        return string3;
    }

    public String getClassRef(String string) {
        return this.getClassName(string).replace('.', '/');
    }

    @Override
    public byte[] generate(String string) {
        return this.getBytes(string);
    }

    public byte[] getBytes(String string) {
        byte[] arrby = this.classBytes.get(string);
        if (arrby == null) {
            String string2 = (String)this.classNames.inverse().get(string);
            if (string2 == null) {
                return null;
            }
            arrby = this.generateClass(string, string2);
            this.classBytes.put(string, arrby);
        }
        return arrby;
    }

    private byte[] generateClass(String string, String string2) {
        String string3 = string.replace('.', '/');
        Type[] arrtype = Type.getArgumentTypes(string2);
        ClassWriter classWriter = new ClassWriter(2);
        ClassVisitor classVisitor = classWriter;
        if (MixinEnvironment.getCurrentEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERIFY)) {
            classVisitor = new CheckClassAdapter(classWriter);
        }
        classVisitor.visit(50, 4129, string3, null, ARGS_REF, null);
        classVisitor.visitSource(string.substring(string.lastIndexOf(46) + 1) + ".java", null);
        this.generateCtor(string3, string2, arrtype, classVisitor);
        this.generateToString(string3, string2, arrtype, classVisitor);
        this.generateFactory(string3, string2, arrtype, classVisitor);
        this.generateSetters(string3, string2, arrtype, classVisitor);
        this.generateGetters(string3, string2, arrtype, classVisitor);
        classVisitor.visitEnd();
        return classWriter.toByteArray();
    }

    private void generateCtor(String string, String string2, Type[] arrtype, ClassVisitor classVisitor) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(2, "<init>", "([Ljava/lang/Object;)V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(183, ARGS_REF, "<init>", "([Ljava/lang/Object;)V", false);
        methodVisitor.visitInsn(177);
        methodVisitor.visitMaxs(2, 2);
        methodVisitor.visitEnd();
    }

    private void generateToString(String string, String string2, Type[] arrtype, ClassVisitor classVisitor) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(1, "toString", "()Ljava/lang/String;", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitLdcInsn("Args" + ArgsClassGenerator.getSignature(arrtype));
        methodVisitor.visitInsn(176);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();
    }

    private void generateFactory(String string, String string2, Type[] arrtype, ClassVisitor classVisitor) {
        String string3 = Bytecode.changeDescriptorReturnType(string2, "L" + string + ";");
        MethodVisitorEx methodVisitorEx = new MethodVisitorEx(classVisitor.visitMethod(9, "of", string3, null, null));
        methodVisitorEx.visitCode();
        methodVisitorEx.visitTypeInsn(187, string);
        methodVisitorEx.visitInsn(89);
        methodVisitorEx.visitConstant((byte)arrtype.length);
        methodVisitorEx.visitTypeInsn(189, OBJECT);
        byte by = 0;
        for (Type type : arrtype) {
            methodVisitorEx.visitInsn(89);
            methodVisitorEx.visitConstant(by);
            methodVisitorEx.visitVarInsn(type.getOpcode(21), by);
            ArgsClassGenerator.box(methodVisitorEx, type);
            methodVisitorEx.visitInsn(83);
            by = (byte)(by + type.getSize());
        }
        methodVisitorEx.visitMethodInsn(183, string, "<init>", "([Ljava/lang/Object;)V", false);
        methodVisitorEx.visitInsn(176);
        methodVisitorEx.visitMaxs(6, Bytecode.getArgsSize(arrtype));
        methodVisitorEx.visitEnd();
    }

    private void generateGetters(String string, String string2, Type[] arrtype, ClassVisitor classVisitor) {
        byte by = 0;
        for (Type type : arrtype) {
            String string3 = GETTER_PREFIX + by;
            String string4 = "()" + type.getDescriptor();
            MethodVisitorEx methodVisitorEx = new MethodVisitorEx(classVisitor.visitMethod(1, string3, string4, null, null));
            methodVisitorEx.visitCode();
            methodVisitorEx.visitVarInsn(25, 0);
            methodVisitorEx.visitFieldInsn(180, string, VALUES_FIELD, OBJECT_ARRAY);
            methodVisitorEx.visitConstant(by);
            methodVisitorEx.visitInsn(50);
            ArgsClassGenerator.unbox(methodVisitorEx, type);
            methodVisitorEx.visitInsn(type.getOpcode(172));
            methodVisitorEx.visitMaxs(2, 1);
            methodVisitorEx.visitEnd();
            by = (byte)(by + 1);
        }
    }

    private void generateSetters(String string, String string2, Type[] arrtype, ClassVisitor classVisitor) {
        this.generateIndexedSetter(string, string2, arrtype, classVisitor);
        this.generateMultiSetter(string, string2, arrtype, classVisitor);
    }

    private void generateIndexedSetter(String string, String string2, Type[] arrtype, ClassVisitor classVisitor) {
        int n2;
        MethodVisitorEx methodVisitorEx = new MethodVisitorEx(classVisitor.visitMethod(1, SET, SET_DESC, null, null));
        methodVisitorEx.visitCode();
        Label label = new Label();
        Label label2 = new Label();
        Label[] arrlabel = new Label[arrtype.length];
        for (n2 = 0; n2 < arrlabel.length; ++n2) {
            arrlabel[n2] = new Label();
        }
        methodVisitorEx.visitVarInsn(25, 0);
        methodVisitorEx.visitFieldInsn(180, string, VALUES_FIELD, OBJECT_ARRAY);
        for (n2 = 0; n2 < arrtype.length; n2 = (byte)(n2 + 1)) {
            methodVisitorEx.visitVarInsn(21, 1);
            methodVisitorEx.visitConstant((byte)n2);
            methodVisitorEx.visitJumpInsn(159, arrlabel[n2]);
        }
        ArgsClassGenerator.throwAIOOBE(methodVisitorEx, 1);
        for (n2 = 0; n2 < arrtype.length; ++n2) {
            String string3 = Bytecode.getBoxingType(arrtype[n2]);
            methodVisitorEx.visitLabel(arrlabel[n2]);
            methodVisitorEx.visitVarInsn(21, 1);
            methodVisitorEx.visitVarInsn(25, 2);
            methodVisitorEx.visitTypeInsn(192, string3 != null ? string3 : arrtype[n2].getInternalName());
            methodVisitorEx.visitJumpInsn(167, string3 != null ? label2 : label);
        }
        methodVisitorEx.visitLabel(label2);
        methodVisitorEx.visitInsn(89);
        methodVisitorEx.visitJumpInsn(199, label);
        ArgsClassGenerator.throwNPE(methodVisitorEx, "Argument with primitive type cannot be set to NULL");
        methodVisitorEx.visitLabel(label);
        methodVisitorEx.visitInsn(83);
        methodVisitorEx.visitInsn(177);
        methodVisitorEx.visitMaxs(6, 3);
        methodVisitorEx.visitEnd();
    }

    private void generateMultiSetter(String string, String string2, Type[] arrtype, ClassVisitor classVisitor) {
        MethodVisitorEx methodVisitorEx = new MethodVisitorEx(classVisitor.visitMethod(1, SETALL, "([Ljava/lang/Object;)V", null, null));
        methodVisitorEx.visitCode();
        Label label = new Label();
        Label label2 = new Label();
        int n2 = 6;
        methodVisitorEx.visitVarInsn(25, 1);
        methodVisitorEx.visitInsn(190);
        methodVisitorEx.visitInsn(89);
        methodVisitorEx.visitConstant((byte)arrtype.length);
        methodVisitorEx.visitJumpInsn(159, label);
        methodVisitorEx.visitTypeInsn(187, ACE);
        methodVisitorEx.visitInsn(89);
        methodVisitorEx.visitInsn(93);
        methodVisitorEx.visitInsn(88);
        methodVisitorEx.visitConstant((byte)arrtype.length);
        methodVisitorEx.visitLdcInsn(ArgsClassGenerator.getSignature(arrtype));
        methodVisitorEx.visitMethodInsn(183, ACE, "<init>", ACE_CTOR_DESC, false);
        methodVisitorEx.visitInsn(191);
        methodVisitorEx.visitLabel(label);
        methodVisitorEx.visitInsn(87);
        methodVisitorEx.visitVarInsn(25, 0);
        methodVisitorEx.visitFieldInsn(180, string, VALUES_FIELD, OBJECT_ARRAY);
        for (byte by = 0; by < arrtype.length; by = (byte)(by + 1)) {
            methodVisitorEx.visitInsn(89);
            methodVisitorEx.visitConstant(by);
            methodVisitorEx.visitVarInsn(25, 1);
            methodVisitorEx.visitConstant(by);
            methodVisitorEx.visitInsn(50);
            String string3 = Bytecode.getBoxingType(arrtype[by]);
            methodVisitorEx.visitTypeInsn(192, string3 != null ? string3 : arrtype[by].getInternalName());
            if (string3 != null) {
                methodVisitorEx.visitInsn(89);
                methodVisitorEx.visitJumpInsn(198, label2);
                n2 = 7;
            }
            methodVisitorEx.visitInsn(83);
        }
        methodVisitorEx.visitInsn(177);
        methodVisitorEx.visitLabel(label2);
        ArgsClassGenerator.throwNPE(methodVisitorEx, "Argument with primitive type cannot be set to NULL");
        methodVisitorEx.visitInsn(177);
        methodVisitorEx.visitMaxs(n2, 2);
        methodVisitorEx.visitEnd();
    }

    private static void throwNPE(MethodVisitorEx methodVisitorEx, String string) {
        methodVisitorEx.visitTypeInsn(187, NPE);
        methodVisitorEx.visitInsn(89);
        methodVisitorEx.visitLdcInsn(string);
        methodVisitorEx.visitMethodInsn(183, NPE, "<init>", NPE_CTOR_DESC, false);
        methodVisitorEx.visitInsn(191);
    }

    private static void throwAIOOBE(MethodVisitorEx methodVisitorEx, int n2) {
        methodVisitorEx.visitTypeInsn(187, AIOOBE);
        methodVisitorEx.visitInsn(89);
        methodVisitorEx.visitVarInsn(21, n2);
        methodVisitorEx.visitMethodInsn(183, AIOOBE, "<init>", AIOOBE_CTOR_DESC, false);
        methodVisitorEx.visitInsn(191);
    }

    private static void box(MethodVisitor methodVisitor, Type type) {
        String string = Bytecode.getBoxingType(type);
        if (string != null) {
            String string2 = String.format("(%s)L%s;", type.getDescriptor(), string);
            methodVisitor.visitMethodInsn(184, string, "valueOf", string2, false);
        }
    }

    private static void unbox(MethodVisitor methodVisitor, Type type) {
        String string = Bytecode.getBoxingType(type);
        if (string != null) {
            String string2 = Bytecode.getUnboxingMethod(type);
            String string3 = "()" + type.getDescriptor();
            methodVisitor.visitTypeInsn(192, string);
            methodVisitor.visitMethodInsn(182, string, string2, string3, false);
        } else {
            methodVisitor.visitTypeInsn(192, type.getInternalName());
        }
    }

    private static String getSignature(Type[] arrtype) {
        return new SignaturePrinter("", null, arrtype).setFullyQualified(true).getFormattedArgs();
    }
}

