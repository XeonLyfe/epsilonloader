/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf;

public interface FlatSystemProperties {
    public static final String UI_SCALE = "flatlaf.uiScale";
    public static final String UI_SCALE_ENABLED = "flatlaf.uiScale.enabled";
    public static final String UI_SCALE_ALLOW_SCALE_DOWN = "flatlaf.uiScale.allowScaleDown";
    public static final String USE_UBUNTU_FONT = "flatlaf.useUbuntuFont";
    public static final String USE_WINDOW_DECORATIONS = "flatlaf.useWindowDecorations";
    public static final String USE_JETBRAINS_CUSTOM_DECORATIONS = "flatlaf.useJetBrainsCustomDecorations";
    public static final String MENUBAR_EMBEDDED = "flatlaf.menuBarEmbedded";
    public static final String ANIMATION = "flatlaf.animation";
    public static final String USE_TEXT_Y_CORRECTION = "flatlaf.useTextYCorrection";

    public static boolean getBoolean(String string, boolean bl) {
        String string2 = System.getProperty(string);
        return string2 != null ? Boolean.parseBoolean(string2) : bl;
    }

    public static Boolean getBooleanStrict(String string, Boolean bl) {
        String string2 = System.getProperty(string);
        if ("true".equalsIgnoreCase(string2)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(string2)) {
            return Boolean.FALSE;
        }
        return bl;
    }
}

