/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatHighContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "High contrast";

    public static boolean install() {
        try {
            return FlatHighContrastIJTheme.install(new FlatHighContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)1385186570 ^ (long)1385186570) != 0;
        }
    }

    public static void installLafInfo() {
        FlatHighContrastIJTheme.installLafInfo(NAME, FlatHighContrastIJTheme.class);
    }

    public FlatHighContrastIJTheme() {
        super(Utils.loadTheme("HighContrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

