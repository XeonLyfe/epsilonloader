/*
 * Decompiled with CFR 0.150.
 */
package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.UnsignedKt;
import kotlin.collections.UIntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u000bH\u0096\u0002J\u0015\u0010\r\u001a\u00020\u0003H\u0016\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0016\u0010\b\u001a\u00020\u0003X\u0082\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u00020\u0003X\u0082\u000e\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\u0005\u001a\u00020\u0003X\u0082\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\t\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\u0010"}, d2={"Lkotlin/ranges/UIntProgressionIterator;", "Lkotlin/collections/UIntIterator;", "first", "Lkotlin/UInt;", "last", "step", "", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "finalElement", "I", "hasNext", "", "next", "nextUInt", "nextUInt-pVg5ArA", "()I", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
final class UIntProgressionIterator
extends UIntIterator {
    private final int finalElement;
    private boolean hasNext;
    private final int step;
    private int next;

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override
    public int nextUInt-pVg5ArA() {
        int n2 = this.next;
        if (n2 == this.finalElement) {
            if (!this.hasNext) {
                throw (Throwable)new NoSuchElementException();
            }
            this.hasNext = false;
        } else {
            int n3 = this.next;
            int n4 = this.step;
            boolean bl = false;
            this.next = UInt.constructor-impl(n3 + n4);
        }
        return n2;
    }

    private UIntProgressionIterator(int n2, int n3, int n4) {
        boolean bl;
        boolean bl2;
        int n5;
        this.finalElement = n3;
        if (n4 > 0) {
            n5 = n2;
            bl2 = false;
            bl = UnsignedKt.uintCompare(n5, n3) <= 0;
        } else {
            n5 = n2;
            bl2 = false;
            bl = UnsignedKt.uintCompare(n5, n3) >= 0;
        }
        this.hasNext = bl;
        n5 = n4;
        bl2 = false;
        this.step = UInt.constructor-impl(n5);
        this.next = this.hasNext ? n2 : this.finalElement;
    }

    public /* synthetic */ UIntProgressionIterator(int n2, int n3, int n4, DefaultConstructorMarker defaultConstructorMarker) {
        this(n2, n3, n4);
    }
}

