/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ExperimentalTime;
import kotlin.time.TimeMark;
import kotlin.time.TimeSource;
import kotlin.time.TimedValue;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\u0005\u001a3\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0087\b\u00f8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\n\u001a7\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0087\b\u00f8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001\u00a8\u0006\u000b"}, d2={"measureTime", "Lkotlin/time/Duration;", "block", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)J", "measureTimedValue", "Lkotlin/time/TimedValue;", "T", "Lkotlin/time/TimeSource;", "(Lkotlin/time/TimeSource;Lkotlin/jvm/functions/Function0;)J", "kotlin-stdlib"})
public final class MeasureTimeKt {
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static final long measureTime(@NotNull Function0<Unit> function0) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(function0, "block");
        boolean bl = false;
        TimeSource timeSource = TimeSource.Monotonic.INSTANCE;
        boolean bl2 = false;
        boolean bl3 = false;
        TimeMark timeMark = timeSource.markNow();
        function0.invoke();
        return timeMark.elapsedNow-UwyO8pc();
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static final long measureTime(@NotNull TimeSource timeSource, @NotNull Function0<Unit> function0) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(timeSource, "$this$measureTime");
        Intrinsics.checkNotNullParameter(function0, "block");
        boolean bl = false;
        TimeMark timeMark = timeSource.markNow();
        function0.invoke();
        return timeMark.elapsedNow-UwyO8pc();
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    @NotNull
    public static final <T> TimedValue<T> measureTimedValue(@NotNull Function0<? extends T> function0) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(function0, "block");
        boolean bl = false;
        TimeSource timeSource = TimeSource.Monotonic.INSTANCE;
        boolean bl2 = false;
        boolean bl3 = false;
        TimeMark timeMark = timeSource.markNow();
        T t2 = function0.invoke();
        return new TimedValue(t2, timeMark.elapsedNow-UwyO8pc(), null);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    @NotNull
    public static final <T> TimedValue<T> measureTimedValue(@NotNull TimeSource timeSource, @NotNull Function0<? extends T> function0) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(timeSource, "$this$measureTimedValue");
        Intrinsics.checkNotNullParameter(function0, "block");
        boolean bl = false;
        TimeMark timeMark = timeSource.markNow();
        T t2 = function0.invoke();
        return new TimedValue(t2, timeMark.elapsedNow-UwyO8pc(), null);
    }
}

