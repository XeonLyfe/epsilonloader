/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatNativeWindowBorder;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.BorderUIResource;

public class JBRCustomDecorations {
    private static Boolean supported;
    private static Method Window_hasCustomDecoration;
    private static Method Window_setHasCustomDecoration;
    private static Method WWindowPeer_setCustomDecorationTitleBarHeight;
    private static Method WWindowPeer_setCustomDecorationHitTestSpots;
    private static Method AWTAccessor_getComponentAccessor;
    private static Method AWTAccessor_ComponentAccessor_getPeer;

    public static boolean isSupported() {
        JBRCustomDecorations.initialize();
        return supported;
    }

    static Object install(final JRootPane jRootPane) {
        if (!JBRCustomDecorations.isSupported()) {
            return null;
        }
        Window window = SwingUtilities.windowForComponent(jRootPane);
        if (window != null) {
            FlatNativeWindowBorder.install(window);
            return null;
        }
        HierarchyListener hierarchyListener = new HierarchyListener(){

            @Override
            public void hierarchyChanged(HierarchyEvent hierarchyEvent) {
                if (hierarchyEvent.getChanged() != jRootPane || (hierarchyEvent.getChangeFlags() & 1L) == 0L) {
                    return;
                }
                Container container = hierarchyEvent.getChangedParent();
                if (container instanceof Window) {
                    FlatNativeWindowBorder.install((Window)container);
                }
                EventQueue.invokeLater(() -> jRootPane.removeHierarchyListener(this));
            }
        };
        jRootPane.addHierarchyListener(hierarchyListener);
        return hierarchyListener;
    }

    static void uninstall(JRootPane jRootPane, Object object) {
        Window window;
        if (object instanceof HierarchyListener) {
            jRootPane.removeHierarchyListener((HierarchyListener)object);
        }
        if ((window = SwingUtilities.windowForComponent(jRootPane)) != null) {
            JBRCustomDecorations.setHasCustomDecoration(window, ((int)-156070688L ^ 0xF6B28CE0) != 0);
        }
    }

    static boolean hasCustomDecoration(Window window) {
        if (!JBRCustomDecorations.isSupported()) {
            return (int)((long)485395478 ^ (long)485395478) != 0;
        }
        try {
            return (Boolean)Window_hasCustomDecoration.invoke(window, new Object[(int)((long)-213791210 ^ (long)-213791210)]);
        }
        catch (Exception exception) {
            LoggingFacade.INSTANCE.logSevere(null, exception);
            return (int)((long)606626677 ^ (long)606626677) != 0;
        }
    }

    static void setHasCustomDecoration(Window window, boolean bl) {
        if (!JBRCustomDecorations.isSupported()) {
            return;
        }
        try {
            if (bl) {
                Window_setHasCustomDecoration.invoke(window, new Object[(int)1395566028L ^ 0x532EA5CC]);
            } else {
                JBRCustomDecorations.setTitleBarHeightAndHitTestSpots(window, (int)((long)-1895296964 ^ (long)-1895296963) << 2, Collections.emptyList());
            }
        }
        catch (Exception exception) {
            LoggingFacade.INSTANCE.logSevere(null, exception);
        }
    }

    static void setTitleBarHeightAndHitTestSpots(Window window, int n2, List<Rectangle> list) {
        if (!JBRCustomDecorations.isSupported()) {
            return;
        }
        try {
            Object object = AWTAccessor_getComponentAccessor.invoke(null, new Object[(int)((long)-1188135025 ^ (long)-1188135025)]);
            Object[] arrobject = new Object[(int)611617511L ^ 0x24748AE6];
            arrobject[(int)((long)-1322869990 ^ (long)-1322869990)] = window;
            Object object2 = AWTAccessor_ComponentAccessor_getPeer.invoke(object, arrobject);
            Object[] arrobject2 = new Object[(int)((long)751611502 ^ (long)751611503)];
            arrobject2[(int)1239078599L ^ 1239078599] = n2;
            WWindowPeer_setCustomDecorationTitleBarHeight.invoke(object2, arrobject2);
            Object[] arrobject3 = new Object[(int)-1681764481L ^ 0x9BC24F7E];
            arrobject3[(int)-724000181L ^ -724000181] = list;
            WWindowPeer_setCustomDecorationHitTestSpots.invoke(object2, arrobject3);
        }
        catch (Exception exception) {
            LoggingFacade.INSTANCE.logSevere(null, exception);
        }
    }

    private static void initialize() {
        if (supported != null) {
            return;
        }
        supported = (boolean)((long)-407258452 ^ (long)-407258452);
        if (!SystemInfo.isJetBrainsJVM_11_orLater || !SystemInfo.isWindows_10_orLater) {
            return;
        }
        try {
            Class<?> class_ = Class.forName("sun.awt.AWTAccessor");
            Class<?> class_2 = Class.forName("sun.awt.AWTAccessor$ComponentAccessor");
            AWTAccessor_getComponentAccessor = class_.getDeclaredMethod("getComponentAccessor", new Class[(int)510578282L ^ 0x1E6ECE6A]);
            Class[] arrclass = new Class[(int)((long)1334068355 ^ (long)1334068354)];
            arrclass[(int)653754559L ^ 653754559] = Component.class;
            AWTAccessor_ComponentAccessor_getPeer = class_2.getDeclaredMethod("getPeer", arrclass);
            Class<?> class_3 = Class.forName("sun.awt.windows.WWindowPeer");
            Class[] arrclass2 = new Class[(int)-1318393496L ^ 0xB16AE969];
            arrclass2[(int)((long)625499926 ^ (long)625499926)] = Integer.TYPE;
            WWindowPeer_setCustomDecorationTitleBarHeight = class_3.getDeclaredMethod("setCustomDecorationTitleBarHeight", arrclass2);
            Class[] arrclass3 = new Class[(int)1319693890L ^ 0x4EA8EE43];
            arrclass3[(int)((long)366327764 ^ (long)366327764)] = List.class;
            WWindowPeer_setCustomDecorationHitTestSpots = class_3.getDeclaredMethod("setCustomDecorationHitTestSpots", arrclass3);
            WWindowPeer_setCustomDecorationTitleBarHeight.setAccessible(((int)2053944176L ^ 0x7A6CB371) != 0);
            WWindowPeer_setCustomDecorationHitTestSpots.setAccessible((boolean)((long)937219228 ^ (long)937219229));
            Window_hasCustomDecoration = Window.class.getDeclaredMethod("hasCustomDecoration", new Class[(int)((long)798831753 ^ (long)798831753)]);
            Window_setHasCustomDecoration = Window.class.getDeclaredMethod("setHasCustomDecoration", new Class[(int)692690219L ^ 0x29499D2B]);
            Window_hasCustomDecoration.setAccessible(((int)-610300411L ^ 0xDB9F8E04) != 0);
            Window_setHasCustomDecoration.setAccessible((boolean)((long)-859552981 ^ (long)-859552982));
            supported = (boolean)((long)-839062658 ^ (long)-839062657);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    static class JBRWindowTopBorder
    extends BorderUIResource.EmptyBorderUIResource {
        private static JBRWindowTopBorder instance;
        private final Color defaultActiveBorder = new Color(((int)1817709898L ^ 0x6C5F0A4D) << 4);
        private final Color inactiveLightColor = new Color((int)((long)-1715019322 ^ (long)-1718373229) << 1);
        private boolean colorizationAffectsBorders;
        private Color activeColor = this.defaultActiveBorder;

        static JBRWindowTopBorder getInstance() {
            if (instance == null) {
                instance = new JBRWindowTopBorder();
            }
            return instance;
        }

        JBRWindowTopBorder() {
            super((int)((long)-1704276000 ^ (long)-1704275999), (int)((long)-45307061 ^ (long)-45307061), (int)((long)1677903703 ^ (long)1677903703), (int)((long)1921998924 ^ (long)1921998924));
            this.update();
            this.installListeners();
        }

        void update() {
            this.colorizationAffectsBorders = this.isColorizationColorAffectsBorders();
            this.activeColor = this.calculateActiveBorderColor();
        }

        void installListeners() {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.addPropertyChangeListener("win.dwm.colorizationColor.affects.borders", propertyChangeEvent -> {
                this.colorizationAffectsBorders = this.isColorizationColorAffectsBorders();
                this.activeColor = this.calculateActiveBorderColor();
            });
            PropertyChangeListener propertyChangeListener = propertyChangeEvent -> {
                this.activeColor = this.calculateActiveBorderColor();
            };
            toolkit.addPropertyChangeListener("win.dwm.colorizationColor", propertyChangeListener);
            toolkit.addPropertyChangeListener("win.dwm.colorizationColorBalance", propertyChangeListener);
            toolkit.addPropertyChangeListener("win.frame.activeBorderColor", propertyChangeListener);
        }

        boolean isColorizationColorAffectsBorders() {
            Object object = Toolkit.getDefaultToolkit().getDesktopProperty("win.dwm.colorizationColor.affects.borders");
            return (object instanceof Boolean ? (Boolean)object : (int)-1190169810L ^ 0xB90F732F) != 0;
        }

        Color getColorizationColor() {
            return (Color)Toolkit.getDefaultToolkit().getDesktopProperty("win.dwm.colorizationColor");
        }

        int getColorizationColorBalance() {
            Object object = Toolkit.getDefaultToolkit().getDesktopProperty("win.dwm.colorizationColorBalance");
            return object instanceof Integer ? (Integer)object : (int)53788115L ^ 0xFCCB422C;
        }

        private Color calculateActiveBorderColor() {
            if (!this.colorizationAffectsBorders) {
                return this.defaultActiveBorder;
            }
            Color color = this.getColorizationColor();
            if (color != null) {
                int n2 = this.getColorizationColorBalance();
                if (n2 < 0 || n2 > ((int)-1704249442L ^ 0x9A6B3787) << 2) {
                    n2 = ((int)-1650135016L ^ 0x9DA4F001) << 2;
                }
                if (n2 == 0) {
                    return new Color((int)1678977628L ^ 0x64CAF385);
                }
                if (n2 == ((int)962387468L ^ 0x395CDE15) << 2) {
                    return color;
                }
                float f2 = (float)n2 / Float.intBitsToFloat((int)265067404L ^ 0x4D049B8C);
                float f3 = 1.0f - f2;
                int n3 = Math.round((float)color.getRed() * f2 + Float.intBitsToFloat((int)((long)1755203064 ^ (long)734479864)) * f3);
                int n4 = Math.round((float)color.getGreen() * f2 + Float.intBitsToFloat(0xD40C503 ^ 0x4E19C503) * f3);
                int n5 = Math.round((float)color.getBlue() * f2 + Float.intBitsToFloat((int)((long)588312770 ^ (long)1615458498)) * f3);
                n3 = Math.min(Math.max(n3, (int)((long)-864710044 ^ (long)-864710044)), (int)((long)-688139510 ^ (long)-688139275));
                n4 = Math.min(Math.max(n4, (int)-1594094726L ^ 0xA0FC0B7A), (int)-379116104L ^ 0xE9672547);
                n5 = Math.min(Math.max(n5, (int)-1848505037L ^ 0x91D20D33), (int)-1109043300L ^ 0xBDE55763);
                return new Color(n3, n4, n5);
            }
            Color color2 = (Color)Toolkit.getDefaultToolkit().getDesktopProperty("win.frame.activeBorderColor");
            return color2 != null ? color2 : UIManager.getColor("MenuBar.borderColor");
        }

        @Override
        public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
            int n6;
            Window window = SwingUtilities.windowForComponent(component);
            boolean bl = (window != null ? window.isActive() : (int)((long)2109002491 ^ (long)2109002491)) ? 1 : 0;
            int n7 = n6 = !FlatLaf.isLafDark() || bl && this.colorizationAffectsBorders ? (int)2060296446L ^ 0x7ACDA0FF : (int)((long)-1359063438 ^ (long)-1359063438);
            if (n6 == 0) {
                return;
            }
            graphics.setColor(bl ? this.activeColor : this.inactiveLightColor);
            HiDPIUtils.paintAtScale1x((Graphics2D)graphics, n2, n3, n4, n5, (arg_0, arg_1, arg_2, arg_3, arg_4, arg_5) -> this.paintImpl(arg_0, arg_1, arg_2, arg_3, arg_4, arg_5));
        }

        private void paintImpl(Graphics2D graphics2D, int n2, int n3, int n4, int n5, double d2) {
            graphics2D.drawRect(n2, n3, n4 - (int)((long)-1535622908 ^ (long)-1535622907), (int)((long)-198943595 ^ (long)-198943595));
        }

        void repaintBorder(Component component) {
            component.repaint((int)780792886L ^ 0x2E89F436, (int)840077340L ^ 0x3212901C, component.getWidth(), (int)((long)-150510803 ^ (long)-150510804));
        }
    }
}

