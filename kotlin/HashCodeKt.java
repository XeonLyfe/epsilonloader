/*
 * Decompiled with CFR 0.150.
 */
package kotlin;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000\f\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0000\n\u0000\u001a\u000f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002H\u0087\b\u00a8\u0006\u0003"}, d2={"hashCode", "", "", "kotlin-stdlib"})
public final class HashCodeKt {
    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final int hashCode(Object object) {
        int n2 = 0;
        Object object2 = object;
        return object2 != null ? object2.hashCode() : 0;
    }
}

