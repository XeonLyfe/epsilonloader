/*
 * Decompiled with CFR 0.150.
 */
package com.loader.epsilon;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class $$$$$$$$ESKID$$$$$$$$$o {
    public static byte[] a(String string, Charset charset) {
        return string.getBytes(charset);
    }

    public static byte[] a(MessageDigest messageDigest, byte[] arrby) {
        return messageDigest.digest(arrby);
    }

    public static void a(NoSuchAlgorithmException noSuchAlgorithmException) {
        noSuchAlgorithmException.printStackTrace();
    }

    public static void a(Cipher cipher, int n2, Key key) {
        cipher.init(n2, key);
    }

    public static byte[] a(Cipher cipher, byte[] arrby) {
        return cipher.doFinal(arrby);
    }

    public static String a(Base64.Encoder encoder, byte[] arrby) {
        return encoder.encodeToString(arrby);
    }

    public static void a(PrintStream printStream, Object object) {
        printStream.println(object);
    }

    public static byte[] a(Base64.Decoder decoder, String string) {
        return decoder.decode(string);
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

