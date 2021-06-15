/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicOptionPaneUI;

public class FlatOptionPaneUI
extends BasicOptionPaneUI {
    protected int iconMessageGap;
    protected int messagePadding;
    protected int maxCharactersPerLine;
    private int focusWidth;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatOptionPaneUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        this.iconMessageGap = UIManager.getInt("OptionPane.iconMessageGap");
        this.messagePadding = UIManager.getInt("OptionPane.messagePadding");
        this.maxCharactersPerLine = UIManager.getInt("OptionPane.maxCharactersPerLine");
        this.focusWidth = UIManager.getInt("Component.focusWidth");
    }

    @Override
    protected void installComponents() {
        super.installComponents();
        this.updateChildPanels(this.optionPane);
    }

    @Override
    public Dimension getMinimumOptionPaneSize() {
        return UIScale.scale(super.getMinimumOptionPaneSize());
    }

    @Override
    protected int getMaxCharactersPerLineCount() {
        int n2 = super.getMaxCharactersPerLineCount();
        return this.maxCharactersPerLine > 0 && n2 == Integer.MAX_VALUE ? this.maxCharactersPerLine : n2;
    }

    @Override
    protected Container createMessageArea() {
        Component component;
        Container container = super.createMessageArea();
        if (this.iconMessageGap > 0 && (component = this.findByName(container, "OptionPane.separator")) != null) {
            component.setPreferredSize(new Dimension(UIScale.scale(this.iconMessageGap), (int)((long)1072507387 ^ (long)1072507386)));
        }
        return container;
    }

    @Override
    protected Container createButtonArea() {
        Container container = super.createButtonArea();
        if (container.getLayout() instanceof BasicOptionPaneUI.ButtonAreaLayout) {
            BasicOptionPaneUI.ButtonAreaLayout buttonAreaLayout = (BasicOptionPaneUI.ButtonAreaLayout)container.getLayout();
            buttonAreaLayout.setPadding(UIScale.scale(buttonAreaLayout.getPadding() - this.focusWidth * ((int)((long)3505672 ^ (long)3505673) << 1)));
        }
        return container;
    }

    @Override
    protected void addMessageComponents(Container container, GridBagConstraints gridBagConstraints, Object object, int n2, boolean bl) {
        if (this.messagePadding > 0) {
            gridBagConstraints.insets.bottom = UIScale.scale(this.messagePadding);
        }
        if (object instanceof String && BasicHTML.isHTMLString((String)object)) {
            n2 = Integer.MAX_VALUE;
        }
        super.addMessageComponents(container, gridBagConstraints, object, n2, bl);
    }

    private void updateChildPanels(Container container) {
        Component[] arrcomponent = container.getComponents();
        int n2 = arrcomponent.length;
        for (int i2 = (int)((long)335459528 ^ (long)335459528); i2 < n2; ++i2) {
            Component component = arrcomponent[i2];
            if (component instanceof JPanel) {
                JPanel jPanel = (JPanel)component;
                jPanel.setOpaque(((int)2052697463L ^ 0x7A59AD77) != 0);
                Border border = jPanel.getBorder();
                if (border instanceof UIResource) {
                    jPanel.setBorder(new NonUIResourceBorder(border));
                }
            }
            if (!(component instanceof Container)) continue;
            this.updateChildPanels((Container)component);
        }
    }

    private Component findByName(Container container, String string) {
        Component[] arrcomponent = container.getComponents();
        int n2 = arrcomponent.length;
        for (int i2 = (int)((long)283708752 ^ (long)283708752); i2 < n2; ++i2) {
            Component component;
            Component component2 = arrcomponent[i2];
            if (string.equals(component2.getName())) {
                return component2;
            }
            if (!(component2 instanceof Container) || (component = this.findByName((Container)component2, string)) == null) continue;
            return component;
        }
        return null;
    }

    private static class NonUIResourceBorder
    implements Border {
        private final Border delegate;

        NonUIResourceBorder(Border border) {
            this.delegate = border;
        }

        @Override
        public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
            this.delegate.paintBorder(component, graphics, n2, n3, n4, n5);
        }

        @Override
        public Insets getBorderInsets(Component component) {
            return this.delegate.getBorderInsets(component);
        }

        @Override
        public boolean isBorderOpaque() {
            return this.delegate.isBorderOpaque();
        }
    }
}

