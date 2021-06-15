/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialOceanicIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Oceanic (Material)";

    public static boolean install() {
        try {
            return FlatMaterialOceanicIJTheme.install(new FlatMaterialOceanicIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-1932194843 ^ (long)-1932194843) != 0;
        }
    }

    public static void installLafInfo() {
        FlatMaterialOceanicIJTheme.installLafInfo(NAME, FlatMaterialOceanicIJTheme.class);
    }

    public FlatMaterialOceanicIJTheme() {
        super(Utils.loadTheme("Material Oceanic.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

