/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=2, d1={"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u001a\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0001\u001a\"\u0010\f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000e\u001a\"\u0010\u000f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u000e\u001a\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\tH\u0001\u001a\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u0013H\u0001\u001a\"\u0010\u0014\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a\"\u0010\u0017\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0016\u001a\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0013H\u0001\u001a\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u0013H\u0000\u001a\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\tH\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2={"doubleToUInt", "Lkotlin/UInt;", "v", "", "(D)I", "doubleToULong", "Lkotlin/ULong;", "(D)J", "uintCompare", "", "v1", "v2", "uintDivide", "uintDivide-J1ME1BU", "(II)I", "uintRemainder", "uintRemainder-J1ME1BU", "uintToDouble", "ulongCompare", "", "ulongDivide", "ulongDivide-eb3DHEI", "(JJ)J", "ulongRemainder", "ulongRemainder-eb3DHEI", "ulongToDouble", "ulongToString", "", "base", "kotlin-stdlib"})
@JvmName(name="UnsignedKt")
public final class UnsignedKt {
    @PublishedApi
    public static final int uintCompare(int n2, int n3) {
        return Intrinsics.compare(n2 ^ Integer.MIN_VALUE, n3 ^ Integer.MIN_VALUE);
    }

    @PublishedApi
    public static final int ulongCompare(long l2, long l3) {
        long l4 = (l2 ^ Long.MIN_VALUE) - (l3 ^ Long.MIN_VALUE);
        return l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
    }

    @PublishedApi
    public static final int uintDivide-J1ME1BU(int n2, int n3) {
        int n4 = n2;
        boolean bl = false;
        long l2 = (long)n4 & 0xFFFFFFFFL;
        n4 = n3;
        bl = false;
        long l3 = l2 / ((long)n4 & 0xFFFFFFFFL);
        boolean bl2 = false;
        return UInt.constructor-impl((int)l3);
    }

    @PublishedApi
    public static final int uintRemainder-J1ME1BU(int n2, int n3) {
        int n4 = n2;
        boolean bl = false;
        long l2 = (long)n4 & 0xFFFFFFFFL;
        n4 = n3;
        bl = false;
        long l3 = l2 % ((long)n4 & 0xFFFFFFFFL);
        boolean bl2 = false;
        return UInt.constructor-impl((int)l3);
    }

    @PublishedApi
    public static final long ulongDivide-eb3DHEI(long l2, long l3) {
        long l4 = l2;
        boolean bl = false;
        long l5 = l4;
        long l6 = l3;
        boolean bl2 = false;
        l4 = l6;
        if (l4 < 0L) {
            l6 = l2;
            bl2 = false;
            return UnsignedKt.ulongCompare(l6, l3) < 0 ? ULong.constructor-impl(0L) : ULong.constructor-impl(1L);
        }
        if (l5 >= 0L) {
            return ULong.constructor-impl(l5 / l4);
        }
        l6 = (l5 >>> 1) / l4 << 1;
        long l7 = l5 - l6 * l4;
        long l8 = ULong.constructor-impl(l7);
        long l9 = ULong.constructor-impl(l4);
        boolean bl3 = false;
        return ULong.constructor-impl(l6 + (long)(UnsignedKt.ulongCompare(l8, l9) >= 0 ? 1 : 0));
    }

    @PublishedApi
    public static final long ulongRemainder-eb3DHEI(long l2, long l3) {
        long l4 = l2;
        boolean bl = false;
        long l5 = l4;
        long l6 = l3;
        boolean bl2 = false;
        l4 = l6;
        if (l4 < 0L) {
            long l7;
            l6 = l2;
            bl2 = false;
            if (UnsignedKt.ulongCompare(l6, l3) < 0) {
                l7 = l2;
            } else {
                l6 = l2;
                bl2 = false;
                l7 = ULong.constructor-impl(l6 - l3);
            }
            return l7;
        }
        if (l5 >= 0L) {
            return ULong.constructor-impl(l5 % l4);
        }
        l6 = (l5 >>> 1) / l4 << 1;
        long l8 = l5 - l6 * l4;
        long l9 = ULong.constructor-impl(l8);
        long l10 = ULong.constructor-impl(l4);
        boolean bl3 = false;
        return ULong.constructor-impl(l8 - (UnsignedKt.ulongCompare(l9, l10) >= 0 ? l4 : 0L));
    }

    @PublishedApi
    public static final int doubleToUInt(double d2) {
        int n2;
        double d3 = d2;
        boolean bl = false;
        if (Double.isNaN(d3)) {
            n2 = 0;
        } else {
            int n3 = 0;
            int n4 = 0;
            if (d2 <= UnsignedKt.uintToDouble(n3)) {
                n2 = 0;
            } else {
                n3 = -1;
                n4 = 0;
                if (d2 >= UnsignedKt.uintToDouble(n3)) {
                    n2 = -1;
                } else if (d2 <= (double)Integer.MAX_VALUE) {
                    n3 = (int)d2;
                    n4 = 0;
                    n2 = UInt.constructor-impl(n3);
                } else {
                    n3 = (int)(d2 - (double)Integer.MAX_VALUE);
                    n4 = 0;
                    n3 = UInt.constructor-impl(n3);
                    n4 = Integer.MAX_VALUE;
                    bl = false;
                    n4 = UInt.constructor-impl(n4);
                    bl = false;
                    n2 = UInt.constructor-impl(n3 + n4);
                }
            }
        }
        return n2;
    }

    @PublishedApi
    public static final long doubleToULong(double d2) {
        long l2;
        double d3 = d2;
        boolean bl = false;
        if (Double.isNaN(d3)) {
            l2 = 0L;
        } else {
            long l3 = 0L;
            bl = false;
            if (d2 <= UnsignedKt.ulongToDouble(l3)) {
                l2 = 0L;
            } else {
                l3 = -1L;
                bl = false;
                if (d2 >= UnsignedKt.ulongToDouble(l3)) {
                    l2 = -1L;
                } else if (d2 < (double)Long.MAX_VALUE) {
                    l3 = (long)d2;
                    bl = false;
                    l2 = ULong.constructor-impl(l3);
                } else {
                    l3 = (long)(d2 - 9.223372036854776E18);
                    bl = false;
                    l3 = ULong.constructor-impl(l3);
                    long l4 = Long.MIN_VALUE;
                    boolean bl2 = false;
                    l2 = ULong.constructor-impl(l3 + l4);
                }
            }
        }
        return l2;
    }

    @PublishedApi
    public static final double uintToDouble(int n2) {
        return (double)(n2 & Integer.MAX_VALUE) + (double)(n2 >>> 31 << 30) * (double)2;
    }

    @PublishedApi
    public static final double ulongToDouble(long l2) {
        return (double)(l2 >>> 11) * (double)2048 + (double)(l2 & 0x7FFL);
    }

    @NotNull
    public static final String ulongToString(long l2) {
        return UnsignedKt.ulongToString(l2, 10);
    }

    @NotNull
    public static final String ulongToString(long l2, int n2) {
        if (l2 >= 0L) {
            long l3 = l2;
            boolean bl = false;
            String string = Long.toString(l3, CharsKt.checkRadix(n2));
            Intrinsics.checkNotNullExpressionValue(string, "java.lang.Long.toString(this, checkRadix(radix))");
            return string;
        }
        long l4 = (l2 >>> 1) / (long)n2 << 1;
        long l5 = l2 - l4 * (long)n2;
        if (l5 >= (long)n2) {
            l5 -= (long)n2;
            ++l4;
        }
        StringBuilder stringBuilder = new StringBuilder();
        long l6 = l4;
        boolean bl = false;
        String string = Long.toString(l6, CharsKt.checkRadix(n2));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Long.toString(this, checkRadix(radix))");
        StringBuilder stringBuilder2 = stringBuilder.append(string);
        l6 = l5;
        bl = false;
        String string2 = Long.toString(l6, CharsKt.checkRadix(n2));
        Intrinsics.checkNotNullExpressionValue(string2, "java.lang.Long.toString(this, checkRadix(radix))");
        return stringBuilder2.append(string2).toString();
    }
}

