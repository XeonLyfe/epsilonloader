/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.FlatWindowsNativeWindowBorder;
import com.formdev.flatlaf.ui.JBRCustomDecorations;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Window;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;

public class FlatNativeWindowBorder {
    private static final boolean canUseWindowDecorations = SystemInfo.isWindows_10_orLater && !SystemInfo.isProjector && !SystemInfo.isWebswing && !SystemInfo.isWinPE && FlatSystemProperties.getBoolean("flatlaf.useWindowDecorations", (boolean)((long)-1202299354 ^ (long)-1202299353)) ? (int)((long)-85385889 ^ (long)-85385890) : (int)((long)1128158830 ^ (long)1128158830);
    private static final boolean canUseJBRCustomDecorations = canUseWindowDecorations && SystemInfo.isJetBrainsJVM_11_orLater && FlatSystemProperties.getBoolean("flatlaf.useJetBrainsCustomDecorations", (boolean)((long)-1648185109 ^ (long)-1648185110)) ? (int)((long)-431596943 ^ (long)-431596944) : (int)1771391604L ^ 0x69954A74;
    private static Boolean supported;
    private static Provider nativeProvider;

    public static boolean isSupported() {
        if (canUseJBRCustomDecorations) {
            return JBRCustomDecorations.isSupported();
        }
        FlatNativeWindowBorder.initialize();
        return supported;
    }

    static Object install(JRootPane jRootPane) {
        if (canUseJBRCustomDecorations) {
            return JBRCustomDecorations.install(jRootPane);
        }
        if (!FlatNativeWindowBorder.isSupported()) {
            return null;
        }
        Window window = SwingUtilities.windowForComponent(jRootPane);
        if (window != null && window.isDisplayable()) {
            FlatNativeWindowBorder.install(window);
        }
        PropertyChangeListener propertyChangeListener = propertyChangeEvent -> {
            Object object = propertyChangeEvent.getNewValue();
            if (object instanceof Window) {
                FlatNativeWindowBorder.install((Window)object);
            } else if (object == null && propertyChangeEvent.getOldValue() instanceof Window) {
                FlatNativeWindowBorder.uninstall((Window)propertyChangeEvent.getOldValue());
            }
        };
        jRootPane.addPropertyChangeListener("ancestor", propertyChangeListener);
        return propertyChangeListener;
    }

    static void install(Window window) {
        if (FlatNativeWindowBorder.hasCustomDecoration(window)) {
            return;
        }
        if (UIManager.getLookAndFeel().getSupportsWindowDecorations()) {
            return;
        }
        if (window instanceof JFrame) {
            JFrame jFrame = (JFrame)window;
            JRootPane jRootPane = jFrame.getRootPane();
            if (!FlatNativeWindowBorder.useWindowDecorations(jRootPane)) {
                return;
            }
            if (jFrame.isUndecorated()) {
                return;
            }
            FlatNativeWindowBorder.setHasCustomDecoration(jFrame, (boolean)((long)-1328181667 ^ (long)-1328181668));
            jRootPane.setWindowDecorationStyle((int)-1975494283L ^ 0x8A405974);
        } else if (window instanceof JDialog) {
            JDialog jDialog = (JDialog)window;
            JRootPane jRootPane = jDialog.getRootPane();
            if (!FlatNativeWindowBorder.useWindowDecorations(jRootPane)) {
                return;
            }
            if (jDialog.isUndecorated()) {
                return;
            }
            FlatNativeWindowBorder.setHasCustomDecoration(jDialog, (boolean)((long)1888803473 ^ (long)1888803472));
            jRootPane.setWindowDecorationStyle(((int)554594792L ^ 0x210E71E9) << 1);
        }
    }

    static void uninstall(JRootPane jRootPane, Object object) {
        if (canUseJBRCustomDecorations) {
            JBRCustomDecorations.uninstall(jRootPane, object);
            return;
        }
        if (!FlatNativeWindowBorder.isSupported()) {
            return;
        }
        if (object instanceof PropertyChangeListener) {
            jRootPane.removePropertyChangeListener("ancestor", (PropertyChangeListener)object);
        }
        if (UIManager.getLookAndFeel() instanceof FlatLaf && FlatNativeWindowBorder.useWindowDecorations(jRootPane)) {
            return;
        }
        Window window = SwingUtilities.windowForComponent(jRootPane);
        if (window != null) {
            FlatNativeWindowBorder.uninstall(window);
        }
    }

    private static void uninstall(Window window) {
        if (!FlatNativeWindowBorder.hasCustomDecoration(window)) {
            return;
        }
        FlatNativeWindowBorder.setHasCustomDecoration(window, (boolean)((long)1894392374 ^ (long)1894392374));
        if (window instanceof JFrame) {
            JFrame jFrame = (JFrame)window;
            jFrame.getRootPane().setWindowDecorationStyle((int)((long)-1756756104 ^ (long)-1756756104));
        } else if (window instanceof JDialog) {
            JDialog jDialog = (JDialog)window;
            jDialog.getRootPane().setWindowDecorationStyle((int)((long)-959607228 ^ (long)-959607228));
        }
    }

    private static boolean useWindowDecorations(JRootPane jRootPane) {
        return FlatUIUtils.getBoolean(jRootPane, "flatlaf.useWindowDecorations", "JRootPane.useWindowDecorations", "TitlePane.useWindowDecorations", (boolean)((long)550441490 ^ (long)550441490));
    }

    public static boolean hasCustomDecoration(Window window) {
        if (canUseJBRCustomDecorations) {
            return JBRCustomDecorations.hasCustomDecoration(window);
        }
        if (!FlatNativeWindowBorder.isSupported()) {
            return (int)((long)-114242398 ^ (long)-114242398) != 0;
        }
        return nativeProvider.hasCustomDecoration(window);
    }

    public static void setHasCustomDecoration(Window window, boolean bl) {
        if (canUseJBRCustomDecorations) {
            JBRCustomDecorations.setHasCustomDecoration(window, bl);
            return;
        }
        if (!FlatNativeWindowBorder.isSupported()) {
            return;
        }
        nativeProvider.setHasCustomDecoration(window, bl);
    }

    static void setTitleBarHeightAndHitTestSpots(Window window, int n2, List<Rectangle> list, Rectangle rectangle) {
        if (canUseJBRCustomDecorations) {
            JBRCustomDecorations.setTitleBarHeightAndHitTestSpots(window, n2, list);
            return;
        }
        if (!FlatNativeWindowBorder.isSupported()) {
            return;
        }
        nativeProvider.setTitleBarHeight(window, n2);
        nativeProvider.setTitleBarHitTestSpots(window, list);
        nativeProvider.setTitleBarAppIconBounds(window, rectangle);
    }

    static boolean showWindow(Window window, int n2) {
        if (canUseJBRCustomDecorations || !FlatNativeWindowBorder.isSupported()) {
            return (int)((long)478753930 ^ (long)478753930) != 0;
        }
        return nativeProvider.showWindow(window, n2);
    }

    private static void initialize() {
        if (supported != null) {
            return;
        }
        supported = (boolean)((long)1183885198 ^ (long)1183885198);
        if (!canUseWindowDecorations) {
            return;
        }
        try {
            FlatNativeWindowBorder.setNativeProvider(FlatWindowsNativeWindowBorder.getInstance());
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void setNativeProvider(Provider provider) {
        if (nativeProvider != null) {
            throw new IllegalStateException();
        }
        nativeProvider = provider;
        supported = (nativeProvider != null ? (int)595809623L ^ 0x23835556 : (int)((long)-402809495 ^ (long)-402809495)) != 0;
    }

    static class WindowTopBorder
    extends JBRCustomDecorations.JBRWindowTopBorder {
        private static WindowTopBorder instance;

        WindowTopBorder() {
        }

        static JBRCustomDecorations.JBRWindowTopBorder getInstance() {
            if (canUseJBRCustomDecorations) {
                return JBRCustomDecorations.JBRWindowTopBorder.getInstance();
            }
            if (instance == null) {
                instance = new WindowTopBorder();
            }
            return instance;
        }

        @Override
        void installListeners() {
            nativeProvider.addChangeListener(changeEvent -> {
                this.update();
                Window[] arrwindow = Window.getWindows();
                int n2 = arrwindow.length;
                for (int i2 = (int)-260741148L ^ 0xF07567E4; i2 < n2; ++i2) {
                    Window window = arrwindow[i2];
                    if (!window.isDisplayable()) continue;
                    window.repaint((int)((long)1029439321 ^ (long)1029439321), (int)-1345543127L ^ 0xAFCCA429, window.getWidth(), (int)((long)-176530475 ^ (long)-176530476));
                }
            });
        }

        @Override
        boolean isColorizationColorAffectsBorders() {
            return nativeProvider.isColorizationColorAffectsBorders();
        }

        @Override
        Color getColorizationColor() {
            return nativeProvider.getColorizationColor();
        }

        @Override
        int getColorizationColorBalance() {
            return nativeProvider.getColorizationColorBalance();
        }
    }

    public static interface Provider {
        public static final int SW_MAXIMIZE = 3;
        public static final int SW_MINIMIZE = 6;
        public static final int SW_RESTORE = 9;

        public boolean hasCustomDecoration(Window var1);

        public void setHasCustomDecoration(Window var1, boolean var2);

        public void setTitleBarHeight(Window var1, int var2);

        public void setTitleBarHitTestSpots(Window var1, List<Rectangle> var2);

        public void setTitleBarAppIconBounds(Window var1, Rectangle var2);

        public boolean showWindow(Window var1, int var2);

        public boolean isColorizationColorAffectsBorders();

        public Color getColorizationColor();

        public int getColorizationColorBalance();

        public void addChangeListener(ChangeListener var1);

        public void removeChangeListener(ChangeListener var1);
    }
}

