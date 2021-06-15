/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.collections;

import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.collections.MapWithDefault;
import kotlin.collections.MapWithDefaultImpl;
import kotlin.collections.MapsKt;
import kotlin.collections.MutableMapWithDefault;
import kotlin.collections.MutableMapWithDefaultImpl;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\u001e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0001\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001aQ\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\t\u001aX\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f2!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\tH\u0007\u00a2\u0006\u0002\b\r\u00a8\u0006\u000e"}, d2={"getOrImplicitDefault", "V", "K", "", "key", "getOrImplicitDefaultNullable", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "withDefault", "defaultValue", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "withDefaultMutable", "kotlin-stdlib"}, xs="kotlin/collections/MapsKt")
class MapsKt__MapWithDefaultKt {
    @JvmName(name="getOrImplicitDefaultNullable")
    @PublishedApi
    public static final <K, V> V getOrImplicitDefaultNullable(@NotNull Map<K, ? extends V> map, K k2) {
        Intrinsics.checkNotNullParameter(map, "$this$getOrImplicitDefault");
        if (map instanceof MapWithDefault) {
            return ((MapWithDefault)map).getOrImplicitDefault(k2);
        }
        Map<K, V> map2 = map;
        boolean bl = false;
        V v2 = map2.get(k2);
        if (v2 == null && !map2.containsKey(k2)) {
            boolean bl2 = false;
            throw (Throwable)new NoSuchElementException("Key " + k2 + " is missing in the map.");
        }
        return v2;
    }

    @NotNull
    public static final <K, V> Map<K, V> withDefault(@NotNull Map<K, ? extends V> map, @NotNull Function1<? super K, ? extends V> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$withDefault");
        Intrinsics.checkNotNullParameter(function1, "defaultValue");
        Map<K, ? extends V> map2 = map;
        return map2 instanceof MapWithDefault ? MapsKt.withDefault(((MapWithDefault)map).getMap(), function1) : (Map)new MapWithDefaultImpl<K, V>(map, function1);
    }

    @JvmName(name="withDefaultMutable")
    @NotNull
    public static final <K, V> Map<K, V> withDefaultMutable(@NotNull Map<K, V> map, @NotNull Function1<? super K, ? extends V> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$withDefault");
        Intrinsics.checkNotNullParameter(function1, "defaultValue");
        Map<K, V> map2 = map;
        return map2 instanceof MutableMapWithDefault ? MapsKt.withDefaultMutable(((MutableMapWithDefault)map).getMap(), function1) : (Map)new MutableMapWithDefaultImpl<K, V>(map, function1);
    }
}

