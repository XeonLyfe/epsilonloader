/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatLightOwlIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Light Owl (Material)";

    public static boolean install() {
        try {
            return FlatLightOwlIJTheme.install(new FlatLightOwlIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-1919869980 ^ (long)-1919869980) != 0;
        }
    }

    public static void installLafInfo() {
        FlatLightOwlIJTheme.installLafInfo(NAME, FlatLightOwlIJTheme.class);
    }

    public FlatLightOwlIJTheme() {
        super(Utils.loadTheme("Light Owl.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

