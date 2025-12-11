package org.example.learning.learning_Journey.introduction_toStreams.reactiveStreamsJava.streamTest;

import java.util.stream.Stream;

public class StreamTest {
    
    public static void main(String[] args) {

//        Stream.of("fun", "learning", "tarek", "express")
//                        .forEach(System.out::println);

        Stream.of(1,2,3,4,5,6,7,8,9)
                    .peek(no -> System.out.print("\nA" + no))
                    .skip(5)
                    .peek(no -> System.out.print("B" + no))
                    .forEach(no -> System.out.print("C" + no));


    }
}
