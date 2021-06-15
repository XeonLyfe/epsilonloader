/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatArcDarkContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Arc Dark Contrast (Material)";

    public static boolean install() {
        try {
            return FlatArcDarkContrastIJTheme.install(new FlatArcDarkContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-1848190654 ^ (long)-1848190654) != 0;
        }
    }

    public static void installLafInfo() {
        FlatArcDarkContrastIJTheme.installLafInfo(NAME, FlatArcDarkContrastIJTheme.class);
    }

    public FlatArcDarkContrastIJTheme() {
        super(Utils.loadTheme("Arc Dark Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

