/*
 * Decompiled with CFR 0.150.
 */
package kotlin.jvm;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;

@kotlin.annotation.Target(allowedTargets={AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER})
@kotlin.annotation.Retention(value=AnnotationRetention.RUNTIME)
@MustBeDocumented
@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000\u00a8\u0006\u0002"}, d2={"Lkotlin/jvm/JvmStatic;", "", "kotlin-stdlib"})
public @interface JvmStatic {
}

