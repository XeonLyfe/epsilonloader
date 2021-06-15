/*
 * Decompiled with CFR 0.150.
 */
package com.loader.epsilon;

import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$t;
import org.spongepowered.asm.mixin.MixinEnvironment;

public class $$$$$$$$ESKID$$$$$$$$$e {
    public static boolean a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t) {
        return $$$$$$$$ESKID$$$$$$$$$t.g();
    }

    public static void a(MixinEnvironment mixinEnvironment, String string) {
        mixinEnvironment.setObfuscationContext(string);
    }

    public static MixinEnvironment a(MixinEnvironment mixinEnvironment, MixinEnvironment.Side side) {
        return mixinEnvironment.setSide(side);
    }

    public static boolean a(Boolean bl) {
        return bl;
    }
}

