/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.concurrent;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aJ\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u001a3\u0010\u000e\u001a\u0002H\u000f\"\b\b\u0000\u0010\u000f*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u000f0\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\fH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u0014"}, d2={"thread", "Ljava/lang/Thread;", "start", "", "isDaemon", "contextClassLoader", "Ljava/lang/ClassLoader;", "name", "", "priority", "", "block", "Lkotlin/Function0;", "", "getOrSet", "T", "", "Ljava/lang/ThreadLocal;", "default", "(Ljava/lang/ThreadLocal;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-stdlib"})
@JvmName(name="ThreadsKt")
public final class ThreadsKt {
    @NotNull
    public static final Thread thread(boolean bl, boolean bl2, @Nullable ClassLoader classLoader, @Nullable String string, int n2, @NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "block");
        Thread thread2 = new Thread(function0){
            final /* synthetic */ Function0 $block;

            public void run() {
                this.$block.invoke();
            }
            {
                this.$block = function0;
            }
        };
        if (bl2) {
            thread2.setDaemon(true);
        }
        if (n2 > 0) {
            thread2.setPriority(n2);
        }
        if (string != null) {
            thread2.setName(string);
        }
        if (classLoader != null) {
            thread2.setContextClassLoader(classLoader);
        }
        if (bl) {
            thread2.start();
        }
        return thread2;
    }

    public static /* synthetic */ Thread thread$default(boolean bl, boolean bl2, ClassLoader classLoader, String string, int n2, Function0 function0, int n3, Object object) {
        if ((n3 & 1) != 0) {
            bl = true;
        }
        if ((n3 & 2) != 0) {
            bl2 = false;
        }
        if ((n3 & 4) != 0) {
            classLoader = null;
        }
        if ((n3 & 8) != 0) {
            string = null;
        }
        if ((n3 & 0x10) != 0) {
            n2 = -1;
        }
        return ThreadsKt.thread(bl, bl2, classLoader, string, n2, function0);
    }

    @InlineOnly
    private static final <T> T getOrSet(ThreadLocal<T> threadLocal, Function0<? extends T> function0) {
        int n2 = 0;
        T t2 = threadLocal.get();
        if (t2 == null) {
            T t3 = function0.invoke();
            boolean bl = false;
            boolean bl2 = false;
            T t4 = t3;
            boolean bl3 = false;
            threadLocal.set(t4);
            t2 = t3;
        }
        return t2;
    }
}

