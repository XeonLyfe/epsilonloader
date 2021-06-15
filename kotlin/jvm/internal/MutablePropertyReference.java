/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.PropertyReference;
import kotlin.reflect.KMutableProperty;

public abstract class MutablePropertyReference
extends PropertyReference
implements KMutableProperty {
    public MutablePropertyReference() {
    }

    @SinceKotlin(version="1.1")
    public MutablePropertyReference(Object object) {
        super(object);
    }

    @SinceKotlin(version="1.4")
    public MutablePropertyReference(Object object, Class class_, String string, String string2, int n2) {
        super(object, class_, string, string2, n2);
    }
}

