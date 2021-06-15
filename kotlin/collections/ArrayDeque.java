/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.collections.AbstractList;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0007\u0018\u0000 P*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001PB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0006B\u0015\b\u0016\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u00a2\u0006\u0002\u0010\tJ\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0016J\u001d\u0010\u0013\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0016\u0010\u001a\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0013\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u001cJ\b\u0010\u001e\u001a\u00020\u0017H\u0016J\u0016\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0002\u0010\u0016J\u001e\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0002J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004H\u0002J\u0010\u0010$\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0004H\u0002J\u001d\u0010'\u001a\u00020\u00142\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140)H\u0082\bJ\u000b\u0010*\u001a\u00028\u0000\u00a2\u0006\u0002\u0010+J\r\u0010,\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0002\u0010+J\u0016\u0010-\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0004H\u0096\u0002\u00a2\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0015\u00100\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u00101J\u0016\u00102\u001a\u00028\u00002\u0006\u0010!\u001a\u00020\u0004H\u0083\b\u00a2\u0006\u0002\u0010.J\u0011\u0010!\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0083\bJM\u00103\u001a\u00020\u00172>\u00104\u001a:\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(\u000e\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u00a2\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u001705H\u0000\u00a2\u0006\u0002\b8J\b\u00109\u001a\u00020\u0014H\u0016J\u000b\u0010:\u001a\u00028\u0000\u00a2\u0006\u0002\u0010+J\u0015\u0010;\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u00101J\r\u0010<\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0002\u0010+J\u0010\u0010=\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010>\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0015\u0010?\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010@\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0015\u0010A\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0004H\u0016\u00a2\u0006\u0002\u0010.J\u000b\u0010B\u001a\u00028\u0000\u00a2\u0006\u0002\u0010+J\r\u0010C\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0002\u0010+J\u000b\u0010D\u001a\u00028\u0000\u00a2\u0006\u0002\u0010+J\r\u0010E\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0002\u0010+J\u0016\u0010F\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u001e\u0010G\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0002\u0010HJ\u0017\u0010I\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0000\u00a2\u0006\u0004\bJ\u0010KJ)\u0010I\u001a\b\u0012\u0004\u0012\u0002HL0\u000b\"\u0004\b\u0001\u0010L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HL0\u000bH\u0000\u00a2\u0006\u0004\bJ\u0010NJ\u0015\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0016\u00a2\u0006\u0002\u0010KJ'\u0010O\u001a\b\u0012\u0004\u0012\u0002HL0\u000b\"\u0004\b\u0001\u0010L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HL0\u000bH\u0016\u00a2\u0006\u0002\u0010NR\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004@RX\u0096\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006Q"}, d2={"Lkotlin/collections/ArrayDeque;", "E", "Lkotlin/collections/AbstractMutableList;", "initialCapacity", "", "(I)V", "()V", "elements", "", "(Ljava/util/Collection;)V", "elementData", "", "", "[Ljava/lang/Object;", "head", "<set-?>", "size", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "addFirst", "(Ljava/lang/Object;)V", "addLast", "clear", "contains", "copyCollectionElements", "internalIndex", "copyElements", "newCapacity", "decremented", "ensureCapacity", "minCapacity", "filterInPlace", "predicate", "Lkotlin/Function1;", "first", "()Ljava/lang/Object;", "firstOrNull", "get", "(I)Ljava/lang/Object;", "incremented", "indexOf", "(Ljava/lang/Object;)I", "internalGet", "internalStructure", "structure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "internalStructure$kotlin_stdlib", "isEmpty", "last", "lastIndexOf", "lastOrNull", "negativeMod", "positiveMod", "remove", "removeAll", "removeAt", "removeFirst", "removeFirstOrNull", "removeLast", "removeLastOrNull", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "testToArray", "testToArray$kotlin_stdlib", "()[Ljava/lang/Object;", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toArray", "Companion", "kotlin-stdlib"})
@SinceKotlin(version="1.4")
@WasExperimental(markerClass={ExperimentalStdlibApi.class})
public final class ArrayDeque<E>
extends AbstractMutableList<E> {
    private int head;
    private Object[] elementData;
    private int size;
    private static final Object[] emptyElementData;
    private static final int maxArraySize = 0x7FFFFFF7;
    private static final int defaultMinCapacity = 10;
    @NotNull
    public static final Companion Companion;

    @Override
    public int getSize() {
        return this.size;
    }

    private final void ensureCapacity(int n2) {
        if (n2 < 0) {
            throw (Throwable)new IllegalStateException("Deque is too big.");
        }
        if (n2 <= this.elementData.length) {
            return;
        }
        if (this.elementData == emptyElementData) {
            this.elementData = new Object[RangesKt.coerceAtLeast(n2, 10)];
            return;
        }
        int n3 = Companion.newCapacity$kotlin_stdlib(this.elementData.length, n2);
        this.copyElements(n3);
    }

    private final void copyElements(int n2) {
        Object[] arrobject = new Object[n2];
        ArraysKt.copyInto(this.elementData, arrobject, 0, this.head, this.elementData.length);
        ArraysKt.copyInto(this.elementData, arrobject, this.elementData.length - this.head, 0, this.head);
        this.head = 0;
        this.elementData = arrobject;
    }

    @InlineOnly
    private final E internalGet(int n2) {
        int n3 = 0;
        return (E)this.elementData[n2];
    }

    private final int positiveMod(int n2) {
        return n2 >= this.elementData.length ? n2 - this.elementData.length : n2;
    }

    private final int negativeMod(int n2) {
        return n2 < 0 ? n2 + this.elementData.length : n2;
    }

    @InlineOnly
    private final int internalIndex(int n2) {
        int n3 = 0;
        return this.positiveMod(this.head + n2);
    }

    private final int incremented(int n2) {
        return n2 == ArraysKt.getLastIndex(this.elementData) ? 0 : n2 + 1;
    }

    private final int decremented(int n2) {
        return n2 == 0 ? ArraysKt.getLastIndex(this.elementData) : n2 - 1;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    public final E first() {
        if (this.isEmpty()) {
            throw (Throwable)new NoSuchElementException("ArrayDeque is empty.");
        }
        ArrayDeque arrayDeque = this;
        int n2 = this.head;
        boolean bl = false;
        return (E)arrayDeque.elementData[n2];
    }

    @Nullable
    public final E firstOrNull() {
        Object object;
        if (this.isEmpty()) {
            object = null;
        } else {
            ArrayDeque arrayDeque = this;
            int n2 = this.head;
            boolean bl = false;
            object = arrayDeque.elementData[n2];
        }
        return (E)object;
    }

    public final E last() {
        if (this.isEmpty()) {
            throw (Throwable)new NoSuchElementException("ArrayDeque is empty.");
        }
        ArrayDeque arrayDeque = this;
        ArrayDeque arrayDeque2 = this;
        int n2 = CollectionsKt.getLastIndex(this);
        boolean bl = false;
        int n3 = arrayDeque2.positiveMod(arrayDeque2.head + n2);
        n2 = 0;
        return (E)arrayDeque.elementData[n3];
    }

    @Nullable
    public final E lastOrNull() {
        Object object;
        if (this.isEmpty()) {
            object = null;
        } else {
            ArrayDeque arrayDeque = this;
            ArrayDeque arrayDeque2 = this;
            int n2 = CollectionsKt.getLastIndex(this);
            boolean bl = false;
            int n3 = arrayDeque2.positiveMod(arrayDeque2.head + n2);
            n2 = 0;
            object = arrayDeque.elementData[n3];
        }
        return (E)object;
    }

    public final void addFirst(E e2) {
        this.ensureCapacity(this.size() + 1);
        this.head = this.decremented(this.head);
        this.elementData[this.head] = e2;
        this.size = this.size() + 1;
    }

    public final void addLast(E e2) {
        this.ensureCapacity(this.size() + 1);
        ArrayDeque arrayDeque = this;
        int n2 = this.size();
        boolean bl = false;
        this.elementData[((ArrayDeque)arrayDeque).positiveMod((int)(((ArrayDeque)arrayDeque).head + n2))] = e2;
        this.size = this.size() + 1;
    }

    public final E removeFirst() {
        if (this.isEmpty()) {
            throw (Throwable)new NoSuchElementException("ArrayDeque is empty.");
        }
        ArrayDeque arrayDeque = this;
        int n2 = this.head;
        boolean bl = false;
        Object object = arrayDeque.elementData[n2];
        this.elementData[this.head] = null;
        this.head = this.incremented(this.head);
        this.size = this.size() - 1;
        return (E)object;
    }

    @Nullable
    public final E removeFirstOrNull() {
        return this.isEmpty() ? null : (E)this.removeFirst();
    }

    public final E removeLast() {
        if (this.isEmpty()) {
            throw (Throwable)new NoSuchElementException("ArrayDeque is empty.");
        }
        Object object = this;
        int n2 = CollectionsKt.getLastIndex(this);
        boolean bl = false;
        int n3 = ((ArrayDeque)object).positiveMod(((ArrayDeque)object).head + n2);
        ArrayDeque arrayDeque = this;
        bl = false;
        object = arrayDeque.elementData[n3];
        this.elementData[n3] = null;
        this.size = this.size() - 1;
        return (E)object;
    }

    @Nullable
    public final E removeLastOrNull() {
        return this.isEmpty() ? null : (E)this.removeLast();
    }

    @Override
    public boolean add(E e2) {
        this.addLast(e2);
        return true;
    }

    @Override
    public void add(int n2, E e2) {
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(n2, this.size());
        if (n2 == this.size()) {
            this.addLast(e2);
            return;
        }
        if (n2 == 0) {
            this.addFirst(e2);
            return;
        }
        this.ensureCapacity(this.size() + 1);
        ArrayDeque arrayDeque = this;
        int n3 = 0;
        int n4 = arrayDeque.positiveMod(arrayDeque.head + n2);
        if (n2 < this.size() + 1 >> 1) {
            int n5 = this.decremented(n4);
            n3 = this.decremented(this.head);
            if (n5 >= this.head) {
                this.elementData[n3] = this.elementData[this.head];
                ArraysKt.copyInto(this.elementData, this.elementData, this.head, this.head + 1, n5 + 1);
            } else {
                ArraysKt.copyInto(this.elementData, this.elementData, this.head - 1, this.head, this.elementData.length);
                this.elementData[this.elementData.length - 1] = this.elementData[0];
                ArraysKt.copyInto(this.elementData, this.elementData, 0, 1, n5 + 1);
            }
            this.elementData[n5] = e2;
            this.head = n3;
        } else {
            ArrayDeque arrayDeque2 = this;
            int n6 = this.size();
            boolean bl = false;
            int n7 = arrayDeque2.positiveMod(arrayDeque2.head + n6);
            if (n4 < n7) {
                ArraysKt.copyInto(this.elementData, this.elementData, n4 + 1, n4, n7);
            } else {
                ArraysKt.copyInto(this.elementData, this.elementData, 1, 0, n7);
                this.elementData[0] = this.elementData[this.elementData.length - 1];
                ArraysKt.copyInto(this.elementData, this.elementData, n4 + 1, n4, this.elementData.length - 1);
            }
            this.elementData[n4] = e2;
        }
        this.size = this.size() + 1;
    }

    private final void copyCollectionElements(int n2, Collection<? extends E> collection) {
        int n3;
        Iterator<E> iterator2 = collection.iterator();
        int n4 = this.elementData.length;
        for (n3 = n2; n3 < n4 && iterator2.hasNext(); ++n3) {
            this.elementData[n3] = iterator2.next();
        }
        n4 = this.head;
        for (n3 = 0; n3 < n4 && iterator2.hasNext(); ++n3) {
            this.elementData[n3] = iterator2.next();
        }
        this.size = this.size() + collection.size();
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        this.ensureCapacity(this.size() + collection.size());
        ArrayDeque arrayDeque = this;
        int n2 = this.size();
        boolean bl = false;
        this.copyCollectionElements(arrayDeque.positiveMod(arrayDeque.head + n2), collection);
        return true;
    }

    @Override
    public boolean addAll(int n2, @NotNull Collection<? extends E> collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(n2, this.size());
        if (collection.isEmpty()) {
            return false;
        }
        if (n2 == this.size()) {
            return this.addAll(collection);
        }
        this.ensureCapacity(this.size() + collection.size());
        ArrayDeque arrayDeque = this;
        int n3 = this.size();
        int n4 = 0;
        int n5 = arrayDeque.positiveMod(arrayDeque.head + n3);
        ArrayDeque arrayDeque2 = this;
        n4 = 0;
        int n6 = arrayDeque2.positiveMod(arrayDeque2.head + n2);
        int n7 = collection.size();
        if (n2 < this.size() + 1 >> 1) {
            n4 = this.head - n7;
            if (n6 >= this.head) {
                if (n4 >= 0) {
                    ArraysKt.copyInto(this.elementData, this.elementData, n4, this.head, n6);
                } else {
                    int n8 = this.elementData.length - (n4 += this.elementData.length);
                    int n9 = n6 - this.head;
                    if (n8 >= n9) {
                        ArraysKt.copyInto(this.elementData, this.elementData, n4, this.head, n6);
                    } else {
                        ArraysKt.copyInto(this.elementData, this.elementData, n4, this.head, this.head + n8);
                        ArraysKt.copyInto(this.elementData, this.elementData, 0, this.head + n8, n6);
                    }
                }
            } else {
                ArraysKt.copyInto(this.elementData, this.elementData, n4, this.head, this.elementData.length);
                if (n7 >= n6) {
                    ArraysKt.copyInto(this.elementData, this.elementData, this.elementData.length - n7, 0, n6);
                } else {
                    ArraysKt.copyInto(this.elementData, this.elementData, this.elementData.length - n7, 0, n7);
                    ArraysKt.copyInto(this.elementData, this.elementData, 0, n7, n6);
                }
            }
            this.head = n4;
            this.copyCollectionElements(this.negativeMod(n6 - n7), collection);
        } else {
            n4 = n6 + n7;
            if (n6 < n5) {
                if (n5 + n7 <= this.elementData.length) {
                    ArraysKt.copyInto(this.elementData, this.elementData, n4, n6, n5);
                } else if (n4 >= this.elementData.length) {
                    ArraysKt.copyInto(this.elementData, this.elementData, n4 - this.elementData.length, n6, n5);
                } else {
                    int n10 = n5 + n7 - this.elementData.length;
                    ArraysKt.copyInto(this.elementData, this.elementData, 0, n5 - n10, n5);
                    ArraysKt.copyInto(this.elementData, this.elementData, n4, n6, n5 - n10);
                }
            } else {
                ArraysKt.copyInto(this.elementData, this.elementData, n7, 0, n5);
                if (n4 >= this.elementData.length) {
                    ArraysKt.copyInto(this.elementData, this.elementData, n4 - this.elementData.length, n6, this.elementData.length);
                } else {
                    ArraysKt.copyInto(this.elementData, this.elementData, 0, this.elementData.length - n7, this.elementData.length);
                    ArraysKt.copyInto(this.elementData, this.elementData, n4, n6, this.elementData.length - n7);
                }
            }
            this.copyCollectionElements(n6, collection);
        }
        return true;
    }

    @Override
    public E get(int n2) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(n2, this.size());
        ArrayDeque arrayDeque = this;
        ArrayDeque arrayDeque2 = this;
        boolean bl = false;
        int n3 = arrayDeque2.positiveMod(arrayDeque2.head + n2);
        bl = false;
        return (E)arrayDeque.elementData[n3];
    }

    @Override
    public E set(int n2, E e2) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(n2, this.size());
        Object object = this;
        boolean bl = false;
        int n3 = ((ArrayDeque)object).positiveMod(((ArrayDeque)object).head + n2);
        ArrayDeque arrayDeque = this;
        boolean bl2 = false;
        object = arrayDeque.elementData[n3];
        this.elementData[n3] = e2;
        return (E)object;
    }

    @Override
    public boolean contains(Object object) {
        return this.indexOf(object) != -1;
    }

    @Override
    public int indexOf(Object object) {
        block4: {
            int n2;
            int n3;
            int n4;
            block3: {
                ArrayDeque arrayDeque = this;
                n4 = this.size();
                boolean bl = false;
                n3 = arrayDeque.positiveMod(arrayDeque.head + n4);
                if (this.head >= n3) break block3;
                n4 = n3;
                for (int i2 = this.head; i2 < n4; ++i2) {
                    if (!Intrinsics.areEqual(object, this.elementData[i2])) continue;
                    return i2 - this.head;
                }
                break block4;
            }
            if (this.head < n3) break block4;
            n4 = this.elementData.length;
            for (n2 = this.head; n2 < n4; ++n2) {
                if (!Intrinsics.areEqual(object, this.elementData[n2])) continue;
                return n2 - this.head;
            }
            n4 = n3;
            for (n2 = 0; n2 < n4; ++n2) {
                if (!Intrinsics.areEqual(object, this.elementData[n2])) continue;
                return n2 + this.elementData.length - this.head;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        ArrayDeque arrayDeque = this;
        int n2 = this.size();
        boolean bl = false;
        int n3 = arrayDeque.positiveMod(arrayDeque.head + n2);
        if (this.head < n3) {
            int n4 = n3 - 1;
            n2 = this.head;
            if (n4 >= n2) {
                while (true) {
                    if (Intrinsics.areEqual(object, this.elementData[n4])) {
                        return n4 - this.head;
                    }
                    if (n4 != n2) {
                        --n4;
                        continue;
                    }
                    break;
                }
            }
        } else if (this.head > n3) {
            int n5;
            n2 = 0;
            for (n5 = n3 - 1; n5 >= 0; --n5) {
                if (!Intrinsics.areEqual(object, this.elementData[n5])) continue;
                return n5 + this.elementData.length - this.head;
            }
            n5 = ArraysKt.getLastIndex(this.elementData);
            if (n5 >= (n2 = this.head)) {
                while (true) {
                    if (Intrinsics.areEqual(object, this.elementData[n5])) {
                        return n5 - this.head;
                    }
                    if (n5 == n2) break;
                    --n5;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean remove(Object object) {
        int n2 = this.indexOf(object);
        if (n2 == -1) {
            return false;
        }
        this.remove(n2);
        return true;
    }

    @Override
    public E removeAt(int n2) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(n2, this.size());
        if (n2 == CollectionsKt.getLastIndex(this)) {
            return this.removeLast();
        }
        if (n2 == 0) {
            return this.removeFirst();
        }
        Object object = this;
        boolean bl = false;
        int n3 = ((ArrayDeque)object).positiveMod(((ArrayDeque)object).head + n2);
        ArrayDeque arrayDeque = this;
        boolean bl2 = false;
        object = arrayDeque.elementData[n3];
        if (n2 < this.size() >> 1) {
            if (n3 >= this.head) {
                ArraysKt.copyInto(this.elementData, this.elementData, this.head + 1, this.head, n3);
            } else {
                ArraysKt.copyInto(this.elementData, this.elementData, 1, 0, n3);
                this.elementData[0] = this.elementData[this.elementData.length - 1];
                ArraysKt.copyInto(this.elementData, this.elementData, this.head + 1, this.head, this.elementData.length - 1);
            }
            this.elementData[this.head] = null;
            this.head = this.incremented(this.head);
        } else {
            ArrayDeque arrayDeque2 = this;
            int n4 = CollectionsKt.getLastIndex(this);
            boolean bl3 = false;
            int n5 = arrayDeque2.positiveMod(arrayDeque2.head + n4);
            if (n3 <= n5) {
                ArraysKt.copyInto(this.elementData, this.elementData, n3, n3 + 1, n5 + 1);
            } else {
                ArraysKt.copyInto(this.elementData, this.elementData, n3, n3 + 1, this.elementData.length);
                this.elementData[this.elementData.length - 1] = this.elementData[0];
                ArraysKt.copyInto(this.elementData, this.elementData, 0, 1, n5 + 1);
            }
            this.elementData[n5] = null;
        }
        this.size = this.size() - 1;
        return (E)object;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean removeAll(@NotNull Collection<? extends Object> collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        ArrayDeque arrayDeque = this;
        boolean bl = false;
        if (arrayDeque.isEmpty()) return 0 != 0;
        Object[] arrobject = arrayDeque.elementData;
        boolean bl2 = false;
        if (arrobject.length == 0) {
            return 0 != 0;
        }
        boolean bl3 = false;
        if (bl3) {
            return 0 != 0;
        }
        ArrayDeque arrayDeque2 = arrayDeque;
        int n2 = arrayDeque.size();
        int n3 = 0;
        int n4 = arrayDeque2.positiveMod(arrayDeque2.head + n2);
        int n5 = arrayDeque.head;
        n2 = 0;
        if (arrayDeque.head < n4) {
            int n6 = n4;
            for (n3 = arrayDeque.head; n3 < n6; ++n3) {
                Object object;
                Object object2 = object = arrayDeque.elementData[n3];
                boolean bl4 = false;
                if (!collection.contains(object2)) {
                    ((ArrayDeque)arrayDeque).elementData[n5++] = object;
                    continue;
                }
                n2 = 1;
            }
            ArraysKt.fill(arrayDeque.elementData, null, n5, n4);
        } else {
            boolean bl5;
            Object object;
            Object object3;
            int n7 = arrayDeque.elementData.length;
            for (n3 = arrayDeque.head; n3 < n7; ++n3) {
                object3 = arrayDeque.elementData[n3];
                ((ArrayDeque)arrayDeque).elementData[n3] = null;
                object = object3;
                bl5 = false;
                if (!collection.contains(object)) {
                    ((ArrayDeque)arrayDeque).elementData[n5++] = object3;
                    continue;
                }
                n2 = 1;
            }
            n5 = arrayDeque.positiveMod(n5);
            n7 = n4;
            for (n3 = 0; n3 < n7; ++n3) {
                object3 = arrayDeque.elementData[n3];
                ((ArrayDeque)arrayDeque).elementData[n3] = null;
                object = object3;
                bl5 = false;
                if (!collection.contains(object)) {
                    ((ArrayDeque)arrayDeque).elementData[n5] = object3;
                    n5 = arrayDeque.incremented(n5);
                    continue;
                }
                n2 = 1;
            }
        }
        if (n2 != 0) {
            arrayDeque.size = arrayDeque.negativeMod(n5 - arrayDeque.head);
        }
        int n8 = n2;
        return n8 != 0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean retainAll(@NotNull Collection<? extends Object> collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        ArrayDeque arrayDeque = this;
        boolean bl = false;
        if (arrayDeque.isEmpty()) return 0 != 0;
        Object[] arrobject = arrayDeque.elementData;
        boolean bl2 = false;
        if (arrobject.length == 0) {
            return 0 != 0;
        }
        boolean bl3 = false;
        if (bl3) {
            return 0 != 0;
        }
        ArrayDeque arrayDeque2 = arrayDeque;
        int n2 = arrayDeque.size();
        int n3 = 0;
        int n4 = arrayDeque2.positiveMod(arrayDeque2.head + n2);
        int n5 = arrayDeque.head;
        n2 = 0;
        if (arrayDeque.head < n4) {
            int n6 = n4;
            for (n3 = arrayDeque.head; n3 < n6; ++n3) {
                Object object;
                Object object2 = object = arrayDeque.elementData[n3];
                boolean bl4 = false;
                if (collection.contains(object2)) {
                    ((ArrayDeque)arrayDeque).elementData[n5++] = object;
                    continue;
                }
                n2 = 1;
            }
            ArraysKt.fill(arrayDeque.elementData, null, n5, n4);
        } else {
            boolean bl5;
            Object object;
            Object object3;
            int n7 = arrayDeque.elementData.length;
            for (n3 = arrayDeque.head; n3 < n7; ++n3) {
                object3 = arrayDeque.elementData[n3];
                ((ArrayDeque)arrayDeque).elementData[n3] = null;
                object = object3;
                bl5 = false;
                if (collection.contains(object)) {
                    ((ArrayDeque)arrayDeque).elementData[n5++] = object3;
                    continue;
                }
                n2 = 1;
            }
            n5 = arrayDeque.positiveMod(n5);
            n7 = n4;
            for (n3 = 0; n3 < n7; ++n3) {
                object3 = arrayDeque.elementData[n3];
                ((ArrayDeque)arrayDeque).elementData[n3] = null;
                object = object3;
                bl5 = false;
                if (collection.contains(object)) {
                    ((ArrayDeque)arrayDeque).elementData[n5] = object3;
                    n5 = arrayDeque.incremented(n5);
                    continue;
                }
                n2 = 1;
            }
        }
        if (n2 != 0) {
            arrayDeque.size = arrayDeque.negativeMod(n5 - arrayDeque.head);
        }
        int n8 = n2;
        return n8 != 0;
    }

    private final boolean filterInPlace(Function1<? super E, Boolean> function1) {
        block13: {
            block12: {
                int n2 = 0;
                if (this.isEmpty()) break block12;
                Object[] arrobject = this.elementData;
                boolean bl = false;
                if (!(arrobject.length == 0)) break block13;
            }
            return false;
        }
        ArrayDeque arrayDeque = this;
        int n3 = this.size();
        int n4 = 0;
        int n5 = arrayDeque.positiveMod(arrayDeque.head + n3);
        int n6 = this.head;
        n3 = 0;
        if (this.head < n5) {
            int n7 = n5;
            for (n4 = this.head; n4 < n7; ++n4) {
                Object object = this.elementData[n4];
                if (function1.invoke(object).booleanValue()) {
                    ((ArrayDeque)this).elementData[n6++] = object;
                    continue;
                }
                n3 = 1;
            }
            ArraysKt.fill(this.elementData, null, n6, n5);
        } else {
            Object object;
            int n8 = this.elementData.length;
            for (n4 = this.head; n4 < n8; ++n4) {
                object = this.elementData[n4];
                ((ArrayDeque)this).elementData[n4] = null;
                if (function1.invoke(object).booleanValue()) {
                    ((ArrayDeque)this).elementData[n6++] = object;
                    continue;
                }
                n3 = 1;
            }
            n6 = this.positiveMod(n6);
            n8 = n5;
            for (n4 = 0; n4 < n8; ++n4) {
                object = this.elementData[n4];
                ((ArrayDeque)this).elementData[n4] = null;
                if (function1.invoke(object).booleanValue()) {
                    ((ArrayDeque)this).elementData[n6] = object;
                    n6 = this.incremented(n6);
                    continue;
                }
                n3 = 1;
            }
        }
        if (n3 != 0) {
            this.size = this.negativeMod(n6 - this.head);
        }
        return n3 != 0;
    }

    @Override
    public void clear() {
        ArrayDeque arrayDeque = this;
        int n2 = this.size();
        boolean bl = false;
        int n3 = arrayDeque.positiveMod(arrayDeque.head + n2);
        if (this.head < n3) {
            ArraysKt.fill(this.elementData, null, this.head, n3);
        } else {
            arrayDeque = this;
            n2 = 0;
            if (!arrayDeque.isEmpty()) {
                ArraysKt.fill(this.elementData, null, this.head, this.elementData.length);
                ArraysKt.fill(this.elementData, null, 0, n3);
            }
        }
        this.head = 0;
        this.size = 0;
    }

    @Override
    @NotNull
    public <T> T[] toArray(@NotNull T[] arrT) {
        Intrinsics.checkNotNullParameter(arrT, "array");
        Object[] arrobject = arrT.length >= this.size() ? arrT : ArraysKt.arrayOfNulls(arrT, this.size());
        if (arrobject == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        }
        Object[] arrobject2 = arrobject;
        ArrayDeque arrayDeque = this;
        int n2 = this.size();
        boolean bl = false;
        int n3 = arrayDeque.positiveMod(arrayDeque.head + n2);
        if (this.head < n3) {
            ArraysKt.copyInto$default(this.elementData, arrobject2, 0, this.head, n3, 2, null);
        } else {
            arrayDeque = this;
            n2 = 0;
            if (!arrayDeque.isEmpty()) {
                ArraysKt.copyInto(this.elementData, arrobject2, 0, this.head, this.elementData.length);
                ArraysKt.copyInto(this.elementData, arrobject2, this.elementData.length - this.head, 0, n3);
            }
        }
        if (arrobject2.length > this.size()) {
            arrobject2[this.size()] = null;
        }
        if (arrobject2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return arrobject2;
    }

    @Override
    @NotNull
    public Object[] toArray() {
        return this.toArray(new Object[this.size()]);
    }

    @NotNull
    public final <T> T[] testToArray$kotlin_stdlib(@NotNull T[] arrT) {
        Intrinsics.checkNotNullParameter(arrT, "array");
        return this.toArray(arrT);
    }

    @NotNull
    public final Object[] testToArray$kotlin_stdlib() {
        return this.toArray();
    }

    public final void internalStructure$kotlin_stdlib(@NotNull Function2<? super Integer, ? super Object[], Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "structure");
        ArrayDeque arrayDeque = this;
        int n2 = this.size();
        boolean bl = false;
        int n3 = arrayDeque.positiveMod(arrayDeque.head + n2);
        int n4 = this.isEmpty() || this.head < n3 ? this.head : this.head - this.elementData.length;
        function2.invoke((Integer)n4, (Object[])this.toArray());
    }

    public ArrayDeque(int n2) {
        Object[] arrobject;
        if (n2 == 0) {
            arrobject = emptyElementData;
        } else if (n2 > 0) {
            arrobject = new Object[n2];
        } else {
            throw (Throwable)new IllegalArgumentException("Illegal Capacity: " + n2);
        }
        this.elementData = arrobject;
    }

    public ArrayDeque() {
        this.elementData = emptyElementData;
    }

    public ArrayDeque(@NotNull Collection<? extends E> arrobject) {
        Intrinsics.checkNotNullParameter(arrobject, "elements");
        Object[] arrobject2 = arrobject;
        boolean bl = false;
        Object[] arrobject3 = arrobject2;
        Object[] arrobject4 = arrobject3.toArray(new Object[0]);
        if (arrobject4 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        this.elementData = arrobject4;
        this.size = this.elementData.length;
        arrobject2 = this.elementData;
        bl = false;
        if (arrobject2.length == 0) {
            this.elementData = emptyElementData;
        }
    }

    static {
        Companion = new Companion(null);
        emptyElementData = new Object[0];
    }

    public static final /* synthetic */ void access$setElementData$p(ArrayDeque arrayDeque, Object[] arrobject) {
        arrayDeque.elementData = arrobject;
    }

    public static final /* synthetic */ void access$setHead$p(ArrayDeque arrayDeque, int n2) {
        arrayDeque.head = n2;
    }

    public static final /* synthetic */ int access$getSize$p(ArrayDeque arrayDeque) {
        return arrayDeque.size();
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001d\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0000\u00a2\u0006\u0002\b\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"Lkotlin/collections/ArrayDeque$Companion;", "", "()V", "defaultMinCapacity", "", "emptyElementData", "", "[Ljava/lang/Object;", "maxArraySize", "newCapacity", "oldCapacity", "minCapacity", "newCapacity$kotlin_stdlib", "kotlin-stdlib"})
    public static final class Companion {
        public final int newCapacity$kotlin_stdlib(int n2, int n3) {
            int n4 = n2 + (n2 >> 1);
            if (n4 - n3 < 0) {
                n4 = n3;
            }
            if (n4 - 0x7FFFFFF7 > 0) {
                n4 = n3 > 0x7FFFFFF7 ? Integer.MAX_VALUE : 0x7FFFFFF7;
            }
            return n4;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

