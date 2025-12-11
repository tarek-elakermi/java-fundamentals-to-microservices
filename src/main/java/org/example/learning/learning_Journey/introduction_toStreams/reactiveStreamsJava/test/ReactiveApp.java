package org.example.learning.learning_Journey.introduction_toStreams.reactiveStreamsJava.test;

import java.io.IOException;
import java.util.Scanner;

public class ReactiveApp {

    public static void main(String[] args) throws IOException {

        // normal way
//        StreamUtils.worldStream()
//                .map(String::toUpperCase)
//                .forEach(System.out::println);

        // reactive form (import reactor core from maven)
        ReactiveStreamUtils.worldReactiveStream()
                .subscribe(word -> System.out.println(word));

        Scanner sc = new Scanner(System.in);
        System.out.println("press any key to exit the program4");
        sc.nextLine();



    }
}
