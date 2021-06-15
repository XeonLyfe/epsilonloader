/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialOceanicContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Oceanic Contrast (Material)";

    public static boolean install() {
        try {
            return FlatMaterialOceanicContrastIJTheme.install(new FlatMaterialOceanicContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-539600647 ^ (long)-539600647) != 0;
        }
    }

    public static void installLafInfo() {
        FlatMaterialOceanicContrastIJTheme.installLafInfo(NAME, FlatMaterialOceanicContrastIJTheme.class);
    }

    public FlatMaterialOceanicContrastIJTheme() {
        super(Utils.loadTheme("Material Oceanic Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

