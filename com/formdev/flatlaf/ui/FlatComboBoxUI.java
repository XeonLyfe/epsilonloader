/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatArrowButton;
import com.formdev.flatlaf.ui.FlatTextBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.lang.ref.WeakReference;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.JTextComponent;

public class FlatComboBoxUI
extends BasicComboBoxUI {
    protected int minimumWidth;
    protected int editorColumns;
    protected String buttonStyle;
    protected String arrowType;
    protected boolean isIntelliJTheme;
    protected Color borderColor;
    protected Color disabledBorderColor;
    protected Color editableBackground;
    protected Color disabledBackground;
    protected Color disabledForeground;
    protected Color buttonBackground;
    protected Color buttonEditableBackground;
    protected Color buttonArrowColor;
    protected Color buttonDisabledArrowColor;
    protected Color buttonHoverArrowColor;
    protected Color buttonPressedArrowColor;
    private MouseListener hoverListener;
    protected boolean hover;
    protected boolean pressed;
    private WeakReference<Component> lastRendererComponent;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatComboBoxUI();
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        this.hoverListener = new MouseAdapter(){

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                FlatComboBoxUI.this.hover = (int)-1527799558L ^ 0xA4EFA0FB;
                this.repaintArrowButton();
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                FlatComboBoxUI.this.hover = (int)((long)264009416 ^ (long)264009416);
                this.repaintArrowButton();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                FlatComboBoxUI.this.pressed = (int)((long)-221850338 ^ (long)-221850337);
                this.repaintArrowButton();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                FlatComboBoxUI.this.pressed = (int)((long)-200799423 ^ (long)-200799423);
                this.repaintArrowButton();
            }

            private void repaintArrowButton() {
                if (FlatComboBoxUI.this.arrowButton != null && !FlatComboBoxUI.this.comboBox.isEditable()) {
                    FlatComboBoxUI.this.arrowButton.repaint();
                }
            }
        };
        this.comboBox.addMouseListener(this.hoverListener);
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        this.comboBox.removeMouseListener(this.hoverListener);
        this.hoverListener = null;
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installProperty(this.comboBox, "opaque", (boolean)((long)1591141373 ^ (long)1591141373));
        this.minimumWidth = UIManager.getInt("ComboBox.minimumWidth");
        this.editorColumns = UIManager.getInt("ComboBox.editorColumns");
        this.buttonStyle = UIManager.getString("ComboBox.buttonStyle");
        this.arrowType = UIManager.getString("Component.arrowType");
        this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
        this.borderColor = UIManager.getColor("Component.borderColor");
        this.disabledBorderColor = UIManager.getColor("Component.disabledBorderColor");
        this.editableBackground = UIManager.getColor("ComboBox.editableBackground");
        this.disabledBackground = UIManager.getColor("ComboBox.disabledBackground");
        this.disabledForeground = UIManager.getColor("ComboBox.disabledForeground");
        this.buttonBackground = UIManager.getColor("ComboBox.buttonBackground");
        this.buttonEditableBackground = UIManager.getColor("ComboBox.buttonEditableBackground");
        this.buttonArrowColor = UIManager.getColor("ComboBox.buttonArrowColor");
        this.buttonDisabledArrowColor = UIManager.getColor("ComboBox.buttonDisabledArrowColor");
        this.buttonHoverArrowColor = UIManager.getColor("ComboBox.buttonHoverArrowColor");
        this.buttonPressedArrowColor = UIManager.getColor("ComboBox.buttonPressedArrowColor");
        int n2 = UIManager.getInt("ComboBox.maximumRowCount");
        if (n2 > 0 && n2 != (int)((long)1787716275 ^ (long)1787716274) << 3 && this.comboBox.getMaximumRowCount() == ((int)614133766L ^ 0x249AF007) << 3) {
            this.comboBox.setMaximumRowCount(n2);
        }
        this.padding = UIScale.scale(this.padding);
        MigLayoutVisualPadding.install(this.comboBox);
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.borderColor = null;
        this.disabledBorderColor = null;
        this.editableBackground = null;
        this.disabledBackground = null;
        this.disabledForeground = null;
        this.buttonBackground = null;
        this.buttonEditableBackground = null;
        this.buttonArrowColor = null;
        this.buttonDisabledArrowColor = null;
        this.buttonHoverArrowColor = null;
        this.buttonPressedArrowColor = null;
        MigLayoutVisualPadding.uninstall(this.comboBox);
    }

    @Override
    protected LayoutManager createLayoutManager() {
        return new BasicComboBoxUI.ComboBoxLayoutManager(){

            @Override
            public void layoutContainer(Container container) {
                super.layoutContainer(container);
                if (FlatComboBoxUI.this.arrowButton != null) {
                    Insets insets = FlatComboBoxUI.this.getInsets();
                    int n2 = container.getPreferredSize().height - insets.top - insets.bottom;
                    if (n2 != FlatComboBoxUI.this.arrowButton.getWidth()) {
                        int n3 = FlatComboBoxUI.this.comboBox.getComponentOrientation().isLeftToRight() ? FlatComboBoxUI.this.arrowButton.getWidth() - n2 : (int)((long)-609477730 ^ (long)-609477730);
                        FlatComboBoxUI.this.arrowButton.setBounds(FlatComboBoxUI.this.arrowButton.getX() + n3, FlatComboBoxUI.this.arrowButton.getY(), n2, FlatComboBoxUI.this.arrowButton.getHeight());
                        if (FlatComboBoxUI.this.editor != null) {
                            FlatComboBoxUI.this.editor.setBounds(FlatComboBoxUI.this.rectangleForCurrentValue());
                        }
                    }
                }
                if (FlatComboBoxUI.this.editor != null && FlatComboBoxUI.this.padding != null) {
                    FlatComboBoxUI.this.editor.setBounds(FlatUIUtils.subtractInsets(FlatComboBoxUI.this.editor.getBounds(), FlatComboBoxUI.this.padding));
                }
            }
        };
    }

    @Override
    protected FocusListener createFocusListener() {
        return new BasicComboBoxUI.FocusHandler(){

            @Override
            public void focusGained(FocusEvent focusEvent) {
                super.focusGained(focusEvent);
                if (FlatComboBoxUI.this.comboBox != null && FlatComboBoxUI.this.comboBox.isEditable()) {
                    FlatComboBoxUI.this.comboBox.repaint();
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                super.focusLost(focusEvent);
                if (FlatComboBoxUI.this.comboBox != null && FlatComboBoxUI.this.comboBox.isEditable()) {
                    FlatComboBoxUI.this.comboBox.repaint();
                }
            }
        };
    }

    @Override
    protected PropertyChangeListener createPropertyChangeListener() {
        PropertyChangeListener propertyChangeListener = super.createPropertyChangeListener();
        return propertyChangeEvent -> {
            propertyChangeListener.propertyChange(propertyChangeEvent);
            Object object = propertyChangeEvent.getSource();
            String string = propertyChangeEvent.getPropertyName();
            if (this.editor != null && (object == this.comboBox && string == "foreground" || object == this.editor && string == "enabled")) {
                this.updateEditorColors();
            } else if (this.editor != null && object == this.comboBox && string == "componentOrientation") {
                ComponentOrientation componentOrientation = (ComponentOrientation)propertyChangeEvent.getNewValue();
                this.editor.applyComponentOrientation(componentOrientation);
            } else if (this.editor != null && "JTextField.placeholderText".equals(string)) {
                this.editor.repaint();
            } else if ("JComponent.roundRect".equals(string)) {
                this.comboBox.repaint();
            } else if ("JComponent.minimumWidth".equals(string)) {
                this.comboBox.revalidate();
            }
        };
    }

    @Override
    protected ComboPopup createPopup() {
        return new FlatComboPopup(this.comboBox);
    }

    @Override
    protected ComboBoxEditor createEditor() {
        ComboBoxEditor comboBoxEditor = super.createEditor();
        Component component = comboBoxEditor.getEditorComponent();
        if (component instanceof JTextField) {
            JTextField jTextField = (JTextField)component;
            jTextField.setColumns(this.editorColumns);
            jTextField.setBorder(BorderFactory.createEmptyBorder());
        }
        return comboBoxEditor;
    }

    @Override
    protected void configureEditor() {
        super.configureEditor();
        if (this.editor instanceof JTextField && ((JTextField)this.editor).getBorder() instanceof FlatTextBorder) {
            ((JTextField)this.editor).setBorder(BorderFactory.createEmptyBorder());
        }
        if (this.editor instanceof JComponent) {
            ((JComponent)this.editor).setOpaque(((int)-1811990526L ^ 0x93FF3802) != 0);
        }
        this.editor.applyComponentOrientation(this.comboBox.getComponentOrientation());
        this.updateEditorColors();
        if (SystemInfo.isMacOS && this.editor instanceof JTextComponent) {
            InputMap inputMap = ((JTextComponent)this.editor).getInputMap();
            new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("UP"));
            new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("KP_UP"));
            new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("DOWN"));
            new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("KP_DOWN"));
            new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("HOME"));
            new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("END"));
        }
    }

    private void updateEditorColors() {
        boolean bl = this.editor instanceof JTextComponent;
        this.editor.setForeground(FlatUIUtils.nonUIResource(this.getForeground((bl || this.editor.isEnabled() ? (int)((long)-925426042 ^ (long)-925426041) : (int)-429485531L ^ 0xE6669225) != 0)));
        if (bl) {
            ((JTextComponent)this.editor).setDisabledTextColor(FlatUIUtils.nonUIResource(this.getForeground(((int)451885378L ^ 0x1AEF3942) != 0)));
        }
    }

    @Override
    protected JButton createArrowButton() {
        return new FlatComboBoxButton();
    }

    @Override
    public void update(Graphics graphics, JComponent jComponent) {
        float f2 = FlatUIUtils.getBorderFocusWidth(jComponent);
        float f3 = FlatUIUtils.getBorderArc(jComponent);
        if (jComponent.isOpaque() && (f2 > 0.0f || f3 > 0.0f)) {
            FlatUIUtils.paintParentBackground(graphics, jComponent);
        }
        Graphics2D graphics2D = (Graphics2D)graphics;
        Object[] arrobject = FlatUIUtils.setRenderingHints(graphics2D);
        int n2 = jComponent.getWidth();
        int n3 = jComponent.getHeight();
        int n4 = this.arrowButton.getX();
        int n5 = this.arrowButton.getWidth();
        int n6 = (this.comboBox.isEditable() || "button".equals(this.buttonStyle)) && !"none".equals(this.buttonStyle) ? (int)1841753347L ^ 0x6DC6ED02 : (int)((long)153239679 ^ (long)153239679);
        boolean bl = this.comboBox.isEnabled();
        boolean bl2 = this.comboBox.getComponentOrientation().isLeftToRight();
        graphics2D.setColor(this.getBackground(bl));
        FlatUIUtils.paintComponentBackground(graphics2D, (int)-1249675326L ^ 0xB58377C2, (int)((long)290831314 ^ (long)290831314), n2, n3, f2, f3);
        if (bl) {
            graphics2D.setColor(n6 != 0 ? this.buttonEditableBackground : this.buttonBackground);
            Shape shape = graphics2D.getClip();
            if (bl2) {
                graphics2D.clipRect(n4, (int)-979275093L ^ 0xC5A172AB, n2 - n4, n3);
            } else {
                graphics2D.clipRect((int)1289083480L ^ 0x4CD5DA58, (int)((long)1821680209 ^ (long)1821680209), n4 + n5, n3);
            }
            FlatUIUtils.paintComponentBackground(graphics2D, (int)((long)-1861932546 ^ (long)-1861932546), (int)((long)1159135206 ^ (long)1159135206), n2, n3, f2, f3);
            graphics2D.setClip(shape);
        }
        if (n6 != 0) {
            graphics2D.setColor(bl ? this.borderColor : this.disabledBorderColor);
            float f4 = UIScale.scale(1.0f);
            float f5 = bl2 ? (float)n4 : (float)(n4 + n5) - f4;
            graphics2D.fill(new Rectangle2D.Float(f5, f2, f4, (float)(n3 - ((int)1296854737L ^ 0x4D4C6ED0)) - f2 * 2.0f));
        }
        FlatUIUtils.resetRenderingHints(graphics2D, arrobject);
        this.paint(graphics, jComponent);
    }

    @Override
    public void paintCurrentValue(Graphics graphics, Rectangle rectangle, boolean bl) {
        Insets insets;
        DefaultListCellRenderer defaultListCellRenderer = this.comboBox.getRenderer();
        this.uninstallCellPaddingBorder(defaultListCellRenderer);
        if (defaultListCellRenderer == null) {
            defaultListCellRenderer = new DefaultListCellRenderer();
        }
        Component component = defaultListCellRenderer.getListCellRendererComponent(this.listBox, this.comboBox.getSelectedItem(), (int)2048947255L ^ 0x85DF8BC8, (boolean)((long)-1751874990 ^ (long)-1751874990), ((int)-1186336493L ^ 0xB949F113) != 0);
        component.setFont(this.comboBox.getFont());
        component.applyComponentOrientation(this.comboBox.getComponentOrientation());
        this.uninstallCellPaddingBorder(component);
        boolean bl2 = this.comboBox.isEnabled();
        component.setBackground(this.getBackground(bl2));
        component.setForeground(this.getForeground(bl2));
        boolean bl3 = component instanceof JPanel;
        if (this.padding != null) {
            rectangle = FlatUIUtils.subtractInsets(rectangle, this.padding);
        }
        if ((insets = this.getRendererComponentInsets(component)) != null) {
            rectangle = FlatUIUtils.addInsets(rectangle, insets);
        }
        this.currentValuePane.paintComponent(graphics, component, this.comboBox, rectangle.x, rectangle.y, rectangle.width, rectangle.height, bl3);
    }

    @Override
    public void paintCurrentValueBackground(Graphics graphics, Rectangle rectangle, boolean bl) {
    }

    protected Color getBackground(boolean bl) {
        return bl ? (this.editableBackground != null && this.comboBox.isEditable() ? this.editableBackground : this.comboBox.getBackground()) : (this.isIntelliJTheme ? FlatUIUtils.getParentBackground(this.comboBox) : this.disabledBackground);
    }

    protected Color getForeground(boolean bl) {
        return bl ? this.comboBox.getForeground() : this.disabledForeground;
    }

    @Override
    public Dimension getMinimumSize(JComponent jComponent) {
        Dimension dimension = super.getMinimumSize(jComponent);
        dimension.width = Math.max(dimension.width, UIScale.scale(FlatUIUtils.minimumWidth(jComponent, this.minimumWidth)));
        return dimension;
    }

    @Override
    protected Dimension getDefaultSize() {
        ListCellRenderer listCellRenderer = this.comboBox.getRenderer();
        this.uninstallCellPaddingBorder(listCellRenderer);
        Dimension dimension = super.getDefaultSize();
        this.uninstallCellPaddingBorder(listCellRenderer);
        return dimension;
    }

    @Override
    protected Dimension getDisplaySize() {
        ListCellRenderer listCellRenderer = this.comboBox.getRenderer();
        this.uninstallCellPaddingBorder(listCellRenderer);
        Dimension dimension = super.getDisplaySize();
        if (dimension.width == (((int)75322866L ^ 0x47D55EB) << 2) + this.padding.left + this.padding.right && this.comboBox.isEditable() && this.comboBox.getItemCount() == 0 && this.comboBox.getPrototypeDisplayValue() == null) {
            int n2 = this.getDefaultSize().width;
            n2 = Math.max(n2, this.editor.getPreferredSize().width);
            dimension = new Dimension(n2 += this.padding.left + this.padding.right, dimension.height);
        }
        this.uninstallCellPaddingBorder(listCellRenderer);
        return dimension;
    }

    @Override
    protected Dimension getSizeForComponent(Component component) {
        Dimension dimension = super.getSizeForComponent(component);
        Insets insets = this.getRendererComponentInsets(component);
        if (insets != null) {
            dimension = new Dimension(dimension.width, dimension.height - insets.top - insets.bottom);
        }
        return dimension;
    }

    private Insets getRendererComponentInsets(Component component) {
        Border border;
        if (component instanceof JComponent && (border = ((JComponent)component).getBorder()) != null) {
            return border.getBorderInsets(component);
        }
        return null;
    }

    private void uninstallCellPaddingBorder(Object object) {
        CellPaddingBorder.uninstall(object);
        if (this.lastRendererComponent != null) {
            CellPaddingBorder.uninstall(this.lastRendererComponent);
            this.lastRendererComponent = null;
        }
    }

    private class EditorDelegateAction
    extends AbstractAction {
        private final KeyStroke keyStroke;

        EditorDelegateAction(InputMap inputMap, KeyStroke keyStroke) {
            this.keyStroke = keyStroke;
            inputMap.put(keyStroke, this);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ActionListener actionListener = FlatComboBoxUI.this.comboBox.getActionForKeyStroke(this.keyStroke);
            if (actionListener != null) {
                actionListener.actionPerformed(new ActionEvent(FlatComboBoxUI.this.comboBox, actionEvent.getID(), actionEvent.getActionCommand(), actionEvent.getWhen(), actionEvent.getModifiers()));
            }
        }
    }

    private static class CellPaddingBorder
    extends AbstractBorder {
        private final Insets padding;
        private Border rendererBorder;

        CellPaddingBorder(Insets insets) {
            this.padding = insets;
        }

        void install(JComponent jComponent) {
            Border border = jComponent.getBorder();
            if (!(border instanceof CellPaddingBorder)) {
                this.rendererBorder = border;
                jComponent.setBorder(this);
            }
        }

        static void uninstall(Object object) {
            if (object instanceof WeakReference) {
                object = ((WeakReference)object).get();
            }
            if (!(object instanceof JComponent)) {
                return;
            }
            JComponent jComponent = (JComponent)object;
            Border border = jComponent.getBorder();
            if (border instanceof CellPaddingBorder) {
                CellPaddingBorder cellPaddingBorder = (CellPaddingBorder)border;
                jComponent.setBorder(cellPaddingBorder.rendererBorder);
                cellPaddingBorder.rendererBorder = null;
            }
        }

        @Override
        public Insets getBorderInsets(Component component, Insets insets) {
            if (this.rendererBorder != null) {
                Insets insets2 = this.rendererBorder.getBorderInsets(component);
                insets.top = Math.max(this.padding.top, insets2.top);
                insets.left = Math.max(this.padding.left, insets2.left);
                insets.bottom = Math.max(this.padding.bottom, insets2.bottom);
                insets.right = Math.max(this.padding.right, insets2.right);
            } else {
                insets.top = this.padding.top;
                insets.left = this.padding.left;
                insets.bottom = this.padding.bottom;
                insets.right = this.padding.right;
            }
            return insets;
        }

        @Override
        public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
            if (this.rendererBorder != null) {
                this.rendererBorder.paintBorder(component, graphics, n2, n3, n4, n5);
            }
        }
    }

    protected class FlatComboPopup
    extends BasicComboPopup {
        private CellPaddingBorder paddingBorder;

        protected FlatComboPopup(JComboBox jComboBox) {
            super(jComboBox);
            ComponentOrientation componentOrientation = this.comboBox.getComponentOrientation();
            this.list.setComponentOrientation(componentOrientation);
            this.scroller.setComponentOrientation(componentOrientation);
            this.setComponentOrientation(componentOrientation);
        }

        @Override
        protected Rectangle computePopupBounds(int n2, int n3, int n4, int n5) {
            Object object;
            int n6 = FlatComboBoxUI.this.getDisplaySize().width;
            Border[] arrborder = new Border[((int)1983544149L ^ 0x763A7B54) << 1];
            arrborder[(int)-2045369176L ^ -2045369176] = this.scroller.getViewportBorder();
            arrborder[(int)((long)-488530562 ^ (long)-488530561)] = this.scroller.getBorder();
            Object object2 = arrborder;
            int n7 = ((Border[])object2).length;
            for (int i2 = (int)-1208726361L ^ 0xB7F44CA7; i2 < n7; ++i2) {
                object = object2[i2];
                if (object == null) continue;
                Insets insets = object.getBorderInsets(null);
                n6 += insets.left + insets.right;
            }
            object2 = this.scroller.getVerticalScrollBar();
            if (object2 != null) {
                n6 += object2.getPreferredSize().width;
            }
            if (n6 > n4) {
                Cloneable cloneable;
                GraphicsConfiguration graphicsConfiguration = this.comboBox.getGraphicsConfiguration();
                if (graphicsConfiguration != null) {
                    cloneable = graphicsConfiguration.getBounds();
                    object = Toolkit.getDefaultToolkit().getScreenInsets(graphicsConfiguration);
                    n6 = Math.min(n6, cloneable.width - ((Insets)object).left - ((Insets)object).right);
                } else {
                    cloneable = Toolkit.getDefaultToolkit().getScreenSize();
                    n6 = Math.min(n6, ((Dimension)cloneable).width);
                }
                int n8 = n6 - n4;
                n4 = n6;
                if (!this.comboBox.getComponentOrientation().isLeftToRight()) {
                    n2 -= n8;
                }
            }
            return super.computePopupBounds(n2, n3, n4, n5);
        }

        @Override
        protected void configurePopup() {
            super.configurePopup();
            Border border = UIManager.getBorder("PopupMenu.border");
            if (border != null) {
                this.setBorder(border);
            }
        }

        @Override
        protected void configureList() {
            super.configureList();
            this.list.setCellRenderer(new PopupListCellRenderer());
        }

        @Override
        protected PropertyChangeListener createPropertyChangeListener() {
            PropertyChangeListener propertyChangeListener = super.createPropertyChangeListener();
            return propertyChangeEvent -> {
                propertyChangeListener.propertyChange(propertyChangeEvent);
                if (propertyChangeEvent.getPropertyName() == "renderer") {
                    this.list.setCellRenderer(new PopupListCellRenderer());
                }
            };
        }

        private class PopupListCellRenderer
        implements ListCellRenderer {
            private PopupListCellRenderer() {
            }

            public Component getListCellRendererComponent(JList jList, Object object, int n2, boolean bl, boolean bl2) {
                DefaultListCellRenderer defaultListCellRenderer = FlatComboPopup.this.comboBox.getRenderer();
                CellPaddingBorder.uninstall(defaultListCellRenderer);
                CellPaddingBorder.uninstall(FlatComboBoxUI.this.lastRendererComponent);
                if (defaultListCellRenderer == null) {
                    defaultListCellRenderer = new DefaultListCellRenderer();
                }
                Component component = defaultListCellRenderer.getListCellRendererComponent(jList, object, n2, bl, bl2);
                component.applyComponentOrientation(FlatComboPopup.this.comboBox.getComponentOrientation());
                if (component instanceof JComponent) {
                    if (FlatComboPopup.this.paddingBorder == null) {
                        FlatComboPopup.this.paddingBorder = new CellPaddingBorder(FlatComboBoxUI.this.padding);
                    }
                    FlatComboPopup.this.paddingBorder.install((JComponent)component);
                }
                FlatComboBoxUI.this.lastRendererComponent = component != defaultListCellRenderer ? new WeakReference<Component>(component) : null;
                return component;
            }
        }
    }

    protected class FlatComboBoxButton
    extends FlatArrowButton {
        protected FlatComboBoxButton() {
            this((int)((long)1875150498 ^ (long)1875150503), flatComboBoxUI.arrowType, flatComboBoxUI.buttonArrowColor, flatComboBoxUI.buttonDisabledArrowColor, flatComboBoxUI.buttonHoverArrowColor, null, flatComboBoxUI.buttonPressedArrowColor, null);
        }

        protected FlatComboBoxButton(int n2, String string, Color color, Color color2, Color color3, Color color4, Color color5, Color color6) {
            super(n2, string, color, color2, color3, color4, color5, color6);
        }

        @Override
        protected boolean isHover() {
            return (super.isHover() || !FlatComboBoxUI.this.comboBox.isEditable() && FlatComboBoxUI.this.hover ? (int)-820076505L ^ 0xCF1EA026 : (int)-2058840328L ^ 0x854896F8) != 0;
        }

        @Override
        protected boolean isPressed() {
            return (super.isPressed() || !FlatComboBoxUI.this.comboBox.isEditable() && FlatComboBoxUI.this.pressed ? (int)-154382177L ^ 0xF6CC509E : (int)-632144095L ^ 0xDA523F21) != 0;
        }
    }
}

