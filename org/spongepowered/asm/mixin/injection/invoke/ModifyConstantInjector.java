/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 */
package org.spongepowered.asm.mixin.injection.invoke;

import org.apache.logging.log4j.Level;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.JumpInsnNode;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.invoke.RedirectInjector;
import org.spongepowered.asm.mixin.injection.invoke.util.InsnFinder;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.Locals;
import org.spongepowered.asm.util.SignaturePrinter;

public class ModifyConstantInjector
extends RedirectInjector {
    private static final int OPCODE_OFFSET = 6;

    public ModifyConstantInjector(InjectionInfo injectionInfo) {
        super(injectionInfo, "@ModifyConstant");
    }

    @Override
    protected void inject(Target target, InjectionNodes.InjectionNode injectionNode) {
        if (!this.preInject(injectionNode)) {
            return;
        }
        if (injectionNode.isReplaced()) {
            throw new UnsupportedOperationException("Target failure for " + this.info);
        }
        AbstractInsnNode abstractInsnNode = injectionNode.getCurrentTarget();
        if (abstractInsnNode instanceof JumpInsnNode) {
            this.checkTargetModifiers(target, false);
            this.injectExpandedConstantModifier(target, (JumpInsnNode)abstractInsnNode);
            return;
        }
        if (Bytecode.isConstant(abstractInsnNode)) {
            this.checkTargetModifiers(target, false);
            this.injectConstantModifier(target, abstractInsnNode);
            return;
        }
        throw new InvalidInjectionException(this.info, this.annotationType + " annotation is targetting an invalid insn in " + target + " in " + this);
    }

    private void injectExpandedConstantModifier(Target target, JumpInsnNode jumpInsnNode) {
        int n2 = jumpInsnNode.getOpcode();
        if (n2 < 155 || n2 > 158) {
            throw new InvalidInjectionException(this.info, this.annotationType + " annotation selected an invalid opcode " + Bytecode.getOpcodeName(n2) + " in " + target + " in " + this);
        }
        InsnList insnList = new InsnList();
        insnList.add(new InsnNode(3));
        AbstractInsnNode abstractInsnNode = this.invokeConstantHandler(Type.getType("I"), target, insnList, insnList);
        insnList.add(new JumpInsnNode(n2 + 6, jumpInsnNode.label));
        target.replaceNode(jumpInsnNode, abstractInsnNode, insnList);
        target.addToStack(1);
    }

    private void injectConstantModifier(Target target, AbstractInsnNode abstractInsnNode) {
        Type type = Bytecode.getConstantType(abstractInsnNode);
        if (type.getSort() <= 5 && this.info.getContext().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
            this.checkNarrowing(target, abstractInsnNode, type);
        }
        InsnList insnList = new InsnList();
        InsnList insnList2 = new InsnList();
        AbstractInsnNode abstractInsnNode2 = this.invokeConstantHandler(type, target, insnList, insnList2);
        target.wrapNode(abstractInsnNode, abstractInsnNode2, insnList, insnList2);
    }

    private AbstractInsnNode invokeConstantHandler(Type type, Target target, InsnList insnList, InsnList insnList2) {
        String string = Bytecode.generateDescriptor(type, type);
        boolean bl = this.checkDescriptor(string, target, "getter");
        if (!this.isStatic) {
            insnList.insert(new VarInsnNode(25, 0));
            target.addToStack(1);
        }
        if (bl) {
            this.pushArgs(target.arguments, insnList2, target.getArgIndices(), 0, target.arguments.length);
            target.addToStack(Bytecode.getArgsSize(target.arguments));
        }
        return this.invokeHandler(insnList2);
    }

    private void checkNarrowing(Target target, AbstractInsnNode abstractInsnNode, Type type) {
        int n2;
        LocalVariableNode localVariableNode;
        AbstractInsnNode abstractInsnNode2 = new InsnFinder().findPopInsn(target, abstractInsnNode);
        if (abstractInsnNode2 == null) {
            return;
        }
        if (abstractInsnNode2 instanceof FieldInsnNode) {
            FieldInsnNode fieldInsnNode = (FieldInsnNode)abstractInsnNode2;
            Type type2 = Type.getType(fieldInsnNode.desc);
            this.checkNarrowing(target, abstractInsnNode, type, type2, target.indexOf(abstractInsnNode2), String.format("%s %s %s.%s", Bytecode.getOpcodeName(abstractInsnNode2), SignaturePrinter.getTypeName(type2, false), fieldInsnNode.owner.replace('/', '.'), fieldInsnNode.name));
        } else if (abstractInsnNode2.getOpcode() == 172) {
            this.checkNarrowing(target, abstractInsnNode, type, target.returnType, target.indexOf(abstractInsnNode2), "RETURN " + SignaturePrinter.getTypeName(target.returnType, false));
        } else if (abstractInsnNode2.getOpcode() == 54 && (localVariableNode = Locals.getLocalVariableAt(target.classNode, target.method, abstractInsnNode2, n2 = ((VarInsnNode)abstractInsnNode2).var)) != null && localVariableNode.desc != null) {
            String string = localVariableNode.name != null ? localVariableNode.name : "unnamed";
            Type type3 = Type.getType(localVariableNode.desc);
            this.checkNarrowing(target, abstractInsnNode, type, type3, target.indexOf(abstractInsnNode2), String.format("ISTORE[var=%d] %s %s", n2, SignaturePrinter.getTypeName(type3, false), string));
        }
    }

    private void checkNarrowing(Target target, AbstractInsnNode abstractInsnNode, Type type, Type type2, int n2, String string) {
        int n3 = type.getSort();
        int n4 = type2.getSort();
        if (n4 < n3) {
            String string2 = SignaturePrinter.getTypeName(type, false);
            String string3 = SignaturePrinter.getTypeName(type2, false);
            String string4 = n4 == 1 ? ". Implicit conversion to <boolean> can cause nondeterministic (JVM-specific) behaviour!" : "";
            Level level = n4 == 1 ? Level.ERROR : Level.WARN;
            Injector.logger.log(level, "Narrowing conversion of <{}> to <{}> in {} target {} at opcode {} ({}){}", new Object[]{string2, string3, this.info, target, n2, string, string4});
        }
    }
}

