/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatMaterialDesignDarkIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Design Dark";

    public static boolean install() {
        try {
            return FlatMaterialDesignDarkIJTheme.install(new FlatMaterialDesignDarkIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)1420440193 ^ (long)1420440193) != 0;
        }
    }

    public static void installLafInfo() {
        FlatMaterialDesignDarkIJTheme.installLafInfo(NAME, FlatMaterialDesignDarkIJTheme.class);
    }

    public FlatMaterialDesignDarkIJTheme() {
        super(Utils.loadTheme("MaterialTheme.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

