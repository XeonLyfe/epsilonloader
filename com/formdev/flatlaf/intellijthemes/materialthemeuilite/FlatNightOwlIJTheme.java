/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatNightOwlIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Night Owl (Material)";

    public static boolean install() {
        try {
            return FlatNightOwlIJTheme.install(new FlatNightOwlIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)728053424L ^ 0x2B6536B0) != 0;
        }
    }

    public static void installLafInfo() {
        FlatNightOwlIJTheme.installLafInfo(NAME, FlatNightOwlIJTheme.class);
    }

    public FlatNightOwlIJTheme() {
        super(Utils.loadTheme("Night Owl.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

