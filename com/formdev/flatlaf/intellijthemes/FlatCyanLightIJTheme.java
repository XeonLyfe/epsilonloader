/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatCyanLightIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Cyan light";

    public static boolean install() {
        try {
            return FlatCyanLightIJTheme.install(new FlatCyanLightIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)-341313008L ^ 0xEBA7FA10) != 0;
        }
    }

    public static void installLafInfo() {
        FlatCyanLightIJTheme.installLafInfo(NAME, FlatCyanLightIJTheme.class);
    }

    public FlatCyanLightIJTheme() {
        super(Utils.loadTheme("Cyan.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

