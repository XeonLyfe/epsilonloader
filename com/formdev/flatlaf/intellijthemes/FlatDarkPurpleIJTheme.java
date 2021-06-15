/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatDarkPurpleIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Dark purple";

    public static boolean install() {
        try {
            return FlatDarkPurpleIJTheme.install(new FlatDarkPurpleIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)1409203767 ^ (long)1409203767) != 0;
        }
    }

    public static void installLafInfo() {
        FlatDarkPurpleIJTheme.installLafInfo(NAME, FlatDarkPurpleIJTheme.class);
    }

    public FlatDarkPurpleIJTheme() {
        super(Utils.loadTheme("DarkPurple.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

