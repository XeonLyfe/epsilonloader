/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatSolarizedDarkIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Solarized Dark (Material)";

    public static boolean install() {
        try {
            return FlatSolarizedDarkIJTheme.install(new FlatSolarizedDarkIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)849045271L ^ 0x329B6717) != 0;
        }
    }

    public static void installLafInfo() {
        FlatSolarizedDarkIJTheme.installLafInfo(NAME, FlatSolarizedDarkIJTheme.class);
    }

    public FlatSolarizedDarkIJTheme() {
        super(Utils.loadTheme("Solarized Dark.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

