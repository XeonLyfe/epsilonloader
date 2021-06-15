/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.gen;

import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.gen.AccessorGeneratorField;
import org.spongepowered.asm.mixin.gen.AccessorInfo;

public class AccessorGeneratorFieldGetter
extends AccessorGeneratorField {
    public AccessorGeneratorFieldGetter(AccessorInfo accessorInfo) {
        super(accessorInfo);
    }

    @Override
    public MethodNode generate() {
        MethodNode methodNode = this.createMethod(this.targetType.getSize(), this.targetType.getSize());
        if (this.isInstanceField) {
            methodNode.instructions.add(new VarInsnNode(25, 0));
        }
        int n2 = this.isInstanceField ? 180 : 178;
        methodNode.instructions.add(new FieldInsnNode(n2, this.info.getClassNode().name, this.targetField.name, this.targetField.desc));
        methodNode.instructions.add(new InsnNode(this.targetType.getOpcode(172)));
        return methodNode;
    }
}

