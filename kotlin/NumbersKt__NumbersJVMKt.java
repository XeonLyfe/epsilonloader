/*
 * Decompiled with CFR 0.150.
 */
package kotlin;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.NumbersKt__FloorDivModKt;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000*\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0005\u001a\u00020\t*\u00020\n2\u0006\u0010\b\u001a\u00020\u0001H\u0087\b\u001a\r\u0010\u000b\u001a\u00020\f*\u00020\u0006H\u0087\b\u001a\r\u0010\u000b\u001a\u00020\f*\u00020\tH\u0087\b\u001a\r\u0010\r\u001a\u00020\f*\u00020\u0006H\u0087\b\u001a\r\u0010\r\u001a\u00020\f*\u00020\tH\u0087\b\u001a\r\u0010\u000e\u001a\u00020\f*\u00020\u0006H\u0087\b\u001a\r\u0010\u000e\u001a\u00020\f*\u00020\tH\u0087\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0001H\u0087\b\u001a\r\u0010\u0012\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0012\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\u0013\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0013\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\u0014\u001a\u00020\u0002*\u00020\u0006H\u0087\b\u001a\r\u0010\u0014\u001a\u00020\u0001*\u00020\tH\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0002*\u00020\u0006H\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\tH\u0087\b\u00a8\u0006\u0016"}, d2={"countLeadingZeroBits", "", "", "countOneBits", "countTrailingZeroBits", "fromBits", "", "Lkotlin/Double$Companion;", "bits", "", "Lkotlin/Float$Companion;", "isFinite", "", "isInfinite", "isNaN", "rotateLeft", "bitCount", "rotateRight", "takeHighestOneBit", "takeLowestOneBit", "toBits", "toRawBits", "kotlin-stdlib"}, xs="kotlin/NumbersKt")
class NumbersKt__NumbersJVMKt
extends NumbersKt__FloorDivModKt {
    @InlineOnly
    private static final boolean isNaN(double d2) {
        int n2 = 0;
        return Double.isNaN(d2);
    }

    @InlineOnly
    private static final boolean isNaN(float f2) {
        int n2 = 0;
        return Float.isNaN(f2);
    }

    @InlineOnly
    private static final boolean isInfinite(double d2) {
        int n2 = 0;
        return Double.isInfinite(d2);
    }

    @InlineOnly
    private static final boolean isInfinite(float f2) {
        int n2 = 0;
        return Float.isInfinite(f2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @InlineOnly
    private static final boolean isFinite(double d2) {
        int n2 = 0;
        double d3 = d2;
        boolean bl = false;
        if (Double.isInfinite(d3)) return false;
        d3 = d2;
        bl = false;
        if (Double.isNaN(d3)) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @InlineOnly
    private static final boolean isFinite(float f2) {
        int n2 = 0;
        float f3 = f2;
        boolean bl = false;
        if (Float.isInfinite(f3)) return false;
        f3 = f2;
        bl = false;
        if (Float.isNaN(f3)) return false;
        return true;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final long toBits(double d2) {
        int n2 = 0;
        return Double.doubleToLongBits(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final long toRawBits(double d2) {
        int n2 = 0;
        return Double.doubleToRawLongBits(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double fromBits(DoubleCompanionObject doubleCompanionObject, long l2) {
        int n2 = 0;
        return Double.longBitsToDouble(l2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final int toBits(float f2) {
        int n2 = 0;
        return Float.floatToIntBits(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final int toRawBits(float f2) {
        int n2 = 0;
        return Float.floatToRawIntBits(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float fromBits(FloatCompanionObject floatCompanionObject, int n2) {
        int n3 = 0;
        return Float.intBitsToFloat(n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countOneBits(int n2) {
        int n3 = 0;
        return Integer.bitCount(n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countLeadingZeroBits(int n2) {
        int n3 = 0;
        return Integer.numberOfLeadingZeros(n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countTrailingZeroBits(int n2) {
        int n3 = 0;
        return Integer.numberOfTrailingZeros(n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int takeHighestOneBit(int n2) {
        int n3 = 0;
        return Integer.highestOneBit(n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int takeLowestOneBit(int n2) {
        int n3 = 0;
        return Integer.lowestOneBit(n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int rotateLeft(int n2, int n3) {
        int n4 = 0;
        return Integer.rotateLeft(n2, n3);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int rotateRight(int n2, int n3) {
        int n4 = 0;
        return Integer.rotateRight(n2, n3);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countOneBits(long l2) {
        int n2 = 0;
        return Long.bitCount(l2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countLeadingZeroBits(long l2) {
        int n2 = 0;
        return Long.numberOfLeadingZeros(l2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countTrailingZeroBits(long l2) {
        int n2 = 0;
        return Long.numberOfTrailingZeros(l2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final long takeHighestOneBit(long l2) {
        int n2 = 0;
        return Long.highestOneBit(l2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final long takeLowestOneBit(long l2) {
        int n2 = 0;
        return Long.lowestOneBit(l2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final long rotateLeft(long l2, int n2) {
        int n3 = 0;
        return Long.rotateLeft(l2, n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final long rotateRight(long l2, int n2) {
        int n3 = 0;
        return Long.rotateRight(l2, n2);
    }
}

