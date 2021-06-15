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

@InjectionPoint.AtCode(value="RETURN")
public class BeforeReturn
extends InjectionPoint {
    private final int ordinal;

    public BeforeReturn(InjectionPointData injectionPointData) {
        super(injectionPointData);
        this.ordinal = injectionPointData.getOrdinal();
    }

    @Override
    public boolean checkPriority(int n2, int n3) {
        return true;
    }

    @Override
    public boolean find(String string, InsnList insnList, Collection<AbstractInsnNode> collection) {
        boolean bl = false;
        int n2 = Type.getReturnType(string).getOpcode(172);
        int n3 = 0;
        ListIterator<AbstractInsnNode> listIterator = insnList.iterator();
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode = listIterator.next();
            if (!(abstractInsnNode instanceof InsnNode) || abstractInsnNode.getOpcode() != n2) continue;
            if (this.ordinal == -1 || this.ordinal == n3) {
                collection.add(abstractInsnNode);
                bl = true;
            }
            ++n3;
        }
        return bl;
    }
}

