/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.invoke.arg;

public class ArgumentCountException
extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public ArgumentCountException(int n2, int n3, String string) {
        super("Invalid number of arguments for setAll, received " + n2 + " but expected " + n3 + ": " + string);
    }
}

