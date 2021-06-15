/*
 * Decompiled with CFR 0.150.
 */
package kotlin.contracts;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.contracts.ExperimentalContracts;
import kotlin.internal.ContractsDsl;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2={"Lkotlin/contracts/InvocationKind;", "", "(Ljava/lang/String;I)V", "AT_MOST_ONCE", "AT_LEAST_ONCE", "EXACTLY_ONCE", "UNKNOWN", "kotlin-stdlib"})
@ContractsDsl
@ExperimentalContracts
@SinceKotlin(version="1.3")
public final class InvocationKind
extends Enum<InvocationKind> {
    @ContractsDsl
    public static final /* enum */ InvocationKind AT_MOST_ONCE;
    @ContractsDsl
    public static final /* enum */ InvocationKind AT_LEAST_ONCE;
    @ContractsDsl
    public static final /* enum */ InvocationKind EXACTLY_ONCE;
    @ContractsDsl
    public static final /* enum */ InvocationKind UNKNOWN;
    private static final /* synthetic */ InvocationKind[] $VALUES;

    static {
        InvocationKind[] arrinvocationKind = new InvocationKind[4];
        InvocationKind[] arrinvocationKind2 = arrinvocationKind;
        arrinvocationKind[0] = AT_MOST_ONCE = new InvocationKind();
        arrinvocationKind[1] = AT_LEAST_ONCE = new InvocationKind();
        arrinvocationKind[2] = EXACTLY_ONCE = new InvocationKind();
        arrinvocationKind[3] = UNKNOWN = new InvocationKind();
        $VALUES = arrinvocationKind;
    }

    public static InvocationKind[] values() {
        return (InvocationKind[])$VALUES.clone();
    }

    public static InvocationKind valueOf(String string) {
        return Enum.valueOf(InvocationKind.class, string);
    }
}

