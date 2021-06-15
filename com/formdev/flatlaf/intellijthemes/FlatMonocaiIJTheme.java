/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatMonocaiIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Monocai";

    public static boolean install() {
        try {
            return FlatMonocaiIJTheme.install(new FlatMonocaiIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)555836313L ^ 0x21216399) != 0;
        }
    }

    public static void installLafInfo() {
        FlatMonocaiIJTheme.installLafInfo(NAME, FlatMonocaiIJTheme.class);
    }

    public FlatMonocaiIJTheme() {
        super(Utils.loadTheme("Monocai.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

