/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.ArrayAsCollection;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.EmptyList;
import kotlin.comparisons.ComparisonsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\u0088\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aC\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u00072\u0006\u0010\f\u001a\u00020\u00062!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u00070\u000eH\u0087\b\u00f8\u0001\u0000\u001aC\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0013\"\u0004\b\u0000\u0010\u00072\u0006\u0010\f\u001a\u00020\u00062!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u00070\u000eH\u0087\b\u00f8\u0001\u0000\u001a\u001f\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u0002H\u00070\u0015j\b\u0012\u0004\u0012\u0002H\u0007`\u0016\"\u0004\b\u0000\u0010\u0007H\u0087\b\u001a5\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u0002H\u00070\u0015j\b\u0012\u0004\u0012\u0002H\u0007`\u0016\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018\"\u0002H\u0007\u00a2\u0006\u0002\u0010\u0019\u001aN\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001b0\b\"\u0004\b\u0000\u0010\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\u001f\b\u0001\u0010\u001d\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001b0\u0013\u0012\u0004\u0012\u00020\u001e0\u000e\u00a2\u0006\u0002\b\u001fH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001aF\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001b0\b\"\u0004\b\u0000\u0010\u001b2\u001f\b\u0001\u0010\u001d\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001b0\u0013\u0012\u0004\u0012\u00020\u001e0\u000e\u00a2\u0006\u0002\b\u001fH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a\u0012\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007\u001a\u0015\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007H\u0087\b\u001a+\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018\"\u0002H\u0007\u00a2\u0006\u0002\u0010\"\u001a%\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\b\b\u0000\u0010\u0007*\u00020$2\b\u0010%\u001a\u0004\u0018\u0001H\u0007\u00a2\u0006\u0002\u0010&\u001a3\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\b\b\u0000\u0010\u0007*\u00020$2\u0016\u0010\u0017\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001H\u00070\u0018\"\u0004\u0018\u0001H\u0007\u00a2\u0006\u0002\u0010\"\u001a\u0015\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0013\"\u0004\b\u0000\u0010\u0007H\u0087\b\u001a+\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0013\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018\"\u0002H\u0007\u00a2\u0006\u0002\u0010\"\u001a%\u0010(\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0002\u00a2\u0006\u0002\b+\u001a\b\u0010,\u001a\u00020\u001eH\u0001\u001a\b\u0010-\u001a\u00020\u001eH\u0001\u001a%\u0010.\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0002\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018H\u0000\u00a2\u0006\u0002\u0010/\u001aS\u00100\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b2\u0006\u0010%\u001a\u0002H\u00072\u001a\u00101\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000702j\n\u0012\u0006\b\u0000\u0012\u0002H\u0007`32\b\b\u0002\u0010)\u001a\u00020\u00062\b\b\u0002\u0010*\u001a\u00020\u0006\u00a2\u0006\u0002\u00104\u001a>\u00100\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b2\b\b\u0002\u0010)\u001a\u00020\u00062\b\b\u0002\u0010*\u001a\u00020\u00062\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00060\u000e\u001aE\u00100\u001a\u00020\u0006\"\u000e\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u000706*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00070\b2\b\u0010%\u001a\u0004\u0018\u0001H\u00072\b\b\u0002\u0010)\u001a\u00020\u00062\b\b\u0002\u0010*\u001a\u00020\u0006\u00a2\u0006\u0002\u00107\u001ag\u00108\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007\"\u000e\b\u0001\u00109*\b\u0012\u0004\u0012\u0002H906*\b\u0012\u0004\u0012\u0002H\u00070\b2\b\u0010:\u001a\u0004\u0018\u0001H92\b\b\u0002\u0010)\u001a\u00020\u00062\b\b\u0002\u0010*\u001a\u00020\u00062\u0016\b\u0004\u0010;\u001a\u0010\u0012\u0004\u0012\u0002H\u0007\u0012\u0006\u0012\u0004\u0018\u0001H90\u000eH\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010<\u001a,\u0010=\u001a\u00020>\"\t\b\u0000\u0010\u0007\u00a2\u0006\u0002\b?*\b\u0012\u0004\u0012\u0002H\u00070\u00022\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0002H\u0087\b\u001a;\u0010@\u001a\u0002HA\"\u0010\b\u0000\u0010B*\u0006\u0012\u0002\b\u00030\u0002*\u0002HA\"\u0004\b\u0001\u0010A*\u0002HB2\f\u0010C\u001a\b\u0012\u0004\u0012\u0002HA0DH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010E\u001a\u0019\u0010F\u001a\u00020>\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0002H\u0087\b\u001a,\u0010G\u001a\u00020>\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\u001e\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\bH\u0000\u001a!\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0002\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u0002H\u0087\b\u001a!\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\bH\u0087\b\u001a&\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070K2\u0006\u0010L\u001a\u00020MH\u0007\"\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"!\u0010\u0005\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b8F\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006N"}, d2={"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/util/Collection;)Lkotlin/ranges/IntRange;", "lastIndex", "", "T", "", "getLastIndex", "(Ljava/util/List;)I", "List", "size", "init", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "index", "MutableList", "", "arrayListOf", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "elements", "", "([Ljava/lang/Object;)Ljava/util/ArrayList;", "buildList", "E", "capacity", "builderAction", "", "Lkotlin/ExtensionFunctionType;", "emptyList", "listOf", "([Ljava/lang/Object;)Ljava/util/List;", "listOfNotNull", "", "element", "(Ljava/lang/Object;)Ljava/util/List;", "mutableListOf", "rangeCheck", "fromIndex", "toIndex", "rangeCheck$CollectionsKt__CollectionsKt", "throwCountOverflow", "throwIndexOverflow", "asCollection", "([Ljava/lang/Object;)Ljava/util/Collection;", "binarySearch", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;II)I", "comparison", "", "(Ljava/util/List;Ljava/lang/Comparable;II)I", "binarySearchBy", "K", "key", "selector", "(Ljava/util/List;Ljava/lang/Comparable;IILkotlin/jvm/functions/Function1;)I", "containsAll", "", "Lkotlin/internal/OnlyInputTypes;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "isNullOrEmpty", "optimizeReadOnlyList", "orEmpty", "shuffled", "", "random", "Lkotlin/random/Random;", "kotlin-stdlib"}, xs="kotlin/collections/CollectionsKt")
class CollectionsKt__CollectionsKt
extends CollectionsKt__CollectionsJVMKt {
    @NotNull
    public static final <T> Collection<T> asCollection(@NotNull T[] arrT) {
        Intrinsics.checkNotNullParameter(arrT, "$this$asCollection");
        return new ArrayAsCollection<T>(arrT, false);
    }

    @NotNull
    public static final <T> List<T> emptyList() {
        return EmptyList.INSTANCE;
    }

    @NotNull
    public static final <T> List<T> listOf(T ... arrT) {
        Intrinsics.checkNotNullParameter(arrT, "elements");
        return arrT.length > 0 ? ArraysKt.asList(arrT) : CollectionsKt.emptyList();
    }

    @InlineOnly
    private static final <T> List<T> listOf() {
        int n2 = 0;
        return CollectionsKt.emptyList();
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <T> List<T> mutableListOf() {
        int n2 = 0;
        return new ArrayList();
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <T> ArrayList<T> arrayListOf() {
        int n2 = 0;
        return new ArrayList();
    }

    @NotNull
    public static final <T> List<T> mutableListOf(T ... arrT) {
        Intrinsics.checkNotNullParameter(arrT, "elements");
        return arrT.length == 0 ? (List)new ArrayList() : (List)new ArrayList((Collection)new ArrayAsCollection<T>(arrT, true));
    }

    @NotNull
    public static final <T> ArrayList<T> arrayListOf(T ... arrT) {
        Intrinsics.checkNotNullParameter(arrT, "elements");
        return arrT.length == 0 ? new ArrayList() : new ArrayList((Collection)new ArrayAsCollection<T>(arrT, true));
    }

    @NotNull
    public static final <T> List<T> listOfNotNull(@Nullable T t2) {
        return t2 != null ? CollectionsKt.listOf(t2) : CollectionsKt.emptyList();
    }

    @NotNull
    public static final <T> List<T> listOfNotNull(T ... arrT) {
        Intrinsics.checkNotNullParameter(arrT, "elements");
        return ArraysKt.filterNotNull(arrT);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <T> List<T> List(int n2, Function1<? super Integer, ? extends T> function1) {
        int n3 = 0;
        boolean bl = false;
        ArrayList<T> arrayList = new ArrayList<T>(n2);
        boolean bl2 = false;
        int n4 = 0;
        n4 = 0;
        int n5 = n2;
        while (n4 < n5) {
            int n6 = n4++;
            boolean bl3 = false;
            arrayList.add(function1.invoke(n6));
        }
        return arrayList;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <T> List<T> MutableList(int n2, Function1<? super Integer, ? extends T> function1) {
        int n3 = 0;
        ArrayList<T> arrayList = new ArrayList<T>(n2);
        boolean bl = false;
        int n4 = 0;
        n4 = 0;
        int n5 = n2;
        while (n4 < n5) {
            int n6 = n4++;
            boolean bl2 = false;
            arrayList.add(function1.invoke(n6));
        }
        return arrayList;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <E> List<E> buildList(@BuilderInference Function1<? super List<E>, Unit> function1) {
        int n2 = 0;
        boolean bl = false;
        bl = false;
        List list = CollectionsKt.createListBuilder();
        boolean bl2 = false;
        boolean bl3 = false;
        function1.invoke(list);
        return CollectionsKt.build(list);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <E> List<E> buildList(int n2, @BuilderInference Function1<? super List<E>, Unit> function1) {
        int n3 = 0;
        boolean bl = false;
        bl = false;
        List list = CollectionsKt.createListBuilder(n2);
        boolean bl2 = false;
        boolean bl3 = false;
        function1.invoke(list);
        return CollectionsKt.build(list);
    }

    @NotNull
    public static final IntRange getIndices(@NotNull Collection<?> collection) {
        Intrinsics.checkNotNullParameter(collection, "$this$indices");
        int n2 = 0;
        return new IntRange(n2, collection.size() - 1);
    }

    public static final <T> int getLastIndex(@NotNull List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "$this$lastIndex");
        return list.size() - 1;
    }

    @InlineOnly
    private static final <T> boolean isNotEmpty(Collection<? extends T> collection) {
        int n2 = 0;
        return !collection.isEmpty();
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <T> boolean isNullOrEmpty(Collection<? extends T> collection) {
        int n2 = 0;
        boolean bl = false;
        return collection == null || collection.isEmpty();
    }

    @InlineOnly
    private static final <T> Collection<T> orEmpty(Collection<? extends T> collection) {
        int n2 = 0;
        Collection collection2 = collection;
        if (collection2 == null) {
            collection2 = CollectionsKt.emptyList();
        }
        return collection2;
    }

    @InlineOnly
    private static final <T> List<T> orEmpty(List<? extends T> list) {
        int n2 = 0;
        List<Object> list2 = list;
        if (list2 == null) {
            list2 = CollectionsKt.emptyList();
        }
        return list2;
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <C extends Collection<?> & R, R> R ifEmpty(C c2, Function0<? extends R> function0) {
        int n2 = 0;
        return (R)(c2.isEmpty() ? function0.invoke() : c2);
    }

    @InlineOnly
    private static final <T> boolean containsAll(Collection<? extends T> collection, Collection<? extends T> collection2) {
        int n2 = 0;
        return collection.containsAll(collection2);
    }

    @SinceKotlin(version="1.3")
    @NotNull
    public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> iterable, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(iterable, "$this$shuffled");
        Intrinsics.checkNotNullParameter(random, "random");
        List<? extends T> list = CollectionsKt.toMutableList(iterable);
        boolean bl = false;
        boolean bl2 = false;
        List<? extends T> list2 = list;
        boolean bl3 = false;
        CollectionsKt.shuffle(list2, random);
        return list;
    }

    @NotNull
    public static final <T> List<T> optimizeReadOnlyList(@NotNull List<? extends T> list) {
        List<T> list2;
        Intrinsics.checkNotNullParameter(list, "$this$optimizeReadOnlyList");
        switch (list.size()) {
            case 0: {
                list2 = CollectionsKt.emptyList();
                break;
            }
            case 1: {
                list2 = CollectionsKt.listOf(list.get(0));
                break;
            }
            default: {
                list2 = list;
            }
        }
        return list2;
    }

    public static final <T extends Comparable<? super T>> int binarySearch(@NotNull List<? extends T> list, @Nullable T t2, int n2, int n3) {
        Intrinsics.checkNotNullParameter(list, "$this$binarySearch");
        CollectionsKt__CollectionsKt.rangeCheck$CollectionsKt__CollectionsKt(list.size(), n2, n3);
        int n4 = n2;
        int n5 = n3 - 1;
        while (n4 <= n5) {
            int n6 = n4 + n5 >>> 1;
            Comparable comparable = (Comparable)list.get(n6);
            int n7 = ComparisonsKt.compareValues(comparable, t2);
            if (n7 < 0) {
                n4 = n6 + 1;
                continue;
            }
            if (n7 > 0) {
                n5 = n6 - 1;
                continue;
            }
            return n6;
        }
        return -(n4 + 1);
    }

    public static /* synthetic */ int binarySearch$default(List list, Comparable comparable, int n2, int n3, int n4, Object object) {
        if ((n4 & 2) != 0) {
            n2 = 0;
        }
        if ((n4 & 4) != 0) {
            n3 = list.size();
        }
        return CollectionsKt.binarySearch(list, comparable, n2, n3);
    }

    public static final <T> int binarySearch(@NotNull List<? extends T> list, T t2, @NotNull Comparator<? super T> comparator, int n2, int n3) {
        Intrinsics.checkNotNullParameter(list, "$this$binarySearch");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        CollectionsKt__CollectionsKt.rangeCheck$CollectionsKt__CollectionsKt(list.size(), n2, n3);
        int n4 = n2;
        int n5 = n3 - 1;
        while (n4 <= n5) {
            int n6 = n4 + n5 >>> 1;
            T t3 = list.get(n6);
            int n7 = comparator.compare(t3, t2);
            if (n7 < 0) {
                n4 = n6 + 1;
                continue;
            }
            if (n7 > 0) {
                n5 = n6 - 1;
                continue;
            }
            return n6;
        }
        return -(n4 + 1);
    }

    public static /* synthetic */ int binarySearch$default(List list, Object object, Comparator comparator, int n2, int n3, int n4, Object object2) {
        if ((n4 & 4) != 0) {
            n2 = 0;
        }
        if ((n4 & 8) != 0) {
            n3 = list.size();
        }
        return CollectionsKt.binarySearch(list, object, comparator, n2, n3);
    }

    public static final <T, K extends Comparable<? super K>> int binarySearchBy(@NotNull List<? extends T> list, @Nullable K k2, int n2, int n3, @NotNull Function1<? super T, ? extends K> function1) {
        int n4 = 0;
        Intrinsics.checkNotNullParameter(list, "$this$binarySearchBy");
        Intrinsics.checkNotNullParameter(function1, "selector");
        return CollectionsKt.binarySearch(list, n2, n3, new Function1<T, Integer>(function1, k2){
            final /* synthetic */ Function1 $selector;
            final /* synthetic */ Comparable $key;

            public final int invoke(T t2) {
                return ComparisonsKt.compareValues((Comparable)this.$selector.invoke(t2), this.$key);
            }
            {
                this.$selector = function1;
                this.$key = comparable;
                super(1);
            }
        });
    }

    public static /* synthetic */ int binarySearchBy$default(List list, Comparable comparable, int n2, int n3, Function1 function1, int n4, Object object) {
        if ((n4 & 2) != 0) {
            n2 = 0;
        }
        if ((n4 & 4) != 0) {
            n3 = list.size();
        }
        n4 = 0;
        Intrinsics.checkNotNullParameter(list, "$this$binarySearchBy");
        Intrinsics.checkNotNullParameter(function1, "selector");
        return CollectionsKt.binarySearch(list, n2, n3, new /* invalid duplicate definition of identical inner class */);
    }

    public static final <T> int binarySearch(@NotNull List<? extends T> list, int n2, int n3, @NotNull Function1<? super T, Integer> function1) {
        Intrinsics.checkNotNullParameter(list, "$this$binarySearch");
        Intrinsics.checkNotNullParameter(function1, "comparison");
        CollectionsKt__CollectionsKt.rangeCheck$CollectionsKt__CollectionsKt(list.size(), n2, n3);
        int n4 = n2;
        int n5 = n3 - 1;
        while (n4 <= n5) {
            int n6 = n4 + n5 >>> 1;
            T t2 = list.get(n6);
            int n7 = ((Number)function1.invoke(t2)).intValue();
            if (n7 < 0) {
                n4 = n6 + 1;
                continue;
            }
            if (n7 > 0) {
                n5 = n6 - 1;
                continue;
            }
            return n6;
        }
        return -(n4 + 1);
    }

    public static /* synthetic */ int binarySearch$default(List list, int n2, int n3, Function1 function1, int n4, Object object) {
        if ((n4 & 1) != 0) {
            n2 = 0;
        }
        if ((n4 & 2) != 0) {
            n3 = list.size();
        }
        return CollectionsKt.binarySearch(list, n2, n3, function1);
    }

    private static final void rangeCheck$CollectionsKt__CollectionsKt(int n2, int n3, int n4) {
        if (n3 > n4) {
            throw (Throwable)new IllegalArgumentException("fromIndex (" + n3 + ") is greater than toIndex (" + n4 + ").");
        }
        if (n3 < 0) {
            throw (Throwable)new IndexOutOfBoundsException("fromIndex (" + n3 + ") is less than zero.");
        }
        if (n4 > n2) {
            throw (Throwable)new IndexOutOfBoundsException("toIndex (" + n4 + ") is greater than size (" + n2 + ").");
        }
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    public static final void throwIndexOverflow() {
        throw (Throwable)new ArithmeticException("Index overflow has happened.");
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    public static final void throwCountOverflow() {
        throw (Throwable)new ArithmeticException("Count overflow has happened.");
    }
}

