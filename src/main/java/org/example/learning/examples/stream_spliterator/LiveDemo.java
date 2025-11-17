package org.example.learning.examples.stream_spliterator;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LiveDemo {

    public static void main(String[] args) {
        List<String> strings =
                Arrays.asList("one", "two", "three", "four", "five", "six",
                        "seven", "eight", "nine", "ten", "eleven", "twelve");


        Stream<String> stream = strings.stream();
        stream.onClose(() -> System.out.println("Closing"));




        //TreeSet<String> treeSet = new TreeSet<>(strings);

        Spliterator<String> spliterator = stream.spliterator();

        NoOpSpliterator<String> noOpSpliterator = new NoOpSpliterator<>(spliterator);

        FilteringSpliterator<String> filteringSpliterator =
                new FilteringSpliterator<>(noOpSpliterator, s -> s.length() == 3 );

        MappingSpliterator<String> mappingSpliterator
                = new MappingSpliterator<>(filteringSpliterator, String::toUpperCase);

        RepeatingSpliterator<String> repeatingSpliterator
                = new RepeatingSpliterator<>(mappingSpliterator, 2);

        Stream<String> stream2 = StreamSupport.stream(repeatingSpliterator, false);
        stream2.onClose(stream::close);

        stream2.forEach(System.out::println);
        stream2.close();

    }
}
