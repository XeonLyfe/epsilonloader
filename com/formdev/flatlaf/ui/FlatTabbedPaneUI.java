/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatArrowButton;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import com.formdev.flatlaf.util.Animator;
import com.formdev.flatlaf.util.CubicBezierEasing;
import com.formdev.flatlaf.util.JavaCompatibility;
import com.formdev.flatlaf.util.StringUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.JTextComponent;
import javax.swing.text.View;

public class FlatTabbedPaneUI
extends BasicTabbedPaneUI {
    protected static final int NEVER = 0;
    protected static final int AS_NEEDED = 2;
    protected static final int AS_NEEDED_SINGLE = 3;
    protected static final int BOTH = 100;
    protected static final int FILL = 100;
    protected static final int WIDTH_MODE_PREFERRED = 0;
    protected static final int WIDTH_MODE_EQUAL = 1;
    protected static final int WIDTH_MODE_COMPACT = 2;
    private static Set<KeyStroke> focusForwardTraversalKeys;
    private static Set<KeyStroke> focusBackwardTraversalKeys;
    protected Color foreground;
    protected Color disabledForeground;
    protected Color selectedBackground;
    protected Color selectedForeground;
    protected Color underlineColor;
    protected Color disabledUnderlineColor;
    protected Color hoverColor;
    protected Color focusColor;
    protected Color tabSeparatorColor;
    protected Color contentAreaColor;
    private int textIconGapUnscaled;
    protected int minimumTabWidth;
    protected int maximumTabWidth;
    protected int tabHeight;
    protected int tabSelectionHeight;
    protected int contentSeparatorHeight;
    protected boolean showTabSeparators;
    protected boolean tabSeparatorsFullHeight;
    protected boolean hasFullBorder;
    protected boolean tabsOpaque = (int)((long)1393343454 ^ (long)1393343455);
    private int tabsPopupPolicy;
    private int scrollButtonsPolicy;
    private int scrollButtonsPlacement;
    private int tabAreaAlignment;
    private int tabAlignment;
    private int tabWidthMode;
    protected Icon closeIcon;
    protected String arrowType;
    protected Insets buttonInsets;
    protected int buttonArc;
    protected Color buttonHoverBackground;
    protected Color buttonPressedBackground;
    protected String moreTabsButtonToolTipText;
    protected JViewport tabViewport;
    protected FlatWheelTabScroller wheelTabScroller;
    private JButton tabCloseButton;
    private JButton moreTabsButton;
    private Container leadingComponent;
    private Container trailingComponent;
    private Dimension scrollBackwardButtonPrefSize;
    private Handler handler;
    private boolean blockRollover;
    private boolean rolloverTabClose;
    private boolean pressedTabClose;
    private Object[] oldRenderingHints;
    private boolean inCalculateEqual;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatTabbedPaneUI();
    }

    /*
     * Exception decompiling
     */
    @Override
    public void installUI(JComponent var1_1) {
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
    protected void installDefaults() {
        Object object;
        if (UIManager.getBoolean("TabbedPane.tabsOverlapBorder")) {
            object = UIManager.put("TabbedPane.tabsOverlapBorder", ((int)-606667851L ^ 0xDBD6FBB5) != 0);
            super.installDefaults();
            UIManager.put("TabbedPane.tabsOverlapBorder", object);
        } else {
            super.installDefaults();
        }
        this.selectedBackground = UIManager.getColor("TabbedPane.selectedBackground");
        this.selectedForeground = UIManager.getColor("TabbedPane.selectedForeground");
        this.underlineColor = UIManager.getColor("TabbedPane.underlineColor");
        this.disabledUnderlineColor = UIManager.getColor("TabbedPane.disabledUnderlineColor");
        this.hoverColor = UIManager.getColor("TabbedPane.hoverColor");
        this.focusColor = UIManager.getColor("TabbedPane.focusColor");
        this.tabSeparatorColor = UIManager.getColor("TabbedPane.tabSeparatorColor");
        this.contentAreaColor = UIManager.getColor("TabbedPane.contentAreaColor");
        this.textIconGapUnscaled = UIManager.getInt("TabbedPane.textIconGap");
        this.minimumTabWidth = UIManager.getInt("TabbedPane.minimumTabWidth");
        this.maximumTabWidth = UIManager.getInt("TabbedPane.maximumTabWidth");
        this.tabHeight = UIManager.getInt("TabbedPane.tabHeight");
        this.tabSelectionHeight = UIManager.getInt("TabbedPane.tabSelectionHeight");
        this.contentSeparatorHeight = UIManager.getInt("TabbedPane.contentSeparatorHeight");
        this.showTabSeparators = UIManager.getBoolean("TabbedPane.showTabSeparators");
        this.tabSeparatorsFullHeight = UIManager.getBoolean("TabbedPane.tabSeparatorsFullHeight");
        this.hasFullBorder = UIManager.getBoolean("TabbedPane.hasFullBorder");
        this.tabsOpaque = UIManager.getBoolean("TabbedPane.tabsOpaque");
        this.tabsPopupPolicy = FlatTabbedPaneUI.parseTabsPopupPolicy(UIManager.getString("TabbedPane.tabsPopupPolicy"));
        this.scrollButtonsPolicy = FlatTabbedPaneUI.parseScrollButtonsPolicy(UIManager.getString("TabbedPane.scrollButtonsPolicy"));
        this.scrollButtonsPlacement = FlatTabbedPaneUI.parseScrollButtonsPlacement(UIManager.getString("TabbedPane.scrollButtonsPlacement"));
        this.tabAreaAlignment = FlatTabbedPaneUI.parseAlignment(UIManager.getString("TabbedPane.tabAreaAlignment"), (int)((long)1907860169 ^ (long)1907860172) << 1);
        this.tabAlignment = FlatTabbedPaneUI.parseAlignment(UIManager.getString("TabbedPane.tabAlignment"), (int)-1329111307L ^ 0xB0C75EF5);
        this.tabWidthMode = FlatTabbedPaneUI.parseTabWidthMode(UIManager.getString("TabbedPane.tabWidthMode"));
        this.closeIcon = UIManager.getIcon("TabbedPane.closeIcon");
        this.buttonInsets = UIManager.getInsets("TabbedPane.buttonInsets");
        this.buttonArc = UIManager.getInt("TabbedPane.buttonArc");
        object = this.tabPane.getLocale();
        this.moreTabsButtonToolTipText = UIManager.getString((Object)"TabbedPane.moreTabsButtonToolTipText", (Locale)object);
        this.textIconGap = UIScale.scale(this.textIconGapUnscaled);
        if (focusForwardTraversalKeys == null) {
            focusForwardTraversalKeys = Collections.singleton(KeyStroke.getKeyStroke((int)((long)618339899 ^ (long)618339890), (int)((long)819665177 ^ (long)819665177)));
            focusBackwardTraversalKeys = Collections.singleton(KeyStroke.getKeyStroke((int)-519588997L ^ 0xE107B372, (int)1619179879L ^ 0x6082B966));
        }
        this.tabPane.setFocusTraversalKeys((int)((long)-1321357393 ^ (long)-1321357393), focusForwardTraversalKeys);
        this.tabPane.setFocusTraversalKeys((int)((long)1576181352 ^ (long)1576181353), focusBackwardTraversalKeys);
        MigLayoutVisualPadding.install(this.tabPane, null);
    }

    @Override
    protected void uninstallDefaults() {
        this.tabPane.setFocusTraversalKeys((int)-1744962240L ^ 0x97FDFD40, null);
        this.tabPane.setFocusTraversalKeys((int)((long)-990572368 ^ (long)-990572367), null);
        super.uninstallDefaults();
        this.foreground = null;
        this.disabledForeground = null;
        this.selectedBackground = null;
        this.selectedForeground = null;
        this.underlineColor = null;
        this.disabledUnderlineColor = null;
        this.hoverColor = null;
        this.focusColor = null;
        this.tabSeparatorColor = null;
        this.contentAreaColor = null;
        this.closeIcon = null;
        this.buttonHoverBackground = null;
        this.buttonPressedBackground = null;
        MigLayoutVisualPadding.uninstall(this.tabPane);
    }

    @Override
    protected void installComponents() {
        super.installComponents();
        this.tabViewport = null;
        if (this.isScrollTabLayout()) {
            Component[] arrcomponent = this.tabPane.getComponents();
            int n2 = arrcomponent.length;
            for (int i2 = (int)((long)-1591548177 ^ (long)-1591548177); i2 < n2; ++i2) {
                Component component = arrcomponent[i2];
                if (!(component instanceof JViewport) || !component.getClass().getName().equals("javax.swing.plaf.basic.BasicTabbedPaneUI$ScrollableTabViewport")) continue;
                this.tabViewport = (JViewport)component;
                break;
            }
        }
        this.installHiddenTabsNavigation();
        this.installLeadingComponent();
        this.installTrailingComponent();
    }

    @Override
    protected void uninstallComponents() {
        this.uninstallHiddenTabsNavigation();
        this.uninstallLeadingComponent();
        this.uninstallTrailingComponent();
        super.uninstallComponents();
        this.tabCloseButton = null;
        this.tabViewport = null;
    }

    protected void installHiddenTabsNavigation() {
        if (!this.isScrollTabLayout() || this.tabViewport == null) {
            return;
        }
        this.tabPane.setLayout(this.createScrollLayoutManager((BasicTabbedPaneUI.TabbedPaneLayout)this.tabPane.getLayout()));
        this.moreTabsButton = this.createMoreTabsButton();
        this.tabPane.add(this.moreTabsButton);
    }

    protected void uninstallHiddenTabsNavigation() {
        if (this.tabPane.getLayout() instanceof FlatTabbedPaneScrollLayout) {
            this.tabPane.setLayout(((FlatTabbedPaneScrollLayout)this.tabPane.getLayout()).delegate);
        }
        if (this.moreTabsButton != null) {
            this.tabPane.remove(this.moreTabsButton);
            this.moreTabsButton = null;
        }
    }

    protected void installLeadingComponent() {
        Object object = this.tabPane.getClientProperty("JTabbedPane.leadingComponent");
        if (object instanceof Component) {
            this.leadingComponent = new ContainerUIResource((Component)object);
            this.tabPane.add(this.leadingComponent);
        }
    }

    protected void uninstallLeadingComponent() {
        if (this.leadingComponent != null) {
            this.tabPane.remove(this.leadingComponent);
            this.leadingComponent = null;
        }
    }

    protected void installTrailingComponent() {
        Object object = this.tabPane.getClientProperty("JTabbedPane.trailingComponent");
        if (object instanceof Component) {
            this.trailingComponent = new ContainerUIResource((Component)object);
            this.tabPane.add(this.trailingComponent);
        }
    }

    protected void uninstallTrailingComponent() {
        if (this.trailingComponent != null) {
            this.tabPane.remove(this.trailingComponent);
            this.trailingComponent = null;
        }
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        this.getHandler().installListeners();
        if (this.tabViewport != null && (this.wheelTabScroller = this.createWheelTabScroller()) != null) {
            this.tabPane.addMouseWheelListener(this.wheelTabScroller);
            this.tabPane.addMouseMotionListener(this.wheelTabScroller);
            this.tabPane.addMouseListener(this.wheelTabScroller);
        }
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        if (this.handler != null) {
            this.handler.uninstallListeners();
            this.handler = null;
        }
        if (this.wheelTabScroller != null) {
            this.wheelTabScroller.uninstall();
            this.tabPane.removeMouseWheelListener(this.wheelTabScroller);
            this.tabPane.removeMouseMotionListener(this.wheelTabScroller);
            this.tabPane.removeMouseListener(this.wheelTabScroller);
            this.wheelTabScroller = null;
        }
    }

    private Handler getHandler() {
        if (this.handler == null) {
            this.handler = new Handler();
        }
        return this.handler;
    }

    protected FlatWheelTabScroller createWheelTabScroller() {
        return new FlatWheelTabScroller();
    }

    @Override
    protected MouseListener createMouseListener() {
        Handler handler = this.getHandler();
        handler.mouseDelegate = super.createMouseListener();
        return handler;
    }

    @Override
    protected PropertyChangeListener createPropertyChangeListener() {
        Handler handler = this.getHandler();
        handler.propertyChangeDelegate = super.createPropertyChangeListener();
        return handler;
    }

    @Override
    protected ChangeListener createChangeListener() {
        Handler handler = this.getHandler();
        handler.changeDelegate = super.createChangeListener();
        return handler;
    }

    @Override
    protected LayoutManager createLayoutManager() {
        if (this.tabPane.getTabLayoutPolicy() == 0) {
            return new FlatTabbedPaneLayout();
        }
        return super.createLayoutManager();
    }

    protected LayoutManager createScrollLayoutManager(BasicTabbedPaneUI.TabbedPaneLayout tabbedPaneLayout) {
        return new FlatTabbedPaneScrollLayout(tabbedPaneLayout);
    }

    protected JButton createMoreTabsButton() {
        return new FlatMoreTabsButton();
    }

    @Override
    protected JButton createScrollButton(int n2) {
        return new FlatScrollableTabButton(n2);
    }

    protected void setRolloverTab(int n2, int n3) {
        this.setRolloverTab(this.tabForCoordinate(this.tabPane, n2, n3));
    }

    @Override
    protected void setRolloverTab(int n2) {
        if (this.blockRollover) {
            return;
        }
        int n3 = this.getRolloverTab();
        super.setRolloverTab(n2);
        if (n2 == n3) {
            return;
        }
        this.repaintTab(n3);
        this.repaintTab(n2);
    }

    protected boolean isRolloverTabClose() {
        return this.rolloverTabClose;
    }

    protected void setRolloverTabClose(boolean bl) {
        if (this.rolloverTabClose == bl) {
            return;
        }
        this.rolloverTabClose = bl;
        this.repaintTab(this.getRolloverTab());
    }

    protected boolean isPressedTabClose() {
        return this.pressedTabClose;
    }

    protected void setPressedTabClose(boolean bl) {
        if (this.pressedTabClose == bl) {
            return;
        }
        this.pressedTabClose = bl;
        this.repaintTab(this.getRolloverTab());
    }

    private void repaintTab(int n2) {
        if (n2 < 0 || n2 >= this.tabPane.getTabCount()) {
            return;
        }
        Rectangle rectangle = this.getTabBounds(this.tabPane, n2);
        if (rectangle != null) {
            this.tabPane.repaint(rectangle);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected int calculateTabWidth(int n2, int n3, FontMetrics fontMetrics) {
        int n4;
        Icon icon;
        int n5 = this.getTabWidthMode();
        if (n5 == (int)((long)-1387922672 ^ (long)-1387922671) && this.isHorizontalTabPlacement() && !this.inCalculateEqual) {
            int n6;
            this.inCalculateEqual = (int)((long)627295235 ^ (long)627295234);
            try {
                n6 = this.calculateMaxTabWidth(n2);
            }
            catch (Throwable throwable) {
                this.inCalculateEqual = (int)-402643484L ^ 0xE80025E4;
                throw throwable;
            }
            this.inCalculateEqual = (int)((long)-181465898 ^ (long)-181465898);
            return n6;
        }
        this.textIconGap = UIScale.scale(this.textIconGapUnscaled);
        if (n5 == ((int)-1081578530L ^ 0xBF886BDF) << 1 && n3 != this.tabPane.getSelectedIndex() && this.isHorizontalTabPlacement() && this.tabPane.getTabComponentAt(n3) == null && (icon = this.getIconForTab(n3)) != null) {
            Insets insets = this.getTabInsets(n2, n3);
            n4 = icon.getIconWidth() + insets.left + insets.right;
        } else {
            int n7 = FlatClientProperties.clientPropertyInt(this.tabPane, "JTabbedPane.tabIconPlacement", (int)((long)-1940978531 ^ (long)-1940978536) << 1);
            if ((n7 == ((int)-1484888941L ^ 0xA77E6492) || n7 == (int)((long)-1183287041 ^ (long)-1183287044)) && this.tabPane.getTabComponentAt(n3) == null && (icon = this.getIconForTab(n3)) != null) {
                Object object;
                n4 = icon.getIconWidth();
                View view = this.getTextViewForTab(n3);
                if (view != null) {
                    n4 = Math.max(n4, (int)view.getPreferredSpan((int)1309500799L ^ 0x4E0D657F));
                } else {
                    object = this.tabPane.getTitleAt(n3);
                    if (object != null) {
                        n4 = Math.max(n4, fontMetrics.stringWidth((String)object));
                    }
                }
                object = this.getTabInsets(n2, n3);
                n4 += ((Insets)object).left + ((Insets)object).right;
            } else {
                n4 = super.calculateTabWidth(n2, n3, fontMetrics) - ((int)-1746163606L ^ 0x97EBA869);
            }
        }
        if (this.isTabClosable(n3)) {
            n4 += this.closeIcon.getIconWidth();
        }
        int n8 = this.getTabClientPropertyInt(n3, "JTabbedPane.minimumTabWidth", this.minimumTabWidth);
        int n9 = this.getTabClientPropertyInt(n3, "JTabbedPane.maximumTabWidth", this.maximumTabWidth);
        if (n8 > 0) {
            n4 = Math.max(n4, UIScale.scale(n8));
        }
        if (n9 > 0 && this.tabPane.getTabComponentAt(n3) == null) {
            n4 = Math.min(n4, UIScale.scale(n9));
        }
        return n4;
    }

    @Override
    protected int calculateTabHeight(int n2, int n3, int n4) {
        int n5;
        Icon icon;
        int n6 = FlatClientProperties.clientPropertyInt(this.tabPane, "JTabbedPane.tabIconPlacement", ((int)791147765L ^ 0x2F27F4F0) << 1);
        if ((n6 == (int)((long)-1997501302 ^ (long)-1997501301) || n6 == (int)((long)2083218220 ^ (long)2083218223)) && this.tabPane.getTabComponentAt(n3) == null && (icon = this.getIconForTab(n3)) != null) {
            n5 = icon.getIconHeight();
            View view = this.getTextViewForTab(n3);
            if (view != null) {
                n5 += (int)view.getPreferredSpan((int)((long)230905734 ^ (long)230905735)) + UIScale.scale(this.textIconGapUnscaled);
            } else if (this.tabPane.getTitleAt(n3) != null) {
                n5 += n4 + UIScale.scale(this.textIconGapUnscaled);
            }
            Insets insets = this.getTabInsets(n2, n3);
            n5 += insets.top + insets.bottom;
        } else {
            n5 = super.calculateTabHeight(n2, n3, n4) - ((int)((long)202194228 ^ (long)202194229) << 1);
        }
        return Math.max(n5, UIScale.scale(FlatClientProperties.clientPropertyInt(this.tabPane, "JTabbedPane.tabHeight", this.tabHeight)));
    }

    @Override
    protected int calculateMaxTabWidth(int n2) {
        return this.hideTabArea() ? (int)((long)-1356016138 ^ (long)-1356016138) : super.calculateMaxTabWidth(n2);
    }

    @Override
    protected int calculateMaxTabHeight(int n2) {
        return this.hideTabArea() ? (int)((long)1862131590 ^ (long)1862131590) : super.calculateMaxTabHeight(n2);
    }

    @Override
    protected int calculateTabAreaWidth(int n2, int n3, int n4) {
        return this.hideTabArea() ? (int)((long)-621189833 ^ (long)-621189833) : super.calculateTabAreaWidth(n2, n3, n4);
    }

    @Override
    protected int calculateTabAreaHeight(int n2, int n3, int n4) {
        return this.hideTabArea() ? (int)1456319706L ^ 0x56CDACDA : super.calculateTabAreaHeight(n2, n3, n4);
    }

    @Override
    protected Insets getTabInsets(int n2, int n3) {
        Object object = this.getTabClientProperty(n3, "JTabbedPane.tabInsets");
        return UIScale.scale(object instanceof Insets ? (Insets)object : super.getTabInsets(n2, n3));
    }

    @Override
    protected Insets getSelectedTabPadInsets(int n2) {
        return new Insets((int)((long)-1845488040 ^ (long)-1845488040), (int)((long)-1612676103 ^ (long)-1612676103), (int)-430810025L ^ 0xE6525C57, (int)1537415236L ^ 0x5BA31844);
    }

    protected Insets getRealTabAreaInsets(int n2) {
        if (this.tabAreaInsets == null) {
            this.tabAreaInsets = new Insets((int)((long)-2034698143 ^ (long)-2034698143), (int)((long)1152857357 ^ (long)1152857357), (int)-1013274801L ^ 0xC39AA74F, (int)1190628413L ^ 0x46F78C3D);
        }
        Insets insets = super.getTabAreaInsets(n2);
        Insets insets2 = (Insets)insets.clone();
        Object object = this.tabPane.getClientProperty("JTabbedPane.tabAreaInsets");
        if (object instanceof Insets) {
            FlatTabbedPaneUI.rotateInsets((Insets)object, insets2, n2);
        }
        insets.top = insets.left = (int)((long)1235086656 ^ (long)-1235077712);
        insets2 = UIScale.scale(insets2);
        return insets2;
    }

    @Override
    protected Insets getTabAreaInsets(int n2) {
        Insets insets = this.getRealTabAreaInsets(n2);
        if (this.tabPane.getTabLayoutPolicy() == 0) {
            if (this.isHorizontalTabPlacement()) {
                insets.left += this.getLeadingPreferredWidth();
                insets.right += this.getTrailingPreferredWidth();
            } else {
                insets.top += this.getLeadingPreferredHeight();
                insets.bottom += this.getTrailingPreferredHeight();
            }
        }
        return insets;
    }

    @Override
    protected Insets getContentBorderInsets(int n2) {
        if (this.hideTabArea() || this.contentSeparatorHeight == 0 || !FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.showContentSeparator", ((int)-1832093932L ^ 0x92CC7715) != 0)) {
            return new Insets((int)-1881617394L ^ 0x8FD8CC0E, (int)-60370135L ^ 0xFC66D329, (int)1709391994L ^ 0x65E3407A, (int)((long)18993178 ^ (long)18993178));
        }
        boolean bl = FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.hasFullBorder", this.hasFullBorder);
        int n3 = UIScale.scale(this.contentSeparatorHeight);
        Insets insets = bl ? new Insets(n3, n3, n3, n3) : new Insets(n3, (int)((long)-410234333 ^ (long)-410234333), (int)((long)-1988475999 ^ (long)-1988475999), (int)((long)-45359402 ^ (long)-45359402));
        Insets insets2 = new Insets((int)((long)1129643115 ^ (long)1129643115), (int)-434442694L ^ 0xE61AEE3A, (int)-609702003L ^ 0xDBA8AF8D, (int)-63606856L ^ 0xFC356FB8);
        FlatTabbedPaneUI.rotateInsets(insets, insets2, n2);
        return insets2;
    }

    @Override
    protected int getTabLabelShiftX(int n2, int n3, boolean bl) {
        if (this.isTabClosable(n3)) {
            int n4 = this.closeIcon.getIconWidth() / (((int)-1074337145L ^ 0xBFF6EA86) << 1);
            return this.isLeftToRight() ? -n4 : n4;
        }
        return (int)-1317046783L ^ 0xB17F7601;
    }

    @Override
    protected int getTabLabelShiftY(int n2, int n3, boolean bl) {
        return (int)((long)453303097 ^ (long)453303097);
    }

    @Override
    public void update(Graphics graphics, JComponent jComponent) {
        this.oldRenderingHints = FlatUIUtils.setRenderingHints(graphics);
        super.update(graphics, jComponent);
        FlatUIUtils.resetRenderingHints(graphics, this.oldRenderingHints);
        this.oldRenderingHints = null;
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        if (this.hideTabArea()) {
            return;
        }
        this.ensureCurrentLayout();
        int n2 = this.tabPane.getTabPlacement();
        int n3 = this.tabPane.getSelectedIndex();
        this.paintContentBorder(graphics, n2, n3);
        if (!this.isScrollTabLayout()) {
            this.paintTabArea(graphics, n2, n3);
        }
    }

    @Override
    protected void paintTab(Graphics graphics, int n2, Rectangle[] arrrectangle, int n3, Rectangle rectangle, Rectangle rectangle2) {
        int n4;
        int n5;
        Rectangle rectangle3 = arrrectangle[n3];
        int n6 = rectangle3.x;
        int n7 = rectangle3.y;
        int n8 = rectangle3.width;
        int n9 = rectangle3.height;
        int n10 = n5 = n3 == this.tabPane.getSelectedIndex() ? (int)((long)-1682376648 ^ (long)-1682376647) : (int)2098260020L ^ 0x7D10E834;
        if (this.tabsOpaque || this.tabPane.isOpaque()) {
            this.paintTabBackground(graphics, n2, n3, n6, n7, n8, n9, n5 != 0);
        }
        this.paintTabBorder(graphics, n2, n3, n6, n7, n8, n9, n5 != 0);
        if (this.isTabClosable(n3)) {
            this.paintTabCloseButton(graphics, n3, n6, n7, n8, n9);
        }
        if (n5 != 0) {
            this.paintTabSelection(graphics, n2, n6, n7, n8, n9);
        }
        if (this.tabPane.getTabComponentAt(n3) != null) {
            return;
        }
        String string = this.tabPane.getTitleAt(n3);
        Icon icon = this.getIconForTab(n3);
        Font font = this.tabPane.getFont();
        FontMetrics fontMetrics = this.tabPane.getFontMetrics(font);
        int n11 = n4 = icon != null && n5 == 0 && this.getTabWidthMode() == (int)((long)1503777175 ^ (long)1503777174) << 1 && this.isHorizontalTabPlacement() ? (int)((long)1929924276 ^ (long)1929924277) : (int)420574580L ^ 0x19117574;
        if (n4 != 0) {
            string = null;
        }
        String string2 = this.layoutAndClipLabel(n2, fontMetrics, n3, string, icon, rectangle3, rectangle, rectangle2, n5 != 0);
        if (this.tabViewport != null && (n2 == ((int)109150072L ^ 0x6817F79) || n2 == ((int)705862072L ^ 0x2A1299BB))) {
            Rectangle rectangle4 = this.tabViewport.getViewRect();
            rectangle4.width -= ((int)-254885941L ^ 0xF0CEBFCA) << 2;
            if (!rectangle4.contains(rectangle2)) {
                Rectangle rectangle5 = rectangle4.intersection(rectangle2);
                if (rectangle5.x > rectangle4.x) {
                    string2 = JavaCompatibility.getClippedString(null, fontMetrics, string, rectangle5.width);
                }
            }
        }
        if (n4 == 0) {
            this.paintText(graphics, n2, font, fontMetrics, n3, string2, rectangle2, n5 != 0);
        }
        this.paintIcon(graphics, n2, n3, icon, rectangle, n5 != 0);
    }

    @Override
    protected void paintText(Graphics graphics, int n2, Font font, FontMetrics fontMetrics, int n3, String string, Rectangle rectangle, boolean bl) {
        graphics.setFont(font);
        FlatUIUtils.runWithoutRenderingHints(graphics, this.oldRenderingHints, () -> {
            Color color;
            View view = this.getTextViewForTab(n3);
            if (view != null) {
                view.paint(graphics, rectangle);
                return;
            }
            if (this.tabPane.isEnabled() && this.tabPane.isEnabledAt(n3)) {
                color = this.tabPane.getForegroundAt(n3);
                if (bl && this.selectedForeground != null && color == this.tabPane.getForeground()) {
                    color = this.selectedForeground;
                }
            } else {
                color = this.disabledForeground;
            }
            int n3 = FlatLaf.isShowMnemonics() ? this.tabPane.getDisplayedMnemonicIndexAt(n3) : (int)((long)-1270564343 ^ (long)1270564342);
            graphics.setColor(color);
            FlatUIUtils.drawStringUnderlineCharAt(this.tabPane, graphics, string, n3, rectangle.x, rectangle.y + fontMetrics.getAscent());
        });
    }

    @Override
    protected void paintTabBackground(Graphics graphics, int n2, int n3, int n4, int n5, int n6, int n7, boolean bl) {
        boolean bl2 = this.tabPane.isEnabled();
        Color color = bl2 && this.tabPane.isEnabledAt(n3) && this.getRolloverTab() == n3 ? this.hoverColor : (bl2 && bl && FlatUIUtils.isPermanentFocusOwner(this.tabPane) ? this.focusColor : (this.selectedBackground != null && bl2 && bl ? this.selectedBackground : this.tabPane.getBackgroundAt(n3)));
        graphics.setColor(FlatUIUtils.deriveColor(color, this.tabPane.getBackground()));
        graphics.fillRect(n4, n5, n6, n7);
    }

    @Override
    protected void paintTabBorder(Graphics graphics, int n2, int n3, int n4, int n5, int n6, int n7, boolean bl) {
        if (FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.showTabSeparators", this.showTabSeparators) && !this.isLastInRun(n3)) {
            this.paintTabSeparator(graphics, n2, n4, n5, n6, n7);
        }
    }

    protected void paintTabCloseButton(Graphics graphics, int n2, int n3, int n4, int n5, int n6) {
        if (this.tabCloseButton == null) {
            this.tabCloseButton = new TabCloseButton();
            this.tabCloseButton.setVisible(((int)-1988699484L ^ 0x8976DAA4) != 0);
        }
        int n7 = n2 == this.getRolloverTab() ? (int)((long)-1641749469 ^ (long)-1641749470) : (int)527519902L ^ 0x1F71509E;
        ButtonModel buttonModel = this.tabCloseButton.getModel();
        buttonModel.setRollover((n7 != 0 && this.isRolloverTabClose() ? (int)((long)-177607168 ^ (long)-177607167) : (int)((long)-1840906466 ^ (long)-1840906466)) != 0);
        buttonModel.setPressed((n7 != 0 && this.isPressedTabClose() ? (int)-1702985888L ^ 0x9A7E7F61 : (int)((long)1276876872 ^ (long)1276876872)) != 0);
        this.tabCloseButton.setBackground(this.tabPane.getBackground());
        this.tabCloseButton.setForeground(this.tabPane.getForeground());
        Rectangle rectangle = this.getTabCloseBounds(n2, n3, n4, n5, n6, this.calcRect);
        this.closeIcon.paintIcon(this.tabCloseButton, graphics, rectangle.x, rectangle.y);
    }

    protected void paintTabSeparator(Graphics graphics, int n2, int n3, int n4, int n5, int n6) {
        float f2 = UIScale.scale(1.0f);
        float f3 = this.tabSeparatorsFullHeight ? 0.0f : UIScale.scale(Float.intBitsToFloat((int)((long)509004018 ^ (long)1593231602)));
        graphics.setColor(this.tabSeparatorColor != null ? this.tabSeparatorColor : this.contentAreaColor);
        if (n2 == (int)((long)1115453702 ^ (long)1115453703) << 1 || n2 == (int)((long)1139898165 ^ (long)1139898164) << 2) {
            ((Graphics2D)graphics).fill(new Rectangle2D.Float((float)n3 + f3, (float)(n4 + n6) - f2, (float)n5 - f3 * 2.0f, f2));
        } else if (this.isLeftToRight()) {
            ((Graphics2D)graphics).fill(new Rectangle2D.Float((float)(n3 + n5) - f2, (float)n4 + f3, f2, (float)n6 - f3 * 2.0f));
        } else {
            ((Graphics2D)graphics).fill(new Rectangle2D.Float(n3, (float)n4 + f3, f2, (float)n6 - f3 * 2.0f));
        }
    }

    protected void paintTabSelection(Graphics graphics, int n2, int n3, int n4, int n5, int n6) {
        graphics.setColor(this.tabPane.isEnabled() ? this.underlineColor : this.disabledUnderlineColor);
        Insets insets = this.getContentBorderInsets(n2);
        int n7 = UIScale.scale(this.tabSelectionHeight);
        switch (n2) {
            default: {
                int n8 = n4 + n6 + insets.top - n7;
                graphics.fillRect(n3, n8, n5, n7);
                break;
            }
            case 3: {
                graphics.fillRect(n3, n4 - insets.bottom, n5, n7);
                break;
            }
            case 2: {
                int n9 = n3 + n5 + insets.left - n7;
                graphics.fillRect(n9, n4, n7, n6);
                break;
            }
            case 4: {
                graphics.fillRect(n3 - insets.right, n4, n7, n6);
            }
        }
    }

    @Override
    protected void paintContentBorder(Graphics graphics, int n2, int n3) {
        if (this.tabPane.getTabCount() <= 0 || this.contentSeparatorHeight == 0 || !FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.showContentSeparator", (boolean)((long)-254923647 ^ (long)-254923648))) {
            return;
        }
        Insets insets = this.tabPane.getInsets();
        Insets insets2 = this.getTabAreaInsets(n2);
        int n4 = insets.left;
        int n5 = insets.top;
        int n6 = this.tabPane.getWidth() - insets.right - insets.left;
        int n7 = this.tabPane.getHeight() - insets.top - insets.bottom;
        switch (n2) {
            default: {
                n5 += this.calculateTabAreaHeight(n2, this.runCount, this.maxTabHeight);
                n7 -= (n5 -= insets2.bottom) - insets.top;
                break;
            }
            case 3: {
                n7 -= this.calculateTabAreaHeight(n2, this.runCount, this.maxTabHeight);
                n7 += insets2.top;
                break;
            }
            case 2: {
                n4 += this.calculateTabAreaWidth(n2, this.runCount, this.maxTabWidth);
                n6 -= (n4 -= insets2.right) - insets.left;
                break;
            }
            case 4: {
                n6 -= this.calculateTabAreaWidth(n2, this.runCount, this.maxTabWidth);
                n6 += insets2.left;
            }
        }
        boolean bl = FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.hasFullBorder", this.hasFullBorder);
        int n8 = UIScale.scale(this.contentSeparatorHeight * (((int)154192438L ^ 0x930CA2F) << 2));
        Insets insets3 = new Insets((int)((long)-407702981 ^ (long)-407702981), (int)((long)-852982985 ^ (long)-852982985), (int)((long)306465292 ^ (long)306465292), (int)-1939472772L ^ 0x8C65FE7C);
        FlatTabbedPaneUI.rotateInsets(bl ? new Insets(n8, n8, n8, n8) : new Insets(n8, (int)((long)-2007213754 ^ (long)-2007213754), (int)-1116892963L ^ 0xBD6D90DD, (int)((long)-7626070 ^ (long)-7626070)), insets3, n2);
        graphics.setColor(this.contentAreaColor);
        Path2D.Float float_ = new Path2D.Float((int)((long)1068750168 ^ (long)1068750168));
        float_.append(new Rectangle2D.Float(n4, n5, n6, n7), (boolean)((long)2092394311 ^ (long)2092394311));
        float_.append(new Rectangle2D.Float((float)n4 + (float)insets3.left / Float.intBitsToFloat((int)((long)1473096142 ^ (long)352692686)), (float)n5 + (float)insets3.top / Float.intBitsToFloat((int)((long)2139222616 ^ (long)1028256344)), (float)n6 - (float)insets3.left / Float.intBitsToFloat((int)((long)2112903925 ^ (long)1060657909)) - (float)insets3.right / Float.intBitsToFloat(0x14D4ABF4 ^ 0x561CABF4), (float)n7 - (float)insets3.top / Float.intBitsToFloat(0x1136FF29 ^ 0x53FEFF29) - (float)insets3.bottom / Float.intBitsToFloat((int)((long)594185722 ^ (long)1638043130))), (boolean)((long)2030932196 ^ (long)2030932196));
        ((Graphics2D)graphics).fill(float_);
        if (this.isScrollTabLayout() && n3 >= 0 && this.tabViewport != null) {
            Rectangle rectangle = this.getTabBounds(this.tabPane, n3);
            Shape shape = graphics.getClip();
            Rectangle rectangle2 = this.tabViewport.getBounds();
            if (this.isHorizontalTabPlacement()) {
                graphics.clipRect(rectangle2.x, (int)17525252L ^ 0x10B6A04, rectangle2.width, this.tabPane.getHeight());
            } else {
                graphics.clipRect((int)862093075L ^ 0x33627F13, rectangle2.y, this.tabPane.getWidth(), rectangle2.height);
            }
            this.paintTabSelection(graphics, n2, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            graphics.setClip(shape);
        }
    }

    @Override
    protected void paintFocusIndicator(Graphics graphics, int n2, Rectangle[] arrrectangle, int n3, Rectangle rectangle, Rectangle rectangle2, boolean bl) {
    }

    protected String layoutAndClipLabel(int n2, FontMetrics fontMetrics, int n3, String string, Icon icon, Rectangle rectangle, Rectangle rectangle2, Rectangle rectangle3, boolean bl) {
        int n4;
        int n5;
        rectangle = FlatUIUtils.subtractInsets(rectangle, this.getTabInsets(n2, n3));
        if (this.isTabClosable(n3)) {
            rectangle.width -= this.closeIcon.getIconWidth();
            if (!this.isLeftToRight()) {
                rectangle.x += this.closeIcon.getIconWidth();
            }
        }
        switch (FlatClientProperties.clientPropertyInt(this.tabPane, "JTabbedPane.tabIconPlacement", (int)((long)-725381797 ^ (long)-725381794) << 1)) {
            default: {
                n5 = (int)1490630893L ^ 0x58D938ED;
                n4 = (int)1244471210L ^ 0x4A2D1FA1;
                break;
            }
            case 11: {
                n5 = (int)((long)479084192 ^ (long)479084192);
                n4 = (int)((long)98338884 ^ (long)98338881) << 1;
                break;
            }
            case 1: {
                n5 = (int)((long)1927998713 ^ (long)1927998714);
                n4 = (int)-1209601094L ^ 0xB7E6F3BA;
                break;
            }
            case 3: {
                n5 = (int)((long)-269047718 ^ (long)-269047717);
                n4 = (int)1715955470L ^ 0x6647670E;
            }
        }
        rectangle3.setBounds((int)-630577247L ^ 0xDA6A27A1, (int)1750791805L ^ 0x685AF67D, (int)734556468L ^ 0x2BC87134, (int)((long)-813902828 ^ (long)-813902828));
        rectangle2.setBounds((int)1688275701L ^ 0x64A10AF5, (int)-1604701023L ^ 0xA05A34A1, (int)-663499879L ^ 0xD873CB99, (int)-404281990L ^ 0xE7E7257A);
        View view = this.getTextViewForTab(n3);
        if (view != null) {
            this.tabPane.putClientProperty("html", view);
        }
        String string2 = SwingUtilities.layoutCompoundLabel(this.tabPane, fontMetrics, string, icon, (int)((long)1797570332 ^ (long)1797570332), this.getTabAlignment(n3), n5, n4, rectangle, rectangle2, rectangle3, UIScale.scale(this.textIconGapUnscaled));
        this.tabPane.putClientProperty("html", null);
        return string2;
    }

    @Override
    public int tabForCoordinate(JTabbedPane jTabbedPane, int n2, int n3) {
        if (this.moreTabsButton != null) {
            Point point = this.tabViewport.getViewPosition();
            n2 = n2 - this.tabViewport.getX() + point.x;
            n3 = n3 - this.tabViewport.getY() + point.y;
            if (!this.tabViewport.getViewRect().contains(n2, n3)) {
                return (int)((long)147682598 ^ (long)-147682599);
            }
        }
        return super.tabForCoordinate(jTabbedPane, n2, n3);
    }

    @Override
    protected Rectangle getTabBounds(int n2, Rectangle rectangle) {
        if (this.moreTabsButton != null) {
            rectangle.setBounds(this.rects[n2]);
            Point point = this.tabViewport.getViewPosition();
            rectangle.x = rectangle.x + this.tabViewport.getX() - point.x;
            rectangle.y = rectangle.y + this.tabViewport.getY() - point.y;
            return rectangle;
        }
        return super.getTabBounds(n2, rectangle);
    }

    protected Rectangle getTabCloseBounds(int n2, int n3, int n4, int n5, int n6, Rectangle rectangle) {
        int n7 = this.closeIcon.getIconWidth();
        int n8 = this.closeIcon.getIconHeight();
        Insets insets = this.getTabInsets(this.tabPane.getTabPlacement(), n2);
        rectangle.x = this.isLeftToRight() ? n3 + n5 - insets.right / ((int)-318804260L ^ 0xECFF6EDF) * ((int)((long)-1916628915 ^ (long)-1916628916) << 1) - n7 : n3 + insets.left / ((int)2046437540L ^ 0x79FA28A7) * (((int)1312148180L ^ 0x4E35CAD5) << 1);
        rectangle.y = n4 + (n6 - n8) / (((int)-992167560L ^ 0xC4DCB979) << 1);
        rectangle.width = n7;
        rectangle.height = n8;
        return rectangle;
    }

    protected Rectangle getTabCloseHitArea(int n2) {
        Rectangle rectangle = this.getTabBounds(this.tabPane, n2);
        Rectangle rectangle2 = this.getTabCloseBounds(n2, rectangle.x, rectangle.y, rectangle.width, rectangle.height, this.calcRect);
        return new Rectangle(rectangle2.x, rectangle.y, rectangle2.width, rectangle.height);
    }

    protected boolean isTabClosable(int n2) {
        Object object = this.getTabClientProperty(n2, "JTabbedPane.tabClosable");
        return (object instanceof Boolean ? (Boolean)object : (int)560219123L ^ 0x216443F3) != 0;
    }

    protected void closeTab(int n2) {
        Object object = this.getTabClientProperty(n2, "JTabbedPane.tabCloseCallback");
        if (object instanceof IntConsumer) {
            ((IntConsumer)object).accept(n2);
        } else if (object instanceof BiConsumer) {
            ((BiConsumer)object).accept(this.tabPane, n2);
        } else {
            throw new RuntimeException("Missing tab close callback. Set client property 'JTabbedPane.tabCloseCallback' to a 'java.util.function.IntConsumer' or 'java.util.function.BiConsumer<JTabbedPane, Integer>'");
        }
    }

    protected Object getTabClientProperty(int n2, String string) {
        Object object;
        if (n2 < 0) {
            return null;
        }
        Component component = this.tabPane.getComponentAt(n2);
        if (component instanceof JComponent && (object = ((JComponent)component).getClientProperty(string)) != null) {
            return object;
        }
        return this.tabPane.getClientProperty(string);
    }

    protected int getTabClientPropertyInt(int n2, String string, int n3) {
        Object object = this.getTabClientProperty(n2, string);
        return object instanceof Integer ? (Integer)object : n3;
    }

    protected void ensureCurrentLayout() {
        super.getTabRunCount(this.tabPane);
    }

    private boolean isLastInRun(int n2) {
        int n3 = this.getRunForTab(this.tabPane.getTabCount(), n2);
        return (this.lastTabInRun(this.tabPane.getTabCount(), n3) == n2 ? (int)-1882810919L ^ 0x8FC695D8 : (int)((long)-924143340 ^ (long)-924143340)) != 0;
    }

    private boolean isScrollTabLayout() {
        return (this.tabPane.getTabLayoutPolicy() == ((int)-1178623178L ^ 0xB9BFA337) ? (int)-1981006841L ^ 0x89EC3C06 : (int)((long)-315551543 ^ (long)-315551543)) != 0;
    }

    private boolean isLeftToRight() {
        return this.tabPane.getComponentOrientation().isLeftToRight();
    }

    protected boolean isHorizontalTabPlacement() {
        int n2 = this.tabPane.getTabPlacement();
        return (n2 == ((int)-931959656L ^ 0xC8736C99) || n2 == (int)((long)-1911807724 ^ (long)-1911807721) ? (int)((long)-1046914411 ^ (long)-1046914412) : (int)((long)-1548552422 ^ (long)-1548552422)) != 0;
    }

    protected boolean isSmoothScrollingEnabled() {
        if (!Animator.useAnimation()) {
            return ((int)-815478969L ^ 0xCF64C747) != 0;
        }
        return UIManager.getBoolean("ScrollPane.smoothScrolling");
    }

    protected boolean hideTabArea() {
        return (this.tabPane.getTabCount() == ((int)48153086L ^ 0x2DEC1FF) && this.leadingComponent == null && this.trailingComponent == null && FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.hideTabAreaWithOneTab", ((int)-275439027L ^ 0xEF95224D) != 0) ? (int)((long)1768442669 ^ (long)1768442668) : (int)((long)378117608 ^ (long)378117608)) != 0;
    }

    protected int getTabsPopupPolicy() {
        Object object = this.tabPane.getClientProperty("JTabbedPane.tabsPopupPolicy");
        return object instanceof String ? FlatTabbedPaneUI.parseTabsPopupPolicy((String)object) : this.tabsPopupPolicy;
    }

    protected int getScrollButtonsPolicy() {
        Object object = this.tabPane.getClientProperty("JTabbedPane.scrollButtonsPolicy");
        return object instanceof String ? FlatTabbedPaneUI.parseScrollButtonsPolicy((String)object) : this.scrollButtonsPolicy;
    }

    protected int getScrollButtonsPlacement() {
        Object object = this.tabPane.getClientProperty("JTabbedPane.scrollButtonsPlacement");
        return object instanceof String ? FlatTabbedPaneUI.parseScrollButtonsPlacement((String)object) : this.scrollButtonsPlacement;
    }

    protected int getTabAreaAlignment() {
        Object object = this.tabPane.getClientProperty("JTabbedPane.tabAreaAlignment");
        if (object instanceof Integer) {
            return (Integer)object;
        }
        return object instanceof String ? FlatTabbedPaneUI.parseAlignment((String)object, ((int)1203341286L ^ 0x47B987E3) << 1) : this.tabAreaAlignment;
    }

    protected int getTabAlignment(int n2) {
        Object object = this.getTabClientProperty(n2, "JTabbedPane.tabAlignment");
        if (object instanceof Integer) {
            return (Integer)object;
        }
        return object instanceof String ? FlatTabbedPaneUI.parseAlignment((String)object, (int)((long)2108473589 ^ (long)2108473589)) : this.tabAlignment;
    }

    protected int getTabWidthMode() {
        Object object = this.tabPane.getClientProperty("JTabbedPane.tabWidthMode");
        return object instanceof String ? FlatTabbedPaneUI.parseTabWidthMode((String)object) : this.tabWidthMode;
    }

    /*
     * Exception decompiling
     */
    protected static int parseTabsPopupPolicy(String var0) {
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

    /*
     * Exception decompiling
     */
    protected static int parseScrollButtonsPolicy(String var0) {
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

    /*
     * Exception decompiling
     */
    protected static int parseScrollButtonsPlacement(String var0) {
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

    /*
     * Exception decompiling
     */
    protected static int parseAlignment(String var0, int var1_1) {
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

    /*
     * Exception decompiling
     */
    protected static int parseTabWidthMode(String var0) {
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

    private void runWithOriginalLayoutManager(Runnable runnable) {
        LayoutManager layoutManager = this.tabPane.getLayout();
        if (layoutManager instanceof FlatTabbedPaneScrollLayout) {
            this.tabPane.setLayout(((FlatTabbedPaneScrollLayout)layoutManager).delegate);
            runnable.run();
            this.tabPane.setLayout(layoutManager);
        } else {
            runnable.run();
        }
    }

    protected void ensureSelectedTabIsVisibleLater() {
        if (!this.tabPane.isDisplayable() || !EventQueue.isDispatchThread()) {
            return;
        }
        EventQueue.invokeLater(() -> this.ensureSelectedTabIsVisible());
    }

    protected void ensureSelectedTabIsVisible() {
        if (this.tabPane == null || this.tabViewport == null || !this.tabPane.isDisplayable()) {
            return;
        }
        this.ensureCurrentLayout();
        int n2 = this.tabPane.getSelectedIndex();
        if (n2 < 0 || n2 >= this.rects.length) {
            return;
        }
        ((JComponent)this.tabViewport.getView()).scrollRectToVisible((Rectangle)this.rects[n2].clone());
    }

    private int getLeadingPreferredWidth() {
        return this.leadingComponent != null ? this.leadingComponent.getPreferredSize().width : (int)((long)-49950076 ^ (long)-49950076);
    }

    private int getLeadingPreferredHeight() {
        return this.leadingComponent != null ? this.leadingComponent.getPreferredSize().height : (int)-721642610L ^ 0xD4FC9B8E;
    }

    private int getTrailingPreferredWidth() {
        return this.trailingComponent != null ? this.trailingComponent.getPreferredSize().width : (int)((long)-1593948992 ^ (long)-1593948992);
    }

    private int getTrailingPreferredHeight() {
        return this.trailingComponent != null ? this.trailingComponent.getPreferredSize().height : (int)542393660L ^ 0x2054453C;
    }

    private void shiftTabs(int n2, int n3) {
        if (n2 == 0 && n3 == 0) {
            return;
        }
        for (int i2 = (int)1234828122L ^ 0x4999FB5A; i2 < this.rects.length; ++i2) {
            this.rects[i2].x += n2;
            this.rects[i2].y += n3;
            Component component = this.tabPane.getTabComponentAt(i2);
            if (component == null) continue;
            component.setLocation(component.getX() + n2, component.getY() + n3);
        }
    }

    private void stretchTabsWidth(int n2, boolean bl) {
        int n3;
        int n4 = n2 / this.rects.length;
        int n5 = this.rects[(int)((long)0x111D811D ^ (long)0x111D811D)].x - (bl ? (int)((long)-661515558 ^ (long)-661515558) : n4);
        for (n3 = (int)-1767838955L ^ 0x96A0EB15; n3 < this.rects.length; ++n3) {
            Component component = this.tabPane.getTabComponentAt(n3);
            if (component != null) {
                component.setLocation(n5 + (component.getX() - this.rects[n3].x) + n4 / ((int)((long)797267665 ^ (long)797267664) << 1), component.getY());
            }
            this.rects[n3].x = n5;
            this.rects[n3].width += n4;
            if (bl) {
                n5 += this.rects[n3].width;
                continue;
            }
            if (n3 + (int)((long)726754418 ^ (long)726754419) >= this.rects.length) continue;
            n5 = this.rects[n3].x - this.rects[n3 + ((int)280883504L ^ 280883505)].width - n4;
        }
        n3 = n2 - n4 * this.rects.length;
        this.rects[this.rects.length - (int)((long)-125289827 ^ (long)-125289828)].width += n3;
        if (!bl) {
            this.rects[this.rects.length - ((int)-2043978296L ^ -2043978295)].x -= n3;
        }
    }

    private void stretchTabsHeight(int n2) {
        int n3 = n2 / this.rects.length;
        int n4 = this.rects[(int)405741095L ^ 405741095].y;
        for (int i2 = (int)((long)-1712252242 ^ (long)-1712252242); i2 < this.rects.length; ++i2) {
            Component component = this.tabPane.getTabComponentAt(i2);
            if (component != null) {
                component.setLocation(component.getX(), n4 + (component.getY() - this.rects[i2].y) + n3 / ((int)((long)1918934488 ^ (long)1918934489) << 1));
            }
            this.rects[i2].y = n4;
            this.rects[i2].height += n3;
            n4 += this.rects[i2].height;
        }
        this.rects[this.rects.length - ((int)-1622075070L ^ -1622075069)].height += n2 - n3 * this.rects.length;
    }

    private int rectsTotalWidth(boolean bl) {
        int n2 = this.rects.length - (int)((long)-857425818 ^ (long)-857425817);
        return bl ? this.rects[n2].x + this.rects[n2].width - this.rects[(int)((long)-315165909 ^ (long)-315165909)].x : this.rects[(int)((long)-1598364391 ^ (long)-1598364391)].x + this.rects[(int)((long)-236533091 ^ (long)-236533091)].width - this.rects[n2].x;
    }

    private int rectsTotalHeight() {
        int n2 = this.rects.length - (int)((long)-997405966 ^ (long)-997405965);
        return this.rects[n2].y + this.rects[n2].height - this.rects[(int)-928389414L ^ -928389414].y;
    }

    static /* synthetic */ JTabbedPane access$5100(FlatTabbedPaneUI flatTabbedPaneUI) {
        return flatTabbedPaneUI.tabPane;
    }

    static /* synthetic */ JTabbedPane access$5200(FlatTabbedPaneUI flatTabbedPaneUI) {
        return flatTabbedPaneUI.tabPane;
    }

    static /* synthetic */ JTabbedPane access$5300(FlatTabbedPaneUI flatTabbedPaneUI) {
        return flatTabbedPaneUI.tabPane;
    }

    static /* synthetic */ JTabbedPane access$5400(FlatTabbedPaneUI flatTabbedPaneUI) {
        return flatTabbedPaneUI.tabPane;
    }

    static /* synthetic */ JTabbedPane access$5500(FlatTabbedPaneUI flatTabbedPaneUI) {
        return flatTabbedPaneUI.tabPane;
    }

    static /* synthetic */ JTabbedPane access$5600(FlatTabbedPaneUI flatTabbedPaneUI) {
        return flatTabbedPaneUI.tabPane;
    }

    static /* synthetic */ JTabbedPane access$5700(FlatTabbedPaneUI flatTabbedPaneUI) {
        return flatTabbedPaneUI.tabPane;
    }

    static /* synthetic */ JTabbedPane access$5800(FlatTabbedPaneUI flatTabbedPaneUI) {
        return flatTabbedPaneUI.tabPane;
    }

    protected class FlatTabbedPaneScrollLayout
    extends FlatTabbedPaneLayout
    implements LayoutManager {
        private final BasicTabbedPaneUI.TabbedPaneLayout delegate;

        protected FlatTabbedPaneScrollLayout(BasicTabbedPaneUI.TabbedPaneLayout tabbedPaneLayout) {
            this.delegate = tabbedPaneLayout;
        }

        @Override
        public void calculateLayoutInfo() {
            this.delegate.calculateLayoutInfo();
        }

        @Override
        protected Dimension calculateTabAreaSize() {
            Dimension dimension = super.calculateTabAreaSize();
            if (FlatTabbedPaneUI.this.isHorizontalTabPlacement()) {
                dimension.width = Math.min(dimension.width, UIScale.scale(((int)1059977888L ^ 0x3F2DFAB9) << 2));
            } else {
                dimension.height = Math.min(dimension.height, UIScale.scale((int)((long)322245857 ^ (long)322245880) << 2));
            }
            return dimension;
        }

        @Override
        public Dimension preferredLayoutSize(Container container) {
            if (this.isContentEmpty()) {
                return this.calculateTabAreaSize();
            }
            return this.delegate.preferredLayoutSize(container);
        }

        @Override
        public Dimension minimumLayoutSize(Container container) {
            if (this.isContentEmpty()) {
                return this.calculateTabAreaSize();
            }
            return this.delegate.minimumLayoutSize(container);
        }

        @Override
        public void addLayoutComponent(String string, Component component) {
            this.delegate.addLayoutComponent(string, component);
        }

        @Override
        public void removeLayoutComponent(Component component) {
            this.delegate.removeLayoutComponent(component);
        }

        @Override
        public void layoutContainer(Container container) {
            int n2;
            Serializable serializable;
            int n3;
            FlatTabbedPaneUI.this.runWithOriginalLayoutManager(() -> this.delegate.layoutContainer(container));
            int n4 = FlatTabbedPaneUI.this.getTabsPopupPolicy();
            int n5 = FlatTabbedPaneUI.this.getScrollButtonsPolicy();
            int n6 = FlatTabbedPaneUI.this.getScrollButtonsPlacement();
            int n7 = n4 == (int)((long)-1361232685 ^ (long)-1361232686) << 1 ? (int)((long)-1157236633 ^ (long)-1157236634) : (int)((long)-1641219391 ^ (long)-1641219391);
            int n8 = n5 == (int)((long)-1632712590 ^ (long)-1632712589) << 1 || n5 == ((int)287364656L ^ 0x1120D633) ? (int)((long)-790576848 ^ (long)-790576847) : (int)((long)-60156821 ^ (long)-60156821);
            int n9 = n5 == (int)((long)-1283769839 ^ (long)-1283769838) && n6 == ((int)1106881606L ^ 0x41F9AC5F) << 2 ? (int)1063207732L ^ 0x3F5F4335 : (int)-1651680971L ^ 0x9D8D5935;
            int n10 = n6 == (int)((long)1392028454 ^ (long)1392028461) ? (int)((long)-173331134 ^ (long)-173331133) : (int)((long)-835922896 ^ (long)-835922896);
            boolean bl = FlatTabbedPaneUI.this.isLeftToRight();
            if (!bl && FlatTabbedPaneUI.this.isHorizontalTabPlacement()) {
                n7 = (int)((long)2012103385 ^ (long)2012103384);
                n8 = (int)1066410296L ^ 0x3F902138;
            }
            JButton jButton = null;
            JButton jButton2 = null;
            Object object = FlatTabbedPaneUI.this.tabPane.getComponents();
            int n11 = ((Component[])object).length;
            for (n3 = (int)-1870072227L ^ 0x9088F65D; n3 < n11; ++n3) {
                Component component = object[n3];
                if (!(component instanceof FlatScrollableTabButton)) continue;
                int n12 = ((FlatScrollableTabButton)component).getDirection();
                if (n12 == ((int)-1117511559L ^ 0xBD64207E) || n12 == (int)((long)-528513380 ^ (long)-528513379)) {
                    jButton = (JButton)component;
                    continue;
                }
                if (n12 != (int)((long)696247489 ^ (long)696247490) && n12 != (int)((long)349238694 ^ (long)349238691)) continue;
                jButton2 = (JButton)component;
            }
            if (jButton == null || jButton2 == null) {
                return;
            }
            object = FlatTabbedPaneUI.this.tabPane.getBounds();
            Insets insets = FlatTabbedPaneUI.this.tabPane.getInsets();
            n3 = FlatTabbedPaneUI.this.tabPane.getTabPlacement();
            int n13 = FlatTabbedPaneUI.this.getTabAreaAlignment();
            Insets insets2 = FlatTabbedPaneUI.this.getRealTabAreaInsets(n3);
            int n14 = (int)((long)1494113903 ^ (long)1494113903);
            int n15 = (int)-2014264826L ^ 0x87F0C206;
            int n16 = (int)2138137412L ^ 0x7F716344;
            if (insets2.left != 0 || insets2.top != 0) {
                FlatTabbedPaneUI.this.shiftTabs(-insets2.left, -insets2.top);
                serializable = FlatTabbedPaneUI.this.tabViewport.getView();
                Dimension dimension = ((Component)serializable).getPreferredSize();
                n2 = n3 == ((int)1181563769L ^ 0x466D3B78) || n3 == ((int)1607281032L ^ 0x5FCD298B) ? (int)-1724084542L ^ 0x993C8EC3 : (int)266540032L ^ 0xFE31400;
                ((Component)serializable).setPreferredSize(new Dimension(dimension.width - (n2 != 0 ? insets2.left : (int)((long)-599492860 ^ (long)-599492860)), dimension.height - (n2 != 0 ? (int)-304514941L ^ 0xEDD97883 : insets2.top)));
            }
            if (n3 == (int)((long)2058883430 ^ (long)2058883431) || n3 == (int)((long)1460652012 ^ (long)1460652015)) {
                int n17;
                int n18;
                int n19;
                if (n8 != 0 && n9 != 0) {
                    serializable = FlatTabbedPaneUI.this.tabViewport.getViewPosition();
                    if (((Point)serializable).x <= jButton.getPreferredSize().width) {
                        FlatTabbedPaneUI.this.tabViewport.setViewPosition(new Point((int)((long)345977412 ^ (long)345977412), ((Point)serializable).y));
                    }
                }
                int n20 = FlatTabbedPaneUI.this.maxTabHeight > 0 ? FlatTabbedPaneUI.this.maxTabHeight : Math.max(Math.max(FlatTabbedPaneUI.this.getLeadingPreferredHeight(), FlatTabbedPaneUI.this.getTrailingPreferredHeight()), UIScale.scale(FlatClientProperties.clientPropertyInt(FlatTabbedPaneUI.this.tabPane, "JTabbedPane.tabHeight", FlatTabbedPaneUI.this.tabHeight)));
                int n21 = insets.left;
                n2 = n3 == (int)((long)-1910703052 ^ (long)-1910703051) ? insets.top + insets2.top : object.height - insets.bottom - insets2.bottom - n20;
                int n22 = object.width - insets.left - insets.right;
                int n23 = n20;
                int n24 = FlatTabbedPaneUI.this.getLeadingPreferredWidth();
                int n25 = FlatTabbedPaneUI.this.getTrailingPreferredWidth();
                int n26 = n22 - n24 - n25 - insets2.left - insets2.right;
                int n27 = n19 = FlatTabbedPaneUI.this.rects.length > 0 ? FlatTabbedPaneUI.this.rectsTotalWidth(bl) : (int)((long)-1059413419 ^ (long)-1059413419);
                if (n19 < n26 && FlatTabbedPaneUI.this.rects.length > 0) {
                    int n28 = n26 - n19;
                    switch (n13) {
                        case 10: {
                            n25 += n28;
                            break;
                        }
                        case 11: {
                            n24 += n28;
                            break;
                        }
                        case 0: {
                            n24 += n28 / (((int)1876778866L ^ 0x6FDD5F73) << 1);
                            n25 += n28 - n28 / (((int)1153902857L ^ 0x44C72908) << 1);
                            break;
                        }
                        case 100: {
                            FlatTabbedPaneUI.this.stretchTabsWidth(n28, bl);
                            n19 = FlatTabbedPaneUI.this.rectsTotalWidth(bl);
                        }
                    }
                } else if (FlatTabbedPaneUI.this.rects.length == 0) {
                    n25 = n22 - n24;
                }
                Container container2 = bl ? FlatTabbedPaneUI.this.leadingComponent : FlatTabbedPaneUI.this.trailingComponent;
                int n29 = n18 = bl ? n24 : n25;
                if (container2 != null) {
                    container2.setBounds(n21, n2, n18, n23);
                }
                Container container3 = bl ? FlatTabbedPaneUI.this.trailingComponent : FlatTabbedPaneUI.this.leadingComponent;
                int n30 = n17 = bl ? n25 : n24;
                if (container3 != null) {
                    container3.setBounds(n21 + n22 - n17, n2, n17, n23);
                }
                if (FlatTabbedPaneUI.this.rects.length > 0) {
                    int n31 = n21 + n18 + (bl ? insets2.left : insets2.right);
                    int n32 = n22 - n18 - n17 - insets2.left - insets2.right;
                    int n33 = n31;
                    int n34 = n32;
                    if (n34 < n19) {
                        int n35;
                        if (n7 != 0) {
                            n35 = ((FlatTabbedPaneUI)FlatTabbedPaneUI.this).moreTabsButton.getPreferredSize().width;
                            FlatTabbedPaneUI.this.moreTabsButton.setBounds(bl ? n33 + n34 - n35 : n33, n2, n35, n23);
                            n33 += bl ? (int)2100373124L ^ 0x7D312684 : n35;
                            n34 -= n35;
                            n14 = (int)((long)399475461 ^ (long)399475460);
                        }
                        if (n8 != 0) {
                            if (n9 == 0 || jButton2.isEnabled()) {
                                n35 = jButton2.getPreferredSize().width;
                                jButton2.setBounds(bl ? n33 + n34 - n35 : n33, n2, n35, n23);
                                n33 += bl ? (int)((long)1992815590 ^ (long)1992815590) : n35;
                                n34 -= n35;
                                n16 = (int)-592065553L ^ 0xDCB5CBEE;
                            }
                            if (n9 == 0 || jButton.isEnabled()) {
                                n35 = jButton.getPreferredSize().width;
                                if (n10 != 0) {
                                    jButton.setBounds(bl ? n33 + n34 - n35 : n33, n2, n35, n23);
                                    n33 += bl ? (int)((long)1261109941 ^ (long)1261109941) : n35;
                                } else {
                                    jButton.setBounds(bl ? n33 : n33 + n34 - n35, n2, n35, n23);
                                    n33 += bl ? n35 : (int)((long)1926006751 ^ (long)1926006751);
                                }
                                n34 -= n35;
                                n15 = (int)((long)655434350 ^ (long)655434351);
                            }
                        }
                    }
                    FlatTabbedPaneUI.this.tabViewport.setBounds(n33, n2, n34, n23);
                    if (!bl) {
                        FlatTabbedPaneUI.this.tabViewport.doLayout();
                        FlatTabbedPaneUI.this.shiftTabs(FlatTabbedPaneUI.this.tabViewport.getView().getWidth() - (((FlatTabbedPaneUI)FlatTabbedPaneUI.this).rects[(int)((long)778923219 ^ (long)778923219)].x + ((FlatTabbedPaneUI)FlatTabbedPaneUI.this).rects[(int)-1026546055L ^ -1026546055].width), (int)-38227207L ^ 0xFDB8B2F9);
                    }
                }
            } else {
                int n36;
                int n37;
                if (n8 != 0 && n9 != 0) {
                    serializable = FlatTabbedPaneUI.this.tabViewport.getViewPosition();
                    if (((Point)serializable).y <= jButton.getPreferredSize().height) {
                        FlatTabbedPaneUI.this.tabViewport.setViewPosition(new Point(((Point)serializable).x, (int)1431029410L ^ 0x554BC6A2));
                    }
                }
                int n38 = FlatTabbedPaneUI.this.maxTabWidth > 0 ? FlatTabbedPaneUI.this.maxTabWidth : Math.max(FlatTabbedPaneUI.this.getLeadingPreferredWidth(), FlatTabbedPaneUI.this.getTrailingPreferredWidth());
                int n39 = n3 == (int)((long)-58468637 ^ (long)-58468638) << 1 ? insets.left + insets2.left : object.width - insets.right - insets2.right - n38;
                n2 = insets.top;
                int n40 = n38;
                int n41 = object.height - insets.top - insets.bottom;
                int n42 = FlatTabbedPaneUI.this.getLeadingPreferredHeight();
                int n43 = FlatTabbedPaneUI.this.getTrailingPreferredHeight();
                int n44 = n41 - n42 - n43 - insets2.top - insets2.bottom;
                int n45 = n37 = FlatTabbedPaneUI.this.rects.length > 0 ? FlatTabbedPaneUI.this.rectsTotalHeight() : (int)((long)9940963 ^ (long)9940963);
                if (n37 < n44 && FlatTabbedPaneUI.this.rects.length > 0) {
                    n36 = n44 - n37;
                    switch (n13) {
                        case 10: {
                            n43 += n36;
                            break;
                        }
                        case 11: {
                            n42 += n36;
                            break;
                        }
                        case 0: {
                            n42 += n36 / (((int)-1037932976L ^ 0xC2226651) << 1);
                            n43 += n36 - n36 / (((int)-1313542870L ^ 0xB1B4ED2B) << 1);
                            break;
                        }
                        case 100: {
                            FlatTabbedPaneUI.this.stretchTabsHeight(n36);
                            n37 = FlatTabbedPaneUI.this.rectsTotalHeight();
                        }
                    }
                } else if (FlatTabbedPaneUI.this.rects.length == 0) {
                    n43 = n41 - n42;
                }
                if (FlatTabbedPaneUI.this.leadingComponent != null) {
                    FlatTabbedPaneUI.this.leadingComponent.setBounds(n39, n2, n40, n42);
                }
                if (FlatTabbedPaneUI.this.trailingComponent != null) {
                    FlatTabbedPaneUI.this.trailingComponent.setBounds(n39, n2 + n41 - n43, n40, n43);
                }
                if (FlatTabbedPaneUI.this.rects.length > 0) {
                    n36 = n2 + n42 + insets2.top;
                    int n46 = n41 - n42 - n43 - insets2.top - insets2.bottom;
                    int n47 = n36;
                    int n48 = n46;
                    if (n48 < n37) {
                        int n49;
                        if (n7 != 0) {
                            n49 = ((FlatTabbedPaneUI)FlatTabbedPaneUI.this).moreTabsButton.getPreferredSize().height;
                            FlatTabbedPaneUI.this.moreTabsButton.setBounds(n39, n47 + n48 - n49, n40, n49);
                            n48 -= n49;
                            n14 = (int)((long)1154455290 ^ (long)1154455291);
                        }
                        if (n8 != 0) {
                            if (n9 == 0 || jButton2.isEnabled()) {
                                n49 = jButton2.getPreferredSize().height;
                                jButton2.setBounds(n39, n47 + n48 - n49, n40, n49);
                                n48 -= n49;
                                n16 = (int)432035012L ^ 0x19C054C5;
                            }
                            if (n9 == 0 || jButton.isEnabled()) {
                                n49 = jButton.getPreferredSize().height;
                                if (n10 != 0) {
                                    jButton.setBounds(n39, n47 + n48 - n49, n40, n49);
                                } else {
                                    jButton.setBounds(n39, n47, n40, n49);
                                    n47 += n49;
                                }
                                n48 -= n49;
                                n15 = (int)-11880604L ^ 0xFF4AB765;
                            }
                        }
                    }
                    FlatTabbedPaneUI.this.tabViewport.setBounds(n39, n47, n40, n48);
                }
            }
            FlatTabbedPaneUI.this.tabViewport.setVisible((FlatTabbedPaneUI.this.rects.length > 0 ? (int)((long)-606639087 ^ (long)-606639088) : (int)((long)917598359 ^ (long)917598359)) != 0);
            FlatTabbedPaneUI.this.moreTabsButton.setVisible(n14 != 0);
            jButton.setVisible(n15 != 0);
            jButton2.setVisible(n16 != 0);
            FlatTabbedPaneUI.this.scrollBackwardButtonPrefSize = jButton.getPreferredSize();
        }
    }

    protected class FlatTabbedPaneLayout
    extends BasicTabbedPaneUI.TabbedPaneLayout {
        protected FlatTabbedPaneLayout() {
            super(FlatTabbedPaneUI.this);
        }

        @Override
        protected Dimension calculateSize(boolean bl) {
            if (this.isContentEmpty()) {
                return this.calculateTabAreaSize();
            }
            return super.calculateSize(bl);
        }

        protected boolean isContentEmpty() {
            int n2 = FlatTabbedPaneUI.this.tabPane.getTabCount();
            if (n2 == 0) {
                return ((int)-1654801319L ^ 0x9D5DBC59) != 0;
            }
            for (int i2 = (int)-1403900819L ^ 0xAC522C6D; i2 < n2; ++i2) {
                Component component = FlatTabbedPaneUI.this.tabPane.getComponentAt(i2);
                if (component == null) continue;
                Dimension dimension = component.getPreferredSize();
                if (dimension.width == 0 && dimension.height == 0) continue;
                return (int)((long)722758767 ^ (long)722758767) != 0;
            }
            return (int)((long)1742330125 ^ (long)1742330124) != 0;
        }

        protected Dimension calculateTabAreaSize() {
            boolean bl = FlatTabbedPaneUI.this.isHorizontalTabPlacement();
            int n2 = FlatTabbedPaneUI.this.tabPane.getTabPlacement();
            FontMetrics fontMetrics = FlatTabbedPaneUI.this.getFontMetrics();
            int n3 = fontMetrics.getHeight();
            int n4 = (int)((long)155975875 ^ (long)155975875);
            int n5 = (int)((long)-327434527 ^ (long)-327434527);
            int n6 = FlatTabbedPaneUI.this.tabPane.getTabCount();
            for (int i2 = (int)-708250258L ^ 0xD5C8F56E; i2 < n6; ++i2) {
                if (bl) {
                    n4 += FlatTabbedPaneUI.this.calculateTabWidth(n2, i2, fontMetrics);
                    n5 = Math.max(n5, FlatTabbedPaneUI.this.calculateTabHeight(n2, i2, n3));
                    continue;
                }
                n4 = Math.max(n4, FlatTabbedPaneUI.this.calculateTabWidth(n2, i2, fontMetrics));
                n5 += FlatTabbedPaneUI.this.calculateTabHeight(n2, i2, n3);
            }
            if (bl) {
                n5 += UIScale.scale(FlatTabbedPaneUI.this.contentSeparatorHeight);
            } else {
                n4 += UIScale.scale(FlatTabbedPaneUI.this.contentSeparatorHeight);
            }
            Insets insets = FlatTabbedPaneUI.this.tabPane.getInsets();
            Insets insets2 = FlatTabbedPaneUI.this.getTabAreaInsets(n2);
            return new Dimension(n4 + insets.left + insets.right + insets2.left + insets2.right, n5 + insets.bottom + insets.top + insets2.top + insets2.bottom);
        }

        @Override
        public void layoutContainer(Container container) {
            super.layoutContainer(container);
            Rectangle rectangle = FlatTabbedPaneUI.this.tabPane.getBounds();
            Insets insets = FlatTabbedPaneUI.this.tabPane.getInsets();
            int n2 = FlatTabbedPaneUI.this.tabPane.getTabPlacement();
            int n3 = FlatTabbedPaneUI.this.getTabAreaAlignment();
            Insets insets2 = FlatTabbedPaneUI.this.getRealTabAreaInsets(n2);
            boolean bl = FlatTabbedPaneUI.this.isLeftToRight();
            if (n2 == (int)((long)-1375440807 ^ (long)-1375440808) || n2 == (int)((long)661548049 ^ (long)661548050)) {
                Container container2;
                Container container3;
                int n4;
                int n5;
                if (!bl) {
                    FlatTabbedPaneUI.this.shiftTabs(insets.left + insets2.right + FlatTabbedPaneUI.this.getTrailingPreferredWidth(), (int)-701695558L ^ 0xD62CF9BA);
                }
                int n6 = FlatTabbedPaneUI.this.maxTabHeight > 0 ? FlatTabbedPaneUI.this.maxTabHeight : Math.max(Math.max(FlatTabbedPaneUI.this.getLeadingPreferredHeight(), FlatTabbedPaneUI.this.getTrailingPreferredHeight()), UIScale.scale(FlatClientProperties.clientPropertyInt(FlatTabbedPaneUI.this.tabPane, "JTabbedPane.tabHeight", FlatTabbedPaneUI.this.tabHeight)));
                int n7 = insets.left;
                int n8 = n2 == (int)((long)-1860374341 ^ (long)-1860374342) ? insets.top + insets2.top : rectangle.height - insets.bottom - insets2.bottom - n6;
                int n9 = rectangle.width - insets.left - insets.right;
                int n10 = n6;
                int n11 = FlatTabbedPaneUI.this.getLeadingPreferredWidth();
                int n12 = FlatTabbedPaneUI.this.getTrailingPreferredWidth();
                if (FlatTabbedPaneUI.this.runCount == (int)((long)-186171676 ^ (long)-186171675) && FlatTabbedPaneUI.this.rects.length > 0) {
                    int n13 = n9 - n11 - n12 - insets2.left - insets2.right;
                    n5 = FlatTabbedPaneUI.this.rectsTotalWidth(bl);
                    n4 = n13 - n5;
                    switch (n3) {
                        case 10: {
                            n12 += n4;
                            break;
                        }
                        case 11: {
                            FlatTabbedPaneUI.this.shiftTabs(bl ? n4 : -n4, (int)((long)-1838713913 ^ (long)-1838713913));
                            n11 += n4;
                            break;
                        }
                        case 0: {
                            FlatTabbedPaneUI.this.shiftTabs((bl ? n4 : -n4) / (((int)627250927L ^ 0x256316EE) << 1), (int)-1620440564L ^ 0x9F6A0A0C);
                            n11 += n4 / (((int)-1534449166L ^ 0xA48A29F3) << 1);
                            n12 += n4 - n4 / ((int)((long)-518838813 ^ (long)-518838814) << 1);
                            break;
                        }
                        case 100: {
                            FlatTabbedPaneUI.this.stretchTabsWidth(n4, bl);
                        }
                    }
                } else if (FlatTabbedPaneUI.this.rects.length == 0) {
                    n12 = n9 - n11;
                }
                Container container4 = container3 = bl ? FlatTabbedPaneUI.this.leadingComponent : FlatTabbedPaneUI.this.trailingComponent;
                if (container3 != null) {
                    n5 = bl ? n11 : n12;
                    container3.setBounds(n7, n8, n5, n10);
                }
                Container container5 = container2 = bl ? FlatTabbedPaneUI.this.trailingComponent : FlatTabbedPaneUI.this.leadingComponent;
                if (container2 != null) {
                    n4 = bl ? n12 : n11;
                    container2.setBounds(n7 + n9 - n4, n8, n4, n10);
                }
            } else {
                int n14 = FlatTabbedPaneUI.this.maxTabWidth > 0 ? FlatTabbedPaneUI.this.maxTabWidth : Math.max(FlatTabbedPaneUI.this.getLeadingPreferredWidth(), FlatTabbedPaneUI.this.getTrailingPreferredWidth());
                int n15 = n2 == ((int)-42306314L ^ 0xFD7A74F7) << 1 ? insets.left + insets2.left : rectangle.width - insets.right - insets2.right - n14;
                int n16 = insets.top;
                int n17 = n14;
                int n18 = rectangle.height - insets.top - insets.bottom;
                int n19 = FlatTabbedPaneUI.this.getLeadingPreferredHeight();
                int n20 = FlatTabbedPaneUI.this.getTrailingPreferredHeight();
                if (FlatTabbedPaneUI.this.runCount == ((int)751567818L ^ 0x2CCC03CB) && FlatTabbedPaneUI.this.rects.length > 0) {
                    int n21 = n18 - n19 - n20 - insets2.top - insets2.bottom;
                    int n22 = FlatTabbedPaneUI.this.rectsTotalHeight();
                    int n23 = n21 - n22;
                    switch (n3) {
                        case 10: {
                            n20 += n23;
                            break;
                        }
                        case 11: {
                            FlatTabbedPaneUI.this.shiftTabs((int)((long)-1575286725 ^ (long)-1575286725), n23);
                            n19 += n23;
                            break;
                        }
                        case 0: {
                            FlatTabbedPaneUI.this.shiftTabs((int)374205219L ^ 0x164DEB23, n23 / (((int)331614996L ^ 0x13C40B15) << 1));
                            n19 += n23 / (((int)962809644L ^ 0x39634F2D) << 1);
                            n20 += n23 - n23 / (((int)-835094769L ^ 0xCE39770E) << 1);
                            break;
                        }
                        case 100: {
                            FlatTabbedPaneUI.this.stretchTabsHeight(n23);
                        }
                    }
                } else if (FlatTabbedPaneUI.this.rects.length == 0) {
                    n20 = n18 - n19;
                }
                if (FlatTabbedPaneUI.this.leadingComponent != null) {
                    FlatTabbedPaneUI.this.leadingComponent.setBounds(n15, n16, n17, n19);
                }
                if (FlatTabbedPaneUI.this.trailingComponent != null) {
                    FlatTabbedPaneUI.this.trailingComponent.setBounds(n15, n16 + n18 - n20, n17, n20);
                }
            }
        }
    }

    private class Handler
    implements MouseListener,
    MouseMotionListener,
    PropertyChangeListener,
    ChangeListener,
    ComponentListener,
    ContainerListener {
        MouseListener mouseDelegate;
        PropertyChangeListener propertyChangeDelegate;
        ChangeListener changeDelegate;
        private final PropertyChangeListener contentListener = this::contentPropertyChange;
        private int pressedTabIndex = (int)-1146645273L ^ 0x44586B18;
        private int lastTipTabIndex = (int)1052015304L ^ 0xC14B8537;
        private String lastTip;

        private Handler() {
        }

        void installListeners() {
            FlatTabbedPaneUI.this.tabPane.addMouseMotionListener(this);
            FlatTabbedPaneUI.this.tabPane.addComponentListener(this);
            FlatTabbedPaneUI.this.tabPane.addContainerListener(this);
            Component[] arrcomponent = FlatTabbedPaneUI.this.tabPane.getComponents();
            int n2 = arrcomponent.length;
            for (int i2 = (int)((long)-1530761541 ^ (long)-1530761541); i2 < n2; ++i2) {
                Component component = arrcomponent[i2];
                if (component instanceof UIResource) continue;
                component.addPropertyChangeListener(this.contentListener);
            }
        }

        void uninstallListeners() {
            FlatTabbedPaneUI.this.tabPane.removeMouseMotionListener(this);
            FlatTabbedPaneUI.this.tabPane.removeComponentListener(this);
            FlatTabbedPaneUI.this.tabPane.removeContainerListener(this);
            Component[] arrcomponent = FlatTabbedPaneUI.this.tabPane.getComponents();
            int n2 = arrcomponent.length;
            for (int i2 = (int)((long)459740011 ^ (long)459740011); i2 < n2; ++i2) {
                Component component = arrcomponent[i2];
                if (component instanceof UIResource) continue;
                component.removePropertyChangeListener(this.contentListener);
            }
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            this.mouseDelegate.mouseClicked(mouseEvent);
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            this.updateRollover(mouseEvent);
            if (!FlatTabbedPaneUI.this.isPressedTabClose()) {
                this.mouseDelegate.mousePressed(mouseEvent);
            }
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            if (FlatTabbedPaneUI.this.isPressedTabClose()) {
                this.updateRollover(mouseEvent);
                if (this.pressedTabIndex >= 0 && this.pressedTabIndex == FlatTabbedPaneUI.this.getRolloverTab()) {
                    this.restoreTabToolTip();
                    FlatTabbedPaneUI.this.closeTab(this.pressedTabIndex);
                }
            } else {
                this.mouseDelegate.mouseReleased(mouseEvent);
            }
            this.pressedTabIndex = (int)((long)862137020 ^ (long)-862137021);
            this.updateRollover(mouseEvent);
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            this.updateRollover(mouseEvent);
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            this.updateRollover(mouseEvent);
        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            this.updateRollover(mouseEvent);
        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            this.updateRollover(mouseEvent);
        }

        private void updateRollover(MouseEvent mouseEvent) {
            int n2;
            int n3 = mouseEvent.getX();
            int n4 = mouseEvent.getY();
            int n5 = FlatTabbedPaneUI.this.tabForCoordinate(FlatTabbedPaneUI.this.tabPane, n3, n4);
            FlatTabbedPaneUI.this.setRolloverTab(n5);
            int n6 = n2 = FlatTabbedPaneUI.this.isTabClosable(n5) ? (int)(FlatTabbedPaneUI.this.getTabCloseHitArea(n5).contains(n3, n4) ? 1 : 0) : (int)((long)-1899910261 ^ (long)-1899910261);
            if (mouseEvent.getID() == ((int)55614283L ^ 0x3509ABE)) {
                this.pressedTabIndex = n2 != 0 ? n5 : (int)((long)-1148218276 ^ (long)1148218275);
            }
            FlatTabbedPaneUI.this.setRolloverTabClose(n2 != 0);
            FlatTabbedPaneUI.this.setPressedTabClose((n2 != 0 && n5 == this.pressedTabIndex ? (int)((long)-1612660934 ^ (long)-1612660933) : (int)((long)655207140 ^ (long)655207140)) != 0);
            if (n5 >= 0 && n2 != 0) {
                Object object = FlatTabbedPaneUI.this.getTabClientProperty(n5, "JTabbedPane.tabCloseToolTipText");
                if (object instanceof String) {
                    this.setCloseToolTip(n5, (String)object);
                } else {
                    this.restoreTabToolTip();
                }
            } else {
                this.restoreTabToolTip();
            }
        }

        private void setCloseToolTip(int n2, String string) {
            if (n2 == this.lastTipTabIndex) {
                return;
            }
            if (n2 != this.lastTipTabIndex) {
                this.restoreTabToolTip();
            }
            this.lastTipTabIndex = n2;
            this.lastTip = FlatTabbedPaneUI.this.tabPane.getToolTipTextAt(this.lastTipTabIndex);
            FlatTabbedPaneUI.this.tabPane.setToolTipTextAt(this.lastTipTabIndex, string);
        }

        private void restoreTabToolTip() {
            if (this.lastTipTabIndex < 0) {
                return;
            }
            if (this.lastTipTabIndex < FlatTabbedPaneUI.this.tabPane.getTabCount()) {
                FlatTabbedPaneUI.this.tabPane.setToolTipTextAt(this.lastTipTabIndex, this.lastTip);
            }
            this.lastTip = null;
            this.lastTipTabIndex = (int)((long)975856380 ^ (long)-975856381);
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
        public void stateChanged(ChangeEvent changeEvent) {
            this.changeDelegate.stateChanged(changeEvent);
            if (FlatTabbedPaneUI.this.moreTabsButton != null) {
                FlatTabbedPaneUI.this.ensureSelectedTabIsVisible();
            }
        }

        /*
         * Exception decompiling
         */
        protected void contentPropertyChange(PropertyChangeEvent var1_1) {
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
        public void componentResized(ComponentEvent componentEvent) {
            FlatTabbedPaneUI.this.ensureSelectedTabIsVisibleLater();
        }

        @Override
        public void componentMoved(ComponentEvent componentEvent) {
        }

        @Override
        public void componentShown(ComponentEvent componentEvent) {
        }

        @Override
        public void componentHidden(ComponentEvent componentEvent) {
        }

        @Override
        public void componentAdded(ContainerEvent containerEvent) {
            Component component = containerEvent.getChild();
            if (!(component instanceof UIResource)) {
                component.addPropertyChangeListener(this.contentListener);
            }
        }

        @Override
        public void componentRemoved(ContainerEvent containerEvent) {
            Component component = containerEvent.getChild();
            if (!(component instanceof UIResource)) {
                component.removePropertyChangeListener(this.contentListener);
            }
        }

        private /* synthetic */ void lambda$propertyChange$0(PropertyChangeEvent propertyChangeEvent) {
            this.propertyChangeDelegate.propertyChange(propertyChangeEvent);
        }
    }

    protected class FlatWheelTabScroller
    extends MouseAdapter {
        private int lastMouseX;
        private int lastMouseY;
        private boolean inViewport;
        private boolean scrolled;
        private Timer rolloverTimer;
        private Timer exitedTimer;
        private Animator animator;
        private Point startViewPosition;
        private Point targetViewPosition;

        protected FlatWheelTabScroller() {
        }

        protected void uninstall() {
            if (this.rolloverTimer != null) {
                this.rolloverTimer.stop();
            }
            if (this.exitedTimer != null) {
                this.exitedTimer.stop();
            }
            if (this.animator != null) {
                this.animator.cancel();
            }
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
            if (FlatTabbedPaneUI.this.tabPane.getMouseWheelListeners().length > ((int)256187910L ^ 0xF451E07)) {
                return;
            }
            if (!this.isInViewport(mouseWheelEvent.getX(), mouseWheelEvent.getY())) {
                return;
            }
            this.lastMouseX = mouseWheelEvent.getX();
            this.lastMouseY = mouseWheelEvent.getY();
            double d2 = mouseWheelEvent.getPreciseWheelRotation();
            int n2 = d2 != 0.0 && d2 != (double)mouseWheelEvent.getWheelRotation() ? (int)497797856L ^ 0x1DABCAE1 : (int)-110436331L ^ 0xF96AE015;
            int n3 = (int)((double)FlatTabbedPaneUI.this.maxTabHeight * d2);
            if (n3 == 0) {
                if (d2 > 0.0) {
                    n3 = (int)-184573008L ^ 0xF4FFA3B1;
                } else if (d2 < 0.0) {
                    n3 = (int)748137303L ^ 0xD36854A8;
                }
            }
            Point point = this.targetViewPosition != null ? this.targetViewPosition : FlatTabbedPaneUI.this.tabViewport.getViewPosition();
            Dimension dimension = FlatTabbedPaneUI.this.tabViewport.getViewSize();
            boolean bl = FlatTabbedPaneUI.this.isHorizontalTabPlacement();
            int n4 = point.x;
            int n5 = point.y;
            if (bl) {
                n4 += FlatTabbedPaneUI.this.isLeftToRight() ? n3 : -n3;
            } else {
                n5 += n3;
            }
            if (n2 != 0 && FlatTabbedPaneUI.this.getScrollButtonsPlacement() == ((int)-949597316L ^ 0xC7664B65) << 2 && FlatTabbedPaneUI.this.getScrollButtonsPolicy() == (int)((long)-1025468428 ^ (long)-1025468425) && (FlatTabbedPaneUI.this.isLeftToRight() || !bl) || FlatTabbedPaneUI.this.scrollBackwardButtonPrefSize != null) {
                if (bl) {
                    if (point.x == 0 && n4 > 0) {
                        n4 += ((FlatTabbedPaneUI)FlatTabbedPaneUI.this).scrollBackwardButtonPrefSize.width;
                    } else if (n3 < 0 && n4 <= ((FlatTabbedPaneUI)FlatTabbedPaneUI.this).scrollBackwardButtonPrefSize.width) {
                        n4 = (int)((long)2092457568 ^ (long)2092457568);
                    }
                } else if (point.y == 0 && n5 > 0) {
                    n5 += ((FlatTabbedPaneUI)FlatTabbedPaneUI.this).scrollBackwardButtonPrefSize.height;
                } else if (n3 < 0 && n5 <= ((FlatTabbedPaneUI)FlatTabbedPaneUI.this).scrollBackwardButtonPrefSize.height) {
                    n5 = (int)((long)-469389828 ^ (long)-469389828);
                }
            }
            if (bl) {
                n4 = Math.min(Math.max(n4, (int)((long)87416334 ^ (long)87416334)), dimension.width - FlatTabbedPaneUI.this.tabViewport.getWidth());
            } else {
                n5 = Math.min(Math.max(n5, (int)((long)1965094208 ^ (long)1965094208)), dimension.height - FlatTabbedPaneUI.this.tabViewport.getHeight());
            }
            Point point2 = new Point(n4, n5);
            if (point2.equals(point)) {
                return;
            }
            if (n2 != 0) {
                if (this.animator != null) {
                    this.animator.stop();
                }
                FlatTabbedPaneUI.this.tabViewport.setViewPosition(point2);
                this.updateRolloverDelayed();
            } else {
                this.setViewPositionAnimated(point2);
            }
            this.scrolled = (int)1145543910L ^ 0x44479CE7;
        }

        protected void setViewPositionAnimated(Point point) {
            if (point.equals(FlatTabbedPaneUI.this.tabViewport.getViewPosition())) {
                return;
            }
            if (!FlatTabbedPaneUI.this.isSmoothScrollingEnabled()) {
                FlatTabbedPaneUI.this.tabViewport.setViewPosition(point);
                this.updateRolloverDelayed();
                return;
            }
            this.startViewPosition = FlatTabbedPaneUI.this.tabViewport.getViewPosition();
            this.targetViewPosition = point;
            if (this.animator == null) {
                int n2 = (int)((long)539582467 ^ (long)539582490) << 3;
                int n3 = (int)((long)246099870 ^ (long)246099867) << 1;
                this.animator = new Animator(n2, f2 -> {
                    if (FlatTabbedPaneUI.this.tabViewport == null || !FlatTabbedPaneUI.this.tabViewport.isShowing()) {
                        this.animator.stop();
                        return;
                    }
                    int n2 = this.startViewPosition.x + Math.round((float)(this.targetViewPosition.x - this.startViewPosition.x) * f2);
                    int n3 = this.startViewPosition.y + Math.round((float)(this.targetViewPosition.y - this.startViewPosition.y) * f2);
                    FlatTabbedPaneUI.this.tabViewport.setViewPosition(new Point(n2, n3));
                }, () -> {
                    this.targetViewPosition = null;
                    this.startViewPosition = null;
                    if (FlatTabbedPaneUI.this.tabPane != null) {
                        FlatTabbedPaneUI.this.setRolloverTab(this.lastMouseX, this.lastMouseY);
                    }
                });
                this.animator.setResolution(n3);
                this.animator.setInterpolator(new CubicBezierEasing(Float.intBitsToFloat((int)((long)311389640 ^ (long)764374472)), Float.intBitsToFloat(0x518FDE6A ^ 0x6E8FDE6A), Float.intBitsToFloat((int)((long)2076547504 ^ (long)1153800624)), 1.0f));
            }
            this.animator.restart();
        }

        protected void updateRolloverDelayed() {
            int n2;
            FlatTabbedPaneUI.this.blockRollover = (boolean)((long)-1290811792 ^ (long)-1290811791);
            int n3 = FlatTabbedPaneUI.this.getRolloverTab();
            if (n3 >= 0 && (n2 = FlatTabbedPaneUI.this.tabForCoordinate(FlatTabbedPaneUI.this.tabPane, this.lastMouseX, this.lastMouseY)) >= 0 && n2 != n3) {
                FlatTabbedPaneUI.this.blockRollover = (boolean)((long)-1552885300 ^ (long)-1552885300);
                FlatTabbedPaneUI.this.setRolloverTab((int)((long)-485454806 ^ (long)485454805));
                FlatTabbedPaneUI.this.blockRollover = ((int)-788500275L ^ 0xD10070CC) != 0;
            }
            if (this.rolloverTimer == null) {
                this.rolloverTimer = new Timer(((int)1432510823L ^ 0x5562612C) << 1, actionEvent -> {
                    FlatTabbedPaneUI.this.blockRollover = ((int)1864615201L ^ 0x6F23C521) != 0;
                    if (FlatTabbedPaneUI.this.tabPane != null) {
                        FlatTabbedPaneUI.this.setRolloverTab(this.lastMouseX, this.lastMouseY);
                    }
                });
                this.rolloverTimer.setRepeats((boolean)((long)2102423262 ^ (long)2102423262));
            }
            this.rolloverTimer.restart();
        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            this.checkViewportExited(mouseEvent.getX(), mouseEvent.getY());
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            this.checkViewportExited(mouseEvent.getX(), mouseEvent.getY());
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            FlatTabbedPaneUI.this.setRolloverTab(mouseEvent.getX(), mouseEvent.getY());
        }

        protected boolean isInViewport(int n2, int n3) {
            return (FlatTabbedPaneUI.this.tabViewport != null && FlatTabbedPaneUI.this.tabViewport.getBounds().contains(n2, n3) ? (int)1674405618L ^ 0x63CD66F3 : (int)1863090268L ^ 0x6F0C805C) != 0;
        }

        protected void checkViewportExited(int n2, int n3) {
            this.lastMouseX = n2;
            this.lastMouseY = n3;
            boolean bl = this.inViewport;
            this.inViewport = this.isInViewport(n2, n3);
            if (this.inViewport != bl) {
                if (!this.inViewport) {
                    this.viewportExited();
                } else if (this.exitedTimer != null) {
                    this.exitedTimer.stop();
                }
            }
        }

        protected void viewportExited() {
            if (!this.scrolled) {
                return;
            }
            if (this.exitedTimer == null) {
                this.exitedTimer = new Timer((int)((long)-842963737 ^ (long)-842963814) << 2, actionEvent -> this.ensureSelectedTabVisible());
                this.exitedTimer.setRepeats(((int)-337114044L ^ 0xEBE80C44) != 0);
            }
            this.exitedTimer.start();
        }

        protected void ensureSelectedTabVisible() {
            if (FlatTabbedPaneUI.this.tabPane == null || FlatTabbedPaneUI.this.tabViewport == null) {
                return;
            }
            if (!this.scrolled || FlatTabbedPaneUI.this.tabViewport == null) {
                return;
            }
            this.scrolled = (int)1849886580L ^ 0x6E430774;
            FlatTabbedPaneUI.this.ensureSelectedTabIsVisible();
        }
    }

    protected class FlatScrollableTabButton
    extends FlatTabAreaButton
    implements MouseListener {
        private Timer autoRepeatTimer;

        protected FlatScrollableTabButton(int n2) {
            super(n2);
            this.addMouseListener(this);
        }

        @Override
        protected void fireActionPerformed(ActionEvent actionEvent) {
            FlatTabbedPaneUI.this.runWithOriginalLayoutManager(() -> super.fireActionPerformed(actionEvent));
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            if (SwingUtilities.isLeftMouseButton(mouseEvent) && this.isEnabled()) {
                if (this.autoRepeatTimer == null) {
                    this.autoRepeatTimer = new Timer((int)((long)-1339754486 ^ (long)-1339754491) << 2, actionEvent -> {
                        if (this.isEnabled()) {
                            this.doClick();
                        }
                    });
                    this.autoRepeatTimer.setInitialDelay((int)((long)-1945318039 ^ (long)-1945318110) << 2);
                }
                this.autoRepeatTimer.start();
            }
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            if (this.autoRepeatTimer != null) {
                this.autoRepeatTimer.stop();
            }
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            if (this.autoRepeatTimer != null && this.isPressed()) {
                this.autoRepeatTimer.start();
            }
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            if (this.autoRepeatTimer != null) {
                this.autoRepeatTimer.stop();
            }
        }
    }

    protected class FlatMoreTabsButton
    extends FlatTabAreaButton
    implements ActionListener,
    PopupMenuListener {
        private boolean popupVisible;

        public FlatMoreTabsButton() {
            super((int)((long)-848320684 ^ (long)-848320687));
            this.updateDirection();
            this.setToolTipText(FlatTabbedPaneUI.this.moreTabsButtonToolTipText);
            this.addActionListener(this);
        }

        protected void updateDirection() {
            int n2;
            switch (FlatTabbedPaneUI.this.tabPane.getTabPlacement()) {
                default: {
                    n2 = (int)903429767L ^ 0x35D93E82;
                    break;
                }
                case 3: {
                    n2 = (int)((long)-775794525 ^ (long)-775794526);
                    break;
                }
                case 2: {
                    n2 = (int)((long)-2083747564 ^ (long)-2083747561);
                    break;
                }
                case 4: {
                    n2 = (int)-77452874L ^ 0xFB6229B1;
                }
            }
            this.setDirection(n2);
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension dimension = super.getPreferredSize();
            int n2 = this.direction == ((int)1566843142L ^ 0x5D642103) || this.direction == ((int)-1042408885L ^ 0xC1DE1A4A) ? (int)-708919786L ^ 0xD5BEBE17 : (int)((long)-1756177252 ^ (long)-1756177252);
            int n3 = UIScale.scale(((int)697846913L ^ 0x29984C80) << 3);
            return new Dimension(dimension.width + (n2 != 0 ? n3 : (int)((long)1136528717 ^ (long)1136528717)), dimension.height + (n2 != 0 ? (int)((long)-1898228303 ^ (long)-1898228303) : n3));
        }

        @Override
        public void paint(Graphics graphics) {
            if (this.direction == ((int)-107729402L ^ 0xF9942E05) || this.direction == (int)((long)357354317 ^ (long)357354314)) {
                int n2 = Math.max(UIScale.unscale((this.getWidth() - this.getHeight()) / (((int)606780049L ^ 0x242ABA90) << 1)) - ((int)((long)-1229392855 ^ (long)-1229392856) << 2), (int)((long)757485198 ^ (long)757485198));
                this.setXOffset(this.direction == (int)((long)-3219802 ^ (long)-3219803) ? n2 : -n2);
            } else {
                this.setXOffset((int)58136147L ^ 0x3771653);
            }
            super.paint(graphics);
        }

        @Override
        protected boolean isHover() {
            return (super.isHover() || this.popupVisible ? (int)((long)-715398901 ^ (long)-715398902) : (int)1724718013L ^ 0x66CD1BBD) != 0;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int n2;
            if (FlatTabbedPaneUI.this.tabViewport == null) {
                return;
            }
            JPopupMenu jPopupMenu = new JPopupMenu();
            jPopupMenu.addPopupMenuListener(this);
            Rectangle rectangle = FlatTabbedPaneUI.this.tabViewport.getViewRect();
            int n3 = (int)-2007790301L ^ 0x77AC72DC;
            for (n2 = (int)((long)-953068277 ^ (long)-953068277); n2 < FlatTabbedPaneUI.this.rects.length; ++n2) {
                if (rectangle.contains(FlatTabbedPaneUI.this.rects[n2])) continue;
                if (n3 >= 0 && n3 + ((int)772873391L ^ 0x2E111CAE) != n2) {
                    jPopupMenu.addSeparator();
                }
                n3 = n2;
                jPopupMenu.add(this.createTabMenuItem(n2));
            }
            n2 = this.getWidth();
            int n4 = this.getHeight();
            Dimension dimension = jPopupMenu.getPreferredSize();
            int n5 = FlatTabbedPaneUI.this.isLeftToRight() ? n2 - dimension.width : (int)((long)1130571356 ^ (long)1130571356);
            int n6 = n4 - dimension.height;
            switch (FlatTabbedPaneUI.this.tabPane.getTabPlacement()) {
                default: {
                    n6 = n4;
                    break;
                }
                case 3: {
                    n6 = -dimension.height;
                    break;
                }
                case 2: {
                    n5 = n2;
                    break;
                }
                case 4: {
                    n5 = -dimension.width;
                }
            }
            jPopupMenu.show(this, n5, n6);
        }

        protected JMenuItem createTabMenuItem(int n2) {
            Color color;
            Component component;
            String string = FlatTabbedPaneUI.this.tabPane.getTitleAt(n2);
            if (StringUtils.isEmpty(string)) {
                component = FlatTabbedPaneUI.this.tabPane.getTabComponentAt(n2);
                if (component != null) {
                    string = this.findTabTitle(component);
                }
                if (StringUtils.isEmpty(string)) {
                    string = FlatTabbedPaneUI.this.tabPane.getAccessibleContext().getAccessibleChild(n2).getAccessibleContext().getAccessibleName();
                }
                if (StringUtils.isEmpty(string) && component instanceof Accessible) {
                    string = this.findTabTitleInAccessible((Accessible)((Object)component));
                }
                if (StringUtils.isEmpty(string)) {
                    string = n2 + ((int)-1774401755L ^ 0x963CC724) + ". Tab";
                }
            }
            component = new JMenuItem(string, FlatTabbedPaneUI.this.tabPane.getIconAt(n2));
            ((AbstractButton)component).setDisabledIcon(FlatTabbedPaneUI.this.tabPane.getDisabledIconAt(n2));
            ((JComponent)component).setToolTipText(FlatTabbedPaneUI.this.tabPane.getToolTipTextAt(n2));
            Color color2 = FlatTabbedPaneUI.this.tabPane.getForegroundAt(n2);
            if (color2 != FlatTabbedPaneUI.this.tabPane.getForeground()) {
                ((JComponent)component).setForeground(color2);
            }
            if ((color = FlatTabbedPaneUI.this.tabPane.getBackgroundAt(n2)) != FlatTabbedPaneUI.this.tabPane.getBackground()) {
                ((JComponent)component).setBackground(color);
                ((JComponent)component).setOpaque((boolean)((long)-1890879656 ^ (long)-1890879655));
            }
            if (!FlatTabbedPaneUI.this.tabPane.isEnabledAt(n2)) {
                ((JMenuItem)component).setEnabled(((int)-563018771L ^ 0xDE7103ED) != 0);
            }
            ((AbstractButton)component).addActionListener(actionEvent -> this.selectTab(n2));
            return component;
        }

        private String findTabTitle(Component component) {
            String string = null;
            if (component instanceof JLabel) {
                string = ((JLabel)component).getText();
            } else if (component instanceof JTextComponent) {
                string = ((JTextComponent)component).getText();
            }
            if (!StringUtils.isEmpty(string)) {
                return string;
            }
            if (component instanceof Container) {
                Component[] arrcomponent = ((Container)component).getComponents();
                int n2 = arrcomponent.length;
                for (int i2 = (int)((long)-455325568 ^ (long)-455325568); i2 < n2; ++i2) {
                    Component component2 = arrcomponent[i2];
                    string = this.findTabTitle(component2);
                    if (string == null) continue;
                    return string;
                }
            }
            return null;
        }

        private String findTabTitleInAccessible(Accessible accessible) {
            AccessibleContext accessibleContext = accessible.getAccessibleContext();
            if (accessibleContext == null) {
                return null;
            }
            String string = accessibleContext.getAccessibleName();
            if (!StringUtils.isEmpty(string)) {
                return string;
            }
            int n2 = accessibleContext.getAccessibleChildrenCount();
            for (int i2 = (int)((long)706757372 ^ (long)706757372); i2 < n2; ++i2) {
                string = this.findTabTitleInAccessible(accessibleContext.getAccessibleChild(i2));
                if (string == null) continue;
                return string;
            }
            return null;
        }

        protected void selectTab(int n2) {
            FlatTabbedPaneUI.this.tabPane.setSelectedIndex(n2);
            FlatTabbedPaneUI.this.ensureSelectedTabIsVisible();
        }

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent) {
            this.popupVisible = (int)((long)-119412439 ^ (long)-119412440);
            this.repaint();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent) {
            this.popupVisible = (int)((long)1099863913 ^ (long)1099863913);
            this.repaint();
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent popupMenuEvent) {
            this.popupVisible = (int)((long)-1363651249 ^ (long)-1363651249);
            this.repaint();
        }
    }

    protected class FlatTabAreaButton
    extends FlatArrowButton {
        public FlatTabAreaButton(int n2) {
            super(n2, FlatTabbedPaneUI.this.arrowType, FlatTabbedPaneUI.this.foreground, FlatTabbedPaneUI.this.disabledForeground, null, FlatTabbedPaneUI.this.buttonHoverBackground, null, FlatTabbedPaneUI.this.buttonPressedBackground);
            this.setArrowWidth(((int)-1803531140L ^ 0x94804C79) << 1);
        }

        @Override
        protected Color deriveBackground(Color color) {
            return FlatUIUtils.deriveColor(color, FlatTabbedPaneUI.this.tabPane.getBackground());
        }

        @Override
        public void paint(Graphics graphics) {
            if (FlatTabbedPaneUI.this.tabsOpaque || FlatTabbedPaneUI.this.tabPane.isOpaque()) {
                graphics.setColor(FlatTabbedPaneUI.this.tabPane.getBackground());
                graphics.fillRect((int)((long)-83573639 ^ (long)-83573639), (int)((long)1601158985 ^ (long)1601158985), this.getWidth(), this.getHeight());
            }
            super.paint(graphics);
        }

        @Override
        protected void paintBackground(Graphics2D graphics2D) {
            Insets insets = new Insets((int)((long)1793922961 ^ (long)1793922961), (int)((long)-389018820 ^ (long)-389018820), (int)-130884793L ^ 0xF832DB47, (int)-1267174392L ^ 0xB4787408);
            FlatTabbedPaneUI.rotateInsets(FlatTabbedPaneUI.this.buttonInsets, insets, FlatTabbedPaneUI.this.tabPane.getTabPlacement());
            int n2 = UIScale.scale2(insets.top);
            int n3 = UIScale.scale2(insets.left);
            int n4 = UIScale.scale2(insets.bottom);
            int n5 = UIScale.scale2(insets.right);
            FlatUIUtils.paintComponentBackground(graphics2D, n3, n2, this.getWidth() - n3 - n5, this.getHeight() - n2 - n4, 0.0f, UIScale.scale((float)FlatTabbedPaneUI.this.buttonArc));
        }
    }

    private class ContainerUIResource
    extends JPanel
    implements UIResource {
        private ContainerUIResource(Component component) {
            super(new BorderLayout());
            this.add(component);
        }
    }

    private class TabCloseButton
    extends JButton
    implements UIResource {
        private TabCloseButton() {
        }
    }
}

