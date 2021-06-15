/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.PropertyReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty1;

public abstract class PropertyReference1
extends PropertyReference
implements KProperty1 {
    public PropertyReference1() {
    }

    @SinceKotlin(version="1.1")
    public PropertyReference1(Object object) {
        super(object);
    }

    @SinceKotlin(version="1.4")
    public PropertyReference1(Object object, Class class_, String string, String string2, int n2) {
        super(object, class_, string, string2, n2);
    }

    @Override
    protected KCallable computeReflected() {
        return Reflection.property1(this);
    }

    @Override
    public Object invoke(Object object) {
        return this.get(object);
    }

    public KProperty1.Getter getGetter() {
        return ((KProperty1)this.getReflected()).getGetter();
    }

    @SinceKotlin(version="1.1")
    public Object getDelegate(Object object) {
        return ((KProperty1)this.getReflected()).getDelegate(object);
    }
}

