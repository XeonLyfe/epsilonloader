/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.obfuscation.mapping.common;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import org.spongepowered.asm.obfuscation.mapping.IMapping;

public class MappingField
implements IMapping<MappingField> {
    private final String owner;
    private final String name;
    private final String desc;

    public MappingField(String string, String string2) {
        this(string, string2, null);
    }

    public MappingField(String string, String string2, String string3) {
        this.owner = string;
        this.name = string2;
        this.desc = string3;
    }

    @Override
    public IMapping.Type getType() {
        return IMapping.Type.FIELD;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public final String getSimpleName() {
        return this.name;
    }

    @Override
    public final String getOwner() {
        return this.owner;
    }

    @Override
    public final String getDesc() {
        return this.desc;
    }

    @Override
    public MappingField getSuper() {
        return null;
    }

    @Override
    public MappingField move(String string) {
        return new MappingField(string, this.getName(), this.getDesc());
    }

    @Override
    public MappingField remap(String string) {
        return new MappingField(this.getOwner(), string, this.getDesc());
    }

    @Override
    public MappingField transform(String string) {
        return new MappingField(this.getOwner(), this.getName(), string);
    }

    @Override
    public MappingField copy() {
        return new MappingField(this.getOwner(), this.getName(), this.getDesc());
    }

    public int hashCode() {
        return Objects.hashCode(this.toString());
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof MappingField) {
            return Objects.equal(this.toString(), ((MappingField)object).toString());
        }
        return false;
    }

    @Override
    public String serialise() {
        return this.toString();
    }

    public String toString() {
        return String.format("L%s;%s:%s", this.getOwner(), this.getName(), Strings.nullToEmpty(this.getDesc()));
    }
}

