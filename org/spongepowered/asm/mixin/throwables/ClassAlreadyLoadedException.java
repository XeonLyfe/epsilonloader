/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.throwables;

import org.spongepowered.asm.mixin.throwables.MixinException;

public class ClassAlreadyLoadedException
extends MixinException {
    private static final long serialVersionUID = 1L;

    public ClassAlreadyLoadedException(String string) {
        super(string);
    }

    public ClassAlreadyLoadedException(Throwable throwable) {
        super(throwable);
    }

    public ClassAlreadyLoadedException(String string, Throwable throwable) {
        super(string, throwable);
    }
}

