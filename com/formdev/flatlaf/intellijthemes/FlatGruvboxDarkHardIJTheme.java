/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatGruvboxDarkHardIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Gruvbox Dark Hard";

    public static boolean install() {
        try {
            return FlatGruvboxDarkHardIJTheme.install(new FlatGruvboxDarkHardIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-291943788 ^ (long)-291943788) != 0;
        }
    }

    public static void installLafInfo() {
        FlatGruvboxDarkHardIJTheme.installLafInfo(NAME, FlatGruvboxDarkHardIJTheme.class);
    }

    public FlatGruvboxDarkHardIJTheme() {
        super(Utils.loadTheme("gruvbox_dark_hard.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

