/*
 * Decompiled with CFR 0.150.
 */
package kotlin.reflect;

import kotlin.Metadata;
import kotlin.reflect.KVariance;

@Metadata(mv={1, 5, 1}, k=3)
public final class KTypeProjection$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static /* synthetic */ {
        $EnumSwitchMapping$0 = new int[KVariance.values().length];
        KTypeProjection$WhenMappings.$EnumSwitchMapping$0[KVariance.INVARIANT.ordinal()] = 1;
        KTypeProjection$WhenMappings.$EnumSwitchMapping$0[KVariance.IN.ordinal()] = 2;
        KTypeProjection$WhenMappings.$EnumSwitchMapping$0[KVariance.OUT.ordinal()] = 3;
    }
}

