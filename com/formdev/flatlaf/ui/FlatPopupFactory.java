/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatDropShadowBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.JWindow;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class FlatPopupFactory
extends PopupFactory {
    private Method java8getPopupMethod;
    private Method java9getPopupMethod;

    @Override
    public Popup getPopup(Component component, Component component2, int n2, int n3) throws IllegalArgumentException {
        Point point = this.fixToolTipLocation(component2, n2, n3);
        if (point != null) {
            n2 = point.x;
            n3 = point.y;
        }
        boolean bl = this.isOptionEnabled(component, component2, "Popup.forceHeavyWeight", "Popup.forceHeavyWeight");
        if (!this.isOptionEnabled(component, component2, "Popup.dropShadowPainted", "Popup.dropShadowPainted") || SystemInfo.isProjector || SystemInfo.isWebswing) {
            return new NonFlashingPopup(this.getPopupForScreenOfOwner(component, component2, n2, n3, bl), component2);
        }
        if (SystemInfo.isMacOS || SystemInfo.isLinux) {
            return new NonFlashingPopup(this.getPopupForScreenOfOwner(component, component2, n2, n3, ((int)-1586643967L ^ 0xA16DBC00) != 0), component2);
        }
        return new DropShadowPopup(this.getPopupForScreenOfOwner(component, component2, n2, n3, bl), component, component2);
    }

    private Popup getPopupForScreenOfOwner(Component component, Component component2, int n2, int n3, boolean bl) throws IllegalArgumentException {
        Popup popup;
        int n4 = (int)((long)-746112952 ^ (long)-746112952);
        do {
            popup = bl ? this.getHeavyWeightPopup(component, component2, n2, n3) : super.getPopup(component, component2, n2, n3);
            Window window = SwingUtilities.windowForComponent(component2);
            if (window == null || component == null || window.getGraphicsConfiguration() == component.getGraphicsConfiguration()) {
                return popup;
            }
            if (window instanceof JWindow) {
                ((JWindow)window).getContentPane().removeAll();
            }
            window.dispose();
        } while (++n4 <= (int)((long)-1529998397 ^ (long)-1529998394) << 1);
        return popup;
    }

    private static void showPopupAndFixLocation(Popup popup, Window window) {
        if (window != null) {
            int n2 = window.getX();
            int n3 = window.getY();
            popup.show();
            if (window.getX() != n2 || window.getY() != n3) {
                window.setLocation(n2, n3);
            }
        } else {
            popup.show();
        }
    }

    private boolean isOptionEnabled(Component component, Component component2, String string, String string2) {
        Boolean bl;
        if (component instanceof JComponent && (bl = FlatClientProperties.clientPropertyBooleanStrict((JComponent)component, string, null)) != null) {
            return bl;
        }
        if (component2 instanceof JComponent && (bl = FlatClientProperties.clientPropertyBooleanStrict((JComponent)component2, string, null)) != null) {
            return bl;
        }
        return UIManager.getBoolean(string2);
    }

    private Popup getHeavyWeightPopup(Component component, Component component2, int n2, int n3) throws IllegalArgumentException {
        try {
            if (SystemInfo.isJava_9_orLater) {
                if (this.java9getPopupMethod == null) {
                    Class[] arrclass = new Class[(int)82241831L ^ 0x4E6E922];
                    arrclass[(int)((long)-751827545 ^ (long)-751827545)] = Component.class;
                    arrclass[(int)((long)-460841861 ^ (long)-460841862)] = Component.class;
                    arrclass[(int)((long)-1868018744 ^ (long)-1868018743) << 1] = Integer.TYPE;
                    arrclass[(int)((long)-1632297850 ^ (long)-1632297851)] = Integer.TYPE;
                    arrclass[((int)-1379855659L ^ -1379855660) << 2] = Boolean.TYPE;
                    this.java9getPopupMethod = PopupFactory.class.getDeclaredMethod("getPopup", arrclass);
                }
                Object[] arrobject = new Object[(int)-1239111545L ^ 0xB624A882];
                arrobject[(int)1217962968L ^ 1217962968] = component;
                arrobject[(int)131554289L ^ 131554288] = component2;
                arrobject[(int)((long)-645065145 ^ (long)-645065146) << 1] = n2;
                arrobject[(int)-2110877245L ^ -2110877248] = n3;
                arrobject[((int)-1566571579L ^ -1566571580) << 2] = (boolean)((long)1357509319 ^ (long)1357509318);
                return (Popup)this.java9getPopupMethod.invoke(this, arrobject);
            }
            if (this.java8getPopupMethod == null) {
                Class[] arrclass = new Class[(int)((long)-525964953 ^ (long)-525964958)];
                arrclass[(int)127048432L ^ 127048432] = Component.class;
                arrclass[(int)-1183885843L ^ -1183885844] = Component.class;
                arrclass[((int)773336497L ^ 773336496) << 1] = Integer.TYPE;
                arrclass[(int)220896904L ^ 220896907] = Integer.TYPE;
                arrclass[((int)-201262052L ^ -201262051) << 2] = Integer.TYPE;
                this.java8getPopupMethod = PopupFactory.class.getDeclaredMethod("getPopup", arrclass);
                this.java8getPopupMethod.setAccessible(((int)2145487525L ^ 0x7FE18AA4) != 0);
            }
            Object[] arrobject = new Object[(int)1887493385L ^ 0x7080DD0C];
            arrobject[(int)1575206141L ^ 1575206141] = component;
            arrobject[(int)((long)864251381 ^ (long)864251380)] = component2;
            arrobject[((int)451641839L ^ 451641838) << 1] = n2;
            arrobject[(int)-372432024L ^ -372432021] = n3;
            arrobject[((int)-1900352369L ^ -1900352370) << 2] = (int)((long)740927073 ^ (long)740927072) << 1;
            return (Popup)this.java8getPopupMethod.invoke(this, arrobject);
        }
        catch (IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException exception) {
            return null;
        }
    }

    private Point fixToolTipLocation(Component component, int n2, int n3) {
        if (!(component instanceof JToolTip) || !this.wasInvokedFromToolTipManager()) {
            return null;
        }
        Point point = MouseInfo.getPointerInfo().getLocation();
        Dimension dimension = component.getPreferredSize();
        Rectangle rectangle = new Rectangle(n2, n3, dimension.width, dimension.height);
        if (!rectangle.contains(point)) {
            return null;
        }
        return new Point(n2, point.y - dimension.height - UIScale.scale((int)((long)-522885102 ^ (long)-522885097) << 2));
    }

    private boolean wasInvokedFromToolTipManager() {
        StackTraceElement[] arrstackTraceElement;
        StackTraceElement[] arrstackTraceElement2 = arrstackTraceElement = Thread.currentThread().getStackTrace();
        int n2 = arrstackTraceElement2.length;
        for (int i2 = (int)((long)1478254158 ^ (long)1478254158); i2 < n2; ++i2) {
            StackTraceElement stackTraceElement = arrstackTraceElement2[i2];
            if (!"javax.swing.ToolTipManager".equals(stackTraceElement.getClassName()) || !"showTipWindow".equals(stackTraceElement.getMethodName())) continue;
            return ((int)385174593L ^ 0x16F54C40) != 0;
        }
        return ((int)551430504L ^ 0x20DE2968) != 0;
    }

    private class DropShadowPopup
    extends NonFlashingPopup {
        private final Component owner;
        private JComponent lightComp;
        private Border oldBorder;
        private boolean oldOpaque;
        private boolean mediumWeightShown;
        private Panel mediumWeightPanel;
        private JPanel dropShadowPanel;
        private ComponentListener mediumPanelListener;
        private Popup dropShadowDelegate;
        private Window dropShadowWindow;
        private Color oldDropShadowWindowBackground;

        DropShadowPopup(Popup popup, Component component, Component component2) {
            super(popup, component2);
            this.owner = component;
            Dimension dimension = component2.getPreferredSize();
            if (dimension.width <= 0 || dimension.height <= 0) {
                return;
            }
            if (this.popupWindow != null) {
                JPanel jPanel = new JPanel();
                jPanel.setBorder(this.createDropShadowBorder());
                jPanel.setOpaque((boolean)((long)155150229 ^ (long)155150229));
                Dimension dimension2 = this.popupWindow.getPreferredSize();
                Insets insets = jPanel.getInsets();
                jPanel.setPreferredSize(new Dimension(dimension2.width + insets.left + insets.right, dimension2.height + insets.top + insets.bottom));
                int n2 = this.popupWindow.getX() - insets.left;
                int n3 = this.popupWindow.getY() - insets.top;
                this.dropShadowDelegate = FlatPopupFactory.this.getPopupForScreenOfOwner(component, jPanel, n2, n3, (boolean)((long)112108020 ^ (long)112108021));
                this.dropShadowWindow = SwingUtilities.windowForComponent(jPanel);
                if (this.dropShadowWindow != null) {
                    this.oldDropShadowWindowBackground = this.dropShadowWindow.getBackground();
                    this.dropShadowWindow.setBackground(new Color((int)524802100L ^ 0x1F47D834, (boolean)((long)-1789806688 ^ (long)-1789806687)));
                }
            } else {
                this.mediumWeightPanel = (Panel)SwingUtilities.getAncestorOfClass(Panel.class, component2);
                if (this.mediumWeightPanel != null) {
                    this.dropShadowPanel = new JPanel();
                    this.dropShadowPanel.setBorder(this.createDropShadowBorder());
                    this.dropShadowPanel.setOpaque(((int)1157627995L ^ 0x4500005B) != 0);
                    this.dropShadowPanel.setSize(FlatUIUtils.addInsets(this.mediumWeightPanel.getSize(), this.dropShadowPanel.getInsets()));
                } else {
                    Container container = component2.getParent();
                    if (!(container instanceof JComponent)) {
                        return;
                    }
                    this.lightComp = (JComponent)container;
                    this.oldBorder = this.lightComp.getBorder();
                    this.oldOpaque = this.lightComp.isOpaque();
                    this.lightComp.setBorder(this.createDropShadowBorder());
                    this.lightComp.setOpaque(((int)428366988L ^ 0x19885C8C) != 0);
                    this.lightComp.setSize(this.lightComp.getPreferredSize());
                }
            }
        }

        private Border createDropShadowBorder() {
            return new FlatDropShadowBorder(UIManager.getColor("Popup.dropShadowColor"), UIManager.getInsets("Popup.dropShadowInsets"), FlatUIUtils.getUIFloat("Popup.dropShadowOpacity", Float.intBitsToFloat((int)((long)729025914 ^ (long)343149946))));
        }

        @Override
        public void show() {
            if (this.dropShadowDelegate != null) {
                FlatPopupFactory.showPopupAndFixLocation(this.dropShadowDelegate, this.dropShadowWindow);
            }
            if (this.mediumWeightPanel != null) {
                this.showMediumWeightDropShadow();
            }
            super.show();
            if (this.lightComp != null) {
                Insets insets = this.lightComp.getInsets();
                if (insets.left != 0 || insets.top != 0) {
                    this.lightComp.setLocation(this.lightComp.getX() - insets.left, this.lightComp.getY() - insets.top);
                }
            }
        }

        @Override
        public void hide() {
            if (this.dropShadowDelegate != null) {
                this.dropShadowDelegate.hide();
                this.dropShadowDelegate = null;
            }
            if (this.mediumWeightPanel != null) {
                this.hideMediumWeightDropShadow();
                this.dropShadowPanel = null;
                this.mediumWeightPanel = null;
            }
            super.hide();
            if (this.dropShadowWindow != null) {
                this.dropShadowWindow.setBackground(this.oldDropShadowWindowBackground);
                this.dropShadowWindow = null;
            }
            if (this.lightComp != null) {
                this.lightComp.setBorder(this.oldBorder);
                this.lightComp.setOpaque(this.oldOpaque);
                this.lightComp = null;
            }
        }

        private void showMediumWeightDropShadow() {
            if (this.mediumWeightShown) {
                return;
            }
            this.mediumWeightShown = (int)-1458098525L ^ 0xA9172EA2;
            if (this.owner == null) {
                return;
            }
            Window window = SwingUtilities.windowForComponent(this.owner);
            if (!(window instanceof RootPaneContainer)) {
                return;
            }
            this.dropShadowPanel.setVisible(((int)-130260558L ^ 0xF83C61B2) != 0);
            JLayeredPane jLayeredPane = ((RootPaneContainer)((Object)window)).getLayeredPane();
            jLayeredPane.add(this.dropShadowPanel, JLayeredPane.POPUP_LAYER, (int)1426669707L ^ 0x5509408B);
            this.mediumPanelListener = new ComponentListener(){

                @Override
                public void componentShown(ComponentEvent componentEvent) {
                    if (DropShadowPopup.this.dropShadowPanel != null) {
                        DropShadowPopup.this.dropShadowPanel.setVisible(((int)1297882268L ^ 0x4D5C1C9D) != 0);
                    }
                }

                @Override
                public void componentHidden(ComponentEvent componentEvent) {
                    if (DropShadowPopup.this.dropShadowPanel != null) {
                        DropShadowPopup.this.dropShadowPanel.setVisible(((int)1602095899L ^ 0x5F7E0B1B) != 0);
                    }
                }

                @Override
                public void componentMoved(ComponentEvent componentEvent) {
                    if (DropShadowPopup.this.dropShadowPanel != null && DropShadowPopup.this.mediumWeightPanel != null) {
                        Point point = DropShadowPopup.this.mediumWeightPanel.getLocation();
                        Insets insets = DropShadowPopup.this.dropShadowPanel.getInsets();
                        DropShadowPopup.this.dropShadowPanel.setLocation(point.x - insets.left, point.y - insets.top);
                    }
                }

                @Override
                public void componentResized(ComponentEvent componentEvent) {
                    if (DropShadowPopup.this.dropShadowPanel != null) {
                        DropShadowPopup.this.dropShadowPanel.setSize(FlatUIUtils.addInsets(DropShadowPopup.this.mediumWeightPanel.getSize(), DropShadowPopup.this.dropShadowPanel.getInsets()));
                    }
                }
            };
            this.mediumWeightPanel.addComponentListener(this.mediumPanelListener);
        }

        private void hideMediumWeightDropShadow() {
            this.mediumWeightPanel.removeComponentListener(this.mediumPanelListener);
            Container container = this.dropShadowPanel.getParent();
            if (container != null) {
                Rectangle rectangle = this.dropShadowPanel.getBounds();
                container.remove(this.dropShadowPanel);
                container.repaint(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
    }

    private class NonFlashingPopup
    extends Popup {
        private Popup delegate;
        private Component contents;
        protected Window popupWindow;
        private Color oldPopupWindowBackground;

        NonFlashingPopup(Popup popup, Component component) {
            this.delegate = popup;
            this.contents = component;
            this.popupWindow = SwingUtilities.windowForComponent(component);
            if (this.popupWindow != null) {
                this.oldPopupWindowBackground = this.popupWindow.getBackground();
                this.popupWindow.setBackground(component.getBackground());
            }
        }

        @Override
        public void show() {
            if (this.delegate != null) {
                Dimension dimension;
                Container container;
                FlatPopupFactory.showPopupAndFixLocation(this.delegate, this.popupWindow);
                if (this.contents instanceof JToolTip && this.popupWindow == null && (container = this.contents.getParent()) instanceof JPanel && !(dimension = container.getPreferredSize()).equals(container.getSize())) {
                    Container container2 = SwingUtilities.getAncestorOfClass(Panel.class, container);
                    Container container3 = container2 != null ? container2 : container;
                    container3.setSize(dimension);
                    container3.validate();
                }
            }
        }

        @Override
        public void hide() {
            if (this.delegate != null) {
                this.delegate.hide();
                this.delegate = null;
                this.contents = null;
            }
            if (this.popupWindow != null) {
                this.popupWindow.setBackground(this.oldPopupWindowBackground);
                this.popupWindow = null;
            }
        }
    }
}

