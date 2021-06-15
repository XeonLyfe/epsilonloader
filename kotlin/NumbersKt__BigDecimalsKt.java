/*
 * Decompiled with CFR 0.150.
 */
package kotlin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\nH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\rH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u000eH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u000fH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\u0010\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u00a8\u0006\u0011"}, d2={"dec", "Ljava/math/BigDecimal;", "div", "other", "inc", "minus", "plus", "rem", "times", "toBigDecimal", "", "mathContext", "Ljava/math/MathContext;", "", "", "", "unaryMinus", "kotlin-stdlib"}, xs="kotlin/NumbersKt")
class NumbersKt__BigDecimalsKt {
    @InlineOnly
    private static final BigDecimal plus(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(bigDecimal, "$this$plus");
        BigDecimal bigDecimal3 = bigDecimal.add(bigDecimal2);
        Intrinsics.checkNotNullExpressionValue(bigDecimal3, "this.add(other)");
        return bigDecimal3;
    }

    @InlineOnly
    private static final BigDecimal minus(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(bigDecimal, "$this$minus");
        BigDecimal bigDecimal3 = bigDecimal.subtract(bigDecimal2);
        Intrinsics.checkNotNullExpressionValue(bigDecimal3, "this.subtract(other)");
        return bigDecimal3;
    }

    @InlineOnly
    private static final BigDecimal times(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(bigDecimal, "$this$times");
        BigDecimal bigDecimal3 = bigDecimal.multiply(bigDecimal2);
        Intrinsics.checkNotNullExpressionValue(bigDecimal3, "this.multiply(other)");
        return bigDecimal3;
    }

    @InlineOnly
    private static final BigDecimal div(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(bigDecimal, "$this$div");
        BigDecimal bigDecimal3 = bigDecimal.divide(bigDecimal2, RoundingMode.HALF_EVEN);
        Intrinsics.checkNotNullExpressionValue(bigDecimal3, "this.divide(other, RoundingMode.HALF_EVEN)");
        return bigDecimal3;
    }

    @InlineOnly
    private static final BigDecimal rem(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(bigDecimal, "$this$rem");
        BigDecimal bigDecimal3 = bigDecimal.remainder(bigDecimal2);
        Intrinsics.checkNotNullExpressionValue(bigDecimal3, "this.remainder(other)");
        return bigDecimal3;
    }

    @InlineOnly
    private static final BigDecimal unaryMinus(BigDecimal bigDecimal) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(bigDecimal, "$this$unaryMinus");
        BigDecimal bigDecimal2 = bigDecimal.negate();
        Intrinsics.checkNotNullExpressionValue(bigDecimal2, "this.negate()");
        return bigDecimal2;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal inc(BigDecimal bigDecimal) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(bigDecimal, "$this$inc");
        BigDecimal bigDecimal2 = bigDecimal.add(BigDecimal.ONE);
        Intrinsics.checkNotNullExpressionValue(bigDecimal2, "this.add(BigDecimal.ONE)");
        return bigDecimal2;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal dec(BigDecimal bigDecimal) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(bigDecimal, "$this$dec");
        BigDecimal bigDecimal2 = bigDecimal.subtract(BigDecimal.ONE);
        Intrinsics.checkNotNullExpressionValue(bigDecimal2, "this.subtract(BigDecimal.ONE)");
        return bigDecimal2;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(int n2) {
        int n3 = 0;
        BigDecimal bigDecimal = BigDecimal.valueOf(n2);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this.toLong())");
        return bigDecimal;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(int n2, MathContext mathContext) {
        int n3 = 0;
        return new BigDecimal(n2, mathContext);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(long l2) {
        int n2 = 0;
        BigDecimal bigDecimal = BigDecimal.valueOf(l2);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this)");
        return bigDecimal;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(long l2, MathContext mathContext) {
        int n2 = 0;
        return new BigDecimal(l2, mathContext);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(float f2) {
        int n2 = 0;
        return new BigDecimal(String.valueOf(f2));
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(float f2, MathContext mathContext) {
        int n2 = 0;
        return new BigDecimal(String.valueOf(f2), mathContext);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(double d2) {
        int n2 = 0;
        return new BigDecimal(String.valueOf(d2));
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(double d2, MathContext mathContext) {
        int n2 = 0;
        return new BigDecimal(String.valueOf(d2), mathContext);
    }
}

