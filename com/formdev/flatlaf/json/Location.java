/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.json;

public class Location {
    public final int offset;
    public final int line;
    public final int column;

    Location(int n2, int n3, int n4) {
        this.offset = n2;
        this.column = n4;
        this.line = n3;
    }

    public String toString() {
        return this.line + ":" + this.column;
    }

    public int hashCode() {
        return this.offset;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return ((int)-1540220257L ^ 0xA4321A9E) != 0;
        }
        if (object == null) {
            return ((int)-423168626L ^ 0xE6C6F58E) != 0;
        }
        if (this.getClass() != object.getClass()) {
            return ((int)1376877893L ^ 0x52117D45) != 0;
        }
        Location location = (Location)object;
        return (this.offset == location.offset && this.column == location.column && this.line == location.line ? (int)((long)-167595790 ^ (long)-167595789) : (int)((long)1631937119 ^ (long)1631937119)) != 0;
    }
}

