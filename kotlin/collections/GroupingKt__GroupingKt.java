/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.Grouping;
import kotlin.collections.GroupingKt__GroupingJVMKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000@\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u009e\u0001\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\b\u00f8\u0001\u0000\u001a\u00b7\u0001\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013\u001aI\u0010\u0014\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0016\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00150\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u0010H\u0007\u00a2\u0006\u0002\u0010\u0016\u001a\u00bf\u0001\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u000526\u0010\u0018\u001a2\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u00192K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001aH\u0087\b\u00f8\u0001\u0000\u001a\u007f\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u001b\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0019H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001c\u001a\u00d8\u0001\u0010\u001d\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u001026\u0010\u0018\u001a2\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u00192K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001aH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001e\u001a\u0093\u0001\u0010\u001d\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102\u0006\u0010\u001b\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0019H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001f\u001a\u008b\u0001\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0\u0001\"\u0004\b\u0000\u0010!\"\b\b\u0001\u0010\u0004*\u0002H!\"\u0004\b\u0002\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H!\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H!0\u001aH\u0087\b\u00f8\u0001\u0000\u001a\u00a4\u0001\u0010\"\u001a\u0002H\u0010\"\u0004\b\u0000\u0010!\"\b\b\u0001\u0010\u0004*\u0002H!\"\u0004\b\u0002\u0010\u0002\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H!\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H!0\u001aH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006$"}, d2={"aggregate", "", "K", "R", "T", "Lkotlin/collections/Grouping;", "operation", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "key", "accumulator", "element", "", "first", "aggregateTo", "M", "", "destination", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function4;)Ljava/util/Map;", "eachCountTo", "", "(Lkotlin/collections/Grouping;Ljava/util/Map;)Ljava/util/Map;", "fold", "initialValueSelector", "Lkotlin/Function2;", "Lkotlin/Function3;", "initialValue", "(Lkotlin/collections/Grouping;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "foldTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "(Lkotlin/collections/Grouping;Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "reduce", "S", "reduceTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "kotlin-stdlib"}, xs="kotlin/collections/GroupingKt")
class GroupingKt__GroupingKt
extends GroupingKt__GroupingJVMKt {
    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T, K, R> Map<K, R> aggregate(@NotNull Grouping<T, ? extends K> grouping, @NotNull Function4<? super K, ? super R, ? super T, ? super Boolean, ? extends R> function4) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(grouping, "$this$aggregate");
        Intrinsics.checkNotNullParameter(function4, "operation");
        Grouping<T, K> grouping2 = grouping;
        boolean bl = false;
        Map map = new LinkedHashMap();
        boolean bl2 = false;
        Iterator<Object> iterator2 = grouping2.sourceIterator();
        boolean bl3 = false;
        Iterator<T> iterator3 = iterator2;
        while (iterator3.hasNext()) {
            T t2;
            Object v2;
            map.put(iterator2, function4.invoke(iterator2, v2, t2, (v2 = map.get(iterator2 = grouping2.keyOf(t2 = iterator3.next()))) == null && !map.containsKey(iterator2)));
        }
        return map;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T, K, R, M extends Map<? super K, R>> M aggregateTo(@NotNull Grouping<T, ? extends K> grouping, @NotNull M m2, @NotNull Function4<? super K, ? super R, ? super T, ? super Boolean, ? extends R> function4) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(grouping, "$this$aggregateTo");
        Intrinsics.checkNotNullParameter(m2, "destination");
        Intrinsics.checkNotNullParameter(function4, "operation");
        Iterator<Object> iterator2 = grouping.sourceIterator();
        boolean bl = false;
        Iterator<T> iterator3 = iterator2;
        while (iterator3.hasNext()) {
            T t2;
            R r2;
            m2.put(iterator2, function4.invoke(iterator2, r2, t2, (r2 = m2.get(iterator2 = grouping.keyOf(t2 = iterator3.next()))) == null && !m2.containsKey(iterator2)));
        }
        return m2;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T, K, R> Map<K, R> fold(@NotNull Grouping<T, ? extends K> grouping, @NotNull Function2<? super K, ? super T, ? extends R> function2, @NotNull Function3<? super K, ? super R, ? super T, ? extends R> function3) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(grouping, "$this$fold");
        Intrinsics.checkNotNullParameter(function2, "initialValueSelector");
        Intrinsics.checkNotNullParameter(function3, "operation");
        Grouping<T, K> grouping2 = grouping;
        boolean bl = false;
        Grouping<T, K> grouping3 = grouping2;
        boolean bl2 = false;
        Map map = new LinkedHashMap();
        boolean bl3 = false;
        Iterator<Object> iterator2 = grouping3.sourceIterator();
        boolean bl4 = false;
        Iterator<T> iterator3 = iterator2;
        while (iterator3.hasNext()) {
            T t2 = iterator3.next();
            iterator2 = grouping3.keyOf(t2);
            Object v2 = map.get(iterator2);
            boolean bl5 = v2 == null && !map.containsKey(iterator2);
            T t3 = t2;
            Object v3 = v2;
            Iterator<Object> iterator4 = iterator2;
            Iterator<Object> iterator5 = iterator2;
            Map map2 = map;
            boolean bl6 = false;
            R r2 = function3.invoke(iterator4, bl5 ? function2.invoke(iterator4, t3) : v3, t3);
            map2.put(iterator5, r2);
        }
        return map;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T, K, R, M extends Map<? super K, R>> M foldTo(@NotNull Grouping<T, ? extends K> grouping, @NotNull M m2, @NotNull Function2<? super K, ? super T, ? extends R> function2, @NotNull Function3<? super K, ? super R, ? super T, ? extends R> function3) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(grouping, "$this$foldTo");
        Intrinsics.checkNotNullParameter(m2, "destination");
        Intrinsics.checkNotNullParameter(function2, "initialValueSelector");
        Intrinsics.checkNotNullParameter(function3, "operation");
        Grouping<T, K> grouping2 = grouping;
        boolean bl = false;
        Iterator<Object> iterator2 = grouping2.sourceIterator();
        boolean bl2 = false;
        Iterator<T> iterator3 = iterator2;
        while (iterator3.hasNext()) {
            T t2 = iterator3.next();
            iterator2 = grouping2.keyOf(t2);
            R r2 = m2.get(iterator2);
            boolean bl3 = r2 == null && !m2.containsKey(iterator2);
            T t3 = t2;
            R r3 = r2;
            Iterator<Object> iterator4 = iterator2;
            Iterator<Object> iterator5 = iterator2;
            M m3 = m2;
            boolean bl4 = false;
            R r4 = function3.invoke(iterator4, bl3 ? function2.invoke(iterator4, t3) : r3, t3);
            m3.put(iterator5, r4);
        }
        return m2;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T, K, R> Map<K, R> fold(@NotNull Grouping<T, ? extends K> grouping, R r2, @NotNull Function2<? super R, ? super T, ? extends R> function2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(grouping, "$this$fold");
        Intrinsics.checkNotNullParameter(function2, "operation");
        Grouping<T, K> grouping2 = grouping;
        boolean bl = false;
        Grouping<T, K> grouping3 = grouping2;
        boolean bl2 = false;
        Map map = new LinkedHashMap();
        boolean bl3 = false;
        Iterator<Object> iterator2 = grouping3.sourceIterator();
        boolean bl4 = false;
        Iterator<T> iterator3 = iterator2;
        while (iterator3.hasNext()) {
            T t2 = iterator3.next();
            iterator2 = grouping3.keyOf(t2);
            Object v2 = map.get(iterator2);
            boolean bl5 = v2 == null && !map.containsKey(iterator2);
            T t3 = t2;
            Object v3 = v2;
            Iterator<Object> iterator4 = iterator2;
            Iterator<Object> iterator5 = iterator2;
            Map map2 = map;
            boolean bl6 = false;
            R r3 = function2.invoke(bl5 ? r2 : v3, t3);
            map2.put(iterator5, r3);
        }
        return map;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T, K, R, M extends Map<? super K, R>> M foldTo(@NotNull Grouping<T, ? extends K> grouping, @NotNull M m2, R r2, @NotNull Function2<? super R, ? super T, ? extends R> function2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(grouping, "$this$foldTo");
        Intrinsics.checkNotNullParameter(m2, "destination");
        Intrinsics.checkNotNullParameter(function2, "operation");
        Grouping<T, K> grouping2 = grouping;
        boolean bl = false;
        Iterator<Object> iterator2 = grouping2.sourceIterator();
        boolean bl2 = false;
        Iterator<T> iterator3 = iterator2;
        while (iterator3.hasNext()) {
            T t2 = iterator3.next();
            iterator2 = grouping2.keyOf(t2);
            R r3 = m2.get(iterator2);
            boolean bl3 = r3 == null && !m2.containsKey(iterator2);
            T t3 = t2;
            R r4 = r3;
            Iterator<Object> iterator4 = iterator2;
            Iterator<Object> iterator5 = iterator2;
            M m3 = m2;
            boolean bl4 = false;
            R r5 = function2.invoke(bl3 ? r2 : r4, t3);
            m3.put(iterator5, r5);
        }
        return m2;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <S, T extends S, K> Map<K, S> reduce(@NotNull Grouping<T, ? extends K> grouping, @NotNull Function3<? super K, ? super S, ? super T, ? extends S> function3) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(grouping, "$this$reduce");
        Intrinsics.checkNotNullParameter(function3, "operation");
        Grouping<T, K> grouping2 = grouping;
        boolean bl = false;
        Grouping<T, K> grouping3 = grouping2;
        boolean bl2 = false;
        Map map = new LinkedHashMap();
        boolean bl3 = false;
        Iterator<Object> iterator2 = grouping3.sourceIterator();
        boolean bl4 = false;
        Iterator<T> iterator3 = iterator2;
        while (iterator3.hasNext()) {
            T t2 = iterator3.next();
            iterator2 = grouping3.keyOf(t2);
            Object v2 = map.get(iterator2);
            boolean bl5 = v2 == null && !map.containsKey(iterator2);
            T t3 = t2;
            Object v3 = v2;
            Iterator<Object> iterator4 = iterator2;
            Iterator<Object> iterator5 = iterator2;
            Map map2 = map;
            boolean bl6 = false;
            T t4 = bl5 ? t3 : function3.invoke(iterator4, v3, t3);
            map2.put(iterator5, t4);
        }
        return map;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <S, T extends S, K, M extends Map<? super K, S>> M reduceTo(@NotNull Grouping<T, ? extends K> grouping, @NotNull M m2, @NotNull Function3<? super K, ? super S, ? super T, ? extends S> function3) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(grouping, "$this$reduceTo");
        Intrinsics.checkNotNullParameter(m2, "destination");
        Intrinsics.checkNotNullParameter(function3, "operation");
        Grouping<T, K> grouping2 = grouping;
        boolean bl = false;
        Iterator<Object> iterator2 = grouping2.sourceIterator();
        boolean bl2 = false;
        Iterator<T> iterator3 = iterator2;
        while (iterator3.hasNext()) {
            T t2 = iterator3.next();
            iterator2 = grouping2.keyOf(t2);
            S s2 = m2.get(iterator2);
            boolean bl3 = s2 == null && !m2.containsKey(iterator2);
            T t3 = t2;
            S s3 = s2;
            Iterator<Object> iterator4 = iterator2;
            Iterator<Object> iterator5 = iterator2;
            M m3 = m2;
            boolean bl4 = false;
            T t4 = bl3 ? t3 : function3.invoke(iterator4, s3, t3);
            m3.put(iterator5, t4);
        }
        return m2;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T, K, M extends Map<? super K, Integer>> M eachCountTo(@NotNull Grouping<T, ? extends K> grouping, @NotNull M m2) {
        Intrinsics.checkNotNullParameter(grouping, "$this$eachCountTo");
        Intrinsics.checkNotNullParameter(m2, "destination");
        Grouping<T, K> grouping2 = grouping;
        Integer n2 = 0;
        boolean bl = false;
        Grouping<T, K> grouping3 = grouping2;
        boolean bl2 = false;
        Iterator<Object> iterator2 = grouping3.sourceIterator();
        boolean bl3 = false;
        Iterator<T> iterator3 = iterator2;
        while (iterator3.hasNext()) {
            T t2 = iterator3.next();
            iterator2 = grouping3.keyOf(t2);
            Integer n3 = m2.get(iterator2);
            boolean bl4 = n3 == null && !m2.containsKey(iterator2);
            T t3 = t2;
            Integer n4 = n3;
            Iterator<Object> iterator4 = iterator2;
            Iterator<Object> iterator5 = iterator2;
            M m3 = m2;
            boolean bl5 = false;
            T t4 = t3;
            int n5 = ((Number)(bl4 ? n2 : n4)).intValue();
            boolean bl6 = false;
            Integer n6 = n5 + 1;
            m3.put(iterator5, (Integer)n6);
        }
        return m2;
    }
}

