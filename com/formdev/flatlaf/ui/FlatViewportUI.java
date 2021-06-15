/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicViewportUI;

public class FlatViewportUI
extends BasicViewportUI {
    public static ComponentUI createUI(JComponent jComponent) {
        return FlatUIUtils.createSharedUI(FlatViewportUI.class, FlatViewportUI::new);
    }

    @Override
    public void update(Graphics graphics, JComponent jComponent) {
        Component component = ((JViewport)jComponent).getView();
        if (jComponent.isOpaque() && component instanceof JTable) {
            graphics.setColor(component.getBackground());
            graphics.fillRect((int)251658775L ^ 0xF000217, (int)1668380255L ^ 0x6371765F, jComponent.getWidth(), jComponent.getHeight());
            this.paint(graphics, jComponent);
        } else {
            super.update(graphics, jComponent);
        }
    }
}

