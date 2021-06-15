/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.obfuscation.mapping.common;

import com.google.common.base.Objects;
import org.spongepowered.asm.obfuscation.mapping.IMapping;

public class MappingMethod
implements IMapping<MappingMethod> {
    private final String owner;
    private final String name;
    private final String desc;

    public MappingMethod(String string, String string2) {
        this(MappingMethod.getOwnerFromName(string), MappingMethod.getBaseName(string), string2);
    }

    public MappingMethod(String string, String string2, String string3) {
        this.owner = string;
        this.name = string2;
        this.desc = string3;
    }

    @Override
    public IMapping.Type getType() {
        return IMapping.Type.METHOD;
    }

    @Override
    public String getName() {
        if (this.name == null) {
            return null;
        }
        return (this.owner != null ? this.owner + "/" : "") + this.name;
    }

    @Override
    public String getSimpleName() {
        return this.name;
    }

    @Override
    public String getOwner() {
        return this.owner;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public MappingMethod getSuper() {
        return null;
    }

    public boolean isConstructor() {
        return "<init>".equals(this.name);
    }

    @Override
    public MappingMethod move(String string) {
        return new MappingMethod(string, this.getSimpleName(), this.getDesc());
    }

    @Override
    public MappingMethod remap(String string) {
        return new MappingMethod(this.getOwner(), string, this.getDesc());
    }

    @Override
    public MappingMethod transform(String string) {
        return new MappingMethod(this.getOwner(), this.getSimpleName(), string);
    }

    @Override
    public MappingMethod copy() {
        return new MappingMethod(this.getOwner(), this.getSimpleName(), this.getDesc());
    }

    public MappingMethod addPrefix(String string) {
        String string2 = this.getSimpleName();
        if (string2 == null || string2.startsWith(string)) {
            return this;
        }
        return new MappingMethod(this.getOwner(), string + string2, this.getDesc());
    }

    public int hashCode() {
        return Objects.hashCode(this.getName(), this.getDesc());
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof MappingMethod) {
            return Objects.equal(this.name, ((MappingMethod)object).name) && Objects.equal(this.desc, ((MappingMethod)object).desc);
        }
        return false;
    }

    @Override
    public String serialise() {
        return this.toString();
    }

    public String toString() {
        String string = this.getDesc();
        return String.format("%s%s%s", this.getName(), string != null ? " " : "", string != null ? string : "");
    }

    private static String getBaseName(String string) {
        if (string == null) {
            return null;
        }
        int n2 = string.lastIndexOf(47);
        return n2 > -1 ? string.substring(n2 + 1) : string;
    }

    private static String getOwnerFromName(String string) {
        if (string == null) {
            return null;
        }
        int n2 = string.lastIndexOf(47);
        return n2 > -1 ? string.substring(0, n2) : null;
    }
}

