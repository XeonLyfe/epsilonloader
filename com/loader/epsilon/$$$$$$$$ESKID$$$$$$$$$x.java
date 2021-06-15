/*
 * Decompiled with CFR 0.150.
 */
package com.loader.epsilon;

import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$n;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$r;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$t;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$v;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class $$$$$$$$ESKID$$$$$$$$$x {
    public static String a(BufferedReader bufferedReader) {
        return bufferedReader.readLine();
    }

    public static boolean a($$$$$$$$ESKID$$$$$$$$$r $$$$$$$$ESKID$$$$$$$$$r, String string) {
        return $$$$$$$$ESKID$$$$$$$$$r.c(string);
    }

    public static void a(BufferedReader bufferedReader) {
        bufferedReader.close();
    }

    public static String a($$$$$$$$ESKID$$$$$$$$$n $$$$$$$$ESKID$$$$$$$$$n, String string, String string2) {
        return $$$$$$$$ESKID$$$$$$$$$n.c(string, string2);
    }

    public static void a(Exception exception) {
        exception.printStackTrace();
    }

    public static $$$$$$$$ESKID$$$$$$$$$v a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t) {
        return $$$$$$$$ESKID$$$$$$$$$t.i();
    }

    public static String a($$$$$$$$ESKID$$$$$$$$$v $$$$$$$$ESKID$$$$$$$$$v) {
        return $$$$$$$$ESKID$$$$$$$$$v.c();
    }

    public static String b($$$$$$$$ESKID$$$$$$$$$v $$$$$$$$ESKID$$$$$$$$$v) {
        return $$$$$$$$ESKID$$$$$$$$$v.a();
    }

    public static StringBuilder a(StringBuilder stringBuilder, String string) {
        return stringBuilder.append(string);
    }

    public static StringBuilder a(StringBuilder stringBuilder, char c2) {
        return stringBuilder.append(c2);
    }

    public static StringBuilder a(StringBuilder stringBuilder, boolean bl) {
        return stringBuilder.append(bl);
    }

    public static String a(StringBuilder stringBuilder) {
        return stringBuilder.toString();
    }

    public static String c($$$$$$$$ESKID$$$$$$$$$n $$$$$$$$ESKID$$$$$$$$$n, String string, String string2) {
        return $$$$$$$$ESKID$$$$$$$$$n.b(string, string2);
    }

    public static void a(BufferedWriter bufferedWriter, String string) {
        bufferedWriter.write(string);
    }

    public static boolean a(File file) {
        return file.exists();
    }

    public static File a(File file) {
        return file.getParentFile();
    }

    public static boolean d(File file) {
        return file.mkdirs();
    }

    public static boolean e(File file) {
        return file.createNewFile();
    }
}

