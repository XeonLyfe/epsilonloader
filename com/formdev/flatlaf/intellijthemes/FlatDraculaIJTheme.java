/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatDraculaIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Dracula";

    public static boolean install() {
        try {
            return FlatDraculaIJTheme.install(new FlatDraculaIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)269271688L ^ 0x100CC288) != 0;
        }
    }

    public static void installLafInfo() {
        FlatDraculaIJTheme.installLafInfo(NAME, FlatDraculaIJTheme.class);
    }

    public FlatDraculaIJTheme() {
        super(Utils.loadTheme("Dracula.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

