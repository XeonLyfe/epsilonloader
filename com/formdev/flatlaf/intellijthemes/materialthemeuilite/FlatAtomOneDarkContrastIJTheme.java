/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatAtomOneDarkContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Atom One Dark Contrast (Material)";

    public static boolean install() {
        try {
            return FlatAtomOneDarkContrastIJTheme.install(new FlatAtomOneDarkContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-233909711 ^ (long)-233909711) != 0;
        }
    }

    public static void installLafInfo() {
        FlatAtomOneDarkContrastIJTheme.installLafInfo(NAME, FlatAtomOneDarkContrastIJTheme.class);
    }

    public FlatAtomOneDarkContrastIJTheme() {
        super(Utils.loadTheme("Atom One Dark Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

