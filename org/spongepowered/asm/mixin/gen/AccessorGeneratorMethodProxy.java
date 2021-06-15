/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.gen;

import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.gen.AccessorGenerator;
import org.spongepowered.asm.mixin.gen.AccessorInfo;
import org.spongepowered.asm.util.Bytecode;

public class AccessorGeneratorMethodProxy
extends AccessorGenerator {
    private final MethodNode targetMethod;
    private final Type[] argTypes;
    private final Type returnType;
    private final boolean isInstanceMethod;

    public AccessorGeneratorMethodProxy(AccessorInfo accessorInfo) {
        super(accessorInfo);
        this.targetMethod = accessorInfo.getTargetMethod();
        this.argTypes = accessorInfo.getArgTypes();
        this.returnType = accessorInfo.getReturnType();
        this.isInstanceMethod = !Bytecode.hasFlag(this.targetMethod, 8);
    }

    @Override
    public MethodNode generate() {
        int n2 = Bytecode.getArgsSize(this.argTypes) + this.returnType.getSize() + (this.isInstanceMethod ? 1 : 0);
        MethodNode methodNode = this.createMethod(n2, n2);
        if (this.isInstanceMethod) {
            methodNode.instructions.add(new VarInsnNode(25, 0));
        }
        Bytecode.loadArgs(this.argTypes, methodNode.instructions, this.isInstanceMethod ? 1 : 0);
        boolean bl = Bytecode.hasFlag(this.targetMethod, 2);
        int n3 = this.isInstanceMethod ? (bl ? 183 : 182) : 184;
        methodNode.instructions.add(new MethodInsnNode(n3, this.info.getClassNode().name, this.targetMethod.name, this.targetMethod.desc, false));
        methodNode.instructions.add(new InsnNode(this.returnType.getOpcode(172)));
        return methodNode;
    }
}

