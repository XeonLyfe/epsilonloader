/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util.throwables;

import org.spongepowered.asm.util.ConstraintParser;

public class ConstraintViolationException
extends Exception {
    private static final String MISSING_VALUE = "UNRESOLVED";
    private static final long serialVersionUID = 1L;
    private final ConstraintParser.Constraint constraint;
    private final String badValue;

    public ConstraintViolationException(ConstraintParser.Constraint constraint) {
        this.constraint = constraint;
        this.badValue = MISSING_VALUE;
    }

    public ConstraintViolationException(ConstraintParser.Constraint constraint, int n2) {
        this.constraint = constraint;
        this.badValue = String.valueOf(n2);
    }

    public ConstraintViolationException(String string, ConstraintParser.Constraint constraint) {
        super(string);
        this.constraint = constraint;
        this.badValue = MISSING_VALUE;
    }

    public ConstraintViolationException(String string, ConstraintParser.Constraint constraint, int n2) {
        super(string);
        this.constraint = constraint;
        this.badValue = String.valueOf(n2);
    }

    public ConstraintViolationException(Throwable throwable, ConstraintParser.Constraint constraint) {
        super(throwable);
        this.constraint = constraint;
        this.badValue = MISSING_VALUE;
    }

    public ConstraintViolationException(Throwable throwable, ConstraintParser.Constraint constraint, int n2) {
        super(throwable);
        this.constraint = constraint;
        this.badValue = String.valueOf(n2);
    }

    public ConstraintViolationException(String string, Throwable throwable, ConstraintParser.Constraint constraint) {
        super(string, throwable);
        this.constraint = constraint;
        this.badValue = MISSING_VALUE;
    }

    public ConstraintViolationException(String string, Throwable throwable, ConstraintParser.Constraint constraint, int n2) {
        super(string, throwable);
        this.constraint = constraint;
        this.badValue = String.valueOf(n2);
    }

    public ConstraintParser.Constraint getConstraint() {
        return this.constraint;
    }

    public String getBadValue() {
        return this.badValue;
    }
}

