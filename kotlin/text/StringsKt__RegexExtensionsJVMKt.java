/*
 * Decompiled with CFR 0.150.
 */
package kotlin.text;

import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.text.Regex;
import kotlin.text.StringsKt__IndentKt;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u00a8\u0006\u0003"}, d2={"toRegex", "Lkotlin/text/Regex;", "Ljava/util/regex/Pattern;", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__RegexExtensionsJVMKt
extends StringsKt__IndentKt {
    @InlineOnly
    private static final Regex toRegex(Pattern pattern) {
        int n2 = 0;
        return new Regex(pattern);
    }
}
