/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.LaunchClassLoader
 */
package com.loader.epsilon;

import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$t;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import net.minecraft.launchwrapper.LaunchClassLoader;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class $$$$$$$$ESKID$$$$$$$$$i {
    public static boolean a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t) {
        return $$$$$$$$ESKID$$$$$$$$$t.g();
    }

    public static File a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t) {
        return $$$$$$$$ESKID$$$$$$$$$t.a();
    }

    public static URI a(File file) {
        return file.toURI();
    }

    public static URL a(URI uRI) {
        return uRI.toURL();
    }

    public static void a(LaunchClassLoader launchClassLoader, URL uRL) {
        launchClassLoader.addURL(uRL);
    }

    public static void a(MalformedURLException malformedURLException) {
        malformedURLException.printStackTrace();
    }

    public static byte[] a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t) {
        return $$$$$$$$ESKID$$$$$$$$$t.d();
    }

    public static void a(FileOutputStream fileOutputStream, byte[] arrby) {
        fileOutputStream.write(arrby);
    }

    public static void a(FileOutputStream fileOutputStream) {
        fileOutputStream.flush();
    }

    public static void b(FileOutputStream fileOutputStream) {
        fileOutputStream.close();
    }

    public static void a(File file) {
        file.deleteOnExit();
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t, byte[] arrby) {
        $$$$$$$$ESKID$$$$$$$$$t.e(arrby);
    }

    public static ArrayList a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t) {
        return $$$$$$$$ESKID$$$$$$$$$t.f();
    }

    public static Class a(LaunchClassLoader launchClassLoader, String string) {
        return launchClassLoader.loadClass(string);
    }

    public static void a(ArrayList arrayList) {
        arrayList.clear();
    }

    public static Method a(Class class_, String string, Class[] arrclass) {
        return class_.getDeclaredMethod(string, arrclass);
    }

    public static void a(Method method, boolean bl) {
        method.setAccessible(bl);
    }

    public static Object a(Method method, Object object, Object[] arrobject) {
        return method.invoke(object, arrobject);
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t, boolean bl) {
        $$$$$$$$ESKID$$$$$$$$$t.h(bl);
    }

    public static String a(File file) {
        return file.getAbsolutePath();
    }

    public static String a(Object object) {
        return object.toString();
    }

    public static String a(String string) {
        return string.toUpperCase();
    }

    public static int a(Object object) {
        return object.hashCode();
    }
}

