/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.FrameNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.LineNumberNode;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.lib.tree.analysis.Analyzer;
import org.spongepowered.asm.lib.tree.analysis.AnalyzerException;
import org.spongepowered.asm.lib.tree.analysis.BasicValue;
import org.spongepowered.asm.lib.tree.analysis.Frame;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.util.asm.MixinVerifier;
import org.spongepowered.asm.util.throwables.LVTGeneratorException;

public final class Locals {
    private static final Map<String, List<LocalVariableNode>> calculatedLocalVariables = new HashMap<String, List<LocalVariableNode>>();

    private Locals() {
    }

    public static void loadLocals(Type[] arrtype, InsnList insnList, int n2, int n3) {
        while (n2 < arrtype.length && n3 > 0) {
            if (arrtype[n2] != null) {
                insnList.add(new VarInsnNode(arrtype[n2].getOpcode(21), n2));
                --n3;
            }
            ++n2;
        }
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static LocalVariableNode[] getLocalsAt(ClassNode classNode, MethodNode methodNode, AbstractInsnNode abstractInsnNode) {
        void var12_17;
        for (int i2 = 0; i2 < 3 && (abstractInsnNode instanceof LabelNode || abstractInsnNode instanceof LineNumberNode); ++i2) {
            abstractInsnNode = Locals.nextNode(methodNode.instructions, abstractInsnNode);
        }
        ClassInfo classInfo = ClassInfo.forName(classNode.name);
        if (classInfo == null) {
            throw new LVTGeneratorException("Could not load class metadata for " + classNode.name + " generating LVT for " + methodNode.name);
        }
        ClassInfo.Method method = classInfo.findMethod(methodNode);
        if (method == null) {
            throw new LVTGeneratorException("Could not locate method metadata for " + methodNode.name + " generating LVT in " + classNode.name);
        }
        List<ClassInfo.FrameData> list = method.getFrames();
        LocalVariableNode[] arrlocalVariableNode = new LocalVariableNode[methodNode.maxLocals];
        int n2 = 0;
        int n3 = 0;
        if ((methodNode.access & 8) == 0) {
            arrlocalVariableNode[n2++] = new LocalVariableNode("this", classNode.name, null, null, null, 0);
        }
        for (Type type : Type.getArgumentTypes(methodNode.desc)) {
            arrlocalVariableNode[n2] = new LocalVariableNode("arg" + n3++, type.toString(), null, null, null, n2);
            n2 += type.getSize();
        }
        int n4 = n2;
        int n5 = -1;
        int n6 = 0;
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode2;
            AbstractInsnNode abstractInsnNode3 = (AbstractInsnNode)listIterator.next();
            if (abstractInsnNode3 instanceof FrameNode) {
                abstractInsnNode2 = (FrameNode)abstractInsnNode3;
                ClassInfo.FrameData frameData = ++n5 < list.size() ? list.get(n5) : null;
                n6 = frameData != null && frameData.type == 0 ? Math.min(n6, frameData.locals) : abstractInsnNode2.local.size();
                int n7 = 0;
                int n8 = 0;
                while (n8 < arrlocalVariableNode.length) {
                    Object object;
                    Object object2 = object = n7 < abstractInsnNode2.local.size() ? abstractInsnNode2.local.get(n7) : null;
                    if (object instanceof String) {
                        arrlocalVariableNode[n8] = Locals.getLocalVariableAt(classNode, methodNode, abstractInsnNode, n8);
                    } else if (object instanceof Integer) {
                        boolean bl;
                        boolean bl2 = object == Opcodes.UNINITIALIZED_THIS || object == Opcodes.NULL;
                        boolean bl3 = object == Opcodes.INTEGER || object == Opcodes.FLOAT;
                        boolean bl4 = bl = object == Opcodes.DOUBLE || object == Opcodes.LONG;
                        if (object != Opcodes.TOP) {
                            if (bl2) {
                                arrlocalVariableNode[n8] = null;
                            } else {
                                if (!bl3 && !bl) throw new LVTGeneratorException("Unrecognised locals opcode " + object + " in locals array at position " + n7 + " in " + classNode.name + "." + methodNode.name + methodNode.desc);
                                arrlocalVariableNode[n8] = Locals.getLocalVariableAt(classNode, methodNode, abstractInsnNode, n8);
                                if (bl) {
                                    arrlocalVariableNode[++n8] = null;
                                }
                            }
                        }
                    } else {
                        if (object != null) throw new LVTGeneratorException("Invalid value " + object + " in locals array at position " + n7 + " in " + classNode.name + "." + methodNode.name + methodNode.desc);
                        if (n8 >= n4 && n8 >= n6 && n6 > 0) {
                            arrlocalVariableNode[n8] = null;
                        }
                    }
                    ++n8;
                    ++n7;
                }
            } else if (abstractInsnNode3 instanceof VarInsnNode) {
                abstractInsnNode2 = (VarInsnNode)abstractInsnNode3;
                arrlocalVariableNode[abstractInsnNode2.var] = Locals.getLocalVariableAt(classNode, methodNode, abstractInsnNode, ((VarInsnNode)abstractInsnNode2).var);
            }
            if (abstractInsnNode3 != abstractInsnNode) continue;
            break;
        }
        boolean bl = false;
        while (++var12_17 < arrlocalVariableNode.length) {
            if (arrlocalVariableNode[var12_17] == null || arrlocalVariableNode[var12_17].desc != null) continue;
            arrlocalVariableNode[var12_17] = null;
        }
        return arrlocalVariableNode;
    }

    public static LocalVariableNode getLocalVariableAt(ClassNode classNode, MethodNode methodNode, AbstractInsnNode abstractInsnNode, int n2) {
        return Locals.getLocalVariableAt(classNode, methodNode, methodNode.instructions.indexOf(abstractInsnNode), n2);
    }

    private static LocalVariableNode getLocalVariableAt(ClassNode classNode, MethodNode methodNode, int n2, int n3) {
        LocalVariableNode localVariableNode = null;
        LocalVariableNode localVariableNode2 = null;
        for (LocalVariableNode localVariableNode3 : Locals.getLocalVariableTable(classNode, methodNode)) {
            if (localVariableNode3.index != n3) continue;
            if (Locals.isOpcodeInRange(methodNode.instructions, localVariableNode3, n2)) {
                localVariableNode = localVariableNode3;
                continue;
            }
            if (localVariableNode != null) continue;
            localVariableNode2 = localVariableNode3;
        }
        if (localVariableNode == null && !methodNode.localVariables.isEmpty()) {
            for (LocalVariableNode localVariableNode3 : Locals.getGeneratedLocalVariableTable(classNode, methodNode)) {
                if (localVariableNode3.index != n3 || !Locals.isOpcodeInRange(methodNode.instructions, localVariableNode3, n2)) continue;
                localVariableNode = localVariableNode3;
            }
        }
        return localVariableNode != null ? localVariableNode : localVariableNode2;
    }

    private static boolean isOpcodeInRange(InsnList insnList, LocalVariableNode localVariableNode, int n2) {
        return insnList.indexOf(localVariableNode.start) < n2 && insnList.indexOf(localVariableNode.end) > n2;
    }

    public static List<LocalVariableNode> getLocalVariableTable(ClassNode classNode, MethodNode methodNode) {
        if (methodNode.localVariables.isEmpty()) {
            return Locals.getGeneratedLocalVariableTable(classNode, methodNode);
        }
        return methodNode.localVariables;
    }

    public static List<LocalVariableNode> getGeneratedLocalVariableTable(ClassNode classNode, MethodNode methodNode) {
        String string = String.format("%s.%s%s", classNode.name, methodNode.name, methodNode.desc);
        List<LocalVariableNode> list = calculatedLocalVariables.get(string);
        if (list != null) {
            return list;
        }
        list = Locals.generateLocalVariableTable(classNode, methodNode);
        calculatedLocalVariables.put(string, list);
        return list;
    }

    public static List<LocalVariableNode> generateLocalVariableTable(ClassNode classNode, MethodNode methodNode) {
        int n2;
        ArrayList<Type> arrayList = null;
        if (classNode.interfaces != null) {
            arrayList = new ArrayList<Type>();
            for (String object2 : classNode.interfaces) {
                arrayList.add(Type.getObjectType(object2));
            }
        }
        Object object3 = null;
        if (classNode.superName != null) {
            object3 = Type.getObjectType(classNode.superName);
        }
        Analyzer<BasicValue> analyzer = new Analyzer<BasicValue>(new MixinVerifier(Type.getObjectType(classNode.name), (Type)object3, arrayList, false));
        try {
            analyzer.analyze(classNode.name, methodNode);
        }
        catch (AnalyzerException arrframe) {
            arrframe.printStackTrace();
        }
        Frame<BasicValue>[] arrframe = analyzer.getFrames();
        int n3 = methodNode.instructions.size();
        ArrayList<LocalVariableNode> arrayList2 = new ArrayList<LocalVariableNode>();
        LocalVariableNode[] arrlocalVariableNode = new LocalVariableNode[methodNode.maxLocals];
        BasicValue[] arrbasicValue = new BasicValue[methodNode.maxLocals];
        LabelNode[] arrlabelNode = new LabelNode[n3];
        String[] arrstring = new String[methodNode.maxLocals];
        for (int labelNode = 0; labelNode < n3; ++labelNode) {
            Frame<BasicValue> n22 = arrframe[labelNode];
            if (n22 == null) continue;
            LabelNode labelNode2 = null;
            for (int i2 = 0; i2 < n22.getLocals(); ++i2) {
                Object object;
                BasicValue basicValue = n22.getLocal(i2);
                if (basicValue == null && arrbasicValue[i2] == null || basicValue != null && basicValue.equals(arrbasicValue[i2])) continue;
                if (labelNode2 == null) {
                    object = methodNode.instructions.get(labelNode);
                    if (object instanceof LabelNode) {
                        labelNode2 = (LabelNode)object;
                    } else {
                        arrlabelNode[labelNode] = labelNode2 = new LabelNode();
                    }
                }
                if (basicValue == null && arrbasicValue[i2] != null) {
                    arrayList2.add(arrlocalVariableNode[i2]);
                    arrlocalVariableNode[i2].end = labelNode2;
                    arrlocalVariableNode[i2] = null;
                } else if (basicValue != null) {
                    if (arrbasicValue[i2] != null) {
                        arrayList2.add(arrlocalVariableNode[i2]);
                        arrlocalVariableNode[i2].end = labelNode2;
                        arrlocalVariableNode[i2] = null;
                    }
                    object = basicValue.getType() != null ? basicValue.getType().getDescriptor() : arrstring[i2];
                    arrlocalVariableNode[i2] = new LocalVariableNode("var" + i2, (String)object, null, labelNode2, null, i2);
                    if (object != null) {
                        arrstring[i2] = object;
                    }
                }
                arrbasicValue[i2] = basicValue;
            }
        }
        LabelNode labelNode = null;
        for (n2 = 0; n2 < arrlocalVariableNode.length; ++n2) {
            if (arrlocalVariableNode[n2] == null) continue;
            if (labelNode == null) {
                labelNode = new LabelNode();
                methodNode.instructions.add(labelNode);
            }
            arrlocalVariableNode[n2].end = labelNode;
            arrayList2.add(arrlocalVariableNode[n2]);
        }
        for (n2 = n3 - 1; n2 >= 0; --n2) {
            if (arrlabelNode[n2] == null) continue;
            methodNode.instructions.insert(methodNode.instructions.get(n2), arrlabelNode[n2]);
        }
        return arrayList2;
    }

    private static AbstractInsnNode nextNode(InsnList insnList, AbstractInsnNode abstractInsnNode) {
        int n2 = insnList.indexOf(abstractInsnNode) + 1;
        if (n2 > 0 && n2 < insnList.size()) {
            return insnList.get(n2);
        }
        return abstractInsnNode;
    }
}

