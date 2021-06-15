/*
 * Decompiled with CFR 0.150.
 */
package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.collections.ULongIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u000bH\u0096\u0002J\u0015\u0010\r\u001a\u00020\u0003H\u0016\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0016\u0010\b\u001a\u00020\u0003X\u0082\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u00020\u0003X\u0082\u000e\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\u0005\u001a\u00020\u0003X\u0082\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\t\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\u0010"}, d2={"Lkotlin/ranges/ULongProgressionIterator;", "Lkotlin/collections/ULongIterator;", "first", "Lkotlin/ULong;", "last", "step", "", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "finalElement", "J", "hasNext", "", "next", "nextULong", "nextULong-s-VKNKU", "()J", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
final class ULongProgressionIterator
extends ULongIterator {
    private final long finalElement;
    private boolean hasNext;
    private final long step;
    private long next;

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override
    public long nextULong-s-VKNKU() {
        long l2 = this.next;
        if (l2 == this.finalElement) {
            if (!this.hasNext) {
                throw (Throwable)new NoSuchElementException();
            }
            this.hasNext = false;
        } else {
            long l3 = this.next;
            long l4 = this.step;
            boolean bl = false;
            this.next = ULong.constructor-impl(l3 + l4);
        }
        return l2;
    }

    private ULongProgressionIterator(long l2, long l3, long l4) {
        boolean bl;
        boolean bl2;
        long l5;
        this.finalElement = l3;
        if (l4 > 0L) {
            l5 = l2;
            bl2 = false;
            bl = UnsignedKt.ulongCompare(l5, l3) <= 0;
        } else {
            l5 = l2;
            bl2 = false;
            bl = UnsignedKt.ulongCompare(l5, l3) >= 0;
        }
        this.hasNext = bl;
        l5 = l4;
        bl2 = false;
        this.step = ULong.constructor-impl(l5);
        this.next = this.hasNext ? l2 : this.finalElement;
    }

    public /* synthetic */ ULongProgressionIterator(long l2, long l3, long l4, DefaultConstructorMarker defaultConstructorMarker) {
        this(l2, l3, l4);
    }
}

