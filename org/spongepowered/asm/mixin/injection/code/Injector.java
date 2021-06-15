/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.injection.code;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.LdcInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.code.InjectorTarget;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.util.Bytecode;

public abstract class Injector {
    protected static final Logger logger = LogManager.getLogger((String)"mixin");
    protected InjectionInfo info;
    protected final ClassNode classNode;
    protected final MethodNode methodNode;
    protected final Type[] methodArgs;
    protected final Type returnType;
    protected final boolean isStatic;

    public Injector(InjectionInfo injectionInfo) {
        this(injectionInfo.getClassNode(), injectionInfo.getMethod());
        this.info = injectionInfo;
    }

    private Injector(ClassNode classNode, MethodNode methodNode) {
        this.classNode = classNode;
        this.methodNode = methodNode;
        this.methodArgs = Type.getArgumentTypes(this.methodNode.desc);
        this.returnType = Type.getReturnType(this.methodNode.desc);
        this.isStatic = Bytecode.methodIsStatic(this.methodNode);
    }

    public String toString() {
        return String.format("%s::%s", this.classNode.name, this.methodNode.name);
    }

    public final List<InjectionNodes.InjectionNode> find(InjectorTarget injectorTarget, List<InjectionPoint> list) {
        this.sanityCheck(injectorTarget.getTarget(), list);
        ArrayList<InjectionNodes.InjectionNode> arrayList = new ArrayList<InjectionNodes.InjectionNode>();
        for (TargetNode targetNode : this.findTargetNodes(injectorTarget, list)) {
            this.addTargetNode(injectorTarget.getTarget(), arrayList, targetNode.insn, targetNode.nominators);
        }
        return arrayList;
    }

    protected void addTargetNode(Target target, List<InjectionNodes.InjectionNode> list, AbstractInsnNode abstractInsnNode, Set<InjectionPoint> set) {
        list.add(target.addInjectionNode(abstractInsnNode));
    }

    public final void inject(Target target, List<InjectionNodes.InjectionNode> list) {
        for (InjectionNodes.InjectionNode injectionNode : list) {
            if (injectionNode.isRemoved()) {
                if (!this.info.getContext().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) continue;
                logger.warn("Target node for {} was removed by a previous injector in {}", new Object[]{this.info, target});
                continue;
            }
            this.inject(target, injectionNode);
        }
        for (InjectionNodes.InjectionNode injectionNode : list) {
            this.postInject(target, injectionNode);
        }
    }

    private Collection<TargetNode> findTargetNodes(InjectorTarget injectorTarget, List<InjectionPoint> list) {
        IMixinContext iMixinContext = this.info.getContext();
        MethodNode methodNode = injectorTarget.getMethod();
        TreeMap<Integer, TargetNode> treeMap = new TreeMap<Integer, TargetNode>();
        ArrayList<AbstractInsnNode> arrayList = new ArrayList<AbstractInsnNode>(32);
        for (InjectionPoint injectionPoint : list) {
            arrayList.clear();
            if (injectorTarget.isMerged() && !iMixinContext.getClassName().equals(injectorTarget.getMergedBy()) && !injectionPoint.checkPriority(injectorTarget.getMergedPriority(), iMixinContext.getPriority())) {
                throw new InvalidInjectionException(this.info, String.format("%s on %s with priority %d cannot inject into %s merged by %s with priority %d", injectionPoint, this, iMixinContext.getPriority(), injectorTarget, injectorTarget.getMergedBy(), injectorTarget.getMergedPriority()));
            }
            if (!this.findTargetNodes(methodNode, injectionPoint, injectorTarget.getSlice(injectionPoint), arrayList)) continue;
            for (AbstractInsnNode abstractInsnNode : arrayList) {
                Integer n2 = methodNode.instructions.indexOf(abstractInsnNode);
                TargetNode targetNode = (TargetNode)treeMap.get(n2);
                if (targetNode == null) {
                    targetNode = new TargetNode(abstractInsnNode);
                    treeMap.put(n2, targetNode);
                }
                targetNode.nominators.add(injectionPoint);
            }
        }
        return treeMap.values();
    }

    protected boolean findTargetNodes(MethodNode methodNode, InjectionPoint injectionPoint, InsnList insnList, Collection<AbstractInsnNode> collection) {
        return injectionPoint.find(methodNode.desc, insnList, collection);
    }

    protected void sanityCheck(Target target, List<InjectionPoint> list) {
        if (target.classNode != this.classNode) {
            throw new InvalidInjectionException(this.info, "Target class does not match injector class in " + this);
        }
    }

    protected abstract void inject(Target var1, InjectionNodes.InjectionNode var2);

    protected void postInject(Target target, InjectionNodes.InjectionNode injectionNode) {
    }

    protected AbstractInsnNode invokeHandler(InsnList insnList) {
        return this.invokeHandler(insnList, this.methodNode);
    }

    protected AbstractInsnNode invokeHandler(InsnList insnList, MethodNode methodNode) {
        boolean bl;
        boolean bl2 = bl = (methodNode.access & 2) != 0;
        int n2 = this.isStatic ? 184 : (bl ? 183 : 182);
        MethodInsnNode methodInsnNode = new MethodInsnNode(n2, this.classNode.name, methodNode.name, methodNode.desc, false);
        insnList.add(methodInsnNode);
        this.info.addCallbackInvocation(methodNode);
        return methodInsnNode;
    }

    protected void throwException(InsnList insnList, String string, String string2) {
        insnList.add(new TypeInsnNode(187, string));
        insnList.add(new InsnNode(89));
        insnList.add(new LdcInsnNode(string2));
        insnList.add(new MethodInsnNode(183, string, "<init>", "(Ljava/lang/String;)V", false));
        insnList.add(new InsnNode(191));
    }

    public static boolean canCoerce(Type type, Type type2) {
        if (type.getSort() == 10 && type2.getSort() == 10) {
            return Injector.canCoerce(ClassInfo.forType(type), ClassInfo.forType(type2));
        }
        return Injector.canCoerce(type.getDescriptor(), type2.getDescriptor());
    }

    public static boolean canCoerce(String string, String string2) {
        if (string.length() > 1 || string2.length() > 1) {
            return false;
        }
        return Injector.canCoerce(string.charAt(0), string2.charAt(0));
    }

    public static boolean canCoerce(char c2, char c3) {
        return c3 == 'I' && "IBSCZ".indexOf(c2) > -1;
    }

    private static boolean canCoerce(ClassInfo classInfo, ClassInfo classInfo2) {
        return classInfo != null && classInfo2 != null && (classInfo2 == classInfo || classInfo2.hasSuperClass(classInfo));
    }

    public static final class TargetNode {
        final AbstractInsnNode insn;
        final Set<InjectionPoint> nominators = new HashSet<InjectionPoint>();

        TargetNode(AbstractInsnNode abstractInsnNode) {
            this.insn = abstractInsnNode;
        }

        public AbstractInsnNode getNode() {
            return this.insn;
        }

        public Set<InjectionPoint> getNominators() {
            return Collections.unmodifiableSet(this.nominators);
        }

        public boolean equals(Object object) {
            if (object == null || object.getClass() != TargetNode.class) {
                return false;
            }
            return ((TargetNode)object).insn == this.insn;
        }

        public int hashCode() {
            return this.insn.hashCode();
        }
    }
}

