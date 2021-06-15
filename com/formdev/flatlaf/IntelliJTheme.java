/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.UIDefaultsLoader;
import com.formdev.flatlaf.json.Json;
import com.formdev.flatlaf.json.ParseException;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.StringUtils;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;

public class IntelliJTheme {
    public final String name;
    public final boolean dark;
    public final String author;
    private final boolean isMaterialUILite;
    private final Map<String, String> colors;
    private final Map<String, Object> ui;
    private final Map<String, Object> icons;
    private Map<String, ColorUIResource> namedColors = Collections.emptyMap();
    private static Map<String, String> uiKeyMapping;
    private static Map<String, String> uiKeyCopying;
    private static Map<String, String> uiKeyInverseMapping;
    private static Map<String, String> checkboxKeyMapping;
    private static Map<String, String> checkboxDuplicateColors;

    public static boolean install(InputStream inputStream) {
        try {
            return FlatLaf.install(IntelliJTheme.createLaf(inputStream));
        }
        catch (Exception exception) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to load IntelliJ theme", exception);
            return ((int)897176149L ^ 0x3579D255) != 0;
        }
    }

    public static FlatLaf createLaf(InputStream inputStream) throws IOException {
        return IntelliJTheme.createLaf(new IntelliJTheme(inputStream));
    }

    public static FlatLaf createLaf(IntelliJTheme intelliJTheme) {
        return new ThemeLaf(intelliJTheme);
    }

    public IntelliJTheme(InputStream inputStream) throws IOException {
        Map map;
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);){
            map = (Map)Json.parse(inputStreamReader);
        }
        catch (ParseException parseException) {
            throw new IOException(parseException.getMessage(), parseException);
        }
        this.name = (String)map.get("name");
        this.dark = Boolean.parseBoolean((String)map.get("dark"));
        this.author = (String)map.get("author");
        this.isMaterialUILite = this.author.equals("Mallowigi");
        this.colors = (Map)map.get("colors");
        this.ui = (Map)map.get("ui");
        this.icons = (Map)map.get("icons");
    }

    /*
     * WARNING - void declaration
     */
    private void applyProperties(UIDefaults uIDefaults) {
        int n2;
        void var6_11;
        if (this.ui == null) {
            return;
        }
        uIDefaults.put("Component.isIntelliJTheme", (Object)(((int)540878213L ^ 0x203D2584) != 0));
        uIDefaults.put("Button.paintShadow", (Object)(((int)1913134486L ^ 0x72081D97) != 0));
        uIDefaults.put("Button.shadowWidth", (Object)(this.dark ? (int)((long)-1016347904 ^ (long)-1016347903) << 1 : (int)1147791248L ^ 0x4469E791));
        Map<Object, Object> map = this.removeThemeSpecificDefaults(uIDefaults);
        this.loadNamedColors(uIDefaults);
        ArrayList<Object> arrayList = new ArrayList<Object>();
        HashSet<String> hashSet = new HashSet<String>();
        for (Map.Entry<String, Object> object2 : this.ui.entrySet()) {
            this.apply(object2.getKey(), object2.getValue(), uIDefaults, arrayList, hashSet);
        }
        this.applyColorPalette(uIDefaults);
        this.applyCheckBoxColors(uIDefaults);
        for (Map.Entry<String, Object> entry : uiKeyCopying.entrySet()) {
            uIDefaults.put(entry.getKey(), uIDefaults.get(entry.getValue()));
        }
        Object object3 = uIDefaults.get("Panel.background");
        uIDefaults.put("Button.disabledBackground", object3);
        uIDefaults.put("ToggleButton.disabledBackground", object3);
        this.copyIfNotSet(uIDefaults, "Button.focusedBorderColor", "Component.focusedBorderColor", hashSet);
        uIDefaults.put("Button.hoverBorderColor", uIDefaults.get("Button.focusedBorderColor"));
        uIDefaults.put("HelpButton.hoverBorderColor", uIDefaults.get("Button.focusedBorderColor"));
        Object object = uIDefaults.get("Button.startBackground");
        Object object2 = uIDefaults.get("Button.startBorderColor");
        if (object == null) {
            Object object4 = uIDefaults.get("Button.background");
        }
        if (object2 == null) {
            object2 = uIDefaults.get("Button.borderColor");
        }
        uIDefaults.put("HelpButton.background", (Object)var6_11);
        uIDefaults.put("HelpButton.borderColor", object2);
        uIDefaults.put("HelpButton.disabledBackground", object3);
        uIDefaults.put("HelpButton.disabledBorderColor", uIDefaults.get("Button.disabledBorderColor"));
        uIDefaults.put("HelpButton.focusedBorderColor", uIDefaults.get("Button.focusedBorderColor"));
        uIDefaults.put("HelpButton.focusedBackground", uIDefaults.get("Button.focusedBackground"));
        uIDefaults.put("ComboBox.editableBackground", uIDefaults.get("TextField.background"));
        uIDefaults.put("Spinner.background", uIDefaults.get("TextField.background"));
        uIDefaults.put("Spinner.buttonBackground", uIDefaults.get("ComboBox.buttonEditableBackground"));
        uIDefaults.put("Spinner.buttonArrowColor", uIDefaults.get("ComboBox.buttonArrowColor"));
        uIDefaults.put("Spinner.buttonDisabledArrowColor", uIDefaults.get("ComboBox.buttonDisabledArrowColor"));
        if (hashSet.contains("TextField.background")) {
            Object object5 = uIDefaults.get("TextField.background");
            if (!hashSet.contains("FormattedTextField.background")) {
                uIDefaults.put("FormattedTextField.background", object5);
            }
            if (!hashSet.contains("PasswordField.background")) {
                uIDefaults.put("PasswordField.background", object5);
            }
            if (!hashSet.contains("EditorPane.background")) {
                uIDefaults.put("EditorPane.background", object5);
            }
            if (!hashSet.contains("TextArea.background")) {
                uIDefaults.put("TextArea.background", object5);
            }
            if (!hashSet.contains("TextPane.background")) {
                uIDefaults.put("TextPane.background", object5);
            }
            if (!hashSet.contains("Spinner.background")) {
                uIDefaults.put("Spinner.background", object5);
            }
        }
        if (!hashSet.contains("ToggleButton.startBackground") && !hashSet.contains("*.startBackground")) {
            uIDefaults.put("ToggleButton.startBackground", uIDefaults.get("Button.startBackground"));
        }
        if (!hashSet.contains("ToggleButton.endBackground") && !hashSet.contains("*.endBackground")) {
            uIDefaults.put("ToggleButton.endBackground", uIDefaults.get("Button.endBackground"));
        }
        if (!hashSet.contains("ToggleButton.foreground") && hashSet.contains("Button.foreground")) {
            uIDefaults.put("ToggleButton.foreground", uIDefaults.get("Button.foreground"));
        }
        if (this.isMaterialUILite) {
            uIDefaults.put("List.background", uIDefaults.get("Tree.background"));
            uIDefaults.put("Table.background", uIDefaults.get("Tree.background"));
        }
        if ((n2 = uIDefaults.getInt("Tree.rowHeight")) > ((int)1725883897L ^ 0x66DEE5F2) << 1) {
            uIDefaults.put("Tree.rowHeight", (Object)(((int)190723951L ^ 0xB5E3764) << 1));
        }
        uIDefaults.putAll(map);
    }

    private Map<Object, Object> removeThemeSpecificDefaults(UIDefaults uIDefaults) {
        Object object2;
        ArrayList<String> arrayList = new ArrayList<String>();
        for (Object object2 : uIDefaults.keySet()) {
            if (!(object2 instanceof String) || !((String)object2).startsWith("[")) continue;
            arrayList.add((String)object2);
        }
        HashMap hashMap = new HashMap();
        object2 = (char)((int)-186637137L ^ 0xF4E024F4) + this.name.replace((int)((long)2031345069 ^ (long)2031345068) << 5, (char)((int)1554135560L ^ 0x5CA23A57)) + (char)((int)905692454L ^ 0x35FBC57B);
        String string = "[author-" + this.author.replace(((int)1255215682L ^ 0x4AD11243) << 5, (char)((long)1367236232 ^ (long)1367236311)) + (char)((int)530191504L ^ 0x1F9A14CD);
        String string2 = "[*]";
        String[] arrstring = new String[(int)435833090L ^ 0x19FA4901];
        arrstring[(int)((long)1675664832 ^ (long)1675664832)] = object2;
        arrstring[(int)-1693010596L ^ -1693010595] = string;
        arrstring[((int)-42796153L ^ -42796154) << 1] = string2;
        String[] arrstring2 = arrstring;
        block1: for (String string3 : arrayList) {
            Object v2 = uIDefaults.remove(string3);
            String[] arrstring3 = arrstring2;
            int n2 = arrstring3.length;
            for (int i2 = (int)-2039009136L ^ 0x86773090; i2 < n2; ++i2) {
                String string4 = arrstring3[i2];
                if (!string3.startsWith(string4)) continue;
                hashMap.put(string3.substring(string4.length()), v2);
                continue block1;
            }
        }
        return hashMap;
    }

    private void loadNamedColors(UIDefaults uIDefaults) {
        if (this.colors == null) {
            return;
        }
        this.namedColors = new HashMap<String, ColorUIResource>();
        for (Map.Entry<String, String> entry : this.colors.entrySet()) {
            String string = entry.getValue();
            ColorUIResource colorUIResource = UIDefaultsLoader.parseColor(string);
            if (colorUIResource == null) continue;
            String string2 = entry.getKey();
            this.namedColors.put(string2, colorUIResource);
            uIDefaults.put("ColorPalette." + string2, colorUIResource);
        }
    }

    private void apply(String string, Object object, UIDefaults uIDefaults, ArrayList<Object> arrayList, Set<String> set) {
        if (object instanceof Map) {
            for (Map.Entry entry : ((Map)object).entrySet()) {
                this.apply(string + (((int)-1678508019L ^ 0x9BF4001A) << 1) + (String)entry.getKey(), entry.getValue(), uIDefaults, arrayList, set);
            }
        } else {
            Object object2;
            if ("".equals(object)) {
                return;
            }
            set.add(string);
            if (this.isMaterialUILite && (string.equals("ComboBox.padding") || string.equals("Spinner.border"))) {
                return;
            }
            if ((string = uiKeyMapping.getOrDefault(string, string)).isEmpty()) {
                return;
            }
            String string2 = object.toString();
            Object object3 = this.namedColors.get(string2);
            if (object3 == null) {
                if (!string2.startsWith("#") && (string.endsWith("ground") || string.endsWith("Color"))) {
                    string2 = this.fixColorIfValid("#" + string2, string2);
                } else if (string2.startsWith("##")) {
                    string2 = this.fixColorIfValid(string2.substring((int)((long)-1570460772 ^ (long)-1570460771)), string2);
                } else if ((string.endsWith(".border") || string.endsWith("Border")) && (object2 = StringUtils.split(string2, (int)((long)1424364626 ^ (long)1424364633) << 2)).size() == (int)((long)-20969167 ^ (long)-20969164) && !((String)object2.get(((int)934302745L ^ 0x37B05418) << 2)).startsWith("#")) {
                    object2.set((int)((long)191848078 ^ (long)191848079) << 2, "#" + (String)object2.get((int)((long)195193537 ^ (long)195193536) << 2));
                    string2 = String.join((CharSequence)",", (Iterable<? extends CharSequence>)object2);
                }
                try {
                    object3 = UIDefaultsLoader.parseValue(string, string2);
                }
                catch (RuntimeException runtimeException) {
                    UIDefaultsLoader.logParseError(string, string2, runtimeException, (boolean)((long)1875931634 ^ (long)1875931634));
                    return;
                }
            }
            if (string.startsWith("*.")) {
                object2 = string.substring((int)((long)539544656 ^ (long)539544657));
                if (arrayList.size() != uIDefaults.size()) {
                    arrayList.clear();
                    Enumeration enumeration = uIDefaults.keys();
                    while (enumeration.hasMoreElements()) {
                        arrayList.add(enumeration.nextElement());
                    }
                }
                for (Object e2 : arrayList) {
                    String string3;
                    if (!(e2 instanceof String) || !(string3 = uiKeyInverseMapping.getOrDefault(e2, (String)e2)).endsWith((String)object2) || ((String)e2).startsWith("CheckBox.icon.")) continue;
                    uIDefaults.put(e2, object3);
                }
            } else {
                uIDefaults.put(string, object3);
            }
        }
    }

    private String fixColorIfValid(String string, String string2) {
        try {
            UIDefaultsLoader.parseColorRGBA(string);
            return string;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return string2;
        }
    }

    private void applyColorPalette(UIDefaults uIDefaults) {
        if (this.icons == null) {
            return;
        }
        Object object = this.icons.get("ColorPalette");
        if (!(object instanceof Map)) {
            return;
        }
        Map map = (Map)object;
        for (Map.Entry entry : map.entrySet()) {
            ColorUIResource colorUIResource;
            String string = (String)entry.getKey();
            Object v2 = entry.getValue();
            if (string.startsWith("Checkbox.") || !(v2 instanceof String)) continue;
            if (this.dark) {
                string = StringUtils.removeTrailing(string, ".Dark");
            }
            if ((colorUIResource = this.toColor((String)v2)) == null) continue;
            uIDefaults.put(string, colorUIResource);
        }
    }

    private ColorUIResource toColor(String string) {
        ColorUIResource colorUIResource = this.namedColors.get(string);
        return colorUIResource != null ? colorUIResource : UIDefaultsLoader.parseColor(string);
    }

    private void applyCheckBoxColors(UIDefaults uIDefaults) {
        Object object;
        String string;
        String[] arrstring;
        if (this.icons == null) {
            return;
        }
        Object object2 = this.icons.get("ColorPalette");
        if (!(object2 instanceof Map)) {
            return;
        }
        int n2 = (int)1522116836L ^ 0x5AB9A8E4;
        Map map = (Map)object2;
        String[] arrstring2 = map.entrySet().iterator();
        while (arrstring2.hasNext()) {
            ColorUIResource colorUIResource;
            arrstring = arrstring2.next();
            String string2 = (String)arrstring.getKey();
            Object object3 = arrstring.getValue();
            if (!string2.startsWith("Checkbox.") || !(object3 instanceof String)) continue;
            if (string2.equals("Checkbox.Background.Default") || string2.equals("Checkbox.Foreground.Selected")) {
                object3 = "#ffffff";
            }
            string = checkboxDuplicateColors.get(string2);
            if (this.dark) {
                string2 = StringUtils.removeTrailing(string2, ".Dark");
            }
            if ((object = checkboxKeyMapping.get(string2)) == null) continue;
            String string3 = "CheckBox.icon.";
            if (!this.dark && ((String)object).startsWith(string3)) {
                object = "CheckBox.icon[filled].".concat(((String)object).substring(string3.length()));
            }
            if ((colorUIResource = this.toColor((String)object3)) != null) {
                uIDefaults.put(object, colorUIResource);
                if (string != null) {
                    String string4;
                    if (this.dark) {
                        string = StringUtils.removeTrailing(string, ".Dark");
                    }
                    if ((string4 = checkboxKeyMapping.get(string)) != null) {
                        uIDefaults.put(string4, colorUIResource);
                    }
                }
            }
            n2 = (int)((long)136988689 ^ (long)136988688);
        }
        if (n2 != 0) {
            uIDefaults.remove("CheckBox.icon.focusWidth");
            uIDefaults.put("CheckBox.icon.hoverBorderColor", uIDefaults.get("CheckBox.icon.focusedBorderColor"));
            uIDefaults.remove("CheckBox.icon[filled].focusWidth");
            uIDefaults.put("CheckBox.icon[filled].hoverBorderColor", uIDefaults.get("CheckBox.icon[filled].focusedBorderColor"));
            uIDefaults.put("CheckBox.icon[filled].selectedFocusedBackground", uIDefaults.get("CheckBox.icon[filled].selectedBackground"));
            if (this.dark) {
                String[] arrstring3 = new String[(int)((long)-815008760 ^ (long)-815008759) << 2];
                arrstring3[(int)-358269175L ^ -358269175] = "CheckBox.icon.focusedBorderColor";
                arrstring3[(int)((long)905732427 ^ (long)905732426)] = "CheckBox.icon.selectedFocusedBorderColor";
                arrstring3[(int)((long)1352667179 ^ (long)1352667178) << 1] = "CheckBox.icon[filled].focusedBorderColor";
                arrstring3[(int)-1231806080L ^ -1231806077] = "CheckBox.icon[filled].selectedFocusedBorderColor";
                arrstring = arrstring2 = arrstring3;
                int n3 = arrstring.length;
                for (int i2 = (int)((long)924079315 ^ (long)924079315); i2 < n3; ++i2) {
                    string = arrstring[i2];
                    object = uIDefaults.getColor(string);
                    if (object == null) continue;
                    uIDefaults.put(string, new ColorUIResource(new Color(((Color)object).getRGB() & ((int)-1320212930L ^ 0xB1B0D9C1) | (int)-518899022L ^ 0x47123AB2, (boolean)((long)-1303840557 ^ (long)-1303840558))));
                }
            }
        }
    }

    private void copyIfNotSet(UIDefaults uIDefaults, String string, String string2, Set<String> set) {
        if (!set.contains(string)) {
            uIDefaults.put(string, uIDefaults.get(string2));
        }
    }

    static {
        Map.Entry[] arrentry;
        uiKeyMapping = new HashMap<String, String>();
        uiKeyCopying = new HashMap<String, String>();
        uiKeyInverseMapping = new HashMap<String, String>();
        checkboxKeyMapping = new HashMap<String, String>();
        checkboxDuplicateColors = new HashMap<String, String>();
        uiKeyMapping.put("ComboBox.background", "");
        uiKeyMapping.put("ComboBox.nonEditableBackground", "ComboBox.background");
        uiKeyMapping.put("ComboBox.ArrowButton.background", "ComboBox.buttonEditableBackground");
        uiKeyMapping.put("ComboBox.ArrowButton.disabledIconColor", "ComboBox.buttonDisabledArrowColor");
        uiKeyMapping.put("ComboBox.ArrowButton.iconColor", "ComboBox.buttonArrowColor");
        uiKeyMapping.put("ComboBox.ArrowButton.nonEditableBackground", "ComboBox.buttonBackground");
        uiKeyMapping.put("Component.inactiveErrorFocusColor", "Component.error.borderColor");
        uiKeyMapping.put("Component.errorFocusColor", "Component.error.focusedBorderColor");
        uiKeyMapping.put("Component.inactiveWarningFocusColor", "Component.warning.borderColor");
        uiKeyMapping.put("Component.warningFocusColor", "Component.warning.focusedBorderColor");
        uiKeyMapping.put("Link.activeForeground", "Component.linkColor");
        uiKeyMapping.put("Menu.border", "Menu.margin");
        uiKeyMapping.put("MenuItem.border", "MenuItem.margin");
        uiKeyCopying.put("CheckBoxMenuItem.margin", "MenuItem.margin");
        uiKeyCopying.put("RadioButtonMenuItem.margin", "MenuItem.margin");
        uiKeyMapping.put("PopupMenu.border", "PopupMenu.borderInsets");
        uiKeyCopying.put("MenuItem.underlineSelectionColor", "TabbedPane.underlineColor");
        uiKeyCopying.put("Menu.selectionBackground", "List.selectionBackground");
        uiKeyCopying.put("MenuItem.selectionBackground", "List.selectionBackground");
        uiKeyCopying.put("CheckBoxMenuItem.selectionBackground", "List.selectionBackground");
        uiKeyCopying.put("RadioButtonMenuItem.selectionBackground", "List.selectionBackground");
        uiKeyMapping.put("ProgressBar.background", "");
        uiKeyMapping.put("ProgressBar.foreground", "");
        uiKeyMapping.put("ProgressBar.trackColor", "ProgressBar.background");
        uiKeyMapping.put("ProgressBar.progressColor", "ProgressBar.foreground");
        uiKeyCopying.put("ProgressBar.selectionForeground", "ProgressBar.background");
        uiKeyCopying.put("ProgressBar.selectionBackground", "ProgressBar.foreground");
        uiKeyMapping.put("ScrollBar.trackColor", "ScrollBar.track");
        uiKeyMapping.put("ScrollBar.thumbColor", "ScrollBar.thumb");
        uiKeyMapping.put("Separator.separatorColor", "Separator.foreground");
        uiKeyMapping.put("Slider.trackWidth", "");
        uiKeyCopying.put("Slider.trackValueColor", "ProgressBar.foreground");
        uiKeyCopying.put("Slider.thumbColor", "ProgressBar.foreground");
        uiKeyCopying.put("Slider.trackColor", "ProgressBar.background");
        uiKeyCopying.put("TitlePane.inactiveBackground", "TitlePane.background");
        uiKeyMapping.put("TitlePane.infoForeground", "TitlePane.foreground");
        uiKeyMapping.put("TitlePane.inactiveInfoForeground", "TitlePane.inactiveForeground");
        Map.Entry[] arrentry2 = uiKeyMapping.entrySet().iterator();
        while (arrentry2.hasNext()) {
            arrentry = arrentry2.next();
            uiKeyInverseMapping.put(arrentry.getValue(), arrentry.getKey());
        }
        uiKeyCopying.put("ToggleButton.tab.underlineColor", "TabbedPane.underlineColor");
        uiKeyCopying.put("ToggleButton.tab.disabledUnderlineColor", "TabbedPane.disabledUnderlineColor");
        uiKeyCopying.put("ToggleButton.tab.selectedBackground", "TabbedPane.selectedBackground");
        uiKeyCopying.put("ToggleButton.tab.hoverBackground", "TabbedPane.hoverColor");
        uiKeyCopying.put("ToggleButton.tab.focusBackground", "TabbedPane.focusColor");
        checkboxKeyMapping.put("Checkbox.Background.Default", "CheckBox.icon.background");
        checkboxKeyMapping.put("Checkbox.Background.Disabled", "CheckBox.icon.disabledBackground");
        checkboxKeyMapping.put("Checkbox.Border.Default", "CheckBox.icon.borderColor");
        checkboxKeyMapping.put("Checkbox.Border.Disabled", "CheckBox.icon.disabledBorderColor");
        checkboxKeyMapping.put("Checkbox.Focus.Thin.Default", "CheckBox.icon.focusedBorderColor");
        checkboxKeyMapping.put("Checkbox.Focus.Wide", "CheckBox.icon.focusColor");
        checkboxKeyMapping.put("Checkbox.Foreground.Disabled", "CheckBox.icon.disabledCheckmarkColor");
        checkboxKeyMapping.put("Checkbox.Background.Selected", "CheckBox.icon.selectedBackground");
        checkboxKeyMapping.put("Checkbox.Border.Selected", "CheckBox.icon.selectedBorderColor");
        checkboxKeyMapping.put("Checkbox.Foreground.Selected", "CheckBox.icon.checkmarkColor");
        checkboxKeyMapping.put("Checkbox.Focus.Thin.Selected", "CheckBox.icon.selectedFocusedBorderColor");
        checkboxDuplicateColors.put("Checkbox.Background.Default.Dark", "Checkbox.Background.Selected.Dark");
        checkboxDuplicateColors.put("Checkbox.Border.Default.Dark", "Checkbox.Border.Selected.Dark");
        checkboxDuplicateColors.put("Checkbox.Focus.Thin.Default.Dark", "Checkbox.Focus.Thin.Selected.Dark");
        arrentry = arrentry2 = checkboxDuplicateColors.entrySet().toArray(new Map.Entry[checkboxDuplicateColors.size()]);
        int n2 = arrentry.length;
        for (int i2 = (int)917755346L ^ 0x36B3D5D2; i2 < n2; ++i2) {
            Map.Entry entry = arrentry[i2];
            checkboxDuplicateColors.put((String)entry.getValue(), (String)entry.getKey());
        }
    }

    public static class ThemeLaf
    extends FlatLaf {
        private final IntelliJTheme theme;

        public ThemeLaf(IntelliJTheme intelliJTheme) {
            this.theme = intelliJTheme;
        }

        @Override
        public String getName() {
            return this.theme.name;
        }

        @Override
        public String getDescription() {
            return this.getName();
        }

        @Override
        public boolean isDark() {
            return this.theme.dark;
        }

        public IntelliJTheme getTheme() {
            return this.theme;
        }

        @Override
        void applyAdditionalDefaults(UIDefaults uIDefaults) {
            this.theme.applyProperties(uIDefaults);
        }

        protected ArrayList<Class<?>> getLafClassesForDefaultsLoading() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(FlatLaf.class);
            arrayList.add(this.theme.dark ? FlatDarkLaf.class : FlatLightLaf.class);
            arrayList.add(this.theme.dark ? FlatDarculaLaf.class : FlatIntelliJLaf.class);
            arrayList.add(ThemeLaf.class);
            return arrayList;
        }
    }
}

