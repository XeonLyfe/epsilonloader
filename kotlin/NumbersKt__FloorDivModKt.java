/*
 * Decompiled with CFR 0.150.
 */
package kotlin;

import kotlin.Metadata;
import kotlin.NumbersKt__BigIntegersKt;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000 \n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\t\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\bH\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\bH\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u00a8\u0006\t"}, d2={"floorDiv", "", "", "other", "", "", "mod", "", "", "kotlin-stdlib"}, xs="kotlin/NumbersKt")
class NumbersKt__FloorDivModKt
extends NumbersKt__BigIntegersKt {
    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int floorDiv(byte by, byte by2) {
        int n2 = 0;
        byte by3 = by;
        byte by4 = by2;
        boolean bl = false;
        int n3 = by3 / by4;
        if ((by3 ^ by4) < 0 && n3 * by4 != by3) {
            --n3;
        }
        return n3;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final byte mod(byte by, byte by2) {
        int n2 = 0;
        byte by3 = by;
        byte by4 = by2;
        boolean bl = false;
        int n3 = by3 % by4;
        return (byte)(n3 + (by4 & ((n3 ^ by4) & (n3 | -n3)) >> 31));
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int floorDiv(byte by, short s2) {
        int n2 = 0;
        byte by2 = by;
        short s3 = s2;
        boolean bl = false;
        int n3 = by2 / s3;
        if ((by2 ^ s3) < 0 && n3 * s3 != by2) {
            --n3;
        }
        return n3;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final short mod(byte by, short s2) {
        int n2 = 0;
        byte by2 = by;
        short s3 = s2;
        boolean bl = false;
        int n3 = by2 % s3;
        return (short)(n3 + (s3 & ((n3 ^ s3) & (n3 | -n3)) >> 31));
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int floorDiv(byte by, int n2) {
        int n3 = 0;
        byte by2 = by;
        boolean bl = false;
        int n4 = by2 / n2;
        if ((by2 ^ n2) < 0 && n4 * n2 != by2) {
            --n4;
        }
        return n4;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int mod(byte by, int n2) {
        int n3 = 0;
        byte by2 = by;
        boolean bl = false;
        int n4 = by2 % n2;
        return n4 + (n2 & ((n4 ^ n2) & (n4 | -n4)) >> 31);
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final long floorDiv(byte by, long l2) {
        int n2 = 0;
        long l3 = by;
        boolean bl = false;
        long l4 = l3 / l2;
        if ((l3 ^ l2) < 0L && l4 * l2 != l3) {
            long l5 = l4;
            l4 = l5 + -1L;
        }
        return l4;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final long mod(byte by, long l2) {
        int n2 = 0;
        long l3 = by;
        boolean bl = false;
        long l4 = l3 % l2;
        return l4 + (l2 & ((l4 ^ l2) & (l4 | -l4)) >> 63);
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int floorDiv(short s2, byte by) {
        int n2 = 0;
        short s3 = s2;
        byte by2 = by;
        boolean bl = false;
        int n3 = s3 / by2;
        if ((s3 ^ by2) < 0 && n3 * by2 != s3) {
            --n3;
        }
        return n3;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final byte mod(short s2, byte by) {
        int n2 = 0;
        short s3 = s2;
        byte by2 = by;
        boolean bl = false;
        int n3 = s3 % by2;
        return (byte)(n3 + (by2 & ((n3 ^ by2) & (n3 | -n3)) >> 31));
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int floorDiv(short s2, short s3) {
        int n2 = 0;
        short s4 = s2;
        short s5 = s3;
        boolean bl = false;
        int n3 = s4 / s5;
        if ((s4 ^ s5) < 0 && n3 * s5 != s4) {
            --n3;
        }
        return n3;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final short mod(short s2, short s3) {
        int n2 = 0;
        short s4 = s2;
        short s5 = s3;
        boolean bl = false;
        int n3 = s4 % s5;
        return (short)(n3 + (s5 & ((n3 ^ s5) & (n3 | -n3)) >> 31));
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int floorDiv(short s2, int n2) {
        int n3 = 0;
        short s3 = s2;
        boolean bl = false;
        int n4 = s3 / n2;
        if ((s3 ^ n2) < 0 && n4 * n2 != s3) {
            --n4;
        }
        return n4;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int mod(short s2, int n2) {
        int n3 = 0;
        short s3 = s2;
        boolean bl = false;
        int n4 = s3 % n2;
        return n4 + (n2 & ((n4 ^ n2) & (n4 | -n4)) >> 31);
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final long floorDiv(short s2, long l2) {
        int n2 = 0;
        long l3 = s2;
        boolean bl = false;
        long l4 = l3 / l2;
        if ((l3 ^ l2) < 0L && l4 * l2 != l3) {
            long l5 = l4;
            l4 = l5 + -1L;
        }
        return l4;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final long mod(short s2, long l2) {
        int n2 = 0;
        long l3 = s2;
        boolean bl = false;
        long l4 = l3 % l2;
        return l4 + (l2 & ((l4 ^ l2) & (l4 | -l4)) >> 63);
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int floorDiv(int n2, byte by) {
        int n3 = 0;
        int n4 = n2;
        byte by2 = by;
        boolean bl = false;
        int n5 = n4 / by2;
        if ((n4 ^ by2) < 0 && n5 * by2 != n4) {
            --n5;
        }
        return n5;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final byte mod(int n2, byte by) {
        int n3 = 0;
        int n4 = n2;
        byte by2 = by;
        boolean bl = false;
        int n5 = n4 % by2;
        return (byte)(n5 + (by2 & ((n5 ^ by2) & (n5 | -n5)) >> 31));
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int floorDiv(int n2, short s2) {
        int n3 = 0;
        int n4 = n2;
        short s3 = s2;
        boolean bl = false;
        int n5 = n4 / s3;
        if ((n4 ^ s3) < 0 && n5 * s3 != n4) {
            --n5;
        }
        return n5;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final short mod(int n2, short s2) {
        int n3 = 0;
        int n4 = n2;
        short s3 = s2;
        boolean bl = false;
        int n5 = n4 % s3;
        return (short)(n5 + (s3 & ((n5 ^ s3) & (n5 | -n5)) >> 31));
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int floorDiv(int n2, int n3) {
        int n4 = 0;
        int n5 = n2 / n3;
        if ((n2 ^ n3) < 0 && n5 * n3 != n2) {
            --n5;
        }
        return n5;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int mod(int n2, int n3) {
        int n4 = 0;
        int n5 = n2 % n3;
        return n5 + (n3 & ((n5 ^ n3) & (n5 | -n5)) >> 31);
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final long floorDiv(int n2, long l2) {
        int n3 = 0;
        long l3 = n2;
        boolean bl = false;
        long l4 = l3 / l2;
        if ((l3 ^ l2) < 0L && l4 * l2 != l3) {
            long l5 = l4;
            l4 = l5 + -1L;
        }
        return l4;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final long mod(int n2, long l2) {
        int n3 = 0;
        long l3 = n2;
        boolean bl = false;
        long l4 = l3 % l2;
        return l4 + (l2 & ((l4 ^ l2) & (l4 | -l4)) >> 63);
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final long floorDiv(long l2, byte by) {
        int n2 = 0;
        long l3 = l2;
        long l4 = by;
        boolean bl = false;
        long l5 = l3 / l4;
        if ((l3 ^ l4) < 0L && l5 * l4 != l3) {
            long l6 = l5;
            l5 = l6 + -1L;
        }
        return l5;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final byte mod(long l2, byte by) {
        int n2 = 0;
        long l3 = l2;
        long l4 = by;
        boolean bl = false;
        long l5 = l3 % l4;
        return (byte)(l5 + (l4 & ((l5 ^ l4) & (l5 | -l5)) >> 63));
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final long floorDiv(long l2, short s2) {
        int n2 = 0;
        long l3 = l2;
        long l4 = s2;
        boolean bl = false;
        long l5 = l3 / l4;
        if ((l3 ^ l4) < 0L && l5 * l4 != l3) {
            long l6 = l5;
            l5 = l6 + -1L;
        }
        return l5;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final short mod(long l2, short s2) {
        int n2 = 0;
        long l3 = l2;
        long l4 = s2;
        boolean bl = false;
        long l5 = l3 % l4;
        return (short)(l5 + (l4 & ((l5 ^ l4) & (l5 | -l5)) >> 63));
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final long floorDiv(long l2, int n2) {
        int n3 = 0;
        long l3 = l2;
        long l4 = n2;
        boolean bl = false;
        long l5 = l3 / l4;
        if ((l3 ^ l4) < 0L && l5 * l4 != l3) {
            long l6 = l5;
            l5 = l6 + -1L;
        }
        return l5;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final int mod(long l2, int n2) {
        int n3 = 0;
        long l3 = l2;
        long l4 = n2;
        boolean bl = false;
        long l5 = l3 % l4;
        return (int)(l5 + (l4 & ((l5 ^ l4) & (l5 | -l5)) >> 63));
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final long floorDiv(long l2, long l3) {
        int n2 = 0;
        long l4 = l2 / l3;
        if ((l2 ^ l3) < 0L && l4 * l3 != l2) {
            long l5 = l4;
            l4 = l5 + -1L;
        }
        return l4;
    }

    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final long mod(long l2, long l3) {
        int n2 = 0;
        long l4 = l2 % l3;
        return l4 + (l3 & ((l4 ^ l3) & (l4 | -l4)) >> 63);
    }

    /*
     * Enabled aggressive block sorting
     */
    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final float mod(float f2, float f3) {
        float f4;
        int n2 = 0;
        float f5 = f2 % f3;
        if (f5 != (float)0.0) {
            float f6 = f5;
            boolean bl = false;
            float f7 = Math.signum(f6);
            f6 = f3;
            bl = false;
            if (f7 != Math.signum(f6)) {
                f4 = f5 + f3;
                return f4;
            }
        }
        f4 = f5;
        return f4;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final double mod(float f2, double d2) {
        double d3;
        int n2 = 0;
        double d4 = f2;
        boolean bl = false;
        double d5 = d4 % d2;
        if (d5 != 0.0) {
            double d6 = d5;
            boolean bl2 = false;
            double d7 = Math.signum(d6);
            d6 = d2;
            bl2 = false;
            if (d7 != Math.signum(d6)) {
                d3 = d5 + d2;
                return d3;
            }
        }
        d3 = d5;
        return d3;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final double mod(double d2, float f2) {
        double d3;
        int n2 = 0;
        double d4 = d2;
        double d5 = f2;
        boolean bl = false;
        double d6 = d4 % d5;
        if (d6 != 0.0) {
            double d7 = d6;
            boolean bl2 = false;
            double d8 = Math.signum(d7);
            d7 = d5;
            bl2 = false;
            if (d8 != Math.signum(d7)) {
                d3 = d6 + d5;
                return d3;
            }
        }
        d3 = d6;
        return d3;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SinceKotlin(version="1.5")
    @InlineOnly
    private static final double mod(double d2, double d3) {
        double d4;
        int n2 = 0;
        double d5 = d2 % d3;
        if (d5 != 0.0) {
            double d6 = d5;
            boolean bl = false;
            double d7 = Math.signum(d6);
            d6 = d3;
            bl = false;
            if (d7 != Math.signum(d6)) {
                d4 = d5 + d3;
                return d4;
            }
        }
        d4 = d5;
        return d4;
    }
}

