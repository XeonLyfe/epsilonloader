/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.collections.ArraysKt;
import kotlin.collections.UIntIterator;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@JvmInline
@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00012B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001e\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0004H\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0004H\u00d6\u0001\u00a2\u0006\u0004\b!\u0010\u000bJ\u000f\u0010\"\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b#\u0010$J\u0019\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020&H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010(J#\u0010)\u001a\u00020*2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0002H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020/H\u00d6\u0001\u00a2\u0006\u0004\b0\u00101R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\f\u0010\r\u0088\u0001\u0007\u0092\u0001\u00020\b\u00f8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u00063"}, d2={"Lkotlin/UIntArray;", "", "Lkotlin/UInt;", "size", "", "constructor-impl", "(I)[I", "storage", "", "([I)[I", "getSize-impl", "([I)I", "getStorage$annotations", "()V", "contains", "", "element", "contains-WZ4Q5Ns", "([II)Z", "containsAll", "elements", "containsAll-impl", "([ILjava/util/Collection;)Z", "equals", "other", "", "equals-impl", "([ILjava/lang/Object;)Z", "get", "index", "get-pVg5ArA", "([II)I", "hashCode", "hashCode-impl", "isEmpty", "isEmpty-impl", "([I)Z", "iterator", "", "iterator-impl", "([I)Ljava/util/Iterator;", "set", "", "value", "set-VXSXFK8", "([III)V", "toString", "", "toString-impl", "([I)Ljava/lang/String;", "Iterator", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalUnsignedTypes
public final class UIntArray
implements Collection<UInt>,
KMappedMarker {
    @NotNull
    private final int[] storage;

    public int getSize() {
        return UIntArray.getSize-impl(this.storage);
    }

    @Override
    @NotNull
    public java.util.Iterator<UInt> iterator() {
        return UIntArray.iterator-impl(this.storage);
    }

    public boolean contains-WZ4Q5Ns(int n2) {
        return UIntArray.contains-WZ4Q5Ns(this.storage, n2);
    }

    @Override
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return UIntArray.containsAll-impl(this.storage, collection);
    }

    @Override
    public boolean isEmpty() {
        return UIntArray.isEmpty-impl(this.storage);
    }

    @PublishedApi
    public static /* synthetic */ void getStorage$annotations() {
    }

    @PublishedApi
    private /* synthetic */ UIntArray(int[] arrn) {
        Intrinsics.checkNotNullParameter(arrn, "storage");
        this.storage = arrn;
    }

    public static final int get-pVg5ArA(int[] arrn, int n2) {
        int n3 = arrn[n2];
        boolean bl = false;
        return UInt.constructor-impl(n3);
    }

    public static final void set-VXSXFK8(int[] arrn, int n2, int n3) {
        int n4 = n3;
        boolean bl = false;
        arrn[n2] = n4;
    }

    public static int getSize-impl(int[] arrn) {
        return arrn.length;
    }

    @NotNull
    public static java.util.Iterator<UInt> iterator-impl(int[] arrn) {
        return new Iterator(arrn);
    }

    public static boolean contains-WZ4Q5Ns(int[] arrn, int n2) {
        int n3 = n2;
        boolean bl = false;
        return ArraysKt.contains(arrn, n3);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean containsAll-impl(int[] arrn, @NotNull Collection<UInt> collection) {
        boolean bl;
        Intrinsics.checkNotNullParameter(collection, "elements");
        Iterable iterable = collection;
        boolean bl2 = false;
        if (((Collection)iterable).isEmpty()) {
            return true;
        }
        java.util.Iterator iterator2 = iterable.iterator();
        do {
            Object t2;
            if (!iterator2.hasNext()) return true;
            Object t3 = t2 = iterator2.next();
            boolean bl3 = false;
            if (!(t3 instanceof UInt)) return false;
            int n2 = ((UInt)t3).unbox-impl();
            boolean bl4 = false;
            if (!ArraysKt.contains(arrn, n2)) return false;
            bl = true;
        } while (bl);
        return false;
    }

    public static boolean isEmpty-impl(int[] arrn) {
        return arrn.length == 0;
    }

    @PublishedApi
    @NotNull
    public static int[] constructor-impl(@NotNull int[] arrn) {
        Intrinsics.checkNotNullParameter(arrn, "storage");
        return arrn;
    }

    @NotNull
    public static int[] constructor-impl(int n2) {
        return UIntArray.constructor-impl(new int[n2]);
    }

    public static final /* synthetic */ UIntArray box-impl(int[] arrn) {
        Intrinsics.checkNotNullParameter(arrn, "v");
        return new UIntArray(arrn);
    }

    public static String toString-impl(int[] arrn) {
        return "UIntArray(storage=" + Arrays.toString(arrn) + ")";
    }

    public static int hashCode-impl(int[] arrn) {
        return arrn != null ? Arrays.hashCode(arrn) : 0;
    }

    public static boolean equals-impl(int[] arrn, Object object) {
        int[] arrn2;
        return object instanceof UIntArray && Intrinsics.areEqual(arrn, arrn2 = ((UIntArray)object).unbox-impl());
    }

    public static final boolean equals-impl0(int[] arrn, int[] arrn2) {
        return Intrinsics.areEqual(arrn, arrn2);
    }

    public final /* synthetic */ int[] unbox-impl() {
        return this.storage;
    }

    public String toString() {
        return UIntArray.toString-impl(this.storage);
    }

    @Override
    public int hashCode() {
        return UIntArray.hashCode-impl(this.storage);
    }

    @Override
    public boolean equals(Object object) {
        return UIntArray.equals-impl(this.storage, object);
    }

    public boolean add-WZ4Q5Ns(int n2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(Collection<? extends UInt> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean remove(Object object) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public /* synthetic */ boolean add(Object object) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override
    public <T> T[] toArray(T[] arrT) {
        return CollectionToArray.toArray(this, arrT);
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u0015\u0010\t\u001a\u00020\nH\u0016\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\r"}, d2={"Lkotlin/UIntArray$Iterator;", "Lkotlin/collections/UIntIterator;", "array", "", "([I)V", "index", "", "hasNext", "", "nextUInt", "Lkotlin/UInt;", "nextUInt-pVg5ArA", "()I", "kotlin-stdlib"})
    private static final class Iterator
    extends UIntIterator {
        private int index;
        private final int[] array;

        @Override
        public boolean hasNext() {
            return this.index < this.array.length;
        }

        @Override
        public int nextUInt-pVg5ArA() {
            if (this.index >= this.array.length) {
                throw (Throwable)new NoSuchElementException(String.valueOf(this.index));
            }
            int n2 = this.index;
            this.index = n2 + 1;
            n2 = this.array[n2];
            boolean bl = false;
            return UInt.constructor-impl(n2);
        }

        public Iterator(@NotNull int[] arrn) {
            Intrinsics.checkNotNullParameter(arrn, "array");
            this.array = arrn;
        }
    }
}

