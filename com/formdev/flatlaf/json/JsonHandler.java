/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.json;

import com.formdev.flatlaf.json.JsonParser;
import com.formdev.flatlaf.json.Location;

abstract class JsonHandler<A, O> {
    JsonParser parser;

    JsonHandler() {
    }

    protected Location getLocation() {
        return this.parser.getLocation();
    }

    public void startNull() {
    }

    public void endNull() {
    }

    public void startBoolean() {
    }

    public void endBoolean(boolean bl) {
    }

    public void startString() {
    }

    public void endString(String string) {
    }

    public void startNumber() {
    }

    public void endNumber(String string) {
    }

    public A startArray() {
        return null;
    }

    public void endArray(A a2) {
    }

    public void startArrayValue(A a2) {
    }

    public void endArrayValue(A a2) {
    }

    public O startObject() {
        return null;
    }

    public void endObject(O o2) {
    }

    public void startObjectName(O o2) {
    }

    public void endObjectName(O o2, String string) {
    }

    public void startObjectValue(O o2, String string) {
    }

    public void endObjectValue(O o2, String string) {
    }
}

