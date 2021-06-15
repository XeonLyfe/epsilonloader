/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatButtonBorder;
import com.formdev.flatlaf.ui.FlatLabelUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.plaf.basic.BasicButtonUI;

public class FlatButtonUI
extends BasicButtonUI {
    protected int minimumWidth;
    protected int iconTextGap;
    protected Color background;
    protected Color foreground;
    protected Color startBackground;
    protected Color endBackground;
    protected Color focusedBackground;
    protected Color hoverBackground;
    protected Color pressedBackground;
    protected Color selectedBackground;
    protected Color selectedForeground;
    protected Color disabledBackground;
    protected Color disabledText;
    protected Color disabledSelectedBackground;
    protected Color defaultBackground;
    protected Color defaultEndBackground;
    protected Color defaultForeground;
    protected Color defaultFocusedBackground;
    protected Color defaultHoverBackground;
    protected Color defaultPressedBackground;
    protected boolean defaultBoldText;
    protected int shadowWidth;
    protected Color shadowColor;
    protected Color defaultShadowColor;
    protected Insets toolbarSpacingInsets;
    protected Color toolbarHoverBackground;
    protected Color toolbarPressedBackground;
    protected Color toolbarSelectedBackground;
    private Icon helpButtonIcon;
    private boolean defaults_initialized = (int)-1537029971L ^ 0xA462C8AD;
    static final int TYPE_OTHER = -1;
    static final int TYPE_SQUARE = 0;
    static final int TYPE_ROUND_RECT = 1;

    public static ComponentUI createUI(JComponent jComponent) {
        return FlatUIUtils.createSharedUI(FlatButtonUI.class, FlatButtonUI::new);
    }

    @Override
    protected void installDefaults(AbstractButton abstractButton) {
        Object object;
        super.installDefaults(abstractButton);
        if (!this.defaults_initialized) {
            object = this.getPropertyPrefix();
            this.minimumWidth = UIManager.getInt((String)object + "minimumWidth");
            this.iconTextGap = FlatUIUtils.getUIInt((String)object + "iconTextGap", ((int)-1506132589L ^ 0xA63A3D92) << 2);
            this.background = UIManager.getColor((String)object + "background");
            this.foreground = UIManager.getColor((String)object + "foreground");
            this.startBackground = UIManager.getColor((String)object + "startBackground");
            this.endBackground = UIManager.getColor((String)object + "endBackground");
            this.focusedBackground = UIManager.getColor((String)object + "focusedBackground");
            this.hoverBackground = UIManager.getColor((String)object + "hoverBackground");
            this.pressedBackground = UIManager.getColor((String)object + "pressedBackground");
            this.selectedBackground = UIManager.getColor((String)object + "selectedBackground");
            this.selectedForeground = UIManager.getColor((String)object + "selectedForeground");
            this.disabledBackground = UIManager.getColor((String)object + "disabledBackground");
            this.disabledText = UIManager.getColor((String)object + "disabledText");
            this.disabledSelectedBackground = UIManager.getColor((String)object + "disabledSelectedBackground");
            if (UIManager.getBoolean("Button.paintShadow")) {
                this.shadowWidth = FlatUIUtils.getUIInt("Button.shadowWidth", ((int)1885664054L ^ 0x7064F337) << 1);
                this.shadowColor = UIManager.getColor("Button.shadowColor");
                this.defaultShadowColor = UIManager.getColor("Button.default.shadowColor");
            } else {
                this.shadowWidth = (int)((long)797868572 ^ (long)797868572);
                this.shadowColor = null;
                this.defaultShadowColor = null;
            }
            this.defaultBackground = FlatUIUtils.getUIColor("Button.default.startBackground", "Button.default.background");
            this.defaultEndBackground = UIManager.getColor("Button.default.endBackground");
            this.defaultForeground = UIManager.getColor("Button.default.foreground");
            this.defaultFocusedBackground = UIManager.getColor("Button.default.focusedBackground");
            this.defaultHoverBackground = UIManager.getColor("Button.default.hoverBackground");
            this.defaultPressedBackground = UIManager.getColor("Button.default.pressedBackground");
            this.defaultBoldText = UIManager.getBoolean("Button.default.boldText");
            this.toolbarSpacingInsets = UIManager.getInsets("Button.toolbar.spacingInsets");
            this.toolbarHoverBackground = UIManager.getColor((String)object + "toolbar.hoverBackground");
            this.toolbarPressedBackground = UIManager.getColor((String)object + "toolbar.pressedBackground");
            this.toolbarSelectedBackground = UIManager.getColor((String)object + "toolbar.selectedBackground");
            this.helpButtonIcon = UIManager.getIcon("HelpButton.icon");
            this.defaults_initialized = (int)986531028L ^ 0x3ACD44D5;
        }
        if (this.startBackground != null && ((object = abstractButton.getBackground()) == null || object instanceof UIResource)) {
            abstractButton.setBackground(this.startBackground);
        }
        LookAndFeel.installProperty(abstractButton, "opaque", (boolean)((long)609243160 ^ (long)609243160));
        LookAndFeel.installProperty(abstractButton, "iconTextGap", UIScale.scale(this.iconTextGap));
        MigLayoutVisualPadding.install(abstractButton);
    }

    @Override
    protected void uninstallDefaults(AbstractButton abstractButton) {
        super.uninstallDefaults(abstractButton);
        MigLayoutVisualPadding.uninstall(abstractButton);
        this.defaults_initialized = (int)((long)2018547377 ^ (long)2018547377);
    }

    @Override
    protected BasicButtonListener createButtonListener(AbstractButton abstractButton) {
        return new FlatButtonListener(abstractButton);
    }

    /*
     * Exception decompiling
     */
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

    static boolean isContentAreaFilled(Component component) {
        return (!(component instanceof AbstractButton) || ((AbstractButton)component).isContentAreaFilled() ? (int)904061122L ^ 0x35E2E0C3 : (int)((long)-421624844 ^ (long)-421624844)) != 0;
    }

    public static boolean isFocusPainted(Component component) {
        return (!(component instanceof AbstractButton) || ((AbstractButton)component).isFocusPainted() ? (int)1851305503L ^ 0x6E58AE1E : (int)-47702088L ^ 0xFD281FB8) != 0;
    }

    static boolean isDefaultButton(Component component) {
        return (component instanceof JButton && ((JButton)component).isDefaultButton() ? (int)-1199828088L ^ 0xB87C1389 : (int)((long)-1777345234 ^ (long)-1777345234)) != 0;
    }

    static boolean isIconOnlyOrSingleCharacterButton(Component component) {
        if (!(component instanceof JButton) && !(component instanceof JToggleButton)) {
            return (int)((long)-1024360299 ^ (long)-1024360299) != 0;
        }
        Icon icon = ((AbstractButton)component).getIcon();
        String string = ((AbstractButton)component).getText();
        return (icon != null && (string == null || string.isEmpty()) || icon == null && string != null && ("...".equals(string) || string.length() == (int)((long)94841233 ^ (long)94841232) || string.length() == ((int)-550730623L ^ 0xDF2C8480) << 1 && Character.isSurrogatePair(string.charAt((int)-1207638668L ^ 0xB804E574), string.charAt((int)((long)-2105925815 ^ (long)-2105925816)))) ? (int)619991081L ^ 0x24F45028 : (int)-881455212L ^ 0xCB760F94) != 0;
    }

    /*
     * Exception decompiling
     */
    static int getButtonType(Component var0) {
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

    static boolean isHelpButton(Component component) {
        return (component instanceof JButton && FlatClientProperties.clientPropertyEquals((JButton)component, "JButton.buttonType", "help") ? (int)320551643L ^ 0x131B3ADA : (int)306414398L ^ 0x1243833E) != 0;
    }

    static boolean isToolBarButton(Component component) {
        return (component.getParent() instanceof JToolBar || component instanceof AbstractButton && FlatClientProperties.clientPropertyEquals((AbstractButton)component, "JButton.buttonType", "toolBarButton") ? (int)((long)320024411 ^ (long)320024410) : (int)((long)1242775294 ^ (long)1242775294)) != 0;
    }

    @Override
    public void update(Graphics graphics, JComponent jComponent) {
        if (jComponent.isOpaque()) {
            FlatUIUtils.paintParentBackground(graphics, jComponent);
        }
        if (FlatButtonUI.isHelpButton(jComponent)) {
            this.helpButtonIcon.paintIcon(jComponent, graphics, (int)((long)-1136924155 ^ (long)-1136924155), (int)((long)2068714146 ^ (long)2068714146));
            return;
        }
        if (FlatButtonUI.isContentAreaFilled(jComponent)) {
            this.paintBackground(graphics, jComponent);
        }
        this.paint(graphics, jComponent);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void paintBackground(Graphics graphics, JComponent jComponent) {
        Color color = this.getBackground(jComponent);
        if (color == null) {
            return;
        }
        Graphics2D graphics2D = (Graphics2D)graphics.create();
        try {
            Color color2;
            Serializable serializable;
            FlatUIUtils.setRenderingHints(graphics2D);
            boolean bl = FlatButtonUI.isToolBarButton(jComponent);
            float f2 = bl ? 0.0f : FlatUIUtils.getBorderFocusWidth(jComponent);
            float f3 = FlatUIUtils.getBorderArc(jComponent);
            boolean bl2 = FlatButtonUI.isDefaultButton(jComponent);
            int n2 = (int)1285761652L ^ 0x4CA32A74;
            int n3 = (int)254310571L ^ 0xF2878AB;
            int n4 = jComponent.getWidth();
            int n5 = jComponent.getHeight();
            if (bl) {
                serializable = UIScale.scale(this.toolbarSpacingInsets);
                n2 += serializable.left;
                n3 += serializable.top;
                n4 -= serializable.left + serializable.right;
                n5 -= serializable.top + serializable.bottom;
            }
            Serializable serializable2 = serializable = bl2 ? this.defaultShadowColor : this.shadowColor;
            if (!(bl || serializable == null || this.shadowWidth <= 0 || !(f2 > 0.0f) || FlatButtonUI.isFocusPainted(jComponent) && FlatUIUtils.isPermanentFocusOwner(jComponent) || !jComponent.isEnabled())) {
                graphics2D.setColor((Color)serializable);
                graphics2D.fill(new RoundRectangle2D.Float(f2, f2 + UIScale.scale((float)this.shadowWidth), (float)n4 - f2 * 2.0f, (float)n5 - f2 * 2.0f, f3, f3));
            }
            Color color3 = bl2 ? this.defaultBackground : this.startBackground;
            Color color4 = color2 = bl2 ? this.defaultEndBackground : this.endBackground;
            if (color == color3 && color2 != null && !color3.equals(color2)) {
                graphics2D.setPaint(new GradientPaint(0.0f, 0.0f, color3, 0.0f, n5, color2));
            } else {
                graphics2D.setColor(FlatUIUtils.deriveColor(color, this.getBackgroundBase(jComponent, bl2)));
            }
            FlatUIUtils.paintComponentBackground(graphics2D, n2, n3, n4, n5, f2, f3);
        }
        finally {
            graphics2D.dispose();
        }
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        super.paint(FlatLabelUI.createGraphicsHTMLTextYCorrection(graphics, jComponent), jComponent);
    }

    @Override
    protected void paintText(Graphics graphics, AbstractButton abstractButton, Rectangle rectangle, String string) {
        if (FlatButtonUI.isHelpButton(abstractButton)) {
            return;
        }
        if (this.defaultBoldText && FlatButtonUI.isDefaultButton(abstractButton) && abstractButton.getFont() instanceof UIResource) {
            Font font = graphics.getFont().deriveFont((int)((long)-1037006526 ^ (long)-1037006525));
            graphics.setFont(font);
            int n2 = abstractButton.getFontMetrics(font).stringWidth(string);
            if (n2 > rectangle.width) {
                rectangle.x -= (n2 - rectangle.width) / (((int)966788227L ^ 0x39A00482) << 1);
                rectangle.width = n2;
            }
        }
        FlatButtonUI.paintText(graphics, abstractButton, rectangle, string, this.getForeground(abstractButton));
    }

    public static void paintText(Graphics graphics, AbstractButton abstractButton, Rectangle rectangle, String string, Color color) {
        FontMetrics fontMetrics = abstractButton.getFontMetrics(abstractButton.getFont());
        int n2 = FlatLaf.isShowMnemonics() ? abstractButton.getDisplayedMnemonicIndex() : (int)1948610884L ^ 0x8BDA8EBB;
        graphics.setColor(color);
        FlatUIUtils.drawStringUnderlineCharAt(abstractButton, graphics, string, n2, rectangle.x, rectangle.y + fontMetrics.getAscent());
    }

    protected Color getBackground(JComponent jComponent) {
        boolean bl = FlatButtonUI.isToolBarButton(jComponent);
        if (((AbstractButton)jComponent).isSelected()) {
            return FlatButtonUI.buttonStateColor(jComponent, bl ? this.toolbarSelectedBackground : this.selectedBackground, bl ? this.toolbarSelectedBackground : this.disabledSelectedBackground, null, null, bl ? this.toolbarPressedBackground : this.pressedBackground);
        }
        if (bl) {
            Color color = jComponent.getBackground();
            return FlatButtonUI.buttonStateColor(jComponent, this.isCustomBackground(color) ? color : null, null, null, this.toolbarHoverBackground, this.toolbarPressedBackground);
        }
        boolean bl2 = FlatButtonUI.isDefaultButton(jComponent);
        return FlatButtonUI.buttonStateColor(jComponent, this.getBackgroundBase(jComponent, bl2), this.disabledBackground, this.isCustomBackground(jComponent.getBackground()) ? null : (bl2 ? this.defaultFocusedBackground : this.focusedBackground), bl2 ? this.defaultHoverBackground : this.hoverBackground, bl2 ? this.defaultPressedBackground : this.pressedBackground);
    }

    protected Color getBackgroundBase(JComponent jComponent, boolean bl) {
        Color color = jComponent.getBackground();
        if (this.isCustomBackground(color)) {
            return color;
        }
        return bl ? this.defaultBackground : color;
    }

    protected boolean isCustomBackground(Color color) {
        return (color != this.background && (this.startBackground == null || color != this.startBackground) ? (int)757425091L ^ 0x2D2563C2 : (int)((long)1126803271 ^ (long)1126803271)) != 0;
    }

    public static Color buttonStateColor(Component component, Color color, Color color2, Color color3, Color color4, Color color5) {
        if (!component.isEnabled()) {
            return color2;
        }
        if (component instanceof AbstractButton) {
            ButtonModel buttonModel = ((AbstractButton)component).getModel();
            if (color5 != null && buttonModel.isPressed()) {
                return color5;
            }
            if (color4 != null && buttonModel.isRollover()) {
                return color4;
            }
        }
        if (color3 != null && FlatButtonUI.isFocusPainted(component) && FlatUIUtils.isPermanentFocusOwner(component)) {
            return color3;
        }
        return color;
    }

    protected Color getForeground(JComponent jComponent) {
        if (!jComponent.isEnabled()) {
            return this.disabledText;
        }
        if (((AbstractButton)jComponent).isSelected() && !FlatButtonUI.isToolBarButton(jComponent)) {
            return this.selectedForeground;
        }
        Color color = jComponent.getForeground();
        if (this.isCustomForeground(color)) {
            return color;
        }
        boolean bl = FlatButtonUI.isDefaultButton(jComponent);
        return bl ? this.defaultForeground : color;
    }

    protected boolean isCustomForeground(Color color) {
        return (color != this.foreground ? (int)((long)1443795138 ^ (long)1443795139) : (int)1783506265L ^ 0x6A4E2559) != 0;
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        if (FlatButtonUI.isHelpButton(jComponent)) {
            return new Dimension(this.helpButtonIcon.getIconWidth(), this.helpButtonIcon.getIconHeight());
        }
        Dimension dimension = super.getPreferredSize(jComponent);
        if (dimension == null) {
            return null;
        }
        boolean bl = FlatButtonUI.isIconOnlyOrSingleCharacterButton(jComponent);
        if (FlatClientProperties.clientPropertyBoolean(jComponent, "JButton.squareSize", (boolean)((long)-358207558 ^ (long)-358207558))) {
            dimension.width = dimension.height = Math.max(dimension.width, dimension.height);
        } else if (bl && ((AbstractButton)jComponent).getIcon() == null) {
            dimension.width = Math.max(dimension.width, dimension.height);
        } else if (!bl && !FlatButtonUI.isToolBarButton(jComponent) && jComponent.getBorder() instanceof FlatButtonBorder) {
            float f2 = FlatUIUtils.getBorderFocusWidth(jComponent);
            dimension.width = Math.max(dimension.width, UIScale.scale(FlatUIUtils.minimumWidth(jComponent, this.minimumWidth)) + Math.round(f2 * 2.0f));
            dimension.height = Math.max(dimension.height, UIScale.scale(FlatUIUtils.minimumHeight(jComponent, (int)((long)775430576 ^ (long)775430576))) + Math.round(f2 * 2.0f));
        }
        return dimension;
    }

    protected class FlatButtonListener
    extends BasicButtonListener {
        private final AbstractButton b;

        protected FlatButtonListener(AbstractButton abstractButton) {
            super(abstractButton);
            this.b = abstractButton;
        }

        @Override
        public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
            super.propertyChange(propertyChangeEvent);
            FlatButtonUI.this.propertyChange(this.b, propertyChangeEvent);
        }
    }
}

