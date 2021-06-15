/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.ScreenFloatValueRegEx;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringBuilderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000Z\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0005H\u0082\b\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\r\u0010\b\u001a\u00020\t*\u00020\u0003H\u0087\b\u001a\u0015\u0010\b\u001a\u00020\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001a\u000e\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u0003H\u0007\u001a\u0016\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\u0003H\u0087\b\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u000e\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\r\u0010\u0012\u001a\u00020\u0013*\u00020\u0003H\u0087\b\u001a\u0014\u0010\u0012\u001a\u00020\u0013*\u0004\u0018\u00010\u0003H\u0087\b\u00a2\u0006\u0002\b\u0014\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u0015\u001a\u00020\u0016*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0003H\u0087\b\u001a\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0018*\u00020\u0003H\u0007\u00a2\u0006\u0002\u0010\u001a\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0003H\u0087\b\u001a\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001c*\u00020\u0003H\u0007\u00a2\u0006\u0002\u0010\u001e\u001a\r\u0010\u001f\u001a\u00020\u0010*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u001f\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010 \u001a\u00020!*\u00020\u0003H\u0087\b\u001a\u0015\u0010 \u001a\u00020!*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010\"\u001a\u00020#*\u00020\u0003H\u0087\b\u001a\u0015\u0010\"\u001a\u00020#*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020!2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020#2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u00a8\u0006%"}, d2={"screenFloatValue", "T", "str", "", "parse", "Lkotlin/Function1;", "screenFloatValue$StringsKt__StringNumberConversionsJVMKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toBigDecimal", "Ljava/math/BigDecimal;", "mathContext", "Ljava/math/MathContext;", "toBigDecimalOrNull", "toBigInteger", "Ljava/math/BigInteger;", "radix", "", "toBigIntegerOrNull", "toBoolean", "", "toBooleanNullable", "toByte", "", "toDouble", "", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "toFloat", "", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toInt", "toLong", "", "toShort", "", "toString", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__StringNumberConversionsJVMKt
extends StringsKt__StringBuilderKt {
    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final String toString(byte by, int n2) {
        int n3 = 0;
        byte by2 = by;
        int n4 = CharsKt.checkRadix(n2);
        boolean bl = false;
        String string = Integer.toString(by2, CharsKt.checkRadix(n4));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final String toString(short s2, int n2) {
        int n3 = 0;
        short s3 = s2;
        int n4 = CharsKt.checkRadix(n2);
        boolean bl = false;
        String string = Integer.toString(s3, CharsKt.checkRadix(n4));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final String toString(int n2, int n3) {
        int n4 = 0;
        String string = Integer.toString(n2, CharsKt.checkRadix(n3));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final String toString(long l2, int n2) {
        int n3 = 0;
        String string = Long.toString(l2, CharsKt.checkRadix(n2));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Long.toString(this, checkRadix(radix))");
        return string;
    }

    @Deprecated(message="Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince="1.4")
    @InlineOnly
    private static final /* synthetic */ boolean toBoolean(String string) {
        int n2 = 0;
        String string2 = string;
        boolean bl = false;
        return Boolean.parseBoolean(string2);
    }

    @JvmName(name="toBooleanNullable")
    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final boolean toBooleanNullable(String string) {
        int n2 = 0;
        return Boolean.parseBoolean(string);
    }

    @InlineOnly
    private static final byte toByte(String string) {
        int n2 = 0;
        return Byte.parseByte(string);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final byte toByte(String string, int n2) {
        int n3 = 0;
        return Byte.parseByte(string, CharsKt.checkRadix(n2));
    }

    @InlineOnly
    private static final short toShort(String string) {
        int n2 = 0;
        return Short.parseShort(string);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final short toShort(String string, int n2) {
        int n3 = 0;
        return Short.parseShort(string, CharsKt.checkRadix(n2));
    }

    @InlineOnly
    private static final int toInt(String string) {
        int n2 = 0;
        return Integer.parseInt(string);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final int toInt(String string, int n2) {
        int n3 = 0;
        return Integer.parseInt(string, CharsKt.checkRadix(n2));
    }

    @InlineOnly
    private static final long toLong(String string) {
        int n2 = 0;
        return Long.parseLong(string);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final long toLong(String string, int n2) {
        int n3 = 0;
        return Long.parseLong(string, CharsKt.checkRadix(n2));
    }

    @InlineOnly
    private static final float toFloat(String string) {
        int n2 = 0;
        return Float.parseFloat(string);
    }

    @InlineOnly
    private static final double toDouble(String string) {
        int n2 = 0;
        return Double.parseDouble(string);
    }

    @SinceKotlin(version="1.1")
    @Nullable
    public static final Float toFloatOrNull(@NotNull String string) {
        String string2;
        Intrinsics.checkNotNullParameter(string, "$this$toFloatOrNull");
        boolean bl = false;
        try {
            Float f2;
            if (ScreenFloatValueRegEx.value.matches(string)) {
                string2 = string;
                boolean bl2 = false;
                f2 = Float.valueOf(Float.parseFloat(string2));
            } else {
                f2 = null;
            }
            string2 = f2;
        }
        catch (NumberFormatException numberFormatException) {
            string2 = null;
        }
        return string2;
    }

    @SinceKotlin(version="1.1")
    @Nullable
    public static final Double toDoubleOrNull(@NotNull String string) {
        String string2;
        Intrinsics.checkNotNullParameter(string, "$this$toDoubleOrNull");
        boolean bl = false;
        try {
            Double d2;
            if (ScreenFloatValueRegEx.value.matches(string)) {
                string2 = string;
                boolean bl2 = false;
                d2 = Double.parseDouble(string2);
            } else {
                d2 = null;
            }
            string2 = d2;
        }
        catch (NumberFormatException numberFormatException) {
            string2 = null;
        }
        return string2;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(String string) {
        int n2 = 0;
        return new BigInteger(string);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(String string, int n2) {
        int n3 = 0;
        return new BigInteger(string, CharsKt.checkRadix(n2));
    }

    @SinceKotlin(version="1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toBigIntegerOrNull");
        return StringsKt.toBigIntegerOrNull(string, 10);
    }

    @SinceKotlin(version="1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String string, int n2) {
        int n3;
        Intrinsics.checkNotNullParameter(string, "$this$toBigIntegerOrNull");
        CharsKt.checkRadix(n2);
        int n4 = string.length();
        switch (n4) {
            case 0: {
                return null;
            }
            case 1: {
                if (CharsKt.digitOf(string.charAt(0), n2) >= 0) break;
                return null;
            }
            default: {
                int n5;
                int n6 = n4;
                for (n3 = n5 = string.charAt(0) == '-' ? 1 : 0; n3 < n6; ++n3) {
                    if (CharsKt.digitOf(string.charAt(n3), n2) >= 0) continue;
                    return null;
                }
            }
        }
        String string2 = string;
        n3 = 0;
        return new BigInteger(string2, CharsKt.checkRadix(n2));
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(String string) {
        int n2 = 0;
        return new BigDecimal(string);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(String string, MathContext mathContext) {
        int n2 = 0;
        return new BigDecimal(string, mathContext);
    }

    @SinceKotlin(version="1.2")
    @Nullable
    public static final BigDecimal toBigDecimalOrNull(@NotNull String string) {
        String string2;
        Intrinsics.checkNotNullParameter(string, "$this$toBigDecimalOrNull");
        boolean bl = false;
        try {
            BigDecimal bigDecimal;
            if (ScreenFloatValueRegEx.value.matches(string)) {
                string2 = string;
                boolean bl2 = false;
                String string3 = string2;
                boolean bl3 = false;
                bigDecimal = new BigDecimal(string3);
            } else {
                bigDecimal = null;
            }
            string2 = bigDecimal;
        }
        catch (NumberFormatException numberFormatException) {
            string2 = null;
        }
        return string2;
    }

    @SinceKotlin(version="1.2")
    @Nullable
    public static final BigDecimal toBigDecimalOrNull(@NotNull String string, @NotNull MathContext mathContext) {
        BigDecimal bigDecimal;
        Intrinsics.checkNotNullParameter(string, "$this$toBigDecimalOrNull");
        Intrinsics.checkNotNullParameter(mathContext, "mathContext");
        boolean bl = false;
        try {
            BigDecimal bigDecimal2;
            if (ScreenFloatValueRegEx.value.matches(string)) {
                String string2 = string;
                boolean bl2 = false;
                String string3 = string2;
                MathContext mathContext2 = mathContext;
                boolean bl3 = false;
                bigDecimal2 = new BigDecimal(string3, mathContext2);
            } else {
                bigDecimal2 = null;
            }
            bigDecimal = bigDecimal2;
        }
        catch (NumberFormatException numberFormatException) {
            bigDecimal = null;
        }
        return bigDecimal;
    }

    private static final <T> T screenFloatValue$StringsKt__StringNumberConversionsJVMKt(String string, Function1<? super String, ? extends T> function1) {
        Object var3_3;
        int n2 = 0;
        try {
            var3_3 = ScreenFloatValueRegEx.value.matches(string) ? function1.invoke(string) : null;
        }
        catch (NumberFormatException numberFormatException) {
            var3_3 = null;
        }
        return var3_3;
    }
}

