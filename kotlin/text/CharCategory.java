/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b \b\u0086\u0001\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001-B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0002R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,\u00a8\u0006."}, d2={"Lkotlin/text/CharCategory;", "", "value", "", "code", "", "(Ljava/lang/String;IILjava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getValue", "()I", "contains", "", "char", "", "UNASSIGNED", "UPPERCASE_LETTER", "LOWERCASE_LETTER", "TITLECASE_LETTER", "MODIFIER_LETTER", "OTHER_LETTER", "NON_SPACING_MARK", "ENCLOSING_MARK", "COMBINING_SPACING_MARK", "DECIMAL_DIGIT_NUMBER", "LETTER_NUMBER", "OTHER_NUMBER", "SPACE_SEPARATOR", "LINE_SEPARATOR", "PARAGRAPH_SEPARATOR", "CONTROL", "FORMAT", "PRIVATE_USE", "SURROGATE", "DASH_PUNCTUATION", "START_PUNCTUATION", "END_PUNCTUATION", "CONNECTOR_PUNCTUATION", "OTHER_PUNCTUATION", "MATH_SYMBOL", "CURRENCY_SYMBOL", "MODIFIER_SYMBOL", "OTHER_SYMBOL", "INITIAL_QUOTE_PUNCTUATION", "FINAL_QUOTE_PUNCTUATION", "Companion", "kotlin-stdlib"})
public final class CharCategory
extends Enum<CharCategory> {
    public static final /* enum */ CharCategory UNASSIGNED;
    public static final /* enum */ CharCategory UPPERCASE_LETTER;
    public static final /* enum */ CharCategory LOWERCASE_LETTER;
    public static final /* enum */ CharCategory TITLECASE_LETTER;
    public static final /* enum */ CharCategory MODIFIER_LETTER;
    public static final /* enum */ CharCategory OTHER_LETTER;
    public static final /* enum */ CharCategory NON_SPACING_MARK;
    public static final /* enum */ CharCategory ENCLOSING_MARK;
    public static final /* enum */ CharCategory COMBINING_SPACING_MARK;
    public static final /* enum */ CharCategory DECIMAL_DIGIT_NUMBER;
    public static final /* enum */ CharCategory LETTER_NUMBER;
    public static final /* enum */ CharCategory OTHER_NUMBER;
    public static final /* enum */ CharCategory SPACE_SEPARATOR;
    public static final /* enum */ CharCategory LINE_SEPARATOR;
    public static final /* enum */ CharCategory PARAGRAPH_SEPARATOR;
    public static final /* enum */ CharCategory CONTROL;
    public static final /* enum */ CharCategory FORMAT;
    public static final /* enum */ CharCategory PRIVATE_USE;
    public static final /* enum */ CharCategory SURROGATE;
    public static final /* enum */ CharCategory DASH_PUNCTUATION;
    public static final /* enum */ CharCategory START_PUNCTUATION;
    public static final /* enum */ CharCategory END_PUNCTUATION;
    public static final /* enum */ CharCategory CONNECTOR_PUNCTUATION;
    public static final /* enum */ CharCategory OTHER_PUNCTUATION;
    public static final /* enum */ CharCategory MATH_SYMBOL;
    public static final /* enum */ CharCategory CURRENCY_SYMBOL;
    public static final /* enum */ CharCategory MODIFIER_SYMBOL;
    public static final /* enum */ CharCategory OTHER_SYMBOL;
    public static final /* enum */ CharCategory INITIAL_QUOTE_PUNCTUATION;
    public static final /* enum */ CharCategory FINAL_QUOTE_PUNCTUATION;
    private static final /* synthetic */ CharCategory[] $VALUES;
    private final int value;
    @NotNull
    private final String code;
    @NotNull
    public static final Companion Companion;

    static {
        CharCategory[] arrcharCategory = new CharCategory[30];
        CharCategory[] arrcharCategory2 = arrcharCategory;
        arrcharCategory[0] = UNASSIGNED = new CharCategory(0, "Cn");
        arrcharCategory[1] = UPPERCASE_LETTER = new CharCategory(1, "Lu");
        arrcharCategory[2] = LOWERCASE_LETTER = new CharCategory(2, "Ll");
        arrcharCategory[3] = TITLECASE_LETTER = new CharCategory(3, "Lt");
        arrcharCategory[4] = MODIFIER_LETTER = new CharCategory(4, "Lm");
        arrcharCategory[5] = OTHER_LETTER = new CharCategory(5, "Lo");
        arrcharCategory[6] = NON_SPACING_MARK = new CharCategory(6, "Mn");
        arrcharCategory[7] = ENCLOSING_MARK = new CharCategory(7, "Me");
        arrcharCategory[8] = COMBINING_SPACING_MARK = new CharCategory(8, "Mc");
        arrcharCategory[9] = DECIMAL_DIGIT_NUMBER = new CharCategory(9, "Nd");
        arrcharCategory[10] = LETTER_NUMBER = new CharCategory(10, "Nl");
        arrcharCategory[11] = OTHER_NUMBER = new CharCategory(11, "No");
        arrcharCategory[12] = SPACE_SEPARATOR = new CharCategory(12, "Zs");
        arrcharCategory[13] = LINE_SEPARATOR = new CharCategory(13, "Zl");
        arrcharCategory[14] = PARAGRAPH_SEPARATOR = new CharCategory(14, "Zp");
        arrcharCategory[15] = CONTROL = new CharCategory(15, "Cc");
        arrcharCategory[16] = FORMAT = new CharCategory(16, "Cf");
        arrcharCategory[17] = PRIVATE_USE = new CharCategory(18, "Co");
        arrcharCategory[18] = SURROGATE = new CharCategory(19, "Cs");
        arrcharCategory[19] = DASH_PUNCTUATION = new CharCategory(20, "Pd");
        arrcharCategory[20] = START_PUNCTUATION = new CharCategory(21, "Ps");
        arrcharCategory[21] = END_PUNCTUATION = new CharCategory(22, "Pe");
        arrcharCategory[22] = CONNECTOR_PUNCTUATION = new CharCategory(23, "Pc");
        arrcharCategory[23] = OTHER_PUNCTUATION = new CharCategory(24, "Po");
        arrcharCategory[24] = MATH_SYMBOL = new CharCategory(25, "Sm");
        arrcharCategory[25] = CURRENCY_SYMBOL = new CharCategory(26, "Sc");
        arrcharCategory[26] = MODIFIER_SYMBOL = new CharCategory(27, "Sk");
        arrcharCategory[27] = OTHER_SYMBOL = new CharCategory(28, "So");
        arrcharCategory[28] = INITIAL_QUOTE_PUNCTUATION = new CharCategory(29, "Pi");
        arrcharCategory[29] = FINAL_QUOTE_PUNCTUATION = new CharCategory(30, "Pf");
        $VALUES = arrcharCategory;
        Companion = new Companion(null);
    }

    public final boolean contains(char c2) {
        return Character.getType(c2) == this.value;
    }

    public final int getValue() {
        return this.value;
    }

    @NotNull
    public final String getCode() {
        return this.code;
    }

    private CharCategory(int n3, String string2) {
        this.value = n3;
        this.code = string2;
    }

    public static CharCategory[] values() {
        return (CharCategory[])$VALUES.clone();
    }

    public static CharCategory valueOf(String string) {
        return Enum.valueOf(CharCategory.class, string);
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2={"Lkotlin/text/CharCategory$Companion;", "", "()V", "valueOf", "Lkotlin/text/CharCategory;", "category", "", "kotlin-stdlib"})
    public static final class Companion {
        @NotNull
        public final CharCategory valueOf(int n2) {
            CharCategory charCategory;
            int n3 = n2;
            int n4 = n3;
            if (0 <= n4 && 16 >= n4) {
                charCategory = CharCategory.values()[n2];
            } else {
                n4 = n3;
                if (18 <= n4 && 30 >= n4) {
                    charCategory = CharCategory.values()[n2 - 1];
                } else {
                    throw (Throwable)new IllegalArgumentException("Category #" + n2 + " is not defined.");
                }
            }
            return charCategory;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

