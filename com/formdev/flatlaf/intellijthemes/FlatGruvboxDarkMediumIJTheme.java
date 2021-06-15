/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatGruvboxDarkMediumIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Gruvbox Dark Medium";

    public static boolean install() {
        try {
            return FlatGruvboxDarkMediumIJTheme.install(new FlatGruvboxDarkMediumIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-38853922 ^ (long)-38853922) != 0;
        }
    }

    public static void installLafInfo() {
        FlatGruvboxDarkMediumIJTheme.installLafInfo(NAME, FlatGruvboxDarkMediumIJTheme.class);
    }

    public FlatGruvboxDarkMediumIJTheme() {
        super(Utils.loadTheme("gruvbox_dark_medium.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

