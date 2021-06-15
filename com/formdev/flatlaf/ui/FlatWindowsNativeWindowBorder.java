/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatNativeWindowBorder;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.NativeLibrary;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.geom.AffineTransform;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

class FlatWindowsNativeWindowBorder
implements FlatNativeWindowBorder.Provider {
    private final Map<Window, WndProc> windowsMap = Collections.synchronizedMap(new IdentityHashMap());
    private final EventListenerList listenerList = new EventListenerList();
    private Timer fireStateChangedTimer;
    private boolean colorizationUpToDate;
    private boolean colorizationColorAffectsBorders;
    private Color colorizationColor;
    private int colorizationColorBalance;
    private static NativeLibrary nativeLibrary;
    private static FlatWindowsNativeWindowBorder instance;

    static FlatNativeWindowBorder.Provider getInstance() {
        if (!SystemInfo.isWindows_10_orLater) {
            return null;
        }
        if (nativeLibrary == null) {
            if (!SystemInfo.isJava_9_orLater) {
                try {
                    System.loadLibrary("jawt");
                }
                catch (Exception exception) {
                    LoggingFacade.INSTANCE.logSevere(null, exception);
                }
            }
            String string = "com/formdev/flatlaf/natives/flatlaf-windows-x86";
            if (SystemInfo.isX86_64) {
                string = string + "_64";
            }
            nativeLibrary = new NativeLibrary(string, null, (boolean)((long)1069927718 ^ (long)1069927719));
        }
        if (!nativeLibrary.isLoaded()) {
            return null;
        }
        if (instance == null) {
            instance = new FlatWindowsNativeWindowBorder();
        }
        return instance;
    }

    private FlatWindowsNativeWindowBorder() {
    }

    @Override
    public boolean hasCustomDecoration(Window window) {
        return this.windowsMap.containsKey(window);
    }

    @Override
    public void setHasCustomDecoration(Window window, boolean bl) {
        if (bl) {
            this.install(window);
        } else {
            this.uninstall(window);
        }
    }

    private void install(Window window) {
        if (!SystemInfo.isWindows_10_orLater) {
            return;
        }
        if (!(window instanceof JFrame) && !(window instanceof JDialog)) {
            return;
        }
        if (window instanceof Frame && ((Frame)window).isUndecorated() || window instanceof Dialog && ((Dialog)window).isUndecorated()) {
            return;
        }
        if (this.windowsMap.containsKey(window)) {
            return;
        }
        WndProc wndProc = new WndProc(window);
        if (wndProc.hwnd == 0L) {
            return;
        }
        this.windowsMap.put(window, wndProc);
    }

    private void uninstall(Window window) {
        WndProc wndProc = this.windowsMap.remove(window);
        if (wndProc != null) {
            wndProc.uninstall();
        }
    }

    @Override
    public void setTitleBarHeight(Window window, int n2) {
        WndProc wndProc = this.windowsMap.get(window);
        if (wndProc == null) {
            return;
        }
        wndProc.titleBarHeight = n2;
    }

    @Override
    public void setTitleBarHitTestSpots(Window window, List<Rectangle> list) {
        WndProc wndProc = this.windowsMap.get(window);
        if (wndProc == null) {
            return;
        }
        WndProc.access$202(wndProc, list.toArray(new Rectangle[list.size()]));
    }

    @Override
    public void setTitleBarAppIconBounds(Window window, Rectangle rectangle) {
        WndProc wndProc = this.windowsMap.get(window);
        if (wndProc == null) {
            return;
        }
        wndProc.appIconBounds = rectangle != null ? new Rectangle(rectangle) : null;
    }

    @Override
    public boolean showWindow(Window window, int n2) {
        WndProc wndProc = this.windowsMap.get(window);
        if (wndProc == null) {
            return (int)((long)-1735630097 ^ (long)-1735630097) != 0;
        }
        wndProc.showWindow(wndProc.hwnd, n2);
        return ((int)-295216182L ^ 0xEE675BCB) != 0;
    }

    @Override
    public boolean isColorizationColorAffectsBorders() {
        this.updateColorization();
        return this.colorizationColorAffectsBorders;
    }

    @Override
    public Color getColorizationColor() {
        this.updateColorization();
        return this.colorizationColor;
    }

    @Override
    public int getColorizationColorBalance() {
        this.updateColorization();
        return this.colorizationColorBalance;
    }

    private void updateColorization() {
        if (this.colorizationUpToDate) {
            return;
        }
        this.colorizationUpToDate = (int)((long)24952324 ^ (long)24952325);
        String string = "SOFTWARE\\Microsoft\\Windows\\DWM";
        int n2 = FlatWindowsNativeWindowBorder.registryGetIntValue(string, "ColorPrevalence", (int)-686429579L ^ 0x28EA158A);
        this.colorizationColorAffectsBorders = n2 > 0 ? (int)-203612901L ^ 0xF3DD1D1A : (int)((long)116987505 ^ (long)116987505);
        n2 = FlatWindowsNativeWindowBorder.registryGetIntValue(string, "ColorizationColor", (int)-852285682L ^ 0x32CCD8F1);
        this.colorizationColor = n2 != (int)((long)-868337829 ^ (long)868337828) ? new Color(n2) : null;
        this.colorizationColorBalance = FlatWindowsNativeWindowBorder.registryGetIntValue(string, "ColorizationColorBalance", (int)((long)-230119320 ^ (long)230119319));
    }

    private static native int registryGetIntValue(String var0, String var1, int var2);

    @Override
    public void addChangeListener(ChangeListener changeListener) {
        this.listenerList.add(ChangeListener.class, changeListener);
    }

    @Override
    public void removeChangeListener(ChangeListener changeListener) {
        this.listenerList.remove(ChangeListener.class, changeListener);
    }

    private void fireStateChanged() {
        Object[] arrobject = this.listenerList.getListenerList();
        if (arrobject.length == 0) {
            return;
        }
        ChangeEvent changeEvent = new ChangeEvent(this);
        for (int i2 = (int)-1436864352L ^ 0xAA5B30A0; i2 < arrobject.length; i2 += 2) {
            if (arrobject[i2] != ChangeListener.class) continue;
            ((ChangeListener)arrobject[i2 + ((int)-584400682L ^ 0xDD2AC0D7)]).stateChanged(changeEvent);
        }
    }

    void fireStateChangedLaterOnce() {
        EventQueue.invokeLater(() -> {
            if (this.fireStateChangedTimer != null) {
                this.fireStateChangedTimer.restart();
                return;
            }
            this.fireStateChangedTimer = new Timer((int)((long)-1460844740 ^ (long)-1460844681) << 2, actionEvent -> {
                this.fireStateChangedTimer = null;
                this.colorizationUpToDate = (int)-100724019L ^ 0xF9FF12CD;
                this.fireStateChanged();
            });
            this.fireStateChangedTimer.setRepeats((boolean)((long)52420094 ^ (long)52420094));
            this.fireStateChangedTimer.start();
        });
    }

    private class WndProc {
        private static final int HTCLIENT = 1;
        private static final int HTCAPTION = 2;
        private static final int HTSYSMENU = 3;
        private static final int HTTOP = 12;
        private Window window;
        private final long hwnd;
        private int titleBarHeight;
        private Rectangle[] hitTestSpots;
        private Rectangle appIconBounds;

        WndProc(Window window) {
            this.window = window;
            this.hwnd = this.installImpl(window);
            if (window instanceof JFrame && ((JFrame)window).getExtendedState() != 0) {
                EventQueue.invokeLater(() -> this.updateFrame(this.hwnd));
            } else {
                this.updateFrame(this.hwnd);
            }
        }

        void uninstall() {
            this.uninstallImpl(this.hwnd);
            this.window = null;
        }

        private native long installImpl(Window var1);

        private native void uninstallImpl(long var1);

        private native void updateFrame(long var1);

        private native void showWindow(long var1, int var3);

        private int onNcHitTest(int n2, int n3, boolean bl) {
            int n4;
            Point point = this.scaleDown(n2, n3);
            int n5 = point.x;
            int n6 = point.y;
            if (this.appIconBounds != null && this.appIconBounds.contains(n5, n6)) {
                return (int)((long)1969994377 ^ (long)1969994378);
            }
            int n7 = n4 = n6 < this.titleBarHeight ? (int)((long)1687861026 ^ (long)1687861027) : (int)((long)-1097214340 ^ (long)-1097214340);
            if (n4 != 0) {
                Rectangle[] arrrectangle;
                Rectangle[] arrrectangle2 = arrrectangle = this.hitTestSpots;
                int n8 = arrrectangle2.length;
                for (int i2 = (int)1838922672L ^ 0x6D9BBBB0; i2 < n8; ++i2) {
                    Rectangle rectangle = arrrectangle2[i2];
                    if (!rectangle.contains(n5, n6)) continue;
                    return (int)((long)-1074044383 ^ (long)-1074044384);
                }
                return bl ? ((int)493242105L ^ 0x1D6646FA) << 2 : ((int)-1140608764L ^ 0xBC03B105) << 1;
            }
            return bl ? (int)((long)-589237644 ^ (long)-589237641) << 2 : (int)((long)-1816215718 ^ (long)-1816215717);
        }

        private Point scaleDown(int n2, int n3) {
            GraphicsConfiguration graphicsConfiguration = this.window.getGraphicsConfiguration();
            if (graphicsConfiguration == null) {
                return new Point(n2, n3);
            }
            AffineTransform affineTransform = graphicsConfiguration.getDefaultTransform();
            return new Point(this.clipRound((double)n2 / affineTransform.getScaleX()), this.clipRound((double)n3 / affineTransform.getScaleY()));
        }

        private int clipRound(double d2) {
            if ((d2 -= Double.longBitsToDouble(0xC0B55281F0AF93C6L ^ 0xFF555281F0AF93C6L)) < Double.longBitsToDouble((long)675046805 ^ 0xC1E00000283C6595L)) {
                return (int)((long)-180432011 ^ (long)1967051637);
            }
            if (d2 > Double.longBitsToDouble(0xE27D5DFF0CD61EDDL ^ 0xA3A2A200F3161EDDL)) {
                return Integer.MAX_VALUE;
            }
            return (int)Math.ceil(d2);
        }

        private boolean isFullscreen() {
            GraphicsConfiguration graphicsConfiguration = this.window.getGraphicsConfiguration();
            if (graphicsConfiguration == null) {
                return ((int)590027528L ^ 0x232B1B08) != 0;
            }
            return (graphicsConfiguration.getDevice().getFullScreenWindow() == this.window ? (int)((long)-1307353868 ^ (long)-1307353867) : (int)((long)-876105541 ^ (long)-876105541)) != 0;
        }

        private void fireStateChangedLaterOnce() {
            FlatWindowsNativeWindowBorder.this.fireStateChangedLaterOnce();
        }

        static /* synthetic */ Rectangle[] access$202(WndProc wndProc, Rectangle[] arrrectangle) {
            wndProc.hitTestSpots = arrrectangle;
            return arrrectangle;
        }
    }
}

