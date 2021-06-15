/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.invoke;

import java.util.Arrays;
import java.util.List;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.invoke.InvokeInjector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Bytecode;

public class ModifyArgInjector
extends InvokeInjector {
    private final int index;
    private final boolean singleArgMode;

    public ModifyArgInjector(InjectionInfo injectionInfo, int n2) {
        super(injectionInfo, "@ModifyArg");
        this.index = n2;
        this.singleArgMode = this.methodArgs.length == 1;
    }

    @Override
    protected void sanityCheck(Target target, List<InjectionPoint> list) {
        super.sanityCheck(target, list);
        if (this.singleArgMode && !this.methodArgs[0].equals(this.returnType)) {
            throw new InvalidInjectionException(this.info, "@ModifyArg return type on " + this + " must match the parameter type. ARG=" + this.methodArgs[0] + " RETURN=" + this.returnType);
        }
    }

    @Override
    protected void checkTarget(Target target) {
        if (!this.isStatic && target.isStatic) {
            throw new InvalidInjectionException(this.info, "non-static callback method " + this + " targets a static method which is not supported");
        }
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
        int n2 = this.findArgIndex(target, arrtype);
        InsnList insnList = new InsnList();
        int n3 = 0;
        n3 = this.singleArgMode ? this.injectSingleArgHandler(target, arrtype, n2, insnList) : this.injectMultiArgHandler(target, arrtype, n2, insnList);
        target.insns.insertBefore((AbstractInsnNode)methodInsnNode, insnList);
        target.addToLocals(n3);
        target.addToStack(2 - (n3 - 1));
    }

    private int injectSingleArgHandler(Target target, Type[] arrtype, int n2, InsnList insnList) {
        int[] arrn = this.storeArgs(target, arrtype, insnList, n2);
        this.invokeHandlerWithArgs(arrtype, insnList, arrn, n2, n2 + 1);
        this.pushArgs(arrtype, insnList, arrn, n2 + 1, arrtype.length);
        return arrn[arrn.length - 1] - target.getMaxLocals() + arrtype[arrtype.length - 1].getSize();
    }

    private int injectMultiArgHandler(Target target, Type[] arrtype, int n2, InsnList insnList) {
        if (!Arrays.equals(arrtype, this.methodArgs)) {
            throw new InvalidInjectionException(this.info, "@ModifyArg method " + this + " targets a method with an invalid signature " + Bytecode.getDescriptor(arrtype) + ", expected " + Bytecode.getDescriptor(this.methodArgs));
        }
        int[] arrn = this.storeArgs(target, arrtype, insnList, 0);
        this.pushArgs(arrtype, insnList, arrn, 0, n2);
        this.invokeHandlerWithArgs(arrtype, insnList, arrn, 0, arrtype.length);
        this.pushArgs(arrtype, insnList, arrn, n2 + 1, arrtype.length);
        return arrn[arrn.length - 1] - target.getMaxLocals() + arrtype[arrtype.length - 1].getSize();
    }

    protected int findArgIndex(Target target, Type[] arrtype) {
        if (this.index > -1) {
            if (this.index >= arrtype.length || !arrtype[this.index].equals(this.returnType)) {
                throw new InvalidInjectionException(this.info, "Specified index " + this.index + " for @ModifyArg is invalid for args " + Bytecode.getDescriptor(arrtype) + ", expected " + this.returnType + " on " + this);
            }
            return this.index;
        }
        int n2 = -1;
        for (int i2 = 0; i2 < arrtype.length; ++i2) {
            if (!arrtype[i2].equals(this.returnType)) continue;
            if (n2 != -1) {
                throw new InvalidInjectionException(this.info, "Found duplicate args with index [" + n2 + ", " + i2 + "] matching type " + this.returnType + " for @ModifyArg target " + target + " in " + this + ". Please specify index of desired arg.");
            }
            n2 = i2;
        }
        if (n2 == -1) {
            throw new InvalidInjectionException(this.info, "Could not find arg matching type " + this.returnType + " for @ModifyArg target " + target + " in " + this);
        }
        return n2;
    }
}

