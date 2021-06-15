/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.transformer.throwables;

public class MixinTransformerError
extends Error {
    private static final long serialVersionUID = 1L;

    public MixinTransformerError(String string) {
        super(string);
    }

    public MixinTransformerError(Throwable throwable) {
        super(throwable);
    }

    public MixinTransformerError(String string, Throwable throwable) {
        super(string, throwable);
    }
}

