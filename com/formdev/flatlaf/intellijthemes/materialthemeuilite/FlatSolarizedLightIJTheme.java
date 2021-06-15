/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatSolarizedLightIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Solarized Light (Material)";

    public static boolean install() {
        try {
            return FlatSolarizedLightIJTheme.install(new FlatSolarizedLightIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)1543797243 ^ (long)1543797243) != 0;
        }
    }

    public static void installLafInfo() {
        FlatSolarizedLightIJTheme.installLafInfo(NAME, FlatSolarizedLightIJTheme.class);
    }

    public FlatSolarizedLightIJTheme() {
        super(Utils.loadTheme("Solarized Light.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

