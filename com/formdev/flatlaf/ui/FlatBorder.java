/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Paint;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicBorders;

public class FlatBorder
extends BasicBorders.MarginBorder {
    protected final int focusWidth = UIManager.getInt("Component.focusWidth");
    protected final float innerFocusWidth = FlatUIUtils.getUIFloat("Component.innerFocusWidth", 0.0f);
    protected final float innerOutlineWidth = FlatUIUtils.getUIFloat("Component.innerOutlineWidth", 0.0f);
    protected final Color focusColor = UIManager.getColor("Component.focusColor");
    protected final Color borderColor = UIManager.getColor("Component.borderColor");
    protected final Color disabledBorderColor = UIManager.getColor("Component.disabledBorderColor");
    protected final Color focusedBorderColor = UIManager.getColor("Component.focusedBorderColor");
    protected final Color errorBorderColor = UIManager.getColor("Component.error.borderColor");
    protected final Color errorFocusedBorderColor = UIManager.getColor("Component.error.focusedBorderColor");
    protected final Color warningBorderColor = UIManager.getColor("Component.warning.borderColor");
    protected final Color warningFocusedBorderColor = UIManager.getColor("Component.warning.focusedBorderColor");
    protected final Color customBorderColor = UIManager.getColor("Component.custom.borderColor");

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
        Graphics2D graphics2D = (Graphics2D)graphics.create();
        try {
            FlatUIUtils.setRenderingHints(graphics2D);
            float f2 = UIScale.scale((float)this.getFocusWidth(component));
            float f3 = UIScale.scale((float)this.getBorderWidth(component));
            float f4 = UIScale.scale((float)this.getArc(component));
            Color color = this.getOutlineColor(component);
            if (color != null || this.isFocused(component)) {
                float f5;
                float f6 = !this.isCellEditor(component) && !(component instanceof JScrollPane) ? (color != null ? this.innerOutlineWidth : this.getInnerFocusWidth(component)) : (f5 = 0.0f);
                if (f2 > 0.0f || f5 > 0.0f) {
                    graphics2D.setColor(color != null ? color : this.getFocusColor(component));
                    FlatUIUtils.paintComponentOuterBorder(graphics2D, n2, n3, n4, n5, f2, f3 + UIScale.scale(f5), f4);
                }
            }
            graphics2D.setPaint(color != null ? color : this.getBorderColor(component));
            FlatUIUtils.paintComponentBorder(graphics2D, n2, n3, n4, n5, f2, f3, f4);
        }
        finally {
            graphics2D.dispose();
        }
    }

    /*
     * Exception decompiling
     */
    protected Color getOutlineColor(Component var1_1) {
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

    protected Color getFocusColor(Component component) {
        return this.focusColor;
    }

    protected Paint getBorderColor(Component component) {
        return this.isEnabled(component) ? (this.isFocused(component) ? this.focusedBorderColor : this.borderColor) : this.disabledBorderColor;
    }

    protected boolean isEnabled(Component component) {
        if (component instanceof JScrollPane) {
            Component component2;
            JViewport jViewport = ((JScrollPane)component).getViewport();
            Component component3 = component2 = jViewport != null ? jViewport.getView() : null;
            if (component2 != null && !this.isEnabled(component2)) {
                return (int)((long)1880739894 ^ (long)1880739894) != 0;
            }
        }
        return component.isEnabled();
    }

    protected boolean isFocused(Component component) {
        if (component instanceof JScrollPane) {
            Component component2;
            JViewport jViewport = ((JScrollPane)component).getViewport();
            Component component3 = component2 = jViewport != null ? jViewport.getView() : null;
            if (component2 != null) {
                Component component4;
                if (FlatUIUtils.isPermanentFocusOwner(component2)) {
                    return ((int)1457767141L ^ 0x56E3C2E4) != 0;
                }
                if ((component2 instanceof JTable && ((JTable)component2).isEditing() || component2 instanceof JTree && ((JTree)component2).isEditing()) && (component4 = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()) != null) {
                    return SwingUtilities.isDescendingFrom(component4, component2);
                }
            }
            return ((int)-1496011764L ^ 0xA6D4AC0C) != 0;
        }
        if (component instanceof JComboBox && ((JComboBox)component).isEditable()) {
            Component component5 = ((JComboBox)component).getEditor().getEditorComponent();
            return (component5 != null ? FlatUIUtils.isPermanentFocusOwner(component5) : (int)-1198652795L ^ 0xB88E0285) != 0;
        }
        if (component instanceof JSpinner) {
            JFormattedTextField jFormattedTextField;
            if (FlatUIUtils.isPermanentFocusOwner(component)) {
                return (int)((long)1494892546 ^ (long)1494892547) != 0;
            }
            JComponent jComponent = ((JSpinner)component).getEditor();
            if (jComponent instanceof JSpinner.DefaultEditor && (jFormattedTextField = ((JSpinner.DefaultEditor)jComponent).getTextField()) != null) {
                return FlatUIUtils.isPermanentFocusOwner(jFormattedTextField);
            }
            return ((int)-1275719164L ^ 0xB3F61204) != 0;
        }
        return FlatUIUtils.isPermanentFocusOwner(component);
    }

    protected boolean isCellEditor(Component component) {
        return FlatUIUtils.isCellEditor(component);
    }

    @Override
    public Insets getBorderInsets(Component component, Insets insets) {
        float f2 = UIScale.scale((float)this.getFocusWidth(component));
        float f3 = f2 + UIScale.scale((float)this.getLineWidth(component));
        insets = super.getBorderInsets(component, insets);
        insets.top = Math.round(UIScale.scale((float)insets.top) + f3);
        insets.left = Math.round(UIScale.scale((float)insets.left) + f3);
        insets.bottom = Math.round(UIScale.scale((float)insets.bottom) + f3);
        insets.right = Math.round(UIScale.scale((float)insets.right) + f3);
        if (this.isCellEditor(component)) {
            insets.top = insets.bottom = (int)188913048L ^ 0xB429598;
            if (component.getComponentOrientation().isLeftToRight()) {
                insets.right = (int)((long)-426430654 ^ (long)-426430654);
            } else {
                insets.left = (int)-1246789824L ^ 0xB5AF7F40;
            }
        }
        return insets;
    }

    protected int getFocusWidth(Component component) {
        if (this.isCellEditor(component)) {
            return (int)400989206L ^ 0x17E69C16;
        }
        return this.focusWidth;
    }

    protected float getInnerFocusWidth(Component component) {
        return this.innerFocusWidth;
    }

    protected int getLineWidth(Component component) {
        return (int)((long)-683330649 ^ (long)-683330650);
    }

    protected int getBorderWidth(Component component) {
        return this.getLineWidth(component);
    }

    protected int getArc(Component component) {
        return (int)((long)-1356298985 ^ (long)-1356298985);
    }
}

