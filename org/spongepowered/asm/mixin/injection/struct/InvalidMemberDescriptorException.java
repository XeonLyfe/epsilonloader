/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.mixin.throwables.MixinException;

public class InvalidMemberDescriptorException
extends MixinException {
    private static final long serialVersionUID = 1L;

    public InvalidMemberDescriptorException(String string) {
        super(string);
    }

    public InvalidMemberDescriptorException(Throwable throwable) {
        super(throwable);
    }

    public InvalidMemberDescriptorException(String string, Throwable throwable) {
        super(string, throwable);
    }
}

