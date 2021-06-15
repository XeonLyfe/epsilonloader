/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatInternalFrameAbstractIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

public class FlatInternalFrameCloseIcon
extends FlatInternalFrameAbstractIcon {
    private final Color hoverForeground = UIManager.getColor("InternalFrame.closeHoverForeground");
    private final Color pressedForeground = UIManager.getColor("InternalFrame.closePressedForeground");

    public FlatInternalFrameCloseIcon() {
        super(UIManager.getDimension("InternalFrame.buttonSize"), UIManager.getColor("InternalFrame.closeHoverBackground"), UIManager.getColor("InternalFrame.closePressedBackground"));
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        this.paintBackground(component, graphics2D);
        graphics2D.setColor(FlatButtonUI.buttonStateColor(component, component.getForeground(), null, null, this.hoverForeground, this.pressedForeground));
        float f2 = this.width / ((int)((long)-1536336268 ^ (long)-1536336267) << 1);
        float f3 = this.height / ((int)((long)1146681104 ^ (long)1146681105) << 1);
        float f4 = Float.intBitsToFloat((int)((long)846390156 ^ (long)1914889100));
        Path2D.Float float_ = new Path2D.Float((int)-1651857274L ^ 0x9D8AA886);
        float_.append(new Line2D.Float(f2 - f4, f3 - f4, f2 + f4, f3 + f4), ((int)-1304146264L ^ 0xB2444EA8) != 0);
        float_.append(new Line2D.Float(f2 - f4, f3 + f4, f2 + f4, f3 - f4), ((int)-137689383L ^ 0xF7CB06D9) != 0);
        graphics2D.setStroke(new BasicStroke(1.0f));
        graphics2D.draw(float_);
    }
}

