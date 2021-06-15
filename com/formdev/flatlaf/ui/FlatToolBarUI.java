/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolBarUI;

public class FlatToolBarUI
extends BasicToolBarUI {
    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatToolBarUI();
    }

    @Override
    protected ContainerListener createToolBarContListener() {
        return new BasicToolBarUI.ToolBarContListener(){

            @Override
            public void componentAdded(ContainerEvent containerEvent) {
                super.componentAdded(containerEvent);
                Component component = containerEvent.getChild();
                if (component instanceof AbstractButton) {
                    component.setFocusable((boolean)((long)-211061161 ^ (long)-211061161));
                }
            }

            @Override
            public void componentRemoved(ContainerEvent containerEvent) {
                super.componentRemoved(containerEvent);
                Component component = containerEvent.getChild();
                if (component instanceof AbstractButton) {
                    component.setFocusable((boolean)((long)2109018680 ^ (long)2109018681));
                }
            }
        };
    }

    @Override
    protected void setBorderToRollover(Component component) {
    }

    @Override
    protected void setBorderToNonRollover(Component component) {
    }

    @Override
    protected void setBorderToNormal(Component component) {
    }

    @Override
    protected void installRolloverBorders(JComponent jComponent) {
    }

    @Override
    protected void installNonRolloverBorders(JComponent jComponent) {
    }

    @Override
    protected void installNormalBorders(JComponent jComponent) {
    }

    @Override
    protected Border createRolloverBorder() {
        return null;
    }

    @Override
    protected Border createNonRolloverBorder() {
        return null;
    }

    @Override
    public void setOrientation(int n2) {
        Insets insets;
        Insets insets2;
        if (n2 != this.toolBar.getOrientation() && !(insets2 = new Insets(insets.left, insets.top, insets.right, insets.bottom)).equals(insets = this.toolBar.getMargin())) {
            this.toolBar.setMargin(insets2);
        }
        super.setOrientation(n2);
    }
}

