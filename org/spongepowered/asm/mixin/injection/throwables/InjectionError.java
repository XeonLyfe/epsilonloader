/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.throwables;

public class InjectionError
extends Error {
    private static final long serialVersionUID = 1L;

    public InjectionError() {
    }

    public InjectionError(String string) {
        super(string);
    }

    public InjectionError(Throwable throwable) {
        super(throwable);
    }

    public InjectionError(String string, Throwable throwable) {
        super(string, throwable);
    }
}

