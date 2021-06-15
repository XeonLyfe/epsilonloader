/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.WasExperimental;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\u0005\u001a\u001a\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012\u001a\u001a\u0010\u0013\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015\u001a\u001a\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\n0\u000eH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2={"sum", "Lkotlin/UInt;", "", "Lkotlin/UByte;", "sumOfUByte", "(Ljava/lang/Iterable;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Ljava/lang/Iterable;)J", "Lkotlin/UShort;", "sumOfUShort", "toUByteArray", "Lkotlin/UByteArray;", "", "(Ljava/util/Collection;)[B", "toUIntArray", "Lkotlin/UIntArray;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "(Ljava/util/Collection;)[S", "kotlin-stdlib"}, xs="kotlin/collections/UCollectionsKt")
class UCollectionsKt___UCollectionsKt {
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] toUByteArray(@NotNull Collection<UByte> collection) {
        Intrinsics.checkNotNullParameter(collection, "$this$toUByteArray");
        byte[] arrby = UByteArray.constructor-impl(collection.size());
        int n2 = 0;
        Iterator<UByte> iterator2 = collection.iterator();
        while (iterator2.hasNext()) {
            byte by = iterator2.next().unbox-impl();
            UByteArray.set-VurrAj0(arrby, n2++, by);
        }
        return arrby;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final int[] toUIntArray(@NotNull Collection<UInt> collection) {
        Intrinsics.checkNotNullParameter(collection, "$this$toUIntArray");
        int[] arrn = UIntArray.constructor-impl(collection.size());
        int n2 = 0;
        Iterator<UInt> iterator2 = collection.iterator();
        while (iterator2.hasNext()) {
            int n3 = iterator2.next().unbox-impl();
            UIntArray.set-VXSXFK8(arrn, n2++, n3);
        }
        return arrn;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final long[] toULongArray(@NotNull Collection<ULong> collection) {
        Intrinsics.checkNotNullParameter(collection, "$this$toULongArray");
        long[] arrl = ULongArray.constructor-impl(collection.size());
        int n2 = 0;
        Iterator<ULong> iterator2 = collection.iterator();
        while (iterator2.hasNext()) {
            long l2 = iterator2.next().unbox-impl();
            ULongArray.set-k8EXiF4(arrl, n2++, l2);
        }
        return arrl;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final short[] toUShortArray(@NotNull Collection<UShort> collection) {
        Intrinsics.checkNotNullParameter(collection, "$this$toUShortArray");
        short[] arrs = UShortArray.constructor-impl(collection.size());
        int n2 = 0;
        Iterator<UShort> iterator2 = collection.iterator();
        while (iterator2.hasNext()) {
            short s2 = iterator2.next().unbox-impl();
            UShortArray.set-01HTLdE(arrs, n2++, s2);
        }
        return arrs;
    }

    @JvmName(name="sumOfUInt")
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(@NotNull Iterable<UInt> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "$this$sum");
        int n2 = 0;
        Iterator<UInt> iterator2 = iterable.iterator();
        while (iterator2.hasNext()) {
            int n3 = iterator2.next().unbox-impl();
            int n4 = n2;
            boolean bl = false;
            n2 = UInt.constructor-impl(n4 + n3);
        }
        return n2;
    }

    @JvmName(name="sumOfULong")
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(@NotNull Iterable<ULong> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "$this$sum");
        long l2 = 0L;
        Iterator<ULong> iterator2 = iterable.iterator();
        while (iterator2.hasNext()) {
            long l3 = iterator2.next().unbox-impl();
            long l4 = l2;
            boolean bl = false;
            l2 = ULong.constructor-impl(l4 + l3);
        }
        return l2;
    }

    @JvmName(name="sumOfUByte")
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int sumOfUByte(@NotNull Iterable<UByte> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "$this$sum");
        int n2 = 0;
        Iterator<UByte> iterator2 = iterable.iterator();
        while (iterator2.hasNext()) {
            int n3 = iterator2.next().unbox-impl();
            int n4 = n2;
            boolean bl = false;
            int n5 = n4;
            int n6 = n3;
            boolean bl2 = false;
            n6 = UInt.constructor-impl(n6 & 0xFF);
            bl2 = false;
            n2 = UInt.constructor-impl(n5 + n6);
        }
        return n2;
    }

    @JvmName(name="sumOfUShort")
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int sumOfUShort(@NotNull Iterable<UShort> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "$this$sum");
        int n2 = 0;
        Iterator<UShort> iterator2 = iterable.iterator();
        while (iterator2.hasNext()) {
            int n3 = iterator2.next().unbox-impl();
            int n4 = n2;
            boolean bl = false;
            int n5 = n4;
            int n6 = n3;
            boolean bl2 = false;
            n6 = UInt.constructor-impl(n6 & 0xFFFF);
            bl2 = false;
            n2 = UInt.constructor-impl(n5 + n6);
        }
        return n2;
    }
}

