/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.EmptyIterator;
import kotlin.collections.RingBuffer;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001aH\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u001aD\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u000e\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u00a8\u0006\u000f"}, d2={"checkWindowSizeStep", "", "size", "", "step", "windowedIterator", "", "", "T", "iterator", "partialWindows", "", "reuseBuffer", "windowedSequence", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"})
public final class SlidingWindowKt {
    public static final void checkWindowSizeStep(int n2, int n3) {
        boolean bl = n2 > 0 && n3 > 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = n2 != n3 ? "Both size " + n2 + " and step " + n3 + " must be greater than zero." : "size " + n2 + " must be greater than zero.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
    }

    @NotNull
    public static final <T> Sequence<List<T>> windowedSequence(@NotNull Sequence<? extends T> sequence, int n2, int n3, boolean bl, boolean bl2) {
        Intrinsics.checkNotNullParameter(sequence, "$this$windowedSequence");
        SlidingWindowKt.checkWindowSizeStep(n2, n3);
        boolean bl3 = false;
        return new Sequence<List<? extends T>>(sequence, n2, n3, bl, bl2){
            final /* synthetic */ Sequence $this_windowedSequence$inlined;
            final /* synthetic */ int $size$inlined;
            final /* synthetic */ int $step$inlined;
            final /* synthetic */ boolean $partialWindows$inlined;
            final /* synthetic */ boolean $reuseBuffer$inlined;
            {
                this.$this_windowedSequence$inlined = sequence;
                this.$size$inlined = n2;
                this.$step$inlined = n3;
                this.$partialWindows$inlined = bl;
                this.$reuseBuffer$inlined = bl2;
            }

            @NotNull
            public Iterator<List<? extends T>> iterator() {
                boolean bl = false;
                return SlidingWindowKt.windowedIterator(this.$this_windowedSequence$inlined.iterator(), this.$size$inlined, this.$step$inlined, this.$partialWindows$inlined, this.$reuseBuffer$inlined);
            }
        };
    }

    @NotNull
    public static final <T> Iterator<List<T>> windowedIterator(@NotNull Iterator<? extends T> iterator2, int n2, int n3, boolean bl, boolean bl2) {
        Intrinsics.checkNotNullParameter(iterator2, "iterator");
        if (!iterator2.hasNext()) {
            return EmptyIterator.INSTANCE;
        }
        return SequencesKt.iterator(new Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object>(n2, n3, iterator2, bl2, bl, null){
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int I$0;
            int label;
            final /* synthetic */ int $size;
            final /* synthetic */ int $step;
            final /* synthetic */ Iterator $iterator;
            final /* synthetic */ boolean $reuseBuffer;
            final /* synthetic */ boolean $partialWindows;

            /*
             * Unable to fully structure code
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Nullable
            public final Object invokeSuspend(@NotNull Object var1_1) {
                var11_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(var1_1);
                        var2_3 = (SequenceScope)this.L$0;
                        var3_4 = RangesKt.coerceAtMost(this.$size, 1024);
                        var4_5 = this.$step - this.$size;
                        if (var4_5 < 0) break;
                        var5_6 = new ArrayList<Object>(var3_4);
                        var6_8 = 0;
                        var9_10 = this.$iterator;
                        var10_12 = false;
                        var8_13 = var9_10;
lbl14:
                        // 4 sources

                        while (var8_13.hasNext()) {
                            var7_16 = var8_13.next();
                            if (var6_8 > 0) {
                                --var6_8;
                                continue;
                            }
                            var5_6.add(var7_16);
                            if (var5_6.size() != this.$size) continue;
                            this.L$0 = var2_3;
                            this.L$1 = var5_6;
                            this.L$2 = var8_13;
                            this.I$0 = var4_5;
                            this.label = 1;
                            v0 = var2_3.yield(var5_6, this);
                            if (v0 == var11_2) {
                                return var11_2;
                            }
                            ** GOTO lbl39
                        }
                        break;
                    }
                    case 1: {
                        var4_5 = this.I$0;
                        var8_13 = (Iterator)this.L$2;
                        var5_6 = (ArrayList<Object>)this.L$1;
                        var2_3 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(var1_1);
                        v0 = var1_1;
lbl39:
                        // 2 sources

                        if (this.$reuseBuffer) {
                            var5_6.clear();
                        } else {
                            var5_6 = new ArrayList<E>(this.$size);
                        }
                        var6_8 = var4_5;
                        ** GOTO lbl14
                    }
                }
                var7_16 = var5_6;
                var8_14 = false;
                if (!(var7_16.isEmpty() == false) || !this.$partialWindows && var5_6.size() != this.$size) return Unit.INSTANCE;
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = null;
                this.label = 2;
                v1 = var2_3.yield(var5_6, this);
                if (v1 != var11_2) return Unit.INSTANCE;
                return var11_2;
                {
                    case 2: {
                        ResultKt.throwOnFailure(var1_1);
                        v1 = var1_1;
                        return Unit.INSTANCE;
                    }
                }
                var5_7 = new RingBuffer<Collection<E>>(var3_4);
                var8_15 = this.$iterator;
                var9_11 = false;
                var7_17 = var8_15;
lbl63:
                // 4 sources

                while (var7_17.hasNext()) {
                    var6_9 = var7_17.next();
                    var5_7.add(var6_9);
                    if (!var5_7.isFull()) continue;
                    if (var5_7.size() < this.$size) {
                        var5_7 = var5_7.expanded(this.$size);
                        continue;
                    }
                    this.L$0 = var2_3;
                    this.L$1 = var5_7;
                    this.L$2 = var7_17;
                    this.label = 3;
                    v2 = var2_3.yield(this.$reuseBuffer != false ? (List)var5_7 : (List)new ArrayList<E>((Collection)var5_7), this);
                    if (v2 == var11_2) {
                        return var11_2;
                    }
                    ** GOTO lbl85
                }
                {
                    break;
                    case 3: {
                        var7_17 = (Iterator)this.L$2;
                        var5_7 = (RingBuffer)this.L$1;
                        var2_3 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(var1_1);
                        v2 = var1_1;
lbl85:
                        // 2 sources

                        var5_7.removeFirst(this.$step);
                        ** GOTO lbl63
                    }
                }
                if (!this.$partialWindows) return Unit.INSTANCE;
lbl88:
                // 2 sources

                while (var5_7.size() > this.$step) {
                    this.L$0 = var2_3;
                    this.L$1 = var5_7;
                    this.L$2 = null;
                    this.label = 4;
                    v3 = var2_3.yield(this.$reuseBuffer != false ? (List)var5_7 : (List)new ArrayList<E>((Collection)var5_7), this);
                    if (v3 == var11_2) {
                        return var11_2;
                    }
                    ** GOTO lbl103
                }
                {
                    break;
                    case 4: {
                        var5_7 = (RingBuffer<Collection<E>>)this.L$1;
                        var2_3 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(var1_1);
                        v3 = var1_1;
lbl103:
                        // 2 sources

                        var5_7.removeFirst(this.$step);
                        ** GOTO lbl88
                    }
                }
                var6_9 = var5_7;
                var7_18 = false;
                if (!(var6_9.isEmpty() == false)) return Unit.INSTANCE;
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = null;
                this.label = 5;
                v4 = var2_3.yield(var5_7, this);
                if (v4 != var11_2) return Unit.INSTANCE;
                return var11_2;
                {
                    case 5: {
                        ResultKt.throwOnFailure(var1_1);
                        v4 = var1_1;
                        return Unit.INSTANCE;
                    }
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            {
                this.$size = n2;
                this.$step = n3;
                this.$iterator = iterator2;
                this.$reuseBuffer = bl;
                this.$partialWindows = bl2;
                super(2, continuation);
            }

            @NotNull
            public final Continuation<Unit> create(@Nullable Object object, @NotNull Continuation<?> continuation) {
                Intrinsics.checkNotNullParameter(continuation, "completion");
                Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> function2 = new /* invalid duplicate definition of identical inner class */;
                Object object2 = function2.L$0 = object;
                return function2;
            }

            public final Object invoke(Object object, Object object2) {
                return (this.create(object, (Continuation)object2)).invokeSuspend(Unit.INSTANCE);
            }
        });
    }
}

