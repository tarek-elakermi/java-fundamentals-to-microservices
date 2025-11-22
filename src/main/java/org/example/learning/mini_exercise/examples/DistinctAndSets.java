package org.example.learning.mini_exercise.examples;

import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Person;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DistinctAndSets {

    /**
     * This test should demonstrate how to remove duplicates using
     * Stream.distinct().
     *
     * Input:
     * [1, 1, 2, 2, 3, 3, ..., 9, 9, 9, 9]
     *
     * Expected output:
     * [1, 2, 3, 4, 5, 6, 7, 8, 9]
     *
     * Purpose:
     * - Show how .distinct() works on a stream.
     * - Understand that it uses equals() to determine duplicates.
     * - Preserve element order while removing duplicates.
     */
    @Test
    public void distinct() throws Exception {
        List<Integer> numbers = List.of(1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,9,9,9);
        List<Integer> list = numbers.stream().distinct().toList();
        System.out.println(list);
    }


    /**
     * This test should demonstrate removing duplicates using a Set.
     *
     * Purpose:
     * - Convert the list to a Set (HashSet, LinkedHashSet, TreeSet)
     * - Understand differences:
     *   • HashSet: removes duplicates, no order guarantee
     *   • LinkedHashSet: removes duplicates, keeps insertion order
     *   • TreeSet: sorted order
     *
     * Example outputs:
     * HashSet      -> random order
     * LinkedHashSet -> [1,2,3,4,5,6,7,8,9]
     * TreeSet      -> [1,2,3,4,5,6,7,8,9] (sorted)
     */
    @Test
    public void distinctWithSet() throws Exception {
        List<Integer> numbers = List.of(1,1,2,2,5,5,3,3,4,4,6,6,8,8,7,7,9,9,9,9,9);
        // new HashSet<>(numbers)
        System.out.println(new HashSet<>(numbers));
        // new LinkedHashSet<>(numbers)
        System.out.println(new LinkedHashSet<>(numbers));
        // new TreeSet<>(numbers)
        System.out.println(new TreeSet<>(numbers));
    }


    // ---------------------------------------------------------------
    // BONUS TESTS to better understand distinct and sets
    // ---------------------------------------------------------------

    /**
     * BONUS:
     * Demonstrate distinct() on a stream of custom objects.
     *
     * Purpose:
     * - Understand that distinct() requires proper equals() and hashCode()
     * - Without them, duplicates will NOT be removed
     */
    @Test
    public void distinctOnCustomObjects() throws Exception {
        List<Person> people = MockData.getPeople();
        List<Person> list = people.stream().distinct().toList();
        System.out.println(list.size());
        list.forEach(System.out::println); // it always shows the added element 1001
        // Only works if Person has equals + hashCode

        // after generating the equals and hash excluding the id the distinc now it works and it exclude the person 1001 that
        // have same content as person with id 1000
    }


    /**
     * BONUS:
     * Remove duplicates AND sort numbers using Streams.
     *
     * Example:
     * [1,2,3,4,5,6,7,8,9]
     */
    @Test
    public void distinctAndSorted() throws Exception {
        List<Integer> numbers = List.of(
                1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,9
        );
//        System.out.println(numbers.stream().distinct().sorted());;
    }


    /**
     * BONUS:
     * Count how many duplicates were removed.
     *
     * Example:
     * Original size = 21
     * Distinct size = 9
     * Removed = 12
     */
    @Test
    public void countRemovedDuplicates() throws Exception {
        List<Integer> numbers = List.of(
                1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,9
        );
        // Compare list.size() vs stream.distinct().count()
    }
}
