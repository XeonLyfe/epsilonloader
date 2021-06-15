/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.FunctionReference;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

public class FunctionReferenceImpl
extends FunctionReference {
    public FunctionReferenceImpl(int n2, KDeclarationContainer kDeclarationContainer, String string, String string2) {
        super(n2, NO_RECEIVER, ((ClassBasedDeclarationContainer)kDeclarationContainer).getJClass(), string, string2, kDeclarationContainer instanceof KClass ? 0 : 1);
    }

    @SinceKotlin(version="1.4")
    public FunctionReferenceImpl(int n2, Class class_, String string, String string2, int n3) {
        super(n2, NO_RECEIVER, class_, string, string2, n3);
    }

    @SinceKotlin(version="1.4")
    public FunctionReferenceImpl(int n2, Object object, Class class_, String string, String string2, int n3) {
        super(n2, object, class_, string, string2, n3);
    }
}

