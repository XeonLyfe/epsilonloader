/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree.analysis;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class SmallSet<E>
extends AbstractSet<E>
implements Iterator<E> {
    E e1;
    E e2;

    static final <T> Set<T> emptySet() {
        return new SmallSet<Object>(null, null);
    }

    SmallSet(E e2, E e3) {
        this.e1 = e2;
        this.e2 = e3;
    }

    @Override
    public Iterator<E> iterator() {
        return new SmallSet<E>(this.e1, this.e2);
    }

    @Override
    public int size() {
        return this.e1 == null ? 0 : (this.e2 == null ? 1 : 2);
    }

    @Override
    public boolean hasNext() {
        return this.e1 != null;
    }

    @Override
    public E next() {
        if (this.e1 == null) {
            throw new NoSuchElementException();
        }
        E e2 = this.e1;
        this.e1 = this.e2;
        this.e2 = null;
        return e2;
    }

    @Override
    public void remove() {
    }

    Set<E> union(SmallSet<E> smallSet) {
        if (smallSet.e1 == this.e1 && smallSet.e2 == this.e2 || smallSet.e1 == this.e2 && smallSet.e2 == this.e1) {
            return this;
        }
        if (smallSet.e1 == null) {
            return this;
        }
        if (this.e1 == null) {
            return smallSet;
        }
        if (smallSet.e2 == null) {
            if (this.e2 == null) {
                return new SmallSet<E>(this.e1, smallSet.e1);
            }
            if (smallSet.e1 == this.e1 || smallSet.e1 == this.e2) {
                return this;
            }
        }
        if (this.e2 == null && (this.e1 == smallSet.e1 || this.e1 == smallSet.e2)) {
            return smallSet;
        }
        HashSet<E> hashSet = new HashSet<E>(4);
        hashSet.add(this.e1);
        if (this.e2 != null) {
            hashSet.add(this.e2);
        }
        hashSet.add(smallSet.e1);
        if (smallSet.e2 != null) {
            hashSet.add(smallSet.e2);
        }
        return hashSet;
    }
}

