/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveSpreadBuilder;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0002J\f\u0010\f\u001a\u00020\u0004*\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"Lkotlin/jvm/internal/DoubleSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", "value", "", "toArray", "getSize", "kotlin-stdlib"})
public final class DoubleSpreadBuilder
extends PrimitiveSpreadBuilder<double[]> {
    private final double[] values;

    @Override
    protected int getSize(@NotNull double[] arrd) {
        Intrinsics.checkNotNullParameter(arrd, "$this$getSize");
        return arrd.length;
    }

    public final void add(double d2) {
        DoubleSpreadBuilder doubleSpreadBuilder = this;
        int n2 = doubleSpreadBuilder.getPosition();
        doubleSpreadBuilder.setPosition(n2 + 1);
        this.values[n2] = d2;
    }

    @NotNull
    public final double[] toArray() {
        return this.toArray(this.values, new double[this.size()]);
    }

    public DoubleSpreadBuilder(int n2) {
        super(n2);
        this.values = new double[n2];
    }
}

