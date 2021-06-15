/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.throwables;

import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

public class InvalidInjectionPointException
extends InvalidInjectionException {
    private static final long serialVersionUID = 2L;

    public InvalidInjectionPointException(IMixinContext iMixinContext, String string, Object ... arrobject) {
        super(iMixinContext, String.format(string, arrobject));
    }

    public InvalidInjectionPointException(InjectionInfo injectionInfo, String string, Object ... arrobject) {
        super(injectionInfo, String.format(string, arrobject));
    }

    public InvalidInjectionPointException(IMixinContext iMixinContext, Throwable throwable, String string, Object ... arrobject) {
        super(iMixinContext, String.format(string, arrobject), throwable);
    }

    public InvalidInjectionPointException(InjectionInfo injectionInfo, Throwable throwable, String string, Object ... arrobject) {
        super(injectionInfo, String.format(string, arrobject), throwable);
    }
}

