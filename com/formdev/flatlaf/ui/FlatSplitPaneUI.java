/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatArrowButton;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class FlatSplitPaneUI
extends BasicSplitPaneUI {
    protected String arrowType;
    protected Color oneTouchArrowColor;
    protected Color oneTouchHoverArrowColor;
    protected Color oneTouchPressedArrowColor;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatSplitPaneUI();
    }

    @Override
    protected void installDefaults() {
        this.arrowType = UIManager.getString("Component.arrowType");
        this.oneTouchArrowColor = UIManager.getColor("SplitPaneDivider.oneTouchArrowColor");
        this.oneTouchHoverArrowColor = UIManager.getColor("SplitPaneDivider.oneTouchHoverArrowColor");
        this.oneTouchPressedArrowColor = UIManager.getColor("SplitPaneDivider.oneTouchPressedArrowColor");
        super.installDefaults();
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.oneTouchArrowColor = null;
        this.oneTouchHoverArrowColor = null;
        this.oneTouchPressedArrowColor = null;
    }

    @Override
    public BasicSplitPaneDivider createDefaultDivider() {
        return new FlatSplitPaneDivider(this);
    }

    protected class FlatSplitPaneDivider
    extends BasicSplitPaneDivider {
        protected final String style;
        protected final Color gripColor;
        protected final int gripDotCount;
        protected final int gripDotSize;
        protected final int gripGap;

        protected FlatSplitPaneDivider(BasicSplitPaneUI basicSplitPaneUI) {
            super(basicSplitPaneUI);
            this.style = UIManager.getString("SplitPaneDivider.style");
            this.gripColor = UIManager.getColor("SplitPaneDivider.gripColor");
            this.gripDotCount = FlatUIUtils.getUIInt("SplitPaneDivider.gripDotCount", (int)-2021166686L ^ 0x878771A1);
            this.gripDotSize = FlatUIUtils.getUIInt("SplitPaneDivider.gripDotSize", (int)((long)1485096413 ^ (long)1485096414));
            this.gripGap = FlatUIUtils.getUIInt("SplitPaneDivider.gripGap", ((int)1969541147L ^ 0x7564D01A) << 1);
            this.setLayout(new FlatDividerLayout());
        }

        @Override
        public void setDividerSize(int n2) {
            super.setDividerSize(UIScale.scale(n2));
        }

        @Override
        protected JButton createLeftOneTouchButton() {
            return new FlatOneTouchButton(((int)-472743007L ^ 0xE3D283A0) != 0);
        }

        @Override
        protected JButton createRightOneTouchButton() {
            return new FlatOneTouchButton(((int)-121897670L ^ 0xF8BBFD3A) != 0);
        }

        /*
         * Exception decompiling
         */
        @Override
        public void propertyChange(PropertyChangeEvent var1_1) {
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
             * org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:903)
             * org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1015)
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
        public void paint(Graphics graphics) {
            super.paint(graphics);
            if ("plain".equals(this.style)) {
                return;
            }
            Object[] arrobject = FlatUIUtils.setRenderingHints(graphics);
            graphics.setColor(this.gripColor);
            this.paintGrip(graphics, (int)((long)-1049839997 ^ (long)-1049839997), (int)-960864149L ^ 0xC6BA606B, this.getWidth(), this.getHeight());
            FlatUIUtils.resetRenderingHints(graphics, arrobject);
        }

        protected void paintGrip(Graphics graphics, int n2, int n3, int n4, int n5) {
            FlatUIUtils.paintGrip(graphics, n2, n3, n4, n5, (this.splitPane.getOrientation() == 0 ? (int)-1926027386L ^ 0x8D332787 : (int)((long)-1654285388 ^ (long)-1654285388)) != 0, this.gripDotCount, this.gripDotSize, this.gripGap, ((int)1253828471L ^ 0x4ABBE776) != 0);
        }

        protected boolean isLeftCollapsed() {
            int n2 = this.splitPane.getDividerLocation();
            Insets insets = this.splitPane.getInsets();
            return (this.orientation == 0 ? (n2 == insets.top ? (int)-388426770L ^ 0xE8D913EF : (int)((long)1901805801 ^ (long)1901805801)) : (n2 == insets.left ? (int)-910441723L ^ 0xC9BBC304 : (int)554351184L ^ 0x210ABA50)) != 0;
        }

        protected boolean isRightCollapsed() {
            int n2 = this.splitPane.getDividerLocation();
            Insets insets = this.splitPane.getInsets();
            return (this.orientation == 0 ? (n2 == this.splitPane.getHeight() - this.getHeight() - insets.bottom ? (int)((long)1215631839 ^ (long)1215631838) : (int)((long)-1775129963 ^ (long)-1775129963)) : (n2 == this.splitPane.getWidth() - this.getWidth() - insets.right ? (int)2051160501L ^ 0x7A4239B4 : (int)-1774521585L ^ 0x963AF30F)) != 0;
        }

        protected class FlatDividerLayout
        extends BasicSplitPaneDivider.DividerLayout {
            protected FlatDividerLayout() {
                super(FlatSplitPaneDivider.this);
            }

            @Override
            public void layoutContainer(Container container) {
                super.layoutContainer(container);
                if (FlatSplitPaneDivider.this.leftButton == null || FlatSplitPaneDivider.this.rightButton == null || !FlatSplitPaneDivider.this.splitPane.isOneTouchExpandable()) {
                    return;
                }
                int n2 = UIScale.scale(((int)-1364492887L ^ 0xAEAB7DA8) << 2);
                if (FlatSplitPaneDivider.this.orientation == 0) {
                    FlatSplitPaneDivider.this.leftButton.setSize(FlatSplitPaneDivider.this.leftButton.getWidth() + n2, FlatSplitPaneDivider.this.leftButton.getHeight());
                    FlatSplitPaneDivider.this.rightButton.setBounds(FlatSplitPaneDivider.this.leftButton.getX() + FlatSplitPaneDivider.this.leftButton.getWidth(), FlatSplitPaneDivider.this.rightButton.getY(), FlatSplitPaneDivider.this.rightButton.getWidth() + n2, FlatSplitPaneDivider.this.rightButton.getHeight());
                } else {
                    FlatSplitPaneDivider.this.leftButton.setSize(FlatSplitPaneDivider.this.leftButton.getWidth(), FlatSplitPaneDivider.this.leftButton.getHeight() + n2);
                    FlatSplitPaneDivider.this.rightButton.setBounds(FlatSplitPaneDivider.this.rightButton.getX(), FlatSplitPaneDivider.this.leftButton.getY() + FlatSplitPaneDivider.this.leftButton.getHeight(), FlatSplitPaneDivider.this.rightButton.getWidth(), FlatSplitPaneDivider.this.rightButton.getHeight() + n2);
                }
                boolean bl = FlatSplitPaneDivider.this.isLeftCollapsed();
                if (bl) {
                    FlatSplitPaneDivider.this.rightButton.setLocation(FlatSplitPaneDivider.this.leftButton.getLocation());
                }
                FlatSplitPaneDivider.this.leftButton.setVisible((!bl ? (int)-205138027L ^ 0xF3C5D794 : (int)755453565L ^ 0x2D074E7D) != 0);
                FlatSplitPaneDivider.this.rightButton.setVisible((!FlatSplitPaneDivider.this.isRightCollapsed() ? (int)((long)-395279941 ^ (long)-395279942) : (int)1761457493L ^ 0x68FDB555) != 0);
            }
        }

        protected class FlatOneTouchButton
        extends FlatArrowButton {
            protected final boolean left;

            protected FlatOneTouchButton(boolean bl) {
                super((int)((long)2038639226 ^ (long)2038639227), FlatSplitPaneUI.this.arrowType, FlatSplitPaneUI.this.oneTouchArrowColor, null, FlatSplitPaneUI.this.oneTouchHoverArrowColor, null, FlatSplitPaneUI.this.oneTouchPressedArrowColor, null);
                this.setCursor(Cursor.getPredefinedCursor((int)987002382L ^ 0x3AD4760E));
                ToolTipManager.sharedInstance().registerComponent(this);
                this.left = bl;
            }

            @Override
            public int getDirection() {
                return FlatSplitPaneDivider.this.orientation == 0 ? (this.left ? (int)((long)702162279 ^ (long)702162278) : (int)1493855241L ^ 0x590A6C0C) : (this.left ? (int)((long)-2027126950 ^ (long)-2027126947) : (int)((long)1915524630 ^ (long)1915524629));
            }

            @Override
            public String getToolTipText(MouseEvent mouseEvent) {
                String string = FlatSplitPaneDivider.this.orientation == 0 ? (this.left ? (FlatSplitPaneDivider.this.isRightCollapsed() ? "SplitPaneDivider.expandBottomToolTipText" : "SplitPaneDivider.collapseTopToolTipText") : (FlatSplitPaneDivider.this.isLeftCollapsed() ? "SplitPaneDivider.expandTopToolTipText" : "SplitPaneDivider.collapseBottomToolTipText")) : (this.left ? (FlatSplitPaneDivider.this.isRightCollapsed() ? "SplitPaneDivider.expandRightToolTipText" : "SplitPaneDivider.collapseLeftToolTipText") : (FlatSplitPaneDivider.this.isLeftCollapsed() ? "SplitPaneDivider.expandLeftToolTipText" : "SplitPaneDivider.collapseRightToolTipText"));
                Object object = FlatSplitPaneDivider.this.splitPane.getClientProperty(string);
                if (object instanceof String) {
                    return (String)object;
                }
                return UIManager.getString((Object)string, this.getLocale());
            }
        }
    }
}

