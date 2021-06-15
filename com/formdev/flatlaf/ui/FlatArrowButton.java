/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicArrowButton;

public class FlatArrowButton
extends BasicArrowButton
implements UIResource {
    public static final int DEFAULT_ARROW_WIDTH = 8;
    protected final boolean chevron;
    protected final Color foreground;
    protected final Color disabledForeground;
    protected final Color hoverForeground;
    protected final Color hoverBackground;
    protected final Color pressedForeground;
    protected final Color pressedBackground;
    private int arrowWidth = (int)((long)-508542158 ^ (long)-508542157) << 3;
    private int xOffset = (int)((long)924859430 ^ (long)924859430);
    private int yOffset = (int)1179251138L ^ 0x4649F1C2;
    private boolean hover;
    private boolean pressed;

    public FlatArrowButton(int n2, String string, Color color, Color color2, Color color3, Color color4, Color color5, Color color6) {
        super(n2, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
        this.chevron = FlatUIUtils.isChevron(string);
        this.foreground = color;
        this.disabledForeground = color2;
        this.hoverForeground = color3;
        this.hoverBackground = color4;
        this.pressedForeground = color5;
        this.pressedBackground = color6;
        this.setOpaque((boolean)((long)1608867213 ^ (long)1608867213));
        this.setBorder(null);
        if (color3 != null || color4 != null || color5 != null || color6 != null) {
            this.addMouseListener(new MouseAdapter(){

                @Override
                public void mouseEntered(MouseEvent mouseEvent) {
                    FlatArrowButton.this.hover = ((int)412069972L ^ 0x188FB055) != 0;
                    FlatArrowButton.this.repaint();
                }

                @Override
                public void mouseExited(MouseEvent mouseEvent) {
                    FlatArrowButton.this.hover = (boolean)((long)877033413 ^ (long)877033413);
                    FlatArrowButton.this.repaint();
                }

                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    FlatArrowButton.this.pressed = ((int)-1452751377L ^ 0xA968C5EE) != 0;
                    FlatArrowButton.this.repaint();
                }

                @Override
                public void mouseReleased(MouseEvent mouseEvent) {
                    FlatArrowButton.this.pressed = ((int)-726257478L ^ 0xD4B630BA) != 0;
                    FlatArrowButton.this.repaint();
                }
            });
        }
    }

    public int getArrowWidth() {
        return this.arrowWidth;
    }

    public void setArrowWidth(int n2) {
        this.arrowWidth = n2;
    }

    protected boolean isHover() {
        return this.hover;
    }

    protected boolean isPressed() {
        return this.pressed;
    }

    public int getXOffset() {
        return this.xOffset;
    }

    public void setXOffset(int n2) {
        this.xOffset = n2;
    }

    public int getYOffset() {
        return this.yOffset;
    }

    public void setYOffset(int n2) {
        this.yOffset = n2;
    }

    protected Color deriveBackground(Color color) {
        return color;
    }

    protected Color deriveForeground(Color color) {
        return FlatUIUtils.deriveColor(color, this.foreground);
    }

    @Override
    public Dimension getPreferredSize() {
        return UIScale.scale(super.getPreferredSize());
    }

    @Override
    public Dimension getMinimumSize() {
        return UIScale.scale(super.getMinimumSize());
    }

    @Override
    public void paint(Graphics graphics) {
        Object[] arrobject = FlatUIUtils.setRenderingHints(graphics);
        if (this.isEnabled()) {
            Color color;
            Color color2 = this.pressedBackground != null && this.isPressed() ? this.pressedBackground : (color = this.hoverBackground != null && this.isHover() ? this.hoverBackground : null);
            if (color != null) {
                graphics.setColor(this.deriveBackground(color));
                this.paintBackground((Graphics2D)graphics);
            }
        }
        graphics.setColor(this.deriveForeground(this.isEnabled() ? (this.pressedForeground != null && this.isPressed() ? this.pressedForeground : (this.hoverForeground != null && this.isHover() ? this.hoverForeground : this.foreground)) : this.disabledForeground));
        this.paintArrow((Graphics2D)graphics);
        FlatUIUtils.resetRenderingHints(graphics, arrobject);
    }

    protected void paintBackground(Graphics2D graphics2D) {
        graphics2D.fillRect((int)((long)-577544176 ^ (long)-577544176), (int)-380314577L ^ 0xE954DC2F, this.getWidth(), this.getHeight());
    }

    protected void paintArrow(Graphics2D graphics2D) {
        int n2 = this.direction == ((int)202381486L ^ 0xC1018AF) || this.direction == (int)((long)413058066 ^ (long)413058071) ? (int)-1734753091L ^ 0x9899C4BC : (int)((long)2005927051 ^ (long)2005927051);
        int n3 = (int)-1286975631L ^ 0xB34A4F71;
        Container container = this.getParent();
        if (n2 != 0 && container instanceof JComponent && FlatUIUtils.hasRoundBorder((JComponent)container)) {
            n3 -= UIScale.scale(container.getComponentOrientation().isLeftToRight() ? (int)-445780714L ^ 0xE56DED17 : (int)((long)-2042261328 ^ (long)2042261327));
        }
        FlatUIUtils.paintArrow(graphics2D, n3, (int)1118907503L ^ 0x42B12C6F, this.getWidth(), this.getHeight(), this.getDirection(), this.chevron, this.arrowWidth, this.xOffset, this.yOffset);
    }
}

