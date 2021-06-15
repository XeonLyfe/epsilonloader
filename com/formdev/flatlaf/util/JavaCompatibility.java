/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.JComponent;

public class JavaCompatibility {
    private static Method drawStringUnderlineCharAtMethod;
    private static Method getClippedStringMethod;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void drawStringUnderlineCharAt(JComponent jComponent, Graphics graphics, String string, int n2, int n3, int n4) {
        Class<JavaCompatibility> class_ = JavaCompatibility.class;
        synchronized (JavaCompatibility.class) {
            if (drawStringUnderlineCharAtMethod == null) {
                try {
                    Class[] arrclass;
                    Class<?> class_2 = Class.forName(SystemInfo.isJava_9_orLater ? "javax.swing.plaf.basic.BasicGraphicsUtils" : "sun.swing.SwingUtilities2");
                    if (SystemInfo.isJava_9_orLater) {
                        Class[] arrclass2 = new Class[(int)((long)1678703731 ^ (long)1678703728) << 1];
                        arrclass2[(int)((long)2012874399 ^ (long)2012874399)] = JComponent.class;
                        arrclass2[(int)1521191709L ^ 1521191708] = Graphics2D.class;
                        arrclass2[((int)58654666L ^ 58654667) << 1] = String.class;
                        arrclass2[(int)((long)884673904 ^ (long)884673907)] = Integer.TYPE;
                        arrclass2[(int)((long)1912358560 ^ (long)1912358561) << 2] = Float.TYPE;
                        arrclass = arrclass2;
                        arrclass2[(int)((long)594660821 ^ (long)594660816)] = Float.TYPE;
                    } else {
                        Class[] arrclass3 = new Class[(int)((long)-1263696042 ^ (long)-1263696043) << 1];
                        arrclass3[(int)((long)-14616727 ^ (long)-14616727)] = JComponent.class;
                        arrclass3[(int)-1454692772L ^ -1454692771] = Graphics.class;
                        arrclass3[(int)((long)1972839061 ^ (long)1972839060) << 1] = String.class;
                        arrclass3[(int)-2103983664L ^ -2103983661] = Integer.TYPE;
                        arrclass3[((int)1327398003L ^ 1327398002) << 2] = Integer.TYPE;
                        arrclass = arrclass3;
                        arrclass3[(int)((long)1340358595 ^ (long)1340358598)] = Integer.TYPE;
                    }
                    drawStringUnderlineCharAtMethod = class_2.getMethod("drawStringUnderlineCharAt", arrclass);
                }
                catch (Exception exception) {
                    LoggingFacade.INSTANCE.logSevere(null, exception);
                    throw new RuntimeException(exception);
                }
            }
            // ** MonitorExit[var6_6] (shouldn't be in output)
            try {
                if (SystemInfo.isJava_9_orLater) {
                    Object[] arrobject = new Object[((int)1400467639L ^ 0x537970B4) << 1];
                    arrobject[(int)((long)-597915044 ^ (long)-597915044)] = jComponent;
                    arrobject[(int)((long)804649807 ^ (long)804649806)] = graphics;
                    arrobject[(int)((long)1093757978 ^ (long)1093757979) << 1] = string;
                    arrobject[(int)2110845429L ^ 2110845430] = n2;
                    arrobject[(int)((long)-2130194299 ^ (long)-2130194300) << 2] = Float.valueOf(n3);
                    arrobject[(int)((long)-753916759 ^ (long)-753916756)] = Float.valueOf(n4);
                    drawStringUnderlineCharAtMethod.invoke(null, arrobject);
                } else {
                    Object[] arrobject = new Object[((int)174222478L ^ 0xA626C8D) << 1];
                    arrobject[(int)((long)127099364 ^ (long)127099364)] = jComponent;
                    arrobject[(int)793903747L ^ 793903746] = graphics;
                    arrobject[((int)1117706981L ^ 1117706980) << 1] = string;
                    arrobject[(int)1936186315L ^ 1936186312] = n2;
                    arrobject[((int)-1765994625L ^ -1765994626) << 2] = n3;
                    arrobject[(int)-860888199L ^ -860888196] = n4;
                    drawStringUnderlineCharAtMethod.invoke(null, arrobject);
                }
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                LoggingFacade.INSTANCE.logSevere(null, exception);
                throw new RuntimeException(exception);
            }
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String getClippedString(JComponent jComponent, FontMetrics fontMetrics, String string, int n2) {
        Class<JavaCompatibility> class_ = JavaCompatibility.class;
        synchronized (JavaCompatibility.class) {
            if (getClippedStringMethod == null) {
                try {
                    Class<?> class_2 = Class.forName(SystemInfo.isJava_9_orLater ? "javax.swing.plaf.basic.BasicGraphicsUtils" : "sun.swing.SwingUtilities2");
                    Class[] arrclass = new Class[(int)((long)450064030 ^ (long)450064031) << 2];
                    arrclass[(int)((long)-828578855 ^ (long)-828578855)] = JComponent.class;
                    arrclass[(int)-895978947L ^ -895978948] = FontMetrics.class;
                    arrclass[(int)((long)1651431132 ^ (long)1651431133) << 1] = String.class;
                    arrclass[(int)-1933942811L ^ -1933942810] = Integer.TYPE;
                    getClippedStringMethod = class_2.getMethod(SystemInfo.isJava_9_orLater ? "getClippedString" : "clipStringIfNecessary", arrclass);
                }
                catch (Exception exception) {
                    LoggingFacade.INSTANCE.logSevere(null, exception);
                    throw new RuntimeException(exception);
                }
            }
            // ** MonitorExit[var4_4] (shouldn't be in output)
            try {
                Object[] arrobject = new Object[((int)1504639257L ^ 0x59AEF918) << 2];
                arrobject[(int)227361905L ^ 227361905] = jComponent;
                arrobject[(int)-471432214L ^ -471432213] = fontMetrics;
                arrobject[((int)-1118339705L ^ -1118339706) << 1] = string;
                arrobject[(int)1237314864L ^ 1237314867] = n2;
                return (String)getClippedStringMethod.invoke(null, arrobject);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                LoggingFacade.INSTANCE.logSevere(null, exception);
                throw new RuntimeException(exception);
            }
        }
    }
}

