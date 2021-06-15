/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.icons.FlatCheckBoxIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatLabelUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRadioButtonUI;

public class FlatRadioButtonUI
extends BasicRadioButtonUI {
    protected int iconTextGap;
    protected Color disabledText;
    private Color defaultBackground;
    private boolean defaults_initialized = (int)-1220064145L ^ 0xB7474C6F;
    private static Insets tempInsets = new Insets((int)((long)903002120 ^ (long)903002120), (int)((long)-1768485072 ^ (long)-1768485072), (int)-26721171L ^ 0xFE68446D, (int)1543584838L ^ 0x5C013C46);

    public static ComponentUI createUI(JComponent jComponent) {
        return FlatUIUtils.createSharedUI(FlatRadioButtonUI.class, FlatRadioButtonUI::new);
    }

    @Override
    public void installDefaults(AbstractButton abstractButton) {
        super.installDefaults(abstractButton);
        if (!this.defaults_initialized) {
            String string = this.getPropertyPrefix();
            this.iconTextGap = FlatUIUtils.getUIInt(string + "iconTextGap", (int)((long)-349749379 ^ (long)-349749380) << 2);
            this.disabledText = UIManager.getColor(string + "disabledText");
            this.defaultBackground = UIManager.getColor(string + "background");
            this.defaults_initialized = (int)((long)-236875025 ^ (long)-236875026);
        }
        LookAndFeel.installProperty(abstractButton, "opaque", (boolean)((long)-2077199519 ^ (long)-2077199519));
        LookAndFeel.installProperty(abstractButton, "iconTextGap", UIScale.scale(this.iconTextGap));
        MigLayoutVisualPadding.install(abstractButton, null);
    }

    @Override
    protected void uninstallDefaults(AbstractButton abstractButton) {
        super.uninstallDefaults(abstractButton);
        MigLayoutVisualPadding.uninstall(abstractButton);
        this.defaults_initialized = (int)((long)1453387096 ^ (long)1453387096);
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        Dimension dimension = super.getPreferredSize(jComponent);
        if (dimension == null) {
            return null;
        }
        int n2 = this.getIconFocusWidth(jComponent);
        if (n2 > 0) {
            Insets insets = jComponent.getInsets(tempInsets);
            dimension.width += Math.max(n2 - insets.left, (int)((long)-1395840832 ^ (long)-1395840832)) + Math.max(n2 - insets.right, (int)-1183011485L ^ 0xB97CAD63);
            dimension.height += Math.max(n2 - insets.top, (int)-1036626675L ^ 0xC236550D) + Math.max(n2 - insets.bottom, (int)((long)-1300484117 ^ (long)-1300484117));
        }
        return dimension;
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        int n2;
        if (!jComponent.isOpaque() && ((AbstractButton)jComponent).isContentAreaFilled() && !this.defaultBackground.equals(jComponent.getBackground())) {
            graphics.setColor(jComponent.getBackground());
            graphics.fillRect((int)((long)-543226416 ^ (long)-543226416), (int)-1090355229L ^ 0xBF027FE3, jComponent.getWidth(), jComponent.getHeight());
        }
        if ((n2 = this.getIconFocusWidth(jComponent)) > 0) {
            int n3;
            boolean bl = jComponent.getComponentOrientation().isLeftToRight();
            Insets insets = jComponent.getInsets(tempInsets);
            int n4 = n3 = bl ? insets.left : insets.right;
            if (n2 > n3) {
                int n5 = n2 - n3;
                if (!bl) {
                    n5 = -n5;
                }
                graphics.translate(n5, (int)((long)-1439231319 ^ (long)-1439231319));
                super.paint(graphics, jComponent);
                graphics.translate(-n5, (int)-332551301L ^ 0xEC2DAB7B);
                return;
            }
        }
        super.paint(FlatLabelUI.createGraphicsHTMLTextYCorrection(graphics, jComponent), jComponent);
    }

    @Override
    protected void paintText(Graphics graphics, AbstractButton abstractButton, Rectangle rectangle, String string) {
        FlatButtonUI.paintText(graphics, abstractButton, rectangle, string, abstractButton.isEnabled() ? abstractButton.getForeground() : this.disabledText);
    }

    private int getIconFocusWidth(JComponent jComponent) {
        AbstractButton abstractButton = (AbstractButton)jComponent;
        return abstractButton.getIcon() == null && this.getDefaultIcon() instanceof FlatCheckBoxIcon ? UIScale.scale(((FlatCheckBoxIcon)this.getDefaultIcon()).focusWidth) : (int)-1510987327L ^ 0xA5F029C1;
    }
}

