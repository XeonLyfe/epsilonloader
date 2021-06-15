/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

public final class Handle {
    final int tag;
    final String owner;
    final String name;
    final String desc;
    final boolean itf;

    @Deprecated
    public Handle(int n2, String string, String string2, String string3) {
        this(n2, string, string2, string3, n2 == 9);
    }

    public Handle(int n2, String string, String string2, String string3, boolean bl) {
        this.tag = n2;
        this.owner = string;
        this.name = string2;
        this.desc = string3;
        this.itf = bl;
    }

    public int getTag() {
        return this.tag;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean isInterface() {
        return this.itf;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Handle)) {
            return false;
        }
        Handle handle = (Handle)object;
        return this.tag == handle.tag && this.itf == handle.itf && this.owner.equals(handle.owner) && this.name.equals(handle.name) && this.desc.equals(handle.desc);
    }

    public int hashCode() {
        return this.tag + (this.itf ? 64 : 0) + this.owner.hashCode() * this.name.hashCode() * this.desc.hashCode();
    }

    public String toString() {
        return this.owner + '.' + this.name + this.desc + " (" + this.tag + (this.itf ? " itf" : "") + ')';
    }
}

