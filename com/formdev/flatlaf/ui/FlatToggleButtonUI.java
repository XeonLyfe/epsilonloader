/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

public class FlatToggleButtonUI
extends FlatButtonUI {
    protected int tabUnderlineHeight;
    protected Color tabUnderlineColor;
    protected Color tabDisabledUnderlineColor;
    protected Color tabSelectedBackground;
    protected Color tabHoverBackground;
    protected Color tabFocusBackground;
    private boolean defaults_initialized = (int)506826130L ^ 0x1E358D92;

    public static ComponentUI createUI(JComponent jComponent) {
        return FlatUIUtils.createSharedUI(FlatToggleButtonUI.class, FlatToggleButtonUI::new);
    }

    @Override
    protected String getPropertyPrefix() {
        return "ToggleButton.";
    }

    @Override
    protected void installDefaults(AbstractButton abstractButton) {
        super.installDefaults(abstractButton);
        if (!this.defaults_initialized) {
            this.tabUnderlineHeight = UIManager.getInt("ToggleButton.tab.underlineHeight");
            this.tabUnderlineColor = UIManager.getColor("ToggleButton.tab.underlineColor");
            this.tabDisabledUnderlineColor = UIManager.getColor("ToggleButton.tab.disabledUnderlineColor");
            this.tabSelectedBackground = UIManager.getColor("ToggleButton.tab.selectedBackground");
            this.tabHoverBackground = UIManager.getColor("ToggleButton.tab.hoverBackground");
            this.tabFocusBackground = UIManager.getColor("ToggleButton.tab.focusBackground");
            this.defaults_initialized = (int)1964860586L ^ 0x751D64AB;
        }
    }

    @Override
    protected void uninstallDefaults(AbstractButton abstractButton) {
        super.uninstallDefaults(abstractButton);
        this.defaults_initialized = (int)((long)-765097267 ^ (long)-765097267);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void propertyChange(AbstractButton var1_1, PropertyChangeEvent var2_2) {
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

    static boolean isTabButton(Component component) {
        return (component instanceof JToggleButton && FlatClientProperties.clientPropertyEquals((JToggleButton)component, "JButton.buttonType", "tab") ? (int)-722074279L ^ 0xD4F60558 : (int)321463761L ^ 0x132925D1) != 0;
    }

    @Override
    protected void paintBackground(Graphics graphics, JComponent jComponent) {
        if (FlatToggleButtonUI.isTabButton(jComponent)) {
            Color color;
            Color color2;
            int n2 = jComponent.getHeight();
            int n3 = jComponent.getWidth();
            boolean bl = ((AbstractButton)jComponent).isSelected();
            Color color3 = color2 = bl ? FlatClientProperties.clientPropertyColor(jComponent, "JToggleButton.tab.selectedBackground", this.tabSelectedBackground) : null;
            if (color2 == null && this.isCustomBackground(color = jComponent.getBackground())) {
                color2 = color;
            }
            if ((color = FlatToggleButtonUI.buttonStateColor(jComponent, color2, null, this.tabFocusBackground, this.tabHoverBackground, null)) != null) {
                graphics.setColor(color);
                graphics.fillRect((int)-1601831713L ^ 0xA085FCDF, (int)((long)-954923455 ^ (long)-954923455), n3, n2);
            }
            if (bl) {
                int n4 = UIScale.scale(FlatClientProperties.clientPropertyInt(jComponent, "JToggleButton.tab.underlineHeight", this.tabUnderlineHeight));
                graphics.setColor(jComponent.isEnabled() ? FlatClientProperties.clientPropertyColor(jComponent, "JToggleButton.tab.underlineColor", this.tabUnderlineColor) : this.tabDisabledUnderlineColor);
                graphics.fillRect((int)((long)-2141377514 ^ (long)-2141377514), n2 - n4, n3, n4);
            }
        } else {
            super.paintBackground(graphics, jComponent);
        }
    }
}

