/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.FlagEnum;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexKt;
import kotlin.text.RegexOption;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 ,2\u00060\u0001j\u0002`\u0002:\u0002,-B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bB\u001d\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n\u00a2\u0006\u0002\u0010\u000bB\u000f\b\u0001\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001d2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u0011\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086\u0004J\"\u0010 \u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170\"J\u0016\u0010 \u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004J\u0016\u0010$\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040&2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010'\u001a\u00020\u001bJ\u0006\u0010(\u001a\u00020\rJ\b\u0010)\u001a\u00020\u0004H\u0016J\b\u0010*\u001a\u00020+H\u0002R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0003\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006."}, d2={"Lkotlin/text/Regex;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "pattern", "", "(Ljava/lang/String;)V", "option", "Lkotlin/text/RegexOption;", "(Ljava/lang/String;Lkotlin/text/RegexOption;)V", "options", "", "(Ljava/lang/String;Ljava/util/Set;)V", "nativePattern", "Ljava/util/regex/Pattern;", "(Ljava/util/regex/Pattern;)V", "_options", "getOptions", "()Ljava/util/Set;", "getPattern", "()Ljava/lang/String;", "containsMatchIn", "", "input", "", "find", "Lkotlin/text/MatchResult;", "startIndex", "", "findAll", "Lkotlin/sequences/Sequence;", "matchEntire", "matches", "replace", "transform", "Lkotlin/Function1;", "replacement", "replaceFirst", "split", "", "limit", "toPattern", "toString", "writeReplace", "", "Companion", "Serialized", "kotlin-stdlib"})
public final class Regex
implements Serializable {
    private Set<? extends RegexOption> _options;
    private final Pattern nativePattern;
    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    public final String getPattern() {
        String string = this.nativePattern.pattern();
        Intrinsics.checkNotNullExpressionValue(string, "nativePattern.pattern()");
        return string;
    }

    @NotNull
    public final Set<RegexOption> getOptions() {
        Set<RegexOption> set = this._options;
        if (set == null) {
            int n2 = this.nativePattern.flags();
            boolean bl = false;
            EnumSet<RegexOption> enumSet = EnumSet.allOf(RegexOption.class);
            boolean bl2 = false;
            boolean bl3 = false;
            EnumSet<RegexOption> enumSet2 = enumSet;
            boolean bl4 = false;
            CollectionsKt.retainAll((Iterable)enumSet2, (Function1)new Function1<T, Boolean>(n2){
                final /* synthetic */ int $value$inlined;
                {
                    this.$value$inlined = n2;
                    super(1);
                }

                public final boolean invoke(T t2) {
                    return (this.$value$inlined & ((FlagEnum)t2).getMask()) == ((FlagEnum)t2).getValue();
                }
            });
            Set set2 = Collections.unmodifiableSet((Set)enumSet);
            Intrinsics.checkNotNullExpressionValue(set2, "Collections.unmodifiable\u2026mask == it.value }\n    })");
            Set set3 = set2;
            bl = false;
            boolean bl5 = false;
            Set set4 = set3;
            bl3 = false;
            this._options = set4;
            set = set3;
        }
        return set;
    }

    public final boolean matches(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "input");
        return this.nativePattern.matcher(charSequence).matches();
    }

    public final boolean containsMatchIn(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "input");
        return this.nativePattern.matcher(charSequence).find();
    }

    @Nullable
    public final MatchResult find(@NotNull CharSequence charSequence, int n2) {
        Intrinsics.checkNotNullParameter(charSequence, "input");
        Matcher matcher = this.nativePattern.matcher(charSequence);
        Intrinsics.checkNotNullExpressionValue(matcher, "nativePattern.matcher(input)");
        return RegexKt.access$findNext(matcher, n2, charSequence);
    }

    public static /* synthetic */ MatchResult find$default(Regex regex, CharSequence charSequence, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 0;
        }
        return regex.find(charSequence, n2);
    }

    @NotNull
    public final Sequence<MatchResult> findAll(@NotNull CharSequence charSequence, int n2) {
        Intrinsics.checkNotNullParameter(charSequence, "input");
        if (n2 < 0 || n2 > charSequence.length()) {
            throw (Throwable)new IndexOutOfBoundsException("Start index out of bounds: " + n2 + ", input length: " + charSequence.length());
        }
        return SequencesKt.generateSequence((Function0)new Function0<MatchResult>(this, charSequence, n2){
            final /* synthetic */ Regex this$0;
            final /* synthetic */ CharSequence $input;
            final /* synthetic */ int $startIndex;

            @Nullable
            public final MatchResult invoke() {
                return this.this$0.find(this.$input, this.$startIndex);
            }
            {
                this.this$0 = regex;
                this.$input = charSequence;
                this.$startIndex = n2;
                super(0);
            }
        }, (Function1)findAll.2.INSTANCE);
    }

    public static /* synthetic */ Sequence findAll$default(Regex regex, CharSequence charSequence, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 0;
        }
        return regex.findAll(charSequence, n2);
    }

    @Nullable
    public final MatchResult matchEntire(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "input");
        Matcher matcher = this.nativePattern.matcher(charSequence);
        Intrinsics.checkNotNullExpressionValue(matcher, "nativePattern.matcher(input)");
        return RegexKt.access$matchEntire(matcher, charSequence);
    }

    @NotNull
    public final String replace(@NotNull CharSequence charSequence, @NotNull String string) {
        Intrinsics.checkNotNullParameter(charSequence, "input");
        Intrinsics.checkNotNullParameter(string, "replacement");
        String string2 = this.nativePattern.matcher(charSequence).replaceAll(string);
        Intrinsics.checkNotNullExpressionValue(string2, "nativePattern.matcher(in\u2026).replaceAll(replacement)");
        return string2;
    }

    @NotNull
    public final String replace(@NotNull CharSequence charSequence, @NotNull Function1<? super MatchResult, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(charSequence, "input");
        Intrinsics.checkNotNullParameter(function1, "transform");
        MatchResult matchResult = Regex.find$default(this, charSequence, 0, 2, null);
        if (matchResult == null) {
            return ((Object)charSequence).toString();
        }
        MatchResult matchResult2 = matchResult;
        int n2 = 0;
        int n3 = charSequence.length();
        StringBuilder stringBuilder = new StringBuilder(n3);
        do {
            MatchResult matchResult3;
            Intrinsics.checkNotNull(matchResult2);
            stringBuilder.append(charSequence, n2, (int)matchResult3.getRange().getStart());
            stringBuilder.append(function1.invoke(matchResult3));
            n2 = matchResult3.getRange().getEndInclusive() + 1;
            matchResult2 = matchResult3.next();
        } while (n2 < n3 && matchResult2 != null);
        if (n2 < n3) {
            stringBuilder.append(charSequence, n2, n3);
        }
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }

    @NotNull
    public final String replaceFirst(@NotNull CharSequence charSequence, @NotNull String string) {
        Intrinsics.checkNotNullParameter(charSequence, "input");
        Intrinsics.checkNotNullParameter(string, "replacement");
        String string2 = this.nativePattern.matcher(charSequence).replaceFirst(string);
        Intrinsics.checkNotNullExpressionValue(string2, "nativePattern.matcher(in\u2026replaceFirst(replacement)");
        return string2;
    }

    @NotNull
    public final List<String> split(@NotNull CharSequence charSequence, int n2) {
        boolean bl;
        int n3;
        CharSequence charSequence2;
        Intrinsics.checkNotNullParameter(charSequence, "input");
        boolean bl2 = n2 >= 0;
        boolean bl3 = false;
        int n4 = 0;
        if (!bl2) {
            boolean bl4 = false;
            String string = "Limit must be non-negative, but was " + n2 + '.';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        Matcher matcher = this.nativePattern.matcher(charSequence);
        if (!matcher.find() || n2 == 1) {
            return CollectionsKt.listOf(((Object)charSequence).toString());
        }
        ArrayList<String> arrayList = new ArrayList<String>(n2 > 0 ? RangesKt.coerceAtMost(n2, 10) : 10);
        n4 = 0;
        int n5 = n2 - 1;
        do {
            charSequence2 = charSequence;
            n3 = matcher.start();
            bl = false;
            arrayList.add(((Object)charSequence2.subSequence(n4, n3)).toString());
            n4 = matcher.end();
        } while ((n5 < 0 || arrayList.size() != n5) && matcher.find());
        charSequence2 = charSequence;
        n3 = charSequence.length();
        bl = false;
        arrayList.add(((Object)charSequence2.subSequence(n4, n3)).toString());
        return arrayList;
    }

    public static /* synthetic */ List split$default(Regex regex, CharSequence charSequence, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 0;
        }
        return regex.split(charSequence, n2);
    }

    @NotNull
    public String toString() {
        String string = this.nativePattern.toString();
        Intrinsics.checkNotNullExpressionValue(string, "nativePattern.toString()");
        return string;
    }

    @NotNull
    public final Pattern toPattern() {
        return this.nativePattern;
    }

    private final Object writeReplace() {
        String string = this.nativePattern.pattern();
        Intrinsics.checkNotNullExpressionValue(string, "nativePattern.pattern()");
        return new Serialized(string, this.nativePattern.flags());
    }

    @PublishedApi
    public Regex(@NotNull Pattern pattern) {
        Intrinsics.checkNotNullParameter(pattern, "nativePattern");
        this.nativePattern = pattern;
    }

    public Regex(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "pattern");
        Pattern pattern = Pattern.compile(string);
        Intrinsics.checkNotNullExpressionValue(pattern, "Pattern.compile(pattern)");
        this(pattern);
    }

    public Regex(@NotNull String string, @NotNull RegexOption regexOption) {
        Intrinsics.checkNotNullParameter(string, "pattern");
        Intrinsics.checkNotNullParameter(regexOption, "option");
        Pattern pattern = Pattern.compile(string, Regex.Companion.ensureUnicodeCase(regexOption.getValue()));
        Intrinsics.checkNotNullExpressionValue(pattern, "Pattern.compile(pattern,\u2026nicodeCase(option.value))");
        this(pattern);
    }

    public Regex(@NotNull String string, @NotNull Set<? extends RegexOption> set) {
        Intrinsics.checkNotNullParameter(string, "pattern");
        Intrinsics.checkNotNullParameter(set, "options");
        Pattern pattern = Pattern.compile(string, Regex.Companion.ensureUnicodeCase(RegexKt.access$toInt(set)));
        Intrinsics.checkNotNullExpressionValue(pattern, "Pattern.compile(pattern,\u2026odeCase(options.toInt()))");
        this(pattern);
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \u000e2\u00060\u0001j\u0002`\u0002:\u0001\u000eB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0002R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000f"}, d2={"Lkotlin/text/Regex$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "pattern", "", "flags", "", "(Ljava/lang/String;I)V", "getFlags", "()I", "getPattern", "()Ljava/lang/String;", "readResolve", "", "Companion", "kotlin-stdlib"})
    private static final class Serialized
    implements Serializable {
        @NotNull
        private final String pattern;
        private final int flags;
        private static final long serialVersionUID = 0L;
        @NotNull
        public static final Companion Companion = new Companion(null);

        private final Object readResolve() {
            Pattern pattern = Pattern.compile(this.pattern, this.flags);
            Intrinsics.checkNotNullExpressionValue(pattern, "Pattern.compile(pattern, flags)");
            return new Regex(pattern);
        }

        @NotNull
        public final String getPattern() {
            return this.pattern;
        }

        public final int getFlags() {
            return this.flags;
        }

        public Serialized(@NotNull String string, int n2) {
            Intrinsics.checkNotNullParameter(string, "pattern");
            this.pattern = string;
            this.flags = n2;
        }

        @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2={"Lkotlin/text/Regex$Serialized$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007\u00a8\u0006\f"}, d2={"Lkotlin/text/Regex$Companion;", "", "()V", "ensureUnicodeCase", "", "flags", "escape", "", "literal", "escapeReplacement", "fromLiteral", "Lkotlin/text/Regex;", "kotlin-stdlib"})
    public static final class Companion {
        @NotNull
        public final Regex fromLiteral(@NotNull String string) {
            Intrinsics.checkNotNullParameter(string, "literal");
            String string2 = string;
            RegexOption regexOption = RegexOption.LITERAL;
            boolean bl = false;
            return new Regex(string2, regexOption);
        }

        @NotNull
        public final String escape(@NotNull String string) {
            Intrinsics.checkNotNullParameter(string, "literal");
            String string2 = Pattern.quote(string);
            Intrinsics.checkNotNullExpressionValue(string2, "Pattern.quote(literal)");
            return string2;
        }

        @NotNull
        public final String escapeReplacement(@NotNull String string) {
            Intrinsics.checkNotNullParameter(string, "literal");
            String string2 = Matcher.quoteReplacement(string);
            Intrinsics.checkNotNullExpressionValue(string2, "Matcher.quoteReplacement(literal)");
            return string2;
        }

        private final int ensureUnicodeCase(int n2) {
            return (n2 & 2) != 0 ? n2 | 0x40 : n2;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

