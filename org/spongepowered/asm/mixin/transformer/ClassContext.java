/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.transformer;

import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.struct.MemberRef;
import org.spongepowered.asm.mixin.transformer.ClassInfo;

abstract class ClassContext {
    private final Set<ClassInfo.Method> upgradedMethods = new HashSet<ClassInfo.Method>();

    ClassContext() {
    }

    abstract String getClassRef();

    abstract ClassNode getClassNode();

    abstract ClassInfo getClassInfo();

    void addUpgradedMethod(MethodNode methodNode) {
        ClassInfo.Method method = this.getClassInfo().findMethod(methodNode);
        if (method == null) {
            throw new IllegalStateException("Meta method for " + methodNode.name + " not located in " + this);
        }
        this.upgradedMethods.add(method);
    }

    protected void upgradeMethods() {
        for (MethodNode methodNode : this.getClassNode().methods) {
            this.upgradeMethod(methodNode);
        }
    }

    private void upgradeMethod(MethodNode methodNode) {
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
        while (listIterator.hasNext()) {
            MemberRef.Method method;
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
            if (!(abstractInsnNode instanceof MethodInsnNode) || !((MemberRef)(method = new MemberRef.Method((MethodInsnNode)abstractInsnNode))).getOwner().equals(this.getClassRef())) continue;
            ClassInfo.Method method2 = this.getClassInfo().findMethod(((MemberRef)method).getName(), ((MemberRef)method).getDesc(), 10);
            this.upgradeMethodRef(methodNode, method, method2);
        }
    }

    protected void upgradeMethodRef(MethodNode methodNode, MemberRef memberRef, ClassInfo.Method method) {
        if (memberRef.getOpcode() != 183) {
            return;
        }
        if (this.upgradedMethods.contains(method)) {
            memberRef.setOpcode(182);
        }
    }
}

