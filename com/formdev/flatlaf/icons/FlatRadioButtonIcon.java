/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatCheckBoxIcon;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class FlatRadioButtonIcon
extends FlatCheckBoxIcon {
    protected final int centerDiameter;

    public FlatRadioButtonIcon() {
        this.centerDiameter = FlatRadioButtonIcon.getUIInt("RadioButton.icon.centerDiameter", ((int)1051653251L ^ 0x3EAEF482) << 3, this.style);
    }

    @Override
    protected void paintFocusBorder(Component component, Graphics2D graphics2D) {
        int n2 = (int)((long)998392517 ^ (long)998392522) + this.focusWidth * (((int)408889632L ^ 0x185F2921) << 1);
        graphics2D.fillOval(-this.focusWidth, -this.focusWidth, n2, n2);
    }

    @Override
    protected void paintBorder(Component component, Graphics2D graphics2D) {
        graphics2D.fillOval((int)((long)1753603836 ^ (long)1753603836), (int)-59330498L ^ 0xFC76B03E, (int)((long)-1476936876 ^ (long)-1476936869), (int)((long)-1932018039 ^ (long)-1932018042));
    }

    @Override
    protected void paintBackground(Component component, Graphics2D graphics2D) {
        graphics2D.fillOval((int)((long)-32699049 ^ (long)-32699050), (int)((long)-1527889501 ^ (long)-1527889502), (int)((long)1909579736 ^ (long)1909579733), (int)((long)-2038032592 ^ (long)-2038032579));
    }

    @Override
    protected void paintCheckmark(Component component, Graphics2D graphics2D) {
        float f2 = (float)((int)((long)-875968734 ^ (long)-875968723) - this.centerDiameter) / 2.0f;
        graphics2D.fill(new Ellipse2D.Float(f2, f2, this.centerDiameter, this.centerDiameter));
    }
}

