/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.StringUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class LinuxFontPolicy {
    LinuxFontPolicy() {
    }

    static Font getFont() {
        return SystemInfo.isKDE ? LinuxFontPolicy.getKDEFont() : LinuxFontPolicy.getGnomeFont();
    }

    private static Font getGnomeFont() {
        String string;
        double d2;
        Object object = Toolkit.getDefaultToolkit().getDesktopProperty("gnome.Gtk/FontName");
        if (!(object instanceof String)) {
            object = "sans 10";
        }
        String string2 = "";
        int n2 = (int)((long)-1427740177 ^ (long)-1427740177);
        int n3 = (int)((long)-1857449488 ^ (long)-1857449483) << 1;
        StringTokenizer stringTokenizer = new StringTokenizer((String)object);
        while (stringTokenizer.hasMoreTokens()) {
            String string3 = stringTokenizer.nextToken();
            if (string3.equalsIgnoreCase("italic")) {
                n2 |= ((int)-1848183032L ^ 0x91D6F709) << 1;
                continue;
            }
            if (string3.equalsIgnoreCase("bold")) {
                n2 |= (int)((long)-603501039 ^ (long)-603501040);
                continue;
            }
            if (Character.isDigit(string3.charAt((int)1571875626L ^ 0x5DB0EB2A))) {
                try {
                    n3 = Integer.parseInt(string3);
                }
                catch (NumberFormatException numberFormatException) {}
                continue;
            }
            string2 = string2.isEmpty() ? string3 : string2 + (((int)1243168503L ^ 0x4A193EF6) << 5) + string3;
        }
        if (string2.startsWith("Ubuntu") && !SystemInfo.isJetBrainsJVM && !FlatSystemProperties.getBoolean("flatlaf.useUbuntuFont", (boolean)((long)2007269593 ^ (long)2007269593))) {
            string2 = "Liberation Sans";
        }
        if ((n3 = (int)((d2 = (double)n3 * LinuxFontPolicy.getGnomeFontScale()) + Double.longBitsToDouble((long)216817267 ^ 0x3FE000000CEC5E73L))) < (int)((long)-1516532442 ^ (long)-1516532441)) {
            n3 = (int)((long)1986445816 ^ (long)1986445817);
        }
        if ((string = LinuxFontPolicy.mapFcName(string2.toLowerCase())) != null) {
            string2 = string;
        }
        return LinuxFontPolicy.createFont(string2, n2, n3, d2);
    }

    private static Font createFont(String string, int n2, int n3, double d2) {
        Font font = FlatLaf.createCompositeFont(string, n2, n3);
        font = font.deriveFont(n2, (float)d2);
        return font;
    }

    private static double getGnomeFontScale() {
        if (LinuxFontPolicy.isSystemScaling()) {
            return Double.longBitsToDouble(0x7C5E5D08B63771E5L ^ 0x43AB085DE36224B0L);
        }
        Object object = Toolkit.getDefaultToolkit().getDesktopProperty("gnome.Xft/DPI");
        if (object instanceof Integer) {
            int n2 = (Integer)object / ((int)((long)1919015525 ^ (long)1919015524) << 10);
            if (n2 == (int)((long)-1665758769 ^ (long)1665758768)) {
                n2 = (int)((long)-1785387031 ^ (long)-1785387030) << 5;
            }
            if (n2 < ((int)14605834L ^ 0xDEDE13) << 1) {
                n2 = ((int)2015675725L ^ 0x7824C554) << 1;
            }
            return (double)n2 / Double.longBitsToDouble(0xF4C4602E74FA4195L ^ 0xB496602E74FA4195L);
        }
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getNormalizingTransform().getScaleY();
    }

    /*
     * Exception decompiling
     */
    private static String mapFcName(String var0) {
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

    private static Font getKDEFont() {
        double d2;
        double d3;
        List<String> list = LinuxFontPolicy.readConfig("kdeglobals");
        List<String> list2 = LinuxFontPolicy.readConfig("kcmfonts");
        String string = LinuxFontPolicy.getConfigEntry(list, "General", "font");
        String string2 = LinuxFontPolicy.getConfigEntry(list2, "General", "forceFontDPI");
        String string3 = "sansserif";
        int n2 = (int)((long)1965571547 ^ (long)1965571547);
        int n3 = ((int)414946392L ^ 0x18BB945D) << 1;
        if (string != null) {
            List<String> list3 = StringUtils.split(string, (int)((long)1415470556 ^ (long)1415470551) << 2);
            try {
                string3 = list3.get((int)((long)-1965332373 ^ (long)-1965332373));
                n3 = Integer.parseInt(list3.get((int)1810676750L ^ 0x6BECBC0F));
                if ("75".equals(list3.get(((int)-108223494L ^ 0xF98CA3FB) << 2))) {
                    n2 |= (int)-99145346L ^ 0xFA17297F;
                }
                if ("1".equals(list3.get((int)((long)124956906 ^ (long)124956911)))) {
                    n2 |= (int)((long)-1082899535 ^ (long)-1082899536) << 1;
                }
            }
            catch (RuntimeException runtimeException) {
                LoggingFacade.INSTANCE.logConfig("FlatLaf: Failed to parse 'font=" + string + "'.", runtimeException);
            }
        }
        int n4 = (int)((long)2007011223 ^ (long)2007011220) << 5;
        if (string2 != null && !LinuxFontPolicy.isSystemScaling()) {
            try {
                n4 = Integer.parseInt(string2);
                if (n4 <= 0) {
                    n4 = ((int)898723394L ^ 0x35916E41) << 5;
                }
                if (n4 < (int)((long)-1179933245 ^ (long)-1179933222) << 1) {
                    n4 = ((int)1350462662L ^ 0x507E6CDF) << 1;
                }
            }
            catch (NumberFormatException numberFormatException) {
                LoggingFacade.INSTANCE.logConfig("FlatLaf: Failed to parse 'forceFontDPI=" + string2 + "'.", numberFormatException);
            }
        }
        if ((n3 = (int)((d3 = (double)n3 * (d2 = (double)n4 / Double.longBitsToDouble((long)567012292 ^ 0x4052000021CBEBC4L))) + Double.longBitsToDouble((long)1938586553 ^ 0x3FE00000738C7BB9L))) < (int)((long)208260692 ^ (long)208260693)) {
            n3 = (int)-1284432811L ^ 0xB3711C54;
        }
        return LinuxFontPolicy.createFont(string3, n2, n3, d3);
    }

    private static List<String> readConfig(String string) {
        String string2;
        File file = new File(System.getProperty("user.home"));
        String[] arrstring = new String[(int)((long)120084856 ^ (long)120084859)];
        arrstring[(int)((long)-30981501 ^ (long)-30981501)] = ".config";
        arrstring[(int)249276593L ^ 249276592] = ".kde4/share/config";
        arrstring[(int)((long)-711579740 ^ (long)-711579739) << 1] = ".kde/share/config";
        String[] arrstring2 = arrstring;
        File file2 = null;
        Object object = arrstring2;
        int n2 = ((String[])object).length;
        for (int i2 = (int)-972315869L ^ 0xC60BA323; i2 < n2 && !(file2 = new File(file, (string2 = object[i2]) + "/" + string)).isFile(); ++i2) {
        }
        if (!file2.isFile()) {
            return Collections.emptyList();
        }
        object = new ArrayList(((int)104229093L ^ 0x63668FC) << 3);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file2));){
            String string3 = null;
            while ((string3 = bufferedReader.readLine()) != null) {
                ((ArrayList)object).add(string3);
            }
        }
        catch (IOException iOException) {
            LoggingFacade.INSTANCE.logConfig("FlatLaf: Failed to read '" + string + "'.", iOException);
        }
        return object;
    }

    private static String getConfigEntry(List<String> list, String string, String string2) {
        int n2 = string.length();
        int n3 = string2.length();
        int n4 = (int)142126290L ^ 0x878ACD2;
        for (String string3 : list) {
            if (n4 == 0) {
                if (string3.length() < n2 + ((int)((long)-785336733 ^ (long)-785336734) << 1) || string3.charAt((int)-697139073L ^ 0xD672807F) != ((int)-232318543L ^ 0xF22719EA) || string3.charAt(n2 + ((int)-1580393647L ^ 0xA1CD1B50)) != ((int)66480101L ^ 0x3F667B8) || string3.indexOf(string) != (int)((long)1315142497 ^ (long)1315142496)) continue;
                n4 = (int)-1455844825L ^ 0xA9399226;
                continue;
            }
            if (string3.startsWith("[")) {
                return null;
            }
            if (string3.length() < n3 + ((int)((long)278248585 ^ (long)278248584) << 1) || string3.charAt(n3) != (int)((long)-2026471044 ^ (long)-2026471103) || !string3.startsWith(string2)) continue;
            return string3.substring(n3 + (int)((long)1083394933 ^ (long)1083394932));
        }
        return null;
    }

    private static boolean isSystemScaling() {
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        return (UIScale.getSystemScaleFactor(graphicsConfiguration) > 1.0 ? (int)-910414391L ^ 0xC9BC2DC8 : (int)((long)595542704 ^ (long)595542704)) != 0;
    }
}

