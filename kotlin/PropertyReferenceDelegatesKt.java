/*
 * Decompiled with CFR 0.150.
 */
package kotlin;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u00004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0087\n\u00a2\u0006\u0002\u0010\u0007\u001a>\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00010\t2\u0006\u0010\u0003\u001a\u0002H\b2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0087\n\u00a2\u0006\u0002\u0010\n\u001a<\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\r2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u000e\u001a\u0002H\u0001H\u0087\n\u00a2\u0006\u0002\u0010\u000f\u001aF\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00010\u00102\u0006\u0010\u0003\u001a\u0002H\b2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u000e\u001a\u0002H\u0001H\u0087\n\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2={"getValue", "V", "Lkotlin/reflect/KProperty0;", "thisRef", "", "property", "Lkotlin/reflect/KProperty;", "(Lkotlin/reflect/KProperty0;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "T", "Lkotlin/reflect/KProperty1;", "(Lkotlin/reflect/KProperty1;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "Lkotlin/reflect/KMutableProperty0;", "value", "(Lkotlin/reflect/KMutableProperty0;Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "Lkotlin/reflect/KMutableProperty1;", "(Lkotlin/reflect/KMutableProperty1;Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "kotlin-stdlib"})
public final class PropertyReferenceDelegatesKt {
    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final <V> V getValue(KProperty0<? extends V> kProperty0, Object object, KProperty<?> kProperty) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(kProperty0, "$this$getValue");
        return kProperty0.get();
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final <V> void setValue(KMutableProperty0<V> kMutableProperty0, Object object, KProperty<?> kProperty, V v2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(kMutableProperty0, "$this$setValue");
        kMutableProperty0.set(v2);
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final <T, V> V getValue(KProperty1<T, ? extends V> kProperty1, T t2, KProperty<?> kProperty) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(kProperty1, "$this$getValue");
        return kProperty1.get(t2);
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final <T, V> void setValue(KMutableProperty1<T, V> kMutableProperty1, T t2, KProperty<?> kProperty, V v2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(kMutableProperty1, "$this$setValue");
        kMutableProperty1.set(t2, v2);
    }
}

