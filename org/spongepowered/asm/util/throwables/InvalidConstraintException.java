/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util.throwables;

public class InvalidConstraintException
extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public InvalidConstraintException() {
    }

    public InvalidConstraintException(String string) {
        super(string);
    }

    public InvalidConstraintException(Throwable throwable) {
        super(throwable);
    }

    public InvalidConstraintException(String string, Throwable throwable) {
        super(string, throwable);
    }
}

