/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatGradiantoNatureGreenIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Gradianto Nature Green";

    public static boolean install() {
        try {
            return FlatGradiantoNatureGreenIJTheme.install(new FlatGradiantoNatureGreenIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)-2136292702 ^ (long)-2136292702) != 0;
        }
    }

    public static void installLafInfo() {
        FlatGradiantoNatureGreenIJTheme.installLafInfo(NAME, FlatGradiantoNatureGreenIJTheme.class);
    }

    public FlatGradiantoNatureGreenIJTheme() {
        super(Utils.loadTheme("Gradianto_Nature_Green.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

