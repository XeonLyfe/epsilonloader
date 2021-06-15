/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.json;

import com.formdev.flatlaf.json.Location;

public class ParseException
extends RuntimeException {
    private final Location location;

    ParseException(String string, Location location) {
        super(string + " at " + location);
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    @Deprecated
    public int getOffset() {
        return this.location.offset;
    }

    @Deprecated
    public int getLine() {
        return this.location.line;
    }

    @Deprecated
    public int getColumn() {
        return this.location.column;
    }
}

