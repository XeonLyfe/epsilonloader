/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aF\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00012\u001c\b\u0004\u0010\u0005\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0083\b\u00a2\u0006\u0002\b\b\u001aD\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u001a]\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0003*#\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f\u00a2\u0006\u0002\b\r2\u0006\u0010\u000e\u001a\u0002H\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u001a\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u001aA\u0010\u0011\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0003*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012\u001aZ\u0010\u0011\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0003*#\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f\u00a2\u0006\u0002\b\r2\u0006\u0010\u000e\u001a\u0002H\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013\u001an\u0010\u0011\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0014\"\u0004\b\u0002\u0010\u0003*)\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0015\u00a2\u0006\u0002\b\r2\u0006\u0010\u000e\u001a\u0002H\u000b2\u0006\u0010\u0016\u001a\u0002H\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0081\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2={"createCoroutineFromSuspendFunction", "Lkotlin/coroutines/Continuation;", "", "T", "completion", "block", "Lkotlin/Function1;", "", "createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt", "createCoroutineUnintercepted", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "intercepted", "startCoroutineUninterceptedOrReturn", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "P", "Lkotlin/Function3;", "param", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"}, xs="kotlin/coroutines/intrinsics/IntrinsicsKt")
class IntrinsicsKt__IntrinsicsJvmKt {
    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <T> Object startCoroutineUninterceptedOrReturn(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super T> continuation) {
        int n2 = 0;
        Function1<? super Continuation<? super T>, ? extends Object> function12 = function1;
        if (function12 == null) {
            throw new NullPointerException("null cannot be cast to non-null type (kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
        }
        return ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function12, 1)).invoke(continuation);
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <R, T> Object startCoroutineUninterceptedOrReturn(Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r2, Continuation<? super T> continuation) {
        int n2 = 0;
        Function2<? super R, ? super Continuation<? super T>, ? extends Object> function22 = function2;
        if (function22 == null) {
            throw new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
        }
        return ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function22, 2)).invoke(r2, continuation);
    }

    @InlineOnly
    private static final <R, P, T> Object startCoroutineUninterceptedOrReturn(Function3<? super R, ? super P, ? super Continuation<? super T>, ? extends Object> function3, R r2, P p2, Continuation<? super T> continuation) {
        int n2 = 0;
        Function3<? super R, ? super P, ? super Continuation<? super T>, ? extends Object> function32 = function3;
        if (function32 == null) {
            throw new NullPointerException("null cannot be cast to non-null type (R, P, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
        }
        return ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function32, 3)).invoke(r2, p2, continuation);
    }

    @SinceKotlin(version="1.3")
    @NotNull
    public static final <T> Continuation<Unit> createCoroutineUnintercepted(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        Continuation continuation2;
        Intrinsics.checkNotNullParameter(function1, "$this$createCoroutineUnintercepted");
        Intrinsics.checkNotNullParameter(continuation, "completion");
        Continuation<T> continuation3 = DebugProbesKt.probeCoroutineCreated(continuation);
        if (function1 instanceof BaseContinuationImpl) {
            continuation2 = ((BaseContinuationImpl)((Object)function1)).create(continuation3);
        } else {
            boolean bl = false;
            CoroutineContext coroutineContext = continuation3.getContext();
            if (coroutineContext == EmptyCoroutineContext.INSTANCE) {
                Continuation<T> continuation4 = continuation3;
                if (continuation4 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
                }
                continuation2 = new RestrictedContinuationImpl(continuation3, continuation4, function1){
                    private int label;
                    final /* synthetic */ Continuation $completion;
                    final /* synthetic */ Function1 $this_createCoroutineUnintercepted$inlined;
                    {
                        this.$completion = continuation;
                        this.$this_createCoroutineUnintercepted$inlined = function1;
                        super(continuation2);
                    }

                    @Nullable
                    protected Object invokeSuspend(@NotNull Object object) {
                        Object object2;
                        switch (this.label) {
                            case 0: {
                                this.label = 1;
                                Object object3 = object;
                                boolean bl = false;
                                ResultKt.throwOnFailure(object3);
                                Continuation continuation = this;
                                boolean bl2 = false;
                                Function1 function1 = this.$this_createCoroutineUnintercepted$inlined;
                                if (function1 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type (kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
                                }
                                object2 = ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1)).invoke(continuation);
                                break;
                            }
                            case 1: {
                                this.label = 2;
                                Object object4 = object;
                                boolean bl = false;
                                ResultKt.throwOnFailure(object4);
                                object2 = object4;
                                break;
                            }
                            default: {
                                String string = "This coroutine had already completed";
                                boolean bl = false;
                                throw (Throwable)new IllegalStateException(string.toString());
                            }
                        }
                        return object2;
                    }
                };
            } else {
                Continuation<T> continuation5 = continuation3;
                if (continuation5 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
                }
                continuation2 = new ContinuationImpl(continuation3, coroutineContext, continuation5, coroutineContext, function1){
                    private int label;
                    final /* synthetic */ Continuation $completion;
                    final /* synthetic */ CoroutineContext $context;
                    final /* synthetic */ Function1 $this_createCoroutineUnintercepted$inlined;
                    {
                        this.$completion = continuation;
                        this.$context = coroutineContext;
                        this.$this_createCoroutineUnintercepted$inlined = function1;
                        super(continuation2, coroutineContext2);
                    }

                    @Nullable
                    protected Object invokeSuspend(@NotNull Object object) {
                        Object object2;
                        switch (this.label) {
                            case 0: {
                                this.label = 1;
                                Object object3 = object;
                                boolean bl = false;
                                ResultKt.throwOnFailure(object3);
                                Continuation continuation = this;
                                boolean bl2 = false;
                                Function1 function1 = this.$this_createCoroutineUnintercepted$inlined;
                                if (function1 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type (kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
                                }
                                object2 = ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1)).invoke(continuation);
                                break;
                            }
                            case 1: {
                                this.label = 2;
                                Object object4 = object;
                                boolean bl = false;
                                ResultKt.throwOnFailure(object4);
                                object2 = object4;
                                break;
                            }
                            default: {
                                String string = "This coroutine had already completed";
                                boolean bl = false;
                                throw (Throwable)new IllegalStateException(string.toString());
                            }
                        }
                        return object2;
                    }
                };
            }
        }
        return continuation2;
    }

    @SinceKotlin(version="1.3")
    @NotNull
    public static final <R, T> Continuation<Unit> createCoroutineUnintercepted(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r2, @NotNull Continuation<? super T> continuation) {
        Continuation continuation2;
        Intrinsics.checkNotNullParameter(function2, "$this$createCoroutineUnintercepted");
        Intrinsics.checkNotNullParameter(continuation, "completion");
        Continuation<T> continuation3 = DebugProbesKt.probeCoroutineCreated(continuation);
        if (function2 instanceof BaseContinuationImpl) {
            continuation2 = ((BaseContinuationImpl)((Object)function2)).create(r2, continuation3);
        } else {
            boolean bl = false;
            CoroutineContext coroutineContext = continuation3.getContext();
            if (coroutineContext == EmptyCoroutineContext.INSTANCE) {
                Continuation<T> continuation4 = continuation3;
                if (continuation4 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
                }
                continuation2 = new RestrictedContinuationImpl(continuation3, continuation4, function2, r2){
                    private int label;
                    final /* synthetic */ Continuation $completion;
                    final /* synthetic */ Function2 $this_createCoroutineUnintercepted$inlined;
                    final /* synthetic */ Object $receiver$inlined;
                    {
                        this.$completion = continuation;
                        this.$this_createCoroutineUnintercepted$inlined = function2;
                        this.$receiver$inlined = object;
                        super(continuation2);
                    }

                    @Nullable
                    protected Object invokeSuspend(@NotNull Object object) {
                        Object object2;
                        switch (this.label) {
                            case 0: {
                                this.label = 1;
                                Object object3 = object;
                                boolean bl = false;
                                ResultKt.throwOnFailure(object3);
                                Continuation continuation = this;
                                boolean bl2 = false;
                                Function2 function2 = this.$this_createCoroutineUnintercepted$inlined;
                                if (function2 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
                                }
                                object2 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(this.$receiver$inlined, continuation);
                                break;
                            }
                            case 1: {
                                this.label = 2;
                                Object object4 = object;
                                boolean bl = false;
                                ResultKt.throwOnFailure(object4);
                                object2 = object4;
                                break;
                            }
                            default: {
                                String string = "This coroutine had already completed";
                                boolean bl = false;
                                throw (Throwable)new IllegalStateException(string.toString());
                            }
                        }
                        return object2;
                    }
                };
            } else {
                Continuation<T> continuation5 = continuation3;
                if (continuation5 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
                }
                continuation2 = new ContinuationImpl(continuation3, coroutineContext, continuation5, coroutineContext, function2, r2){
                    private int label;
                    final /* synthetic */ Continuation $completion;
                    final /* synthetic */ CoroutineContext $context;
                    final /* synthetic */ Function2 $this_createCoroutineUnintercepted$inlined;
                    final /* synthetic */ Object $receiver$inlined;
                    {
                        this.$completion = continuation;
                        this.$context = coroutineContext;
                        this.$this_createCoroutineUnintercepted$inlined = function2;
                        this.$receiver$inlined = object;
                        super(continuation2, coroutineContext2);
                    }

                    @Nullable
                    protected Object invokeSuspend(@NotNull Object object) {
                        Object object2;
                        switch (this.label) {
                            case 0: {
                                this.label = 1;
                                Object object3 = object;
                                boolean bl = false;
                                ResultKt.throwOnFailure(object3);
                                Continuation continuation = this;
                                boolean bl2 = false;
                                Function2 function2 = this.$this_createCoroutineUnintercepted$inlined;
                                if (function2 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
                                }
                                object2 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(this.$receiver$inlined, continuation);
                                break;
                            }
                            case 1: {
                                this.label = 2;
                                Object object4 = object;
                                boolean bl = false;
                                ResultKt.throwOnFailure(object4);
                                object2 = object4;
                                break;
                            }
                            default: {
                                String string = "This coroutine had already completed";
                                boolean bl = false;
                                throw (Throwable)new IllegalStateException(string.toString());
                            }
                        }
                        return object2;
                    }
                };
            }
        }
        return continuation2;
    }

    @SinceKotlin(version="1.3")
    @NotNull
    public static final <T> Continuation<T> intercepted(@NotNull Continuation<? super T> continuation) {
        Continuation<Object> continuation2;
        Intrinsics.checkNotNullParameter(continuation, "$this$intercepted");
        Continuation<? super T> continuation3 = continuation;
        if (!(continuation3 instanceof ContinuationImpl)) {
            continuation3 = null;
        }
        if ((continuation2 = (ContinuationImpl)continuation3) == null || (continuation2 = continuation2.intercepted()) == null) {
            continuation2 = continuation;
        }
        return continuation2;
    }

    @SinceKotlin(version="1.3")
    private static final <T> Continuation<Unit> createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(Continuation<? super T> continuation, Function1<? super Continuation<? super T>, ? extends Object> function1) {
        Continuation continuation2;
        int n2 = 0;
        CoroutineContext coroutineContext = continuation.getContext();
        if (coroutineContext == EmptyCoroutineContext.INSTANCE) {
            Continuation<T> continuation3 = continuation;
            if (continuation3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }
            continuation2 = new RestrictedContinuationImpl(function1, continuation, continuation3){
                private int label;
                final /* synthetic */ Function1 $block;
                final /* synthetic */ Continuation $completion;

                @Nullable
                protected Object invokeSuspend(@NotNull Object object) {
                    Object object2;
                    switch (this.label) {
                        case 0: {
                            this.label = 1;
                            Object object3 = object;
                            boolean bl = false;
                            ResultKt.throwOnFailure(object3);
                            object2 = this.$block.invoke(this);
                            break;
                        }
                        case 1: {
                            this.label = 2;
                            Object object4 = object;
                            boolean bl = false;
                            ResultKt.throwOnFailure(object4);
                            object2 = object4;
                            break;
                        }
                        default: {
                            String string = "This coroutine had already completed";
                            boolean bl = false;
                            throw (Throwable)new IllegalStateException(string.toString());
                        }
                    }
                    return object2;
                }
                {
                    this.$block = function1;
                    this.$completion = continuation;
                    super(continuation2);
                }
            };
        } else {
            Continuation<T> continuation4 = continuation;
            if (continuation4 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }
            continuation2 = new ContinuationImpl(function1, continuation, coroutineContext, continuation4, coroutineContext){
                private int label;
                final /* synthetic */ Function1 $block;
                final /* synthetic */ Continuation $completion;
                final /* synthetic */ CoroutineContext $context;

                @Nullable
                protected Object invokeSuspend(@NotNull Object object) {
                    Object object2;
                    switch (this.label) {
                        case 0: {
                            this.label = 1;
                            Object object3 = object;
                            boolean bl = false;
                            ResultKt.throwOnFailure(object3);
                            object2 = this.$block.invoke(this);
                            break;
                        }
                        case 1: {
                            this.label = 2;
                            Object object4 = object;
                            boolean bl = false;
                            ResultKt.throwOnFailure(object4);
                            object2 = object4;
                            break;
                        }
                        default: {
                            String string = "This coroutine had already completed";
                            boolean bl = false;
                            throw (Throwable)new IllegalStateException(string.toString());
                        }
                    }
                    return object2;
                }
                {
                    this.$block = function1;
                    this.$completion = continuation;
                    this.$context = coroutineContext;
                    super(continuation2, coroutineContext2);
                }
            };
        }
        return continuation2;
    }
}

