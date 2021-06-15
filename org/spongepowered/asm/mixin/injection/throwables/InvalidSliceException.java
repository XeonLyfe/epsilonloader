/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.throwables;

import org.spongepowered.asm.mixin.injection.code.ISliceContext;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

public class InvalidSliceException
extends InvalidInjectionException {
    private static final long serialVersionUID = 1L;

    public InvalidSliceException(IMixinContext iMixinContext, String string) {
        super(iMixinContext, string);
    }

    public InvalidSliceException(ISliceContext iSliceContext, String string) {
        super(iSliceContext.getContext(), string);
    }

    public InvalidSliceException(IMixinContext iMixinContext, Throwable throwable) {
        super(iMixinContext, throwable);
    }

    public InvalidSliceException(ISliceContext iSliceContext, Throwable throwable) {
        super(iSliceContext.getContext(), throwable);
    }

    public InvalidSliceException(IMixinContext iMixinContext, String string, Throwable throwable) {
        super(iMixinContext, string, throwable);
    }

    public InvalidSliceException(ISliceContext iSliceContext, String string, Throwable throwable) {
        super(iSliceContext.getContext(), string, throwable);
    }
}

