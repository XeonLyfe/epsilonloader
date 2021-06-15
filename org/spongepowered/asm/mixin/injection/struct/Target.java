/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.struct;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.util.Bytecode;

public class Target
implements Comparable<Target>,
Iterable<AbstractInsnNode> {
    public final ClassNode classNode;
    public final MethodNode method;
    public final InsnList insns;
    public final boolean isStatic;
    public final boolean isCtor;
    public final Type[] arguments;
    public final Type returnType;
    private final int maxStack;
    private final int maxLocals;
    private final InjectionNodes injectionNodes = new InjectionNodes();
    private String callbackInfoClass;
    private String callbackDescriptor;
    private int[] argIndices;
    private List<Integer> argMapVars;
    private LabelNode start;
    private LabelNode end;

    public Target(ClassNode classNode, MethodNode methodNode) {
        this.classNode = classNode;
        this.method = methodNode;
        this.insns = methodNode.instructions;
        this.isStatic = Bytecode.methodIsStatic(methodNode);
        this.isCtor = methodNode.name.equals("<init>");
        this.arguments = Type.getArgumentTypes(methodNode.desc);
        this.returnType = Type.getReturnType(methodNode.desc);
        this.maxStack = methodNode.maxStack;
        this.maxLocals = methodNode.maxLocals;
    }

    public InjectionNodes.InjectionNode addInjectionNode(AbstractInsnNode abstractInsnNode) {
        return this.injectionNodes.add(abstractInsnNode);
    }

    public InjectionNodes.InjectionNode getInjectionNode(AbstractInsnNode abstractInsnNode) {
        return this.injectionNodes.get(abstractInsnNode);
    }

    public int getMaxLocals() {
        return this.maxLocals;
    }

    public int getMaxStack() {
        return this.maxStack;
    }

    public int getCurrentMaxLocals() {
        return this.method.maxLocals;
    }

    public int getCurrentMaxStack() {
        return this.method.maxStack;
    }

    public int allocateLocal() {
        return this.allocateLocals(1);
    }

    public int allocateLocals(int n2) {
        int n3 = this.method.maxLocals;
        this.method.maxLocals += n2;
        return n3;
    }

    public void addToLocals(int n2) {
        this.setMaxLocals(this.maxLocals + n2);
    }

    public void setMaxLocals(int n2) {
        if (n2 > this.method.maxLocals) {
            this.method.maxLocals = n2;
        }
    }

    public void addToStack(int n2) {
        this.setMaxStack(this.maxStack + n2);
    }

    public void setMaxStack(int n2) {
        if (n2 > this.method.maxStack) {
            this.method.maxStack = n2;
        }
    }

    public int[] generateArgMap(Type[] arrtype, int n2) {
        if (this.argMapVars == null) {
            this.argMapVars = new ArrayList<Integer>();
        }
        int[] arrn = new int[arrtype.length];
        int n3 = 0;
        for (int i2 = n2; i2 < arrtype.length; ++i2) {
            int n4 = arrtype[i2].getSize();
            arrn[i2] = this.allocateArgMapLocal(n3, n4);
            n3 += n4;
        }
        return arrn;
    }

    private int allocateArgMapLocal(int n2, int n3) {
        if (n2 >= this.argMapVars.size()) {
            int n4 = this.allocateLocals(n3);
            for (int i2 = 0; i2 < n3; ++i2) {
                this.argMapVars.add(n4 + i2);
            }
            return n4;
        }
        int n5 = this.argMapVars.get(n2);
        if (n3 > 1 && n2 + n3 > this.argMapVars.size()) {
            int n6 = this.allocateLocals(1);
            if (n6 == n5 + 1) {
                this.argMapVars.add(n6);
                return n5;
            }
            this.argMapVars.set(n2, n6);
            this.argMapVars.add(this.allocateLocals(1));
            return n6;
        }
        return n5;
    }

    public int[] getArgIndices() {
        if (this.argIndices == null) {
            this.argIndices = this.calcArgIndices(this.isStatic ? 0 : 1);
        }
        return this.argIndices;
    }

    private int[] calcArgIndices(int n2) {
        int[] arrn = new int[this.arguments.length];
        for (int i2 = 0; i2 < this.arguments.length; ++i2) {
            arrn[i2] = n2;
            n2 += this.arguments[i2].getSize();
        }
        return arrn;
    }

    public String getCallbackInfoClass() {
        if (this.callbackInfoClass == null) {
            this.callbackInfoClass = CallbackInfo.getCallInfoClassName(this.returnType);
        }
        return this.callbackInfoClass;
    }

    public String getSimpleCallbackDescriptor() {
        return String.format("(L%s;)V", this.getCallbackInfoClass());
    }

    public String getCallbackDescriptor(Type[] arrtype, Type[] arrtype2) {
        return this.getCallbackDescriptor(false, arrtype, arrtype2, 0, 32767);
    }

    public String getCallbackDescriptor(boolean bl, Type[] arrtype, Type[] arrtype2, int n2, int n3) {
        if (this.callbackDescriptor == null) {
            this.callbackDescriptor = String.format("(%sL%s;)V", this.method.desc.substring(1, this.method.desc.indexOf(41)), this.getCallbackInfoClass());
        }
        if (!bl || arrtype == null) {
            return this.callbackDescriptor;
        }
        StringBuilder stringBuilder = new StringBuilder(this.callbackDescriptor.substring(0, this.callbackDescriptor.indexOf(41)));
        for (int i2 = n2; i2 < arrtype.length && n3 > 0; ++i2) {
            if (arrtype[i2] == null) continue;
            stringBuilder.append(arrtype[i2].getDescriptor());
            --n3;
        }
        return stringBuilder.append(")V").toString();
    }

    public String toString() {
        return String.format("%s::%s%s", this.classNode.name, this.method.name, this.method.desc);
    }

    @Override
    public int compareTo(Target target) {
        if (target == null) {
            return Integer.MAX_VALUE;
        }
        return this.toString().compareTo(target.toString());
    }

    public int indexOf(InjectionNodes.InjectionNode injectionNode) {
        return this.insns.indexOf(injectionNode.getCurrentTarget());
    }

    public int indexOf(AbstractInsnNode abstractInsnNode) {
        return this.insns.indexOf(abstractInsnNode);
    }

    public AbstractInsnNode get(int n2) {
        return this.insns.get(n2);
    }

    @Override
    public Iterator<AbstractInsnNode> iterator() {
        return this.insns.iterator();
    }

    public MethodInsnNode findInitNodeFor(TypeInsnNode typeInsnNode) {
        int n2 = this.indexOf(typeInsnNode);
        ListIterator<AbstractInsnNode> listIterator = this.insns.iterator(n2);
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
            if (!(abstractInsnNode instanceof MethodInsnNode) || abstractInsnNode.getOpcode() != 183) continue;
            MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
            if (!"<init>".equals(methodInsnNode.name) || !methodInsnNode.owner.equals(typeInsnNode.desc)) continue;
            return methodInsnNode;
        }
        return null;
    }

    public MethodInsnNode findSuperInitNode() {
        if (!this.isCtor) {
            return null;
        }
        return Bytecode.findSuperInit(this.method, ClassInfo.forName(this.classNode.name).getSuperName());
    }

    public void insertBefore(InjectionNodes.InjectionNode injectionNode, InsnList insnList) {
        this.insns.insertBefore(injectionNode.getCurrentTarget(), insnList);
    }

    public void insertBefore(AbstractInsnNode abstractInsnNode, InsnList insnList) {
        this.insns.insertBefore(abstractInsnNode, insnList);
    }

    public void replaceNode(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        this.insns.insertBefore(abstractInsnNode, abstractInsnNode2);
        this.insns.remove(abstractInsnNode);
        this.injectionNodes.replace(abstractInsnNode, abstractInsnNode2);
    }

    public void replaceNode(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2, InsnList insnList) {
        this.insns.insertBefore(abstractInsnNode, insnList);
        this.insns.remove(abstractInsnNode);
        this.injectionNodes.replace(abstractInsnNode, abstractInsnNode2);
    }

    public void wrapNode(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2, InsnList insnList, InsnList insnList2) {
        this.insns.insertBefore(abstractInsnNode, insnList);
        this.insns.insert(abstractInsnNode, insnList2);
        this.injectionNodes.replace(abstractInsnNode, abstractInsnNode2);
    }

    public void replaceNode(AbstractInsnNode abstractInsnNode, InsnList insnList) {
        this.insns.insertBefore(abstractInsnNode, insnList);
        this.removeNode(abstractInsnNode);
    }

    public void removeNode(AbstractInsnNode abstractInsnNode) {
        this.insns.remove(abstractInsnNode);
        this.injectionNodes.remove(abstractInsnNode);
    }

    public void addLocalVariable(int n2, String string, String string2) {
        if (this.start == null) {
            this.start = new LabelNode(new Label());
            this.end = new LabelNode(new Label());
            this.insns.insert(this.start);
            this.insns.add(this.end);
        }
        this.addLocalVariable(n2, string, string2, this.start, this.end);
    }

    private void addLocalVariable(int n2, String string, String string2, LabelNode labelNode, LabelNode labelNode2) {
        if (this.method.localVariables == null) {
            this.method.localVariables = new ArrayList<LocalVariableNode>();
        }
        this.method.localVariables.add(new LocalVariableNode(string, string2, null, labelNode, labelNode2, n2));
    }
}

