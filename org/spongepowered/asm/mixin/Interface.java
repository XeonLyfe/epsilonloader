/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.TYPE})
@Retention(value=RetentionPolicy.CLASS)
public @interface Interface {
    public Class<?> iface();

    public String prefix();

    public boolean unique() default false;

    public Remap remap() default Remap.ALL;

    public static enum Remap {
        ALL,
        FORCE(true),
        ONLY_PREFIXED,
        NONE;

        private final boolean forceRemap;

        private Remap() {
            this(false);
        }

        private Remap(boolean bl) {
            this.forceRemap = bl;
        }

        public boolean forceRemap() {
            return this.forceRemap;
        }
    }
}

