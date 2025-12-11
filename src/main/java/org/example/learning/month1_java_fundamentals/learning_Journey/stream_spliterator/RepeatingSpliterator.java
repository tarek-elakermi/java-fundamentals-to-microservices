package org.example.learning.month1_java_fundamentals.learning_Journey.stream_spliterator;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class RepeatingSpliterator<T> implements Spliterator<T> {

    private final int repeat;
    private Spliterator<T> spliterator;

    public RepeatingSpliterator(Spliterator<T> spliterator, int repeat) {
        this.spliterator = spliterator;
        this.repeat = repeat;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        boolean hasMore = this.spliterator.tryAdvance(
                t -> {
                    IntStream.range(0, repeat).forEach(index -> action.accept(t));
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

