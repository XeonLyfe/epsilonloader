/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatVuesionIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Vuesion";

    public static boolean install() {
        try {
            return FlatVuesionIJTheme.install(new FlatVuesionIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)1690244012L ^ 0x64BF13AC) != 0;
        }
    }

    public static void installLafInfo() {
        FlatVuesionIJTheme.installLafInfo(NAME, FlatVuesionIJTheme.class);
    }

    public FlatVuesionIJTheme() {
        super(Utils.loadTheme("vuesion_theme.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

