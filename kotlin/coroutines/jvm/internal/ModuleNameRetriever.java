/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c2\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2={"Lkotlin/coroutines/jvm/internal/ModuleNameRetriever;", "", "()V", "cache", "Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "notOnJava9", "buildCache", "continuation", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "getModuleName", "", "Cache", "kotlin-stdlib"})
final class ModuleNameRetriever {
    private static final Cache notOnJava9;
    @JvmField
    @Nullable
    public static Cache cache;
    @NotNull
    public static final ModuleNameRetriever INSTANCE;

    @Nullable
    public final String getModuleName(@NotNull BaseContinuationImpl baseContinuationImpl) {
        Cache cache;
        Intrinsics.checkNotNullParameter(baseContinuationImpl, "continuation");
        Cache cache2 = ModuleNameRetriever.cache;
        if (cache2 == null) {
            cache2 = cache = this.buildCache(baseContinuationImpl);
        }
        if (cache == notOnJava9) {
            return null;
        }
        Object object = cache.getModuleMethod;
        if (object == null || (object = ((Method)object).invoke(baseContinuationImpl.getClass(), new Object[0])) == null) {
            return null;
        }
        Object object2 = object;
        Object object3 = cache.getDescriptorMethod;
        if (object3 == null || (object3 = ((Method)object3).invoke(object2, new Object[0])) == null) {
            return null;
        }
        Object object4 = object3;
        Method method = cache.nameMethod;
        Object object5 = method != null ? method.invoke(object4, new Object[0]) : null;
        if (!(object5 instanceof String)) {
            object5 = null;
        }
        return (String)object5;
    }

    private final Cache buildCache(BaseContinuationImpl baseContinuationImpl) {
        try {
            Method method = Class.class.getDeclaredMethod("getModule", new Class[0]);
            Class<?> class_ = baseContinuationImpl.getClass().getClassLoader().loadClass("java.lang.Module");
            Method method2 = class_.getDeclaredMethod("getDescriptor", new Class[0]);
            Class<?> class_2 = baseContinuationImpl.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor");
            Method method3 = class_2.getDeclaredMethod("name", new Class[0]);
            Cache cache = new Cache(method, method2, method3);
            boolean bl = false;
            boolean bl2 = false;
            Cache cache2 = cache;
            boolean bl3 = false;
            ModuleNameRetriever.cache = cache2;
            return cache;
        }
        catch (Exception exception) {
            Cache cache = notOnJava9;
            boolean bl = false;
            boolean bl4 = false;
            Cache cache3 = cache;
            boolean bl5 = false;
            ModuleNameRetriever.cache = cache3;
            return cache;
        }
    }

    private ModuleNameRetriever() {
    }

    static {
        ModuleNameRetriever moduleNameRetriever;
        INSTANCE = moduleNameRetriever = new ModuleNameRetriever();
        notOnJava9 = new Cache(null, null, null);
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0006R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2={"Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "", "getModuleMethod", "Ljava/lang/reflect/Method;", "getDescriptorMethod", "nameMethod", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "kotlin-stdlib"})
    private static final class Cache {
        @JvmField
        @Nullable
        public final Method getModuleMethod;
        @JvmField
        @Nullable
        public final Method getDescriptorMethod;
        @JvmField
        @Nullable
        public final Method nameMethod;

        public Cache(@Nullable Method method, @Nullable Method method2, @Nullable Method method3) {
            this.getModuleMethod = method;
            this.getDescriptorMethod = method2;
            this.nameMethod = method3;
        }
    }
}

