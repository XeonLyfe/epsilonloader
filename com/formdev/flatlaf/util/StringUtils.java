/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    public static boolean isEmpty(String string) {
        return (string == null || string.isEmpty() ? (int)((long)-1828251938 ^ (long)-1828251937) : (int)((long)1471349071 ^ (long)1471349071)) != 0;
    }

    public static String removeLeading(String string, String string2) {
        return string.startsWith(string2) ? string.substring(string2.length()) : string;
    }

    public static String removeTrailing(String string, String string2) {
        return string.endsWith(string2) ? string.substring((int)786036662L ^ 0x2ED9F7B6, string.length() - string2.length()) : string;
    }

    public static List<String> split(String string, char c2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        int n2 = string.indexOf(c2);
        int n3 = (int)((long)1216593121 ^ (long)1216593121);
        while (n2 >= 0) {
            arrayList.add(string.substring(n3, n2));
            n3 = n2 + (int)((long)-2120405584 ^ (long)-2120405583);
            n2 = string.indexOf(c2, n3);
        }
        arrayList.add(string.substring(n3));
        return arrayList;
    }
}

