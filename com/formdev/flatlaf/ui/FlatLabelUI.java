/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicLabelUI;

public class FlatLabelUI
extends BasicLabelUI {
    private Color disabledForeground;
    private boolean defaults_initialized = (int)((long)-883900302 ^ (long)-883900302);
    private static Set<String> tagsUseFontSizeSet;

    public static ComponentUI createUI(JComponent jComponent) {
        return FlatUIUtils.createSharedUI(FlatLabelUI.class, FlatLabelUI::new);
    }

    @Override
    protected void installDefaults(JLabel jLabel) {
        super.installDefaults(jLabel);
        if (!this.defaults_initialized) {
            this.disabledForeground = UIManager.getColor("Label.disabledForeground");
            this.defaults_initialized = (int)2042677712L ^ 0x79C0C9D1;
        }
    }

    @Override
    protected void uninstallDefaults(JLabel jLabel) {
        super.uninstallDefaults(jLabel);
        this.defaults_initialized = (int)2000481613L ^ 0x773CED4D;
    }

    @Override
    protected void installComponents(JLabel jLabel) {
        super.installComponents(jLabel);
        FlatLabelUI.updateHTMLRenderer(jLabel, jLabel.getText(), (boolean)((long)-591367241 ^ (long)-591367241));
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        String string = propertyChangeEvent.getPropertyName();
        if (string == "text" || string == "font" || string == "foreground") {
            JLabel jLabel = (JLabel)propertyChangeEvent.getSource();
            FlatLabelUI.updateHTMLRenderer(jLabel, jLabel.getText(), ((int)-1931067756L ^ 0x8CE63E95) != 0);
        } else {
            super.propertyChange(propertyChangeEvent);
        }
    }

    static void updateHTMLRenderer(JComponent jComponent, String string, boolean bl) {
        if (BasicHTML.isHTMLString(string) && jComponent.getClientProperty("html.disable") != Boolean.TRUE && FlatLabelUI.needsFontBaseSize(string)) {
            int n2;
            String string2 = "<style>BASE_SIZE " + jComponent.getFont().getSize() + "</style>";
            String string3 = string.toLowerCase();
            int n3 = string3.indexOf("<head>");
            if (n3 >= 0) {
                n2 = n3 + "<head>".length();
            } else {
                int n4 = string3.indexOf("<style>");
                if (n4 >= 0) {
                    n2 = n4;
                } else {
                    string2 = "<head>" + string2 + "</head>";
                    n2 = "<html>".length();
                }
            }
            string = string.substring((int)1016800852L ^ 0x3C9B2654, n2) + string2 + string.substring(n2);
        } else if (!bl) {
            return;
        }
        BasicHTML.updateRenderer(jComponent, string);
    }

    private static boolean needsFontBaseSize(String string) {
        if (tagsUseFontSizeSet == null) {
            String[] arrstring = new String[(int)542030554L ^ 0x204EBAD1];
            arrstring[(int)-1802300465L ^ -1802300465] = "h1";
            arrstring[(int)-95595544L ^ -95595543] = "h2";
            arrstring[(int)((long)1144892352 ^ (long)1144892353) << 1] = "h3";
            arrstring[(int)-765622035L ^ -765622034] = "h4";
            arrstring[(int)((long)598995548 ^ (long)598995549) << 2] = "h5";
            arrstring[(int)-345448624L ^ -345448619] = "h6";
            arrstring[((int)1655180619L ^ 1655180616) << 1] = "code";
            arrstring[(int)-1123638367L ^ -1123638362] = "kbd";
            arrstring[(int)((long)837592132 ^ (long)837592133) << 3] = "big";
            arrstring[(int)((long)-734140436 ^ (long)-734140443)] = "small";
            arrstring[((int)241053956L ^ 241053953) << 1] = "samp";
            tagsUseFontSizeSet = new HashSet<String>(Arrays.asList(arrstring));
        }
        int n2 = string.length();
        block3: for (int i2 = ((int)1237395420L ^ 0x49C127DF) << 1; i2 < n2 - ((int)-547414103L ^ 0xDF5F1FA8); ++i2) {
            if (string.charAt(i2) != ((int)674306140L ^ 0x28311853) << 2) continue;
            switch (string.charAt(i2 + (int)((long)602170188 ^ (long)602170189))) {
                case 'B': 
                case 'C': 
                case 'H': 
                case 'K': 
                case 'S': 
                case 'b': 
                case 'c': 
                case 'h': 
                case 'k': 
                case 's': {
                    int n3 = i2 + ((int)1978061775L ^ 0x75E6D3CE);
                    i2 += 2;
                    while (i2 < n2) {
                        if (!Character.isLetterOrDigit(string.charAt(i2))) {
                            String string2 = string.substring(n3, i2).toLowerCase();
                            if (!tagsUseFontSizeSet.contains(string2)) continue block3;
                            return (int)((long)1561486787 ^ (long)1561486786) != 0;
                        }
                        ++i2;
                    }
                    continue block3;
                }
            }
        }
        return ((int)735529813L ^ 0x2BD74B55) != 0;
    }

    static Graphics createGraphicsHTMLTextYCorrection(Graphics graphics, JComponent jComponent) {
        return jComponent.getClientProperty("html") != null ? HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)graphics) : graphics;
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        super.paint(FlatLabelUI.createGraphicsHTMLTextYCorrection(graphics, jComponent), jComponent);
    }

    @Override
    protected void paintEnabledText(JLabel jLabel, Graphics graphics, String string, int n2, int n3) {
        int n4 = FlatLaf.isShowMnemonics() ? jLabel.getDisplayedMnemonicIndex() : (int)-1780813947L ^ 0x6A25107A;
        graphics.setColor(jLabel.getForeground());
        FlatUIUtils.drawStringUnderlineCharAt(jLabel, graphics, string, n4, n2, n3);
    }

    @Override
    protected void paintDisabledText(JLabel jLabel, Graphics graphics, String string, int n2, int n3) {
        int n4 = FlatLaf.isShowMnemonics() ? jLabel.getDisplayedMnemonicIndex() : (int)-1102423802L ^ 0x41B5A6F9;
        graphics.setColor(this.disabledForeground);
        FlatUIUtils.drawStringUnderlineCharAt(jLabel, graphics, string, n4, n2, n3);
    }

    @Override
    protected String layoutCL(JLabel jLabel, FontMetrics fontMetrics, String string, Icon icon, Rectangle rectangle, Rectangle rectangle2, Rectangle rectangle3) {
        return SwingUtilities.layoutCompoundLabel(jLabel, fontMetrics, string, icon, jLabel.getVerticalAlignment(), jLabel.getHorizontalAlignment(), jLabel.getVerticalTextPosition(), jLabel.getHorizontalTextPosition(), rectangle, rectangle2, rectangle3, UIScale.scale(jLabel.getIconTextGap()));
    }
}

