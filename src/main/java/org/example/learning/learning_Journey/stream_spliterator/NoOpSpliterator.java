package org.example.learning.learning_Journey.stream_spliterator;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class NoOpSpliterator<T> implements Spliterator<T> {


    private Spliterator<T> spliterator;

    public NoOpSpliterator(Spliterator<T> spliterator) {
        this.spliterator = spliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        return this.spliterator.tryAdvance(
                t -> {
                    action.accept(t);
                }
        );
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
