/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.throwables;

public class MixinPrepareError
extends Error {
    private static final long serialVersionUID = 1L;

    public MixinPrepareError(String string) {
        super(string);
    }

    public MixinPrepareError(Throwable throwable) {
        super(throwable);
    }

    public MixinPrepareError(String string, Throwable throwable) {
        super(string, throwable);
    }
}

