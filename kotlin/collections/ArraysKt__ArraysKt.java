/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.collections.ArraysKt;
import kotlin.collections.ArraysKt__ArraysJVMKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000H\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a5\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u00032\u0010\u0010\u0004\u001a\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0001\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0001\u00a2\u0006\u0004\b\t\u0010\n\u001a?\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0011H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a+\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00030\u0003\u00a2\u0006\u0002\u0010\u0016\u001a;\u0010\u0017\u001a\u0002H\u0018\"\u0010\b\u0000\u0010\u0019*\u0006\u0012\u0002\b\u00030\u0003*\u0002H\u0018\"\u0004\b\u0001\u0010\u0018*\u0002H\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001c\u001a)\u0010\u001d\u001a\u00020\u0001*\b\u0012\u0002\b\u0003\u0018\u00010\u0003H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u00a2\u0006\u0002\u0010\u001e\u001aG\u0010\u001f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00150 \"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0018*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00180 0\u0003\u00a2\u0006\u0002\u0010!\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\""}, d2={"contentDeepEqualsImpl", "", "T", "", "other", "contentDeepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepToStringImpl", "", "contentDeepToString", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringInternal", "", "result", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "processed", "", "contentDeepToStringInternal$ArraysKt__ArraysKt", "([Ljava/lang/Object;Ljava/lang/StringBuilder;Ljava/util/List;)V", "flatten", "", "([[Ljava/lang/Object;)Ljava/util/List;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNullOrEmpty", "([Ljava/lang/Object;)Z", "unzip", "Lkotlin/Pair;", "([Lkotlin/Pair;)Lkotlin/Pair;", "kotlin-stdlib"}, xs="kotlin/collections/ArraysKt")
class ArraysKt__ArraysKt
extends ArraysKt__ArraysJVMKt {
    @NotNull
    public static final <T> List<T> flatten(@NotNull T[][] arrT) {
        int n2;
        Intrinsics.checkNotNullParameter(arrT, "$this$flatten");
        Object[] arrobject3 = (Object[])arrT;
        int n3 = 0;
        int n4 = 0;
        for (Object object : arrobject3) {
            Object[] arrobject = (Object[])object;
            n2 = n4;
            boolean bl = false;
            int n5 = arrobject.length;
            n4 = n2 + n5;
        }
        int n6 = n2 = n4;
        ArrayList arrayList = new ArrayList(n6);
        for (T[] arrT2 : arrT) {
            CollectionsKt.addAll((Collection)arrayList, arrT2);
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Pair<? extends T, ? extends R>[] arrpair) {
        Intrinsics.checkNotNullParameter(arrpair, "$this$unzip");
        ArrayList<T> arrayList = new ArrayList<T>(arrpair.length);
        ArrayList<R> arrayList2 = new ArrayList<R>(arrpair.length);
        for (Pair<T, R> pair : arrpair) {
            arrayList.add(pair.getFirst());
            arrayList2.add(pair.getSecond());
        }
        return TuplesKt.to(arrayList, arrayList2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final boolean isNullOrEmpty(Object[] arrobject) {
        int n2 = 0;
        boolean bl = false;
        if (arrobject == null) return true;
        Object[] arrobject2 = arrobject;
        boolean bl2 = false;
        if (arrobject2.length != 0) return false;
        return true;
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <C extends Object[], R> R ifEmpty(C c2, Function0<? extends R> function0) {
        int n2 = 0;
        C c3 = c2;
        boolean bl = false;
        return (R)(((C)c3).length == 0 ? function0.invoke() : c2);
    }

    @SinceKotlin(version="1.3")
    @PublishedApi
    @JvmName(name="contentDeepEquals")
    public static final <T> boolean contentDeepEquals(@Nullable T[] arrT, @Nullable T[] arrT2) {
        if (arrT == arrT2) {
            return true;
        }
        if (arrT == null || arrT2 == null || arrT.length != arrT2.length) {
            return false;
        }
        int n2 = arrT.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            boolean bl;
            Object[] arrobject;
            T t2 = arrT[i2];
            T t3 = arrT2[i2];
            if (t2 == t3) continue;
            if (t2 == null || t3 == null) {
                return false;
            }
            if (t2 instanceof Object[] && t3 instanceof Object[]) {
                arrobject = (Object[])t2;
                bl = false;
                if (ArraysKt.contentDeepEquals(arrobject, (Object[])t3)) continue;
                return false;
            }
            if (t2 instanceof byte[] && t3 instanceof byte[]) {
                arrobject = (byte[])t2;
                bl = false;
                if (Arrays.equals((byte[])arrobject, (byte[])t3)) continue;
                return false;
            }
            if (t2 instanceof short[] && t3 instanceof short[]) {
                arrobject = (short[])t2;
                bl = false;
                if (Arrays.equals((short[])arrobject, (short[])t3)) continue;
                return false;
            }
            if (t2 instanceof int[] && t3 instanceof int[]) {
                arrobject = (int[])t2;
                bl = false;
                if (Arrays.equals((int[])arrobject, (int[])t3)) continue;
                return false;
            }
            if (t2 instanceof long[] && t3 instanceof long[]) {
                arrobject = (long[])t2;
                bl = false;
                if (Arrays.equals((long[])arrobject, (long[])t3)) continue;
                return false;
            }
            if (t2 instanceof float[] && t3 instanceof float[]) {
                arrobject = (float[])t2;
                bl = false;
                if (Arrays.equals((float[])arrobject, (float[])t3)) continue;
                return false;
            }
            if (t2 instanceof double[] && t3 instanceof double[]) {
                arrobject = (double[])t2;
                bl = false;
                if (Arrays.equals((double[])arrobject, (double[])t3)) continue;
                return false;
            }
            if (t2 instanceof char[] && t3 instanceof char[]) {
                arrobject = (char[])t2;
                bl = false;
                if (Arrays.equals((char[])arrobject, (char[])t3)) continue;
                return false;
            }
            if (t2 instanceof boolean[] && t3 instanceof boolean[]) {
                arrobject = (boolean[])t2;
                bl = false;
                if (Arrays.equals((boolean[])arrobject, (boolean[])t3)) continue;
                return false;
            }
            if (!(t2 instanceof UByteArray && t3 instanceof UByteArray ? !UArraysKt.contentEquals-kV0jMPg(((UByteArray)t2).unbox-impl(), ((UByteArray)t3).unbox-impl()) : (t2 instanceof UShortArray && t3 instanceof UShortArray ? !UArraysKt.contentEquals-FGO6Aew(((UShortArray)t2).unbox-impl(), ((UShortArray)t3).unbox-impl()) : (t2 instanceof UIntArray && t3 instanceof UIntArray ? !UArraysKt.contentEquals-KJPZfPQ(((UIntArray)t2).unbox-impl(), ((UIntArray)t3).unbox-impl()) : (t2 instanceof ULongArray && t3 instanceof ULongArray ? !UArraysKt.contentEquals-lec5QzE(((ULongArray)t2).unbox-impl(), ((ULongArray)t3).unbox-impl()) : Intrinsics.areEqual(t2, t3) ^ true))))) continue;
            return false;
        }
        return true;
    }

    @SinceKotlin(version="1.3")
    @PublishedApi
    @JvmName(name="contentDeepToString")
    @NotNull
    public static final <T> String contentDeepToString(@Nullable T[] arrT) {
        if (arrT == null) {
            return "null";
        }
        int n2 = RangesKt.coerceAtMost(arrT.length, 0x19999999) * 5 + 2;
        boolean bl = false;
        boolean bl2 = false;
        StringBuilder stringBuilder = new StringBuilder(n2);
        boolean bl3 = false;
        boolean bl4 = false;
        StringBuilder stringBuilder2 = stringBuilder;
        boolean bl5 = false;
        boolean bl6 = false;
        ArraysKt__ArraysKt.contentDeepToStringInternal$ArraysKt__ArraysKt(arrT, stringBuilder2, new ArrayList());
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).\u2026builderAction).toString()");
        return string;
    }

    private static final <T> void contentDeepToStringInternal$ArraysKt__ArraysKt(T[] arrT, StringBuilder stringBuilder, List<Object[]> list) {
        if (list.contains(arrT)) {
            stringBuilder.append("[...]");
            return;
        }
        list.add(arrT);
        stringBuilder.append('[');
        int n2 = arrT.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            boolean bl;
            Object[] arrobject;
            T t2;
            T t3;
            if (i2 != 0) {
                stringBuilder.append(", ");
            }
            if ((t3 = (t2 = arrT[i2])) == null) {
                stringBuilder.append("null");
                continue;
            }
            if (t3 instanceof Object[]) {
                ArraysKt__ArraysKt.contentDeepToStringInternal$ArraysKt__ArraysKt((Object[])t2, stringBuilder, list);
                continue;
            }
            if (t3 instanceof byte[]) {
                arrobject = (byte[])t2;
                bl = false;
                String string = Arrays.toString(arrobject);
                Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
                stringBuilder.append(string);
                continue;
            }
            if (t3 instanceof short[]) {
                arrobject = (short[])t2;
                bl = false;
                String string = Arrays.toString((short[])arrobject);
                Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
                stringBuilder.append(string);
                continue;
            }
            if (t3 instanceof int[]) {
                arrobject = (int[])t2;
                bl = false;
                String string = Arrays.toString((int[])arrobject);
                Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
                stringBuilder.append(string);
                continue;
            }
            if (t3 instanceof long[]) {
                arrobject = (long[])t2;
                bl = false;
                String string = Arrays.toString((long[])arrobject);
                Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
                stringBuilder.append(string);
                continue;
            }
            if (t3 instanceof float[]) {
                arrobject = (float[])t2;
                bl = false;
                String string = Arrays.toString((float[])arrobject);
                Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
                stringBuilder.append(string);
                continue;
            }
            if (t3 instanceof double[]) {
                arrobject = (double[])t2;
                bl = false;
                String string = Arrays.toString((double[])arrobject);
                Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
                stringBuilder.append(string);
                continue;
            }
            if (t3 instanceof char[]) {
                arrobject = (char[])t2;
                bl = false;
                String string = Arrays.toString((char[])arrobject);
                Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
                stringBuilder.append(string);
                continue;
            }
            if (t3 instanceof boolean[]) {
                arrobject = (boolean[])t2;
                bl = false;
                String string = Arrays.toString((boolean[])arrobject);
                Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
                stringBuilder.append(string);
                continue;
            }
            if (t3 instanceof UByteArray) {
                UByteArray uByteArray = (UByteArray)t2;
                stringBuilder.append(UArraysKt.contentToString-2csIQuQ((byte[])(uByteArray != null ? uByteArray.unbox-impl() : null)));
                continue;
            }
            if (t3 instanceof UShortArray) {
                UShortArray uShortArray = (UShortArray)t2;
                stringBuilder.append(UArraysKt.contentToString-d-6D3K8((short[])(uShortArray != null ? uShortArray.unbox-impl() : null)));
                continue;
            }
            if (t3 instanceof UIntArray) {
                UIntArray uIntArray = (UIntArray)t2;
                stringBuilder.append(UArraysKt.contentToString-XUkPCBk((int[])(uIntArray != null ? uIntArray.unbox-impl() : null)));
                continue;
            }
            if (t3 instanceof ULongArray) {
                ULongArray uLongArray = (ULongArray)t2;
                stringBuilder.append(UArraysKt.contentToString-uLth9ew((long[])(uLongArray != null ? uLongArray.unbox-impl() : null)));
                continue;
            }
            stringBuilder.append(t2.toString());
        }
        stringBuilder.append(']');
        list.remove(CollectionsKt.getLastIndex(list));
    }
}

