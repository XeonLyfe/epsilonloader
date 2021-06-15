/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatArcOrangeIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Arc - Orange";

    public static boolean install() {
        try {
            return FlatArcOrangeIJTheme.install(new FlatArcOrangeIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)1797018110L ^ 0x6B1C51FE) != 0;
        }
    }

    public static void installLafInfo() {
        FlatArcOrangeIJTheme.installLafInfo(NAME, FlatArcOrangeIJTheme.class);
    }

    public FlatArcOrangeIJTheme() {
        super(Utils.loadTheme("arc-theme-orange.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

