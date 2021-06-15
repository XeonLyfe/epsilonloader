/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin;

import kotlin.Lazy;
import kotlin.LazyKt$WhenMappings;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SafePublicationLazyImpl;
import kotlin.SynchronizedLazyImpl;
import kotlin.UnsafeLazyImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u00a8\u0006\t"}, d2={"lazy", "Lkotlin/Lazy;", "T", "initializer", "Lkotlin/Function0;", "lock", "", "mode", "Lkotlin/LazyThreadSafetyMode;", "kotlin-stdlib"}, xs="kotlin/LazyKt")
class LazyKt__LazyJVMKt {
    @NotNull
    public static final <T> Lazy<T> lazy(@NotNull Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(function0, "initializer");
        return new SynchronizedLazyImpl(function0, null, 2, null);
    }

    @NotNull
    public static final <T> Lazy<T> lazy(@NotNull LazyThreadSafetyMode lazyThreadSafetyMode, @NotNull Function0<? extends T> function0) {
        Lazy lazy;
        Intrinsics.checkNotNullParameter((Object)lazyThreadSafetyMode, "mode");
        Intrinsics.checkNotNullParameter(function0, "initializer");
        switch (LazyKt$WhenMappings.$EnumSwitchMapping$0[lazyThreadSafetyMode.ordinal()]) {
            case 1: {
                lazy = new SynchronizedLazyImpl(function0, null, 2, null);
                break;
            }
            case 2: {
                lazy = new SafePublicationLazyImpl<T>(function0);
                break;
            }
            case 3: {
                lazy = new UnsafeLazyImpl<T>(function0);
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
        return lazy;
    }

    @NotNull
    public static final <T> Lazy<T> lazy(@Nullable Object object, @NotNull Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(function0, "initializer");
        return new SynchronizedLazyImpl<T>(function0, object);
    }
}

