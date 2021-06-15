/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.mixin.throwables.MixinException;

public class InvalidImplicitDiscriminatorException
extends MixinException {
    private static final long serialVersionUID = 1L;

    public InvalidImplicitDiscriminatorException(String string) {
        super(string);
    }

    public InvalidImplicitDiscriminatorException(String string, Throwable throwable) {
        super(string, throwable);
    }
}

