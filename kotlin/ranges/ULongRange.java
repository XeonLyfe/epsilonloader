/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.ranges;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.ULongProgression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00172\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0017B\u0018\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\b\u00f8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\u0018"}, d2={"Lkotlin/ranges/ULongRange;", "Lkotlin/ranges/ULongProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/ULong;", "start", "endInclusive", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEndInclusive-s-VKNKU", "()J", "getStart-s-VKNKU", "contains", "", "value", "contains-VKZWuLQ", "(J)Z", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"})
@SinceKotlin(version="1.5")
@WasExperimental(markerClass={ExperimentalUnsignedTypes.class})
public final class ULongRange
extends ULongProgression
implements ClosedRange<ULong> {
    @NotNull
    private static final ULongRange EMPTY;
    @NotNull
    public static final Companion Companion;

    public long getStart-s-VKNKU() {
        return this.getFirst-s-VKNKU();
    }

    public long getEndInclusive-s-VKNKU() {
        return this.getLast-s-VKNKU();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean contains-VKZWuLQ(long l2) {
        long l3 = this.getFirst-s-VKNKU();
        boolean bl = false;
        if (UnsignedKt.ulongCompare(l3, l2) > 0) return false;
        l3 = l2;
        long l4 = this.getLast-s-VKNKU();
        boolean bl2 = false;
        if (UnsignedKt.ulongCompare(l3, l4) > 0) return false;
        return true;
    }

    @Override
    public boolean isEmpty() {
        long l2 = this.getFirst-s-VKNKU();
        long l3 = this.getLast-s-VKNKU();
        boolean bl = false;
        return UnsignedKt.ulongCompare(l2, l3) > 0;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return object instanceof ULongRange && (this.isEmpty() && ((ULongRange)object).isEmpty() || this.getFirst-s-VKNKU() == ((ULongRange)object).getFirst-s-VKNKU() && this.getLast-s-VKNKU() == ((ULongRange)object).getLast-s-VKNKU());
    }

    @Override
    public int hashCode() {
        int n2;
        if (this.isEmpty()) {
            n2 = -1;
        } else {
            long l2 = this.getFirst-s-VKNKU();
            long l3 = this.getFirst-s-VKNKU();
            int n3 = 32;
            boolean bl = false;
            l3 = ULong.constructor-impl(l3 >>> n3);
            n3 = 0;
            l2 = ULong.constructor-impl(l2 ^ l3);
            boolean bl2 = false;
            int n4 = 31 * (int)l2;
            l2 = this.getLast-s-VKNKU();
            long l4 = this.getLast-s-VKNKU();
            n3 = 32;
            bl = false;
            l4 = ULong.constructor-impl(l4 >>> n3);
            n3 = 0;
            l2 = ULong.constructor-impl(l2 ^ l4);
            boolean bl3 = false;
            n2 = n4 + (int)l2;
        }
        return n2;
    }

    @Override
    @NotNull
    public String toString() {
        return ULong.toString-impl(this.getFirst-s-VKNKU()) + ".." + ULong.toString-impl(this.getLast-s-VKNKU());
    }

    private ULongRange(long l2, long l3) {
        super(l2, l3, 1L, null);
    }

    static {
        Companion = new Companion(null);
        EMPTY = new ULongRange(-1L, 0L);
    }

    public /* synthetic */ ULongRange(long l2, long l3, DefaultConstructorMarker defaultConstructorMarker) {
        this(l2, l3);
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lkotlin/ranges/ULongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/ULongRange;", "getEMPTY", "()Lkotlin/ranges/ULongRange;", "kotlin-stdlib"})
    public static final class Companion {
        @NotNull
        public final ULongRange getEMPTY() {
            return EMPTY;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

