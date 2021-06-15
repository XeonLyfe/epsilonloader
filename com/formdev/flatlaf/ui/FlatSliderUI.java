/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSliderUI;

public class FlatSliderUI
extends BasicSliderUI {
    protected int trackWidth;
    protected Dimension thumbSize;
    protected int focusWidth;
    protected Color trackValueColor;
    protected Color trackColor;
    protected Color thumbColor;
    protected Color thumbBorderColor;
    protected Color focusBaseColor;
    protected Color focusedColor;
    protected Color focusedThumbBorderColor;
    protected Color hoverThumbColor;
    protected Color pressedThumbColor;
    protected Color disabledTrackColor;
    protected Color disabledThumbColor;
    protected Color disabledThumbBorderColor;
    private Color defaultBackground;
    private Color defaultForeground;
    protected boolean thumbHover;
    protected boolean thumbPressed;
    private Object[] oldRenderingHints;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatSliderUI();
    }

    public FlatSliderUI() {
        super(null);
    }

    @Override
    protected void installDefaults(JSlider jSlider) {
        super.installDefaults(jSlider);
        LookAndFeel.installProperty(jSlider, "opaque", ((int)452847784L ^ 0x1AFDE8A8) != 0);
        this.trackWidth = UIManager.getInt("Slider.trackWidth");
        this.thumbSize = UIManager.getDimension("Slider.thumbSize");
        if (this.thumbSize == null) {
            int n2 = UIManager.getInt("Slider.thumbWidth");
            this.thumbSize = new Dimension(n2, n2);
        }
        this.focusWidth = FlatUIUtils.getUIInt("Slider.focusWidth", ((int)475908327L ^ 0x1C5DC8E6) << 2);
        this.trackValueColor = FlatUIUtils.getUIColor("Slider.trackValueColor", "Slider.thumbColor");
        this.trackColor = UIManager.getColor("Slider.trackColor");
        this.thumbColor = UIManager.getColor("Slider.thumbColor");
        this.thumbBorderColor = UIManager.getColor("Slider.thumbBorderColor");
        this.focusBaseColor = UIManager.getColor("Component.focusColor");
        this.focusedColor = FlatUIUtils.getUIColor("Slider.focusedColor", this.focusBaseColor);
        this.focusedThumbBorderColor = FlatUIUtils.getUIColor("Slider.focusedThumbBorderColor", "Component.focusedBorderColor");
        this.hoverThumbColor = UIManager.getColor("Slider.hoverThumbColor");
        this.pressedThumbColor = UIManager.getColor("Slider.pressedThumbColor");
        this.disabledTrackColor = UIManager.getColor("Slider.disabledTrackColor");
        this.disabledThumbColor = UIManager.getColor("Slider.disabledThumbColor");
        this.disabledThumbBorderColor = FlatUIUtils.getUIColor("Slider.disabledThumbBorderColor", "Component.disabledBorderColor");
        this.defaultBackground = UIManager.getColor("Slider.background");
        this.defaultForeground = UIManager.getColor("Slider.foreground");
    }

    @Override
    protected void uninstallDefaults(JSlider jSlider) {
        super.uninstallDefaults(jSlider);
        this.trackValueColor = null;
        this.trackColor = null;
        this.thumbColor = null;
        this.thumbBorderColor = null;
        this.focusBaseColor = null;
        this.focusedColor = null;
        this.focusedThumbBorderColor = null;
        this.hoverThumbColor = null;
        this.pressedThumbColor = null;
        this.disabledTrackColor = null;
        this.disabledThumbColor = null;
        this.disabledThumbBorderColor = null;
        this.defaultBackground = null;
        this.defaultForeground = null;
    }

    @Override
    protected BasicSliderUI.TrackListener createTrackListener(JSlider jSlider) {
        return new FlatTrackListener();
    }

    @Override
    public int getBaseline(JComponent jComponent, int n2, int n3) {
        if (jComponent == null) {
            throw new NullPointerException();
        }
        if (n2 < 0 || n3 < 0) {
            throw new IllegalArgumentException();
        }
        if (this.slider.getOrientation() == ((int)-1169228705L ^ 0xBA4EFC5E)) {
            return (int)-468640687L ^ 0x1BEEE3AE;
        }
        FontMetrics fontMetrics = this.slider.getFontMetrics(this.slider.getFont());
        return this.trackRect.y + Math.round((float)(this.trackRect.height - fontMetrics.getHeight()) / 2.0f) + fontMetrics.getAscent() - ((int)-204913362L ^ 0xF3C9452F);
    }

    @Override
    public Dimension getPreferredHorizontalSize() {
        return UIScale.scale(super.getPreferredHorizontalSize());
    }

    @Override
    public Dimension getPreferredVerticalSize() {
        return UIScale.scale(super.getPreferredVerticalSize());
    }

    @Override
    public Dimension getMinimumHorizontalSize() {
        return UIScale.scale(super.getMinimumHorizontalSize());
    }

    @Override
    public Dimension getMinimumVerticalSize() {
        return UIScale.scale(super.getMinimumVerticalSize());
    }

    @Override
    protected int getTickLength() {
        return UIScale.scale(super.getTickLength());
    }

    @Override
    protected Dimension getThumbSize() {
        return FlatSliderUI.calcThumbSize(this.slider, this.thumbSize, this.focusWidth);
    }

    public static Dimension calcThumbSize(JSlider jSlider, Dimension dimension, int n2) {
        int n3 = UIScale.scale(n2);
        int n4 = UIScale.scale(dimension.width) + n3 + n3;
        int n5 = UIScale.scale(dimension.height) + n3 + n3;
        return jSlider.getOrientation() == 0 ? new Dimension(n4, n5) : new Dimension(n5, n4);
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        this.oldRenderingHints = FlatUIUtils.setRenderingHints(graphics);
        super.paint(graphics, jComponent);
        FlatUIUtils.resetRenderingHints(graphics, this.oldRenderingHints);
        this.oldRenderingHints = null;
    }

    @Override
    public void paintLabels(Graphics graphics) {
        FlatUIUtils.runWithoutRenderingHints(graphics, this.oldRenderingHints, () -> super.paintLabels(graphics));
    }

    @Override
    public void paintFocus(Graphics graphics) {
    }

    @Override
    public void paintTrack(Graphics graphics) {
        RoundRectangle2D.Float float_;
        float f2;
        float f3;
        boolean bl = this.slider.isEnabled();
        float f4 = f3 = UIScale.scale((float)this.trackWidth);
        RoundRectangle2D.Float float_2 = null;
        if (this.slider.getOrientation() == 0) {
            f2 = (float)this.trackRect.y + ((float)this.trackRect.height - f3) / 2.0f;
            if (bl && this.isRoundThumb()) {
                if (this.slider.getComponentOrientation().isLeftToRight()) {
                    int n2 = this.thumbRect.x + this.thumbRect.width / ((int)((long)2018111988 ^ (long)2018111989) << 1) - this.trackRect.x;
                    float_2 = new RoundRectangle2D.Float(this.trackRect.x, f2, n2, f3, f4, f4);
                    float_ = new RoundRectangle2D.Float(this.trackRect.x + n2, f2, this.trackRect.width - n2, f3, f4, f4);
                } else {
                    int n3 = this.trackRect.x + this.trackRect.width - this.thumbRect.x - this.thumbRect.width / ((int)((long)1754652684 ^ (long)1754652685) << 1);
                    float_2 = new RoundRectangle2D.Float(this.trackRect.x + this.trackRect.width - n3, f2, n3, f3, f4, f4);
                    float_ = new RoundRectangle2D.Float(this.trackRect.x, f2, this.trackRect.width - n3, f3, f4, f4);
                }
            } else {
                float_ = new RoundRectangle2D.Float(this.trackRect.x, f2, this.trackRect.width, f3, f4, f4);
            }
        } else {
            f2 = (float)this.trackRect.x + ((float)this.trackRect.width - f3) / 2.0f;
            if (bl && this.isRoundThumb()) {
                int n4 = this.thumbRect.y + this.thumbRect.height / (((int)268845214L ^ 0x1006409F) << 1) - this.trackRect.y;
                float_ = new RoundRectangle2D.Float(f2, this.trackRect.y, f3, n4, f4, f4);
                float_2 = new RoundRectangle2D.Float(f2, this.trackRect.y + n4, f3, this.trackRect.height - n4, f4, f4);
            } else {
                float_ = new RoundRectangle2D.Float(f2, this.trackRect.y, f3, this.trackRect.height, f4, f4);
            }
        }
        if (float_2 != null) {
            if (this.slider.getInverted()) {
                RoundRectangle2D.Float float_3 = float_;
                float_ = float_2;
                float_2 = float_3;
            }
            graphics.setColor(this.getTrackValueColor());
            ((Graphics2D)graphics).fill(float_2);
        }
        graphics.setColor(bl ? this.getTrackColor() : this.disabledTrackColor);
        ((Graphics2D)graphics).fill(float_);
    }

    @Override
    public void paintThumb(Graphics graphics) {
        Color color = this.getThumbColor();
        Color color2 = FlatSliderUI.stateColor(this.slider, this.thumbHover, this.thumbPressed, color, this.disabledThumbColor, null, this.hoverThumbColor, this.pressedThumbColor);
        color2 = FlatUIUtils.deriveColor(color2, color);
        Color color3 = this.slider.getForeground();
        Color color4 = this.thumbBorderColor != null && color3 == this.defaultForeground ? FlatSliderUI.stateColor(this.slider, (boolean)((long)-280788083 ^ (long)-280788083), (boolean)((long)848148716 ^ (long)848148716), this.thumbBorderColor, this.disabledThumbBorderColor, this.focusedThumbBorderColor, null, null) : null;
        Color color5 = FlatUIUtils.deriveColor(this.focusedColor, color3 != this.defaultForeground ? color3 : this.focusBaseColor);
        FlatSliderUI.paintThumb(graphics, this.slider, this.thumbRect, this.isRoundThumb(), color2, color4, color5, this.focusWidth);
    }

    public static void paintThumb(Graphics graphics, JSlider jSlider, Rectangle rectangle, boolean bl, Color color, Color color2, Color color3, int n2) {
        double d3 = UIScale.getSystemScaleFactor((Graphics2D)graphics);
        if (d3 != 1.0 && d3 != Double.longBitsToDouble(0xFA4AC4B358CB2DDBL ^ 0xBA4AC4B358CB2DDBL)) {
            HiDPIUtils.paintAtScale1x((Graphics2D)graphics, rectangle.x, rectangle.y, rectangle.width, rectangle.height, (graphics2D, n3, n4, n5, n6, d2) -> FlatSliderUI.paintThumbImpl(graphics, jSlider, n3, n4, n5, n6, bl, color, color2, color3, (float)((double)n2 * d2)));
            return;
        }
        FlatSliderUI.paintThumbImpl(graphics, jSlider, rectangle.x, rectangle.y, rectangle.width, rectangle.height, bl, color, color2, color3, n2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void paintThumbImpl(Graphics graphics, JSlider jSlider, int n2, int n3, int n4, int n5, boolean bl, Color color, Color color2, Color color3, float f2) {
        int n6 = Math.round(UIScale.scale(f2));
        int n7 = n2 + n6;
        int n8 = n3 + n6;
        int n9 = n4 - n6 - n6;
        int n10 = n5 - n6 - n6;
        boolean bl2 = FlatUIUtils.isPermanentFocusOwner(jSlider);
        if (bl) {
            if (bl2) {
                graphics.setColor(color3);
                ((Graphics2D)graphics).fill(FlatSliderUI.createRoundThumbShape(n2, n3, n4, n5));
            }
            if (color2 != null) {
                graphics.setColor(color2);
                ((Graphics2D)graphics).fill(FlatSliderUI.createRoundThumbShape(n7, n8, n9, n10));
                float f3 = UIScale.scale(1.0f);
                graphics.setColor(color);
                ((Graphics2D)graphics).fill(FlatSliderUI.createRoundThumbShape((float)n7 + f3, (float)n8 + f3, (float)n9 - f3 - f3, (float)n10 - f3 - f3));
            } else {
                graphics.setColor(color);
                ((Graphics2D)graphics).fill(FlatSliderUI.createRoundThumbShape(n7, n8, n9, n10));
            }
        } else {
            Graphics2D graphics2D = (Graphics2D)graphics.create();
            try {
                graphics2D.translate(n2, n3);
                if (jSlider.getOrientation() == (int)((long)-229865028 ^ (long)-229865027)) {
                    if (jSlider.getComponentOrientation().isLeftToRight()) {
                        graphics2D.translate((int)1692865128L ^ 0x64E71268, n5);
                        graphics2D.rotate(Math.toRadians(Double.longBitsToDouble((long)1834425698 ^ 0x4070E0006D571D62L)));
                    } else {
                        graphics2D.translate(n4, (int)((long)-784818175 ^ (long)-784818175));
                        graphics2D.rotate(Math.toRadians(Double.longBitsToDouble(0x6F53D4D2EDB1A521L ^ 0x2F0554D2EDB1A521L)));
                    }
                    int n11 = n9;
                    n9 = n10;
                    n10 = n11;
                }
                if (bl2) {
                    graphics2D.setColor(color3);
                    graphics2D.fill(FlatSliderUI.createDirectionalThumbShape(0.0f, 0.0f, n9 + n6 + n6, (float)(n10 + n6 + n6) + (float)n6 * Float.intBitsToFloat(0x1EF4FDBE ^ 0x2020EFB8), n6));
                }
                if (color2 != null) {
                    graphics2D.setColor(color2);
                    graphics2D.fill(FlatSliderUI.createDirectionalThumbShape(n6, n6, n9, n10, 0.0f));
                    float f4 = UIScale.scale(1.0f);
                    graphics2D.setColor(color);
                    graphics2D.fill(FlatSliderUI.createDirectionalThumbShape((float)n6 + f4, (float)n6 + f4, (float)n9 - f4 - f4, (float)n10 - f4 - f4 - f4 * Float.intBitsToFloat((int)((long)1518046483 ^ (long)1689231125)), 0.0f));
                } else {
                    graphics2D.setColor(color);
                    graphics2D.fill(FlatSliderUI.createDirectionalThumbShape(n6, n6, n9, n10, 0.0f));
                }
            }
            finally {
                graphics2D.dispose();
            }
        }
    }

    public static Shape createRoundThumbShape(float f2, float f3, float f4, float f5) {
        if (f4 == f5) {
            return new Ellipse2D.Float(f2, f3, f4, f5);
        }
        float f6 = Math.min(f4, f5);
        return new RoundRectangle2D.Float(f2, f3, f4, f5, f6, f6);
    }

    public static Shape createDirectionalThumbShape(float f2, float f3, float f4, float f5, float f6) {
        float f7 = f4 / 2.0f;
        Path2D.Float float_ = new Path2D.Float();
        ((Path2D)float_).moveTo(f2 + f7, f3 + f5);
        ((Path2D)float_).lineTo(f2, f3 + (f5 - f7));
        ((Path2D)float_).lineTo(f2, f3 + f6);
        ((Path2D)float_).quadTo(f2, f3, f2 + f6, f3);
        ((Path2D)float_).lineTo(f2 + (f4 - f6), f3);
        ((Path2D)float_).quadTo(f2 + f4, f3, f2 + f4, f3 + f6);
        ((Path2D)float_).lineTo(f2 + f4, f3 + (f5 - f7));
        float_.closePath();
        return float_;
    }

    protected Color getTrackValueColor() {
        Color color = this.slider.getForeground();
        return color != this.defaultForeground ? color : this.trackValueColor;
    }

    protected Color getTrackColor() {
        Color color = this.slider.getBackground();
        return color != this.defaultBackground ? color : this.trackColor;
    }

    protected Color getThumbColor() {
        Color color = this.slider.getForeground();
        return color != this.defaultForeground ? color : this.thumbColor;
    }

    public static Color stateColor(JSlider jSlider, boolean bl, boolean bl2, Color color, Color color2, Color color3, Color color4, Color color5) {
        if (color2 != null && !jSlider.isEnabled()) {
            return color2;
        }
        if (color5 != null && bl2) {
            return color5;
        }
        if (color4 != null && bl) {
            return color4;
        }
        if (color3 != null && FlatUIUtils.isPermanentFocusOwner(jSlider)) {
            return color3;
        }
        return color;
    }

    protected boolean isRoundThumb() {
        return (!this.slider.getPaintTicks() && !this.slider.getPaintLabels() ? (int)((long)-2121170189 ^ (long)-2121170190) : (int)1469598798L ^ 0x57984C4E) != 0;
    }

    @Override
    public void setThumbLocation(int n2, int n3) {
        if (!this.isRoundThumb()) {
            Rectangle rectangle = new Rectangle(this.thumbRect);
            this.thumbRect.setLocation(n2, n3);
            SwingUtilities.computeUnion(this.thumbRect.x, this.thumbRect.y, this.thumbRect.width, this.thumbRect.height, rectangle);
            int n4 = (int)Math.ceil((float)UIScale.scale(this.focusWidth) * Float.intBitsToFloat((int)((long)1246527140 ^ (long)1956146338)));
            if (this.slider.getOrientation() == 0) {
                rectangle.height += n4;
            } else {
                rectangle.width += n4;
                if (!this.slider.getComponentOrientation().isLeftToRight()) {
                    rectangle.x -= n4;
                }
            }
            this.slider.repaint(rectangle);
        } else {
            super.setThumbLocation(n2, n3);
        }
    }

    protected class FlatTrackListener
    extends BasicSliderUI.TrackListener {
        protected FlatTrackListener() {
            super(FlatSliderUI.this);
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            this.setThumbHover(this.isOverThumb(mouseEvent));
            super.mouseEntered(mouseEvent);
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            this.setThumbHover(((int)-369510758L ^ 0xE9F9B69A) != 0);
            super.mouseExited(mouseEvent);
        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            this.setThumbHover(this.isOverThumb(mouseEvent));
            super.mouseMoved(mouseEvent);
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            this.setThumbPressed(this.isOverThumb(mouseEvent));
            if (!FlatSliderUI.this.slider.isEnabled()) {
                return;
            }
            if (UIManager.getBoolean("Slider.scrollOnTrackClick")) {
                super.mousePressed(mouseEvent);
                return;
            }
            int n2 = mouseEvent.getX();
            int n3 = mouseEvent.getY();
            FlatSliderUI.this.calculateGeometry();
            if (FlatSliderUI.this.thumbRect.contains(n2, n3)) {
                super.mousePressed(mouseEvent);
                return;
            }
            if (UIManager.getBoolean("Slider.onlyLeftMouseButtonDrag") && !SwingUtilities.isLeftMouseButton(mouseEvent)) {
                return;
            }
            int n4 = ((FlatSliderUI)FlatSliderUI.this).thumbRect.x + ((FlatSliderUI)FlatSliderUI.this).thumbRect.width / (((int)1904991598L ^ 0x718BDD6F) << 1) - n2;
            int n5 = ((FlatSliderUI)FlatSliderUI.this).thumbRect.y + ((FlatSliderUI)FlatSliderUI.this).thumbRect.height / (((int)616673564L ^ 0x24C1B11D) << 1) - n3;
            mouseEvent.translatePoint(n4, n5);
            super.mousePressed(mouseEvent);
            mouseEvent.translatePoint(-n4, -n5);
            this.mouseDragged(mouseEvent);
            this.setThumbPressed(((int)654555481L ^ 0x2703B958) != 0);
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            this.setThumbPressed((boolean)((long)-1104380998 ^ (long)-1104380998));
            super.mouseReleased(mouseEvent);
        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            super.mouseDragged(mouseEvent);
            if (FlatSliderUI.this.isDragging() && FlatSliderUI.this.slider.getSnapToTicks() && FlatSliderUI.this.slider.isEnabled() && !UIManager.getBoolean("Slider.snapToTicksOnReleased")) {
                FlatSliderUI.this.calculateThumbLocation();
                FlatSliderUI.this.slider.repaint();
            }
        }

        protected void setThumbHover(boolean bl) {
            if (bl != FlatSliderUI.this.thumbHover) {
                FlatSliderUI.this.thumbHover = bl;
                FlatSliderUI.this.slider.repaint(FlatSliderUI.this.thumbRect);
            }
        }

        protected void setThumbPressed(boolean bl) {
            if (bl != FlatSliderUI.this.thumbPressed) {
                FlatSliderUI.this.thumbPressed = bl;
                FlatSliderUI.this.slider.repaint(FlatSliderUI.this.thumbRect);
            }
        }

        protected boolean isOverThumb(MouseEvent mouseEvent) {
            return (mouseEvent != null && FlatSliderUI.this.slider.isEnabled() && FlatSliderUI.this.thumbRect.contains(mouseEvent.getX(), mouseEvent.getY()) ? (int)((long)-1110764210 ^ (long)-1110764209) : (int)((long)-1847271195 ^ (long)-1847271195)) != 0;
        }
    }
}

