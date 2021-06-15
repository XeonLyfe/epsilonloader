/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.callback;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.JumpInsnNode;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.LdcInsnNode;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.Surrogate;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.points.BeforeReturn;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InjectionError;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.Locals;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.SignaturePrinter;

public class CallbackInjector
extends Injector {
    private final boolean cancellable;
    private final LocalCapture localCapture;
    private final String identifier;
    private final Map<Integer, String> ids = new HashMap<Integer, String>();
    private int totalInjections = 0;
    private int callbackInfoVar = -1;
    private String lastId;
    private String lastDesc;
    private Target lastTarget;
    private String callbackInfoClass;

    public CallbackInjector(InjectionInfo injectionInfo, boolean bl, LocalCapture localCapture, String string) {
        super(injectionInfo);
        this.cancellable = bl;
        this.localCapture = localCapture;
        this.identifier = string;
    }

    @Override
    protected void sanityCheck(Target target, List<InjectionPoint> list) {
        super.sanityCheck(target, list);
        if (target.isStatic != this.isStatic) {
            throw new InvalidInjectionException(this.info, "'static' modifier of callback method does not match target in " + this);
        }
        if ("<init>".equals(target.method.name)) {
            for (InjectionPoint injectionPoint : list) {
                if (injectionPoint.getClass().equals(BeforeReturn.class)) continue;
                throw new InvalidInjectionException(this.info, "Found injection point type " + injectionPoint.getClass().getSimpleName() + " targetting a ctor in " + this + ". Only RETURN allowed for a ctor target");
            }
        }
    }

    @Override
    protected void addTargetNode(Target target, List<InjectionNodes.InjectionNode> list, AbstractInsnNode abstractInsnNode, Set<InjectionPoint> set) {
        InjectionNodes.InjectionNode injectionNode = target.addInjectionNode(abstractInsnNode);
        for (InjectionPoint injectionPoint : set) {
            String string = injectionPoint.getId();
            if (Strings.isNullOrEmpty(string)) continue;
            String string2 = this.ids.get(injectionNode.getId());
            if (string2 != null && !string2.equals(string)) {
                Injector.logger.warn("Conflicting id for {} insn in {}, found id {} on {}, previously defined as {}", new Object[]{Bytecode.getOpcodeName(abstractInsnNode), target.toString(), string, this.info, string2});
                break;
            }
            this.ids.put(injectionNode.getId(), string);
        }
        list.add(injectionNode);
        ++this.totalInjections;
    }

    @Override
    protected void inject(Target target, InjectionNodes.InjectionNode injectionNode) {
        LocalVariableNode[] arrlocalVariableNode = null;
        if (this.localCapture.isCaptureLocals() || this.localCapture.isPrintLocals()) {
            arrlocalVariableNode = Locals.getLocalsAt(this.classNode, target.method, injectionNode.getCurrentTarget());
        }
        this.inject(new Callback(this.methodNode, target, injectionNode, arrlocalVariableNode, this.localCapture.isCaptureLocals()));
    }

    private void inject(Callback callback) {
        if (this.localCapture.isPrintLocals()) {
            this.printLocals(callback);
            this.info.addCallbackInvocation(this.methodNode);
            return;
        }
        MethodNode methodNode = this.methodNode;
        if (!callback.checkDescriptor(this.methodNode.desc)) {
            if (this.info.getTargets().size() > 1) {
                return;
            }
            if (callback.canCaptureLocals) {
                MethodNode methodNode2 = Bytecode.findMethod(this.classNode, this.methodNode.name, callback.getDescriptor());
                if (methodNode2 != null && Annotations.getVisible(methodNode2, Surrogate.class) != null) {
                    methodNode = methodNode2;
                } else {
                    String string = this.generateBadLVTMessage(callback);
                    switch (this.localCapture) {
                        case CAPTURE_FAILEXCEPTION: {
                            Injector.logger.error("Injection error: {}", new Object[]{string});
                            methodNode = this.generateErrorMethod(callback, "org/spongepowered/asm/mixin/injection/throwables/InjectionError", string);
                            break;
                        }
                        case CAPTURE_FAILSOFT: {
                            Injector.logger.warn("Injection warning: {}", new Object[]{string});
                            return;
                        }
                        default: {
                            Injector.logger.error("Critical injection failure: {}", new Object[]{string});
                            throw new InjectionError(string);
                        }
                    }
                }
            } else {
                String string = this.methodNode.desc.replace("Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;", "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;");
                if (callback.checkDescriptor(string)) {
                    throw new InvalidInjectionException(this.info, "Invalid descriptor on " + this.info + "! CallbackInfoReturnable is required!");
                }
                MethodNode methodNode3 = Bytecode.findMethod(this.classNode, this.methodNode.name, callback.getDescriptor());
                if (methodNode3 != null && Annotations.getVisible(methodNode3, Surrogate.class) != null) {
                    methodNode = methodNode3;
                } else {
                    throw new InvalidInjectionException(this.info, "Invalid descriptor on " + this.info + "! Expected " + callback.getDescriptor() + " but found " + this.methodNode.desc);
                }
            }
        }
        this.dupReturnValue(callback);
        if (this.cancellable || this.totalInjections > 1) {
            this.createCallbackInfo(callback, true);
        }
        this.invokeCallback(callback, methodNode);
        this.injectCancellationCode(callback);
        callback.inject();
        this.info.notifyInjected(callback.target);
    }

    private String generateBadLVTMessage(Callback callback) {
        int n2 = callback.target.indexOf(callback.node);
        List<String> list = CallbackInjector.summariseLocals(this.methodNode.desc, callback.target.arguments.length + 1);
        List<String> list2 = CallbackInjector.summariseLocals(callback.getDescriptorWithAllLocals(), callback.frameSize);
        return String.format("LVT in %s has incompatible changes at opcode %d in callback %s.\nExpected: %s\n   Found: %s", callback.target, n2, this, list, list2);
    }

    private MethodNode generateErrorMethod(Callback callback, String string, String string2) {
        MethodNode methodNode = this.info.addMethod(this.methodNode.access, this.methodNode.name + "$missing", callback.getDescriptor());
        methodNode.maxLocals = Bytecode.getFirstNonArgLocalIndex(Type.getArgumentTypes(callback.getDescriptor()), !this.isStatic);
        methodNode.maxStack = 3;
        InsnList insnList = methodNode.instructions;
        insnList.add(new TypeInsnNode(187, string));
        insnList.add(new InsnNode(89));
        insnList.add(new LdcInsnNode(string2));
        insnList.add(new MethodInsnNode(183, string, "<init>", "(Ljava/lang/String;)V", false));
        insnList.add(new InsnNode(191));
        return methodNode;
    }

    private void printLocals(Callback callback) {
        Type[] arrtype = Type.getArgumentTypes(callback.getDescriptorWithAllLocals());
        SignaturePrinter signaturePrinter = new SignaturePrinter(callback.target.method, callback.argNames);
        SignaturePrinter signaturePrinter2 = new SignaturePrinter(this.methodNode.name, callback.target.returnType, arrtype, callback.argNames);
        signaturePrinter2.setModifiers(this.methodNode);
        PrettyPrinter prettyPrinter = new PrettyPrinter();
        prettyPrinter.kv("Target Class", this.classNode.name.replace('/', '.'));
        prettyPrinter.kv("Target Method", signaturePrinter);
        prettyPrinter.kv("Target Max LOCALS", callback.target.getMaxLocals());
        prettyPrinter.kv("Initial Frame Size", callback.frameSize);
        prettyPrinter.kv("Callback Name", this.methodNode.name);
        prettyPrinter.kv("Instruction", "%s %s", callback.node.getClass().getSimpleName(), Bytecode.getOpcodeName(callback.node.getCurrentTarget().getOpcode()));
        prettyPrinter.hr();
        if (callback.locals.length > callback.frameSize) {
            prettyPrinter.add("  %s  %20s  %s", "LOCAL", "TYPE", "NAME");
            for (int i2 = 0; i2 < callback.locals.length; ++i2) {
                String string;
                String string2 = string = i2 == callback.frameSize ? ">" : " ";
                if (callback.locals[i2] != null) {
                    prettyPrinter.add("%s [%3d]  %20s  %-50s %s", string, i2, SignaturePrinter.getTypeName(callback.localTypes[i2], false), CallbackInjector.meltSnowman(i2, callback.locals[i2].name), i2 >= callback.frameSize ? "<capture>" : "");
                    continue;
                }
                boolean bl = i2 > 0 && callback.localTypes[i2 - 1] != null && callback.localTypes[i2 - 1].getSize() > 1;
                prettyPrinter.add("%s [%3d]  %20s", string, i2, bl ? "<top>" : "-");
            }
            prettyPrinter.hr();
        }
        prettyPrinter.add().add("/**").add(" * Expected callback signature").add(" * /");
        prettyPrinter.add("%s {", signaturePrinter2);
        prettyPrinter.add("    // Method body").add("}").add().print(System.err);
    }

    private void createCallbackInfo(Callback callback, boolean bl) {
        if (callback.target != this.lastTarget) {
            this.lastId = null;
            this.lastDesc = null;
        }
        this.lastTarget = callback.target;
        String string = this.getIdentifier(callback);
        String string2 = callback.getCallbackInfoConstructorDescriptor();
        if (string.equals(this.lastId) && string2.equals(this.lastDesc) && !callback.isAtReturn && !this.cancellable) {
            return;
        }
        this.instanceCallbackInfo(callback, string, string2, bl);
    }

    private void loadOrCreateCallbackInfo(Callback callback) {
        if (this.cancellable || this.totalInjections > 1) {
            callback.add(new VarInsnNode(25, this.callbackInfoVar), false, true);
        } else {
            this.createCallbackInfo(callback, false);
        }
    }

    private void dupReturnValue(Callback callback) {
        if (!callback.isAtReturn) {
            return;
        }
        callback.add(new InsnNode(89));
        callback.add(new VarInsnNode(callback.target.returnType.getOpcode(54), callback.marshalVar()));
    }

    protected void instanceCallbackInfo(Callback callback, String string, String string2, boolean bl) {
        this.lastId = string;
        this.lastDesc = string2;
        this.callbackInfoVar = callback.marshalVar();
        this.callbackInfoClass = callback.target.getCallbackInfoClass();
        boolean bl2 = bl && this.totalInjections > 1 && !callback.isAtReturn && !this.cancellable;
        callback.add(new TypeInsnNode(187, this.callbackInfoClass), true, !bl, bl2);
        callback.add(new InsnNode(89), true, true, bl2);
        callback.add(new LdcInsnNode(string), true, !bl, bl2);
        callback.add(new InsnNode(this.cancellable ? 4 : 3), true, !bl, bl2);
        if (callback.isAtReturn) {
            callback.add(new VarInsnNode(callback.target.returnType.getOpcode(21), callback.marshalVar()), true, !bl);
            callback.add(new MethodInsnNode(183, this.callbackInfoClass, "<init>", string2, false));
        } else {
            callback.add(new MethodInsnNode(183, this.callbackInfoClass, "<init>", string2, false), false, false, bl2);
        }
        if (bl) {
            callback.target.addLocalVariable(this.callbackInfoVar, "callbackInfo" + this.callbackInfoVar, "L" + this.callbackInfoClass + ";");
            callback.add(new VarInsnNode(58, this.callbackInfoVar), false, false, bl2);
        }
    }

    private void invokeCallback(Callback callback, MethodNode methodNode) {
        if (!this.isStatic) {
            callback.add(new VarInsnNode(25, 0), false, true);
        }
        if (callback.captureArgs()) {
            Bytecode.loadArgs(callback.target.arguments, callback, this.isStatic ? 0 : 1, -1);
        }
        this.loadOrCreateCallbackInfo(callback);
        if (callback.canCaptureLocals) {
            Locals.loadLocals(callback.localTypes, callback, callback.frameSize, callback.extraArgs);
        }
        this.invokeHandler(callback, methodNode);
    }

    private String getIdentifier(Callback callback) {
        String string = Strings.isNullOrEmpty(this.identifier) ? callback.target.method.name : this.identifier;
        String string2 = this.ids.get(callback.node.getId());
        return string + (Strings.isNullOrEmpty(string2) ? "" : ":" + string2);
    }

    protected void injectCancellationCode(Callback callback) {
        if (!this.cancellable) {
            return;
        }
        callback.add(new VarInsnNode(25, this.callbackInfoVar));
        callback.add(new MethodInsnNode(182, this.callbackInfoClass, CallbackInfo.getIsCancelledMethodName(), CallbackInfo.getIsCancelledMethodSig(), false));
        LabelNode labelNode = new LabelNode();
        callback.add(new JumpInsnNode(153, labelNode));
        this.injectReturnCode(callback);
        callback.add(labelNode);
    }

    protected void injectReturnCode(Callback callback) {
        if (callback.target.returnType.equals(Type.VOID_TYPE)) {
            callback.add(new InsnNode(177));
        } else {
            callback.add(new VarInsnNode(25, callback.marshalVar()));
            String string = CallbackInfoReturnable.getReturnAccessor(callback.target.returnType);
            String string2 = CallbackInfoReturnable.getReturnDescriptor(callback.target.returnType);
            callback.add(new MethodInsnNode(182, this.callbackInfoClass, string, string2, false));
            if (callback.target.returnType.getSort() == 10) {
                callback.add(new TypeInsnNode(192, callback.target.returnType.getInternalName()));
            }
            callback.add(new InsnNode(callback.target.returnType.getOpcode(172)));
        }
    }

    protected boolean isStatic() {
        return this.isStatic;
    }

    private static List<String> summariseLocals(String string, int n2) {
        return CallbackInjector.summariseLocals(Type.getArgumentTypes(string), n2);
    }

    private static List<String> summariseLocals(Type[] arrtype, int n2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        if (arrtype != null) {
            while (n2 < arrtype.length) {
                if (arrtype[n2] != null) {
                    arrayList.add(arrtype[n2].toString());
                }
                ++n2;
            }
        }
        return arrayList;
    }

    static String meltSnowman(int n2, String string) {
        return string != null && '\u2603' == string.charAt(0) ? "var" + n2 : string;
    }

    private class Callback
    extends InsnList {
        private final MethodNode handler;
        private final AbstractInsnNode head;
        final Target target;
        final InjectionNodes.InjectionNode node;
        final LocalVariableNode[] locals;
        final Type[] localTypes;
        final int frameSize;
        final int extraArgs;
        final boolean canCaptureLocals;
        final boolean isAtReturn;
        final String desc;
        final String descl;
        final String[] argNames;
        int ctor;
        int invoke;
        private int marshalVar = -1;
        private boolean captureArgs = true;

        Callback(MethodNode methodNode, Target target, InjectionNodes.InjectionNode injectionNode, LocalVariableNode[] arrlocalVariableNode, boolean bl) {
            this.handler = methodNode;
            this.target = target;
            this.head = target.insns.getFirst();
            this.node = injectionNode;
            this.locals = arrlocalVariableNode;
            this.localTypes = arrlocalVariableNode != null ? new Type[arrlocalVariableNode.length] : null;
            this.frameSize = Bytecode.getFirstNonArgLocalIndex(target.arguments, !CallbackInjector.this.isStatic());
            ArrayList<String> arrayList = null;
            if (arrlocalVariableNode != null) {
                int n2 = CallbackInjector.this.isStatic() ? 0 : 1;
                arrayList = new ArrayList<String>();
                for (int i2 = 0; i2 <= arrlocalVariableNode.length; ++i2) {
                    if (i2 == this.frameSize) {
                        arrayList.add(target.returnType == Type.VOID_TYPE ? "ci" : "cir");
                    }
                    if (i2 >= arrlocalVariableNode.length || arrlocalVariableNode[i2] == null) continue;
                    this.localTypes[i2] = Type.getType(arrlocalVariableNode[i2].desc);
                    if (i2 < n2) continue;
                    arrayList.add(CallbackInjector.meltSnowman(i2, arrlocalVariableNode[i2].name));
                }
            }
            this.extraArgs = Math.max(0, Bytecode.getFirstNonArgLocalIndex(this.handler) - (this.frameSize + 1));
            this.argNames = arrayList != null ? arrayList.toArray(new String[arrayList.size()]) : null;
            this.canCaptureLocals = bl && arrlocalVariableNode != null && arrlocalVariableNode.length > this.frameSize;
            this.isAtReturn = this.node.getCurrentTarget() instanceof InsnNode && this.isValueReturnOpcode(this.node.getCurrentTarget().getOpcode());
            this.desc = target.getCallbackDescriptor(this.localTypes, target.arguments);
            this.descl = target.getCallbackDescriptor(true, this.localTypes, target.arguments, this.frameSize, this.extraArgs);
            this.invoke = target.arguments.length + (this.canCaptureLocals ? this.localTypes.length - this.frameSize : 0);
        }

        private boolean isValueReturnOpcode(int n2) {
            return n2 >= 172 && n2 < 177;
        }

        String getDescriptor() {
            return this.canCaptureLocals ? this.descl : this.desc;
        }

        String getDescriptorWithAllLocals() {
            return this.target.getCallbackDescriptor(true, this.localTypes, this.target.arguments, this.frameSize, 32767);
        }

        String getCallbackInfoConstructorDescriptor() {
            return this.isAtReturn ? CallbackInfo.getConstructorDescriptor(this.target.returnType) : CallbackInfo.getConstructorDescriptor();
        }

        void add(AbstractInsnNode abstractInsnNode, boolean bl, boolean bl2) {
            this.add(abstractInsnNode, bl, bl2, false);
        }

        void add(AbstractInsnNode abstractInsnNode, boolean bl, boolean bl2, boolean bl3) {
            if (bl3) {
                this.target.insns.insertBefore(this.head, abstractInsnNode);
            } else {
                this.add(abstractInsnNode);
            }
            this.ctor += bl ? 1 : 0;
            this.invoke += bl2 ? 1 : 0;
        }

        void inject() {
            this.target.insertBefore(this.node, (InsnList)this);
            this.target.addToStack(Math.max(this.invoke, this.ctor));
        }

        boolean checkDescriptor(String string) {
            Type[] arrtype;
            if (this.getDescriptor().equals(string)) {
                return true;
            }
            if (this.target.getSimpleCallbackDescriptor().equals(string) && !this.canCaptureLocals) {
                this.captureArgs = false;
                return true;
            }
            Type[] arrtype2 = Type.getArgumentTypes(string);
            if (arrtype2.length != (arrtype = Type.getArgumentTypes(this.descl)).length) {
                return false;
            }
            for (int i2 = 0; i2 < arrtype.length; ++i2) {
                Type type = arrtype2[i2];
                if (type.equals(arrtype[i2])) continue;
                if (type.getSort() == 9) {
                    return false;
                }
                if (Annotations.getInvisibleParameter(this.handler, Coerce.class, i2) == null) {
                    return false;
                }
                if (Injector.canCoerce(arrtype2[i2], arrtype[i2])) continue;
                return false;
            }
            return true;
        }

        boolean captureArgs() {
            return this.captureArgs;
        }

        int marshalVar() {
            if (this.marshalVar < 0) {
                this.marshalVar = this.target.allocateLocal();
            }
            return this.marshalVar;
        }
    }
}

