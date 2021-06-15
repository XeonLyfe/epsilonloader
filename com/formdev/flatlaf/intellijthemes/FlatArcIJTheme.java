/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatArcIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Arc";

    public static boolean install() {
        try {
            return FlatArcIJTheme.install(new FlatArcIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)23758303 ^ (long)23758303) != 0;
        }
    }

    public static void installLafInfo() {
        FlatArcIJTheme.installLafInfo(NAME, FlatArcIJTheme.class);
    }

    public FlatArcIJTheme() {
        super(Utils.loadTheme("arc-theme.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

