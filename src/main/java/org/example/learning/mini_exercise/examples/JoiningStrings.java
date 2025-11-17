package org.example.learning.mini_exercise.examples;

import org.junit.Test;

import java.util.List;

public class JoiningStrings {

    /**
     * This test should convert each name to capitalized form
     * and join them into a single comma-separated string:
     *
     * Input:  ["anna", "john", "marcos", "helena", "yasmin"]
     * Output: "Anna, John, Marcos, Helena, Yasmin"
     *
     * This version does it WITHOUT using streams
     * (using a normal for-loop or StringBuilder).
     */
    @Test
    public void joiningStrings() throws Exception {
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        // implement classic Java logic here (no streams)
    }


    /**
     * This test should do the same job using Java Streams.
     *
     * Steps:
     * 1. Stream the list
     * 2. Capitalize each name
     * 3. Join them using Collectors.joining(", ")
     *
     * Output: "Anna, John, Marcos, Helena, Yasmin"
     */
    @Test
    public void joiningStringsWithStream() throws Exception {
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        // implement with stream + map + Collectors.joining
    }


    /**
     * BONUS TEST:
     * Joining with a PREFIX and SUFFIX.
     *
     * Should produce:
     * "[Anna; John; Marcos; Helena; Yasmin]"
     */
    @Test
    public void joiningWithPrefixSuffix() throws Exception {
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        // Collectors.joining(";", "[", "]")
    }


    /**
     * BONUS TEST:
     * Join only names starting with 'h'.
     *
     * Should produce:
     * "Helena"
     */
    @Test
    public void joiningFilteredNames() throws Exception {
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        // use filter(name -> name.startsWith("h"))
    }


    /**
     * BONUS TEST:
     * Convert the list into uppercase before joining.
     *
     * Output:
     * "ANNA | JOHN | MARCOS | HELENA | YASMIN"
     */
    @Test
    public void joiningUppercaseNames() throws Exception {
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        // map(String::toUpperCase) + joining(" | ")
    }


    /**
     * BONUS TEST:
     * Demonstrates using Collectors.mapping() inside Collectors.joining().
     *
     * Same output:
     * "Anna, John, Marcos, Helena, Yasmin"
     *
     * But using a nested collector.
     */
    @Test
    public void joiningUsingMappingCollector() throws Exception {
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        // Collectors.mapping(...) inside Collectors.joining
    }
}

