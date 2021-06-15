/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMonokaiProIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Monokai Pro (Material)";

    public static boolean install() {
        try {
            return FlatMonokaiProIJTheme.install(new FlatMonokaiProIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)-1631033724L ^ 0x9EC86684) != 0;
        }
    }

    public static void installLafInfo() {
        FlatMonokaiProIJTheme.installLafInfo(NAME, FlatMonokaiProIJTheme.class);
    }

    public FlatMonokaiProIJTheme() {
        super(Utils.loadTheme("Monokai Pro.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

