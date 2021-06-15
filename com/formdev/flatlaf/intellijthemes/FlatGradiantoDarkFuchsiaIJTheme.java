/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatGradiantoDarkFuchsiaIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Gradianto Dark Fuchsia";

    public static boolean install() {
        try {
            return FlatGradiantoDarkFuchsiaIJTheme.install(new FlatGradiantoDarkFuchsiaIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)524711431L ^ 0x1F467607) != 0;
        }
    }

    public static void installLafInfo() {
        FlatGradiantoDarkFuchsiaIJTheme.installLafInfo(NAME, FlatGradiantoDarkFuchsiaIJTheme.class);
    }

    public FlatGradiantoDarkFuchsiaIJTheme() {
        super(Utils.loadTheme("Gradianto_dark_fuchsia.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

