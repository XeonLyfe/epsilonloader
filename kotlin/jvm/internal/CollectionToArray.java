/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0007\u00a2\u0006\u0004\b\t\u0010\n\u001a5\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\u0007\u00a2\u0006\u0004\b\t\u0010\f\u001a~\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0014\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u000f2\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u00112(\u0010\u0012\u001a$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u0013H\u0082\b\u00a2\u0006\u0002\u0010\u0014\"\u0018\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2={"EMPTY", "", "", "[Ljava/lang/Object;", "MAX_SIZE", "", "collectionToArray", "collection", "", "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "a", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "toArrayImpl", "empty", "Lkotlin/Function0;", "alloc", "Lkotlin/Function1;", "trim", "Lkotlin/Function2;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "kotlin-stdlib"})
@JvmName(name="CollectionToArray")
public final class CollectionToArray {
    private static final Object[] EMPTY = new Object[0];
    private static final int MAX_SIZE = 0x7FFFFFFD;

    @JvmName(name="toArray")
    @NotNull
    public static final Object[] toArray(@NotNull Collection<?> collection) {
        Object[] arrobject;
        block9: {
            Intrinsics.checkNotNullParameter(collection, "collection");
            boolean bl = false;
            int n2 = collection.size();
            if (n2 == 0) {
                boolean bl2 = false;
                arrobject = EMPTY;
            } else {
                Iterator<?> iterator2 = collection.iterator();
                if (!iterator2.hasNext()) {
                    boolean bl3 = false;
                    arrobject = EMPTY;
                } else {
                    int n3 = n2;
                    int n4 = 0;
                    Object[] arrobject2 = new Object[n3];
                    n4 = 0;
                    while (true) {
                        arrobject2[n4++] = iterator2.next();
                        if (n4 >= arrobject2.length) {
                            if (!iterator2.hasNext()) {
                                arrobject = arrobject2;
                                break block9;
                            }
                            int n5 = n4 * 3 + 1 >>> 1;
                            if (n5 <= n4) {
                                if (n4 >= 0x7FFFFFFD) {
                                    throw (Throwable)new OutOfMemoryError();
                                }
                                n5 = 0x7FFFFFFD;
                            }
                            Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(arrobject2, n5), "Arrays.copyOf(result, newSize)");
                            continue;
                        }
                        if (!iterator2.hasNext()) break;
                    }
                    int n6 = n4;
                    Object[] arrobject3 = arrobject2;
                    boolean bl4 = false;
                    Object[] arrobject4 = Arrays.copyOf(arrobject3, n6);
                    arrobject = arrobject4;
                    Intrinsics.checkNotNullExpressionValue(arrobject4, "Arrays.copyOf(result, size)");
                }
            }
        }
        return arrobject;
    }

    @JvmName(name="toArray")
    @NotNull
    public static final Object[] toArray(@NotNull Collection<?> collection, @Nullable Object[] arrobject) {
        Object[] arrobject2;
        block17: {
            Intrinsics.checkNotNullParameter(collection, "collection");
            if (arrobject == null) {
                throw (Throwable)new NullPointerException();
            }
            boolean bl = false;
            int n2 = collection.size();
            if (n2 == 0) {
                boolean bl2 = false;
                if (arrobject.length > 0) {
                    arrobject[0] = null;
                }
                arrobject2 = arrobject;
            } else {
                Iterator<?> iterator2 = collection.iterator();
                if (!iterator2.hasNext()) {
                    boolean bl3 = false;
                    if (arrobject.length > 0) {
                        arrobject[0] = null;
                    }
                    arrobject2 = arrobject;
                } else {
                    Object[] arrobject3;
                    int n3 = n2;
                    int n4 = 0;
                    if (n3 <= arrobject.length) {
                        arrobject3 = arrobject;
                    } else {
                        Object object = Array.newInstance(arrobject.getClass().getComponentType(), n3);
                        if (object == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                        }
                        arrobject3 = (Object[])object;
                    }
                    Object[] arrobject4 = arrobject3;
                    n3 = 0;
                    while (true) {
                        arrobject4[n3++] = iterator2.next();
                        if (n3 >= arrobject4.length) {
                            if (!iterator2.hasNext()) {
                                arrobject2 = arrobject4;
                                break block17;
                            }
                            n4 = n3 * 3 + 1 >>> 1;
                            if (n4 <= n3) {
                                if (n3 >= 0x7FFFFFFD) {
                                    throw (Throwable)new OutOfMemoryError();
                                }
                                n4 = 0x7FFFFFFD;
                            }
                            Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(arrobject4, n4), "Arrays.copyOf(result, newSize)");
                            continue;
                        }
                        if (!iterator2.hasNext()) break;
                    }
                    int n5 = n3;
                    Object[] arrobject5 = arrobject4;
                    boolean bl4 = false;
                    if (arrobject5 == arrobject) {
                        arrobject[n5] = null;
                        arrobject2 = arrobject;
                    } else {
                        Object[] arrobject6 = Arrays.copyOf(arrobject5, n5);
                        arrobject2 = arrobject6;
                        Intrinsics.checkNotNullExpressionValue(arrobject6, "Arrays.copyOf(result, size)");
                    }
                }
            }
        }
        return arrobject2;
    }

    private static final Object[] toArrayImpl(Collection<?> collection, Function0<Object[]> function0, Function1<? super Integer, Object[]> function1, Function2<? super Object[], ? super Integer, Object[]> function2) {
        int n2 = 0;
        int n3 = collection.size();
        if (n3 == 0) {
            return function0.invoke();
        }
        Iterator<?> iterator2 = collection.iterator();
        if (!iterator2.hasNext()) {
            return function0.invoke();
        }
        Object[] arrobject = function1.invoke((Integer)n3);
        int n4 = 0;
        while (true) {
            arrobject[n4++] = iterator2.next();
            if (n4 >= arrobject.length) {
                if (!iterator2.hasNext()) {
                    return arrobject;
                }
                int n5 = n4 * 3 + 1 >>> 1;
                if (n5 <= n4) {
                    if (n4 >= 0x7FFFFFFD) {
                        throw (Throwable)new OutOfMemoryError();
                    }
                    n5 = 0x7FFFFFFD;
                }
                Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(arrobject, n5), "Arrays.copyOf(result, newSize)");
                continue;
            }
            if (!iterator2.hasNext()) break;
        }
        return function2.invoke((Object[])arrobject, (Integer)n4);
    }
}

