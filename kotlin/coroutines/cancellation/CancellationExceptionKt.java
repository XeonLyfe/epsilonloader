/*
 * Decompiled with CFR 0.150.
 */
package kotlin.coroutines.cancellation;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0087\b\u001a\u0017\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0087\b*\u001a\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u00a8\u0006\n"}, d2={"CancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlin/coroutines/cancellation/CancellationException;", "message", "", "cause", "", "Lkotlin/SinceKotlin;", "version", "1.4", "kotlin-stdlib"})
public final class CancellationExceptionKt {
    @SinceKotlin(version="1.4")
    public static /* synthetic */ void CancellationException$annotations() {
    }

    @InlineOnly
    @SinceKotlin(version="1.4")
    private static final CancellationException CancellationException(String string, Throwable throwable) {
        int n2 = 0;
        CancellationException cancellationException = new CancellationException(string);
        boolean bl = false;
        boolean bl2 = false;
        CancellationException cancellationException2 = cancellationException;
        boolean bl3 = false;
        cancellationException2.initCause(throwable);
        return cancellationException;
    }

    @InlineOnly
    @SinceKotlin(version="1.4")
    private static final CancellationException CancellationException(Throwable throwable) {
        int n2 = 0;
        Throwable throwable2 = throwable;
        CancellationException cancellationException = new CancellationException(throwable2 != null ? throwable2.toString() : null);
        boolean bl = false;
        boolean bl2 = false;
        CancellationException cancellationException2 = cancellationException;
        boolean bl3 = false;
        cancellationException2.initCause(throwable);
        return cancellationException;
    }
}

