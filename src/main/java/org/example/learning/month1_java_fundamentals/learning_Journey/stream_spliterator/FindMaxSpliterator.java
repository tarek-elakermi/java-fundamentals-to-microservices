package org.example.learning.month1_java_fundamentals.learning_Journey.stream_spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class FindMaxSpliterator implements Spliterator<Integer> {
    int start, end;
    int[] arr;

    public FindMaxSpliterator(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;

    }

    @Override
    public boolean tryAdvance(Consumer<? super Integer> action) {
        if(start <= end){
            action.accept(arr[start]);
            start++;
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<Integer> trySplit() {
        if(end - start < 1000){
            return null;
        }
        int mid = (start + end)  / 2 ;
        int oldStart = start;
        start = mid + 1 ;
        return new FindMaxSpliterator(arr, oldStart, mid);
    }

    @Override
    public long estimateSize() {
        return end - start;
    }

    @Override
    public int characteristics() {
        return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
    }
}
