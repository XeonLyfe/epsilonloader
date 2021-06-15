/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatAtomOneLightContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Atom One Light Contrast (Material)";

    public static boolean install() {
        try {
            return FlatAtomOneLightContrastIJTheme.install(new FlatAtomOneLightContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)-78839902L ^ 0xFB4CFFA2) != 0;
        }
    }

    public static void installLafInfo() {
        FlatAtomOneLightContrastIJTheme.installLafInfo(NAME, FlatAtomOneLightContrastIJTheme.class);
    }

    public FlatAtomOneLightContrastIJTheme() {
        super(Utils.loadTheme("Atom One Light Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

