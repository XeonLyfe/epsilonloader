/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatLineBorder;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FlatTableCellBorder
extends FlatLineBorder {
    final boolean showCellFocusIndicator = UIManager.getBoolean("Table.showCellFocusIndicator");

    protected FlatTableCellBorder() {
        super(UIManager.getInsets("Table.cellMargins"), UIManager.getColor("Table.cellFocusColor"));
    }

    public static class Selected
    extends FlatTableCellBorder {
        @Override
        public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
            JTable jTable;
            if (!this.showCellFocusIndicator && (jTable = (JTable)SwingUtilities.getAncestorOfClass(JTable.class, component)) != null && !this.isSelectionEditable(jTable)) {
                return;
            }
            super.paintBorder(component, graphics, n2, n3, n4, n5);
        }

        protected boolean isSelectionEditable(JTable jTable) {
            int n2;
            int n3;
            int n4;
            int n5;
            int[] arrn;
            int[] arrn2;
            int n6;
            if (jTable.getRowSelectionAllowed()) {
                n6 = jTable.getColumnCount();
                arrn = arrn2 = jTable.getSelectedRows();
                n5 = arrn.length;
                for (n4 = (int)((long)-1129103523 ^ (long)-1129103523); n4 < n5; ++n4) {
                    n3 = arrn[n4];
                    for (n2 = (int)344301159L ^ 0x14859E67; n2 < n6; ++n2) {
                        if (!jTable.isCellEditable(n3, n2)) continue;
                        return (int)((long)2108722654 ^ (long)2108722655) != 0;
                    }
                }
            }
            if (jTable.getColumnSelectionAllowed()) {
                n6 = jTable.getRowCount();
                arrn = arrn2 = jTable.getSelectedColumns();
                n5 = arrn.length;
                for (n4 = (int)465506068L ^ 0x1BBF0F14; n4 < n5; ++n4) {
                    n3 = arrn[n4];
                    for (n2 = (int)((long)-812815276 ^ (long)-812815276); n2 < n6; ++n2) {
                        if (!jTable.isCellEditable(n2, n3)) continue;
                        return (int)((long)281460697 ^ (long)281460696) != 0;
                    }
                }
            }
            return ((int)447043072L ^ 0x1AA55600) != 0;
        }
    }

    public static class Focused
    extends FlatTableCellBorder {
    }

    public static class Default
    extends FlatTableCellBorder {
        @Override
        public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
        }
    }
}

