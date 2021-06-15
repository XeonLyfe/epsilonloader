/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.DerivedColor;
import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.text.AttributedCharacterIterator;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.View;

public class FlatMenuItemRenderer {
    protected final JMenuItem menuItem;
    protected final Icon checkIcon;
    protected final Icon arrowIcon;
    protected final Font acceleratorFont;
    protected final String acceleratorDelimiter;
    protected final int minimumWidth = UIManager.getInt("MenuItem.minimumWidth");
    protected final Dimension minimumIconSize;
    protected final int textAcceleratorGap = FlatUIUtils.getUIInt("MenuItem.textAcceleratorGap", ((int)-517339278L ^ 0xE12A0775) << 2);
    protected final int textNoAcceleratorGap = FlatUIUtils.getUIInt("MenuItem.textNoAcceleratorGap", ((int)-1196223202L ^ 0xB8B3151D) << 1);
    protected final int acceleratorArrowGap = FlatUIUtils.getUIInt("MenuItem.acceleratorArrowGap", (int)((long)1391207556 ^ (long)1391207557) << 1);
    protected final Color checkBackground = UIManager.getColor("MenuItem.checkBackground");
    protected final Insets checkMargins = UIManager.getInsets("MenuItem.checkMargins");
    protected final Color underlineSelectionBackground = UIManager.getColor("MenuItem.underlineSelectionBackground");
    protected final Color underlineSelectionCheckBackground = UIManager.getColor("MenuItem.underlineSelectionCheckBackground");
    protected final Color underlineSelectionColor = UIManager.getColor("MenuItem.underlineSelectionColor");
    protected final int underlineSelectionHeight = UIManager.getInt("MenuItem.underlineSelectionHeight");
    protected final Color selectionBackground = UIManager.getColor("MenuItem.selectionBackground");
    private KeyStroke cachedAccelerator;
    private String cachedAcceleratorText;
    private boolean cachedAcceleratorLeftToRight;
    private static final char controlGlyph = '\u2303';
    private static final char optionGlyph = '\u2325';
    private static final char shiftGlyph = '\u21e7';
    private static final char commandGlyph = '\u2318';

    protected FlatMenuItemRenderer(JMenuItem jMenuItem, Icon icon, Icon icon2, Font font, String string) {
        this.menuItem = jMenuItem;
        this.checkIcon = icon;
        this.arrowIcon = icon2;
        this.acceleratorFont = font;
        this.acceleratorDelimiter = string;
        Dimension dimension = UIManager.getDimension("MenuItem.minimumIconSize");
        this.minimumIconSize = dimension != null ? dimension : new Dimension(((int)1480029846L ^ 0x58377697) << 4, ((int)393853804L ^ 0x1779BB6D) << 4);
    }

    protected Dimension getPreferredMenuItemSize() {
        Serializable serializable;
        int n2 = (int)1947167519L ^ 0x740F6B1F;
        int n3 = (int)2101517519L ^ 0x7D429CCF;
        boolean bl = FlatMenuItemRenderer.isTopLevelMenu(this.menuItem);
        Rectangle rectangle = new Rectangle((int)((long)666986688 ^ (long)666986688), (int)((long)1888721869 ^ (long)1888721869), Integer.MAX_VALUE, Integer.MAX_VALUE);
        Rectangle rectangle2 = new Rectangle();
        Rectangle rectangle3 = new Rectangle();
        SwingUtilities.layoutCompoundLabel(this.menuItem, this.menuItem.getFontMetrics(this.menuItem.getFont()), this.menuItem.getText(), this.getIconForLayout(), this.menuItem.getVerticalAlignment(), this.menuItem.getHorizontalAlignment(), this.menuItem.getVerticalTextPosition(), this.menuItem.getHorizontalTextPosition(), rectangle, rectangle2, rectangle3, UIScale.scale(this.menuItem.getIconTextGap()));
        Rectangle rectangle4 = rectangle2.union(rectangle3);
        n2 += rectangle4.width;
        n3 = Math.max(rectangle4.height, n3);
        String string = this.getAcceleratorText();
        if (string != null) {
            n2 += UIScale.scale(!bl ? this.textAcceleratorGap : this.menuItem.getIconTextGap());
            serializable = this.menuItem.getFontMetrics(this.acceleratorFont);
            n2 += SwingUtilities.computeStringWidth((FontMetrics)serializable, string);
            n3 = Math.max(((FontMetrics)serializable).getHeight(), n3);
        }
        if (!bl && this.arrowIcon != null) {
            if (string == null) {
                n2 += UIScale.scale(this.textNoAcceleratorGap);
            }
            n2 += UIScale.scale(this.acceleratorArrowGap);
            n2 += this.arrowIcon.getIconWidth();
            n3 = Math.max(this.arrowIcon.getIconHeight(), n3);
        }
        serializable = this.menuItem.getInsets();
        n2 += ((Insets)serializable).left + ((Insets)serializable).right;
        n3 += ((Insets)serializable).top + ((Insets)serializable).bottom;
        if (!bl) {
            int n4 = FlatUIUtils.minimumWidth(this.menuItem, this.minimumWidth);
            n2 = Math.max(n2, UIScale.scale(n4));
        }
        return new Dimension(n2, n3);
    }

    private void layout(Rectangle rectangle, Rectangle rectangle2, Rectangle rectangle3, Rectangle rectangle4, Rectangle rectangle5, Rectangle rectangle6) {
        int n2;
        boolean bl = FlatMenuItemRenderer.isTopLevelMenu(this.menuItem);
        if (!bl && this.arrowIcon != null) {
            rectangle5.width = this.arrowIcon.getIconWidth();
            rectangle5.height = this.arrowIcon.getIconHeight();
        } else {
            rectangle5.setSize((int)-1535838002L ^ 0xA474F8CE, (int)876483693L ^ 0x343E146D);
        }
        rectangle5.y = rectangle.y + FlatMenuItemRenderer.centerOffset(rectangle.height, rectangle5.height);
        String string = this.getAcceleratorText();
        if (string != null) {
            FontMetrics fontMetrics = this.menuItem.getFontMetrics(this.acceleratorFont);
            rectangle4.width = SwingUtilities.computeStringWidth(fontMetrics, string);
            rectangle4.height = fontMetrics.getHeight();
            rectangle4.y = rectangle.y + FlatMenuItemRenderer.centerOffset(rectangle.height, rectangle4.height);
        } else {
            rectangle4.setBounds((int)((long)-1642708729 ^ (long)-1642708729), (int)-436717012L ^ 0xE5F83A2C, (int)((long)1940512246 ^ (long)1940512246), (int)-1259425381L ^ 0xB4EEB19B);
        }
        int n3 = n2 = !bl ? UIScale.scale(this.acceleratorArrowGap) : (int)-1022787785L ^ 0xC3097F37;
        if (this.menuItem.getComponentOrientation().isLeftToRight()) {
            rectangle5.x = rectangle.x + rectangle.width - rectangle5.width;
            rectangle4.x = rectangle5.x - n2 - rectangle4.width;
        } else {
            rectangle5.x = rectangle.x;
            rectangle4.x = rectangle5.x + n2 + rectangle5.width;
        }
        int n4 = rectangle4.width + rectangle5.width;
        if (string != null) {
            n4 += UIScale.scale(!bl ? this.textAcceleratorGap : this.menuItem.getIconTextGap());
        }
        if (!bl && this.arrowIcon != null) {
            if (string == null) {
                n4 += UIScale.scale(this.textNoAcceleratorGap);
            }
            n4 += UIScale.scale(this.acceleratorArrowGap);
        }
        rectangle6.setBounds(rectangle);
        rectangle6.width -= n4;
        if (!this.menuItem.getComponentOrientation().isLeftToRight()) {
            rectangle6.x += n4;
        }
        SwingUtilities.layoutCompoundLabel(this.menuItem, this.menuItem.getFontMetrics(this.menuItem.getFont()), this.menuItem.getText(), this.getIconForLayout(), this.menuItem.getVerticalAlignment(), this.menuItem.getHorizontalAlignment(), this.menuItem.getVerticalTextPosition(), this.menuItem.getHorizontalTextPosition(), rectangle6, rectangle2, rectangle3, UIScale.scale(this.menuItem.getIconTextGap()));
    }

    private static int centerOffset(int n2, int n3) {
        return n2 / (((int)136224370L ^ 0x81E9E73) << 1) - n3 / (((int)1817779873L ^ 0x6C591EA0) << 1);
    }

    protected void paintMenuItem(Graphics graphics, Color color, Color color2, Color color3, Color color4, Color color5) {
        Rectangle rectangle = new Rectangle(this.menuItem.getWidth(), this.menuItem.getHeight());
        Insets insets = this.menuItem.getInsets();
        rectangle.x += insets.left;
        rectangle.y += insets.top;
        rectangle.width -= insets.left + insets.right;
        rectangle.height -= insets.top + insets.bottom;
        Rectangle rectangle2 = new Rectangle();
        Rectangle rectangle3 = new Rectangle();
        Rectangle rectangle4 = new Rectangle();
        Rectangle rectangle5 = new Rectangle();
        Rectangle rectangle6 = new Rectangle();
        this.layout(rectangle, rectangle2, rectangle3, rectangle4, rectangle5, rectangle6);
        boolean bl = this.isUnderlineSelection();
        this.paintBackground(graphics, bl ? this.underlineSelectionBackground : color);
        if (bl && FlatMenuItemRenderer.isArmedOrSelected(this.menuItem)) {
            this.paintUnderlineSelection(graphics, this.underlineSelectionColor, this.underlineSelectionHeight);
        }
        this.paintIcon(graphics, rectangle2, this.getIconForPainting(), bl ? this.underlineSelectionCheckBackground : this.checkBackground);
        this.paintText(graphics, rectangle3, this.menuItem.getText(), color2, color3);
        this.paintAccelerator(graphics, rectangle4, this.getAcceleratorText(), color4, color5, color3);
        if (!FlatMenuItemRenderer.isTopLevelMenu(this.menuItem)) {
            this.paintArrowIcon(graphics, rectangle5, this.arrowIcon);
        }
    }

    protected void paintBackground(Graphics graphics, Color color) {
        boolean bl = FlatMenuItemRenderer.isArmedOrSelected(this.menuItem);
        if (this.menuItem.isOpaque() || bl) {
            graphics.setColor(bl ? this.deriveBackground(color) : this.menuItem.getBackground());
            graphics.fillRect((int)-317649102L ^ 0xED110F32, (int)((long)-529344549 ^ (long)-529344549), this.menuItem.getWidth(), this.menuItem.getHeight());
        }
    }

    protected void paintUnderlineSelection(Graphics graphics, Color color, int n2) {
        int n3 = this.menuItem.getWidth();
        int n4 = this.menuItem.getHeight();
        int n5 = UIScale.scale(n2);
        graphics.setColor(color);
        if (FlatMenuItemRenderer.isTopLevelMenu(this.menuItem)) {
            graphics.fillRect((int)2132491836L ^ 0x7F1B3E3C, n4 - n5, n3, n5);
        } else if (this.menuItem.getComponentOrientation().isLeftToRight()) {
            graphics.fillRect((int)-956273772L ^ 0xC7006B94, (int)-1363237159L ^ 0xAEBEA6D9, n5, n4);
        } else {
            graphics.fillRect(n3 - n5, (int)((long)644857829 ^ (long)644857829), n5, n4);
        }
    }

    protected Color deriveBackground(Color color) {
        if (!(color instanceof DerivedColor)) {
            return color;
        }
        Color color2 = this.menuItem.isOpaque() ? this.menuItem.getBackground() : FlatUIUtils.getParentBackground(this.menuItem);
        return FlatUIUtils.deriveColor(color, color2);
    }

    protected void paintIcon(Graphics graphics, Rectangle rectangle, Icon icon, Color color) {
        if (this.menuItem.isSelected() && this.checkIcon != null && icon != this.checkIcon) {
            Rectangle rectangle2 = FlatUIUtils.addInsets(rectangle, UIScale.scale(this.checkMargins));
            graphics.setColor(FlatUIUtils.deriveColor(color, this.selectionBackground));
            graphics.fillRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
        }
        FlatMenuItemRenderer.paintIcon(graphics, this.menuItem, icon, rectangle);
    }

    protected void paintText(Graphics graphics, Rectangle rectangle, String string, Color color, Color color2) {
        View view = (View)this.menuItem.getClientProperty("html");
        if (view != null) {
            FlatMenuItemRenderer.paintHTMLText(graphics, this.menuItem, rectangle, view, this.isUnderlineSelection() ? null : color);
            return;
        }
        int n2 = FlatLaf.isShowMnemonics() ? this.menuItem.getDisplayedMnemonicIndex() : (int)1004557029L ^ 0xC41FAD1A;
        Color color3 = (FlatMenuItemRenderer.isTopLevelMenu(this.menuItem) ? this.menuItem.getParent() : this.menuItem).getForeground();
        FlatMenuItemRenderer.paintText(graphics, this.menuItem, rectangle, string, n2, this.menuItem.getFont(), color3, this.isUnderlineSelection() ? color3 : color, color2);
    }

    protected void paintAccelerator(Graphics graphics, Rectangle rectangle, String string, Color color, Color color2, Color color3) {
        FlatMenuItemRenderer.paintText(graphics, this.menuItem, rectangle, string, (int)234176861L ^ 0xF20ABEA2, this.acceleratorFont, color, this.isUnderlineSelection() ? color : color2, color3);
    }

    protected void paintArrowIcon(Graphics graphics, Rectangle rectangle, Icon icon) {
        FlatMenuItemRenderer.paintIcon(graphics, this.menuItem, icon, rectangle);
    }

    protected static void paintIcon(Graphics graphics, JMenuItem jMenuItem, Icon icon, Rectangle rectangle) {
        if (icon == null) {
            return;
        }
        int n2 = rectangle.x + FlatMenuItemRenderer.centerOffset(rectangle.width, icon.getIconWidth());
        int n3 = rectangle.y + FlatMenuItemRenderer.centerOffset(rectangle.height, icon.getIconHeight());
        icon.paintIcon(jMenuItem, graphics, n2, n3);
    }

    protected static void paintText(Graphics graphics, JMenuItem jMenuItem, Rectangle rectangle, String string, int n2, Font font, Color color, Color color2, Color color3) {
        if (string == null || string.isEmpty()) {
            return;
        }
        FontMetrics fontMetrics = jMenuItem.getFontMetrics(font);
        Font font2 = graphics.getFont();
        graphics.setFont(font);
        graphics.setColor(!jMenuItem.isEnabled() ? color3 : (FlatMenuItemRenderer.isArmedOrSelected(jMenuItem) ? color2 : color));
        FlatUIUtils.drawStringUnderlineCharAt(jMenuItem, graphics, string, n2, rectangle.x, rectangle.y + fontMetrics.getAscent());
        graphics.setFont(font2);
    }

    protected static void paintHTMLText(Graphics graphics, JMenuItem jMenuItem, Rectangle rectangle, View view, Color color) {
        if (FlatMenuItemRenderer.isArmedOrSelected(jMenuItem) && color != null) {
            graphics = new GraphicsProxyWithTextColor((Graphics2D)graphics, color);
        }
        view.paint(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)graphics), rectangle);
    }

    protected static boolean isArmedOrSelected(JMenuItem jMenuItem) {
        return (jMenuItem.isArmed() || jMenuItem instanceof JMenu && jMenuItem.isSelected() ? (int)((long)1098293850 ^ (long)1098293851) : (int)1614780508L ^ 0x603F985C) != 0;
    }

    protected static boolean isTopLevelMenu(JMenuItem jMenuItem) {
        return (jMenuItem instanceof JMenu && ((JMenu)jMenuItem).isTopLevelMenu() ? (int)-314090814L ^ 0xED475AC3 : (int)((long)1031331369 ^ (long)1031331369)) != 0;
    }

    protected boolean isUnderlineSelection() {
        return "underline".equals(UIManager.getString("MenuItem.selectionType"));
    }

    private Icon getIconForPainting() {
        Icon icon;
        Icon icon2 = this.menuItem.getIcon();
        if (icon2 == null && this.checkIcon != null && !FlatMenuItemRenderer.isTopLevelMenu(this.menuItem)) {
            return this.checkIcon;
        }
        if (icon2 == null) {
            return null;
        }
        if (!this.menuItem.isEnabled()) {
            return this.menuItem.getDisabledIcon();
        }
        if (this.menuItem.getModel().isPressed() && this.menuItem.isArmed() && (icon = this.menuItem.getPressedIcon()) != null) {
            return icon;
        }
        return icon2;
    }

    private Icon getIconForLayout() {
        Icon icon = this.menuItem.getIcon();
        if (FlatMenuItemRenderer.isTopLevelMenu(this.menuItem)) {
            return icon != null ? new MinSizeIcon(icon) : null;
        }
        return new MinSizeIcon(icon != null ? icon : this.checkIcon);
    }

    private String getAcceleratorText() {
        KeyStroke keyStroke = this.menuItem.getAccelerator();
        if (keyStroke == null) {
            return null;
        }
        boolean bl = this.menuItem.getComponentOrientation().isLeftToRight();
        if (keyStroke == this.cachedAccelerator && bl == this.cachedAcceleratorLeftToRight) {
            return this.cachedAcceleratorText;
        }
        this.cachedAccelerator = keyStroke;
        this.cachedAcceleratorText = this.getTextForAccelerator(keyStroke);
        this.cachedAcceleratorLeftToRight = bl;
        return this.cachedAcceleratorText;
    }

    protected String getTextForAccelerator(KeyStroke keyStroke) {
        int n2;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl = this.menuItem.getComponentOrientation().isLeftToRight();
        int n3 = keyStroke.getModifiers();
        if (n3 != 0) {
            if (SystemInfo.isMacOS) {
                if (bl) {
                    stringBuilder.append(this.getMacOSModifiersExText(n3, bl));
                }
            } else {
                stringBuilder.append(InputEvent.getModifiersExText(n3)).append(this.acceleratorDelimiter);
            }
        }
        if ((n2 = keyStroke.getKeyCode()) != 0) {
            stringBuilder.append(KeyEvent.getKeyText(n2));
        } else {
            stringBuilder.append(keyStroke.getKeyChar());
        }
        if (n3 != 0 && !bl && SystemInfo.isMacOS) {
            stringBuilder.append(this.getMacOSModifiersExText(n3, bl));
        }
        return stringBuilder.toString();
    }

    protected String getMacOSModifiersExText(int n2, boolean bl) {
        StringBuilder stringBuilder = new StringBuilder();
        if ((n2 & (int)((long)293349973 ^ (long)293349972) << 7) != 0) {
            stringBuilder.append((char)((long)-1095472391 ^ (long)-1095480838));
        }
        if ((n2 & (int)((long)599007741 ^ (long)599007724) << 9) != 0) {
            stringBuilder.append((char)((long)-1156059668 ^ (long)-1156067639));
        }
        if ((n2 & ((int)-1552123211L ^ 0xA37C7AB4) << 6) != 0) {
            stringBuilder.append((char)((int)24670299L ^ 0x17851BC));
        }
        if ((n2 & (int)((long)1838408370 ^ (long)1838408371) << 8) != 0) {
            stringBuilder.append(((int)1473147704L ^ 0x57CE775B) << 3);
        }
        if (!bl) {
            stringBuilder.reverse();
        }
        return stringBuilder.toString();
    }

    private static class GraphicsProxyWithTextColor
    extends Graphics2DProxy {
        private final Color textColor;

        GraphicsProxyWithTextColor(Graphics2D graphics2D, Color color) {
            super(graphics2D);
            this.textColor = color;
        }

        @Override
        public void drawString(String string, int n2, int n3) {
            Paint paint = this.getPaint();
            this.setPaint(this.textColor);
            super.drawString(string, n2, n3);
            this.setPaint(paint);
        }

        @Override
        public void drawString(String string, float f2, float f3) {
            Paint paint = this.getPaint();
            this.setPaint(this.textColor);
            super.drawString(string, f2, f3);
            this.setPaint(paint);
        }

        @Override
        public void drawString(AttributedCharacterIterator attributedCharacterIterator, int n2, int n3) {
            Paint paint = this.getPaint();
            this.setPaint(this.textColor);
            super.drawString(attributedCharacterIterator, n2, n3);
            this.setPaint(paint);
        }

        @Override
        public void drawString(AttributedCharacterIterator attributedCharacterIterator, float f2, float f3) {
            Paint paint = this.getPaint();
            this.setPaint(this.textColor);
            super.drawString(attributedCharacterIterator, f2, f3);
            this.setPaint(paint);
        }

        @Override
        public void drawChars(char[] arrc, int n2, int n3, int n4, int n5) {
            Paint paint = this.getPaint();
            this.setPaint(this.textColor);
            super.drawChars(arrc, n2, n3, n4, n5);
            this.setPaint(paint);
        }
    }

    private class MinSizeIcon
    implements Icon {
        private final Icon delegate;

        MinSizeIcon(Icon icon) {
            this.delegate = icon;
        }

        @Override
        public int getIconWidth() {
            int n2 = this.delegate != null ? this.delegate.getIconWidth() : (int)((long)-642555128 ^ (long)-642555128);
            return Math.max(n2, UIScale.scale(FlatMenuItemRenderer.this.minimumIconSize.width));
        }

        @Override
        public int getIconHeight() {
            int n2 = this.delegate != null ? this.delegate.getIconHeight() : (int)163546786L ^ 0x9BF86A2;
            return Math.max(n2, UIScale.scale(FlatMenuItemRenderer.this.minimumIconSize.height));
        }

        @Override
        public void paintIcon(Component component, Graphics graphics, int n2, int n3) {
        }
    }
}

