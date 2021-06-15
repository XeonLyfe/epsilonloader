/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000.\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0003H\u0007\u00a2\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007\u00a2\u0006\u0002\u0010\t\u001a\u0013\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u0003H\u0007\u00a2\u0006\u0002\u0010\u000b\u001a\u001b\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007\u00a2\u0006\u0002\u0010\f\u001a\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007\u00a2\u0006\u0002\u0010\u000f\u001a\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007\u00a2\u0006\u0002\u0010\u0010\u001a\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u0003H\u0007\u00a2\u0006\u0002\u0010\u0013\u001a\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007\u00a2\u0006\u0002\u0010\u0014\u00a8\u0006\u0015"}, d2={"numberFormatError", "", "input", "", "toByteOrNull", "", "(Ljava/lang/String;)Ljava/lang/Byte;", "radix", "", "(Ljava/lang/String;I)Ljava/lang/Byte;", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLongOrNull", "", "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "toShortOrNull", "", "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__StringNumberConversionsKt
extends StringsKt__StringNumberConversionsJVMKt {
    @SinceKotlin(version="1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toByteOrNull");
        return StringsKt.toByteOrNull(string, 10);
    }

    @SinceKotlin(version="1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String string, int n2) {
        Intrinsics.checkNotNullParameter(string, "$this$toByteOrNull");
        Integer n3 = StringsKt.toIntOrNull(string, n2);
        if (n3 == null) {
            return null;
        }
        int n4 = n3;
        if (n4 < -128 || n4 > 127) {
            return null;
        }
        return (byte)n4;
    }

    @SinceKotlin(version="1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toShortOrNull");
        return StringsKt.toShortOrNull(string, 10);
    }

    @SinceKotlin(version="1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String string, int n2) {
        Intrinsics.checkNotNullParameter(string, "$this$toShortOrNull");
        Integer n3 = StringsKt.toIntOrNull(string, n2);
        if (n3 == null) {
            return null;
        }
        int n4 = n3;
        if (n4 < -32768 || n4 > 32767) {
            return null;
        }
        return (short)n4;
    }

    @SinceKotlin(version="1.1")
    @Nullable
    public static final Integer toIntOrNull(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toIntOrNull");
        return StringsKt.toIntOrNull(string, 10);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @SinceKotlin(version="1.1")
    @Nullable
    public static final Integer toIntOrNull(@NotNull String string, int n2) {
        int n3;
        Intrinsics.checkNotNullParameter(string, "$this$toIntOrNull");
        CharsKt.checkRadix(n2);
        int n4 = string.length();
        if (n4 == 0) {
            return null;
        }
        int n5 = 0;
        boolean bl = false;
        int n6 = 0;
        char c2 = string.charAt(0);
        if (Intrinsics.compare(c2, 48) < 0) {
            if (n4 == 1) {
                return null;
            }
            n5 = 1;
            if (c2 == '-') {
                bl = true;
                n6 = Integer.MIN_VALUE;
            } else {
                if (c2 != '+') return null;
                bl = false;
                n6 = -2147483647;
            }
        } else {
            n5 = 0;
            bl = false;
            n6 = -2147483647;
        }
        int n7 = n3 = -59652323;
        int n8 = 0;
        int n9 = n4;
        for (int i2 = n5; i2 < n9; ++i2) {
            int n10 = CharsKt.digitOf(string.charAt(i2), n2);
            if (n10 < 0) {
                return null;
            }
            if (n8 < n7) {
                if (n7 != n3) return null;
                n7 = n6 / n2;
                if (n8 < n7) {
                    return null;
                }
            }
            if ((n8 *= n2) < n6 + n10) {
                return null;
            }
            n8 -= n10;
        }
        return bl ? Integer.valueOf(n8) : Integer.valueOf(-n8);
    }

    @SinceKotlin(version="1.1")
    @Nullable
    public static final Long toLongOrNull(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toLongOrNull");
        return StringsKt.toLongOrNull(string, 10);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @SinceKotlin(version="1.1")
    @Nullable
    public static final Long toLongOrNull(@NotNull String string, int n2) {
        long l2;
        Intrinsics.checkNotNullParameter(string, "$this$toLongOrNull");
        CharsKt.checkRadix(n2);
        int n3 = string.length();
        if (n3 == 0) {
            return null;
        }
        int n4 = 0;
        boolean bl = false;
        long l3 = 0L;
        char c2 = string.charAt(0);
        if (Intrinsics.compare(c2, 48) < 0) {
            if (n3 == 1) {
                return null;
            }
            n4 = 1;
            if (c2 == '-') {
                bl = true;
                l3 = Long.MIN_VALUE;
            } else {
                if (c2 != '+') return null;
                bl = false;
                l3 = -9223372036854775807L;
            }
        } else {
            n4 = 0;
            bl = false;
            l3 = -9223372036854775807L;
        }
        long l4 = l2 = -256204778801521550L;
        long l5 = 0L;
        int n5 = n3;
        for (int i2 = n4; i2 < n5; ++i2) {
            int n6 = CharsKt.digitOf(string.charAt(i2), n2);
            if (n6 < 0) {
                return null;
            }
            if (l5 < l4) {
                if (l4 != l2) return null;
                l4 = l3 / (long)n2;
                if (l5 < l4) {
                    return null;
                }
            }
            if ((l5 *= (long)n2) < l3 + (long)n6) {
                return null;
            }
            l5 -= (long)n6;
        }
        return bl ? Long.valueOf(l5) : Long.valueOf(-l5);
    }

    @NotNull
    public static final Void numberFormatError(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "input");
        throw (Throwable)new NumberFormatException("Invalid number format: '" + string + '\'');
    }
}

