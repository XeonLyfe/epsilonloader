/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.reflect;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KTypeBase;
import kotlin.reflect.GenericArrayTypeImpl;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import kotlin.reflect.ParameterizedTypeImpl;
import kotlin.reflect.TypeVariableImpl;
import kotlin.reflect.TypesJVMKt;
import kotlin.reflect.TypesJVMKt$WhenMappings;
import kotlin.reflect.WildcardTypeImpl;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a\"\u0010\n\u001a\u00020\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0003\u001a\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001H\u0002\u001a\u0016\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0003\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00078BX\u0083\u0004\u00a2\u0006\f\u0012\u0004\b\u0003\u0010\b\u001a\u0004\b\u0005\u0010\t\u00a8\u0006\u0015"}, d2={"javaType", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "getJavaType$annotations", "(Lkotlin/reflect/KType;)V", "getJavaType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "Lkotlin/reflect/KTypeProjection;", "(Lkotlin/reflect/KTypeProjection;)V", "(Lkotlin/reflect/KTypeProjection;)Ljava/lang/reflect/Type;", "createPossiblyInnerType", "jClass", "Ljava/lang/Class;", "arguments", "", "typeToString", "", "type", "computeJavaType", "forceWrapper", "", "kotlin-stdlib"})
public final class TypesJVMKt {
    @SinceKotlin(version="1.4")
    @ExperimentalStdlibApi
    @LowPriorityInOverloadResolution
    public static /* synthetic */ void getJavaType$annotations(KType kType) {
    }

    @NotNull
    public static final Type getJavaType(@NotNull KType kType) {
        Intrinsics.checkNotNullParameter(kType, "$this$javaType");
        if (kType instanceof KTypeBase) {
            Type type = ((KTypeBase)kType).getJavaType();
            if (type != null) {
                Type type2 = type;
                boolean bl = false;
                boolean bl2 = false;
                Type type3 = type2;
                boolean bl3 = false;
                return type3;
            }
        }
        return TypesJVMKt.computeJavaType$default(kType, false, 1, null);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @ExperimentalStdlibApi
    private static final Type computeJavaType(KType var0, boolean var1_1) {
        var2_2 = var0.getClassifier();
        if (var2_2 instanceof KTypeParameter) {
            return new TypeVariableImpl((KTypeParameter)var2_2);
        }
        if (var2_2 instanceof KClass == false) throw (Throwable)new UnsupportedOperationException("Unsupported type classifier: " + var0);
        var3_3 = var1_1 != false ? JvmClassMappingKt.getJavaObjectType((KClass)var2_2) : JvmClassMappingKt.getJavaClass((KClass)var2_2);
        var4_4 = var0.getArguments();
        if (var4_4.isEmpty()) {
            return var3_3;
        }
        if (var3_3.isArray() == false) return TypesJVMKt.createPossiblyInnerType(var3_3, var4_4);
        v0 = var3_3.getComponentType();
        Intrinsics.checkNotNullExpressionValue(v0, "jClass.componentType");
        if (v0.isPrimitive()) {
            return var3_3;
        }
        v1 = CollectionsKt.singleOrNull(var4_4);
        if (v1 == null) throw (Throwable)new IllegalArgumentException("kotlin.Array must have exactly one type argument: " + var0);
        var7_5 = v1;
        var5_6 = var7_5.component1();
        var6_7 = var7_5.component2();
        v2 = var5_6;
        if (v2 == null) ** GOTO lbl-1000
        switch (TypesJVMKt$WhenMappings.$EnumSwitchMapping$0[v2.ordinal()]) {
            case 1: lbl-1000:
            // 2 sources

            {
                v3 = var3_3;
                return v3;
            }
            case 2: 
            case 3: {
                v4 = var6_7;
                Intrinsics.checkNotNull(v4);
                var7_5 = TypesJVMKt.computeJavaType$default(v4, false, 1, null);
                if (var7_5 instanceof Class) {
                    v3 = var3_3;
                    return v3;
                }
                v3 = new GenericArrayTypeImpl((Type)var7_5);
                return v3;
            }
        }
        throw new NoWhenBranchMatchedException();
    }

    static /* synthetic */ Type computeJavaType$default(KType kType, boolean bl, int n2, Object object) {
        if ((n2 & 1) != 0) {
            bl = false;
        }
        return TypesJVMKt.computeJavaType(kType, bl);
    }

    @ExperimentalStdlibApi
    private static final Type createPossiblyInnerType(Class<?> class_, List<KTypeProjection> list) {
        Collection<Type> collection;
        Class<?> class_2 = class_.getDeclaringClass();
        if (class_2 == null) {
            Collection<Type> collection2;
            Iterable iterable = list;
            Type type = null;
            Class<?> class_3 = class_;
            boolean bl = false;
            Iterable iterable2 = iterable;
            Collection collection3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            boolean bl2 = false;
            Iterator iterator2 = iterable2.iterator();
            while (iterator2.hasNext()) {
                Object t2;
                Object t3 = t2 = iterator2.next();
                collection2 = collection3;
                boolean bl3 = false;
                Type type2 = TypesJVMKt.getJavaType((KTypeProjection)t3);
                collection2.add(type2);
            }
            Collection<Type> collection4 = collection2 = (List)collection3;
            Type type3 = type;
            Class<?> class_4 = class_3;
            return new ParameterizedTypeImpl(class_4, type3, (List<? extends Type>)collection4);
        }
        Class<?> class_5 = class_2;
        if (Modifier.isStatic(class_.getModifiers())) {
            Collection<Type> collection5;
            Iterable iterable = list;
            Type type = class_5;
            Class<?> class_6 = class_;
            boolean bl = false;
            Iterable iterable3 = iterable;
            Collection collection6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            boolean bl4 = false;
            Iterator iterator3 = iterable3.iterator();
            while (iterator3.hasNext()) {
                Object t4;
                Object t5 = t4 = iterator3.next();
                collection5 = collection6;
                boolean bl5 = false;
                Type type4 = TypesJVMKt.getJavaType((KTypeProjection)t5);
                collection5.add(type4);
            }
            Collection<Type> collection7 = collection5 = (List)collection6;
            Type type5 = type;
            Class<?> class_7 = class_6;
            return new ParameterizedTypeImpl(class_7, type5, (List<? extends Type>)collection7);
        }
        int n2 = class_.getTypeParameters().length;
        Iterable iterable = list.subList(0, n2);
        Type type = TypesJVMKt.createPossiblyInnerType(class_5, list.subList(n2, list.size()));
        Class<?> class_8 = class_;
        boolean bl = false;
        Iterable iterable4 = iterable;
        Collection collection8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        boolean bl6 = false;
        Iterator iterator4 = iterable4.iterator();
        while (iterator4.hasNext()) {
            Object t6;
            Object t7 = t6 = iterator4.next();
            collection = collection8;
            boolean bl7 = false;
            Type type6 = TypesJVMKt.getJavaType((KTypeProjection)t7);
            collection.add(type6);
        }
        Collection<Type> collection9 = collection = (List)collection8;
        Type type7 = type;
        Class<?> class_9 = class_8;
        return new ParameterizedTypeImpl(class_9, type7, (List<? extends Type>)collection9);
    }

    @ExperimentalStdlibApi
    private static /* synthetic */ void getJavaType$annotations(KTypeProjection kTypeProjection) {
    }

    private static final Type getJavaType(KTypeProjection kTypeProjection) {
        Type type;
        KVariance kVariance = kTypeProjection.getVariance();
        if (kVariance == null) {
            return WildcardTypeImpl.Companion.getSTAR();
        }
        KVariance kVariance2 = kVariance;
        KType kType = kTypeProjection.getType();
        Intrinsics.checkNotNull(kType);
        KType kType2 = kType;
        switch (TypesJVMKt$WhenMappings.$EnumSwitchMapping$1[kVariance2.ordinal()]) {
            case 1: {
                type = TypesJVMKt.computeJavaType(kType2, true);
                break;
            }
            case 2: {
                type = new WildcardTypeImpl(null, TypesJVMKt.computeJavaType(kType2, true));
                break;
            }
            case 3: {
                type = new WildcardTypeImpl(TypesJVMKt.computeJavaType(kType2, true), null);
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
        return type;
    }

    private static final String typeToString(Type type) {
        String string;
        if (type instanceof Class) {
            String string2;
            if (((Class)type).isArray()) {
                Sequence<Type> sequence = SequencesKt.generateSequence(type, (Function1)typeToString.unwrap.1.INSTANCE);
                string2 = ((Class)SequencesKt.last(sequence)).getName() + StringsKt.repeat("[]", SequencesKt.count(sequence));
            } else {
                string2 = ((Class)type).getName();
            }
            string = string2;
            Intrinsics.checkNotNullExpressionValue(string2, "if (type.isArray) {\n    \u2026\n        } else type.name");
        } else {
            string = type.toString();
        }
        return string;
    }

    public static final /* synthetic */ Type access$computeJavaType(KType kType, boolean bl) {
        return TypesJVMKt.computeJavaType(kType, bl);
    }

    public static final /* synthetic */ String access$typeToString(Type type) {
        return TypesJVMKt.typeToString(type);
    }
}

