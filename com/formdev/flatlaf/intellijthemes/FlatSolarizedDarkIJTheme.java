/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatSolarizedDarkIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Solarized Dark";

    public static boolean install() {
        try {
            return FlatSolarizedDarkIJTheme.install(new FlatSolarizedDarkIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)693611499 ^ (long)693611499) != 0;
        }
    }

    public static void installLafInfo() {
        FlatSolarizedDarkIJTheme.installLafInfo(NAME, FlatSolarizedDarkIJTheme.class);
    }

    public FlatSolarizedDarkIJTheme() {
        super(Utils.loadTheme("SolarizedDark.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

