/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0013\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0004H\u0004J\u001d\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0000H\u0004\u00a2\u0006\u0002\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\u0004*\u00028\u0000H$\u00a2\u0006\u0002\u0010\u0018R\u001a\u0010\u0006\u001a\u00020\u0004X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0005R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000bX\u0082\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u0012\u0004\b\f\u0010\r\u00a8\u0006\u0019"}, d2={"Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "T", "", "size", "", "(I)V", "position", "getPosition", "()I", "setPosition", "spreads", "", "getSpreads$annotations", "()V", "[Ljava/lang/Object;", "addSpread", "", "spreadArgument", "(Ljava/lang/Object;)V", "toArray", "values", "result", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getSize", "(Ljava/lang/Object;)I", "kotlin-stdlib"})
public abstract class PrimitiveSpreadBuilder<T> {
    private int position;
    private final T[] spreads;
    private final int size;

    protected abstract int getSize(@NotNull T var1);

    protected final int getPosition() {
        return this.position;
    }

    protected final void setPosition(int n2) {
        this.position = n2;
    }

    private static /* synthetic */ void getSpreads$annotations() {
    }

    public final void addSpread(@NotNull T t2) {
        Intrinsics.checkNotNullParameter(t2, "spreadArgument");
        int n2 = this.position;
        this.position = n2 + 1;
        this.spreads[n2] = t2;
    }

    protected final int size() {
        int n2 = 0;
        int n3 = 0;
        int n4 = this.size - 1;
        if (n3 <= n4) {
            while (true) {
                T t2 = this.spreads[n3];
                n2 += t2 != null ? this.getSize(t2) : 1;
                if (n3 == n4) break;
                ++n3;
            }
        }
        return n2;
    }

    @NotNull
    protected final T toArray(@NotNull T t2, @NotNull T t3) {
        Intrinsics.checkNotNullParameter(t2, "values");
        Intrinsics.checkNotNullParameter(t3, "result");
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = this.size - 1;
        if (n4 <= n5) {
            while (true) {
                T t4;
                if ((t4 = this.spreads[n4]) != null) {
                    if (n3 < n4) {
                        System.arraycopy(t2, n3, t3, n2, n4 - n3);
                        n2 += n4 - n3;
                    }
                    int n6 = this.getSize(t4);
                    System.arraycopy(t4, 0, t3, n2, n6);
                    n2 += n6;
                    n3 = n4 + 1;
                }
                if (n4 == n5) break;
                ++n4;
            }
        }
        if (n3 < this.size) {
            System.arraycopy(t2, n3, t3, n2, this.size - n3);
        }
        return t3;
    }

    public PrimitiveSpreadBuilder(int n2) {
        this.size = n2;
        this.spreads = new Object[this.size];
    }
}

