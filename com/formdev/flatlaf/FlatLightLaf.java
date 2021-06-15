/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatLaf;

public class FlatLightLaf
extends FlatLaf {
    public static final String NAME = "FlatLaf Light";

    public static boolean install() {
        return FlatLightLaf.install(new FlatLightLaf());
    }

    public static void installLafInfo() {
        FlatLightLaf.installLafInfo(NAME, FlatLightLaf.class);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return "FlatLaf Light Look and Feel";
    }

    @Override
    public boolean isDark() {
        return ((int)1887903623L ^ 0x70871F87) != 0;
    }
}

