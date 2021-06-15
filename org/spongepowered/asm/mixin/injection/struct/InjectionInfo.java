/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.struct;

import com.google.common.base.Strings;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.code.ISliceContext;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.code.InjectorTarget;
import org.spongepowered.asm.mixin.injection.code.MethodSlice;
import org.spongepowered.asm.mixin.injection.code.MethodSlices;
import org.spongepowered.asm.mixin.injection.struct.CallbackInjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.InjectorGroupInfo;
import org.spongepowered.asm.mixin.injection.struct.InvalidMemberDescriptorException;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.mixin.injection.struct.ModifyArgInjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.ModifyArgsInjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.ModifyConstantInjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.ModifyVariableInjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.RedirectInjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InjectionError;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.struct.SpecialMethodInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.mixin.transformer.meta.MixinMerged;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

public abstract class InjectionInfo
extends SpecialMethodInfo
implements ISliceContext {
    protected final boolean isStatic;
    protected final Deque<MethodNode> targets = new ArrayDeque<MethodNode>();
    protected final MethodSlices slices;
    protected final String atKey;
    protected final List<InjectionPoint> injectionPoints = new ArrayList<InjectionPoint>();
    protected final Map<Target, List<InjectionNodes.InjectionNode>> targetNodes = new LinkedHashMap<Target, List<InjectionNodes.InjectionNode>>();
    protected Injector injector;
    protected InjectorGroupInfo group;
    private final List<MethodNode> injectedMethods = new ArrayList<MethodNode>(0);
    private int expectedCallbackCount = 1;
    private int requiredCallbackCount = 0;
    private int maxCallbackCount = Integer.MAX_VALUE;
    private int injectedCallbackCount = 0;

    protected InjectionInfo(MixinTargetContext mixinTargetContext, MethodNode methodNode, AnnotationNode annotationNode) {
        this(mixinTargetContext, methodNode, annotationNode, "at");
    }

    protected InjectionInfo(MixinTargetContext mixinTargetContext, MethodNode methodNode, AnnotationNode annotationNode, String string) {
        super(mixinTargetContext, methodNode, annotationNode);
        this.isStatic = Bytecode.methodIsStatic(methodNode);
        this.slices = MethodSlices.parse(this);
        this.atKey = string;
        this.readAnnotation();
    }

    protected void readAnnotation() {
        if (this.annotation == null) {
            return;
        }
        String string = "@" + Bytecode.getSimpleName(this.annotation);
        List<AnnotationNode> list = this.readInjectionPoints(string);
        this.findMethods(this.parseTargets(string), string);
        this.parseInjectionPoints(list);
        this.parseRequirements();
        this.injector = this.parseInjector(this.annotation);
    }

    protected Set<MemberInfo> parseTargets(String string) {
        List<String> list = Annotations.getValue(this.annotation, "method", false);
        if (list == null) {
            throw new InvalidInjectionException(this, String.format("%s annotation on %s is missing method name", string, this.method.name));
        }
        LinkedHashSet<MemberInfo> linkedHashSet = new LinkedHashSet<MemberInfo>();
        for (String string2 : list) {
            try {
                MemberInfo memberInfo = MemberInfo.parseAndValidate(string2, this.mixin);
                if (memberInfo.owner != null && !memberInfo.owner.equals(this.mixin.getTargetClassRef())) {
                    throw new InvalidInjectionException(this, String.format("%s annotation on %s specifies a target class '%s', which is not supported", string, this.method.name, memberInfo.owner));
                }
                linkedHashSet.add(memberInfo);
            }
            catch (InvalidMemberDescriptorException invalidMemberDescriptorException) {
                throw new InvalidInjectionException(this, String.format("%s annotation on %s, has invalid target descriptor: \"%s\". %s", string, this.method.name, string2, this.mixin.getReferenceMapper().getStatus()));
            }
        }
        return linkedHashSet;
    }

    protected List<AnnotationNode> readInjectionPoints(String string) {
        List<AnnotationNode> list = Annotations.getValue(this.annotation, this.atKey, false);
        if (list == null) {
            throw new InvalidInjectionException(this, String.format("%s annotation on %s is missing '%s' value(s)", string, this.method.name, this.atKey));
        }
        return list;
    }

    protected void parseInjectionPoints(List<AnnotationNode> list) {
        this.injectionPoints.addAll(InjectionPoint.parse((IMixinContext)this.mixin, this.method, this.annotation, list));
    }

    protected void parseRequirements() {
        Integer n2;
        this.group = this.mixin.getInjectorGroups().parseGroup(this.method, this.mixin.getDefaultInjectorGroup()).add(this);
        Integer n3 = (Integer)Annotations.getValue(this.annotation, "expect");
        if (n3 != null) {
            this.expectedCallbackCount = n3;
        }
        if ((n2 = (Integer)Annotations.getValue(this.annotation, "require")) != null && n2 > -1) {
            this.requiredCallbackCount = n2;
        } else if (this.group.isDefault()) {
            this.requiredCallbackCount = this.mixin.getDefaultRequiredInjections();
        }
        Integer n4 = (Integer)Annotations.getValue(this.annotation, "allow");
        if (n4 != null) {
            this.maxCallbackCount = Math.max(Math.max(this.requiredCallbackCount, 1), n4);
        }
    }

    protected abstract Injector parseInjector(AnnotationNode var1);

    public boolean isValid() {
        return this.targets.size() > 0 && this.injectionPoints.size() > 0;
    }

    public void prepare() {
        this.targetNodes.clear();
        for (MethodNode methodNode : this.targets) {
            Target target = this.mixin.getTargetMethod(methodNode);
            InjectorTarget injectorTarget = new InjectorTarget(this, target);
            this.targetNodes.put(target, this.injector.find(injectorTarget, this.injectionPoints));
            injectorTarget.dispose();
        }
    }

    public void inject() {
        for (Map.Entry<Target, List<InjectionNodes.InjectionNode>> entry : this.targetNodes.entrySet()) {
            this.injector.inject(entry.getKey(), entry.getValue());
        }
        this.targets.clear();
    }

    public void postInject() {
        for (MethodNode object2 : this.injectedMethods) {
            this.classNode.methods.add(object2);
        }
        String string = this.getDescription();
        String string2 = this.mixin.getReferenceMapper().getStatus();
        String string3 = this.getDynamicInfo();
        if (this.mixin.getEnvironment().getOption(MixinEnvironment.Option.DEBUG_INJECTORS) && this.injectedCallbackCount < this.expectedCallbackCount) {
            throw new InvalidInjectionException(this, String.format("Injection validation failed: %s %s%s in %s expected %d invocation(s) but %d succeeded. %s%s", string, this.method.name, this.method.desc, this.mixin, this.expectedCallbackCount, this.injectedCallbackCount, string2, string3));
        }
        if (this.injectedCallbackCount < this.requiredCallbackCount) {
            throw new InjectionError(String.format("Critical injection failure: %s %s%s in %s failed injection check, (%d/%d) succeeded. %s%s", string, this.method.name, this.method.desc, this.mixin, this.injectedCallbackCount, this.requiredCallbackCount, string2, string3));
        }
        if (this.injectedCallbackCount > this.maxCallbackCount) {
            throw new InjectionError(String.format("Critical injection failure: %s %s%s in %s failed injection check, %d succeeded of %d allowed.%s", string, this.method.name, this.method.desc, this.mixin, this.injectedCallbackCount, this.maxCallbackCount, string3));
        }
    }

    public void notifyInjected(Target target) {
    }

    protected String getDescription() {
        return "Callback method";
    }

    public String toString() {
        return InjectionInfo.describeInjector(this.mixin, this.annotation, this.method);
    }

    public Collection<MethodNode> getTargets() {
        return this.targets;
    }

    @Override
    public MethodSlice getSlice(String string) {
        return this.slices.get(this.getSliceId(string));
    }

    public String getSliceId(String string) {
        return "";
    }

    public int getInjectedCallbackCount() {
        return this.injectedCallbackCount;
    }

    public MethodNode addMethod(int n2, String string, String string2) {
        MethodNode methodNode = new MethodNode(327680, n2 | 0x1000, string, string2, null, null);
        this.injectedMethods.add(methodNode);
        return methodNode;
    }

    public void addCallbackInvocation(MethodNode methodNode) {
        ++this.injectedCallbackCount;
    }

    private void findMethods(Set<MemberInfo> set, String string) {
        this.targets.clear();
        int n2 = this.mixin.getEnvironment().getOption(MixinEnvironment.Option.REFMAP_REMAP) ? 2 : 1;
        for (MemberInfo memberInfo : set) {
            int n3 = 0;
            for (int i2 = 0; i2 < n2 && n3 < 1; ++i2) {
                int n4 = 0;
                for (MethodNode methodNode : this.classNode.methods) {
                    boolean bl;
                    if (!memberInfo.matches(methodNode.name, methodNode.desc, n4)) continue;
                    boolean bl2 = bl = Annotations.getVisible(methodNode, MixinMerged.class) != null;
                    if (memberInfo.matchAll && (Bytecode.methodIsStatic(methodNode) != this.isStatic || methodNode == this.method || bl)) continue;
                    this.checkTarget(methodNode);
                    this.targets.add(methodNode);
                    ++n4;
                    ++n3;
                }
                memberInfo = memberInfo.transform(null);
            }
        }
        if (this.targets.size() == 0) {
            throw new InvalidInjectionException(this, String.format("%s annotation on %s could not find any targets matching %s in the target class %s. %s%s", string, this.method.name, InjectionInfo.namesOf(set), this.mixin.getTarget(), this.mixin.getReferenceMapper().getStatus(), this.getDynamicInfo()));
        }
    }

    private void checkTarget(MethodNode methodNode) {
        AnnotationNode annotationNode = Annotations.getVisible(methodNode, MixinMerged.class);
        if (annotationNode == null) {
            return;
        }
        if (Annotations.getVisible(methodNode, Final.class) != null) {
            throw new InvalidInjectionException(this, String.format("%s cannot inject into @Final method %s::%s%s merged by %s", this, this.classNode.name, methodNode.name, methodNode.desc, Annotations.getValue(annotationNode, "mixin")));
        }
    }

    protected String getDynamicInfo() {
        AnnotationNode annotationNode = Annotations.getInvisible(this.method, Dynamic.class);
        String string = Strings.nullToEmpty((String)Annotations.getValue(annotationNode));
        Type type = (Type)Annotations.getValue(annotationNode, "mixin");
        if (type != null) {
            string = String.format("{%s} %s", type.getClassName(), string).trim();
        }
        return string.length() > 0 ? String.format(" Method is @Dynamic(%s)", string) : "";
    }

    public static InjectionInfo parse(MixinTargetContext mixinTargetContext, MethodNode methodNode) {
        AnnotationNode annotationNode = InjectionInfo.getInjectorAnnotation(mixinTargetContext.getMixin(), methodNode);
        if (annotationNode == null) {
            return null;
        }
        if (annotationNode.desc.endsWith(Inject.class.getSimpleName() + ";")) {
            return new CallbackInjectionInfo(mixinTargetContext, methodNode, annotationNode);
        }
        if (annotationNode.desc.endsWith(ModifyArg.class.getSimpleName() + ";")) {
            return new ModifyArgInjectionInfo(mixinTargetContext, methodNode, annotationNode);
        }
        if (annotationNode.desc.endsWith(ModifyArgs.class.getSimpleName() + ";")) {
            return new ModifyArgsInjectionInfo(mixinTargetContext, methodNode, annotationNode);
        }
        if (annotationNode.desc.endsWith(Redirect.class.getSimpleName() + ";")) {
            return new RedirectInjectionInfo(mixinTargetContext, methodNode, annotationNode);
        }
        if (annotationNode.desc.endsWith(ModifyVariable.class.getSimpleName() + ";")) {
            return new ModifyVariableInjectionInfo(mixinTargetContext, methodNode, annotationNode);
        }
        if (annotationNode.desc.endsWith(ModifyConstant.class.getSimpleName() + ";")) {
            return new ModifyConstantInjectionInfo(mixinTargetContext, methodNode, annotationNode);
        }
        return null;
    }

    public static AnnotationNode getInjectorAnnotation(IMixinInfo iMixinInfo, MethodNode methodNode) {
        AnnotationNode annotationNode = null;
        try {
            annotationNode = Annotations.getSingleVisible(methodNode, Inject.class, ModifyArg.class, ModifyArgs.class, Redirect.class, ModifyVariable.class, ModifyConstant.class);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidMixinException(iMixinInfo, String.format("Error parsing annotations on %s in %s: %s", methodNode.name, iMixinInfo.getClassName(), illegalArgumentException.getMessage()));
        }
        return annotationNode;
    }

    public static String getInjectorPrefix(AnnotationNode annotationNode) {
        if (annotationNode != null) {
            if (annotationNode.desc.endsWith(ModifyArg.class.getSimpleName() + ";")) {
                return "modify";
            }
            if (annotationNode.desc.endsWith(ModifyArgs.class.getSimpleName() + ";")) {
                return "args";
            }
            if (annotationNode.desc.endsWith(Redirect.class.getSimpleName() + ";")) {
                return "redirect";
            }
            if (annotationNode.desc.endsWith(ModifyVariable.class.getSimpleName() + ";")) {
                return "localvar";
            }
            if (annotationNode.desc.endsWith(ModifyConstant.class.getSimpleName() + ";")) {
                return "constant";
            }
        }
        return "handler";
    }

    static String describeInjector(IMixinContext iMixinContext, AnnotationNode annotationNode, MethodNode methodNode) {
        return String.format("%s->@%s::%s%s", iMixinContext.toString(), Bytecode.getSimpleName(annotationNode), methodNode.name, methodNode.desc);
    }

    private static String namesOf(Collection<MemberInfo> collection) {
        int n2 = 0;
        int n3 = collection.size();
        StringBuilder stringBuilder = new StringBuilder();
        for (MemberInfo memberInfo : collection) {
            if (n2 > 0) {
                if (n2 == n3 - 1) {
                    stringBuilder.append(" or ");
                } else {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append('\'').append(memberInfo.name).append('\'');
            ++n2;
        }
        return stringBuilder.toString();
    }
}

