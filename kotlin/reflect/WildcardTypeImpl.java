/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.TypeImpl;
import kotlin.reflect.TypesJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u0000 \u00142\u00020\u00012\u00020\u0002:\u0001\u0014B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\u0013\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\fH\u0016\u00a2\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\fH\u0016\u00a2\u0006\u0002\u0010\rJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2={"Lkotlin/reflect/WildcardTypeImpl;", "Ljava/lang/reflect/WildcardType;", "Lkotlin/reflect/TypeImpl;", "upperBound", "Ljava/lang/reflect/Type;", "lowerBound", "(Ljava/lang/reflect/Type;Ljava/lang/reflect/Type;)V", "equals", "", "other", "", "getLowerBounds", "", "()[Ljava/lang/reflect/Type;", "getTypeName", "", "getUpperBounds", "hashCode", "", "toString", "Companion", "kotlin-stdlib"})
@ExperimentalStdlibApi
final class WildcardTypeImpl
implements WildcardType,
TypeImpl {
    private final Type upperBound;
    private final Type lowerBound;
    @NotNull
    private static final WildcardTypeImpl STAR;
    @NotNull
    public static final Companion Companion;

    @Override
    @NotNull
    public Type[] getUpperBounds() {
        Type[] arrtype = new Type[1];
        Type type = this.upperBound;
        if (type == null) {
            type = (Type)((Object)Object.class);
        }
        arrtype[0] = type;
        return arrtype;
    }

    @Override
    @NotNull
    public Type[] getLowerBounds() {
        Type[] arrtype;
        if (this.lowerBound == null) {
            arrtype = new Type[]{};
        } else {
            Type[] arrtype2 = new Type[1];
            arrtype = arrtype2;
            arrtype2[0] = this.lowerBound;
        }
        return arrtype;
    }

    @Override
    @NotNull
    public String getTypeName() {
        return this.lowerBound != null ? "? super " + TypesJVMKt.access$typeToString(this.lowerBound) : (this.upperBound != null && Intrinsics.areEqual(this.upperBound, Object.class) ^ true ? "? extends " + TypesJVMKt.access$typeToString(this.upperBound) : "?");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean equals(@Nullable Object object) {
        if (!(object instanceof WildcardType)) return false;
        Object[] arrobject = this.getUpperBounds();
        Object[] arrobject2 = ((WildcardType)object).getUpperBounds();
        boolean bl = false;
        if (!Arrays.equals(arrobject, arrobject2)) return false;
        arrobject = this.getLowerBounds();
        arrobject2 = ((WildcardType)object).getLowerBounds();
        bl = false;
        if (!Arrays.equals(arrobject, arrobject2)) return false;
        return true;
    }

    public int hashCode() {
        Object[] arrobject = this.getUpperBounds();
        boolean bl = false;
        int n2 = Arrays.hashCode(arrobject);
        arrobject = this.getLowerBounds();
        bl = false;
        return n2 ^ Arrays.hashCode(arrobject);
    }

    @NotNull
    public String toString() {
        return this.getTypeName();
    }

    public WildcardTypeImpl(@Nullable Type type, @Nullable Type type2) {
        this.upperBound = type;
        this.lowerBound = type2;
    }

    static {
        Companion = new Companion(null);
        STAR = new WildcardTypeImpl(null, null);
    }

    @Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lkotlin/reflect/WildcardTypeImpl$Companion;", "", "()V", "STAR", "Lkotlin/reflect/WildcardTypeImpl;", "getSTAR", "()Lkotlin/reflect/WildcardTypeImpl;", "kotlin-stdlib"})
    public static final class Companion {
        @NotNull
        public final WildcardTypeImpl getSTAR() {
            return STAR;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

