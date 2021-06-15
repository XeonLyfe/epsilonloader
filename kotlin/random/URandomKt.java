/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.random;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a\u001c\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010\u001a\u001e\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a2\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0017\u001a\u0014\u0010\u0018\u001a\u00020\u0003*\u00020\rH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019\u001a\u001e\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a&\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u001d\u001a\u001c\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 \u001a\u0014\u0010!\u001a\u00020\b*\u00020\rH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"\u001a\u001e\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0004\u001a\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010$\u001a&\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010&\u001a\u001c\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u001e\u001a\u00020'H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010(\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006)"}, d2={"checkUIntRangeBounds", "", "from", "Lkotlin/UInt;", "until", "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkULongRangeBounds", "Lkotlin/ULong;", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "nextUBytes", "Lkotlin/UByteArray;", "Lkotlin/random/Random;", "size", "", "(Lkotlin/random/Random;I)[B", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "nextUInt", "(Lkotlin/random/Random;)I", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", "range", "Lkotlin/ranges/UIntRange;", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "nextULong", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "kotlin-stdlib"})
public final class URandomKt {
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int nextUInt(@NotNull Random random) {
        Intrinsics.checkNotNullParameter(random, "$this$nextUInt");
        int n2 = random.nextInt();
        boolean bl = false;
        return UInt.constructor-impl(n2);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int nextUInt-qCasIEU(@NotNull Random random, int n2) {
        Intrinsics.checkNotNullParameter(random, "$this$nextUInt");
        return URandomKt.nextUInt-a8DCA5k(random, 0, n2);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int nextUInt-a8DCA5k(@NotNull Random random, int n2, int n3) {
        Intrinsics.checkNotNullParameter(random, "$this$nextUInt");
        URandomKt.checkUIntRangeBounds-J1ME1BU(n2, n3);
        int n4 = n2;
        int n5 = 0;
        int n6 = n4 ^ Integer.MIN_VALUE;
        n5 = n3;
        int n7 = 0;
        n4 = n5 ^ Integer.MIN_VALUE;
        n7 = n5 = random.nextInt(n6, n4) ^ Integer.MIN_VALUE;
        boolean bl = false;
        return UInt.constructor-impl(n7);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int nextUInt(@NotNull Random random, @NotNull UIntRange uIntRange) {
        int n2;
        Intrinsics.checkNotNullParameter(random, "$this$nextUInt");
        Intrinsics.checkNotNullParameter(uIntRange, "range");
        if (uIntRange.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Cannot get random in empty range: " + uIntRange);
        }
        int n3 = uIntRange.getLast-pVg5ArA();
        int n4 = -1;
        boolean bl = false;
        if (UnsignedKt.uintCompare(n3, n4) < 0) {
            n3 = uIntRange.getLast-pVg5ArA();
            n4 = 1;
            bl = false;
            n2 = URandomKt.nextUInt-a8DCA5k(random, uIntRange.getFirst-pVg5ArA(), UInt.constructor-impl(n3 + n4));
        } else {
            n3 = uIntRange.getFirst-pVg5ArA();
            n4 = 0;
            bl = false;
            if (UnsignedKt.uintCompare(n3, n4) > 0) {
                n3 = uIntRange.getFirst-pVg5ArA();
                n4 = 1;
                bl = false;
                n3 = URandomKt.nextUInt-a8DCA5k(random, UInt.constructor-impl(n3 - n4), uIntRange.getLast-pVg5ArA());
                n4 = 1;
                bl = false;
                n2 = UInt.constructor-impl(n3 + n4);
            } else {
                n2 = URandomKt.nextUInt(random);
            }
        }
        return n2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long nextULong(@NotNull Random random) {
        Intrinsics.checkNotNullParameter(random, "$this$nextULong");
        long l2 = random.nextLong();
        boolean bl = false;
        return ULong.constructor-impl(l2);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long nextULong-V1Xi4fY(@NotNull Random random, long l2) {
        Intrinsics.checkNotNullParameter(random, "$this$nextULong");
        return URandomKt.nextULong-jmpaW-c(random, 0L, l2);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long nextULong-jmpaW-c(@NotNull Random random, long l2, long l3) {
        Intrinsics.checkNotNullParameter(random, "$this$nextULong");
        URandomKt.checkULongRangeBounds-eb3DHEI(l2, l3);
        long l4 = l2;
        boolean bl = false;
        long l5 = l4 ^ Long.MIN_VALUE;
        long l6 = l3;
        boolean bl2 = false;
        l4 = l6 ^ Long.MIN_VALUE;
        long l7 = l6 = random.nextLong(l5, l4) ^ Long.MIN_VALUE;
        boolean bl3 = false;
        return ULong.constructor-impl(l7);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long nextULong(@NotNull Random random, @NotNull ULongRange uLongRange) {
        long l2;
        Intrinsics.checkNotNullParameter(random, "$this$nextULong");
        Intrinsics.checkNotNullParameter(uLongRange, "range");
        if (uLongRange.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Cannot get random in empty range: " + uLongRange);
        }
        long l3 = uLongRange.getLast-s-VKNKU();
        long l4 = -1L;
        boolean bl = false;
        if (UnsignedKt.ulongCompare(l3, l4) < 0) {
            l3 = uLongRange.getLast-s-VKNKU();
            int n2 = 1;
            boolean bl2 = false;
            long l5 = l3;
            int n3 = n2;
            boolean bl3 = false;
            long l6 = ULong.constructor-impl((long)n3 & 0xFFFFFFFFL);
            boolean bl4 = false;
            l2 = URandomKt.nextULong-jmpaW-c(random, uLongRange.getFirst-s-VKNKU(), ULong.constructor-impl(l5 + l6));
        } else {
            l3 = uLongRange.getFirst-s-VKNKU();
            l4 = 0L;
            bl = false;
            if (UnsignedKt.ulongCompare(l3, l4) > 0) {
                l3 = uLongRange.getFirst-s-VKNKU();
                int n4 = 1;
                boolean bl5 = false;
                long l7 = l3;
                int n5 = n4;
                boolean bl6 = false;
                long l8 = ULong.constructor-impl((long)n5 & 0xFFFFFFFFL);
                boolean bl7 = false;
                l3 = URandomKt.nextULong-jmpaW-c(random, ULong.constructor-impl(l7 - l8), uLongRange.getLast-s-VKNKU());
                n4 = 1;
                bl5 = false;
                l7 = l3;
                n5 = n4;
                bl6 = false;
                l8 = ULong.constructor-impl((long)n5 & 0xFFFFFFFFL);
                bl7 = false;
                l2 = ULong.constructor-impl(l7 + l8);
            } else {
                l2 = URandomKt.nextULong(random);
            }
        }
        return l2;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] nextUBytes-EVgfTAA(@NotNull Random random, @NotNull byte[] arrby) {
        Intrinsics.checkNotNullParameter(random, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(arrby, "array");
        byte[] arrby2 = arrby;
        boolean bl = false;
        random.nextBytes(arrby2);
        return arrby;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] nextUBytes(@NotNull Random random, int n2) {
        Intrinsics.checkNotNullParameter(random, "$this$nextUBytes");
        byte[] arrby = random.nextBytes(n2);
        boolean bl = false;
        return UByteArray.constructor-impl(arrby);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] nextUBytes-Wvrt4B4(@NotNull Random random, @NotNull byte[] arrby, int n2, int n3) {
        Intrinsics.checkNotNullParameter(random, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(arrby, "array");
        byte[] arrby2 = arrby;
        boolean bl = false;
        random.nextBytes(arrby2, n2, n3);
        return arrby;
    }

    public static /* synthetic */ byte[] nextUBytes-Wvrt4B4$default(Random random, byte[] arrby, int n2, int n3, int n4, Object object) {
        if ((n4 & 2) != 0) {
            n2 = 0;
        }
        if ((n4 & 4) != 0) {
            n3 = UByteArray.getSize-impl(arrby);
        }
        return URandomKt.nextUBytes-Wvrt4B4(random, arrby, n2, n3);
    }

    public static final void checkUIntRangeBounds-J1ME1BU(int n2, int n3) {
        int n4 = n3;
        boolean bl = false;
        n4 = UnsignedKt.uintCompare(n4, n2) > 0 ? 1 : 0;
        bl = false;
        boolean bl2 = false;
        if (n4 == 0) {
            boolean bl3 = false;
            String string = RandomKt.boundsErrorMessage(UInt.box-impl(n2), UInt.box-impl(n3));
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
    }

    public static final void checkULongRangeBounds-eb3DHEI(long l2, long l3) {
        long l4 = l3;
        boolean bl = false;
        boolean bl2 = UnsignedKt.ulongCompare(l4, l2) > 0;
        boolean bl3 = false;
        bl = false;
        if (!bl2) {
            boolean bl4 = false;
            String string = RandomKt.boundsErrorMessage(ULong.box-impl(l2), ULong.box-impl(l3));
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
    }
}

