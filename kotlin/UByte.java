/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@JvmInline
@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001tB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010\u0018J\u001a\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#H\u00d6\u0003\u00a2\u0006\u0004\b$\u0010%J\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010\u000fJ\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b(\u0010\u0012J\u001b\u0010&\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b)\u0010\u001fJ\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010\u0018J\u0010\u0010+\u001a\u00020\rH\u00d6\u0001\u00a2\u0006\u0004\b,\u0010-J\u0016\u0010.\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b/\u0010\u0005J\u0016\u00100\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b1\u0010\u0005J\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b3\u0010\u000fJ\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b4\u0010\u0012J\u001b\u00102\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b5\u0010\u001fJ\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b6\u0010\u0018J\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b8\u0010\u000bJ\u001b\u00107\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b9\u0010\u0012J\u001b\u00107\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b:\u0010\u001fJ\u001b\u00107\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b;\u0010<J\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b>\u0010\u000bJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b@\u0010\u000fJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bA\u0010\u0012J\u001b\u0010?\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bB\u0010\u001fJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bC\u0010\u0018J\u001b\u0010D\u001a\u00020E2\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bF\u0010GJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bI\u0010\u000fJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bJ\u0010\u0012J\u001b\u0010H\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bK\u0010\u001fJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bL\u0010\u0018J\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bN\u0010\u000fJ\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bO\u0010\u0012J\u001b\u0010M\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bP\u0010\u001fJ\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bQ\u0010\u0018J\u0010\u0010R\u001a\u00020\u0003H\u0087\b\u00a2\u0006\u0004\bS\u0010\u0005J\u0010\u0010T\u001a\u00020UH\u0087\b\u00a2\u0006\u0004\bV\u0010WJ\u0010\u0010X\u001a\u00020YH\u0087\b\u00a2\u0006\u0004\bZ\u0010[J\u0010\u0010\\\u001a\u00020\rH\u0087\b\u00a2\u0006\u0004\b]\u0010-J\u0010\u0010^\u001a\u00020_H\u0087\b\u00a2\u0006\u0004\b`\u0010aJ\u0010\u0010b\u001a\u00020cH\u0087\b\u00a2\u0006\u0004\bd\u0010eJ\u000f\u0010f\u001a\u00020gH\u0016\u00a2\u0006\u0004\bh\u0010iJ\u0016\u0010j\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bk\u0010\u0005J\u0016\u0010l\u001a\u00020\u0010H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bm\u0010-J\u0016\u0010n\u001a\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bo\u0010aJ\u0016\u0010p\u001a\u00020\u0016H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bq\u0010eJ\u001b\u0010r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\bs\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u00f8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006u"}, d2={"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "getData$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-w2LRezQ", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(BLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(B)I", "inc", "inc-w2LRezQ", "inv", "inv-w2LRezQ", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(BS)S", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"})
@SinceKotlin(version="1.5")
@WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
public final class UByte
implements Comparable<UByte> {
    private final byte data;
    public static final byte MIN_VALUE = 0;
    public static final byte MAX_VALUE = -1;
    public static final int SIZE_BYTES = 1;
    public static final int SIZE_BITS = 8;
    @NotNull
    public static final Companion Companion = new Companion(null);

    @InlineOnly
    private int compareTo-7apg3OU(byte by) {
        return UByte.compareTo-7apg3OU(this.data, by);
    }

    @NotNull
    public String toString() {
        return UByte.toString-impl(this.data);
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    @PublishedApi
    private /* synthetic */ UByte(byte by) {
        this.data = by;
    }

    @InlineOnly
    private static int compareTo-7apg3OU(byte by, byte by2) {
        int n2 = 0;
        byte by3 = by;
        boolean bl = false;
        int n3 = by3 & 0xFF;
        by3 = by2;
        bl = false;
        return Intrinsics.compare(n3, by3 & 0xFF);
    }

    @InlineOnly
    private static final int compareTo-xj2QHRw(byte s2, short s3) {
        int n2 = 0;
        short s4 = s2;
        boolean bl = false;
        int n3 = s4 & 0xFF;
        s4 = s3;
        bl = false;
        return Intrinsics.compare(n3, s4 & 0xFFFF);
    }

    @InlineOnly
    private static final int compareTo-WZ4Q5Ns(byte n2, int n3) {
        int n4 = 0;
        int n5 = n2;
        boolean bl = false;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        bl = false;
        return UnsignedKt.uintCompare(n5, n3);
    }

    @InlineOnly
    private static final int compareTo-VKZWuLQ(byte by, long l2) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        long l3 = ULong.constructor-impl((long)by2 & 0xFFL);
        boolean bl2 = false;
        return UnsignedKt.ulongCompare(l3, l2);
    }

    @InlineOnly
    private static final int plus-7apg3OU(byte n2, byte n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n5 + n6);
    }

    @InlineOnly
    private static final int plus-xj2QHRw(byte n2, short n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFFFF);
        bl = false;
        return UInt.constructor-impl(n5 + n6);
    }

    @InlineOnly
    private static final int plus-WZ4Q5Ns(byte n2, int n3) {
        int n4 = 0;
        int n5 = n2;
        boolean bl = false;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n5 + n3);
    }

    @InlineOnly
    private static final long plus-VKZWuLQ(byte by, long l2) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        long l3 = ULong.constructor-impl((long)by2 & 0xFFL);
        boolean bl2 = false;
        return ULong.constructor-impl(l3 + l2);
    }

    @InlineOnly
    private static final int minus-7apg3OU(byte n2, byte n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n5 - n6);
    }

    @InlineOnly
    private static final int minus-xj2QHRw(byte n2, short n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFFFF);
        bl = false;
        return UInt.constructor-impl(n5 - n6);
    }

    @InlineOnly
    private static final int minus-WZ4Q5Ns(byte n2, int n3) {
        int n4 = 0;
        int n5 = n2;
        boolean bl = false;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n5 - n3);
    }

    @InlineOnly
    private static final long minus-VKZWuLQ(byte by, long l2) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        long l3 = ULong.constructor-impl((long)by2 & 0xFFL);
        boolean bl2 = false;
        return ULong.constructor-impl(l3 - l2);
    }

    @InlineOnly
    private static final int times-7apg3OU(byte n2, byte n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n5 * n6);
    }

    @InlineOnly
    private static final int times-xj2QHRw(byte n2, short n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFFFF);
        bl = false;
        return UInt.constructor-impl(n5 * n6);
    }

    @InlineOnly
    private static final int times-WZ4Q5Ns(byte n2, int n3) {
        int n4 = 0;
        int n5 = n2;
        boolean bl = false;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n5 * n3);
    }

    @InlineOnly
    private static final long times-VKZWuLQ(byte by, long l2) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        long l3 = ULong.constructor-impl((long)by2 & 0xFFL);
        boolean bl2 = false;
        return ULong.constructor-impl(l3 * l2);
    }

    @InlineOnly
    private static final int div-7apg3OU(byte n2, byte n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFF);
        bl = false;
        return UnsignedKt.uintDivide-J1ME1BU(n5, n6);
    }

    @InlineOnly
    private static final int div-xj2QHRw(byte n2, short n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFFFF);
        bl = false;
        return UnsignedKt.uintDivide-J1ME1BU(n5, n6);
    }

    @InlineOnly
    private static final int div-WZ4Q5Ns(byte n2, int n3) {
        int n4 = 0;
        int n5 = n2;
        boolean bl = false;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        bl = false;
        return UnsignedKt.uintDivide-J1ME1BU(n5, n3);
    }

    @InlineOnly
    private static final long div-VKZWuLQ(byte by, long l2) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        long l3 = ULong.constructor-impl((long)by2 & 0xFFL);
        boolean bl2 = false;
        return UnsignedKt.ulongDivide-eb3DHEI(l3, l2);
    }

    @InlineOnly
    private static final int rem-7apg3OU(byte n2, byte n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFF);
        bl = false;
        return UnsignedKt.uintRemainder-J1ME1BU(n5, n6);
    }

    @InlineOnly
    private static final int rem-xj2QHRw(byte n2, short n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFFFF);
        bl = false;
        return UnsignedKt.uintRemainder-J1ME1BU(n5, n6);
    }

    @InlineOnly
    private static final int rem-WZ4Q5Ns(byte n2, int n3) {
        int n4 = 0;
        int n5 = n2;
        boolean bl = false;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        bl = false;
        return UnsignedKt.uintRemainder-J1ME1BU(n5, n3);
    }

    @InlineOnly
    private static final long rem-VKZWuLQ(byte by, long l2) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        long l3 = ULong.constructor-impl((long)by2 & 0xFFL);
        boolean bl2 = false;
        return UnsignedKt.ulongRemainder-eb3DHEI(l3, l2);
    }

    @InlineOnly
    private static final int floorDiv-7apg3OU(byte n2, byte n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFF);
        bl = false;
        int n7 = n5;
        boolean bl2 = false;
        return UnsignedKt.uintDivide-J1ME1BU(n7, n6);
    }

    @InlineOnly
    private static final int floorDiv-xj2QHRw(byte n2, short n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFFFF);
        bl = false;
        int n7 = n5;
        boolean bl2 = false;
        return UnsignedKt.uintDivide-J1ME1BU(n7, n6);
    }

    @InlineOnly
    private static final int floorDiv-WZ4Q5Ns(byte n2, int n3) {
        int n4 = 0;
        int n5 = n2;
        boolean bl = false;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        bl = false;
        int n6 = n5;
        boolean bl2 = false;
        return UnsignedKt.uintDivide-J1ME1BU(n6, n3);
    }

    @InlineOnly
    private static final long floorDiv-VKZWuLQ(byte by, long l2) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        long l3 = ULong.constructor-impl((long)by2 & 0xFFL);
        boolean bl2 = false;
        long l4 = l3;
        boolean bl3 = false;
        return UnsignedKt.ulongDivide-eb3DHEI(l4, l2);
    }

    @InlineOnly
    private static final byte mod-7apg3OU(byte n2, byte n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        int n7 = 0;
        n6 = UInt.constructor-impl(n6 & 0xFF);
        n7 = 0;
        int n8 = n5;
        boolean bl = false;
        n5 = UnsignedKt.uintRemainder-J1ME1BU(n8, n6);
        n6 = 0;
        n7 = n5;
        n8 = 0;
        return UByte.constructor-impl((byte)n7);
    }

    @InlineOnly
    private static final short mod-xj2QHRw(byte n2, short n3) {
        int n4 = 0;
        int n5 = n2;
        int n6 = 0;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        n6 = n3;
        int n7 = 0;
        n6 = UInt.constructor-impl(n6 & 0xFFFF);
        n7 = 0;
        int n8 = n5;
        boolean bl = false;
        n5 = UnsignedKt.uintRemainder-J1ME1BU(n8, n6);
        n6 = 0;
        n7 = n5;
        n8 = 0;
        return UShort.constructor-impl((short)n7);
    }

    @InlineOnly
    private static final int mod-WZ4Q5Ns(byte n2, int n3) {
        int n4 = 0;
        int n5 = n2;
        boolean bl = false;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        bl = false;
        int n6 = n5;
        boolean bl2 = false;
        return UnsignedKt.uintRemainder-J1ME1BU(n6, n3);
    }

    @InlineOnly
    private static final long mod-VKZWuLQ(byte by, long l2) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        long l3 = ULong.constructor-impl((long)by2 & 0xFFL);
        boolean bl2 = false;
        long l4 = l3;
        boolean bl3 = false;
        return UnsignedKt.ulongRemainder-eb3DHEI(l4, l2);
    }

    @InlineOnly
    private static final byte inc-w2LRezQ(byte by) {
        int n2 = 0;
        return UByte.constructor-impl((byte)(by + 1));
    }

    @InlineOnly
    private static final byte dec-w2LRezQ(byte by) {
        int n2 = 0;
        return UByte.constructor-impl((byte)(by + -1));
    }

    @InlineOnly
    private static final UIntRange rangeTo-7apg3OU(byte by, byte by2) {
        int n2 = 0;
        byte by3 = by;
        boolean bl = false;
        int n3 = UInt.constructor-impl(by3 & 0xFF);
        by3 = by2;
        bl = false;
        return new UIntRange(n3, UInt.constructor-impl(by3 & 0xFF), null);
    }

    @InlineOnly
    private static final byte and-7apg3OU(byte by, byte by2) {
        int n2 = 0;
        byte by3 = by;
        byte by4 = by2;
        boolean bl = false;
        return UByte.constructor-impl((byte)(by3 & by4));
    }

    @InlineOnly
    private static final byte or-7apg3OU(byte by, byte by2) {
        int n2 = 0;
        byte by3 = by;
        byte by4 = by2;
        boolean bl = false;
        return UByte.constructor-impl((byte)(by3 | by4));
    }

    @InlineOnly
    private static final byte xor-7apg3OU(byte by, byte by2) {
        int n2 = 0;
        byte by3 = by;
        byte by4 = by2;
        boolean bl = false;
        return UByte.constructor-impl((byte)(by3 ^ by4));
    }

    @InlineOnly
    private static final byte inv-w2LRezQ(byte by) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        return UByte.constructor-impl(~by2);
    }

    @InlineOnly
    private static final byte toByte-impl(byte by) {
        int n2 = 0;
        return by;
    }

    @InlineOnly
    private static final short toShort-impl(byte by) {
        int n2 = 0;
        short s2 = by;
        int n3 = 255;
        boolean bl = false;
        return (short)(s2 & n3);
    }

    @InlineOnly
    private static final int toInt-impl(byte by) {
        int n2 = 0;
        return by & 0xFF;
    }

    @InlineOnly
    private static final long toLong-impl(byte by) {
        int n2 = 0;
        return (long)by & 0xFFL;
    }

    @InlineOnly
    private static final byte toUByte-w2LRezQ(byte by) {
        int n2 = 0;
        return by;
    }

    @InlineOnly
    private static final short toUShort-Mh2AYeg(byte by) {
        int n2 = 0;
        short s2 = by;
        int n3 = 255;
        boolean bl = false;
        return UShort.constructor-impl((short)(s2 & n3));
    }

    @InlineOnly
    private static final int toUInt-pVg5ArA(byte by) {
        int n2 = 0;
        return UInt.constructor-impl(by & 0xFF);
    }

    @InlineOnly
    private static final long toULong-s-VKNKU(byte by) {
        int n2 = 0;
        return ULong.constructor-impl((long)by & 0xFFL);
    }

    @InlineOnly
    private static final float toFloat-impl(byte by) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        return by2 & 0xFF;
    }

    @InlineOnly
    private static final double toDouble-impl(byte by) {
        int n2 = 0;
        byte by2 = by;
        boolean bl = false;
        return by2 & 0xFF;
    }

    @NotNull
    public static String toString-impl(byte by) {
        byte by2 = by;
        boolean bl = false;
        return String.valueOf(by2 & 0xFF);
    }

    @PublishedApi
    public static byte constructor-impl(byte by) {
        return by;
    }

    public static final /* synthetic */ UByte box-impl(byte by) {
        return new UByte(by);
    }

    public static int hashCode-impl(byte by) {
        return by;
    }

    public static boolean equals-impl(byte by, Object object) {
        byte by2;
        return object instanceof UByte && by == (by2 = ((UByte)object).unbox-impl());
    }

    public static final boolean equals-impl0(byte by, byte by2) {
        return by == by2;
    }

    public final /* synthetic */ byte unbox-impl() {
        return this.data;
    }

    public int hashCode() {
        return UByte.hashCode-impl(this.data);
    }

    public boolean equals(Object object) {
        return UByte.equals-impl(this.data, object);
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086T\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0086T\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\n"}, d2={"Lkotlin/UByte$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UByte;", "B", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

