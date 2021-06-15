/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatDefaultsAddon;
import com.formdev.flatlaf.FlatIconColors;
import com.formdev.flatlaf.FlatInputMaps;
import com.formdev.flatlaf.LinuxFontPolicy;
import com.formdev.flatlaf.MnemonicHandler;
import com.formdev.flatlaf.UIDefaultsLoader;
import com.formdev.flatlaf.ui.FlatNativeWindowBorder;
import com.formdev.flatlaf.ui.FlatPopupFactory;
import com.formdev.flatlaf.ui.FlatRootPaneUI;
import com.formdev.flatlaf.util.GrayFilter;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.MultiResolutionImageSupport;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.PopupFactory;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.text.StyleContext;
import javax.swing.text.html.HTMLEditorKit;

public abstract class FlatLaf
extends BasicLookAndFeel {
    private static final String DESKTOPFONTHINTS = "awt.font.desktophints";
    private static List<Object> customDefaultsSources;
    private String desktopPropertyName;
    private String desktopPropertyName2;
    private PropertyChangeListener desktopPropertyListener;
    private static boolean aquaLoaded;
    private static boolean updateUIPending;
    private PopupFactory oldPopupFactory;
    private MnemonicHandler mnemonicHandler;
    private Consumer<UIDefaults> postInitialization;

    public static boolean install(LookAndFeel lookAndFeel) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            return ((int)-11474756L ^ 0xFF50E8BD) != 0;
        }
        catch (Exception exception) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to initialize look and feel '" + lookAndFeel.getClass().getName() + "'.", exception);
            return ((int)-2097482384L ^ 0x82FAF570) != 0;
        }
    }

    public static void installLafInfo(String string, Class<? extends LookAndFeel> class_) {
        UIManager.installLookAndFeel(new UIManager.LookAndFeelInfo(string, class_.getName()));
    }

    @Override
    public String getID() {
        return "FlatLaf - " + this.getName();
    }

    public abstract boolean isDark();

    public static boolean isLafDark() {
        LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        return (lookAndFeel instanceof FlatLaf && ((FlatLaf)lookAndFeel).isDark() ? (int)-581528421L ^ 0xDD56949A : (int)-1161790251L ^ 0xBAC07CD5) != 0;
    }

    @Override
    public boolean getSupportsWindowDecorations() {
        if (SystemInfo.isProjector || SystemInfo.isWebswing || SystemInfo.isWinPE) {
            return (int)((long)1626860467 ^ (long)1626860467) != 0;
        }
        if (SystemInfo.isWindows_10_orLater && FlatNativeWindowBorder.isSupported()) {
            return (int)((long)1911693088 ^ (long)1911693088) != 0;
        }
        return SystemInfo.isWindows_10_orLater;
    }

    @Override
    public boolean isNativeLookAndFeel() {
        return (int)((long)-1265620202 ^ (long)-1265620202) != 0;
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        return (int)((long)1572994239 ^ (long)1572994238) != 0;
    }

    @Override
    public Icon getDisabledIcon(JComponent jComponent, Icon icon) {
        if (icon instanceof DisabledIconProvider) {
            return ((DisabledIconProvider)((Object)icon)).getDisabledIcon();
        }
        if (icon instanceof ImageIcon) {
            Object object = UIManager.get("Component.grayFilter");
            ImageFilter imageFilter = object instanceof ImageFilter ? (ImageFilter)object : GrayFilter.createDisabledIconFilter(this.isDark());
            Function<Image, Image> function = image -> {
                FilteredImageSource filteredImageSource = new FilteredImageSource(image.getSource(), imageFilter);
                return Toolkit.getDefaultToolkit().createImage(filteredImageSource);
            };
            Image image2 = ((ImageIcon)icon).getImage();
            return new ImageIconUIResource(MultiResolutionImageSupport.map(image2, function));
        }
        return null;
    }

    @Override
    public void initialize() {
        if (SystemInfo.isMacOS) {
            this.initializeAqua();
        }
        super.initialize();
        this.oldPopupFactory = PopupFactory.getSharedInstance();
        PopupFactory.setSharedInstance(new FlatPopupFactory());
        this.mnemonicHandler = new MnemonicHandler();
        this.mnemonicHandler.install();
        if (SystemInfo.isWindows) {
            this.desktopPropertyName = "win.messagebox.font";
        } else if (SystemInfo.isLinux) {
            this.desktopPropertyName = "gnome.Gtk/FontName";
            this.desktopPropertyName2 = "gnome.Xft/DPI";
        }
        if (this.desktopPropertyName != null) {
            this.desktopPropertyListener = propertyChangeEvent -> {
                String string = propertyChangeEvent.getPropertyName();
                if (this.desktopPropertyName.equals(string) || string.equals(this.desktopPropertyName2)) {
                    FlatLaf.reSetLookAndFeel();
                } else if ("awt.font.desktophints".equals(string) && UIManager.getLookAndFeel() instanceof FlatLaf) {
                    this.putAATextInfo(UIManager.getLookAndFeelDefaults());
                    FlatLaf.updateUILater();
                }
            };
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.addPropertyChangeListener(this.desktopPropertyName, this.desktopPropertyListener);
            if (this.desktopPropertyName2 != null) {
                toolkit.addPropertyChangeListener(this.desktopPropertyName2, this.desktopPropertyListener);
            }
            toolkit.addPropertyChangeListener("awt.font.desktophints", this.desktopPropertyListener);
        }
        this.postInitialization = uIDefaults -> {
            Color color = uIDefaults.getColor("Component.linkColor");
            if (color != null) {
                Object[] arrobject = new Object[(int)((long)-1415263861 ^ (long)-1415263862)];
                arrobject[(int)((long)1699981436 ^ (long)1699981436)] = color.getRGB() & (int)((long)1220164054 ^ (long)1212532265);
                new HTMLEditorKit().getStyleSheet().addRule(String.format("a, address { color: #%06x; }", arrobject));
            }
        };
    }

    @Override
    public void uninitialize() {
        if (this.desktopPropertyListener != null) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.removePropertyChangeListener(this.desktopPropertyName, this.desktopPropertyListener);
            if (this.desktopPropertyName2 != null) {
                toolkit.removePropertyChangeListener(this.desktopPropertyName2, this.desktopPropertyListener);
            }
            toolkit.removePropertyChangeListener("awt.font.desktophints", this.desktopPropertyListener);
            this.desktopPropertyName = null;
            this.desktopPropertyName2 = null;
            this.desktopPropertyListener = null;
        }
        if (this.oldPopupFactory != null) {
            PopupFactory.setSharedInstance(this.oldPopupFactory);
            this.oldPopupFactory = null;
        }
        if (this.mnemonicHandler != null) {
            this.mnemonicHandler.uninstall();
            this.mnemonicHandler = null;
        }
        new HTMLEditorKit().getStyleSheet().addRule("a, address { color: blue; }");
        this.postInitialization = null;
        super.uninitialize();
    }

    private void initializeAqua() {
        BasicLookAndFeel basicLookAndFeel;
        Object object;
        if (aquaLoaded) {
            return;
        }
        aquaLoaded = (int)((long)1156927198 ^ (long)1156927199);
        String string = "com.apple.laf.AquaLookAndFeel";
        try {
            if (SystemInfo.isJava_9_orLater) {
                Class[] arrclass = new Class[(int)1847306514L ^ 0x6E1BA913];
                arrclass[(int)((long)-186612652 ^ (long)-186612652)] = String.class;
                object = UIManager.class.getMethod("createLookAndFeel", arrclass);
                Object[] arrobject = new Object[(int)((long)-166940689 ^ (long)-166940690)];
                arrobject[(int)((long)-1179733657 ^ (long)-1179733657)] = "Mac OS X";
                basicLookAndFeel = (BasicLookAndFeel)((Method)object).invoke(null, arrobject);
            } else {
                basicLookAndFeel = (BasicLookAndFeel)Class.forName(string).newInstance();
            }
        }
        catch (Exception exception) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to initialize Aqua look and feel '" + string + "'.", exception);
            throw new IllegalStateException();
        }
        object = PopupFactory.getSharedInstance();
        basicLookAndFeel.initialize();
        basicLookAndFeel.uninitialize();
        PopupFactory.setSharedInstance((PopupFactory)object);
    }

    /*
     * Could not resolve type clashes
     */
    @Override
    public UIDefaults getDefaults() {
        UIDefaults uIDefaults2 = super.getDefaults();
        uIDefaults2.put("laf.dark", (Object)this.isDark());
        uIDefaults2.addResourceBundle("com.formdev.flatlaf.resources.Bundle");
        String[] arrstring = new String[(int)((long)1647360727 ^ (long)1647360724) << 2];
        arrstring[(int)((long)-1383260619 ^ (long)-1383260619)] = "Button.disabledBackground";
        arrstring[(int)682241984L ^ 682241985] = "EditorPane.disabledBackground";
        arrstring[((int)-749315961L ^ -749315962) << 1] = "EditorPane.inactiveBackground";
        arrstring[(int)1007454336L ^ 1007454339] = "FormattedTextField.disabledBackground";
        arrstring[((int)2139639596L ^ 2139639597) << 2] = "PasswordField.disabledBackground";
        arrstring[(int)((long)1393628148 ^ (long)1393628145)] = "Spinner.disabledBackground";
        arrstring[((int)302699180L ^ 302699183) << 1] = "TextArea.disabledBackground";
        arrstring[(int)((long)1890256917 ^ (long)1890256914)] = "TextArea.inactiveBackground";
        arrstring[(int)((long)-194961567 ^ (long)-194961568) << 3] = "TextField.disabledBackground";
        arrstring[(int)((long)-409405496 ^ (long)-409405503)] = "TextPane.disabledBackground";
        arrstring[((int)814451678L ^ 814451675) << 1] = "TextPane.inactiveBackground";
        arrstring[(int)-1196097627L ^ -1196097618] = "ToggleButton.disabledBackground";
        this.putDefaults(uIDefaults2, uIDefaults2.getColor("control"), arrstring);
        String[] arrstring2 = new String[(int)((long)149245493 ^ (long)149245500)];
        arrstring2[(int)((long)-767259458 ^ (long)-767259458)] = "Button.disabledText";
        arrstring2[(int)((long)934892898 ^ (long)934892899)] = "CheckBox.disabledText";
        arrstring2[(int)((long)992232660 ^ (long)992232661) << 1] = "CheckBoxMenuItem.disabledForeground";
        arrstring2[(int)1735835247L ^ 1735835244] = "Menu.disabledForeground";
        arrstring2[((int)-1451599247L ^ -1451599248) << 2] = "MenuItem.disabledForeground";
        arrstring2[(int)((long)-109027431 ^ (long)-109027428)] = "RadioButton.disabledText";
        arrstring2[((int)1455192893L ^ 1455192894) << 1] = "RadioButtonMenuItem.disabledForeground";
        arrstring2[(int)600972577L ^ 600972582] = "Spinner.disabledForeground";
        arrstring2[(int)((long)1820302997 ^ (long)1820302996) << 3] = "ToggleButton.disabledText";
        this.putDefaults(uIDefaults2, uIDefaults2.getColor("textInactiveText"), arrstring2);
        String[] arrstring3 = new String[(int)((long)-753033043 ^ (long)-753033044)];
        arrstring3[(int)((long)2050386663 ^ (long)2050386663)] = "DesktopIcon.foreground";
        this.putDefaults(uIDefaults2, uIDefaults2.getColor("textText"), arrstring3);
        this.initFonts(uIDefaults2);
        FlatLaf.initIconColors(uIDefaults2, this.isDark());
        FlatInputMaps.initInputMaps(uIDefaults2);
        Object v2 = uIDefaults2.remove("InternalFrame.icon");
        uIDefaults2.put("InternalFrame.icon", v2);
        uIDefaults2.put("TitlePane.icon", v2);
        ServiceLoader<FlatDefaultsAddon> serviceLoader = ServiceLoader.load(FlatDefaultsAddon.class);
        ArrayList<FlatDefaultsAddon> arrayList = new ArrayList<FlatDefaultsAddon>();
        for (Object object : serviceLoader) {
            arrayList.add((FlatDefaultsAddon)object);
        }
        arrayList.sort((flatDefaultsAddon, flatDefaultsAddon2) -> flatDefaultsAddon.getPriority() - flatDefaultsAddon2.getPriority());
        List<Class<?>> list = this.getLafClassesForDefaultsLoading();
        if (list != null) {
            UIDefaultsLoader.loadDefaultsFromProperties(list, arrayList, this.getAdditionalDefaults(), this.isDark(), uIDefaults2);
        } else {
            UIDefaultsLoader.loadDefaultsFromProperties(this.getClass(), arrayList, this.getAdditionalDefaults(), this.isDark(), uIDefaults2);
        }
        if (SystemInfo.isMacOS && Boolean.getBoolean("apple.laf.useScreenMenuBar")) {
            uIDefaults2.put("MenuBarUI", "com.apple.laf.AquaMenuBarUI");
            uIDefaults2.put("MenuBar.backgroundPainter", BorderFactory.createEmptyBorder());
        }
        this.putAATextInfo(uIDefaults2);
        this.applyAdditionalDefaults(uIDefaults2);
        for (FlatDefaultsAddon flatDefaultsAddon3 : arrayList) {
            flatDefaultsAddon3.afterDefaultsLoading(this, uIDefaults2);
        }
        uIDefaults2.put("laf.scaleFactor", uIDefaults -> Float.valueOf(UIScale.getUserScaleFactor()));
        if (this.postInitialization != null) {
            this.postInitialization.accept(uIDefaults2);
            this.postInitialization = null;
        }
        return uIDefaults2;
    }

    void applyAdditionalDefaults(UIDefaults uIDefaults) {
    }

    protected List<Class<?>> getLafClassesForDefaultsLoading() {
        return null;
    }

    protected Properties getAdditionalDefaults() {
        return null;
    }

    private void initFonts(UIDefaults uIDefaults) {
        Object object;
        FontUIResource fontUIResource = null;
        if (SystemInfo.isWindows) {
            object = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.messagebox.font");
            if (object != null) {
                if (SystemInfo.isWinPE) {
                    Font font = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.defaultGUI.font");
                    if (font != null) {
                        fontUIResource = FlatLaf.createCompositeFont(font.getFamily(), font.getStyle(), ((Font)object).getSize());
                    }
                } else {
                    fontUIResource = FlatLaf.createCompositeFont(((Font)object).getFamily(), ((Font)object).getStyle(), ((Font)object).getSize());
                }
            }
        } else if (SystemInfo.isMacOS) {
            object = SystemInfo.isMacOS_10_15_Catalina_orLater ? (SystemInfo.isJetBrainsJVM_11_orLater ? ".AppleSystemUIFont" : "Helvetica Neue") : (SystemInfo.isMacOS_10_11_ElCapitan_orLater ? ".SF NS Text" : "Lucida Grande");
            fontUIResource = FlatLaf.createCompositeFont((String)object, (int)((long)-1598982835 ^ (long)-1598982835), (int)-972035285L ^ 0xC60FEB26);
        } else if (SystemInfo.isLinux) {
            object = LinuxFontPolicy.getFont();
            FontUIResource fontUIResource2 = fontUIResource = object instanceof FontUIResource ? (FontUIResource)object : new FontUIResource((Font)object);
        }
        if (fontUIResource == null) {
            fontUIResource = FlatLaf.createCompositeFont("SansSerif", (int)711583765L ^ 0x2A69E815, (int)((long)228028929 ^ (long)228028930) << 2);
        }
        fontUIResource = UIScale.applyCustomScaleFactor(fontUIResource);
        object = new ActiveFont(1.0f);
        for (Object e2 : uIDefaults.keySet()) {
            if (!(e2 instanceof String) || !((String)e2).endsWith(".font") && !((String)e2).endsWith("Font")) continue;
            uIDefaults.put(e2, object);
        }
        uIDefaults.put("ProgressBar.font", new ActiveFont(Float.intBitsToFloat((int)((long)1701550913 ^ (long)1513226971))));
        uIDefaults.put("defaultFont", fontUIResource);
    }

    static FontUIResource createCompositeFont(String string, int n2, int n3) {
        Font font = StyleContext.getDefaultStyleContext().getFont(string, n2, n3);
        return font instanceof FontUIResource ? (FontUIResource)font : new FontUIResource(font);
    }

    public static UIDefaults.ActiveValue createActiveFontValue(float f2) {
        return new ActiveFont(f2);
    }

    public static void initIconColors(UIDefaults uIDefaults, boolean bl) {
        FlatIconColors[] arrflatIconColors = FlatIconColors.values();
        int n2 = arrflatIconColors.length;
        for (int i2 = (int)1469256637L ^ 0x579313BD; i2 < n2; ++i2) {
            FlatIconColors flatIconColors = arrflatIconColors[i2];
            if (flatIconColors.light != (!bl ? (int)52856184L ^ 0x3268579 : (int)((long)1819531175 ^ (long)1819531175)) && flatIconColors.dark != bl) continue;
            uIDefaults.put(flatIconColors.key, new ColorUIResource(flatIconColors.rgb));
        }
    }

    private void putAATextInfo(UIDefaults uIDefaults) {
        if (SystemInfo.isMacOS && SystemInfo.isJetBrainsJVM) {
            uIDefaults.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        } else if (SystemInfo.isJava_9_orLater) {
            Map map;
            Object v2;
            Object object = Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");
            if (object instanceof Map && (v2 = (map = (Map)object).get(RenderingHints.KEY_TEXT_ANTIALIASING)) != null && v2 != RenderingHints.VALUE_TEXT_ANTIALIAS_OFF && v2 != RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT) {
                uIDefaults.put(RenderingHints.KEY_TEXT_ANTIALIASING, v2);
                uIDefaults.put(RenderingHints.KEY_TEXT_LCD_CONTRAST, map.get(RenderingHints.KEY_TEXT_LCD_CONTRAST));
            }
        } else {
            try {
                Object object = Class.forName("sun.swing.SwingUtilities2").getField("AA_TEXT_PROPERTY_KEY").get(null);
                Class[] arrclass = new Class[(int)1311235947L ^ 0x4E27DF6A];
                arrclass[(int)((long)-1433353507 ^ (long)-1433353507)] = Boolean.TYPE;
                Object[] arrobject = new Object[(int)((long)-295066985 ^ (long)-295066986)];
                arrobject[(int)-1098037317L ^ -1098037317] = (boolean)((long)-1904229541 ^ (long)-1904229542);
                Object object2 = Class.forName("sun.swing.SwingUtilities2$AATextInfo").getMethod("getAATextInfo", arrclass).invoke(null, arrobject);
                uIDefaults.put(object, object2);
            }
            catch (Exception exception) {
                LoggingFacade.INSTANCE.logSevere(null, exception);
                throw new RuntimeException(exception);
            }
        }
    }

    private void putDefaults(UIDefaults uIDefaults, Object object, String ... arrstring) {
        String[] arrstring2 = arrstring;
        int n2 = arrstring2.length;
        for (int i2 = (int)((long)847108475 ^ (long)847108475); i2 < n2; ++i2) {
            String string = arrstring2[i2];
            uIDefaults.put(string, object);
        }
    }

    static List<Object> getCustomDefaultsSources() {
        return customDefaultsSources;
    }

    public static void registerCustomDefaultsSource(String string) {
        FlatLaf.registerCustomDefaultsSource(string, null);
    }

    public static void unregisterCustomDefaultsSource(String string) {
        FlatLaf.unregisterCustomDefaultsSource(string, null);
    }

    public static void registerCustomDefaultsSource(String string, ClassLoader classLoader) {
        if (customDefaultsSources == null) {
            customDefaultsSources = new ArrayList<Object>();
        }
        customDefaultsSources.add(string);
        customDefaultsSources.add(classLoader);
    }

    public static void unregisterCustomDefaultsSource(String string, ClassLoader classLoader) {
        if (customDefaultsSources == null) {
            return;
        }
        int n2 = customDefaultsSources.size();
        for (int i2 = (int)((long)-828182042 ^ (long)-828182042); i2 < n2 - ((int)1172664998L ^ 0x45E572A7); ++i2) {
            Object object = customDefaultsSources.get(i2);
            if (!string.equals(object) || customDefaultsSources.get(i2 + ((int)1657265702L ^ 0x62C7DE27)) != classLoader) continue;
            customDefaultsSources.remove(i2 + ((int)1129174217L ^ 0x434DD4C8));
            customDefaultsSources.remove(i2);
            break;
        }
    }

    public static void registerCustomDefaultsSource(File file) {
        if (customDefaultsSources == null) {
            customDefaultsSources = new ArrayList<Object>();
        }
        customDefaultsSources.add(file);
    }

    public static void unregisterCustomDefaultsSource(File file) {
        if (customDefaultsSources == null) {
            return;
        }
        customDefaultsSources.remove(file);
    }

    private static void reSetLookAndFeel() {
        EventQueue.invokeLater(() -> {
            LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
            try {
                UIManager.setLookAndFeel(lookAndFeel);
                PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(UIManager.class, "lookAndFeel", lookAndFeel, lookAndFeel);
                PropertyChangeListener[] arrpropertyChangeListener = UIManager.getPropertyChangeListeners();
                int n2 = arrpropertyChangeListener.length;
                for (int i2 = (int)911412970L ^ 0x36530EEA; i2 < n2; ++i2) {
                    PropertyChangeListener propertyChangeListener = arrpropertyChangeListener[i2];
                    propertyChangeListener.propertyChange(propertyChangeEvent);
                }
                FlatLaf.updateUI();
            }
            catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
                LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to reinitialize look and feel '" + lookAndFeel.getClass().getName() + "'.", unsupportedLookAndFeelException);
            }
        });
    }

    public static void updateUI() {
        Window[] arrwindow = Window.getWindows();
        int n2 = arrwindow.length;
        for (int i2 = (int)((long)1523347219 ^ (long)1523347219); i2 < n2; ++i2) {
            Window window = arrwindow[i2];
            SwingUtilities.updateComponentTreeUI(window);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void updateUILater() {
        Class<FlatLaf> class_ = FlatLaf.class;
        synchronized (FlatLaf.class) {
            if (updateUIPending) {
                // ** MonitorExit[var0] (shouldn't be in output)
                return;
            }
            updateUIPending = (int)((long)-1318933936 ^ (long)-1318933935);
            // ** MonitorExit[var0] (shouldn't be in output)
            EventQueue.invokeLater(() -> {
                FlatLaf.updateUI();
                Class<FlatLaf> class_ = FlatLaf.class;
                synchronized (FlatLaf.class) {
                    updateUIPending = (int)-577102974L ^ 0xDD9A1B82;
                    // ** MonitorExit[var0] (shouldn't be in output)
                    return;
                }
            });
            return;
        }
    }

    public static boolean supportsNativeWindowDecorations() {
        return (SystemInfo.isWindows_10_orLater && FlatNativeWindowBorder.isSupported() ? (int)1438151354L ^ 0x55B872BB : (int)1131360775L ^ 0x436F3207) != 0;
    }

    public static boolean isUseNativeWindowDecorations() {
        return UIManager.getBoolean("TitlePane.useWindowDecorations");
    }

    public static void setUseNativeWindowDecorations(boolean bl) {
        UIManager.put("TitlePane.useWindowDecorations", bl);
        if (!(UIManager.getLookAndFeel() instanceof FlatLaf)) {
            return;
        }
        Window[] arrwindow = Window.getWindows();
        int n2 = arrwindow.length;
        for (int i2 = (int)-1882074821L ^ 0x8FD1D13B; i2 < n2; ++i2) {
            Window window = arrwindow[i2];
            if (!FlatLaf.isDisplayableFrameOrDialog(window)) continue;
            FlatRootPaneUI.updateNativeWindowBorder(((RootPaneContainer)((Object)window)).getRootPane());
        }
    }

    public static void revalidateAndRepaintAllFramesAndDialogs() {
        Window[] arrwindow = Window.getWindows();
        int n2 = arrwindow.length;
        for (int i2 = (int)-1427872860L ^ 0xAAE463A4; i2 < n2; ++i2) {
            Window window = arrwindow[i2];
            if (!FlatLaf.isDisplayableFrameOrDialog(window)) continue;
            window.revalidate();
            window.repaint();
        }
    }

    public static void repaintAllFramesAndDialogs() {
        Window[] arrwindow = Window.getWindows();
        int n2 = arrwindow.length;
        for (int i2 = (int)-1560246795L ^ 0xA30085F5; i2 < n2; ++i2) {
            Window window = arrwindow[i2];
            if (!FlatLaf.isDisplayableFrameOrDialog(window)) continue;
            window.repaint();
        }
    }

    private static boolean isDisplayableFrameOrDialog(Window window) {
        return (window.isDisplayable() && (window instanceof JFrame || window instanceof JDialog) ? (int)((long)-1596302605 ^ (long)-1596302606) : (int)((long)-1462660814 ^ (long)-1462660814)) != 0;
    }

    public static boolean isShowMnemonics() {
        return MnemonicHandler.isShowMnemonics();
    }

    public static void showMnemonics(Component component) {
        MnemonicHandler.showMnemonics(((int)-1084860610L ^ 0xBF56573F) != 0, component);
    }

    public static void hideMnemonics() {
        MnemonicHandler.showMnemonics(((int)52768371L ^ 0x3252E73) != 0, null);
    }

    public final boolean equals(Object object) {
        return super.equals(object);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public static interface DisabledIconProvider {
        public Icon getDisabledIcon();
    }

    private static class ImageIconUIResource
    extends ImageIcon
    implements UIResource {
        ImageIconUIResource(Image image) {
            super(image);
        }
    }

    private static class ActiveFont
    implements UIDefaults.ActiveValue {
        private final float scaleFactor;
        private Font font;
        private Font lastDefaultFont;

        ActiveFont(float f2) {
            this.scaleFactor = f2;
        }

        @Override
        public Object createValue(UIDefaults uIDefaults) {
            Font font = UIManager.getFont("defaultFont");
            if (font == null) {
                font = UIManager.getFont("Label.font");
            }
            if (this.lastDefaultFont != font) {
                this.lastDefaultFont = font;
                if (this.scaleFactor != 1.0f) {
                    int n2 = Math.round((float)font.getSize() * this.scaleFactor);
                    this.font = new FontUIResource(font.deriveFont((float)n2));
                } else {
                    this.font = font instanceof UIResource ? font : new FontUIResource(font);
                }
            }
            return this.font;
        }
    }
}

