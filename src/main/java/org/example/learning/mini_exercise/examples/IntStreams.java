package org.example.learning.mini_exercise.examples;

import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Person;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;

public class IntStreams {

    /**
     * This test should demonstrate how IntStream.range() works.
     *
     * Goal:
     * - Create a range of integers from 0 to N (exclusive) or inclusive using rangeClosed().
     * - Example: IntStream.range(0, 10) → 0..9
     * - Count, sum, or print the values.
     *
     * Purpose:
     * Understand how IntStream produces primitive ints efficiently.
     */
    @Test
    public void range() throws Exception {
        // IntStream.range(start, end)
         // generate sequential stream of primitives int
        IntStream.range(0,10).forEach(System.out::print);
        System.out.println();
        // we return an array of primitive ints
        int[] array = IntStream.range(0,10).toArray();
        System.out.println(Arrays.toString(array));
        // generate a list of Integers ==> so here we need to autoBox the primitives
        List<Integer> boxedInts = IntStream.range(0,10)
                .boxed()
                .toList();
        System.out.println(boxedInts);

        System.out.println();
        System.out.println("Range closed include last in range");
        // IntStream.rangeClosed(start, end)
        IntStream.rangeClosed(0,10).forEach(System.out::print);
        System.out.println();
        // we return an array of primitive ints
        int[] array1 = IntStream.rangeClosed(0,10).toArray();
        System.out.println(Arrays.toString(array1));
        System.out.println();
        // generate a list of Integers ==> so here we need to autoBox the primitives
        List<Integer> boxedInts1 = IntStream.rangeClosed(0,10)
                .boxed()
                .toList();
        System.out.println(boxedInts1);

    }


    /**
     * This test should show how to iterate over a list using IntStream.range().
     *
     * Example:
     * List<Person> people = ...
     * Use IntStream.range(0, people.size()) to loop by index:
     * - Access people.get(i)
     * - Print name, age, etc.
     *
     * Purpose:
     * Understand when using IntStream is better than a standard for-loop.
     */
    @Test
    public void rangeIteratingLists() throws Exception {
        List<Person> people = MockData.getPeople();
        // IntStream.range(0, people.size())
        //   .forEach(i -> people.get(i) ...)
//        List<Person> list = IntStream.range(0, people.size())      // now we have a stream on the indexes inside the stream of person haha very powerful (for i loop)
//                .filter(i -> i % 2 == 0)
//                .mapToObj(i -> people.get(i))
//                .toList();
//        System.out.println(list.size());
//        list.forEach(System.out::println);
        /********************/
        var result = IntStream.range(0, people.size())
                .filter(i -> i % 2 == 0)
                .filter(i -> people.get(i).getAge() > 80 && people.get(i).getGender().equalsIgnoreCase("female"))
                .boxed()
                .collect(Collectors.toMap(k -> k, v -> people.get(v).getId()));

        result.forEach((key, value) -> System.out.println(
                "Even index: " + key + " -> id person :  " + value
        ));



    }


    /**
     * This test should demonstrate IntStream.iterate().
     *
     * Example:
     * IntStream.iterate(0, n -> n + 2).limit(5)
     * Produces: 0, 2, 4, 6, 8
     *
     * Purpose:
     * Learn how to generate numeric sequences with a unary operator
     * instead of using for-loops.
     */
    @Test
    public void intStreamIterate()  {
        // IntStream.iterate(seed, UnaryOperator)
        //   .limit(n)
        List<Integer> list = IntStream.iterate(0, n -> n + 1)
                .limit(10)
                .boxed()
                .toList();
        System.out.println(list);

        List<Integer> fib = IntStream.iterate(0, n -> n + 1)
                .limit(10)
                .boxed()
                .toList();
        System.out.println(fib);


    }


    // ----------------------------------------------------------
    // BONUS TESTS YOU SHOULD ADD TO MASTER IntStream
    // ----------------------------------------------------------

    /**
     * BONUS:
     * Show how to convert an IntStream to a List<Integer>.
     *
     * Example result: [1, 2, 3, 4, 5]
     */
    @Test
    public void intStreamToList() {
//        List<Integer> collect = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());
//        System.out.println(collect);
        List<Integer> list = IntStream.rangeClosed(1, 5)
                .boxed()
                .toList();
        System.out.println(list);
    }

    /**
     * BONUS:
     * Show how to compute statistics using IntStream.summaryStatistics().
     *
     * Should show:
     * - min
     * - max
     * - average
     * - sum
     * - count
     */
    @Test
    public void intStreamStatistics() {
        // IntSummaryStatistics stats = IntStream.of(...).summaryStatistics();
        DoubleStream doubleStream = DoubleStream.of(1.0, 2.8, 3.3, 4, 5.4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        DoubleSummaryStatistics doubleSummaryStatistics = doubleStream.summaryStatistics();
        System.out.println(doubleSummaryStatistics);
    }


    /**
     * BONUS:
     * Show how to filter numbers, e.g. even numbers only.
     *
     * Example output: 2, 4, 6, 8
     */
    @Test
    public void filteringIntStream() {
        // IntStream.range(1, 10)
        //   .filter(n -> n % 2 == 0)
        IntStream range = IntStream.range(1, 10);
        List<Integer> list = range.filter(n -> n % 2 == 0)
                .boxed()
                .toList();
        System.out.println(list);
    }


    /**
     * BONUS:
     * Convert IntStream to an array.
     *
     * Example:
     * int[] numbers = IntStream.range(1, 5).toArray();
     */
    @Test
    public void intStreamToArray() {
        // IntStream.range(1, 5).toArray();
        IntStream range = IntStream.range(1, 5);
        int[] arr = range.toArray();
        System.out.println(Arrays.toString(arr));
    }


    // ----------------------------------------------------------
    // More Advance concept
    // ----------------------------------------------------------

    /** Main Class for test */
        //    public static void main(String[] args) {
//
//        // boxing converting a primitive type into its wrapper class (to Object type)
//        int i = 10;
//        Integer integer = Integer.valueOf(i);
//        System.out.println(integer);
//
//        // Auto boxing is : the same converting but happens internally by the compiler
//        Integer integer1 = i;
//        System.out.println(integer1);
//
//        // example :
//        List<Integer> arrayList = new ArrayList<Integer>(10);
//        arrayList.add(1); // that's auto boxing, or it will be Integer.valueOf(i)
//
//        // auto unboxing from object type to primitive by compiler itself
//        Integer integer = arrayList.get(0);
//        System.out.println(integer);
//
//        // unboxing
//        int i2 = arrayList.get(0).intValue(); // assigning the object type to primitive
//        System.out.println(i2);
//
//
//        // ----------------------------------------------------------
//        // IntStream
//        // ----------------------------------------------------------
//
//        int i = 10; // 4 bytes of memory
//        Integer i1 = Integer.valueOf(10); // 20 bytes of memory (4 bytes for references + 16 bytes ofr the object)
//
//       IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        intStream.forEach(System.out::println);
//
//        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        integerStream.forEach(System.out::println);
//
//        // ==> performance wise you check Haa
//
//
//
//        // Advantages of IntStream
//        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9,10);
//        Optional<Integer> result = intStream
//                .reduce((a,b) -> a+ b);
//        result.ifPresent(System.out::println);
//
//        Integer reduce = intStream
//                .reduce(0, (a, b) -> a + b);
//        System.out.println(reduce);
//
//        //Unboxing in Stream API
//        IntStream intStream =
//                integerStream.mapToInt(Integer::intValue);
//        int sum = intStream.sum();
//        //System.out.println(sum);
//        // so to summup we can do
//        int sum1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                .mapToInt(Integer::intValue)
//                .sum();
//
//        //System.out.println(sum1);
//
//        // boxing in Stream API, from IntStream to Stream of objects Integer
//        IntStream intStream1 = IntStream.rangeClosed(0, 10);
//        intStream1
//                .boxed()
//                .map(n-> {
//                    return n.hashCode();
//                })
//                .forEach(hashCode -> System.out.println(hashCode));
//
//        // example
//        List<Integer> listOfIntegers = IntStream.rangeClosed(0, 10)
//                .boxed()
//                .collect(Collectors.toList());
//        System.out.println(listOfIntegers);
//
//        // assignment
//        Integer[] integersArray = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        Stream<Integer> stream = Arrays.stream(integersArray);
//
//        //1
//        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        IntStream stream1 = Arrays.stream(arr);
//
//
//    }
    /****/

    // ----------------------------------------------------------
    // Exercises
    // ----------------------------------------------------------

    // --------------------------------------------------------------------------------------------
    // EXERCISE 1 — Sum of digits
    // --------------------------------------------------------------------------------------------

    /* Purpose: compute the sum of digits of an integer (e.g., 987 → 24). */
    public int sumDigitsImperative(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    @Test
    public void sumDigitsStream() {
        int num = 987;
        String str = String.valueOf(num);
        int sum = str.chars()
                        .map(c -> c - '0')
                                .sum();
        //System.out.println(sum);
        // or
        int sum1 = IntStream.iterate(987, x -> x > 0, x -> x / 10) // this is like while(n > 0)
                .map(x -> x % 10)
                .sum();
        System.out.println(sum1);

    }


    // --------------------------------------------------------------------------------------------
    // EXERCISE 2 — Count numbers divisible by 3 and 5 (1..1000)
    // --------------------------------------------------------------------------------------------

    /* Purpose: count numbers between 1 and 1000 divisible by both 3 and 5. */
    public int countDivisibleImperative() {
        int count = 0;
        for (int i = 1; i <= 1000; i++) {
            if (i % 15 == 0) count++;
        }
        return count;
    }

    @Test
    public void countDivisibleStream() {
        long count = IntStream.rangeClosed(1, 1000)
                .filter(c -> c % 15 == 0)
                .count();
        System.out.println(count);

    }


    // --------------------------------------------------------------------------------------------
    // EXERCISE 3 — Map index to squared value
    // --------------------------------------------------------------------------------------------

    /* Purpose: build Map<index, value²> from a list. */
    public Map<Integer, Integer> indexToSquareImperative(List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(i, list.get(i) * list.get(i));
        }
        return map;
    }

    @Test
    public void indexToSquareStream() {
        List<Integer> list = IntStream.range(0,10).boxed().toList();
        System.out.println(list);
        Map<Integer, Integer> collect = IntStream.range(0, list.size())
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        i -> (int) Math.pow(i,2)
                ));
        System.out.println(collect);
    }


    // --------------------------------------------------------------------------------------------
    // EXERCISE 4 — Remove duplicates but keep first occurrence order
    // --------------------------------------------------------------------------------------------

    /* Purpose: remove duplicates but keep original order (first appearance only). */
    public List<Integer> removeDuplicatesImperative(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (Integer x : list) {
            if (!result.contains(x)) {
                result.add(x);
            }
        }
        return result;
    }

    @Test
    public void removeDuplicatesStream() {
        List<Integer> list = IntStream.of(1,2,1,8,2,3,4,5,5,6,7,9)
                .boxed()
                .distinct()
                .toList();
        System.out.println(list);
    }


    // --------------------------------------------------------------------------------------------
    // EXERCISE 5 — Generate prime numbers up to N
    // --------------------------------------------------------------------------------------------

    /* Purpose: return all prime numbers up to n. */
    public List<Integer> primesUpToImperative(int n) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            boolean prime = true;
            for (int d = 2; d * d <= i; d++) {
                if (i % d == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) primes.add(i);
        }
        return primes;
    }

    @Test
    public void primesUpToStream() {
        //System.out.println(Arrays.toString(array));
        // first way
        List<Integer> list = IntStream.rangeClosed(0,40)
                        .filter(
                                x -> IntStream.rangeClosed(2, (int) Math.sqrt(x))
                                        .allMatch(i -> x % i != 0)
                        )
                                .boxed()
                                        .toList();
        System.out.println(list);

        // second way is declaring a predicate method that returns
        IntPredicate intPredicate = p -> IntStream.rangeClosed(2,(int) Math.sqrt(p))
                .allMatch(i -> p % i != 0);
        List<Integer> list1 = IntStream.range(0, 40)
                .filter(intPredicate)
                .boxed()
                .toList();
        System.out.println(list1);

    }


    // --------------------------------------------------------------------------------------------
    // EXERCISE 6 — Multiplication table NxN
    // --------------------------------------------------------------------------------------------

    /* Purpose: return an NxN multiplication table. */
    public int[][] multiplicationTableImperative(int n) {
        int[][] table = new int[n][n];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                table[i - 1][j - 1] = i * j;
            }
        }
        return table;
    }

    @Test
    public void multiplicationTableStream() {
//        IntStream.rangeClosed(1, 3)
//                .mapToObj(
//                        x -> IntStream.rangeClosed(1, 3)
//                                .map(v -> v * x)
//                                .boxed()
//                                .toList()
//                ).forEach(System.out::println);

        // other solution:
        int[] array = IntStream.rangeClosed(1, 3).toArray();
        //System.out.println(Arrays.toString(array));
        Arrays.stream(array)
                .mapToObj(x -> IntStream.rangeClosed(1, 3)
                        .map(v -> v * x).boxed().toList()).forEach(System.out::println);

    }


    // --------------------------------------------------------------------------------------------
    // EXERCISE 7 — First 20 nums whose digit-sum is divisible by 7
    // --------------------------------------------------------------------------------------------

    /* Purpose: generate first 20 numbers whose digit sum is divisible by 7. */
    public List<Integer> first20DigitSumDivisibleImperative() {
        List<Integer> result = new ArrayList<>();
        int i = 1;
        while (result.size() < 20) {
            int sum = sumDigitsImperative(i);
            if (sum % 7 == 0) {
                result.add(i);
            }
            i++;
        }
        return result;
    }

    @Test
    public void first20DigitSumDivisibleStream() {

        int[] array = IntStream.rangeClosed(1, 20).toArray();
        //System.out.println(Arrays.toString(array));
//        int sum = IntStream.iterate(19, x -> x >= 0, x -> x / 10)
//                .map(x -> x % 10)
//                .limit(20)
//                .sum();
//        System.out.println(sum);
        
//        IntPredicate intPredicate = x -> IntStream.iterate(x, n -> n > 0, n -> n / 10)
//                .allMatch(v -> v % 7 == 0);
//        List<Integer> list = IntStream.iterate(1, x -> x + 1)
//                .filter(intPredicate)
//                .limit(20)
//                .boxed()
//                .toList();
//        System.out.println(list);

        int[] array1 = IntStream.iterate(1, x -> x + 1)
                .takeWhile(x -> x > 0)
                .filter(w -> IntStream.iterate(w, n -> n / 10)
                        .takeWhile(c -> c > 0)
                        .map(x -> x % 10)
                        .sum() % 7 == 0
                ).limit(20).toArray();
        System.out.println(Arrays.toString(array1));

//        IntPredicate intPredicate = x -> IntStream.iterate(x, n -> n / 10)
//                .limit(20)
//                .map(v -> v % 10)
//                .sum() % 7 == 0;
//        List<Integer> list = IntStream.iterate(1, n -> n + 1)
//                .filter(intPredicate)
//                .limit(20)
//                .boxed()
//                .toList();
//        System.out.println(list);


    }


    // --------------------------------------------------------------------------------------------
    // EXERCISE 8 — Zip two lists
    // --------------------------------------------------------------------------------------------

    /* Purpose: combine name and age lists into ["A:10", "B:20"...]. */
    public List<String> zipImperative(List<String> names, List<Integer> ages) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            result.add(names.get(i) + ":" + ages.get(i));
        }
        return result;
    }

    @Test
    public void zipStream() {
        List<String> result = List.of("A","B","C","D");
        List<Integer> ages = List.of(10,20,30);
        List<String> list = IntStream.range(0, result.size())
                .mapToObj(i -> result.get(i) + ":" + (i < ages.size() ? ages.get(i) : "no age"))
                .toList();
        System.out.println(list);


//        List<Integer> list = IntStream.iterate(1, n -> n + 1)
//                .limit(result.size())
//                .map(x -> x * 10)
//                .boxed().toList();
//        System.out.println(list);

    }


    // --------------------------------------------------------------------------------------------
    // EXERCISE 9 — Fibonacci using IntStream.iterate
    // --------------------------------------------------------------------------------------------

    /* Purpose: generate first n Fibonacci numbers. */
    public List<Integer> fibonacciImperative(int n) {
        List<Integer> fib = new ArrayList<>();
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            fib.add(a);
            int next = a + b;
            a = b;
            b = next;
        }
        return fib;
    }

    @Test
    public void fibonacciStream() {
        // using Streams
        List<Integer> collect = Stream.iterate(new int[]{0, 1}, a -> new int[]{a[1], a[0] + a[1]})
                .limit(10)
                .map(a -> a[0])
                .collect(Collectors.toList());
        System.out.println(collect);


    }


    // --------------------------------------------------------------------------------------------
    // EXERCISE 10 — Sliding window average (window = 3)
    // --------------------------------------------------------------------------------------------

    /* Purpose: compute average of each window of size 3 in the list. */
    public List<Double> slidingWindowImperative(List<Integer> list) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < list.size() - 2; i++) {
            double avg = (list.get(i) + list.get(i + 1) + list.get(i + 2)) / 3.0;
            result.add(avg);
        }
        return result;
    }

    @Test
    public void slidingWindowStream() {
        List<Integer> list = List.of(1,2,3,4,15,6,8,6,9);
        int k = 3;
        List<Double> list1 = IntStream.range(0, list.size() - k + 1)
                .mapToDouble(i -> IntStream.range(i, i + k)
                        .map(list::get)
                        .average().orElse(0)
                ).boxed()
                .toList();
        System.out.println(list1);
    }










}

