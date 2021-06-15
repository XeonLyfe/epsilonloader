/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import javax.swing.CellRendererPane;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

public class FlatTreeUI
extends BasicTreeUI {
    protected Color selectionBackground;
    protected Color selectionForeground;
    protected Color selectionInactiveBackground;
    protected Color selectionInactiveForeground;
    protected Color selectionBorderColor;
    protected boolean wideSelection;
    protected boolean showCellFocusIndicator;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatTreeUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installBorder(this.tree, "Tree.border");
        this.selectionBackground = UIManager.getColor("Tree.selectionBackground");
        this.selectionForeground = UIManager.getColor("Tree.selectionForeground");
        this.selectionInactiveBackground = UIManager.getColor("Tree.selectionInactiveBackground");
        this.selectionInactiveForeground = UIManager.getColor("Tree.selectionInactiveForeground");
        this.selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
        this.wideSelection = UIManager.getBoolean("Tree.wideSelection");
        this.showCellFocusIndicator = UIManager.getBoolean("Tree.showCellFocusIndicator");
        int n2 = FlatUIUtils.getUIInt("Tree.rowHeight", ((int)-2045773355L ^ 0x860FF9D4) << 4);
        if (n2 > 0) {
            LookAndFeel.installProperty(this.tree, "rowHeight", UIScale.scale(n2));
        }
        this.setLeftChildIndent(UIScale.scale(this.getLeftChildIndent()));
        this.setRightChildIndent(UIScale.scale(this.getRightChildIndent()));
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        LookAndFeel.uninstallBorder(this.tree);
        this.selectionBackground = null;
        this.selectionForeground = null;
        this.selectionInactiveBackground = null;
        this.selectionInactiveForeground = null;
        this.selectionBorderColor = null;
    }

    @Override
    protected MouseListener createMouseListener() {
        return new BasicTreeUI.MouseHandler(){

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(this.handleWideMouseEvent(mouseEvent));
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                super.mouseReleased(this.handleWideMouseEvent(mouseEvent));
            }

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                super.mouseDragged(this.handleWideMouseEvent(mouseEvent));
            }

            private MouseEvent handleWideMouseEvent(MouseEvent mouseEvent) {
                if (!FlatTreeUI.this.isWideSelection() || !FlatTreeUI.this.tree.isEnabled() || !SwingUtilities.isLeftMouseButton(mouseEvent) || mouseEvent.isConsumed()) {
                    return mouseEvent;
                }
                int n2 = mouseEvent.getX();
                int n3 = mouseEvent.getY();
                TreePath treePath = FlatTreeUI.this.getClosestPathForLocation(FlatTreeUI.this.tree, n2, n3);
                if (treePath == null || FlatTreeUI.this.isLocationInExpandControl(treePath, n2, n3)) {
                    return mouseEvent;
                }
                Rectangle rectangle = FlatTreeUI.this.getPathBounds(FlatTreeUI.this.tree, treePath);
                if (rectangle == null || n3 < rectangle.y || n3 >= rectangle.y + rectangle.height) {
                    return mouseEvent;
                }
                int n4 = Math.max(rectangle.x, Math.min(n2, rectangle.x + rectangle.width - (int)((long)93595662 ^ (long)93595663)));
                if (n4 == n2) {
                    return mouseEvent;
                }
                return new MouseEvent(mouseEvent.getComponent(), mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers() | mouseEvent.getModifiersEx(), n4, mouseEvent.getY(), mouseEvent.getClickCount(), mouseEvent.isPopupTrigger(), mouseEvent.getButton());
            }
        };
    }

    @Override
    protected PropertyChangeListener createPropertyChangeListener() {
        PropertyChangeListener propertyChangeListener = super.createPropertyChangeListener();
        return var2_2 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter$TooOptimisticMatchException
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.getInt(SwitchStringRewriter.java:392)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.access$300(SwitchStringRewriter.java:50)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter$SwitchStringMatchResultCollector.collectMatches(SwitchStringRewriter.java:343)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.ResetAfterTest.match(ResetAfterTest.java:24)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.KleeneN.match(KleeneN.java:24)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.MatchSequence.match(MatchSequence.java:26)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.ResetAfterTest.match(ResetAfterTest.java:23)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.rewriteComplex(SwitchStringRewriter.java:197)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.rewrite(SwitchStringRewriter.java:70)
             * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:837)
             * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:258)
             * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:192)
             * org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             * org.benf.cfr.reader.entities.Method.analyse(Method.java:521)
             * org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1030)
             * org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:922)
             * org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:253)
             * org.benf.cfr.reader.Driver.doJar(Driver.java:135)
             * org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:65)
             * org.benf.cfr.reader.Main.main(Main.java:49)
             * async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:259)
             * async.DecompilerRunnable.call(DecompilerRunnable.java:220)
             * async.DecompilerRunnable.call(DecompilerRunnable.java:26)
             * java.util.concurrent.FutureTask.run(FutureTask.java:266)
             * java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
             * java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
             * java.lang.Thread.run(Thread.java:748)
             */
            throw new IllegalStateException(Decompilation failed);
        };
    }

    private void repaintWideDropLocation(JTree.DropLocation dropLocation) {
        if (dropLocation == null || this.isDropLine(dropLocation)) {
            return;
        }
        Rectangle rectangle = this.tree.getPathBounds(dropLocation.getPath());
        if (rectangle != null) {
            this.tree.repaint((int)((long)-1146185132 ^ (long)-1146185132), rectangle.y, this.tree.getWidth(), rectangle.height);
        }
    }

    @Override
    protected void paintRow(Graphics graphics, Rectangle rectangle, Insets insets, Rectangle rectangle2, TreePath treePath, int n2, boolean bl, boolean bl2, boolean bl3) {
        Serializable serializable;
        Serializable serializable2;
        int n3;
        int n4;
        int n5 = this.editingComponent != null && this.editingRow == n2 ? (int)-1406065891L ^ 0xAC31231C : (int)((long)1124417040 ^ (long)1124417040);
        boolean bl4 = this.tree.isRowSelected(n2);
        boolean bl5 = this.isDropRow(n2);
        int n6 = n4 = (bl4 || bl5) && this.isPaintSelection() ? (int)-715633035L ^ 0xD5584E74 : (int)1150644048L ^ 0x44956F50;
        if (n5 != 0 && n4 == 0) {
            return;
        }
        boolean bl6 = FlatUIUtils.isPermanentFocusOwner(this.tree);
        int n7 = n3 = bl6 && n2 == this.getLeadSelectionRow() ? (int)((long)223106767 ^ (long)223106766) : (int)2062288211L ^ 0x7AEC0553;
        if (!bl6 && bl4 && this.tree.getParent() instanceof CellRendererPane) {
            bl6 = FlatUIUtils.isPermanentFocusOwner(this.tree.getParent().getParent());
        }
        Component component = this.currentCellRenderer.getTreeCellRendererComponent(this.tree, treePath.getLastPathComponent(), bl4, bl, bl3, n2, n3 != 0);
        Color color = null;
        if (bl4 && !bl6 && !bl5) {
            if (component instanceof DefaultTreeCellRenderer) {
                serializable2 = (DefaultTreeCellRenderer)component;
                if (serializable2.getBackgroundSelectionColor() == this.selectionBackground) {
                    color = serializable2.getBackgroundSelectionColor();
                    serializable2.setBackgroundSelectionColor(this.selectionInactiveBackground);
                }
            } else if (component.getBackground() == this.selectionBackground) {
                component.setBackground(this.selectionInactiveBackground);
            }
            if (component.getForeground() == this.selectionForeground) {
                component.setForeground(this.selectionInactiveForeground);
            }
        }
        serializable2 = null;
        if (bl4 && bl6 && (!this.showCellFocusIndicator || this.tree.getMinSelectionRow() == this.tree.getMaxSelectionRow()) && component instanceof DefaultTreeCellRenderer && ((DefaultTreeCellRenderer)(serializable = (DefaultTreeCellRenderer)component)).getBorderSelectionColor() == this.selectionBorderColor) {
            serializable2 = ((DefaultTreeCellRenderer)serializable).getBorderSelectionColor();
            ((DefaultTreeCellRenderer)serializable).setBorderSelectionColor(null);
        }
        if (n4 != 0) {
            serializable = graphics.getColor();
            graphics.setColor(bl5 ? UIManager.getColor("Tree.dropCellBackground") : (component instanceof DefaultTreeCellRenderer ? ((DefaultTreeCellRenderer)component).getBackgroundSelectionColor() : (bl6 ? this.selectionBackground : this.selectionInactiveBackground)));
            if (this.isWideSelection()) {
                graphics.fillRect((int)((long)307034828 ^ (long)307034828), rectangle2.y, this.tree.getWidth(), rectangle2.height);
                if (this.shouldPaintExpandControl(treePath, n2, bl, bl2, bl3)) {
                    this.paintExpandControl(graphics, rectangle, insets, rectangle2, treePath, n2, bl, bl2, bl3);
                }
            } else {
                int n8 = (int)-1394376893L ^ 0xACE37F43;
                int n9 = (int)((long)-1477817638 ^ (long)-1477817638);
                if (component instanceof JLabel) {
                    JLabel jLabel = (JLabel)component;
                    Icon icon = jLabel.getIcon();
                    n9 = icon != null && jLabel.getText() != null ? icon.getIconWidth() + Math.max(jLabel.getIconTextGap() - (int)((long)-1087783197 ^ (long)-1087783198), (int)895276642L ^ 0x355CD662) : (int)1198365996L ^ 0x476D9D2C;
                    n8 = jLabel.getComponentOrientation().isLeftToRight() ? n9 : (int)-1399742866L ^ 0xAC919E6E;
                }
                graphics.fillRect(rectangle2.x + n8, rectangle2.y, rectangle2.width - n9, rectangle2.height);
            }
            graphics.setColor((Color)serializable);
        }
        if (n5 == 0) {
            this.rendererPane.paintComponent(graphics, component, this.tree, rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height, (boolean)((long)1973566619 ^ (long)1973566618));
        }
        if (color != null) {
            ((DefaultTreeCellRenderer)component).setBackgroundSelectionColor(color);
        }
        if (serializable2 != null) {
            ((DefaultTreeCellRenderer)component).setBorderSelectionColor((Color)serializable2);
        }
    }

    private boolean isDropRow(int n2) {
        JTree.DropLocation dropLocation = this.tree.getDropLocation();
        return (dropLocation != null && dropLocation.getChildIndex() == (int)((long)1862939194 ^ (long)-1862939195) && this.tree.getRowForPath(dropLocation.getPath()) == n2 ? (int)-1473546936L ^ 0xA82B7549 : (int)1762107537L ^ 0x6907A091) != 0;
    }

    @Override
    protected Rectangle getDropLineRect(JTree.DropLocation dropLocation) {
        Rectangle rectangle = super.getDropLineRect(dropLocation);
        return this.isWideSelection() ? new Rectangle((int)((long)-1267613606 ^ (long)-1267613606), rectangle.y, this.tree.getWidth(), rectangle.height) : rectangle;
    }

    protected boolean isWideSelection() {
        return FlatClientProperties.clientPropertyBoolean(this.tree, "JTree.wideSelection", this.wideSelection);
    }

    protected boolean isPaintSelection() {
        return FlatClientProperties.clientPropertyBoolean(this.tree, "JTree.paintSelection", ((int)1312376140L ^ 0x4E39454D) != 0);
    }
}

