package org.example.learning.mini_exercise.examples;

import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Car;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertThat;


public class StreamFiltering {


    // 1 - find cars list with price smaller than 20k and with color Yellow
    @Test
    public void filter() throws IOException {
        List<Car> cars = MockData.getCars();
        cars.stream()
                .filter(car -> car.getPrice() < 20_000)
                .filter(car -> car.getColor().equalsIgnoreCase("yellow"))
                .forEachOrdered(System.out::println);
    }


    // 1 - Test dropWhile: skip elements from the stream while the condition is true,
    //     then return the rest. Example goal: drop numbers while they are < 5.
    @Test
    public void dropWhile(){
//        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12).filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        System.out.println("using dropWhile");
        Stream.of(2, 4, 6, 8, 9, 10, 12).dropWhile(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
    }

    // 2 - Test takeWhile: take elements from the stream while the condition is true,
    //     then stop. Example goal: take numbers while they are < 5.
    @Test
    public void takeWhile(){
        // using filter
        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12).filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();
        System.out.println("using take while");
        Stream.of(2, 4, 6, 8, 9, 10, 12).takeWhile(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
    }


    // 3 - Test findFirst: find the first element in the stream.
    //     Example goal: get the first even number from the array.
    @Test
    public void findFirst(){
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int asInt = Arrays.stream(numbers).filter(n -> n % 2 == 0).findFirst().orElse(0);
        System.out.println(asInt);
    }

    // 4 - Test findAny: find any element matching a condition (used often with parallel streams).
    //     Example goal: find any number equal to 9 in the array.
    @Test
    public void findAny(){
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10};
        int i = Arrays.stream(numbers).filter(n -> n == 9).findAny().orElse(0);
        System.out.println(i);
    }

    // 5 - Test allMatch: check if all elements match a condition.
    //     Example goal: verify all numbers are even.
    @Test
    public void allMatch(){
        int[] even = {2, 4, 6, 8, 10};
        boolean b = Arrays.stream(even).allMatch(n -> n % 2 == 0);
        System.out.println(b);
    }


    // 6 - Test anyMatch: check if at least one element matches a condition.
    //     Example goal: check if the array contains at least one odd number.
    @Test
    public void anyMatch(){
        int[] evenAndOneOdd = {2, 4, 6, 8, 10, 11};
        boolean res = Arrays.stream(evenAndOneOdd).anyMatch(n -> n % 2 != 0);
        System.out.println(res);
    }


}
