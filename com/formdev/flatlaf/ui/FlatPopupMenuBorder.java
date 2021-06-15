/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class FlatPopupMenuBorder
extends FlatLineBorder {
    public FlatPopupMenuBorder() {
        super(UIManager.getInsets("PopupMenu.borderInsets"), UIManager.getColor("PopupMenu.borderColor"));
    }

    @Override
    public Insets getBorderInsets(Component component, Insets insets) {
        if (component instanceof Container && ((Container)component).getComponentCount() > 0 && ((Container)component).getComponent((int)((long)-1167132757 ^ (long)-1167132757)) instanceof JScrollPane) {
            insets.right = insets.bottom = UIScale.scale((int)-1112524727L ^ 0xBDB03848);
            insets.top = insets.bottom;
            insets.left = insets.bottom;
            return insets;
        }
        return super.getBorderInsets(component, insets);
    }
}

