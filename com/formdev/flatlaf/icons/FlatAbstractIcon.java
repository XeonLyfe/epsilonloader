/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.plaf.UIResource;

public abstract class FlatAbstractIcon
implements Icon,
UIResource {
    protected final int width;
    protected final int height;
    protected final Color color;

    public FlatAbstractIcon(int n2, int n3, Color color) {
        this.width = n2;
        this.height = n3;
        this.color = color;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void paintIcon(Component component, Graphics graphics, int n2, int n3) {
        Graphics2D graphics2D = (Graphics2D)graphics.create();
        try {
            FlatUIUtils.setRenderingHints(graphics2D);
            graphics2D.translate(n2, n3);
            UIScale.scaleGraphics(graphics2D);
            if (this.color != null) {
                graphics2D.setColor(this.color);
            }
            this.paintIcon(component, graphics2D);
        }
        finally {
            graphics2D.dispose();
        }
    }

    protected abstract void paintIcon(Component var1, Graphics2D var2);

    @Override
    public int getIconWidth() {
        return UIScale.scale(this.width);
    }

    @Override
    public int getIconHeight() {
        return UIScale.scale(this.height);
    }
}

