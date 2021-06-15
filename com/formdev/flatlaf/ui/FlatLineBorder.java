/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

public class FlatLineBorder
extends FlatEmptyBorder {
    private final Color lineColor;
    private final float lineThickness;

    public FlatLineBorder(Insets insets, Color color) {
        this(insets, color, 1.0f);
    }

    public FlatLineBorder(Insets insets, Color color, float f2) {
        super(insets);
        this.lineColor = color;
        this.lineThickness = f2;
    }

    public Color getLineColor() {
        return this.lineColor;
    }

    public float getLineThickness() {
        return this.lineThickness;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
        Graphics2D graphics2D = (Graphics2D)graphics.create();
        try {
            FlatUIUtils.setRenderingHints(graphics2D);
            graphics2D.setColor(this.lineColor);
            FlatUIUtils.paintComponentBorder(graphics2D, n2, n3, n4, n5, 0.0f, UIScale.scale(this.lineThickness), 0.0f);
        }
        finally {
            graphics2D.dispose();
        }
    }
}

