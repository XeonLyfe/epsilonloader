/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

public class FlatTabbedPaneCloseIcon
extends FlatAbstractIcon {
    protected final Dimension size = UIManager.getDimension("TabbedPane.closeSize");
    protected final int arc = UIManager.getInt("TabbedPane.closeArc");
    protected final float crossPlainSize = FlatUIUtils.getUIFloat("TabbedPane.closeCrossPlainSize", Float.intBitsToFloat((int)((long)2124466984 ^ (long)1045482280)));
    protected final float crossFilledSize = FlatUIUtils.getUIFloat("TabbedPane.closeCrossFilledSize", this.crossPlainSize);
    protected final float closeCrossLineWidth = FlatUIUtils.getUIFloat("TabbedPane.closeCrossLineWidth", 1.0f);
    protected final Color background = UIManager.getColor("TabbedPane.closeBackground");
    protected final Color foreground = UIManager.getColor("TabbedPane.closeForeground");
    protected final Color hoverBackground = UIManager.getColor("TabbedPane.closeHoverBackground");
    protected final Color hoverForeground = UIManager.getColor("TabbedPane.closeHoverForeground");
    protected final Color pressedBackground = UIManager.getColor("TabbedPane.closePressedBackground");
    protected final Color pressedForeground = UIManager.getColor("TabbedPane.closePressedForeground");

    public FlatTabbedPaneCloseIcon() {
        super((int)((long)1598184010 ^ (long)1598184011) << 4, ((int)-1914543311L ^ 0x8DE26330) << 4, null);
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        Color color = FlatButtonUI.buttonStateColor(component, this.background, null, null, this.hoverBackground, this.pressedBackground);
        if (color != null) {
            graphics2D.setColor(FlatUIUtils.deriveColor(color, component.getBackground()));
            graphics2D.fillRoundRect((this.width - this.size.width) / (((int)1416874006L ^ 0x5473C817) << 1), (this.height - this.size.height) / ((int)((long)1358838542 ^ (long)1358838543) << 1), this.size.width, this.size.height, this.arc, this.arc);
        }
        Color color2 = FlatButtonUI.buttonStateColor(component, this.foreground, null, null, this.hoverForeground, this.pressedForeground);
        graphics2D.setColor(FlatUIUtils.deriveColor(color2, component.getForeground()));
        float f2 = this.width / (((int)-1713596230L ^ 0x99DC98BB) << 1);
        float f3 = this.height / (((int)-915535257L ^ 0xC96E0A66) << 1);
        float f4 = (color != null ? this.crossFilledSize : this.crossPlainSize) / 2.0f;
        Path2D.Float float_ = new Path2D.Float((int)123005527L ^ 0x754EA57);
        float_.append(new Line2D.Float(f2 - f4, f3 - f4, f2 + f4, f3 + f4), ((int)-1656056054L ^ 0x9D4A970A) != 0);
        float_.append(new Line2D.Float(f2 - f4, f3 + f4, f2 + f4, f3 - f4), ((int)869820553L ^ 0x33D86889) != 0);
        graphics2D.setStroke(new BasicStroke(this.closeCrossLineWidth));
        graphics2D.draw(float_);
    }
}

