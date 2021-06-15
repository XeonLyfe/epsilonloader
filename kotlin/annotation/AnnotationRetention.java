/*
 * Decompiled with CFR 0.150.
 */
package kotlin.annotation;

import kotlin.Metadata;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lkotlin/annotation/AnnotationRetention;", "", "(Ljava/lang/String;I)V", "SOURCE", "BINARY", "RUNTIME", "kotlin-stdlib"})
public final class AnnotationRetention
extends Enum<AnnotationRetention> {
    public static final /* enum */ AnnotationRetention SOURCE;
    public static final /* enum */ AnnotationRetention BINARY;
    public static final /* enum */ AnnotationRetention RUNTIME;
    private static final /* synthetic */ AnnotationRetention[] $VALUES;

    static {
        AnnotationRetention[] arrannotationRetention = new AnnotationRetention[3];
        AnnotationRetention[] arrannotationRetention2 = arrannotationRetention;
        arrannotationRetention[0] = SOURCE = new AnnotationRetention();
        arrannotationRetention[1] = BINARY = new AnnotationRetention();
        arrannotationRetention[2] = RUNTIME = new AnnotationRetention();
        $VALUES = arrannotationRetention;
    }

    public static AnnotationRetention[] values() {
        return (AnnotationRetention[])$VALUES.clone();
    }

    public static AnnotationRetention valueOf(String string) {
        return Enum.valueOf(AnnotationRetention.class, string);
    }
}

