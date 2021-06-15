/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.reflect.KVariance;

@Metadata(mv={1, 5, 1}, k=3)
public final class TypeParameterReference$Companion$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static /* synthetic */ {
        $EnumSwitchMapping$0 = new int[KVariance.values().length];
        TypeParameterReference$Companion$WhenMappings.$EnumSwitchMapping$0[KVariance.INVARIANT.ordinal()] = 1;
        TypeParameterReference$Companion$WhenMappings.$EnumSwitchMapping$0[KVariance.IN.ordinal()] = 2;
        TypeParameterReference$Companion$WhenMappings.$EnumSwitchMapping$0[KVariance.OUT.ordinal()] = 3;
    }
}

