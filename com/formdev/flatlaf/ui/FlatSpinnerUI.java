/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatArrowButton;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSpinnerUI;

public class FlatSpinnerUI
extends BasicSpinnerUI {
    private Handler handler;
    protected int minimumWidth;
    protected String buttonStyle;
    protected String arrowType;
    protected boolean isIntelliJTheme;
    protected Color borderColor;
    protected Color disabledBorderColor;
    protected Color disabledBackground;
    protected Color disabledForeground;
    protected Color buttonBackground;
    protected Color buttonArrowColor;
    protected Color buttonDisabledArrowColor;
    protected Color buttonHoverArrowColor;
    protected Color buttonPressedArrowColor;
    protected Insets padding;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatSpinnerUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installProperty(this.spinner, "opaque", (boolean)((long)-311212105 ^ (long)-311212105));
        this.minimumWidth = UIManager.getInt("Component.minimumWidth");
        this.buttonStyle = UIManager.getString("Spinner.buttonStyle");
        this.arrowType = UIManager.getString("Component.arrowType");
        this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
        this.borderColor = UIManager.getColor("Component.borderColor");
        this.disabledBorderColor = UIManager.getColor("Component.disabledBorderColor");
        this.disabledBackground = UIManager.getColor("Spinner.disabledBackground");
        this.disabledForeground = UIManager.getColor("Spinner.disabledForeground");
        this.buttonBackground = UIManager.getColor("Spinner.buttonBackground");
        this.buttonArrowColor = UIManager.getColor("Spinner.buttonArrowColor");
        this.buttonDisabledArrowColor = UIManager.getColor("Spinner.buttonDisabledArrowColor");
        this.buttonHoverArrowColor = UIManager.getColor("Spinner.buttonHoverArrowColor");
        this.buttonPressedArrowColor = UIManager.getColor("Spinner.buttonPressedArrowColor");
        this.padding = UIManager.getInsets("Spinner.padding");
        this.padding = UIScale.scale(this.padding);
        MigLayoutVisualPadding.install(this.spinner);
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.borderColor = null;
        this.disabledBorderColor = null;
        this.disabledBackground = null;
        this.disabledForeground = null;
        this.buttonBackground = null;
        this.buttonArrowColor = null;
        this.buttonDisabledArrowColor = null;
        this.buttonHoverArrowColor = null;
        this.buttonPressedArrowColor = null;
        this.padding = null;
        MigLayoutVisualPadding.uninstall(this.spinner);
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        this.addEditorFocusListener(this.spinner.getEditor());
        this.spinner.addFocusListener(this.getHandler());
        this.spinner.addPropertyChangeListener(this.getHandler());
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        this.removeEditorFocusListener(this.spinner.getEditor());
        this.spinner.removeFocusListener(this.getHandler());
        this.spinner.removePropertyChangeListener(this.getHandler());
        this.handler = null;
    }

    private Handler getHandler() {
        if (this.handler == null) {
            this.handler = new Handler();
        }
        return this.handler;
    }

    @Override
    protected JComponent createEditor() {
        JComponent jComponent = super.createEditor();
        jComponent.setOpaque(((int)2070565509L ^ 0x7B6A5285) != 0);
        JTextField jTextField = FlatSpinnerUI.getEditorTextField(jComponent);
        if (jTextField != null) {
            jTextField.setOpaque((boolean)((long)-1245530051 ^ (long)-1245530051));
        }
        this.updateEditorColors();
        return jComponent;
    }

    @Override
    protected void replaceEditor(JComponent jComponent, JComponent jComponent2) {
        super.replaceEditor(jComponent, jComponent2);
        this.removeEditorFocusListener(jComponent);
        this.addEditorFocusListener(jComponent2);
        this.updateEditorColors();
    }

    private void addEditorFocusListener(JComponent jComponent) {
        JTextField jTextField = FlatSpinnerUI.getEditorTextField(jComponent);
        if (jTextField != null) {
            jTextField.addFocusListener(this.getHandler());
        }
    }

    private void removeEditorFocusListener(JComponent jComponent) {
        JTextField jTextField = FlatSpinnerUI.getEditorTextField(jComponent);
        if (jTextField != null) {
            jTextField.removeFocusListener(this.getHandler());
        }
    }

    private void updateEditorColors() {
        JTextField jTextField = FlatSpinnerUI.getEditorTextField(this.spinner.getEditor());
        if (jTextField != null) {
            jTextField.setForeground(FlatUIUtils.nonUIResource(this.getForeground(((int)2081825663L ^ 0x7C16237E) != 0)));
            jTextField.setDisabledTextColor(FlatUIUtils.nonUIResource(this.getForeground(((int)-1124204102L ^ 0xBCFE01BA) != 0)));
        }
    }

    private static JTextField getEditorTextField(JComponent jComponent) {
        return jComponent instanceof JSpinner.DefaultEditor ? ((JSpinner.DefaultEditor)jComponent).getTextField() : null;
    }

    protected Color getBackground(boolean bl) {
        return bl ? this.spinner.getBackground() : (this.isIntelliJTheme ? FlatUIUtils.getParentBackground(this.spinner) : this.disabledBackground);
    }

    protected Color getForeground(boolean bl) {
        return bl ? this.spinner.getForeground() : this.disabledForeground;
    }

    @Override
    protected LayoutManager createLayout() {
        return this.getHandler();
    }

    @Override
    protected Component createNextButton() {
        return this.createArrowButton((int)-1521112115L ^ 0xA555ABCC, "Spinner.nextButton");
    }

    @Override
    protected Component createPreviousButton() {
        return this.createArrowButton((int)1253968013L ^ 0x4ABE0888, "Spinner.previousButton");
    }

    private Component createArrowButton(int n2, String string) {
        FlatArrowButton flatArrowButton = new FlatArrowButton(n2, this.arrowType, this.buttonArrowColor, this.buttonDisabledArrowColor, this.buttonHoverArrowColor, null, this.buttonPressedArrowColor, null);
        flatArrowButton.setName(string);
        flatArrowButton.setYOffset(n2 == ((int)-551561569L ^ 0xDF1FD69E) ? (int)((long)-1958881195 ^ (long)-1958881196) : (int)1494660806L ^ 0xA6E94939);
        if (n2 == (int)((long)-1562848445 ^ (long)-1562848446)) {
            this.installNextButtonListeners(flatArrowButton);
        } else {
            this.installPreviousButtonListeners(flatArrowButton);
        }
        return flatArrowButton;
    }

    @Override
    public void update(Graphics graphics, JComponent jComponent) {
        float f2 = FlatUIUtils.getBorderFocusWidth(jComponent);
        float f3 = FlatUIUtils.getBorderArc(jComponent);
        if (jComponent.isOpaque() && (f2 > 0.0f || f3 > 0.0f)) {
            FlatUIUtils.paintParentBackground(graphics, jComponent);
        }
        Graphics2D graphics2D = (Graphics2D)graphics;
        Object[] arrobject = FlatUIUtils.setRenderingHints(graphics2D);
        int n2 = jComponent.getWidth();
        int n3 = jComponent.getHeight();
        boolean bl = this.spinner.isEnabled();
        graphics2D.setColor(this.getBackground(bl));
        FlatUIUtils.paintComponentBackground(graphics2D, (int)((long)-329543709 ^ (long)-329543709), (int)10756519L ^ 0xA421A7, n2, n3, f2, f3);
        int n4 = !"none".equals(this.buttonStyle) ? (int)((long)2032036300 ^ (long)2032036301) : (int)((long)-1916954659 ^ (long)-1916954659);
        Handler handler = this.getHandler();
        if (n4 != 0 && (handler.nextButton != null || handler.previousButton != null)) {
            Component component = handler.nextButton != null ? handler.nextButton : handler.previousButton;
            int n5 = component.getX();
            int n6 = component.getWidth();
            boolean bl2 = this.spinner.getComponentOrientation().isLeftToRight();
            if (bl) {
                graphics2D.setColor(this.buttonBackground);
                Shape shape = graphics2D.getClip();
                if (bl2) {
                    graphics2D.clipRect(n5, (int)((long)-230821779 ^ (long)-230821779), n2 - n5, n3);
                } else {
                    graphics2D.clipRect((int)-627192441L ^ 0xDA9DCD87, (int)((long)1788200249 ^ (long)1788200249), n5 + n6, n3);
                }
                FlatUIUtils.paintComponentBackground(graphics2D, (int)-889060320L ^ 0xCB020420, (int)((long)982525336 ^ (long)982525336), n2, n3, f2, f3);
                graphics2D.setClip(shape);
            }
            graphics2D.setColor(bl ? this.borderColor : this.disabledBorderColor);
            float f4 = UIScale.scale(1.0f);
            float f5 = bl2 ? (float)n5 : (float)(n5 + n6) - f4;
            graphics2D.fill(new Rectangle2D.Float(f5, f2, f4, (float)(n3 - (int)((long)-1983567725 ^ (long)-1983567726)) - f2 * 2.0f));
        }
        this.paint(graphics, jComponent);
        FlatUIUtils.resetRenderingHints(graphics, arrobject);
    }

    static /* synthetic */ void access$1000(FlatSpinnerUI flatSpinnerUI) {
        flatSpinnerUI.updateEditorColors();
    }

    static /* synthetic */ JSpinner access$1100(FlatSpinnerUI flatSpinnerUI) {
        return flatSpinnerUI.spinner;
    }

    static /* synthetic */ JSpinner access$1200(FlatSpinnerUI flatSpinnerUI) {
        return flatSpinnerUI.spinner;
    }

    private class Handler
    implements LayoutManager,
    FocusListener,
    PropertyChangeListener {
        private Component editor = null;
        private Component nextButton;
        private Component previousButton;

        private Handler() {
        }

        /*
         * Exception decompiling
         */
        @Override
        public void addLayoutComponent(String var1_1, Component var2_2) {
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
        public void removeLayoutComponent(Component component) {
            if (component == this.editor) {
                this.editor = null;
            } else if (component == this.nextButton) {
                this.nextButton = null;
            } else if (component == this.previousButton) {
                this.previousButton = null;
            }
        }

        @Override
        public Dimension preferredLayoutSize(Container container) {
            Insets insets = container.getInsets();
            Dimension dimension = this.editor != null ? this.editor.getPreferredSize() : new Dimension((int)((long)-2090920336 ^ (long)-2090920336), (int)((long)-1931942938 ^ (long)-1931942938));
            int n2 = FlatUIUtils.minimumWidth(FlatSpinnerUI.this.spinner, FlatSpinnerUI.this.minimumWidth);
            int n3 = dimension.height + FlatSpinnerUI.this.padding.top + FlatSpinnerUI.this.padding.bottom;
            float f2 = FlatUIUtils.getBorderFocusWidth(FlatSpinnerUI.this.spinner);
            return new Dimension(Math.max(insets.left + insets.right + dimension.width + FlatSpinnerUI.this.padding.left + FlatSpinnerUI.this.padding.right + n3, UIScale.scale(n2) + Math.round(f2 * 2.0f)), insets.top + insets.bottom + n3);
        }

        @Override
        public Dimension minimumLayoutSize(Container container) {
            return this.preferredLayoutSize(container);
        }

        @Override
        public void layoutContainer(Container container) {
            int n2;
            Dimension dimension = container.getSize();
            Insets insets = container.getInsets();
            Rectangle rectangle = FlatUIUtils.subtractInsets(new Rectangle(dimension), insets);
            if (this.nextButton == null && this.previousButton == null) {
                if (this.editor != null) {
                    this.editor.setBounds(FlatUIUtils.subtractInsets(rectangle, FlatSpinnerUI.this.padding));
                }
                return;
            }
            Rectangle rectangle2 = new Rectangle(rectangle);
            Rectangle rectangle3 = new Rectangle(rectangle);
            rectangle3.width = n2 = container.getPreferredSize().height - insets.top - insets.bottom;
            if (container.getComponentOrientation().isLeftToRight()) {
                rectangle2.width -= n2;
                rectangle3.x += rectangle2.width;
            } else {
                rectangle2.x += n2;
                rectangle2.width -= n2;
            }
            if (this.editor != null) {
                this.editor.setBounds(FlatUIUtils.subtractInsets(rectangle2, FlatSpinnerUI.this.padding));
            }
            int n3 = rectangle3.height / ((int)((long)925947788 ^ (long)925947789) << 1) + rectangle3.height % (((int)-40693452L ^ 0xFD931135) << 1);
            if (this.nextButton != null) {
                this.nextButton.setBounds(rectangle3.x, rectangle3.y, rectangle3.width, n3);
            }
            if (this.previousButton != null) {
                int n4 = rectangle3.y + rectangle3.height - n3;
                this.previousButton.setBounds(rectangle3.x, n4, rectangle3.width, n3);
            }
        }

        @Override
        public void focusGained(FocusEvent focusEvent) {
            JTextField jTextField;
            FlatSpinnerUI.this.spinner.repaint();
            if (focusEvent.getComponent() == FlatSpinnerUI.this.spinner && (jTextField = FlatSpinnerUI.getEditorTextField(FlatSpinnerUI.this.spinner.getEditor())) != null) {
                jTextField.requestFocusInWindow();
            }
        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            FlatSpinnerUI.this.spinner.repaint();
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
    }
}

