/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class SpreadBuilder {
    private final ArrayList<Object> list;

    public SpreadBuilder(int n2) {
        this.list = new ArrayList(n2);
    }

    public void addSpread(Object object) {
        if (object == null) {
            return;
        }
        if (object instanceof Object[]) {
            Object[] arrobject = (Object[])object;
            if (arrobject.length > 0) {
                this.list.ensureCapacity(this.list.size() + arrobject.length);
                Collections.addAll(this.list, arrobject);
            }
        } else if (object instanceof Collection) {
            this.list.addAll((Collection)object);
        } else if (object instanceof Iterable) {
            for (Object t2 : (Iterable)object) {
                this.list.add(t2);
            }
        } else if (object instanceof Iterator) {
            Iterator iterator2 = (Iterator)object;
            while (iterator2.hasNext()) {
                this.list.add(iterator2.next());
            }
        } else {
            throw new UnsupportedOperationException("Don't know how to spread " + object.getClass());
        }
    }

    public int size() {
        return this.list.size();
    }

    public void add(Object object) {
        this.list.add(object);
    }

    public Object[] toArray(Object[] arrobject) {
        return this.list.toArray(arrobject);
    }
}

