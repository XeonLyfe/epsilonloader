/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util.throwables;

import org.spongepowered.asm.mixin.throwables.MixinException;

public class LVTGeneratorException
extends MixinException {
    private static final long serialVersionUID = 1L;

    public LVTGeneratorException(String string) {
        super(string);
    }

    public LVTGeneratorException(String string, Throwable throwable) {
        super(string, throwable);
    }
}

