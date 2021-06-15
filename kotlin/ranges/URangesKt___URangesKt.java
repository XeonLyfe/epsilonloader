/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.URandomKt;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.RangesKt;
import kotlin.ranges.UIntProgression;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongProgression;
import kotlin.ranges.ULongRange;
import kotlin.ranges.URangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\n\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0005H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\u001e\u0010\u0000\u001a\u00020\b*\u00020\b2\u0006\u0010\u0002\u001a\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a\u001e\u0010\u0000\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\r\u001a\u001e\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0004\u001a\u001e\u0010\u000e\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0007\u001a\u001e\u0010\u000e\u001a\u00020\b*\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\n\u001a\u001e\u0010\u000e\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\r\u001a&\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a&\u0010\u0014\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a$\u0010\u0014\u001a\u00020\u0005*\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a&\u0010\u0014\u001a\u00020\b*\u00020\b2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001e\u001a$\u0010\u0014\u001a\u00020\b*\u00020\b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u001aH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010 \u001a&\u0010\u0014\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b!\u0010\"\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u0001H\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010(\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\b\u0010)\u001a\u0004\u0018\u00010\u0005H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0002\b*\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\bH\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b+\u0010,\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u000bH\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010.\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u0001H\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b0\u00101\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u0005H\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b2\u00103\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\b\u0010)\u001a\u0004\u0018\u00010\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0002\b4\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u000bH\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b5\u00106\u001a\u001f\u00107\u001a\u000208*\u00020\u00012\u0006\u00109\u001a\u00020\u0001H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b:\u0010;\u001a\u001f\u00107\u001a\u000208*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b<\u0010=\u001a\u001f\u00107\u001a\u00020>*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b?\u0010@\u001a\u001f\u00107\u001a\u000208*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\bA\u0010B\u001a\u0015\u0010C\u001a\u00020\u0005*\u00020%H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010D\u001a\u001c\u0010C\u001a\u00020\u0005*\u00020%2\u0006\u0010C\u001a\u00020EH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010F\u001a\u0015\u0010C\u001a\u00020\b*\u00020/H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010G\u001a\u001c\u0010C\u001a\u00020\b*\u00020/2\u0006\u0010C\u001a\u00020EH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010H\u001a\u0012\u0010I\u001a\u0004\u0018\u00010\u0005*\u00020%H\u0087\b\u00f8\u0001\u0000\u001a\u0019\u0010I\u001a\u0004\u0018\u00010\u0005*\u00020%2\u0006\u0010C\u001a\u00020EH\u0007\u00f8\u0001\u0000\u001a\u0012\u0010I\u001a\u0004\u0018\u00010\b*\u00020/H\u0087\b\u00f8\u0001\u0000\u001a\u0019\u0010I\u001a\u0004\u0018\u00010\b*\u00020/2\u0006\u0010C\u001a\u00020EH\u0007\u00f8\u0001\u0000\u001a\f\u0010J\u001a\u000208*\u000208H\u0007\u001a\f\u0010J\u001a\u00020>*\u00020>H\u0007\u001a\u0015\u0010K\u001a\u000208*\u0002082\u0006\u0010K\u001a\u00020LH\u0087\u0004\u001a\u0015\u0010K\u001a\u00020>*\u00020>2\u0006\u0010K\u001a\u00020MH\u0087\u0004\u001a\u001f\u0010N\u001a\u00020%*\u00020\u00012\u0006\u00109\u001a\u00020\u0001H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\bO\u0010P\u001a\u001f\u0010N\u001a\u00020%*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\bQ\u0010R\u001a\u001f\u0010N\u001a\u00020/*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\bS\u0010T\u001a\u001f\u0010N\u001a\u00020%*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\bU\u0010V\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006W"}, d2={"coerceAtLeast", "Lkotlin/UByte;", "minimumValue", "coerceAtLeast-Kr8caGY", "(BB)B", "Lkotlin/UInt;", "coerceAtLeast-J1ME1BU", "(II)I", "Lkotlin/ULong;", "coerceAtLeast-eb3DHEI", "(JJ)J", "Lkotlin/UShort;", "coerceAtLeast-5PvTz6A", "(SS)S", "coerceAtMost", "maximumValue", "coerceAtMost-Kr8caGY", "coerceAtMost-J1ME1BU", "coerceAtMost-eb3DHEI", "coerceAtMost-5PvTz6A", "coerceIn", "coerceIn-b33U2AM", "(BBB)B", "coerceIn-WZ9TVnA", "(III)I", "range", "Lkotlin/ranges/ClosedRange;", "coerceIn-wuiCnnA", "(ILkotlin/ranges/ClosedRange;)I", "coerceIn-sambcqE", "(JJJ)J", "coerceIn-JPwROB0", "(JLkotlin/ranges/ClosedRange;)J", "coerceIn-VKSA0NQ", "(SSS)S", "contains", "", "Lkotlin/ranges/UIntRange;", "value", "contains-68kG9v0", "(Lkotlin/ranges/UIntRange;B)Z", "element", "contains-biwQdVI", "contains-fz5IDCE", "(Lkotlin/ranges/UIntRange;J)Z", "contains-ZsK3CEQ", "(Lkotlin/ranges/UIntRange;S)Z", "Lkotlin/ranges/ULongRange;", "contains-ULb-yJY", "(Lkotlin/ranges/ULongRange;B)Z", "contains-Gab390E", "(Lkotlin/ranges/ULongRange;I)Z", "contains-GYNo2lE", "contains-uhHAxoY", "(Lkotlin/ranges/ULongRange;S)Z", "downTo", "Lkotlin/ranges/UIntProgression;", "to", "downTo-Kr8caGY", "(BB)Lkotlin/ranges/UIntProgression;", "downTo-J1ME1BU", "(II)Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ULongProgression;", "downTo-eb3DHEI", "(JJ)Lkotlin/ranges/ULongProgression;", "downTo-5PvTz6A", "(SS)Lkotlin/ranges/UIntProgression;", "random", "(Lkotlin/ranges/UIntRange;)I", "Lkotlin/random/Random;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/ULongRange;)J", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)J", "randomOrNull", "reversed", "step", "", "", "until", "until-Kr8caGY", "(BB)Lkotlin/ranges/UIntRange;", "until-J1ME1BU", "(II)Lkotlin/ranges/UIntRange;", "until-eb3DHEI", "(JJ)Lkotlin/ranges/ULongRange;", "until-5PvTz6A", "(SS)Lkotlin/ranges/UIntRange;", "kotlin-stdlib"}, xs="kotlin/ranges/URangesKt")
class URangesKt___URangesKt {
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final int random(UIntRange uIntRange) {
        int n2 = 0;
        return URangesKt.random(uIntRange, (Random)Random.Default);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final long random(ULongRange uLongRange) {
        int n2 = 0;
        return URangesKt.random(uLongRange, (Random)Random.Default);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int random(@NotNull UIntRange uIntRange, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(uIntRange, "$this$random");
        Intrinsics.checkNotNullParameter(random, "random");
        try {
            return URandomKt.nextUInt(random, uIntRange);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw (Throwable)new NoSuchElementException(illegalArgumentException.getMessage());
        }
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long random(@NotNull ULongRange uLongRange, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(uLongRange, "$this$random");
        Intrinsics.checkNotNullParameter(random, "random");
        try {
            return URandomKt.nextULong(random, uLongRange);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw (Throwable)new NoSuchElementException(illegalArgumentException.getMessage());
        }
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final UInt randomOrNull(UIntRange uIntRange) {
        int n2 = 0;
        return URangesKt.randomOrNull(uIntRange, (Random)Random.Default);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final ULong randomOrNull(ULongRange uLongRange) {
        int n2 = 0;
        return URangesKt.randomOrNull(uLongRange, (Random)Random.Default);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UInt randomOrNull(@NotNull UIntRange uIntRange, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(uIntRange, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (uIntRange.isEmpty()) {
            return null;
        }
        return UInt.box-impl(URandomKt.nextUInt(random, uIntRange));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class})
    @Nullable
    public static final ULong randomOrNull(@NotNull ULongRange uLongRange, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(uLongRange, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (uLongRange.isEmpty()) {
            return null;
        }
        return ULong.box-impl(URandomKt.nextULong(random, uLongRange));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final boolean contains-biwQdVI(UIntRange uIntRange, UInt uInt) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(uIntRange, "$this$contains");
        return uInt != null && uIntRange.contains-WZ4Q5Ns(uInt.unbox-impl());
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final boolean contains-GYNo2lE(ULongRange uLongRange, ULong uLong) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(uLongRange, "$this$contains");
        return uLong != null && uLongRange.contains-VKZWuLQ(uLong.unbox-impl());
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final boolean contains-68kG9v0(@NotNull UIntRange uIntRange, byte by) {
        Intrinsics.checkNotNullParameter(uIntRange, "$this$contains");
        byte by2 = by;
        boolean bl = false;
        return uIntRange.contains-WZ4Q5Ns(UInt.constructor-impl(by2 & 0xFF));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final boolean contains-ULb-yJY(@NotNull ULongRange uLongRange, byte by) {
        Intrinsics.checkNotNullParameter(uLongRange, "$this$contains");
        byte by2 = by;
        boolean bl = false;
        return uLongRange.contains-VKZWuLQ(ULong.constructor-impl((long)by2 & 0xFFL));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final boolean contains-Gab390E(@NotNull ULongRange uLongRange, int n2) {
        Intrinsics.checkNotNullParameter(uLongRange, "$this$contains");
        int n3 = n2;
        boolean bl = false;
        return uLongRange.contains-VKZWuLQ(ULong.constructor-impl((long)n3 & 0xFFFFFFFFL));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final boolean contains-fz5IDCE(@NotNull UIntRange uIntRange, long l2) {
        Intrinsics.checkNotNullParameter(uIntRange, "$this$contains");
        long l3 = l2;
        int n2 = 32;
        boolean bl = false;
        if (ULong.constructor-impl(l3 >>> n2) != 0L) return false;
        l3 = l2;
        n2 = 0;
        long l4 = l3;
        boolean bl2 = false;
        if (!uIntRange.contains-WZ4Q5Ns(UInt.constructor-impl((int)l4))) return false;
        return true;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final boolean contains-ZsK3CEQ(@NotNull UIntRange uIntRange, short s2) {
        Intrinsics.checkNotNullParameter(uIntRange, "$this$contains");
        short s3 = s2;
        boolean bl = false;
        return uIntRange.contains-WZ4Q5Ns(UInt.constructor-impl(s3 & 0xFFFF));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final boolean contains-uhHAxoY(@NotNull ULongRange uLongRange, short s2) {
        Intrinsics.checkNotNullParameter(uLongRange, "$this$contains");
        short s3 = s2;
        boolean bl = false;
        return uLongRange.contains-VKZWuLQ(ULong.constructor-impl((long)s3 & 0xFFFFL));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final UIntProgression downTo-Kr8caGY(byte by, byte by2) {
        byte by3 = by;
        boolean bl = false;
        int n2 = UInt.constructor-impl(by3 & 0xFF);
        by3 = by2;
        bl = false;
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs(n2, UInt.constructor-impl(by3 & 0xFF), -1);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final UIntProgression downTo-J1ME1BU(int n2, int n3) {
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs(n2, n3, -1);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final ULongProgression downTo-eb3DHEI(long l2, long l3) {
        return ULongProgression.Companion.fromClosedRange-7ftBX0g(l2, l3, -1L);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final UIntProgression downTo-5PvTz6A(short s2, short s3) {
        short s4 = s2;
        boolean bl = false;
        int n2 = UInt.constructor-impl(s4 & 0xFFFF);
        s4 = s3;
        bl = false;
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs(n2, UInt.constructor-impl(s4 & 0xFFFF), -1);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final UIntProgression reversed(@NotNull UIntProgression uIntProgression) {
        Intrinsics.checkNotNullParameter(uIntProgression, "$this$reversed");
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs(uIntProgression.getLast-pVg5ArA(), uIntProgression.getFirst-pVg5ArA(), -uIntProgression.getStep());
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final ULongProgression reversed(@NotNull ULongProgression uLongProgression) {
        Intrinsics.checkNotNullParameter(uLongProgression, "$this$reversed");
        return ULongProgression.Companion.fromClosedRange-7ftBX0g(uLongProgression.getLast-s-VKNKU(), uLongProgression.getFirst-s-VKNKU(), -uLongProgression.getStep());
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final UIntProgression step(@NotNull UIntProgression uIntProgression, int n2) {
        Intrinsics.checkNotNullParameter(uIntProgression, "$this$step");
        RangesKt.checkStepIsPositive(n2 > 0, n2);
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs(uIntProgression.getFirst-pVg5ArA(), uIntProgression.getLast-pVg5ArA(), uIntProgression.getStep() > 0 ? n2 : -n2);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final ULongProgression step(@NotNull ULongProgression uLongProgression, long l2) {
        Intrinsics.checkNotNullParameter(uLongProgression, "$this$step");
        RangesKt.checkStepIsPositive(l2 > 0L, l2);
        return ULongProgression.Companion.fromClosedRange-7ftBX0g(uLongProgression.getFirst-s-VKNKU(), uLongProgression.getLast-s-VKNKU(), uLongProgression.getStep() > 0L ? l2 : -l2);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final UIntRange until-Kr8caGY(byte n2, byte n3) {
        int n4 = n3;
        int n5 = 0;
        int n6 = 0;
        int n7 = n4;
        int n8 = 0;
        int n9 = n7 & 0xFF;
        n7 = n5;
        n8 = 0;
        if (Intrinsics.compare(n9, n7 & 0xFF) <= 0) {
            return UIntRange.Companion.getEMPTY();
        }
        n4 = n2;
        n5 = 0;
        n4 = UInt.constructor-impl(n4 & 0xFF);
        n5 = n3;
        n6 = 1;
        n7 = 0;
        n8 = n5;
        boolean bl = false;
        n8 = UInt.constructor-impl(n8 & 0xFF);
        bl = false;
        n5 = UInt.constructor-impl(n8 - n6);
        n6 = 0;
        n6 = 0;
        return new UIntRange(n4, n5, null);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final UIntRange until-J1ME1BU(int n2, int n3) {
        int n4 = n3;
        int n5 = 0;
        int n6 = 0;
        if (UnsignedKt.uintCompare(n4, n5) <= 0) {
            return UIntRange.Companion.getEMPTY();
        }
        n4 = n2;
        n5 = n3;
        n6 = 1;
        boolean bl = false;
        n5 = UInt.constructor-impl(n5 - n6);
        n6 = 0;
        n6 = 0;
        return new UIntRange(n4, n5, null);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final ULongRange until-eb3DHEI(long l2, long l3) {
        long l4 = l3;
        long l5 = 0L;
        int n2 = 0;
        if (UnsignedKt.ulongCompare(l4, l5) <= 0) {
            return ULongRange.Companion.getEMPTY();
        }
        l4 = l2;
        l5 = l3;
        n2 = 1;
        boolean bl = false;
        long l6 = l5;
        int n3 = n2;
        boolean bl2 = false;
        long l7 = ULong.constructor-impl((long)n3 & 0xFFFFFFFFL);
        boolean bl3 = false;
        l5 = ULong.constructor-impl(l6 - l7);
        n2 = 0;
        n2 = 0;
        return new ULongRange(l4, l5, null);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final UIntRange until-5PvTz6A(short n2, short n3) {
        int n4 = n3;
        int n5 = 0;
        int n6 = 0;
        int n7 = n4;
        int n8 = 0;
        int n9 = n7 & 0xFFFF;
        n7 = n5;
        n8 = 0;
        if (Intrinsics.compare(n9, n7 & 0xFFFF) <= 0) {
            return UIntRange.Companion.getEMPTY();
        }
        n4 = n2;
        n5 = 0;
        n4 = UInt.constructor-impl(n4 & 0xFFFF);
        n5 = n3;
        n6 = 1;
        n7 = 0;
        n8 = n5;
        boolean bl = false;
        n8 = UInt.constructor-impl(n8 & 0xFFFF);
        bl = false;
        n5 = UInt.constructor-impl(n8 - n6);
        n6 = 0;
        n6 = 0;
        return new UIntRange(n4, n5, null);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int coerceAtLeast-J1ME1BU(int n2, int n3) {
        int n4 = n2;
        boolean bl = false;
        return UnsignedKt.uintCompare(n4, n3) < 0 ? n3 : n2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long coerceAtLeast-eb3DHEI(long l2, long l3) {
        long l4 = l2;
        boolean bl = false;
        return UnsignedKt.ulongCompare(l4, l3) < 0 ? l3 : l2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final byte coerceAtLeast-Kr8caGY(byte by, byte by2) {
        byte by3 = by;
        boolean bl = false;
        byte by4 = by3;
        boolean bl2 = false;
        int n2 = by4 & 0xFF;
        by4 = by2;
        bl2 = false;
        return Intrinsics.compare(n2, by4 & 0xFF) < 0 ? by2 : by;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final short coerceAtLeast-5PvTz6A(short s2, short s3) {
        short s4 = s2;
        boolean bl = false;
        short s5 = s4;
        boolean bl2 = false;
        int n2 = s5 & 0xFFFF;
        s5 = s3;
        bl2 = false;
        return Intrinsics.compare(n2, s5 & 0xFFFF) < 0 ? s3 : s2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int coerceAtMost-J1ME1BU(int n2, int n3) {
        int n4 = n2;
        boolean bl = false;
        return UnsignedKt.uintCompare(n4, n3) > 0 ? n3 : n2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long coerceAtMost-eb3DHEI(long l2, long l3) {
        long l4 = l2;
        boolean bl = false;
        return UnsignedKt.ulongCompare(l4, l3) > 0 ? l3 : l2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final byte coerceAtMost-Kr8caGY(byte by, byte by2) {
        byte by3 = by;
        boolean bl = false;
        byte by4 = by3;
        boolean bl2 = false;
        int n2 = by4 & 0xFF;
        by4 = by2;
        bl2 = false;
        return Intrinsics.compare(n2, by4 & 0xFF) > 0 ? by2 : by;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final short coerceAtMost-5PvTz6A(short s2, short s3) {
        short s4 = s2;
        boolean bl = false;
        short s5 = s4;
        boolean bl2 = false;
        int n2 = s5 & 0xFFFF;
        s5 = s3;
        bl2 = false;
        return Intrinsics.compare(n2, s5 & 0xFFFF) > 0 ? s3 : s2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int coerceIn-WZ9TVnA(int n2, int n3, int n4) {
        int n5 = n3;
        boolean bl = false;
        if (UnsignedKt.uintCompare(n5, n4) > 0) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UInt.toString-impl(n4) + " is less than minimum " + UInt.toString-impl(n3) + '.');
        }
        n5 = n2;
        bl = false;
        if (UnsignedKt.uintCompare(n5, n3) < 0) {
            return n3;
        }
        n5 = n2;
        bl = false;
        if (UnsignedKt.uintCompare(n5, n4) > 0) {
            return n4;
        }
        return n2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long coerceIn-sambcqE(long l2, long l3, long l4) {
        long l5 = l3;
        boolean bl = false;
        if (UnsignedKt.ulongCompare(l5, l4) > 0) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + ULong.toString-impl(l4) + " is less than minimum " + ULong.toString-impl(l3) + '.');
        }
        l5 = l2;
        bl = false;
        if (UnsignedKt.ulongCompare(l5, l3) < 0) {
            return l3;
        }
        l5 = l2;
        bl = false;
        if (UnsignedKt.ulongCompare(l5, l4) > 0) {
            return l4;
        }
        return l2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final byte coerceIn-b33U2AM(byte by, byte by2, byte by3) {
        byte by4 = by2;
        boolean bl = false;
        byte by5 = by4;
        boolean bl2 = false;
        int n2 = by5 & 0xFF;
        by5 = by3;
        bl2 = false;
        if (Intrinsics.compare(n2, by5 & 0xFF) > 0) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UByte.toString-impl(by3) + " is less than minimum " + UByte.toString-impl(by2) + '.');
        }
        by4 = by;
        bl = false;
        by5 = by4;
        bl2 = false;
        int n3 = by5 & 0xFF;
        by5 = by2;
        bl2 = false;
        if (Intrinsics.compare(n3, by5 & 0xFF) < 0) {
            return by2;
        }
        by4 = by;
        bl = false;
        by5 = by4;
        bl2 = false;
        int n4 = by5 & 0xFF;
        by5 = by3;
        bl2 = false;
        if (Intrinsics.compare(n4, by5 & 0xFF) > 0) {
            return by3;
        }
        return by;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final short coerceIn-VKSA0NQ(short s2, short s3, short s4) {
        short s5 = s3;
        boolean bl = false;
        short s6 = s5;
        boolean bl2 = false;
        int n2 = s6 & 0xFFFF;
        s6 = s4;
        bl2 = false;
        if (Intrinsics.compare(n2, s6 & 0xFFFF) > 0) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UShort.toString-impl(s4) + " is less than minimum " + UShort.toString-impl(s3) + '.');
        }
        s5 = s2;
        bl = false;
        s6 = s5;
        bl2 = false;
        int n3 = s6 & 0xFFFF;
        s6 = s3;
        bl2 = false;
        if (Intrinsics.compare(n3, s6 & 0xFFFF) < 0) {
            return s3;
        }
        s5 = s2;
        bl = false;
        s6 = s5;
        bl2 = false;
        int n4 = s6 & 0xFFFF;
        s6 = s4;
        bl2 = false;
        if (Intrinsics.compare(n4, s6 & 0xFFFF) > 0) {
            return s4;
        }
        return s2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int coerceIn-wuiCnnA(int n2, @NotNull ClosedRange<UInt> closedRange) {
        int n3;
        Intrinsics.checkNotNullParameter(closedRange, "range");
        if (closedRange instanceof ClosedFloatingPointRange) {
            return RangesKt.coerceIn(UInt.box-impl(n2), (ClosedFloatingPointRange)closedRange).unbox-impl();
        }
        if (closedRange.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + '.');
        }
        int n4 = n2;
        int n5 = closedRange.getStart().unbox-impl();
        boolean bl = false;
        if (UnsignedKt.uintCompare(n4, n5) < 0) {
            n3 = closedRange.getStart().unbox-impl();
        } else {
            n4 = n2;
            n5 = closedRange.getEndInclusive().unbox-impl();
            bl = false;
            n3 = UnsignedKt.uintCompare(n4, n5) > 0 ? closedRange.getEndInclusive().unbox-impl() : n2;
        }
        return n3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long coerceIn-JPwROB0(long l2, @NotNull ClosedRange<ULong> closedRange) {
        long l3;
        Intrinsics.checkNotNullParameter(closedRange, "range");
        if (closedRange instanceof ClosedFloatingPointRange) {
            return RangesKt.coerceIn(ULong.box-impl(l2), (ClosedFloatingPointRange)closedRange).unbox-impl();
        }
        if (closedRange.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + '.');
        }
        long l4 = l2;
        long l5 = closedRange.getStart().unbox-impl();
        boolean bl = false;
        if (UnsignedKt.ulongCompare(l4, l5) < 0) {
            l3 = closedRange.getStart().unbox-impl();
        } else {
            l4 = l2;
            l5 = closedRange.getEndInclusive().unbox-impl();
            bl = false;
            l3 = UnsignedKt.ulongCompare(l4, l5) > 0 ? closedRange.getEndInclusive().unbox-impl() : l2;
        }
        return l3;
    }
}

