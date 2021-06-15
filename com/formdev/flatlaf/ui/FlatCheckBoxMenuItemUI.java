/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatMenuItemRenderer;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;

public class FlatCheckBoxMenuItemUI
extends BasicCheckBoxMenuItemUI {
    private FlatMenuItemRenderer renderer;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatCheckBoxMenuItemUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installProperty(this.menuItem, "iconTextGap", FlatUIUtils.getUIInt("MenuItem.iconTextGap", ((int)-963274131L ^ 0xC6959A6C) << 2));
        this.renderer = this.createRenderer();
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.renderer = null;
    }

    protected FlatMenuItemRenderer createRenderer() {
        return new FlatMenuItemRenderer(this.menuItem, this.checkIcon, this.arrowIcon, this.acceleratorFont, this.acceleratorDelimiter);
    }

    @Override
    protected Dimension getPreferredMenuItemSize(JComponent jComponent, Icon icon, Icon icon2, int n2) {
        return this.renderer.getPreferredMenuItemSize();
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        this.renderer.paintMenuItem(graphics, this.selectionBackground, this.selectionForeground, this.disabledForeground, this.acceleratorForeground, this.acceleratorSelectionForeground);
    }
}

