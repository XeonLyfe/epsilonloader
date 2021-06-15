/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatHiberbeeDarkIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Hiberbee Dark";

    public static boolean install() {
        try {
            return FlatHiberbeeDarkIJTheme.install(new FlatHiberbeeDarkIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-1010992454 ^ (long)-1010992454) != 0;
        }
    }

    public static void installLafInfo() {
        FlatHiberbeeDarkIJTheme.installLafInfo(NAME, FlatHiberbeeDarkIJTheme.class);
    }

    public FlatHiberbeeDarkIJTheme() {
        super(Utils.loadTheme("HiberbeeDark.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

