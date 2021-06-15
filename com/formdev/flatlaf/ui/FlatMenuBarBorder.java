/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatMarginBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

public class FlatMenuBarBorder
extends FlatMarginBorder {
    private final Color borderColor = UIManager.getColor("MenuBar.borderColor");

    @Override
    public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
        float f2 = UIScale.scale(1.0f);
        FlatUIUtils.paintFilledRectangle(graphics, this.borderColor, n2, (float)(n3 + n5) - f2, n4, f2);
    }

    @Override
    public Insets getBorderInsets(Component component, Insets insets) {
        Insets insets2 = component instanceof JMenuBar ? ((JMenuBar)component).getMargin() : new Insets((int)((long)1041034492 ^ (long)1041034492), (int)1700975693L ^ 0x6562D44D, (int)((long)1575710660 ^ (long)1575710660), (int)-1190331634L ^ 0xB90CFB0E);
        insets.top = UIScale.scale(insets2.top);
        insets.left = UIScale.scale(insets2.left);
        insets.bottom = UIScale.scale(insets2.bottom + ((int)1687486461L ^ 0x6494FFFC));
        insets.right = UIScale.scale(insets2.right);
        return insets;
    }
}

