package org.example.learning.learning_Journey.stream_spliterator;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LiveDemo {

    public static void main(String[] args) {

        // Spliterator Test
        Random random = new Random(100);

        int[] array = IntStream.rangeClosed(1,1_000_000)
                .map(random::nextInt)
                .map(i -> (i * i) + i)
                .skip(20)
                .toArray();
        //System.out.println(Arrays.toString(array));


        System.out.println("************");
        Integer maxPar = StreamSupport
                .stream(new FindMaxSpliterator(array, 0, array.length - 1), true)
                .reduce(0, Integer::max, Integer::max);
        System.out.println(maxPar);









        /**********************/
        List<String> strings =
                Arrays.asList("one", "two", "three", "four", "five", "six",
                        "seven", "eight", "nine", "ten", "eleven", "twelve");


        Stream<String> stream = strings.stream();
        stream.onClose(() -> System.out.println("Closing"));




        //TreeSet<String> treeSet = new TreeSet<>(strings);

//        Spliterator<String> spliterator = stream.spliterator();
//
//        NoOpSpliterator<String> noOpSpliterator = new NoOpSpliterator<>(spliterator);
//
//        FilteringSpliterator<String> filteringSpliterator =
//                new FilteringSpliterator<>(noOpSpliterator, s -> s.length() == 3 );
//
//        MappingSpliterator<String> mappingSpliterator
//                = new MappingSpliterator<>(filteringSpliterator, String::toUpperCase);
//
//        RepeatingSpliterator<String> repeatingSpliterator
//                = new RepeatingSpliterator<>(mappingSpliterator, 2);
//
//        Stream<String> stream2 = StreamSupport.stream(repeatingSpliterator, false);
//        stream2.onClose(stream::close);
//
//        stream2.forEach(System.out::println);
//        stream2.close();




    }
}
