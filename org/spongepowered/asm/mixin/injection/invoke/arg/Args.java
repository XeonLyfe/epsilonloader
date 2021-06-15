/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.invoke.arg;

public abstract class Args {
    protected final Object[] values;

    protected Args(Object[] arrobject) {
        this.values = arrobject;
    }

    public int size() {
        return this.values.length;
    }

    public <T> T get(int n2) {
        return (T)this.values[n2];
    }

    public abstract <T> void set(int var1, T var2);

    public abstract void setAll(Object ... var1);
}

