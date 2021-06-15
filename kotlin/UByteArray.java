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
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.collections.UByteIterator;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@JvmInline
@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00012B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001e\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0004H\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0004H\u00d6\u0001\u00a2\u0006\u0004\b!\u0010\u000bJ\u000f\u0010\"\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b#\u0010$J\u0019\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020&H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010(J#\u0010)\u001a\u00020*2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0002H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020/H\u00d6\u0001\u00a2\u0006\u0004\b0\u00101R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\f\u0010\r\u0088\u0001\u0007\u0092\u0001\u00020\b\u00f8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u00063"}, d2={"Lkotlin/UByteArray;", "", "Lkotlin/UByte;", "size", "", "constructor-impl", "(I)[B", "storage", "", "([B)[B", "getSize-impl", "([B)I", "getStorage$annotations", "()V", "contains", "", "element", "contains-7apg3OU", "([BB)Z", "containsAll", "elements", "containsAll-impl", "([BLjava/util/Collection;)Z", "equals", "other", "", "equals-impl", "([BLjava/lang/Object;)Z", "get", "index", "get-w2LRezQ", "([BI)B", "hashCode", "hashCode-impl", "isEmpty", "isEmpty-impl", "([B)Z", "iterator", "", "iterator-impl", "([B)Ljava/util/Iterator;", "set", "", "value", "set-VurrAj0", "([BIB)V", "toString", "", "toString-impl", "([B)Ljava/lang/String;", "Iterator", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalUnsignedTypes
public final class UByteArray
implements Collection<UByte>,
KMappedMarker {
    @NotNull
    private final byte[] storage;

    public int getSize() {
        return UByteArray.getSize-impl(this.storage);
    }

    @Override
    @NotNull
    public java.util.Iterator<UByte> iterator() {
        return UByteArray.iterator-impl(this.storage);
    }

    public boolean contains-7apg3OU(byte by) {
        return UByteArray.contains-7apg3OU(this.storage, by);
    }

    @Override
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return UByteArray.containsAll-impl(this.storage, collection);
    }

    @Override
    public boolean isEmpty() {
        return UByteArray.isEmpty-impl(this.storage);
    }

    @PublishedApi
    public static /* synthetic */ void getStorage$annotations() {
    }

    @PublishedApi
    private /* synthetic */ UByteArray(byte[] arrby) {
        Intrinsics.checkNotNullParameter(arrby, "storage");
        this.storage = arrby;
    }

    public static final byte get-w2LRezQ(byte[] arrby, int n2) {
        byte by = arrby[n2];
        boolean bl = false;
        return UByte.constructor-impl(by);
    }

    public static final void set-VurrAj0(byte[] arrby, int n2, byte by) {
        byte by2 = by;
        boolean bl = false;
        arrby[n2] = by2;
    }

    public static int getSize-impl(byte[] arrby) {
        return arrby.length;
    }

    @NotNull
    public static java.util.Iterator<UByte> iterator-impl(byte[] arrby) {
        return new Iterator(arrby);
    }

    public static boolean contains-7apg3OU(byte[] arrby, byte by) {
        byte by2 = by;
        boolean bl = false;
        return ArraysKt.contains(arrby, by2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean containsAll-impl(byte[] arrby, @NotNull Collection<UByte> collection) {
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
            if (!(t3 instanceof UByte)) return false;
            byte by = ((UByte)t3).unbox-impl();
            boolean bl4 = false;
            if (!ArraysKt.contains(arrby, by)) return false;
            bl = true;
        } while (bl);
        return false;
    }

    public static boolean isEmpty-impl(byte[] arrby) {
        return arrby.length == 0;
    }

    @PublishedApi
    @NotNull
    public static byte[] constructor-impl(@NotNull byte[] arrby) {
        Intrinsics.checkNotNullParameter(arrby, "storage");
        return arrby;
    }

    @NotNull
    public static byte[] constructor-impl(int n2) {
        return UByteArray.constructor-impl(new byte[n2]);
    }

    public static final /* synthetic */ UByteArray box-impl(byte[] arrby) {
        Intrinsics.checkNotNullParameter(arrby, "v");
        return new UByteArray(arrby);
    }

    public static String toString-impl(byte[] arrby) {
        return "UByteArray(storage=" + Arrays.toString(arrby) + ")";
    }

    public static int hashCode-impl(byte[] arrby) {
        return arrby != null ? Arrays.hashCode(arrby) : 0;
    }

    public static boolean equals-impl(byte[] arrby, Object object) {
        byte[] arrby2;
        return object instanceof UByteArray && Intrinsics.areEqual(arrby, arrby2 = ((UByteArray)object).unbox-impl());
    }

    public static final boolean equals-impl0(byte[] arrby, byte[] arrby2) {
        return Intrinsics.areEqual(arrby, arrby2);
    }

    public final /* synthetic */ byte[] unbox-impl() {
        return this.storage;
    }

    public String toString() {
        return UByteArray.toString-impl(this.storage);
    }

    @Override
    public int hashCode() {
        return UByteArray.hashCode-impl(this.storage);
    }

    @Override
    public boolean equals(Object object) {
        return UByteArray.equals-impl(this.storage, object);
    }

    public boolean add-7apg3OU(byte by) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(Collection<? extends UByte> collection) {
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
    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u0015\u0010\t\u001a\u00020\nH\u0016\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\r"}, d2={"Lkotlin/UByteArray$Iterator;", "Lkotlin/collections/UByteIterator;", "array", "", "([B)V", "index", "", "hasNext", "", "nextUByte", "Lkotlin/UByte;", "nextUByte-w2LRezQ", "()B", "kotlin-stdlib"})
    private static final class Iterator
    extends UByteIterator {
        private int index;
        private final byte[] array;

        @Override
        public boolean hasNext() {
            return this.index < this.array.length;
        }

        @Override
        public byte nextUByte-w2LRezQ() {
            if (this.index >= this.array.length) {
                throw (Throwable)new NoSuchElementException(String.valueOf(this.index));
            }
            int by = this.index;
            this.index = by + 1;
            byte by2 = this.array[by];
            boolean bl = false;
            return UByte.constructor-impl(by2);
        }

        public Iterator(@NotNull byte[] arrby) {
            Intrinsics.checkNotNullParameter(arrby, "array");
            this.array = arrby;
        }
    }
}

