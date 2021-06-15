/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B1\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\u0002\u0010\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0096\u0002R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2={"Lkotlin/sequences/FilteringSequence;", "T", "Lkotlin/sequences/Sequence;", "sequence", "sendWhen", "", "predicate", "Lkotlin/Function1;", "(Lkotlin/sequences/Sequence;ZLkotlin/jvm/functions/Function1;)V", "iterator", "", "kotlin-stdlib"})
public final class FilteringSequence<T>
implements Sequence<T> {
    private final Sequence<T> sequence;
    private final boolean sendWhen;
    private final Function1<T, Boolean> predicate;

    @Override
    @NotNull
    public Iterator<T> iterator() {
        return new Iterator<T>(this){
            @NotNull
            private final Iterator<T> iterator;
            private int nextState;
            @Nullable
            private T nextItem;
            final /* synthetic */ FilteringSequence this$0;

            @NotNull
            public final Iterator<T> getIterator() {
                return this.iterator;
            }

            public final int getNextState() {
                return this.nextState;
            }

            public final void setNextState(int n2) {
                this.nextState = n2;
            }

            @Nullable
            public final T getNextItem() {
                return this.nextItem;
            }

            public final void setNextItem(@Nullable T t2) {
                this.nextItem = t2;
            }

            private final void calcNext() {
                while (this.iterator.hasNext()) {
                    T t2 = this.iterator.next();
                    if ((Boolean)FilteringSequence.access$getPredicate$p(this.this$0).invoke(t2) != FilteringSequence.access$getSendWhen$p(this.this$0)) continue;
                    this.nextItem = t2;
                    this.nextState = 1;
                    return;
                }
                this.nextState = 0;
            }

            public T next() {
                if (this.nextState == -1) {
                    this.calcNext();
                }
                if (this.nextState == 0) {
                    throw (Throwable)new NoSuchElementException();
                }
                T t2 = this.nextItem;
                this.nextItem = null;
                this.nextState = -1;
                return t2;
            }

            public boolean hasNext() {
                if (this.nextState == -1) {
                    this.calcNext();
                }
                return this.nextState == 1;
            }
            {
                this.this$0 = filteringSequence;
                this.iterator = FilteringSequence.access$getSequence$p(filteringSequence).iterator();
                this.nextState = -1;
            }

            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }

    public FilteringSequence(@NotNull Sequence<? extends T> sequence, boolean bl, @NotNull Function1<? super T, Boolean> function1) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        this.sequence = sequence;
        this.sendWhen = bl;
        this.predicate = function1;
    }

    public /* synthetic */ FilteringSequence(Sequence sequence, boolean bl, Function1 function1, int n2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n2 & 2) != 0) {
            bl = true;
        }
        this(sequence, bl, function1);
    }

    public static final /* synthetic */ boolean access$getSendWhen$p(FilteringSequence filteringSequence) {
        return filteringSequence.sendWhen;
    }

    public static final /* synthetic */ Function1 access$getPredicate$p(FilteringSequence filteringSequence) {
        return filteringSequence.predicate;
    }

    public static final /* synthetic */ Sequence access$getSequence$p(FilteringSequence filteringSequence) {
        return filteringSequence.sequence;
    }
}

