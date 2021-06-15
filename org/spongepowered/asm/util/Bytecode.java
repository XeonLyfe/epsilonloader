/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.util;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.FrameNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.IntInsnNode;
import org.spongepowered.asm.lib.tree.JumpInsnNode;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.LdcInsnNode;
import org.spongepowered.asm.lib.tree.LineNumberNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.lib.util.CheckClassAdapter;
import org.spongepowered.asm.lib.util.TraceClassVisitor;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.util.Constants;
import org.spongepowered.asm.util.throwables.SyntheticBridgeException;

public final class Bytecode {
    public static final int[] CONSTANTS_INT = new int[]{2, 3, 4, 5, 6, 7, 8};
    public static final int[] CONSTANTS_FLOAT = new int[]{11, 12, 13};
    public static final int[] CONSTANTS_DOUBLE = new int[]{14, 15};
    public static final int[] CONSTANTS_LONG = new int[]{9, 10};
    public static final int[] CONSTANTS_ALL = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    private static final Object[] CONSTANTS_VALUES = new Object[]{null, -1, 0, 1, 2, 3, 4, 5, 0L, 1L, Float.valueOf(0.0f), Float.valueOf(1.0f), Float.valueOf(2.0f), 0.0, 1.0};
    private static final String[] CONSTANTS_TYPES = new String[]{null, "I", "I", "I", "I", "I", "I", "I", "J", "J", "F", "F", "F", "D", "D", "I", "I"};
    private static final String[] BOXING_TYPES = new String[]{null, "java/lang/Boolean", "java/lang/Character", "java/lang/Byte", "java/lang/Short", "java/lang/Integer", "java/lang/Float", "java/lang/Long", "java/lang/Double", null, null, null};
    private static final String[] UNBOXING_METHODS = new String[]{null, "booleanValue", "charValue", "byteValue", "shortValue", "intValue", "floatValue", "longValue", "doubleValue", null, null, null};
    private static final Class<?>[] MERGEABLE_MIXIN_ANNOTATIONS = new Class[]{Overwrite.class, Intrinsic.class, Final.class, Debug.class};
    private static Pattern mergeableAnnotationPattern = Bytecode.getMergeableAnnotationPattern();
    private static final Logger logger = LogManager.getLogger((String)"mixin");

    private Bytecode() {
    }

    public static MethodNode findMethod(ClassNode classNode, String string, String string2) {
        for (MethodNode methodNode : classNode.methods) {
            if (!methodNode.name.equals(string) || !methodNode.desc.equals(string2)) continue;
            return methodNode;
        }
        return null;
    }

    public static AbstractInsnNode findInsn(MethodNode methodNode, int n2) {
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
            if (abstractInsnNode.getOpcode() != n2) continue;
            return abstractInsnNode;
        }
        return null;
    }

    public static MethodInsnNode findSuperInit(MethodNode methodNode, String string) {
        if (!"<init>".equals(methodNode.name)) {
            return null;
        }
        int n2 = 0;
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
            if (abstractInsnNode instanceof TypeInsnNode && abstractInsnNode.getOpcode() == 187) {
                ++n2;
                continue;
            }
            if (!(abstractInsnNode instanceof MethodInsnNode) || abstractInsnNode.getOpcode() != 183) continue;
            MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
            if (!"<init>".equals(methodInsnNode.name)) continue;
            if (n2 > 0) {
                --n2;
                continue;
            }
            if (!methodInsnNode.owner.equals(string)) continue;
            return methodInsnNode;
        }
        return null;
    }

    public static void textify(ClassNode classNode, OutputStream outputStream) {
        classNode.accept(new TraceClassVisitor(new PrintWriter(outputStream)));
    }

    public static void textify(MethodNode methodNode, OutputStream outputStream) {
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(new PrintWriter(outputStream));
        MethodVisitor methodVisitor = traceClassVisitor.visitMethod(methodNode.access, methodNode.name, methodNode.desc, methodNode.signature, methodNode.exceptions.toArray(new String[0]));
        methodNode.accept(methodVisitor);
        traceClassVisitor.visitEnd();
    }

    public static void dumpClass(ClassNode classNode) {
        ClassWriter classWriter = new ClassWriter(3);
        classNode.accept(classWriter);
        Bytecode.dumpClass(classWriter.toByteArray());
    }

    public static void dumpClass(byte[] arrby) {
        ClassReader classReader = new ClassReader(arrby);
        CheckClassAdapter.verify(classReader, true, new PrintWriter(System.out));
    }

    public static void printMethodWithOpcodeIndices(MethodNode methodNode) {
        System.err.printf("%s%s\n", methodNode.name, methodNode.desc);
        int n2 = 0;
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
        while (listIterator.hasNext()) {
            System.err.printf("[%4d] %s\n", n2++, Bytecode.describeNode((AbstractInsnNode)listIterator.next()));
        }
    }

    public static void printMethod(MethodNode methodNode) {
        System.err.printf("%s%s\n", methodNode.name, methodNode.desc);
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
        while (listIterator.hasNext()) {
            System.err.print("  ");
            Bytecode.printNode((AbstractInsnNode)listIterator.next());
        }
    }

    public static void printNode(AbstractInsnNode abstractInsnNode) {
        System.err.printf("%s\n", Bytecode.describeNode(abstractInsnNode));
    }

    public static String describeNode(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode == null) {
            return String.format("   %-14s ", "null");
        }
        if (abstractInsnNode instanceof LabelNode) {
            return String.format("[%s]", ((LabelNode)abstractInsnNode).getLabel());
        }
        String string = String.format("   %-14s ", abstractInsnNode.getClass().getSimpleName().replace("Node", ""));
        if (abstractInsnNode instanceof JumpInsnNode) {
            string = string + String.format("[%s] [%s]", Bytecode.getOpcodeName(abstractInsnNode), ((JumpInsnNode)abstractInsnNode).label.getLabel());
        } else if (abstractInsnNode instanceof VarInsnNode) {
            string = string + String.format("[%s] %d", Bytecode.getOpcodeName(abstractInsnNode), ((VarInsnNode)abstractInsnNode).var);
        } else if (abstractInsnNode instanceof MethodInsnNode) {
            MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
            string = string + String.format("[%s] %s %s %s", Bytecode.getOpcodeName(abstractInsnNode), methodInsnNode.owner, methodInsnNode.name, methodInsnNode.desc);
        } else if (abstractInsnNode instanceof FieldInsnNode) {
            FieldInsnNode fieldInsnNode = (FieldInsnNode)abstractInsnNode;
            string = string + String.format("[%s] %s %s %s", Bytecode.getOpcodeName(abstractInsnNode), fieldInsnNode.owner, fieldInsnNode.name, fieldInsnNode.desc);
        } else if (abstractInsnNode instanceof LineNumberNode) {
            LineNumberNode lineNumberNode = (LineNumberNode)abstractInsnNode;
            string = string + String.format("LINE=[%d] LABEL=[%s]", lineNumberNode.line, lineNumberNode.start.getLabel());
        } else {
            string = abstractInsnNode instanceof LdcInsnNode ? string + ((LdcInsnNode)abstractInsnNode).cst : (abstractInsnNode instanceof IntInsnNode ? string + ((IntInsnNode)abstractInsnNode).operand : (abstractInsnNode instanceof FrameNode ? string + String.format("[%s] ", Bytecode.getOpcodeName(((FrameNode)abstractInsnNode).type, "H_INVOKEINTERFACE", -1)) : string + String.format("[%s] ", Bytecode.getOpcodeName(abstractInsnNode))));
        }
        return string;
    }

    public static String getOpcodeName(AbstractInsnNode abstractInsnNode) {
        return abstractInsnNode != null ? Bytecode.getOpcodeName(abstractInsnNode.getOpcode()) : "";
    }

    public static String getOpcodeName(int n2) {
        return Bytecode.getOpcodeName(n2, "UNINITIALIZED_THIS", 1);
    }

    private static String getOpcodeName(int n2, String string, int n3) {
        if (n2 >= n3) {
            boolean bl = false;
            try {
                for (Field field : Opcodes.class.getDeclaredFields()) {
                    if (!bl && !field.getName().equals(string)) continue;
                    bl = true;
                    if (field.getType() != Integer.TYPE || field.getInt(null) != n2) continue;
                    return field.getName();
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return n2 >= 0 ? String.valueOf(n2) : "UNKNOWN";
    }

    public static boolean methodHasLineNumbers(MethodNode methodNode) {
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
        while (listIterator.hasNext()) {
            if (!(listIterator.next() instanceof LineNumberNode)) continue;
            return true;
        }
        return false;
    }

    public static boolean methodIsStatic(MethodNode methodNode) {
        return (methodNode.access & 8) == 8;
    }

    public static boolean fieldIsStatic(FieldNode fieldNode) {
        return (fieldNode.access & 8) == 8;
    }

    public static int getFirstNonArgLocalIndex(MethodNode methodNode) {
        return Bytecode.getFirstNonArgLocalIndex(Type.getArgumentTypes(methodNode.desc), (methodNode.access & 8) == 0);
    }

    public static int getFirstNonArgLocalIndex(Type[] arrtype, boolean bl) {
        return Bytecode.getArgsSize(arrtype) + (bl ? 1 : 0);
    }

    public static int getArgsSize(Type[] arrtype) {
        int n2 = 0;
        for (Type type : arrtype) {
            n2 += type.getSize();
        }
        return n2;
    }

    public static void loadArgs(Type[] arrtype, InsnList insnList, int n2) {
        Bytecode.loadArgs(arrtype, insnList, n2, -1);
    }

    public static void loadArgs(Type[] arrtype, InsnList insnList, int n2, int n3) {
        Bytecode.loadArgs(arrtype, insnList, n2, n3, null);
    }

    public static void loadArgs(Type[] arrtype, InsnList insnList, int n2, int n3, Type[] arrtype2) {
        int n4 = n2;
        int n5 = 0;
        for (Type type : arrtype) {
            insnList.add(new VarInsnNode(type.getOpcode(21), n4));
            if (arrtype2 != null && n5 < arrtype2.length && arrtype2[n5] != null) {
                insnList.add(new TypeInsnNode(192, arrtype2[n5].getInternalName()));
            }
            if (n3 >= n2 && (n4 += type.getSize()) >= n3) {
                return;
            }
            ++n5;
        }
    }

    public static Map<LabelNode, LabelNode> cloneLabels(InsnList insnList) {
        HashMap<LabelNode, LabelNode> hashMap = new HashMap<LabelNode, LabelNode>();
        ListIterator<AbstractInsnNode> listIterator = insnList.iterator();
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
            if (!(abstractInsnNode instanceof LabelNode)) continue;
            hashMap.put((LabelNode)abstractInsnNode, new LabelNode(((LabelNode)abstractInsnNode).getLabel()));
        }
        return hashMap;
    }

    public static String generateDescriptor(Object object, Object ... arrobject) {
        StringBuilder stringBuilder = new StringBuilder().append('(');
        for (Object object2 : arrobject) {
            stringBuilder.append(Bytecode.toDescriptor(object2));
        }
        return stringBuilder.append(')').append(object != null ? Bytecode.toDescriptor(object) : "V").toString();
    }

    private static String toDescriptor(Object object) {
        if (object instanceof String) {
            return (String)object;
        }
        if (object instanceof Type) {
            return object.toString();
        }
        if (object instanceof Class) {
            return Type.getDescriptor((Class)object);
        }
        return object == null ? "" : object.toString();
    }

    public static String getDescriptor(Type[] arrtype) {
        return "(" + Joiner.on("").join(arrtype) + ")";
    }

    public static String getDescriptor(Type[] arrtype, Type type) {
        return Bytecode.getDescriptor(arrtype) + type.toString();
    }

    public static String changeDescriptorReturnType(String string, String string2) {
        if (string == null) {
            return null;
        }
        if (string2 == null) {
            return string;
        }
        return string.substring(0, string.lastIndexOf(41) + 1) + string2;
    }

    public static String getSimpleName(Class<? extends Annotation> class_) {
        return class_.getSimpleName();
    }

    public static String getSimpleName(AnnotationNode annotationNode) {
        return Bytecode.getSimpleName(annotationNode.desc);
    }

    public static String getSimpleName(String string) {
        int n2 = Math.max(string.lastIndexOf(47), 0);
        return string.substring(n2 + 1).replace(";", "");
    }

    public static boolean isConstant(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode == null) {
            return false;
        }
        return Ints.contains(CONSTANTS_ALL, abstractInsnNode.getOpcode());
    }

    public static Object getConstant(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode == null) {
            return null;
        }
        if (abstractInsnNode instanceof LdcInsnNode) {
            return ((LdcInsnNode)abstractInsnNode).cst;
        }
        if (abstractInsnNode instanceof IntInsnNode) {
            int n2 = ((IntInsnNode)abstractInsnNode).operand;
            if (abstractInsnNode.getOpcode() == 16 || abstractInsnNode.getOpcode() == 17) {
                return n2;
            }
            throw new IllegalArgumentException("IntInsnNode with invalid opcode " + abstractInsnNode.getOpcode() + " in getConstant");
        }
        int n3 = Ints.indexOf(CONSTANTS_ALL, abstractInsnNode.getOpcode());
        return n3 < 0 ? null : CONSTANTS_VALUES[n3];
    }

    public static Type getConstantType(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode == null) {
            return null;
        }
        if (abstractInsnNode instanceof LdcInsnNode) {
            Object object = ((LdcInsnNode)abstractInsnNode).cst;
            if (object instanceof Integer) {
                return Type.getType("I");
            }
            if (object instanceof Float) {
                return Type.getType("F");
            }
            if (object instanceof Long) {
                return Type.getType("J");
            }
            if (object instanceof Double) {
                return Type.getType("D");
            }
            if (object instanceof String) {
                return Type.getType("Ljava/lang/String;");
            }
            if (object instanceof Type) {
                return Type.getType("Ljava/lang/Class;");
            }
            throw new IllegalArgumentException("LdcInsnNode with invalid payload type " + object.getClass() + " in getConstant");
        }
        int n2 = Ints.indexOf(CONSTANTS_ALL, abstractInsnNode.getOpcode());
        return n2 < 0 ? null : Type.getType(CONSTANTS_TYPES[n2]);
    }

    public static boolean hasFlag(ClassNode classNode, int n2) {
        return (classNode.access & n2) == n2;
    }

    public static boolean hasFlag(MethodNode methodNode, int n2) {
        return (methodNode.access & n2) == n2;
    }

    public static boolean hasFlag(FieldNode fieldNode, int n2) {
        return (fieldNode.access & n2) == n2;
    }

    public static boolean compareFlags(MethodNode methodNode, MethodNode methodNode2, int n2) {
        return Bytecode.hasFlag(methodNode, n2) == Bytecode.hasFlag(methodNode2, n2);
    }

    public static boolean compareFlags(FieldNode fieldNode, FieldNode fieldNode2, int n2) {
        return Bytecode.hasFlag(fieldNode, n2) == Bytecode.hasFlag(fieldNode2, n2);
    }

    public static Visibility getVisibility(MethodNode methodNode) {
        return Bytecode.getVisibility(methodNode.access & 7);
    }

    public static Visibility getVisibility(FieldNode fieldNode) {
        return Bytecode.getVisibility(fieldNode.access & 7);
    }

    private static Visibility getVisibility(int n2) {
        if ((n2 & 4) != 0) {
            return Visibility.PROTECTED;
        }
        if ((n2 & 2) != 0) {
            return Visibility.PRIVATE;
        }
        if ((n2 & 1) != 0) {
            return Visibility.PUBLIC;
        }
        return Visibility.PACKAGE;
    }

    public static void setVisibility(MethodNode methodNode, Visibility visibility) {
        methodNode.access = Bytecode.setVisibility(methodNode.access, visibility.access);
    }

    public static void setVisibility(FieldNode fieldNode, Visibility visibility) {
        fieldNode.access = Bytecode.setVisibility(fieldNode.access, visibility.access);
    }

    public static void setVisibility(MethodNode methodNode, int n2) {
        methodNode.access = Bytecode.setVisibility(methodNode.access, n2);
    }

    public static void setVisibility(FieldNode fieldNode, int n2) {
        fieldNode.access = Bytecode.setVisibility(fieldNode.access, n2);
    }

    private static int setVisibility(int n2, int n3) {
        return n2 & 0xFFFFFFF8 | n3 & 7;
    }

    public static int getMaxLineNumber(ClassNode classNode, int n2, int n3) {
        int n4 = 0;
        for (MethodNode methodNode : classNode.methods) {
            ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
            while (listIterator.hasNext()) {
                AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
                if (!(abstractInsnNode instanceof LineNumberNode)) continue;
                n4 = Math.max(n4, ((LineNumberNode)abstractInsnNode).line);
            }
        }
        return Math.max(n2, n4 + n3);
    }

    public static String getBoxingType(Type type) {
        return type == null ? null : BOXING_TYPES[type.getSort()];
    }

    public static String getUnboxingMethod(Type type) {
        return type == null ? null : UNBOXING_METHODS[type.getSort()];
    }

    public static void mergeAnnotations(ClassNode classNode, ClassNode classNode2) {
        classNode2.visibleAnnotations = Bytecode.mergeAnnotations(classNode.visibleAnnotations, classNode2.visibleAnnotations, "class", classNode.name);
        classNode2.invisibleAnnotations = Bytecode.mergeAnnotations(classNode.invisibleAnnotations, classNode2.invisibleAnnotations, "class", classNode.name);
    }

    public static void mergeAnnotations(MethodNode methodNode, MethodNode methodNode2) {
        methodNode2.visibleAnnotations = Bytecode.mergeAnnotations(methodNode.visibleAnnotations, methodNode2.visibleAnnotations, "method", methodNode.name);
        methodNode2.invisibleAnnotations = Bytecode.mergeAnnotations(methodNode.invisibleAnnotations, methodNode2.invisibleAnnotations, "method", methodNode.name);
    }

    public static void mergeAnnotations(FieldNode fieldNode, FieldNode fieldNode2) {
        fieldNode2.visibleAnnotations = Bytecode.mergeAnnotations(fieldNode.visibleAnnotations, fieldNode2.visibleAnnotations, "field", fieldNode.name);
        fieldNode2.invisibleAnnotations = Bytecode.mergeAnnotations(fieldNode.invisibleAnnotations, fieldNode2.invisibleAnnotations, "field", fieldNode.name);
    }

    private static List<AnnotationNode> mergeAnnotations(List<AnnotationNode> list, List<AnnotationNode> list2, String string, String string2) {
        try {
            if (list == null) {
                return list2;
            }
            if (list2 == null) {
                list2 = new ArrayList<AnnotationNode>();
            }
            for (AnnotationNode annotationNode : list) {
                if (!Bytecode.isMergeableAnnotation(annotationNode)) continue;
                Iterator<AnnotationNode> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    if (!iterator2.next().desc.equals(annotationNode.desc)) continue;
                    iterator2.remove();
                    break;
                }
                list2.add(annotationNode);
            }
        }
        catch (Exception exception) {
            logger.warn("Exception encountered whilst merging annotations for {} {}", new Object[]{string, string2});
        }
        return list2;
    }

    private static boolean isMergeableAnnotation(AnnotationNode annotationNode) {
        if (annotationNode.desc.startsWith("L" + Constants.MIXIN_PACKAGE_REF)) {
            return mergeableAnnotationPattern.matcher(annotationNode.desc).matches();
        }
        return true;
    }

    private static Pattern getMergeableAnnotationPattern() {
        StringBuilder stringBuilder = new StringBuilder("^L(");
        for (int i2 = 0; i2 < MERGEABLE_MIXIN_ANNOTATIONS.length; ++i2) {
            if (i2 > 0) {
                stringBuilder.append('|');
            }
            stringBuilder.append(MERGEABLE_MIXIN_ANNOTATIONS[i2].getName().replace('.', '/'));
        }
        return Pattern.compile(stringBuilder.append(");$").toString());
    }

    public static void compareBridgeMethods(MethodNode methodNode, MethodNode methodNode2) {
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
        ListIterator<AbstractInsnNode> listIterator2 = methodNode2.instructions.iterator();
        int n2 = 0;
        while (listIterator.hasNext() && listIterator2.hasNext()) {
            AbstractInsnNode abstractInsnNode = listIterator.next();
            AbstractInsnNode abstractInsnNode2 = listIterator2.next();
            if (!(abstractInsnNode instanceof LabelNode)) {
                AbstractInsnNode abstractInsnNode3;
                AbstractInsnNode abstractInsnNode4;
                if (abstractInsnNode instanceof MethodInsnNode) {
                    abstractInsnNode4 = (MethodInsnNode)abstractInsnNode;
                    abstractInsnNode3 = (MethodInsnNode)abstractInsnNode2;
                    if (!((MethodInsnNode)abstractInsnNode4).name.equals(abstractInsnNode3.name)) {
                        throw new SyntheticBridgeException(SyntheticBridgeException.Problem.BAD_INVOKE_NAME, methodNode.name, methodNode.desc, n2, abstractInsnNode, abstractInsnNode2);
                    }
                    if (!((MethodInsnNode)abstractInsnNode4).desc.equals(abstractInsnNode3.desc)) {
                        throw new SyntheticBridgeException(SyntheticBridgeException.Problem.BAD_INVOKE_DESC, methodNode.name, methodNode.desc, n2, abstractInsnNode, abstractInsnNode2);
                    }
                } else {
                    if (abstractInsnNode.getOpcode() != abstractInsnNode2.getOpcode()) {
                        throw new SyntheticBridgeException(SyntheticBridgeException.Problem.BAD_INSN, methodNode.name, methodNode.desc, n2, abstractInsnNode, abstractInsnNode2);
                    }
                    if (abstractInsnNode instanceof VarInsnNode) {
                        abstractInsnNode4 = (VarInsnNode)abstractInsnNode;
                        abstractInsnNode3 = (VarInsnNode)abstractInsnNode2;
                        if (((VarInsnNode)abstractInsnNode4).var != ((VarInsnNode)abstractInsnNode3).var) {
                            throw new SyntheticBridgeException(SyntheticBridgeException.Problem.BAD_LOAD, methodNode.name, methodNode.desc, n2, abstractInsnNode, abstractInsnNode2);
                        }
                    } else if (abstractInsnNode instanceof TypeInsnNode) {
                        abstractInsnNode4 = (TypeInsnNode)abstractInsnNode;
                        abstractInsnNode3 = (TypeInsnNode)abstractInsnNode2;
                        if (abstractInsnNode4.getOpcode() == 192 && !((TypeInsnNode)abstractInsnNode4).desc.equals(((TypeInsnNode)abstractInsnNode3).desc)) {
                            throw new SyntheticBridgeException(SyntheticBridgeException.Problem.BAD_CAST, methodNode.name, methodNode.desc, n2, abstractInsnNode, abstractInsnNode2);
                        }
                    }
                }
            }
            ++n2;
        }
        if (listIterator.hasNext() || listIterator2.hasNext()) {
            throw new SyntheticBridgeException(SyntheticBridgeException.Problem.BAD_LENGTH, methodNode.name, methodNode.desc, n2, null, null);
        }
    }

    public static enum Visibility {
        PRIVATE(2),
        PROTECTED(4),
        PACKAGE(0),
        PUBLIC(1);

        static final int MASK = 7;
        final int access;

        private Visibility(int n3) {
            this.access = n3;
        }
    }
}

