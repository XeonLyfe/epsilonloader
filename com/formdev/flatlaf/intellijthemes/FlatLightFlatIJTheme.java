/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatLightFlatIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Light Flat";

    public static boolean install() {
        try {
            return FlatLightFlatIJTheme.install(new FlatLightFlatIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)789760799 ^ (long)789760799) != 0;
        }
    }

    public static void installLafInfo() {
        FlatLightFlatIJTheme.installLafInfo(NAME, FlatLightFlatIJTheme.class);
    }

    public FlatLightFlatIJTheme() {
        super(Utils.loadTheme("LightFlatTheme.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

