/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.invoke;

import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.invoke.InvokeInjector;
import org.spongepowered.asm.mixin.injection.invoke.arg.ArgsClassGenerator;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Bytecode;

public class ModifyArgsInjector
extends InvokeInjector {
    private final ArgsClassGenerator argsClassGenerator;

    public ModifyArgsInjector(InjectionInfo injectionInfo) {
        super(injectionInfo, "@ModifyArgs");
        this.argsClassGenerator = (ArgsClassGenerator)injectionInfo.getContext().getExtensions().getGenerator(ArgsClassGenerator.class);
    }

    @Override
    protected void checkTarget(Target target) {
        this.checkTargetModifiers(target, false);
    }

    @Override
    protected void inject(Target target, InjectionNodes.InjectionNode injectionNode) {
        this.checkTargetForNode(target, injectionNode);
        super.inject(target, injectionNode);
    }

    @Override
    protected void injectAtInvoke(Target target, InjectionNodes.InjectionNode injectionNode) {
        MethodInsnNode methodInsnNode = (MethodInsnNode)injectionNode.getCurrentTarget();
        Type[] arrtype = Type.getArgumentTypes(methodInsnNode.desc);
        if (arrtype.length == 0) {
            throw new InvalidInjectionException(this.info, "@ModifyArgs injector " + this + " targets a method invocation " + methodInsnNode.name + methodInsnNode.desc + " with no arguments!");
        }
        String string = this.argsClassGenerator.getClassRef(methodInsnNode.desc);
        boolean bl = this.verifyTarget(target);
        InsnList insnList = new InsnList();
        target.addToStack(1);
        this.packArgs(insnList, string, methodInsnNode);
        if (bl) {
            target.addToStack(Bytecode.getArgsSize(target.arguments));
            Bytecode.loadArgs(target.arguments, insnList, target.isStatic ? 0 : 1);
        }
        this.invokeHandler(insnList);
        this.unpackArgs(insnList, string, arrtype);
        target.insns.insertBefore((AbstractInsnNode)methodInsnNode, insnList);
    }

    private boolean verifyTarget(Target target) {
        String string = String.format("(L%s;)V", ArgsClassGenerator.ARGS_REF);
        if (!this.methodNode.desc.equals(string)) {
            String string2 = Bytecode.changeDescriptorReturnType(target.method.desc, "V");
            String string3 = String.format("(L%s;%s", ArgsClassGenerator.ARGS_REF, string2.substring(1));
            if (this.methodNode.desc.equals(string3)) {
                return true;
            }
            throw new InvalidInjectionException(this.info, "@ModifyArgs injector " + this + " has an invalid signature " + this.methodNode.desc + ", expected " + string + " or " + string3);
        }
        return false;
    }

    private void packArgs(InsnList insnList, String string, MethodInsnNode methodInsnNode) {
        String string2 = Bytecode.changeDescriptorReturnType(methodInsnNode.desc, "L" + string + ";");
        insnList.add(new MethodInsnNode(184, string, "of", string2, false));
        insnList.add(new InsnNode(89));
        if (!this.isStatic) {
            insnList.add(new VarInsnNode(25, 0));
            insnList.add(new InsnNode(95));
        }
    }

    private void unpackArgs(InsnList insnList, String string, Type[] arrtype) {
        for (int i2 = 0; i2 < arrtype.length; ++i2) {
            if (i2 < arrtype.length - 1) {
                insnList.add(new InsnNode(89));
            }
            insnList.add(new MethodInsnNode(182, string, "$" + i2, "()" + arrtype[i2].getDescriptor(), false));
            if (i2 >= arrtype.length - 1) continue;
            if (arrtype[i2].getSize() == 1) {
                insnList.add(new InsnNode(95));
                continue;
            }
            insnList.add(new InsnNode(93));
            insnList.add(new InsnNode(88));
        }
    }
}

