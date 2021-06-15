/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.throwables;

public class MixinApplyError
extends Error {
    private static final long serialVersionUID = 1L;

    public MixinApplyError(String string) {
        super(string);
    }

    public MixinApplyError(Throwable throwable) {
        super(throwable);
    }

    public MixinApplyError(String string, Throwable throwable) {
        super(string, throwable);
    }
}

