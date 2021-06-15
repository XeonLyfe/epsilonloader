/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.invoke;

import com.google.common.base.Joiner;
import com.google.common.collect.ObjectArrays;
import com.google.common.primitives.Ints;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.JumpInsnNode;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.invoke.InvokeInjector;
import org.spongepowered.asm.mixin.injection.points.BeforeFieldAccess;
import org.spongepowered.asm.mixin.injection.points.BeforeNew;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

public class RedirectInjector
extends InvokeInjector {
    private static final String KEY_NOMINATORS = "nominators";
    private static final String KEY_FUZZ = "fuzz";
    private static final String KEY_OPCODE = "opcode";
    protected Meta meta;
    private Map<BeforeNew, ConstructorRedirectData> ctorRedirectors = new HashMap<BeforeNew, ConstructorRedirectData>();

    public RedirectInjector(InjectionInfo injectionInfo) {
        this(injectionInfo, "@Redirect");
    }

    protected RedirectInjector(InjectionInfo injectionInfo, String string) {
        super(injectionInfo, string);
        int n2 = injectionInfo.getContext().getPriority();
        boolean bl = Annotations.getVisible(this.methodNode, Final.class) != null;
        this.meta = new Meta(n2, bl, this.info.toString(), this.methodNode.desc);
    }

    @Override
    protected void checkTarget(Target target) {
    }

    @Override
    protected void addTargetNode(Target target, List<InjectionNodes.InjectionNode> list, AbstractInsnNode abstractInsnNode, Set<InjectionPoint> set) {
        Object object;
        InjectionNodes.InjectionNode injectionNode = target.getInjectionNode(abstractInsnNode);
        ConstructorRedirectData constructorRedirectData = null;
        int n2 = 8;
        int n3 = 0;
        if (injectionNode != null && (object = (Meta)injectionNode.getDecoration("redirector")) != null && ((Meta)object).getOwner() != this) {
            if (((Meta)object).priority >= this.meta.priority) {
                Injector.logger.warn("{} conflict. Skipping {} with priority {}, already redirected by {} with priority {}", new Object[]{this.annotationType, this.info, this.meta.priority, ((Meta)object).name, ((Meta)object).priority});
                return;
            }
            if (((Meta)object).isFinal) {
                throw new InvalidInjectionException(this.info, String.format("%s conflict: %s failed because target was already remapped by %s", this.annotationType, this, ((Meta)object).name));
            }
        }
        for (InjectionPoint injectionPoint : set) {
            if (injectionPoint instanceof BeforeNew) {
                constructorRedirectData = this.getCtorRedirect((BeforeNew)injectionPoint);
                constructorRedirectData.wildcard = !((BeforeNew)injectionPoint).hasDescriptor();
                continue;
            }
            if (!(injectionPoint instanceof BeforeFieldAccess)) continue;
            BeforeFieldAccess beforeFieldAccess = (BeforeFieldAccess)injectionPoint;
            n2 = beforeFieldAccess.getFuzzFactor();
            n3 = beforeFieldAccess.getArrayOpcode();
        }
        object = target.addInjectionNode(abstractInsnNode);
        ((InjectionNodes.InjectionNode)object).decorate("redirector", this.meta);
        ((InjectionNodes.InjectionNode)object).decorate(KEY_NOMINATORS, set);
        if (abstractInsnNode instanceof TypeInsnNode && abstractInsnNode.getOpcode() == 187) {
            ((InjectionNodes.InjectionNode)object).decorate("ctor", constructorRedirectData);
        } else {
            ((InjectionNodes.InjectionNode)object).decorate(KEY_FUZZ, n2);
            ((InjectionNodes.InjectionNode)object).decorate(KEY_OPCODE, n3);
        }
        list.add((InjectionNodes.InjectionNode)object);
    }

    private ConstructorRedirectData getCtorRedirect(BeforeNew beforeNew) {
        ConstructorRedirectData constructorRedirectData = this.ctorRedirectors.get(beforeNew);
        if (constructorRedirectData == null) {
            constructorRedirectData = new ConstructorRedirectData();
            this.ctorRedirectors.put(beforeNew, constructorRedirectData);
        }
        return constructorRedirectData;
    }

    @Override
    protected void inject(Target target, InjectionNodes.InjectionNode injectionNode) {
        if (!this.preInject(injectionNode)) {
            return;
        }
        if (injectionNode.isReplaced()) {
            throw new UnsupportedOperationException("Redirector target failure for " + this.info);
        }
        if (injectionNode.getCurrentTarget() instanceof MethodInsnNode) {
            this.checkTargetForNode(target, injectionNode);
            this.injectAtInvoke(target, injectionNode);
            return;
        }
        if (injectionNode.getCurrentTarget() instanceof FieldInsnNode) {
            this.checkTargetForNode(target, injectionNode);
            this.injectAtFieldAccess(target, injectionNode);
            return;
        }
        if (injectionNode.getCurrentTarget() instanceof TypeInsnNode && injectionNode.getCurrentTarget().getOpcode() == 187) {
            if (!this.isStatic && target.isStatic) {
                throw new InvalidInjectionException(this.info, String.format("non-static callback method %s has a static target which is not supported", this));
            }
            this.injectAtConstructor(target, injectionNode);
            return;
        }
        throw new InvalidInjectionException(this.info, String.format("%s annotation on is targetting an invalid insn in %s in %s", this.annotationType, target, this));
    }

    protected boolean preInject(InjectionNodes.InjectionNode injectionNode) {
        Meta meta = (Meta)injectionNode.getDecoration("redirector");
        if (meta.getOwner() != this) {
            Injector.logger.warn("{} conflict. Skipping {} with priority {}, already redirected by {} with priority {}", new Object[]{this.annotationType, this.info, this.meta.priority, meta.name, meta.priority});
            return false;
        }
        return true;
    }

    @Override
    protected void postInject(Target target, InjectionNodes.InjectionNode injectionNode) {
        super.postInject(target, injectionNode);
        if (injectionNode.getOriginalTarget() instanceof TypeInsnNode && injectionNode.getOriginalTarget().getOpcode() == 187) {
            ConstructorRedirectData constructorRedirectData = (ConstructorRedirectData)injectionNode.getDecoration("ctor");
            if (constructorRedirectData.wildcard && constructorRedirectData.injected == 0) {
                throw new InvalidInjectionException(this.info, String.format("%s ctor invocation was not found in %s", this.annotationType, target));
            }
        }
    }

    @Override
    protected void injectAtInvoke(Target target, InjectionNodes.InjectionNode injectionNode) {
        RedirectedInvoke redirectedInvoke = new RedirectedInvoke(target, (MethodInsnNode)injectionNode.getCurrentTarget());
        this.validateParams(redirectedInvoke);
        InsnList insnList = new InsnList();
        int n2 = Bytecode.getArgsSize(redirectedInvoke.locals) + 1;
        int n3 = 1;
        int[] arrn = this.storeArgs(target, redirectedInvoke.locals, insnList, 0);
        if (redirectedInvoke.captureTargetArgs) {
            int n4 = Bytecode.getArgsSize(target.arguments);
            n2 += n4;
            n3 += n4;
            arrn = Ints.concat(arrn, target.getArgIndices());
        }
        AbstractInsnNode abstractInsnNode = this.invokeHandlerWithArgs(this.methodArgs, insnList, arrn);
        target.replaceNode(redirectedInvoke.node, abstractInsnNode, insnList);
        target.addToLocals(n2);
        target.addToStack(n3);
    }

    protected void validateParams(RedirectedInvoke redirectedInvoke) {
        int n2 = this.methodArgs.length;
        String string = String.format("%s handler method %s", this.annotationType, this);
        if (!redirectedInvoke.returnType.equals(this.returnType)) {
            throw new InvalidInjectionException(this.info, String.format("%s has an invalid signature. Expected return type %s found %s", string, this.returnType, redirectedInvoke.returnType));
        }
        for (int i2 = 0; i2 < n2; ++i2) {
            Type type = null;
            if (i2 >= this.methodArgs.length) {
                throw new InvalidInjectionException(this.info, String.format("%s has an invalid signature. Not enough arguments found for capture of target method args, expected %d but found %d", string, n2, this.methodArgs.length));
            }
            Type type2 = this.methodArgs[i2];
            if (i2 < redirectedInvoke.locals.length) {
                type = redirectedInvoke.locals[i2];
            } else {
                redirectedInvoke.captureTargetArgs = true;
                n2 = Math.max(n2, redirectedInvoke.locals.length + redirectedInvoke.target.arguments.length);
                int n3 = i2 - redirectedInvoke.locals.length;
                if (n3 >= redirectedInvoke.target.arguments.length) {
                    throw new InvalidInjectionException(this.info, String.format("%s has an invalid signature. Found unexpected additional target argument with type %s at index %d", string, type2, i2));
                }
                type = redirectedInvoke.target.arguments[n3];
            }
            AnnotationNode annotationNode = Annotations.getInvisibleParameter(this.methodNode, Coerce.class, i2);
            if (type2.equals(type)) {
                if (annotationNode == null || !this.info.getContext().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) continue;
                Injector.logger.warn("Redundant @Coerce on {} argument {}, {} is identical to {}", new Object[]{string, i2, type, type2});
                continue;
            }
            boolean bl = Injector.canCoerce(type2, type);
            if (annotationNode == null) {
                throw new InvalidInjectionException(this.info, String.format("%s has an invalid signature. Found unexpected argument type %s at index %d, expected %s", string, type2, i2, type));
            }
            if (bl) continue;
            throw new InvalidInjectionException(this.info, String.format("%s has an invalid signature. Cannot @Coerce argument type %s at index %d to %s", string, type, i2, type2));
        }
    }

    private void injectAtFieldAccess(Target target, InjectionNodes.InjectionNode injectionNode) {
        int n2;
        FieldInsnNode fieldInsnNode = (FieldInsnNode)injectionNode.getCurrentTarget();
        int n3 = fieldInsnNode.getOpcode();
        Type type = Type.getType("L" + fieldInsnNode.owner + ";");
        Type type2 = Type.getType(fieldInsnNode.desc);
        int n4 = type2.getSort() == 9 ? type2.getDimensions() : 0;
        int n5 = n2 = this.returnType.getSort() == 9 ? this.returnType.getDimensions() : 0;
        if (n2 > n4) {
            throw new InvalidInjectionException(this.info, "Dimensionality of handler method is greater than target array on " + this);
        }
        if (n2 == 0 && n4 > 0) {
            int n6 = (Integer)injectionNode.getDecoration(KEY_FUZZ);
            int n7 = (Integer)injectionNode.getDecoration(KEY_OPCODE);
            this.injectAtArrayField(target, fieldInsnNode, n3, type, type2, n6, n7);
        } else {
            this.injectAtScalarField(target, fieldInsnNode, n3, type, type2);
        }
    }

    private void injectAtArrayField(Target target, FieldInsnNode fieldInsnNode, int n2, Type type, Type type2, int n3, int n4) {
        Type type3 = type2.getElementType();
        if (n2 != 178 && n2 != 180) {
            throw new InvalidInjectionException(this.info, String.format("Unspported opcode %s for array access %s", Bytecode.getOpcodeName(n2), this.info));
        }
        if (this.returnType.getSort() != 0) {
            if (n4 != 190) {
                n4 = type3.getOpcode(46);
            }
            AbstractInsnNode abstractInsnNode = BeforeFieldAccess.findArrayNode(target.insns, fieldInsnNode, n4, n3);
            this.injectAtGetArray(target, fieldInsnNode, abstractInsnNode, type, type2);
        } else {
            AbstractInsnNode abstractInsnNode = BeforeFieldAccess.findArrayNode(target.insns, fieldInsnNode, type3.getOpcode(79), n3);
            this.injectAtSetArray(target, fieldInsnNode, abstractInsnNode, type, type2);
        }
    }

    private void injectAtGetArray(Target target, FieldInsnNode fieldInsnNode, AbstractInsnNode abstractInsnNode, Type type, Type type2) {
        String string = RedirectInjector.getGetArrayHandlerDescriptor(abstractInsnNode, this.returnType, type2);
        boolean bl = this.checkDescriptor(string, target, "array getter");
        this.injectArrayRedirect(target, fieldInsnNode, abstractInsnNode, bl, "array getter");
    }

    private void injectAtSetArray(Target target, FieldInsnNode fieldInsnNode, AbstractInsnNode abstractInsnNode, Type type, Type type2) {
        String string = Bytecode.generateDescriptor(null, RedirectInjector.getArrayArgs(type2, 1, type2.getElementType()));
        boolean bl = this.checkDescriptor(string, target, "array setter");
        this.injectArrayRedirect(target, fieldInsnNode, abstractInsnNode, bl, "array setter");
    }

    public void injectArrayRedirect(Target target, FieldInsnNode fieldInsnNode, AbstractInsnNode abstractInsnNode, boolean bl, String string) {
        if (abstractInsnNode == null) {
            String string2 = "";
            throw new InvalidInjectionException(this.info, String.format("Array element %s on %s could not locate a matching %s instruction in %s. %s", this.annotationType, this, string, target, string2));
        }
        if (!this.isStatic) {
            target.insns.insertBefore((AbstractInsnNode)fieldInsnNode, new VarInsnNode(25, 0));
            target.addToStack(1);
        }
        InsnList insnList = new InsnList();
        if (bl) {
            this.pushArgs(target.arguments, insnList, target.getArgIndices(), 0, target.arguments.length);
            target.addToStack(Bytecode.getArgsSize(target.arguments));
        }
        target.replaceNode(abstractInsnNode, this.invokeHandler(insnList), insnList);
    }

    public void injectAtScalarField(Target target, FieldInsnNode fieldInsnNode, int n2, Type type, Type type2) {
        AbstractInsnNode abstractInsnNode = null;
        InsnList insnList = new InsnList();
        if (n2 == 178 || n2 == 180) {
            abstractInsnNode = this.injectAtGetField(insnList, target, fieldInsnNode, n2 == 178, type, type2);
        } else if (n2 == 179 || n2 == 181) {
            abstractInsnNode = this.injectAtPutField(insnList, target, fieldInsnNode, n2 == 179, type, type2);
        } else {
            throw new InvalidInjectionException(this.info, String.format("Unspported opcode %s for %s", Bytecode.getOpcodeName(n2), this.info));
        }
        target.replaceNode(fieldInsnNode, abstractInsnNode, insnList);
    }

    private AbstractInsnNode injectAtGetField(InsnList insnList, Target target, FieldInsnNode fieldInsnNode, boolean bl, Type type, Type type2) {
        String string = bl ? Bytecode.generateDescriptor(type2, new Object[0]) : Bytecode.generateDescriptor(type2, type);
        boolean bl2 = this.checkDescriptor(string, target, "getter");
        if (!this.isStatic) {
            insnList.add(new VarInsnNode(25, 0));
            if (!bl) {
                insnList.add(new InsnNode(95));
            }
        }
        if (bl2) {
            this.pushArgs(target.arguments, insnList, target.getArgIndices(), 0, target.arguments.length);
            target.addToStack(Bytecode.getArgsSize(target.arguments));
        }
        target.addToStack(this.isStatic ? 0 : 1);
        return this.invokeHandler(insnList);
    }

    private AbstractInsnNode injectAtPutField(InsnList insnList, Target target, FieldInsnNode fieldInsnNode, boolean bl, Type type, Type type2) {
        String string = bl ? Bytecode.generateDescriptor(null, type2) : Bytecode.generateDescriptor(null, type, type2);
        boolean bl2 = this.checkDescriptor(string, target, "setter");
        if (!this.isStatic) {
            if (bl) {
                insnList.add(new VarInsnNode(25, 0));
                insnList.add(new InsnNode(95));
            } else {
                int n2 = target.allocateLocals(type2.getSize());
                insnList.add(new VarInsnNode(type2.getOpcode(54), n2));
                insnList.add(new VarInsnNode(25, 0));
                insnList.add(new InsnNode(95));
                insnList.add(new VarInsnNode(type2.getOpcode(21), n2));
            }
        }
        if (bl2) {
            this.pushArgs(target.arguments, insnList, target.getArgIndices(), 0, target.arguments.length);
            target.addToStack(Bytecode.getArgsSize(target.arguments));
        }
        target.addToStack(!this.isStatic && !bl ? 1 : 0);
        return this.invokeHandler(insnList);
    }

    protected boolean checkDescriptor(String string, Target target, String string2) {
        if (this.methodNode.desc.equals(string)) {
            return false;
        }
        int n2 = string.indexOf(41);
        String string3 = String.format("%s%s%s", string.substring(0, n2), Joiner.on("").join(target.arguments), string.substring(n2));
        if (this.methodNode.desc.equals(string3)) {
            return true;
        }
        throw new InvalidInjectionException(this.info, String.format("%s method %s %s has an invalid signature. Expected %s but found %s", this.annotationType, string2, this, string, this.methodNode.desc));
    }

    protected void injectAtConstructor(Target target, InjectionNodes.InjectionNode injectionNode) {
        ConstructorRedirectData constructorRedirectData = (ConstructorRedirectData)injectionNode.getDecoration("ctor");
        if (constructorRedirectData == null) {
            throw new InvalidInjectionException(this.info, String.format("%s ctor redirector has no metadata, the injector failed a preprocessing phase", this.annotationType));
        }
        TypeInsnNode typeInsnNode = (TypeInsnNode)injectionNode.getCurrentTarget();
        AbstractInsnNode abstractInsnNode = target.get(target.indexOf(typeInsnNode) + 1);
        MethodInsnNode methodInsnNode = target.findInitNodeFor(typeInsnNode);
        if (methodInsnNode == null) {
            if (!constructorRedirectData.wildcard) {
                throw new InvalidInjectionException(this.info, String.format("%s ctor invocation was not found in %s", this.annotationType, target));
            }
            return;
        }
        boolean bl = abstractInsnNode.getOpcode() == 89;
        String string = methodInsnNode.desc.replace(")V", ")L" + typeInsnNode.desc + ";");
        boolean bl2 = false;
        try {
            bl2 = this.checkDescriptor(string, target, "constructor");
        }
        catch (InvalidInjectionException invalidInjectionException) {
            if (!constructorRedirectData.wildcard) {
                throw invalidInjectionException;
            }
            return;
        }
        if (bl) {
            target.removeNode(abstractInsnNode);
        }
        if (this.isStatic) {
            target.removeNode(typeInsnNode);
        } else {
            target.replaceNode((AbstractInsnNode)typeInsnNode, new VarInsnNode(25, 0));
        }
        InsnList insnList = new InsnList();
        if (bl2) {
            this.pushArgs(target.arguments, insnList, target.getArgIndices(), 0, target.arguments.length);
            target.addToStack(Bytecode.getArgsSize(target.arguments));
        }
        this.invokeHandler(insnList);
        if (bl) {
            LabelNode labelNode = new LabelNode();
            insnList.add(new InsnNode(89));
            insnList.add(new JumpInsnNode(199, labelNode));
            this.throwException(insnList, "java/lang/NullPointerException", String.format("%s constructor handler %s returned null for %s", this.annotationType, this, typeInsnNode.desc.replace('/', '.')));
            insnList.add(labelNode);
            target.addToStack(1);
        } else {
            insnList.add(new InsnNode(87));
        }
        target.replaceNode((AbstractInsnNode)methodInsnNode, insnList);
        ++constructorRedirectData.injected;
    }

    private static String getGetArrayHandlerDescriptor(AbstractInsnNode abstractInsnNode, Type type, Type type2) {
        if (abstractInsnNode != null && abstractInsnNode.getOpcode() == 190) {
            return Bytecode.generateDescriptor(Type.INT_TYPE, RedirectInjector.getArrayArgs(type2, 0, new Type[0]));
        }
        return Bytecode.generateDescriptor(type, RedirectInjector.getArrayArgs(type2, 1, new Type[0]));
    }

    private static Type[] getArrayArgs(Type type, int n2, Type ... arrtype) {
        int n3 = type.getDimensions() + n2;
        Type[] arrtype2 = new Type[n3 + arrtype.length];
        for (int i2 = 0; i2 < arrtype2.length; ++i2) {
            arrtype2[i2] = i2 == 0 ? type : (i2 < n3 ? Type.INT_TYPE : arrtype[n3 - i2]);
        }
        return arrtype2;
    }

    static class RedirectedInvoke {
        final Target target;
        final MethodInsnNode node;
        final Type returnType;
        final Type[] args;
        final Type[] locals;
        boolean captureTargetArgs = false;

        RedirectedInvoke(Target target, MethodInsnNode methodInsnNode) {
            this.target = target;
            this.node = methodInsnNode;
            this.returnType = Type.getReturnType(methodInsnNode.desc);
            this.args = Type.getArgumentTypes(methodInsnNode.desc);
            this.locals = methodInsnNode.getOpcode() == 184 ? this.args : ObjectArrays.concat(Type.getType("L" + methodInsnNode.owner + ";"), this.args);
        }
    }

    static class ConstructorRedirectData {
        public static final String KEY = "ctor";
        public boolean wildcard = false;
        public int injected = 0;

        ConstructorRedirectData() {
        }
    }

    class Meta {
        public static final String KEY = "redirector";
        final int priority;
        final boolean isFinal;
        final String name;
        final String desc;

        public Meta(int n2, boolean bl, String string, String string2) {
            this.priority = n2;
            this.isFinal = bl;
            this.name = string;
            this.desc = string2;
        }

        RedirectInjector getOwner() {
            return RedirectInjector.this;
        }
    }
}

