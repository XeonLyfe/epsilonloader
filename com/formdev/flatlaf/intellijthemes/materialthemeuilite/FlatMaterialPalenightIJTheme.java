/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialPalenightIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Palenight (Material)";

    public static boolean install() {
        try {
            return FlatMaterialPalenightIJTheme.install(new FlatMaterialPalenightIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-1826377696 ^ (long)-1826377696) != 0;
        }
    }

    public static void installLafInfo() {
        FlatMaterialPalenightIJTheme.installLafInfo(NAME, FlatMaterialPalenightIJTheme.class);
    }

    public FlatMaterialPalenightIJTheme() {
        super(Utils.loadTheme("Material Palenight.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

