/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.text;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\t\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\u0002*\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0010\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012\u001a\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007\u00f8\u0001\u0000\u001a\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u001a\u0014\u0010\u0014\u001a\u00020\u0007*\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0014\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016\u001a\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u0001H\u0007\u00f8\u0001\u0000\u001a\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u001a\u0014\u0010\u0018\u001a\u00020\n*\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u0018\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u0001H\u0007\u00f8\u0001\u0000\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001d\u001a\u001c\u0010\u001c\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001e\u001a\u0011\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u0001H\u0007\u00f8\u0001\u0000\u001a\u0019\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006 "}, d2={"toString", "", "Lkotlin/UByte;", "radix", "", "toString-LxnNnR4", "(BI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUByteOrNull", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toUIntOrNull", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toULongOrNull", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUShortOrNull", "kotlin-stdlib"})
@JvmName(name="UStringsKt")
public final class UStringsKt {
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final String toString-LxnNnR4(byte n2, int n3) {
        int n4 = n2;
        boolean bl = false;
        bl = false;
        String string = Integer.toString(n4 &= 0xFF, CharsKt.checkRadix(n3));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final String toString-olVBNx4(short n2, int n3) {
        int n4 = n2;
        boolean bl = false;
        bl = false;
        String string = Integer.toString(n4 &= 0xFFFF, CharsKt.checkRadix(n3));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final String toString-V7xB4Y4(int n2, int n3) {
        int n4 = n2;
        boolean bl = false;
        long l2 = (long)n4 & 0xFFFFFFFFL;
        boolean bl2 = false;
        String string = Long.toString(l2, CharsKt.checkRadix(n3));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Long.toString(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @NotNull
    public static final String toString-JSWoG40(long l2, int n2) {
        long l3 = l2;
        boolean bl = false;
        return UnsignedKt.ulongToString(l3, CharsKt.checkRadix(n2));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final byte toUByte(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toUByte");
        UByte uByte = UStringsKt.toUByteOrNull(string);
        if (uByte == null) {
            Void void_ = StringsKt.numberFormatError(string);
            throw new KotlinNothingValueException();
        }
        return uByte.unbox-impl();
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final byte toUByte(@NotNull String string, int n2) {
        Intrinsics.checkNotNullParameter(string, "$this$toUByte");
        UByte uByte = UStringsKt.toUByteOrNull(string, n2);
        if (uByte == null) {
            Void void_ = StringsKt.numberFormatError(string);
            throw new KotlinNothingValueException();
        }
        return uByte.unbox-impl();
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final short toUShort(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toUShort");
        UShort uShort = UStringsKt.toUShortOrNull(string);
        if (uShort == null) {
            Void void_ = StringsKt.numberFormatError(string);
            throw new KotlinNothingValueException();
        }
        return uShort.unbox-impl();
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final short toUShort(@NotNull String string, int n2) {
        Intrinsics.checkNotNullParameter(string, "$this$toUShort");
        UShort uShort = UStringsKt.toUShortOrNull(string, n2);
        if (uShort == null) {
            Void void_ = StringsKt.numberFormatError(string);
            throw new KotlinNothingValueException();
        }
        return uShort.unbox-impl();
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int toUInt(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toUInt");
        UInt uInt = UStringsKt.toUIntOrNull(string);
        if (uInt == null) {
            Void void_ = StringsKt.numberFormatError(string);
            throw new KotlinNothingValueException();
        }
        return uInt.unbox-impl();
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int toUInt(@NotNull String string, int n2) {
        Intrinsics.checkNotNullParameter(string, "$this$toUInt");
        UInt uInt = UStringsKt.toUIntOrNull(string, n2);
        if (uInt == null) {
            Void void_ = StringsKt.numberFormatError(string);
            throw new KotlinNothingValueException();
        }
        return uInt.unbox-impl();
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long toULong(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toULong");
        ULong uLong = UStringsKt.toULongOrNull(string);
        if (uLong == null) {
            Void void_ = StringsKt.numberFormatError(string);
            throw new KotlinNothingValueException();
        }
        return uLong.unbox-impl();
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long toULong(@NotNull String string, int n2) {
        Intrinsics.checkNotNullParameter(string, "$this$toULong");
        ULong uLong = UStringsKt.toULongOrNull(string, n2);
        if (uLong == null) {
            Void void_ = StringsKt.numberFormatError(string);
            throw new KotlinNothingValueException();
        }
        return uLong.unbox-impl();
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UByte toUByteOrNull(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toUByteOrNull");
        return UStringsKt.toUByteOrNull(string, 10);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UByte toUByteOrNull(@NotNull String string, int n2) {
        int n3;
        Intrinsics.checkNotNullParameter(string, "$this$toUByteOrNull");
        UInt uInt = UStringsKt.toUIntOrNull(string, n2);
        if (uInt == null) {
            return null;
        }
        int n4 = n3 = uInt.unbox-impl();
        int n5 = -1;
        int n6 = 0;
        int n7 = n4;
        int n8 = n5;
        boolean bl = false;
        n8 = UInt.constructor-impl(n8 & 0xFF);
        bl = false;
        if (UnsignedKt.uintCompare(n7, n8) > 0) {
            return null;
        }
        n4 = n3;
        n5 = 0;
        n6 = n4;
        n7 = 0;
        return UByte.box-impl(UByte.constructor-impl((byte)n6));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UShort toUShortOrNull(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toUShortOrNull");
        return UStringsKt.toUShortOrNull(string, 10);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UShort toUShortOrNull(@NotNull String string, int n2) {
        int n3;
        Intrinsics.checkNotNullParameter(string, "$this$toUShortOrNull");
        UInt uInt = UStringsKt.toUIntOrNull(string, n2);
        if (uInt == null) {
            return null;
        }
        int n4 = n3 = uInt.unbox-impl();
        int n5 = -1;
        int n6 = 0;
        int n7 = n4;
        int n8 = n5;
        boolean bl = false;
        n8 = UInt.constructor-impl(n8 & 0xFFFF);
        bl = false;
        if (UnsignedKt.uintCompare(n7, n8) > 0) {
            return null;
        }
        n4 = n3;
        n5 = 0;
        n6 = n4;
        n7 = 0;
        return UShort.box-impl(UShort.constructor-impl((short)n6));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UInt toUIntOrNull(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toUIntOrNull");
        return UStringsKt.toUIntOrNull(string, 10);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UInt toUIntOrNull(@NotNull String string, int n2) {
        int n3;
        Intrinsics.checkNotNullParameter(string, "$this$toUIntOrNull");
        CharsKt.checkRadix(n2);
        int n4 = string.length();
        if (n4 == 0) {
            return null;
        }
        int n5 = -1;
        int n6 = 0;
        char c2 = string.charAt(0);
        if (Intrinsics.compare(c2, 48) < 0) {
            if (n4 == 1 || c2 != '+') {
                return null;
            }
            n6 = 1;
        } else {
            n6 = 0;
        }
        int n7 = n3 = 0x71C71C7;
        int n8 = n2;
        int n9 = 0;
        int n10 = UInt.constructor-impl(n8);
        n8 = 0;
        int n11 = n4;
        for (n9 = n6; n9 < n11; ++n9) {
            int n12 = CharsKt.digitOf(string.charAt(n9), n2);
            if (n12 < 0) {
                return null;
            }
            int n13 = n8;
            int n14 = 0;
            if (UnsignedKt.uintCompare(n13, n7) > 0) {
                if (n7 == n3) {
                    n13 = n5;
                    n14 = 0;
                    n7 = UnsignedKt.uintDivide-J1ME1BU(n13, n10);
                    n13 = n8;
                    n14 = 0;
                    if (UnsignedKt.uintCompare(n13, n7) > 0) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            n13 = n8;
            n14 = 0;
            n13 = n8 = UInt.constructor-impl(n13 * n10);
            n14 = n8;
            int n15 = n12;
            boolean bl = false;
            n15 = UInt.constructor-impl(n15);
            bl = false;
            n14 = n8 = UInt.constructor-impl(n14 + n15);
            n15 = 0;
            if (UnsignedKt.uintCompare(n14, n13) >= 0) continue;
            return null;
        }
        return UInt.box-impl(n8);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @Nullable
    public static final ULong toULongOrNull(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "$this$toULongOrNull");
        return UStringsKt.toULongOrNull(string, 10);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    @Nullable
    public static final ULong toULongOrNull(@NotNull String string, int n2) {
        long l2;
        Intrinsics.checkNotNullParameter(string, "$this$toULongOrNull");
        CharsKt.checkRadix(n2);
        int n3 = string.length();
        if (n3 == 0) {
            return null;
        }
        long l3 = -1L;
        int n4 = 0;
        char c2 = string.charAt(0);
        if (Intrinsics.compare(c2, 48) < 0) {
            if (n3 == 1 || c2 != '+') {
                return null;
            }
            n4 = 1;
        } else {
            n4 = 0;
        }
        long l4 = l2 = 0x71C71C71C71C71CL;
        int n5 = n2;
        boolean bl = false;
        long l5 = ULong.constructor-impl(n5);
        long l6 = 0L;
        int n6 = n3;
        for (int i2 = n4; i2 < n6; ++i2) {
            int n7 = CharsKt.digitOf(string.charAt(i2), n2);
            if (n7 < 0) {
                return null;
            }
            long l7 = l6;
            boolean bl2 = false;
            if (UnsignedKt.ulongCompare(l7, l4) > 0) {
                if (l4 == l2) {
                    l7 = l3;
                    bl2 = false;
                    l4 = UnsignedKt.ulongDivide-eb3DHEI(l7, l5);
                    l7 = l6;
                    bl2 = false;
                    if (UnsignedKt.ulongCompare(l7, l4) > 0) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            l7 = l6;
            bl2 = false;
            l7 = l6 = ULong.constructor-impl(l7 * l5);
            long l8 = l6;
            int n8 = n7;
            boolean bl3 = false;
            n8 = UInt.constructor-impl(n8);
            bl3 = false;
            long l9 = l8;
            int n9 = n8;
            boolean bl4 = false;
            long l10 = ULong.constructor-impl((long)n9 & 0xFFFFFFFFL);
            boolean bl5 = false;
            l8 = l6 = ULong.constructor-impl(l9 + l10);
            n8 = 0;
            if (UnsignedKt.ulongCompare(l8, l7) >= 0) continue;
            return null;
        }
        return ULong.box-impl(l6);
    }
}

