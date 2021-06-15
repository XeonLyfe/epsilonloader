/*
 * Decompiled with CFR 0.150.
 */
package com.loader.epsilon;

import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$j;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$r;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$t;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$v;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$w;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class $$$$$$$$ESKID$$$$$$$$$m {
    public static String a($$$$$$$$ESKID$$$$$$$$$w $$$$$$$$ESKID$$$$$$$$$w) {
        return $$$$$$$$ESKID$$$$$$$$$w.a();
    }

    public static void a(JTextField jTextField, String string) {
        jTextField.setText(string);
    }

    public static void a(JPasswordField jPasswordField, String string) {
        jPasswordField.setText(string);
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

    public static void a(JCheckBox jCheckBox, boolean bl) {
        jCheckBox.setSelected(bl);
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$j $$$$$$$$ESKID$$$$$$$$$j, boolean bl) {
        $$$$$$$$ESKID$$$$$$$$$j.setVisible(bl);
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$j $$$$$$$$ESKID$$$$$$$$$j, int n2) {
        $$$$$$$$ESKID$$$$$$$$$j.setDefaultCloseOperation(n2);
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

    public static boolean a(Socket socket) {
        return socket.isClosed();
    }

    public static InputStream a(Socket socket) {
        return socket.getInputStream();
    }

    public static OutputStream a(Socket socket) {
        return socket.getOutputStream();
    }

    public static void a(JButton jButton, ActionListener actionListener) {
        jButton.addActionListener(actionListener);
    }

    public static boolean a($$$$$$$$ESKID$$$$$$$$$r $$$$$$$$ESKID$$$$$$$$$r, String string) {
        return $$$$$$$$ESKID$$$$$$$$$r.c(string);
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$r $$$$$$$$ESKID$$$$$$$$$r, String string, String string2) {
        $$$$$$$$ESKID$$$$$$$$$r.a(string, string2);
    }

    public static boolean c($$$$$$$$ESKID$$$$$$$$$r $$$$$$$$ESKID$$$$$$$$$r, String string) {
        return $$$$$$$$ESKID$$$$$$$$$r.d(string);
    }

    public static boolean d($$$$$$$$ESKID$$$$$$$$$r $$$$$$$$ESKID$$$$$$$$$r, String string) {
        return $$$$$$$$ESKID$$$$$$$$$r.e(string);
    }

    public static String a(JPasswordField jPasswordField) {
        return jPasswordField.getText();
    }

    public static String a(JTextField jTextField) {
        return jTextField.getText();
    }

    public static void a(JLabel jLabel, String string) {
        jLabel.setText(string);
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

    public static int a(String string) {
        return string.hashCode();
    }

    public static boolean a(String string, Object object) {
        return Integer.valueOf(object.hashCode()).equals(string.hashCode());
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t, $$$$$$$$ESKID$$$$$$$$$v $$$$$$$$ESKID$$$$$$$$$v) {
        $$$$$$$$ESKID$$$$$$$$$t.j($$$$$$$$ESKID$$$$$$$$$v);
    }

    public static void a(Thread thread2) {
        thread2.resume();
    }

    public static int a(Object object) {
        return object.hashCode();
    }
}

