/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.reflect;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a+\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0002\u0010\u0005\u001a-\u0010\u0006\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\u0007"}, d2={"cast", "T", "", "Lkotlin/reflect/KClass;", "value", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Ljava/lang/Object;", "safeCast", "kotlin-stdlib"})
@JvmName(name="KClasses")
public final class KClasses {
    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @LowPriorityInOverloadResolution
    @NotNull
    public static final <T> T cast(@NotNull KClass<T> kClass, @Nullable Object object) {
        Intrinsics.checkNotNullParameter(kClass, "$this$cast");
        if (!kClass.isInstance(object)) {
            KClass<T> kClass2 = kClass;
            boolean bl = false;
            throw (Throwable)new ClassCastException("Value cannot be cast to " + kClass2.getQualifiedName());
        }
        Object object2 = object;
        if (object2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type T");
        }
        return (T)object2;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @LowPriorityInOverloadResolution
    @Nullable
    public static final <T> T safeCast(@NotNull KClass<T> kClass, @Nullable Object object) {
        Object object2;
        Intrinsics.checkNotNullParameter(kClass, "$this$safeCast");
        if (kClass.isInstance(object)) {
            object2 = object;
            if (object2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type T");
            }
        } else {
            object2 = null;
        }
        return (T)object2;
    }
}

