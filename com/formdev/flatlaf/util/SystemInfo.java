/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import java.util.Locale;
import java.util.StringTokenizer;

public class SystemInfo {
    public static final boolean isWindows;
    public static final boolean isMacOS;
    public static final boolean isLinux;
    public static final long osVersion;
    public static final boolean isWindows_10_orLater;
    public static final boolean isMacOS_10_11_ElCapitan_orLater;
    public static final boolean isMacOS_10_14_Mojave_orLater;
    public static final boolean isMacOS_10_15_Catalina_orLater;
    public static final boolean isX86_64;
    public static final long javaVersion;
    public static final boolean isJava_9_orLater;
    public static final boolean isJava_11_orLater;
    public static final boolean isJava_15_orLater;
    public static final boolean isJetBrainsJVM;
    public static final boolean isJetBrainsJVM_11_orLater;
    public static final boolean isKDE;
    public static final boolean isProjector;
    public static final boolean isWebswing;
    public static final boolean isWinPE;

    public static long scanVersion(String string) {
        int n2 = (int)((long)103478163 ^ (long)103478162);
        int n3 = (int)((long)1518700261 ^ (long)1518700261);
        int n4 = (int)-1673329189L ^ 0x9C4305DB;
        int n5 = (int)((long)-168358367 ^ (long)-168358367);
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(string, "._-+");
            n2 = Integer.parseInt(stringTokenizer.nextToken());
            n3 = Integer.parseInt(stringTokenizer.nextToken());
            n4 = Integer.parseInt(stringTokenizer.nextToken());
            n5 = Integer.parseInt(stringTokenizer.nextToken());
        }
        catch (Exception exception) {
            // empty catch block
        }
        return SystemInfo.toVersion(n2, n3, n4, n5);
    }

    public static long toVersion(int n2, int n3, int n4, int n5) {
        return ((long)n2 << (((int)1137445764L ^ 0x43CC0B87) << 4)) + ((long)n3 << (((int)-662407316L ^ 0xD884776D) << 5)) + ((long)n4 << (((int)-1266095420L ^ 0xB488EAC5) << 4)) + (long)n5;
    }

    static {
        String string = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        isWindows = string.startsWith("windows");
        isMacOS = string.startsWith("mac");
        isLinux = string.startsWith("linux");
        osVersion = SystemInfo.scanVersion(System.getProperty("os.version"));
        isWindows_10_orLater = isWindows && osVersion >= SystemInfo.toVersion((int)((long)1336684173 ^ (long)1336684168) << 1, (int)-420446999L ^ 0xE6F07CE9, (int)((long)185840172 ^ (long)185840172), (int)379954903L ^ 0x16A5A6D7) ? (int)((long)-1647619469 ^ (long)-1647619470) : (int)((long)1731303542 ^ (long)1731303542);
        isMacOS_10_11_ElCapitan_orLater = isMacOS && osVersion >= SystemInfo.toVersion((int)((long)-2101813104 ^ (long)-2101813099) << 1, (int)((long)1164120262 ^ (long)1164120269), (int)-408423634L ^ 0xE7A7F32E, (int)-1409060430L ^ 0xAC0371B2) ? (int)-1622301767L ^ 0x9F4DA3B8 : (int)((long)-859469252 ^ (long)-859469252);
        isMacOS_10_14_Mojave_orLater = isMacOS && osVersion >= SystemInfo.toVersion((int)((long)-238087895 ^ (long)-238087892) << 1, ((int)-1921595592L ^ 0x8D76C73F) << 1, (int)((long)1978351823 ^ (long)1978351823), (int)-675155808L ^ 0xD7C1F0A0) ? (int)((long)-812246264 ^ (long)-812246263) : (int)-1722505009L ^ 0x9954A8CF;
        isMacOS_10_15_Catalina_orLater = isMacOS && osVersion >= SystemInfo.toVersion((int)((long)825817841 ^ (long)825817844) << 1, (int)((long)122940088 ^ (long)122940087), (int)1115037034L ^ 0x42761D6A, (int)((long)-1552029672 ^ (long)-1552029672)) ? (int)146848447L ^ 0x8C0BABE : (int)-1931377447L ^ 0x8CE184D9;
        String string2 = System.getProperty("os.arch");
        isX86_64 = string2.equals("amd64") || string2.equals("x86_64") ? (int)((long)-147233726 ^ (long)-147233725) : (int)-1352058224L ^ 0xAF693A90;
        javaVersion = SystemInfo.scanVersion(System.getProperty("java.version"));
        isJava_9_orLater = javaVersion >= SystemInfo.toVersion((int)((long)-1150261314 ^ (long)-1150261321), (int)-2135626028L ^ 0x80B4EED4, (int)((long)-1032839498 ^ (long)-1032839498), (int)657668537L ^ 0x273339B9) ? (int)((long)-1734764607 ^ (long)-1734764608) : (int)((long)1431495122 ^ (long)1431495122);
        isJava_11_orLater = javaVersion >= SystemInfo.toVersion((int)-760155053L ^ 0xD2B0F458, (int)-835147319L ^ 0xCE38A9C9, (int)904811996L ^ 0x35EE55DC, (int)((long)-1669066395 ^ (long)-1669066395)) ? (int)-1441697729L ^ 0xAA11703E : (int)((long)-2079350416 ^ (long)-2079350416);
        isJava_15_orLater = javaVersion >= SystemInfo.toVersion((int)((long)1327839235 ^ (long)1327839244), (int)((long)-1388690094 ^ (long)-1388690094), (int)((long)1548867080 ^ (long)1548867080), (int)1772008427L ^ 0x699EB3EB) ? (int)((long)1199486744 ^ (long)1199486745) : (int)((long)-571104178 ^ (long)-571104178);
        isJetBrainsJVM = System.getProperty("java.vm.vendor", "Unknown").toLowerCase(Locale.ENGLISH).contains("jetbrains");
        isJetBrainsJVM_11_orLater = isJetBrainsJVM && isJava_11_orLater ? (int)((long)-441196550 ^ (long)-441196549) : (int)764284634L ^ 0x2D8E0EDA;
        isKDE = isLinux && System.getenv("KDE_FULL_SESSION") != null ? (int)-1904966361L ^ 0x8E748526 : (int)((long)1972835409 ^ (long)1972835409);
        isProjector = Boolean.getBoolean("org.jetbrains.projector.server.enable");
        isWebswing = System.getProperty("webswing.rootDir") != null ? (int)-1253718824L ^ 0xB545C4D9 : (int)-1634334188L ^ 0x9E960A14;
        isWinPE = isWindows && "X:\\Windows\\System32".equalsIgnoreCase(System.getProperty("user.dir")) ? (int)1376891959L ^ 0x5211B436 : (int)-746079828L ^ 0xD387B9AC;
    }
}

