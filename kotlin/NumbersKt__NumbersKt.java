/*
 * Decompiled with CFR 0.150.
 */
package kotlin;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.NumbersKt__NumbersJVMKt;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\b\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\u0014\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\b\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\r\u0010\t\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\t\u001a\u00020\u0003*\u00020\u0003H\u0087\b\u001a\r\u0010\n\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\n\u001a\u00020\u0003*\u00020\u0003H\u0087\b\u00a8\u0006\u000b"}, d2={"countLeadingZeroBits", "", "", "", "countOneBits", "countTrailingZeroBits", "rotateLeft", "bitCount", "rotateRight", "takeHighestOneBit", "takeLowestOneBit", "kotlin-stdlib"}, xs="kotlin/NumbersKt")
class NumbersKt__NumbersKt
extends NumbersKt__NumbersJVMKt {
    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countOneBits(byte by) {
        int n2 = 0;
        int n3 = by & 0xFF;
        boolean bl = false;
        return Integer.bitCount(n3);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countLeadingZeroBits(byte by) {
        int n2 = 0;
        int n3 = by & 0xFF;
        boolean bl = false;
        return Integer.numberOfLeadingZeros(n3) - 24;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countTrailingZeroBits(byte by) {
        int n2 = 0;
        int n3 = by | 0x100;
        boolean bl = false;
        return Integer.numberOfTrailingZeros(n3);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final byte takeHighestOneBit(byte by) {
        int n2 = 0;
        int n3 = by & 0xFF;
        boolean bl = false;
        return (byte)Integer.highestOneBit(n3);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final byte takeLowestOneBit(byte by) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        return (byte)Integer.lowestOneBit(by2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    public static final byte rotateLeft(byte by, int n2) {
        return (byte)(by << (n2 & 7) | (by & 0xFF) >>> 8 - (n2 & 7));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    public static final byte rotateRight(byte by, int n2) {
        return (byte)(by << 8 - (n2 & 7) | (by & 0xFF) >>> (n2 & 7));
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countOneBits(short s2) {
        int n2 = 0;
        int n3 = s2 & 0xFFFF;
        boolean bl = false;
        return Integer.bitCount(n3);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countLeadingZeroBits(short s2) {
        int n2 = 0;
        int n3 = s2 & 0xFFFF;
        boolean bl = false;
        return Integer.numberOfLeadingZeros(n3) - 16;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countTrailingZeroBits(short s2) {
        int n2 = 0;
        int n3 = s2 | 0x10000;
        boolean bl = false;
        return Integer.numberOfTrailingZeros(n3);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final short takeHighestOneBit(short s2) {
        int n2 = 0;
        int n3 = s2 & 0xFFFF;
        boolean bl = false;
        return (short)Integer.highestOneBit(n3);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final short takeLowestOneBit(short s2) {
        int n2 = 0;
        short s3 = s2;
        boolean bl = false;
        return (short)Integer.lowestOneBit(s3);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    public static final short rotateLeft(short s2, int n2) {
        return (short)(s2 << (n2 & 0xF) | (s2 & 0xFFFF) >>> 16 - (n2 & 0xF));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    public static final short rotateRight(short s2, int n2) {
        return (short)(s2 << 16 - (n2 & 0xF) | (s2 & 0xFFFF) >>> (n2 & 0xF));
    }
}

