/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatCarbonIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Carbon";

    public static boolean install() {
        try {
            return FlatCarbonIJTheme.install(new FlatCarbonIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-625664896 ^ (long)-625664896) != 0;
        }
    }

    public static void installLafInfo() {
        FlatCarbonIJTheme.installLafInfo(NAME, FlatCarbonIJTheme.class);
    }

    public FlatCarbonIJTheme() {
        super(Utils.loadTheme("Carbon.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

