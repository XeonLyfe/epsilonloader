/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.text;

import java.util.Locale;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.CharCategory;
import kotlin.text.CharDirectionality;
import kotlin.text.CharsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0001\u001a\u0018\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0000\u001a\r\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0010\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0011\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0012\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0013\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0014\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0016\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0018\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0019\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u001a\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u001b\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\n\u0010\u001c\u001a\u00020\u000f*\u00020\u0002\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0002H\u0087\b\u001a\u0014\u0010\u001d\u001a\u00020\u001e*\u00020\u00022\u0006\u0010\u001f\u001a\u00020 H\u0007\u001a\r\u0010!\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0014\u0010\"\u001a\u00020\u001e*\u00020\u00022\u0006\u0010\u001f\u001a\u00020 H\u0007\u001a\r\u0010#\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010$\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010%\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010&\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010'\u001a\u00020\u001e*\u00020\u0002H\u0087\b\u001a\u0014\u0010'\u001a\u00020\u001e*\u00020\u00022\u0006\u0010\u001f\u001a\u00020 H\u0007\u001a\r\u0010(\u001a\u00020\u0002*\u00020\u0002H\u0087\b\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006)"}, d2={"category", "Lkotlin/text/CharCategory;", "", "getCategory", "(C)Lkotlin/text/CharCategory;", "directionality", "Lkotlin/text/CharDirectionality;", "getDirectionality", "(C)Lkotlin/text/CharDirectionality;", "checkRadix", "", "radix", "digitOf", "char", "isDefined", "", "isDigit", "isHighSurrogate", "isISOControl", "isIdentifierIgnorable", "isJavaIdentifierPart", "isJavaIdentifierStart", "isLetter", "isLetterOrDigit", "isLowSurrogate", "isLowerCase", "isTitleCase", "isUpperCase", "isWhitespace", "lowercase", "", "locale", "Ljava/util/Locale;", "lowercaseChar", "titlecase", "titlecaseChar", "toLowerCase", "toTitleCase", "toUpperCase", "uppercase", "uppercaseChar", "kotlin-stdlib"}, xs="kotlin/text/CharsKt")
class CharsKt__CharJVMKt {
    @NotNull
    public static final CharCategory getCategory(char c2) {
        return CharCategory.Companion.valueOf(Character.getType(c2));
    }

    @InlineOnly
    private static final boolean isDefined(char c2) {
        int n2 = 0;
        return Character.isDefined(c2);
    }

    @InlineOnly
    private static final boolean isLetter(char c2) {
        int n2 = 0;
        return Character.isLetter(c2);
    }

    @InlineOnly
    private static final boolean isLetterOrDigit(char c2) {
        int n2 = 0;
        return Character.isLetterOrDigit(c2);
    }

    @InlineOnly
    private static final boolean isDigit(char c2) {
        int n2 = 0;
        return Character.isDigit(c2);
    }

    @InlineOnly
    private static final boolean isIdentifierIgnorable(char c2) {
        int n2 = 0;
        return Character.isIdentifierIgnorable(c2);
    }

    @InlineOnly
    private static final boolean isISOControl(char c2) {
        int n2 = 0;
        return Character.isISOControl(c2);
    }

    @InlineOnly
    private static final boolean isJavaIdentifierPart(char c2) {
        int n2 = 0;
        return Character.isJavaIdentifierPart(c2);
    }

    @InlineOnly
    private static final boolean isJavaIdentifierStart(char c2) {
        int n2 = 0;
        return Character.isJavaIdentifierStart(c2);
    }

    public static final boolean isWhitespace(char c2) {
        return Character.isWhitespace(c2) || Character.isSpaceChar(c2);
    }

    @InlineOnly
    private static final boolean isUpperCase(char c2) {
        int n2 = 0;
        return Character.isUpperCase(c2);
    }

    @InlineOnly
    private static final boolean isLowerCase(char c2) {
        int n2 = 0;
        return Character.isLowerCase(c2);
    }

    @Deprecated(message="Use uppercaseChar() instead.", replaceWith=@ReplaceWith(imports={}, expression="uppercaseChar()"))
    @DeprecatedSinceKotlin(warningSince="1.5")
    @InlineOnly
    private static final char toUpperCase(char c2) {
        int n2 = 0;
        char c3 = c2;
        boolean bl = false;
        return Character.toUpperCase(c3);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final char uppercaseChar(char c2) {
        int n2 = 0;
        return Character.toUpperCase(c2);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final String uppercase(char c2) {
        int n2 = 0;
        String string = String.valueOf(c2);
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026.toUpperCase(Locale.ROOT)");
        return string3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final String uppercase(char c2, @NotNull Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        String string = String.valueOf(c2);
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).toUpperCase(locale)");
        return string3;
    }

    @Deprecated(message="Use lowercaseChar() instead.", replaceWith=@ReplaceWith(imports={}, expression="lowercaseChar()"))
    @DeprecatedSinceKotlin(warningSince="1.5")
    @InlineOnly
    private static final char toLowerCase(char c2) {
        int n2 = 0;
        char c3 = c2;
        boolean bl = false;
        return Character.toLowerCase(c3);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final char lowercaseChar(char c2) {
        int n2 = 0;
        return Character.toLowerCase(c2);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final String lowercase(char c2) {
        int n2 = 0;
        String string = String.valueOf(c2);
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026.toLowerCase(Locale.ROOT)");
        return string3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final String lowercase(char c2, @NotNull Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        String string = String.valueOf(c2);
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).toLowerCase(locale)");
        return string3;
    }

    @InlineOnly
    private static final boolean isTitleCase(char c2) {
        int n2 = 0;
        return Character.isTitleCase(c2);
    }

    @Deprecated(message="Use titlecaseChar() instead.", replaceWith=@ReplaceWith(imports={}, expression="titlecaseChar()"))
    @DeprecatedSinceKotlin(warningSince="1.5")
    @InlineOnly
    private static final char toTitleCase(char c2) {
        int n2 = 0;
        char c3 = c2;
        boolean bl = false;
        return Character.toTitleCase(c3);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final char titlecaseChar(char c2) {
        int n2 = 0;
        return Character.toTitleCase(c2);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final String titlecase(char c2, @NotNull Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        String string = CharsKt.uppercase(c2, locale);
        if (string.length() > 1) {
            String string2;
            if (c2 == '\u0149') {
                string2 = string;
            } else {
                char c3 = string.charAt(0);
                String string3 = string;
                int n2 = 1;
                boolean bl = false;
                String string4 = string3;
                if (string4 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String string5 = string4.substring(n2);
                Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.String).substring(startIndex)");
                string3 = string5;
                n2 = 0;
                String string6 = string3;
                if (string6 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String string7 = string6.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(string7, "(this as java.lang.Strin\u2026.toLowerCase(Locale.ROOT)");
                string3 = string7;
                n2 = 0;
                string2 = String.valueOf(c3) + string3;
            }
            return string2;
        }
        char c4 = c2;
        boolean bl = false;
        String string8 = String.valueOf(c4);
        boolean bl2 = false;
        String string9 = string8;
        if (string9 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string10 = string9.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(string10, "(this as java.lang.Strin\u2026.toUpperCase(Locale.ROOT)");
        if (Intrinsics.areEqual(string, string10) ^ true) {
            return string;
        }
        c4 = c2;
        bl = false;
        return String.valueOf(Character.toTitleCase(c4));
    }

    @NotNull
    public static final CharDirectionality getDirectionality(char c2) {
        return CharDirectionality.Companion.valueOf(Character.getDirectionality(c2));
    }

    @InlineOnly
    private static final boolean isHighSurrogate(char c2) {
        int n2 = 0;
        return Character.isHighSurrogate(c2);
    }

    @InlineOnly
    private static final boolean isLowSurrogate(char c2) {
        int n2 = 0;
        return Character.isLowSurrogate(c2);
    }

    public static final int digitOf(char c2, int n2) {
        char c3 = c2;
        boolean bl = false;
        return Character.digit((int)c3, n2);
    }

    @PublishedApi
    public static final int checkRadix(int n2) {
        int n3 = n2;
        if (2 > n3 || 36 < n3) {
            n3 = 2;
            throw (Throwable)new IllegalArgumentException("radix " + n2 + " was not in valid range " + new IntRange(n3, 36));
        }
        return n2;
    }
}

