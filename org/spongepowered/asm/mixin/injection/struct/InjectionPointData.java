/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.struct;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.modify.LocalVariableDiscriminator;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InvalidMemberDescriptorException;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionPointException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

public class InjectionPointData {
    private static final Pattern AT_PATTERN = InjectionPointData.createPattern();
    private final Map<String, String> args = new HashMap<String, String>();
    private final IMixinContext context;
    private final MethodNode method;
    private final AnnotationNode parent;
    private final String at;
    private final String type;
    private final InjectionPoint.Selector selector;
    private final String target;
    private final String slice;
    private final int ordinal;
    private final int opcode;
    private final String id;

    public InjectionPointData(IMixinContext iMixinContext, MethodNode methodNode, AnnotationNode annotationNode, String string, List<String> list, String string2, String string3, int n2, int n3, String string4) {
        this.context = iMixinContext;
        this.method = methodNode;
        this.parent = annotationNode;
        this.at = string;
        this.target = string2;
        this.slice = Strings.nullToEmpty(string3);
        this.ordinal = Math.max(-1, n2);
        this.opcode = n3;
        this.id = string4;
        this.parseArgs(list);
        this.args.put("target", string2);
        this.args.put("ordinal", String.valueOf(n2));
        this.args.put("opcode", String.valueOf(n3));
        Matcher matcher = AT_PATTERN.matcher(string);
        this.type = InjectionPointData.parseType(matcher, string);
        this.selector = InjectionPointData.parseSelector(matcher);
    }

    private void parseArgs(List<String> list) {
        if (list == null) {
            return;
        }
        for (String string : list) {
            if (string == null) continue;
            int n2 = string.indexOf(61);
            if (n2 > -1) {
                this.args.put(string.substring(0, n2), string.substring(n2 + 1));
                continue;
            }
            this.args.put(string, "");
        }
    }

    public String getAt() {
        return this.at;
    }

    public String getType() {
        return this.type;
    }

    public InjectionPoint.Selector getSelector() {
        return this.selector;
    }

    public IMixinContext getContext() {
        return this.context;
    }

    public MethodNode getMethod() {
        return this.method;
    }

    public Type getMethodReturnType() {
        return Type.getReturnType(this.method.desc);
    }

    public AnnotationNode getParent() {
        return this.parent;
    }

    public String getSlice() {
        return this.slice;
    }

    public LocalVariableDiscriminator getLocalVariableDiscriminator() {
        return LocalVariableDiscriminator.parse(this.parent);
    }

    public String get(String string, String string2) {
        String string3 = this.args.get(string);
        return string3 != null ? string3 : string2;
    }

    public int get(String string, int n2) {
        return InjectionPointData.parseInt(this.get(string, String.valueOf(n2)), n2);
    }

    public boolean get(String string, boolean bl) {
        return InjectionPointData.parseBoolean(this.get(string, String.valueOf(bl)), bl);
    }

    public MemberInfo get(String string) {
        try {
            return MemberInfo.parseAndValidate(this.get(string, ""), this.context);
        }
        catch (InvalidMemberDescriptorException invalidMemberDescriptorException) {
            throw new InvalidInjectionPointException(this.context, "Failed parsing @At(\"%s\").%s descriptor \"%s\" on %s", this.at, string, this.target, InjectionInfo.describeInjector(this.context, this.parent, this.method));
        }
    }

    public MemberInfo getTarget() {
        try {
            return MemberInfo.parseAndValidate(this.target, this.context);
        }
        catch (InvalidMemberDescriptorException invalidMemberDescriptorException) {
            throw new InvalidInjectionPointException(this.context, "Failed parsing @At(\"%s\") descriptor \"%s\" on %s", this.at, this.target, InjectionInfo.describeInjector(this.context, this.parent, this.method));
        }
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public int getOpcode(int n2) {
        return this.opcode > 0 ? this.opcode : n2;
    }

    public int getOpcode(int n2, int ... arrn) {
        for (int n3 : arrn) {
            if (this.opcode != n3) continue;
            return this.opcode;
        }
        return n2;
    }

    public String getId() {
        return this.id;
    }

    public String toString() {
        return this.type;
    }

    private static Pattern createPattern() {
        return Pattern.compile(String.format("^([^:]+):?(%s)?$", Joiner.on('|').join((Object[])InjectionPoint.Selector.values())));
    }

    public static String parseType(String string) {
        Matcher matcher = AT_PATTERN.matcher(string);
        return InjectionPointData.parseType(matcher, string);
    }

    private static String parseType(Matcher matcher, String string) {
        return matcher.matches() ? matcher.group(1) : string;
    }

    private static InjectionPoint.Selector parseSelector(Matcher matcher) {
        return matcher.matches() && matcher.group(2) != null ? InjectionPoint.Selector.valueOf(matcher.group(2)) : InjectionPoint.Selector.DEFAULT;
    }

    private static int parseInt(String string, int n2) {
        try {
            return Integer.parseInt(string);
        }
        catch (Exception exception) {
            return n2;
        }
    }

    private static boolean parseBoolean(String string, boolean bl) {
        try {
            return Boolean.parseBoolean(string);
        }
        catch (Exception exception) {
            return bl;
        }
    }
}

