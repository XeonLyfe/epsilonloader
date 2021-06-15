/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.random;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.AbstractPlatformRandom;
import kotlin.random.KotlinRandom;
import kotlin.random.PlatformRandom;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\t\u0010\u0000\u001a\u00020\u0001H\u0081\b\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0001H\u0007\u001a\f\u0010\t\u001a\u00020\u0001*\u00020\bH\u0007\u00a8\u0006\n"}, d2={"defaultPlatformRandom", "Lkotlin/random/Random;", "doubleFromParts", "", "hi26", "", "low27", "asJavaRandom", "Ljava/util/Random;", "asKotlinRandom", "kotlin-stdlib"})
public final class PlatformRandomKt {
    @SinceKotlin(version="1.3")
    @NotNull
    public static final java.util.Random asJavaRandom(@NotNull Random random) {
        Object object;
        Intrinsics.checkNotNullParameter(random, "$this$asJavaRandom");
        Random random2 = random;
        if (!(random2 instanceof AbstractPlatformRandom)) {
            random2 = null;
        }
        if ((object = (AbstractPlatformRandom)random2) == null || (object = ((AbstractPlatformRandom)object).getImpl()) == null) {
            object = new KotlinRandom(random);
        }
        return object;
    }

    @SinceKotlin(version="1.3")
    @NotNull
    public static final Random asKotlinRandom(@NotNull java.util.Random random) {
        Object object;
        Intrinsics.checkNotNullParameter(random, "$this$asKotlinRandom");
        java.util.Random random2 = random;
        if (!(random2 instanceof KotlinRandom)) {
            random2 = null;
        }
        if ((object = (KotlinRandom)random2) == null || (object = ((KotlinRandom)object).getImpl()) == null) {
            object = new PlatformRandom(random);
        }
        return object;
    }

    @InlineOnly
    private static final Random defaultPlatformRandom() {
        int n2 = 0;
        return PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
    }

    public static final double doubleFromParts(int n2, int n3) {
        return (double)(((long)n2 << 27) + (long)n3) / (double)0x20000000000000L;
    }
}

