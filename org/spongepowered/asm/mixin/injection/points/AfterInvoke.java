/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.points.BeforeInvoke;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;

@InjectionPoint.AtCode(value="INVOKE_ASSIGN")
public class AfterInvoke
extends BeforeInvoke {
    public AfterInvoke(InjectionPointData injectionPointData) {
        super(injectionPointData);
    }

    @Override
    protected boolean addInsn(InsnList insnList, Collection<AbstractInsnNode> collection, AbstractInsnNode abstractInsnNode) {
        MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
        if (Type.getReturnType(methodInsnNode.desc) == Type.VOID_TYPE) {
            return false;
        }
        if ((abstractInsnNode = InjectionPoint.nextNode(insnList, abstractInsnNode)) instanceof VarInsnNode && abstractInsnNode.getOpcode() >= 54) {
            abstractInsnNode = InjectionPoint.nextNode(insnList, abstractInsnNode);
        }
        collection.add(abstractInsnNode);
        return true;
    }
}

