/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.JTableHeader;

public class FlatTableUI
extends BasicTableUI {
    protected boolean showHorizontalLines;
    protected boolean showVerticalLines;
    protected Dimension intercellSpacing;
    protected Color selectionBackground;
    protected Color selectionForeground;
    protected Color selectionInactiveBackground;
    protected Color selectionInactiveForeground;
    private boolean oldShowHorizontalLines;
    private boolean oldShowVerticalLines;
    private Dimension oldIntercellSpacing;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatTableUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        this.showHorizontalLines = UIManager.getBoolean("Table.showHorizontalLines");
        this.showVerticalLines = UIManager.getBoolean("Table.showVerticalLines");
        this.intercellSpacing = UIManager.getDimension("Table.intercellSpacing");
        this.selectionBackground = UIManager.getColor("Table.selectionBackground");
        this.selectionForeground = UIManager.getColor("Table.selectionForeground");
        this.selectionInactiveBackground = UIManager.getColor("Table.selectionInactiveBackground");
        this.selectionInactiveForeground = UIManager.getColor("Table.selectionInactiveForeground");
        this.toggleSelectionColors();
        int n2 = FlatUIUtils.getUIInt("Table.rowHeight", ((int)-794429985L ^ 0xD0A5F5DE) << 4);
        if (n2 > 0) {
            LookAndFeel.installProperty(this.table, "rowHeight", UIScale.scale(n2));
        }
        if (!this.showHorizontalLines) {
            this.oldShowHorizontalLines = this.table.getShowHorizontalLines();
            this.table.setShowHorizontalLines((boolean)((long)-342494520 ^ (long)-342494520));
        }
        if (!this.showVerticalLines) {
            this.oldShowVerticalLines = this.table.getShowVerticalLines();
            this.table.setShowVerticalLines((boolean)((long)1648618846 ^ (long)1648618846));
        }
        if (this.intercellSpacing != null) {
            this.oldIntercellSpacing = this.table.getIntercellSpacing();
            this.table.setIntercellSpacing(this.intercellSpacing);
        }
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.selectionBackground = null;
        this.selectionForeground = null;
        this.selectionInactiveBackground = null;
        this.selectionInactiveForeground = null;
        if (!this.showHorizontalLines && this.oldShowHorizontalLines && !this.table.getShowHorizontalLines()) {
            this.table.setShowHorizontalLines((boolean)((long)-1453025287 ^ (long)-1453025288));
        }
        if (!this.showVerticalLines && this.oldShowVerticalLines && !this.table.getShowVerticalLines()) {
            this.table.setShowVerticalLines((boolean)((long)-1461652726 ^ (long)-1461652725));
        }
        if (this.intercellSpacing != null && this.table.getIntercellSpacing().equals(this.intercellSpacing)) {
            this.table.setIntercellSpacing(this.oldIntercellSpacing);
        }
    }

    @Override
    protected FocusListener createFocusListener() {
        return new BasicTableUI.FocusHandler(){

            @Override
            public void focusGained(FocusEvent focusEvent) {
                super.focusGained(focusEvent);
                FlatTableUI.this.toggleSelectionColors();
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                super.focusLost(focusEvent);
                EventQueue.invokeLater(() -> FlatTableUI.this.toggleSelectionColors());
            }
        };
    }

    private void toggleSelectionColors() {
        if (this.table == null) {
            return;
        }
        if (FlatUIUtils.isPermanentFocusOwner(this.table)) {
            if (this.table.getSelectionBackground() == this.selectionInactiveBackground) {
                this.table.setSelectionBackground(this.selectionBackground);
            }
            if (this.table.getSelectionForeground() == this.selectionInactiveForeground) {
                this.table.setSelectionForeground(this.selectionForeground);
            }
        } else {
            if (this.table.getSelectionBackground() == this.selectionBackground) {
                this.table.setSelectionBackground(this.selectionInactiveBackground);
            }
            if (this.table.getSelectionForeground() == this.selectionForeground) {
                this.table.setSelectionForeground(this.selectionInactiveForeground);
            }
        }
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        final boolean bl = this.table.getShowHorizontalLines();
        final boolean bl2 = this.table.getShowVerticalLines();
        if (bl || bl2) {
            final boolean bl3 = this.hideLastVerticalLine();
            final int n2 = this.table.getWidth();
            JTableHeader jTableHeader = this.table.getTableHeader();
            final boolean bl4 = jTableHeader != null && jTableHeader.getDraggedColumn() != null ? (int)((long)1013453618 ^ (long)1013453619) : (int)1783108092L ^ 0x6A4811FC;
            double d2 = UIScale.getSystemScaleFactor((Graphics2D)graphics);
            final double d3 = 1.0 / d2 * (double)((int)d2);
            graphics = new Graphics2DProxy((Graphics2D)graphics){

                @Override
                public void drawLine(int n22, int n3, int n4, int n5) {
                    if (bl3 && bl2 && n22 == n4 && n3 == 0 && n22 == n2 - ((int)1635630686L ^ 0x617DBE5F) && this.wasInvokedFromPaintGrid()) {
                        return;
                    }
                    if (bl4 && SystemInfo.isJava_9_orLater && (bl && n3 == n5 || bl2 && n22 == n4) && this.wasInvokedFromPaintDraggedArea()) {
                        if (n3 == n5) {
                            super.fill(new Rectangle2D.Double(n22, n3, n4 - n22 + ((int)1471324951L ^ 0x57B2A316), d3));
                        } else if (n22 == n4) {
                            super.fill(new Rectangle2D.Double(n22, n3, d3, n5 - n3 + (int)((long)1410329292 ^ (long)1410329293)));
                        }
                        return;
                    }
                    super.drawLine(n22, n3, n4, n5);
                }

                @Override
                public void fillRect(int n22, int n3, int n4, int n5) {
                    if (bl3 && bl2 && n4 == (int)((long)-1275836606 ^ (long)-1275836605) && n3 == 0 && n22 == n2 - ((int)2126202879L ^ 0x7EBB47FE) && this.wasInvokedFromPaintGrid()) {
                        return;
                    }
                    if (d3 != 1.0) {
                        if (bl && n5 == (int)((long)2099682901 ^ (long)2099682900) && this.wasInvokedFromPaintGrid()) {
                            super.fill(new Rectangle2D.Double(n22, n3, n4, d3));
                            return;
                        }
                        if (bl2 && n4 == (int)((long)505625310 ^ (long)505625311) && n3 == 0 && this.wasInvokedFromPaintGrid()) {
                            super.fill(new Rectangle2D.Double(n22, n3, d3, n5));
                            return;
                        }
                    }
                    super.fillRect(n22, n3, n4, n5);
                }

                private boolean wasInvokedFromPaintGrid() {
                    return this.wasInvokedFromMethod("paintGrid");
                }

                private boolean wasInvokedFromPaintDraggedArea() {
                    return this.wasInvokedFromMethod("paintDraggedArea");
                }

                private boolean wasInvokedFromMethod(String string) {
                    StackTraceElement[] arrstackTraceElement = Thread.currentThread().getStackTrace();
                    for (int i2 = (int)-1253863049L ^ 0xB5439177; i2 < (int)((long)-1870283422 ^ (long)-1870283417) << 1 || i2 < arrstackTraceElement.length; ++i2) {
                        if (!"javax.swing.plaf.basic.BasicTableUI".equals(arrstackTraceElement[i2].getClassName())) continue;
                        String string2 = arrstackTraceElement[i2].getMethodName();
                        if ("paintCell".equals(string2)) {
                            return ((int)937066457L ^ 0x37DA7FD9) != 0;
                        }
                        if (!string.equals(string2)) continue;
                        return ((int)-1832536532L ^ 0x92C5B62D) != 0;
                    }
                    return ((int)-987294491L ^ 0xC52714E5) != 0;
                }
            };
        }
        super.paint(graphics, jComponent);
    }

    protected boolean hideLastVerticalLine() {
        Container container;
        Container container2 = SwingUtilities.getUnwrappedParent(this.table);
        Container container3 = container = container2 != null ? container2.getParent() : null;
        if (!(container instanceof JScrollPane)) {
            return (int)((long)-1517551059 ^ (long)-1517551059) != 0;
        }
        if (this.table.getX() + this.table.getWidth() < container2.getWidth()) {
            return ((int)1929252622L ^ 0x72FE0F0E) != 0;
        }
        JScrollPane jScrollPane = (JScrollPane)container;
        JViewport jViewport = jScrollPane.getRowHeader();
        return (jScrollPane.getComponentOrientation().isLeftToRight() ? (container2 != jViewport ? (int)-1683015922L ^ 0x9BAF370F : (int)-604050430L ^ 0xDBFEEC02) : (container2 == jViewport || jViewport == null ? (int)414257393L ^ 0x18B110F0 : (int)((long)-196190764 ^ (long)-196190764))) != 0;
    }
}

