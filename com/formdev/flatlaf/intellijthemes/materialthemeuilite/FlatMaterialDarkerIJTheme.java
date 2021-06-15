/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialDarkerIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Darker (Material)";

    public static boolean install() {
        try {
            return FlatMaterialDarkerIJTheme.install(new FlatMaterialDarkerIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)1244450832L ^ 0x4A2CD010) != 0;
        }
    }

    public static void installLafInfo() {
        FlatMaterialDarkerIJTheme.installLafInfo(NAME, FlatMaterialDarkerIJTheme.class);
    }

    public FlatMaterialDarkerIJTheme() {
        super(Utils.loadTheme("Material Darker.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

