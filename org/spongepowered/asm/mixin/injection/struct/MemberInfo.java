/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.struct;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.mixin.injection.struct.InvalidMemberDescriptorException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.refmap.IReferenceMapper;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.util.SignaturePrinter;

public final class MemberInfo {
    public final String owner;
    public final String name;
    public final String desc;
    public final boolean matchAll;
    private final boolean forceField;
    private final String unparsed;

    public MemberInfo(String string, boolean bl) {
        this(string, null, null, bl);
    }

    public MemberInfo(String string, String string2, boolean bl) {
        this(string, string2, null, bl);
    }

    public MemberInfo(String string, String string2, String string3) {
        this(string, string2, string3, false);
    }

    public MemberInfo(String string, String string2, String string3, boolean bl) {
        this(string, string2, string3, bl, null);
    }

    public MemberInfo(String string, String string2, String string3, boolean bl, String string4) {
        if (string2 != null && string2.contains(".")) {
            throw new IllegalArgumentException("Attempt to instance a MemberInfo with an invalid owner format");
        }
        this.owner = string2;
        this.name = string;
        this.desc = string3;
        this.matchAll = bl;
        this.forceField = false;
        this.unparsed = string4;
    }

    public MemberInfo(AbstractInsnNode abstractInsnNode) {
        this.matchAll = false;
        this.forceField = false;
        this.unparsed = null;
        if (abstractInsnNode instanceof MethodInsnNode) {
            MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
            this.owner = methodInsnNode.owner;
            this.name = methodInsnNode.name;
            this.desc = methodInsnNode.desc;
        } else if (abstractInsnNode instanceof FieldInsnNode) {
            FieldInsnNode fieldInsnNode = (FieldInsnNode)abstractInsnNode;
            this.owner = fieldInsnNode.owner;
            this.name = fieldInsnNode.name;
            this.desc = fieldInsnNode.desc;
        } else {
            throw new IllegalArgumentException("insn must be an instance of MethodInsnNode or FieldInsnNode");
        }
    }

    public MemberInfo(IMapping<?> iMapping) {
        this.owner = iMapping.getOwner();
        this.name = iMapping.getSimpleName();
        this.desc = iMapping.getDesc();
        this.matchAll = false;
        this.forceField = iMapping.getType() == IMapping.Type.FIELD;
        this.unparsed = null;
    }

    private MemberInfo(MemberInfo memberInfo, MappingMethod mappingMethod, boolean bl) {
        this.owner = bl ? mappingMethod.getOwner() : memberInfo.owner;
        this.name = mappingMethod.getSimpleName();
        this.desc = mappingMethod.getDesc();
        this.matchAll = memberInfo.matchAll;
        this.forceField = false;
        this.unparsed = null;
    }

    private MemberInfo(MemberInfo memberInfo, String string) {
        this.owner = string;
        this.name = memberInfo.name;
        this.desc = memberInfo.desc;
        this.matchAll = memberInfo.matchAll;
        this.forceField = memberInfo.forceField;
        this.unparsed = null;
    }

    public String toString() {
        String string;
        String string2 = this.owner != null ? "L" + this.owner + ";" : "";
        String string3 = this.name != null ? this.name : "";
        String string4 = this.matchAll ? "*" : "";
        String string5 = string = this.desc != null ? this.desc : "";
        String string6 = string.startsWith("(") ? "" : (this.desc != null ? ":" : "");
        return string2 + string3 + string4 + string6 + string;
    }

    @Deprecated
    public String toSrg() {
        if (!this.isFullyQualified()) {
            throw new MixinException("Cannot convert unqualified reference to SRG mapping");
        }
        if (this.desc.startsWith("(")) {
            return this.owner + "/" + this.name + " " + this.desc;
        }
        return this.owner + "/" + this.name;
    }

    public String toDescriptor() {
        if (this.desc == null) {
            return "";
        }
        return new SignaturePrinter(this).setFullyQualified(true).toDescriptor();
    }

    public String toCtorType() {
        if (this.unparsed == null) {
            return null;
        }
        String string = this.getReturnType();
        if (string != null) {
            return string;
        }
        if (this.owner != null) {
            return this.owner;
        }
        if (this.name != null && this.desc == null) {
            return this.name;
        }
        return this.desc != null ? this.desc : this.unparsed;
    }

    public String toCtorDesc() {
        if (this.desc != null && this.desc.startsWith("(") && this.desc.indexOf(41) > -1) {
            return this.desc.substring(0, this.desc.indexOf(41) + 1) + "V";
        }
        return null;
    }

    public String getReturnType() {
        if (this.desc == null || this.desc.indexOf(41) == -1 || this.desc.indexOf(40) != 0) {
            return null;
        }
        String string = this.desc.substring(this.desc.indexOf(41) + 1);
        if (string.startsWith("L") && string.endsWith(";")) {
            return string.substring(1, string.length() - 1);
        }
        return string;
    }

    public IMapping<?> asMapping() {
        return this.isField() ? this.asFieldMapping() : this.asMethodMapping();
    }

    public MappingMethod asMethodMapping() {
        if (!this.isFullyQualified()) {
            throw new MixinException("Cannot convert unqualified reference " + this + " to MethodMapping");
        }
        if (this.isField()) {
            throw new MixinException("Cannot convert a non-method reference " + this + " to MethodMapping");
        }
        return new MappingMethod(this.owner, this.name, this.desc);
    }

    public MappingField asFieldMapping() {
        if (!this.isField()) {
            throw new MixinException("Cannot convert non-field reference " + this + " to FieldMapping");
        }
        return new MappingField(this.owner, this.name, this.desc);
    }

    public boolean isFullyQualified() {
        return this.owner != null && this.name != null && this.desc != null;
    }

    public boolean isField() {
        return this.forceField || this.desc != null && !this.desc.startsWith("(");
    }

    public boolean isConstructor() {
        return "<init>".equals(this.name);
    }

    public boolean isClassInitialiser() {
        return "<clinit>".equals(this.name);
    }

    public boolean isInitialiser() {
        return this.isConstructor() || this.isClassInitialiser();
    }

    public MemberInfo validate() throws InvalidMemberDescriptorException {
        if (this.owner != null) {
            if (!this.owner.matches("(?i)^[\\w\\p{Sc}/]+$")) {
                throw new InvalidMemberDescriptorException("Invalid owner: " + this.owner);
            }
            if (this.unparsed != null && this.unparsed.lastIndexOf(46) > 0 && this.owner.startsWith("L")) {
                throw new InvalidMemberDescriptorException("Malformed owner: " + this.owner + " If you are seeing this message unexpectedly and the owner appears to be correct, replace the owner descriptor with formal type L" + this.owner + "; to suppress this error");
            }
        }
        if (this.name != null && !this.name.matches("(?i)^<?[\\w\\p{Sc}]+>?$")) {
            throw new InvalidMemberDescriptorException("Invalid name: " + this.name);
        }
        if (this.desc != null) {
            if (!this.desc.matches("^(\\([\\w\\p{Sc}\\[/;]*\\))?\\[*[\\w\\p{Sc}/;]+$")) {
                throw new InvalidMemberDescriptorException("Invalid descriptor: " + this.desc);
            }
            if (this.isField()) {
                if (!this.desc.equals(Type.getType(this.desc).getDescriptor())) {
                    throw new InvalidMemberDescriptorException("Invalid field type in descriptor: " + this.desc);
                }
            } else {
                try {
                    Type.getArgumentTypes(this.desc);
                }
                catch (Exception exception) {
                    throw new InvalidMemberDescriptorException("Invalid descriptor: " + this.desc);
                }
                String string = this.desc.substring(this.desc.indexOf(41) + 1);
                try {
                    Type type = Type.getType(string);
                    if (!string.equals(type.getDescriptor())) {
                        throw new InvalidMemberDescriptorException("Invalid return type \"" + string + "\" in descriptor: " + this.desc);
                    }
                }
                catch (Exception exception) {
                    throw new InvalidMemberDescriptorException("Invalid return type \"" + string + "\" in descriptor: " + this.desc);
                }
            }
        }
        return this;
    }

    public boolean matches(String string, String string2, String string3) {
        return this.matches(string, string2, string3, 0);
    }

    public boolean matches(String string, String string2, String string3, int n2) {
        if (this.desc != null && string3 != null && !this.desc.equals(string3)) {
            return false;
        }
        if (this.name != null && string2 != null && !this.name.equals(string2)) {
            return false;
        }
        if (this.owner != null && string != null && !this.owner.equals(string)) {
            return false;
        }
        return n2 == 0 || this.matchAll;
    }

    public boolean matches(String string, String string2) {
        return this.matches(string, string2, 0);
    }

    public boolean matches(String string, String string2, int n2) {
        return !(this.name != null && !this.name.equals(string) || this.desc != null && (string2 == null || !string2.equals(this.desc)) || n2 != 0 && !this.matchAll);
    }

    public boolean equals(Object object) {
        if (object == null || object.getClass() != MemberInfo.class) {
            return false;
        }
        MemberInfo memberInfo = (MemberInfo)object;
        return this.matchAll == memberInfo.matchAll && this.forceField == memberInfo.forceField && Objects.equal(this.owner, memberInfo.owner) && Objects.equal(this.name, memberInfo.name) && Objects.equal(this.desc, memberInfo.desc);
    }

    public int hashCode() {
        return Objects.hashCode(this.matchAll, this.owner, this.name, this.desc);
    }

    public MemberInfo move(String string) {
        if (string == null && this.owner == null || string != null && string.equals(this.owner)) {
            return this;
        }
        return new MemberInfo(this, string);
    }

    public MemberInfo transform(String string) {
        if (string == null && this.desc == null || string != null && string.equals(this.desc)) {
            return this;
        }
        return new MemberInfo(this.name, this.owner, string, this.matchAll);
    }

    public MemberInfo remapUsing(MappingMethod mappingMethod, boolean bl) {
        return new MemberInfo(this, mappingMethod, bl);
    }

    public static MemberInfo parseAndValidate(String string) throws InvalidMemberDescriptorException {
        return MemberInfo.parse(string, null, null).validate();
    }

    public static MemberInfo parseAndValidate(String string, IMixinContext iMixinContext) throws InvalidMemberDescriptorException {
        return MemberInfo.parse(string, iMixinContext.getReferenceMapper(), iMixinContext.getClassRef()).validate();
    }

    public static MemberInfo parse(String string) {
        return MemberInfo.parse(string, null, null);
    }

    public static MemberInfo parse(String string, IMixinContext iMixinContext) {
        return MemberInfo.parse(string, iMixinContext.getReferenceMapper(), iMixinContext.getClassRef());
    }

    private static MemberInfo parse(String string, IReferenceMapper iReferenceMapper, String string2) {
        boolean bl;
        String string3 = null;
        String string4 = null;
        String string5 = Strings.nullToEmpty(string).replaceAll("\\s", "");
        if (iReferenceMapper != null) {
            string5 = iReferenceMapper.remap(string2, string5);
        }
        int n2 = string5.lastIndexOf(46);
        int n3 = string5.indexOf(59);
        if (n2 > -1) {
            string4 = string5.substring(0, n2).replace('.', '/');
            string5 = string5.substring(n2 + 1);
        } else if (n3 > -1 && string5.startsWith("L")) {
            string4 = string5.substring(1, n3).replace('.', '/');
            string5 = string5.substring(n3 + 1);
        }
        int n4 = string5.indexOf(40);
        int n5 = string5.indexOf(58);
        if (n4 > -1) {
            string3 = string5.substring(n4);
            string5 = string5.substring(0, n4);
        } else if (n5 > -1) {
            string3 = string5.substring(n5 + 1);
            string5 = string5.substring(0, n5);
        }
        if ((string5.indexOf(47) > -1 || string5.indexOf(46) > -1) && string4 == null) {
            string4 = string5;
            string5 = "";
        }
        if (bl = string5.endsWith("*")) {
            string5 = string5.substring(0, string5.length() - 1);
        }
        if (string5.isEmpty()) {
            string5 = null;
        }
        return new MemberInfo(string5, string4, string3, bl, string);
    }

    public static MemberInfo fromMapping(IMapping<?> iMapping) {
        return new MemberInfo(iMapping);
    }
}

