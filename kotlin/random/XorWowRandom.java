/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.random;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0000\u0018\u0000 \u00122\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001\u0012B\u0017\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007B7\b\u0000\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J\b\u0010\u0011\u001a\u00020\u0005H\u0016R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2={"Lkotlin/random/XorWowRandom;", "Lkotlin/random/Random;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "seed1", "", "seed2", "(II)V", "x", "y", "z", "w", "v", "addend", "(IIIIII)V", "nextBits", "bitCount", "nextInt", "Companion", "kotlin-stdlib"})
public final class XorWowRandom
extends Random
implements Serializable {
    private int x;
    private int y;
    private int z;
    private int w;
    private int v;
    private int addend;
    @Deprecated
    private static final long serialVersionUID = 0L;
    @NotNull
    private static final Companion Companion = new Companion(null);

    @Override
    public int nextInt() {
        int n2;
        int n3 = this.x;
        n3 ^= n3 >>> 2;
        this.x = this.y;
        this.y = this.z;
        this.z = this.w;
        this.w = n2 = this.v;
        this.v = n3 = n3 ^ n3 << 1 ^ n2 ^ n2 << 4;
        this.addend += 362437;
        return n3 + this.addend;
    }

    @Override
    public int nextBits(int n2) {
        return RandomKt.takeUpperBits(this.nextInt(), n2);
    }

    public XorWowRandom(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.x = n2;
        this.y = n3;
        this.z = n4;
        this.w = n5;
        this.v = n6;
        this.addend = n7;
        int n8 = (this.x | this.y | this.z | this.w | this.v) != 0 ? 1 : 0;
        boolean bl = false;
        int n9 = 0;
        if (n8 == 0) {
            boolean bl2 = false;
            String string = "Initial state must have at least one non-zero element.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        n8 = 64;
        bl = false;
        n9 = 0;
        n9 = 0;
        int n10 = n8;
        while (n9 < n10) {
            int n11 = n9++;
            boolean bl3 = false;
            this.nextInt();
        }
    }

    public XorWowRandom(int n2, int n3) {
        this(n2, n3, 0, 0, ~n2, n2 << 10 ^ n3 >>> 4);
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2={"Lkotlin/random/XorWowRandom$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"})
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

