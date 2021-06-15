/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util;

public final class Counter {
    public int value;

    public boolean equals(Object object) {
        return object != null && object.getClass() == Counter.class && ((Counter)object).value == this.value;
    }

    public int hashCode() {
        return this.value;
    }
}

