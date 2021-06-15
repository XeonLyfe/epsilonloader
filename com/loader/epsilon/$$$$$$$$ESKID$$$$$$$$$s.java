/*
 * Decompiled with CFR 0.150.
 */
package com.loader.epsilon;

import java.awt.Image;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import kotlin.collections.IntIterator;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class $$$$$$$$ESKID$$$$$$$$$s {
    public static JDialog a(JOptionPane jOptionPane, String string) {
        return jOptionPane.createDialog(string);
    }

    public static URL a(Class class_, String string) {
        return class_.getResource(string);
    }

    public static Image a(ImageIcon imageIcon) {
        return imageIcon.getImage();
    }

    public static void a(JDialog jDialog, Image image) {
        jDialog.setIconImage(image);
    }

    public static void a(JDialog jDialog, boolean bl) {
        jDialog.setAlwaysOnTop(bl);
    }

    public static void b(JDialog jDialog, boolean bl) {
        jDialog.setVisible(bl);
    }

    public static void a(FileOutputStream fileOutputStream, byte[] arrby) {
        fileOutputStream.write(arrby);
    }

    public static void a(FileOutputStream fileOutputStream) {
        fileOutputStream.flush();
    }

    public static void c(FileOutputStream fileOutputStream) {
        fileOutputStream.close();
    }

    public static int a(String string) {
        return string.length();
    }

    public static int a(IntIterator intIterator) {
        return intIterator.nextInt();
    }

    public static char a(Character c2) {
        return c2.charValue();
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
}

