/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty;

public abstract class PropertyReference
extends CallableReference
implements KProperty {
    public PropertyReference() {
    }

    @SinceKotlin(version="1.1")
    public PropertyReference(Object object) {
        super(object);
    }

    @SinceKotlin(version="1.4")
    public PropertyReference(Object object, Class class_, String string, String string2, int n2) {
        super(object, class_, string, string2, (n2 & 1) == 1);
    }

    @Override
    @SinceKotlin(version="1.1")
    protected KProperty getReflected() {
        return (KProperty)super.getReflected();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isLateinit() {
        return this.getReflected().isLateinit();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isConst() {
        return this.getReflected().isConst();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof PropertyReference) {
            PropertyReference propertyReference = (PropertyReference)object;
            return this.getOwner().equals(propertyReference.getOwner()) && this.getName().equals(propertyReference.getName()) && this.getSignature().equals(propertyReference.getSignature()) && Intrinsics.areEqual(this.getBoundReceiver(), propertyReference.getBoundReceiver());
        }
        if (object instanceof KProperty) {
            return object.equals(this.compute());
        }
        return false;
    }

    public int hashCode() {
        return (this.getOwner().hashCode() * 31 + this.getName().hashCode()) * 31 + this.getSignature().hashCode();
    }

    public String toString() {
        KCallable kCallable = this.compute();
        if (kCallable != this) {
            return kCallable.toString();
        }
        return "property " + this.getName() + " (Kotlin reflection is not available)";
    }
}

