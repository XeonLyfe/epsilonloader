/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2={"Lkotlin/sequences/DropWhileSequence;", "T", "Lkotlin/sequences/Sequence;", "sequence", "predicate", "Lkotlin/Function1;", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "iterator", "", "kotlin-stdlib"})
public final class DropWhileSequence<T>
implements Sequence<T> {
    private final Sequence<T> sequence;
    private final Function1<T, Boolean> predicate;

    @Override
    @NotNull
    public Iterator<T> iterator() {
        return new Iterator<T>(this){
            @NotNull
            private final Iterator<T> iterator;
            private int dropState;
            @Nullable
            private T nextItem;
            final /* synthetic */ DropWhileSequence this$0;

            @NotNull
            public final Iterator<T> getIterator() {
                return this.iterator;
            }

            public final int getDropState() {
                return this.dropState;
            }

            public final void setDropState(int n2) {
                this.dropState = n2;
            }

            @Nullable
            public final T getNextItem() {
                return this.nextItem;
            }

            public final void setNextItem(@Nullable T t2) {
                this.nextItem = t2;
            }

            private final void drop() {
                while (this.iterator.hasNext()) {
                    T t2 = this.iterator.next();
                    if (((Boolean)DropWhileSequence.access$getPredicate$p(this.this$0).invoke(t2)).booleanValue()) continue;
                    this.nextItem = t2;
                    this.dropState = 1;
                    return;
                }
                this.dropState = 0;
            }

            public T next() {
                if (this.dropState == -1) {
                    this.drop();
                }
                if (this.dropState == 1) {
                    T t2 = this.nextItem;
                    this.nextItem = null;
                    this.dropState = 0;
                    return t2;
                }
                return this.iterator.next();
            }

            public boolean hasNext() {
                if (this.dropState == -1) {
                    this.drop();
                }
                return this.dropState == 1 || this.iterator.hasNext();
            }
            {
                this.this$0 = dropWhileSequence;
                this.iterator = DropWhileSequence.access$getSequence$p(dropWhileSequence).iterator();
                this.dropState = -1;
            }

            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }

    public DropWhileSequence(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        this.sequence = sequence;
        this.predicate = function1;
    }

    public static final /* synthetic */ Function1 access$getPredicate$p(DropWhileSequence dropWhileSequence) {
        return dropWhileSequence.predicate;
    }

    public static final /* synthetic */ Sequence access$getSequence$p(DropWhileSequence dropWhileSequence) {
        return dropWhileSequence.sequence;
    }
}

