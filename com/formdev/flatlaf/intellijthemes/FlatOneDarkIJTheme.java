/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatOneDarkIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "One Dark";

    public static boolean install() {
        try {
            return FlatOneDarkIJTheme.install(new FlatOneDarkIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return (int)((long)1808603425 ^ (long)1808603425) != 0;
        }
    }

    public static void installLafInfo() {
        FlatOneDarkIJTheme.installLafInfo(NAME, FlatOneDarkIJTheme.class);
    }

    public FlatOneDarkIJTheme() {
        super(Utils.loadTheme("one_dark.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

