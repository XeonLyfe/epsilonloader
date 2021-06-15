/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.random;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.PlatformRandomKt;
import kotlin.random.RandomKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b'\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016\u00a8\u0006\u0018"}, d2={"Lkotlin/random/Random;", "", "()V", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "Default", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
public abstract class Random {
    private static final Random defaultRandom;
    @NotNull
    public static final Default Default;

    public abstract int nextBits(int var1);

    public int nextInt() {
        return this.nextBits(32);
    }

    public int nextInt(int n2) {
        return this.nextInt(0, n2);
    }

    public int nextInt(int n2, int n3) {
        int n4;
        int n5;
        RandomKt.checkRangeBounds(n2, n3);
        int n6 = n3 - n2;
        if (n6 > 0 || n6 == Integer.MIN_VALUE) {
            int n7;
            int n8;
            if ((n6 & -n6) == n6) {
                n8 = RandomKt.fastLog2(n6);
                n7 = this.nextBits(n8);
            } else {
                int n9;
                n8 = 0;
                while ((n9 = this.nextInt() >>> 1) - (n8 = n9 % n6) + (n6 - 1) < 0) {
                }
                n7 = n8;
            }
            int n10 = n7;
            return n2 + n10;
        }
        while (n2 > (n5 = (n4 = this.nextInt())) || n3 <= n5) {
        }
        return n4;
    }

    public long nextLong() {
        return ((long)this.nextInt() << 32) + (long)this.nextInt();
    }

    public long nextLong(long l2) {
        return this.nextLong(0L, l2);
    }

    public long nextLong(long l2, long l3) {
        long l4;
        long l5;
        RandomKt.checkRangeBounds(l2, l3);
        long l6 = l3 - l2;
        if (l6 > 0L) {
            long l7 = 0L;
            if ((l6 & -l6) == l6) {
                long l8;
                int n2;
                int n3 = (int)l6;
                int n4 = (int)(l6 >>> 32);
                if (n3 != 0) {
                    n2 = RandomKt.fastLog2(n3);
                    l8 = (long)this.nextBits(n2) & 0xFFFFFFFFL;
                } else if (n4 == 1) {
                    l8 = (long)this.nextInt() & 0xFFFFFFFFL;
                } else {
                    n2 = RandomKt.fastLog2(n4);
                    l8 = ((long)this.nextBits(n2) << 32) + (long)this.nextInt();
                }
                l7 = l8;
            } else {
                long l9;
                long l10 = 0L;
                while ((l9 = this.nextLong() >>> 1) - (l10 = l9 % l6) + (l6 - 1L) < 0L) {
                }
                l7 = l10;
            }
            return l2 + l7;
        }
        while (l2 > (l5 = (l4 = this.nextLong())) || l3 <= l5) {
        }
        return l4;
    }

    public boolean nextBoolean() {
        return this.nextBits(1) != 0;
    }

    public double nextDouble() {
        return PlatformRandomKt.doubleFromParts(this.nextBits(26), this.nextBits(27));
    }

    public double nextDouble(double d2) {
        return this.nextDouble(0.0, d2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public double nextDouble(double var1_1, double var3_2) {
        RandomKt.checkRangeBounds(var1_1, var3_2);
        var9_4 = var5_3 = var3_2 - var1_1;
        var11_5 = false;
        if (!Double.isInfinite(var9_4)) ** GOTO lbl-1000
        var9_4 = var1_1;
        var11_5 = false;
        var12_6 = var9_4;
        var14_7 = false;
        if (Double.isInfinite(var12_6)) ** GOTO lbl-1000
        var12_6 = var9_4;
        var14_7 = false;
        if (!Double.isNaN(var12_6)) {
            v0 = true;
        } else lbl-1000:
        // 2 sources

        {
            v0 = false;
        }
        if (!v0) ** GOTO lbl-1000
        var9_4 = var3_2;
        var11_5 = false;
        var12_6 = var9_4;
        var14_7 = false;
        if (Double.isInfinite(var12_6)) ** GOTO lbl-1000
        var12_6 = var9_4;
        var14_7 = false;
        if (!Double.isNaN(var12_6)) {
            v1 = true;
        } else lbl-1000:
        // 2 sources

        {
            v1 = false;
        }
        if (v1) {
            var9_4 = this.nextDouble() * (var3_2 / (double)2 - var1_1 / (double)2);
            v2 = var1_1 + var9_4 + var9_4;
        } else lbl-1000:
        // 3 sources

        {
            v2 = var7_8 = var1_1 + this.nextDouble() * var5_3;
        }
        if (var7_8 >= var3_2) {
            var9_4 = var3_2;
            var11_5 = false;
            v3 = Math.nextAfter(var9_4, -Infinity);
            return v3;
        }
        v3 = var7_8;
        return v3;
    }

    public float nextFloat() {
        return (float)this.nextBits(24) / (float)0x1000000;
    }

    @NotNull
    public byte[] nextBytes(@NotNull byte[] arrby, int n2, int n3) {
        int n4;
        Intrinsics.checkNotNullParameter(arrby, "array");
        int n5 = n2;
        n5 = 0 <= n5 && arrby.length >= n5 && 0 <= (n5 = n3) && arrby.length >= n5 ? 1 : 0;
        int n6 = 0;
        int n7 = 0;
        if (n5 == 0) {
            boolean bl = false;
            String string = "fromIndex (" + n2 + ") or toIndex (" + n3 + ") are out of range: 0.." + arrby.length + '.';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        n5 = n2 <= n3 ? 1 : 0;
        n6 = 0;
        n7 = 0;
        if (n5 == 0) {
            boolean bl = false;
            String string = "fromIndex (" + n2 + ") must be not greater than toIndex (" + n3 + ").";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        n5 = (n3 - n2) / 4;
        n6 = n2;
        n7 = 0;
        int n8 = 0;
        n8 = 0;
        int n9 = n5;
        while (n8 < n9) {
            n4 = n8++;
            boolean bl = false;
            int n10 = this.nextInt();
            arrby[n6] = (byte)n10;
            arrby[n6 + 1] = (byte)(n10 >>> 8);
            arrby[n6 + 2] = (byte)(n10 >>> 16);
            arrby[n6 + 3] = (byte)(n10 >>> 24);
            n6 += 4;
        }
        n7 = n3 - n6;
        n8 = this.nextBits(n7 * 8);
        n4 = n7;
        for (n9 = 0; n9 < n4; ++n9) {
            arrby[n6 + n9] = (byte)(n8 >>> n9 * 8);
        }
        return arrby;
    }

    public static /* synthetic */ byte[] nextBytes$default(Random random, byte[] arrby, int n2, int n3, int n4, Object object) {
        if (object != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nextBytes");
        }
        if ((n4 & 2) != 0) {
            n2 = 0;
        }
        if ((n4 & 4) != 0) {
            n3 = arrby.length;
        }
        return random.nextBytes(arrby, n2, n3);
    }

    @NotNull
    public byte[] nextBytes(@NotNull byte[] arrby) {
        Intrinsics.checkNotNullParameter(arrby, "array");
        return this.nextBytes(arrby, 0, arrby.length);
    }

    @NotNull
    public byte[] nextBytes(int n2) {
        return this.nextBytes(new byte[n2]);
    }

    static {
        Default = new Default(null);
        boolean bl = false;
        defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001\u001cB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0007H\u0016J\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0016J\u0018\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0019H\u0016J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2={"Lkotlin/random/Random$Default;", "Lkotlin/random/Random;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "defaultRandom", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "writeReplace", "", "Serialized", "kotlin-stdlib"})
    public static final class Default
    extends Random
    implements Serializable {
        private final Object writeReplace() {
            return Serialized.INSTANCE;
        }

        @Override
        public int nextBits(int n2) {
            return defaultRandom.nextBits(n2);
        }

        @Override
        public int nextInt() {
            return defaultRandom.nextInt();
        }

        @Override
        public int nextInt(int n2) {
            return defaultRandom.nextInt(n2);
        }

        @Override
        public int nextInt(int n2, int n3) {
            return defaultRandom.nextInt(n2, n3);
        }

        @Override
        public long nextLong() {
            return defaultRandom.nextLong();
        }

        @Override
        public long nextLong(long l2) {
            return defaultRandom.nextLong(l2);
        }

        @Override
        public long nextLong(long l2, long l3) {
            return defaultRandom.nextLong(l2, l3);
        }

        @Override
        public boolean nextBoolean() {
            return defaultRandom.nextBoolean();
        }

        @Override
        public double nextDouble() {
            return defaultRandom.nextDouble();
        }

        @Override
        public double nextDouble(double d2) {
            return defaultRandom.nextDouble(d2);
        }

        @Override
        public double nextDouble(double d2, double d3) {
            return defaultRandom.nextDouble(d2, d3);
        }

        @Override
        public float nextFloat() {
            return defaultRandom.nextFloat();
        }

        @Override
        @NotNull
        public byte[] nextBytes(@NotNull byte[] arrby) {
            Intrinsics.checkNotNullParameter(arrby, "array");
            return defaultRandom.nextBytes(arrby);
        }

        @Override
        @NotNull
        public byte[] nextBytes(int n2) {
            return defaultRandom.nextBytes(n2);
        }

        @Override
        @NotNull
        public byte[] nextBytes(@NotNull byte[] arrby, int n2, int n3) {
            Intrinsics.checkNotNullParameter(arrby, "array");
            return defaultRandom.nextBytes(arrby, n2, n3);
        }

        private Default() {
        }

        public /* synthetic */ Default(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u00c2\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2={"Lkotlin/random/Random$Default$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "serialVersionUID", "", "readResolve", "", "kotlin-stdlib"})
        private static final class Serialized
        implements Serializable {
            private static final long serialVersionUID = 0L;
            @NotNull
            public static final Serialized INSTANCE;

            private final Object readResolve() {
                return Default;
            }

            private Serialized() {
            }

            static {
                Serialized serialized;
                INSTANCE = serialized = new Serialized();
            }
        }
    }
}

