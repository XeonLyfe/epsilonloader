/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

public class MixinTargetAlreadyLoadedException
extends InvalidMixinException {
    private static final long serialVersionUID = 1L;
    private final String target;

    public MixinTargetAlreadyLoadedException(IMixinInfo iMixinInfo, String string, String string2) {
        super(iMixinInfo, string);
        this.target = string2;
    }

    public MixinTargetAlreadyLoadedException(IMixinInfo iMixinInfo, String string, String string2, Throwable throwable) {
        super(iMixinInfo, string, throwable);
        this.target = string2;
    }

    public String getTarget() {
        return this.target;
    }
}

