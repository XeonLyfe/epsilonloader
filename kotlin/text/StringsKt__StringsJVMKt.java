/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.text;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.internal.InlineOnly;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0010\f\n\u0002\b\u0011\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0087\b\u001a\u0019\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a)\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a\f\u0010\u0017\u001a\u00020\u0002*\u00020\u0002H\u0007\u001a\u0014\u0010\u0017\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\u0015\u0010\u001a\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0011H\u0087\b\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010\u001d\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a\u001c\u0010 \u001a\u00020\u0011*\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a\f\u0010$\u001a\u00020\u0002*\u00020\u0014H\u0007\u001a \u0010$\u001a\u00020\u0002*\u00020\u00142\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0007\u001a\u0019\u0010&\u001a\u00020#*\u0004\u0018\u00010'2\b\u0010!\u001a\u0004\u0018\u00010'H\u0087\u0004\u001a \u0010&\u001a\u00020#*\u0004\u0018\u00010'2\b\u0010!\u001a\u0004\u0018\u00010'2\u0006\u0010\"\u001a\u00020#H\u0007\u001a\u0015\u0010&\u001a\u00020#*\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0087\b\u001a\u0015\u0010&\u001a\u00020#*\u00020\u00022\u0006\u0010(\u001a\u00020'H\u0087\b\u001a\f\u0010)\u001a\u00020\u0002*\u00020\u0002H\u0007\u001a\u0014\u0010)\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\f\u0010*\u001a\u00020\u0002*\u00020\rH\u0007\u001a*\u0010*\u001a\u00020\u0002*\u00020\r2\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\b\b\u0002\u0010+\u001a\u00020#H\u0007\u001a\f\u0010,\u001a\u00020\r*\u00020\u0002H\u0007\u001a*\u0010,\u001a\u00020\r*\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\b\b\u0002\u0010+\u001a\u00020#H\u0007\u001a\u001c\u0010-\u001a\u00020#*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a \u0010/\u001a\u00020#*\u0004\u0018\u00010\u00022\b\u0010!\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a2\u00100\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b\u00a2\u0006\u0002\u00104\u001a6\u00100\u001a\u00020\u0002*\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b\u00a2\u0006\u0004\b5\u00104\u001a*\u00100\u001a\u00020\u0002*\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b\u00a2\u0006\u0002\u00106\u001a:\u00100\u001a\u00020\u0002*\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b\u00a2\u0006\u0002\u00107\u001a>\u00100\u001a\u00020\u0002*\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b\u00a2\u0006\u0004\b5\u00107\u001a2\u00100\u001a\u00020\u0002*\u00020\u00042\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b\u00a2\u0006\u0002\u00108\u001a\r\u00109\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\n\u0010:\u001a\u00020#*\u00020'\u001a\r\u0010;\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010;\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\u001a\u001d\u0010<\u001a\u00020\u0011*\u00020\u00022\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010<\u001a\u00020\u0011*\u00020\u00022\u0006\u0010@\u001a\u00020\u00022\u0006\u0010?\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010A\u001a\u00020\u0011*\u00020\u00022\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010A\u001a\u00020\u0011*\u00020\u00022\u0006\u0010@\u001a\u00020\u00022\u0006\u0010?\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010B\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010C\u001a\u00020\u0011H\u0087\b\u001a4\u0010D\u001a\u00020#*\u00020'2\u0006\u0010E\u001a\u00020\u00112\u0006\u0010!\u001a\u00020'2\u0006\u0010F\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a4\u0010D\u001a\u00020#*\u00020\u00022\u0006\u0010E\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00022\u0006\u0010F\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a\u0012\u0010G\u001a\u00020\u0002*\u00020'2\u0006\u0010H\u001a\u00020\u0011\u001a$\u0010I\u001a\u00020\u0002*\u00020\u00022\u0006\u0010J\u001a\u00020>2\u0006\u0010K\u001a\u00020>2\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010I\u001a\u00020\u0002*\u00020\u00022\u0006\u0010L\u001a\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010J\u001a\u00020>2\u0006\u0010K\u001a\u00020>2\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010L\u001a\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a\"\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00020P*\u00020'2\u0006\u0010Q\u001a\u00020R2\b\b\u0002\u0010S\u001a\u00020\u0011\u001a\u001c\u0010T\u001a\u00020#*\u00020\u00022\u0006\u0010U\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010T\u001a\u00020#*\u00020\u00022\u0006\u0010U\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a\u0015\u0010V\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010V\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a\u0017\u0010W\u001a\u00020\r*\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a\r\u0010X\u001a\u00020\u0014*\u00020\u0002H\u0087\b\u001a3\u0010X\u001a\u00020\u0014*\u00020\u00022\u0006\u0010Y\u001a\u00020\u00142\b\b\u0002\u0010Z\u001a\u00020\u00112\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a \u0010X\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0007\u001a\r\u0010[\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010[\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\u001a\u0017\u0010\\\u001a\u00020R*\u00020\u00022\b\b\u0002\u0010]\u001a\u00020\u0011H\u0087\b\u001a\r\u0010^\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010^\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\u001a\r\u0010_\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010_\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\"%\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003*\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006`"}, d2={"CASE_INSENSITIVE_ORDER", "Ljava/util/Comparator;", "", "Lkotlin/Comparator;", "Lkotlin/String$Companion;", "getCASE_INSENSITIVE_ORDER", "(Lkotlin/jvm/internal/StringCompanionObject;)Ljava/util/Comparator;", "String", "stringBuffer", "Ljava/lang/StringBuffer;", "stringBuilder", "Ljava/lang/StringBuilder;", "bytes", "", "charset", "Ljava/nio/charset/Charset;", "offset", "", "length", "chars", "", "codePoints", "", "capitalize", "locale", "Ljava/util/Locale;", "codePointAt", "index", "codePointBefore", "codePointCount", "beginIndex", "endIndex", "compareTo", "other", "ignoreCase", "", "concatToString", "startIndex", "contentEquals", "", "charSequence", "decapitalize", "decodeToString", "throwOnInvalidSequence", "encodeToByteArray", "endsWith", "suffix", "equals", "format", "args", "", "", "(Ljava/lang/String;Ljava/util/Locale;[Ljava/lang/Object;)Ljava/lang/String;", "formatNullable", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "intern", "isBlank", "lowercase", "nativeIndexOf", "ch", "", "fromIndex", "str", "nativeLastIndexOf", "offsetByCodePoints", "codePointOffset", "regionMatches", "thisOffset", "otherOffset", "repeat", "n", "replace", "oldChar", "newChar", "oldValue", "newValue", "replaceFirst", "split", "", "regex", "Ljava/util/regex/Pattern;", "limit", "startsWith", "prefix", "substring", "toByteArray", "toCharArray", "destination", "destinationOffset", "toLowerCase", "toPattern", "flags", "toUpperCase", "uppercase", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__StringsJVMKt
extends StringsKt__StringNumberConversionsKt {
    @InlineOnly
    private static final int nativeIndexOf(String string, char c2, int n2) {
        int n3 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        char c3 = c2;
        boolean bl = false;
        return string2.indexOf(c3, n2);
    }

    @InlineOnly
    private static final int nativeIndexOf(String string, String string2, int n2) {
        int n3 = 0;
        String string3 = string;
        if (string3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string3.indexOf(string2, n2);
    }

    @InlineOnly
    private static final int nativeLastIndexOf(String string, char c2, int n2) {
        int n3 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        char c3 = c2;
        boolean bl = false;
        return string2.lastIndexOf(c3, n2);
    }

    @InlineOnly
    private static final int nativeLastIndexOf(String string, String string2, int n2) {
        int n3 = 0;
        String string3 = string;
        if (string3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string3.lastIndexOf(string2, n2);
    }

    public static final boolean equals(@Nullable String string, @Nullable String string2, boolean bl) {
        if (string == null) {
            return string2 == null;
        }
        return !bl ? string.equals(string2) : string.equalsIgnoreCase(string2);
    }

    public static /* synthetic */ boolean equals$default(String string, String string2, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return StringsKt.equals(string, string2, bl);
    }

    @NotNull
    public static final String replace(@NotNull String string, char c2, char c3, boolean bl) {
        Intrinsics.checkNotNullParameter(string, "$this$replace");
        if (!bl) {
            String string2 = string.replace(c2, c3);
            Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026replace(oldChar, newChar)");
            return string2;
        }
        int n2 = string.length();
        boolean bl2 = false;
        boolean bl3 = false;
        StringBuilder stringBuilder = new StringBuilder(n2);
        boolean bl4 = false;
        boolean bl5 = false;
        StringBuilder stringBuilder2 = stringBuilder;
        boolean bl6 = false;
        CharSequence charSequence = string;
        boolean bl7 = false;
        CharSequence charSequence2 = charSequence;
        for (int i2 = 0; i2 < charSequence2.length(); ++i2) {
            char c4;
            char c5 = c4 = charSequence2.charAt(i2);
            boolean bl8 = false;
            stringBuilder2.append(CharsKt.equals(c5, c2, bl) ? c3 : c5);
        }
        String string3 = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string3, "StringBuilder(capacity).\u2026builderAction).toString()");
        return string3;
    }

    public static /* synthetic */ String replace$default(String string, char c2, char c3, boolean bl, int n2, Object object) {
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.replace(string, c2, c3, bl);
    }

    @NotNull
    public static final String replace(@NotNull String string, @NotNull String string2, @NotNull String string3, boolean bl) {
        Intrinsics.checkNotNullParameter(string, "$this$replace");
        Intrinsics.checkNotNullParameter(string2, "oldValue");
        Intrinsics.checkNotNullParameter(string3, "newValue");
        String string4 = string;
        boolean bl2 = false;
        boolean bl3 = false;
        String string5 = string4;
        boolean bl4 = false;
        int n2 = StringsKt.indexOf((CharSequence)string5, string2, 0, bl);
        if (n2 < 0) {
            return string5;
        }
        int n3 = string2.length();
        int n4 = RangesKt.coerceAtLeast(n3, 1);
        int n5 = string5.length() - n3 + string3.length();
        if (n5 < 0) {
            throw (Throwable)new OutOfMemoryError();
        }
        StringBuilder stringBuilder = new StringBuilder(n5);
        int n6 = 0;
        do {
            stringBuilder.append(string5, n6, n2).append(string3);
            n6 = n2 + n3;
        } while (n2 < string5.length() && (n2 = StringsKt.indexOf((CharSequence)string5, string2, n2 + n4, bl)) > 0);
        String string6 = stringBuilder.append(string5, n6, string5.length()).toString();
        Intrinsics.checkNotNullExpressionValue(string6, "stringBuilder.append(this, i, length).toString()");
        return string6;
    }

    public static /* synthetic */ String replace$default(String string, String string2, String string3, boolean bl, int n2, Object object) {
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.replace(string, string2, string3, bl);
    }

    @NotNull
    public static final String replaceFirst(@NotNull String string, char c2, char c3, boolean bl) {
        String string2;
        Intrinsics.checkNotNullParameter(string, "$this$replaceFirst");
        int n2 = StringsKt.indexOf$default((CharSequence)string, c2, 0, bl, 2, null);
        if (n2 < 0) {
            string2 = string;
        } else {
            String string3 = string;
            int n3 = n2 + 1;
            CharSequence charSequence = String.valueOf(c3);
            boolean bl2 = false;
            string2 = ((Object)StringsKt.replaceRange((CharSequence)string3, n2, n3, charSequence)).toString();
        }
        return string2;
    }

    public static /* synthetic */ String replaceFirst$default(String string, char c2, char c3, boolean bl, int n2, Object object) {
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.replaceFirst(string, c2, c3, bl);
    }

    @NotNull
    public static final String replaceFirst(@NotNull String string, @NotNull String string2, @NotNull String string3, boolean bl) {
        String string4;
        Intrinsics.checkNotNullParameter(string, "$this$replaceFirst");
        Intrinsics.checkNotNullParameter(string2, "oldValue");
        Intrinsics.checkNotNullParameter(string3, "newValue");
        int n2 = StringsKt.indexOf$default((CharSequence)string, string2, 0, bl, 2, null);
        if (n2 < 0) {
            string4 = string;
        } else {
            String string5 = string;
            int n3 = n2 + string2.length();
            boolean bl2 = false;
            string4 = ((Object)StringsKt.replaceRange((CharSequence)string5, n2, n3, (CharSequence)string3)).toString();
        }
        return string4;
    }

    public static /* synthetic */ String replaceFirst$default(String string, String string2, String string3, boolean bl, int n2, Object object) {
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.replaceFirst(string, string2, string3, bl);
    }

    @Deprecated(message="Use uppercase() instead.", replaceWith=@ReplaceWith(imports={"java.util.Locale"}, expression="uppercase(Locale.getDefault())"))
    @DeprecatedSinceKotlin(warningSince="1.5")
    @InlineOnly
    private static final String toUpperCase(String string) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toUpperCase();
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).toUpperCase()");
        return string3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final String uppercase(String string) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026.toUpperCase(Locale.ROOT)");
        return string3;
    }

    @Deprecated(message="Use lowercase() instead.", replaceWith=@ReplaceWith(imports={"java.util.Locale"}, expression="lowercase(Locale.getDefault())"))
    @DeprecatedSinceKotlin(warningSince="1.5")
    @InlineOnly
    private static final String toLowerCase(String string) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).toLowerCase()");
        return string3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final String lowercase(String string) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026.toLowerCase(Locale.ROOT)");
        return string3;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final String concatToString(@NotNull char[] arrc) {
        Intrinsics.checkNotNullParameter(arrc, "$this$concatToString");
        boolean bl = false;
        return new String(arrc);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final String concatToString(@NotNull char[] arrc, int n2, int n3) {
        Intrinsics.checkNotNullParameter(arrc, "$this$concatToString");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(n2, n3, arrc.length);
        int n4 = n3 - n2;
        boolean bl = false;
        return new String(arrc, n2, n4);
    }

    public static /* synthetic */ String concatToString$default(char[] arrc, int n2, int n3, int n4, Object object) {
        if ((n4 & 1) != 0) {
            n2 = 0;
        }
        if ((n4 & 2) != 0) {
            n3 = arrc.length;
        }
        return StringsKt.concatToString(arrc, n2, n3);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final char[] toCharArray(@NotNull String string, int n2, int n3) {
        Intrinsics.checkNotNullParameter(string, "$this$toCharArray");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(n2, n3, string.length());
        String string2 = string;
        char[] arrc = new char[n3 - n2];
        int n4 = 0;
        boolean bl = false;
        string2.getChars(n2, n3, arrc, n4);
        return arrc;
    }

    public static /* synthetic */ char[] toCharArray$default(String string, int n2, int n3, int n4, Object object) {
        if ((n4 & 1) != 0) {
            n2 = 0;
        }
        if ((n4 & 2) != 0) {
            n3 = string.length();
        }
        return StringsKt.toCharArray(string, n2, n3);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final String decodeToString(@NotNull byte[] arrby) {
        Intrinsics.checkNotNullParameter(arrby, "$this$decodeToString");
        boolean bl = false;
        return new String(arrby, Charsets.UTF_8);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final String decodeToString(@NotNull byte[] arrby, int n2, int n3, boolean bl) {
        Intrinsics.checkNotNullParameter(arrby, "$this$decodeToString");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(n2, n3, arrby.length);
        if (!bl) {
            int n4 = n3 - n2;
            boolean bl2 = false;
            return new String(arrby, n2, n4, Charsets.UTF_8);
        }
        CharsetDecoder charsetDecoder = Charsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
        String string = charsetDecoder.decode(ByteBuffer.wrap(arrby, n2, n3 - n2)).toString();
        Intrinsics.checkNotNullExpressionValue(string, "decoder.decode(ByteBuffe\u2026- startIndex)).toString()");
        return string;
    }

    public static /* synthetic */ String decodeToString$default(byte[] arrby, int n2, int n3, boolean bl, int n4, Object object) {
        if ((n4 & 1) != 0) {
            n2 = 0;
        }
        if ((n4 & 2) != 0) {
            n3 = arrby.length;
        }
        if ((n4 & 4) != 0) {
            bl = false;
        }
        return StringsKt.decodeToString(arrby, n2, n3, bl);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final byte[] encodeToByteArray(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$encodeToByteArray");
        String string2 = string;
        Charset charset = Charsets.UTF_8;
        boolean bl = false;
        byte[] arrby = string2.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(arrby, "(this as java.lang.String).getBytes(charset)");
        return arrby;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final byte[] encodeToByteArray(@NotNull String string, int n2, int n3, boolean bl) {
        byte[] arrby;
        Intrinsics.checkNotNullParameter(string, "$this$encodeToByteArray");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(n2, n3, string.length());
        if (!bl) {
            String string2 = string;
            boolean bl2 = false;
            String string3 = string2.substring(n2, n3);
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            string2 = string3;
            Charset charset = Charsets.UTF_8;
            boolean bl3 = false;
            String string4 = string2;
            if (string4 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            byte[] arrby2 = string4.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(arrby2, "(this as java.lang.String).getBytes(charset)");
            return arrby2;
        }
        CharsetEncoder charsetEncoder = Charsets.UTF_8.newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
        ByteBuffer byteBuffer = charsetEncoder.encode(CharBuffer.wrap(string, n2, n3));
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            int n4 = byteBuffer.remaining();
            byte[] arrby3 = byteBuffer.array();
            Intrinsics.checkNotNull(arrby3);
            if (n4 == arrby3.length) {
                byte[] arrby4 = byteBuffer.array();
                arrby = arrby4;
                Intrinsics.checkNotNullExpressionValue(arrby4, "byteBuffer.array()");
                return arrby;
            }
        }
        byte[] arrby5 = new byte[byteBuffer.remaining()];
        boolean bl4 = false;
        boolean bl5 = false;
        byte[] arrby6 = arrby5;
        boolean bl6 = false;
        byteBuffer.get(arrby6);
        arrby = arrby5;
        return arrby;
    }

    public static /* synthetic */ byte[] encodeToByteArray$default(String string, int n2, int n3, boolean bl, int n4, Object object) {
        if ((n4 & 1) != 0) {
            n2 = 0;
        }
        if ((n4 & 2) != 0) {
            n3 = string.length();
        }
        if ((n4 & 4) != 0) {
            bl = false;
        }
        return StringsKt.encodeToByteArray(string, n2, n3, bl);
    }

    @InlineOnly
    private static final char[] toCharArray(String string) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        char[] arrc = string2.toCharArray();
        Intrinsics.checkNotNullExpressionValue(arrc, "(this as java.lang.String).toCharArray()");
        return arrc;
    }

    @InlineOnly
    private static final char[] toCharArray(String string, char[] arrc, int n2, int n3, int n4) {
        int n5 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        string2.getChars(n3, n4, arrc, n2);
        return arrc;
    }

    static /* synthetic */ char[] toCharArray$default(String string, char[] arrc, int n2, int n3, int n4, int n5, Object object) {
        if ((n5 & 2) != 0) {
            n2 = 0;
        }
        if ((n5 & 4) != 0) {
            n3 = 0;
        }
        if ((n5 & 8) != 0) {
            n4 = string.length();
        }
        n5 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        string2.getChars(n3, n4, arrc, n2);
        return arrc;
    }

    @InlineOnly
    private static final String format(String string, Object ... arrobject) {
        int n2 = 0;
        String string2 = String.format(string, Arrays.copyOf(arrobject, arrobject.length));
        Intrinsics.checkNotNullExpressionValue(string2, "java.lang.String.format(this, *args)");
        return string2;
    }

    @InlineOnly
    private static final String format(StringCompanionObject stringCompanionObject, String string, Object ... arrobject) {
        int n2 = 0;
        String string2 = String.format(string, Arrays.copyOf(arrobject, arrobject.length));
        Intrinsics.checkNotNullExpressionValue(string2, "java.lang.String.format(format, *args)");
        return string2;
    }

    @Deprecated(message="Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince="1.4")
    @InlineOnly
    private static final /* synthetic */ String format(String string, Locale locale, Object ... arrobject) {
        int n2 = 0;
        String string2 = String.format(locale, string, Arrays.copyOf(arrobject, arrobject.length));
        Intrinsics.checkNotNullExpressionValue(string2, "java.lang.String.format(locale, this, *args)");
        return string2;
    }

    @SinceKotlin(version="1.4")
    @JvmName(name="formatNullable")
    @InlineOnly
    private static final String formatNullable(String string, Locale locale, Object ... arrobject) {
        int n2 = 0;
        String string2 = String.format(locale, string, Arrays.copyOf(arrobject, arrobject.length));
        Intrinsics.checkNotNullExpressionValue(string2, "java.lang.String.format(locale, this, *args)");
        return string2;
    }

    @Deprecated(message="Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince="1.4")
    @InlineOnly
    private static final /* synthetic */ String format(StringCompanionObject stringCompanionObject, Locale locale, String string, Object ... arrobject) {
        int n2 = 0;
        String string2 = String.format(locale, string, Arrays.copyOf(arrobject, arrobject.length));
        Intrinsics.checkNotNullExpressionValue(string2, "java.lang.String.format(locale, format, *args)");
        return string2;
    }

    @SinceKotlin(version="1.4")
    @JvmName(name="formatNullable")
    @InlineOnly
    private static final String formatNullable(StringCompanionObject stringCompanionObject, Locale locale, String string, Object ... arrobject) {
        int n2 = 0;
        String string2 = String.format(locale, string, Arrays.copyOf(arrobject, arrobject.length));
        Intrinsics.checkNotNullExpressionValue(string2, "java.lang.String.format(locale, format, *args)");
        return string2;
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence charSequence, @NotNull Pattern pattern, int n2) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$split");
        Intrinsics.checkNotNullParameter(pattern, "regex");
        boolean bl = n2 >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Limit must be non-negative, but was " + n2 + '.';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        String[] arrstring = pattern.split(charSequence, n2 == 0 ? -1 : n2);
        Intrinsics.checkNotNullExpressionValue(arrstring, "regex.split(this, if (limit == 0) -1 else limit)");
        return ArraysKt.asList(arrstring);
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, Pattern pattern, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 0;
        }
        return StringsKt.split(charSequence, pattern, n2);
    }

    @InlineOnly
    private static final String substring(String string, int n2) {
        int n3 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.substring(n2);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).substring(startIndex)");
        return string3;
    }

    @InlineOnly
    private static final String substring(String string, int n2, int n3) {
        int n4 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.substring(n2, n3);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return string3;
    }

    public static final boolean startsWith(@NotNull String string, @NotNull String string2, boolean bl) {
        Intrinsics.checkNotNullParameter(string, "$this$startsWith");
        Intrinsics.checkNotNullParameter(string2, "prefix");
        if (!bl) {
            return string.startsWith(string2);
        }
        return StringsKt.regionMatches(string, 0, string2, 0, string2.length(), bl);
    }

    public static /* synthetic */ boolean startsWith$default(String string, String string2, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return StringsKt.startsWith(string, string2, bl);
    }

    public static final boolean startsWith(@NotNull String string, @NotNull String string2, int n2, boolean bl) {
        Intrinsics.checkNotNullParameter(string, "$this$startsWith");
        Intrinsics.checkNotNullParameter(string2, "prefix");
        if (!bl) {
            return string.startsWith(string2, n2);
        }
        return StringsKt.regionMatches(string, n2, string2, 0, string2.length(), bl);
    }

    public static /* synthetic */ boolean startsWith$default(String string, String string2, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.startsWith(string, string2, n2, bl);
    }

    public static final boolean endsWith(@NotNull String string, @NotNull String string2, boolean bl) {
        Intrinsics.checkNotNullParameter(string, "$this$endsWith");
        Intrinsics.checkNotNullParameter(string2, "suffix");
        if (!bl) {
            return string.endsWith(string2);
        }
        return StringsKt.regionMatches(string, string.length() - string2.length(), string2, 0, string2.length(), true);
    }

    public static /* synthetic */ boolean endsWith$default(String string, String string2, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return StringsKt.endsWith(string, string2, bl);
    }

    @InlineOnly
    private static final String String(byte[] arrby, int n2, int n3, Charset charset) {
        int n4 = 0;
        return new String(arrby, n2, n3, charset);
    }

    @InlineOnly
    private static final String String(byte[] arrby, Charset charset) {
        int n2 = 0;
        return new String(arrby, charset);
    }

    @InlineOnly
    private static final String String(byte[] arrby, int n2, int n3) {
        int n4 = 0;
        return new String(arrby, n2, n3, Charsets.UTF_8);
    }

    @InlineOnly
    private static final String String(byte[] arrby) {
        int n2 = 0;
        return new String(arrby, Charsets.UTF_8);
    }

    @InlineOnly
    private static final String String(char[] arrc) {
        int n2 = 0;
        return new String(arrc);
    }

    @InlineOnly
    private static final String String(char[] arrc, int n2, int n3) {
        int n4 = 0;
        return new String(arrc, n2, n3);
    }

    @InlineOnly
    private static final String String(int[] arrn, int n2, int n3) {
        int n4 = 0;
        return new String(arrn, n2, n3);
    }

    @InlineOnly
    private static final String String(StringBuffer stringBuffer) {
        int n2 = 0;
        return new String(stringBuffer);
    }

    @InlineOnly
    private static final String String(StringBuilder stringBuilder) {
        int n2 = 0;
        return new String(stringBuilder);
    }

    @InlineOnly
    private static final int codePointAt(String string, int n2) {
        int n3 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string2.codePointAt(n2);
    }

    @InlineOnly
    private static final int codePointBefore(String string, int n2) {
        int n3 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string2.codePointBefore(n2);
    }

    @InlineOnly
    private static final int codePointCount(String string, int n2, int n3) {
        int n4 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string2.codePointCount(n2, n3);
    }

    public static final int compareTo(@NotNull String string, @NotNull String string2, boolean bl) {
        Intrinsics.checkNotNullParameter(string, "$this$compareTo");
        Intrinsics.checkNotNullParameter(string2, "other");
        if (bl) {
            return string.compareToIgnoreCase(string2);
        }
        return string.compareTo(string2);
    }

    public static /* synthetic */ int compareTo$default(String string, String string2, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return StringsKt.compareTo(string, string2, bl);
    }

    @InlineOnly
    private static final boolean contentEquals(String string, CharSequence charSequence) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string2.contentEquals(charSequence);
    }

    @InlineOnly
    private static final boolean contentEquals(String string, StringBuffer stringBuffer) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string2.contentEquals(stringBuffer);
    }

    @SinceKotlin(version="1.5")
    public static final boolean contentEquals(@Nullable CharSequence charSequence, @Nullable CharSequence charSequence2) {
        boolean bl;
        if (charSequence instanceof String && charSequence2 != null) {
            String string = (String)charSequence;
            boolean bl2 = false;
            bl = string.contentEquals(charSequence2);
        } else {
            bl = StringsKt.contentEqualsImpl(charSequence, charSequence2);
        }
        return bl;
    }

    @SinceKotlin(version="1.5")
    public static final boolean contentEquals(@Nullable CharSequence charSequence, @Nullable CharSequence charSequence2, boolean bl) {
        return bl ? StringsKt.contentEqualsIgnoreCaseImpl(charSequence, charSequence2) : StringsKt.contentEquals(charSequence, charSequence2);
    }

    @InlineOnly
    private static final String intern(String string) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.intern();
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).intern()");
        return string3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final boolean isBlank(@NotNull CharSequence charSequence) {
        int n2;
        Intrinsics.checkNotNullParameter(charSequence, "$this$isBlank");
        if (charSequence.length() == 0) return true;
        Iterable iterable = StringsKt.getIndices(charSequence);
        boolean bl = false;
        if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
            return true;
        }
        Iterator iterator2 = iterable.iterator();
        do {
            int n3;
            if (!iterator2.hasNext()) return true;
            n2 = n3 = ((IntIterator)iterator2).nextInt();
            boolean bl2 = false;
        } while (CharsKt.isWhitespace(charSequence.charAt(n2)));
        return false;
    }

    @InlineOnly
    private static final int offsetByCodePoints(String string, int n2, int n3) {
        int n4 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string2.offsetByCodePoints(n2, n3);
    }

    public static final boolean regionMatches(@NotNull CharSequence charSequence, int n2, @NotNull CharSequence charSequence2, int n3, int n4, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$regionMatches");
        Intrinsics.checkNotNullParameter(charSequence2, "other");
        if (charSequence instanceof String && charSequence2 instanceof String) {
            return StringsKt.regionMatches((String)charSequence, n2, (String)charSequence2, n3, n4, bl);
        }
        return StringsKt.regionMatchesImpl(charSequence, n2, charSequence2, n3, n4, bl);
    }

    public static /* synthetic */ boolean regionMatches$default(CharSequence charSequence, int n2, CharSequence charSequence2, int n3, int n4, boolean bl, int n5, Object object) {
        if ((n5 & 0x10) != 0) {
            bl = false;
        }
        return StringsKt.regionMatches(charSequence, n2, charSequence2, n3, n4, bl);
    }

    public static final boolean regionMatches(@NotNull String string, int n2, @NotNull String string2, int n3, int n4, boolean bl) {
        Intrinsics.checkNotNullParameter(string, "$this$regionMatches");
        Intrinsics.checkNotNullParameter(string2, "other");
        return !bl ? string.regionMatches(n2, string2, n3, n4) : string.regionMatches(bl, n2, string2, n3, n4);
    }

    public static /* synthetic */ boolean regionMatches$default(String string, int n2, String string2, int n3, int n4, boolean bl, int n5, Object object) {
        if ((n5 & 0x10) != 0) {
            bl = false;
        }
        return StringsKt.regionMatches(string, n2, string2, n3, n4, bl);
    }

    @Deprecated(message="Use lowercase() instead.", replaceWith=@ReplaceWith(imports={}, expression="lowercase(locale)"))
    @DeprecatedSinceKotlin(warningSince="1.5")
    @InlineOnly
    private static final String toLowerCase(String string, Locale locale) {
        int n2 = 0;
        String string2 = string;
        boolean bl = false;
        String string3 = string2;
        if (string3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string4 = string3.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.String).toLowerCase(locale)");
        return string4;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final String lowercase(String string, Locale locale) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).toLowerCase(locale)");
        return string3;
    }

    @Deprecated(message="Use uppercase() instead.", replaceWith=@ReplaceWith(imports={}, expression="uppercase(locale)"))
    @DeprecatedSinceKotlin(warningSince="1.5")
    @InlineOnly
    private static final String toUpperCase(String string, Locale locale) {
        int n2 = 0;
        String string2 = string;
        boolean bl = false;
        String string3 = string2;
        if (string3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string4 = string3.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.String).toUpperCase(locale)");
        return string4;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final String uppercase(String string, Locale locale) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).toUpperCase(locale)");
        return string3;
    }

    @InlineOnly
    private static final byte[] toByteArray(String string, Charset charset) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] arrby = string2.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(arrby, "(this as java.lang.String).getBytes(charset)");
        return arrby;
    }

    static /* synthetic */ byte[] toByteArray$default(String string, Charset charset, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] arrby = string2.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(arrby, "(this as java.lang.String).getBytes(charset)");
        return arrby;
    }

    @InlineOnly
    private static final Pattern toPattern(String string, int n2) {
        int n3 = 0;
        Pattern pattern = Pattern.compile(string, n2);
        Intrinsics.checkNotNullExpressionValue(pattern, "java.util.regex.Pattern.compile(this, flags)");
        return pattern;
    }

    static /* synthetic */ Pattern toPattern$default(String string, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            n2 = 0;
        }
        n3 = 0;
        Pattern pattern = Pattern.compile(string, n2);
        Intrinsics.checkNotNullExpressionValue(pattern, "java.util.regex.Pattern.compile(this, flags)");
        return pattern;
    }

    @Deprecated(message="Use replaceFirstChar instead.", replaceWith=@ReplaceWith(imports={"java.util.Locale"}, expression="replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }"))
    @DeprecatedSinceKotlin(warningSince="1.5")
    @NotNull
    public static final String capitalize(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$capitalize");
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "Locale.getDefault()");
        return StringsKt.capitalize(string, locale);
    }

    @Deprecated(message="Use replaceFirstChar instead.", replaceWith=@ReplaceWith(imports={}, expression="replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }"))
    @DeprecatedSinceKotlin(warningSince="1.5")
    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @LowPriorityInOverloadResolution
    @NotNull
    public static final String capitalize(@NotNull String string, @NotNull Locale locale) {
        Intrinsics.checkNotNullParameter(string, "$this$capitalize");
        Intrinsics.checkNotNullParameter(locale, "locale");
        CharSequence charSequence = string;
        char c2 = '\u0000';
        if (charSequence.length() > 0) {
            char c3;
            c2 = c3 = string.charAt(0);
            boolean bl = false;
            if (Character.isLowerCase(c2)) {
                int n2;
                c2 = '\u0000';
                bl = false;
                StringBuilder stringBuilder = new StringBuilder();
                boolean bl2 = false;
                boolean bl3 = false;
                StringBuilder stringBuilder2 = stringBuilder;
                boolean bl4 = false;
                char c4 = c3;
                int n3 = 0;
                char c5 = Character.toTitleCase(c4);
                c4 = c3;
                n3 = 0;
                if (c5 != Character.toUpperCase(c4)) {
                    stringBuilder2.append(c5);
                } else {
                    String string2 = string;
                    n3 = 0;
                    n2 = 1;
                    boolean bl5 = false;
                    String string3 = string2.substring(n3, n2);
                    Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    string2 = string3;
                    Locale locale2 = locale;
                    n2 = 0;
                    String string4 = string2;
                    if (string4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string5 = string4.toUpperCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.String).toUpperCase(locale)");
                    stringBuilder2.append(string5);
                }
                String string6 = string;
                int n4 = 1;
                n2 = 0;
                String string7 = string6.substring(n4);
                Intrinsics.checkNotNullExpressionValue(string7, "(this as java.lang.String).substring(startIndex)");
                stringBuilder2.append(string7);
                String string8 = stringBuilder.toString();
                Intrinsics.checkNotNullExpressionValue(string8, "StringBuilder().apply(builderAction).toString()");
                return string8;
            }
        }
        return string;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Deprecated(message="Use replaceFirstChar instead.", replaceWith=@ReplaceWith(imports={"java.util.Locale"}, expression="replaceFirstChar { it.lowercase(Locale.getDefault()) }"))
    @DeprecatedSinceKotlin(warningSince="1.5")
    @NotNull
    public static final String decapitalize(@NotNull String string) {
        String string2;
        Intrinsics.checkNotNullParameter(string, "$this$decapitalize");
        CharSequence charSequence = string;
        int n2 = 0;
        if (charSequence.length() > 0) {
            char c2 = string.charAt(0);
            n2 = 0;
            if (!Character.isLowerCase(c2)) {
                StringBuilder stringBuilder = new StringBuilder();
                String string3 = string;
                n2 = 0;
                int n3 = 1;
                boolean bl = false;
                String string4 = string3.substring(n2, n3);
                Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                string3 = string4;
                n2 = 0;
                String string5 = string3;
                if (string5 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String string6 = string5.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(string6, "(this as java.lang.String).toLowerCase()");
                StringBuilder stringBuilder2 = stringBuilder.append(string6);
                string3 = string;
                n2 = 1;
                n3 = 0;
                String string7 = string3.substring(n2);
                Intrinsics.checkNotNullExpressionValue(string7, "(this as java.lang.String).substring(startIndex)");
                string2 = stringBuilder2.append(string7).toString();
                return string2;
            }
        }
        string2 = string;
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Deprecated(message="Use replaceFirstChar instead.", replaceWith=@ReplaceWith(imports={}, expression="replaceFirstChar { it.lowercase(locale) }"))
    @DeprecatedSinceKotlin(warningSince="1.5")
    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @LowPriorityInOverloadResolution
    @NotNull
    public static final String decapitalize(@NotNull String string, @NotNull Locale locale) {
        String string2;
        Intrinsics.checkNotNullParameter(string, "$this$decapitalize");
        Intrinsics.checkNotNullParameter(locale, "locale");
        CharSequence charSequence = string;
        int n2 = 0;
        if (charSequence.length() > 0) {
            char c2 = string.charAt(0);
            n2 = 0;
            if (!Character.isLowerCase(c2)) {
                StringBuilder stringBuilder = new StringBuilder();
                String string3 = string;
                n2 = 0;
                int n3 = 1;
                boolean bl = false;
                String string4 = string3.substring(n2, n3);
                Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                string3 = string4;
                n2 = 0;
                String string5 = string3;
                if (string5 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String string6 = string5.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(string6, "(this as java.lang.String).toLowerCase(locale)");
                StringBuilder stringBuilder2 = stringBuilder.append(string6);
                string3 = string;
                n2 = 1;
                n3 = 0;
                String string7 = string3.substring(n2);
                Intrinsics.checkNotNullExpressionValue(string7, "(this as java.lang.String).substring(startIndex)");
                string2 = stringBuilder2.append(string7).toString();
                return string2;
            }
        }
        string2 = string;
        return string2;
    }

    @NotNull
    public static final String repeat(@NotNull CharSequence charSequence, int n2) {
        String string;
        Intrinsics.checkNotNullParameter(charSequence, "$this$repeat");
        char c2 = n2 >= 0 ? (char)'\u0001' : '\u0000';
        int n3 = 0;
        int n4 = 0;
        if (c2 == '\u0000') {
            boolean bl = false;
            String string2 = "Count 'n' must be non-negative, but was " + n2 + '.';
            throw (Throwable)new IllegalArgumentException(string2.toString());
        }
        block0 : switch (n2) {
            case 0: {
                string = "";
                break;
            }
            case 1: {
                string = ((Object)charSequence).toString();
                break;
            }
            default: {
                switch (charSequence.length()) {
                    case 0: {
                        string = "";
                        break block0;
                    }
                    case 1: {
                        c2 = charSequence.charAt(0);
                        n3 = 0;
                        n4 = 0;
                        char c3 = c2;
                        boolean bl = false;
                        int n5 = n2;
                        char[] arrc = new char[n5];
                        int n6 = 0;
                        while (n6 < n5) {
                            char c4;
                            int n7 = n6;
                            int n8 = n6++;
                            char[] arrc2 = arrc;
                            boolean bl2 = false;
                            arrc2[n8] = c4 = c3;
                        }
                        char[] arrc3 = arrc;
                        boolean bl3 = false;
                        string = new String(arrc3);
                        break block0;
                    }
                }
                StringBuilder stringBuilder = new StringBuilder(n2 * charSequence.length());
                n3 = 1;
                n4 = n2;
                if (n3 <= n4) {
                    while (true) {
                        stringBuilder.append(charSequence);
                        if (n3 == n4) break;
                        ++n3;
                    }
                }
                String string3 = stringBuilder.toString();
                string = string3;
                Intrinsics.checkNotNullExpressionValue(string3, "sb.toString()");
            }
        }
        return string;
    }

    @NotNull
    public static final Comparator<String> getCASE_INSENSITIVE_ORDER(@NotNull StringCompanionObject stringCompanionObject) {
        Intrinsics.checkNotNullParameter(stringCompanionObject, "$this$CASE_INSENSITIVE_ORDER");
        Comparator comparator = String.CASE_INSENSITIVE_ORDER;
        Intrinsics.checkNotNullExpressionValue(comparator, "java.lang.String.CASE_INSENSITIVE_ORDER");
        return comparator;
    }
}

