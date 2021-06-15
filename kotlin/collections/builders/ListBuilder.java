/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.collections.builders;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.builders.ListBuilderKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010+\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005:\u0001QB\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tBM\b\u0002\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000\u00a2\u0006\u0002\u0010\u0012J\u0015\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0019J\u001d\u0010\u0017\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u001cJ\u001e\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J\u0016\u0010\u001d\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J&\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\u0006\u0010\"\u001a\u00020\bH\u0002J\u001d\u0010#\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00028\u0000H\u0002\u00a2\u0006\u0002\u0010\u001cJ\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%J\b\u0010&\u001a\u00020\u001aH\u0002J\b\u0010'\u001a\u00020\u001aH\u0016J\u0014\u0010(\u001a\u00020\u000f2\n\u0010)\u001a\u0006\u0012\u0002\b\u00030%H\u0002J\u0010\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\bH\u0002J\u0010\u0010,\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\bH\u0002J\u0013\u0010-\u001a\u00020\u000f2\b\u0010)\u001a\u0004\u0018\u00010.H\u0096\u0002J\u0016\u0010/\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\bH\u0096\u0002\u00a2\u0006\u0002\u00100J\b\u00101\u001a\u00020\bH\u0016J\u0015\u00102\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u00103J\u0018\u00104\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bH\u0002J\b\u00105\u001a\u00020\u000fH\u0016J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00028\u000007H\u0096\u0002J\u0015\u00108\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u00103J\u000e\u00109\u001a\b\u0012\u0004\u0012\u00028\u00000:H\u0016J\u0016\u00109\u001a\b\u0012\u0004\u0012\u00028\u00000:2\u0006\u0010\u001b\u001a\u00020\bH\u0016J\u0015\u0010;\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010<\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J\u0015\u0010=\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\bH\u0016\u00a2\u0006\u0002\u00100J\u0015\u0010>\u001a\u00028\u00002\u0006\u0010!\u001a\u00020\bH\u0002\u00a2\u0006\u0002\u00100J\u0018\u0010?\u001a\u00020\u001a2\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\bH\u0002J\u0016\u0010B\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J.\u0010C\u001a\u00020\b2\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\u0006\u0010D\u001a\u00020\u000fH\u0002J\u001e\u0010E\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0002\u0010FJ\u001e\u0010G\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010H\u001a\u00020\b2\u0006\u0010I\u001a\u00020\bH\u0016J\u0015\u0010J\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010.0\u000bH\u0016\u00a2\u0006\u0002\u0010KJ'\u0010J\u001a\b\u0012\u0004\u0012\u0002HL0\u000b\"\u0004\b\u0001\u0010L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HL0\u000bH\u0016\u00a2\u0006\u0002\u0010NJ\b\u0010O\u001a\u00020PH\u0016R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0013R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006R"}, d2={"Lkotlin/collections/builders/ListBuilder;", "E", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "Lkotlin/collections/AbstractMutableList;", "()V", "initialCapacity", "", "(I)V", "array", "", "offset", "length", "isReadOnly", "", "backing", "root", "([Ljava/lang/Object;IIZLkotlin/collections/builders/ListBuilder;Lkotlin/collections/builders/ListBuilder;)V", "[Ljava/lang/Object;", "size", "getSize", "()I", "add", "element", "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "elements", "", "addAllInternal", "i", "n", "addAtInternal", "build", "", "checkIsMutable", "clear", "contentEquals", "other", "ensureCapacity", "minCapacity", "ensureExtraCapacity", "equals", "", "get", "(I)Ljava/lang/Object;", "hashCode", "indexOf", "(Ljava/lang/Object;)I", "insertAtInternal", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "remove", "removeAll", "removeAt", "removeAtInternal", "removeRangeInternal", "rangeOffset", "rangeLength", "retainAll", "retainOrRemoveAllInternal", "retain", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "subList", "fromIndex", "toIndex", "toArray", "()[Ljava/lang/Object;", "T", "destination", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "", "Itr", "kotlin-stdlib"})
public final class ListBuilder<E>
extends AbstractMutableList<E>
implements List<E>,
RandomAccess,
KMutableList {
    private E[] array;
    private int offset;
    private int length;
    private boolean isReadOnly;
    private final ListBuilder<E> backing;
    private final ListBuilder<E> root;

    @NotNull
    public final List<E> build() {
        if (this.backing != null) {
            throw (Throwable)new IllegalStateException();
        }
        this.checkIsMutable();
        this.isReadOnly = true;
        return this;
    }

    @Override
    public int getSize() {
        return this.length;
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public E get(int n2) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(n2, this.length);
        return this.array[this.offset + n2];
    }

    @Override
    public E set(int n2, E e2) {
        this.checkIsMutable();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(n2, this.length);
        E e3 = this.array[this.offset + n2];
        this.array[this.offset + n2] = e2;
        return e3;
    }

    @Override
    public int indexOf(Object object) {
        for (int i2 = 0; i2 < this.length; ++i2) {
            if (!Intrinsics.areEqual(this.array[this.offset + i2], object)) continue;
            return i2;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i2 = this.length - 1; i2 >= 0; --i2) {
            if (!Intrinsics.areEqual(this.array[this.offset + i2], object)) continue;
            return i2;
        }
        return -1;
    }

    @Override
    @NotNull
    public Iterator<E> iterator() {
        return new Itr(this, 0);
    }

    @Override
    @NotNull
    public ListIterator<E> listIterator() {
        return new Itr(this, 0);
    }

    @Override
    @NotNull
    public ListIterator<E> listIterator(int n2) {
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(n2, this.length);
        return new Itr(this, n2);
    }

    @Override
    public boolean add(E e2) {
        this.checkIsMutable();
        this.addAtInternal(this.offset + this.length, e2);
        return true;
    }

    @Override
    public void add(int n2, E e2) {
        this.checkIsMutable();
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(n2, this.length);
        this.addAtInternal(this.offset + n2, e2);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        this.checkIsMutable();
        int n2 = collection.size();
        this.addAllInternal(this.offset + this.length, collection, n2);
        return n2 > 0;
    }

    @Override
    public boolean addAll(int n2, @NotNull Collection<? extends E> collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        this.checkIsMutable();
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(n2, this.length);
        int n3 = collection.size();
        this.addAllInternal(this.offset + n2, collection, n3);
        return n3 > 0;
    }

    @Override
    public void clear() {
        this.checkIsMutable();
        this.removeRangeInternal(this.offset, this.length);
    }

    @Override
    public E removeAt(int n2) {
        this.checkIsMutable();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(n2, this.length);
        return this.removeAtInternal(this.offset + n2);
    }

    @Override
    public boolean remove(Object object) {
        this.checkIsMutable();
        int n2 = this.indexOf(object);
        if (n2 >= 0) {
            this.remove(n2);
        }
        return n2 >= 0;
    }

    @Override
    public boolean removeAll(@NotNull Collection<? extends Object> collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        this.checkIsMutable();
        return this.retainOrRemoveAllInternal(this.offset, this.length, collection, false) > 0;
    }

    @Override
    public boolean retainAll(@NotNull Collection<? extends Object> collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        this.checkIsMutable();
        return this.retainOrRemoveAllInternal(this.offset, this.length, collection, true) > 0;
    }

    @Override
    @NotNull
    public List<E> subList(int n2, int n3) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(n2, n3, this.length);
        ListBuilder listBuilder = this.root;
        if (listBuilder == null) {
            listBuilder = this;
        }
        return new ListBuilder<E>(this.array, this.offset + n2, n3 - n2, this.isReadOnly, this, listBuilder);
    }

    @Override
    @NotNull
    public <T> T[] toArray(@NotNull T[] arrT) {
        Intrinsics.checkNotNullParameter(arrT, "destination");
        if (arrT.length < this.length) {
            T[] arrT2 = Arrays.copyOfRange(this.array, this.offset, this.offset + this.length, arrT.getClass());
            Intrinsics.checkNotNullExpressionValue(arrT2, "java.util.Arrays.copyOfR\u2026h, destination.javaClass)");
            return arrT2;
        }
        if (this.array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        ArraysKt.copyInto(this.array, arrT, 0, this.offset, this.offset + this.length);
        if (arrT.length > this.length) {
            arrT[this.length] = null;
        }
        return arrT;
    }

    @Override
    @NotNull
    public Object[] toArray() {
        E[] arrE = this.array;
        int n2 = this.offset;
        int n3 = this.offset + this.length;
        boolean bl = false;
        Object[] arrobject = ArraysKt.copyOfRange(arrE, n2, n3);
        if (arrobject == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        }
        return arrobject;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return object == this || object instanceof List && this.contentEquals((List)object);
    }

    @Override
    public int hashCode() {
        return ListBuilderKt.access$subarrayContentHashCode(this.array, this.offset, this.length);
    }

    @Override
    @NotNull
    public String toString() {
        return ListBuilderKt.access$subarrayContentToString(this.array, this.offset, this.length);
    }

    private final void ensureCapacity(int n2) {
        if (this.backing != null) {
            throw (Throwable)new IllegalStateException();
        }
        if (n2 > this.array.length) {
            int n3 = ArrayDeque.Companion.newCapacity$kotlin_stdlib(this.array.length, n2);
            this.array = ListBuilderKt.copyOfUninitializedElements(this.array, n3);
        }
    }

    private final void checkIsMutable() {
        if (this.isReadOnly || this.root != null && this.root.isReadOnly) {
            throw (Throwable)new UnsupportedOperationException();
        }
    }

    private final void ensureExtraCapacity(int n2) {
        this.ensureCapacity(this.length + n2);
    }

    private final boolean contentEquals(List<?> list) {
        return ListBuilderKt.access$subarrayContentEquals(this.array, this.offset, this.length, list);
    }

    private final void insertAtInternal(int n2, int n3) {
        this.ensureExtraCapacity(n3);
        int n4 = n2 + n3;
        int n5 = this.offset + this.length;
        int n6 = n2;
        ArraysKt.copyInto(this.array, this.array, n4, n6, n5);
        this.length += n3;
    }

    private final void addAtInternal(int n2, E e2) {
        if (this.backing != null) {
            super.addAtInternal(n2, e2);
            this.array = this.backing.array;
            int n3 = this.length;
            this.length = n3 + 1;
        } else {
            this.insertAtInternal(n2, 1);
            this.array[n2] = e2;
        }
    }

    private final void addAllInternal(int n2, Collection<? extends E> collection, int n3) {
        if (this.backing != null) {
            super.addAllInternal(n2, collection, n3);
            this.array = this.backing.array;
            this.length += n3;
        } else {
            this.insertAtInternal(n2, n3);
            Iterator<E> iterator2 = collection.iterator();
            for (int i2 = 0; i2 < n3; ++i2) {
                this.array[n2 + i2] = iterator2.next();
            }
        }
    }

    private final E removeAtInternal(int n2) {
        if (this.backing != null) {
            E e2 = super.removeAtInternal(n2);
            int n3 = this.length;
            this.length = n3 + -1;
            return e2;
        }
        E e3 = this.array[n2];
        int n4 = n2;
        int n5 = this.offset + this.length;
        int n6 = n2 + 1;
        ArraysKt.copyInto(this.array, this.array, n4, n6, n5);
        ListBuilderKt.resetAt(this.array, this.offset + this.length - 1);
        n4 = this.length;
        this.length = n4 + -1;
        return e3;
    }

    private final void removeRangeInternal(int n2, int n3) {
        if (this.backing != null) {
            super.removeRangeInternal(n2, n3);
        } else {
            int n4 = n2;
            int n5 = this.length;
            int n6 = n2 + n3;
            ArraysKt.copyInto(this.array, this.array, n4, n6, n5);
            ListBuilderKt.resetRange(this.array, this.length - n3, this.length);
        }
        this.length -= n3;
    }

    private final int retainOrRemoveAllInternal(int n2, int n3, Collection<? extends E> collection, boolean bl) {
        if (this.backing != null) {
            int n4 = super.retainOrRemoveAllInternal(n2, n3, collection, bl);
            this.length -= n4;
            return n4;
        }
        int n5 = 0;
        int n6 = 0;
        while (n5 < n3) {
            if (collection.contains(this.array[n2 + n5]) == bl) {
                this.array[n2 + n6++] = this.array[n2 + n5++];
                continue;
            }
            ++n5;
        }
        int n7 = n3 - n6;
        int n8 = n2 + n6;
        int n9 = this.length;
        int n10 = n2 + n3;
        ArraysKt.copyInto(this.array, this.array, n8, n10, n9);
        ListBuilderKt.resetRange(this.array, this.length - n7, this.length);
        this.length -= n7;
        return n7;
    }

    private ListBuilder(E[] arrE, int n2, int n3, boolean bl, ListBuilder<E> listBuilder, ListBuilder<E> listBuilder2) {
        this.array = arrE;
        this.offset = n2;
        this.length = n3;
        this.isReadOnly = bl;
        this.backing = listBuilder;
        this.root = listBuilder2;
    }

    public ListBuilder() {
        this(10);
    }

    public ListBuilder(int n2) {
        this(ListBuilderKt.arrayOfUninitializedElements(n2), 0, 0, false, null, null);
    }

    public static final /* synthetic */ void access$setLength$p(ListBuilder listBuilder, int n2) {
        listBuilder.length = n2;
    }

    public static final /* synthetic */ void access$setArray$p(ListBuilder listBuilder, Object[] arrobject) {
        listBuilder.array = arrobject;
    }

    public static final /* synthetic */ void access$setOffset$p(ListBuilder listBuilder, int n2) {
        listBuilder.offset = n2;
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016\u00a2\u0006\u0002\u0010\fJ\t\u0010\r\u001a\u00020\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u000e\u0010\u0010\u001a\u00028\u0001H\u0096\u0002\u00a2\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\r\u0010\u0013\u001a\u00028\u0001H\u0016\u00a2\u0006\u0002\u0010\u0011J\b\u0010\u0014\u001a\u00020\u0006H\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016J\u0015\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2={"Lkotlin/collections/builders/ListBuilder$Itr;", "E", "", "list", "Lkotlin/collections/builders/ListBuilder;", "index", "", "(Lkotlin/collections/builders/ListBuilder;I)V", "lastIndex", "add", "", "element", "(Ljava/lang/Object;)V", "hasNext", "", "hasPrevious", "next", "()Ljava/lang/Object;", "nextIndex", "previous", "previousIndex", "remove", "set", "kotlin-stdlib"})
    private static final class Itr<E>
    implements ListIterator<E>,
    KMutableListIterator {
        private final ListBuilder<E> list;
        private int index;
        private int lastIndex;

        @Override
        public boolean hasPrevious() {
            return this.index > 0;
        }

        @Override
        public boolean hasNext() {
            return this.index < ((ListBuilder)this.list).length;
        }

        @Override
        public int previousIndex() {
            return this.index - 1;
        }

        @Override
        public int nextIndex() {
            return this.index;
        }

        @Override
        public E previous() {
            if (this.index <= 0) {
                throw (Throwable)new NoSuchElementException();
            }
            Itr itr = this;
            itr.index += -1;
            this.lastIndex = itr.index;
            return (E)((ListBuilder)this.list).array[((ListBuilder)this.list).offset + this.lastIndex];
        }

        @Override
        public E next() {
            if (this.index >= ((ListBuilder)this.list).length) {
                throw (Throwable)new NoSuchElementException();
            }
            int n2 = this.index;
            this.index = n2 + 1;
            this.lastIndex = n2;
            return (E)((ListBuilder)this.list).array[((ListBuilder)this.list).offset + this.lastIndex];
        }

        @Override
        public void set(E e2) {
            boolean bl = this.lastIndex != -1;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "Call next() or previous() before replacing element from the iterator.";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            this.list.set(this.lastIndex, e2);
        }

        @Override
        public void add(E e2) {
            int n2 = this.index;
            this.index = n2 + 1;
            this.list.add(n2, e2);
            this.lastIndex = -1;
        }

        @Override
        public void remove() {
            boolean bl = this.lastIndex != -1;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "Call next() or previous() before removing element from the iterator.";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            this.list.remove(this.lastIndex);
            this.index = this.lastIndex;
            this.lastIndex = -1;
        }

        public Itr(@NotNull ListBuilder<E> listBuilder, int n2) {
            Intrinsics.checkNotNullParameter(listBuilder, "list");
            this.list = listBuilder;
            this.index = n2;
            this.lastIndex = -1;
        }
    }
}

