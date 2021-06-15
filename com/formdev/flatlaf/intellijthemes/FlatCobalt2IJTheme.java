/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatCobalt2IJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Cobalt 2";

    public static boolean install() {
        try {
            return FlatCobalt2IJTheme.install(new FlatCobalt2IJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)-1722463412L ^ 0x99554B4C) != 0;
        }
    }

    public static void installLafInfo() {
        FlatCobalt2IJTheme.installLafInfo(NAME, FlatCobalt2IJTheme.class);
    }

    public FlatCobalt2IJTheme() {
        super(Utils.loadTheme("Cobalt_2.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

