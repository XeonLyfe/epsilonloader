/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.EmptySet;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000J\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\u001aN\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u001f\b\u0001\u0010\u0005\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0002\b\tH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001aF\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u001f\b\u0001\u0010\u0005\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0002\b\tH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a\u0012\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000b\u001a\u001f\u0010\f\u001a\u0012\u0012\u0004\u0012\u0002H\u000b0\rj\b\u0012\u0004\u0012\u0002H\u000b`\u000e\"\u0004\b\u0000\u0010\u000bH\u0087\b\u001a5\u0010\f\u001a\u0012\u0012\u0004\u0012\u0002H\u000b0\rj\b\u0012\u0004\u0012\u0002H\u000b`\u000e\"\u0004\b\u0000\u0010\u000b2\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000b0\u0010\"\u0002H\u000b\u00a2\u0006\u0002\u0010\u0011\u001a\u001f\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u0002H\u000b0\u0013j\b\u0012\u0004\u0012\u0002H\u000b`\u0014\"\u0004\b\u0000\u0010\u000bH\u0087\b\u001a5\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u0002H\u000b0\u0013j\b\u0012\u0004\u0012\u0002H\u000b`\u0014\"\u0004\b\u0000\u0010\u000b2\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000b0\u0010\"\u0002H\u000b\u00a2\u0006\u0002\u0010\u0015\u001a\u0015\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0007\"\u0004\b\u0000\u0010\u000bH\u0087\b\u001a+\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0007\"\u0004\b\u0000\u0010\u000b2\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000b0\u0010\"\u0002H\u000b\u00a2\u0006\u0002\u0010\u0017\u001a\u0015\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000bH\u0087\b\u001a+\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000b2\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000b0\u0010\"\u0002H\u000b\u00a2\u0006\u0002\u0010\u0017\u001a'\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\b\b\u0000\u0010\u000b*\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u0001H\u000bH\u0007\u00a2\u0006\u0002\u0010\u001c\u001a5\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\b\b\u0000\u0010\u000b*\u00020\u001a2\u0016\u0010\u000f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001H\u000b0\u0010\"\u0004\u0018\u0001H\u000bH\u0007\u00a2\u0006\u0002\u0010\u0017\u001a\u001e\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u0001H\u0000\u001a!\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000b*\n\u0012\u0004\u0012\u0002H\u000b\u0018\u00010\u0001H\u0087\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u001f"}, d2={"buildSet", "", "E", "capacity", "", "builderAction", "Lkotlin/Function1;", "", "", "Lkotlin/ExtensionFunctionType;", "emptySet", "T", "hashSetOf", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "elements", "", "([Ljava/lang/Object;)Ljava/util/HashSet;", "linkedSetOf", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "([Ljava/lang/Object;)Ljava/util/LinkedHashSet;", "mutableSetOf", "([Ljava/lang/Object;)Ljava/util/Set;", "setOf", "setOfNotNull", "", "element", "(Ljava/lang/Object;)Ljava/util/Set;", "optimizeReadOnlySet", "orEmpty", "kotlin-stdlib"}, xs="kotlin/collections/SetsKt")
class SetsKt__SetsKt
extends SetsKt__SetsJVMKt {
    @NotNull
    public static final <T> Set<T> emptySet() {
        return EmptySet.INSTANCE;
    }

    @NotNull
    public static final <T> Set<T> setOf(T ... arrT) {
        Intrinsics.checkNotNullParameter(arrT, "elements");
        return arrT.length > 0 ? ArraysKt.toSet(arrT) : SetsKt.emptySet();
    }

    @InlineOnly
    private static final <T> Set<T> setOf() {
        int n2 = 0;
        return SetsKt.emptySet();
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <T> Set<T> mutableSetOf() {
        int n2 = 0;
        return new LinkedHashSet();
    }

    @NotNull
    public static final <T> Set<T> mutableSetOf(T ... arrT) {
        Intrinsics.checkNotNullParameter(arrT, "elements");
        return (Set)ArraysKt.toCollection(arrT, (Collection)new LinkedHashSet(MapsKt.mapCapacity(arrT.length)));
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <T> HashSet<T> hashSetOf() {
        int n2 = 0;
        return new HashSet();
    }

    @NotNull
    public static final <T> HashSet<T> hashSetOf(T ... arrT) {
        Intrinsics.checkNotNullParameter(arrT, "elements");
        return (HashSet)ArraysKt.toCollection(arrT, (Collection)new HashSet(MapsKt.mapCapacity(arrT.length)));
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <T> LinkedHashSet<T> linkedSetOf() {
        int n2 = 0;
        return new LinkedHashSet();
    }

    @NotNull
    public static final <T> LinkedHashSet<T> linkedSetOf(T ... arrT) {
        Intrinsics.checkNotNullParameter(arrT, "elements");
        return (LinkedHashSet)ArraysKt.toCollection(arrT, (Collection)new LinkedHashSet(MapsKt.mapCapacity(arrT.length)));
    }

    @SinceKotlin(version="1.4")
    @NotNull
    public static final <T> Set<T> setOfNotNull(@Nullable T t2) {
        return t2 != null ? SetsKt.setOf(t2) : SetsKt.emptySet();
    }

    @SinceKotlin(version="1.4")
    @NotNull
    public static final <T> Set<T> setOfNotNull(T ... arrT) {
        Intrinsics.checkNotNullParameter(arrT, "elements");
        return (Set)ArraysKt.filterNotNullTo(arrT, (Collection)new LinkedHashSet());
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <E> Set<E> buildSet(@BuilderInference Function1<? super Set<E>, Unit> function1) {
        int n2 = 0;
        boolean bl = false;
        bl = false;
        Set set = SetsKt.createSetBuilder();
        boolean bl2 = false;
        boolean bl3 = false;
        function1.invoke(set);
        return SetsKt.build(set);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <E> Set<E> buildSet(int n2, @BuilderInference Function1<? super Set<E>, Unit> function1) {
        int n3 = 0;
        boolean bl = false;
        bl = false;
        Set set = SetsKt.createSetBuilder(n2);
        boolean bl2 = false;
        boolean bl3 = false;
        function1.invoke(set);
        return SetsKt.build(set);
    }

    @InlineOnly
    private static final <T> Set<T> orEmpty(Set<? extends T> set) {
        int n2 = 0;
        Set<Object> set2 = set;
        if (set2 == null) {
            set2 = SetsKt.emptySet();
        }
        return set2;
    }

    @NotNull
    public static final <T> Set<T> optimizeReadOnlySet(@NotNull Set<? extends T> set) {
        Set<T> set2;
        Intrinsics.checkNotNullParameter(set, "$this$optimizeReadOnlySet");
        switch (set.size()) {
            case 0: {
                set2 = SetsKt.emptySet();
                break;
            }
            case 1: {
                set2 = SetsKt.setOf(set.iterator().next());
                break;
            }
            default: {
                set2 = set;
            }
        }
        return set2;
    }
}

