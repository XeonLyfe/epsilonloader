/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatAtomOneDarkIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Atom One Dark (Material)";

    public static boolean install() {
        try {
            return FlatAtomOneDarkIJTheme.install(new FlatAtomOneDarkIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)838233965L ^ 0x31F66F6D) != 0;
        }
    }

    public static void installLafInfo() {
        FlatAtomOneDarkIJTheme.installLafInfo(NAME, FlatAtomOneDarkIJTheme.class);
    }

    public FlatAtomOneDarkIJTheme() {
        super(Utils.loadTheme("Atom One Dark.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

