/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatSolarizedLightIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Solarized Light";

    public static boolean install() {
        try {
            return FlatSolarizedLightIJTheme.install(new FlatSolarizedLightIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)-1219538353L ^ 0xB74F524F) != 0;
        }
    }

    public static void installLafInfo() {
        FlatSolarizedLightIJTheme.installLafInfo(NAME, FlatSolarizedLightIJTheme.class);
    }

    public FlatSolarizedLightIJTheme() {
        super(Utils.loadTheme("SolarizedLight.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

