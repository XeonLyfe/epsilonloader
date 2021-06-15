/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

public class ByteVector {
    byte[] data;
    int length;

    public ByteVector() {
        this.data = new byte[64];
    }

    public ByteVector(int n2) {
        this.data = new byte[n2];
    }

    public ByteVector putByte(int n2) {
        int n3 = this.length;
        if (n3 + 1 > this.data.length) {
            this.enlarge(1);
        }
        this.data[n3++] = (byte)n2;
        this.length = n3;
        return this;
    }

    ByteVector put11(int n2, int n3) {
        int n4 = this.length;
        if (n4 + 2 > this.data.length) {
            this.enlarge(2);
        }
        byte[] arrby = this.data;
        arrby[n4++] = (byte)n2;
        arrby[n4++] = (byte)n3;
        this.length = n4;
        return this;
    }

    public ByteVector putShort(int n2) {
        int n3 = this.length;
        if (n3 + 2 > this.data.length) {
            this.enlarge(2);
        }
        byte[] arrby = this.data;
        arrby[n3++] = (byte)(n2 >>> 8);
        arrby[n3++] = (byte)n2;
        this.length = n3;
        return this;
    }

    ByteVector put12(int n2, int n3) {
        int n4 = this.length;
        if (n4 + 3 > this.data.length) {
            this.enlarge(3);
        }
        byte[] arrby = this.data;
        arrby[n4++] = (byte)n2;
        arrby[n4++] = (byte)(n3 >>> 8);
        arrby[n4++] = (byte)n3;
        this.length = n4;
        return this;
    }

    public ByteVector putInt(int n2) {
        int n3 = this.length;
        if (n3 + 4 > this.data.length) {
            this.enlarge(4);
        }
        byte[] arrby = this.data;
        arrby[n3++] = (byte)(n2 >>> 24);
        arrby[n3++] = (byte)(n2 >>> 16);
        arrby[n3++] = (byte)(n2 >>> 8);
        arrby[n3++] = (byte)n2;
        this.length = n3;
        return this;
    }

    public ByteVector putLong(long l2) {
        int n2 = this.length;
        if (n2 + 8 > this.data.length) {
            this.enlarge(8);
        }
        byte[] arrby = this.data;
        int n3 = (int)(l2 >>> 32);
        arrby[n2++] = (byte)(n3 >>> 24);
        arrby[n2++] = (byte)(n3 >>> 16);
        arrby[n2++] = (byte)(n3 >>> 8);
        arrby[n2++] = (byte)n3;
        n3 = (int)l2;
        arrby[n2++] = (byte)(n3 >>> 24);
        arrby[n2++] = (byte)(n3 >>> 16);
        arrby[n2++] = (byte)(n3 >>> 8);
        arrby[n2++] = (byte)n3;
        this.length = n2;
        return this;
    }

    public ByteVector putUTF8(String string) {
        int n2 = string.length();
        if (n2 > 65535) {
            throw new IllegalArgumentException();
        }
        int n3 = this.length;
        if (n3 + 2 + n2 > this.data.length) {
            this.enlarge(2 + n2);
        }
        byte[] arrby = this.data;
        arrby[n3++] = (byte)(n2 >>> 8);
        arrby[n3++] = (byte)n2;
        for (int i2 = 0; i2 < n2; ++i2) {
            char c2 = string.charAt(i2);
            if (c2 < '\u0001' || c2 > '\u007f') {
                this.length = n3;
                return this.encodeUTF8(string, i2, 65535);
            }
            arrby[n3++] = (byte)c2;
        }
        this.length = n3;
        return this;
    }

    ByteVector encodeUTF8(String string, int n2, int n3) {
        char c2;
        int n4;
        int n5 = string.length();
        int n6 = n2;
        for (n4 = n2; n4 < n5; ++n4) {
            c2 = string.charAt(n4);
            if (c2 >= '\u0001' && c2 <= '\u007f') {
                ++n6;
                continue;
            }
            if (c2 > '\u07ff') {
                n6 += 3;
                continue;
            }
            n6 += 2;
        }
        if (n6 > n3) {
            throw new IllegalArgumentException();
        }
        n4 = this.length - n2 - 2;
        if (n4 >= 0) {
            this.data[n4] = (byte)(n6 >>> 8);
            this.data[n4 + 1] = (byte)n6;
        }
        if (this.length + n6 - n2 > this.data.length) {
            this.enlarge(n6 - n2);
        }
        int n7 = this.length;
        for (int i2 = n2; i2 < n5; ++i2) {
            c2 = string.charAt(i2);
            if (c2 >= '\u0001' && c2 <= '\u007f') {
                this.data[n7++] = (byte)c2;
                continue;
            }
            if (c2 > '\u07ff') {
                this.data[n7++] = (byte)(0xE0 | c2 >> 12 & 0xF);
                this.data[n7++] = (byte)(0x80 | c2 >> 6 & 0x3F);
                this.data[n7++] = (byte)(0x80 | c2 & 0x3F);
                continue;
            }
            this.data[n7++] = (byte)(0xC0 | c2 >> 6 & 0x1F);
            this.data[n7++] = (byte)(0x80 | c2 & 0x3F);
        }
        this.length = n7;
        return this;
    }

    public ByteVector putByteArray(byte[] arrby, int n2, int n3) {
        if (this.length + n3 > this.data.length) {
            this.enlarge(n3);
        }
        if (arrby != null) {
            System.arraycopy(arrby, n2, this.data, this.length, n3);
        }
        this.length += n3;
        return this;
    }

    private void enlarge(int n2) {
        int n3 = 2 * this.data.length;
        int n4 = this.length + n2;
        byte[] arrby = new byte[n3 > n4 ? n3 : n4];
        System.arraycopy(this.data, 0, arrby, 0, this.length);
        this.data = arrby;
    }
}

