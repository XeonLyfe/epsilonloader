/*
 * Decompiled with CFR 0.150.
 */
package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.FlagEnum;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0006R\u0014\u0010\u0005\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010\u00a8\u0006\u0011"}, d2={"Lkotlin/text/RegexOption;", "", "Lkotlin/text/FlagEnum;", "value", "", "mask", "(Ljava/lang/String;III)V", "getMask", "()I", "getValue", "IGNORE_CASE", "MULTILINE", "LITERAL", "UNIX_LINES", "COMMENTS", "DOT_MATCHES_ALL", "CANON_EQ", "kotlin-stdlib"})
public final class RegexOption
extends Enum<RegexOption>
implements FlagEnum {
    public static final /* enum */ RegexOption IGNORE_CASE;
    public static final /* enum */ RegexOption MULTILINE;
    public static final /* enum */ RegexOption LITERAL;
    public static final /* enum */ RegexOption UNIX_LINES;
    public static final /* enum */ RegexOption COMMENTS;
    public static final /* enum */ RegexOption DOT_MATCHES_ALL;
    public static final /* enum */ RegexOption CANON_EQ;
    private static final /* synthetic */ RegexOption[] $VALUES;
    private final int value;
    private final int mask;

    static {
        RegexOption[] arrregexOption = new RegexOption[7];
        RegexOption[] arrregexOption2 = arrregexOption;
        arrregexOption[0] = IGNORE_CASE = new RegexOption("IGNORE_CASE", 0, 2, 0, 2, null);
        arrregexOption[1] = MULTILINE = new RegexOption("MULTILINE", 1, 8, 0, 2, null);
        arrregexOption[2] = LITERAL = new RegexOption("LITERAL", 2, 16, 0, 2, null);
        arrregexOption[3] = UNIX_LINES = new RegexOption("UNIX_LINES", 3, 1, 0, 2, null);
        arrregexOption[4] = COMMENTS = new RegexOption("COMMENTS", 4, 4, 0, 2, null);
        arrregexOption[5] = DOT_MATCHES_ALL = new RegexOption("DOT_MATCHES_ALL", 5, 32, 0, 2, null);
        arrregexOption[6] = CANON_EQ = new RegexOption("CANON_EQ", 6, 128, 0, 2, null);
        $VALUES = arrregexOption;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public int getMask() {
        return this.mask;
    }

    private RegexOption(int n3, int n4) {
        this.value = n3;
        this.mask = n4;
    }

    /* synthetic */ RegexOption(String string, int n2, int n3, int n4, int n5, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n5 & 2) != 0) {
            n4 = n3;
        }
        this(n3, n4);
    }

    public static RegexOption[] values() {
        return (RegexOption[])$VALUES.clone();
    }

    public static RegexOption valueOf(String string) {
        return Enum.valueOf(RegexOption.class, string);
    }
}

