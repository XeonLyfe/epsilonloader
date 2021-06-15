/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

import org.spongepowered.asm.lib.ByteVector;

public class TypePath {
    public static final int ARRAY_ELEMENT = 0;
    public static final int INNER_TYPE = 1;
    public static final int WILDCARD_BOUND = 2;
    public static final int TYPE_ARGUMENT = 3;
    byte[] b;
    int offset;

    TypePath(byte[] arrby, int n2) {
        this.b = arrby;
        this.offset = n2;
    }

    public int getLength() {
        return this.b[this.offset];
    }

    public int getStep(int n2) {
        return this.b[this.offset + 2 * n2 + 1];
    }

    public int getStepArgument(int n2) {
        return this.b[this.offset + 2 * n2 + 2];
    }

    public static TypePath fromString(String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        int n2 = string.length();
        ByteVector byteVector = new ByteVector(n2);
        byteVector.putByte(0);
        int n3 = 0;
        while (n3 < n2) {
            char c2;
            if ((c2 = string.charAt(n3++)) == '[') {
                byteVector.put11(0, 0);
                continue;
            }
            if (c2 == '.') {
                byteVector.put11(1, 0);
                continue;
            }
            if (c2 == '*') {
                byteVector.put11(2, 0);
                continue;
            }
            if (c2 < '0' || c2 > '9') continue;
            int n4 = c2 - 48;
            while (n3 < n2 && (c2 = string.charAt(n3)) >= '0' && c2 <= '9') {
                n4 = n4 * 10 + c2 - 48;
                ++n3;
            }
            if (n3 < n2 && string.charAt(n3) == ';') {
                ++n3;
            }
            byteVector.put11(3, n4);
        }
        byteVector.data[0] = (byte)(byteVector.length / 2);
        return new TypePath(byteVector.data, 0);
    }

    public String toString() {
        int n2 = this.getLength();
        StringBuilder stringBuilder = new StringBuilder(n2 * 2);
        block6: for (int i2 = 0; i2 < n2; ++i2) {
            switch (this.getStep(i2)) {
                case 0: {
                    stringBuilder.append('[');
                    continue block6;
                }
                case 1: {
                    stringBuilder.append('.');
                    continue block6;
                }
                case 2: {
                    stringBuilder.append('*');
                    continue block6;
                }
                case 3: {
                    stringBuilder.append(this.getStepArgument(i2)).append(';');
                    continue block6;
                }
                default: {
                    stringBuilder.append('_');
                }
            }
        }
        return stringBuilder.toString();
    }
}

