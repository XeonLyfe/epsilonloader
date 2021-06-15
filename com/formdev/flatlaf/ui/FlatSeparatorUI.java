/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;

public class FlatSeparatorUI
extends BasicSeparatorUI {
    protected int height;
    protected int stripeWidth;
    protected int stripeIndent;
    private boolean defaults_initialized = (int)((long)262412643 ^ (long)262412643);

    public static ComponentUI createUI(JComponent jComponent) {
        return FlatUIUtils.createSharedUI(FlatSeparatorUI.class, FlatSeparatorUI::new);
    }

    @Override
    protected void installDefaults(JSeparator jSeparator) {
        super.installDefaults(jSeparator);
        if (!this.defaults_initialized) {
            String string = this.getPropertyPrefix();
            this.height = UIManager.getInt(string + ".height");
            this.stripeWidth = UIManager.getInt(string + ".stripeWidth");
            this.stripeIndent = UIManager.getInt(string + ".stripeIndent");
            this.defaults_initialized = (int)((long)-863470192 ^ (long)-863470191);
        }
    }

    @Override
    protected void uninstallDefaults(JSeparator jSeparator) {
        super.uninstallDefaults(jSeparator);
        this.defaults_initialized = (int)-1100924646L ^ 0xBE61391A;
    }

    protected String getPropertyPrefix() {
        return "Separator";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        Graphics2D graphics2D = (Graphics2D)graphics.create();
        try {
            FlatUIUtils.setRenderingHints(graphics2D);
            graphics2D.setColor(jComponent.getForeground());
            float f2 = UIScale.scale((float)this.stripeWidth);
            float f3 = UIScale.scale((float)this.stripeIndent);
            if (((JSeparator)jComponent).getOrientation() == (int)((long)-101075050 ^ (long)-101075049)) {
                graphics2D.fill(new Rectangle2D.Float(f3, 0.0f, f2, jComponent.getHeight()));
            } else {
                graphics2D.fill(new Rectangle2D.Float(0.0f, f3, jComponent.getWidth(), f2));
            }
        }
        finally {
            graphics2D.dispose();
        }
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        if (((JSeparator)jComponent).getOrientation() == (int)((long)195321963 ^ (long)195321962)) {
            return new Dimension(UIScale.scale(this.height), (int)((long)109641515 ^ (long)109641515));
        }
        return new Dimension((int)-352116938L ^ 0xEB031F36, UIScale.scale(this.height));
    }
}

