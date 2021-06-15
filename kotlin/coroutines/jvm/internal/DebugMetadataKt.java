/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.ModuleNameRetriever;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\bH\u0002\u001a\f\u0010\t\u001a\u00020\u0001*\u00020\bH\u0002\u001a\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b*\u00020\bH\u0001\u00a2\u0006\u0002\u0010\r\u001a\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\bH\u0001\u00a2\u0006\u0002\b\u0010\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2={"COROUTINES_DEBUG_METADATA_VERSION", "", "checkDebugMetadataVersion", "", "expected", "actual", "getDebugMetadataAnnotation", "Lkotlin/coroutines/jvm/internal/DebugMetadata;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "getLabel", "getSpilledVariableFieldMapping", "", "", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)[Ljava/lang/String;", "getStackTraceElementImpl", "Ljava/lang/StackTraceElement;", "getStackTraceElement", "kotlin-stdlib"})
public final class DebugMetadataKt {
    private static final int COROUTINES_DEBUG_METADATA_VERSION = 1;

    @SinceKotlin(version="1.3")
    @JvmName(name="getStackTraceElement")
    @Nullable
    public static final StackTraceElement getStackTraceElement(@NotNull BaseContinuationImpl baseContinuationImpl) {
        Intrinsics.checkNotNullParameter(baseContinuationImpl, "$this$getStackTraceElementImpl");
        DebugMetadata debugMetadata = DebugMetadataKt.getDebugMetadataAnnotation(baseContinuationImpl);
        if (debugMetadata == null) {
            return null;
        }
        DebugMetadata debugMetadata2 = debugMetadata;
        DebugMetadataKt.checkDebugMetadataVersion(1, debugMetadata2.v());
        int n2 = DebugMetadataKt.getLabel(baseContinuationImpl);
        int n3 = n2 < 0 ? -1 : debugMetadata2.l()[n2];
        String string = ModuleNameRetriever.INSTANCE.getModuleName(baseContinuationImpl);
        String string2 = string == null ? debugMetadata2.c() : string + '/' + debugMetadata2.c();
        return new StackTraceElement(string2, debugMetadata2.m(), debugMetadata2.f(), n3);
    }

    private static final DebugMetadata getDebugMetadataAnnotation(BaseContinuationImpl baseContinuationImpl) {
        return baseContinuationImpl.getClass().getAnnotation(DebugMetadata.class);
    }

    private static final int getLabel(BaseContinuationImpl baseContinuationImpl) {
        int n2;
        try {
            Field field;
            Field field2 = field = baseContinuationImpl.getClass().getDeclaredField("label");
            Intrinsics.checkNotNullExpressionValue(field2, "field");
            field2.setAccessible(true);
            Object object = field.get(baseContinuationImpl);
            if (!(object instanceof Integer)) {
                object = null;
            }
            Integer n3 = (Integer)object;
            n2 = (n3 != null ? n3 : 0) - 1;
        }
        catch (Exception exception) {
            n2 = -1;
        }
        return n2;
    }

    private static final void checkDebugMetadataVersion(int n2, int n3) {
        if (n3 > n2) {
            String string = "Debug metadata version mismatch. Expected: " + n2 + ", got " + n3 + ". Please update the Kotlin standard library.";
            boolean bl = false;
            throw (Throwable)new IllegalStateException(string.toString());
        }
    }

    @SinceKotlin(version="1.3")
    @JvmName(name="getSpilledVariableFieldMapping")
    @Nullable
    public static final String[] getSpilledVariableFieldMapping(@NotNull BaseContinuationImpl baseContinuationImpl) {
        int n2;
        Intrinsics.checkNotNullParameter(baseContinuationImpl, "$this$getSpilledVariableFieldMapping");
        DebugMetadata debugMetadata = DebugMetadataKt.getDebugMetadataAnnotation(baseContinuationImpl);
        if (debugMetadata == null) {
            return null;
        }
        DebugMetadata debugMetadata2 = debugMetadata;
        DebugMetadataKt.checkDebugMetadataVersion(1, debugMetadata2.v());
        int n3 = 0;
        ArrayList<String> arrayList = new ArrayList<String>();
        n3 = DebugMetadataKt.getLabel(baseContinuationImpl);
        Object object = debugMetadata2.i();
        int n4 = ((int[])object).length;
        for (int i2 = 0; i2 < n4; ++i2) {
            n2 = object[i2];
            if (n2 != n3) continue;
            arrayList.add(debugMetadata2.s()[i2]);
            arrayList.add(debugMetadata2.n()[i2]);
        }
        Collection collection = arrayList;
        n2 = 0;
        object = collection;
        String[] arrstring = object.toArray(new String[0]);
        if (arrstring == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return arrstring;
    }
}

