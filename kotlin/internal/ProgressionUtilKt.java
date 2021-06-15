/*
 * Decompiled with CFR 0.150.
 */
package kotlin.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a \u0010\u0000\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a \u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0001\u001a \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0001\u001a\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0002\u00a8\u0006\u000b"}, d2={"differenceModulo", "", "a", "b", "c", "", "getProgressionLastElement", "start", "end", "step", "mod", "kotlin-stdlib"})
public final class ProgressionUtilKt {
    private static final int mod(int n2, int n3) {
        int n4 = n2 % n3;
        return n4 >= 0 ? n4 : n4 + n3;
    }

    private static final long mod(long l2, long l3) {
        long l4 = l2 % l3;
        return l4 >= 0L ? l4 : l4 + l3;
    }

    private static final int differenceModulo(int n2, int n3, int n4) {
        return ProgressionUtilKt.mod(ProgressionUtilKt.mod(n2, n4) - ProgressionUtilKt.mod(n3, n4), n4);
    }

    private static final long differenceModulo(long l2, long l3, long l4) {
        return ProgressionUtilKt.mod(ProgressionUtilKt.mod(l2, l4) - ProgressionUtilKt.mod(l3, l4), l4);
    }

    @PublishedApi
    public static final int getProgressionLastElement(int n2, int n3, int n4) {
        int n5;
        if (n4 > 0) {
            n5 = n2 >= n3 ? n3 : n3 - ProgressionUtilKt.differenceModulo(n3, n2, n4);
        } else if (n4 < 0) {
            n5 = n2 <= n3 ? n3 : n3 + ProgressionUtilKt.differenceModulo(n2, n3, -n4);
        } else {
            throw (Throwable)new IllegalArgumentException("Step is zero.");
        }
        return n5;
    }

    @PublishedApi
    public static final long getProgressionLastElement(long l2, long l3, long l4) {
        long l5;
        if (l4 > 0L) {
            l5 = l2 >= l3 ? l3 : l3 - ProgressionUtilKt.differenceModulo(l3, l2, l4);
        } else if (l4 < 0L) {
            l5 = l2 <= l3 ? l3 : l3 + ProgressionUtilKt.differenceModulo(l2, l3, -l4);
        } else {
            throw (Throwable)new IllegalArgumentException("Step is zero.");
        }
        return l5;
    }
}

