/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatSolarizedDarkContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Solarized Dark Contrast (Material)";

    public static boolean install() {
        try {
            return FlatSolarizedDarkContrastIJTheme.install(new FlatSolarizedDarkContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)900228793L ^ 0x35A866B9) != 0;
        }
    }

    public static void installLafInfo() {
        FlatSolarizedDarkContrastIJTheme.installLafInfo(NAME, FlatSolarizedDarkContrastIJTheme.class);
    }

    public FlatSolarizedDarkContrastIJTheme() {
        super(Utils.loadTheme("Solarized Dark Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

