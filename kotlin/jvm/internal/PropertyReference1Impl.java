/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

public class PropertyReference1Impl
extends PropertyReference1 {
    public PropertyReference1Impl(KDeclarationContainer kDeclarationContainer, String string, String string2) {
        super(NO_RECEIVER, ((ClassBasedDeclarationContainer)kDeclarationContainer).getJClass(), string, string2, kDeclarationContainer instanceof KClass ? 0 : 1);
    }

    @SinceKotlin(version="1.4")
    public PropertyReference1Impl(Class class_, String string, String string2, int n2) {
        super(NO_RECEIVER, class_, string, string2, n2);
    }

    @SinceKotlin(version="1.4")
    public PropertyReference1Impl(Object object, Class class_, String string, String string2, int n2) {
        super(object, class_, string, string2, n2);
    }

    public Object get(Object object) {
        return this.getGetter().call(object);
    }
}

