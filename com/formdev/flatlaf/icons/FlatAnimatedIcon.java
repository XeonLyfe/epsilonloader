/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.util.AnimatedIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class FlatAnimatedIcon
extends FlatAbstractIcon
implements AnimatedIcon {
    public FlatAnimatedIcon(int n2, int n3, Color color) {
        super(n2, n3, color);
    }

    @Override
    public void paintIcon(Component component, Graphics graphics, int n2, int n3) {
        super.paintIcon(component, graphics, n2, n3);
        AnimatedIcon.AnimationSupport.saveIconLocation(this, component, n2, n3);
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        AnimatedIcon.AnimationSupport.paintIcon(this, component, graphics2D, (int)1447067698L ^ 0x56408032, (int)134517251L ^ 0x8049203);
    }
}

