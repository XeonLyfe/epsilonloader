/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class FlatProgressBarUI
extends BasicProgressBarUI {
    protected int arc;
    protected Dimension horizontalSize;
    protected Dimension verticalSize;
    private PropertyChangeListener propertyChangeListener;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatProgressBarUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installProperty(this.progressBar, "opaque", ((int)-2047015627L ^ 0x85FD0535) != 0);
        this.arc = UIManager.getInt("ProgressBar.arc");
        this.horizontalSize = UIManager.getDimension("ProgressBar.horizontalSize");
        this.verticalSize = UIManager.getDimension("ProgressBar.verticalSize");
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        this.propertyChangeListener = var1_1 -> {
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
        this.progressBar.addPropertyChangeListener(this.propertyChangeListener);
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        this.progressBar.removePropertyChangeListener(this.propertyChangeListener);
        this.propertyChangeListener = null;
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        Dimension dimension = super.getPreferredSize(jComponent);
        if (this.progressBar.isStringPainted() || FlatClientProperties.clientPropertyBoolean(jComponent, "JProgressBar.largeHeight", ((int)-1493486354L ^ 0xA6FB34EE) != 0)) {
            Insets insets = this.progressBar.getInsets();
            FontMetrics fontMetrics = this.progressBar.getFontMetrics(this.progressBar.getFont());
            if (this.progressBar.getOrientation() == 0) {
                dimension.height = Math.max(fontMetrics.getHeight() + insets.top + insets.bottom, this.getPreferredInnerHorizontal().height);
            } else {
                dimension.width = Math.max(fontMetrics.getHeight() + insets.left + insets.right, this.getPreferredInnerVertical().width);
            }
        }
        return dimension;
    }

    @Override
    protected Dimension getPreferredInnerHorizontal() {
        return UIScale.scale(this.horizontalSize);
    }

    @Override
    protected Dimension getPreferredInnerVertical() {
        return UIScale.scale(this.verticalSize);
    }

    @Override
    public void update(Graphics graphics, JComponent jComponent) {
        if (jComponent.isOpaque()) {
            FlatUIUtils.paintParentBackground(graphics, jComponent);
        }
        this.paint(graphics, jComponent);
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        int n2;
        Insets insets = this.progressBar.getInsets();
        int n3 = insets.left;
        int n4 = insets.top;
        int n5 = this.progressBar.getWidth() - (insets.right + insets.left);
        int n6 = this.progressBar.getHeight() - (insets.top + insets.bottom);
        if (n5 <= 0 || n6 <= 0) {
            return;
        }
        int n7 = n2 = this.progressBar.getOrientation() == 0 ? (int)240341050L ^ 0xE53503B : (int)((long)1236331084 ^ (long)1236331084);
        int n8 = FlatClientProperties.clientPropertyBoolean(jComponent, "JProgressBar.square", (boolean)((long)-30457980 ^ (long)-30457980)) ? (int)((long)-23432070 ^ (long)-23432070) : Math.min(UIScale.scale(this.arc), n2 != 0 ? n6 : n5);
        Object[] arrobject = FlatUIUtils.setRenderingHints(graphics);
        RoundRectangle2D.Float float_ = new RoundRectangle2D.Float(n3, n4, n5, n6, n8, n8);
        graphics.setColor(this.progressBar.getBackground());
        ((Graphics2D)graphics).fill(float_);
        int n9 = (int)-1123689104L ^ 0xBD05DD70;
        if (this.progressBar.isIndeterminate()) {
            this.boxRect = this.getBox(this.boxRect);
            if (this.boxRect != null) {
                graphics.setColor(this.progressBar.getForeground());
                ((Graphics2D)graphics).fill(new RoundRectangle2D.Float(this.boxRect.x, this.boxRect.y, this.boxRect.width, this.boxRect.height, n8, n8));
            }
        } else {
            n9 = this.getAmountFull(insets, n5, n6);
            RoundRectangle2D.Float float_2 = n2 != 0 ? new RoundRectangle2D.Float(jComponent.getComponentOrientation().isLeftToRight() ? (float)n3 : (float)(n3 + (n5 - n9)), n4, n9, n6, n8, n8) : new RoundRectangle2D.Float(n3, n4 + (n6 - n9), n5, n9, n8, n8);
            graphics.setColor(this.progressBar.getForeground());
            if (n9 < (n2 != 0 ? n6 : n5)) {
                Area area = new Area(float_);
                area.intersect(new Area(float_2));
                ((Graphics2D)graphics).fill(area);
            } else {
                ((Graphics2D)graphics).fill(float_2);
            }
        }
        FlatUIUtils.resetRenderingHints(graphics, arrobject);
        if (this.progressBar.isStringPainted()) {
            this.paintString(graphics, n3, n4, n5, n6, n9, insets);
        }
    }

    @Override
    protected void paintString(Graphics graphics, int n2, int n3, int n4, int n5, int n6, Insets insets) {
        super.paintString(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)graphics), n2, n3, n4, n5, n6, insets);
    }

    @Override
    protected void setAnimationIndex(int n2) {
        super.setAnimationIndex(n2);
        double d2 = UIScale.getSystemScaleFactor(this.progressBar.getGraphicsConfiguration());
        if ((double)((int)d2) != d2) {
            this.progressBar.repaint();
        }
    }
}

