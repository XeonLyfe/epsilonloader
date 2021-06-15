/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatDesktopIconUI;
import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicDesktopPaneUI;

public class FlatDesktopPaneUI
extends BasicDesktopPaneUI {
    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatDesktopPaneUI();
    }

    @Override
    protected void installDesktopManager() {
        this.desktopManager = this.desktop.getDesktopManager();
        if (this.desktopManager == null) {
            this.desktopManager = new FlatDesktopManager();
            this.desktop.setDesktopManager(this.desktopManager);
        }
    }

    private class FlatDesktopManager
    extends DefaultDesktopManager
    implements UIResource {
        private FlatDesktopManager() {
        }

        @Override
        public void iconifyFrame(JInternalFrame jInternalFrame) {
            super.iconifyFrame(jInternalFrame);
            ((FlatDesktopIconUI)jInternalFrame.getDesktopIcon().getUI()).updateDockIcon();
        }
    }
}

