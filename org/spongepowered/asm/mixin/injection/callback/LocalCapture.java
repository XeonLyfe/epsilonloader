/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.callback;

public enum LocalCapture {
    NO_CAPTURE(false, false),
    PRINT(false, true),
    CAPTURE_FAILSOFT,
    CAPTURE_FAILHARD,
    CAPTURE_FAILEXCEPTION;

    private final boolean captureLocals;
    private final boolean printLocals;

    private LocalCapture() {
        this(true, false);
    }

    private LocalCapture(boolean bl, boolean bl2) {
        this.captureLocals = bl;
        this.printLocals = bl2;
    }

    boolean isCaptureLocals() {
        return this.captureLocals;
    }

    boolean isPrintLocals() {
        return this.printLocals;
    }
}

