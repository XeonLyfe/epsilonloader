/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ExperimentalTime;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000*\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0001\u001a(\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0001\u001a(\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0001*\u001e\b\u0007\u0010\t\"\u00020\u00042\u00020\u0004B\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\fB\u0002\b\r\u00a8\u0006\u000e"}, d2={"convertDurationUnit", "", "value", "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "", "convertDurationUnitOverflow", "DurationUnit", "Lkotlin/SinceKotlin;", "version", "1.3", "Lkotlin/time/ExperimentalTime;", "kotlin-stdlib"}, xs="kotlin/time/DurationUnitKt")
class DurationUnitKt__DurationUnitJvmKt {
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void DurationUnit$annotations() {
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static final double convertDurationUnit(double d2, @NotNull TimeUnit timeUnit, @NotNull TimeUnit timeUnit2) {
        Intrinsics.checkNotNullParameter((Object)timeUnit, "sourceUnit");
        Intrinsics.checkNotNullParameter((Object)timeUnit2, "targetUnit");
        long l2 = timeUnit2.convert(1L, timeUnit);
        if (l2 > 0L) {
            return d2 * (double)l2;
        }
        long l3 = timeUnit.convert(1L, timeUnit2);
        return d2 / (double)l3;
    }

    @SinceKotlin(version="1.5")
    @ExperimentalTime
    public static final long convertDurationUnitOverflow(long l2, @NotNull TimeUnit timeUnit, @NotNull TimeUnit timeUnit2) {
        Intrinsics.checkNotNullParameter((Object)timeUnit, "sourceUnit");
        Intrinsics.checkNotNullParameter((Object)timeUnit2, "targetUnit");
        return timeUnit2.convert(l2, timeUnit);
    }

    @SinceKotlin(version="1.5")
    @ExperimentalTime
    public static final long convertDurationUnit(long l2, @NotNull TimeUnit timeUnit, @NotNull TimeUnit timeUnit2) {
        Intrinsics.checkNotNullParameter((Object)timeUnit, "sourceUnit");
        Intrinsics.checkNotNullParameter((Object)timeUnit2, "targetUnit");
        return timeUnit2.convert(l2, timeUnit);
    }
}

