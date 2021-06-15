/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.spongepowered.tools.obfuscation.ObfuscationType;

public class ObfuscationData<T>
implements Iterable<ObfuscationType> {
    private final Map<ObfuscationType, T> data = new HashMap<ObfuscationType, T>();
    private final T defaultValue;

    public ObfuscationData() {
        this(null);
    }

    public ObfuscationData(T t2) {
        this.defaultValue = t2;
    }

    @Deprecated
    public void add(ObfuscationType obfuscationType, T t2) {
        this.put(obfuscationType, t2);
    }

    public void put(ObfuscationType obfuscationType, T t2) {
        this.data.put(obfuscationType, t2);
    }

    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    public T get(ObfuscationType obfuscationType) {
        T t2 = this.data.get(obfuscationType);
        return t2 != null ? t2 : this.defaultValue;
    }

    @Override
    public Iterator<ObfuscationType> iterator() {
        return this.data.keySet().iterator();
    }

    public String toString() {
        return String.format("ObfuscationData[%s,DEFAULT=%s]", this.listValues(), this.defaultValue);
    }

    public String values() {
        return "[" + this.listValues() + "]";
    }

    private String listValues() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl = false;
        for (ObfuscationType obfuscationType : this.data.keySet()) {
            if (bl) {
                stringBuilder.append(',');
            }
            stringBuilder.append(obfuscationType.getKey()).append('=').append(this.data.get(obfuscationType));
            bl = true;
        }
        return stringBuilder.toString();
    }
}

