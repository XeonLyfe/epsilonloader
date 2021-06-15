/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatNightOwlContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Night Owl Contrast (Material)";

    public static boolean install() {
        try {
            return FlatNightOwlContrastIJTheme.install(new FlatNightOwlContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)-949637001L ^ 0xC765B077) != 0;
        }
    }

    public static void installLafInfo() {
        FlatNightOwlContrastIJTheme.installLafInfo(NAME, FlatNightOwlContrastIJTheme.class);
    }

    public FlatNightOwlContrastIJTheme() {
        super(Utils.loadTheme("Night Owl Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

