/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.launch;

public class MixinInitialisationError
extends Error {
    private static final long serialVersionUID = 1L;

    public MixinInitialisationError() {
    }

    public MixinInitialisationError(String string) {
        super(string);
    }

    public MixinInitialisationError(Throwable throwable) {
        super(throwable);
    }

    public MixinInitialisationError(String string, Throwable throwable) {
        super(string, throwable);
    }
}

