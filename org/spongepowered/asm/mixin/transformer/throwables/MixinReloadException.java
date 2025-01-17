/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.throwables.MixinException;

public class MixinReloadException
extends MixinException {
    private static final long serialVersionUID = 2L;
    private final IMixinInfo mixinInfo;

    public MixinReloadException(IMixinInfo iMixinInfo, String string) {
        super(string);
        this.mixinInfo = iMixinInfo;
    }

    public IMixinInfo getMixinInfo() {
        return this.mixinInfo;
    }
}

