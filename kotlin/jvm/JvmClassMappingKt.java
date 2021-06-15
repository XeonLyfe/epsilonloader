/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.jvm;

import java.lang.annotation.Annotation;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u001f\u0010\u0018\u001a\u00020\u0019\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\r*\u0006\u0012\u0002\b\u00030\u001a\u00a2\u0006\u0002\u0010\u001b\"'\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"-\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00018G\u00a2\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"&\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\u0002H\u00028\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000e\";\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018\u00c7\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u000f\u0010\t\u001a\u0004\b\u0010\u0010\u000b\"+\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u000b\"-\u0010\u0013\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u000b\"+\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00078G\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u001c"}, d2={"annotationClass", "Lkotlin/reflect/KClass;", "T", "", "getAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "java", "Ljava/lang/Class;", "getJavaClass$annotations", "(Lkotlin/reflect/KClass;)V", "getJavaClass", "(Lkotlin/reflect/KClass;)Ljava/lang/Class;", "javaClass", "", "(Ljava/lang/Object;)Ljava/lang/Class;", "getRuntimeClassOfKClassInstance$annotations", "getRuntimeClassOfKClassInstance", "javaObjectType", "getJavaObjectType", "javaPrimitiveType", "getJavaPrimitiveType", "kotlin", "getKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/KClass;", "isArrayOf", "", "", "([Ljava/lang/Object;)Z", "kotlin-stdlib"})
@JvmName(name="JvmClassMappingKt")
public final class JvmClassMappingKt {
    public static /* synthetic */ void getJavaClass$annotations(KClass kClass) {
    }

    @JvmName(name="getJavaClass")
    @NotNull
    public static final <T> Class<T> getJavaClass(@NotNull KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "$this$java");
        Class<?> class_ = ((ClassBasedDeclarationContainer)((Object)kClass)).getJClass();
        if (class_ == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        return class_;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Nullable
    public static final <T> Class<T> getJavaPrimitiveType(@NotNull KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "$this$javaPrimitiveType");
        Class<?> class_ = ((ClassBasedDeclarationContainer)((Object)kClass)).getJClass();
        if (class_.isPrimitive()) {
            Class<?> class_2 = class_;
            if (class_2 != null) return class_2;
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        String string = class_.getName();
        if (string == null) return null;
        switch (string) {
            case "java.lang.Boolean": {
                Class<Object> class_3 = Boolean.TYPE;
                return class_3;
            }
            case "java.lang.Character": {
                Class<Object> class_3 = Character.TYPE;
                return class_3;
            }
            case "java.lang.Byte": {
                Class<Object> class_3 = Byte.TYPE;
                return class_3;
            }
            case "java.lang.Short": {
                Class<Object> class_3 = Short.TYPE;
                return class_3;
            }
            case "java.lang.Integer": {
                Class<Object> class_3 = Integer.TYPE;
                return class_3;
            }
            case "java.lang.Float": {
                Class<Object> class_3 = Float.TYPE;
                return class_3;
            }
            case "java.lang.Long": {
                Class<Object> class_3 = Long.TYPE;
                return class_3;
            }
            case "java.lang.Double": {
                Class<Object> class_3 = Double.TYPE;
                return class_3;
            }
            case "java.lang.Void": {
                Class<Object> class_3 = Void.TYPE;
                return class_3;
            }
            default: {
                return null;
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @NotNull
    public static final <T> Class<T> getJavaObjectType(@NotNull KClass<T> var0) {
        Intrinsics.checkNotNullParameter(var0, "$this$javaObjectType");
        var1_1 = ((ClassBasedDeclarationContainer)var0).getJClass();
        if (!var1_1.isPrimitive()) {
            v0 = var1_1;
            if (v0 != null) return v0;
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        v1 = var1_1.getName();
        if (v1 == null) ** GOTO lbl-1000
        var2_2 = v1;
        tmp = -1;
        switch (var2_2.hashCode()) {
            case 64711720: {
                if (var2_2.equals("boolean")) {
                    tmp = 1;
                }
                break;
            }
            case 3625364: {
                if (var2_2.equals("void")) {
                    tmp = 2;
                }
                break;
            }
            case 3039496: {
                if (var2_2.equals("byte")) {
                    tmp = 3;
                }
                break;
            }
            case -1325958191: {
                if (var2_2.equals("double")) {
                    tmp = 4;
                }
                break;
            }
            case 3052374: {
                if (var2_2.equals("char")) {
                    tmp = 5;
                }
                break;
            }
            case 109413500: {
                if (var2_2.equals("short")) {
                    tmp = 6;
                }
                break;
            }
            case 97526364: {
                if (var2_2.equals("float")) {
                    tmp = 7;
                }
                break;
            }
            case 104431: {
                if (var2_2.equals("int")) {
                    tmp = 8;
                }
                break;
            }
            case 3327612: {
                if (var2_2.equals("long")) {
                    tmp = 9;
                }
                break;
            }
            default: {
            }
        }
        switch (tmp) {
            case 1: {
                v2 /* !! */  = Boolean.class;
                break;
            }
            case 5: {
                v2 /* !! */  = Character.class;
                break;
            }
            case 3: {
                v2 /* !! */  = Byte.class;
                break;
            }
            case 6: {
                v2 /* !! */  = Short.class;
                break;
            }
            case 8: {
                v2 /* !! */  = Integer.class;
                break;
            }
            case 7: {
                v2 /* !! */  = Float.class;
                break;
            }
            case 9: {
                v2 /* !! */  = Long.class;
                break;
            }
            case 4: {
                v2 /* !! */  = Double.class;
                break;
            }
            case 2: {
                v2 /* !! */  = Void.class;
                break;
            }
            default: lbl-1000:
            // 2 sources

            {
                v2 /* !! */  = var1_1;
            }
        }
        if (v2 /* !! */  != null) return v2 /* !! */ ;
        throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
    }

    @JvmName(name="getKotlinClass")
    @NotNull
    public static final <T> KClass<T> getKotlinClass(@NotNull Class<T> class_) {
        Intrinsics.checkNotNullParameter(class_, "$this$kotlin");
        return Reflection.getOrCreateKotlinClass(class_);
    }

    @NotNull
    public static final <T> Class<T> getJavaClass(@NotNull T t2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(t2, "$this$javaClass");
        Class<?> class_ = t2.getClass();
        if (class_ == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        return class_;
    }

    @Deprecated(message="Use 'java' property to get Java class corresponding to this Kotlin class or cast this instance to Any if you really want to get the runtime Java class of this implementation of KClass.", replaceWith=@ReplaceWith(imports={}, expression="(this as Any).javaClass"), level=DeprecationLevel.ERROR)
    public static /* synthetic */ void getRuntimeClassOfKClassInstance$annotations(KClass kClass) {
    }

    @JvmName(name="getRuntimeClassOfKClassInstance")
    @NotNull
    public static final <T> Class<KClass<T>> getRuntimeClassOfKClassInstance(@NotNull KClass<T> kClass) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(kClass, "$this$javaClass");
        Class<KClass<T>> class_ = ((Object)kClass).getClass();
        if (class_ == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<kotlin.reflect.KClass<T>>");
        }
        return class_;
    }

    public static final /* synthetic */ <T> boolean isArrayOf(Object[] arrobject) {
        Intrinsics.checkNotNullParameter(arrobject, "$this$isArrayOf");
        Intrinsics.reifiedOperationMarker(4, "T");
        return Object.class.isAssignableFrom(arrobject.getClass().getComponentType());
    }

    @NotNull
    public static final <T extends Annotation> KClass<? extends T> getAnnotationClass(@NotNull T t2) {
        Intrinsics.checkNotNullParameter(t2, "$this$annotationClass");
        Class<? extends Annotation> class_ = t2.annotationType();
        Intrinsics.checkNotNullExpressionValue(class_, "(this as java.lang.annot\u2026otation).annotationType()");
        KClass<? extends Annotation> kClass = JvmClassMappingKt.getKotlinClass(class_);
        if (kClass == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.KClass<out T>");
        }
        return kClass;
    }
}

