/*
 * Decompiled with CFR 0.150.
 */
package kotlin.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2={"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"})
public final class UProgressionUtilKt {
    private static final int differenceModulo-WZ9TVnA(int n2, int n3, int n4) {
        int n5;
        int n6 = n2;
        int n7 = 0;
        int n8 = UnsignedKt.uintRemainder-J1ME1BU(n6, n4);
        n7 = n3;
        boolean bl = false;
        n6 = UnsignedKt.uintRemainder-J1ME1BU(n7, n4);
        n7 = n8;
        bl = false;
        if (UnsignedKt.uintCompare(n7, n6) >= 0) {
            n7 = n8;
            bl = false;
            n5 = UInt.constructor-impl(n7 - n6);
        } else {
            n7 = n8;
            bl = false;
            n7 = UInt.constructor-impl(n7 - n6);
            bl = false;
            n5 = UInt.constructor-impl(n7 + n4);
        }
        return n5;
    }

    private static final long differenceModulo-sambcqE(long l2, long l3, long l4) {
        long l5;
        long l6 = l2;
        boolean bl = false;
        long l7 = UnsignedKt.ulongRemainder-eb3DHEI(l6, l4);
        long l8 = l3;
        boolean bl2 = false;
        l6 = UnsignedKt.ulongRemainder-eb3DHEI(l8, l4);
        l8 = l7;
        bl2 = false;
        if (UnsignedKt.ulongCompare(l8, l6) >= 0) {
            l8 = l7;
            bl2 = false;
            l5 = ULong.constructor-impl(l8 - l6);
        } else {
            l8 = l7;
            bl2 = false;
            l8 = ULong.constructor-impl(l8 - l6);
            bl2 = false;
            l5 = ULong.constructor-impl(l8 + l4);
        }
        return l5;
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    public static final int getProgressionLastElement-Nkh28Cs(int n2, int n3, int n4) {
        int n5;
        if (n4 > 0) {
            int n6 = n2;
            int n7 = 0;
            if (UnsignedKt.uintCompare(n6, n3) >= 0) {
                n5 = n3;
            } else {
                n6 = n3;
                n7 = n4;
                boolean bl = false;
                n7 = UProgressionUtilKt.differenceModulo-WZ9TVnA(n3, n2, UInt.constructor-impl(n7));
                bl = false;
                n5 = UInt.constructor-impl(n6 - n7);
            }
        } else if (n4 < 0) {
            int n8 = n2;
            int n9 = 0;
            if (UnsignedKt.uintCompare(n8, n3) <= 0) {
                n5 = n3;
            } else {
                n8 = n3;
                n9 = -n4;
                boolean bl = false;
                n9 = UProgressionUtilKt.differenceModulo-WZ9TVnA(n2, n3, UInt.constructor-impl(n9));
                bl = false;
                n5 = UInt.constructor-impl(n8 + n9);
            }
        } else {
            throw (Throwable)new IllegalArgumentException("Step is zero.");
        }
        return n5;
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    public static final long getProgressionLastElement-7ftBX0g(long l2, long l3, long l4) {
        long l5;
        if (l4 > 0L) {
            long l6 = l2;
            boolean bl = false;
            if (UnsignedKt.ulongCompare(l6, l3) >= 0) {
                l5 = l3;
            } else {
                l6 = l3;
                long l7 = l4;
                boolean bl2 = false;
                l7 = UProgressionUtilKt.differenceModulo-sambcqE(l3, l2, ULong.constructor-impl(l7));
                bl2 = false;
                l5 = ULong.constructor-impl(l6 - l7);
            }
        } else if (l4 < 0L) {
            long l8 = l2;
            boolean bl = false;
            if (UnsignedKt.ulongCompare(l8, l3) <= 0) {
                l5 = l3;
            } else {
                l8 = l3;
                long l9 = -l4;
                boolean bl3 = false;
                l9 = UProgressionUtilKt.differenceModulo-sambcqE(l2, l3, ULong.constructor-impl(l9));
                bl3 = false;
                l5 = ULong.constructor-impl(l8 + l9);
            }
        } else {
            throw (Throwable)new IllegalArgumentException("Step is zero.");
        }
        return l5;
    }
}

