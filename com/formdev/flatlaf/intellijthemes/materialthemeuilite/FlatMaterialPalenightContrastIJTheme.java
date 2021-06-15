/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialPalenightContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Palenight Contrast (Material)";

    public static boolean install() {
        try {
            return FlatMaterialPalenightContrastIJTheme.install(new FlatMaterialPalenightContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-1028930545 ^ (long)-1028930545) != 0;
        }
    }

    public static void installLafInfo() {
        FlatMaterialPalenightContrastIJTheme.installLafInfo(NAME, FlatMaterialPalenightContrastIJTheme.class);
    }

    public FlatMaterialPalenightContrastIJTheme() {
        super(Utils.loadTheme("Material Palenight Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

