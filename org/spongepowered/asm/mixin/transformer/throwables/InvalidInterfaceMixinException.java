/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

public class InvalidInterfaceMixinException
extends InvalidMixinException {
    private static final long serialVersionUID = 2L;

    public InvalidInterfaceMixinException(IMixinInfo iMixinInfo, String string) {
        super(iMixinInfo, string);
    }

    public InvalidInterfaceMixinException(IMixinContext iMixinContext, String string) {
        super(iMixinContext, string);
    }

    public InvalidInterfaceMixinException(IMixinInfo iMixinInfo, Throwable throwable) {
        super(iMixinInfo, throwable);
    }

    public InvalidInterfaceMixinException(IMixinContext iMixinContext, Throwable throwable) {
        super(iMixinContext, throwable);
    }

    public InvalidInterfaceMixinException(IMixinInfo iMixinInfo, String string, Throwable throwable) {
        super(iMixinInfo, string, throwable);
    }

    public InvalidInterfaceMixinException(IMixinContext iMixinContext, String string, Throwable throwable) {
        super(iMixinContext, string, throwable);
    }
}

