/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractIterator;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010(\n\u0002\b\b\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007B\u001d\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\fJ\u0013\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0018\u001a\u00020\u0006J\u0016\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0006H\u0096\u0002\u00a2\u0006\u0002\u0010\u001bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0096\u0002J\u000e\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0006J\u0015\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tH\u0014\u00a2\u0006\u0002\u0010#J'\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00010\t\"\u0004\b\u0001\u0010\u00012\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00010\tH\u0014\u00a2\u0006\u0002\u0010%J\u0015\u0010&\u001a\u00020\u0006*\u00020\u00062\u0006\u0010!\u001a\u00020\u0006H\u0082\bR\u0018\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006@RX\u0096\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006'"}, d2={"Lkotlin/collections/RingBuffer;", "T", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "capacity", "", "(I)V", "buffer", "", "", "filledSize", "([Ljava/lang/Object;I)V", "[Ljava/lang/Object;", "<set-?>", "size", "getSize", "()I", "startIndex", "add", "", "element", "(Ljava/lang/Object;)V", "expanded", "maxCapacity", "get", "index", "(I)Ljava/lang/Object;", "isFull", "", "iterator", "", "removeFirst", "n", "toArray", "()[Ljava/lang/Object;", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "forward", "kotlin-stdlib"})
final class RingBuffer<T>
extends AbstractList<T>
implements RandomAccess {
    private final int capacity;
    private int startIndex;
    private int size;
    private final Object[] buffer;

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public T get(int n2) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(n2, this.size());
        int n3 = this.startIndex;
        RingBuffer ringBuffer = this;
        boolean bl = false;
        return (T)this.buffer[(n3 + n2) % ringBuffer.capacity];
    }

    public final boolean isFull() {
        return this.size() == this.capacity;
    }

    @Override
    @NotNull
    public Iterator<T> iterator() {
        return new AbstractIterator<T>(this){
            private int count;
            private int index;
            final /* synthetic */ RingBuffer this$0;

            protected void computeNext() {
                if (this.count == 0) {
                    this.done();
                } else {
                    this.setNext(RingBuffer.access$getBuffer$p(this.this$0)[this.index]);
                    int n2 = this.index;
                    RingBuffer ringBuffer = this.this$0;
                    int n3 = 1;
                    boolean bl = false;
                    this.index = (n2 + n3) % RingBuffer.access$getCapacity$p(ringBuffer);
                    int n4 = this.count;
                    this.count = n4 + -1;
                }
            }
            {
                this.this$0 = ringBuffer;
                this.count = ringBuffer.size();
                this.index = RingBuffer.access$getStartIndex$p(ringBuffer);
            }
        };
    }

    @Override
    @NotNull
    public <T> T[] toArray(@NotNull T[] arrT) {
        T[] arrT2;
        int n2;
        int n3;
        Intrinsics.checkNotNullParameter(arrT, "array");
        if (arrT.length < this.size()) {
            T[] arrT3 = arrT;
            n3 = this.size();
            n2 = 0;
            T[] arrT4 = Arrays.copyOf(arrT3, n3);
            arrT2 = arrT4;
            Intrinsics.checkNotNullExpressionValue(arrT4, "java.util.Arrays.copyOf(this, newSize)");
        } else {
            arrT2 = arrT;
        }
        T[] arrT5 = arrT2;
        int n4 = this.size();
        n3 = 0;
        for (n2 = this.startIndex; n3 < n4 && n2 < this.capacity; ++n3, ++n2) {
            arrT5[n3] = this.buffer[n2];
        }
        n2 = 0;
        while (n3 < n4) {
            arrT5[n3] = this.buffer[n2];
            ++n3;
            ++n2;
        }
        if (arrT5.length > this.size()) {
            arrT5[this.size()] = null;
        }
        if (arrT5 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return arrT5;
    }

    @Override
    @NotNull
    public Object[] toArray() {
        return this.toArray(new Object[this.size()]);
    }

    @NotNull
    public final RingBuffer<T> expanded(int n2) {
        Object[] arrobject;
        int n3 = RangesKt.coerceAtMost(this.capacity + (this.capacity >> 1) + 1, n2);
        if (this.startIndex == 0) {
            Object[] arrobject2 = this.buffer;
            boolean bl = false;
            Object[] arrobject3 = Arrays.copyOf(arrobject2, n3);
            arrobject = arrobject3;
            Intrinsics.checkNotNullExpressionValue(arrobject3, "java.util.Arrays.copyOf(this, newSize)");
        } else {
            arrobject = this.toArray(new Object[n3]);
        }
        Object[] arrobject4 = arrobject;
        return new RingBuffer<T>(arrobject4, this.size());
    }

    public final void add(T t2) {
        if (this.isFull()) {
            throw (Throwable)new IllegalStateException("ring buffer is full");
        }
        int n2 = this.startIndex;
        RingBuffer ringBuffer = this;
        int n3 = this.size();
        boolean bl = false;
        this.buffer[(n2 + n3) % ((RingBuffer)ringBuffer).capacity] = t2;
        int n4 = this.size();
        this.size = n4 + 1;
    }

    public final void removeFirst(int n2) {
        int n3 = n2 >= 0 ? 1 : 0;
        int n4 = 0;
        boolean bl = false;
        if (n3 == 0) {
            boolean bl2 = false;
            String string = "n shouldn't be negative but it is " + n2;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        n3 = n2 <= this.size() ? 1 : 0;
        n4 = 0;
        bl = false;
        if (n3 == 0) {
            boolean bl3 = false;
            String string = "n shouldn't be greater than the buffer size: n = " + n2 + ", size = " + this.size();
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if (n2 > 0) {
            int n5 = n3 = this.startIndex;
            RingBuffer ringBuffer = this;
            boolean bl4 = false;
            n4 = (n5 + n2) % ringBuffer.capacity;
            if (n3 > n4) {
                ArraysKt.fill(this.buffer, null, n3, this.capacity);
                ArraysKt.fill(this.buffer, null, 0, n4);
            } else {
                ArraysKt.fill(this.buffer, null, n3, n4);
            }
            this.startIndex = n4;
            this.size = this.size() - n2;
        }
    }

    private final int forward(int n2, int n3) {
        int n4 = 0;
        return (n2 + n3) % this.capacity;
    }

    public RingBuffer(@NotNull Object[] arrobject, int n2) {
        Intrinsics.checkNotNullParameter(arrobject, "buffer");
        this.buffer = arrobject;
        boolean bl = n2 >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "ring buffer filled size should not be negative but it is " + n2;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        bl = n2 <= this.buffer.length;
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "ring buffer filled size: " + n2 + " cannot be larger than the buffer size: " + this.buffer.length;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        this.capacity = this.buffer.length;
        this.size = n2;
    }

    public RingBuffer(int n2) {
        this(new Object[n2], 0);
    }

    public static final /* synthetic */ Object[] access$getBuffer$p(RingBuffer ringBuffer) {
        return ringBuffer.buffer;
    }

    public static final /* synthetic */ int access$forward(RingBuffer ringBuffer, int n2, int n3) {
        return ringBuffer.forward(n2, n3);
    }

    public static final /* synthetic */ int access$getSize$p(RingBuffer ringBuffer) {
        return ringBuffer.size();
    }

    public static final /* synthetic */ void access$setSize$p(RingBuffer ringBuffer, int n2) {
        ringBuffer.size = n2;
    }

    public static final /* synthetic */ int access$getStartIndex$p(RingBuffer ringBuffer) {
        return ringBuffer.startIndex;
    }

    public static final /* synthetic */ void access$setStartIndex$p(RingBuffer ringBuffer, int n2) {
        ringBuffer.startIndex = n2;
    }
}

