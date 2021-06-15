/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.time;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000.\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\u001a\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u001a\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0000\"\u001c\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"precisionFormats", "", "Ljava/lang/ThreadLocal;", "Ljava/text/DecimalFormat;", "[Ljava/lang/ThreadLocal;", "rootNegativeExpFormatSymbols", "Ljava/text/DecimalFormatSymbols;", "rootPositiveExpFormatSymbols", "scientificFormat", "createFormatForDecimals", "decimals", "", "formatScientific", "", "value", "", "formatToExactDecimals", "formatUpToDecimals", "kotlin-stdlib"})
public final class FormatToDecimalsKt {
    private static final DecimalFormatSymbols rootNegativeExpFormatSymbols;
    private static final DecimalFormatSymbols rootPositiveExpFormatSymbols;
    private static final ThreadLocal<DecimalFormat>[] precisionFormats;
    private static final ThreadLocal<DecimalFormat> scientificFormat;

    private static final DecimalFormat createFormatForDecimals(int n2) {
        DecimalFormat decimalFormat = new DecimalFormat("0", rootNegativeExpFormatSymbols);
        boolean bl = false;
        boolean bl2 = false;
        DecimalFormat decimalFormat2 = decimalFormat;
        boolean bl3 = false;
        if (n2 > 0) {
            decimalFormat2.setMinimumFractionDigits(n2);
        }
        decimalFormat2.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat;
    }

    @NotNull
    public static final String formatToExactDecimals(double d2, int n2) {
        DecimalFormat decimalFormat;
        if (n2 < precisionFormats.length) {
            ThreadLocal<DecimalFormat> threadLocal = precisionFormats[n2];
            boolean bl = false;
            DecimalFormat decimalFormat2 = threadLocal.get();
            if (decimalFormat2 == null) {
                boolean bl2 = false;
                DecimalFormat decimalFormat3 = FormatToDecimalsKt.createFormatForDecimals(n2);
                boolean bl3 = false;
                boolean bl4 = false;
                DecimalFormat decimalFormat4 = decimalFormat3;
                boolean bl5 = false;
                threadLocal.set(decimalFormat4);
                decimalFormat2 = decimalFormat3;
            }
            decimalFormat = decimalFormat2;
        } else {
            decimalFormat = FormatToDecimalsKt.createFormatForDecimals(n2);
        }
        DecimalFormat decimalFormat5 = decimalFormat;
        String string = decimalFormat5.format(d2);
        Intrinsics.checkNotNullExpressionValue(string, "format.format(value)");
        return string;
    }

    @NotNull
    public static final String formatUpToDecimals(double d2, int n2) {
        DecimalFormat decimalFormat = FormatToDecimalsKt.createFormatForDecimals(0);
        boolean bl = false;
        boolean bl2 = false;
        DecimalFormat decimalFormat2 = decimalFormat;
        boolean bl3 = false;
        decimalFormat2.setMaximumFractionDigits(n2);
        String string = decimalFormat.format(d2);
        Intrinsics.checkNotNullExpressionValue(string, "createFormatForDecimals(\u2026 }\n        .format(value)");
        return string;
    }

    @NotNull
    public static final String formatScientific(double d2) {
        boolean bl;
        Object object = scientificFormat;
        boolean bl2 = false;
        DecimalFormat decimalFormat = ((ThreadLocal)object).get();
        if (decimalFormat == null) {
            boolean bl3 = false;
            DecimalFormat decimalFormat2 = new DecimalFormat("0E0", rootNegativeExpFormatSymbols);
            bl = false;
            boolean bl4 = false;
            DecimalFormat decimalFormat3 = decimalFormat2;
            boolean bl5 = false;
            decimalFormat3.setMinimumFractionDigits(2);
            DecimalFormat decimalFormat4 = decimalFormat2;
            boolean bl6 = false;
            bl = false;
            DecimalFormat decimalFormat5 = decimalFormat4;
            boolean bl7 = false;
            ((ThreadLocal)object).set(decimalFormat5);
            decimalFormat = decimalFormat4;
        }
        object = decimalFormat;
        bl2 = false;
        boolean bl8 = false;
        DecimalFormat decimalFormat6 = (DecimalFormat)object;
        bl = false;
        decimalFormat6.setDecimalFormatSymbols(d2 >= 1.0 || d2 <= (double)-1 ? rootPositiveExpFormatSymbols : rootNegativeExpFormatSymbols);
        String string = ((DecimalFormat)object).format(d2);
        Intrinsics.checkNotNullExpressionValue(string, "scientificFormat.getOrSe\u2026 }\n        .format(value)");
        return string;
    }

    static {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.ROOT);
        boolean bl = false;
        int n2 = 0;
        DecimalFormatSymbols decimalFormatSymbols2 = decimalFormatSymbols;
        boolean bl2 = false;
        decimalFormatSymbols2.setExponentSeparator("e");
        rootNegativeExpFormatSymbols = decimalFormatSymbols;
        decimalFormatSymbols = new DecimalFormatSymbols(Locale.ROOT);
        bl = false;
        n2 = 0;
        decimalFormatSymbols2 = decimalFormatSymbols;
        bl2 = false;
        decimalFormatSymbols2.setExponentSeparator("e+");
        rootPositiveExpFormatSymbols = decimalFormatSymbols;
        int n3 = 4;
        ThreadLocal[] arrthreadLocal = new ThreadLocal[n3];
        n2 = 0;
        while (n2 < n3) {
            ThreadLocal threadLocal;
            int n4 = n2;
            int n5 = n2++;
            ThreadLocal[] arrthreadLocal2 = arrthreadLocal;
            bl2 = false;
            arrthreadLocal2[n5] = threadLocal = new ThreadLocal();
        }
        precisionFormats = arrthreadLocal;
        scientificFormat = new ThreadLocal();
    }
}

