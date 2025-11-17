package org.example.learning.mini_exercise.examples;

import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Person;
import org.junit.Test;

import java.util.List;

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
        // IntStream.rangeClosed(start, end)
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
        // IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());
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
    }
}

