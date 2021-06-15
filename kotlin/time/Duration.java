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
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnitKt;
import kotlin.time.ExperimentalTime;
import kotlin.time.FormatToDecimalsKt;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@JvmInline
@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b4\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0087@\u0018\u0000 \u00a5\u00012\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u00a5\u0001B\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005J%\u0010K\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u00032\u0006\u0010M\u001a\u00020\u0003H\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bN\u0010OJ\u001b\u0010P\u001a\u00020\t2\u0006\u0010Q\u001a\u00020\u0000H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\bR\u0010SJ\u001e\u0010T\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\u000fH\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bV\u0010WJ\u001e\u0010T\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\tH\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bV\u0010XJ\u001b\u0010T\u001a\u00020\u000f2\u0006\u0010Q\u001a\u00020\u0000H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\bY\u0010ZJ\u001a\u0010[\u001a\u00020\\2\b\u0010Q\u001a\u0004\u0018\u00010]H\u00d6\u0003\u00a2\u0006\u0004\b^\u0010_J\u0010\u0010`\u001a\u00020\tH\u00d6\u0001\u00a2\u0006\u0004\ba\u0010\rJ\r\u0010b\u001a\u00020\\\u00a2\u0006\u0004\bc\u0010dJ\u000f\u0010e\u001a\u00020\\H\u0002\u00a2\u0006\u0004\bf\u0010dJ\u000f\u0010g\u001a\u00020\\H\u0002\u00a2\u0006\u0004\bh\u0010dJ\r\u0010i\u001a\u00020\\\u00a2\u0006\u0004\bj\u0010dJ\r\u0010k\u001a\u00020\\\u00a2\u0006\u0004\bl\u0010dJ\r\u0010m\u001a\u00020\\\u00a2\u0006\u0004\bn\u0010dJ\u001b\u0010o\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\u0000H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\bp\u0010qJ\u001b\u0010r\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\u0000H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\bs\u0010qJ\u0017\u0010t\u001a\u00020\t2\u0006\u0010I\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bu\u0010vJ\u001e\u0010w\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\u000fH\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bx\u0010WJ\u001e\u0010w\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\tH\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bx\u0010XJ\u00a3\u0001\u0010y\u001a\u0002Hz\"\u0004\b\u0000\u0010z2y\u0010{\u001au\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b}\u0012\b\b~\u0012\u0004\b\b(\u007f\u0012\u0014\u0012\u00120\t\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0080\u0001\u0012\u0014\u0012\u00120\t\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0081\u0001\u0012\u0014\u0012\u00120\t\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0082\u0001\u0012\u0014\u0012\u00120\t\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0083\u0001\u0012\u0004\u0012\u0002Hz0|H\u0086\b\u00f8\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u008f\u0001\u0010y\u001a\u0002Hz\"\u0004\b\u0000\u0010z2e\u0010{\u001aa\u0012\u0014\u0012\u00120\t\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0080\u0001\u0012\u0014\u0012\u00120\t\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0081\u0001\u0012\u0014\u0012\u00120\t\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0082\u0001\u0012\u0014\u0012\u00120\t\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0083\u0001\u0012\u0004\u0012\u0002Hz0\u0086\u0001H\u0086\b\u00f8\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0006\b\u0084\u0001\u0010\u0087\u0001Jy\u0010y\u001a\u0002Hz\"\u0004\b\u0000\u0010z2O\u0010{\u001aK\u0012\u0014\u0012\u00120\t\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0081\u0001\u0012\u0014\u0012\u00120\t\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0082\u0001\u0012\u0014\u0012\u00120\t\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0083\u0001\u0012\u0004\u0012\u0002Hz0\u0088\u0001H\u0086\b\u00f8\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0006\b\u0084\u0001\u0010\u0089\u0001Jc\u0010y\u001a\u0002Hz\"\u0004\b\u0000\u0010z29\u0010{\u001a5\u0012\u0014\u0012\u00120\u0003\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0082\u0001\u0012\u0014\u0012\u00120\t\u00a2\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0083\u0001\u0012\u0004\u0012\u0002Hz0\u008a\u0001H\u0086\b\u00f8\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0006\b\u0084\u0001\u0010\u008b\u0001J\u001e\u0010\u008c\u0001\u001a\u00020\u000f2\f\u0010\u008d\u0001\u001a\u00070Dj\u0003`\u008e\u0001\u00a2\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J\u001e\u0010\u0091\u0001\u001a\u00020\t2\f\u0010\u008d\u0001\u001a\u00070Dj\u0003`\u008e\u0001\u00a2\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001J\u0011\u0010\u0094\u0001\u001a\u00030\u0095\u0001\u00a2\u0006\u0006\b\u0096\u0001\u0010\u0097\u0001J\u001e\u0010\u0098\u0001\u001a\u00020\u00032\f\u0010\u008d\u0001\u001a\u00070Dj\u0003`\u008e\u0001\u00a2\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001J\u0011\u0010\u009b\u0001\u001a\u00020\u0003H\u0007\u00a2\u0006\u0005\b\u009c\u0001\u0010\u0005J\u0011\u0010\u009d\u0001\u001a\u00020\u0003H\u0007\u00a2\u0006\u0005\b\u009e\u0001\u0010\u0005J\u0013\u0010\u009f\u0001\u001a\u00030\u0095\u0001H\u0016\u00a2\u0006\u0006\b\u00a0\u0001\u0010\u0097\u0001J*\u0010\u009f\u0001\u001a\u00030\u0095\u00012\f\u0010\u008d\u0001\u001a\u00070Dj\u0003`\u008e\u00012\t\b\u0002\u0010\u00a1\u0001\u001a\u00020\t\u00a2\u0006\u0006\b\u00a0\u0001\u0010\u00a2\u0001J\u0018\u0010\u00a3\u0001\u001a\u00020\u0000H\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0005\b\u00a4\u0001\u0010\u0005R\u0017\u0010\u0006\u001a\u00020\u00008F\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0081\u0004\u00a2\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000f8FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000f8FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u000f8FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u000f8FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u001b\u0010\u0012R\u001a\u0010\u001c\u001a\u00020\u000f8FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\u0012R\u001a\u0010\u001f\u001a\u00020\u000f8FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\u0012R\u001a\u0010\"\u001a\u00020\u000f8FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\u0012R\u001a\u0010%\u001a\u00020\u00038FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b&\u0010\u000b\u001a\u0004\b'\u0010\u0005R\u001a\u0010(\u001a\u00020\u00038FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b)\u0010\u000b\u001a\u0004\b*\u0010\u0005R\u001a\u0010+\u001a\u00020\u00038FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b,\u0010\u000b\u001a\u0004\b-\u0010\u0005R\u001a\u0010.\u001a\u00020\u00038FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b/\u0010\u000b\u001a\u0004\b0\u0010\u0005R\u001a\u00101\u001a\u00020\u00038FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b2\u0010\u000b\u001a\u0004\b3\u0010\u0005R\u001a\u00104\u001a\u00020\u00038FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b5\u0010\u000b\u001a\u0004\b6\u0010\u0005R\u001a\u00107\u001a\u00020\u00038FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b8\u0010\u000b\u001a\u0004\b9\u0010\u0005R\u001a\u0010:\u001a\u00020\t8@X\u0081\u0004\u00a2\u0006\f\u0012\u0004\b;\u0010\u000b\u001a\u0004\b<\u0010\rR\u001a\u0010=\u001a\u00020\t8@X\u0081\u0004\u00a2\u0006\f\u0012\u0004\b>\u0010\u000b\u001a\u0004\b?\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010@\u001a\u00020\t8@X\u0081\u0004\u00a2\u0006\f\u0012\u0004\bA\u0010\u000b\u001a\u0004\bB\u0010\rR\u0014\u0010C\u001a\u00020D8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bE\u0010FR\u0015\u0010G\u001a\u00020\t8\u00c2\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bH\u0010\rR\u0014\u0010I\u001a\u00020\u00038BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bJ\u0010\u0005\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u00f8\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u009920\u0001\u00a8\u0006\u00a6\u0001"}, d2={"Lkotlin/time/Duration;", "", "rawValue", "", "constructor-impl", "(J)J", "absoluteValue", "getAbsoluteValue-UwyO8pc", "hoursComponent", "", "getHoursComponent$annotations", "()V", "getHoursComponent-impl", "(J)I", "inDays", "", "getInDays$annotations", "getInDays-impl", "(J)D", "inHours", "getInHours$annotations", "getInHours-impl", "inMicroseconds", "getInMicroseconds$annotations", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds$annotations", "getInMilliseconds-impl", "inMinutes", "getInMinutes$annotations", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds$annotations", "getInNanoseconds-impl", "inSeconds", "getInSeconds$annotations", "getInSeconds-impl", "inWholeDays", "getInWholeDays$annotations", "getInWholeDays-impl", "inWholeHours", "getInWholeHours$annotations", "getInWholeHours-impl", "inWholeMicroseconds", "getInWholeMicroseconds$annotations", "getInWholeMicroseconds-impl", "inWholeMilliseconds", "getInWholeMilliseconds$annotations", "getInWholeMilliseconds-impl", "inWholeMinutes", "getInWholeMinutes$annotations", "getInWholeMinutes-impl", "inWholeNanoseconds", "getInWholeNanoseconds$annotations", "getInWholeNanoseconds-impl", "inWholeSeconds", "getInWholeSeconds$annotations", "getInWholeSeconds-impl", "minutesComponent", "getMinutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "getNanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "getSecondsComponent$annotations", "getSecondsComponent-impl", "storageUnit", "Ljava/util/concurrent/TimeUnit;", "getStorageUnit-impl", "(J)Ljava/util/concurrent/TimeUnit;", "unitDiscriminator", "getUnitDiscriminator-impl", "value", "getValue-impl", "addValuesMixedRanges", "thisMillis", "otherNanos", "addValuesMixedRanges-UwyO8pc", "(JJJ)J", "compareTo", "other", "compareTo-LRDsOJo", "(JJ)I", "div", "scale", "div-UwyO8pc", "(JD)J", "(JI)J", "div-LRDsOJo", "(JJ)D", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "isFinite", "isFinite-impl", "(J)Z", "isInMillis", "isInMillis-impl", "isInNanos", "isInNanos-impl", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "(JJ)J", "plus", "plus-LRDsOJo", "precision", "precision-impl", "(JD)I", "times", "times-UwyO8pc", "toComponents", "T", "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(JLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(JLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(JLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "Lkotlin/time/DurationUnit;", "toDouble-impl", "(JLjava/util/concurrent/TimeUnit;)D", "toInt", "toInt-impl", "(JLjava/util/concurrent/TimeUnit;)I", "toIsoString", "", "toIsoString-impl", "(J)Ljava/lang/String;", "toLong", "toLong-impl", "(JLjava/util/concurrent/TimeUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(JLjava/util/concurrent/TimeUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-UwyO8pc", "Companion", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalTime
public final class Duration
implements Comparable<Duration> {
    private final long rawValue;
    private static final long ZERO;
    private static final long INFINITE;
    private static final long NEG_INFINITE;
    @NotNull
    public static final Companion Companion;

    public int compareTo-LRDsOJo(long l2) {
        return Duration.compareTo-LRDsOJo(this.rawValue, l2);
    }

    @PublishedApi
    public static /* synthetic */ void getHoursComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getMinutesComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getSecondsComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getNanosecondsComponent$annotations() {
    }

    @Deprecated(message="Use inWholeDays property instead or convert toDouble(DAYS) if a double value is required.", replaceWith=@ReplaceWith(imports={}, expression="toDouble(DurationUnit.DAYS)"))
    public static /* synthetic */ void getInDays$annotations() {
    }

    @Deprecated(message="Use inWholeHours property instead or convert toDouble(HOURS) if a double value is required.", replaceWith=@ReplaceWith(imports={}, expression="toDouble(DurationUnit.HOURS)"))
    public static /* synthetic */ void getInHours$annotations() {
    }

    @Deprecated(message="Use inWholeMinutes property instead or convert toDouble(MINUTES) if a double value is required.", replaceWith=@ReplaceWith(imports={}, expression="toDouble(DurationUnit.MINUTES)"))
    public static /* synthetic */ void getInMinutes$annotations() {
    }

    @Deprecated(message="Use inWholeSeconds property instead or convert toDouble(SECONDS) if a double value is required.", replaceWith=@ReplaceWith(imports={}, expression="toDouble(DurationUnit.SECONDS)"))
    public static /* synthetic */ void getInSeconds$annotations() {
    }

    @Deprecated(message="Use inWholeMilliseconds property instead or convert toDouble(MILLISECONDS) if a double value is required.", replaceWith=@ReplaceWith(imports={}, expression="toDouble(DurationUnit.MILLISECONDS)"))
    public static /* synthetic */ void getInMilliseconds$annotations() {
    }

    @Deprecated(message="Use inWholeMicroseconds property instead or convert toDouble(MICROSECONDS) if a double value is required.", replaceWith=@ReplaceWith(imports={}, expression="toDouble(DurationUnit.MICROSECONDS)"))
    public static /* synthetic */ void getInMicroseconds$annotations() {
    }

    @Deprecated(message="Use inWholeNanoseconds property instead or convert toDouble(NANOSECONDS) if a double value is required.", replaceWith=@ReplaceWith(imports={}, expression="toDouble(DurationUnit.NANOSECONDS)"))
    public static /* synthetic */ void getInNanoseconds$annotations() {
    }

    @SinceKotlin(version="1.5")
    public static /* synthetic */ void getInWholeDays$annotations() {
    }

    @SinceKotlin(version="1.5")
    public static /* synthetic */ void getInWholeHours$annotations() {
    }

    @SinceKotlin(version="1.5")
    public static /* synthetic */ void getInWholeMinutes$annotations() {
    }

    @SinceKotlin(version="1.5")
    public static /* synthetic */ void getInWholeSeconds$annotations() {
    }

    @SinceKotlin(version="1.5")
    public static /* synthetic */ void getInWholeMilliseconds$annotations() {
    }

    @SinceKotlin(version="1.5")
    public static /* synthetic */ void getInWholeMicroseconds$annotations() {
    }

    @SinceKotlin(version="1.5")
    public static /* synthetic */ void getInWholeNanoseconds$annotations() {
    }

    @NotNull
    public String toString() {
        return Duration.toString-impl(this.rawValue);
    }

    private /* synthetic */ Duration(long l2) {
        this.rawValue = l2;
    }

    private static final long getValue-impl(long l2) {
        return l2 >> 1;
    }

    private static final int getUnitDiscriminator-impl(long l2) {
        int n2 = 0;
        return (int)l2 & 1;
    }

    private static final boolean isInNanos-impl(long l2) {
        long l3 = l2;
        boolean bl = false;
        return ((int)l3 & 1) == 0;
    }

    private static final boolean isInMillis-impl(long l2) {
        long l3 = l2;
        boolean bl = false;
        return ((int)l3 & 1) == 1;
    }

    private static final TimeUnit getStorageUnit-impl(long l2) {
        return Duration.isInNanos-impl(l2) ? TimeUnit.NANOSECONDS : TimeUnit.MILLISECONDS;
    }

    public static final long unaryMinus-UwyO8pc(long l2) {
        long l3 = l2;
        boolean bl = false;
        return DurationKt.access$durationOf(-Duration.getValue-impl(l2), (int)l3 & 1);
    }

    public static final long plus-LRDsOJo(long l2, long l3) {
        long l4;
        if (Duration.isInfinite-impl(l2)) {
            if (Duration.isFinite-impl(l3) || (l2 ^ l3) >= 0L) {
                return l2;
            }
            throw (Throwable)new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        }
        if (Duration.isInfinite-impl(l3)) {
            return l3;
        }
        long l5 = l2;
        boolean bl = false;
        int n2 = (int)l5 & 1;
        l5 = l3;
        bl = false;
        if (n2 == ((int)l5 & 1)) {
            l5 = Duration.getValue-impl(l2) + Duration.getValue-impl(l3);
            l4 = Duration.isInNanos-impl(l2) ? DurationKt.access$durationOfNanosNormalized(l5) : DurationKt.access$durationOfMillisNormalized(l5);
        } else {
            l4 = Duration.isInMillis-impl(l2) ? Duration.addValuesMixedRanges-UwyO8pc(l2, Duration.getValue-impl(l2), Duration.getValue-impl(l3)) : Duration.addValuesMixedRanges-UwyO8pc(l2, Duration.getValue-impl(l3), Duration.getValue-impl(l2));
        }
        return l4;
    }

    private static final long addValuesMixedRanges-UwyO8pc(long l2, long l3, long l4) {
        long l5;
        long l6 = DurationKt.access$nanosToMillis(l4);
        long l7 = l3 + l6;
        long l8 = l7;
        if (-4611686018426L <= l8 && 4611686018426L >= l8) {
            l8 = l4 - DurationKt.access$millisToNanos(l6);
            l5 = DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(l7) + l8);
        } else {
            l5 = DurationKt.access$durationOfMillis(RangesKt.coerceIn(l7, -4611686018427387903L, 0x3FFFFFFFFFFFFFFFL));
        }
        return l5;
    }

    public static final long minus-LRDsOJo(long l2, long l3) {
        return Duration.plus-LRDsOJo(l2, Duration.unaryMinus-UwyO8pc(l3));
    }

    public static final long times-UwyO8pc(long l2, int n2) {
        long l3;
        if (Duration.isInfinite-impl(l2)) {
            if (n2 == 0) {
                throw (Throwable)new IllegalArgumentException("Multiplying infinite duration by zero yields an undefined result.");
            }
            return n2 > 0 ? l2 : Duration.unaryMinus-UwyO8pc(l2);
        }
        if (n2 == 0) {
            return ZERO;
        }
        long l4 = Duration.getValue-impl(l2);
        long l5 = l4 * (long)n2;
        if (Duration.isInNanos-impl(l2)) {
            long l6 = l4;
            if (-2147483647L <= l6 && Integer.MAX_VALUE >= l6) {
                l3 = DurationKt.access$durationOfNanos(l5);
            } else if (l5 / (long)n2 == l4) {
                l3 = DurationKt.access$durationOfNanosNormalized(l5);
            } else {
                l6 = DurationKt.access$nanosToMillis(l4);
                long l7 = l4 - DurationKt.access$millisToNanos(l6);
                long l8 = l6 * (long)n2;
                long l9 = l8 + DurationKt.access$nanosToMillis(l7 * (long)n2);
                if (l8 / (long)n2 == l6 && (l9 ^ l8) >= 0L) {
                    long l10 = -4611686018427387903L;
                    l3 = DurationKt.access$durationOfMillis(RangesKt.coerceIn(l9, new LongRange(l10, 0x3FFFFFFFFFFFFFFFL)));
                } else {
                    l3 = MathKt.getSign(l4) * MathKt.getSign(n2) > 0 ? INFINITE : NEG_INFINITE;
                }
            }
        } else if (l5 / (long)n2 == l4) {
            long l11 = -4611686018427387903L;
            l3 = DurationKt.access$durationOfMillis(RangesKt.coerceIn(l5, new LongRange(l11, 0x3FFFFFFFFFFFFFFFL)));
        } else {
            l3 = MathKt.getSign(l4) * MathKt.getSign(n2) > 0 ? INFINITE : NEG_INFINITE;
        }
        return l3;
    }

    public static final long times-UwyO8pc(long l2, double d2) {
        int n2 = MathKt.roundToInt(d2);
        if ((double)n2 == d2) {
            return Duration.times-UwyO8pc(l2, n2);
        }
        TimeUnit timeUnit = Duration.getStorageUnit-impl(l2);
        double d3 = Duration.toDouble-impl(l2, timeUnit) * d2;
        return DurationKt.toDuration(d3, timeUnit);
    }

    public static final long div-UwyO8pc(long l2, int n2) {
        if (n2 == 0) {
            long l3;
            if (Duration.isPositive-impl(l2)) {
                l3 = INFINITE;
            } else if (Duration.isNegative-impl(l2)) {
                l3 = NEG_INFINITE;
            } else {
                throw (Throwable)new IllegalArgumentException("Dividing zero duration by zero yields an undefined result.");
            }
            return l3;
        }
        if (Duration.isInNanos-impl(l2)) {
            return DurationKt.access$durationOfNanos(Duration.getValue-impl(l2) / (long)n2);
        }
        if (Duration.isInfinite-impl(l2)) {
            return Duration.times-UwyO8pc(l2, MathKt.getSign(n2));
        }
        long l4 = Duration.getValue-impl(l2) / (long)n2;
        long l5 = l4;
        if (-4611686018426L <= l5 && 4611686018426L >= l5) {
            l5 = DurationKt.access$millisToNanos(Duration.getValue-impl(l2) - l4 * (long)n2) / (long)n2;
            return DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(l4) + l5);
        }
        return DurationKt.access$durationOfMillis(l4);
    }

    public static final long div-UwyO8pc(long l2, double d2) {
        int n2 = MathKt.roundToInt(d2);
        if ((double)n2 == d2 && n2 != 0) {
            return Duration.div-UwyO8pc(l2, n2);
        }
        TimeUnit timeUnit = Duration.getStorageUnit-impl(l2);
        double d3 = Duration.toDouble-impl(l2, timeUnit) / d2;
        return DurationKt.toDuration(d3, timeUnit);
    }

    public static final double div-LRDsOJo(long l2, long l3) {
        TimeUnit timeUnit = (TimeUnit)((Object)ComparisonsKt.maxOf((Comparable)((Object)Duration.getStorageUnit-impl(l2)), (Comparable)((Object)Duration.getStorageUnit-impl(l3))));
        return Duration.toDouble-impl(l2, timeUnit) / Duration.toDouble-impl(l3, timeUnit);
    }

    public static final boolean isNegative-impl(long l2) {
        return l2 < 0L;
    }

    public static final boolean isPositive-impl(long l2) {
        return l2 > 0L;
    }

    public static final boolean isInfinite-impl(long l2) {
        return l2 == INFINITE || l2 == NEG_INFINITE;
    }

    public static final boolean isFinite-impl(long l2) {
        return !Duration.isInfinite-impl(l2);
    }

    public static final long getAbsoluteValue-UwyO8pc(long l2) {
        return Duration.isNegative-impl(l2) ? Duration.unaryMinus-UwyO8pc(l2) : l2;
    }

    public static int compareTo-LRDsOJo(long l2, long l3) {
        long l4 = l2 ^ l3;
        if (l4 < 0L || ((int)l4 & 1) == 0) {
            return l2 == l3 ? 0 : (l2 < l3 ? -1 : 1);
        }
        long l5 = l2;
        boolean bl = false;
        int n2 = (int)l5 & 1;
        l5 = l3;
        bl = false;
        int n3 = n2 - ((int)l5 & 1);
        return Duration.isNegative-impl(l2) ? -n3 : n3;
    }

    public static final <T> T toComponents-impl(long l2, @NotNull Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> function5) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(function5, "action");
        boolean bl = false;
        return function5.invoke(Duration.toInt-impl(l2, TimeUnit.DAYS), Duration.getHoursComponent-impl(l2), Duration.getMinutesComponent-impl(l2), Duration.getSecondsComponent-impl(l2), Duration.getNanosecondsComponent-impl(l2));
    }

    public static final <T> T toComponents-impl(long l2, @NotNull Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> function4) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(function4, "action");
        boolean bl = false;
        return function4.invoke(Duration.toInt-impl(l2, TimeUnit.HOURS), Duration.getMinutesComponent-impl(l2), Duration.getSecondsComponent-impl(l2), Duration.getNanosecondsComponent-impl(l2));
    }

    public static final <T> T toComponents-impl(long l2, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, ? extends T> function3) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(function3, "action");
        boolean bl = false;
        return function3.invoke(Duration.toInt-impl(l2, TimeUnit.MINUTES), Duration.getSecondsComponent-impl(l2), Duration.getNanosecondsComponent-impl(l2));
    }

    public static final <T> T toComponents-impl(long l2, @NotNull Function2<? super Long, ? super Integer, ? extends T> function2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(function2, "action");
        boolean bl = false;
        return function2.invoke(Duration.getInWholeSeconds-impl(l2), Duration.getNanosecondsComponent-impl(l2));
    }

    public static final int getHoursComponent-impl(long l2) {
        return Duration.isInfinite-impl(l2) ? 0 : (int)(Duration.getInWholeHours-impl(l2) % (long)24);
    }

    public static final int getMinutesComponent-impl(long l2) {
        return Duration.isInfinite-impl(l2) ? 0 : (int)(Duration.getInWholeMinutes-impl(l2) % (long)60);
    }

    public static final int getSecondsComponent-impl(long l2) {
        return Duration.isInfinite-impl(l2) ? 0 : (int)(Duration.getInWholeSeconds-impl(l2) % (long)60);
    }

    public static final int getNanosecondsComponent-impl(long l2) {
        return Duration.isInfinite-impl(l2) ? 0 : (Duration.isInMillis-impl(l2) ? (int)DurationKt.access$millisToNanos(Duration.getValue-impl(l2) % (long)1000) : (int)(Duration.getValue-impl(l2) % (long)1000000000));
    }

    public static final double toDouble-impl(long l2, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter((Object)timeUnit, "unit");
        long l3 = l2;
        return l3 == INFINITE ? Double.POSITIVE_INFINITY : (l3 == NEG_INFINITE ? Double.NEGATIVE_INFINITY : DurationUnitKt.convertDurationUnit((double)Duration.getValue-impl(l2), Duration.getStorageUnit-impl(l2), timeUnit));
    }

    public static final long toLong-impl(long l2, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter((Object)timeUnit, "unit");
        long l3 = l2;
        return l3 == INFINITE ? Long.MAX_VALUE : (l3 == NEG_INFINITE ? Long.MIN_VALUE : DurationUnitKt.convertDurationUnit(Duration.getValue-impl(l2), Duration.getStorageUnit-impl(l2), timeUnit));
    }

    public static final int toInt-impl(long l2, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter((Object)timeUnit, "unit");
        return (int)RangesKt.coerceIn(Duration.toLong-impl(l2, timeUnit), (long)Integer.MIN_VALUE, (long)Integer.MAX_VALUE);
    }

    public static final double getInDays-impl(long l2) {
        return Duration.toDouble-impl(l2, TimeUnit.DAYS);
    }

    public static final double getInHours-impl(long l2) {
        return Duration.toDouble-impl(l2, TimeUnit.HOURS);
    }

    public static final double getInMinutes-impl(long l2) {
        return Duration.toDouble-impl(l2, TimeUnit.MINUTES);
    }

    public static final double getInSeconds-impl(long l2) {
        return Duration.toDouble-impl(l2, TimeUnit.SECONDS);
    }

    public static final double getInMilliseconds-impl(long l2) {
        return Duration.toDouble-impl(l2, TimeUnit.MILLISECONDS);
    }

    public static final double getInMicroseconds-impl(long l2) {
        return Duration.toDouble-impl(l2, TimeUnit.MICROSECONDS);
    }

    public static final double getInNanoseconds-impl(long l2) {
        return Duration.toDouble-impl(l2, TimeUnit.NANOSECONDS);
    }

    public static final long getInWholeDays-impl(long l2) {
        return Duration.toLong-impl(l2, TimeUnit.DAYS);
    }

    public static final long getInWholeHours-impl(long l2) {
        return Duration.toLong-impl(l2, TimeUnit.HOURS);
    }

    public static final long getInWholeMinutes-impl(long l2) {
        return Duration.toLong-impl(l2, TimeUnit.MINUTES);
    }

    public static final long getInWholeSeconds-impl(long l2) {
        return Duration.toLong-impl(l2, TimeUnit.SECONDS);
    }

    public static final long getInWholeMilliseconds-impl(long l2) {
        return Duration.isInMillis-impl(l2) && Duration.isFinite-impl(l2) ? Duration.getValue-impl(l2) : Duration.toLong-impl(l2, TimeUnit.MILLISECONDS);
    }

    public static final long getInWholeMicroseconds-impl(long l2) {
        return Duration.toLong-impl(l2, TimeUnit.MICROSECONDS);
    }

    public static final long getInWholeNanoseconds-impl(long l2) {
        long l3 = Duration.getValue-impl(l2);
        return Duration.isInNanos-impl(l2) ? l3 : (l3 > 9223372036854L ? Long.MAX_VALUE : (l3 < -9223372036854L ? Long.MIN_VALUE : DurationKt.access$millisToNanos(l3)));
    }

    @Deprecated(message="Use inWholeNanoseconds property instead.", replaceWith=@ReplaceWith(imports={}, expression="this.inWholeNanoseconds"))
    public static final long toLongNanoseconds-impl(long l2) {
        return Duration.getInWholeNanoseconds-impl(l2);
    }

    @Deprecated(message="Use inWholeMilliseconds property instead.", replaceWith=@ReplaceWith(imports={}, expression="this.inWholeMilliseconds"))
    public static final long toLongMilliseconds-impl(long l2) {
        return Duration.getInWholeMilliseconds-impl(l2);
    }

    @NotNull
    public static String toString-impl(long l2) {
        String string;
        long l3 = l2;
        if (l3 == 0L) {
            string = "0s";
        } else if (l3 == INFINITE) {
            string = "Infinity";
        } else if (l3 == NEG_INFINITE) {
            string = "-Infinity";
        } else {
            String string2;
            TimeUnit timeUnit;
            boolean bl;
            TimeUnit timeUnit2;
            boolean bl2;
            boolean bl3;
            TimeUnit timeUnit3;
            double d2 = Duration.toDouble-impl(Duration.getAbsoluteValue-UwyO8pc(l2), TimeUnit.NANOSECONDS);
            boolean bl4 = false;
            int n2 = 0;
            if (d2 < 1.0E-6) {
                timeUnit3 = TimeUnit.SECONDS;
                bl3 = false;
                bl2 = false;
                timeUnit2 = timeUnit3;
                bl = false;
                bl4 = true;
                timeUnit = timeUnit3;
            } else if (d2 < 1.0) {
                timeUnit3 = TimeUnit.NANOSECONDS;
                bl3 = false;
                bl2 = false;
                timeUnit2 = timeUnit3;
                bl = false;
                n2 = 7;
                timeUnit = timeUnit3;
            } else if (d2 < 1000.0) {
                timeUnit = TimeUnit.NANOSECONDS;
            } else if (d2 < 1000000.0) {
                timeUnit = TimeUnit.MICROSECONDS;
            } else if (d2 < 1.0E9) {
                timeUnit = TimeUnit.MILLISECONDS;
            } else if (d2 < 1.0E12) {
                timeUnit = TimeUnit.SECONDS;
            } else if (d2 < 6.0E13) {
                timeUnit = TimeUnit.MINUTES;
            } else if (d2 < 3.6E15) {
                timeUnit = TimeUnit.HOURS;
            } else if (d2 < 8.64E20) {
                timeUnit = TimeUnit.DAYS;
            } else {
                timeUnit3 = TimeUnit.DAYS;
                bl3 = false;
                bl2 = false;
                timeUnit2 = timeUnit3;
                bl = false;
                bl4 = true;
                timeUnit = timeUnit3;
            }
            TimeUnit timeUnit4 = timeUnit;
            double d3 = Duration.toDouble-impl(l2, timeUnit4);
            StringBuilder stringBuilder = new StringBuilder();
            if (bl4) {
                string2 = FormatToDecimalsKt.formatScientific(d3);
            } else if (n2 > 0) {
                string2 = FormatToDecimalsKt.formatUpToDecimals(d3, n2);
            } else {
                bl2 = false;
                string2 = FormatToDecimalsKt.formatToExactDecimals(d3, Duration.precision-impl(l2, Math.abs(d3)));
            }
            string = stringBuilder.append(string2).append(DurationUnitKt.shortName(timeUnit4)).toString();
        }
        return string;
    }

    private static final int precision-impl(long l2, double d2) {
        return d2 < 1.0 ? 3 : (d2 < (double)10 ? 2 : (d2 < (double)100 ? 1 : 0));
    }

    @NotNull
    public static final String toString-impl(long l2, @NotNull TimeUnit timeUnit, int n2) {
        double d2;
        Intrinsics.checkNotNullParameter((Object)timeUnit, "unit");
        boolean bl = n2 >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "decimals must be not negative, but was " + n2;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        double d3 = d2 = Duration.toDouble-impl(l2, timeUnit);
        boolean bl5 = false;
        if (Double.isInfinite(d3)) {
            return String.valueOf(d2);
        }
        boolean bl6 = false;
        return (Math.abs(d2) < 1.0E14 ? FormatToDecimalsKt.formatToExactDecimals(d2, RangesKt.coerceAtMost(n2, 12)) : FormatToDecimalsKt.formatScientific(d2)) + DurationUnitKt.shortName(timeUnit);
    }

    public static /* synthetic */ String toString-impl$default(long l2, TimeUnit timeUnit, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 0;
        }
        return Duration.toString-impl(l2, timeUnit, n2);
    }

    @NotNull
    public static final String toIsoString-impl(long l2) {
        boolean bl;
        boolean bl2 = false;
        boolean bl3 = false;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl4 = false;
        boolean bl5 = false;
        StringBuilder stringBuilder2 = stringBuilder;
        boolean bl6 = false;
        if (Duration.isNegative-impl(l2)) {
            stringBuilder2.append('-');
        }
        stringBuilder2.append("PT");
        long l3 = Duration.getAbsoluteValue-UwyO8pc(l2);
        boolean bl7 = false;
        boolean bl8 = false;
        int n2 = Duration.getNanosecondsComponent-impl(l3);
        int n3 = Duration.getSecondsComponent-impl(l3);
        int n4 = Duration.getMinutesComponent-impl(l3);
        int n5 = Duration.toInt-impl(l3, TimeUnit.HOURS);
        boolean bl9 = false;
        boolean bl10 = n5 != 0;
        boolean bl11 = n3 != 0 || n2 != 0;
        boolean bl12 = bl = n4 != 0 || bl11 && bl10;
        if (bl10) {
            stringBuilder2.append(n5).append('H');
        }
        if (bl) {
            stringBuilder2.append(n4).append('M');
        }
        if (bl11 || !bl10 && !bl) {
            stringBuilder2.append(n3);
            if (n2 != 0) {
                stringBuilder2.append('.');
                String string = StringsKt.padStart(String.valueOf(n2), 9, '0');
                if (n2 % 1000000 == 0) {
                    StringBuilder stringBuilder3 = stringBuilder2;
                    int n6 = 0;
                    int n7 = 3;
                    boolean bl13 = false;
                    Intrinsics.checkNotNullExpressionValue(stringBuilder3.append(string, n6, n7), "this.append(value, startIndex, endIndex)");
                } else if (n2 % 1000 == 0) {
                    StringBuilder stringBuilder4 = stringBuilder2;
                    int n8 = 0;
                    int n9 = 6;
                    boolean bl14 = false;
                    Intrinsics.checkNotNullExpressionValue(stringBuilder4.append(string, n8, n9), "this.append(value, startIndex, endIndex)");
                } else {
                    stringBuilder2.append(string);
                }
            }
            stringBuilder2.append('S');
        }
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static long constructor-impl(long l2) {
        if (Duration.isInNanos-impl(l2)) {
            long l3 = Duration.getValue-impl(l2);
            if (-4611686018426999999L > l3 || 4611686018426999999L < l3) {
                throw (Throwable)((Object)new AssertionError((Object)(Duration.getValue-impl(l2) + " ns is out of nanoseconds range")));
            }
        } else {
            long l4 = Duration.getValue-impl(l2);
            if (-4611686018427387903L > l4 || 0x3FFFFFFFFFFFFFFFL < l4) {
                throw (Throwable)((Object)new AssertionError((Object)(Duration.getValue-impl(l2) + " ms is out of milliseconds range")));
            }
            l4 = Duration.getValue-impl(l2);
            if (-4611686018426L <= l4 && 4611686018426L >= l4) {
                throw (Throwable)((Object)new AssertionError((Object)(Duration.getValue-impl(l2) + " ms is denormalized")));
            }
        }
        return l2;
    }

    public static final /* synthetic */ Duration box-impl(long l2) {
        return new Duration(l2);
    }

    public static int hashCode-impl(long l2) {
        long l3 = l2;
        return (int)(l3 ^ l3 >>> 32);
    }

    public static boolean equals-impl(long l2, Object object) {
        long l3;
        return object instanceof Duration && l2 == (l3 = ((Duration)object).unbox-impl());
    }

    public static final boolean equals-impl0(long l2, long l3) {
        return l2 == l3;
    }

    public final /* synthetic */ long unbox-impl() {
        return this.rawValue;
    }

    static {
        Companion = new Companion(null);
        ZERO = Duration.constructor-impl(0L);
        INFINITE = DurationKt.access$durationOfMillis(0x3FFFFFFFFFFFFFFFL);
        NEG_INFINITE = DurationKt.access$durationOfMillis(-4611686018427387903L);
    }

    public int hashCode() {
        return Duration.hashCode-impl(this.rawValue);
    }

    public boolean equals(Object object) {
        return Duration.equals-impl(this.rawValue, object);
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={1, 5, 1}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u00112\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u0011J\u001d\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0017J\u001d\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0019J\u001d\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001b\u0010\u0015J\u001d\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001b\u0010\u0017J\u001d\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001b\u0010\u0019J\u001d\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001d\u0010\u0015J\u001d\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001d\u0010\u0017J\u001d\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001d\u0010\u0019J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001f\u0010\u0015J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001f\u0010\u0017J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001f\u0010\u0019J\u001d\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b!\u0010\u0015J\u001d\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b!\u0010\u0017J\u001d\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b!\u0010\u0019J\u001d\u0010\"\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b#\u0010\u0015J\u001d\u0010\"\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b#\u0010\u0017J\u001d\u0010\"\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b#\u0010\u0019J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b%\u0010\u0015J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b%\u0010\u0017J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b%\u0010\u0019R\u0019\u0010\u0003\u001a\u00020\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u0004X\u0080\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006&"}, d2={"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE-UwyO8pc", "()J", "J", "NEG_INFINITE", "getNEG_INFINITE-UwyO8pc$kotlin_stdlib", "ZERO", "getZERO-UwyO8pc", "convert", "", "value", "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "days", "days-UwyO8pc", "(D)J", "", "(I)J", "", "(J)J", "hours", "hours-UwyO8pc", "microseconds", "microseconds-UwyO8pc", "milliseconds", "milliseconds-UwyO8pc", "minutes", "minutes-UwyO8pc", "nanoseconds", "nanoseconds-UwyO8pc", "seconds", "seconds-UwyO8pc", "kotlin-stdlib"})
    public static final class Companion {
        public final long getZERO-UwyO8pc() {
            return ZERO;
        }

        public final long getINFINITE-UwyO8pc() {
            return INFINITE;
        }

        public final long getNEG_INFINITE-UwyO8pc$kotlin_stdlib() {
            return NEG_INFINITE;
        }

        public final double convert(double d2, @NotNull TimeUnit timeUnit, @NotNull TimeUnit timeUnit2) {
            Intrinsics.checkNotNullParameter((Object)timeUnit, "sourceUnit");
            Intrinsics.checkNotNullParameter((Object)timeUnit2, "targetUnit");
            return DurationUnitKt.convertDurationUnit(d2, timeUnit, timeUnit2);
        }

        @SinceKotlin(version="1.5")
        public final long nanoseconds-UwyO8pc(int n2) {
            return DurationKt.toDuration(n2, TimeUnit.NANOSECONDS);
        }

        @SinceKotlin(version="1.5")
        public final long nanoseconds-UwyO8pc(long l2) {
            return DurationKt.toDuration(l2, TimeUnit.NANOSECONDS);
        }

        @SinceKotlin(version="1.5")
        public final long nanoseconds-UwyO8pc(double d2) {
            return DurationKt.toDuration(d2, TimeUnit.NANOSECONDS);
        }

        @SinceKotlin(version="1.5")
        public final long microseconds-UwyO8pc(int n2) {
            return DurationKt.toDuration(n2, TimeUnit.MICROSECONDS);
        }

        @SinceKotlin(version="1.5")
        public final long microseconds-UwyO8pc(long l2) {
            return DurationKt.toDuration(l2, TimeUnit.MICROSECONDS);
        }

        @SinceKotlin(version="1.5")
        public final long microseconds-UwyO8pc(double d2) {
            return DurationKt.toDuration(d2, TimeUnit.MICROSECONDS);
        }

        @SinceKotlin(version="1.5")
        public final long milliseconds-UwyO8pc(int n2) {
            return DurationKt.toDuration(n2, TimeUnit.MILLISECONDS);
        }

        @SinceKotlin(version="1.5")
        public final long milliseconds-UwyO8pc(long l2) {
            return DurationKt.toDuration(l2, TimeUnit.MILLISECONDS);
        }

        @SinceKotlin(version="1.5")
        public final long milliseconds-UwyO8pc(double d2) {
            return DurationKt.toDuration(d2, TimeUnit.MILLISECONDS);
        }

        @SinceKotlin(version="1.5")
        public final long seconds-UwyO8pc(int n2) {
            return DurationKt.toDuration(n2, TimeUnit.SECONDS);
        }

        @SinceKotlin(version="1.5")
        public final long seconds-UwyO8pc(long l2) {
            return DurationKt.toDuration(l2, TimeUnit.SECONDS);
        }

        @SinceKotlin(version="1.5")
        public final long seconds-UwyO8pc(double d2) {
            return DurationKt.toDuration(d2, TimeUnit.SECONDS);
        }

        @SinceKotlin(version="1.5")
        public final long minutes-UwyO8pc(int n2) {
            return DurationKt.toDuration(n2, TimeUnit.MINUTES);
        }

        @SinceKotlin(version="1.5")
        public final long minutes-UwyO8pc(long l2) {
            return DurationKt.toDuration(l2, TimeUnit.MINUTES);
        }

        @SinceKotlin(version="1.5")
        public final long minutes-UwyO8pc(double d2) {
            return DurationKt.toDuration(d2, TimeUnit.MINUTES);
        }

        @SinceKotlin(version="1.5")
        public final long hours-UwyO8pc(int n2) {
            return DurationKt.toDuration(n2, TimeUnit.HOURS);
        }

        @SinceKotlin(version="1.5")
        public final long hours-UwyO8pc(long l2) {
            return DurationKt.toDuration(l2, TimeUnit.HOURS);
        }

        @SinceKotlin(version="1.5")
        public final long hours-UwyO8pc(double d2) {
            return DurationKt.toDuration(d2, TimeUnit.HOURS);
        }

        @SinceKotlin(version="1.5")
        public final long days-UwyO8pc(int n2) {
            return DurationKt.toDuration(n2, TimeUnit.DAYS);
        }

        @SinceKotlin(version="1.5")
        public final long days-UwyO8pc(long l2) {
            return DurationKt.toDuration(l2, TimeUnit.DAYS);
        }

        @SinceKotlin(version="1.5")
        public final long days-UwyO8pc(double d2) {
            return DurationKt.toDuration(d2, TimeUnit.DAYS);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

