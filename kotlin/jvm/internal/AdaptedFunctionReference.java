/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@SinceKotlin(version="1.4")
public class AdaptedFunctionReference
implements FunctionBase,
Serializable {
    protected final Object receiver;
    private final Class owner;
    private final String name;
    private final String signature;
    private final boolean isTopLevel;
    private final int arity;
    private final int flags;

    public AdaptedFunctionReference(int n2, Class class_, String string, String string2, int n3) {
        this(n2, CallableReference.NO_RECEIVER, class_, string, string2, n3);
    }

    public AdaptedFunctionReference(int n2, Object object, Class class_, String string, String string2, int n3) {
        this.receiver = object;
        this.owner = class_;
        this.name = string;
        this.signature = string2;
        this.isTopLevel = (n3 & 1) == 1;
        this.arity = n2;
        this.flags = n3 >> 1;
    }

    @Override
    public int getArity() {
        return this.arity;
    }

    public KDeclarationContainer getOwner() {
        return this.owner == null ? null : (this.isTopLevel ? Reflection.getOrCreateKotlinPackage(this.owner) : Reflection.getOrCreateKotlinClass(this.owner));
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AdaptedFunctionReference)) {
            return false;
        }
        AdaptedFunctionReference adaptedFunctionReference = (AdaptedFunctionReference)object;
        return this.isTopLevel == adaptedFunctionReference.isTopLevel && this.arity == adaptedFunctionReference.arity && this.flags == adaptedFunctionReference.flags && Intrinsics.areEqual(this.receiver, adaptedFunctionReference.receiver) && Intrinsics.areEqual(this.owner, adaptedFunctionReference.owner) && this.name.equals(adaptedFunctionReference.name) && this.signature.equals(adaptedFunctionReference.signature);
    }

    public int hashCode() {
        int n2 = this.receiver != null ? this.receiver.hashCode() : 0;
        n2 = n2 * 31 + (this.owner != null ? this.owner.hashCode() : 0);
        n2 = n2 * 31 + this.name.hashCode();
        n2 = n2 * 31 + this.signature.hashCode();
        n2 = n2 * 31 + (this.isTopLevel ? 1231 : 1237);
        n2 = n2 * 31 + this.arity;
        n2 = n2 * 31 + this.flags;
        return n2;
    }

    public String toString() {
        return Reflection.renderLambdaToString(this);
    }
}

