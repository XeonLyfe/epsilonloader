/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatEditorPaneUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.HiDPIUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.JTextComponent;

public class FlatTextAreaUI
extends BasicTextAreaUI {
    protected int minimumWidth;
    protected boolean isIntelliJTheme;
    protected Color background;
    protected Color disabledBackground;
    protected Color inactiveBackground;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatTextAreaUI();
    }

    @Override
    public void installUI(JComponent jComponent) {
        super.installUI(jComponent);
        this.updateBackground();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        this.minimumWidth = UIManager.getInt("Component.minimumWidth");
        this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
        this.background = UIManager.getColor("TextArea.background");
        this.disabledBackground = UIManager.getColor("TextArea.disabledBackground");
        this.inactiveBackground = UIManager.getColor("TextArea.inactiveBackground");
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.background = null;
        this.disabledBackground = null;
        this.inactiveBackground = null;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void propertyChange(PropertyChangeEvent var1_1) {
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

    private void updateBackground() {
        Color color;
        JTextComponent jTextComponent = this.getComponent();
        Color color2 = jTextComponent.getBackground();
        if (!(color2 instanceof UIResource)) {
            return;
        }
        if (color2 != this.background && color2 != this.disabledBackground && color2 != this.inactiveBackground) {
            return;
        }
        Color color3 = !jTextComponent.isEnabled() ? this.disabledBackground : (color = !jTextComponent.isEditable() ? this.inactiveBackground : this.background);
        if (color != color2) {
            jTextComponent.setBackground(color);
        }
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        return this.applyMinimumWidth(jComponent, super.getPreferredSize(jComponent));
    }

    @Override
    public Dimension getMinimumSize(JComponent jComponent) {
        return this.applyMinimumWidth(jComponent, super.getMinimumSize(jComponent));
    }

    private Dimension applyMinimumWidth(JComponent jComponent, Dimension dimension) {
        if (jComponent instanceof JTextArea && ((JTextArea)jComponent).getColumns() > 0) {
            return dimension;
        }
        return FlatEditorPaneUI.applyMinimumWidth(jComponent, dimension, this.minimumWidth);
    }

    @Override
    protected void paintSafely(Graphics graphics) {
        super.paintSafely(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)graphics));
    }

    @Override
    protected void paintBackground(Graphics graphics) {
        JTextComponent jTextComponent = this.getComponent();
        if (this.isIntelliJTheme && (!jTextComponent.isEnabled() || !jTextComponent.isEditable()) && jTextComponent.getBackground() instanceof UIResource) {
            FlatUIUtils.paintParentBackground(graphics, jTextComponent);
            return;
        }
        super.paintBackground(graphics);
    }
}

