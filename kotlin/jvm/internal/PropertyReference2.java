/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.PropertyReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty2;

public abstract class PropertyReference2
extends PropertyReference
implements KProperty2 {
    public PropertyReference2() {
    }

    @SinceKotlin(version="1.4")
    public PropertyReference2(Class class_, String string, String string2, int n2) {
        super(NO_RECEIVER, class_, string, string2, n2);
    }

    @Override
    protected KCallable computeReflected() {
        return Reflection.property2(this);
    }

    @Override
    public Object invoke(Object object, Object object2) {
        return this.get(object, object2);
    }

    public KProperty2.Getter getGetter() {
        return ((KProperty2)this.getReflected()).getGetter();
    }

    @SinceKotlin(version="1.1")
    public Object getDelegate(Object object, Object object2) {
        return ((KProperty2)this.getReflected()).getDelegate(object, object2);
    }
}

