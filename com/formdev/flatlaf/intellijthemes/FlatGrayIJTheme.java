/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatGrayIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Gray";

    public static boolean install() {
        try {
            return FlatGrayIJTheme.install(new FlatGrayIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)731134775 ^ (long)731134775) != 0;
        }
    }

    public static void installLafInfo() {
        FlatGrayIJTheme.installLafInfo(NAME, FlatGrayIJTheme.class);
    }

    public FlatGrayIJTheme() {
        super(Utils.loadTheme("Gray.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

