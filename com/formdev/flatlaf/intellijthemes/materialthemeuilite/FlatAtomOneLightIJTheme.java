/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatAtomOneLightIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Atom One Light (Material)";

    public static boolean install() {
        try {
            return FlatAtomOneLightIJTheme.install(new FlatAtomOneLightIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)1900121211 ^ (long)1900121211) != 0;
        }
    }

    public static void installLafInfo() {
        FlatAtomOneLightIJTheme.installLafInfo(NAME, FlatAtomOneLightIJTheme.class);
    }

    public FlatAtomOneLightIJTheme() {
        super(Utils.loadTheme("Atom One Light.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

