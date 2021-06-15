/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatGradiantoDeepOceanIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Gradianto Deep Ocean";

    public static boolean install() {
        try {
            return FlatGradiantoDeepOceanIJTheme.install(new FlatGradiantoDeepOceanIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)778050934L ^ 0x2E601D76) != 0;
        }
    }

    public static void installLafInfo() {
        FlatGradiantoDeepOceanIJTheme.installLafInfo(NAME, FlatGradiantoDeepOceanIJTheme.class);
    }

    public FlatGradiantoDeepOceanIJTheme() {
        super(Utils.loadTheme("Gradianto_deep_ocean.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

