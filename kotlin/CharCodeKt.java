/*
 * Decompiled with CFR 0.150.
 */
package kotlin;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UShort;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0010\f\n\u0002\b\u0006\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0000\u001a\u00020\u0001H\u0087\b\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\b"}, d2={"code", "", "", "getCode$annotations", "(C)V", "getCode", "(C)I", "Char", "kotlin-stdlib"})
public final class CharCodeKt {
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final char Char(int n2) {
        int n3 = 0;
        if (n2 < CharCodeKt.getCode('\u0000') || n2 > CharCodeKt.getCode('\uffff')) {
            throw (Throwable)new IllegalArgumentException("Invalid Char code: " + n2);
        }
        int n4 = n2;
        boolean bl = false;
        n4 = UShort.constructor-impl((short)n4);
        bl = false;
        int n5 = n4;
        boolean bl2 = false;
        return (char)(n5 & 0xFFFF);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    public static /* synthetic */ void getCode$annotations(char c2) {
    }

    private static final int getCode(char c2) {
        int n2 = 0;
        return c2;
    }
}

