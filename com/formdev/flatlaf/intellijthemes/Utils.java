/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.util.LoggingFacade;
import java.io.IOException;

class Utils {
    Utils() {
    }

    static IntelliJTheme loadTheme(String string) {
        try {
            return new IntelliJTheme(Utils.class.getResourceAsStream("/com/formdev/flatlaf/intellijthemes/themes/" + string));
        }
        catch (IOException iOException) {
            String string2 = "FlatLaf: Failed to load IntelliJ theme '" + string + "'";
            LoggingFacade.INSTANCE.logSevere(string2, iOException);
            throw new RuntimeException(string2, iOException);
        }
    }
}

