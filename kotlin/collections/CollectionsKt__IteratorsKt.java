/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IteratorsJVMKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b\u00f8\u0001\u0000\u001a\u001f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\n\u001a\"\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b0\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\t"}, d2={"forEach", "", "T", "", "operation", "Lkotlin/Function1;", "iterator", "withIndex", "Lkotlin/collections/IndexedValue;", "kotlin-stdlib"}, xs="kotlin/collections/CollectionsKt")
class CollectionsKt__IteratorsKt
extends CollectionsKt__IteratorsJVMKt {
    @InlineOnly
    private static final <T> Iterator<T> iterator(Iterator<? extends T> iterator2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(iterator2, "$this$iterator");
        return iterator2;
    }

    @NotNull
    public static final <T> Iterator<IndexedValue<T>> withIndex(@NotNull Iterator<? extends T> iterator2) {
        Intrinsics.checkNotNullParameter(iterator2, "$this$withIndex");
        return new IndexingIterator<T>(iterator2);
    }

    public static final <T> void forEach(@NotNull Iterator<? extends T> iterator2, @NotNull Function1<? super T, Unit> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(iterator2, "$this$forEach");
        Intrinsics.checkNotNullParameter(function1, "operation");
        Iterator<T> iterator3 = iterator2;
        boolean bl = false;
        Iterator<T> iterator4 = iterator3;
        while (iterator4.hasNext()) {
            T t2 = iterator4.next();
            function1.invoke(t2);
        }
    }
}

