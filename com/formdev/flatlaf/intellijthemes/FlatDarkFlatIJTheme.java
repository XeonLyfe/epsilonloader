/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatDarkFlatIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Dark Flat";

    public static boolean install() {
        try {
            return FlatDarkFlatIJTheme.install(new FlatDarkFlatIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)-834916173L ^ 0xCE3C30B3) != 0;
        }
    }

    public static void installLafInfo() {
        FlatDarkFlatIJTheme.installLafInfo(NAME, FlatDarkFlatIJTheme.class);
    }

    public FlatDarkFlatIJTheme() {
        super(Utils.loadTheme("DarkFlatTheme.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

