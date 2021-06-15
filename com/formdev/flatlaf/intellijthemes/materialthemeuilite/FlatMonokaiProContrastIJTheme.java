/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMonokaiProContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Monokai Pro Contrast (Material)";

    public static boolean install() {
        try {
            return FlatMonokaiProContrastIJTheme.install(new FlatMonokaiProContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-1295729060 ^ (long)-1295729060) != 0;
        }
    }

    public static void installLafInfo() {
        FlatMonokaiProContrastIJTheme.installLafInfo(NAME, FlatMonokaiProContrastIJTheme.class);
    }

    public FlatMonokaiProContrastIJTheme() {
        super(Utils.loadTheme("Monokai Pro Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

