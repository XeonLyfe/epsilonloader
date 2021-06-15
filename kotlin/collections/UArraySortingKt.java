/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.collections;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=2, d1={"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u0014\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010\u0016\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010\u0018\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b!\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2={"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"})
public final class UArraySortingKt {
    @ExperimentalUnsignedTypes
    private static final int partition-4UcCI2c(byte[] arrby, int n2, int n3) {
        int n4 = n2;
        int n5 = n3;
        byte by = UByteArray.get-w2LRezQ(arrby, (n2 + n3) / 2);
        while (n4 <= n5) {
            boolean bl;
            byte by2;
            boolean bl2;
            byte by3;
            while (true) {
                by3 = UByteArray.get-w2LRezQ(arrby, n4);
                bl2 = false;
                by2 = by3;
                bl = false;
                int n6 = by2 & 0xFF;
                by2 = by;
                bl = false;
                if (Intrinsics.compare(n6, by2 & 0xFF) >= 0) break;
                ++n4;
            }
            while (true) {
                by3 = UByteArray.get-w2LRezQ(arrby, n5);
                bl2 = false;
                by2 = by3;
                bl = false;
                int n7 = by2 & 0xFF;
                by2 = by;
                bl = false;
                if (Intrinsics.compare(n7, by2 & 0xFF) <= 0) break;
                --n5;
            }
            if (n4 > n5) continue;
            by3 = UByteArray.get-w2LRezQ(arrby, n4);
            UByteArray.set-VurrAj0(arrby, n4, UByteArray.get-w2LRezQ(arrby, n5));
            UByteArray.set-VurrAj0(arrby, n5, by3);
            ++n4;
            --n5;
        }
        return n4;
    }

    @ExperimentalUnsignedTypes
    private static final void quickSort-4UcCI2c(byte[] arrby, int n2, int n3) {
        int n4 = UArraySortingKt.partition-4UcCI2c(arrby, n2, n3);
        if (n2 < n4 - 1) {
            UArraySortingKt.quickSort-4UcCI2c(arrby, n2, n4 - 1);
        }
        if (n4 < n3) {
            UArraySortingKt.quickSort-4UcCI2c(arrby, n4, n3);
        }
    }

    @ExperimentalUnsignedTypes
    private static final int partition-Aa5vz7o(short[] arrs, int n2, int n3) {
        int n4 = n2;
        int n5 = n3;
        short s2 = UShortArray.get-Mh2AYeg(arrs, (n2 + n3) / 2);
        while (n4 <= n5) {
            boolean bl;
            short s3;
            boolean bl2;
            short s4;
            while (true) {
                s4 = UShortArray.get-Mh2AYeg(arrs, n4);
                bl2 = false;
                s3 = s4;
                bl = false;
                int n6 = s3 & 0xFFFF;
                s3 = s2;
                bl = false;
                if (Intrinsics.compare(n6, s3 & 0xFFFF) >= 0) break;
                ++n4;
            }
            while (true) {
                s4 = UShortArray.get-Mh2AYeg(arrs, n5);
                bl2 = false;
                s3 = s4;
                bl = false;
                int n7 = s3 & 0xFFFF;
                s3 = s2;
                bl = false;
                if (Intrinsics.compare(n7, s3 & 0xFFFF) <= 0) break;
                --n5;
            }
            if (n4 > n5) continue;
            s4 = UShortArray.get-Mh2AYeg(arrs, n4);
            UShortArray.set-01HTLdE(arrs, n4, UShortArray.get-Mh2AYeg(arrs, n5));
            UShortArray.set-01HTLdE(arrs, n5, s4);
            ++n4;
            --n5;
        }
        return n4;
    }

    @ExperimentalUnsignedTypes
    private static final void quickSort-Aa5vz7o(short[] arrs, int n2, int n3) {
        int n4 = UArraySortingKt.partition-Aa5vz7o(arrs, n2, n3);
        if (n2 < n4 - 1) {
            UArraySortingKt.quickSort-Aa5vz7o(arrs, n2, n4 - 1);
        }
        if (n4 < n3) {
            UArraySortingKt.quickSort-Aa5vz7o(arrs, n4, n3);
        }
    }

    @ExperimentalUnsignedTypes
    private static final int partition-oBK06Vg(int[] arrn, int n2, int n3) {
        int n4 = n2;
        int n5 = n3;
        int n6 = UIntArray.get-pVg5ArA(arrn, (n2 + n3) / 2);
        while (n4 <= n5) {
            boolean bl;
            int n7;
            while (true) {
                n7 = UIntArray.get-pVg5ArA(arrn, n4);
                bl = false;
                if (UnsignedKt.uintCompare(n7, n6) >= 0) break;
                ++n4;
            }
            while (true) {
                n7 = UIntArray.get-pVg5ArA(arrn, n5);
                bl = false;
                if (UnsignedKt.uintCompare(n7, n6) <= 0) break;
                --n5;
            }
            if (n4 > n5) continue;
            n7 = UIntArray.get-pVg5ArA(arrn, n4);
            UIntArray.set-VXSXFK8(arrn, n4, UIntArray.get-pVg5ArA(arrn, n5));
            UIntArray.set-VXSXFK8(arrn, n5, n7);
            ++n4;
            --n5;
        }
        return n4;
    }

    @ExperimentalUnsignedTypes
    private static final void quickSort-oBK06Vg(int[] arrn, int n2, int n3) {
        int n4 = UArraySortingKt.partition-oBK06Vg(arrn, n2, n3);
        if (n2 < n4 - 1) {
            UArraySortingKt.quickSort-oBK06Vg(arrn, n2, n4 - 1);
        }
        if (n4 < n3) {
            UArraySortingKt.quickSort-oBK06Vg(arrn, n4, n3);
        }
    }

    @ExperimentalUnsignedTypes
    private static final int partition--nroSd4(long[] arrl, int n2, int n3) {
        int n4 = n2;
        int n5 = n3;
        long l2 = ULongArray.get-s-VKNKU(arrl, (n2 + n3) / 2);
        while (n4 <= n5) {
            boolean bl;
            long l3;
            while (true) {
                l3 = ULongArray.get-s-VKNKU(arrl, n4);
                bl = false;
                if (UnsignedKt.ulongCompare(l3, l2) >= 0) break;
                ++n4;
            }
            while (true) {
                l3 = ULongArray.get-s-VKNKU(arrl, n5);
                bl = false;
                if (UnsignedKt.ulongCompare(l3, l2) <= 0) break;
                --n5;
            }
            if (n4 > n5) continue;
            l3 = ULongArray.get-s-VKNKU(arrl, n4);
            ULongArray.set-k8EXiF4(arrl, n4, ULongArray.get-s-VKNKU(arrl, n5));
            ULongArray.set-k8EXiF4(arrl, n5, l3);
            ++n4;
            --n5;
        }
        return n4;
    }

    @ExperimentalUnsignedTypes
    private static final void quickSort--nroSd4(long[] arrl, int n2, int n3) {
        int n4 = UArraySortingKt.partition--nroSd4(arrl, n2, n3);
        if (n2 < n4 - 1) {
            UArraySortingKt.quickSort--nroSd4(arrl, n2, n4 - 1);
        }
        if (n4 < n3) {
            UArraySortingKt.quickSort--nroSd4(arrl, n4, n3);
        }
    }

    @ExperimentalUnsignedTypes
    public static final void sortArray-4UcCI2c(@NotNull byte[] arrby, int n2, int n3) {
        Intrinsics.checkNotNullParameter(arrby, "array");
        UArraySortingKt.quickSort-4UcCI2c(arrby, n2, n3 - 1);
    }

    @ExperimentalUnsignedTypes
    public static final void sortArray-Aa5vz7o(@NotNull short[] arrs, int n2, int n3) {
        Intrinsics.checkNotNullParameter(arrs, "array");
        UArraySortingKt.quickSort-Aa5vz7o(arrs, n2, n3 - 1);
    }

    @ExperimentalUnsignedTypes
    public static final void sortArray-oBK06Vg(@NotNull int[] arrn, int n2, int n3) {
        Intrinsics.checkNotNullParameter(arrn, "array");
        UArraySortingKt.quickSort-oBK06Vg(arrn, n2, n3 - 1);
    }

    @ExperimentalUnsignedTypes
    public static final void sortArray--nroSd4(@NotNull long[] arrl, int n2, int n3) {
        Intrinsics.checkNotNullParameter(arrl, "array");
        UArraySortingKt.quickSort--nroSd4(arrl, n2, n3 - 1);
    }
}

