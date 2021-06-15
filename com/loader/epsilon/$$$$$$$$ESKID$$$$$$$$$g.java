/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package com.loader.epsilon;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$l;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$r;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$t;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$v;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$w;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class $$$$$$$$ESKID$$$$$$$$$g {
    public static void a($$$$$$$$ESKID$$$$$$$$$l $$$$$$$$ESKID$$$$$$$$$l, boolean bl, Thread thread2) {
        $$$$$$$$ESKID$$$$$$$$$l.a(bl, thread2);
    }

    public static String a($$$$$$$$ESKID$$$$$$$$$w $$$$$$$$ESKID$$$$$$$$$w) {
        return $$$$$$$$ESKID$$$$$$$$$w.a();
    }

    public static $$$$$$$$ESKID$$$$$$$$$v a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t) {
        return $$$$$$$$ESKID$$$$$$$$$t.i();
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$v $$$$$$$$ESKID$$$$$$$$$v, String string) {
        $$$$$$$$ESKID$$$$$$$$$v.b(string);
    }

    public static void b($$$$$$$$ESKID$$$$$$$$$v $$$$$$$$ESKID$$$$$$$$$v, String string) {
        $$$$$$$$ESKID$$$$$$$$$v.d(string);
    }

    public static String a($$$$$$$$ESKID$$$$$$$$$v $$$$$$$$ESKID$$$$$$$$$v) {
        return $$$$$$$$ESKID$$$$$$$$$v.a();
    }

    public static boolean a($$$$$$$$ESKID$$$$$$$$$r $$$$$$$$ESKID$$$$$$$$$r, String string) {
        return $$$$$$$$ESKID$$$$$$$$$r.c(string);
    }

    public static String c($$$$$$$$ESKID$$$$$$$$$v $$$$$$$$ESKID$$$$$$$$$v) {
        return $$$$$$$$ESKID$$$$$$$$$v.c();
    }

    public static void a(Thread thread2) {
        thread2.suspend();
    }

    public static Field a(Class class_, String string) {
        return class_.getDeclaredField(string);
    }

    public static void a(Field field, boolean bl) {
        field.setAccessible(bl);
    }

    public static Object a(Field field, Object object) {
        return field.get(object);
    }

    public static StringBuilder a(StringBuilder stringBuilder, String string) {
        return stringBuilder.append(string);
    }

    public static StringBuilder a(StringBuilder stringBuilder, Object object) {
        return stringBuilder.append(object);
    }

    public static int a(Runtime runtime) {
        return runtime.availableProcessors();
    }

    public static StringBuilder a(StringBuilder stringBuilder, int n2) {
        return stringBuilder.append(n2);
    }

    public static String a(StringBuilder stringBuilder) {
        return stringBuilder.toString();
    }

    public static InputStream a(Socket socket) {
        return socket.getInputStream();
    }

    public static OutputStream a(Socket socket) {
        return socket.getOutputStream();
    }

    public static StringBuilder a(StringBuilder stringBuilder, char c2) {
        return stringBuilder.append(c2);
    }

    public static void a(DataOutputStream dataOutputStream, String string) {
        dataOutputStream.writeUTF(string);
    }

    public static String a(DataInputStream dataInputStream) {
        return dataInputStream.readUTF();
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t, boolean bl) {
        $$$$$$$$ESKID$$$$$$$$$t.h(bl);
    }

    public static int a(DataInputStream dataInputStream) {
        return dataInputStream.readInt();
    }

    public static ZipEntry a(ZipInputStream zipInputStream) {
        return zipInputStream.getNextEntry();
    }

    public static String a(ZipEntry zipEntry) {
        return zipEntry.getName();
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t, byte[] arrby) {
        $$$$$$$$ESKID$$$$$$$$$t.c(arrby);
    }

    public static void d($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t, byte[] arrby) {
        $$$$$$$$ESKID$$$$$$$$$t.e(arrby);
    }

    public static byte[] a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t) {
        return $$$$$$$$ESKID$$$$$$$$$t.b();
    }

    public static Object a(Gson gson, String string, Class class_) {
        return gson.fromJson(string, class_);
    }

    public static JsonArray a(JsonObject jsonObject, String string) {
        return jsonObject.getAsJsonArray(string);
    }

    public static ArrayList a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t) {
        return $$$$$$$$ESKID$$$$$$$$$t.f();
    }

    public static String a(JsonElement jsonElement) {
        return jsonElement.getAsString();
    }

    public static boolean a(ArrayList arrayList, Object object) {
        return arrayList.add(object);
    }

    public static boolean a(Socket socket) {
        return socket.isClosed();
    }

    public static void a(Socket socket) {
        socket.close();
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$r $$$$$$$$ESKID$$$$$$$$$r, String string, String string2) {
        $$$$$$$$ESKID$$$$$$$$$r.a(string, string2);
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

    public static byte[] a(String string, Charset charset) {
        return string.getBytes(charset);
    }

    public static byte[] a(MessageDigest messageDigest, byte[] arrby) {
        return messageDigest.digest(arrby);
    }

    public static String a(String string) {
        return string.toLowerCase();
    }

    public static int a(Object object) {
        return object.hashCode();
    }
}

