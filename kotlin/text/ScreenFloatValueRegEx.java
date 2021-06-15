/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2={"Lkotlin/text/ScreenFloatValueRegEx;", "", "()V", "value", "Lkotlin/text/Regex;", "kotlin-stdlib"})
final class ScreenFloatValueRegEx {
    @JvmField
    @NotNull
    public static final Regex value;
    @NotNull
    public static final ScreenFloatValueRegEx INSTANCE;

    private ScreenFloatValueRegEx() {
    }

    static {
        ScreenFloatValueRegEx screenFloatValueRegEx;
        INSTANCE = screenFloatValueRegEx = new ScreenFloatValueRegEx();
        ScreenFloatValueRegEx screenFloatValueRegEx2 = screenFloatValueRegEx;
        boolean bl = false;
        boolean bl2 = false;
        ScreenFloatValueRegEx screenFloatValueRegEx3 = screenFloatValueRegEx2;
        boolean bl3 = false;
        String string = "(\\p{Digit}+)";
        String string2 = "(\\p{XDigit}+)";
        String string3 = "[eE][+-]?" + string;
        String string4 = "(0[xX]" + string2 + "(\\.)?)|" + "(0[xX]" + string2 + "?(\\.)" + string2 + ')';
        String string5 = '(' + string + "(\\.)?(" + string + "?)(" + string3 + ")?)|" + "(\\.(" + string + ")(" + string3 + ")?)|" + "((" + string4 + ")[pP][+-]?" + string + ')';
        String string6 = "[\\x00-\\x20]*[+-]?(NaN|Infinity|((" + string5 + ")[fFdD]?))[\\x00-\\x20]*";
        value = new Regex(string6);
    }
}

