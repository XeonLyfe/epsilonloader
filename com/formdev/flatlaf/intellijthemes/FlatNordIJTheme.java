/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatNordIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Nord";

    public static boolean install() {
        try {
            return FlatNordIJTheme.install(new FlatNordIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)543501900 ^ (long)543501900) != 0;
        }
    }

    public static void installLafInfo() {
        FlatNordIJTheme.installLafInfo(NAME, FlatNordIJTheme.class);
    }

    public FlatNordIJTheme() {
        super(Utils.loadTheme("nord.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

