/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.collections.BooleanIterator;
import kotlin.collections.ByteIterator;
import kotlin.collections.CharIterator;
import kotlin.collections.DoubleIterator;
import kotlin.collections.FloatIterator;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.collections.ShortIterator;
import kotlin.jvm.internal.ArrayBooleanIterator;
import kotlin.jvm.internal.ArrayByteIterator;
import kotlin.jvm.internal.ArrayCharIterator;
import kotlin.jvm.internal.ArrayDoubleIterator;
import kotlin.jvm.internal.ArrayFloatIterator;
import kotlin.jvm.internal.ArrayIntIterator;
import kotlin.jvm.internal.ArrayLongIterator;
import kotlin.jvm.internal.ArrayShortIterator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0005\u001a\u000e\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0007\u001a\u000e\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\t\u001a\u000e\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u000b\u001a\u000e\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\r\u001a\u000e\u0010\u0000\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u000f\u001a\u000e\u0010\u0000\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0011\u00a8\u0006\u0012"}, d2={"iterator", "Lkotlin/collections/BooleanIterator;", "array", "", "Lkotlin/collections/ByteIterator;", "", "Lkotlin/collections/CharIterator;", "", "Lkotlin/collections/DoubleIterator;", "", "Lkotlin/collections/FloatIterator;", "", "Lkotlin/collections/IntIterator;", "", "Lkotlin/collections/LongIterator;", "", "Lkotlin/collections/ShortIterator;", "", "kotlin-stdlib"})
public final class ArrayIteratorsKt {
    @NotNull
    public static final ByteIterator iterator(@NotNull byte[] arrby) {
        Intrinsics.checkNotNullParameter(arrby, "array");
        return new ArrayByteIterator(arrby);
    }

    @NotNull
    public static final CharIterator iterator(@NotNull char[] arrc) {
        Intrinsics.checkNotNullParameter(arrc, "array");
        return new ArrayCharIterator(arrc);
    }

    @NotNull
    public static final ShortIterator iterator(@NotNull short[] arrs) {
        Intrinsics.checkNotNullParameter(arrs, "array");
        return new ArrayShortIterator(arrs);
    }

    @NotNull
    public static final IntIterator iterator(@NotNull int[] arrn) {
        Intrinsics.checkNotNullParameter(arrn, "array");
        return new ArrayIntIterator(arrn);
    }

    @NotNull
    public static final LongIterator iterator(@NotNull long[] arrl) {
        Intrinsics.checkNotNullParameter(arrl, "array");
        return new ArrayLongIterator(arrl);
    }

    @NotNull
    public static final FloatIterator iterator(@NotNull float[] arrf) {
        Intrinsics.checkNotNullParameter(arrf, "array");
        return new ArrayFloatIterator(arrf);
    }

    @NotNull
    public static final DoubleIterator iterator(@NotNull double[] arrd) {
        Intrinsics.checkNotNullParameter(arrd, "array");
        return new ArrayDoubleIterator(arrd);
    }

    @NotNull
    public static final BooleanIterator iterator(@NotNull boolean[] arrbl) {
        Intrinsics.checkNotNullParameter(arrbl, "array");
        return new ArrayBooleanIterator(arrbl);
    }
}

