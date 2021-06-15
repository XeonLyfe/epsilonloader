/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatLightOwlContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Light Owl Contrast (Material)";

    public static boolean install() {
        try {
            return FlatLightOwlContrastIJTheme.install(new FlatLightOwlContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-549863709 ^ (long)-549863709) != 0;
        }
    }

    public static void installLafInfo() {
        FlatLightOwlContrastIJTheme.installLafInfo(NAME, FlatLightOwlContrastIJTheme.class);
    }

    public FlatLightOwlContrastIJTheme() {
        super(Utils.loadTheme("Light Owl Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

