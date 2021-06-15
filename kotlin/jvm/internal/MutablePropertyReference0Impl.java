/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

public class MutablePropertyReference0Impl
extends MutablePropertyReference0 {
    public MutablePropertyReference0Impl(KDeclarationContainer kDeclarationContainer, String string, String string2) {
        super(NO_RECEIVER, ((ClassBasedDeclarationContainer)kDeclarationContainer).getJClass(), string, string2, kDeclarationContainer instanceof KClass ? 0 : 1);
    }

    @SinceKotlin(version="1.4")
    public MutablePropertyReference0Impl(Class class_, String string, String string2, int n2) {
        super(NO_RECEIVER, class_, string, string2, n2);
    }

    @SinceKotlin(version="1.4")
    public MutablePropertyReference0Impl(Object object, Class class_, String string, String string2, int n2) {
        super(object, class_, string, string2, n2);
    }

    @Override
    public Object get() {
        return this.getGetter().call(new Object[0]);
    }

    public void set(Object object) {
        this.getSetter().call(object);
    }
}

