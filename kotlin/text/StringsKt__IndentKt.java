/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__AppendableKt;
import kotlin.text.StringsKt__IndentKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0002\b\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002\u00a2\u0006\u0002\b\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001aJ\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\u0082\b\u00a2\u0006\u0002\b\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u001a\u001e\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u001a\n\u0010\u0013\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u00a8\u0006\u0015"}, d2={"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__IndentKt
extends StringsKt__AppendableKt {
    @NotNull
    public static final String trimMargin(@NotNull String string, @NotNull String string2) {
        Intrinsics.checkNotNullParameter(string, "$this$trimMargin");
        Intrinsics.checkNotNullParameter(string2, "marginPrefix");
        return StringsKt.replaceIndentByMargin(string, "", string2);
    }

    public static /* synthetic */ String trimMargin$default(String string, String string2, int n2, Object object) {
        if ((n2 & 1) != 0) {
            string2 = "|";
        }
        return StringsKt.trimMargin(string, string2);
    }

    @NotNull
    public static final String replaceIndentByMargin(@NotNull String string, @NotNull String string2, @NotNull String string3) {
        List<String> list;
        Intrinsics.checkNotNullParameter(string, "$this$replaceIndentByMargin");
        Intrinsics.checkNotNullParameter(string2, "newIndent");
        Intrinsics.checkNotNullParameter(string3, "marginPrefix");
        CharSequence charSequence = string3;
        boolean bl = false;
        boolean bl2 = !StringsKt.isBlank(charSequence);
        bl = false;
        int n2 = 0;
        if (!bl2) {
            boolean bl3 = false;
            String string4 = "marginPrefix must be non-blank string.";
            throw (Throwable)new IllegalArgumentException(string4.toString());
        }
        List<String> list2 = list = StringsKt.lines(string);
        n2 = string.length() + string2.length() * list.size();
        Function1<String, String> function1 = StringsKt__IndentKt.getIndentFunction$StringsKt__IndentKt(string2);
        boolean bl4 = false;
        int n3 = CollectionsKt.getLastIndex(list2);
        Iterable iterable = list2;
        boolean bl5 = false;
        Iterable iterable2 = iterable;
        Collection collection = new ArrayList();
        boolean bl6 = false;
        Iterable iterable3 = iterable2;
        boolean bl7 = false;
        int n4 = 0;
        for (Object t2 : iterable3) {
            String string5;
            block14: {
                String string6;
                block15: {
                    String string7;
                    int n5;
                    int n6;
                    int n7;
                    CharSequence charSequence2;
                    String string8;
                    block12: {
                        block13: {
                            int n8 = n4++;
                            boolean bl8 = false;
                            if (n8 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            Object t3 = t2;
                            int n9 = n8;
                            boolean bl9 = false;
                            string6 = (String)t3;
                            int n10 = n9;
                            boolean bl10 = false;
                            if (n10 != 0 && n10 != n3 || !StringsKt.isBlank(string6)) break block13;
                            string5 = null;
                            break block14;
                        }
                        string8 = string6;
                        boolean bl11 = false;
                        charSequence2 = string8;
                        n7 = 0;
                        int n11 = charSequence2.length();
                        for (n6 = 0; n6 < n11; ++n6) {
                            char c2 = charSequence2.charAt(n6);
                            boolean bl12 = false;
                            if (!(!CharsKt.isWhitespace(c2))) continue;
                            n5 = n6;
                            break block12;
                        }
                        n5 = -1;
                    }
                    int n12 = n5;
                    if (n12 == -1) {
                        string7 = null;
                    } else if (StringsKt.startsWith$default(string8, string3, n12, false, 4, null)) {
                        charSequence2 = string8;
                        n7 = n12 + string3.length();
                        n6 = 0;
                        CharSequence charSequence3 = charSequence2;
                        if (charSequence3 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                        String string9 = ((String)charSequence3).substring(n7);
                        string7 = string9;
                        Intrinsics.checkNotNullExpressionValue(string9, "(this as java.lang.String).substring(startIndex)");
                    } else {
                        string7 = string5 = null;
                    }
                    if (string7 == null) break block15;
                    String string10 = string5;
                    boolean bl13 = false;
                    boolean bl14 = false;
                    string5 = function1.invoke(string10);
                    if (string5 != null) break block14;
                }
                string5 = string6;
            }
            if (string5 == null) continue;
            String string11 = string5;
            boolean bl15 = false;
            boolean bl16 = false;
            String string12 = string11;
            boolean bl17 = false;
            collection.add(string12);
        }
        String string13 = ((StringBuilder)CollectionsKt.joinTo$default((List)collection, new StringBuilder(n2), "\n", null, null, 0, null, null, 124, null)).toString();
        Intrinsics.checkNotNullExpressionValue(string13, "mapIndexedNotNull { inde\u2026\"\\n\")\n        .toString()");
        return string13;
    }

    public static /* synthetic */ String replaceIndentByMargin$default(String string, String string2, String string3, int n2, Object object) {
        if ((n2 & 1) != 0) {
            string2 = "";
        }
        if ((n2 & 2) != 0) {
            string3 = "|";
        }
        return StringsKt.replaceIndentByMargin(string, string2, string3);
    }

    @NotNull
    public static final String trimIndent(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$trimIndent");
        return StringsKt.replaceIndent(string, "");
    }

    @NotNull
    public static final String replaceIndent(@NotNull String string, @NotNull String string2) {
        boolean bl;
        Object object;
        Object t2;
        Intrinsics.checkNotNullParameter(string, "$this$replaceIndent");
        Intrinsics.checkNotNullParameter(string2, "newIndent");
        List<String> list = StringsKt.lines(string);
        List<String> list2 = list;
        int n2 = 0;
        Object object2 = list2;
        Collection collection = new ArrayList();
        int n3 = 0;
        Object object3 = object2.iterator();
        while (object3.hasNext()) {
            t2 = object3.next();
            object = (String)t2;
            bl = false;
            CharSequence charSequence = (CharSequence)object;
            boolean bl2 = false;
            if (!(!StringsKt.isBlank(charSequence))) continue;
            collection.add(t2);
        }
        list2 = (List)collection;
        n2 = 0;
        object2 = list2;
        collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        n3 = 0;
        object3 = object2.iterator();
        while (object3.hasNext()) {
            t2 = object3.next();
            object = (String)t2;
            Collection collection2 = collection;
            bl = false;
            Integer n4 = StringsKt__IndentKt.indentWidth$StringsKt__IndentKt((String)object);
            collection2.add(n4);
        }
        Integer n5 = (Integer)CollectionsKt.minOrNull((List)collection);
        int n6 = n5 != null ? n5 : 0;
        list2 = list;
        n2 = string.length() + string2.length() * list.size();
        object2 = StringsKt__IndentKt.getIndentFunction$StringsKt__IndentKt(string2);
        boolean bl3 = false;
        n3 = CollectionsKt.getLastIndex(list2);
        object3 = list2;
        boolean bl4 = false;
        object = object3;
        Collection collection3 = new ArrayList();
        boolean bl5 = false;
        Object object4 = object;
        boolean bl6 = false;
        int n7 = 0;
        Iterator iterator2 = object4.iterator();
        while (iterator2.hasNext()) {
            String string3;
            block8: {
                String string4;
                block9: {
                    block7: {
                        Object t3 = iterator2.next();
                        int n8 = n7++;
                        boolean bl7 = false;
                        if (n8 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        Object t4 = t3;
                        int n9 = n8;
                        boolean bl8 = false;
                        string4 = (String)t4;
                        int n10 = n9;
                        boolean bl9 = false;
                        if (n10 != 0 && n10 != n3 || !StringsKt.isBlank(string4)) break block7;
                        string3 = null;
                        break block8;
                    }
                    String string5 = string4;
                    boolean bl10 = false;
                    string3 = StringsKt.drop(string5, n6);
                    if (string3 == null) break block9;
                    String string6 = string3;
                    boolean bl11 = false;
                    boolean bl12 = false;
                    string3 = (String)object2.invoke(string6);
                    if (string3 != null) break block8;
                }
                string3 = string4;
            }
            if (string3 == null) continue;
            String string7 = string3;
            boolean bl13 = false;
            boolean bl14 = false;
            String string8 = string7;
            boolean bl15 = false;
            collection3.add(string8);
        }
        String string9 = ((StringBuilder)CollectionsKt.joinTo$default((List)collection3, new StringBuilder(n2), "\n", null, null, 0, null, null, 124, null)).toString();
        Intrinsics.checkNotNullExpressionValue(string9, "mapIndexedNotNull { inde\u2026\"\\n\")\n        .toString()");
        return string9;
    }

    public static /* synthetic */ String replaceIndent$default(String string, String string2, int n2, Object object) {
        if ((n2 & 1) != 0) {
            string2 = "";
        }
        return StringsKt.replaceIndent(string, string2);
    }

    @NotNull
    public static final String prependIndent(@NotNull String string, @NotNull String string2) {
        Intrinsics.checkNotNullParameter(string, "$this$prependIndent");
        Intrinsics.checkNotNullParameter(string2, "indent");
        return SequencesKt.joinToString$default(SequencesKt.map(StringsKt.lineSequence(string), (Function1)new Function1<String, String>(string2){
            final /* synthetic */ String $indent;

            @NotNull
            public final String invoke(@NotNull String string) {
                Intrinsics.checkNotNullParameter(string, "it");
                return StringsKt.isBlank(string) ? (string.length() < this.$indent.length() ? this.$indent : string) : this.$indent + string;
            }
            {
                this.$indent = string;
                super(1);
            }
        }), "\n", null, null, 0, null, null, 62, null);
    }

    public static /* synthetic */ String prependIndent$default(String string, String string2, int n2, Object object) {
        if ((n2 & 1) != 0) {
            string2 = "    ";
        }
        return StringsKt.prependIndent(string, string2);
    }

    private static final int indentWidth$StringsKt__IndentKt(String string) {
        int n2;
        char c2;
        int n3;
        int n4;
        boolean bl;
        block1: {
            CharSequence charSequence = string;
            bl = false;
            n4 = charSequence.length();
            for (n3 = 0; n3 < n4; ++n3) {
                c2 = charSequence.charAt(n3);
                boolean bl2 = false;
                if (!(!CharsKt.isWhitespace(c2))) continue;
                n2 = n3;
                break block1;
            }
            n2 = -1;
        }
        int n5 = n2;
        bl = false;
        n3 = 0;
        n4 = n5;
        c2 = '\u0000';
        return n4 == -1 ? string.length() : n4;
    }

    private static final Function1<String, String> getIndentFunction$StringsKt__IndentKt(String string) {
        CharSequence charSequence = string;
        boolean bl = false;
        return charSequence.length() == 0 ? (Function1)getIndentFunction.1.INSTANCE : (Function1)new Function1<String, String>(string){
            final /* synthetic */ String $indent;

            @NotNull
            public final String invoke(@NotNull String string) {
                Intrinsics.checkNotNullParameter(string, "line");
                return this.$indent + string;
            }
            {
                this.$indent = string;
                super(1);
            }
        };
    }

    private static final String reindent$StringsKt__IndentKt(List<String> list, int n2, Function1<? super String, String> function1, Function1<? super String, String> function12) {
        int n3 = 0;
        int n4 = CollectionsKt.getLastIndex(list);
        Iterable iterable = list;
        boolean bl = false;
        Iterable iterable2 = iterable;
        Collection collection = new ArrayList();
        boolean bl2 = false;
        Iterable iterable3 = iterable2;
        boolean bl3 = false;
        int n5 = 0;
        for (Object t2 : iterable3) {
            String string;
            block8: {
                String string2;
                block9: {
                    block7: {
                        int n6 = n5++;
                        boolean bl4 = false;
                        if (n6 < 0) {
                            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
                                CollectionsKt.throwIndexOverflow();
                            } else {
                                throw (Throwable)new ArithmeticException("Index overflow has happened.");
                            }
                        }
                        Object t3 = t2;
                        int n7 = n6;
                        boolean bl5 = false;
                        string2 = (String)t3;
                        int n8 = n7;
                        boolean bl6 = false;
                        if (n8 != 0 && n8 != n4 || !StringsKt.isBlank(string2)) break block7;
                        string = null;
                        break block8;
                    }
                    string = function12.invoke(string2);
                    if (string == null) break block9;
                    String string3 = string;
                    boolean bl7 = false;
                    boolean bl8 = false;
                    string = function1.invoke(string3);
                    if (string != null) break block8;
                }
                string = string2;
            }
            if (string == null) continue;
            String string4 = string;
            boolean bl9 = false;
            boolean bl10 = false;
            String string5 = string4;
            boolean bl11 = false;
            collection.add(string5);
        }
        String string = ((StringBuilder)CollectionsKt.joinTo$default((List)collection, new StringBuilder(n2), "\n", null, null, 0, null, null, 124, null)).toString();
        Intrinsics.checkNotNullExpressionValue(string, "mapIndexedNotNull { inde\u2026\"\\n\")\n        .toString()");
        return string;
    }
}

