/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import java.util.ListIterator;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.points.BeforeInvoke;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.util.Bytecode;

@InjectionPoint.AtCode(value="FIELD")
public class BeforeFieldAccess
extends BeforeInvoke {
    private static final String ARRAY_GET = "get";
    private static final String ARRAY_SET = "set";
    private static final String ARRAY_LENGTH = "length";
    public static final int ARRAY_SEARCH_FUZZ_DEFAULT = 8;
    private final int opcode;
    private final int arrOpcode;
    private final int fuzzFactor;

    public BeforeFieldAccess(InjectionPointData injectionPointData) {
        super(injectionPointData);
        this.opcode = injectionPointData.getOpcode(-1, 180, 181, 178, 179, -1);
        String string = injectionPointData.get("array", "");
        this.arrOpcode = ARRAY_GET.equalsIgnoreCase(string) ? 46 : (ARRAY_SET.equalsIgnoreCase(string) ? 79 : (ARRAY_LENGTH.equalsIgnoreCase(string) ? 190 : 0));
        this.fuzzFactor = Math.min(Math.max(injectionPointData.get("fuzz", 8), 1), 32);
    }

    public int getFuzzFactor() {
        return this.fuzzFactor;
    }

    public int getArrayOpcode() {
        return this.arrOpcode;
    }

    private int getArrayOpcode(String string) {
        if (this.arrOpcode != 190) {
            return Type.getType(string).getElementType().getOpcode(this.arrOpcode);
        }
        return this.arrOpcode;
    }

    @Override
    protected boolean matchesInsn(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode instanceof FieldInsnNode && (((FieldInsnNode)abstractInsnNode).getOpcode() == this.opcode || this.opcode == -1)) {
            if (this.arrOpcode == 0) {
                return true;
            }
            if (abstractInsnNode.getOpcode() != 178 && abstractInsnNode.getOpcode() != 180) {
                return false;
            }
            return Type.getType(((FieldInsnNode)abstractInsnNode).desc).getSort() == 9;
        }
        return false;
    }

    @Override
    protected boolean addInsn(InsnList insnList, Collection<AbstractInsnNode> collection, AbstractInsnNode abstractInsnNode) {
        if (this.arrOpcode > 0) {
            FieldInsnNode fieldInsnNode = (FieldInsnNode)abstractInsnNode;
            int n2 = this.getArrayOpcode(fieldInsnNode.desc);
            this.log("{} > > > > searching for array access opcode {} fuzz={}", this.className, Bytecode.getOpcodeName(n2), this.fuzzFactor);
            if (BeforeFieldAccess.findArrayNode(insnList, fieldInsnNode, n2, this.fuzzFactor) == null) {
                this.log("{} > > > > > failed to locate matching insn", this.className);
                return false;
            }
        }
        this.log("{} > > > > > adding matching insn", this.className);
        return super.addInsn(insnList, collection, abstractInsnNode);
    }

    public static AbstractInsnNode findArrayNode(InsnList insnList, FieldInsnNode fieldInsnNode, int n2, int n3) {
        int n4 = 0;
        ListIterator<AbstractInsnNode> listIterator = insnList.iterator(insnList.indexOf(fieldInsnNode) + 1);
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
            if (abstractInsnNode.getOpcode() == n2) {
                return abstractInsnNode;
            }
            if (abstractInsnNode.getOpcode() == 190 && n4 == 0) {
                return null;
            }
            if (abstractInsnNode instanceof FieldInsnNode) {
                FieldInsnNode fieldInsnNode2 = (FieldInsnNode)abstractInsnNode;
                if (fieldInsnNode2.desc.equals(fieldInsnNode.desc) && fieldInsnNode2.name.equals(fieldInsnNode.name) && fieldInsnNode2.owner.equals(fieldInsnNode.owner)) {
                    return null;
                }
            }
            if (n4++ <= n3) continue;
            return null;
        }
        return null;
    }
}

