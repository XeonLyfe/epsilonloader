/*
 * Decompiled with CFR 0.150.
 */
package kotlin;

import kotlin.Metadata;
import kotlin.PreconditionsKt__AssertionsJVMKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u00f8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a/\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\bH\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001\u00a2\u0006\u0002\u0010\t\u001a@\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001\u00a2\u0006\u0002\u0010\n\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0087\b\u001a\u001c\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a-\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u00f8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a/\u0010\u000f\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\bH\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001\u00a2\u0006\u0002\u0010\t\u001a@\u0010\u000f\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u0010"}, d2={"check", "", "value", "", "lazyMessage", "Lkotlin/Function0;", "", "checkNotNull", "T", "(Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "error", "", "message", "require", "requireNotNull", "kotlin-stdlib"}, xs="kotlin/PreconditionsKt")
class PreconditionsKt__PreconditionsKt
extends PreconditionsKt__AssertionsJVMKt {
    @InlineOnly
    private static final void require(boolean bl) {
        int n2 = 0;
        boolean bl2 = false;
        bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Failed requirement.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
    }

    @InlineOnly
    private static final void require(boolean bl, Function0<? extends Object> function0) {
        int n2 = 0;
        boolean bl2 = false;
        if (!bl) {
            Object object = function0.invoke();
            throw (Throwable)new IllegalArgumentException(object.toString());
        }
    }

    @InlineOnly
    private static final <T> T requireNotNull(T t2) {
        int n2 = 0;
        boolean bl = false;
        bl = false;
        boolean bl2 = false;
        if (t2 == null) {
            boolean bl3 = false;
            String string = "Required value was null.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        return t2;
    }

    @InlineOnly
    private static final <T> T requireNotNull(T t2, Function0<? extends Object> function0) {
        int n2 = 0;
        boolean bl = false;
        if (t2 == null) {
            Object object = function0.invoke();
            throw (Throwable)new IllegalArgumentException(object.toString());
        }
        return t2;
    }

    @InlineOnly
    private static final void check(boolean bl) {
        int n2 = 0;
        boolean bl2 = false;
        bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Check failed.";
            throw (Throwable)new IllegalStateException(string.toString());
        }
    }

    @InlineOnly
    private static final void check(boolean bl, Function0<? extends Object> function0) {
        int n2 = 0;
        boolean bl2 = false;
        if (!bl) {
            Object object = function0.invoke();
            throw (Throwable)new IllegalStateException(object.toString());
        }
    }

    @InlineOnly
    private static final <T> T checkNotNull(T t2) {
        int n2 = 0;
        boolean bl = false;
        bl = false;
        boolean bl2 = false;
        if (t2 == null) {
            boolean bl3 = false;
            String string = "Required value was null.";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        return t2;
    }

    @InlineOnly
    private static final <T> T checkNotNull(T t2, Function0<? extends Object> function0) {
        int n2 = 0;
        boolean bl = false;
        if (t2 == null) {
            Object object = function0.invoke();
            throw (Throwable)new IllegalStateException(object.toString());
        }
        return t2;
    }

    @InlineOnly
    private static final Void error(Object object) {
        int n2 = 0;
        throw (Throwable)new IllegalStateException(object.toString());
    }
}

