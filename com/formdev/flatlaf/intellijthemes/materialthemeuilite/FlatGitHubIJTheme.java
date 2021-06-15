/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatGitHubIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "GitHub (Material)";

    public static boolean install() {
        try {
            return FlatGitHubIJTheme.install(new FlatGitHubIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)655071799L ^ 0x270B9A37) != 0;
        }
    }

    public static void installLafInfo() {
        FlatGitHubIJTheme.installLafInfo(NAME, FlatGitHubIJTheme.class);
    }

    public FlatGitHubIJTheme() {
        super(Utils.loadTheme("GitHub.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

