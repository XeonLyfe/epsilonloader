/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatLineBorder;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FlatListCellBorder
extends FlatLineBorder {
    final boolean showCellFocusIndicator = UIManager.getBoolean("List.showCellFocusIndicator");

    protected FlatListCellBorder() {
        super(UIManager.getInsets("List.cellMargins"), UIManager.getColor("List.cellFocusColor"));
    }

    public static class Selected
    extends FlatListCellBorder {
        @Override
        public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
            if (!this.showCellFocusIndicator) {
                return;
            }
            JList jList = (JList)SwingUtilities.getAncestorOfClass(JList.class, component);
            if (jList != null && jList.getMinSelectionIndex() == jList.getMaxSelectionIndex()) {
                return;
            }
            super.paintBorder(component, graphics, n2, n3, n4, n5);
        }
    }

    public static class Focused
    extends FlatListCellBorder {
    }

    public static class Default
    extends FlatListCellBorder {
        @Override
        public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
        }
    }
}

