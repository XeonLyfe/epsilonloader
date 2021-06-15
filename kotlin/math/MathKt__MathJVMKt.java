/*
 * Decompiled with CFR 0.150.
 */
package kotlin.math;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.math.Constants;
import kotlin.math.MathKt;
import kotlin.math.MathKt__MathHKt;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\"\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b7\u001a\u0011\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fH\u0087\b\u001a\u0011\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0011\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0011\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0019\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0019\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u0010 \u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0011\u0010 \u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010!\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010!\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\"\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\"\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010#\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010#\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010$\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010$\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010%\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010%\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010&\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010&\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0019\u0010'\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0001H\u0087\b\u001a\u0019\u0010'\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010(\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010(\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010)\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010)\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0018\u0010*\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u0001H\u0007\u001a\u0018\u0010*\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u0006H\u0007\u001a\u0011\u0010,\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010,\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u0010-\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0010\u0010-\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0007\u001a\u0019\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u0001H\u0087\b\u001a\u0019\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0087\b\u001a\u0019\u0010.\u001a\u00020\t2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\tH\u0087\b\u001a\u0019\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\fH\u0087\b\u001a\u0019\u00101\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u0001H\u0087\b\u001a\u0019\u00101\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0087\b\u001a\u0019\u00101\u001a\u00020\t2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\tH\u0087\b\u001a\u0019\u00101\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\fH\u0087\b\u001a\u0011\u00102\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00102\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00103\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00103\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00104\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00104\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00105\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00105\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00106\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00106\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00107\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00107\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u00108\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0010\u00108\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0007\u001a\u0015\u00109\u001a\u00020\u0001*\u00020\u00012\u0006\u0010:\u001a\u00020\u0001H\u0087\b\u001a\u0015\u00109\u001a\u00020\u0006*\u00020\u00062\u0006\u0010:\u001a\u00020\u0006H\u0087\b\u001a\r\u0010;\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010;\u001a\u00020\u0006*\u00020\u0006H\u0087\b\u001a\u0015\u0010<\u001a\u00020\u0001*\u00020\u00012\u0006\u0010=\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010<\u001a\u00020\u0006*\u00020\u00062\u0006\u0010=\u001a\u00020\u0006H\u0087\b\u001a\r\u0010>\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010>\u001a\u00020\u0006*\u00020\u0006H\u0087\b\u001a\u0015\u0010?\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010?\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\tH\u0087\b\u001a\u0015\u0010?\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010?\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\tH\u0087\b\u001a\f\u0010@\u001a\u00020\t*\u00020\u0001H\u0007\u001a\f\u0010@\u001a\u00020\t*\u00020\u0006H\u0007\u001a\f\u0010A\u001a\u00020\f*\u00020\u0001H\u0007\u001a\f\u0010A\u001a\u00020\f*\u00020\u0006H\u0007\u001a\u0015\u0010B\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010B\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\tH\u0087\b\u001a\u0015\u0010B\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010B\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\tH\u0087\b\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00018\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001f\u0010\u0000\u001a\u00020\u0006*\u00020\u00068\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0002\u0010\u0007\u001a\u0004\b\u0004\u0010\b\"\u001f\u0010\u0000\u001a\u00020\t*\u00020\t8\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0002\u0010\n\u001a\u0004\b\u0004\u0010\u000b\"\u001f\u0010\u0000\u001a\u00020\f*\u00020\f8\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0002\u0010\r\u001a\u0004\b\u0004\u0010\u000e\"\u001f\u0010\u000f\u001a\u00020\u0001*\u00020\u00018\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u0011\u0010\u0005\"\u001f\u0010\u000f\u001a\u00020\u0006*\u00020\u00068\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0010\u0010\u0007\u001a\u0004\b\u0011\u0010\b\"\u001e\u0010\u000f\u001a\u00020\t*\u00020\t8FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0010\u0010\n\u001a\u0004\b\u0011\u0010\u000b\"\u001e\u0010\u000f\u001a\u00020\t*\u00020\f8FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012\"\u001f\u0010\u0013\u001a\u00020\u0001*\u00020\u00018\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0015\u0010\u0005\"\u001f\u0010\u0013\u001a\u00020\u0006*\u00020\u00068\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0014\u0010\u0007\u001a\u0004\b\u0015\u0010\b\u00a8\u0006C"}, d2={"absoluteValue", "", "getAbsoluteValue$annotations", "(D)V", "getAbsoluteValue", "(D)D", "", "(F)V", "(F)F", "", "(I)V", "(I)I", "", "(J)V", "(J)J", "sign", "getSign$annotations", "getSign", "(J)I", "ulp", "getUlp$annotations", "getUlp", "abs", "x", "n", "acos", "acosh", "asin", "asinh", "atan", "atan2", "y", "atanh", "ceil", "cos", "cosh", "exp", "expm1", "floor", "hypot", "ln", "ln1p", "log", "base", "log10", "log2", "max", "a", "b", "min", "round", "sin", "sinh", "sqrt", "tan", "tanh", "truncate", "IEEErem", "divisor", "nextDown", "nextTowards", "to", "nextUp", "pow", "roundToInt", "roundToLong", "withSign", "kotlin-stdlib"}, xs="kotlin/math/MathKt")
class MathKt__MathJVMKt
extends MathKt__MathHKt {
    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double sin(double d2) {
        int n2 = 0;
        return Math.sin(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double cos(double d2) {
        int n2 = 0;
        return Math.cos(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double tan(double d2) {
        int n2 = 0;
        return Math.tan(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double asin(double d2) {
        int n2 = 0;
        return Math.asin(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double acos(double d2) {
        int n2 = 0;
        return Math.acos(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double atan(double d2) {
        int n2 = 0;
        return Math.atan(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double atan2(double d2, double d3) {
        int n2 = 0;
        return Math.atan2(d2, d3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double sinh(double d2) {
        int n2 = 0;
        return Math.sinh(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double cosh(double d2) {
        int n2 = 0;
        return Math.cosh(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double tanh(double d2) {
        int n2 = 0;
        return Math.tanh(d2);
    }

    @SinceKotlin(version="1.2")
    public static final double asinh(double d2) {
        double d3;
        if (d2 >= Constants.taylor_n_bound) {
            d3 = d2 > Constants.upper_taylor_n_bound ? (d2 > Constants.upper_taylor_2_bound ? Math.log(d2) + Constants.LN2 : Math.log(d2 * (double)2 + 1.0 / (d2 * (double)2))) : Math.log(d2 + Math.sqrt(d2 * d2 + 1.0));
        } else if (d2 <= -Constants.taylor_n_bound) {
            d3 = -MathKt.asinh(-d2);
        } else {
            double d4 = d2;
            if (Math.abs(d2) >= Constants.taylor_2_bound) {
                d4 -= d2 * d2 * d2 / (double)6;
            }
            d3 = d4;
        }
        return d3;
    }

    @SinceKotlin(version="1.2")
    public static final double acosh(double d2) {
        double d3;
        if (d2 < 1.0) {
            d3 = Double.NaN;
        } else if (d2 > Constants.upper_taylor_2_bound) {
            d3 = Math.log(d2) + Constants.LN2;
        } else if (d2 - 1.0 >= Constants.taylor_n_bound) {
            d3 = Math.log(d2 + Math.sqrt(d2 * d2 - 1.0));
        } else {
            double d4;
            double d5 = d4 = Math.sqrt(d2 - 1.0);
            if (d4 >= Constants.taylor_2_bound) {
                d5 -= d4 * d4 * d4 / (double)12;
            }
            d3 = Math.sqrt(2.0) * d5;
        }
        return d3;
    }

    @SinceKotlin(version="1.2")
    public static final double atanh(double d2) {
        if (Math.abs(d2) < Constants.taylor_n_bound) {
            double d3 = d2;
            if (Math.abs(d2) > Constants.taylor_2_bound) {
                d3 += d2 * d2 * d2 / (double)3;
            }
            return d3;
        }
        return Math.log((1.0 + d2) / (1.0 - d2)) / (double)2;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double hypot(double d2, double d3) {
        int n2 = 0;
        return Math.hypot(d2, d3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double sqrt(double d2) {
        int n2 = 0;
        return Math.sqrt(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double exp(double d2) {
        int n2 = 0;
        return Math.exp(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double expm1(double d2) {
        int n2 = 0;
        return Math.expm1(d2);
    }

    @SinceKotlin(version="1.2")
    public static final double log(double d2, double d3) {
        if (d3 <= 0.0 || d3 == 1.0) {
            return Double.NaN;
        }
        return Math.log(d2) / Math.log(d3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double ln(double d2) {
        int n2 = 0;
        return Math.log(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double log10(double d2) {
        int n2 = 0;
        return Math.log10(d2);
    }

    @SinceKotlin(version="1.2")
    public static final double log2(double d2) {
        return Math.log(d2) / Constants.LN2;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double ln1p(double d2) {
        int n2 = 0;
        return Math.log1p(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double ceil(double d2) {
        int n2 = 0;
        return Math.ceil(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double floor(double d2) {
        int n2 = 0;
        return Math.floor(d2);
    }

    /*
     * Enabled aggressive block sorting
     */
    @SinceKotlin(version="1.2")
    public static final double truncate(double d2) {
        double d3;
        block5: {
            block4: {
                double d4 = d2;
                boolean bl = false;
                if (Double.isNaN(d4)) break block4;
                d4 = d2;
                bl = false;
                if (!Double.isInfinite(d4)) break block5;
            }
            d3 = d2;
            return d3;
        }
        if (d2 > 0.0) {
            boolean bl = false;
            d3 = Math.floor(d2);
            return d3;
        }
        boolean bl = false;
        d3 = Math.ceil(d2);
        return d3;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double round(double d2) {
        int n2 = 0;
        return Math.rint(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double abs(double d2) {
        int n2 = 0;
        return Math.abs(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double sign(double d2) {
        int n2 = 0;
        return Math.signum(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double min(double d2, double d3) {
        int n2 = 0;
        return Math.min(d2, d3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double max(double d2, double d3) {
        int n2 = 0;
        return Math.max(d2, d3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double pow(double d2, double d3) {
        int n2 = 0;
        return Math.pow(d2, d3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double pow(double d2, int n2) {
        int n3 = 0;
        return Math.pow(d2, n2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double IEEErem(double d2, double d3) {
        int n2 = 0;
        return Math.IEEEremainder(d2, d3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getAbsoluteValue$annotations(double d2) {
    }

    private static final double getAbsoluteValue(double d2) {
        int n2 = 0;
        return Math.abs(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getSign$annotations(double d2) {
    }

    private static final double getSign(double d2) {
        int n2 = 0;
        return Math.signum(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double withSign(double d2, double d3) {
        int n2 = 0;
        return Math.copySign(d2, d3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double withSign(double d2, int n2) {
        int n3 = 0;
        return Math.copySign(d2, (double)n2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getUlp$annotations(double d2) {
    }

    private static final double getUlp(double d2) {
        int n2 = 0;
        return Math.ulp(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double nextUp(double d2) {
        int n2 = 0;
        return Math.nextUp(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double nextDown(double d2) {
        int n2 = 0;
        return Math.nextAfter(d2, Double.NEGATIVE_INFINITY);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double nextTowards(double d2, double d3) {
        int n2 = 0;
        return Math.nextAfter(d2, d3);
    }

    @SinceKotlin(version="1.2")
    public static final int roundToInt(double d2) {
        double d3 = d2;
        boolean bl = false;
        if (Double.isNaN(d3)) {
            throw (Throwable)new IllegalArgumentException("Cannot round NaN value.");
        }
        return d2 > (double)Integer.MAX_VALUE ? Integer.MAX_VALUE : (d2 < (double)Integer.MIN_VALUE ? Integer.MIN_VALUE : (int)Math.round(d2));
    }

    @SinceKotlin(version="1.2")
    public static final long roundToLong(double d2) {
        double d3 = d2;
        boolean bl = false;
        if (Double.isNaN(d3)) {
            throw (Throwable)new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(d2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float sin(float f2) {
        int n2 = 0;
        return (float)Math.sin(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float cos(float f2) {
        int n2 = 0;
        return (float)Math.cos(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float tan(float f2) {
        int n2 = 0;
        return (float)Math.tan(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float asin(float f2) {
        int n2 = 0;
        return (float)Math.asin(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float acos(float f2) {
        int n2 = 0;
        return (float)Math.acos(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float atan(float f2) {
        int n2 = 0;
        return (float)Math.atan(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float atan2(float f2, float f3) {
        int n2 = 0;
        return (float)Math.atan2(f2, f3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float sinh(float f2) {
        int n2 = 0;
        return (float)Math.sinh(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float cosh(float f2) {
        int n2 = 0;
        return (float)Math.cosh(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float tanh(float f2) {
        int n2 = 0;
        return (float)Math.tanh(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float asinh(float f2) {
        int n2 = 0;
        return (float)MathKt.asinh((double)f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float acosh(float f2) {
        int n2 = 0;
        return (float)MathKt.acosh((double)f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float atanh(float f2) {
        int n2 = 0;
        return (float)MathKt.atanh((double)f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float hypot(float f2, float f3) {
        int n2 = 0;
        return (float)Math.hypot(f2, f3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float sqrt(float f2) {
        int n2 = 0;
        return (float)Math.sqrt(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float exp(float f2) {
        int n2 = 0;
        return (float)Math.exp(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float expm1(float f2) {
        int n2 = 0;
        return (float)Math.expm1(f2);
    }

    @SinceKotlin(version="1.2")
    public static final float log(float f2, float f3) {
        if (f3 <= 0.0f || f3 == 1.0f) {
            return Float.NaN;
        }
        return (float)(Math.log(f2) / Math.log(f3));
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float ln(float f2) {
        int n2 = 0;
        return (float)Math.log(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float log10(float f2) {
        int n2 = 0;
        return (float)Math.log10(f2);
    }

    @SinceKotlin(version="1.2")
    public static final float log2(float f2) {
        return (float)(Math.log(f2) / Constants.LN2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float ln1p(float f2) {
        int n2 = 0;
        return (float)Math.log1p(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float ceil(float f2) {
        int n2 = 0;
        return (float)Math.ceil(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float floor(float f2) {
        int n2 = 0;
        return (float)Math.floor(f2);
    }

    /*
     * Enabled aggressive block sorting
     */
    @SinceKotlin(version="1.2")
    public static final float truncate(float f2) {
        float f3;
        block5: {
            block4: {
                float f4 = f2;
                boolean bl = false;
                if (Float.isNaN(f4)) break block4;
                f4 = f2;
                bl = false;
                if (!Float.isInfinite(f4)) break block5;
            }
            f3 = f2;
            return f3;
        }
        if (f2 > 0.0f) {
            boolean bl = false;
            f3 = (float)Math.floor(f2);
            return f3;
        }
        boolean bl = false;
        f3 = (float)Math.ceil(f2);
        return f3;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float round(float f2) {
        int n2 = 0;
        return (float)Math.rint(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float abs(float f2) {
        int n2 = 0;
        return Math.abs(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float sign(float f2) {
        int n2 = 0;
        return Math.signum(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float min(float f2, float f3) {
        int n2 = 0;
        return Math.min(f2, f3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float max(float f2, float f3) {
        int n2 = 0;
        return Math.max(f2, f3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float pow(float f2, float f3) {
        int n2 = 0;
        return (float)Math.pow(f2, f3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float pow(float f2, int n2) {
        int n3 = 0;
        return (float)Math.pow(f2, n2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float IEEErem(float f2, float f3) {
        int n2 = 0;
        return (float)Math.IEEEremainder(f2, f3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getAbsoluteValue$annotations(float f2) {
    }

    private static final float getAbsoluteValue(float f2) {
        int n2 = 0;
        return Math.abs(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getSign$annotations(float f2) {
    }

    private static final float getSign(float f2) {
        int n2 = 0;
        return Math.signum(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float withSign(float f2, float f3) {
        int n2 = 0;
        return Math.copySign(f2, f3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float withSign(float f2, int n2) {
        int n3 = 0;
        return Math.copySign(f2, n2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getUlp$annotations(float f2) {
    }

    private static final float getUlp(float f2) {
        int n2 = 0;
        return Math.ulp(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float nextUp(float f2) {
        int n2 = 0;
        return Math.nextUp(f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float nextDown(float f2) {
        int n2 = 0;
        return Math.nextAfter(f2, Double.NEGATIVE_INFINITY);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float nextTowards(float f2, float f3) {
        int n2 = 0;
        return Math.nextAfter(f2, (double)f3);
    }

    @SinceKotlin(version="1.2")
    public static final int roundToInt(float f2) {
        float f3 = f2;
        boolean bl = false;
        if (Float.isNaN(f3)) {
            throw (Throwable)new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(f2);
    }

    @SinceKotlin(version="1.2")
    public static final long roundToLong(float f2) {
        return MathKt.roundToLong((double)f2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final int abs(int n2) {
        int n3 = 0;
        return Math.abs(n2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final int min(int n2, int n3) {
        int n4 = 0;
        return Math.min(n2, n3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final int max(int n2, int n3) {
        int n4 = 0;
        return Math.max(n2, n3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getAbsoluteValue$annotations(int n2) {
    }

    private static final int getAbsoluteValue(int n2) {
        int n3 = 0;
        return Math.abs(n2);
    }

    @SinceKotlin(version="1.2")
    public static /* synthetic */ void getSign$annotations(int n2) {
    }

    public static final int getSign(int n2) {
        return n2 < 0 ? -1 : (n2 > 0 ? 1 : 0);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final long abs(long l2) {
        int n2 = 0;
        return Math.abs(l2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final long min(long l2, long l3) {
        int n2 = 0;
        return Math.min(l2, l3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final long max(long l2, long l3) {
        int n2 = 0;
        return Math.max(l2, l3);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getAbsoluteValue$annotations(long l2) {
    }

    private static final long getAbsoluteValue(long l2) {
        int n2 = 0;
        return Math.abs(l2);
    }

    @SinceKotlin(version="1.2")
    public static /* synthetic */ void getSign$annotations(long l2) {
    }

    public static final int getSign(long l2) {
        return l2 < 0L ? -1 : (l2 > 0L ? 1 : 0);
    }
}

