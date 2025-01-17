/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatDraculaContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Dracula Contrast (Material)";

    public static boolean install() {
        try {
            return FlatDraculaContrastIJTheme.install(new FlatDraculaContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)115692828 ^ (long)115692828) != 0;
        }
    }

    public static void installLafInfo() {
        FlatDraculaContrastIJTheme.installLafInfo(NAME, FlatDraculaContrastIJTheme.class);
    }

    public FlatDraculaContrastIJTheme() {
        super(Utils.loadTheme("Dracula Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

