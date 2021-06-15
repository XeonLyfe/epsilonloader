/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.throwables.MixinException;

public class InvalidMixinException
extends MixinException {
    private static final long serialVersionUID = 2L;
    private final IMixinInfo mixin;

    public InvalidMixinException(IMixinInfo iMixinInfo, String string) {
        super(string);
        this.mixin = iMixinInfo;
    }

    public InvalidMixinException(IMixinContext iMixinContext, String string) {
        this(iMixinContext.getMixin(), string);
    }

    public InvalidMixinException(IMixinInfo iMixinInfo, Throwable throwable) {
        super(throwable);
        this.mixin = iMixinInfo;
    }

    public InvalidMixinException(IMixinContext iMixinContext, Throwable throwable) {
        this(iMixinContext.getMixin(), throwable);
    }

    public InvalidMixinException(IMixinInfo iMixinInfo, String string, Throwable throwable) {
        super(string, throwable);
        this.mixin = iMixinInfo;
    }

    public InvalidMixinException(IMixinContext iMixinContext, String string, Throwable throwable) {
        super(string, throwable);
        this.mixin = iMixinContext.getMixin();
    }

    public IMixinInfo getMixin() {
        return this.mixin;
    }
}

