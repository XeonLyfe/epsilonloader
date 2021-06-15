/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.throwables;

import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

public class InvalidInjectionException
extends InvalidMixinException {
    private static final long serialVersionUID = 2L;
    private final InjectionInfo info;

    public InvalidInjectionException(IMixinContext iMixinContext, String string) {
        super(iMixinContext, string);
        this.info = null;
    }

    public InvalidInjectionException(InjectionInfo injectionInfo, String string) {
        super(injectionInfo.getContext(), string);
        this.info = injectionInfo;
    }

    public InvalidInjectionException(IMixinContext iMixinContext, Throwable throwable) {
        super(iMixinContext, throwable);
        this.info = null;
    }

    public InvalidInjectionException(InjectionInfo injectionInfo, Throwable throwable) {
        super(injectionInfo.getContext(), throwable);
        this.info = injectionInfo;
    }

    public InvalidInjectionException(IMixinContext iMixinContext, String string, Throwable throwable) {
        super(iMixinContext, string, throwable);
        this.info = null;
    }

    public InvalidInjectionException(InjectionInfo injectionInfo, String string, Throwable throwable) {
        super(injectionInfo.getContext(), string, throwable);
        this.info = injectionInfo;
    }

    public InjectionInfo getInjectionInfo() {
        return this.info;
    }
}

