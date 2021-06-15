/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatNativeWindowBorder;
import com.formdev.flatlaf.ui.FlatRootPaneUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.LookAndFeel;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ActionMapUIResource;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicMenuBarUI;

public class FlatMenuBarUI
extends BasicMenuBarUI {
    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatMenuBarUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installProperty(this.menuBar, "opaque", (boolean)((long)421718498 ^ (long)421718498));
    }

    @Override
    protected void installKeyboardActions() {
        super.installKeyboardActions();
        ActionMap actionMap = SwingUtilities.getUIActionMap(this.menuBar);
        if (actionMap == null) {
            actionMap = new ActionMapUIResource();
            SwingUtilities.replaceUIActionMap(this.menuBar, actionMap);
        }
        actionMap.put("takeFocus", new TakeFocus());
    }

    @Override
    public void update(Graphics graphics, JComponent jComponent) {
        Color color = this.getBackground(jComponent);
        if (color != null) {
            graphics.setColor(color);
            graphics.fillRect((int)1383820139L ^ 0x527B6B6B, (int)1339656259L ^ 0x4FD98843, jComponent.getWidth(), jComponent.getHeight());
        }
        this.paint(graphics, jComponent);
    }

    protected Color getBackground(JComponent jComponent) {
        Color color = jComponent.getBackground();
        if (jComponent.isOpaque() || !(color instanceof UIResource)) {
            return color;
        }
        JRootPane jRootPane = SwingUtilities.getRootPane(jComponent);
        if (jRootPane == null || !(jRootPane.getParent() instanceof Window) || jRootPane.getJMenuBar() != jComponent) {
            return color;
        }
        if (UIManager.getBoolean("TitlePane.unifiedBackground") && FlatNativeWindowBorder.hasCustomDecoration((Window)jRootPane.getParent())) {
            color = FlatUIUtils.getParentBackground(jComponent);
        }
        if (FlatUIUtils.isFullScreen(jRootPane)) {
            return color;
        }
        return FlatRootPaneUI.isMenuBarEmbedded(jRootPane) ? null : color;
    }

    private static class TakeFocus
    extends AbstractAction {
        private TakeFocus() {
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JMenuBar jMenuBar = (JMenuBar)actionEvent.getSource();
            JMenu jMenu = jMenuBar.getMenu((int)((long)-823050844 ^ (long)-823050844));
            if (jMenu != null) {
                MenuElement[] arrmenuElement;
                MenuSelectionManager menuSelectionManager = MenuSelectionManager.defaultManager();
                if (SystemInfo.isWindows) {
                    MenuElement[] arrmenuElement2 = new MenuElement[((int)1393828903L ^ 0x53142426) << 1];
                    arrmenuElement2[(int)((long)-1339848071 ^ (long)-1339848071)] = jMenuBar;
                    arrmenuElement = arrmenuElement2;
                    arrmenuElement2[(int)((long)1794469309 ^ (long)1794469308)] = jMenu;
                } else {
                    MenuElement[] arrmenuElement3 = new MenuElement[(int)((long)-853824047 ^ (long)-853824046)];
                    arrmenuElement3[(int)100105282L ^ 100105282] = jMenuBar;
                    arrmenuElement3[(int)910450528L ^ 910450529] = jMenu;
                    arrmenuElement = arrmenuElement3;
                    arrmenuElement3[((int)1867407345L ^ 1867407344) << 1] = jMenu.getPopupMenu();
                }
                menuSelectionManager.setSelectedPath(arrmenuElement);
                FlatLaf.showMnemonics(jMenuBar);
            }
        }
    }
}

