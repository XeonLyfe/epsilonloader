/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatDefaultsAddon;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;
import com.formdev.flatlaf.util.ColorFunctions;
import com.formdev.flatlaf.util.DerivedColor;
import com.formdev.flatlaf.util.GrayFilter;
import com.formdev.flatlaf.util.HSLColor;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.StringUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.InsetsUIResource;

class UIDefaultsLoader {
    private static final String TYPE_PREFIX = "{";
    private static final String TYPE_PREFIX_END = "}";
    private static final String VARIABLE_PREFIX = "@";
    private static final String PROPERTY_PREFIX = "$";
    private static final String OPTIONAL_PREFIX = "?";
    private static final String WILDCARD_PREFIX = "*.";
    private static int parseColorDepth;
    private static ValueType[] tempResultValueType;

    UIDefaultsLoader() {
    }

    static void loadDefaultsFromProperties(Class<?> class_, List<FlatDefaultsAddon> list, Properties properties, boolean bl, UIDefaults uIDefaults) {
        ArrayList arrayList = new ArrayList();
        Class<?> class_2 = class_;
        while (FlatLaf.class.isAssignableFrom(class_2)) {
            arrayList.add((int)-1839391815L ^ 0x925D1BB9, class_2);
            class_2 = class_2.getSuperclass();
        }
        UIDefaultsLoader.loadDefaultsFromProperties(arrayList, list, properties, bl, uIDefaults);
    }

    /*
     * WARNING - void declaration
     */
    static void loadDefaultsFromProperties(List<Class<?>> list, List<FlatDefaultsAddon> list2, Properties properties, boolean bl, UIDefaults uIDefaults) {
        try {
            Object object;
            Map.Entry entry3;
            Object object22;
            void var9_25;
            Object object3;
            Properties properties2 = new Properties();
            for (Class<?> list32 : list) {
                String string2 = (char)((int)-1387581673L ^ 0xAD4B2F38) + list32.getName().replace(((int)1024151274L ^ 0x3D0B4EFD) << 1, (char)((int)328582955L ^ 0x1395C704)) + ".properties";
                InputStream inputStream = list32.getResourceAsStream(string2);
                try {
                    if (inputStream == null) continue;
                    properties2.load(inputStream);
                }
                finally {
                    if (inputStream == null) continue;
                    inputStream.close();
                }
            }
            for (FlatDefaultsAddon flatDefaultsAddon : list2) {
                for (Class<?> class_ : list) {
                    object3 = flatDefaultsAddon.getDefaults(class_);
                    try {
                        if (object3 == null) continue;
                        properties2.load((InputStream)object3);
                    }
                    finally {
                        if (object3 == null) continue;
                        ((InputStream)object3).close();
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (FlatDefaultsAddon flatDefaultsAddon : list2) {
                ClassLoader classLoader = flatDefaultsAddon.getClass().getClassLoader();
                if (arrayList.contains(classLoader)) continue;
                arrayList.add(classLoader);
            }
            List<Object> list3 = FlatLaf.getCustomDefaultsSources();
            int n2 = list3 != null ? list3.size() : (int)((long)1308265101 ^ (long)1308265101);
            int n3 = (int)((long)116910456 ^ (long)116910456);
            while (++var9_25 < n2) {
                object3 = list3.get((int)var9_25);
                if (object3 instanceof String && var9_25 + (int)((long)1792706140 ^ (long)1792706141) < n2) {
                    ClassLoader classLoader;
                    object22 = (String)object3;
                    if ((classLoader = (ClassLoader)list3.get((int)(++var9_25))) != null && !arrayList.contains(classLoader)) {
                        arrayList.add(classLoader);
                    }
                    object22 = ((String)object22).replace((int)((long)-352809398 ^ (long)-352809379) << 1, (char)((long)1024643439 ^ (long)1024643392));
                    if (classLoader == null) {
                        ClassLoader classLoader2 = FlatLaf.class.getClassLoader();
                    }
                    for (Class<?> class_ : list) {
                        void var12_36;
                        entry3 = (String)object22 + (char)((long)-2046034208 ^ (long)-2046034225) + class_.getSimpleName() + ".properties";
                        object = var12_36.getResourceAsStream((String)((Object)entry3));
                        try {
                            if (object == null) continue;
                            properties2.load((InputStream)object);
                        }
                        finally {
                            if (object == null) continue;
                            ((InputStream)object).close();
                        }
                    }
                    continue;
                }
                if (!(object3 instanceof File)) continue;
                object22 = (File)object3;
                for (Class<?> class_ : list) {
                    File file = new File((File)object22, class_.getSimpleName() + ".properties");
                    if (!file.isFile()) continue;
                    entry3 = new FileInputStream(file);
                    try {
                        properties2.load((InputStream)((Object)entry3));
                    }
                    finally {
                        ((InputStream)((Object)entry3)).close();
                    }
                }
            }
            if (properties != null) {
                properties2.putAll(properties);
            }
            ArrayList<String> arrayList2 = new ArrayList<String>();
            for (Object object22 : properties2.keySet()) {
                String string3 = (String)object22;
                if (!string3.startsWith("[") || !string3.startsWith("[win]") && !string3.startsWith("[mac]") && !string3.startsWith("[linux]") && !string3.startsWith("[light]") && !string3.startsWith("[dark]")) continue;
                arrayList2.add(string3);
            }
            if (!arrayList2.isEmpty()) {
                object3 = bl ? "[dark]" : "[light]";
                for (String string4 : arrayList2) {
                    if (!string4.startsWith((String)object3)) continue;
                    properties2.put(string4.substring(((String)object3).length()), properties2.remove(string4));
                }
                object22 = SystemInfo.isWindows ? "[win]" : (SystemInfo.isMacOS ? "[mac]" : (SystemInfo.isLinux ? "[linux]" : "[unknown]"));
                for (String string5 : arrayList2) {
                    Object v2 = properties2.remove(string5);
                    if (!string5.startsWith((String)object22)) continue;
                    properties2.put(string5.substring(((String)object22).length()), v2);
                }
            }
            object3 = new HashMap<String, String>();
            object22 = properties2.entrySet().iterator();
            while (object22.hasNext()) {
                Map.Entry entry2 = (Map.Entry)object22.next();
                String string6 = (String)entry2.getKey();
                if (!string6.startsWith(WILDCARD_PREFIX)) continue;
                ((HashMap)object3).put(string6.substring(WILDCARD_PREFIX.length()), (String)entry2.getValue());
                object22.remove();
            }
            for (Object k2 : uIDefaults.keySet()) {
                int n4;
                if (!(k2 instanceof String) || properties2.containsKey(k2) || (n4 = ((String)k2).lastIndexOf(((int)-1094455026L ^ 0xBEC3F119) << 1)) < 0 || (object = (String)((HashMap)object3).get(entry3 = ((String)k2).substring(n4 + ((int)1516945321L ^ 0x5A6ABFA8)))) == null) continue;
                properties2.put(k2, object);
            }
            Function<String, String> function = string -> properties2.getProperty((String)string);
            Function<String, String> function2 = string -> UIDefaultsLoader.resolveValue(string, function);
            for (Map.Entry entry3 : properties2.entrySet()) {
                object = (String)entry3.getKey();
                if (((String)object).startsWith(VARIABLE_PREFIX)) continue;
                String string7 = UIDefaultsLoader.resolveValue((String)entry3.getValue(), function);
                try {
                    uIDefaults.put(object, UIDefaultsLoader.parseValue((String)object, string7, null, function2, arrayList));
                }
                catch (RuntimeException runtimeException) {
                    UIDefaultsLoader.logParseError((String)object, string7, runtimeException, (boolean)((long)-2078972678 ^ (long)-2078972677));
                }
            }
        }
        catch (IOException iOException) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to load properties files.", iOException);
        }
    }

    static void logParseError(String string, String string2, RuntimeException runtimeException, boolean bl) {
        String string3 = "FlatLaf: Failed to parse: '" + string + (char)((long)1327304896 ^ (long)1327304957) + string2 + (char)((long)51137702 ^ (long)51137665);
        if (bl) {
            LoggingFacade.INSTANCE.logSevere(string3, runtimeException);
        } else {
            LoggingFacade.INSTANCE.logConfig(string3, runtimeException);
        }
    }

    static String resolveValue(String string, Function<String, String> function) {
        String string2;
        String string3 = string = string.trim();
        if (string.startsWith(PROPERTY_PREFIX)) {
            string = string.substring(PROPERTY_PREFIX.length());
        } else if (!string.startsWith(VARIABLE_PREFIX)) {
            return string;
        }
        int n2 = (int)1874778437L ^ 0x6FBED945;
        if (string.startsWith(OPTIONAL_PREFIX)) {
            string = string.substring(OPTIONAL_PREFIX.length());
            n2 = (int)((long)401249053 ^ (long)401249052);
        }
        if ((string2 = function.apply(string)) == null) {
            if (n2 != 0) {
                return "null";
            }
            throw new IllegalArgumentException("variable or property '" + string + "' not found");
        }
        if (string2.equals(string3)) {
            throw new IllegalArgumentException("endless recursion in variable or property '" + string + "'");
        }
        return UIDefaultsLoader.resolveValue(string2, function);
    }

    static Object parseValue(String string2, String string3) {
        return UIDefaultsLoader.parseValue(string2, string3, null, string -> string, Collections.emptyList());
    }

    /*
     * Exception decompiling
     */
    static Object parseValue(String var0, String var1_1, ValueType[] var2_2, Function<String, String> var3_3, List<ClassLoader> var4_4) {
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

    private static Object parseBorder(String string, Function<String, String> function, List<ClassLoader> list) {
        if (string.indexOf(((int)1009351211L ^ 0x3C297A20) << 2) >= 0) {
            List<String> list2 = UIDefaultsLoader.split(string, ((int)-1129232108L ^ 0xBCB1491F) << 2);
            Insets insets = UIDefaultsLoader.parseInsets(string);
            ColorUIResource colorUIResource = list2.size() >= (int)((long)1922232264 ^ (long)1922232269) ? (ColorUIResource)UIDefaultsLoader.parseColorOrFunction(function.apply(list2.get((int)((long)495119337 ^ (long)495119336) << 2)), function, ((int)-1793113833L ^ 0x951F4116) != 0) : null;
            float f2 = list2.size() >= ((int)131972712L ^ 0x7DDBE6B) << 1 ? UIDefaultsLoader.parseFloat(list2.get((int)1259277371L ^ 0x4B0F0C3E), (boolean)((long)1809107471 ^ (long)1809107470)).floatValue() : 1.0f;
            return uIDefaults -> colorUIResource != null ? new FlatLineBorder(insets, colorUIResource, f2) : new FlatEmptyBorder(insets);
        }
        return UIDefaultsLoader.parseInstance(string, list);
    }

    private static Object parseInstance(String string, List<ClassLoader> list) {
        return uIDefaults -> {
            try {
                return UIDefaultsLoader.findClass(string, list).newInstance();
            }
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException reflectiveOperationException) {
                LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to instantiate '" + string + "'.", reflectiveOperationException);
                return null;
            }
        };
    }

    private static Object parseClass(String string, List<ClassLoader> list) {
        return uIDefaults -> {
            try {
                return UIDefaultsLoader.findClass(string, list);
            }
            catch (ClassNotFoundException classNotFoundException) {
                LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to find class '" + string + "'.", classNotFoundException);
                return null;
            }
        };
    }

    private static Class<?> findClass(String string, List<ClassLoader> list) throws ClassNotFoundException {
        try {
            return Class.forName(string);
        }
        catch (ClassNotFoundException classNotFoundException) {
            for (ClassLoader classLoader : list) {
                try {
                    return classLoader.loadClass(string);
                }
                catch (ClassNotFoundException classNotFoundException2) {
                }
            }
            throw classNotFoundException;
        }
    }

    private static Insets parseInsets(String string) {
        List<String> list = UIDefaultsLoader.split(string, ((int)-1607344990L ^ 0xA031DCA9) << 2);
        try {
            return new InsetsUIResource(Integer.parseInt(list.get((int)1161103276L ^ 0x453507AC)), Integer.parseInt(list.get((int)-27282626L ^ 0xFE5FB33F)), Integer.parseInt(list.get((int)((long)1586643580 ^ (long)1586643581) << 1)), Integer.parseInt(list.get((int)((long)-558171352 ^ (long)-558171349))));
        }
        catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("invalid insets '" + string + "'");
        }
    }

    private static Dimension parseDimension(String string) {
        List<String> list = UIDefaultsLoader.split(string, ((int)-1711697563L ^ 0x99F9916E) << 2);
        try {
            return new DimensionUIResource(Integer.parseInt(list.get((int)25302831L ^ 0x182172F)), Integer.parseInt(list.get((int)((long)-342981472 ^ (long)-342981471))));
        }
        catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("invalid size '" + string + "'");
        }
    }

    private static Object parseColorOrFunction(String string, Function<String, String> function, boolean bl) {
        if (string.endsWith(")")) {
            return UIDefaultsLoader.parseColorFunctions(string, function, bl);
        }
        return UIDefaultsLoader.parseColor(string, bl);
    }

    static ColorUIResource parseColor(String string) {
        return UIDefaultsLoader.parseColor(string, (boolean)((long)-1025145283 ^ (long)-1025145283));
    }

    private static ColorUIResource parseColor(String string, boolean bl) {
        try {
            int n2 = UIDefaultsLoader.parseColorRGBA(string);
            return (n2 & ((int)1896368046L ^ 0x8E0847AE)) == ((int)1449932104L ^ 0xA96C3548) ? new ColorUIResource(n2) : new ColorUIResource(new Color(n2, (boolean)((long)-1083705465 ^ (long)-1083705466)));
        }
        catch (IllegalArgumentException illegalArgumentException) {
            if (bl) {
                throw new IllegalArgumentException("invalid color '" + string + "'");
            }
            return null;
        }
    }

    static int parseColorRGBA(String string) {
        int n2;
        int n3;
        int n4;
        int n5 = string.length();
        if (n5 != (int)((long)1716103542 ^ (long)1716103543) << 2 && n5 != (int)((long)-795203925 ^ (long)-795203922) && n5 != (int)((long)2059034688 ^ (long)2059034695) && n5 != ((int)429541089L ^ 0x199A46E8) || string.charAt((int)500526264L ^ 0x1DD56CB8) != (int)((long)-499190199 ^ (long)-499190166)) {
            throw new IllegalArgumentException();
        }
        int n6 = (int)((long)-973327358 ^ (long)-973327358);
        for (n4 = (int)939741011L ^ 0x38034F52; n4 < n5; ++n4) {
            n3 = string.charAt(n4);
            if (n3 >= (int)((long)1288629649 ^ (long)1288629650) << 4 && n3 <= (int)((long)-1525738945 ^ (long)-1525739002)) {
                n2 = n3 - ((int)((long)2000468226 ^ (long)2000468225) << 4);
            } else if (n3 >= ((int)-996392244L ^ 0xC49C42AD) && n3 <= (int)((long)1689984074 ^ (long)1689984121) << 1) {
                n2 = n3 - (int)((long)-389646168 ^ (long)-389646135) + (((int)-423440829L ^ 0xE6C2CE46) << 1);
            } else if (n3 >= (int)((long)-2129734029 ^ (long)-2129734094) && n3 <= ((int)1682501311L ^ 0x6448EE9C) << 1) {
                n2 = n3 - (int)((long)1634181838 ^ (long)1634181775) + (((int)276773574L ^ 0x107F3AC3) << 1);
            } else {
                throw new IllegalArgumentException();
            }
            n6 = n6 << (((int)1828311801L ^ 0x6CF9D2F8) << 2) | n2;
        }
        if (n5 <= ((int)596148918L ^ 0x238882B3)) {
            n4 = n6 & (int)((long)-413686574 ^ (long)-413686563) << 12;
            n3 = n6 & ((int)221502065L ^ 0xD33DA7E) << 8;
            n2 = n6 & (int)((long)-1131911103 ^ (long)-1131911090) << 4;
            int n7 = n6 & (int)((long)-1169642859 ^ (long)-1169642854);
            n6 = n4 << ((int)((long)499024226 ^ (long)499024227) << 4) | n4 << (((int)-2106687241L ^ 0x826E80F4) << 2) | n3 << (((int)579009625L ^ 0x2282FC5A) << 2) | n3 << ((int)((long)-241166614 ^ (long)-241166613) << 3) | n2 << ((int)((long)-16818304 ^ (long)-16818303) << 3) | n2 << ((int)((long)-311912797 ^ (long)-311912798) << 2) | n7 << ((int)((long)199924240 ^ (long)199924241) << 2) | n7;
        }
        return n5 == (int)((long)1629179172 ^ (long)1629179173) << 2 || n5 == ((int)-1173429960L ^ 0xBA0EE13F) ? (int)((long)-2089869443 ^ (long)2087657341) | n6 : n6 >> (((int)1952774339L ^ 0x7464F8C2) << 3) & ((int)584654941L ^ 0x2226DFA2) | (n6 & ((int)-904298124L ^ 0xCA19818B)) << ((int)((long)-663230119 ^ (long)-663230118) << 3);
    }

    /*
     * Exception decompiling
     */
    private static Object parseColorFunctions(String var0, Function<String, String> var1_1, boolean var2_2) {
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

    private static ColorUIResource parseColorRgbOrRgba(boolean bl, List<String> list, Function<String, String> function, boolean bl2) {
        if (bl && list.size() == ((int)-164695950L ^ 0xF62EF073) << 1) {
            String string = list.get((int)((long)1161532390 ^ (long)1161532390));
            int n2 = UIDefaultsLoader.parseInteger(list.get((int)-1669201397L ^ 0x9C82020A), (int)((long)-287504290 ^ (long)-287504290), (int)((long)-1135167978 ^ (long)-1135167767), ((int)-317257749L ^ 0xED1707EA) != 0);
            ColorUIResource colorUIResource = (ColorUIResource)UIDefaultsLoader.parseColorOrFunction(function.apply(string), function, bl2);
            return new ColorUIResource(new Color((n2 & ((int)701903271L ^ 0x29D63158)) << ((int)((long)-558321255 ^ (long)-558321254) << 3) | colorUIResource.getRGB() & (int)((long)-372554881 ^ (long)-382419840), (boolean)((long)696735082 ^ (long)696735083)));
        }
        int n3 = UIDefaultsLoader.parseInteger(list.get((int)((long)39140849 ^ (long)39140849)), (int)-1272354101L ^ 0xB4296ACB, (int)((long)-1366720300 ^ (long)-1366720469), (boolean)((long)-1577233134 ^ (long)-1577233133));
        int n4 = UIDefaultsLoader.parseInteger(list.get((int)((long)-1622302487 ^ (long)-1622302488)), (int)((long)1883234432 ^ (long)1883234432), (int)-536911534L ^ 0xDFFF61AD, ((int)1102153650L ^ 0x41B187B3) != 0);
        int n5 = UIDefaultsLoader.parseInteger(list.get(((int)-311596294L ^ 0xED6D6AFB) << 1), (int)((long)-695071224 ^ (long)-695071224), (int)((long)988852555 ^ (long)988852660), ((int)-147420008L ^ 0xF7368C99) != 0);
        int n6 = bl ? UIDefaultsLoader.parseInteger(list.get((int)((long)-1159596281 ^ (long)-1159596284)), (int)((long)1530252159 ^ (long)1530252159), (int)954883858L ^ 0x38EA5FED, ((int)-1938479444L ^ 0x8C7526AD) != 0) : (int)((long)724889295 ^ (long)724889136);
        return bl ? new ColorUIResource(new Color(n3, n4, n5, n6)) : new ColorUIResource(n3, n4, n5);
    }

    private static ColorUIResource parseColorHslOrHsla(boolean bl, List<String> list) {
        int n2 = UIDefaultsLoader.parseInteger(list.get((int)-356889427L ^ 0xEABA4CAD), (int)((long)-952005299 ^ (long)-952005299), ((int)1296489305L ^ 0x4D46DB74) << 3, ((int)497942510L ^ 0x1DADFFEE) != 0);
        int n3 = UIDefaultsLoader.parsePercentage(list.get((int)-1855184725L ^ 0x916C20AA));
        int n4 = UIDefaultsLoader.parsePercentage(list.get(((int)-545752569L ^ 0xDF787A06) << 1));
        int n5 = bl ? UIDefaultsLoader.parsePercentage(list.get((int)((long)-1840550455 ^ (long)-1840550454))) : (int)((long)458121715 ^ (long)458121706) << 2;
        float[] arrf = new float[(int)939924852L ^ 0x38061D77];
        arrf[(int)756950519L ^ 756950519] = n2;
        arrf[(int)((long)-1274307727 ^ (long)-1274307728)] = n3;
        arrf[((int)2038313827L ^ 2038313826) << 1] = n4;
        float[] arrf2 = arrf;
        return new ColorUIResource(HSLColor.toRGB(arrf2, (float)n5 / Float.intBitsToFloat((int)((long)229371675 ^ (long)1331949339))));
    }

    private static Object parseColorHSLIncreaseDecrease(int n2, boolean bl, List<String> list, Function<String, String> function, boolean bl2) {
        int n3;
        boolean bl3;
        boolean bl4;
        Object object;
        String string = list.get((int)-1667036226L ^ 0x9CA30BBE);
        int n32 = UIDefaultsLoader.parsePercentage(list.get((int)378767961L ^ 0x16938A58));
        int bl32 = (int)((long)-1932312781 ^ (long)-1932312781);
        int n4 = (int)-805402918L ^ 0xCFFE86DA;
        int n5 = (int)-572003447L ^ 0xDDE7EB89;
        int n6 = (int)((long)595168022 ^ (long)595168022);
        if (list.size() > (int)((long)-1880578641 ^ (long)-1880578642) << 1) {
            object = list.get(((int)522235198L ^ 0x1F20AD3F) << 1);
            bl4 = ((String)object).contains("relative");
            boolean bl5 = ((String)object).contains("autoInverse");
            n5 = ((String)object).contains("lazy") ? 1 : 0;
            bl3 = ((String)object).contains("derived");
            if (bl3 && !((String)object).contains("noAutoInverse")) {
                n3 = (int)((long)925927072 ^ (long)925927073);
            }
        }
        object = new ColorFunctions.HSLIncreaseDecrease(n2, bl, n32, bl4, n3 != 0);
        if (n5 != 0) {
            return arg_0 -> UIDefaultsLoader.lambda$parseColorHSLIncreaseDecrease$7(string, (ColorFunctions.ColorFunction)object, arg_0);
        }
        return UIDefaultsLoader.parseFunctionBaseColor(string, (ColorFunctions.ColorFunction)object, bl3, function, bl2);
    }

    private static Object parseColorFade(List<String> list, Function<String, String> function, boolean bl) {
        boolean bl2;
        Object object;
        String string = list.get((int)((long)-1295752445 ^ (long)-1295752445));
        int n2 = UIDefaultsLoader.parsePercentage(list.get((int)203665206L ^ 0xC23AF37));
        int bl22 = (int)((long)-415976890 ^ (long)-415976890);
        if (list.size() > ((int)2037142037L ^ 0x796C5214) << 1) {
            object = list.get(((int)1699307613L ^ 0x6549605C) << 1);
            bl2 = ((String)object).contains("derived");
        }
        object = new ColorFunctions.Fade(n2);
        return UIDefaultsLoader.parseFunctionBaseColor(string, (ColorFunctions.ColorFunction)object, bl2, function, bl);
    }

    private static Object parseColorSpin(List<String> list, Function<String, String> function, boolean bl) {
        boolean bl2;
        Object object;
        String string = list.get((int)1411534068L ^ 0x54224CF4);
        int n2 = UIDefaultsLoader.parseInteger(list.get((int)-496378416L ^ 0xE269DDD1), (boolean)((long)-1015665105 ^ (long)-1015665106));
        int bl22 = (int)((long)1806548051 ^ (long)1806548051);
        if (list.size() > ((int)-643867797L ^ 0xD99F5B6A) << 1) {
            object = list.get((int)((long)1184748384 ^ (long)1184748385) << 1);
            bl2 = ((String)object).contains("derived");
        }
        object = new ColorFunctions.HSLIncreaseDecrease((int)((long)-2121461007 ^ (long)-2121461007), (boolean)((long)796178668 ^ (long)796178669), n2, (boolean)((long)1881861782 ^ (long)1881861782), (boolean)((long)1775576263 ^ (long)1775576263));
        return UIDefaultsLoader.parseFunctionBaseColor(string, (ColorFunctions.ColorFunction)object, bl2, function, bl);
    }

    private static Object parseFunctionBaseColor(String string, ColorFunctions.ColorFunction colorFunction, boolean bl, Function<String, String> function, boolean bl2) {
        String string2 = function.apply(string);
        ColorUIResource colorUIResource = (ColorUIResource)UIDefaultsLoader.parseColorOrFunction(string2, function, bl2);
        if (colorUIResource == null) {
            return null;
        }
        ColorFunctions.ColorFunction[] arrcolorFunction = new ColorFunctions.ColorFunction[(int)((long)1557449712 ^ (long)1557449713)];
        arrcolorFunction[(int)107322298L ^ 107322298] = colorFunction;
        Color color = ColorFunctions.applyFunctions(colorUIResource, arrcolorFunction);
        if (bl) {
            ColorFunctions.ColorFunction[] arrcolorFunction2;
            if (colorUIResource instanceof DerivedColor && string2 == string) {
                ColorFunctions.ColorFunction[] arrcolorFunction3 = ((DerivedColor)colorUIResource).getFunctions();
                arrcolorFunction2 = new ColorFunctions.ColorFunction[arrcolorFunction3.length + (int)((long)656648674 ^ (long)656648675)];
                System.arraycopy(arrcolorFunction3, (int)((long)1180589043 ^ (long)1180589043), arrcolorFunction2, (int)((long)835538525 ^ (long)835538525), arrcolorFunction3.length);
                arrcolorFunction2[arrcolorFunction3.length] = colorFunction;
            } else {
                ColorFunctions.ColorFunction[] arrcolorFunction4 = new ColorFunctions.ColorFunction[(int)-83871031L ^ 0xFB003AC8];
                arrcolorFunction4[(int)((long)864537602 ^ (long)864537602)] = colorFunction;
                arrcolorFunction2 = arrcolorFunction4;
            }
            return new DerivedColor(color, arrcolorFunction2);
        }
        return new ColorUIResource(color);
    }

    private static int parsePercentage(String string) {
        int n2;
        if (!string.endsWith("%")) {
            throw new NumberFormatException("invalid percentage '" + string + "'");
        }
        try {
            n2 = Integer.parseInt(string.substring((int)((long)-1260020555 ^ (long)-1260020555), string.length() - ((int)1350347519L ^ 0x507CAAFE)));
        }
        catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException("invalid percentage '" + string + "'");
        }
        if (n2 < 0 || n2 > ((int)583036101L ^ 0x22C06CDC) << 2) {
            throw new IllegalArgumentException("percentage out of range (0-100%) '" + string + "'");
        }
        return n2;
    }

    private static Character parseCharacter(String string) {
        if (string.length() != (int)((long)-111859813 ^ (long)-111859814)) {
            throw new IllegalArgumentException("invalid character '" + string + "'");
        }
        return Character.valueOf(string.charAt((int)351458971L ^ 0x14F2D69B));
    }

    private static Integer parseInteger(String string, int n2, int n3, boolean bl) {
        if (bl && string.endsWith("%")) {
            int n4 = UIDefaultsLoader.parsePercentage(string);
            return n3 * n4 / (((int)-64797742L ^ 0xFC2343CB) << 2);
        }
        Integer n5 = UIDefaultsLoader.parseInteger(string, ((int)3995829L ^ 0x3CF8B4) != 0);
        if (n5 < n2 || n5 > n3) {
            throw new NumberFormatException("integer '" + string + "' out of range (" + n2 + (char)((int)-586167445L ^ 0xDD0FCB46) + n3 + (char)((int)-280807174L ^ 0xEF4338D3));
        }
        return n5;
    }

    private static Integer parseInteger(String string, boolean bl) {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException numberFormatException) {
            if (bl) {
                throw new NumberFormatException("invalid integer '" + string + "'");
            }
            return null;
        }
    }

    private static Float parseFloat(String string, boolean bl) {
        try {
            return Float.valueOf(Float.parseFloat(string));
        }
        catch (NumberFormatException numberFormatException) {
            if (bl) {
                throw new NumberFormatException("invalid float '" + string + "'");
            }
            return null;
        }
    }

    private static UIDefaults.ActiveValue parseScaledInteger(String string) {
        int n2 = UIDefaultsLoader.parseInteger(string, (boolean)((long)1902682551 ^ (long)1902682550));
        return uIDefaults -> UIScale.scale(n2);
    }

    private static UIDefaults.ActiveValue parseScaledFloat(String string) {
        float f2 = UIDefaultsLoader.parseFloat(string, (boolean)((long)-691098236 ^ (long)-691098235)).floatValue();
        return uIDefaults -> Float.valueOf(UIScale.scale(f2));
    }

    private static UIDefaults.ActiveValue parseScaledInsets(String string) {
        Insets insets = UIDefaultsLoader.parseInsets(string);
        return uIDefaults -> UIScale.scale(insets);
    }

    private static UIDefaults.ActiveValue parseScaledDimension(String string) {
        Dimension dimension = UIDefaultsLoader.parseDimension(string);
        return uIDefaults -> UIScale.scale(dimension);
    }

    private static Object parseGrayFilter(String string) {
        List<String> list = UIDefaultsLoader.split(string, ((int)-2103914638L ^ 0x8298CF79) << 2);
        try {
            int n2 = Integer.parseInt(list.get((int)763929995L ^ 0x2D88A58B));
            int n3 = Integer.parseInt(list.get((int)-1107196709L ^ 0xBE0184DA));
            int n4 = Integer.parseInt(list.get((int)((long)518205833 ^ (long)518205832) << 1));
            return uIDefaults -> new GrayFilter(n2, n3, n4);
        }
        catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("invalid gray filter '" + string + "'");
        }
    }

    private static List<String> split(String string, char c2) {
        List<String> list = StringUtils.split(string, c2);
        int n2 = list.size();
        for (int i2 = (int)((long)-1746228705 ^ (long)-1746228705); i2 < n2; ++i2) {
            list.set(i2, list.get(i2).trim());
        }
        return list;
    }

    private static List<String> splitFunctionParams(String string, char c2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        int n2 = (int)-493379281L ^ 0xE297A12F;
        int n3 = (int)672801860L ^ 0x281A2444;
        int n4 = string.length();
        for (int i2 = (int)-667614749L ^ 0xD83501E3; i2 < n4; ++i2) {
            char c3 = string.charAt(i2);
            if (c3 == ((int)1074182355L ^ 0x4006B8D6) << 3) {
                ++n2;
                continue;
            }
            if (c3 == (int)((long)801594340 ^ (long)801594317)) {
                --n2;
                continue;
            }
            if (n2 != 0 || c3 != c2) continue;
            arrayList.add(string.substring(n3, i2).trim());
            n3 = i2 + ((int)456324647L ^ 0x1B32F626);
        }
        arrayList.add(string.substring(n3).trim());
        return arrayList;
    }

    private static Object lazyUIManagerGet(String string) {
        Object object;
        int n2 = (int)-1592821846L ^ 0xA10F77AA;
        if (string.startsWith(OPTIONAL_PREFIX)) {
            string = string.substring(OPTIONAL_PREFIX.length());
            n2 = (int)1116609712L ^ 0x428E1CB1;
        }
        if ((object = UIManager.get(string)) == null && n2 == 0) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: '" + string + "' not found in UI defaults.", null);
        }
        return object;
    }

    private static /* synthetic */ Object lambda$parseColorHSLIncreaseDecrease$7(String string, ColorFunctions.ColorFunction colorFunction, UIDefaults uIDefaults) {
        ColorUIResource colorUIResource;
        Object object = UIDefaultsLoader.lazyUIManagerGet(string);
        if (object instanceof Color) {
            ColorFunctions.ColorFunction[] arrcolorFunction = new ColorFunctions.ColorFunction[(int)-1929424697L ^ 0x8CFF50C6];
            arrcolorFunction[(int)-180860163L ^ -180860163] = colorFunction;
            colorUIResource = new ColorUIResource(ColorFunctions.applyFunctions((Color)object, arrcolorFunction));
        } else {
            colorUIResource = null;
        }
        return colorUIResource;
    }

    private static /* synthetic */ Object lambda$parseValue$3(String string, UIDefaults uIDefaults) {
        return UIDefaultsLoader.lazyUIManagerGet(string);
    }

    static {
        tempResultValueType = new ValueType[(int)343241172L ^ 0x147571D5];
    }

    static final class ValueType
    extends Enum<ValueType> {
        public static final /* enum */ ValueType UNKNOWN = new ValueType();
        public static final /* enum */ ValueType STRING = new ValueType();
        public static final /* enum */ ValueType BOOLEAN = new ValueType();
        public static final /* enum */ ValueType CHARACTER = new ValueType();
        public static final /* enum */ ValueType INTEGER = new ValueType();
        public static final /* enum */ ValueType FLOAT = new ValueType();
        public static final /* enum */ ValueType BORDER = new ValueType();
        public static final /* enum */ ValueType ICON = new ValueType();
        public static final /* enum */ ValueType INSETS = new ValueType();
        public static final /* enum */ ValueType DIMENSION = new ValueType();
        public static final /* enum */ ValueType COLOR = new ValueType();
        public static final /* enum */ ValueType SCALEDINTEGER = new ValueType();
        public static final /* enum */ ValueType SCALEDFLOAT = new ValueType();
        public static final /* enum */ ValueType SCALEDINSETS = new ValueType();
        public static final /* enum */ ValueType SCALEDDIMENSION = new ValueType();
        public static final /* enum */ ValueType INSTANCE = new ValueType();
        public static final /* enum */ ValueType CLASS = new ValueType();
        public static final /* enum */ ValueType GRAYFILTER = new ValueType();
        public static final /* enum */ ValueType NULL = new ValueType();
        public static final /* enum */ ValueType LAZY = new ValueType();
        private static final /* synthetic */ ValueType[] $VALUES;

        public static ValueType[] values() {
            return (ValueType[])$VALUES.clone();
        }

        public static ValueType valueOf(String string) {
            return Enum.valueOf(ValueType.class, string);
        }

        static {
            ValueType[] arrvalueType = new ValueType[((int)-265377371L ^ 0xF02EA9A0) << 2];
            arrvalueType[(int)-1287861445L ^ -1287861445] = UNKNOWN;
            arrvalueType[(int)((long)-815848720 ^ (long)-815848719)] = STRING;
            arrvalueType[((int)-1100143490L ^ -1100143489) << 1] = BOOLEAN;
            arrvalueType[(int)1341143098L ^ 1341143097] = CHARACTER;
            arrvalueType[((int)570901725L ^ 570901724) << 2] = INTEGER;
            arrvalueType[(int)973978405L ^ 973978400] = FLOAT;
            arrvalueType[(int)((long)-1436065102 ^ (long)-1436065103) << 1] = BORDER;
            arrvalueType[(int)((long)-942747846 ^ (long)-942747843)] = ICON;
            arrvalueType[((int)-1294038161L ^ -1294038162) << 3] = INSETS;
            arrvalueType[(int)((long)918633844 ^ (long)918633853)] = DIMENSION;
            arrvalueType[((int)-1909381714L ^ -1909381717) << 1] = COLOR;
            arrvalueType[(int)1423391618L ^ 1423391625] = SCALEDINTEGER;
            arrvalueType[(int)((long)-350904812 ^ (long)-350904809) << 2] = SCALEDFLOAT;
            arrvalueType[(int)472742752L ^ 472742765] = SCALEDINSETS;
            arrvalueType[(int)((long)1194617554 ^ (long)1194617557) << 1] = SCALEDDIMENSION;
            arrvalueType[(int)((long)-451311014 ^ (long)-451311019)] = INSTANCE;
            arrvalueType[(int)((long)-969243217 ^ (long)-969243218) << 4] = CLASS;
            arrvalueType[(int)((long)-1052368376 ^ (long)-1052368359)] = GRAYFILTER;
            arrvalueType[(int)((long)-990867575 ^ (long)-990867584) << 1] = NULL;
            arrvalueType[(int)((long)-2091448653 ^ (long)-2091448672)] = LAZY;
            $VALUES = arrvalueType;
        }
    }
}

