/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatArcDarkIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Arc Dark (Material)";

    public static boolean install() {
        try {
            return FlatArcDarkIJTheme.install(new FlatArcDarkIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)162121978L ^ 0x9A9C8FA) != 0;
        }
    }

    public static void installLafInfo() {
        FlatArcDarkIJTheme.installLafInfo(NAME, FlatArcDarkIJTheme.class);
    }

    public FlatArcDarkIJTheme() {
        super(Utils.loadTheme("Arc Dark.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

