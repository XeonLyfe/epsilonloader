/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatGruvboxDarkSoftIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Gruvbox Dark Soft";

    public static boolean install() {
        try {
            return FlatGruvboxDarkSoftIJTheme.install(new FlatGruvboxDarkSoftIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)1768113661L ^ 0x696345FD) != 0;
        }
    }

    public static void installLafInfo() {
        FlatGruvboxDarkSoftIJTheme.installLafInfo(NAME, FlatGruvboxDarkSoftIJTheme.class);
    }

    public FlatGruvboxDarkSoftIJTheme() {
        super(Utils.loadTheme("gruvbox_dark_soft.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

