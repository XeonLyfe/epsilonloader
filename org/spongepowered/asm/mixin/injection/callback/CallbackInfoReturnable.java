/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.callback;

import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CancellationException;

public class CallbackInfoReturnable<R>
extends CallbackInfo {
    private R returnValue;

    public CallbackInfoReturnable(String string, boolean bl) {
        super(string, bl);
        this.returnValue = null;
    }

    public CallbackInfoReturnable(String string, boolean bl, R r2) {
        super(string, bl);
        this.returnValue = r2;
    }

    public CallbackInfoReturnable(String string, boolean bl, byte by) {
        super(string, bl);
        this.returnValue = by;
    }

    public CallbackInfoReturnable(String string, boolean bl, char c2) {
        super(string, bl);
        this.returnValue = Character.valueOf(c2);
    }

    public CallbackInfoReturnable(String string, boolean bl, double d2) {
        super(string, bl);
        this.returnValue = d2;
    }

    public CallbackInfoReturnable(String string, boolean bl, float f2) {
        super(string, bl);
        this.returnValue = Float.valueOf(f2);
    }

    public CallbackInfoReturnable(String string, boolean bl, int n2) {
        super(string, bl);
        this.returnValue = n2;
    }

    public CallbackInfoReturnable(String string, boolean bl, long l2) {
        super(string, bl);
        this.returnValue = l2;
    }

    public CallbackInfoReturnable(String string, boolean bl, short s2) {
        super(string, bl);
        this.returnValue = s2;
    }

    public CallbackInfoReturnable(String string, boolean bl, boolean bl2) {
        super(string, bl);
        this.returnValue = bl2;
    }

    public void setReturnValue(R r2) throws CancellationException {
        super.cancel();
        this.returnValue = r2;
    }

    public R getReturnValue() {
        return this.returnValue;
    }

    public byte getReturnValueB() {
        if (this.returnValue == null) {
            return 0;
        }
        return (Byte)this.returnValue;
    }

    public char getReturnValueC() {
        if (this.returnValue == null) {
            return '\u0000';
        }
        return ((Character)this.returnValue).charValue();
    }

    public double getReturnValueD() {
        if (this.returnValue == null) {
            return 0.0;
        }
        return (Double)this.returnValue;
    }

    public float getReturnValueF() {
        if (this.returnValue == null) {
            return 0.0f;
        }
        return ((Float)this.returnValue).floatValue();
    }

    public int getReturnValueI() {
        if (this.returnValue == null) {
            return 0;
        }
        return (Integer)this.returnValue;
    }

    public long getReturnValueJ() {
        if (this.returnValue == null) {
            return 0L;
        }
        return (Long)this.returnValue;
    }

    public short getReturnValueS() {
        if (this.returnValue == null) {
            return 0;
        }
        return (Short)this.returnValue;
    }

    public boolean getReturnValueZ() {
        if (this.returnValue == null) {
            return false;
        }
        return (Boolean)this.returnValue;
    }

    static String getReturnAccessor(Type type) {
        if (type.getSort() == 10 || type.getSort() == 9) {
            return "getReturnValue";
        }
        return String.format("getReturnValue%s", type.getDescriptor());
    }

    static String getReturnDescriptor(Type type) {
        if (type.getSort() == 10 || type.getSort() == 9) {
            return String.format("()%s", "Ljava/lang/Object;");
        }
        return String.format("()%s", type.getDescriptor());
    }
}

