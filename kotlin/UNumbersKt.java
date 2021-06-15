/*
 * Decompiled with CFR 0.150.
 */
package kotlin;

import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.NumbersKt;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000&\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b)\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u000bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\r\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0004\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0007\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\n\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u000bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\r\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0004\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0007\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\n\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u000bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\r\u001a\u001f\u0010\u0018\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a\u001f\u0010\u0018\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u001d\u001a\u001f\u0010\u0018\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001f\u001a\u001f\u0010\u0018\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010!\u001a\u001f\u0010\"\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010\u001b\u001a\u001f\u0010\"\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b$\u0010\u001d\u001a\u001f\u0010\"\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010\u001f\u001a\u001f\u0010\"\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b&\u0010!\u001a\u0017\u0010'\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b(\u0010)\u001a\u0017\u0010'\u001a\u00020\u0005*\u00020\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010\u0007\u001a\u0017\u0010'\u001a\u00020\b*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b+\u0010,\u001a\u0017\u0010'\u001a\u00020\u000b*\u00020\u000bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010.\u001a\u0017\u0010/\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b0\u0010)\u001a\u0017\u0010/\u001a\u00020\u0005*\u00020\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b1\u0010\u0007\u001a\u0017\u0010/\u001a\u00020\b*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b2\u0010,\u001a\u0017\u0010/\u001a\u00020\u000b*\u00020\u000bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b3\u0010.\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00064"}, d2={"countLeadingZeroBits", "", "Lkotlin/UByte;", "countLeadingZeroBits-7apg3OU", "(B)I", "Lkotlin/UInt;", "countLeadingZeroBits-WZ4Q5Ns", "(I)I", "Lkotlin/ULong;", "countLeadingZeroBits-VKZWuLQ", "(J)I", "Lkotlin/UShort;", "countLeadingZeroBits-xj2QHRw", "(S)I", "countOneBits", "countOneBits-7apg3OU", "countOneBits-WZ4Q5Ns", "countOneBits-VKZWuLQ", "countOneBits-xj2QHRw", "countTrailingZeroBits", "countTrailingZeroBits-7apg3OU", "countTrailingZeroBits-WZ4Q5Ns", "countTrailingZeroBits-VKZWuLQ", "countTrailingZeroBits-xj2QHRw", "rotateLeft", "bitCount", "rotateLeft-LxnNnR4", "(BI)B", "rotateLeft-V7xB4Y4", "(II)I", "rotateLeft-JSWoG40", "(JI)J", "rotateLeft-olVBNx4", "(SI)S", "rotateRight", "rotateRight-LxnNnR4", "rotateRight-V7xB4Y4", "rotateRight-JSWoG40", "rotateRight-olVBNx4", "takeHighestOneBit", "takeHighestOneBit-7apg3OU", "(B)B", "takeHighestOneBit-WZ4Q5Ns", "takeHighestOneBit-VKZWuLQ", "(J)J", "takeHighestOneBit-xj2QHRw", "(S)S", "takeLowestOneBit", "takeLowestOneBit-7apg3OU", "takeLowestOneBit-WZ4Q5Ns", "takeLowestOneBit-VKZWuLQ", "takeLowestOneBit-xj2QHRw", "kotlin-stdlib"})
@JvmName(name="UNumbersKt")
public final class UNumbersKt {
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countOneBits-WZ4Q5Ns(int n2) {
        int n3 = 0;
        int n4 = n2;
        boolean bl = false;
        bl = false;
        return Integer.bitCount(n4);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countLeadingZeroBits-WZ4Q5Ns(int n2) {
        int n3 = 0;
        int n4 = n2;
        boolean bl = false;
        bl = false;
        return Integer.numberOfLeadingZeros(n4);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countTrailingZeroBits-WZ4Q5Ns(int n2) {
        int n3 = 0;
        int n4 = n2;
        boolean bl = false;
        bl = false;
        return Integer.numberOfTrailingZeros(n4);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int takeHighestOneBit-WZ4Q5Ns(int n2) {
        int n3 = 0;
        int n4 = n2;
        boolean bl = false;
        bl = false;
        n4 = Integer.highestOneBit(n4);
        bl = false;
        return UInt.constructor-impl(n4);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int takeLowestOneBit-WZ4Q5Ns(int n2) {
        int n3 = 0;
        int n4 = n2;
        boolean bl = false;
        bl = false;
        n4 = Integer.lowestOneBit(n4);
        bl = false;
        return UInt.constructor-impl(n4);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final int rotateLeft-V7xB4Y4(int n2, int n3) {
        int n4 = 0;
        int n5 = n2;
        boolean bl = false;
        bl = false;
        n5 = Integer.rotateLeft(n5, n3);
        bl = false;
        return UInt.constructor-impl(n5);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final int rotateRight-V7xB4Y4(int n2, int n3) {
        int n4 = 0;
        int n5 = n2;
        boolean bl = false;
        bl = false;
        n5 = Integer.rotateRight(n5, n3);
        bl = false;
        return UInt.constructor-impl(n5);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countOneBits-VKZWuLQ(long l2) {
        int n2 = 0;
        long l3 = l2;
        boolean bl = false;
        bl = false;
        return Long.bitCount(l3);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countLeadingZeroBits-VKZWuLQ(long l2) {
        int n2 = 0;
        long l3 = l2;
        boolean bl = false;
        bl = false;
        return Long.numberOfLeadingZeros(l3);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countTrailingZeroBits-VKZWuLQ(long l2) {
        int n2 = 0;
        long l3 = l2;
        boolean bl = false;
        bl = false;
        return Long.numberOfTrailingZeros(l3);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final long takeHighestOneBit-VKZWuLQ(long l2) {
        int n2 = 0;
        long l3 = l2;
        boolean bl = false;
        bl = false;
        l3 = Long.highestOneBit(l3);
        bl = false;
        return ULong.constructor-impl(l3);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final long takeLowestOneBit-VKZWuLQ(long l2) {
        int n2 = 0;
        long l3 = l2;
        boolean bl = false;
        bl = false;
        l3 = Long.lowestOneBit(l3);
        bl = false;
        return ULong.constructor-impl(l3);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final long rotateLeft-JSWoG40(long l2, int n2) {
        int n3 = 0;
        long l3 = l2;
        boolean bl = false;
        bl = false;
        l3 = Long.rotateLeft(l3, n2);
        bl = false;
        return ULong.constructor-impl(l3);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final long rotateRight-JSWoG40(long l2, int n2) {
        int n3 = 0;
        long l3 = l2;
        boolean bl = false;
        bl = false;
        l3 = Long.rotateRight(l3, n2);
        bl = false;
        return ULong.constructor-impl(l3);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countOneBits-7apg3OU(byte n2) {
        int n3 = 0;
        int n4 = n2;
        boolean bl = false;
        n4 = UInt.constructor-impl(n4 & 0xFF);
        bl = false;
        int n5 = n4;
        boolean bl2 = false;
        bl2 = false;
        return Integer.bitCount(n5);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countLeadingZeroBits-7apg3OU(byte by) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        bl = false;
        int n3 = by2 & 0xFF;
        boolean bl2 = false;
        return Integer.numberOfLeadingZeros(n3) - 24;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countTrailingZeroBits-7apg3OU(byte by) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        bl = false;
        int n3 = by2 | 0x100;
        boolean bl2 = false;
        return Integer.numberOfTrailingZeros(n3);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final byte takeHighestOneBit-7apg3OU(byte n2) {
        int n3 = 0;
        int n4 = n2;
        boolean bl = false;
        n4 &= 0xFF;
        bl = false;
        n4 = Integer.highestOneBit(n4);
        bl = false;
        return UByte.constructor-impl((byte)n4);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final byte takeLowestOneBit-7apg3OU(byte n2) {
        int n3 = 0;
        int n4 = n2;
        boolean bl = false;
        n4 &= 0xFF;
        bl = false;
        n4 = Integer.lowestOneBit(n4);
        bl = false;
        return UByte.constructor-impl((byte)n4);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final byte rotateLeft-LxnNnR4(byte by, int n2) {
        int n3 = 0;
        byte by2 = by;
        boolean bl = false;
        by2 = NumbersKt.rotateLeft(by2, n2);
        bl = false;
        return UByte.constructor-impl(by2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final byte rotateRight-LxnNnR4(byte by, int n2) {
        int n3 = 0;
        byte by2 = by;
        boolean bl = false;
        by2 = NumbersKt.rotateRight(by2, n2);
        bl = false;
        return UByte.constructor-impl(by2);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countOneBits-xj2QHRw(short n2) {
        int n3 = 0;
        int n4 = n2;
        boolean bl = false;
        n4 = UInt.constructor-impl(n4 & 0xFFFF);
        bl = false;
        int n5 = n4;
        boolean bl2 = false;
        bl2 = false;
        return Integer.bitCount(n5);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countLeadingZeroBits-xj2QHRw(short s2) {
        int n2 = 0;
        short s3 = s2;
        boolean bl = false;
        bl = false;
        int n3 = s3 & 0xFFFF;
        boolean bl2 = false;
        return Integer.numberOfLeadingZeros(n3) - 16;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countTrailingZeroBits-xj2QHRw(short s2) {
        int n2 = 0;
        short s3 = s2;
        boolean bl = false;
        bl = false;
        int n3 = s3 | 0x10000;
        boolean bl2 = false;
        return Integer.numberOfTrailingZeros(n3);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final short takeHighestOneBit-xj2QHRw(short n2) {
        int n3 = 0;
        int n4 = n2;
        boolean bl = false;
        n4 &= 0xFFFF;
        bl = false;
        n4 = Integer.highestOneBit(n4);
        bl = false;
        return UShort.constructor-impl((short)n4);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    private static final short takeLowestOneBit-xj2QHRw(short n2) {
        int n3 = 0;
        int n4 = n2;
        boolean bl = false;
        n4 &= 0xFFFF;
        bl = false;
        n4 = Integer.lowestOneBit(n4);
        bl = false;
        return UShort.constructor-impl((short)n4);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final short rotateLeft-olVBNx4(short s2, int n2) {
        int n3 = 0;
        short s3 = s2;
        boolean bl = false;
        s3 = NumbersKt.rotateLeft(s3, n2);
        bl = false;
        return UShort.constructor-impl(s3);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final short rotateRight-olVBNx4(short s2, int n2) {
        int n3 = 0;
        short s3 = s2;
        boolean bl = false;
        s3 = NumbersKt.rotateRight(s3, n2);
        bl = false;
        return UShort.constructor-impl(s3);
    }
}

