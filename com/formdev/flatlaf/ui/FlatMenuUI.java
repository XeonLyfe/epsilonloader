/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatMenuItemRenderer;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuUI;

public class FlatMenuUI
extends BasicMenuUI {
    private Color hoverBackground;
    private FlatMenuItemRenderer renderer;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatMenuUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installProperty(this.menuItem, "iconTextGap", FlatUIUtils.getUIInt("MenuItem.iconTextGap", ((int)511763287L ^ 0x1E80E356) << 2));
        this.menuItem.setRolloverEnabled((boolean)((long)-1165428206 ^ (long)-1165428205));
        this.hoverBackground = UIManager.getColor("MenuBar.hoverBackground");
        this.renderer = this.createRenderer();
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.hoverBackground = null;
        this.renderer = null;
    }

    protected FlatMenuItemRenderer createRenderer() {
        return new FlatMenuRenderer(this.menuItem, this.checkIcon, this.arrowIcon, this.acceleratorFont, this.acceleratorDelimiter);
    }

    @Override
    protected MouseInputListener createMouseInputListener(JComponent jComponent) {
        return new BasicMenuUI.MouseInputHandler(){

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                this.rollover(mouseEvent, ((int)-364992078L ^ 0xEA3EA9B3) != 0);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                this.rollover(mouseEvent, (boolean)((long)1697926412 ^ (long)1697926412));
            }

            private void rollover(MouseEvent mouseEvent, boolean bl) {
                JMenu jMenu = (JMenu)mouseEvent.getSource();
                if (jMenu.isTopLevelMenu() && jMenu.isRolloverEnabled()) {
                    jMenu.getModel().setRollover(bl);
                    jMenu.repaint();
                }
            }
        };
    }

    @Override
    public Dimension getMinimumSize(JComponent jComponent) {
        return ((JMenu)this.menuItem).isTopLevelMenu() ? jComponent.getPreferredSize() : null;
    }

    @Override
    protected Dimension getPreferredMenuItemSize(JComponent jComponent, Icon icon, Icon icon2, int n2) {
        return this.renderer.getPreferredMenuItemSize();
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        this.renderer.paintMenuItem(graphics, this.selectionBackground, this.selectionForeground, this.disabledForeground, this.acceleratorForeground, this.acceleratorSelectionForeground);
    }

    protected class FlatMenuRenderer
    extends FlatMenuItemRenderer {
        protected final Color menuBarUnderlineSelectionBackground;
        protected final Color menuBarUnderlineSelectionColor;
        protected final int menuBarUnderlineSelectionHeight;

        protected FlatMenuRenderer(JMenuItem jMenuItem, Icon icon, Icon icon2, Font font, String string) {
            super(jMenuItem, icon, icon2, font, string);
            this.menuBarUnderlineSelectionBackground = FlatUIUtils.getUIColor("MenuBar.underlineSelectionBackground", this.underlineSelectionBackground);
            this.menuBarUnderlineSelectionColor = FlatUIUtils.getUIColor("MenuBar.underlineSelectionColor", this.underlineSelectionColor);
            this.menuBarUnderlineSelectionHeight = FlatUIUtils.getUIInt("MenuBar.underlineSelectionHeight", this.underlineSelectionHeight);
        }

        @Override
        protected void paintBackground(Graphics graphics, Color color) {
            ButtonModel buttonModel;
            if (this.isUnderlineSelection() && ((JMenu)this.menuItem).isTopLevelMenu()) {
                color = this.menuBarUnderlineSelectionBackground;
            }
            if ((buttonModel = this.menuItem.getModel()).isRollover() && !buttonModel.isArmed() && !buttonModel.isSelected() && buttonModel.isEnabled() && ((JMenu)this.menuItem).isTopLevelMenu()) {
                graphics.setColor(this.deriveBackground(FlatMenuUI.this.hoverBackground));
                graphics.fillRect((int)-895985746L ^ 0xCA9857AE, (int)((long)1005577073 ^ (long)1005577073), this.menuItem.getWidth(), this.menuItem.getHeight());
            } else {
                super.paintBackground(graphics, color);
            }
        }

        @Override
        protected void paintUnderlineSelection(Graphics graphics, Color color, int n2) {
            if (((JMenu)this.menuItem).isTopLevelMenu()) {
                color = this.menuBarUnderlineSelectionColor;
                n2 = this.menuBarUnderlineSelectionHeight;
            }
            super.paintUnderlineSelection(graphics, color, n2);
        }
    }
}

