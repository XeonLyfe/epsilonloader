/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\u001c\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0002\u00a2\u0006\u0002\u0010\u0004\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002\u00a2\u0006\u0002\u0010\u0007\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001a,\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\b\u00a2\u0006\u0002\u0010\u0004\u001a,\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0002\u00a2\u0006\u0002\u0010\u0004\u001a4\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002\u00a2\u0006\u0002\u0010\u0007\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001a,\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\b\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\r"}, d2={"minus", "", "T", "element", "(Ljava/util/Set;Ljava/lang/Object;)Ljava/util/Set;", "elements", "", "(Ljava/util/Set;[Ljava/lang/Object;)Ljava/util/Set;", "", "Lkotlin/sequences/Sequence;", "minusElement", "plus", "plusElement", "kotlin-stdlib"}, xs="kotlin/collections/SetsKt")
class SetsKt___SetsKt
extends SetsKt__SetsKt {
    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> set, T t2) {
        Intrinsics.checkNotNullParameter(set, "$this$minus");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(set.size()));
        boolean bl = false;
        Iterable iterable = set;
        boolean bl2 = false;
        Iterator iterator2 = iterable.iterator();
        while (iterator2.hasNext()) {
            boolean bl3;
            Object t3;
            Object t4 = t3 = iterator2.next();
            boolean bl4 = false;
            if (!bl && Intrinsics.areEqual(t4, t2)) {
                bl = true;
                bl3 = false;
            } else {
                bl3 = true;
            }
            if (!bl3) continue;
            ((Collection)linkedHashSet).add(t3);
        }
        return (Set)((Collection)linkedHashSet);
    }

    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> set, @NotNull T[] arrT) {
        Intrinsics.checkNotNullParameter(set, "$this$minus");
        Intrinsics.checkNotNullParameter(arrT, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(set);
        CollectionsKt.removeAll((Collection)linkedHashSet, arrT);
        return linkedHashSet;
    }

    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> set, @NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(set, "$this$minus");
        Intrinsics.checkNotNullParameter(iterable, "elements");
        Collection<T> collection = CollectionsKt.convertToSetForSetOperationWith(iterable, (Iterable)set);
        if (collection.isEmpty()) {
            return CollectionsKt.toSet((Iterable)set);
        }
        if (collection instanceof Set) {
            Iterable iterable2 = set;
            Collection collection2 = new LinkedHashSet();
            boolean bl = false;
            Iterator iterator2 = iterable2.iterator();
            while (iterator2.hasNext()) {
                Object t2;
                Object t3 = t2 = iterator2.next();
                boolean bl2 = false;
                if (collection.contains(t3)) continue;
                collection2.add(t2);
            }
            return (Set)collection2;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(set);
        linkedHashSet.removeAll(collection);
        return linkedHashSet;
    }

    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> set, @NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(set, "$this$minus");
        Intrinsics.checkNotNullParameter(sequence, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(set);
        CollectionsKt.removeAll((Collection)linkedHashSet, sequence);
        return linkedHashSet;
    }

    @InlineOnly
    private static final <T> Set<T> minusElement(Set<? extends T> set, T t2) {
        int n2 = 0;
        return SetsKt.minus(set, t2);
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> set, T t2) {
        Intrinsics.checkNotNullParameter(set, "$this$plus");
        LinkedHashSet<T> linkedHashSet = new LinkedHashSet<T>(MapsKt.mapCapacity(set.size() + 1));
        linkedHashSet.addAll((Collection)set);
        linkedHashSet.add(t2);
        return linkedHashSet;
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> set, @NotNull T[] arrT) {
        Intrinsics.checkNotNullParameter(set, "$this$plus");
        Intrinsics.checkNotNullParameter(arrT, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(set.size() + arrT.length));
        linkedHashSet.addAll(set);
        CollectionsKt.addAll((Collection)linkedHashSet, arrT);
        return linkedHashSet;
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> set, @NotNull Iterable<? extends T> iterable) {
        int n2;
        Intrinsics.checkNotNullParameter(set, "$this$plus");
        Intrinsics.checkNotNullParameter(iterable, "elements");
        Integer n3 = CollectionsKt.collectionSizeOrNull(iterable);
        if (n3 != null) {
            int n4;
            Integer n5 = n3;
            boolean bl = false;
            boolean bl2 = false;
            int n6 = ((Number)n5).intValue();
            boolean bl3 = false;
            n2 = n4 = set.size() + n6;
        } else {
            n2 = set.size() * 2;
        }
        int n7 = MapsKt.mapCapacity(n2);
        LinkedHashSet linkedHashSet = new LinkedHashSet(n7);
        linkedHashSet.addAll(set);
        CollectionsKt.addAll((Collection)linkedHashSet, iterable);
        return linkedHashSet;
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> set, @NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(set, "$this$plus");
        Intrinsics.checkNotNullParameter(sequence, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(set.size() * 2));
        linkedHashSet.addAll(set);
        CollectionsKt.addAll((Collection)linkedHashSet, sequence);
        return linkedHashSet;
    }

    @InlineOnly
    private static final <T> Set<T> plusElement(Set<? extends T> set, T t2) {
        int n2 = 0;
        return SetsKt.plus(set, t2);
    }
}

