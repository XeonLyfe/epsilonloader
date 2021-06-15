/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatNativeWindowBorder;
import com.formdev.flatlaf.ui.FlatTitlePane;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.FlatWindowResizer;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.util.function.Function;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.RootPaneUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicRootPaneUI;

public class FlatRootPaneUI
extends BasicRootPaneUI {
    protected final Color borderColor = UIManager.getColor("TitlePane.borderColor");
    protected JRootPane rootPane;
    protected FlatTitlePane titlePane;
    protected FlatWindowResizer windowResizer;
    private Object nativeWindowBorderData;
    private LayoutManager oldLayout;
    protected static final Integer TITLE_PANE_LAYER = JLayeredPane.FRAME_CONTENT_LAYER - ((int)539702156L ^ 0x202B338D);

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatRootPaneUI();
    }

    @Override
    public void installUI(JComponent jComponent) {
        super.installUI(jComponent);
        this.rootPane = (JRootPane)jComponent;
        if (this.rootPane.getWindowDecorationStyle() != 0) {
            this.installClientDecorations();
        } else {
            this.installBorder();
        }
        this.installNativeWindowBorder();
    }

    protected void installBorder() {
        Border border;
        if (this.borderColor != null && ((border = this.rootPane.getBorder()) == null || border instanceof UIResource)) {
            this.rootPane.setBorder(new FlatWindowTitleBorder(this.borderColor));
        }
    }

    @Override
    public void uninstallUI(JComponent jComponent) {
        super.uninstallUI(jComponent);
        this.uninstallNativeWindowBorder();
        this.uninstallClientDecorations();
        this.rootPane = null;
    }

    @Override
    protected void installDefaults(JRootPane jRootPane) {
        Color color;
        super.installDefaults(jRootPane);
        Container container = jRootPane.getParent();
        if ((container instanceof JFrame || container instanceof JDialog) && ((color = container.getBackground()) == null || color instanceof UIResource)) {
            container.setBackground(UIManager.getColor("control"));
        }
        if (SystemInfo.isJetBrainsJVM && SystemInfo.isMacOS_10_14_Mojave_orLater) {
            jRootPane.putClientProperty("jetbrains.awt.windowDarkAppearance", FlatLaf.isLafDark());
        }
    }

    protected void installNativeWindowBorder() {
        this.nativeWindowBorderData = FlatNativeWindowBorder.install(this.rootPane);
    }

    protected void uninstallNativeWindowBorder() {
        FlatNativeWindowBorder.uninstall(this.rootPane, this.nativeWindowBorderData);
        this.nativeWindowBorderData = null;
    }

    public static void updateNativeWindowBorder(JRootPane jRootPane) {
        RootPaneUI rootPaneUI = jRootPane.getUI();
        if (!(rootPaneUI instanceof FlatRootPaneUI)) {
            return;
        }
        FlatRootPaneUI flatRootPaneUI = (FlatRootPaneUI)rootPaneUI;
        flatRootPaneUI.uninstallNativeWindowBorder();
        flatRootPaneUI.installNativeWindowBorder();
    }

    protected void installClientDecorations() {
        boolean bl = FlatNativeWindowBorder.isSupported();
        if (this.rootPane.getWindowDecorationStyle() != 0 && !bl) {
            LookAndFeel.installBorder(this.rootPane, "RootPane.border");
        } else {
            LookAndFeel.uninstallBorder(this.rootPane);
        }
        this.setTitlePane(this.createTitlePane());
        this.oldLayout = this.rootPane.getLayout();
        this.rootPane.setLayout(this.createRootLayout());
        if (!bl) {
            this.windowResizer = this.createWindowResizer();
        }
    }

    protected void uninstallClientDecorations() {
        LookAndFeel.uninstallBorder(this.rootPane);
        this.setTitlePane(null);
        if (this.windowResizer != null) {
            this.windowResizer.uninstall();
            this.windowResizer = null;
        }
        if (this.oldLayout != null) {
            this.rootPane.setLayout(this.oldLayout);
            this.oldLayout = null;
        }
        if (this.rootPane.getWindowDecorationStyle() == 0) {
            this.rootPane.revalidate();
            this.rootPane.repaint();
        }
    }

    protected FlatRootLayout createRootLayout() {
        return new FlatRootLayout();
    }

    protected FlatWindowResizer createWindowResizer() {
        return new FlatWindowResizer.WindowResizer(this.rootPane);
    }

    protected FlatTitlePane createTitlePane() {
        return new FlatTitlePane(this.rootPane);
    }

    protected void setTitlePane(FlatTitlePane flatTitlePane) {
        JLayeredPane jLayeredPane = this.rootPane.getLayeredPane();
        if (this.titlePane != null) {
            jLayeredPane.remove(this.titlePane);
        }
        if (flatTitlePane != null) {
            jLayeredPane.add((Component)flatTitlePane, TITLE_PANE_LAYER);
        }
        this.titlePane = flatTitlePane;
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

    protected static boolean isMenuBarEmbedded(JRootPane jRootPane) {
        RootPaneUI rootPaneUI = jRootPane.getUI();
        return (rootPaneUI instanceof FlatRootPaneUI && ((FlatRootPaneUI)rootPaneUI).titlePane != null && ((FlatRootPaneUI)rootPaneUI).titlePane.isMenuBarEmbedded() ? (int)-1505675503L ^ 0xA6413710 : (int)1922568987L ^ 0x7298131B) != 0;
    }

    private static class FlatWindowTitleBorder
    extends BorderUIResource.EmptyBorderUIResource {
        private final Color borderColor;

        FlatWindowTitleBorder(Color color) {
            super((int)((long)-1978100887 ^ (long)-1978100887), (int)((long)-296324892 ^ (long)-296324892), (int)((long)-1301763679 ^ (long)-1301763679), (int)-332366948L ^ 0xEC307B9C);
            this.borderColor = color;
        }

        @Override
        public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
            if (this.showBorder(component)) {
                float f2 = UIScale.scale(1.0f);
                FlatUIUtils.paintFilledRectangle(graphics, this.borderColor, n2, n3, n4, f2);
            }
        }

        @Override
        public Insets getBorderInsets(Component component, Insets insets) {
            insets.set(this.showBorder(component) ? (int)((long)-295819052 ^ (long)-295819051) : (int)((long)485456532 ^ (long)485456532), (int)-1916945763L ^ 0x8DBDBA9D, (int)((long)1169305817 ^ (long)1169305817), (int)-1650264516L ^ 0x9DA2F63C);
            return insets;
        }

        private boolean showBorder(Component component) {
            Container container = component.getParent();
            return (container instanceof JFrame && (((JFrame)container).getJMenuBar() == null || !((JFrame)container).getJMenuBar().isVisible()) || container instanceof JDialog && (((JDialog)container).getJMenuBar() == null || !((JDialog)container).getJMenuBar().isVisible()) ? (int)-572079028L ^ 0xDDE6C44D : (int)1174868211L ^ 0x460710F3) != 0;
        }
    }

    public static class FlatWindowBorder
    extends BorderUIResource.EmptyBorderUIResource {
        protected final Color activeBorderColor = UIManager.getColor("RootPane.activeBorderColor");
        protected final Color inactiveBorderColor = UIManager.getColor("RootPane.inactiveBorderColor");
        protected final Color baseBorderColor = UIManager.getColor("Panel.background");

        public FlatWindowBorder() {
            super((int)-1095718713L ^ 0xBEB0A8C6, (int)-203744579L ^ 0xF3DB1ABC, (int)((long)-1627450168 ^ (long)-1627450167), (int)((long)-1789453033 ^ (long)-1789453034));
        }

        @Override
        public Insets getBorderInsets(Component component, Insets insets) {
            if (this.isWindowMaximized(component) || FlatUIUtils.isFullScreen(component)) {
                insets.bottom = insets.right = (int)1064969296L ^ 0x3F7A2450;
                insets.left = insets.right;
                insets.top = insets.right;
                return insets;
            }
            return super.getBorderInsets(component, insets);
        }

        @Override
        public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
            if (this.isWindowMaximized(component) || FlatUIUtils.isFullScreen(component)) {
                return;
            }
            Container container = component.getParent();
            boolean bl = (container instanceof Window ? ((Window)container).isActive() : (int)((long)-1440418339 ^ (long)-1440418339)) ? 1 : 0;
            graphics.setColor(FlatUIUtils.deriveColor(bl ? this.activeBorderColor : this.inactiveBorderColor, this.baseBorderColor));
            HiDPIUtils.paintAtScale1x((Graphics2D)graphics, n2, n3, n4, n5, (arg_0, arg_1, arg_2, arg_3, arg_4, arg_5) -> this.paintImpl(arg_0, arg_1, arg_2, arg_3, arg_4, arg_5));
        }

        private void paintImpl(Graphics2D graphics2D, int n2, int n3, int n4, int n5, double d2) {
            graphics2D.drawRect(n2, n3, n4 - (int)((long)-535632124 ^ (long)-535632123), n5 - (int)((long)1528214287 ^ (long)1528214286));
        }

        protected boolean isWindowMaximized(Component component) {
            Container container = component.getParent();
            return (container instanceof Frame ? ((((Frame)container).getExtendedState() & (int)((long)1103885272 ^ (long)1103885275) << 1) != 0 ? (int)551188753L ^ 0x20DA7910 : (int)658244251L ^ 0x273C029B) : (int)-942976697L ^ 0xC7CB5147) != 0;
        }
    }

    protected class FlatRootLayout
    implements LayoutManager2 {
        protected FlatRootLayout() {
        }

        @Override
        public void addLayoutComponent(String string, Component component) {
        }

        @Override
        public void addLayoutComponent(Component component, Object object) {
        }

        @Override
        public void removeLayoutComponent(Component component) {
        }

        @Override
        public Dimension preferredLayoutSize(Container container) {
            return this.computeLayoutSize(container, component -> component.getPreferredSize());
        }

        @Override
        public Dimension minimumLayoutSize(Container container) {
            return this.computeLayoutSize(container, component -> component.getMinimumSize());
        }

        @Override
        public Dimension maximumLayoutSize(Container container) {
            return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        private Dimension computeLayoutSize(Container container, Function<Component, Dimension> function) {
            Serializable serializable;
            JRootPane jRootPane = (JRootPane)container;
            Dimension dimension = FlatRootPaneUI.this.titlePane != null ? function.apply(FlatRootPaneUI.this.titlePane) : new Dimension();
            Dimension dimension2 = jRootPane.getContentPane() != null ? function.apply(jRootPane.getContentPane()) : jRootPane.getSize();
            int n2 = Math.max(dimension.width, dimension2.width);
            int n3 = dimension.height + dimension2.height;
            if (FlatRootPaneUI.this.titlePane == null || !FlatRootPaneUI.this.titlePane.isMenuBarEmbedded()) {
                serializable = jRootPane.getJMenuBar();
                Dimension dimension3 = serializable != null && serializable.isVisible() ? function.apply((Component)serializable) : new Dimension();
                n2 = Math.max(n2, dimension3.width);
                n3 += dimension3.height;
            }
            serializable = jRootPane.getInsets();
            return new Dimension(n2 + ((Insets)serializable).left + ((Insets)serializable).right, n3 + ((Insets)serializable).top + ((Insets)serializable).bottom);
        }

        @Override
        public void layoutContainer(Container container) {
            Container container2;
            JMenuBar jMenuBar;
            JRootPane jRootPane = (JRootPane)container;
            boolean bl = FlatUIUtils.isFullScreen(jRootPane);
            Insets insets = jRootPane.getInsets();
            int n2 = insets.left;
            int n3 = insets.top;
            int n4 = jRootPane.getWidth() - insets.left - insets.right;
            int n5 = jRootPane.getHeight() - insets.top - insets.bottom;
            if (jRootPane.getLayeredPane() != null) {
                jRootPane.getLayeredPane().setBounds(n2, n3, n4, n5);
            }
            if (jRootPane.getGlassPane() != null) {
                jRootPane.getGlassPane().setBounds(n2, n3, n4, n5);
            }
            int n6 = (int)((long)-2072271270 ^ (long)-2072271270);
            if (FlatRootPaneUI.this.titlePane != null) {
                int n7 = !bl ? FlatRootPaneUI.this.titlePane.getPreferredSize().height : (int)((long)-1560759955 ^ (long)-1560759955);
                FlatRootPaneUI.this.titlePane.setBounds((int)1083474189L ^ 0x4094810D, (int)-1249686718L ^ 0xB5834B42, n4, n7);
                n6 += n7;
            }
            if ((jMenuBar = jRootPane.getJMenuBar()) != null && jMenuBar.isVisible()) {
                int n8;
                int n9 = n8 = !bl && FlatRootPaneUI.this.titlePane != null && FlatRootPaneUI.this.titlePane.isMenuBarEmbedded() ? (int)586354117L ^ 0x22F30DC4 : (int)((long)-1362496467 ^ (long)-1362496467);
                if (n8 != 0) {
                    FlatRootPaneUI.this.titlePane.validate();
                    jMenuBar.setBounds(FlatRootPaneUI.this.titlePane.getMenuBarBounds());
                } else {
                    Dimension dimension = jMenuBar.getPreferredSize();
                    jMenuBar.setBounds((int)((long)-819440065 ^ (long)-819440065), n6, n4, dimension.height);
                    n6 += dimension.height;
                }
            }
            if ((container2 = jRootPane.getContentPane()) != null) {
                container2.setBounds((int)-1748630089L ^ 0x97C605B7, n6, n4, Math.max(n5 - n6, (int)-1025852849L ^ 0xC2DABA4F));
            }
            if (FlatRootPaneUI.this.titlePane != null) {
                FlatRootPaneUI.this.titlePane.menuBarLayouted();
            }
        }

        @Override
        public void invalidateLayout(Container container) {
            if (FlatRootPaneUI.this.titlePane != null) {
                FlatRootPaneUI.this.titlePane.menuBarChanged();
            }
        }

        @Override
        public float getLayoutAlignmentX(Container container) {
            return 0.0f;
        }

        @Override
        public float getLayoutAlignmentY(Container container) {
            return 0.0f;
        }
    }
}

