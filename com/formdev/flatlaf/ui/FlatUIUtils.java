/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.ui.FlatBorder;
import com.formdev.flatlaf.util.DerivedColor;
import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.IdentityHashMap;
import java.util.WeakHashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;

public class FlatUIUtils {
    public static final boolean MAC_USE_QUARTZ = Boolean.getBoolean("apple.awt.graphics.UseQuartz");
    private static WeakHashMap<LookAndFeel, IdentityHashMap<Object, ComponentUI>> sharedUIinstances = new WeakHashMap();

    public static Rectangle addInsets(Rectangle rectangle, Insets insets) {
        return new Rectangle(rectangle.x - insets.left, rectangle.y - insets.top, rectangle.width + insets.left + insets.right, rectangle.height + insets.top + insets.bottom);
    }

    public static Rectangle subtractInsets(Rectangle rectangle, Insets insets) {
        return new Rectangle(rectangle.x + insets.left, rectangle.y + insets.top, rectangle.width - insets.left - insets.right, rectangle.height - insets.top - insets.bottom);
    }

    public static Dimension addInsets(Dimension dimension, Insets insets) {
        return new Dimension(dimension.width + insets.left + insets.right, dimension.height + insets.top + insets.bottom);
    }

    public static Insets addInsets(Insets insets, Insets insets2) {
        return new Insets(insets.top + insets2.top, insets.left + insets2.left, insets.bottom + insets2.bottom, insets.right + insets2.right);
    }

    public static void setInsets(Insets insets, Insets insets2) {
        insets.top = insets2.top;
        insets.left = insets2.left;
        insets.bottom = insets2.bottom;
        insets.right = insets2.right;
    }

    public static Color getUIColor(String string, int n2) {
        Color color = UIManager.getColor(string);
        return color != null ? color : new Color(n2);
    }

    public static Color getUIColor(String string, Color color) {
        Color color2 = UIManager.getColor(string);
        return color2 != null ? color2 : color;
    }

    public static Color getUIColor(String string, String string2) {
        Color color = UIManager.getColor(string);
        return color != null ? color : UIManager.getColor(string2);
    }

    public static boolean getUIBoolean(String string, boolean bl) {
        Object object = UIManager.get(string);
        return object instanceof Boolean ? (Boolean)object : bl;
    }

    public static int getUIInt(String string, int n2) {
        Object object = UIManager.get(string);
        return object instanceof Integer ? (Integer)object : n2;
    }

    public static float getUIFloat(String string, float f2) {
        Object object = UIManager.get(string);
        return object instanceof Number ? ((Number)object).floatValue() : f2;
    }

    public static boolean getBoolean(JComponent jComponent, String string, String string2, String string3, boolean bl) {
        Boolean bl2 = FlatSystemProperties.getBooleanStrict(string, null);
        if (bl2 != null) {
            return bl2;
        }
        bl2 = FlatClientProperties.clientPropertyBooleanStrict(jComponent, string2, null);
        if (bl2 != null) {
            return bl2;
        }
        return FlatUIUtils.getUIBoolean(string3, bl);
    }

    public static boolean isChevron(String string) {
        return (!"triangle".equals(string) ? (int)-393747157L ^ 0xE887E52A : (int)-1000735890L ^ 0xC459FB6E) != 0;
    }

    public static Color nonUIResource(Color color) {
        return color instanceof UIResource ? new Color(color.getRGB(), ((int)329901603L ^ 0x13A9E622) != 0) : color;
    }

    public static Font nonUIResource(Font font) {
        return font instanceof UIResource ? font.deriveFont(font.getStyle()) : font;
    }

    public static int minimumWidth(JComponent jComponent, int n2) {
        return FlatClientProperties.clientPropertyInt(jComponent, "JComponent.minimumWidth", n2);
    }

    public static int minimumHeight(JComponent jComponent, int n2) {
        return FlatClientProperties.clientPropertyInt(jComponent, "JComponent.minimumHeight", n2);
    }

    public static boolean isCellEditor(Component component) {
        Component component2 = component;
        for (int i2 = (int)-6415195L ^ 0xFF9E1CA5; i2 <= (int)((long)-1172066632 ^ (long)-1172066631) << 1 && component2 != null; ++i2) {
            Container container = component2.getParent();
            if (container instanceof JTable && ((JTable)container).getEditorComponent() == component2) {
                return (int)((long)1379884004 ^ (long)1379884005) != 0;
            }
            component2 = container;
        }
        String string = component.getName();
        if ("Table.editor".equals(string) || "Tree.cellEditor".equals(string)) {
            return (int)((long)-1340554580 ^ (long)-1340554579) != 0;
        }
        return (component instanceof JComponent && Boolean.TRUE.equals(((JComponent)component).getClientProperty("JComboBox.isTableCellEditor")) ? (int)((long)1537592488 ^ (long)1537592489) : (int)((long)1203566091 ^ (long)1203566091)) != 0;
    }

    public static boolean isPermanentFocusOwner(Component component) {
        Object object;
        KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        if (component instanceof JComponent && (object = ((JComponent)component).getClientProperty("JComponent.focusOwner")) instanceof Predicate) {
            return (((Predicate)object).test((JComponent)component) && FlatUIUtils.isInActiveWindow(component, keyboardFocusManager.getActiveWindow()) ? (int)((long)813225660 ^ (long)813225661) : (int)1050129277L ^ 0x3E97B37D) != 0;
        }
        return (keyboardFocusManager.getPermanentFocusOwner() == component && FlatUIUtils.isInActiveWindow(component, keyboardFocusManager.getActiveWindow()) ? (int)-1757922496L ^ 0x97383B41 : (int)((long)-1909511249 ^ (long)-1909511249)) != 0;
    }

    private static boolean isInActiveWindow(Component component, Window window) {
        Window window2 = SwingUtilities.windowForComponent(component);
        return (window2 == window || window2 != null && window2.getType() == Window.Type.POPUP && window2.getOwner() == window ? (int)((long)-1453088107 ^ (long)-1453088108) : (int)-1518460817L ^ 0xA57E206F) != 0;
    }

    public static boolean isFullScreen(Component component) {
        GraphicsConfiguration graphicsConfiguration = component.getGraphicsConfiguration();
        GraphicsDevice graphicsDevice = graphicsConfiguration != null ? graphicsConfiguration.getDevice() : null;
        Window window = graphicsDevice != null ? graphicsDevice.getFullScreenWindow() : null;
        return (window != null && window == SwingUtilities.windowForComponent(component) ? (int)-333752732L ^ 0xEC1B5665 : (int)((long)-465585475 ^ (long)-465585475)) != 0;
    }

    public static Boolean isRoundRect(Component component) {
        return component instanceof JComponent ? FlatClientProperties.clientPropertyBooleanStrict((JComponent)component, "JComponent.roundRect", null) : null;
    }

    public static float getBorderFocusWidth(JComponent jComponent) {
        FlatBorder flatBorder = FlatUIUtils.getOutsideFlatBorder(jComponent);
        return flatBorder != null ? UIScale.scale((float)flatBorder.getFocusWidth(jComponent)) : 0.0f;
    }

    public static float getBorderArc(JComponent jComponent) {
        FlatBorder flatBorder = FlatUIUtils.getOutsideFlatBorder(jComponent);
        return flatBorder != null ? UIScale.scale((float)flatBorder.getArc(jComponent)) : 0.0f;
    }

    public static boolean hasRoundBorder(JComponent jComponent) {
        return (FlatUIUtils.getBorderArc(jComponent) >= (float)jComponent.getHeight() ? (int)((long)-394253560 ^ (long)-394253559) : (int)-1843624939L ^ 0x921C8415) != 0;
    }

    public static FlatBorder getOutsideFlatBorder(JComponent jComponent) {
        Border border = jComponent.getBorder();
        while (true) {
            if (border instanceof FlatBorder) {
                return (FlatBorder)border;
            }
            if (!(border instanceof CompoundBorder)) break;
            border = ((CompoundBorder)border).getOutsideBorder();
        }
        return null;
    }

    public static Object[] setRenderingHints(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D)graphics;
        Object[] arrobject = new Object[(int)((long)-1171716442 ^ (long)-1171716441) << 1];
        arrobject[(int)1958446947L ^ 1958446947] = graphics2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        arrobject[(int)968294963L ^ 968294962] = graphics2D.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL);
        Object[] arrobject2 = arrobject;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, MAC_USE_QUARTZ ? RenderingHints.VALUE_STROKE_PURE : RenderingHints.VALUE_STROKE_NORMALIZE);
        return arrobject2;
    }

    public static void resetRenderingHints(Graphics graphics, Object[] arrobject) {
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, arrobject[(int)-1103491707L ^ 0xBE3A0D85]);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, arrobject[(int)((long)1853049973 ^ (long)1853049972)]);
    }

    public static void runWithoutRenderingHints(Graphics graphics, Object[] arrobject, Runnable runnable) {
        if (arrobject == null) {
            runnable.run();
            return;
        }
        Graphics2D graphics2D = (Graphics2D)graphics;
        Object[] arrobject2 = new Object[(int)((long)-1234458891 ^ (long)-1234458892) << 1];
        arrobject2[(int)423125620L ^ 423125620] = graphics2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        arrobject2[(int)-1525224222L ^ -1525224221] = graphics2D.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL);
        Object[] arrobject3 = arrobject2;
        FlatUIUtils.resetRenderingHints(graphics2D, arrobject);
        runnable.run();
        FlatUIUtils.resetRenderingHints(graphics2D, arrobject3);
    }

    public static Color deriveColor(Color color, Color color2) {
        return color instanceof DerivedColor ? ((DerivedColor)color).derive(color2) : color;
    }

    public static void paintComponentOuterBorder(Graphics2D graphics2D2, int n6, int n7, int n8, int n9, float f2, float f3, float f4) {
        if (f2 + f3 == 0.0f) {
            return;
        }
        double d3 = UIScale.getSystemScaleFactor(graphics2D2);
        if (d3 != 1.0 && d3 != Double.longBitsToDouble((long)1780787287 ^ 0x400000006A24A857L)) {
            HiDPIUtils.paintAtScale1x(graphics2D2, n6, n7, n8, n9, (graphics2D, n2, n3, n4, n5, d2) -> FlatUIUtils.paintComponentOuterBorderImpl(graphics2D, n2, n3, n4, n5, (float)((double)f2 * d2), (float)((double)f3 * d2), (float)((double)f4 * d2)));
            return;
        }
        FlatUIUtils.paintComponentOuterBorderImpl(graphics2D2, n6, n7, n8, n9, f2, f3, f4);
    }

    private static void paintComponentOuterBorderImpl(Graphics2D graphics2D, int n2, int n3, int n4, int n5, float f2, float f3, float f4) {
        float f5 = f2 + f3;
        float f6 = f4 + f2 * 2.0f;
        float f7 = f4 - f3 * 2.0f;
        if (f2 > 0.0f && f4 > 0.0f && f4 < (float)UIScale.scale(((int)928712584L ^ 0x375B078D) << 1)) {
            f6 -= UIScale.scale(2.0f);
        }
        Path2D.Float float_ = new Path2D.Float((int)-1361123780L ^ 0xAEDEE63C);
        float_.append(FlatUIUtils.createComponentRectangle(n2, n3, n4, n5, f6), ((int)-712467525L ^ 0xD5889BBB) != 0);
        float_.append(FlatUIUtils.createComponentRectangle((float)n2 + f5, (float)n3 + f5, (float)n4 - f5 * 2.0f, (float)n5 - f5 * 2.0f, f7), ((int)-1869549890L ^ 0x9090EEBE) != 0);
        graphics2D.fill(float_);
    }

    public static void paintComponentBorder(Graphics2D graphics2D2, int n6, int n7, int n8, int n9, float f2, float f3, float f4) {
        if (f3 == 0.0f) {
            return;
        }
        double d3 = UIScale.getSystemScaleFactor(graphics2D2);
        if (d3 != 1.0 && d3 != Double.longBitsToDouble(0x5969B6DD5DB320B5L ^ 0x1969B6DD5DB320B5L)) {
            HiDPIUtils.paintAtScale1x(graphics2D2, n6, n7, n8, n9, (graphics2D, n2, n3, n4, n5, d2) -> FlatUIUtils.paintComponentBorderImpl(graphics2D, n2, n3, n4, n5, (float)((double)f2 * d2), (float)((double)f3 * d2), (float)((double)f4 * d2)));
            return;
        }
        FlatUIUtils.paintComponentBorderImpl(graphics2D2, n6, n7, n8, n9, f2, f3, f4);
    }

    private static void paintComponentBorderImpl(Graphics2D graphics2D, int n2, int n3, int n4, int n5, float f2, float f3, float f4) {
        float f5 = (float)n2 + f2;
        float f6 = (float)n3 + f2;
        float f7 = (float)n4 - f2 * 2.0f;
        float f8 = (float)n5 - f2 * 2.0f;
        float f9 = f4 - f3 * 2.0f;
        Shape shape = FlatUIUtils.createComponentRectangle(f5, f6, f7, f8, f4);
        Shape shape2 = FlatUIUtils.createComponentRectangle(f5 + f3, f6 + f3, f7 - f3 * 2.0f, f8 - f3 * 2.0f, f9);
        Path2D.Float float_ = new Path2D.Float((int)((long)-802056402 ^ (long)-802056402));
        float_.append(shape, (boolean)((long)440805408 ^ (long)440805408));
        float_.append(shape2, (boolean)((long)-1868975260 ^ (long)-1868975260));
        graphics2D.fill(float_);
    }

    public static void paintComponentBackground(Graphics2D graphics2D2, int n6, int n7, int n8, int n9, float f2, float f3) {
        double d3 = UIScale.getSystemScaleFactor(graphics2D2);
        if (d3 != 1.0 && d3 != Double.longBitsToDouble((long)83442305 ^ 0x4000000004F93A81L)) {
            HiDPIUtils.paintAtScale1x(graphics2D2, n6, n7, n8, n9, (graphics2D, n2, n3, n4, n5, d2) -> FlatUIUtils.paintComponentBackgroundImpl(graphics2D, n2, n3, n4, n5, (float)((double)f2 * d2), (float)((double)f3 * d2)));
            return;
        }
        FlatUIUtils.paintComponentBackgroundImpl(graphics2D2, n6, n7, n8, n9, f2, f3);
    }

    private static void paintComponentBackgroundImpl(Graphics2D graphics2D, int n2, int n3, int n4, int n5, float f2, float f3) {
        graphics2D.fill(FlatUIUtils.createComponentRectangle((float)n2 + f2, (float)n3 + f2, (float)n4 - f2 * 2.0f, (float)n5 - f2 * 2.0f, f3));
    }

    public static Shape createComponentRectangle(float f2, float f3, float f4, float f5, float f6) {
        if (f6 <= 0.0f) {
            return new Rectangle2D.Float(f2, f3, f4, f5);
        }
        f6 = Math.min(f6, Math.min(f4, f5));
        return new RoundRectangle2D.Float(f2, f3, f4, f5, f6, f6);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void paintFilledRectangle(Graphics graphics, Color color, float f2, float f3, float f4, float f5) {
        Graphics2D graphics2D = (Graphics2D)graphics.create();
        try {
            FlatUIUtils.setRenderingHints(graphics2D);
            graphics2D.setColor(color);
            graphics2D.fill(new Rectangle2D.Float(f2, f3, f4, f5));
        }
        finally {
            graphics2D.dispose();
        }
    }

    public static void paintGrip(Graphics graphics, int n2, int n3, int n4, int n5, boolean bl, int n6, int n7, int n8, boolean bl2) {
        float f2;
        float f3;
        n7 = UIScale.scale(n7);
        n8 = UIScale.scale(n8);
        int n9 = n7 * n6 + n8 * (n6 - ((int)-1875695972L ^ 0x9033269D));
        if (bl) {
            f3 = n2 + Math.round((float)(n4 - n9) / 2.0f);
            f2 = (float)n3 + (float)(n5 - n7) / 2.0f;
            if (!bl2) {
                f2 = Math.round(f2);
            }
        } else {
            f3 = (float)n2 + (float)(n4 - n7) / 2.0f;
            f2 = n3 + Math.round((float)(n5 - n9) / 2.0f);
            if (!bl2) {
                f3 = Math.round(f3);
            }
        }
        for (int i2 = (int)1061601465L ^ 0x3F46C0B9; i2 < n6; ++i2) {
            ((Graphics2D)graphics).fill(new Ellipse2D.Float(f3, f2, n7, n7));
            if (bl) {
                f3 += (float)(n7 + n8);
                continue;
            }
            f2 += (float)(n7 + n8);
        }
    }

    public static void paintParentBackground(Graphics graphics, JComponent jComponent) {
        Container container = FlatUIUtils.findOpaqueParent(jComponent);
        if (container != null) {
            graphics.setColor(container.getBackground());
            graphics.fillRect((int)((long)1818061655 ^ (long)1818061655), (int)((long)887865893 ^ (long)887865893), jComponent.getWidth(), jComponent.getHeight());
        }
    }

    public static Color getParentBackground(JComponent jComponent) {
        Container container = FlatUIUtils.findOpaqueParent(jComponent);
        return container != null ? container.getBackground() : UIManager.getColor("Panel.background");
    }

    private static Container findOpaqueParent(Container container) {
        while ((container = container.getParent()) != null) {
            if (!container.isOpaque()) continue;
            return container;
        }
        return null;
    }

    public static Path2D createRectangle(float f2, float f3, float f4, float f5, float f6) {
        Path2D.Float float_ = new Path2D.Float((int)83464385L ^ 0x4F990C1);
        float_.append(new Rectangle2D.Float(f2, f3, f4, f5), ((int)520343403L ^ 0x1F03CF6B) != 0);
        float_.append(new Rectangle2D.Float(f2 + f6, f3 + f6, f4 - f6 * 2.0f, f5 - f6 * 2.0f), (boolean)((long)-1234254403 ^ (long)-1234254403));
        return float_;
    }

    public static Path2D createRoundRectangle(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        Path2D.Float float_ = new Path2D.Float((int)((long)69618616 ^ (long)69618616));
        float_.append(FlatUIUtils.createRoundRectanglePath(f2, f3, f4, f5, f7, f8, f9, f10), (boolean)((long)1352747708 ^ (long)1352747708));
        float_.append(FlatUIUtils.createRoundRectanglePath(f2 + f6, f3 + f6, f4 - f6 * 2.0f, f5 - f6 * 2.0f, f7 - f6, f8 - f6, f9 - f6, f10 - f6), (boolean)((long)-1197708014 ^ (long)-1197708014));
        return float_;
    }

    public static Shape createRoundRectanglePath(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (f6 <= 0.0f && f7 <= 0.0f && f8 <= 0.0f && f9 <= 0.0f) {
            return new Rectangle2D.Float(f2, f3, f4, f5);
        }
        float f10 = Math.min(f4, f5) / 2.0f;
        f6 = f6 > 0.0f ? Math.min(f6, f10) : 0.0f;
        f7 = f7 > 0.0f ? Math.min(f7, f10) : 0.0f;
        f8 = f8 > 0.0f ? Math.min(f8, f10) : 0.0f;
        f9 = f9 > 0.0f ? Math.min(f9, f10) : 0.0f;
        float f11 = f2 + f4;
        float f12 = f3 + f5;
        double d2 = Double.longBitsToDouble((long)77094949 ^ 0x3FE1AC5115CB2A04L);
        double d3 = 1.0 - d2;
        double d4 = (double)f6 * d3;
        double d5 = (double)f7 * d3;
        double d6 = (double)f8 * d3;
        double d7 = (double)f9 * d3;
        Path2D.Float float_ = new Path2D.Float();
        ((Path2D)float_).moveTo(f11 - f7, f3);
        ((Path2D)float_).curveTo((double)f11 - d5, f3, f11, (double)f3 + d5, f11, f3 + f7);
        ((Path2D)float_).lineTo(f11, f12 - f9);
        ((Path2D)float_).curveTo(f11, (double)f12 - d7, (double)f11 - d7, f12, f11 - f9, f12);
        ((Path2D)float_).lineTo(f2 + f8, f12);
        ((Path2D)float_).curveTo((double)f2 + d6, f12, f2, (double)f12 - d6, f2, f12 - f8);
        ((Path2D)float_).lineTo(f2, f3 + f6);
        ((Path2D)float_).curveTo(f2, (double)f3 + d4, (double)f2 + d4, f3, f2 + f6, f3);
        float_.closePath();
        return float_;
    }

    public static void paintArrow(Graphics2D graphics2D, int n2, int n3, int n4, int n5, int n6, boolean bl, int n7, int n8, int n9) {
        int n10;
        int n11;
        int n12 = UIScale.scale(n7 + (bl ? (int)-849721376L ^ 0xCD5A47E0 : (int)((long)1508961176 ^ (long)1508961177)));
        int n13 = UIScale.scale(n7 / (((int)906928601L ^ 0x360EA1D8) << 1) + (bl ? (int)((long)-1780112017 ^ (long)-1780112017) : (int)((long)1526450140 ^ (long)1526450141)));
        int n14 = n11 = n6 == (int)((long)-1027877150 ^ (long)-1027877149) || n6 == (int)((long)556661831 ^ (long)556661826) ? (int)247331642L ^ 0xEBDFB3B : (int)((long)-254785210 ^ (long)-254785210);
        if (n11 == 0) {
            n10 = n12;
            n12 = n13;
            n13 = n10;
        }
        n10 = bl ? (int)-1160802886L ^ 0xBACF8DBB : (int)929523028L ^ 0x37676554;
        int n15 = n2 + Math.round((float)(n4 - (n12 + n10)) / 2.0f + UIScale.scale((float)n8));
        int n16 = n3 + Math.round((float)(n5 - (n13 + n10)) / 2.0f + UIScale.scale((float)n9));
        graphics2D.translate(n15, n16);
        Shape shape = FlatUIUtils.createArrowShape(n6, bl, n12, n13);
        if (bl) {
            Stroke stroke = graphics2D.getStroke();
            graphics2D.setStroke(new BasicStroke(UIScale.scale(1.0f)));
            graphics2D.draw(shape);
            graphics2D.setStroke(stroke);
        } else {
            graphics2D.fill(shape);
        }
        graphics2D.translate(-n15, -n16);
    }

    public static Shape createArrowShape(int n2, boolean bl, float f2, float f3) {
        switch (n2) {
            case 1: {
                double[] arrd = new double[((int)-284875561L ^ 0xEF0524D4) << 1];
                arrd[(int)((long)-156983915 ^ (long)-156983915)] = 0.0;
                arrd[(int)-231955738L ^ -231955737] = f3;
                arrd[(int)((long)-620550811 ^ (long)-620550812) << 1] = f2 / 2.0f;
                arrd[(int)((long)121671317 ^ (long)121671318)] = 0.0;
                arrd[(int)((long)1432102942 ^ (long)1432102943) << 2] = f2;
                arrd[(int)((long)1307652871 ^ (long)1307652866)] = f3;
                return FlatUIUtils.createPath((!bl ? (int)-1713064030L ^ 0x99E4B7A3 : (int)571591314L ^ 0x2211CA92) != 0, arrd);
            }
            case 5: {
                double[] arrd = new double[((int)940386359L ^ 0x380D2834) << 1];
                arrd[(int)((long)1672426347 ^ (long)1672426347)] = 0.0;
                arrd[(int)-441682428L ^ -441682427] = 0.0;
                arrd[(int)((long)-595522831 ^ (long)-595522832) << 1] = f2 / 2.0f;
                arrd[(int)((long)-978230319 ^ (long)-978230318)] = f3;
                arrd[(int)((long)1856038357 ^ (long)1856038356) << 2] = f2;
                arrd[(int)((long)-917772455 ^ (long)-917772452)] = 0.0;
                return FlatUIUtils.createPath((!bl ? (int)-150361173L ^ 0xF709ABAA : (int)((long)1543517913 ^ (long)1543517913)) != 0, arrd);
            }
            case 7: {
                double[] arrd = new double[(int)((long)699412847 ^ (long)699412844) << 1];
                arrd[(int)((long)-2071450653 ^ (long)-2071450653)] = f2;
                arrd[(int)((long)1802602092 ^ (long)1802602093)] = 0.0;
                arrd[(int)((long)393831501 ^ (long)393831500) << 1] = 0.0;
                arrd[(int)((long)-1042532271 ^ (long)-1042532270)] = f3 / 2.0f;
                arrd[(int)((long)-460015106 ^ (long)-460015105) << 2] = f2;
                arrd[(int)1657486000L ^ 1657486005] = f3;
                return FlatUIUtils.createPath((!bl ? (int)((long)1437436977 ^ (long)1437436976) : (int)-989227651L ^ 0xC509957D) != 0, arrd);
            }
            case 3: {
                double[] arrd = new double[((int)-1490579206L ^ 0xA72790F9) << 1];
                arrd[(int)-1885124638L ^ -1885124638] = 0.0;
                arrd[(int)((long)-1945838493 ^ (long)-1945838494)] = 0.0;
                arrd[(int)((long)-331939256 ^ (long)-331939255) << 1] = f2;
                arrd[(int)((long)1192892276 ^ (long)1192892279)] = f3 / 2.0f;
                arrd[((int)-1920384501L ^ -1920384502) << 2] = 0.0;
                arrd[(int)((long)-522706431 ^ (long)-522706428)] = f3;
                return FlatUIUtils.createPath((!bl ? (int)((long)-194476501 ^ (long)-194476502) : (int)((long)47214147 ^ (long)47214147)) != 0, arrd);
            }
        }
        return new Path2D.Float();
    }

    public static Path2D createPath(double ... arrd) {
        return FlatUIUtils.createPath((boolean)((long)-1819872182 ^ (long)-1819872181), arrd);
    }

    public static Path2D createPath(boolean bl, double ... arrd) {
        Path2D.Float float_ = new Path2D.Float();
        ((Path2D)float_).moveTo(arrd[(int)((long)1004445012 ^ (long)1004445012)], arrd[(int)((long)-2141717815 ^ (long)-2141717816)]);
        for (int i2 = ((int)766326168L ^ 0x2DAD3599) << 1; i2 < arrd.length; i2 += 2) {
            ((Path2D)float_).lineTo(arrd[i2], arrd[i2 + (int)((long)305419787 ^ (long)305419786)]);
        }
        if (bl) {
            float_.closePath();
        }
        return float_;
    }

    public static void drawString(JComponent jComponent, Graphics graphics, String string, int n2, int n3) {
        HiDPIUtils.drawStringWithYCorrection(jComponent, (Graphics2D)graphics, string, n2, n3);
    }

    public static void drawStringUnderlineCharAt(JComponent jComponent, Graphics graphics, String string, int n2, int n3, int n4) {
        if (n2 >= 0 && UIScale.getUserScaleFactor() > 1.0f) {
            graphics = new Graphics2DProxy((Graphics2D)graphics){

                @Override
                public void fillRect(int n2, int n3, int n4, int n5) {
                    if (n5 == (int)((long)-723510032 ^ (long)-723510031)) {
                        n5 = Math.round(UIScale.scale(Float.intBitsToFloat((int)((long)1007525448 ^ (long)57407534))));
                        n3 += n5 - ((int)-1192334425L ^ 0xB8EE6BA6);
                    }
                    super.fillRect(n2, n3, n4, n5);
                }
            };
        }
        HiDPIUtils.drawStringUnderlineCharAtWithYCorrection(jComponent, (Graphics2D)graphics, string, n2, n3, n4);
    }

    public static boolean hasOpaqueBeenExplicitlySet(JComponent jComponent) {
        boolean bl = jComponent.isOpaque();
        LookAndFeel.installProperty(jComponent, "opaque", (!bl ? (int)-1913900665L ^ 0x8DEC3186 : (int)318259890L ^ 0x12F842B2) != 0);
        int n2 = jComponent.isOpaque() == bl ? (int)922452483L ^ 0x36FB8202 : (int)17376498L ^ 0x10924F2;
        LookAndFeel.installProperty(jComponent, "opaque", bl);
        return n2 != 0;
    }

    public static ComponentUI createSharedUI(Object object2, Supplier<ComponentUI> supplier) {
        return sharedUIinstances.computeIfAbsent(UIManager.getLookAndFeel(), lookAndFeel -> new IdentityHashMap()).computeIfAbsent(object2, object -> (ComponentUI)supplier.get());
    }

    public static class RepaintFocusListener
    implements FocusListener {
        private final Component repaintComponent;

        public RepaintFocusListener(Component component) {
            this.repaintComponent = component;
        }

        @Override
        public void focusGained(FocusEvent focusEvent) {
            this.repaintComponent.repaint();
        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            this.repaintComponent.repaint();
        }
    }
}

