/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.comparisons;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.comparisons.ComparisonsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000F\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\n\n\u0002\u0010\u0017\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u0001H\u0007\u00a2\u0006\u0002\u0010\u0005\u001a5\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u0006\u0010\u0006\u001a\u0002H\u0001H\u0007\u00a2\u0006\u0002\u0010\u0007\u001a9\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\t\"\u0002H\u0001H\u0007\u00a2\u0006\u0002\u0010\n\u001a\u0019\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0087\b\u001a!\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000bH\u0087\b\u001a\u001c\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\n\u0010\b\u001a\u00020\f\"\u00020\u000bH\u0007\u001a\u0019\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\rH\u0087\b\u001a!\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\u0087\b\u001a\u001c\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\n\u0010\b\u001a\u00020\u000e\"\u00020\rH\u0007\u001a\u0019\u0010\u0000\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000fH\u0087\b\u001a!\u0010\u0000\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\b\u001a\u001c\u0010\u0000\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\n\u0010\b\u001a\u00020\u0010\"\u00020\u000fH\u0007\u001a\u0019\u0010\u0000\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0011H\u0087\b\u001a!\u0010\u0000\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0011H\u0087\b\u001a\u001c\u0010\u0000\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\n\u0010\b\u001a\u00020\u0012\"\u00020\u0011H\u0007\u001a\u0019\u0010\u0000\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0013H\u0087\b\u001a!\u0010\u0000\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0013H\u0087\b\u001a\u001c\u0010\u0000\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\n\u0010\b\u001a\u00020\u0014\"\u00020\u0013H\u0007\u001a\u0019\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0015H\u0087\b\u001a!\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0015H\u0087\b\u001a\u001c\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\n\u0010\b\u001a\u00020\u0016\"\u00020\u0015H\u0007\u001a-\u0010\u0017\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u0001H\u0007\u00a2\u0006\u0002\u0010\u0005\u001a5\u0010\u0017\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u0006\u0010\u0006\u001a\u0002H\u0001H\u0007\u00a2\u0006\u0002\u0010\u0007\u001a9\u0010\u0017\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\t\"\u0002H\u0001H\u0007\u00a2\u0006\u0002\u0010\n\u001a\u0019\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0087\b\u001a!\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000bH\u0087\b\u001a\u001c\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\n\u0010\b\u001a\u00020\f\"\u00020\u000bH\u0007\u001a\u0019\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\rH\u0087\b\u001a!\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\u0087\b\u001a\u001c\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\n\u0010\b\u001a\u00020\u000e\"\u00020\rH\u0007\u001a\u0019\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000fH\u0087\b\u001a!\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\b\u001a\u001c\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\n\u0010\b\u001a\u00020\u0010\"\u00020\u000fH\u0007\u001a\u0019\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0011H\u0087\b\u001a!\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0011H\u0087\b\u001a\u001c\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\n\u0010\b\u001a\u00020\u0012\"\u00020\u0011H\u0007\u001a\u0019\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0013H\u0087\b\u001a!\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0013H\u0087\b\u001a\u001c\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\n\u0010\b\u001a\u00020\u0014\"\u00020\u0013H\u0007\u001a\u0019\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0015H\u0087\b\u001a!\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0015H\u0087\b\u001a\u001c\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\n\u0010\b\u001a\u00020\u0016\"\u00020\u0015H\u0007\u00a8\u0006\u0018"}, d2={"maxOf", "T", "", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "c", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "other", "", "(Ljava/lang/Comparable;[Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "", "", "", "", "", "", "", "", "", "", "", "minOf", "kotlin-stdlib"}, xs="kotlin/comparisons/ComparisonsKt")
class ComparisonsKt___ComparisonsJvmKt
extends ComparisonsKt__ComparisonsKt {
    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOf(@NotNull T t2, @NotNull T t3) {
        Intrinsics.checkNotNullParameter(t2, "a");
        Intrinsics.checkNotNullParameter(t3, "b");
        return t2.compareTo(t3) >= 0 ? t2 : t3;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final byte maxOf(byte by, byte by2) {
        int n2 = 0;
        return (byte)Math.max(by, by2);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final short maxOf(short s2, short s3) {
        int n2 = 0;
        return (short)Math.max(s2, s3);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final int maxOf(int n2, int n3) {
        int n4 = 0;
        return Math.max(n2, n3);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final long maxOf(long l2, long l3) {
        int n2 = 0;
        return Math.max(l2, l3);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final float maxOf(float f2, float f3) {
        int n2 = 0;
        return Math.max(f2, f3);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final double maxOf(double d2, double d3) {
        int n2 = 0;
        return Math.max(d2, d3);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOf(@NotNull T t2, @NotNull T t3, @NotNull T t4) {
        Intrinsics.checkNotNullParameter(t2, "a");
        Intrinsics.checkNotNullParameter(t3, "b");
        Intrinsics.checkNotNullParameter(t4, "c");
        return ComparisonsKt.maxOf(t2, ComparisonsKt.maxOf(t3, t4));
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final byte maxOf(byte by, byte by2, byte by3) {
        int n2 = 0;
        return (byte)Math.max(by, Math.max(by2, by3));
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final short maxOf(short s2, short s3, short s4) {
        int n2 = 0;
        return (short)Math.max(s2, Math.max(s3, s4));
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final int maxOf(int n2, int n3, int n4) {
        int n5 = 0;
        int n6 = 0;
        n6 = Math.max(n3, n4);
        boolean bl = false;
        return Math.max(n2, n6);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final long maxOf(long l2, long l3, long l4) {
        int n2 = 0;
        boolean bl = false;
        long l5 = Math.max(l3, l4);
        boolean bl2 = false;
        return Math.max(l2, l5);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final float maxOf(float f2, float f3, float f4) {
        int n2 = 0;
        boolean bl = false;
        float f5 = Math.max(f3, f4);
        boolean bl2 = false;
        return Math.max(f2, f5);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final double maxOf(double d2, double d3, double d4) {
        int n2 = 0;
        boolean bl = false;
        double d5 = Math.max(d3, d4);
        boolean bl2 = false;
        return Math.max(d2, d5);
    }

    @SinceKotlin(version="1.4")
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOf(@NotNull T t2, T ... arrT) {
        Intrinsics.checkNotNullParameter(t2, "a");
        Intrinsics.checkNotNullParameter(arrT, "other");
        T t3 = t2;
        for (T t4 : arrT) {
            t3 = ComparisonsKt.maxOf(t3, t4);
        }
        return t3;
    }

    @SinceKotlin(version="1.4")
    public static final byte maxOf(byte by, byte ... arrby) {
        Intrinsics.checkNotNullParameter(arrby, "other");
        byte by2 = by;
        for (byte by3 : arrby) {
            boolean bl = false;
            by2 = (byte)Math.max(by2, by3);
        }
        return by2;
    }

    @SinceKotlin(version="1.4")
    public static final short maxOf(short s2, short ... arrs) {
        Intrinsics.checkNotNullParameter(arrs, "other");
        short s3 = s2;
        for (short s4 : arrs) {
            boolean bl = false;
            s3 = (short)Math.max(s3, s4);
        }
        return s3;
    }

    @SinceKotlin(version="1.4")
    public static final int maxOf(int n2, int ... arrn) {
        Intrinsics.checkNotNullParameter(arrn, "other");
        int n3 = n2;
        for (int n4 : arrn) {
            boolean bl = false;
            n3 = Math.max(n3, n4);
        }
        return n3;
    }

    @SinceKotlin(version="1.4")
    public static final long maxOf(long l2, long ... arrl) {
        Intrinsics.checkNotNullParameter(arrl, "other");
        long l3 = l2;
        for (long l4 : arrl) {
            boolean bl = false;
            l3 = Math.max(l3, l4);
        }
        return l3;
    }

    @SinceKotlin(version="1.4")
    public static final float maxOf(float f2, float ... arrf) {
        Intrinsics.checkNotNullParameter(arrf, "other");
        float f3 = f2;
        for (float f4 : arrf) {
            boolean bl = false;
            f3 = Math.max(f3, f4);
        }
        return f3;
    }

    @SinceKotlin(version="1.4")
    public static final double maxOf(double d2, double ... arrd) {
        Intrinsics.checkNotNullParameter(arrd, "other");
        double d3 = d2;
        for (double d4 : arrd) {
            boolean bl = false;
            d3 = Math.max(d3, d4);
        }
        return d3;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T t2, @NotNull T t3) {
        Intrinsics.checkNotNullParameter(t2, "a");
        Intrinsics.checkNotNullParameter(t3, "b");
        return t2.compareTo(t3) <= 0 ? t2 : t3;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final byte minOf(byte by, byte by2) {
        int n2 = 0;
        return (byte)Math.min(by, by2);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final short minOf(short s2, short s3) {
        int n2 = 0;
        return (short)Math.min(s2, s3);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final int minOf(int n2, int n3) {
        int n4 = 0;
        return Math.min(n2, n3);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final long minOf(long l2, long l3) {
        int n2 = 0;
        return Math.min(l2, l3);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final float minOf(float f2, float f3) {
        int n2 = 0;
        return Math.min(f2, f3);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final double minOf(double d2, double d3) {
        int n2 = 0;
        return Math.min(d2, d3);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T t2, @NotNull T t3, @NotNull T t4) {
        Intrinsics.checkNotNullParameter(t2, "a");
        Intrinsics.checkNotNullParameter(t3, "b");
        Intrinsics.checkNotNullParameter(t4, "c");
        return ComparisonsKt.minOf(t2, ComparisonsKt.minOf(t3, t4));
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final byte minOf(byte by, byte by2, byte by3) {
        int n2 = 0;
        return (byte)Math.min(by, Math.min(by2, by3));
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final short minOf(short s2, short s3, short s4) {
        int n2 = 0;
        return (short)Math.min(s2, Math.min(s3, s4));
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final int minOf(int n2, int n3, int n4) {
        int n5 = 0;
        int n6 = 0;
        n6 = Math.min(n3, n4);
        boolean bl = false;
        return Math.min(n2, n6);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final long minOf(long l2, long l3, long l4) {
        int n2 = 0;
        boolean bl = false;
        long l5 = Math.min(l3, l4);
        boolean bl2 = false;
        return Math.min(l2, l5);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final float minOf(float f2, float f3, float f4) {
        int n2 = 0;
        boolean bl = false;
        float f5 = Math.min(f3, f4);
        boolean bl2 = false;
        return Math.min(f2, f5);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final double minOf(double d2, double d3, double d4) {
        int n2 = 0;
        boolean bl = false;
        double d5 = Math.min(d3, d4);
        boolean bl2 = false;
        return Math.min(d2, d5);
    }

    @SinceKotlin(version="1.4")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T t2, T ... arrT) {
        Intrinsics.checkNotNullParameter(t2, "a");
        Intrinsics.checkNotNullParameter(arrT, "other");
        T t3 = t2;
        for (T t4 : arrT) {
            t3 = ComparisonsKt.minOf(t3, t4);
        }
        return t3;
    }

    @SinceKotlin(version="1.4")
    public static final byte minOf(byte by, byte ... arrby) {
        Intrinsics.checkNotNullParameter(arrby, "other");
        byte by2 = by;
        for (byte by3 : arrby) {
            boolean bl = false;
            by2 = (byte)Math.min(by2, by3);
        }
        return by2;
    }

    @SinceKotlin(version="1.4")
    public static final short minOf(short s2, short ... arrs) {
        Intrinsics.checkNotNullParameter(arrs, "other");
        short s3 = s2;
        for (short s4 : arrs) {
            boolean bl = false;
            s3 = (short)Math.min(s3, s4);
        }
        return s3;
    }

    @SinceKotlin(version="1.4")
    public static final int minOf(int n2, int ... arrn) {
        Intrinsics.checkNotNullParameter(arrn, "other");
        int n3 = n2;
        for (int n4 : arrn) {
            boolean bl = false;
            n3 = Math.min(n3, n4);
        }
        return n3;
    }

    @SinceKotlin(version="1.4")
    public static final long minOf(long l2, long ... arrl) {
        Intrinsics.checkNotNullParameter(arrl, "other");
        long l3 = l2;
        for (long l4 : arrl) {
            boolean bl = false;
            l3 = Math.min(l3, l4);
        }
        return l3;
    }

    @SinceKotlin(version="1.4")
    public static final float minOf(float f2, float ... arrf) {
        Intrinsics.checkNotNullParameter(arrf, "other");
        float f3 = f2;
        for (float f4 : arrf) {
            boolean bl = false;
            f3 = Math.min(f3, f4);
        }
        return f3;
    }

    @SinceKotlin(version="1.4")
    public static final double minOf(double d2, double ... arrd) {
        Intrinsics.checkNotNullParameter(arrd, "other");
        double d3 = d2;
        for (double d4 : arrd) {
            boolean bl = false;
            d3 = Math.min(d3, d4);
        }
        return d3;
    }
}

