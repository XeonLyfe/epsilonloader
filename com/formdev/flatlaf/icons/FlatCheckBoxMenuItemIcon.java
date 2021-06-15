/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import javax.swing.AbstractButton;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

public class FlatCheckBoxMenuItemIcon
extends FlatAbstractIcon {
    protected final Color checkmarkColor = UIManager.getColor("MenuItemCheckBox.icon.checkmarkColor");
    protected final Color disabledCheckmarkColor = UIManager.getColor("MenuItemCheckBox.icon.disabledCheckmarkColor");
    protected final Color selectionForeground = UIManager.getColor("MenuItem.selectionForeground");

    public FlatCheckBoxMenuItemIcon() {
        super((int)228167350L ^ 0xD998EB9, (int)2025549453L ^ 0x78BB6E82, null);
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        int n2;
        int n3 = n2 = component instanceof AbstractButton && ((AbstractButton)component).isSelected() ? (int)-1540885868L ^ 0xA427F295 : (int)((long)-844925897 ^ (long)-844925897);
        if (n2 != 0) {
            graphics2D.setColor(this.getCheckmarkColor(component));
            this.paintCheckmark(graphics2D);
        }
    }

    protected void paintCheckmark(Graphics2D graphics2D) {
        Path2D.Float float_ = new Path2D.Float();
        float_.moveTo(Float.intBitsToFloat((int)((long)1046383940 ^ (long)2127465796)), Float.intBitsToFloat((int)((long)2084269169 ^ (long)1019964529)));
        float_.lineTo(Float.intBitsToFloat(0xFFB63C4 ^ 0x4F2850F7), Float.intBitsToFloat((int)964329816L ^ 0x785A8158));
        float_.lineTo(Float.intBitsToFloat((int)149150445L ^ 0x49D7DAED), Float.intBitsToFloat((int)((long)998654424 ^ (long)2078687704)));
        graphics2D.setStroke(new BasicStroke(Float.intBitsToFloat(0x72E2629 ^ 0x38DD151A), (int)1905209479L ^ 0x718F3086, (int)1901637088L ^ 0x7158ADE1));
        graphics2D.draw(float_);
    }

    protected Color getCheckmarkColor(Component component) {
        if (component instanceof JMenuItem && ((JMenuItem)component).isArmed() && !this.isUnderlineSelection()) {
            return this.selectionForeground;
        }
        return component.isEnabled() ? this.checkmarkColor : this.disabledCheckmarkColor;
    }

    protected boolean isUnderlineSelection() {
        return "underline".equals(UIManager.getString("MenuItem.selectionType"));
    }
}

