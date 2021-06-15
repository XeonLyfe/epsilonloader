/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatSpacegrayIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Spacegray";

    public static boolean install() {
        try {
            return FlatSpacegrayIJTheme.install(new FlatSpacegrayIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)-773415612L ^ 0xD1E69D44) != 0;
        }
    }

    public static void installLafInfo() {
        FlatSpacegrayIJTheme.installLafInfo(NAME, FlatSpacegrayIJTheme.class);
    }

    public FlatSpacegrayIJTheme() {
        super(Utils.loadTheme("Spacegray.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

