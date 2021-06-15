/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.points;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;

@InjectionPoint.AtCode(value="NEW")
public class BeforeNew
extends InjectionPoint {
    private final String target;
    private final String desc;
    private final int ordinal;

    public BeforeNew(InjectionPointData injectionPointData) {
        super(injectionPointData);
        this.ordinal = injectionPointData.getOrdinal();
        String string = Strings.emptyToNull(injectionPointData.get("class", injectionPointData.get("target", "")).replace('.', '/'));
        MemberInfo memberInfo = MemberInfo.parseAndValidate(string, injectionPointData.getContext());
        this.target = memberInfo.toCtorType();
        this.desc = memberInfo.toCtorDesc();
    }

    public boolean hasDescriptor() {
        return this.desc != null;
    }

    @Override
    public boolean find(String string, InsnList insnList, Collection<AbstractInsnNode> collection) {
        boolean bl = false;
        int n2 = 0;
        ArrayList arrayList = new ArrayList();
        Collection<Object> collection2 = this.desc != null ? arrayList : collection;
        ListIterator<AbstractInsnNode> listIterator = insnList.iterator();
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode = listIterator.next();
            if (!(abstractInsnNode instanceof TypeInsnNode) || abstractInsnNode.getOpcode() != 187 || !this.matchesOwner((TypeInsnNode)abstractInsnNode)) continue;
            if (this.ordinal == -1 || this.ordinal == n2) {
                collection2.add(abstractInsnNode);
                bl = this.desc == null;
            }
            ++n2;
        }
        if (this.desc != null) {
            for (TypeInsnNode typeInsnNode : arrayList) {
                if (!this.findCtor(insnList, typeInsnNode)) continue;
                collection.add(typeInsnNode);
                bl = true;
            }
        }
        return bl;
    }

    protected boolean findCtor(InsnList insnList, TypeInsnNode typeInsnNode) {
        int n2 = insnList.indexOf(typeInsnNode);
        ListIterator<AbstractInsnNode> listIterator = insnList.iterator(n2);
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
            if (!(abstractInsnNode instanceof MethodInsnNode) || abstractInsnNode.getOpcode() != 183) continue;
            MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
            if (!"<init>".equals(methodInsnNode.name) || !methodInsnNode.owner.equals(typeInsnNode.desc) || !methodInsnNode.desc.equals(this.desc)) continue;
            return true;
        }
        return false;
    }

    private boolean matchesOwner(TypeInsnNode typeInsnNode) {
        return this.target == null || this.target.equals(typeInsnNode.desc);
    }
}

