/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.time.Duration;
import kotlin.time.DurationUnitKt;
import kotlin.time.ExperimentalTime;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000(\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b0\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u0005H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&\u001a\u0018\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010\u001a\u0018\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010\u001a\u0018\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010\u001a\u0018\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010\u001a\u0010\u0010/\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u0001H\u0002\u001a\u0010\u00100\u001a\u00020\u00012\u0006\u0010.\u001a\u00020\u0001H\u0002\u001a\u001f\u00101\u001a\u00020\u0007*\u00020\b2\u0006\u00102\u001a\u00020\u0007H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b3\u00104\u001a\u001f\u00101\u001a\u00020\u0007*\u00020\u00052\u0006\u00102\u001a\u00020\u0007H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b5\u00106\u001a \u00107\u001a\u00020\u0007*\u00020\b2\n\u00108\u001a\u000609j\u0002`:H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010;\u001a \u00107\u001a\u00020\u0007*\u00020\u00052\n\u00108\u001a\u000609j\u0002`:H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010<\u001a \u00107\u001a\u00020\u0007*\u00020\u00012\n\u00108\u001a\u000609j\u0002`:H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T\u00a2\u0006\u0002\n\u0000\"!\u0010\u0006\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\t\u0010\r\u001a\u0004\b\u000b\u0010\u000e\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\t\u0010\u000f\u001a\u0004\b\u000b\u0010\u0010\"!\u0010\u0011\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0012\u0010\n\u001a\u0004\b\u0013\u0010\f\"!\u0010\u0011\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000e\"!\u0010\u0011\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0010\"!\u0010\u0014\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\f\"!\u0010\u0014\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0015\u0010\r\u001a\u0004\b\u0016\u0010\u000e\"!\u0010\u0014\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0010\"!\u0010\u0017\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0018\u0010\n\u001a\u0004\b\u0019\u0010\f\"!\u0010\u0017\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0018\u0010\r\u001a\u0004\b\u0019\u0010\u000e\"!\u0010\u0017\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0019\u0010\u0010\"!\u0010\u001a\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001b\u0010\n\u001a\u0004\b\u001c\u0010\f\"!\u0010\u001a\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001b\u0010\r\u001a\u0004\b\u001c\u0010\u000e\"!\u0010\u001a\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u001c\u0010\u0010\"!\u0010\u001d\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001e\u0010\n\u001a\u0004\b\u001f\u0010\f\"!\u0010\u001d\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001e\u0010\r\u001a\u0004\b\u001f\u0010\u000e\"!\u0010\u001d\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001e\u0010\u000f\u001a\u0004\b\u001f\u0010\u0010\"!\u0010 \u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b!\u0010\n\u001a\u0004\b\"\u0010\f\"!\u0010 \u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b!\u0010\r\u001a\u0004\b\"\u0010\u000e\"!\u0010 \u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b!\u0010\u000f\u001a\u0004\b\"\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006>"}, d2={"MAX_MILLIS", "", "MAX_NANOS", "MAX_NANOS_IN_MILLIS", "NANOS_IN_MILLIS", "", "days", "Lkotlin/time/Duration;", "", "getDays$annotations", "(D)V", "getDays", "(D)J", "(I)V", "(I)J", "(J)V", "(J)J", "hours", "getHours$annotations", "getHours", "microseconds", "getMicroseconds$annotations", "getMicroseconds", "milliseconds", "getMilliseconds$annotations", "getMilliseconds", "minutes", "getMinutes$annotations", "getMinutes", "nanoseconds", "getNanoseconds$annotations", "getNanoseconds", "seconds", "getSeconds$annotations", "getSeconds", "durationOf", "normalValue", "unitDiscriminator", "(JI)J", "durationOfMillis", "normalMillis", "durationOfMillisNormalized", "millis", "durationOfNanos", "normalNanos", "durationOfNanosNormalized", "nanos", "millisToNanos", "nanosToMillis", "times", "duration", "times-kIfJnKk", "(DJ)J", "times-mvk6XK0", "(IJ)J", "toDuration", "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "(DLjava/util/concurrent/TimeUnit;)J", "(ILjava/util/concurrent/TimeUnit;)J", "(JLjava/util/concurrent/TimeUnit;)J", "kotlin-stdlib"})
public final class DurationKt {
    public static final int NANOS_IN_MILLIS = 1000000;
    public static final long MAX_NANOS = 4611686018426999999L;
    public static final long MAX_MILLIS = 0x3FFFFFFFFFFFFFFFL;
    private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static final long toDuration(int n2, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter((Object)timeUnit, "unit");
        return timeUnit.compareTo((Enum)TimeUnit.SECONDS) <= 0 ? DurationKt.durationOfNanos(DurationUnitKt.convertDurationUnitOverflow(n2, timeUnit, TimeUnit.NANOSECONDS)) : DurationKt.toDuration((long)n2, timeUnit);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static final long toDuration(long l2, @NotNull TimeUnit timeUnit) {
        long l3;
        Intrinsics.checkNotNullParameter((Object)timeUnit, "unit");
        long l4 = l2;
        if (-l3 <= l4 && (l3 = DurationUnitKt.convertDurationUnitOverflow(4611686018426999999L, TimeUnit.NANOSECONDS, timeUnit)) >= l4) {
            return DurationKt.durationOfNanos(DurationUnitKt.convertDurationUnitOverflow(l2, timeUnit, TimeUnit.NANOSECONDS));
        }
        l4 = DurationUnitKt.convertDurationUnit(l2, timeUnit, TimeUnit.MILLISECONDS);
        return DurationKt.durationOfMillis(RangesKt.coerceIn(l4, -4611686018427387903L, 0x3FFFFFFFFFFFFFFFL));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static final long toDuration(double d2, @NotNull TimeUnit timeUnit) {
        long l2;
        double d3;
        Intrinsics.checkNotNullParameter((Object)timeUnit, "unit");
        double d4 = d3 = DurationUnitKt.convertDurationUnit(d2, timeUnit, TimeUnit.NANOSECONDS);
        boolean bl = false;
        boolean bl2 = !Double.isNaN(d4);
        boolean bl3 = false;
        bl = false;
        if (!bl2) {
            boolean bl4 = false;
            String string = "Duration value cannot be NaN.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        long l3 = (long)d3;
        long l4 = l3;
        if (-4611686018426999999L <= l4 && 4611686018426999999L >= l4) {
            l2 = DurationKt.durationOfNanos(l3);
        } else {
            l4 = (long)DurationUnitKt.convertDurationUnit(d2, timeUnit, TimeUnit.MILLISECONDS);
            l2 = DurationKt.durationOfMillisNormalized(l4);
        }
        return l2;
    }

    @Deprecated(message="Use Duration.nanoseconds() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.nanoseconds(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getNanoseconds$annotations(int n2) {
    }

    public static final long getNanoseconds(int n2) {
        return DurationKt.toDuration(n2, TimeUnit.NANOSECONDS);
    }

    @Deprecated(message="Use Duration.nanoseconds() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.nanoseconds(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getNanoseconds$annotations(long l2) {
    }

    public static final long getNanoseconds(long l2) {
        return DurationKt.toDuration(l2, TimeUnit.NANOSECONDS);
    }

    @Deprecated(message="Use Duration.nanoseconds() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.nanoseconds(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getNanoseconds$annotations(double d2) {
    }

    public static final long getNanoseconds(double d2) {
        return DurationKt.toDuration(d2, TimeUnit.NANOSECONDS);
    }

    @Deprecated(message="Use Duration.microseconds() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.microseconds(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMicroseconds$annotations(int n2) {
    }

    public static final long getMicroseconds(int n2) {
        return DurationKt.toDuration(n2, TimeUnit.MICROSECONDS);
    }

    @Deprecated(message="Use Duration.microseconds() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.microseconds(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMicroseconds$annotations(long l2) {
    }

    public static final long getMicroseconds(long l2) {
        return DurationKt.toDuration(l2, TimeUnit.MICROSECONDS);
    }

    @Deprecated(message="Use Duration.microseconds() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.microseconds(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMicroseconds$annotations(double d2) {
    }

    public static final long getMicroseconds(double d2) {
        return DurationKt.toDuration(d2, TimeUnit.MICROSECONDS);
    }

    @Deprecated(message="Use Duration.milliseconds() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.milliseconds(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMilliseconds$annotations(int n2) {
    }

    public static final long getMilliseconds(int n2) {
        return DurationKt.toDuration(n2, TimeUnit.MILLISECONDS);
    }

    @Deprecated(message="Use Duration.milliseconds() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.milliseconds(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMilliseconds$annotations(long l2) {
    }

    public static final long getMilliseconds(long l2) {
        return DurationKt.toDuration(l2, TimeUnit.MILLISECONDS);
    }

    @Deprecated(message="Use Duration.milliseconds() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.milliseconds(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMilliseconds$annotations(double d2) {
    }

    public static final long getMilliseconds(double d2) {
        return DurationKt.toDuration(d2, TimeUnit.MILLISECONDS);
    }

    @Deprecated(message="Use Duration.seconds() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.seconds(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getSeconds$annotations(int n2) {
    }

    public static final long getSeconds(int n2) {
        return DurationKt.toDuration(n2, TimeUnit.SECONDS);
    }

    @Deprecated(message="Use Duration.seconds() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.seconds(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getSeconds$annotations(long l2) {
    }

    public static final long getSeconds(long l2) {
        return DurationKt.toDuration(l2, TimeUnit.SECONDS);
    }

    @Deprecated(message="Use Duration.seconds() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.seconds(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getSeconds$annotations(double d2) {
    }

    public static final long getSeconds(double d2) {
        return DurationKt.toDuration(d2, TimeUnit.SECONDS);
    }

    @Deprecated(message="Use Duration.minutes() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.minutes(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMinutes$annotations(int n2) {
    }

    public static final long getMinutes(int n2) {
        return DurationKt.toDuration(n2, TimeUnit.MINUTES);
    }

    @Deprecated(message="Use Duration.minutes() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.minutes(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMinutes$annotations(long l2) {
    }

    public static final long getMinutes(long l2) {
        return DurationKt.toDuration(l2, TimeUnit.MINUTES);
    }

    @Deprecated(message="Use Duration.minutes() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.minutes(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMinutes$annotations(double d2) {
    }

    public static final long getMinutes(double d2) {
        return DurationKt.toDuration(d2, TimeUnit.MINUTES);
    }

    @Deprecated(message="Use Duration.hours() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.hours(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getHours$annotations(int n2) {
    }

    public static final long getHours(int n2) {
        return DurationKt.toDuration(n2, TimeUnit.HOURS);
    }

    @Deprecated(message="Use Duration.hours() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.hours(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getHours$annotations(long l2) {
    }

    public static final long getHours(long l2) {
        return DurationKt.toDuration(l2, TimeUnit.HOURS);
    }

    @Deprecated(message="Use Duration.hours() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.hours(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getHours$annotations(double d2) {
    }

    public static final long getHours(double d2) {
        return DurationKt.toDuration(d2, TimeUnit.HOURS);
    }

    @Deprecated(message="Use Duration.days() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.days(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getDays$annotations(int n2) {
    }

    public static final long getDays(int n2) {
        return DurationKt.toDuration(n2, TimeUnit.DAYS);
    }

    @Deprecated(message="Use Duration.days() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.days(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getDays$annotations(long l2) {
    }

    public static final long getDays(long l2) {
        return DurationKt.toDuration(l2, TimeUnit.DAYS);
    }

    @Deprecated(message="Use Duration.days() function instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.Duration"}, expression="Duration.days(this)"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getDays$annotations(double d2) {
    }

    public static final long getDays(double d2) {
        return DurationKt.toDuration(d2, TimeUnit.DAYS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    @InlineOnly
    private static final long times-mvk6XK0(int n2, long l2) {
        int n3 = 0;
        return Duration.times-UwyO8pc(l2, n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    @InlineOnly
    private static final long times-kIfJnKk(double d2, long l2) {
        int n2 = 0;
        return Duration.times-UwyO8pc(l2, d2);
    }

    private static final long nanosToMillis(long l2) {
        return l2 / (long)1000000;
    }

    private static final long millisToNanos(long l2) {
        return l2 * (long)1000000;
    }

    @ExperimentalTime
    private static final long durationOfNanos(long l2) {
        return Duration.constructor-impl(l2 << 1);
    }

    @ExperimentalTime
    private static final long durationOfMillis(long l2) {
        return Duration.constructor-impl((l2 << 1) + 1L);
    }

    @ExperimentalTime
    private static final long durationOf(long l2, int n2) {
        return Duration.constructor-impl((l2 << 1) + (long)n2);
    }

    @ExperimentalTime
    private static final long durationOfNanosNormalized(long l2) {
        long l3 = l2;
        return -4611686018426999999L <= l3 && 4611686018426999999L >= l3 ? DurationKt.durationOfNanos(l2) : DurationKt.durationOfMillis(DurationKt.nanosToMillis(l2));
    }

    @ExperimentalTime
    private static final long durationOfMillisNormalized(long l2) {
        long l3 = l2;
        return -4611686018426L <= l3 && 4611686018426L >= l3 ? DurationKt.durationOfNanos(DurationKt.millisToNanos(l2)) : DurationKt.durationOfMillis(RangesKt.coerceIn(l2, -4611686018427387903L, 0x3FFFFFFFFFFFFFFFL));
    }

    public static final /* synthetic */ long access$durationOf(long l2, int n2) {
        return DurationKt.durationOf(l2, n2);
    }

    public static final /* synthetic */ long access$durationOfNanosNormalized(long l2) {
        return DurationKt.durationOfNanosNormalized(l2);
    }

    public static final /* synthetic */ long access$durationOfMillisNormalized(long l2) {
        return DurationKt.durationOfMillisNormalized(l2);
    }

    public static final /* synthetic */ long access$nanosToMillis(long l2) {
        return DurationKt.nanosToMillis(l2);
    }

    public static final /* synthetic */ long access$millisToNanos(long l2) {
        return DurationKt.millisToNanos(l2);
    }

    public static final /* synthetic */ long access$durationOfNanos(long l2) {
        return DurationKt.durationOfNanos(l2);
    }

    public static final /* synthetic */ long access$durationOfMillis(long l2) {
        return DurationKt.durationOfMillis(l2);
    }
}

