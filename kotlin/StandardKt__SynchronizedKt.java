/*
 * Decompiled with CFR 0.150.
 */
package kotlin;

import kotlin.Metadata;
import kotlin.StandardKt__StandardKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\u0012\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a:\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u0007"}, d2={"synchronized", "R", "lock", "", "block", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-stdlib"}, xs="kotlin/StandardKt")
class StandardKt__SynchronizedKt
extends StandardKt__StandardKt {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @InlineOnly
    private static final <R> R synchronized(Object object, Function0<? extends R> function0) {
        int n2 = 0;
        boolean bl = false;
        synchronized (object) {
            try {
                R r2 = function0.invoke();
                return r2;
            }
            finally {
                InlineMarker.finallyStart(1);
                // MONITOREXIT [1, 3] lbl10 : MonitorExitStatement: MONITOREXIT : var0
                InlineMarker.finallyEnd(1);
            }
        }
    }
}

