/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.gen.throwables;

import org.spongepowered.asm.mixin.gen.AccessorInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

public class InvalidAccessorException
extends InvalidMixinException {
    private static final long serialVersionUID = 2L;
    private final AccessorInfo info;

    public InvalidAccessorException(IMixinContext iMixinContext, String string) {
        super(iMixinContext, string);
        this.info = null;
    }

    public InvalidAccessorException(AccessorInfo accessorInfo, String string) {
        super(accessorInfo.getContext(), string);
        this.info = accessorInfo;
    }

    public InvalidAccessorException(IMixinContext iMixinContext, Throwable throwable) {
        super(iMixinContext, throwable);
        this.info = null;
    }

    public InvalidAccessorException(AccessorInfo accessorInfo, Throwable throwable) {
        super(accessorInfo.getContext(), throwable);
        this.info = accessorInfo;
    }

    public InvalidAccessorException(IMixinContext iMixinContext, String string, Throwable throwable) {
        super(iMixinContext, string, throwable);
        this.info = null;
    }

    public InvalidAccessorException(AccessorInfo accessorInfo, String string, Throwable throwable) {
        super(accessorInfo.getContext(), string, throwable);
        this.info = accessorInfo;
    }

    public AccessorInfo getAccessorInfo() {
        return this.info;
    }
}

