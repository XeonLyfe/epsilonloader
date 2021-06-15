/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatWindowAbstractIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

public class FlatWindowCloseIcon
extends FlatWindowAbstractIcon {
    private final Color hoverForeground = UIManager.getColor("TitlePane.closeHoverForeground");
    private final Color pressedForeground = UIManager.getColor("TitlePane.closePressedForeground");

    public FlatWindowCloseIcon() {
        super(UIManager.getDimension("TitlePane.buttonSize"), UIManager.getColor("TitlePane.closeHoverBackground"), UIManager.getColor("TitlePane.closePressedBackground"));
    }

    @Override
    protected void paintIconAt1x(Graphics2D graphics2D, int n2, int n3, int n4, int n5, double d2) {
        int n6 = (int)(Double.longBitsToDouble((long)209794322 ^ 0x402400000C813512L) * d2);
        int n7 = n2 + (n4 - n6) / (((int)-1855636597L ^ 0x91653B8A) << 1);
        int n8 = n3 + (n5 - n6) / ((int)((long)-1177011382 ^ (long)-1177011381) << 1);
        int n9 = n7 + n6 - ((int)828812968L ^ 0x3166AEA9);
        int n10 = n8 + n6 - ((int)-705966232L ^ 0xD5EBCF69);
        int n11 = (int)d2;
        Path2D.Float float_ = new Path2D.Float((int)-2129181671L ^ 0x81174419);
        float_.append(new Line2D.Float(n7, n8, n9, n10), (boolean)((long)1702765276 ^ (long)1702765276));
        float_.append(new Line2D.Float(n7, n10, n9, n8), (boolean)((long)-189142207 ^ (long)-189142207));
        graphics2D.setStroke(new BasicStroke(n11));
        graphics2D.draw(float_);
    }

    @Override
    protected Color getForeground(Component component) {
        return FlatButtonUI.buttonStateColor(component, component.getForeground(), null, null, this.hoverForeground, this.pressedForeground);
    }
}

