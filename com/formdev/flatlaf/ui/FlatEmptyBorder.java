/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.plaf.BorderUIResource;

public class FlatEmptyBorder
extends BorderUIResource.EmptyBorderUIResource {
    public FlatEmptyBorder() {
        super((int)-977662069L ^ 0xC5BA0F8B, (int)474732256L ^ 0x1C4BD6E0, (int)757426969L ^ 0x2D256B19, (int)((long)-1243065288 ^ (long)-1243065288));
    }

    public FlatEmptyBorder(int n2, int n3, int n4, int n5) {
        super(n2, n3, n4, n5);
    }

    public FlatEmptyBorder(Insets insets) {
        super(insets);
    }

    @Override
    public Insets getBorderInsets() {
        return new Insets(UIScale.scale(this.top), UIScale.scale(this.left), UIScale.scale(this.bottom), UIScale.scale(this.right));
    }

    @Override
    public Insets getBorderInsets(Component component, Insets insets) {
        int n2 = this.left == this.right || component.getComponentOrientation().isLeftToRight() ? (int)((long)1627935495 ^ (long)1627935494) : (int)((long)2034288644 ^ (long)2034288644);
        insets.left = UIScale.scale(n2 != 0 ? this.left : this.right);
        insets.top = UIScale.scale(this.top);
        insets.right = UIScale.scale(n2 != 0 ? this.right : this.left);
        insets.bottom = UIScale.scale(this.bottom);
        return insets;
    }

    public Insets getUnscaledBorderInsets() {
        return super.getBorderInsets();
    }
}

