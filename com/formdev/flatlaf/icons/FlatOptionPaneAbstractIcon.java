/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

public abstract class FlatOptionPaneAbstractIcon
extends FlatAbstractIcon {
    protected final Color foreground = UIManager.getColor("OptionPane.icon.foreground");

    protected FlatOptionPaneAbstractIcon(String string, String string2) {
        super((int)((long)-1733998959 ^ (long)-1733998960) << 5, ((int)873191510L ^ 0x340BD857) << 5, FlatUIUtils.getUIColor(string, string2));
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        if (this.foreground != null) {
            graphics2D.fill(this.createOutside());
            graphics2D.setColor(this.foreground);
            graphics2D.fill(this.createInside());
        } else {
            Path2D.Float float_ = new Path2D.Float((int)((long)910050399 ^ (long)910050399));
            float_.append(this.createOutside(), ((int)-498626031L ^ 0xE2479211) != 0);
            float_.append(this.createInside(), ((int)-580294717L ^ 0xDD6967C3) != 0);
            graphics2D.fill(float_);
        }
    }

    protected abstract Shape createOutside();

    protected abstract Shape createInside();
}

