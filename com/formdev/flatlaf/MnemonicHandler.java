/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.ref.WeakReference;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class MnemonicHandler
implements KeyEventPostProcessor,
ChangeListener {
    private static boolean showMnemonics;
    private static WeakReference<Window> lastShowMnemonicWindow;
    private static WindowListener windowListener;
    private static int altPressedEventCount;
    private static boolean selectMenuOnAltReleased;

    MnemonicHandler() {
    }

    static boolean isShowMnemonics() {
        return (showMnemonics || !UIManager.getBoolean("Component.hideMnemonics") ? (int)1482358249L ^ 0x585AFDE8 : (int)((long)277116377 ^ (long)277116377)) != 0;
    }

    void install() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(this);
        MenuSelectionManager.defaultManager().addChangeListener(this);
    }

    void uninstall() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventPostProcessor(this);
        MenuSelectionManager.defaultManager().removeChangeListener(this);
    }

    @Override
    public boolean postProcessKeyEvent(KeyEvent keyEvent) {
        int n2 = keyEvent.getKeyCode();
        if (SystemInfo.isMacOS) {
            if (n2 == (int)((long)-630100739 ^ (long)-630100756) || n2 == ((int)270140702L ^ 0x101A0517) << 1) {
                MnemonicHandler.showMnemonics((this.shouldShowMnemonics(keyEvent) && keyEvent.isControlDown() && keyEvent.isAltDown() ? (int)((long)-564958403 ^ (long)-564958404) : (int)-186295993L ^ 0xF4E55947) != 0, keyEvent.getComponent());
            }
        } else {
            if (SystemInfo.isWindows) {
                return this.processKeyEventOnWindows(keyEvent);
            }
            if (n2 == ((int)1485908084L ^ 0x5891287D) << 1) {
                MnemonicHandler.showMnemonics(this.shouldShowMnemonics(keyEvent), keyEvent.getComponent());
            }
        }
        return ((int)-622101749L ^ 0xDAEB7B0B) != 0;
    }

    private boolean shouldShowMnemonics(KeyEvent keyEvent) {
        return (keyEvent.getID() == ((int)-1987523524L ^ 0x8988CDAD) || MenuSelectionManager.defaultManager().getSelectedPath().length > 0 ? (int)-1265340202L ^ 0xB49470D7 : (int)-355539558L ^ 0xEACEE59A) != 0;
    }

    private boolean processKeyEventOnWindows(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != (int)((long)-1675266638 ^ (long)-1675266629) << 1) {
            selectMenuOnAltReleased = (int)((long)-1278480756 ^ (long)-1278480756);
            return (int)((long)1202868079 ^ (long)1202868079) != 0;
        }
        if (keyEvent.getID() == ((int)464124107L ^ 0x1BA9F95A)) {
            if ((altPressedEventCount += (int)((long)-564935797 ^ (long)-564935798)) == ((int)-1450596183L ^ 0xA989A8A8) && !keyEvent.isConsumed()) {
                MenuSelectionManager menuSelectionManager = MenuSelectionManager.defaultManager();
                selectMenuOnAltReleased = menuSelectionManager.getSelectedPath().length == 0 ? (int)((long)124327510 ^ (long)124327511) : (int)((long)1431601060 ^ (long)1431601060);
                int n2 = selectMenuOnAltReleased ? 1 : 0;
                if (!selectMenuOnAltReleased) {
                    menuSelectionManager.clearSelectedPath();
                }
            }
            MnemonicHandler.showMnemonics(this.shouldShowMnemonics(keyEvent), keyEvent.getComponent());
            keyEvent.consume();
            return ((int)-1081411973L ^ 0xBF8AF67A) != 0;
        }
        if (keyEvent.getID() == (int)((long)1336354134 ^ (long)1336354207) << 1) {
            MenuSelectionManager menuSelectionManager;
            altPressedEventCount = (int)((long)2049074904 ^ (long)2049074904);
            int n3 = (int)((long)-191345139 ^ (long)-191345139);
            if (selectMenuOnAltReleased && !keyEvent.isConsumed() && (menuSelectionManager = MenuSelectionManager.defaultManager()).getSelectedPath().length == 0) {
                Window window;
                JMenuBar jMenuBar;
                Component component = keyEvent.getComponent();
                JRootPane jRootPane = SwingUtilities.getRootPane(component);
                JMenuBar jMenuBar2 = jMenuBar = jRootPane != null ? jRootPane.getJMenuBar() : null;
                if (jMenuBar == null) {
                    window = SwingUtilities.getWindowAncestor(component);
                    if (window instanceof JFrame) {
                        jMenuBar = ((JFrame)window).getJMenuBar();
                    } else if (window instanceof JDialog) {
                        jMenuBar = ((JDialog)window).getJMenuBar();
                    }
                }
                Container container = window = jMenuBar != null ? jMenuBar.getMenu((int)((long)-1984638879 ^ (long)-1984638879)) : null;
                if (window != null) {
                    MenuElement[] arrmenuElement = new MenuElement[((int)1375561881L ^ 0x51FD6898) << 1];
                    arrmenuElement[(int)((long)-679037395 ^ (long)-679037395)] = jMenuBar;
                    arrmenuElement[(int)((long)-905398199 ^ (long)-905398200)] = window;
                    menuSelectionManager.setSelectedPath(arrmenuElement);
                    MnemonicHandler.showMnemonics(((int)1583599804L ^ 0x5E63D0BD) != 0, component);
                    n3 = (int)-1772135729L ^ 0x965F5ACE;
                }
            }
            selectMenuOnAltReleased = (int)((long)1782493652 ^ (long)1782493652);
            if (n3 == 0) {
                MnemonicHandler.showMnemonics(this.shouldShowMnemonics(keyEvent), keyEvent.getComponent());
            }
        }
        return ((int)1859593236L ^ 0x6ED72414) != 0;
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        MenuElement[] arrmenuElement = MenuSelectionManager.defaultManager().getSelectedPath();
        if (arrmenuElement.length == 0 && altPressedEventCount == 0) {
            MnemonicHandler.showMnemonics((boolean)((long)-962570209 ^ (long)-962570209), null);
        }
    }

    static void showMnemonics(boolean bl, Component component) {
        if (bl == showMnemonics) {
            return;
        }
        showMnemonics = bl;
        if (!UIManager.getBoolean("Component.hideMnemonics")) {
            return;
        }
        if (bl) {
            JRootPane jRootPane = SwingUtilities.getRootPane(component);
            if (jRootPane == null) {
                return;
            }
            Window window = SwingUtilities.getWindowAncestor(jRootPane);
            if (window == null) {
                return;
            }
            MnemonicHandler.repaintMnemonics(window);
            windowListener = new WindowAdapter(){

                @Override
                public void windowDeactivated(WindowEvent windowEvent) {
                    altPressedEventCount = (int)1376325597L ^ 0x52090FDD;
                    selectMenuOnAltReleased = ((int)1814641652L ^ 0x6C293BF4) != 0;
                    EventQueue.invokeLater(() -> MnemonicHandler.showMnemonics((boolean)((long)1962227572 ^ (long)1962227572), null));
                }
            };
            window.addWindowListener(windowListener);
            lastShowMnemonicWindow = new WeakReference<Window>(window);
        } else if (lastShowMnemonicWindow != null) {
            Window window = (Window)lastShowMnemonicWindow.get();
            if (window != null) {
                MnemonicHandler.repaintMnemonics(window);
                if (windowListener != null) {
                    window.removeWindowListener(windowListener);
                    windowListener = null;
                }
            }
            lastShowMnemonicWindow = null;
        }
    }

    private static void repaintMnemonics(Container container) {
        Component[] arrcomponent = container.getComponents();
        int n2 = arrcomponent.length;
        for (int i2 = (int)((long)1729239591 ^ (long)1729239591); i2 < n2; ++i2) {
            Component component = arrcomponent[i2];
            if (!component.isVisible()) continue;
            if (MnemonicHandler.hasMnemonic(component)) {
                component.repaint();
            }
            if (!(component instanceof Container)) continue;
            MnemonicHandler.repaintMnemonics((Container)component);
        }
    }

    private static boolean hasMnemonic(Component component) {
        if (component instanceof JLabel && ((JLabel)component).getDisplayedMnemonicIndex() >= 0) {
            return ((int)-1293383443L ^ 0xB2E888EC) != 0;
        }
        if (component instanceof AbstractButton && ((AbstractButton)component).getDisplayedMnemonicIndex() >= 0) {
            return (int)((long)-1775423651 ^ (long)-1775423652) != 0;
        }
        if (component instanceof JTabbedPane) {
            JTabbedPane jTabbedPane = (JTabbedPane)component;
            int n2 = jTabbedPane.getTabCount();
            for (int i2 = (int)((long)-2138609005 ^ (long)-2138609005); i2 < n2; ++i2) {
                if (jTabbedPane.getDisplayedMnemonicIndexAt(i2) < 0) continue;
                return (int)((long)-1099074259 ^ (long)-1099074260) != 0;
            }
        }
        return ((int)-54620560L ^ 0xFCBE8E70) != 0;
    }
}

