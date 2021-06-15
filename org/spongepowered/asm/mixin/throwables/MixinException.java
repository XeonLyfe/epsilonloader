/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.throwables;

public class MixinException
extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MixinException() {
    }

    public MixinException(String string) {
        super(string);
    }

    public MixinException(Throwable throwable) {
        super(throwable);
    }

    public MixinException(String string, Throwable throwable) {
        super(string, throwable);
    }
}

