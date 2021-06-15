/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.HiDPIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public abstract class FlatWindowAbstractIcon
extends FlatAbstractIcon {
    private final Color hoverBackground;
    private final Color pressedBackground;

    public FlatWindowAbstractIcon() {
        this(UIManager.getDimension("TitlePane.buttonSize"), UIManager.getColor("TitlePane.buttonHoverBackground"), UIManager.getColor("TitlePane.buttonPressedBackground"));
    }

    public FlatWindowAbstractIcon(Dimension dimension, Color color, Color color2) {
        super(dimension.width, dimension.height, null);
        this.hoverBackground = color;
        this.pressedBackground = color2;
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        this.paintBackground(component, graphics2D);
        graphics2D.setColor(this.getForeground(component));
        HiDPIUtils.paintAtScale1x(graphics2D, (int)-1413486327L ^ 0xABBFE909, (int)((long)-488280954 ^ (long)-488280954), this.width, this.height, (arg_0, arg_1, arg_2, arg_3, arg_4, arg_5) -> this.paintIconAt1x(arg_0, arg_1, arg_2, arg_3, arg_4, arg_5));
    }

    protected abstract void paintIconAt1x(Graphics2D var1, int var2, int var3, int var4, int var5, double var6);

    protected void paintBackground(Component component, Graphics2D graphics2D) {
        Color color = FlatButtonUI.buttonStateColor(component, null, null, null, this.hoverBackground, this.pressedBackground);
        if (color != null) {
            graphics2D.setColor(FlatUIUtils.deriveColor(color, component.getBackground()));
            graphics2D.fillRect((int)((long)1076958395 ^ (long)1076958395), (int)((long)722637963 ^ (long)722637963), this.width, this.height);
        }
    }

    protected Color getForeground(Component component) {
        return component.getForeground();
    }
}

