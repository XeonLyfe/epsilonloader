/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.sequences;

import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.WasExperimental;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2={"sum", "Lkotlin/UInt;", "Lkotlin/sequences/Sequence;", "Lkotlin/UByte;", "sumOfUByte", "(Lkotlin/sequences/Sequence;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Lkotlin/sequences/Sequence;)J", "Lkotlin/UShort;", "sumOfUShort", "kotlin-stdlib"}, xs="kotlin/sequences/USequencesKt")
class USequencesKt___USequencesKt {
    @JvmName(name="sumOfUInt")
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(@NotNull Sequence<UInt> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "$this$sum");
        int n2 = 0;
        Iterator<UInt> iterator2 = sequence.iterator();
        while (iterator2.hasNext()) {
            int n3 = iterator2.next().unbox-impl();
            int n4 = n2;
            boolean bl = false;
            n2 = UInt.constructor-impl(n4 + n3);
        }
        return n2;
    }

    @JvmName(name="sumOfULong")
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(@NotNull Sequence<ULong> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "$this$sum");
        long l2 = 0L;
        Iterator<ULong> iterator2 = sequence.iterator();
        while (iterator2.hasNext()) {
            long l3 = iterator2.next().unbox-impl();
            long l4 = l2;
            boolean bl = false;
            l2 = ULong.constructor-impl(l4 + l3);
        }
        return l2;
    }

    @JvmName(name="sumOfUByte")
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int sumOfUByte(@NotNull Sequence<UByte> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "$this$sum");
        int n2 = 0;
        Iterator<UByte> iterator2 = sequence.iterator();
        while (iterator2.hasNext()) {
            int n3 = iterator2.next().unbox-impl();
            int n4 = n2;
            boolean bl = false;
            int n5 = n4;
            int n6 = n3;
            boolean bl2 = false;
            n6 = UInt.constructor-impl(n6 & 0xFF);
            bl2 = false;
            n2 = UInt.constructor-impl(n5 + n6);
        }
        return n2;
    }

    @JvmName(name="sumOfUShort")
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
    public static final int sumOfUShort(@NotNull Sequence<UShort> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "$this$sum");
        int n2 = 0;
        Iterator<UShort> iterator2 = sequence.iterator();
        while (iterator2.hasNext()) {
            int n3 = iterator2.next().unbox-impl();
            int n4 = n2;
            boolean bl = false;
            int n5 = n4;
            int n6 = n3;
            boolean bl2 = false;
            n6 = UInt.constructor-impl(n6 & 0xFFFF);
            bl2 = false;
            n2 = UInt.constructor-impl(n5 + n6);
        }
        return n2;
    }
}

