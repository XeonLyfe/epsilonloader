/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.collections.unsigned;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.RandomAccess;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000T\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001*\u00020\u0007H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\n0\u0001*\u00020\u000bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\r\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001*\u00020\u000fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001e\u001a\u001f\u0010\u001f\u001a\u00020\u0002*\u00020\u00032\u0006\u0010 \u001a\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b!\u0010\"\u001a\u001f\u0010\u001f\u001a\u00020\u0006*\u00020\u00072\u0006\u0010 \u001a\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010$\u001a\u001f\u0010\u001f\u001a\u00020\n*\u00020\u000b2\u0006\u0010 \u001a\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010&\u001a\u001f\u0010\u001f\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010 \u001a\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010(\u001a.\u0010)\u001a\u00020**\u00020\u00032\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020*0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b-\u0010.\u001a.\u0010)\u001a\u00020/*\u00020\u00032\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020/0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b0\u00101\u001a.\u0010)\u001a\u00020**\u00020\u00072\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020*0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b-\u00102\u001a.\u0010)\u001a\u00020/*\u00020\u00072\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020/0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b0\u00103\u001a.\u0010)\u001a\u00020**\u00020\u000b2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020*0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b-\u00104\u001a.\u0010)\u001a\u00020/*\u00020\u000b2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020/0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b0\u00105\u001a.\u0010)\u001a\u00020**\u00020\u000f2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020*0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b-\u00106\u001a.\u0010)\u001a\u00020/*\u00020\u000f2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020/0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b0\u00107\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001\u00a8\u00068"}, d2={"asList", "", "Lkotlin/UByte;", "Lkotlin/UByteArray;", "asList-GBYM_sE", "([B)Ljava/util/List;", "Lkotlin/UInt;", "Lkotlin/UIntArray;", "asList--ajY-9A", "([I)Ljava/util/List;", "Lkotlin/ULong;", "Lkotlin/ULongArray;", "asList-QwZRm1k", "([J)Ljava/util/List;", "Lkotlin/UShort;", "Lkotlin/UShortArray;", "asList-rL5Bavg", "([S)Ljava/util/List;", "binarySearch", "", "element", "fromIndex", "toIndex", "binarySearch-WpHrYlw", "([BBII)I", "binarySearch-2fe2U9s", "([IIII)I", "binarySearch-K6DWlUc", "([JJII)I", "binarySearch-EtDCXyQ", "([SSII)I", "elementAt", "index", "elementAt-PpDY95g", "([BI)B", "elementAt-qFRl0hI", "([II)I", "elementAt-r7IrZao", "([JI)J", "elementAt-nggk6HY", "([SI)S", "sumOf", "Ljava/math/BigDecimal;", "selector", "Lkotlin/Function1;", "sumOfBigDecimal", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "Ljava/math/BigInteger;", "sumOfBigInteger", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "kotlin-stdlib"}, xs="kotlin/collections/unsigned/UArraysKt", pn="kotlin.collections")
class UArraysKt___UArraysJvmKt {
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int elementAt-qFRl0hI(int[] arrn, int n2) {
        int n3 = 0;
        return UIntArray.get-pVg5ArA(arrn, n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long elementAt-r7IrZao(long[] arrl, int n2) {
        int n3 = 0;
        return ULongArray.get-s-VKNKU(arrl, n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte elementAt-PpDY95g(byte[] arrby, int n2) {
        int n3 = 0;
        return UByteArray.get-w2LRezQ(arrby, n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final short elementAt-nggk6HY(short[] arrs, int n2) {
        int n3 = 0;
        return UShortArray.get-Mh2AYeg(arrs, n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final List<UInt> asList--ajY-9A(@NotNull int[] arrn) {
        Intrinsics.checkNotNullParameter(arrn, "$this$asList");
        return (List)((Object)new RandomAccess(arrn){
            final /* synthetic */ int[] $this_asList;

            public int getSize() {
                return UIntArray.getSize-impl(this.$this_asList);
            }

            public boolean isEmpty() {
                return UIntArray.isEmpty-impl(this.$this_asList);
            }

            public boolean contains-WZ4Q5Ns(int n2) {
                return UIntArray.contains-WZ4Q5Ns(this.$this_asList, n2);
            }

            public int get-pVg5ArA(int n2) {
                return UIntArray.get-pVg5ArA(this.$this_asList, n2);
            }

            public int indexOf-WZ4Q5Ns(int n2) {
                int[] arrn = this.$this_asList;
                boolean bl = false;
                int n3 = n2;
                boolean bl2 = false;
                return ArraysKt.indexOf(arrn, n3);
            }

            public int lastIndexOf-WZ4Q5Ns(int n2) {
                int[] arrn = this.$this_asList;
                boolean bl = false;
                int n3 = n2;
                boolean bl2 = false;
                return ArraysKt.lastIndexOf(arrn, n3);
            }
            {
                this.$this_asList = arrn;
            }
        });
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final List<ULong> asList-QwZRm1k(@NotNull long[] arrl) {
        Intrinsics.checkNotNullParameter(arrl, "$this$asList");
        return (List)((Object)new RandomAccess(arrl){
            final /* synthetic */ long[] $this_asList;

            public int getSize() {
                return ULongArray.getSize-impl(this.$this_asList);
            }

            public boolean isEmpty() {
                return ULongArray.isEmpty-impl(this.$this_asList);
            }

            public boolean contains-VKZWuLQ(long l2) {
                return ULongArray.contains-VKZWuLQ(this.$this_asList, l2);
            }

            public long get-s-VKNKU(int n2) {
                return ULongArray.get-s-VKNKU(this.$this_asList, n2);
            }

            public int indexOf-VKZWuLQ(long l2) {
                long[] arrl = this.$this_asList;
                boolean bl = false;
                long l3 = l2;
                boolean bl2 = false;
                return ArraysKt.indexOf(arrl, l3);
            }

            public int lastIndexOf-VKZWuLQ(long l2) {
                long[] arrl = this.$this_asList;
                boolean bl = false;
                long l3 = l2;
                boolean bl2 = false;
                return ArraysKt.lastIndexOf(arrl, l3);
            }
            {
                this.$this_asList = arrl;
            }
        });
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final List<UByte> asList-GBYM_sE(@NotNull byte[] arrby) {
        Intrinsics.checkNotNullParameter(arrby, "$this$asList");
        return (List)((Object)new RandomAccess(arrby){
            final /* synthetic */ byte[] $this_asList;

            public int getSize() {
                return UByteArray.getSize-impl(this.$this_asList);
            }

            public boolean isEmpty() {
                return UByteArray.isEmpty-impl(this.$this_asList);
            }

            public boolean contains-7apg3OU(byte by) {
                return UByteArray.contains-7apg3OU(this.$this_asList, by);
            }

            public byte get-w2LRezQ(int n2) {
                return UByteArray.get-w2LRezQ(this.$this_asList, n2);
            }

            public int indexOf-7apg3OU(byte by) {
                byte[] arrby = this.$this_asList;
                boolean bl = false;
                byte by2 = by;
                boolean bl2 = false;
                return ArraysKt.indexOf(arrby, by2);
            }

            public int lastIndexOf-7apg3OU(byte by) {
                byte[] arrby = this.$this_asList;
                boolean bl = false;
                byte by2 = by;
                boolean bl2 = false;
                return ArraysKt.lastIndexOf(arrby, by2);
            }
            {
                this.$this_asList = arrby;
            }
        });
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final List<UShort> asList-rL5Bavg(@NotNull short[] arrs) {
        Intrinsics.checkNotNullParameter(arrs, "$this$asList");
        return (List)((Object)new RandomAccess(arrs){
            final /* synthetic */ short[] $this_asList;

            public int getSize() {
                return UShortArray.getSize-impl(this.$this_asList);
            }

            public boolean isEmpty() {
                return UShortArray.isEmpty-impl(this.$this_asList);
            }

            public boolean contains-xj2QHRw(short s2) {
                return UShortArray.contains-xj2QHRw(this.$this_asList, s2);
            }

            public short get-Mh2AYeg(int n2) {
                return UShortArray.get-Mh2AYeg(this.$this_asList, n2);
            }

            public int indexOf-xj2QHRw(short s2) {
                short[] arrs = this.$this_asList;
                boolean bl = false;
                short s3 = s2;
                boolean bl2 = false;
                return ArraysKt.indexOf(arrs, s3);
            }

            public int lastIndexOf-xj2QHRw(short s2) {
                short[] arrs = this.$this_asList;
                boolean bl = false;
                short s3 = s2;
                boolean bl2 = false;
                return ArraysKt.lastIndexOf(arrs, s3);
            }
            {
                this.$this_asList = arrs;
            }
        });
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int binarySearch-2fe2U9s(@NotNull int[] arrn, int n2, int n3, int n4) {
        Intrinsics.checkNotNullParameter(arrn, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(n3, n4, UIntArray.getSize-impl(arrn));
        int n5 = n2;
        int n6 = 0;
        int n7 = n5;
        n5 = n3;
        n6 = n4 - 1;
        while (n5 <= n6) {
            int n8 = n5 + n6 >>> 1;
            int n9 = arrn[n8];
            int n10 = UnsignedKt.uintCompare(n9, n7);
            if (n10 < 0) {
                n5 = n8 + 1;
                continue;
            }
            if (n10 > 0) {
                n6 = n8 - 1;
                continue;
            }
            return n8;
        }
        return -(n5 + 1);
    }

    public static /* synthetic */ int binarySearch-2fe2U9s$default(int[] arrn, int n2, int n3, int n4, int n5, Object object) {
        if ((n5 & 2) != 0) {
            n3 = 0;
        }
        if ((n5 & 4) != 0) {
            n4 = UIntArray.getSize-impl(arrn);
        }
        return UArraysKt.binarySearch-2fe2U9s(arrn, n2, n3, n4);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int binarySearch-K6DWlUc(@NotNull long[] arrl, long l2, int n2, int n3) {
        Intrinsics.checkNotNullParameter(arrl, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(n2, n3, ULongArray.getSize-impl(arrl));
        long l3 = l2;
        int n4 = 0;
        long l4 = l3;
        int n5 = n2;
        int n6 = n3 - 1;
        while (n5 <= n6) {
            n4 = n5 + n6 >>> 1;
            long l5 = arrl[n4];
            int n7 = UnsignedKt.ulongCompare(l5, l4);
            if (n7 < 0) {
                n5 = n4 + 1;
                continue;
            }
            if (n7 > 0) {
                n6 = n4 - 1;
                continue;
            }
            return n4;
        }
        return -(n5 + 1);
    }

    public static /* synthetic */ int binarySearch-K6DWlUc$default(long[] arrl, long l2, int n2, int n3, int n4, Object object) {
        if ((n4 & 2) != 0) {
            n2 = 0;
        }
        if ((n4 & 4) != 0) {
            n3 = ULongArray.getSize-impl(arrl);
        }
        return UArraysKt.binarySearch-K6DWlUc(arrl, l2, n2, n3);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int binarySearch-WpHrYlw(@NotNull byte[] arrby, byte n2, int n3, int n4) {
        Intrinsics.checkNotNullParameter(arrby, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(n3, n4, UByteArray.getSize-impl(arrby));
        int n5 = n2;
        int n6 = 0;
        int n7 = n5 & 0xFF;
        n5 = n3;
        n6 = n4 - 1;
        while (n5 <= n6) {
            int n8 = n5 + n6 >>> 1;
            byte by = arrby[n8];
            int n9 = UnsignedKt.uintCompare(by, n7);
            if (n9 < 0) {
                n5 = n8 + 1;
                continue;
            }
            if (n9 > 0) {
                n6 = n8 - 1;
                continue;
            }
            return n8;
        }
        return -(n5 + 1);
    }

    public static /* synthetic */ int binarySearch-WpHrYlw$default(byte[] arrby, byte by, int n2, int n3, int n4, Object object) {
        if ((n4 & 2) != 0) {
            n2 = 0;
        }
        if ((n4 & 4) != 0) {
            n3 = UByteArray.getSize-impl(arrby);
        }
        return UArraysKt.binarySearch-WpHrYlw(arrby, by, n2, n3);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int binarySearch-EtDCXyQ(@NotNull short[] arrs, short n2, int n3, int n4) {
        Intrinsics.checkNotNullParameter(arrs, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(n3, n4, UShortArray.getSize-impl(arrs));
        int n5 = n2;
        int n6 = 0;
        int n7 = n5 & 0xFFFF;
        n5 = n3;
        n6 = n4 - 1;
        while (n5 <= n6) {
            int n8 = n5 + n6 >>> 1;
            short s2 = arrs[n8];
            int n9 = UnsignedKt.uintCompare(s2, n7);
            if (n9 < 0) {
                n5 = n8 + 1;
                continue;
            }
            if (n9 > 0) {
                n6 = n8 - 1;
                continue;
            }
            return n8;
        }
        return -(n5 + 1);
    }

    public static /* synthetic */ int binarySearch-EtDCXyQ$default(short[] arrs, short s2, int n2, int n3, int n4, Object object) {
        if ((n4 & 2) != 0) {
            n2 = 0;
        }
        if ((n4 & 4) != 0) {
            n3 = UShortArray.getSize-impl(arrs);
        }
        return UArraysKt.binarySearch-EtDCXyQ(arrs, s2, n2, n3);
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigDecimal")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigDecimal sumOfBigDecimal(int[] arrn, Function1<? super UInt, ? extends BigDecimal> function1) {
        int n2 = 0;
        int n32 = 0;
        int n4 = 0;
        BigDecimal bigDecimal = BigDecimal.valueOf(n32);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this.toLong())");
        BigDecimal bigDecimal2 = bigDecimal;
        for (int n32 : arrn) {
            BigDecimal bigDecimal3 = bigDecimal2;
            BigDecimal bigDecimal4 = function1.invoke(UInt.box-impl(n32));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigDecimal3.add(bigDecimal4), "this.add(other)");
        }
        return bigDecimal2;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigDecimal")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigDecimal sumOfBigDecimal(long[] arrl, Function1<? super ULong, ? extends BigDecimal> function1) {
        int n2 = 0;
        int n3 = 0;
        boolean bl = false;
        BigDecimal bigDecimal = BigDecimal.valueOf(n3);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this.toLong())");
        BigDecimal bigDecimal2 = bigDecimal;
        for (long l2 : arrl) {
            BigDecimal bigDecimal3 = bigDecimal2;
            BigDecimal bigDecimal4 = function1.invoke(ULong.box-impl(l2));
            boolean bl2 = false;
            Intrinsics.checkNotNullExpressionValue(bigDecimal3.add(bigDecimal4), "this.add(other)");
        }
        return bigDecimal2;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigDecimal")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigDecimal sumOfBigDecimal(byte[] arrby, Function1<? super UByte, ? extends BigDecimal> function1) {
        int n2 = 0;
        byte by2 = 0;
        int n3 = 0;
        BigDecimal bigDecimal = BigDecimal.valueOf(by2);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this.toLong())");
        BigDecimal bigDecimal2 = bigDecimal;
        for (byte by2 : arrby) {
            BigDecimal bigDecimal3 = bigDecimal2;
            BigDecimal bigDecimal4 = function1.invoke(UByte.box-impl(by2));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigDecimal3.add(bigDecimal4), "this.add(other)");
        }
        return bigDecimal2;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigDecimal")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigDecimal sumOfBigDecimal(short[] arrs, Function1<? super UShort, ? extends BigDecimal> function1) {
        int n2 = 0;
        short s22 = 0;
        int n3 = 0;
        BigDecimal bigDecimal = BigDecimal.valueOf(s22);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this.toLong())");
        BigDecimal bigDecimal2 = bigDecimal;
        for (short s22 : arrs) {
            BigDecimal bigDecimal3 = bigDecimal2;
            BigDecimal bigDecimal4 = function1.invoke(UShort.box-impl(s22));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigDecimal3.add(bigDecimal4), "this.add(other)");
        }
        return bigDecimal2;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigInteger")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigInteger sumOfBigInteger(int[] arrn, Function1<? super UInt, ? extends BigInteger> function1) {
        int n2 = 0;
        int n32 = 0;
        int n4 = 0;
        BigInteger bigInteger = BigInteger.valueOf(n32);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger.valueOf(this.toLong())");
        BigInteger bigInteger2 = bigInteger;
        for (int n32 : arrn) {
            BigInteger bigInteger3 = bigInteger2;
            BigInteger bigInteger4 = function1.invoke(UInt.box-impl(n32));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigInteger3.add(bigInteger4), "this.add(other)");
        }
        return bigInteger2;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigInteger")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigInteger sumOfBigInteger(long[] arrl, Function1<? super ULong, ? extends BigInteger> function1) {
        int n2 = 0;
        int n3 = 0;
        boolean bl = false;
        BigInteger bigInteger = BigInteger.valueOf(n3);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger.valueOf(this.toLong())");
        BigInteger bigInteger2 = bigInteger;
        for (long l2 : arrl) {
            BigInteger bigInteger3 = bigInteger2;
            BigInteger bigInteger4 = function1.invoke(ULong.box-impl(l2));
            boolean bl2 = false;
            Intrinsics.checkNotNullExpressionValue(bigInteger3.add(bigInteger4), "this.add(other)");
        }
        return bigInteger2;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigInteger")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigInteger sumOfBigInteger(byte[] arrby, Function1<? super UByte, ? extends BigInteger> function1) {
        int n2 = 0;
        byte by2 = 0;
        int n3 = 0;
        BigInteger bigInteger = BigInteger.valueOf(by2);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger.valueOf(this.toLong())");
        BigInteger bigInteger2 = bigInteger;
        for (byte by2 : arrby) {
            BigInteger bigInteger3 = bigInteger2;
            BigInteger bigInteger4 = function1.invoke(UByte.box-impl(by2));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigInteger3.add(bigInteger4), "this.add(other)");
        }
        return bigInteger2;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigInteger")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigInteger sumOfBigInteger(short[] arrs, Function1<? super UShort, ? extends BigInteger> function1) {
        int n2 = 0;
        short s22 = 0;
        int n3 = 0;
        BigInteger bigInteger = BigInteger.valueOf(s22);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger.valueOf(this.toLong())");
        BigInteger bigInteger2 = bigInteger;
        for (short s22 : arrs) {
            BigInteger bigInteger3 = bigInteger2;
            BigInteger bigInteger4 = function1.invoke(UShort.box-impl(s22));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigInteger3.add(bigInteger4), "this.add(other)");
        }
        return bigInteger2;
    }
}

