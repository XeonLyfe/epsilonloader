/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.modify;

import java.util.Collection;
import java.util.List;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.modify.InvalidImplicitDiscriminatorException;
import org.spongepowered.asm.mixin.injection.modify.LocalVariableDiscriminator;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.SignaturePrinter;

public class ModifyVariableInjector
extends Injector {
    private final LocalVariableDiscriminator discriminator;

    public ModifyVariableInjector(InjectionInfo injectionInfo, LocalVariableDiscriminator localVariableDiscriminator) {
        super(injectionInfo);
        this.discriminator = localVariableDiscriminator;
    }

    @Override
    protected boolean findTargetNodes(MethodNode methodNode, InjectionPoint injectionPoint, InsnList insnList, Collection<AbstractInsnNode> collection) {
        if (injectionPoint instanceof ContextualInjectionPoint) {
            Target target = this.info.getContext().getTargetMethod(methodNode);
            return ((ContextualInjectionPoint)injectionPoint).find(target, collection);
        }
        return injectionPoint.find(methodNode.desc, insnList, collection);
    }

    @Override
    protected void sanityCheck(Target target, List<InjectionPoint> list) {
        super.sanityCheck(target, list);
        if (target.isStatic != this.isStatic) {
            throw new InvalidInjectionException(this.info, "'static' of variable modifier method does not match target in " + this);
        }
        int n2 = this.discriminator.getOrdinal();
        if (n2 < -1) {
            throw new InvalidInjectionException(this.info, "Invalid ordinal " + n2 + " specified in " + this);
        }
        if (this.discriminator.getIndex() == 0 && !this.isStatic) {
            throw new InvalidInjectionException(this.info, "Invalid index 0 specified in non-static variable modifier " + this);
        }
    }

    @Override
    protected void inject(Target target, InjectionNodes.InjectionNode injectionNode) {
        String string;
        if (injectionNode.isReplaced()) {
            throw new InvalidInjectionException(this.info, "Variable modifier target for " + this + " was removed by another injector");
        }
        Context context = new Context(this.returnType, this.discriminator.isArgsOnly(), target, injectionNode.getCurrentTarget());
        if (this.discriminator.printLVT()) {
            this.printLocals(context);
        }
        if (!(string = Bytecode.getDescriptor(new Type[]{this.returnType}, this.returnType)).equals(this.methodNode.desc)) {
            throw new InvalidInjectionException(this.info, "Variable modifier " + this + " has an invalid signature, expected " + string + " but found " + this.methodNode.desc);
        }
        try {
            int n2 = this.discriminator.findLocal(context);
            if (n2 > -1) {
                this.inject(context, n2);
            }
        }
        catch (InvalidImplicitDiscriminatorException invalidImplicitDiscriminatorException) {
            if (this.discriminator.printLVT()) {
                this.info.addCallbackInvocation(this.methodNode);
                return;
            }
            throw new InvalidInjectionException(this.info, "Implicit variable modifier injection failed in " + this, (Throwable)invalidImplicitDiscriminatorException);
        }
        target.insns.insertBefore(context.node, context.insns);
        target.addToStack(this.isStatic ? 1 : 2);
    }

    private void printLocals(Context context) {
        SignaturePrinter signaturePrinter = new SignaturePrinter(this.methodNode.name, this.returnType, this.methodArgs, new String[]{"var"});
        signaturePrinter.setModifiers(this.methodNode);
        new PrettyPrinter().kvWidth(20).kv("Target Class", this.classNode.name.replace('/', '.')).kv("Target Method", context.target.method.name).kv("Callback Name", this.methodNode.name).kv("Capture Type", SignaturePrinter.getTypeName(this.returnType, false)).kv("Instruction", "%s %s", context.node.getClass().getSimpleName(), Bytecode.getOpcodeName(context.node.getOpcode())).hr().kv("Match mode", this.discriminator.isImplicit(context) ? "IMPLICIT (match single)" : "EXPLICIT (match by criteria)").kv("Match ordinal", this.discriminator.getOrdinal() < 0 ? "any" : Integer.valueOf(this.discriminator.getOrdinal())).kv("Match index", this.discriminator.getIndex() < context.baseArgIndex ? "any" : Integer.valueOf(this.discriminator.getIndex())).kv("Match name(s)", this.discriminator.hasNames() ? this.discriminator.getNames() : "any").kv("Args only", this.discriminator.isArgsOnly()).hr().add(context).print(System.err);
    }

    private void inject(Context context, int n2) {
        if (!this.isStatic) {
            context.insns.add(new VarInsnNode(25, 0));
        }
        context.insns.add(new VarInsnNode(this.returnType.getOpcode(21), n2));
        this.invokeHandler(context.insns);
        context.insns.add(new VarInsnNode(this.returnType.getOpcode(54), n2));
    }

    static abstract class ContextualInjectionPoint
    extends InjectionPoint {
        protected final IMixinContext context;

        ContextualInjectionPoint(IMixinContext iMixinContext) {
            this.context = iMixinContext;
        }

        @Override
        public boolean find(String string, InsnList insnList, Collection<AbstractInsnNode> collection) {
            throw new InvalidInjectionException(this.context, this.getAtCode() + " injection point must be used in conjunction with @ModifyVariable");
        }

        abstract boolean find(Target var1, Collection<AbstractInsnNode> var2);
    }

    static class Context
    extends LocalVariableDiscriminator.Context {
        final InsnList insns = new InsnList();

        public Context(Type type, boolean bl, Target target, AbstractInsnNode abstractInsnNode) {
            super(type, bl, target, abstractInsnNode);
        }
    }
}

