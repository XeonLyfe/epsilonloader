/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.text;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringBuilderJVMKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000F\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\r\n\u0000\u001a>\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\u0002\b\tH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a6\u0010\u0000\u001a\u00020\u00012\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\u0002\b\tH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a\u001f\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0087\b\u001a/\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u000e\"\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u000f\u001a/\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000e\"\u0004\u0018\u00010\u0001\u00a2\u0006\u0002\u0010\u0010\u001a\u0015\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u0007H\u0087\b\u001a\u001f\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0087\b\u001a\u001d\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\r\u001a\u00020\u0012H\u0087\b\u001a\u001d\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\r\u001a\u00020\u0013H\u0087\b\u001a\u001d\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\r\u001a\u00020\u0014H\u0087\b\u001a\u001f\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0015H\u0087\b\u001a\u001f\u0010\u0011\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0087\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u0016"}, d2={"buildString", "", "capacity", "", "builderAction", "Lkotlin/Function1;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "", "Lkotlin/ExtensionFunctionType;", "append", "obj", "", "value", "", "(Ljava/lang/StringBuilder;[Ljava/lang/Object;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[Ljava/lang/String;)Ljava/lang/StringBuilder;", "appendLine", "", "", "", "", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__StringBuilderKt
extends StringsKt__StringBuilderJVMKt {
    @Deprecated(message="Use append(value: Any?) instead", replaceWith=@ReplaceWith(imports={}, expression="append(value = obj)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder append(StringBuilder stringBuilder, Object object) {
        int n2 = 0;
        StringBuilder stringBuilder2 = stringBuilder.append(object);
        Intrinsics.checkNotNullExpressionValue(stringBuilder2, "this.append(obj)");
        return stringBuilder2;
    }

    @InlineOnly
    private static final String buildString(Function1<? super StringBuilder, Unit> function1) {
        int n2 = 0;
        boolean bl = false;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl2 = false;
        boolean bl3 = false;
        function1.invoke(stringBuilder);
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final String buildString(int n2, Function1<? super StringBuilder, Unit> function1) {
        int n3 = 0;
        boolean bl = false;
        StringBuilder stringBuilder = new StringBuilder(n2);
        boolean bl2 = false;
        boolean bl3 = false;
        function1.invoke(stringBuilder);
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).\u2026builderAction).toString()");
        return string;
    }

    @NotNull
    public static final StringBuilder append(@NotNull StringBuilder stringBuilder, String ... arrstring) {
        Intrinsics.checkNotNullParameter(stringBuilder, "$this$append");
        Intrinsics.checkNotNullParameter(arrstring, "value");
        for (String string : arrstring) {
            stringBuilder.append(string);
        }
        return stringBuilder;
    }

    @NotNull
    public static final StringBuilder append(@NotNull StringBuilder stringBuilder, Object ... arrobject) {
        Intrinsics.checkNotNullParameter(stringBuilder, "$this$append");
        Intrinsics.checkNotNullParameter(arrobject, "value");
        for (Object object : arrobject) {
            stringBuilder.append(object);
        }
        return stringBuilder;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder stringBuilder) {
        int n2 = 0;
        StringBuilder stringBuilder2 = stringBuilder.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder2, "append('\\n')");
        return stringBuilder2;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder stringBuilder, CharSequence charSequence) {
        int n2 = 0;
        StringBuilder stringBuilder2 = stringBuilder.append(charSequence);
        Intrinsics.checkNotNullExpressionValue(stringBuilder2, "append(value)");
        StringBuilder stringBuilder3 = stringBuilder2;
        boolean bl = false;
        StringBuilder stringBuilder4 = stringBuilder3.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder4, "append('\\n')");
        return stringBuilder4;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder stringBuilder, String string) {
        int n2 = 0;
        StringBuilder stringBuilder2 = stringBuilder.append(string);
        Intrinsics.checkNotNullExpressionValue(stringBuilder2, "append(value)");
        StringBuilder stringBuilder3 = stringBuilder2;
        boolean bl = false;
        StringBuilder stringBuilder4 = stringBuilder3.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder4, "append('\\n')");
        return stringBuilder4;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder stringBuilder, Object object) {
        int n2 = 0;
        StringBuilder stringBuilder2 = stringBuilder.append(object);
        Intrinsics.checkNotNullExpressionValue(stringBuilder2, "append(value)");
        StringBuilder stringBuilder3 = stringBuilder2;
        boolean bl = false;
        StringBuilder stringBuilder4 = stringBuilder3.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder4, "append('\\n')");
        return stringBuilder4;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder stringBuilder, char[] arrc) {
        int n2 = 0;
        StringBuilder stringBuilder2 = stringBuilder.append(arrc);
        Intrinsics.checkNotNullExpressionValue(stringBuilder2, "append(value)");
        StringBuilder stringBuilder3 = stringBuilder2;
        boolean bl = false;
        StringBuilder stringBuilder4 = stringBuilder3.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder4, "append('\\n')");
        return stringBuilder4;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder stringBuilder, char c2) {
        int n2 = 0;
        StringBuilder stringBuilder2 = stringBuilder.append(c2);
        Intrinsics.checkNotNullExpressionValue(stringBuilder2, "append(value)");
        StringBuilder stringBuilder3 = stringBuilder2;
        boolean bl = false;
        StringBuilder stringBuilder4 = stringBuilder3.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder4, "append('\\n')");
        return stringBuilder4;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder stringBuilder, boolean bl) {
        int n2 = 0;
        StringBuilder stringBuilder2 = stringBuilder.append(bl);
        Intrinsics.checkNotNullExpressionValue(stringBuilder2, "append(value)");
        StringBuilder stringBuilder3 = stringBuilder2;
        boolean bl2 = false;
        StringBuilder stringBuilder4 = stringBuilder3.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder4, "append('\\n')");
        return stringBuilder4;
    }
}
