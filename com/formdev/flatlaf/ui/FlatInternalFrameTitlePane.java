/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.ScaledImageIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class FlatInternalFrameTitlePane
extends BasicInternalFrameTitlePane {
    private JLabel titleLabel;
    private JPanel buttonPanel;

    public FlatInternalFrameTitlePane(JInternalFrame jInternalFrame) {
        super(jInternalFrame);
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installBorder(this, "InternalFrameTitlePane.border");
    }

    @Override
    protected PropertyChangeListener createPropertyChangeListener() {
        return new FlatPropertyChangeHandler();
    }

    @Override
    protected LayoutManager createLayout() {
        return new BorderLayout(UIScale.scale(((int)464629394L ^ 0x1BB1AE93) << 2), (int)((long)1356637373 ^ (long)1356637373));
    }

    @Override
    protected void createButtons() {
        super.createButtons();
        this.iconButton.setContentAreaFilled(((int)1759248288L ^ 0x68DBFFA0) != 0);
        this.maxButton.setContentAreaFilled(((int)824461920L ^ 0x31244A60) != 0);
        this.closeButton.setContentAreaFilled(((int)1907533177L ^ 0x71B2A579) != 0);
        Border border = BorderFactory.createEmptyBorder();
        this.iconButton.setBorder(border);
        this.maxButton.setBorder(border);
        this.closeButton.setBorder(border);
        this.updateButtonsVisibility();
    }

    @Override
    protected void addSubComponents() {
        this.titleLabel = new JLabel(this.frame.getTitle());
        this.titleLabel.setFont(FlatUIUtils.nonUIResource(this.getFont()));
        this.titleLabel.setMinimumSize(new Dimension(UIScale.scale(((int)614786073L ^ 0x24A4E418) << 5), (int)-939443053L ^ 0xC8013C92));
        this.updateFrameIcon();
        this.updateColors();
        this.buttonPanel = new JPanel(){

            @Override
            public Dimension getPreferredSize() {
                Dimension dimension = super.getPreferredSize();
                int n2 = dimension.height;
                if (!FlatInternalFrameTitlePane.this.iconButton.isVisible()) {
                    n2 = Math.max(n2, ((FlatInternalFrameTitlePane)FlatInternalFrameTitlePane.this).iconButton.getPreferredSize().height);
                }
                if (!FlatInternalFrameTitlePane.this.maxButton.isVisible()) {
                    n2 = Math.max(n2, ((FlatInternalFrameTitlePane)FlatInternalFrameTitlePane.this).maxButton.getPreferredSize().height);
                }
                if (!FlatInternalFrameTitlePane.this.closeButton.isVisible()) {
                    n2 = Math.max(n2, ((FlatInternalFrameTitlePane)FlatInternalFrameTitlePane.this).closeButton.getPreferredSize().height);
                }
                return new Dimension(dimension.width, n2);
            }
        };
        this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, ((int)-1217257126L ^ 0xB772215B) << 1));
        this.buttonPanel.setOpaque((boolean)((long)-1149400879 ^ (long)-1149400879));
        this.buttonPanel.add(this.iconButton);
        this.buttonPanel.add(this.maxButton);
        this.buttonPanel.add(this.closeButton);
        this.add((Component)this.titleLabel, "Center");
        this.add((Component)this.buttonPanel, "After");
    }

    protected void updateFrameIcon() {
        Icon icon = this.frame.getFrameIcon();
        if (icon != null && (icon.getIconWidth() == 0 || icon.getIconHeight() == 0)) {
            icon = null;
        } else if (icon instanceof ImageIcon) {
            icon = new ScaledImageIcon((ImageIcon)icon);
        }
        this.titleLabel.setIcon(icon);
    }

    protected void updateColors() {
        Color color = FlatUIUtils.nonUIResource(this.frame.isSelected() ? this.selectedTitleColor : this.notSelectedTitleColor);
        Color color2 = FlatUIUtils.nonUIResource(this.frame.isSelected() ? this.selectedTextColor : this.notSelectedTextColor);
        this.titleLabel.setForeground(color2);
        this.iconButton.setBackground(color);
        this.iconButton.setForeground(color2);
        this.maxButton.setBackground(color);
        this.maxButton.setForeground(color2);
        this.closeButton.setBackground(color);
        this.closeButton.setForeground(color2);
    }

    protected void updateButtonsVisibility() {
        this.iconButton.setVisible(this.frame.isIconifiable());
        this.maxButton.setVisible(this.frame.isMaximizable());
        this.closeButton.setVisible(this.frame.isClosable());
    }

    @Override
    protected void assembleSystemMenu() {
    }

    @Override
    protected void showSystemMenu() {
    }

    @Override
    public void paintComponent(Graphics graphics) {
        this.paintTitleBackground(graphics);
    }

    static /* synthetic */ JInternalFrame access$600(FlatInternalFrameTitlePane flatInternalFrameTitlePane) {
        return flatInternalFrameTitlePane.frame;
    }

    static /* synthetic */ JLabel access$700(FlatInternalFrameTitlePane flatInternalFrameTitlePane) {
        return flatInternalFrameTitlePane.titleLabel;
    }

    static /* synthetic */ void access$800(FlatInternalFrameTitlePane flatInternalFrameTitlePane) {
        flatInternalFrameTitlePane.enableActions();
    }

    static /* synthetic */ JInternalFrame access$900(FlatInternalFrameTitlePane flatInternalFrameTitlePane) {
        return flatInternalFrameTitlePane.frame;
    }

    protected class FlatPropertyChangeHandler
    extends BasicInternalFrameTitlePane.PropertyChangeHandler {
        protected FlatPropertyChangeHandler() {
            super(FlatInternalFrameTitlePane.this);
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

