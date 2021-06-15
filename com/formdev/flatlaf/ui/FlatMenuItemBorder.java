/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatMarginBorder;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

public class FlatMenuItemBorder
extends FlatMarginBorder {
    private final Insets menuBarItemMargins = UIManager.getInsets("MenuBar.itemMargins");

    @Override
    public Insets getBorderInsets(Component component, Insets insets) {
        if (component.getParent() instanceof JMenuBar) {
            insets.top = UIScale.scale(this.menuBarItemMargins.top);
            insets.left = UIScale.scale(this.menuBarItemMargins.left);
            insets.bottom = UIScale.scale(this.menuBarItemMargins.bottom);
            insets.right = UIScale.scale(this.menuBarItemMargins.right);
            return insets;
        }
        return super.getBorderInsets(component, insets);
    }
}

