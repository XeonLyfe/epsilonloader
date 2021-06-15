/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolBarSeparatorUI;

public class FlatToolBarSeparatorUI
extends BasicToolBarSeparatorUI {
    private static final int LINE_WIDTH = 1;
    protected int separatorWidth;
    protected Color separatorColor;
    private boolean defaults_initialized = (int)((long)1863701199 ^ (long)1863701199);

    public static ComponentUI createUI(JComponent jComponent) {
        return FlatUIUtils.createSharedUI(FlatToolBarSeparatorUI.class, FlatToolBarSeparatorUI::new);
    }

    @Override
    protected void installDefaults(JSeparator jSeparator) {
        super.installDefaults(jSeparator);
        if (!this.defaults_initialized) {
            this.separatorWidth = UIManager.getInt("ToolBar.separatorWidth");
            this.separatorColor = UIManager.getColor("ToolBar.separatorColor");
            this.defaults_initialized = (int)-1504244034L ^ 0xA6570EBF;
        }
        jSeparator.setAlignmentX(0.0f);
    }

    @Override
    protected void uninstallDefaults(JSeparator jSeparator) {
        super.uninstallDefaults(jSeparator);
        this.defaults_initialized = (int)-848500019L ^ 0xCD6CEACD;
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        Dimension dimension = ((JToolBar.Separator)jComponent).getSeparatorSize();
        if (dimension != null) {
            return UIScale.scale(dimension);
        }
        int n2 = UIScale.scale((this.separatorWidth - (int)((long)1924398868 ^ (long)1924398869)) / (((int)1488979088L ^ 0x58C00491) << 1)) * ((int)((long)1618066835 ^ (long)1618066834) << 1) + UIScale.scale((int)((long)1671431184 ^ (long)1671431185));
        boolean bl = this.isVertical(jComponent);
        return new Dimension(bl ? n2 : (int)((long)1378401437 ^ (long)1378401437), bl ? (int)907323433L ^ 0x3614A829 : n2);
    }

    @Override
    public Dimension getMaximumSize(JComponent jComponent) {
        Dimension dimension = this.getPreferredSize(jComponent);
        if (this.isVertical(jComponent)) {
            return new Dimension(dimension.width, (int)((long)-852454533 ^ (long)-852431740));
        }
        return new Dimension((int)((long)292898579 ^ (long)292894956), dimension.height);
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        int n2 = jComponent.getWidth();
        int n3 = jComponent.getHeight();
        float f2 = UIScale.scale(1.0f);
        float f3 = UIScale.scale(2.0f);
        Object[] arrobject = FlatUIUtils.setRenderingHints(graphics);
        graphics.setColor(this.separatorColor);
        if (this.isVertical(jComponent)) {
            ((Graphics2D)graphics).fill(new Rectangle2D.Float(Math.round(((float)n2 - f2) / 2.0f), f3, f2, (float)n3 - f3 * 2.0f));
        } else {
            ((Graphics2D)graphics).fill(new Rectangle2D.Float(f3, Math.round(((float)n3 - f2) / 2.0f), (float)n2 - f3 * 2.0f, f2));
        }
        FlatUIUtils.resetRenderingHints(graphics, arrobject);
    }

    private boolean isVertical(JComponent jComponent) {
        return (((JToolBar.Separator)jComponent).getOrientation() == ((int)-851988358L ^ 0xCD37B07B) ? (int)((long)-2098114318 ^ (long)-2098114317) : (int)((long)843445991 ^ (long)843445991)) != 0;
    }
}

