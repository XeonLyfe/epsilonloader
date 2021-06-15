/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public abstract class FlatInternalFrameAbstractIcon
extends FlatAbstractIcon {
    private final Color hoverBackground;
    private final Color pressedBackground;

    public FlatInternalFrameAbstractIcon() {
        this(UIManager.getDimension("InternalFrame.buttonSize"), UIManager.getColor("InternalFrame.buttonHoverBackground"), UIManager.getColor("InternalFrame.buttonPressedBackground"));
    }

    public FlatInternalFrameAbstractIcon(Dimension dimension, Color color, Color color2) {
        super(dimension.width, dimension.height, null);
        this.hoverBackground = color;
        this.pressedBackground = color2;
    }

    protected void paintBackground(Component component, Graphics2D graphics2D) {
        Color color = FlatButtonUI.buttonStateColor(component, null, null, null, this.hoverBackground, this.pressedBackground);
        if (color != null) {
            graphics2D.setColor(FlatUIUtils.deriveColor(color, component.getBackground()));
            graphics2D.fillRect((int)1412997606L ^ 0x5438A1E6, (int)((long)-60734493 ^ (long)-60734493), this.width, this.height);
        }
    }
}

