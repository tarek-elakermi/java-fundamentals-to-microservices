package org.example.learning.learning_Journey.stream_spliterator;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilteringSpliterator<T> implements Spliterator<T> {

    private final Predicate<T> filter;
    private Spliterator<T> spliterator;

    public FilteringSpliterator(Spliterator<T> spliterator, Predicate<T> filter) {
        this.spliterator = spliterator;
        this.filter = filter;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        boolean hasMore = this.spliterator.tryAdvance(
                t -> {
                    if(filter.test(t))
                        action.accept(t);
                }
        );
        return hasMore;
    }

    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return this.spliterator.estimateSize();
    }

    @Override
    public int characteristics() {
        return this.spliterator.characteristics();
    }

    @Override
    public Comparator<? super T> getComparator() {
        return this.spliterator.getComparator();
    }
}
