/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;

public class ScaledEmptyBorder
extends EmptyBorder {
    public ScaledEmptyBorder(int n2, int n3, int n4, int n5) {
        super(n2, n3, n4, n5);
    }

    public ScaledEmptyBorder(Insets insets) {
        super(insets);
    }

    @Override
    public Insets getBorderInsets() {
        return new Insets(UIScale.scale(this.top), UIScale.scale(this.left), UIScale.scale(this.bottom), UIScale.scale(this.right));
    }

    @Override
    public Insets getBorderInsets(Component component, Insets insets) {
        insets.left = UIScale.scale(this.left);
        insets.top = UIScale.scale(this.top);
        insets.right = UIScale.scale(this.right);
        insets.bottom = UIScale.scale(this.bottom);
        return insets;
    }
}

