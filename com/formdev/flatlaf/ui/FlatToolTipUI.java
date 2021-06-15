/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatLabelUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.StringUtils;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolTipUI;

public class FlatToolTipUI
extends BasicToolTipUI {
    private static PropertyChangeListener sharedPropertyChangedListener;

    public static ComponentUI createUI(JComponent jComponent) {
        return FlatUIUtils.createSharedUI(FlatToolTipUI.class, FlatToolTipUI::new);
    }

    @Override
    public void installUI(JComponent jComponent) {
        super.installUI(jComponent);
        FlatLabelUI.updateHTMLRenderer(jComponent, ((JToolTip)jComponent).getTipText(), ((int)-1526735833L ^ 0xA4FFDC27) != 0);
    }

    @Override
    protected void installListeners(JComponent jComponent) {
        super.installListeners(jComponent);
        if (sharedPropertyChangedListener == null) {
            sharedPropertyChangedListener = propertyChangeEvent -> {
                String string = propertyChangeEvent.getPropertyName();
                if (string == "tiptext" || string == "font" || string == "foreground") {
                    JToolTip jToolTip = (JToolTip)propertyChangeEvent.getSource();
                    FlatLabelUI.updateHTMLRenderer(jToolTip, jToolTip.getTipText(), (boolean)((long)-2134869717 ^ (long)-2134869717));
                }
            };
        }
        jComponent.addPropertyChangeListener(sharedPropertyChangedListener);
    }

    @Override
    protected void uninstallListeners(JComponent jComponent) {
        super.uninstallListeners(jComponent);
        jComponent.removePropertyChangeListener(sharedPropertyChangedListener);
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        String string = ((JToolTip)jComponent).getTipText();
        if (string == null || string.isEmpty()) {
            return new Dimension();
        }
        if (this.isMultiLine(jComponent)) {
            FontMetrics fontMetrics = jComponent.getFontMetrics(jComponent.getFont());
            Insets insets = jComponent.getInsets();
            List<String> list = StringUtils.split(((JToolTip)jComponent).getTipText(), ((int)-1189783227L ^ 0xB9155940) << 1);
            int n2 = (int)-798648074L ^ 0xD06598F6;
            int n3 = fontMetrics.getHeight() * Math.max(list.size(), (int)((long)2103247686 ^ (long)2103247687));
            for (String string2 : list) {
                n2 = Math.max(n2, SwingUtilities.computeStringWidth(fontMetrics, string2));
            }
            return new Dimension(insets.left + n2 + insets.right + ((int)((long)-1208053759 ^ (long)-1208053758) << 1), insets.top + n3 + insets.bottom);
        }
        return super.getPreferredSize(jComponent);
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        if (this.isMultiLine(jComponent)) {
            FontMetrics fontMetrics = jComponent.getFontMetrics(jComponent.getFont());
            Insets insets = jComponent.getInsets();
            graphics.setColor(jComponent.getForeground());
            List<String> list = StringUtils.split(((JToolTip)jComponent).getTipText(), (int)((long)879721289 ^ (long)879721292) << 1);
            int n2 = insets.left + (int)((long)-1204133864 ^ (long)-1204133861);
            int n3 = jComponent.getWidth() - insets.right - ((int)-712153733L ^ 0xD58D6578);
            int n4 = insets.top - fontMetrics.getDescent();
            int n5 = fontMetrics.getHeight();
            JComponent jComponent2 = ((JToolTip)jComponent).getComponent();
            boolean bl = (jComponent2 != null ? jComponent2 : jComponent).getComponentOrientation().isLeftToRight();
            for (String string : list) {
                FlatUIUtils.drawString(jComponent, graphics, string, bl ? n2 : n3 - SwingUtilities.computeStringWidth(fontMetrics, string), n4 += n5);
            }
        } else {
            super.paint(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)graphics), jComponent);
        }
    }

    private boolean isMultiLine(JComponent jComponent) {
        String string = ((JToolTip)jComponent).getTipText();
        return (jComponent.getClientProperty("html") == null && string != null && string.indexOf(((int)1492747645L ^ 0x58F98578) << 1) >= 0 ? (int)238693325L ^ 0xE3A2BCC : (int)((long)1761159657 ^ (long)1761159657)) != 0;
    }
}

