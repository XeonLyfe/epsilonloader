/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLabelUI;
import com.formdev.flatlaf.ui.FlatNativeWindowBorder;
import com.formdev.flatlaf.ui.FlatTitlePaneIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.JBRCustomDecorations;
import com.formdev.flatlaf.util.ScaledImageIcon;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

public class FlatTitlePane
extends JComponent {
    protected final Color activeBackground = UIManager.getColor("TitlePane.background");
    protected final Color inactiveBackground = UIManager.getColor("TitlePane.inactiveBackground");
    protected final Color activeForeground = UIManager.getColor("TitlePane.foreground");
    protected final Color inactiveForeground = UIManager.getColor("TitlePane.inactiveForeground");
    protected final Color embeddedForeground = UIManager.getColor("TitlePane.embeddedForeground");
    protected final Color borderColor = UIManager.getColor("TitlePane.borderColor");
    protected final Dimension iconSize = UIManager.getDimension("TitlePane.iconSize");
    protected final int buttonMaximizedHeight = UIManager.getInt("TitlePane.buttonMaximizedHeight");
    protected final boolean centerTitle = UIManager.getBoolean("TitlePane.centerTitle");
    protected final boolean centerTitleIfMenuBarEmbedded = FlatUIUtils.getUIBoolean("TitlePane.centerTitleIfMenuBarEmbedded", (boolean)((long)1522488001 ^ (long)1522488000));
    protected final int menuBarTitleGap = FlatUIUtils.getUIInt("TitlePane.menuBarTitleGap", ((int)-735665956L ^ 0xD426A0D9) << 2);
    protected final JRootPane rootPane;
    protected JPanel leftPanel;
    protected JLabel iconLabel;
    protected JComponent menuBarPlaceholder;
    protected JLabel titleLabel;
    protected JPanel buttonPanel;
    protected JButton iconifyButton;
    protected JButton maximizeButton;
    protected JButton restoreButton;
    protected JButton closeButton;
    protected Window window;
    private final Handler handler;
    private static final int HIT_TEST_SPOT_GROW = 2;

    public FlatTitlePane(JRootPane jRootPane) {
        this.rootPane = jRootPane;
        this.handler = this.createHandler();
        this.setBorder(this.createTitlePaneBorder());
        this.addSubComponents();
        this.activeChanged(((int)917195393L ^ 0x36AB4A80) != 0);
        this.addMouseListener(this.handler);
        this.addMouseMotionListener(this.handler);
        this.iconLabel.addMouseListener(this.handler);
    }

    protected FlatTitlePaneBorder createTitlePaneBorder() {
        return new FlatTitlePaneBorder();
    }

    protected Handler createHandler() {
        return new Handler();
    }

    protected void addSubComponents() {
        this.leftPanel = new JPanel();
        this.iconLabel = new JLabel();
        this.titleLabel = new JLabel(){

            @Override
            public void updateUI() {
                this.setUI(new FlatTitleLabelUI());
            }
        };
        this.iconLabel.setBorder(new FlatEmptyBorder(UIManager.getInsets("TitlePane.iconMargins")));
        this.titleLabel.setBorder(new FlatEmptyBorder(UIManager.getInsets("TitlePane.titleMargins")));
        this.titleLabel.setHorizontalAlignment((int)138890445L ^ 0x8474CCD);
        this.leftPanel.setLayout(new BoxLayout(this.leftPanel, (int)((long)-422962712 ^ (long)-422962711) << 1));
        this.leftPanel.setOpaque((boolean)((long)1373600454 ^ (long)1373600454));
        this.leftPanel.add(this.iconLabel);
        this.menuBarPlaceholder = new JComponent(){

            @Override
            public Dimension getPreferredSize() {
                JMenuBar jMenuBar = FlatTitlePane.this.rootPane.getJMenuBar();
                return FlatTitlePane.this.hasVisibleEmbeddedMenuBar(jMenuBar) ? jMenuBar.getPreferredSize() : new Dimension();
            }
        };
        this.leftPanel.add(this.menuBarPlaceholder);
        this.createButtons();
        this.setLayout(new BorderLayout(){

            @Override
            public void layoutContainer(Container container) {
                Component component;
                JMenuBar jMenuBar;
                super.layoutContainer(container);
                Insets insets = container.getInsets();
                int n2 = container.getWidth() - insets.left - insets.right;
                if (FlatTitlePane.this.leftPanel.getWidth() + FlatTitlePane.this.buttonPanel.getWidth() > n2) {
                    int n3 = FlatTitlePane.this.leftPanel.getWidth();
                    int n4 = Math.max(n2 - FlatTitlePane.this.buttonPanel.getWidth(), (int)((long)1603996305 ^ (long)1603996305));
                    FlatTitlePane.this.leftPanel.setSize(n4, FlatTitlePane.this.leftPanel.getHeight());
                    if (!FlatTitlePane.this.getComponentOrientation().isLeftToRight()) {
                        FlatTitlePane.this.leftPanel.setLocation(FlatTitlePane.this.leftPanel.getX() + (n3 - n4), FlatTitlePane.this.leftPanel.getY());
                    }
                }
                if (FlatTitlePane.this.hasVisibleEmbeddedMenuBar(jMenuBar = FlatTitlePane.this.rootPane.getJMenuBar()) && (component = FlatTitlePane.this.findHorizontalGlue(jMenuBar)) != null) {
                    Point point = SwingUtilities.convertPoint(component, (int)((long)703168134 ^ (long)703168134), (int)((long)2020453004 ^ (long)2020453004), FlatTitlePane.this.titleLabel);
                    FlatTitlePane.this.titleLabel.setBounds(FlatTitlePane.this.titleLabel.getX() + point.x, FlatTitlePane.this.titleLabel.getY(), component.getWidth(), FlatTitlePane.this.titleLabel.getHeight());
                }
            }
        });
        this.add((Component)this.leftPanel, "Before");
        this.add((Component)this.titleLabel, "Center");
        this.add((Component)this.buttonPanel, "After");
    }

    protected void createButtons() {
        this.iconifyButton = this.createButton("TitlePane.iconifyIcon", "Iconify", actionEvent -> this.iconify());
        this.maximizeButton = this.createButton("TitlePane.maximizeIcon", "Maximize", actionEvent -> this.maximize());
        this.restoreButton = this.createButton("TitlePane.restoreIcon", "Restore", actionEvent -> this.restore());
        this.closeButton = this.createButton("TitlePane.closeIcon", "Close", actionEvent -> this.close());
        this.buttonPanel = new JPanel(){

            @Override
            public Dimension getPreferredSize() {
                Dimension dimension = super.getPreferredSize();
                if (FlatTitlePane.this.buttonMaximizedHeight > 0 && FlatTitlePane.this.window instanceof Frame && (((Frame)FlatTitlePane.this.window).getExtendedState() & ((int)-1096686783L ^ 0xBEA1E342) << 1) != 0) {
                    dimension = new Dimension(dimension.width, Math.min(dimension.height, UIScale.scale(FlatTitlePane.this.buttonMaximizedHeight)));
                }
                return dimension;
            }
        };
        this.buttonPanel.setOpaque((boolean)((long)-665222327 ^ (long)-665222327));
        this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, ((int)-524179080L ^ 0xE0C1A979) << 1));
        if (this.rootPane.getWindowDecorationStyle() == ((int)59233915L ^ 0x387D67A)) {
            this.restoreButton.setVisible((boolean)((long)1589674760 ^ (long)1589674760));
            this.buttonPanel.add(this.iconifyButton);
            this.buttonPanel.add(this.maximizeButton);
            this.buttonPanel.add(this.restoreButton);
        }
        this.buttonPanel.add(this.closeButton);
    }

    protected JButton createButton(String string, String string2, ActionListener actionListener) {
        JButton jButton = new JButton(UIManager.getIcon(string));
        jButton.setFocusable(((int)1385988701L ^ 0x529C825D) != 0);
        jButton.setContentAreaFilled(((int)855315346L ^ 0x32FB1392) != 0);
        jButton.setBorder(BorderFactory.createEmptyBorder());
        jButton.putClientProperty("AccessibleName", string2);
        jButton.addActionListener(actionListener);
        return jButton;
    }

    protected void activeChanged(boolean bl) {
        Color color;
        Color color2 = FlatClientProperties.clientPropertyColor(this.rootPane, "JRootPane.titleBarBackground", null);
        Color color3 = color = FlatClientProperties.clientPropertyColor(this.rootPane, "JRootPane.titleBarForeground", null);
        if (color2 == null) {
            color2 = FlatUIUtils.nonUIResource(bl ? this.activeBackground : this.inactiveBackground);
        }
        if (color == null) {
            color = FlatUIUtils.nonUIResource(bl ? this.activeForeground : this.inactiveForeground);
            color3 = bl && this.hasVisibleEmbeddedMenuBar(this.rootPane.getJMenuBar()) ? FlatUIUtils.nonUIResource(this.embeddedForeground) : color;
        }
        this.setBackground(color2);
        this.titleLabel.setForeground(color3);
        this.iconifyButton.setForeground(color);
        this.maximizeButton.setForeground(color);
        this.restoreButton.setForeground(color);
        this.closeButton.setForeground(color);
        this.iconifyButton.setBackground(color2);
        this.maximizeButton.setBackground(color2);
        this.restoreButton.setBackground(color2);
        this.closeButton.setBackground(color2);
    }

    protected void frameStateChanged() {
        if (this.window == null || this.rootPane.getWindowDecorationStyle() != (int)((long)-1042813034 ^ (long)-1042813033)) {
            return;
        }
        if (this.window instanceof Frame) {
            Frame frame = (Frame)this.window;
            boolean bl = frame.isResizable();
            int n2 = (frame.getExtendedState() & (int)((long)409680214 ^ (long)409680213) << 1) != 0 ? (int)-2138403295L ^ 0x808A8E20 : (int)-814973479L ^ 0xCF6C7DD9;
            this.iconifyButton.setVisible((boolean)((long)-2036700057 ^ (long)-2036700058));
            this.maximizeButton.setVisible((bl && n2 == 0 ? (int)1872638575L ^ 0x6F9E326E : (int)((long)-1131840686 ^ (long)-1131840686)) != 0);
            this.restoreButton.setVisible((bl && n2 != 0 ? (int)((long)501175744 ^ (long)501175745) : (int)-759155641L ^ 0xD2C03447) != 0);
            if (n2 != 0 && this.rootPane.getClientProperty("_flatlaf.maximizedBoundsUpToDate") == null) {
                this.rootPane.putClientProperty("_flatlaf.maximizedBoundsUpToDate", null);
                Rectangle rectangle = frame.getMaximizedBounds();
                this.updateMaximizedBounds();
                Rectangle rectangle2 = frame.getMaximizedBounds();
                if (rectangle2 != null && !rectangle2.equals(rectangle)) {
                    int n3 = frame.getExtendedState();
                    frame.setExtendedState(n3 & (int)((long)1069589365 ^ (long)-1069589364));
                    frame.setExtendedState(n3);
                }
            }
        } else {
            this.iconifyButton.setVisible((boolean)((long)-1108564257 ^ (long)-1108564257));
            this.maximizeButton.setVisible((boolean)((long)1831980118 ^ (long)1831980118));
            this.restoreButton.setVisible((boolean)((long)365949689 ^ (long)365949689));
            this.revalidate();
            this.repaint();
        }
    }

    protected void updateIcon() {
        List<Image> list = this.window.getIconImages();
        if (list.isEmpty()) {
            for (Window window = this.window.getOwner(); window != null && (list = window.getIconImages()).isEmpty(); window = window.getOwner()) {
            }
        }
        int n2 = (int)-1404665104L ^ 0xAC4682F1;
        if (!list.isEmpty()) {
            this.iconLabel.setIcon(FlatTitlePaneIcon.create(list, this.iconSize));
        } else {
            Icon icon = UIManager.getIcon("TitlePane.icon");
            if (icon != null && (icon.getIconWidth() == 0 || icon.getIconHeight() == 0)) {
                icon = null;
            }
            if (icon != null) {
                if (icon instanceof ImageIcon) {
                    icon = new ScaledImageIcon((ImageIcon)icon, this.iconSize.width, this.iconSize.height);
                }
                this.iconLabel.setIcon(icon);
            } else {
                n2 = (int)379384081L ^ 0x169CF111;
            }
        }
        this.iconLabel.setVisible(n2 != 0);
        this.updateNativeTitleBarHeightAndHitTestSpotsLater();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        this.uninstallWindowListeners();
        this.window = SwingUtilities.getWindowAncestor(this);
        if (this.window != null) {
            this.frameStateChanged();
            this.activeChanged(this.window.isActive());
            this.updateIcon();
            this.titleLabel.setText(this.getWindowTitle());
            this.installWindowListeners();
        }
        this.updateNativeTitleBarHeightAndHitTestSpotsLater();
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        this.uninstallWindowListeners();
        this.window = null;
    }

    protected String getWindowTitle() {
        if (this.window instanceof Frame) {
            return ((Frame)this.window).getTitle();
        }
        if (this.window instanceof Dialog) {
            return ((Dialog)this.window).getTitle();
        }
        return null;
    }

    protected void installWindowListeners() {
        if (this.window == null) {
            return;
        }
        this.window.addPropertyChangeListener(this.handler);
        this.window.addWindowListener(this.handler);
        this.window.addWindowStateListener(this.handler);
        this.window.addComponentListener(this.handler);
    }

    protected void uninstallWindowListeners() {
        if (this.window == null) {
            return;
        }
        this.window.removePropertyChangeListener(this.handler);
        this.window.removeWindowListener(this.handler);
        this.window.removeWindowStateListener(this.handler);
        this.window.removeComponentListener(this.handler);
    }

    protected boolean hasVisibleEmbeddedMenuBar(JMenuBar jMenuBar) {
        return (jMenuBar != null && jMenuBar.isVisible() && this.isMenuBarEmbedded() ? (int)-2021941453L ^ 0x877B9F32 : (int)((long)1057705001 ^ (long)1057705001)) != 0;
    }

    protected boolean isMenuBarEmbedded() {
        return FlatUIUtils.getBoolean(this.rootPane, "flatlaf.menuBarEmbedded", "JRootPane.menuBarEmbedded", "TitlePane.menuBarEmbedded", (boolean)((long)1441626729 ^ (long)1441626729));
    }

    protected Rectangle getMenuBarBounds() {
        Insets insets = this.rootPane.getInsets();
        Rectangle rectangle = new Rectangle(SwingUtilities.convertPoint(this.menuBarPlaceholder, -insets.left, -insets.top, this.rootPane), this.menuBarPlaceholder.getSize());
        Insets insets2 = this.getBorder().getBorderInsets(this);
        rectangle.height += insets2.bottom;
        Component component = this.findHorizontalGlue(this.rootPane.getJMenuBar());
        if (component != null) {
            boolean bl = this.getComponentOrientation().isLeftToRight();
            int n2 = bl ? this.buttonPanel.getX() - (this.leftPanel.getX() + this.leftPanel.getWidth()) : this.leftPanel.getX() - (this.buttonPanel.getX() + this.buttonPanel.getWidth());
            n2 = Math.max(n2, (int)-2091109118L ^ 0x835C3502);
            rectangle.width += n2;
            if (!bl) {
                rectangle.x -= n2;
            }
        }
        return rectangle;
    }

    protected Component findHorizontalGlue(JMenuBar jMenuBar) {
        if (jMenuBar == null) {
            return null;
        }
        int n2 = jMenuBar.getComponentCount();
        for (int i2 = n2 - (int)((long)468045946 ^ (long)468045947); i2 >= 0; --i2) {
            Component component = jMenuBar.getComponent(i2);
            if (!(component instanceof Box.Filler) || component.getMaximumSize().width < ((int)-1522016888L ^ 0xA547A277)) continue;
            return component;
        }
        return null;
    }

    protected void titleBarColorsChanged() {
        this.activeChanged((this.window == null || this.window.isActive() ? (int)((long)1342958269 ^ (long)1342958268) : (int)-1363416033L ^ 0xAEBBEC1F) != 0);
        this.repaint();
    }

    protected void menuBarChanged() {
        this.menuBarPlaceholder.invalidate();
        this.repaint();
        EventQueue.invokeLater(() -> this.activeChanged((this.window == null || this.window.isActive() ? (int)((long)-1168393903 ^ (long)-1168393904) : (int)1544115662L ^ 0x5C0955CE) != 0));
    }

    protected void menuBarLayouted() {
        this.updateNativeTitleBarHeightAndHitTestSpotsLater();
        this.revalidate();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.setColor(UIManager.getBoolean("TitlePane.unifiedBackground") && FlatClientProperties.clientPropertyColor(this.rootPane, "JRootPane.titleBarBackground", null) == null ? FlatUIUtils.getParentBackground(this) : this.getBackground());
        graphics.fillRect((int)((long)-2122779068 ^ (long)-2122779068), (int)((long)-768089288 ^ (long)-768089288), this.getWidth(), this.getHeight());
    }

    protected void repaintWindowBorder() {
        int n2 = this.rootPane.getWidth();
        int n3 = this.rootPane.getHeight();
        Insets insets = this.rootPane.getInsets();
        this.rootPane.repaint((int)((long)1604786869 ^ (long)1604786869), (int)-809668502L ^ 0xCFBD706A, n2, insets.top);
        this.rootPane.repaint((int)-342604245L ^ 0xEB94462B, (int)644813502L ^ 0x266F12BE, insets.left, n3);
        this.rootPane.repaint((int)((long)2128634890 ^ (long)2128634890), n3 - insets.bottom, n2, insets.bottom);
        this.rootPane.repaint(n2 - insets.right, (int)((long)1803449840 ^ (long)1803449840), insets.right, n3);
    }

    protected void iconify() {
        if (!(this.window instanceof Frame)) {
            return;
        }
        Frame frame = (Frame)this.window;
        if (!FlatNativeWindowBorder.showWindow(this.window, (int)((long)934694857 ^ (long)934694858) << 1)) {
            frame.setExtendedState(frame.getExtendedState() | (int)((long)1545220431 ^ (long)1545220430));
        }
    }

    protected void maximize() {
        if (!(this.window instanceof Frame)) {
            return;
        }
        Frame frame = (Frame)this.window;
        this.updateMaximizedBounds();
        this.rootPane.putClientProperty("_flatlaf.maximizedBoundsUpToDate", ((int)-1926301997L ^ 0x8D2EF6D2) != 0);
        if (!FlatNativeWindowBorder.showWindow(frame, (int)-1449665118L ^ 0xA997DDA1)) {
            frame.setExtendedState(frame.getExtendedState() | ((int)887056981L ^ 0x34DF6A56) << 1);
        }
    }

    protected void updateMaximizedBounds() {
        Frame frame = (Frame)this.window;
        Rectangle rectangle = frame.getMaximizedBounds();
        if (!this.hasNativeCustomDecoration() && (rectangle == null || Objects.equals(rectangle, this.rootPane.getClientProperty("_flatlaf.maximizedBounds")))) {
            Cloneable cloneable;
            GraphicsConfiguration graphicsConfiguration = this.window.getGraphicsConfiguration();
            Rectangle rectangle2 = graphicsConfiguration.getBounds();
            int n2 = rectangle2.x;
            int n3 = rectangle2.y;
            int n4 = rectangle2.width;
            int n5 = rectangle2.height;
            if (!this.isMaximizedBoundsFixed()) {
                n2 = (int)((long)1561779460 ^ (long)1561779460);
                n3 = (int)((long)-2146444073 ^ (long)-2146444073);
                cloneable = graphicsConfiguration.getDefaultTransform();
                n4 = (int)((double)n4 * ((AffineTransform)cloneable).getScaleX());
                n5 = (int)((double)n5 * ((AffineTransform)cloneable).getScaleY());
            }
            cloneable = this.window.getToolkit().getScreenInsets(graphicsConfiguration);
            Rectangle rectangle3 = new Rectangle(n2 + ((Insets)cloneable).left, n3 + ((Insets)cloneable).top, n4 - ((Insets)cloneable).left - ((Insets)cloneable).right, n5 - ((Insets)cloneable).top - ((Insets)cloneable).bottom);
            if (!Objects.equals(rectangle, rectangle3)) {
                frame.setMaximizedBounds(rectangle3);
                this.rootPane.putClientProperty("_flatlaf.maximizedBounds", rectangle3);
            }
        }
    }

    private boolean isMaximizedBoundsFixed() {
        return (SystemInfo.isJava_15_orLater || SystemInfo.javaVersion >= SystemInfo.toVersion((int)((long)612766807 ^ (long)612766812), (int)-201770797L ^ 0xF3F938D3, ((int)962682846L ^ 0x39615FDF) << 3, (int)((long)-430114705 ^ (long)-430114705)) && SystemInfo.javaVersion < SystemInfo.toVersion(((int)842620404L ^ 0x32395DF7) << 2, (int)-1410328889L ^ 0xABF016C7, (int)((long)-837816520 ^ (long)-837816520), (int)((long)1837673625 ^ (long)1837673625)) || SystemInfo.javaVersion >= SystemInfo.toVersion((int)((long)-1619541999 ^ (long)-1619541988), (int)696699890L ^ 0x2986CBF2, (int)((long)-163332800 ^ (long)-163332799) << 2, (int)-50966863L ^ 0xFCF64EB1) && SystemInfo.javaVersion < SystemInfo.toVersion((int)((long)-1549356972 ^ (long)-1549356973) << 1, (int)1215896052L ^ 0x487919F4, (int)-693135762L ^ 0xD6AF966E, (int)1751502828L ^ 0x6865CFEC) ? (int)-970663362L ^ 0xC624DA3F : (int)950434971L ^ 0x38A67C9B) != 0;
    }

    protected void restore() {
        if (!(this.window instanceof Frame)) {
            return;
        }
        Frame frame = (Frame)this.window;
        if (!FlatNativeWindowBorder.showWindow(this.window, (int)-73892390L ^ 0xFB987DD3)) {
            int n2 = frame.getExtendedState();
            frame.setExtendedState((n2 & ((int)301768781L ^ 0x11FCA04C)) != 0 ? n2 & ((int)-1047802800L ^ 0x3E7433AE) : n2 & (int)((long)-1069988568 ^ (long)1069988561));
        }
    }

    public void close() {
        if (this.window != null) {
            this.window.dispatchEvent(new WindowEvent(this.window, (int)((long)1993108863 ^ (long)1993108918)));
        }
    }

    private boolean hasJBRCustomDecoration() {
        return (this.window != null && JBRCustomDecorations.hasCustomDecoration(this.window) ? (int)252358789L ^ 0xF0AB084 : (int)((long)-623115738 ^ (long)-623115738)) != 0;
    }

    protected boolean hasNativeCustomDecoration() {
        return (this.window != null && FlatNativeWindowBorder.hasCustomDecoration(this.window) ? (int)1162247407L ^ 0x45467CEE : (int)((long)1137214985 ^ (long)1137214985)) != 0;
    }

    protected void updateNativeTitleBarHeightAndHitTestSpotsLater() {
        EventQueue.invokeLater(() -> this.updateNativeTitleBarHeightAndHitTestSpots());
    }

    protected void updateNativeTitleBarHeightAndHitTestSpots() {
        Serializable serializable;
        Serializable serializable2;
        Cloneable cloneable;
        if (!this.isDisplayable()) {
            return;
        }
        if (!this.hasNativeCustomDecoration()) {
            return;
        }
        int n2 = this.getHeight();
        if (n2 > 0) {
            --n2;
        }
        ArrayList<Rectangle> arrayList = new ArrayList<Rectangle>();
        Serializable serializable3 = null;
        if (this.iconLabel.isVisible()) {
            cloneable = SwingUtilities.convertPoint(this.iconLabel, (int)((long)1990531483 ^ (long)1990531483), (int)((long)-2002425921 ^ (long)-2002425921), this.window);
            serializable2 = this.iconLabel.getInsets();
            serializable = new Rectangle(((Point)cloneable).x + ((Insets)serializable2).left, ((Point)cloneable).y + ((Insets)serializable2).top, this.iconLabel.getWidth() - ((Insets)serializable2).left - ((Insets)serializable2).right, this.iconLabel.getHeight() - ((Insets)serializable2).top - ((Insets)serializable2).bottom);
            if (this.hasJBRCustomDecoration()) {
                arrayList.add((Rectangle)serializable);
            } else {
                serializable3 = serializable;
            }
        }
        if ((cloneable = this.getNativeHitTestSpot(this.buttonPanel)) != null) {
            arrayList.add((Rectangle)cloneable);
        }
        if (this.hasVisibleEmbeddedMenuBar((JMenuBar)(serializable2 = this.rootPane.getJMenuBar())) && (cloneable = this.getNativeHitTestSpot(this.menuBarPlaceholder)) != null) {
            serializable = this.findHorizontalGlue((JMenuBar)serializable2);
            if (serializable != null) {
                Rectangle rectangle;
                Point point = SwingUtilities.convertPoint((Component)serializable, (int)((long)1927523263 ^ (long)1927523263), (int)28274448L ^ 0x1AF6F10, this.window);
                if (this.getComponentOrientation().isLeftToRight()) {
                    int n3 = cloneable.x + cloneable.width - (((int)-75814488L ^ 0xFB7B29A9) << 1) - point.x;
                    cloneable.width -= n3;
                    rectangle = new Rectangle(point.x + ((Component)serializable).getWidth(), cloneable.y, n3, cloneable.height);
                } else {
                    int n4 = point.x + ((Component)serializable).getWidth() - (cloneable.x + ((int)((long)2039800762 ^ (long)2039800763) << 1));
                    cloneable.x += n4;
                    cloneable.width -= n4;
                    rectangle = new Rectangle(point.x - n4, cloneable.y, n4, cloneable.height);
                }
                rectangle.grow((int)((long)-421560496 ^ (long)-421560495) << 1, ((int)-1729269654L ^ 0x98ED706B) << 1);
                arrayList.add(rectangle);
            }
            arrayList.add((Rectangle)cloneable);
        }
        FlatNativeWindowBorder.setTitleBarHeightAndHitTestSpots(this.window, n2, arrayList, serializable3);
    }

    protected Rectangle getNativeHitTestSpot(JComponent jComponent) {
        Dimension dimension = jComponent.getSize();
        if (dimension.width <= 0 || dimension.height <= 0) {
            return null;
        }
        Point point = SwingUtilities.convertPoint(jComponent, (int)((long)-558907971 ^ (long)-558907971), (int)1612949991L ^ 0x6023A9E7, this.window);
        Rectangle rectangle = new Rectangle(point, dimension);
        rectangle.grow((int)((long)-1052278031 ^ (long)-1052278032) << 1, (int)((long)478249673 ^ (long)478249672) << 1);
        return rectangle;
    }

    protected class Handler
    extends WindowAdapter
    implements PropertyChangeListener,
    MouseListener,
    MouseMotionListener,
    ComponentListener {
        private Point dragOffset;

        protected Handler() {
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
        public void windowActivated(WindowEvent windowEvent) {
            FlatTitlePane.this.activeChanged((boolean)((long)1514511650 ^ (long)1514511651));
            FlatTitlePane.this.updateNativeTitleBarHeightAndHitTestSpots();
            if (FlatTitlePane.this.hasNativeCustomDecoration()) {
                FlatNativeWindowBorder.WindowTopBorder.getInstance().repaintBorder(FlatTitlePane.this);
            }
            FlatTitlePane.this.repaintWindowBorder();
        }

        @Override
        public void windowDeactivated(WindowEvent windowEvent) {
            FlatTitlePane.this.activeChanged((boolean)((long)1329664327 ^ (long)1329664327));
            FlatTitlePane.this.updateNativeTitleBarHeightAndHitTestSpots();
            if (FlatTitlePane.this.hasNativeCustomDecoration()) {
                FlatNativeWindowBorder.WindowTopBorder.getInstance().repaintBorder(FlatTitlePane.this);
            }
            FlatTitlePane.this.repaintWindowBorder();
        }

        @Override
        public void windowStateChanged(WindowEvent windowEvent) {
            FlatTitlePane.this.frameStateChanged();
            FlatTitlePane.this.updateNativeTitleBarHeightAndHitTestSpots();
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (mouseEvent.getClickCount() == ((int)-1652915708L ^ 0x9D7A8205) << 1 && SwingUtilities.isLeftMouseButton(mouseEvent)) {
                if (mouseEvent.getSource() == FlatTitlePane.this.iconLabel) {
                    FlatTitlePane.this.close();
                } else if (!FlatTitlePane.this.hasNativeCustomDecoration() && FlatTitlePane.this.window instanceof Frame && ((Frame)FlatTitlePane.this.window).isResizable()) {
                    Frame frame = (Frame)FlatTitlePane.this.window;
                    if ((frame.getExtendedState() & (int)((long)-932971467 ^ (long)-932971466) << 1) != 0) {
                        FlatTitlePane.this.restore();
                    } else {
                        FlatTitlePane.this.maximize();
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            if (FlatTitlePane.this.window == null) {
                return;
            }
            this.dragOffset = SwingUtilities.convertPoint(FlatTitlePane.this, mouseEvent.getPoint(), FlatTitlePane.this.window);
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            Frame frame;
            int n2;
            if (FlatTitlePane.this.window == null) {
                return;
            }
            if (FlatTitlePane.this.hasNativeCustomDecoration()) {
                return;
            }
            if (FlatTitlePane.this.window instanceof Frame && ((n2 = (frame = (Frame)FlatTitlePane.this.window).getExtendedState()) & ((int)-175556539L ^ 0xF5893846) << 1) != 0) {
                int n3 = FlatTitlePane.this.window.getWidth();
                frame.setExtendedState(n2 & ((int)2038979607L ^ 0x8677A3EE));
                int n4 = FlatTitlePane.this.window.getWidth();
                int n5 = n4 / (((int)-234013126L ^ 0xF20D3E3B) << 1);
                if (this.dragOffset.x > n5) {
                    this.dragOffset.x = this.dragOffset.x > n3 - n5 ? n4 - (n3 - this.dragOffset.x) : n5;
                }
            }
            int n6 = mouseEvent.getXOnScreen() - this.dragOffset.x;
            n2 = mouseEvent.getYOnScreen() - this.dragOffset.y;
            if (n6 == FlatTitlePane.this.window.getX() && n2 == FlatTitlePane.this.window.getY()) {
                return;
            }
            FlatTitlePane.this.window.setLocation(n6, n2);
        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
        }

        @Override
        public void componentResized(ComponentEvent componentEvent) {
            FlatTitlePane.this.updateNativeTitleBarHeightAndHitTestSpotsLater();
        }

        @Override
        public void componentShown(ComponentEvent componentEvent) {
            FlatTitlePane.this.frameStateChanged();
        }

        @Override
        public void componentMoved(ComponentEvent componentEvent) {
        }

        @Override
        public void componentHidden(ComponentEvent componentEvent) {
        }
    }

    protected class FlatTitleLabelUI
    extends FlatLabelUI {
        protected FlatTitleLabelUI() {
        }

        @Override
        protected void paintEnabledText(JLabel jLabel, Graphics graphics, String string, int n2, int n3) {
            boolean bl;
            boolean bl2 = FlatTitlePane.this.hasVisibleEmbeddedMenuBar(FlatTitlePane.this.rootPane.getJMenuBar());
            int n4 = jLabel.getWidth();
            int n5 = n4 - n2 * (((int)1763849441L ^ 0x692234E0) << 1);
            int n6 = UIScale.scale(FlatTitlePane.this.menuBarTitleGap);
            boolean bl3 = bl = bl2 ? FlatTitlePane.this.centerTitleIfMenuBarEmbedded : FlatTitlePane.this.centerTitle;
            if (bl) {
                int n7 = (jLabel.getParent().getWidth() - n5) / ((int)((long)-157582456 ^ (long)-157582455) << 1) - jLabel.getX();
                if (n7 >= n6 && n7 + n5 <= n4 - n6) {
                    n2 = n7;
                }
            } else {
                int n8;
                boolean bl4 = FlatTitlePane.this.getComponentOrientation().isLeftToRight();
                Insets insets = jLabel.getInsets();
                int n9 = bl2 ? n6 : (bl4 ? insets.left : insets.right);
                int n10 = n8 = bl4 ? n9 : n4 - n9 - n5;
                if (bl4 ? n8 < n2 : n8 > n2) {
                    n2 = n8;
                }
            }
            super.paintEnabledText(jLabel, graphics, string, n2, n3);
        }
    }

    protected class FlatTitlePaneBorder
    extends AbstractBorder {
        protected FlatTitlePaneBorder() {
        }

        @Override
        public Insets getBorderInsets(Component component, Insets insets) {
            super.getBorderInsets(component, insets);
            Border border = this.getMenuBarBorder();
            if (border != null) {
                Insets insets2 = border.getBorderInsets(component);
                insets.bottom += insets2.bottom;
            } else if (!(FlatTitlePane.this.borderColor == null || FlatTitlePane.this.rootPane.getJMenuBar() != null && FlatTitlePane.this.rootPane.getJMenuBar().isVisible())) {
                insets.bottom += UIScale.scale((int)((long)-1099899626 ^ (long)-1099899625));
            }
            if (FlatTitlePane.this.hasNativeCustomDecoration() && !this.isWindowMaximized(component)) {
                insets = FlatUIUtils.addInsets(insets, FlatNativeWindowBorder.WindowTopBorder.getInstance().getBorderInsets());
            }
            return insets;
        }

        @Override
        public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
            Border border = this.getMenuBarBorder();
            if (border != null) {
                border.paintBorder(component, graphics, n2, n3, n4, n5);
            } else if (!(FlatTitlePane.this.borderColor == null || FlatTitlePane.this.rootPane.getJMenuBar() != null && FlatTitlePane.this.rootPane.getJMenuBar().isVisible())) {
                float f2 = UIScale.scale(1.0f);
                FlatUIUtils.paintFilledRectangle(graphics, FlatTitlePane.this.borderColor, n2, (float)(n3 + n5) - f2, n4, f2);
            }
            if (FlatTitlePane.this.hasNativeCustomDecoration() && !this.isWindowMaximized(component)) {
                FlatNativeWindowBorder.WindowTopBorder.getInstance().paintBorder(component, graphics, n2, n3, n4, n5);
            }
        }

        protected Border getMenuBarBorder() {
            JMenuBar jMenuBar = FlatTitlePane.this.rootPane.getJMenuBar();
            return FlatTitlePane.this.hasVisibleEmbeddedMenuBar(jMenuBar) ? jMenuBar.getBorder() : null;
        }

        protected boolean isWindowMaximized(Component component) {
            return (FlatTitlePane.this.window instanceof Frame ? ((((Frame)FlatTitlePane.this.window).getExtendedState() & ((int)-1501669989L ^ 0xA67E5598) << 1) != 0 ? (int)-1325370259L ^ 0xB100746C : (int)((long)2054811620 ^ (long)2054811620)) : (int)-1303260891L ^ 0xB251D125) != 0;
        }
    }
}

