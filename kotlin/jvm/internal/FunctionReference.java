/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;

public class FunctionReference
extends CallableReference
implements FunctionBase,
KFunction {
    private final int arity;
    @SinceKotlin(version="1.4")
    private final int flags;

    public FunctionReference(int n2) {
        this(n2, NO_RECEIVER, null, null, null, 0);
    }

    @SinceKotlin(version="1.1")
    public FunctionReference(int n2, Object object) {
        this(n2, object, null, null, null, 0);
    }

    @SinceKotlin(version="1.4")
    public FunctionReference(int n2, Object object, Class class_, String string, String string2, int n3) {
        super(object, class_, string, string2, (n3 & 1) == 1);
        this.arity = n2;
        this.flags = n3 >> 1;
    }

    @Override
    public int getArity() {
        return this.arity;
    }

    @Override
    @SinceKotlin(version="1.1")
    protected KFunction getReflected() {
        return (KFunction)super.getReflected();
    }

    @Override
    @SinceKotlin(version="1.1")
    protected KCallable computeReflected() {
        return Reflection.function(this);
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isInline() {
        return this.getReflected().isInline();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isExternal() {
        return this.getReflected().isExternal();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isOperator() {
        return this.getReflected().isOperator();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isInfix() {
        return this.getReflected().isInfix();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isSuspend() {
        return this.getReflected().isSuspend();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof FunctionReference) {
            FunctionReference functionReference = (FunctionReference)object;
            return Intrinsics.areEqual(this.getOwner(), functionReference.getOwner()) && this.getName().equals(functionReference.getName()) && this.getSignature().equals(functionReference.getSignature()) && this.flags == functionReference.flags && this.arity == functionReference.arity && Intrinsics.areEqual(this.getBoundReceiver(), functionReference.getBoundReceiver());
        }
        if (object instanceof KFunction) {
            return object.equals(this.compute());
        }
        return false;
    }

    public int hashCode() {
        return ((this.getOwner() == null ? 0 : this.getOwner().hashCode() * 31) + this.getName().hashCode()) * 31 + this.getSignature().hashCode();
    }

    public String toString() {
        KCallable kCallable = this.compute();
        if (kCallable != this) {
            return kCallable.toString();
        }
        return "<init>".equals(this.getName()) ? "constructor (Kotlin reflection is not available)" : "function " + this.getName() + " (Kotlin reflection is not available)";
    }
}

