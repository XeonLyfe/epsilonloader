/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf;

import java.io.InputStream;
import javax.swing.LookAndFeel;
import javax.swing.UIDefaults;

public abstract class FlatDefaultsAddon {
    public InputStream getDefaults(Class<?> class_) {
        Class<?> class_2 = this.getClass();
        String string = (char)((long)-410962702 ^ (long)-410962723) + class_2.getPackage().getName().replace(((int)-390898503L ^ 0xE8B35CAE) << 1, (char)((int)-1986705165L ^ 0x899548DC)) + (char)((long)-1553053010 ^ (long)-1553053055) + class_.getSimpleName() + ".properties";
        return class_2.getResourceAsStream(string);
    }

    public void afterDefaultsLoading(LookAndFeel lookAndFeel, UIDefaults uIDefaults) {
    }

    public int getPriority() {
        return ((int)310873498L ^ 0x12878FEB) << 4;
    }
}

