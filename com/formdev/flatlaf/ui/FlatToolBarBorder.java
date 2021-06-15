/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatMarginBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.JToolBar;
import javax.swing.UIManager;

public class FlatToolBarBorder
extends FlatMarginBorder {
    private static final int DOT_COUNT = 4;
    private static final int DOT_SIZE = 2;
    private static final int GRIP_SIZE = 6;
    protected final Color gripColor = UIManager.getColor("ToolBar.gripColor");

    public FlatToolBarBorder() {
        super(UIManager.getInsets("ToolBar.borderMargins"));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
        if (component instanceof JToolBar && ((JToolBar)component).isFloatable()) {
            Graphics2D graphics2D = (Graphics2D)graphics.create();
            try {
                FlatUIUtils.setRenderingHints(graphics2D);
                graphics2D.setColor(this.gripColor);
                this.paintGrip(component, graphics2D, n2, n3, n4, n5);
            }
            finally {
                graphics2D.dispose();
            }
        }
    }

    protected void paintGrip(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
        Rectangle rectangle = this.calculateGripBounds(component, n2, n3, n4, n5);
        FlatUIUtils.paintGrip(graphics, rectangle.x, rectangle.y, rectangle.width, rectangle.height, (((JToolBar)component).getOrientation() == ((int)1348262380L ^ 0x505CD9ED) ? (int)-814402411L ^ 0xCF753494 : (int)((long)-2098161470 ^ (long)-2098161470)) != 0, (int)((long)462039238 ^ (long)462039239) << 2, (int)((long)1825517013 ^ (long)1825517012) << 1, ((int)1186941790L ^ 0x46BF4B5F) << 1, (boolean)((long)-680394376 ^ (long)-680394376));
    }

    protected Rectangle calculateGripBounds(Component component, int n2, int n3, int n4, int n5) {
        Insets insets = super.getBorderInsets(component, new Insets((int)((long)-112778666 ^ (long)-112778666), (int)((long)-624065975 ^ (long)-624065975), (int)((long)234155313 ^ (long)234155313), (int)((long)933569652 ^ (long)933569652)));
        Rectangle rectangle = FlatUIUtils.subtractInsets(new Rectangle(n2, n3, n4, n5), insets);
        int n6 = UIScale.scale(((int)-1693797303L ^ 0x9B0AB44A) << 1);
        if (((JToolBar)component).getOrientation() == 0) {
            if (!component.getComponentOrientation().isLeftToRight()) {
                rectangle.x = rectangle.x + rectangle.width - n6;
            }
            rectangle.width = n6;
        } else {
            rectangle.height = n6;
        }
        return rectangle;
    }

    @Override
    public Insets getBorderInsets(Component component, Insets insets) {
        insets = super.getBorderInsets(component, insets);
        if (component instanceof JToolBar && ((JToolBar)component).isFloatable()) {
            int n2 = UIScale.scale((int)((long)904187329 ^ (long)904187330) << 1);
            if (((JToolBar)component).getOrientation() == 0) {
                if (component.getComponentOrientation().isLeftToRight()) {
                    insets.left += n2;
                } else {
                    insets.right += n2;
                }
            } else {
                insets.top += n2;
            }
        }
        return insets;
    }
}

