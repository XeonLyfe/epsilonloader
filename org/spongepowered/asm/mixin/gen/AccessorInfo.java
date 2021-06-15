/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 */
package org.spongepowered.asm.mixin.gen;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.AccessorGenerator;
import org.spongepowered.asm.mixin.gen.AccessorGeneratorFieldGetter;
import org.spongepowered.asm.mixin.gen.AccessorGeneratorFieldSetter;
import org.spongepowered.asm.mixin.gen.AccessorGeneratorMethodProxy;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.gen.InvokerInfo;
import org.spongepowered.asm.mixin.gen.throwables.InvalidAccessorException;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.struct.SpecialMethodInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

public class AccessorInfo
extends SpecialMethodInfo {
    protected static final Pattern PATTERN_ACCESSOR = Pattern.compile("^(get|set|is|invoke|call)(([A-Z])(.*?))(_\\$md.*)?$");
    protected final Type[] argTypes;
    protected final Type returnType;
    protected final AccessorType type;
    private final Type targetFieldType;
    protected final MemberInfo target;
    protected FieldNode targetField;
    protected MethodNode targetMethod;

    public AccessorInfo(MixinTargetContext mixinTargetContext, MethodNode methodNode) {
        this(mixinTargetContext, methodNode, Accessor.class);
    }

    protected AccessorInfo(MixinTargetContext mixinTargetContext, MethodNode methodNode, Class<? extends Annotation> class_) {
        super(mixinTargetContext, methodNode, Annotations.getVisible(methodNode, class_));
        this.argTypes = Type.getArgumentTypes(methodNode.desc);
        this.returnType = Type.getReturnType(methodNode.desc);
        this.type = this.initType();
        this.targetFieldType = this.initTargetFieldType();
        this.target = this.initTarget();
    }

    protected AccessorType initType() {
        if (this.returnType.equals(Type.VOID_TYPE)) {
            return AccessorType.FIELD_SETTER;
        }
        return AccessorType.FIELD_GETTER;
    }

    protected Type initTargetFieldType() {
        switch (this.type) {
            case FIELD_GETTER: {
                if (this.argTypes.length > 0) {
                    throw new InvalidAccessorException((IMixinContext)this.mixin, this + " must take exactly 0 arguments, found " + this.argTypes.length);
                }
                return this.returnType;
            }
            case FIELD_SETTER: {
                if (this.argTypes.length != 1) {
                    throw new InvalidAccessorException((IMixinContext)this.mixin, this + " must take exactly 1 argument, found " + this.argTypes.length);
                }
                return this.argTypes[0];
            }
        }
        throw new InvalidAccessorException((IMixinContext)this.mixin, "Computed unsupported accessor type " + (Object)((Object)this.type) + " for " + this);
    }

    protected MemberInfo initTarget() {
        MemberInfo memberInfo = new MemberInfo(this.getTargetName(), null, this.targetFieldType.getDescriptor());
        this.annotation.visit("target", memberInfo.toString());
        return memberInfo;
    }

    protected String getTargetName() {
        String string = (String)Annotations.getValue(this.annotation);
        if (Strings.isNullOrEmpty(string)) {
            String string2 = this.inflectTarget();
            if (string2 == null) {
                throw new InvalidAccessorException((IMixinContext)this.mixin, "Failed to inflect target name for " + this + ", supported prefixes: [get, set, is]");
            }
            return string2;
        }
        return MemberInfo.parse((String)string, (IMixinContext)this.mixin).name;
    }

    protected String inflectTarget() {
        return AccessorInfo.inflectTarget(this.method.name, this.type, this.toString(), this.mixin, this.mixin.getEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERBOSE));
    }

    public static String inflectTarget(String string, AccessorType accessorType, String string2, IMixinContext iMixinContext, boolean bl) {
        Matcher matcher = PATTERN_ACCESSOR.matcher(string);
        if (matcher.matches()) {
            String string3 = matcher.group(1);
            String string4 = matcher.group(3);
            String string5 = matcher.group(4);
            String string6 = String.format("%s%s", AccessorInfo.toLowerCase(string4, !AccessorInfo.isUpperCase(string5)), string5);
            if (!accessorType.isExpectedPrefix(string3) && bl) {
                LogManager.getLogger((String)"mixin").warn("Unexpected prefix for {}, found [{}] expecting {}", new Object[]{string2, string3, accessorType.getExpectedPrefixes()});
            }
            return MemberInfo.parse((String)string6, (IMixinContext)iMixinContext).name;
        }
        return null;
    }

    public final MemberInfo getTarget() {
        return this.target;
    }

    public final Type getTargetFieldType() {
        return this.targetFieldType;
    }

    public final FieldNode getTargetField() {
        return this.targetField;
    }

    public final MethodNode getTargetMethod() {
        return this.targetMethod;
    }

    public final Type getReturnType() {
        return this.returnType;
    }

    public final Type[] getArgTypes() {
        return this.argTypes;
    }

    public String toString() {
        return String.format("%s->@%s[%s]::%s%s", this.mixin.toString(), Bytecode.getSimpleName(this.annotation), this.type.toString(), this.method.name, this.method.desc);
    }

    public void locate() {
        this.targetField = this.findTargetField();
    }

    public MethodNode generate() {
        MethodNode methodNode = this.type.getGenerator(this).generate();
        Bytecode.mergeAnnotations(this.method, methodNode);
        return methodNode;
    }

    private FieldNode findTargetField() {
        return this.findTarget(this.classNode.fields);
    }

    protected <TNode> TNode findTarget(List<TNode> list) {
        TNode TNode = null;
        ArrayList<TNode> arrayList = new ArrayList<TNode>();
        for (TNode TNode2 : list) {
            String string;
            String string2 = AccessorInfo.getNodeDesc(TNode2);
            if (string2 == null || !string2.equals(this.target.desc) || (string = AccessorInfo.getNodeName(TNode2)) == null) continue;
            if (string.equals(this.target.name)) {
                TNode = TNode2;
            }
            if (!string.equalsIgnoreCase(this.target.name)) continue;
            arrayList.add(TNode2);
        }
        if (TNode != null) {
            if (arrayList.size() > 1) {
                LogManager.getLogger((String)"mixin").debug("{} found an exact match for {} but other candidates were found!", new Object[]{this, this.target});
            }
            return TNode;
        }
        if (arrayList.size() == 1) {
            return (TNode)arrayList.get(0);
        }
        String string = arrayList.size() == 0 ? "No" : "Multiple";
        throw new InvalidAccessorException(this, (String)string + " candidates were found matching " + this.target + " in " + this.classNode.name + " for " + this);
    }

    private static <TNode> String getNodeDesc(TNode TNode) {
        return TNode instanceof MethodNode ? ((MethodNode)TNode).desc : (TNode instanceof FieldNode ? ((FieldNode)TNode).desc : null);
    }

    private static <TNode> String getNodeName(TNode TNode) {
        return TNode instanceof MethodNode ? ((MethodNode)TNode).name : (TNode instanceof FieldNode ? ((FieldNode)TNode).name : null);
    }

    public static AccessorInfo of(MixinTargetContext mixinTargetContext, MethodNode methodNode, Class<? extends Annotation> class_) {
        if (class_ == Accessor.class) {
            return new AccessorInfo(mixinTargetContext, methodNode);
        }
        if (class_ == Invoker.class) {
            return new InvokerInfo(mixinTargetContext, methodNode);
        }
        throw new InvalidAccessorException((IMixinContext)mixinTargetContext, "Could not parse accessor for unknown type " + class_.getName());
    }

    private static String toLowerCase(String string, boolean bl) {
        return bl ? string.toLowerCase() : string;
    }

    private static boolean isUpperCase(String string) {
        return string.toUpperCase().equals(string);
    }

    public static enum AccessorType {
        FIELD_GETTER(ImmutableSet.of("get", "is")){

            @Override
            AccessorGenerator getGenerator(AccessorInfo accessorInfo) {
                return new AccessorGeneratorFieldGetter(accessorInfo);
            }
        }
        ,
        FIELD_SETTER(ImmutableSet.of("set")){

            @Override
            AccessorGenerator getGenerator(AccessorInfo accessorInfo) {
                return new AccessorGeneratorFieldSetter(accessorInfo);
            }
        }
        ,
        METHOD_PROXY(ImmutableSet.of("call", "invoke")){

            @Override
            AccessorGenerator getGenerator(AccessorInfo accessorInfo) {
                return new AccessorGeneratorMethodProxy(accessorInfo);
            }
        };

        private final Set<String> expectedPrefixes;

        private AccessorType(Set<String> set) {
            this.expectedPrefixes = set;
        }

        public boolean isExpectedPrefix(String string) {
            return this.expectedPrefixes.contains(string);
        }

        public String getExpectedPrefixes() {
            return this.expectedPrefixes.toString();
        }

        abstract AccessorGenerator getGenerator(AccessorInfo var1);
    }
}

