/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatGitHubContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "GitHub Contrast (Material)";

    public static boolean install() {
        try {
            return FlatGitHubContrastIJTheme.install(new FlatGitHubContrastIJTheme());
        }
        catch (RuntimeException runtimeException) {
            return ((int)10243821L ^ 0x9C4EED) != 0;
        }
    }

    public static void installLafInfo() {
        FlatGitHubContrastIJTheme.installLafInfo(NAME, FlatGitHubContrastIJTheme.class);
    }

    public FlatGitHubContrastIJTheme() {
        super(Utils.loadTheme("GitHub Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

