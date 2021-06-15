/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatBorder;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatToggleButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Paint;
import javax.swing.AbstractButton;
import javax.swing.UIManager;
import javax.swing.plaf.UIResource;

public class FlatButtonBorder
extends FlatBorder {
    protected final Color borderColor = FlatUIUtils.getUIColor("Button.startBorderColor", "Button.borderColor");
    protected final Color endBorderColor = UIManager.getColor("Button.endBorderColor");
    protected final Color disabledBorderColor = UIManager.getColor("Button.disabledBorderColor");
    protected final Color focusedBorderColor = UIManager.getColor("Button.focusedBorderColor");
    protected final Color hoverBorderColor = UIManager.getColor("Button.hoverBorderColor");
    protected final Color defaultBorderColor = FlatUIUtils.getUIColor("Button.default.startBorderColor", "Button.default.borderColor");
    protected final Color defaultEndBorderColor = UIManager.getColor("Button.default.endBorderColor");
    protected final Color defaultHoverBorderColor = UIManager.getColor("Button.default.hoverBorderColor");
    protected final Color defaultFocusedBorderColor = UIManager.getColor("Button.default.focusedBorderColor");
    protected final Color defaultFocusColor = UIManager.getColor("Button.default.focusColor");
    protected final int borderWidth = UIManager.getInt("Button.borderWidth");
    protected final int defaultBorderWidth = UIManager.getInt("Button.default.borderWidth");
    protected final float buttonInnerFocusWidth = FlatUIUtils.getUIFloat("Button.innerFocusWidth", this.innerFocusWidth);
    protected final Insets toolbarMargin = UIManager.getInsets("Button.toolbar.margin");
    protected final Insets toolbarSpacingInsets = UIManager.getInsets("Button.toolbar.spacingInsets");
    protected final int arc = UIManager.getInt("Button.arc");

    @Override
    public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
        if (FlatButtonUI.isContentAreaFilled(component) && !FlatButtonUI.isToolBarButton(component) && !FlatButtonUI.isHelpButton(component) && !FlatToggleButtonUI.isTabButton(component)) {
            super.paintBorder(component, graphics, n2, n3, n4, n5);
        }
    }

    @Override
    protected Color getFocusColor(Component component) {
        return FlatButtonUI.isDefaultButton(component) ? this.defaultFocusColor : super.getFocusColor(component);
    }

    @Override
    protected boolean isFocused(Component component) {
        return (FlatButtonUI.isFocusPainted(component) && super.isFocused(component) ? (int)814271581L ^ 0x3088CC5C : (int)-941575904L ^ 0xC7E0B120) != 0;
    }

    @Override
    protected Paint getBorderColor(Component component) {
        Color color;
        boolean bl = FlatButtonUI.isDefaultButton(component);
        Paint paint = FlatButtonUI.buttonStateColor(component, bl ? this.defaultBorderColor : this.borderColor, this.disabledBorderColor, bl ? this.defaultFocusedBorderColor : this.focusedBorderColor, bl ? this.defaultHoverBorderColor : this.hoverBorderColor, null);
        Color color2 = bl ? this.defaultBorderColor : this.borderColor;
        Color color3 = color = bl ? this.defaultEndBorderColor : this.endBorderColor;
        if (paint == color2 && color != null && !color2.equals(color)) {
            paint = new GradientPaint(0.0f, 0.0f, color2, 0.0f, component.getHeight(), color);
        }
        return paint;
    }

    @Override
    public Insets getBorderInsets(Component component, Insets insets) {
        if (FlatButtonUI.isToolBarButton(component)) {
            Insets insets2 = component instanceof AbstractButton ? ((AbstractButton)component).getMargin() : null;
            FlatUIUtils.setInsets(insets, UIScale.scale(FlatUIUtils.addInsets(this.toolbarSpacingInsets, insets2 != null && !(insets2 instanceof UIResource) ? insets2 : this.toolbarMargin)));
        } else {
            insets = super.getBorderInsets(component, insets);
            if (FlatButtonUI.isIconOnlyOrSingleCharacterButton(component) && ((AbstractButton)component).getMargin() instanceof UIResource) {
                insets.left = insets.right = Math.min(insets.top, insets.bottom);
            }
        }
        return insets;
    }

    @Override
    protected int getFocusWidth(Component component) {
        return FlatToggleButtonUI.isTabButton(component) ? (int)((long)-650661483 ^ (long)-650661483) : super.getFocusWidth(component);
    }

    @Override
    protected float getInnerFocusWidth(Component component) {
        return this.buttonInnerFocusWidth;
    }

    @Override
    protected int getBorderWidth(Component component) {
        return FlatButtonUI.isDefaultButton(component) ? this.defaultBorderWidth : this.borderWidth;
    }

    @Override
    protected int getArc(Component component) {
        if (this.isCellEditor(component)) {
            return (int)-1167524464L ^ 0xBA68FD90;
        }
        switch (FlatButtonUI.getButtonType(component)) {
            case 0: {
                return (int)567632056L ^ 0x21D560B8;
            }
            case 1: {
                return (int)((long)688080330 ^ (long)688077365);
            }
        }
        return this.arc;
    }
}

