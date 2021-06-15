/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialDarkerContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Darker Contrast (Material)";

    public static boolean install() {
        try {
            return FlatMaterialDarkerContrastIJTheme.install(new FlatMaterialDarkerContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)-2052819467L ^ 0x85A475F5) != 0;
        }
    }

    public static void installLafInfo() {
        FlatMaterialDarkerContrastIJTheme.installLafInfo(NAME, FlatMaterialDarkerContrastIJTheme.class);
    }

    public FlatMaterialDarkerContrastIJTheme() {
        super(Utils.loadTheme("Material Darker Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

