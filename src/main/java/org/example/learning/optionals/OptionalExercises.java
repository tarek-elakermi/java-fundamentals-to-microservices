package org.example.learning.optionals;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalExercises {

    /**
     * EXERCISE 1:
     * Given an Optional<String> name, return it upper-cased.
     * If empty, return "UNKNOWN".
     */
    @Test
    public void exercise1() {
        // Test cases
        Optional<String> name1 = Optional.of("tarek");
        Optional<String> name2 = Optional.empty();
        String s1 = name1.map(String::toUpperCase).get();
        String s2 = name2.map(String::toUpperCase).orElse("UNKNOWN");
        System.out.println(s1);
        System.out.println(s2);
    }

    /**
     * EXERCISE 2:
     * Given an Optional<Integer> age, return true if age >= 18.
     * Return false if empty.
     */
    @Test
    public void exercise2() {
        // Test cases
        Optional<Integer> age1 = Optional.of(20);
        Optional<Integer> age2 = Optional.of(15);
        Optional<Integer> age3 = Optional.empty();
        boolean present = age1.filter(a -> a >= 18).isPresent();
        boolean present2 = age2.filter(a -> a >= 18).isPresent();
        boolean present3 = age3.filter(a -> a >= 18).isPresent();

        System.out.println(present);
        System.out.println(present2);
        System.out.println(present3);

    }

    /**
     * EXERCISE 3:
     * Given an Optional<List<String>> list,
     * return the size of the list using map().
     * If empty, return 0.
     */
    @Test
    public void exercise3() {
        // Test cases
        Optional<List<String>> list1 = Optional.of(List.of("A", "B", "C"));
        Optional<List<String>> list2 = Optional.of(List.of());
        Optional<List<String>> list3 = Optional.empty();
        Integer integer = list1.map(List::size).get();
        Integer integer1 = list2.map(List::size).get();
        Integer integer2 = list3.map(List::size).orElse(0);
        System.out.println(integer);
        System.out.println(integer1);
        System.out.println(integer2);
    }

    /**
     * EXERCISE 4:
     * Given an Optional<String> word,
     * return Optional<Integer> representing its length.
     * Use map().
     */
    @Test
    public void exercise4() {
        // Test cases
        Optional<String> word1 = Optional.of("hello");
        Optional<String> word2 = Optional.empty();
        Optional<Integer> i = word1.map(String::length);
        Optional<Integer> i1 = word2.map(String::length);
        System.out.println(i + " --------- " + i1);
    }

    /**
     * EXERCISE 5:
     * Given Optional<Optional<String>> nested,
     * flatten it so you return Optional<String> using flatMap().
     */
    @Test
    public void exercise5() {
        // Test cases
        Optional<Optional<String>> nested1 = Optional.of(Optional.of("nested"));
        Optional<Optional<String>> nested2 = Optional.of(Optional.empty());
        Optional<Optional<String>> nested3 = Optional.empty();

        Optional<String> s = nested1.flatMap(x -> x);
        Optional<String> s1 = nested2.flatMap(x -> x);
        Optional<String> s2 = nested3.flatMap(x -> x);
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
    }

    /**
     * EXERCISE 6:
     * Given Optional<Integer> number,
     * keep it ONLY if it is even (use filter()).
     * Otherwise return Optional.empty().
     */
    @Test
    public void exercise6() {
        // Test cases
        Optional<Integer> num1 = Optional.of(10);
        Optional<Integer> num2 = Optional.of(7);
        Optional<Integer> num3 = Optional.empty();
        Optional<Integer> i = num1.filter(x -> x % 2 == 0);
        System.out.println(i);
        Optional<Integer> i1 = num2.filter(x -> x % 2 == 0);
        System.out.println(i1);
        Optional<Integer> i2 = num3.filter(x -> x % 2 == 0);
        System.out.println(i2);
    }

    /**
     * EXERCISE 7:
     * Given Optional<String> email,
     * return "Invalid email" if empty.
     * Otherwise return the same email.
     */
    @Test
    public void exercise7() {
        // Test cases
        Optional<String> email1 = Optional.of("example@test.com");
        Optional<String> email2 = Optional.empty();
        String s = email1.orElse("Invalid email");
        String s1 = email2.orElse("Invalid email");
        System.out.println(s);
        System.out.println(s1);
    }

    /**
     * EXERCISE 8:
     * Given an Optional<Double> price,
     * if present return price + 10.
     * If empty throw IllegalArgumentException using orElseThrow().
     */
    @Test
    public void exercise8() {
        // Test cases
        Optional<Double> price1 = Optional.of(90.0);
        Optional<Double> price2 = Optional.empty();
        Double v = price1.map(x -> x + 10)
                .orElseThrow(() -> new IllegalArgumentException("price is not exist"));
        //System.out.println(v);

//        Double v1 = price2.map(x -> x + 10)
//                .orElseThrow(() -> new IllegalArgumentException("price is not exist"));


        //System.out.println(v1);

        // or another clean way
        Double p = price1
                .map(x -> x + 10)
                .orElseThrow(() -> new IllegalArgumentException("price is not exist"));

        System.out.println(p); // 100.0

        try {
            Double p1 = price2
                    .map(x -> x + 10)
                    .orElseThrow(() -> new IllegalArgumentException("price is not exist"));
            System.out.println(p1);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception for price2: " + e.getMessage());
        }
    }

    /**
     * EXERCISE 9:
     * Given an Optional<String> sentence,
     * return the number of words.
     * Use map() and split().
     */
    @Test
    public void exercise9() {
        // Test cases
        Optional<String> sentence1 = Optional.of("hello world this is java");
        Optional<String> sentence2 = Optional.of("single");
        Optional<String> sentence3 = Optional.empty();
        Optional<Integer> i = sentence1.map(l -> l.split(" ").length);
        System.out.println(i);
        Optional<Integer> i1 = sentence2.map(l -> l.split(" ").length);
        System.out.println(i1);
        Optional<Integer> i2 = sentence3.map(l -> l.split(" ").length);
        System.out.println(i2);
    }

    /**
     * EXERCISE 10:
     * Given Optional<Integer> x and Optional<Integer> y,
     * calculate x + y using flatMap() and map().
     * If either is empty, return Optional.empty().
     */
    @Test
    public void exercise10() {
        // Test cases
        Optional<Integer> x1 = Optional.of(5);
        Optional<Integer> y1 = Optional.of(7);

        Optional<Integer> i = x1.flatMap(a -> y1.map(b -> a + b));
        System.out.println(i);

        Optional<Integer> x2 = Optional.of(5);
        Optional<Integer> y2 = Optional.empty();
        Optional<Integer> i1 = x2.flatMap(a -> y2.map(b -> a + b));
        System.out.println(i1);

        Optional<Integer> x3 = Optional.empty();
        Optional<Integer> y3 = Optional.of(7);

        Optional<Integer> i2 = x3.flatMap(a -> y3.map(b -> a + b));
        System.out.println(i2);
    }
}
