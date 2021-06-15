/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.text;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\f\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u00a8\u0006\u0003"}, d2={"titlecaseImpl", "", "", "kotlin-stdlib"})
public final class _OneToManyTitlecaseMappingsKt {
    @NotNull
    public static final String titlecaseImpl(char c2) {
        char c3 = c2;
        boolean bl = false;
        String string = String.valueOf(c3);
        boolean bl2 = false;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026.toUpperCase(Locale.ROOT)");
        String string4 = string3;
        if (string4.length() > 1) {
            String string5;
            if (c2 == '\u0149') {
                string5 = string4;
            } else {
                c3 = string4.charAt(0);
                String string6 = string4;
                int n2 = 1;
                bl2 = false;
                String string7 = string6;
                if (string7 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String string8 = string7.substring(n2);
                Intrinsics.checkNotNullExpressionValue(string8, "(this as java.lang.String).substring(startIndex)");
                string6 = string8;
                n2 = 0;
                String string9 = string6;
                if (string9 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String string10 = string9.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(string10, "(this as java.lang.Strin\u2026.toLowerCase(Locale.ROOT)");
                string6 = string10;
                n2 = 0;
                string5 = String.valueOf(c3) + string6;
            }
            return string5;
        }
        c3 = c2;
        bl = false;
        return String.valueOf(Character.toTitleCase(c3));
    }
}

