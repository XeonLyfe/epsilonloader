/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.jvm.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 O2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001OB\u0011\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u00a2\u0006\u0002\u0010\u0006J\u0013\u0010F\u001a\u00020\u00122\b\u0010G\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010H\u001a\u00020IH\u0002J\b\u0010J\u001a\u00020KH\u0016J\u0012\u0010L\u001a\u00020\u00122\b\u0010M\u001a\u0004\u0018\u00010\u0002H\u0017J\b\u0010N\u001a\u000201H\u0016R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u000e0\r8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0018\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u0018\u0010\u0015R\u001a\u0010\u001a\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u001a\u0010\u0015R\u001a\u0010\u001c\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001c\u0010\u0015R\u001a\u0010\u001e\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u001f\u0010\u0014\u001a\u0004\b\u001e\u0010\u0015R\u001a\u0010 \u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b!\u0010\u0014\u001a\u0004\b \u0010\u0015R\u001a\u0010\"\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b#\u0010\u0014\u001a\u0004\b\"\u0010\u0015R\u001a\u0010$\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b%\u0010\u0014\u001a\u0004\b$\u0010\u0015R\u0018\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001e\u0010(\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030)0\r8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b*\u0010\u0010R\u001e\u0010+\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\r8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010\u0010R\u0016\u0010-\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b.\u0010/R\u0016\u00100\u001a\u0004\u0018\u0001018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b2\u00103R(\u00104\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00010\b8VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b5\u0010\u0014\u001a\u0004\b6\u0010\u000bR\u0016\u00107\u001a\u0004\u0018\u0001018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b8\u00103R \u00109\u001a\b\u0012\u0004\u0012\u00020:0\b8VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b;\u0010\u0014\u001a\u0004\b<\u0010\u000bR \u0010=\u001a\b\u0012\u0004\u0012\u00020>0\b8VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b?\u0010\u0014\u001a\u0004\b@\u0010\u000bR\u001c\u0010A\u001a\u0004\u0018\u00010B8VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\bC\u0010\u0014\u001a\u0004\bD\u0010E\u00a8\u0006P"}, d2={"Lkotlin/jvm/internal/ClassReference;", "Lkotlin/reflect/KClass;", "", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "jClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "constructors", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "isAbstract", "", "isAbstract$annotations", "()V", "()Z", "isCompanion", "isCompanion$annotations", "isData", "isData$annotations", "isFinal", "isFinal$annotations", "isFun", "isFun$annotations", "isInner", "isInner$annotations", "isOpen", "isOpen$annotations", "isSealed", "isSealed$annotations", "isValue", "isValue$annotations", "getJClass", "()Ljava/lang/Class;", "members", "Lkotlin/reflect/KCallable;", "getMembers", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "qualifiedName", "", "getQualifiedName", "()Ljava/lang/String;", "sealedSubclasses", "getSealedSubclasses$annotations", "getSealedSubclasses", "simpleName", "getSimpleName", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes$annotations", "getSupertypes", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters$annotations", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "equals", "other", "error", "", "hashCode", "", "isInstance", "value", "toString", "Companion", "kotlin-stdlib"})
public final class ClassReference
implements KClass<Object>,
ClassBasedDeclarationContainer {
    @NotNull
    private final Class<?> jClass;
    private static final Map<Class<? extends Function<?>>, Integer> FUNCTION_CLASSES;
    private static final HashMap<String, String> primitiveFqNames;
    private static final HashMap<String, String> primitiveWrapperFqNames;
    private static final HashMap<String, String> classFqNames;
    private static final Map<String, String> simpleNames;
    @NotNull
    public static final Companion Companion;

    @Override
    @Nullable
    public String getSimpleName() {
        return Companion.getClassSimpleName(this.getJClass());
    }

    @Override
    @Nullable
    public String getQualifiedName() {
        return Companion.getClassQualifiedName(this.getJClass());
    }

    @Override
    @NotNull
    public Collection<KCallable<?>> getMembers() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @Override
    @NotNull
    public Collection<KFunction<Object>> getConstructors() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @Override
    @NotNull
    public Collection<KClass<?>> getNestedClasses() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @Override
    @NotNull
    public List<Annotation> getAnnotations() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @Override
    @Nullable
    public Object getObjectInstance() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isInstance(@Nullable Object object) {
        return Companion.isInstance(object, this.getJClass());
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void getTypeParameters$annotations() {
    }

    @Override
    @NotNull
    public List<KTypeParameter> getTypeParameters() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void getSupertypes$annotations() {
    }

    @Override
    @NotNull
    public List<KType> getSupertypes() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.3")
    public static /* synthetic */ void getSealedSubclasses$annotations() {
    }

    @Override
    @NotNull
    public List<KClass<? extends Object>> getSealedSubclasses() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void getVisibility$annotations() {
    }

    @Override
    @Nullable
    public KVisibility getVisibility() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isFinal$annotations() {
    }

    @Override
    public boolean isFinal() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isOpen$annotations() {
    }

    @Override
    public boolean isOpen() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isAbstract$annotations() {
    }

    @Override
    public boolean isAbstract() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isSealed$annotations() {
    }

    @Override
    public boolean isSealed() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isData$annotations() {
    }

    @Override
    public boolean isData() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isInner$annotations() {
    }

    @Override
    public boolean isInner() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isCompanion$annotations() {
    }

    @Override
    public boolean isCompanion() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.4")
    public static /* synthetic */ void isFun$annotations() {
    }

    @Override
    public boolean isFun() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.5")
    public static /* synthetic */ void isValue$annotations() {
    }

    @Override
    public boolean isValue() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    private final Void error() {
        throw (Throwable)new KotlinReflectionNotSupportedError();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return object instanceof ClassReference && Intrinsics.areEqual(JvmClassMappingKt.getJavaObjectType(this), JvmClassMappingKt.getJavaObjectType((KClass)object));
    }

    @Override
    public int hashCode() {
        return JvmClassMappingKt.getJavaObjectType(this).hashCode();
    }

    @NotNull
    public String toString() {
        return this.getJClass().toString() + " (Kotlin reflection is not available)";
    }

    @Override
    @NotNull
    public Class<?> getJClass() {
        return this.jClass;
    }

    public ClassReference(@NotNull Class<?> class_) {
        Intrinsics.checkNotNullParameter(class_, "jClass");
        this.jClass = class_;
    }

    static {
        Pair<Class<Object>, Integer> pair;
        Object object;
        int n2;
        Pair<String, String> pair2;
        Object object22;
        Companion = new Companion(null);
        Map map = CollectionsKt.listOf(Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class);
        boolean bl = false;
        Iterable iterable = map;
        Map<String, String> map2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(map, 10));
        boolean bl2 = false;
        int n3 = 0;
        for (Object object22 : iterable) {
            int n4 = n3++;
            boolean bl3 = false;
            if (n4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            pair2 = (Class)object22;
            n2 = n4;
            object = map2;
            boolean bl4 = false;
            pair = TuplesKt.to(pair2, n2);
            object.add(pair);
        }
        FUNCTION_CLASSES = MapsKt.toMap((List)((Object)map2));
        map = new HashMap();
        bl = false;
        boolean bl5 = false;
        map2 = map;
        bl2 = false;
        ((HashMap)map2).put("boolean", "kotlin.Boolean");
        ((HashMap)map2).put("char", "kotlin.Char");
        ((HashMap)map2).put("byte", "kotlin.Byte");
        ((HashMap)map2).put("short", "kotlin.Short");
        ((HashMap)map2).put("int", "kotlin.Int");
        ((HashMap)map2).put("float", "kotlin.Float");
        ((HashMap)map2).put("long", "kotlin.Long");
        ((HashMap)map2).put("double", "kotlin.Double");
        primitiveFqNames = map;
        map = new HashMap<String, String>();
        bl = false;
        bl5 = false;
        map2 = map;
        bl2 = false;
        ((HashMap)map2).put("java.lang.Boolean", "kotlin.Boolean");
        ((HashMap)map2).put("java.lang.Character", "kotlin.Char");
        ((HashMap)map2).put("java.lang.Byte", "kotlin.Byte");
        ((HashMap)map2).put("java.lang.Short", "kotlin.Short");
        ((HashMap)map2).put("java.lang.Integer", "kotlin.Int");
        ((HashMap)map2).put("java.lang.Float", "kotlin.Float");
        ((HashMap)map2).put("java.lang.Long", "kotlin.Long");
        ((HashMap)map2).put("java.lang.Double", "kotlin.Double");
        primitiveWrapperFqNames = map;
        map = new HashMap();
        bl = false;
        bl5 = false;
        map2 = map;
        bl2 = false;
        ((HashMap)map2).put("java.lang.Object", "kotlin.Any");
        ((HashMap)map2).put("java.lang.String", "kotlin.String");
        ((HashMap)map2).put("java.lang.CharSequence", "kotlin.CharSequence");
        ((HashMap)map2).put("java.lang.Throwable", "kotlin.Throwable");
        ((HashMap)map2).put("java.lang.Cloneable", "kotlin.Cloneable");
        ((HashMap)map2).put("java.lang.Number", "kotlin.Number");
        ((HashMap)map2).put("java.lang.Comparable", "kotlin.Comparable");
        ((HashMap)map2).put("java.lang.Enum", "kotlin.Enum");
        ((HashMap)map2).put("java.lang.annotation.Annotation", "kotlin.Annotation");
        ((HashMap)map2).put("java.lang.Iterable", "kotlin.collections.Iterable");
        ((HashMap)map2).put("java.util.Iterator", "kotlin.collections.Iterator");
        ((HashMap)map2).put("java.util.Collection", "kotlin.collections.Collection");
        ((HashMap)map2).put("java.util.List", "kotlin.collections.List");
        ((HashMap)map2).put("java.util.Set", "kotlin.collections.Set");
        ((HashMap)map2).put("java.util.ListIterator", "kotlin.collections.ListIterator");
        ((HashMap)map2).put("java.util.Map", "kotlin.collections.Map");
        ((HashMap)map2).put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        ((HashMap)map2).put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        ((HashMap)map2).put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        ((HashMap)map2).putAll((Map)primitiveFqNames);
        ((HashMap)map2).putAll((Map)primitiveWrapperFqNames);
        Collection<String> collection = primitiveFqNames.values();
        Intrinsics.checkNotNullExpressionValue(collection, "primitiveFqNames.values");
        Object object3 = collection;
        boolean bl6 = false;
        object22 = object3.iterator();
        while (object22.hasNext()) {
            Object e2 = object22.next();
            Map map3 = map2;
            pair2 = (String)e2;
            n2 = 0;
            StringBuilder stringBuilder = new StringBuilder().append("kotlin.jvm.internal.");
            Pair<String, String> pair3 = pair2;
            Intrinsics.checkNotNullExpressionValue(pair3, "kotlinName");
            pair2 = TuplesKt.to(stringBuilder.append(StringsKt.substringAfterLast$default((String)((Object)pair3), '.', null, 2, null)).append("CompanionObject").toString(), (String)((Object)pair2) + ".Companion");
            n2 = 0;
            map3.put(pair2.getFirst(), pair2.getSecond());
        }
        Map cfr_ignored_0 = map2;
        object22 = FUNCTION_CLASSES;
        int n5 = 0;
        Iterator iterator2 = object22.entrySet().iterator();
        while (iterator2.hasNext()) {
            Object object4 = object3 = iterator2.next();
            boolean bl7 = false;
            object22 = (Class)object4.getKey();
            object4 = object3;
            bl7 = false;
            n5 = ((Number)object4.getValue()).intValue();
            ((HashMap)map2).put(((Class)object22).getName(), "kotlin.Function" + n5);
        }
        classFqNames = map;
        map = classFqNames;
        bl = false;
        Map map4 = map;
        map2 = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        bl2 = false;
        object3 = map4.entrySet();
        boolean bl8 = false;
        object22 = object3.iterator();
        while (object22.hasNext()) {
            Object e3 = object22.next();
            Map.Entry entry = (Map.Entry)e3;
            Map<String, String> map5 = map2;
            n2 = 0;
            Object k2 = entry.getKey();
            Map.Entry entry2 = (Map.Entry)e3;
            pair = k2;
            object = map5;
            boolean bl9 = false;
            Map.Entry entry3 = entry2;
            boolean bl10 = false;
            String string = (String)entry3.getValue();
            String string2 = StringsKt.substringAfterLast$default(string, '.', null, 2, null);
            object.put(pair, string2);
        }
        simpleNames = map2;
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\n2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\n2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005J\u001c\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00012\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005R&\u0010\u0003\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2={"Lkotlin/jvm/internal/ClassReference$Companion;", "", "()V", "FUNCTION_CLASSES", "", "Ljava/lang/Class;", "Lkotlin/Function;", "", "classFqNames", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "primitiveFqNames", "primitiveWrapperFqNames", "simpleNames", "getClassQualifiedName", "jClass", "getClassSimpleName", "isInstance", "", "value", "kotlin-stdlib"})
    public static final class Companion {
        @Nullable
        public final String getClassSimpleName(@NotNull Class<?> class_) {
            Object object;
            block14: {
                block15: {
                    Object object2;
                    String string;
                    block17: {
                        boolean bl;
                        Method method;
                        boolean bl2;
                        boolean bl3;
                        Executable executable;
                        block16: {
                            block13: {
                                Intrinsics.checkNotNullParameter(class_, "jClass");
                                if (!class_.isAnonymousClass()) break block13;
                                object = null;
                                break block14;
                            }
                            if (!class_.isLocalClass()) break block15;
                            string = class_.getSimpleName();
                            object2 = class_.getEnclosingMethod();
                            if (object2 == null) break block16;
                            executable = object2;
                            bl3 = false;
                            bl2 = false;
                            method = executable;
                            bl = false;
                            String string2 = string;
                            Intrinsics.checkNotNullExpressionValue(string2, "name");
                            object2 = StringsKt.substringAfter$default(string2, method.getName() + "$", null, 2, null);
                            if (object2 != null) break block17;
                        }
                        Constructor<?> constructor = class_.getEnclosingConstructor();
                        if (constructor != null) {
                            executable = constructor;
                            bl3 = false;
                            bl2 = false;
                            method = executable;
                            bl = false;
                            String string3 = string;
                            Intrinsics.checkNotNullExpressionValue(string3, "name");
                            object2 = StringsKt.substringAfter$default(string3, ((Constructor)((Object)method)).getName() + "$", null, 2, null);
                        } else {
                            object2 = object = null;
                        }
                    }
                    if (object2 == null) {
                        String string4 = string;
                        Intrinsics.checkNotNullExpressionValue(string4, "name");
                        object = StringsKt.substringAfter$default(string4, '$', null, 2, null);
                    }
                    break block14;
                }
                if (class_.isArray()) {
                    Object object3;
                    Class<?> class_2 = class_.getComponentType();
                    Class<?> class_3 = class_2;
                    Intrinsics.checkNotNullExpressionValue(class_3, "componentType");
                    if (class_3.isPrimitive()) {
                        String string = (String)simpleNames.get(class_2.getName());
                        object3 = string != null ? string + "Array" : null;
                    } else {
                        object3 = object = null;
                    }
                    if (object3 == null) {
                        object = "Array";
                    }
                } else {
                    object = (String)simpleNames.get(class_.getName());
                    if (object == null) {
                        object = class_.getSimpleName();
                    }
                }
            }
            return object;
        }

        @Nullable
        public final String getClassQualifiedName(@NotNull Class<?> class_) {
            String string;
            Intrinsics.checkNotNullParameter(class_, "jClass");
            if (class_.isAnonymousClass()) {
                string = null;
            } else if (class_.isLocalClass()) {
                string = null;
            } else if (class_.isArray()) {
                String string2;
                Class<?> class_2 = class_.getComponentType();
                Class<?> class_3 = class_2;
                Intrinsics.checkNotNullExpressionValue(class_3, "componentType");
                if (class_3.isPrimitive()) {
                    String string3 = (String)classFqNames.get(class_2.getName());
                    string2 = string3 != null ? string3 + "Array" : null;
                } else {
                    string2 = string = null;
                }
                if (string2 == null) {
                    string = "kotlin.Array";
                }
            } else {
                string = (String)classFqNames.get(class_.getName());
                if (string == null) {
                    string = class_.getCanonicalName();
                }
            }
            return string;
        }

        public final boolean isInstance(@Nullable Object object, @NotNull Class<?> class_) {
            Intrinsics.checkNotNullParameter(class_, "jClass");
            Class<?> class_2 = FUNCTION_CLASSES;
            boolean bl = false;
            Map map = class_2;
            if (map == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
            }
            Integer n2 = (Integer)map.get(class_);
            if (n2 != null) {
                class_2 = n2;
                bl = false;
                boolean bl2 = false;
                int n3 = ((Number)((Object)class_2)).intValue();
                boolean bl3 = false;
                return TypeIntrinsics.isFunctionOfArity(object, n3);
            }
            class_2 = class_.isPrimitive() ? JvmClassMappingKt.getJavaObjectType(JvmClassMappingKt.getKotlinClass(class_)) : class_;
            return class_2.isInstance(object);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

