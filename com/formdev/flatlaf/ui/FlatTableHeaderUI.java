/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class FlatTableHeaderUI
extends BasicTableHeaderUI {
    protected Color separatorColor;
    protected Color bottomSeparatorColor;
    protected int height;
    protected int sortIconPosition;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatTableHeaderUI();
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void installDefaults() {
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
         * org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
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
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.separatorColor = null;
        this.bottomSeparatorColor = null;
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        Object object;
        if (this.header.getColumnModel().getColumnCount() <= 0) {
            return;
        }
        TableCellRenderer tableCellRenderer = this.header.getDefaultRenderer();
        boolean bl = this.isSystemDefaultRenderer(tableCellRenderer);
        if (!bl) {
            object = tableCellRenderer.getTableCellRendererComponent(this.header.getTable(), "", ((int)-899747013L ^ 0xCA5EF33B) != 0, (boolean)((long)-1199287818 ^ (long)-1199287818), (int)-1602745057L ^ 0x5F87F2E0, (int)-1796231192L ^ 0x94EFAFE8);
            bl = this.isSystemDefaultRenderer(object);
        }
        if (bl) {
            this.paintColumnBorders(graphics, jComponent);
        }
        object = null;
        if (this.sortIconPosition != ((int)-722239021L ^ 0xD4F381D2) << 2) {
            object = new FlatTableCellHeaderRenderer(this.header.getDefaultRenderer());
            this.header.setDefaultRenderer((TableCellRenderer)object);
        }
        super.paint(graphics, jComponent);
        if (object != null) {
            ((FlatTableCellHeaderRenderer)object).reset();
            this.header.setDefaultRenderer(((FlatTableCellHeaderRenderer)object).delegate);
        }
        if (bl) {
            this.paintDraggedColumnBorders(graphics, jComponent);
        }
    }

    private boolean isSystemDefaultRenderer(Object object) {
        String string = object.getClass().getName();
        return (string.equals("sun.swing.table.DefaultTableCellHeaderRenderer") || string.equals("sun.swing.FilePane$AlignableTableHeaderRenderer") ? (int)((long)2005893012 ^ (long)2005893013) : (int)2110133916L ^ 0x7DC6169C) != 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void paintColumnBorders(Graphics graphics, JComponent jComponent) {
        int n2;
        float f2;
        int n3 = jComponent.getWidth();
        int n4 = jComponent.getHeight();
        float f3 = f2 = UIScale.scale(1.0f);
        float f4 = f2 * Float.intBitsToFloat(0x14945EF1 ^ 0x54D45EF1);
        TableColumnModel tableColumnModel = this.header.getColumnModel();
        int n5 = n2 = tableColumnModel.getColumnCount();
        if (this.hideLastVerticalLine()) {
            --n5;
        }
        Graphics2D graphics2D = (Graphics2D)graphics.create();
        try {
            FlatUIUtils.setRenderingHints(graphics2D);
            graphics2D.setColor(this.bottomSeparatorColor);
            graphics2D.fill(new Rectangle2D.Float(0.0f, (float)n4 - f2, n3, f2));
            graphics2D.setColor(this.separatorColor);
            float f5 = f3;
            float f6 = (float)n4 - f4;
            if (this.header.getComponentOrientation().isLeftToRight()) {
                int n6 = (int)((long)-368593490 ^ (long)-368593490);
                for (int i2 = (int)1587456007L ^ 0x5E9EA807; i2 < n5; ++i2) {
                    graphics2D.fill(new Rectangle2D.Float((float)(n6 += tableColumnModel.getColumn(i2).getWidth()) - f2, f5, f2, f6));
                }
                if (!this.hideTrailingVerticalLine()) {
                    graphics2D.fill(new Rectangle2D.Float((float)this.header.getWidth() - f2, f5, f2, f6));
                }
            } else {
                Rectangle rectangle = this.header.getHeaderRect((int)((long)644275991 ^ (long)644275991));
                int n7 = rectangle.x + rectangle.width;
                for (int i3 = (int)-1933559966L ^ 0x8CC03762; i3 < n5; ++i3) {
                    graphics2D.fill(new Rectangle2D.Float((float)(n7 -= tableColumnModel.getColumn(i3).getWidth()) - (i3 < n5 - (int)((long)-1608236592 ^ (long)-1608236591) ? f2 : 0.0f), f5, f2, f6));
                }
                if (!this.hideTrailingVerticalLine()) {
                    graphics2D.fill(new Rectangle2D.Float(0.0f, f5, f2, f6));
                }
            }
        }
        finally {
            graphics2D.dispose();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void paintDraggedColumnBorders(Graphics graphics, JComponent jComponent) {
        float f2;
        TableColumn tableColumn = this.header.getDraggedColumn();
        if (tableColumn == null) {
            return;
        }
        TableColumnModel tableColumnModel = this.header.getColumnModel();
        int n2 = tableColumnModel.getColumnCount();
        int n3 = (int)((long)-517397988 ^ (long)517397987);
        for (int i2 = (int)475577024L ^ 0x1C58BAC0; i2 < n2; ++i2) {
            if (tableColumnModel.getColumn(i2) != tableColumn) continue;
            n3 = i2;
            break;
        }
        if (n3 < 0) {
            return;
        }
        float f3 = f2 = UIScale.scale(1.0f);
        float f4 = f2 * Float.intBitsToFloat((int)((long)1601338333 ^ (long)523402205));
        Rectangle rectangle = this.header.getHeaderRect(n3);
        rectangle.x += this.header.getDraggedDistance();
        Graphics2D graphics2D = (Graphics2D)graphics.create();
        try {
            FlatUIUtils.setRenderingHints(graphics2D);
            graphics2D.setColor(this.bottomSeparatorColor);
            graphics2D.fill(new Rectangle2D.Float(rectangle.x, (float)(rectangle.y + rectangle.height) - f2, rectangle.width, f2));
            graphics2D.setColor(this.separatorColor);
            graphics2D.fill(new Rectangle2D.Float(rectangle.x, f3, f2, (float)rectangle.height - f4));
            graphics2D.fill(new Rectangle2D.Float((float)(rectangle.x + rectangle.width) - f2, (float)rectangle.y + f3, f2, (float)rectangle.height - f4));
        }
        finally {
            graphics2D.dispose();
        }
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        Dimension dimension = super.getPreferredSize(jComponent);
        if (dimension.height > 0) {
            dimension.height = Math.max(dimension.height, UIScale.scale(this.height));
        }
        return dimension;
    }

    protected boolean hideLastVerticalLine() {
        Container container;
        Container container2 = this.header.getParent();
        Container container3 = container = container2 != null ? container2.getParent() : null;
        if (!(container instanceof JScrollPane)) {
            return ((int)734863750L ^ 0x2BCD2186) != 0;
        }
        Rectangle rectangle = this.header.getHeaderRect(this.header.getColumnModel().getColumnCount() - ((int)313756526L ^ 0x12B38B6F));
        JScrollPane jScrollPane = (JScrollPane)container;
        return (jScrollPane.getComponentOrientation().isLeftToRight() ? (rectangle.x + rectangle.width >= container2.getWidth() ? (int)((long)1438219665 ^ (long)1438219664) : (int)((long)-1551747550 ^ (long)-1551747550)) : (rectangle.x <= 0 ? (int)-1594298286L ^ 0xA0F8F053 : (int)-191933807L ^ 0xF48F5291)) != 0;
    }

    protected boolean hideTrailingVerticalLine() {
        Container container;
        Container container2 = this.header.getParent();
        Container container3 = container = container2 != null ? container2.getParent() : null;
        if (!(container instanceof JScrollPane)) {
            return (int)((long)619361504 ^ (long)619361504) != 0;
        }
        JScrollPane jScrollPane = (JScrollPane)container;
        return (container2 == jScrollPane.getColumnHeader() && jScrollPane.getCorner("UPPER_TRAILING_CORNER") == null ? (int)((long)-2027818882 ^ (long)-2027818881) : (int)-524414703L ^ 0xE0BE1111) != 0;
    }

    private class FlatTableCellHeaderRenderer
    implements TableCellRenderer,
    Border,
    UIResource {
        private final TableCellRenderer delegate;
        private JLabel l;
        private int oldHorizontalTextPosition = (int)763456213L ^ 0xD27E952A;
        private Border origBorder;
        private Icon sortIcon;

        FlatTableCellHeaderRenderer(TableCellRenderer tableCellRenderer) {
            this.delegate = tableCellRenderer;
        }

        @Override
        public Component getTableCellRendererComponent(JTable jTable, Object object, boolean bl, boolean bl2, int n2, int n3) {
            Component component = this.delegate.getTableCellRendererComponent(jTable, object, bl, bl2, n2, n3);
            if (!(component instanceof JLabel)) {
                return component;
            }
            this.l = (JLabel)component;
            if (FlatTableHeaderUI.this.sortIconPosition == ((int)869425776L ^ 0x33D26271) << 1) {
                if (this.oldHorizontalTextPosition < 0) {
                    this.oldHorizontalTextPosition = this.l.getHorizontalTextPosition();
                }
                this.l.setHorizontalTextPosition((int)((long)-942877032 ^ (long)-942877031) << 2);
            } else {
                this.sortIcon = this.l.getIcon();
                this.origBorder = this.l.getBorder();
                this.l.setIcon(null);
                this.l.setBorder(this);
            }
            return this.l;
        }

        void reset() {
            if (this.l != null && FlatTableHeaderUI.this.sortIconPosition == ((int)-1957226051L ^ 0x8B5719BC) << 1 && this.oldHorizontalTextPosition >= 0) {
                this.l.setHorizontalTextPosition(this.oldHorizontalTextPosition);
            }
        }

        @Override
        public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
            if (this.origBorder != null) {
                this.origBorder.paintBorder(component, graphics, n2, n3, n4, n5);
            }
            if (this.sortIcon != null) {
                int n6 = n2 + (n4 - this.sortIcon.getIconWidth()) / ((int)((long)-1056490234 ^ (long)-1056490233) << 1);
                int n7 = FlatTableHeaderUI.this.sortIconPosition == (int)((long)1990857039 ^ (long)1990857038) ? n3 + UIScale.scale((int)1676810370L ^ 0x63F21883) : n3 + n5 - this.sortIcon.getIconHeight() - ((int)-340294604L ^ 0xEBB78435) - (int)(1.0f * UIScale.getUserScaleFactor());
                this.sortIcon.paintIcon(component, graphics, n6, n7);
            }
        }

        @Override
        public Insets getBorderInsets(Component component) {
            return this.origBorder != null ? this.origBorder.getBorderInsets(component) : new Insets((int)1659276794L ^ 0x62E68DFA, (int)((long)-1198614189 ^ (long)-1198614189), (int)-1640107124L ^ 0x9E3DF38C, (int)((long)-14438593 ^ (long)-14438593));
        }

        @Override
        public boolean isBorderOpaque() {
            return (this.origBorder != null ? this.origBorder.isBorderOpaque() : (int)2037752166L ^ 0x7975A166) != 0;
        }
    }
}

