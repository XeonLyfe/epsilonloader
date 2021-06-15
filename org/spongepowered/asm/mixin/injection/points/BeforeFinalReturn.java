/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import java.util.ListIterator;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

@InjectionPoint.AtCode(value="TAIL")
public class BeforeFinalReturn
extends InjectionPoint {
    private final IMixinContext context;

    public BeforeFinalReturn(InjectionPointData injectionPointData) {
        super(injectionPointData);
        this.context = injectionPointData.getContext();
    }

    @Override
    public boolean checkPriority(int n2, int n3) {
        return true;
    }

    @Override
    public boolean find(String string, InsnList insnList, Collection<AbstractInsnNode> collection) {
        AbstractInsnNode abstractInsnNode = null;
        int n2 = Type.getReturnType(string).getOpcode(172);
        ListIterator<AbstractInsnNode> listIterator = insnList.iterator();
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode2 = listIterator.next();
            if (!(abstractInsnNode2 instanceof InsnNode) || abstractInsnNode2.getOpcode() != n2) continue;
            abstractInsnNode = abstractInsnNode2;
        }
        if (abstractInsnNode == null) {
            throw new InvalidInjectionException(this.context, "TAIL could not locate a valid RETURN in the target method!");
        }
        collection.add(abstractInsnNode);
        return true;
    }
}

