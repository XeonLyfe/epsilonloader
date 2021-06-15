/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatCaret;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.JavaCompatibility;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

public class FlatTextFieldUI
extends BasicTextFieldUI {
    protected int minimumWidth;
    protected boolean isIntelliJTheme;
    protected Color placeholderForeground;
    private FocusListener focusListener;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatTextFieldUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        String string = this.getPropertyPrefix();
        this.minimumWidth = UIManager.getInt("Component.minimumWidth");
        this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
        this.placeholderForeground = UIManager.getColor(string + ".placeholderForeground");
        LookAndFeel.installProperty(this.getComponent(), "opaque", ((int)-1925291513L ^ 0x8D3E6207) != 0);
        MigLayoutVisualPadding.install(this.getComponent());
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.placeholderForeground = null;
        MigLayoutVisualPadding.uninstall(this.getComponent());
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        this.focusListener = new FlatUIUtils.RepaintFocusListener(this.getComponent());
        this.getComponent().addFocusListener(this.focusListener);
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        this.getComponent().removeFocusListener(this.focusListener);
        this.focusListener = null;
    }

    @Override
    protected Caret createCaret() {
        return new FlatCaret(UIManager.getString("TextComponent.selectAllOnFocusPolicy"), UIManager.getBoolean("TextComponent.selectAllOnMouseClick"));
    }

    @Override
    protected void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        super.propertyChange(propertyChangeEvent);
        FlatTextFieldUI.propertyChange(this.getComponent(), propertyChangeEvent);
    }

    /*
     * Exception decompiling
     */
    static void propertyChange(JTextComponent var0, PropertyChangeEvent var1_1) {
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
    protected void paintSafely(Graphics graphics) {
        FlatTextFieldUI.paintBackground(graphics, this.getComponent(), this.isIntelliJTheme);
        FlatTextFieldUI.paintPlaceholder(graphics, this.getComponent(), this.placeholderForeground);
        super.paintSafely(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)graphics));
    }

    @Override
    protected void paintBackground(Graphics graphics) {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void paintBackground(Graphics graphics, JTextComponent jTextComponent, boolean bl) {
        if (!jTextComponent.isOpaque() && FlatUIUtils.getOutsideFlatBorder(jTextComponent) == null && FlatUIUtils.hasOpaqueBeenExplicitlySet(jTextComponent)) {
            return;
        }
        float f2 = FlatUIUtils.getBorderFocusWidth(jTextComponent);
        float f3 = FlatUIUtils.getBorderArc(jTextComponent);
        if (jTextComponent.isOpaque() && (f2 > 0.0f || f3 > 0.0f)) {
            FlatUIUtils.paintParentBackground(graphics, jTextComponent);
        }
        Graphics2D graphics2D = (Graphics2D)graphics.create();
        try {
            FlatUIUtils.setRenderingHints(graphics2D);
            Color color = jTextComponent.getBackground();
            graphics2D.setColor(!(color instanceof UIResource) ? color : (bl && (!jTextComponent.isEnabled() || !jTextComponent.isEditable()) ? FlatUIUtils.getParentBackground(jTextComponent) : color));
            FlatUIUtils.paintComponentBackground(graphics2D, (int)534262499L ^ 0x1FD832E3, (int)-1814140034L ^ 0x93DE6B7E, jTextComponent.getWidth(), jTextComponent.getHeight(), f2, f3);
        }
        finally {
            graphics2D.dispose();
        }
    }

    static void paintPlaceholder(Graphics graphics, JTextComponent jTextComponent, Color color) {
        if (jTextComponent.getDocument().getLength() > 0) {
            return;
        }
        Container container = jTextComponent.getParent();
        JComponent jComponent = container instanceof JComboBox ? (JComboBox)container : jTextComponent;
        Object object = jComponent.getClientProperty("JTextField.placeholderText");
        if (!(object instanceof String)) {
            return;
        }
        Insets insets = jTextComponent.getInsets();
        FontMetrics fontMetrics = jTextComponent.getFontMetrics(jTextComponent.getFont());
        int n2 = insets.left;
        int n3 = insets.top + fontMetrics.getAscent() + (jTextComponent.getHeight() - insets.top - insets.bottom - fontMetrics.getHeight()) / ((int)((long)1440824177 ^ (long)1440824176) << 1);
        graphics.setColor(color);
        String string = JavaCompatibility.getClippedString(jComponent, fontMetrics, (String)object, jTextComponent.getWidth() - insets.left - insets.right);
        FlatUIUtils.drawString(jTextComponent, graphics, string, n2, n3);
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        return FlatTextFieldUI.applyMinimumWidth(jComponent, super.getPreferredSize(jComponent), this.minimumWidth);
    }

    @Override
    public Dimension getMinimumSize(JComponent jComponent) {
        return FlatTextFieldUI.applyMinimumWidth(jComponent, super.getMinimumSize(jComponent), this.minimumWidth);
    }

    static Dimension applyMinimumWidth(JComponent jComponent, Dimension dimension, int n2) {
        if (jComponent instanceof JTextField && ((JTextField)jComponent).getColumns() > 0) {
            return dimension;
        }
        Container container = jComponent.getParent();
        if (container instanceof JComboBox || container instanceof JSpinner || container != null && container.getParent() instanceof JSpinner) {
            return dimension;
        }
        n2 = FlatUIUtils.minimumWidth(jComponent, n2);
        float f2 = FlatUIUtils.getBorderFocusWidth(jComponent);
        dimension.width = Math.max(dimension.width, UIScale.scale(n2) + Math.round(f2 * 2.0f));
        return dimension;
    }
}

