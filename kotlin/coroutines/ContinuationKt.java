/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0004\u001a?\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u00020\u00012\u001a\b\u0004\u0010\n\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\f\u0012\u0004\u0012\u00020\r0\u000bH\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u001a@\u0010\u000e\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\u001a\b\u0004\u0010\u000f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0004\u0012\u00020\r0\u000bH\u0087H\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\u0010\u001aD\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0007\"\u0004\b\u0000\u0010\b*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014\u001a]\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0007\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\b*#\b\u0001\u0012\u0004\u0012\u0002H\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0016\u00a2\u0006\u0002\b\u00172\u0006\u0010\u0018\u001a\u0002H\u00152\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019\u001a&\u0010\u001a\u001a\u00020\r\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0006\u0010\u001b\u001a\u0002H\bH\u0087\b\u00a2\u0006\u0002\u0010\u001c\u001a!\u0010\u001d\u001a\u00020\r\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0006\u0010\u001e\u001a\u00020\u001fH\u0087\b\u001a>\u0010 \u001a\u00020\r\"\u0004\b\u0000\u0010\b*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!\u001aW\u0010 \u001a\u00020\r\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\b*#\b\u0001\u0012\u0004\u0012\u0002H\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0016\u00a2\u0006\u0002\b\u00172\u0006\u0010\u0018\u001a\u0002H\u00152\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"\"\u001b\u0010\u0000\u001a\u00020\u00018\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001\u00a8\u0006#"}, d2={"coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext$annotations", "()V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "Continuation", "Lkotlin/coroutines/Continuation;", "T", "context", "resumeWith", "Lkotlin/Function1;", "Lkotlin/Result;", "", "suspendCoroutine", "block", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createCoroutine", "", "completion", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "resume", "value", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "resumeWithException", "exception", "", "startCoroutine", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "kotlin-stdlib"})
public final class ContinuationKt {
    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <T> void resume(Continuation<? super T> continuation, T t2) {
        int n2 = 0;
        Result.Companion companion = Result.Companion;
        boolean bl = false;
        continuation.resumeWith(Result.constructor-impl(t2));
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <T> void resumeWithException(Continuation<? super T> continuation, Throwable throwable) {
        int n2 = 0;
        Result.Companion companion = Result.Companion;
        boolean bl = false;
        continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable)));
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <T> Continuation<T> Continuation(CoroutineContext coroutineContext, Function1<? super Result<? extends T>, Unit> function1) {
        int n2 = 0;
        return new Continuation<T>(coroutineContext, function1){
            final /* synthetic */ CoroutineContext $context;
            final /* synthetic */ Function1 $resumeWith;

            @NotNull
            public CoroutineContext getContext() {
                return this.$context;
            }

            public void resumeWith(@NotNull Object object) {
                this.$resumeWith.invoke(Result.box-impl(object));
            }
            {
                this.$context = coroutineContext;
                this.$resumeWith = function1;
            }
        };
    }

    @SinceKotlin(version="1.3")
    @NotNull
    public static final <T> Continuation<Unit> createCoroutine(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkNotNullParameter(function1, "$this$createCoroutine");
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new SafeContinuation<Unit>(IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function1, continuation)), IntrinsicsKt.getCOROUTINE_SUSPENDED());
    }

    @SinceKotlin(version="1.3")
    @NotNull
    public static final <R, T> Continuation<Unit> createCoroutine(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r2, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkNotNullParameter(function2, "$this$createCoroutine");
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new SafeContinuation<Unit>(IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function2, r2, continuation)), IntrinsicsKt.getCOROUTINE_SUSPENDED());
    }

    @SinceKotlin(version="1.3")
    public static final <T> void startCoroutine(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkNotNullParameter(function1, "$this$startCoroutine");
        Intrinsics.checkNotNullParameter(continuation, "completion");
        Continuation<Unit> continuation2 = IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function1, continuation));
        Unit unit = Unit.INSTANCE;
        boolean bl = false;
        Result.Companion companion = Result.Companion;
        boolean bl2 = false;
        continuation2.resumeWith(Result.constructor-impl(unit));
    }

    @SinceKotlin(version="1.3")
    public static final <R, T> void startCoroutine(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r2, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkNotNullParameter(function2, "$this$startCoroutine");
        Intrinsics.checkNotNullParameter(continuation, "completion");
        Continuation<Unit> continuation2 = IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function2, r2, continuation));
        Unit unit = Unit.INSTANCE;
        boolean bl = false;
        Result.Companion companion = Result.Companion;
        boolean bl2 = false;
        continuation2.resumeWith(Result.constructor-impl(unit));
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <T> Object suspendCoroutine(Function1<? super Continuation<? super T>, Unit> function1, Continuation<? super T> continuation) {
        int n2 = 0;
        boolean bl = false;
        InlineMarker.mark(0);
        Continuation<? super T> continuation2 = continuation;
        boolean bl2 = false;
        SafeContinuation<T> safeContinuation = new SafeContinuation<T>(IntrinsicsKt.intercepted(continuation2));
        function1.invoke(safeContinuation);
        Object object = safeContinuation.getOrThrow();
        if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return object;
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    public static /* synthetic */ void getCoroutineContext$annotations() {
    }

    private static final CoroutineContext getCoroutineContext() {
        int n2 = 0;
        throw (Throwable)new NotImplementedError("Implemented as intrinsic");
    }
}

