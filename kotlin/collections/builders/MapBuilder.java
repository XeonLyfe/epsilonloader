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
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.builders.ListBuilderKt;
import kotlin.collections.builders.MapBuilderEntries;
import kotlin.collections.builders.MapBuilderKeys;
import kotlin.collections.builders.MapBuilderValues;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u00a0\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010&\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 v*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003:\u0006vwxyz{B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007BE\b\u0002\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0010J\u0017\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00028\u0000H\u0000\u00a2\u0006\u0004\b0\u00101J\u0013\u00102\u001a\b\u0012\u0004\u0012\u00028\u00010\tH\u0002\u00a2\u0006\u0002\u00103J\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000105J\r\u00106\u001a\u000207H\u0000\u00a2\u0006\u0002\b8J\b\u00109\u001a\u000207H\u0016J\b\u0010:\u001a\u000207H\u0002J\u0019\u0010;\u001a\u00020\u001f2\n\u0010<\u001a\u0006\u0012\u0002\b\u00030=H\u0000\u00a2\u0006\u0002\b>J!\u0010?\u001a\u00020\u001f2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010AH\u0000\u00a2\u0006\u0002\bBJ\u0015\u0010C\u001a\u00020\u001f2\u0006\u0010/\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010DJ\u0015\u0010E\u001a\u00020\u001f2\u0006\u0010F\u001a\u00028\u0001H\u0016\u00a2\u0006\u0002\u0010DJ\u0018\u0010G\u001a\u00020\u001f2\u000e\u0010H\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u000305H\u0002J\u0010\u0010I\u001a\u0002072\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\u0010\u0010J\u001a\u0002072\u0006\u0010K\u001a\u00020\u0006H\u0002J\u0019\u0010L\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010MH\u0000\u00a2\u0006\u0002\bNJ\u0013\u0010O\u001a\u00020\u001f2\b\u0010H\u001a\u0004\u0018\u00010PH\u0096\u0002J\u0015\u0010Q\u001a\u00020\u00062\u0006\u0010/\u001a\u00028\u0000H\u0002\u00a2\u0006\u0002\u00101J\u0015\u0010R\u001a\u00020\u00062\u0006\u0010F\u001a\u00028\u0001H\u0002\u00a2\u0006\u0002\u00101J\u0018\u0010S\u001a\u0004\u0018\u00018\u00012\u0006\u0010/\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0002\u0010TJ\u0015\u0010U\u001a\u00020\u00062\u0006\u0010/\u001a\u00028\u0000H\u0002\u00a2\u0006\u0002\u00101J\b\u0010V\u001a\u00020\u0006H\u0016J\b\u0010W\u001a\u00020\u001fH\u0016J\u0019\u0010X\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010YH\u0000\u00a2\u0006\u0002\bZJ\u001f\u0010[\u001a\u0004\u0018\u00018\u00012\u0006\u0010/\u001a\u00028\u00002\u0006\u0010F\u001a\u00028\u0001H\u0016\u00a2\u0006\u0002\u0010\\J\u001e\u0010]\u001a\u0002072\u0014\u0010^\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000105H\u0016J\"\u0010_\u001a\u00020\u001f2\u0018\u0010^\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010A0=H\u0002J\u001c\u0010`\u001a\u00020\u001f2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010AH\u0002J\u0010\u0010a\u001a\u00020\u001f2\u0006\u0010b\u001a\u00020\u0006H\u0002J\u0010\u0010c\u001a\u0002072\u0006\u0010d\u001a\u00020\u0006H\u0002J\u0017\u0010e\u001a\u0004\u0018\u00018\u00012\u0006\u0010/\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010TJ!\u0010f\u001a\u00020\u001f2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010AH\u0000\u00a2\u0006\u0002\bgJ\u0010\u0010h\u001a\u0002072\u0006\u0010i\u001a\u00020\u0006H\u0002J\u0017\u0010j\u001a\u00020\u00062\u0006\u0010/\u001a\u00028\u0000H\u0000\u00a2\u0006\u0004\bk\u00101J\u0010\u0010l\u001a\u0002072\u0006\u0010m\u001a\u00020\u0006H\u0002J\u0017\u0010n\u001a\u00020\u001f2\u0006\u0010o\u001a\u00028\u0001H\u0000\u00a2\u0006\u0004\bp\u0010DJ\b\u0010q\u001a\u00020rH\u0016J\u0019\u0010s\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010tH\u0000\u00a2\u0006\u0002\buR\u0014\u0010\u0011\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R&\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00160\u00158VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u00158VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b!\u0010\u0018R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\"R\u0016\u0010#\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010&\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u0006@RX\u0096\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0013R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00010)8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b*\u0010+R\u0018\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\"R\u0016\u0010,\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010-X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006|"}, d2={"Lkotlin/collections/builders/MapBuilder;", "K", "V", "", "()V", "initialCapacity", "", "(I)V", "keysArray", "", "valuesArray", "presenceArray", "", "hashArray", "maxProbeDistance", "length", "([Ljava/lang/Object;[Ljava/lang/Object;[I[III)V", "capacity", "getCapacity", "()I", "entries", "", "", "getEntries", "()Ljava/util/Set;", "entriesView", "Lkotlin/collections/builders/MapBuilderEntries;", "hashShift", "hashSize", "getHashSize", "isReadOnly", "", "keys", "getKeys", "[Ljava/lang/Object;", "keysView", "Lkotlin/collections/builders/MapBuilderKeys;", "<set-?>", "size", "getSize", "values", "", "getValues", "()Ljava/util/Collection;", "valuesView", "Lkotlin/collections/builders/MapBuilderValues;", "addKey", "key", "addKey$kotlin_stdlib", "(Ljava/lang/Object;)I", "allocateValuesArray", "()[Ljava/lang/Object;", "build", "", "checkIsMutable", "", "checkIsMutable$kotlin_stdlib", "clear", "compact", "containsAllEntries", "m", "", "containsAllEntries$kotlin_stdlib", "containsEntry", "entry", "", "containsEntry$kotlin_stdlib", "containsKey", "(Ljava/lang/Object;)Z", "containsValue", "value", "contentEquals", "other", "ensureCapacity", "ensureExtraCapacity", "n", "entriesIterator", "Lkotlin/collections/builders/MapBuilder$EntriesItr;", "entriesIterator$kotlin_stdlib", "equals", "", "findKey", "findValue", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "hash", "hashCode", "isEmpty", "keysIterator", "Lkotlin/collections/builders/MapBuilder$KeysItr;", "keysIterator$kotlin_stdlib", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", "from", "putAllEntries", "putEntry", "putRehash", "i", "rehash", "newHashSize", "remove", "removeEntry", "removeEntry$kotlin_stdlib", "removeHashAt", "removedHash", "removeKey", "removeKey$kotlin_stdlib", "removeKeyAt", "index", "removeValue", "element", "removeValue$kotlin_stdlib", "toString", "", "valuesIterator", "Lkotlin/collections/builders/MapBuilder$ValuesItr;", "valuesIterator$kotlin_stdlib", "Companion", "EntriesItr", "EntryRef", "Itr", "KeysItr", "ValuesItr", "kotlin-stdlib"})
public final class MapBuilder<K, V>
implements Map<K, V>,
KMutableMap {
    private int hashShift;
    private int size;
    private MapBuilderKeys<K> keysView;
    private MapBuilderValues<V> valuesView;
    private MapBuilderEntries<K, V> entriesView;
    private boolean isReadOnly;
    private K[] keysArray;
    private V[] valuesArray;
    private int[] presenceArray;
    private int[] hashArray;
    private int maxProbeDistance;
    private int length;
    @Deprecated
    private static final int MAGIC = -1640531527;
    @Deprecated
    private static final int INITIAL_CAPACITY = 8;
    @Deprecated
    private static final int INITIAL_MAX_PROBE_DISTANCE = 2;
    @Deprecated
    private static final int TOMBSTONE = -1;
    @NotNull
    private static final Companion Companion = new Companion(null);

    public int getSize() {
        return this.size;
    }

    @NotNull
    public final Map<K, V> build() {
        this.checkIsMutable$kotlin_stdlib();
        this.isReadOnly = true;
        return this;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object object) {
        return this.findKey(object) >= 0;
    }

    @Override
    public boolean containsValue(Object object) {
        return this.findValue(object) >= 0;
    }

    @Override
    @Nullable
    public V get(Object object) {
        int n2 = this.findKey(object);
        if (n2 < 0) {
            return null;
        }
        Intrinsics.checkNotNull(this.valuesArray);
        return this.valuesArray[n2];
    }

    @Override
    @Nullable
    public V put(K k2, V v2) {
        this.checkIsMutable$kotlin_stdlib();
        int n2 = this.addKey$kotlin_stdlib(k2);
        V[] arrV = this.allocateValuesArray();
        if (n2 < 0) {
            V v3 = arrV[-n2 - 1];
            arrV[-n2 - 1] = v2;
            return v3;
        }
        arrV[n2] = v2;
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "from");
        this.checkIsMutable$kotlin_stdlib();
        this.putAllEntries((Collection)map.entrySet());
    }

    @Override
    @Nullable
    public V remove(Object object) {
        int n2 = this.removeKey$kotlin_stdlib(object);
        if (n2 < 0) {
            return null;
        }
        Intrinsics.checkNotNull(this.valuesArray);
        V[] arrV = this.valuesArray;
        V v2 = arrV[n2];
        ListBuilderKt.resetAt(arrV, n2);
        return v2;
    }

    @Override
    public void clear() {
        this.checkIsMutable$kotlin_stdlib();
        int n2 = 0;
        int n3 = this.length - 1;
        if (n2 <= n3) {
            while (true) {
                int n4;
                if ((n4 = this.presenceArray[n2]) >= 0) {
                    this.hashArray[n4] = 0;
                    this.presenceArray[n2] = -1;
                }
                if (n2 == n3) break;
                ++n2;
            }
        }
        ListBuilderKt.resetRange(this.keysArray, 0, this.length);
        if (this.valuesArray != null) {
            ListBuilderKt.resetRange(this.valuesArray, 0, this.length);
        }
        this.size = 0;
        this.length = 0;
    }

    @NotNull
    public Set<K> getKeys() {
        Set set;
        MapBuilderKeys<K> mapBuilderKeys = this.keysView;
        if (mapBuilderKeys == null) {
            MapBuilderKeys mapBuilderKeys2 = new MapBuilderKeys(this);
            this.keysView = mapBuilderKeys2;
            set = mapBuilderKeys2;
        } else {
            set = mapBuilderKeys;
        }
        return set;
    }

    @NotNull
    public Collection<V> getValues() {
        Collection collection;
        MapBuilderValues<V> mapBuilderValues = this.valuesView;
        if (mapBuilderValues == null) {
            MapBuilderValues mapBuilderValues2 = new MapBuilderValues(this);
            this.valuesView = mapBuilderValues2;
            collection = mapBuilderValues2;
        } else {
            collection = mapBuilderValues;
        }
        return collection;
    }

    @NotNull
    public Set<Map.Entry<K, V>> getEntries() {
        MapBuilderEntries<K, V> mapBuilderEntries = this.entriesView;
        if (mapBuilderEntries == null) {
            MapBuilderEntries mapBuilderEntries2 = new MapBuilderEntries(this);
            this.entriesView = mapBuilderEntries2;
            return mapBuilderEntries2;
        }
        return mapBuilderEntries;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return object == this || object instanceof Map && this.contentEquals((Map)object);
    }

    @Override
    public int hashCode() {
        int n2 = 0;
        EntriesItr<K, V> entriesItr = this.entriesIterator$kotlin_stdlib();
        while (entriesItr.hasNext()) {
            n2 += entriesItr.nextHashCode$kotlin_stdlib();
        }
        return n2;
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(2 + this.size() * 3);
        stringBuilder.append("{");
        int n2 = 0;
        EntriesItr<K, V> entriesItr = this.entriesIterator$kotlin_stdlib();
        while (entriesItr.hasNext()) {
            if (n2 > 0) {
                stringBuilder.append(", ");
            }
            entriesItr.nextAppendString(stringBuilder);
            ++n2;
        }
        stringBuilder.append("}");
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }

    private final int getCapacity() {
        return this.keysArray.length;
    }

    private final int getHashSize() {
        return this.hashArray.length;
    }

    public final void checkIsMutable$kotlin_stdlib() {
        if (this.isReadOnly) {
            throw (Throwable)new UnsupportedOperationException();
        }
    }

    private final void ensureExtraCapacity(int n2) {
        this.ensureCapacity(this.length + n2);
    }

    private final void ensureCapacity(int n2) {
        if (n2 > this.getCapacity()) {
            int n3 = this.getCapacity() * 3 / 2;
            if (n2 > n3) {
                n3 = n2;
            }
            this.keysArray = ListBuilderKt.copyOfUninitializedElements(this.keysArray, n3);
            this.valuesArray = this.valuesArray != null ? ListBuilderKt.copyOfUninitializedElements(this.valuesArray, n3) : null;
            int[] arrn = this.presenceArray;
            boolean bl = false;
            int[] arrn2 = Arrays.copyOf(arrn, n3);
            Intrinsics.checkNotNullExpressionValue(arrn2, "java.util.Arrays.copyOf(this, newSize)");
            this.presenceArray = arrn2;
            int n4 = MapBuilder.Companion.computeHashSize(n3);
            if (n4 > this.getHashSize()) {
                this.rehash(n4);
            }
        } else if (this.length + n2 - this.size() > this.getCapacity()) {
            this.rehash(this.getHashSize());
        }
    }

    private final V[] allocateValuesArray() {
        V[] arrV = this.valuesArray;
        if (arrV != null) {
            return arrV;
        }
        E[] arrE = ListBuilderKt.arrayOfUninitializedElements(this.getCapacity());
        this.valuesArray = arrE;
        return arrE;
    }

    private final int hash(K k2) {
        K k3 = k2;
        boolean bl = false;
        K k4 = k3;
        return (k4 != null ? k4.hashCode() : 0) * -1640531527 >>> this.hashShift;
    }

    private final void compact() {
        int n2 = 0;
        V[] arrV = this.valuesArray;
        for (int i2 = 0; i2 < this.length; ++i2) {
            if (this.presenceArray[i2] < 0) continue;
            this.keysArray[n2] = this.keysArray[i2];
            if (arrV != null) {
                arrV[n2] = arrV[i2];
            }
            ++n2;
        }
        ListBuilderKt.resetRange(this.keysArray, n2, this.length);
        if (arrV != null) {
            ListBuilderKt.resetRange(arrV, n2, this.length);
        }
        this.length = n2;
    }

    private final void rehash(int n2) {
        if (this.length > this.size()) {
            this.compact();
        }
        if (n2 != this.getHashSize()) {
            this.hashArray = new int[n2];
            this.hashShift = MapBuilder.Companion.computeShift(n2);
        } else {
            ArraysKt.fill(this.hashArray, 0, 0, this.getHashSize());
        }
        int n3 = 0;
        while (n3 < this.length) {
            if (this.putRehash(n3++)) continue;
            throw (Throwable)new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
        }
    }

    private final boolean putRehash(int n2) {
        int n3 = this.hash(this.keysArray[n2]);
        int n4 = this.maxProbeDistance;
        while (true) {
            int n5;
            if ((n5 = this.hashArray[n3]) == 0) {
                this.hashArray[n3] = n2 + 1;
                this.presenceArray[n2] = n3;
                return true;
            }
            if (--n4 < 0) {
                return false;
            }
            if (n3-- != 0) continue;
            n3 = this.getHashSize() - 1;
        }
    }

    private final int findKey(K k2) {
        int n2 = this.hash(k2);
        int n3 = this.maxProbeDistance;
        int n4;
        while ((n4 = this.hashArray[n2]) != 0) {
            if (n4 > 0 && Intrinsics.areEqual(this.keysArray[n4 - 1], k2)) {
                return n4 - 1;
            }
            if (--n3 < 0) {
                return -1;
            }
            if (n2-- != 0) continue;
            n2 = this.getHashSize() - 1;
        }
        return -1;
    }

    private final int findValue(V v2) {
        int n2 = this.length;
        while (--n2 >= 0) {
            if (this.presenceArray[n2] < 0) continue;
            Intrinsics.checkNotNull(this.valuesArray);
            if (!Intrinsics.areEqual(this.valuesArray[n2], v2)) continue;
            return n2;
        }
        return -1;
    }

    public final int addKey$kotlin_stdlib(K k2) {
        this.checkIsMutable$kotlin_stdlib();
        block0: while (true) {
            int n2 = this.hash(k2);
            int n3 = RangesKt.coerceAtMost(this.maxProbeDistance * 2, this.getHashSize() / 2);
            int n4 = 0;
            while (true) {
                int n5;
                if ((n5 = this.hashArray[n2]) <= 0) {
                    if (this.length >= this.getCapacity()) {
                        this.ensureExtraCapacity(1);
                        continue block0;
                    }
                    int n6 = this.length;
                    this.length = n6 + 1;
                    int n7 = n6;
                    this.keysArray[n7] = k2;
                    this.presenceArray[n7] = n2;
                    this.hashArray[n2] = n7 + 1;
                    n6 = this.size();
                    this.size = n6 + 1;
                    if (n4 > this.maxProbeDistance) {
                        this.maxProbeDistance = n4;
                    }
                    return n7;
                }
                if (Intrinsics.areEqual(this.keysArray[n5 - 1], k2)) {
                    return -n5;
                }
                if (++n4 > n3) {
                    this.rehash(this.getHashSize() * 2);
                    continue block0;
                }
                if (n2-- != 0) continue;
                n2 = this.getHashSize() - 1;
            }
            break;
        }
    }

    public final int removeKey$kotlin_stdlib(K k2) {
        this.checkIsMutable$kotlin_stdlib();
        int n2 = this.findKey(k2);
        if (n2 < 0) {
            return -1;
        }
        this.removeKeyAt(n2);
        return n2;
    }

    private final void removeKeyAt(int n2) {
        ListBuilderKt.resetAt(this.keysArray, n2);
        this.removeHashAt(this.presenceArray[n2]);
        this.presenceArray[n2] = -1;
        int n3 = this.size();
        this.size = n3 + -1;
    }

    private final void removeHashAt(int n2) {
        int n3 = n2;
        int n4 = n2;
        int n5 = 0;
        int n6 = RangesKt.coerceAtMost(this.maxProbeDistance * 2, this.getHashSize() / 2);
        do {
            if (n3-- == 0) {
                n3 = this.getHashSize() - 1;
            }
            if (++n5 > this.maxProbeDistance) {
                this.hashArray[n4] = 0;
                return;
            }
            int n7 = this.hashArray[n3];
            if (n7 == 0) {
                this.hashArray[n4] = 0;
                return;
            }
            if (n7 < 0) {
                this.hashArray[n4] = -1;
                n4 = n3;
                n5 = 0;
                continue;
            }
            int n8 = this.hash(this.keysArray[n7 - 1]);
            if ((n8 - n3 & this.getHashSize() - 1) < n5) continue;
            this.hashArray[n4] = n7;
            this.presenceArray[n7 - 1] = n4;
            n4 = n3;
            n5 = 0;
        } while (--n6 >= 0);
        this.hashArray[n4] = -1;
    }

    public final boolean containsEntry$kotlin_stdlib(@NotNull Map.Entry<? extends K, ? extends V> entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        int n2 = this.findKey(entry.getKey());
        if (n2 < 0) {
            return false;
        }
        Intrinsics.checkNotNull(this.valuesArray);
        return Intrinsics.areEqual(this.valuesArray[n2], entry.getValue());
    }

    private final boolean contentEquals(Map<?, ?> map) {
        return this.size() == map.size() && this.containsAllEntries$kotlin_stdlib((Collection)map.entrySet());
    }

    public final boolean containsAllEntries$kotlin_stdlib(@NotNull Collection<?> collection) {
        Intrinsics.checkNotNullParameter(collection, "m");
        for (Object obj : collection) {
            try {
                if (obj != null && this.containsEntry$kotlin_stdlib((Map.Entry)obj)) continue;
                return false;
            }
            catch (ClassCastException classCastException) {
                return false;
            }
        }
        return true;
    }

    private final boolean putEntry(Map.Entry<? extends K, ? extends V> entry) {
        int n2 = this.addKey$kotlin_stdlib(entry.getKey());
        V[] arrV = this.allocateValuesArray();
        if (n2 >= 0) {
            arrV[n2] = entry.getValue();
            return true;
        }
        V v2 = arrV[-n2 - 1];
        if (Intrinsics.areEqual(entry.getValue(), v2) ^ true) {
            arrV[-n2 - 1] = entry.getValue();
            return true;
        }
        return false;
    }

    private final boolean putAllEntries(Collection<? extends Map.Entry<? extends K, ? extends V>> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        this.ensureExtraCapacity(collection.size());
        Iterator<Map.Entry<K, V>> iterator2 = collection.iterator();
        boolean bl = false;
        while (iterator2.hasNext()) {
            if (!this.putEntry(iterator2.next())) continue;
            bl = true;
        }
        return bl;
    }

    public final boolean removeEntry$kotlin_stdlib(@NotNull Map.Entry<? extends K, ? extends V> entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        this.checkIsMutable$kotlin_stdlib();
        int n2 = this.findKey(entry.getKey());
        if (n2 < 0) {
            return false;
        }
        Intrinsics.checkNotNull(this.valuesArray);
        if (Intrinsics.areEqual(this.valuesArray[n2], entry.getValue()) ^ true) {
            return false;
        }
        this.removeKeyAt(n2);
        return true;
    }

    public final boolean removeValue$kotlin_stdlib(V v2) {
        this.checkIsMutable$kotlin_stdlib();
        int n2 = this.findValue(v2);
        if (n2 < 0) {
            return false;
        }
        this.removeKeyAt(n2);
        return true;
    }

    @NotNull
    public final KeysItr<K, V> keysIterator$kotlin_stdlib() {
        return new KeysItr(this);
    }

    @NotNull
    public final ValuesItr<K, V> valuesIterator$kotlin_stdlib() {
        return new ValuesItr(this);
    }

    @NotNull
    public final EntriesItr<K, V> entriesIterator$kotlin_stdlib() {
        return new EntriesItr(this);
    }

    private MapBuilder(K[] arrK, V[] arrV, int[] arrn, int[] arrn2, int n2, int n3) {
        this.keysArray = arrK;
        this.valuesArray = arrV;
        this.presenceArray = arrn;
        this.hashArray = arrn2;
        this.maxProbeDistance = n2;
        this.length = n3;
        this.hashShift = MapBuilder.Companion.computeShift(this.getHashSize());
    }

    public MapBuilder() {
        this(8);
    }

    public MapBuilder(int n2) {
        this(ListBuilderKt.arrayOfUninitializedElements(n2), null, new int[n2], new int[MapBuilder.Companion.computeHashSize(n2)], 2, 0);
    }

    public static final /* synthetic */ void access$setLength$p(MapBuilder mapBuilder, int n2) {
        mapBuilder.length = n2;
    }

    public static final /* synthetic */ void access$setPresenceArray$p(MapBuilder mapBuilder, int[] arrn) {
        mapBuilder.presenceArray = arrn;
    }

    public static final /* synthetic */ void access$setKeysArray$p(MapBuilder mapBuilder, Object[] arrobject) {
        mapBuilder.keysArray = arrobject;
    }

    public static final /* synthetic */ void access$setValuesArray$p(MapBuilder mapBuilder, Object[] arrobject) {
        mapBuilder.valuesArray = arrobject;
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0010\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0012\u001a\u00020\u0013J\r\u0010\u0014\u001a\u00020\u0015H\u0000\u00a2\u0006\u0002\b\u0016J\u0006\u0010\u0017\u001a\u00020\u0015R\u001a\u0010\u0007\u001a\u00020\bX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0018"}, d2={"Lkotlin/collections/builders/MapBuilder$Itr;", "K", "V", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "index", "", "getIndex$kotlin_stdlib", "()I", "setIndex$kotlin_stdlib", "(I)V", "lastIndex", "getLastIndex$kotlin_stdlib", "setLastIndex$kotlin_stdlib", "getMap$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder;", "hasNext", "", "initNext", "", "initNext$kotlin_stdlib", "remove", "kotlin-stdlib"})
    public static class Itr<K, V> {
        private int index;
        private int lastIndex;
        @NotNull
        private final MapBuilder<K, V> map;

        public final int getIndex$kotlin_stdlib() {
            return this.index;
        }

        public final void setIndex$kotlin_stdlib(int n2) {
            this.index = n2;
        }

        public final int getLastIndex$kotlin_stdlib() {
            return this.lastIndex;
        }

        public final void setLastIndex$kotlin_stdlib(int n2) {
            this.lastIndex = n2;
        }

        public final void initNext$kotlin_stdlib() {
            while (this.index < ((MapBuilder)this.map).length && ((MapBuilder)this.map).presenceArray[this.index] < 0) {
                int n2 = this.index;
                this.index = n2 + 1;
            }
        }

        public final boolean hasNext() {
            return this.index < ((MapBuilder)this.map).length;
        }

        public final void remove() {
            this.map.checkIsMutable$kotlin_stdlib();
            ((MapBuilder)this.map).removeKeyAt(this.lastIndex);
            this.lastIndex = -1;
        }

        @NotNull
        public final MapBuilder<K, V> getMap$kotlin_stdlib() {
            return this.map;
        }

        public Itr(@NotNull MapBuilder<K, V> mapBuilder) {
            Intrinsics.checkNotNullParameter(mapBuilder, "map");
            this.map = mapBuilder;
            this.lastIndex = -1;
            this.initNext$kotlin_stdlib();
        }
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0019\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00028\u0002H\u0096\u0002\u00a2\u0006\u0002\u0010\t\u00a8\u0006\n"}, d2={"Lkotlin/collections/builders/MapBuilder$KeysItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
    public static final class KeysItr<K, V>
    extends Itr<K, V>
    implements Iterator<K>,
    KMutableIterator {
        @Override
        public K next() {
            if (this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
                throw (Throwable)new NoSuchElementException();
            }
            KeysItr keysItr = this;
            int n2 = keysItr.getIndex$kotlin_stdlib();
            keysItr.setIndex$kotlin_stdlib(n2 + 1);
            this.setLastIndex$kotlin_stdlib(n2);
            Object object = this.getMap$kotlin_stdlib().keysArray[this.getLastIndex$kotlin_stdlib()];
            this.initNext$kotlin_stdlib();
            return (K)object;
        }

        public KeysItr(@NotNull MapBuilder<K, V> mapBuilder) {
            Intrinsics.checkNotNullParameter(mapBuilder, "map");
            super(mapBuilder);
        }
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004B\u0019\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00028\u0003H\u0096\u0002\u00a2\u0006\u0002\u0010\t\u00a8\u0006\n"}, d2={"Lkotlin/collections/builders/MapBuilder$ValuesItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
    public static final class ValuesItr<K, V>
    extends Itr<K, V>
    implements Iterator<V>,
    KMutableIterator {
        @Override
        public V next() {
            if (this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
                throw (Throwable)new NoSuchElementException();
            }
            ValuesItr valuesItr = this;
            int n2 = valuesItr.getIndex$kotlin_stdlib();
            valuesItr.setIndex$kotlin_stdlib(n2 + 1);
            this.setLastIndex$kotlin_stdlib(n2);
            Object[] arrobject = this.getMap$kotlin_stdlib().valuesArray;
            Intrinsics.checkNotNull(arrobject);
            Object object = arrobject[this.getLastIndex$kotlin_stdlib()];
            this.initNext$kotlin_stdlib();
            return (V)object;
        }

        public ValuesItr(@NotNull MapBuilder<K, V> mapBuilder) {
            Intrinsics.checkNotNullParameter(mapBuilder, "map");
            super(mapBuilder);
        }
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0002\u0010'\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00050\u0004B\u0019\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0007\u00a2\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\nH\u0096\u0002J\u0012\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fJ\r\u0010\u0010\u001a\u00020\u0011H\u0000\u00a2\u0006\u0002\b\u0012\u00a8\u0006\u0013"}, d2={"Lkotlin/collections/builders/MapBuilder$EntriesItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", "", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "Lkotlin/collections/builders/MapBuilder$EntryRef;", "nextAppendString", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "nextHashCode", "", "nextHashCode$kotlin_stdlib", "kotlin-stdlib"})
    public static final class EntriesItr<K, V>
    extends Itr<K, V>
    implements Iterator<Map.Entry<K, V>>,
    KMutableIterator {
        @Override
        @NotNull
        public EntryRef<K, V> next() {
            if (this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
                throw (Throwable)new NoSuchElementException();
            }
            EntriesItr entriesItr = this;
            int n2 = entriesItr.getIndex$kotlin_stdlib();
            entriesItr.setIndex$kotlin_stdlib(n2 + 1);
            this.setLastIndex$kotlin_stdlib(n2);
            EntryRef entryRef = new EntryRef(this.getMap$kotlin_stdlib(), this.getLastIndex$kotlin_stdlib());
            this.initNext$kotlin_stdlib();
            return entryRef;
        }

        public final int nextHashCode$kotlin_stdlib() {
            if (this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
                throw (Throwable)new NoSuchElementException();
            }
            EntriesItr entriesItr = this;
            int n2 = entriesItr.getIndex$kotlin_stdlib();
            entriesItr.setIndex$kotlin_stdlib(n2 + 1);
            this.setLastIndex$kotlin_stdlib(n2);
            Object object = this.getMap$kotlin_stdlib().keysArray[this.getLastIndex$kotlin_stdlib()];
            boolean bl = false;
            Object object2 = object;
            int n3 = object2 != null ? object2.hashCode() : 0;
            Object[] arrobject = this.getMap$kotlin_stdlib().valuesArray;
            Intrinsics.checkNotNull(arrobject);
            object = arrobject[this.getLastIndex$kotlin_stdlib()];
            bl = false;
            Object object3 = object;
            n2 = n3 ^ (object3 != null ? object3.hashCode() : 0);
            this.initNext$kotlin_stdlib();
            return n2;
        }

        public final void nextAppendString(@NotNull StringBuilder stringBuilder) {
            Intrinsics.checkNotNullParameter(stringBuilder, "sb");
            if (this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
                throw (Throwable)new NoSuchElementException();
            }
            EntriesItr entriesItr = this;
            int n2 = entriesItr.getIndex$kotlin_stdlib();
            entriesItr.setIndex$kotlin_stdlib(n2 + 1);
            this.setLastIndex$kotlin_stdlib(n2);
            Object object = this.getMap$kotlin_stdlib().keysArray[this.getLastIndex$kotlin_stdlib()];
            if (Intrinsics.areEqual(object, this.getMap$kotlin_stdlib())) {
                stringBuilder.append("(this Map)");
            } else {
                stringBuilder.append(object);
            }
            stringBuilder.append('=');
            Object[] arrobject = this.getMap$kotlin_stdlib().valuesArray;
            Intrinsics.checkNotNull(arrobject);
            Object object2 = arrobject[this.getLastIndex$kotlin_stdlib()];
            if (Intrinsics.areEqual(object2, this.getMap$kotlin_stdlib())) {
                stringBuilder.append("(this Map)");
            } else {
                stringBuilder.append(object2);
            }
            this.initNext$kotlin_stdlib();
        }

        public EntriesItr(@NotNull MapBuilder<K, V> mapBuilder) {
            Intrinsics.checkNotNullParameter(mapBuilder, "map");
            super(mapBuilder);
        }
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B!\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0007H\u0016J\u0015\u0010\u0013\u001a\u00028\u00032\u0006\u0010\u0014\u001a\u00028\u0003H\u0016\u00a2\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00028\u00028VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00028\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000b\u00a8\u0006\u0018"}, d2={"Lkotlin/collections/builders/MapBuilder$EntryRef;", "K", "V", "", "map", "Lkotlin/collections/builders/MapBuilder;", "index", "", "(Lkotlin/collections/builders/MapBuilder;I)V", "key", "getKey", "()Ljava/lang/Object;", "value", "getValue", "equals", "", "other", "", "hashCode", "setValue", "newValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "toString", "", "kotlin-stdlib"})
    public static final class EntryRef<K, V>
    implements Map.Entry<K, V>,
    KMutableMap.Entry {
        private final MapBuilder<K, V> map;
        private final int index;

        @Override
        public K getKey() {
            return (K)((MapBuilder)this.map).keysArray[this.index];
        }

        @Override
        public V getValue() {
            Object[] arrobject = ((MapBuilder)this.map).valuesArray;
            Intrinsics.checkNotNull(arrobject);
            return (V)arrobject[this.index];
        }

        @Override
        public V setValue(V v2) {
            this.map.checkIsMutable$kotlin_stdlib();
            Object[] arrobject = ((MapBuilder)this.map).allocateValuesArray();
            Object object = arrobject[this.index];
            arrobject[this.index] = v2;
            return (V)object;
        }

        @Override
        public boolean equals(@Nullable Object object) {
            return object instanceof Map.Entry && Intrinsics.areEqual(((Map.Entry)object).getKey(), this.getKey()) && Intrinsics.areEqual(((Map.Entry)object).getValue(), this.getValue());
        }

        @Override
        public int hashCode() {
            Object object = this.getKey();
            boolean bl = false;
            K k2 = object;
            object = this.getValue();
            bl = false;
            Object object2 = object;
            return (k2 != null ? k2.hashCode() : 0) ^ (object2 != null ? object2.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "" + this.getKey() + '=' + this.getValue();
        }

        public EntryRef(@NotNull MapBuilder<K, V> mapBuilder, int n2) {
            Intrinsics.checkNotNullParameter(mapBuilder, "map");
            this.map = mapBuilder;
            this.index = n2;
        }
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0002J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2={"Lkotlin/collections/builders/MapBuilder$Companion;", "", "()V", "INITIAL_CAPACITY", "", "INITIAL_MAX_PROBE_DISTANCE", "MAGIC", "TOMBSTONE", "computeHashSize", "capacity", "computeShift", "hashSize", "kotlin-stdlib"})
    private static final class Companion {
        private final int computeHashSize(int n2) {
            int n3 = RangesKt.coerceAtLeast(n2, 1) * 3;
            boolean bl = false;
            return Integer.highestOneBit(n3);
        }

        private final int computeShift(int n2) {
            int n3 = n2;
            boolean bl = false;
            return Integer.numberOfLeadingZeros(n3) + 1;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

