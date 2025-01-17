/*
 * Decompiled with CFR 0.150.
 */
package kotlin.experimental;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0004\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0003*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0005\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u00a8\u0006\u0007"}, d2={"and", "", "other", "", "inv", "or", "xor", "kotlin-stdlib"})
public final class BitwiseOperationsKt {
    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final byte and(byte by, byte by2) {
        int n2 = 0;
        return (byte)(by & by2);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final byte or(byte by, byte by2) {
        int n2 = 0;
        return (byte)(by | by2);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final byte xor(byte by, byte by2) {
        int n2 = 0;
        return (byte)(by ^ by2);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final byte inv(byte by) {
        int n2 = 0;
        return ~by;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final short and(short s2, short s3) {
        int n2 = 0;
        return (short)(s2 & s3);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final short or(short s2, short s3) {
        int n2 = 0;
        return (short)(s2 | s3);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final short xor(short s2, short s3) {
        int n2 = 0;
        return (short)(s2 ^ s3);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final short inv(short s2) {
        int n2 = 0;
        return ~s2;
    }
}

