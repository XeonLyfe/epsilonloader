/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatLaf;

public class FlatDarkLaf
extends FlatLaf {
    public static final String NAME = "FlatLaf Dark";

    public static boolean install() {
        return FlatDarkLaf.install(new FlatDarkLaf());
    }

    public static void installLafInfo() {
        FlatDarkLaf.installLafInfo(NAME, FlatDarkLaf.class);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return "FlatLaf Dark Look and Feel";
    }

    @Override
    public boolean isDark() {
        return (int)((long)1797611323 ^ (long)1797611322) != 0;
    }
}

