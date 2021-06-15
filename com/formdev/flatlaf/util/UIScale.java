/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Method;
import javax.swing.UIManager;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.UIResource;

public class UIScale {
    private static final boolean DEBUG = false;
    private static PropertyChangeSupport changeSupport;
    private static Boolean jreHiDPI;
    private static float scaleFactor;
    private static boolean initialized;

    public static void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        if (changeSupport == null) {
            changeSupport = new PropertyChangeSupport(UIScale.class);
        }
        changeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public static void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        if (changeSupport == null) {
            return;
        }
        changeSupport.removePropertyChangeListener(propertyChangeListener);
    }

    public static boolean isSystemScalingEnabled() {
        if (jreHiDPI != null) {
            return jreHiDPI;
        }
        jreHiDPI = ((int)1546311531L ^ 0x5C2AD76B) != 0;
        if (SystemInfo.isJava_9_orLater) {
            jreHiDPI = (boolean)((long)-1229749566 ^ (long)-1229749565);
        } else if (SystemInfo.isJetBrainsJVM) {
            try {
                GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Class<?> class_ = Class.forName("sun.java2d.SunGraphicsEnvironment");
                if (class_.isInstance(graphicsEnvironment)) {
                    Method method = class_.getDeclaredMethod("isUIScaleOn", new Class[(int)1523151390L ^ 0x5AC9721E]);
                    jreHiDPI = (Boolean)method.invoke(graphicsEnvironment, new Object[(int)((long)-480127471 ^ (long)-480127471)]);
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        return jreHiDPI;
    }

    public static double getSystemScaleFactor(Graphics2D graphics2D) {
        return UIScale.isSystemScalingEnabled() ? UIScale.getSystemScaleFactor(graphics2D.getDeviceConfiguration()) : 1.0;
    }

    public static double getSystemScaleFactor(GraphicsConfiguration graphicsConfiguration) {
        return UIScale.isSystemScalingEnabled() && graphicsConfiguration != null ? graphicsConfiguration.getDefaultTransform().getScaleX() : 1.0;
    }

    private static void initialize() {
        if (initialized) {
            return;
        }
        initialized = (int)-1419099040L ^ 0xAB6A4461;
        if (!UIScale.isUserScalingEnabled()) {
            return;
        }
        PropertyChangeListener propertyChangeListener = new PropertyChangeListener(){

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
        };
        UIManager.addPropertyChangeListener(propertyChangeListener);
        UIManager.getDefaults().addPropertyChangeListener(propertyChangeListener);
        UIManager.getLookAndFeelDefaults().addPropertyChangeListener(propertyChangeListener);
        UIScale.updateScaleFactor();
    }

    private static void updateScaleFactor() {
        Font font;
        if (!UIScale.isUserScalingEnabled()) {
            return;
        }
        float f2 = UIScale.getCustomScaleFactor();
        if (f2 > 0.0f) {
            UIScale.setUserScaleFactor(f2, (boolean)((long)1754532273 ^ (long)1754532273));
            return;
        }
        Font font2 = UIManager.getFont("defaultFont");
        if (font2 == null) {
            font2 = UIManager.getFont("Label.font");
        }
        float f3 = SystemInfo.isWindows ? (font2 instanceof UIResource ? (UIScale.isSystemScalingEnabled() ? 1.0f : UIScale.computeScaleFactor((font = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.defaultGUI.font")) != null ? font : font2)) : UIScale.computeScaleFactor(font2)) : UIScale.computeScaleFactor(font2);
        UIScale.setUserScaleFactor(f3, ((int)1135769336L ^ 0x43B276F9) != 0);
    }

    private static float computeScaleFactor(Font font) {
        float f2 = Float.intBitsToFloat(0x5D385334 ^ 0x1C785334);
        if (SystemInfo.isWindows) {
            if ("Tahoma".equals(font.getFamily())) {
                f2 = Float.intBitsToFloat((int)((long)522558734 ^ (long)1578474766));
            }
        } else if (SystemInfo.isMacOS) {
            f2 = Float.intBitsToFloat((int)((long)1535316352 ^ (long)450040192));
        } else if (SystemInfo.isLinux) {
            f2 = SystemInfo.isKDE ? Float.intBitsToFloat((int)1850694809L ^ 0x2F1F5C99) : Float.intBitsToFloat((int)((long)1501953982 ^ (long)418774974));
        }
        return (float)font.getSize() / f2;
    }

    private static boolean isUserScalingEnabled() {
        return FlatSystemProperties.getBoolean("flatlaf.uiScale.enabled", (boolean)((long)757012367 ^ (long)757012366));
    }

    public static FontUIResource applyCustomScaleFactor(FontUIResource fontUIResource) {
        if (!UIScale.isUserScalingEnabled()) {
            return fontUIResource;
        }
        float f2 = UIScale.getCustomScaleFactor();
        if (f2 <= 0.0f) {
            return fontUIResource;
        }
        float f3 = UIScale.computeScaleFactor(fontUIResource);
        if (f2 == f3) {
            return fontUIResource;
        }
        int n2 = Math.max(Math.round((float)fontUIResource.getSize() / f3 * f2), (int)-1230077348L ^ 0xB6AE825D);
        return new FontUIResource(fontUIResource.deriveFont((float)n2));
    }

    private static float getCustomScaleFactor() {
        return UIScale.parseScaleFactor(System.getProperty("flatlaf.uiScale"));
    }

    private static float parseScaleFactor(String string) {
        if (string == null) {
            return Float.intBitsToFloat((int)((long)1310097861 ^ (long)-241794619));
        }
        float f2 = 1.0f;
        if (string.endsWith("x")) {
            string = string.substring((int)818328146L ^ 0x30C6B252, string.length() - ((int)-1594893324L ^ 0xA0EFDBF5));
        } else if (string.endsWith("dpi")) {
            f2 = Float.intBitsToFloat(0x5A10CC35 ^ 0x18D0CC35);
            string = string.substring((int)-1554570631L ^ 0xA3572279, string.length() - (int)((long)-1222236443 ^ (long)-1222236442));
        } else if (string.endsWith("%")) {
            f2 = Float.intBitsToFloat((int)((long)1185683553 ^ (long)73668705));
            string = string.substring((int)-1599690415L ^ 0xA0A6A951, string.length() - (int)((long)-455841805 ^ (long)-455841806));
        }
        try {
            float f3 = Float.parseFloat(string);
            return f3 > 0.0f ? f3 / f2 : Float.intBitsToFloat((int)666432708L ^ 0x9838F4C4);
        }
        catch (NumberFormatException numberFormatException) {
            return Float.intBitsToFloat((int)((long)312483 ^ (long)-1081817949));
        }
    }

    public static float getUserScaleFactor() {
        UIScale.initialize();
        return scaleFactor;
    }

    private static void setUserScaleFactor(float f2, boolean bl) {
        if (bl) {
            if (f2 < 1.0f) {
                f2 = FlatSystemProperties.getBoolean("flatlaf.uiScale.allowScaleDown", (boolean)((long)-989549903 ^ (long)-989549903)) ? (float)Math.round(f2 * Float.intBitsToFloat((int)((long)733219345 ^ (long)1788086801))) / Float.intBitsToFloat((int)((long)428877271 ^ (long)1487939031)) : 1.0f;
            } else if (f2 > 1.0f) {
                f2 = (float)Math.round(f2 * Float.intBitsToFloat(0xDA665C1 ^ 0x4D2665C1)) / Float.intBitsToFloat(0x496D9B02 ^ 0x9ED9B02);
            }
        }
        f2 = Math.max(f2, Float.intBitsToFloat((int)((long)838484322 ^ (long)204901807)));
        float f3 = scaleFactor;
        scaleFactor = f2;
        if (changeSupport != null) {
            changeSupport.firePropertyChange("userScaleFactor", Float.valueOf(f3), Float.valueOf(f2));
        }
    }

    public static float scale(float f2) {
        UIScale.initialize();
        return scaleFactor == 1.0f ? f2 : f2 * scaleFactor;
    }

    public static int scale(int n2) {
        UIScale.initialize();
        return scaleFactor == 1.0f ? n2 : Math.round((float)n2 * scaleFactor);
    }

    public static int scale2(int n2) {
        UIScale.initialize();
        return scaleFactor == 1.0f ? n2 : (int)((float)n2 * scaleFactor);
    }

    public static float unscale(float f2) {
        UIScale.initialize();
        return scaleFactor == 1.0f ? f2 : f2 / scaleFactor;
    }

    public static int unscale(int n2) {
        UIScale.initialize();
        return scaleFactor == 1.0f ? n2 : Math.round((float)n2 / scaleFactor);
    }

    public static void scaleGraphics(Graphics2D graphics2D) {
        UIScale.initialize();
        if (scaleFactor != 1.0f) {
            graphics2D.scale(scaleFactor, scaleFactor);
        }
    }

    public static Dimension scale(Dimension dimension) {
        UIScale.initialize();
        return dimension == null || scaleFactor == 1.0f ? dimension : (dimension instanceof UIResource ? new DimensionUIResource(UIScale.scale(dimension.width), UIScale.scale(dimension.height)) : new Dimension(UIScale.scale(dimension.width), UIScale.scale(dimension.height)));
    }

    public static Insets scale(Insets insets) {
        UIScale.initialize();
        return insets == null || scaleFactor == 1.0f ? insets : (insets instanceof UIResource ? new InsetsUIResource(UIScale.scale(insets.top), UIScale.scale(insets.left), UIScale.scale(insets.bottom), UIScale.scale(insets.right)) : new Insets(UIScale.scale(insets.top), UIScale.scale(insets.left), UIScale.scale(insets.bottom), UIScale.scale(insets.right)));
    }

    static /* synthetic */ void access$000() {
        UIScale.updateScaleFactor();
    }

    static {
        scaleFactor = 1.0f;
    }
}

