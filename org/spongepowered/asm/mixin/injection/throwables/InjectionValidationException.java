/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.throwables;

import org.spongepowered.asm.mixin.injection.struct.InjectorGroupInfo;

public class InjectionValidationException
extends Exception {
    private static final long serialVersionUID = 1L;
    private final InjectorGroupInfo group;

    public InjectionValidationException(InjectorGroupInfo injectorGroupInfo, String string) {
        super(string);
        this.group = injectorGroupInfo;
    }

    public InjectorGroupInfo getGroup() {
        return this.group;
    }
}

