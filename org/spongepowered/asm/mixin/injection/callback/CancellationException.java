/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.callback;

public class CancellationException
extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CancellationException() {
    }

    public CancellationException(String string) {
        super(string);
    }

    public CancellationException(Throwable throwable) {
        super(throwable);
    }

    public CancellationException(String string, Throwable throwable) {
        super(string, throwable);
    }
}

