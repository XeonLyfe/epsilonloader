/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.text;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000:\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a5\u0010\u0000\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0016\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u0005\"\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007\u001a9\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u00032\u0006\u0010\n\u001a\u0002H\u00012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fH\u0000\u00a2\u0006\u0002\u0010\r\u001a\u0015\u0010\u000e\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u0003H\u0087\b\u001a\u001d\u0010\u000e\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\u0006\u0010\u0004\u001a\u00020\u000fH\u0087\b\u001a\u001f\u0010\u000e\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0006H\u0087\b\u001a7\u0010\u0010\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0006\u0010\u0004\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0007\u00a2\u0006\u0002\u0010\u0014\u00a8\u0006\u0015"}, d2={"append", "T", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", "", "", "(Ljava/lang/Appendable;[Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "appendElement", "", "element", "transform", "Lkotlin/Function1;", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "appendLine", "", "appendRange", "startIndex", "", "endIndex", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;II)Ljava/lang/Appendable;", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__AppendableKt {
    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final <T extends Appendable> T appendRange(@NotNull T t2, @NotNull CharSequence charSequence, int n2, int n3) {
        Intrinsics.checkNotNullParameter(t2, "$this$appendRange");
        Intrinsics.checkNotNullParameter(charSequence, "value");
        Appendable appendable = t2.append(charSequence, n2, n3);
        if (appendable == null) {
            throw new NullPointerException("null cannot be cast to non-null type T");
        }
        return (T)appendable;
    }

    @NotNull
    public static final <T extends Appendable> T append(@NotNull T t2, CharSequence ... arrcharSequence) {
        Intrinsics.checkNotNullParameter(t2, "$this$append");
        Intrinsics.checkNotNullParameter(arrcharSequence, "value");
        for (CharSequence charSequence : arrcharSequence) {
            t2.append(charSequence);
        }
        return t2;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final Appendable appendLine(Appendable appendable) {
        int n2 = 0;
        Appendable appendable2 = appendable.append('\n');
        Intrinsics.checkNotNullExpressionValue(appendable2, "append('\\n')");
        return appendable2;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final Appendable appendLine(Appendable appendable, CharSequence charSequence) {
        int n2 = 0;
        Appendable appendable2 = appendable.append(charSequence);
        Intrinsics.checkNotNullExpressionValue(appendable2, "append(value)");
        Appendable appendable3 = appendable2;
        boolean bl = false;
        Appendable appendable4 = appendable3.append('\n');
        Intrinsics.checkNotNullExpressionValue(appendable4, "append('\\n')");
        return appendable4;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final Appendable appendLine(Appendable appendable, char c2) {
        int n2 = 0;
        Appendable appendable2 = appendable.append(c2);
        Intrinsics.checkNotNullExpressionValue(appendable2, "append(value)");
        Appendable appendable3 = appendable2;
        boolean bl = false;
        Appendable appendable4 = appendable3.append('\n');
        Intrinsics.checkNotNullExpressionValue(appendable4, "append('\\n')");
        return appendable4;
    }

    public static final <T> void appendElement(@NotNull Appendable appendable, T t2, @Nullable Function1<? super T, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(appendable, "$this$appendElement");
        if (function1 != null) {
            appendable.append(function1.invoke(t2));
        } else {
            T t3 = t2;
            if (t3 != null ? t3 instanceof CharSequence : true) {
                appendable.append((CharSequence)t2);
            } else if (t2 instanceof Character) {
                appendable.append(((Character)t2).charValue());
            } else {
                appendable.append(String.valueOf(t2));
            }
        }
    }
}

