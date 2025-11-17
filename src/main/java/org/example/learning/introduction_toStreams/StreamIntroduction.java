package org.example.learning.introduction_toStreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamIntroduction {

    private static final String MALE = "MALE";

    public static void reduceThreeArgs(List<String> words) {
        int result = words.parallelStream().reduce(0, (p, str) -> {
            System.out.println("BiFunc: " + p + "  " + str);
            return p + str.length();
        }, (i, j) -> {
            System.out.println("BiOpr: " + i + "  " + j);
            return i + j;
        });
    }

    public static void main(String[] args) throws IOException {

//        List<String> words =
//                Files.lines(Paths.get("src\\main\\resources\\flatmap.txt"))     // Stream<String>
//                        .map(line -> line.split(" "))             // Stream<String[]>
//                        //.map(Arrays::stream)                      // Stream<Stream<String>>
//                        .flatMap(Arrays::stream)
//                        .distinct()
//                        .collect(Collectors.toList());
//
//        System.out.println(words);

        // Find the number of characters in a string.

        // Generating fibonacci numbers of a given length
//        List<Integer> list = Stream.iterate(new int[]{0, 1}, a -> {
//            int next = a[0] + a[1];
//            a[0] = a[1];
//            a[1] = next;
//            return a;
//        }).limit(10).map(a -> a[0]).toList();
//        System.out.println(list);

        Stream.generate(UUID::randomUUID).limit(5).forEach(System.out::println);


    }
}
