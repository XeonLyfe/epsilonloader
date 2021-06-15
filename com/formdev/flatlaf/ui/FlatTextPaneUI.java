/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatEditorPaneUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.HiDPIUtils;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTextPaneUI;
import javax.swing.text.JTextComponent;

public class FlatTextPaneUI
extends BasicTextPaneUI {
    protected int minimumWidth;
    protected boolean isIntelliJTheme;
    private Object oldHonorDisplayProperties;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatTextPaneUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        this.minimumWidth = UIManager.getInt("Component.minimumWidth");
        this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
        this.oldHonorDisplayProperties = this.getComponent().getClientProperty("JEditorPane.honorDisplayProperties");
        this.getComponent().putClientProperty("JEditorPane.honorDisplayProperties", (boolean)((long)1974306795 ^ (long)1974306794));
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.getComponent().putClientProperty("JEditorPane.honorDisplayProperties", this.oldHonorDisplayProperties);
    }

    @Override
    protected void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        super.propertyChange(propertyChangeEvent);
        FlatEditorPaneUI.propertyChange(this.getComponent(), propertyChangeEvent);
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        return FlatEditorPaneUI.applyMinimumWidth(jComponent, super.getPreferredSize(jComponent), this.minimumWidth);
    }

    @Override
    public Dimension getMinimumSize(JComponent jComponent) {
        return FlatEditorPaneUI.applyMinimumWidth(jComponent, super.getMinimumSize(jComponent), this.minimumWidth);
    }

    @Override
    protected void paintSafely(Graphics graphics) {
        super.paintSafely(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)graphics));
    }

    @Override
    protected void paintBackground(Graphics graphics) {
        JTextComponent jTextComponent = this.getComponent();
        if (this.isIntelliJTheme && (!jTextComponent.isEnabled() || !jTextComponent.isEditable()) && jTextComponent.getBackground() instanceof UIResource) {
            FlatUIUtils.paintParentBackground(graphics, jTextComponent);
            return;
        }
        super.paintBackground(graphics);
    }
}

