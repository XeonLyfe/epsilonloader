/*
 * Decompiled with CFR 0.150.
 */
package com.intellij.uiDesigner.core;

import java.lang.reflect.Method;
import javax.swing.JComponent;

public final class SupportCode {
    public static TextWithMnemonic parseText(String string) {
        if (string == null) {
            throw new IllegalArgumentException("textWithMnemonic cannot be null");
        }
        int n2 = -1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < string.length(); ++i2) {
            char c2 = string.charAt(i2);
            if (c2 == '&') {
                if (++i2 >= string.length()) break;
                c2 = string.charAt(i2);
                if (c2 != '&') {
                    n2 = stringBuilder.length();
                }
            }
            stringBuilder.append(c2);
        }
        return new TextWithMnemonic(stringBuilder.toString(), n2);
    }

    public static void setDisplayedMnemonicIndex(JComponent jComponent, int n2) {
        try {
            Method method = jComponent.getClass().getMethod("setDisplayedMnemonicIndex", Integer.TYPE);
            method.setAccessible(true);
            method.invoke(jComponent, n2);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static final class TextWithMnemonic {
        public final String myText;
        public final int myMnemonicIndex;

        private TextWithMnemonic(String string, int n2) {
            if (string == null) {
                throw new IllegalArgumentException("text cannot be null");
            }
            if (n2 != -1 && (n2 < 0 || n2 >= string.length())) {
                throw new IllegalArgumentException("wrong index: " + n2 + "; text = '" + string + "'");
            }
            this.myText = string;
            this.myMnemonicIndex = n2;
        }

        public char getMnemonicChar() {
            if (this.myMnemonicIndex == -1) {
                throw new IllegalStateException("text doesn't contain mnemonic");
            }
            return Character.toUpperCase(this.myText.charAt(this.myMnemonicIndex));
        }
    }
}

