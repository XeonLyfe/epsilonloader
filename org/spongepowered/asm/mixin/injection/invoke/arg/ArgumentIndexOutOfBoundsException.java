/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.invoke.arg;

public class ArgumentIndexOutOfBoundsException
extends IndexOutOfBoundsException {
    private static final long serialVersionUID = 1L;

    public ArgumentIndexOutOfBoundsException(int n2) {
        super("Argument index is out of bounds: " + n2);
    }
}

