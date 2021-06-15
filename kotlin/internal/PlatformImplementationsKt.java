/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.internal;

import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementations;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0001\u001a\"\u0010\b\u001a\u0002H\t\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0083\b\u00a2\u0006\u0002\u0010\f\u001a\b\u0010\r\u001a\u00020\u0005H\u0002\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2={"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "apiVersionIsAtLeast", "", "major", "", "minor", "patch", "castToBaseType", "T", "", "instance", "(Ljava/lang/Object;)Ljava/lang/Object;", "getJavaVersion", "kotlin-stdlib"})
public final class PlatformImplementationsKt {
    @JvmField
    @NotNull
    public static final PlatformImplementations IMPLEMENTATIONS;

    @InlineOnly
    private static final /* synthetic */ <T> T castToBaseType(Object object) {
        int n2 = 0;
        try {
            Intrinsics.reifiedOperationMarker(1, "T");
            return (T)object;
        }
        catch (ClassCastException classCastException) {
            ClassLoader classLoader = object.getClass().getClassLoader();
            Intrinsics.reifiedOperationMarker(4, "T");
            ClassLoader classLoader2 = Object.class.getClassLoader();
            Throwable throwable = new ClassCastException("Instance classloader: " + classLoader + ", base type classloader: " + classLoader2).initCause(classCastException);
            Intrinsics.checkNotNullExpressionValue(throwable, "ClassCastException(\"Inst\u2026baseTypeCL\").initCause(e)");
            throw throwable;
        }
    }

    private static final int getJavaVersion() {
        int n2;
        int n3 = 65542;
        String string = System.getProperty("java.specification.version");
        if (string == null) {
            return n3;
        }
        String string2 = string;
        int n4 = StringsKt.indexOf$default((CharSequence)string2, '.', 0, false, 6, null);
        if (n4 < 0) {
            int n5;
            try {
                String string3 = string2;
                boolean bl = false;
                n5 = Integer.parseInt(string3) * 65536;
            }
            catch (NumberFormatException numberFormatException) {
                n5 = n3;
            }
            return n5;
        }
        int n6 = StringsKt.indexOf$default((CharSequence)string2, '.', n4 + 1, false, 4, null);
        if (n6 < 0) {
            n6 = string2.length();
        }
        String string4 = string2;
        int n7 = 0;
        int n8 = 0;
        String string5 = string4;
        if (string5 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string6 = string5.substring(n7, n4);
        Intrinsics.checkNotNullExpressionValue(string6, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        String string7 = string6;
        String string8 = string2;
        n8 = n4 + 1;
        boolean bl = false;
        String string9 = string8;
        if (string9 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string10 = string9.substring(n8, n6);
        Intrinsics.checkNotNullExpressionValue(string10, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        string4 = string10;
        try {
            string8 = string7;
            n8 = 0;
            int n9 = Integer.parseInt(string8) * 65536;
            string8 = string4;
            n8 = 0;
            n2 = n9 + Integer.parseInt(string8);
        }
        catch (NumberFormatException numberFormatException) {
            n2 = n3;
        }
        return n2;
    }

    @PublishedApi
    @SinceKotlin(version="1.2")
    public static final boolean apiVersionIsAtLeast(int n2, int n3, int n4) {
        return KotlinVersion.CURRENT.isAtLeast(n2, n3, n4);
    }

    static {
        PlatformImplementations platformImplementations;
        block22: {
            boolean bl = false;
            boolean bl2 = false;
            boolean bl3 = false;
            int n2 = PlatformImplementationsKt.getJavaVersion();
            if (n2 >= 65544) {
                try {
                    Object obj = Class.forName("kotlin.internal.jdk8.JDK8PlatformImplementations").newInstance();
                    Intrinsics.checkNotNullExpressionValue(obj, "Class.forName(\"kotlin.in\u2026entations\").newInstance()");
                    Object obj2 = obj;
                    boolean bl4 = false;
                    try {
                        Object obj3 = obj2;
                        if (obj3 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
                        }
                        platformImplementations = (PlatformImplementations)obj3;
                        break block22;
                    }
                    catch (ClassCastException classCastException) {
                        ClassLoader classLoader = obj2.getClass().getClassLoader();
                        ClassLoader classLoader2 = PlatformImplementations.class.getClassLoader();
                        Throwable throwable = new ClassCastException("Instance classloader: " + classLoader + ", base type classloader: " + classLoader2).initCause(classCastException);
                        Intrinsics.checkNotNullExpressionValue(throwable, "ClassCastException(\"Inst\u2026baseTypeCL\").initCause(e)");
                        throw throwable;
                    }
                }
                catch (ClassNotFoundException classNotFoundException) {
                    try {
                        Object obj = Class.forName("kotlin.internal.JRE8PlatformImplementations").newInstance();
                        Intrinsics.checkNotNullExpressionValue(obj, "Class.forName(\"kotlin.in\u2026entations\").newInstance()");
                        Object obj4 = obj;
                        boolean bl5 = false;
                        try {
                            Object obj5 = obj4;
                            if (obj5 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
                            }
                            platformImplementations = (PlatformImplementations)obj5;
                            break block22;
                        }
                        catch (ClassCastException classCastException) {
                            ClassLoader classLoader = obj4.getClass().getClassLoader();
                            ClassLoader classLoader3 = PlatformImplementations.class.getClassLoader();
                            Throwable throwable = new ClassCastException("Instance classloader: " + classLoader + ", base type classloader: " + classLoader3).initCause(classCastException);
                            Intrinsics.checkNotNullExpressionValue(throwable, "ClassCastException(\"Inst\u2026baseTypeCL\").initCause(e)");
                            throw throwable;
                        }
                    }
                    catch (ClassNotFoundException classNotFoundException2) {
                        // empty catch block
                    }
                }
            }
            if (n2 >= 65543) {
                try {
                    Object obj = Class.forName("kotlin.internal.jdk7.JDK7PlatformImplementations").newInstance();
                    Intrinsics.checkNotNullExpressionValue(obj, "Class.forName(\"kotlin.in\u2026entations\").newInstance()");
                    Object obj6 = obj;
                    boolean bl6 = false;
                    try {
                        Object obj7 = obj6;
                        if (obj7 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
                        }
                        platformImplementations = (PlatformImplementations)obj7;
                        break block22;
                    }
                    catch (ClassCastException classCastException) {
                        ClassLoader classLoader = obj6.getClass().getClassLoader();
                        ClassLoader classLoader4 = PlatformImplementations.class.getClassLoader();
                        Throwable throwable = new ClassCastException("Instance classloader: " + classLoader + ", base type classloader: " + classLoader4).initCause(classCastException);
                        Intrinsics.checkNotNullExpressionValue(throwable, "ClassCastException(\"Inst\u2026baseTypeCL\").initCause(e)");
                        throw throwable;
                    }
                }
                catch (ClassNotFoundException classNotFoundException) {
                    try {
                        Object obj = Class.forName("kotlin.internal.JRE7PlatformImplementations").newInstance();
                        Intrinsics.checkNotNullExpressionValue(obj, "Class.forName(\"kotlin.in\u2026entations\").newInstance()");
                        Object obj8 = obj;
                        boolean bl7 = false;
                        try {
                            Object obj9 = obj8;
                            if (obj9 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
                            }
                            platformImplementations = (PlatformImplementations)obj9;
                            break block22;
                        }
                        catch (ClassCastException classCastException) {
                            ClassLoader classLoader = obj8.getClass().getClassLoader();
                            ClassLoader classLoader5 = PlatformImplementations.class.getClassLoader();
                            Throwable throwable = new ClassCastException("Instance classloader: " + classLoader + ", base type classloader: " + classLoader5).initCause(classCastException);
                            Intrinsics.checkNotNullExpressionValue(throwable, "ClassCastException(\"Inst\u2026baseTypeCL\").initCause(e)");
                            throw throwable;
                        }
                    }
                    catch (ClassNotFoundException classNotFoundException3) {
                        // empty catch block
                    }
                }
            }
            platformImplementations = new PlatformImplementations();
        }
        IMPLEMENTATIONS = platformImplementations;
    }
}

