package org.example.learning.learning_Journey.stream_spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class MappingSpliterator<T> implements Spliterator<T> {

    private final UnaryOperator<T> mapper;
    private Spliterator<T> spliterator;

    public MappingSpliterator(Spliterator<T> spliterator, UnaryOperator<T> mapper) {
        this.spliterator = spliterator;
        this.mapper = mapper;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        boolean hasMore = this.spliterator.tryAdvance(
                t -> {
                        action.accept(mapper.apply(t));
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
        return this.spliterator.characteristics() & ~Spliterator.SORTED;
    }


}
