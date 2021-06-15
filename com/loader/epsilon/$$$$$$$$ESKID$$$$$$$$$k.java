/*
 * Decompiled with CFR 0.150.
 */
package com.loader.epsilon;

import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$j;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$t;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$v;
import com.loader.epsilon.$$$$$$$$ESKID$$$$$$$$$w;
import java.awt.Component;
import java.awt.Image;
import java.awt.LayoutManager;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class $$$$$$$$ESKID$$$$$$$$$k {
    public static Component a($$$$$$$$ESKID$$$$$$$$$j $$$$$$$$ESKID$$$$$$$$$j, Component component) {
        return $$$$$$$$ESKID$$$$$$$$$j.add(component);
    }

    public static URL a(Class class_, String string) {
        return class_.getResource(string);
    }

    public static Image a(ImageIcon imageIcon) {
        return imageIcon.getImage();
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$j $$$$$$$$ESKID$$$$$$$$$j, Image image) {
        $$$$$$$$ESKID$$$$$$$$$j.setIconImage(image);
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$j $$$$$$$$ESKID$$$$$$$$$j, String string) {
        $$$$$$$$ESKID$$$$$$$$$j.setTitle(string);
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$j $$$$$$$$ESKID$$$$$$$$$j, int n2, int n3) {
        $$$$$$$$ESKID$$$$$$$$$j.setSize(n2, n3);
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$j $$$$$$$$ESKID$$$$$$$$$j, Component component) {
        $$$$$$$$ESKID$$$$$$$$$j.setLocationRelativeTo(component);
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$j $$$$$$$$ESKID$$$$$$$$$j, boolean bl) {
        $$$$$$$$ESKID$$$$$$$$$j.setResizable(bl);
    }

    public static void a(Runtime runtime, Thread thread2) {
        runtime.addShutdownHook(thread2);
    }

    public static void a(JPanel jPanel, LayoutManager layoutManager) {
        jPanel.setLayout(layoutManager);
    }

    public static void a(JTabbedPane jTabbedPane, boolean bl) {
        jTabbedPane.setAutoscrolls(bl);
    }

    public static void b(JTabbedPane jTabbedPane, boolean bl) {
        jTabbedPane.setFocusable(bl);
    }

    public static void a(JPanel jPanel, Component component, Object object) {
        jPanel.add(component, object);
    }

    public static void a(JTabbedPane jTabbedPane, String string, Component component) {
        jTabbedPane.addTab(string, component);
    }

    public static void a(JButton jButton, String string) {
        jButton.setText(string);
    }

    public static void a(JCheckBox jCheckBox, String string) {
        jCheckBox.setText(string);
    }

    public static void a(JLabel jLabel, String string) {
        jLabel.setText(string);
    }

    public static Class a(Object object) {
        return object.getClass();
    }

    public static void a(JLabel jLabel, Icon icon) {
        jLabel.setIcon(icon);
    }

    public static $$$$$$$$ESKID$$$$$$$$$v a($$$$$$$$ESKID$$$$$$$$$t $$$$$$$$ESKID$$$$$$$$$t) {
        return $$$$$$$$ESKID$$$$$$$$$t.i();
    }

    public static boolean a($$$$$$$$ESKID$$$$$$$$$v $$$$$$$$ESKID$$$$$$$$$v) {
        return $$$$$$$$ESKID$$$$$$$$$v.e();
    }

    public static boolean a(JCheckBox jCheckBox) {
        return jCheckBox.isSelected();
    }

    public static void a($$$$$$$$ESKID$$$$$$$$$w $$$$$$$$ESKID$$$$$$$$$w, boolean bl) {
        $$$$$$$$ESKID$$$$$$$$$w.b(bl);
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

