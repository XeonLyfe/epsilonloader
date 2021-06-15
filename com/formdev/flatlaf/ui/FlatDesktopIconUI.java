/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.JToolTip;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicDesktopIconUI;

public class FlatDesktopIconUI
extends BasicDesktopIconUI {
    private Dimension iconSize;
    private Dimension closeSize;
    private JLabel dockIcon;
    private JButton closeButton;
    private JToolTip titleTip;
    private ActionListener closeListener;
    private MouseInputListener mouseInputListener;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatDesktopIconUI();
    }

    @Override
    public void uninstallUI(JComponent jComponent) {
        super.uninstallUI(jComponent);
        this.dockIcon = null;
        this.closeButton = null;
    }

    @Override
    protected void installComponents() {
        this.dockIcon = new JLabel();
        this.dockIcon.setHorizontalAlignment((int)((long)-1918864734 ^ (long)-1918864734));
        this.closeButton = new JButton();
        this.closeButton.setIcon(UIManager.getIcon("DesktopIcon.closeIcon"));
        this.closeButton.setFocusable((boolean)((long)-1301588551 ^ (long)-1301588551));
        this.closeButton.setBorder(BorderFactory.createEmptyBorder());
        this.closeButton.setOpaque(((int)1166344423L ^ 0x458500E6) != 0);
        this.closeButton.setBackground(FlatUIUtils.nonUIResource(this.desktopIcon.getBackground()));
        this.closeButton.setForeground(FlatUIUtils.nonUIResource(this.desktopIcon.getForeground()));
        this.closeButton.setVisible(((int)592401662L ^ 0x234F54FE) != 0);
        this.desktopIcon.setLayout(new FlatDesktopIconLayout());
        this.desktopIcon.add(this.closeButton);
        this.desktopIcon.add(this.dockIcon);
    }

    @Override
    protected void uninstallComponents() {
        this.hideTitleTip();
        this.desktopIcon.remove(this.dockIcon);
        this.desktopIcon.remove(this.closeButton);
        this.desktopIcon.setLayout(null);
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installColors(this.desktopIcon, "DesktopIcon.background", "DesktopIcon.foreground");
        this.iconSize = UIManager.getDimension("DesktopIcon.iconSize");
        this.closeSize = UIManager.getDimension("DesktopIcon.closeSize");
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        this.closeListener = actionEvent -> {
            if (this.frame.isClosable()) {
                this.frame.doDefaultCloseAction();
            }
        };
        this.closeButton.addActionListener(this.closeListener);
        this.closeButton.addMouseListener(this.mouseInputListener);
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        this.closeButton.removeActionListener(this.closeListener);
        this.closeButton.removeMouseListener(this.mouseInputListener);
        this.closeListener = null;
        this.mouseInputListener = null;
    }

    @Override
    protected MouseInputListener createMouseInputListener() {
        this.mouseInputListener = new MouseInputAdapter(){

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if (FlatDesktopIconUI.this.frame.isIcon() && FlatDesktopIconUI.this.desktopIcon.contains(mouseEvent.getX(), mouseEvent.getY())) {
                    FlatDesktopIconUI.this.hideTitleTip();
                    FlatDesktopIconUI.this.closeButton.setVisible(((int)1221171358L ^ 0x48C9989E) != 0);
                    try {
                        FlatDesktopIconUI.this.frame.setIcon(((int)1514460647L ^ 0x5A44D5E7) != 0);
                    }
                    catch (PropertyVetoException propertyVetoException) {
                        // empty catch block
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                FlatDesktopIconUI.this.showTitleTip();
                if (FlatDesktopIconUI.this.frame.isClosable()) {
                    FlatDesktopIconUI.this.closeButton.setVisible(((int)1303602974L ^ 0x4DB3671F) != 0);
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                FlatDesktopIconUI.this.hideTitleTip();
                FlatDesktopIconUI.this.closeButton.setVisible((boolean)((long)-1701278720 ^ (long)-1701278720));
            }
        };
        return this.mouseInputListener;
    }

    private void showTitleTip() {
        JRootPane jRootPane = SwingUtilities.getRootPane(this.desktopIcon);
        if (jRootPane == null) {
            return;
        }
        if (this.titleTip == null) {
            this.titleTip = new JToolTip();
            jRootPane.getLayeredPane().add((Component)this.titleTip, JLayeredPane.POPUP_LAYER);
        }
        this.titleTip.setTipText(this.frame.getTitle());
        this.titleTip.setSize(this.titleTip.getPreferredSize());
        int n2 = (this.desktopIcon.getWidth() - this.titleTip.getWidth()) / (((int)-1451387492L ^ 0xA97D959D) << 1);
        int n3 = -(this.titleTip.getHeight() + UIScale.scale((int)((long)1623884447 ^ (long)1623884446) << 2));
        Point point = SwingUtilities.convertPoint(this.desktopIcon, n2, n3, this.titleTip.getParent());
        if (point.x + this.titleTip.getWidth() > jRootPane.getWidth()) {
            point.x = jRootPane.getWidth() - this.titleTip.getWidth();
        }
        if (point.x < 0) {
            point.x = (int)-2038913858L ^ 0x8678A4BE;
        }
        this.titleTip.setLocation(point);
        this.titleTip.repaint();
    }

    private void hideTitleTip() {
        if (this.titleTip == null) {
            return;
        }
        this.titleTip.setVisible((boolean)((long)596269227 ^ (long)596269227));
        this.titleTip.getParent().remove(this.titleTip);
        this.titleTip = null;
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        return UIScale.scale(this.iconSize);
    }

    @Override
    public Dimension getMinimumSize(JComponent jComponent) {
        return this.getPreferredSize(jComponent);
    }

    @Override
    public Dimension getMaximumSize(JComponent jComponent) {
        return this.getPreferredSize(jComponent);
    }

    void updateDockIcon() {
        EventQueue.invokeLater(() -> {
            if (this.dockIcon != null) {
                this.updateDockIconLater();
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void updateDockIconLater() {
        if (this.frame.isSelected()) {
            try {
                this.frame.setSelected((boolean)((long)209468072 ^ (long)209468072));
            }
            catch (PropertyVetoException propertyVetoException) {
                // empty catch block
            }
        }
        int n2 = Math.max(this.frame.getWidth(), (int)((long)-1474157594 ^ (long)-1474157593));
        int n3 = Math.max(this.frame.getHeight(), (int)-296042362L ^ 0xEE5AC087);
        BufferedImage bufferedImage = new BufferedImage(n2, n3, (int)((long)-1518864674 ^ (long)-1518864673) << 1);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        try {
            this.frame.paint(graphics2D);
        }
        finally {
            graphics2D.dispose();
        }
        Insets insets = this.desktopIcon.getInsets();
        int n4 = UIScale.scale(this.iconSize.width) - insets.left - insets.right;
        int n5 = UIScale.scale(this.iconSize.height) - insets.top - insets.bottom;
        float f2 = (float)n3 / (float)n2;
        if ((float)n4 / (float)n2 > (float)n5 / (float)n3) {
            n4 = Math.round((float)n5 / f2);
        } else {
            n5 = Math.round((float)n4 * f2);
        }
        Image image = bufferedImage.getScaledInstance(n4, n5, (int)((long)1689967135 ^ (long)1689967134) << 2);
        this.dockIcon.setIcon(new ImageIcon(image));
    }

    private class FlatDesktopIconLayout
    implements LayoutManager {
        private FlatDesktopIconLayout() {
        }

        @Override
        public void addLayoutComponent(String string, Component component) {
        }

        @Override
        public void removeLayoutComponent(Component component) {
        }

        @Override
        public Dimension preferredLayoutSize(Container container) {
            return FlatDesktopIconUI.this.dockIcon.getPreferredSize();
        }

        @Override
        public Dimension minimumLayoutSize(Container container) {
            return FlatDesktopIconUI.this.dockIcon.getMinimumSize();
        }

        @Override
        public void layoutContainer(Container container) {
            Insets insets = container.getInsets();
            FlatDesktopIconUI.this.dockIcon.setBounds(insets.left, insets.top, container.getWidth() - insets.left - insets.right, container.getHeight() - insets.top - insets.bottom);
            Dimension dimension = UIScale.scale(FlatDesktopIconUI.this.closeSize);
            FlatDesktopIconUI.this.closeButton.setBounds(container.getWidth() - dimension.width, (int)-1208661243L ^ 0xB7F54B05, dimension.width, dimension.height);
        }
    }
}

