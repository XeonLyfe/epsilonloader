/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;

@java.lang.Deprecated
@Deprecated(message="This class is no longer supported, do not use it.", level=DeprecationLevel.ERROR)
public abstract class FunctionImpl
implements Function,
Serializable,
Function0,
Function1,
Function2,
Function3,
Function4,
Function5,
Function6,
Function7,
Function8,
Function9,
Function10,
Function11,
Function12,
Function13,
Function14,
Function15,
Function16,
Function17,
Function18,
Function19,
Function20,
Function21,
Function22 {
    public abstract int getArity();

    public Object invokeVararg(Object ... arrobject) {
        throw new UnsupportedOperationException();
    }

    private void checkArity(int n2) {
        if (this.getArity() != n2) {
            this.throwWrongArity(n2);
        }
    }

    private void throwWrongArity(int n2) {
        throw new IllegalStateException("Wrong function arity, expected: " + n2 + ", actual: " + this.getArity());
    }

    public Object invoke() {
        this.checkArity(0);
        return this.invokeVararg(new Object[0]);
    }

    public Object invoke(Object object) {
        this.checkArity(1);
        return this.invokeVararg(object);
    }

    public Object invoke(Object object, Object object2) {
        this.checkArity(2);
        return this.invokeVararg(object, object2);
    }

    public Object invoke(Object object, Object object2, Object object3) {
        this.checkArity(3);
        return this.invokeVararg(object, object2, object3);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4) {
        this.checkArity(4);
        return this.invokeVararg(object, object2, object3, object4);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5) {
        this.checkArity(5);
        return this.invokeVararg(object, object2, object3, object4, object5);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6) {
        this.checkArity(6);
        return this.invokeVararg(object, object2, object3, object4, object5, object6);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7) {
        this.checkArity(7);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8) {
        this.checkArity(8);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9) {
        this.checkArity(9);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10) {
        this.checkArity(10);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11) {
        this.checkArity(11);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12) {
        this.checkArity(12);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13) {
        this.checkArity(13);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14) {
        this.checkArity(14);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15) {
        this.checkArity(15);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16) {
        this.checkArity(16);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17) {
        this.checkArity(17);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18) {
        this.checkArity(18);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18, Object object19) {
        this.checkArity(19);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18, object19);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18, Object object19, Object object20) {
        this.checkArity(20);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18, object19, object20);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18, Object object19, Object object20, Object object21) {
        this.checkArity(21);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18, object19, object20, object21);
    }

    public Object invoke(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18, Object object19, Object object20, Object object21, Object object22) {
        this.checkArity(22);
        return this.invokeVararg(object, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18, object19, object20, object21, object22);
    }
}

