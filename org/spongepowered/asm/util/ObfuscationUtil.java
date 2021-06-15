/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util;

public abstract class ObfuscationUtil {
    private ObfuscationUtil() {
    }

    public static String mapDescriptor(String string, IClassRemapper iClassRemapper) {
        return ObfuscationUtil.remapDescriptor(string, iClassRemapper, false);
    }

    public static String unmapDescriptor(String string, IClassRemapper iClassRemapper) {
        return ObfuscationUtil.remapDescriptor(string, iClassRemapper, true);
    }

    private static String remapDescriptor(String string, IClassRemapper iClassRemapper, boolean bl) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = null;
        for (int i2 = 0; i2 < string.length(); ++i2) {
            char c2 = string.charAt(i2);
            if (stringBuilder2 != null) {
                if (c2 == ';') {
                    stringBuilder.append('L').append(ObfuscationUtil.remap(stringBuilder2.toString(), iClassRemapper, bl)).append(';');
                    stringBuilder2 = null;
                    continue;
                }
                stringBuilder2.append(c2);
                continue;
            }
            if (c2 == 'L') {
                stringBuilder2 = new StringBuilder();
                continue;
            }
            stringBuilder.append(c2);
        }
        if (stringBuilder2 != null) {
            throw new IllegalArgumentException("Invalid descriptor '" + string + "', missing ';'");
        }
        return stringBuilder.toString();
    }

    private static Object remap(String string, IClassRemapper iClassRemapper, boolean bl) {
        String string2 = bl ? iClassRemapper.unmap(string) : iClassRemapper.map(string);
        return string2 != null ? string2 : string;
    }

    public static interface IClassRemapper {
        public String map(String var1);

        public String unmap(String var1);
    }
}

