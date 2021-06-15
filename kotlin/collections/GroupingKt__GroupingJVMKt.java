/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.collections.Grouping;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000&\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0000\u001a0\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u0005H\u0007\u001aZ\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00072\u001e\u0010\n\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\f\u0012\u0004\u0012\u0002H\b0\u000bH\u0081\b\u00f8\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\r"}, d2={"eachCount", "", "K", "", "T", "Lkotlin/collections/Grouping;", "mapValuesInPlace", "", "R", "V", "f", "Lkotlin/Function1;", "", "kotlin-stdlib"}, xs="kotlin/collections/GroupingKt")
class GroupingKt__GroupingJVMKt {
    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T, K> Map<K, Integer> eachCount(@NotNull Grouping<T, ? extends K> grouping) {
        Serializable serializable;
        Object object;
        boolean bl;
        Object object2;
        Intrinsics.checkNotNullParameter(grouping, "$this$eachCount");
        Object object3 = grouping;
        boolean bl2 = false;
        Map map = new LinkedHashMap();
        boolean bl3 = false;
        Grouping<T, K> grouping2 = object3;
        boolean bl4 = false;
        Iterator<Object> iterator22 = grouping2.sourceIterator();
        boolean bl5 = false;
        Iterator<T> iterator3 = iterator22;
        while (iterator3.hasNext()) {
            Object object4;
            Object object5;
            Object object6;
            object2 = iterator3.next();
            iterator22 = grouping2.keyOf(object2);
            Object v2 = map.get(iterator22);
            bl = v2 == null && !map.containsKey(iterator22);
            T t2 = object2;
            Object v3 = v2;
            Iterator<Object> iterator4 = iterator22;
            Iterator<Object> iterator5 = iterator22;
            Map map2 = map;
            boolean bl6 = false;
            Object object7 = iterator4;
            if (bl) {
                object6 = t2;
                object5 = iterator4;
                object = object7;
                boolean bl7 = false;
                serializable = new Ref.IntRef();
                object7 = object;
                object4 = serializable;
            } else {
                object4 = v3;
            }
            T t3 = t2;
            object6 = (Ref.IntRef)object4;
            object5 = object7;
            boolean bl8 = false;
            Object object8 = object6;
            boolean bl9 = false;
            boolean bl10 = false;
            Object object9 = object8;
            boolean bl11 = false;
            ++((Ref.IntRef)object9).element;
            object5 = object8;
            map2.put(iterator5, object5);
        }
        object3 = map;
        boolean bl12 = false;
        Iterable iterable = object3.entrySet();
        boolean bl13 = false;
        for (Iterator<Object> iterator22 : iterable) {
            Map.Entry entry = (Map.Entry)((Object)iterator22);
            boolean bl14 = false;
            Map.Entry entry2 = entry;
            if (entry2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
            }
            object2 = entry;
            object = TypeIntrinsics.asMutableMapEntry(entry2);
            bl = false;
            serializable = ((Ref.IntRef)object2.getValue()).element;
            object.setValue(serializable);
        }
        return TypeIntrinsics.asMutableMap(object3);
    }

    @PublishedApi
    @InlineOnly
    private static final <K, V, R> Map<K, R> mapValuesInPlace(Map<K, V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        int n2 = 0;
        Iterable iterable = map.entrySet();
        boolean bl = false;
        for (Object t2 : iterable) {
            Map.Entry entry = (Map.Entry)t2;
            boolean bl2 = false;
            Map.Entry entry2 = entry;
            if (entry2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
            }
            TypeIntrinsics.asMutableMapEntry(entry2).setValue(function1.invoke(entry));
        }
        Map<K, V> map2 = map;
        if (map2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, R>");
        }
        return TypeIntrinsics.asMutableMap(map2);
    }
}

