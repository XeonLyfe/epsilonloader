/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.invoke;

import java.util.List;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;

public abstract class InvokeInjector
extends Injector {
    protected final String annotationType;

    public InvokeInjector(InjectionInfo injectionInfo, String string) {
        super(injectionInfo);
        this.annotationType = string;
    }

    @Override
    protected void sanityCheck(Target target, List<InjectionPoint> list) {
        super.sanityCheck(target, list);
        this.checkTarget(target);
    }

    protected void checkTarget(Target target) {
        this.checkTargetModifiers(target, true);
    }

    protected final void checkTargetModifiers(Target target, boolean bl) {
        if (bl && target.isStatic != this.isStatic) {
            throw new InvalidInjectionException(this.info, "'static' modifier of handler method does not match target in " + this);
        }
        if (!bl && !this.isStatic && target.isStatic) {
            throw new InvalidInjectionException(this.info, "non-static callback method " + this + " targets a static method which is not supported");
        }
    }

    protected void checkTargetForNode(Target target, InjectionNodes.InjectionNode injectionNode) {
        if (target.isCtor) {
            MethodInsnNode methodInsnNode = target.findSuperInitNode();
            int n2 = target.indexOf(methodInsnNode);
            int n3 = target.indexOf(injectionNode.getCurrentTarget());
            if (n3 <= n2) {
                if (!this.isStatic) {
                    throw new InvalidInjectionException(this.info, "Pre-super " + this.annotationType + " invocation must be static in " + this);
                }
                return;
            }
        }
        this.checkTargetModifiers(target, true);
    }

    @Override
    protected void inject(Target target, InjectionNodes.InjectionNode injectionNode) {
        if (!(injectionNode.getCurrentTarget() instanceof MethodInsnNode)) {
            throw new InvalidInjectionException(this.info, this.annotationType + " annotation on is targetting a non-method insn in " + target + " in " + this);
        }
        this.injectAtInvoke(target, injectionNode);
    }

    protected abstract void injectAtInvoke(Target var1, InjectionNodes.InjectionNode var2);

    protected AbstractInsnNode invokeHandlerWithArgs(Type[] arrtype, InsnList insnList, int[] arrn) {
        return this.invokeHandlerWithArgs(arrtype, insnList, arrn, 0, arrtype.length);
    }

    protected AbstractInsnNode invokeHandlerWithArgs(Type[] arrtype, InsnList insnList, int[] arrn, int n2, int n3) {
        if (!this.isStatic) {
            insnList.add(new VarInsnNode(25, 0));
        }
        this.pushArgs(arrtype, insnList, arrn, n2, n3);
        return this.invokeHandler(insnList);
    }

    protected int[] storeArgs(Target target, Type[] arrtype, InsnList insnList, int n2) {
        int[] arrn = target.generateArgMap(arrtype, n2);
        this.storeArgs(arrtype, insnList, arrn, n2, arrtype.length);
        return arrn;
    }

    protected void storeArgs(Type[] arrtype, InsnList insnList, int[] arrn, int n2, int n3) {
        for (int i2 = n3 - 1; i2 >= n2; --i2) {
            insnList.add(new VarInsnNode(arrtype[i2].getOpcode(54), arrn[i2]));
        }
    }

    protected void pushArgs(Type[] arrtype, InsnList insnList, int[] arrn, int n2, int n3) {
        for (int i2 = n2; i2 < n3; ++i2) {
            insnList.add(new VarInsnNode(arrtype[i2].getOpcode(21), arrn[i2]));
        }
    }
}

