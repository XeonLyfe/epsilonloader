/*
 * Decompiled with CFR 0.150.
 */
package kotlin;

import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000:\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\t\u0010\u0000\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u001a3\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\tH\u0087\b\u00f8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u001a2\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\u000e\u001aK\u0010\u000f\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u000b2\u0006\u0010\u0011\u001a\u0002H\u00102\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u000b0\t\u00a2\u0006\u0002\b\u0012H\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u00a2\u0006\u0002\u0010\u0013\u001a<\u0010\u0014\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\u0002H\u00102\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00050\tH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\u0013\u001aA\u0010\u0015\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\u0002H\u00102\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00050\t\u00a2\u0006\u0002\b\u0012H\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\u0013\u001aB\u0010\u0016\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u000b*\u0002H\u00102\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u000b0\tH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\u0013\u001aG\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u000b*\u0002H\u00102\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u000b0\t\u00a2\u0006\u0002\b\u0012H\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\u0013\u001a>\u0010\u0017\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\u0002H\u00102\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00190\tH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\u0013\u001a>\u0010\u001a\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\u0002H\u00102\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00190\tH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\u0013\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u001b"}, d2={"TODO", "", "reason", "", "repeat", "", "times", "", "action", "Lkotlin/Function1;", "run", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "with", "T", "receiver", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "also", "apply", "let", "takeIf", "predicate", "", "takeUnless", "kotlin-stdlib"}, xs="kotlin/StandardKt")
class StandardKt__StandardKt {
    @InlineOnly
    private static final Void TODO() {
        int n2 = 0;
        throw (Throwable)new NotImplementedError(null, 1, null);
    }

    @InlineOnly
    private static final Void TODO(String string) {
        int n2 = 0;
        throw (Throwable)new NotImplementedError("An operation is not implemented: " + string);
    }

    @InlineOnly
    private static final <R> R run(Function0<? extends R> function0) {
        int n2 = 0;
        boolean bl = false;
        return function0.invoke();
    }

    @InlineOnly
    private static final <T, R> R run(T t2, Function1<? super T, ? extends R> function1) {
        int n2 = 0;
        boolean bl = false;
        return function1.invoke(t2);
    }

    @InlineOnly
    private static final <T, R> R with(T t2, Function1<? super T, ? extends R> function1) {
        int n2 = 0;
        boolean bl = false;
        return function1.invoke(t2);
    }

    @InlineOnly
    private static final <T> T apply(T t2, Function1<? super T, Unit> function1) {
        int n2 = 0;
        boolean bl = false;
        function1.invoke(t2);
        return t2;
    }

    @InlineOnly
    @SinceKotlin(version="1.1")
    private static final <T> T also(T t2, Function1<? super T, Unit> function1) {
        int n2 = 0;
        boolean bl = false;
        function1.invoke(t2);
        return t2;
    }

    @InlineOnly
    private static final <T, R> R let(T t2, Function1<? super T, ? extends R> function1) {
        int n2 = 0;
        boolean bl = false;
        return function1.invoke(t2);
    }

    @InlineOnly
    @SinceKotlin(version="1.1")
    private static final <T> T takeIf(T t2, Function1<? super T, Boolean> function1) {
        int n2 = 0;
        boolean bl = false;
        return (T)(function1.invoke(t2) != false ? t2 : null);
    }

    @InlineOnly
    @SinceKotlin(version="1.1")
    private static final <T> T takeUnless(T t2, Function1<? super T, Boolean> function1) {
        int n2 = 0;
        boolean bl = false;
        return (T)(function1.invoke(t2) == false ? t2 : null);
    }

    @InlineOnly
    private static final void repeat(int n2, Function1<? super Integer, Unit> function1) {
        int n3 = 0;
        int n4 = 0;
        int n5 = n2;
        for (n4 = 0; n4 < n5; ++n4) {
            function1.invoke((Integer)n4);
        }
    }
}

