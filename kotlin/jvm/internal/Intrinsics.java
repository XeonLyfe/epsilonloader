/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm.internal;

import java.util.Arrays;
import kotlin.KotlinNullPointerException;
import kotlin.SinceKotlin;
import kotlin.UninitializedPropertyAccessException;

public class Intrinsics {
    private Intrinsics() {
    }

    public static String stringPlus(String string, Object object) {
        return string + object;
    }

    public static void checkNotNull(Object object) {
        if (object == null) {
            Intrinsics.throwJavaNpe();
        }
    }

    public static void checkNotNull(Object object, String string) {
        if (object == null) {
            Intrinsics.throwJavaNpe(string);
        }
    }

    public static void throwNpe() {
        throw Intrinsics.sanitizeStackTrace(new KotlinNullPointerException());
    }

    public static void throwNpe(String string) {
        throw Intrinsics.sanitizeStackTrace(new KotlinNullPointerException(string));
    }

    @SinceKotlin(version="1.4")
    public static void throwJavaNpe() {
        throw Intrinsics.sanitizeStackTrace(new NullPointerException());
    }

    @SinceKotlin(version="1.4")
    public static void throwJavaNpe(String string) {
        throw Intrinsics.sanitizeStackTrace(new NullPointerException(string));
    }

    public static void throwUninitializedProperty(String string) {
        throw Intrinsics.sanitizeStackTrace(new UninitializedPropertyAccessException(string));
    }

    public static void throwUninitializedPropertyAccessException(String string) {
        Intrinsics.throwUninitializedProperty("lateinit property " + string + " has not been initialized");
    }

    public static void throwAssert() {
        throw Intrinsics.sanitizeStackTrace(new AssertionError());
    }

    public static void throwAssert(String string) {
        throw Intrinsics.sanitizeStackTrace(new AssertionError((Object)string));
    }

    public static void throwIllegalArgument() {
        throw Intrinsics.sanitizeStackTrace(new IllegalArgumentException());
    }

    public static void throwIllegalArgument(String string) {
        throw Intrinsics.sanitizeStackTrace(new IllegalArgumentException(string));
    }

    public static void throwIllegalState() {
        throw Intrinsics.sanitizeStackTrace(new IllegalStateException());
    }

    public static void throwIllegalState(String string) {
        throw Intrinsics.sanitizeStackTrace(new IllegalStateException(string));
    }

    public static void checkExpressionValueIsNotNull(Object object, String string) {
        if (object == null) {
            throw Intrinsics.sanitizeStackTrace(new IllegalStateException(string + " must not be null"));
        }
    }

    public static void checkNotNullExpressionValue(Object object, String string) {
        if (object == null) {
            throw Intrinsics.sanitizeStackTrace(new NullPointerException(string + " must not be null"));
        }
    }

    public static void checkReturnedValueIsNotNull(Object object, String string, String string2) {
        if (object == null) {
            throw Intrinsics.sanitizeStackTrace(new IllegalStateException("Method specified as non-null returned null: " + string + "." + string2));
        }
    }

    public static void checkReturnedValueIsNotNull(Object object, String string) {
        if (object == null) {
            throw Intrinsics.sanitizeStackTrace(new IllegalStateException(string));
        }
    }

    public static void checkFieldIsNotNull(Object object, String string, String string2) {
        if (object == null) {
            throw Intrinsics.sanitizeStackTrace(new IllegalStateException("Field specified as non-null is null: " + string + "." + string2));
        }
    }

    public static void checkFieldIsNotNull(Object object, String string) {
        if (object == null) {
            throw Intrinsics.sanitizeStackTrace(new IllegalStateException(string));
        }
    }

    public static void checkParameterIsNotNull(Object object, String string) {
        if (object == null) {
            Intrinsics.throwParameterIsNullIAE(string);
        }
    }

    public static void checkNotNullParameter(Object object, String string) {
        if (object == null) {
            Intrinsics.throwParameterIsNullNPE(string);
        }
    }

    private static void throwParameterIsNullIAE(String string) {
        throw Intrinsics.sanitizeStackTrace(new IllegalArgumentException(Intrinsics.createParameterIsNullExceptionMessage(string)));
    }

    private static void throwParameterIsNullNPE(String string) {
        throw Intrinsics.sanitizeStackTrace(new NullPointerException(Intrinsics.createParameterIsNullExceptionMessage(string)));
    }

    private static String createParameterIsNullExceptionMessage(String string) {
        StackTraceElement[] arrstackTraceElement = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = arrstackTraceElement[4];
        String string2 = stackTraceElement.getClassName();
        String string3 = stackTraceElement.getMethodName();
        return "Parameter specified as non-null is null: method " + string2 + "." + string3 + ", parameter " + string;
    }

    public static int compare(long l2, long l3) {
        return l2 < l3 ? -1 : (l2 == l3 ? 0 : 1);
    }

    public static int compare(int n2, int n3) {
        return n2 < n3 ? -1 : (n2 == n3 ? 0 : 1);
    }

    public static boolean areEqual(Object object, Object object2) {
        return object == null ? object2 == null : object.equals(object2);
    }

    @SinceKotlin(version="1.1")
    public static boolean areEqual(Double d2, Double d3) {
        return d2 == null ? d3 == null : d3 != null && d2.doubleValue() == d3.doubleValue();
    }

    @SinceKotlin(version="1.1")
    public static boolean areEqual(Double d2, double d3) {
        return d2 != null && d2 == d3;
    }

    @SinceKotlin(version="1.1")
    public static boolean areEqual(double d2, Double d3) {
        return d3 != null && d2 == d3;
    }

    @SinceKotlin(version="1.1")
    public static boolean areEqual(Float f2, Float f3) {
        return f2 == null ? f3 == null : f3 != null && f2.floatValue() == f3.floatValue();
    }

    @SinceKotlin(version="1.1")
    public static boolean areEqual(Float f2, float f3) {
        return f2 != null && f2.floatValue() == f3;
    }

    @SinceKotlin(version="1.1")
    public static boolean areEqual(float f2, Float f3) {
        return f3 != null && f2 == f3.floatValue();
    }

    public static void throwUndefinedForReified() {
        Intrinsics.throwUndefinedForReified("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static void throwUndefinedForReified(String string) {
        throw new UnsupportedOperationException(string);
    }

    public static void reifiedOperationMarker(int n2, String string) {
        Intrinsics.throwUndefinedForReified();
    }

    public static void reifiedOperationMarker(int n2, String string, String string2) {
        Intrinsics.throwUndefinedForReified(string2);
    }

    public static void needClassReification() {
        Intrinsics.throwUndefinedForReified();
    }

    public static void needClassReification(String string) {
        Intrinsics.throwUndefinedForReified(string);
    }

    public static void checkHasClass(String string) throws ClassNotFoundException {
        String string2 = string.replace('/', '.');
        try {
            Class.forName(string2);
        }
        catch (ClassNotFoundException classNotFoundException) {
            throw Intrinsics.sanitizeStackTrace(new ClassNotFoundException("Class " + string2 + " is not found. Please update the Kotlin runtime to the latest version", classNotFoundException));
        }
    }

    public static void checkHasClass(String string, String string2) throws ClassNotFoundException {
        String string3 = string.replace('/', '.');
        try {
            Class.forName(string3);
        }
        catch (ClassNotFoundException classNotFoundException) {
            throw Intrinsics.sanitizeStackTrace(new ClassNotFoundException("Class " + string3 + " is not found: this code requires the Kotlin runtime of version at least " + string2, classNotFoundException));
        }
    }

    private static <T extends Throwable> T sanitizeStackTrace(T t2) {
        return Intrinsics.sanitizeStackTrace(t2, Intrinsics.class.getName());
    }

    static <T extends Throwable> T sanitizeStackTrace(T t2, String string) {
        StackTraceElement[] arrstackTraceElement = t2.getStackTrace();
        int n2 = arrstackTraceElement.length;
        int n3 = -1;
        for (int i2 = 0; i2 < n2; ++i2) {
            if (!string.equals(arrstackTraceElement[i2].getClassName())) continue;
            n3 = i2;
        }
        StackTraceElement[] arrstackTraceElement2 = Arrays.copyOfRange(arrstackTraceElement, n3 + 1, n2);
        t2.setStackTrace(arrstackTraceElement2);
        return t2;
    }

    @SinceKotlin(version="1.4")
    public static class Kotlin {
        private Kotlin() {
        }
    }
}

