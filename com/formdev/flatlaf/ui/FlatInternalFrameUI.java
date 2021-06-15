/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatDropShadowBorder;
import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatInternalFrameTitlePane;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.FlatWindowResizer;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class FlatInternalFrameUI
extends BasicInternalFrameUI {
    protected FlatWindowResizer windowResizer;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatInternalFrameUI((JInternalFrame)jComponent);
    }

    public FlatInternalFrameUI(JInternalFrame jInternalFrame) {
        super(jInternalFrame);
    }

    @Override
    public void installUI(JComponent jComponent) {
        super.installUI(jComponent);
        LookAndFeel.installProperty(this.frame, "opaque", ((int)571280797L ^ 0x220D0D9D) != 0);
        this.windowResizer = this.createWindowResizer();
    }

    @Override
    public void uninstallUI(JComponent jComponent) {
        super.uninstallUI(jComponent);
        if (this.windowResizer != null) {
            this.windowResizer.uninstall();
            this.windowResizer = null;
        }
    }

    @Override
    protected JComponent createNorthPane(JInternalFrame jInternalFrame) {
        return new FlatInternalFrameTitlePane(jInternalFrame);
    }

    protected FlatWindowResizer createWindowResizer() {
        return new FlatWindowResizer.InternalFrameResizer(this.frame, this::getDesktopManager);
    }

    public static class FlatInternalFrameBorder
    extends FlatEmptyBorder {
        private final Color activeBorderColor = UIManager.getColor("InternalFrame.activeBorderColor");
        private final Color inactiveBorderColor = UIManager.getColor("InternalFrame.inactiveBorderColor");
        private final int borderLineWidth = FlatUIUtils.getUIInt("InternalFrame.borderLineWidth", (int)((long)1586128152 ^ (long)1586128153));
        private final boolean dropShadowPainted = UIManager.getBoolean("InternalFrame.dropShadowPainted");
        private final FlatDropShadowBorder activeDropShadowBorder = new FlatDropShadowBorder(UIManager.getColor("InternalFrame.activeDropShadowColor"), UIManager.getInsets("InternalFrame.activeDropShadowInsets"), FlatUIUtils.getUIFloat("InternalFrame.activeDropShadowOpacity", Float.intBitsToFloat((int)((long)203557964 ^ (long)857869388))));
        private final FlatDropShadowBorder inactiveDropShadowBorder = new FlatDropShadowBorder(UIManager.getColor("InternalFrame.inactiveDropShadowColor"), UIManager.getInsets("InternalFrame.inactiveDropShadowInsets"), FlatUIUtils.getUIFloat("InternalFrame.inactiveDropShadowOpacity", Float.intBitsToFloat((int)((long)1622494614 ^ (long)1605717398))));

        public FlatInternalFrameBorder() {
            super(UIManager.getInsets("InternalFrame.borderMargins"));
        }

        @Override
        public Insets getBorderInsets(Component component, Insets insets) {
            if (component instanceof JInternalFrame && ((JInternalFrame)component).isMaximum()) {
                insets.left = UIScale.scale(Math.min(this.borderLineWidth, this.left));
                insets.top = UIScale.scale(Math.min(this.borderLineWidth, this.top));
                insets.right = UIScale.scale(Math.min(this.borderLineWidth, this.right));
                insets.bottom = UIScale.scale(Math.min(this.borderLineWidth, this.bottom));
                return insets;
            }
            return super.getBorderInsets(component, insets);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
            JInternalFrame jInternalFrame = (JInternalFrame)component;
            Insets insets = this.getBorderInsets(component);
            float f2 = UIScale.scale((float)this.borderLineWidth);
            float f3 = (float)(n2 + insets.left) - f2;
            float f4 = (float)(n3 + insets.top) - f2;
            float f5 = (float)(n4 - insets.left - insets.right) + f2 * 2.0f;
            float f6 = (float)(n5 - insets.top - insets.bottom) + f2 * 2.0f;
            Graphics2D graphics2D = (Graphics2D)graphics.create();
            try {
                FlatUIUtils.setRenderingHints(graphics2D);
                graphics2D.setColor(jInternalFrame.isSelected() ? this.activeBorderColor : this.inactiveBorderColor);
                if (this.dropShadowPainted) {
                    FlatDropShadowBorder flatDropShadowBorder = jInternalFrame.isSelected() ? this.activeDropShadowBorder : this.inactiveDropShadowBorder;
                    Insets insets2 = flatDropShadowBorder.getBorderInsets();
                    flatDropShadowBorder.paintBorder(component, graphics2D, (int)f3 - insets2.left, (int)f4 - insets2.top, (int)f5 + insets2.left + insets2.right, (int)f6 + insets2.top + insets2.bottom);
                }
                graphics2D.fill(FlatUIUtils.createRectangle(f3, f4, f5, f6, f2));
            }
            finally {
                graphics2D.dispose();
            }
        }
    }
}

