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
import kotlin.text.CharsKt;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text._OneToManyTitlecaseMappingsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\u001e\n\u0000\n\u0002\u0010\f\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\f\u0010\u0004\u001a\u00020\u0002*\u00020\u0001H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007\u00a2\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u00a2\u0006\u0002\u0010\u0007\u001a\u001c\u0010\b\u001a\u00020\t*\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\t\u001a\n\u0010\f\u001a\u00020\t*\u00020\u0001\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000eH\u0087\n\u001a\f\u0010\u000f\u001a\u00020\u000e*\u00020\u0001H\u0007\u00a8\u0006\u0010"}, d2={"digitToChar", "", "", "radix", "digitToInt", "digitToIntOrNull", "(C)Ljava/lang/Integer;", "(CI)Ljava/lang/Integer;", "equals", "", "other", "ignoreCase", "isSurrogate", "plus", "", "titlecase", "kotlin-stdlib"}, xs="kotlin/text/CharsKt")
class CharsKt__CharKt
extends CharsKt__CharJVMKt {
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    public static final int digitToInt(char c2) {
        int n2 = CharsKt.digitOf(c2, 10);
        boolean bl = false;
        boolean bl2 = false;
        int n3 = n2;
        boolean bl3 = false;
        if (n3 < 0) {
            throw (Throwable)new IllegalArgumentException("Char " + c2 + " is not a decimal digit");
        }
        return n2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    public static final int digitToInt(char c2, int n2) {
        Integer n3 = CharsKt.digitToIntOrNull(c2, n2);
        if (n3 == null) {
            throw (Throwable)new IllegalArgumentException("Char " + c2 + " is not a digit in the given radix=" + n2);
        }
        return n3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @Nullable
    public static final Integer digitToIntOrNull(char c2) {
        Integer n2 = CharsKt.digitOf(c2, 10);
        boolean bl = false;
        boolean bl2 = false;
        int n3 = ((Number)n2).intValue();
        boolean bl3 = false;
        return n3 >= 0 ? n2 : null;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @Nullable
    public static final Integer digitToIntOrNull(char c2, int n2) {
        CharsKt.checkRadix(n2);
        Integer n3 = CharsKt.digitOf(c2, n2);
        boolean bl = false;
        boolean bl2 = false;
        int n4 = ((Number)n3).intValue();
        boolean bl3 = false;
        return n4 >= 0 ? n3 : null;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    public static final char digitToChar(int n2) {
        int n3 = n2;
        if (0 <= n3 && 9 >= n3) {
            return (char)(48 + n2);
        }
        throw (Throwable)new IllegalArgumentException("Int " + n2 + " is not a decimal digit");
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    public static final char digitToChar(int n2, int n3) {
        int n4 = n3;
        if (2 > n4 || 36 < n4) {
            throw (Throwable)new IllegalArgumentException("Invalid radix: " + n3 + ". Valid radix values are in range 2..36");
        }
        if (n2 < 0 || n2 >= n3) {
            throw (Throwable)new IllegalArgumentException("Digit " + n2 + " does not represent a valid digit in radix " + n3);
        }
        return n2 < 10 ? (char)(48 + n2) : (char)((char)(65 + n2) - 10);
    }

    @SinceKotlin(version="1.5")
    @NotNull
    public static final String titlecase(char c2) {
        return _OneToManyTitlecaseMappingsKt.titlecaseImpl(c2);
    }

    @InlineOnly
    private static final String plus(char c2, String string) {
        int n2 = 0;
        return String.valueOf(c2) + string;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final boolean equals(char c2, char c3, boolean bl) {
        if (c2 == c3) {
            return true;
        }
        if (!bl) {
            return false;
        }
        char c4 = c2;
        char c5 = '\u0000';
        char c6 = Character.toUpperCase(c4);
        c5 = c3;
        boolean bl2 = false;
        c4 = Character.toUpperCase(c5);
        if (c6 == c4) return true;
        c5 = c6;
        bl2 = false;
        char c7 = Character.toLowerCase(c5);
        c5 = c4;
        bl2 = false;
        if (c7 != Character.toLowerCase(c5)) return false;
        return true;
    }

    public static /* synthetic */ boolean equals$default(char c2, char c3, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return CharsKt.equals(c2, c3, bl);
    }

    public static final boolean isSurrogate(char c2) {
        char c3 = c2;
        return '\ud800' <= c3 && '\udfff' >= c3;
    }
}

