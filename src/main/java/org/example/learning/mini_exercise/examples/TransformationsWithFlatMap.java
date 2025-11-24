package org.example.learning.mini_exercise.examples;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

public class TransformationsWithFlatMap {

    private static final List<List<String>> arrayListOfNames = List.of(
            List.of("Mariam", "Alex", "Ismail"),
            List.of("John", "Alesha", "Andre"),
            List.of("Susy", "Ali")
    );

//    @BeforeClass
//    public static void setUp() {
//        System.out.println(arrayListOfNames);
//    }

    // 1 - Test flattening without flatMap:
    //     Manually merge all innersts li into one list of names without using flatMap.
    //     Expected: [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]
    @Test
    public void withoutFlatMap() throws Exception {
        List<String> names = new ArrayList<>();
        for (List<String> list: arrayListOfNames){
            names.addAll(list);
        }
        System.out.println(names);
    }

    // 2 - Test flatMap: use flatMap to flatten all inner lists into a single stream.
    //     Expected: [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]
    @Test
    public void withFlatMap() throws Exception {
        List<String> list = arrayListOfNames.stream()
                .flatMap(Collection::stream)
                .toList();
        System.out.println(list);
    }

    // 3 - Test flatMap with Optional: unwrap each Optional<String> and collect values.
    //     Example goal: turn List<Optional<String>> into List<String>.
    @Test
    public void flatMapWithOptionals() {
        List<Optional<String>> optionals = List.of(
                Optional.of("Amigos"),
                Optional.of("Code")
        );

        System.out.println(optionals);

        List<String> list = optionals.stream().flatMap(Optional::stream).toList();
        System.out.println(list);

    }

    // 4 - Flatten a List of Sets into a single List
    @Test
    public void flattenListOfSets() {
        // Data: List of Sets containing programming languages
        List<Set<String>> listOfSets = List.of(
                Set.of("Java", "Python", "C++"),
                Set.of("JavaScript", "TypeScript"),
                Set.of("Go", "Rust", "Kotlin", "Java"),
                Set.of("Swift", "Dart")
        );

        // Goal: Convert List<Set<String>> into List<String>
        // Expected: Flatten all sets and remove duplicates? Or keep duplicates?
    }

    // 5 - Flatten nested arrays
    @Test
    public void flattenNestedArrays() {
        // Data: 2D array of product categories and items
        String[][] nestedArrays = {
                {"Electronics", "Laptops", "Smartphones"},
                {"Clothing", "Shoes", "Accessories"},
                {"Home", "Furniture", "Appliances", "Decor"},
                {"Sports", "Equipment", "Apparel"}
        };

        // Goal: Convert String[][] into List<String>
    }

    // 6 - Extract all words from sentences
    @Test
    public void extractWordsFromSentences() {
        // Data: List of sentences from a paragraph
        List<String> sentences = List.of(
                "The quick brown fox jumps over the lazy dog",
                "Stream API provides functional operations for processing collections",
                "Java programming is object-oriented and platform independent",
                "FlatMap is used to flatten nested structures in streams"
        );

        // Goal: Given List<String> of sentences, extract all words into a single list
    }

    // 7 - Flatten List of Optional Integers
    @Test
    public void flattenOptionalIntegers() {
        // Data: List of Optional integers (some empty, some with values)
        List<Optional<Integer>> optionalIntegers = List.of(
                Optional.of(10),
                Optional.empty(),
                Optional.of(25),
                Optional.of(30),
                Optional.empty(),
                Optional.of(15),
                Optional.empty()
        );

        // Goal: Convert List<Optional<Integer>> into List<Integer>
    }

    // 8 - Flatten Map values
    @Test
    public void flattenMapValues() {
        // Data: Map of departments and their employees
        Map<String, List<String>> departmentEmployees = Map.of(
                "Engineering", List.of("Alice", "Bob", "Charlie"),
                "Marketing", List.of("David", "Eva"),
                "Sales", List.of("Frank", "Grace", "Henry", "Ivy"),
                "HR", List.of("Jack", "Karen")
        );

        // Goal: Flatten all values of Map<String, List<String>> into a single list
    }

    // 9 - Flatten List of Streams
    @Test
    public void flattenListOfStreams() {
        // Data: List of Streams containing different data batches
        List<Stream<String>> listOfStreams = List.of(
                Stream.of("Batch1-Item1", "Batch1-Item2", "Batch1-Item3"),
                Stream.of("Batch2-Item1", "Batch2-Item2"),
                Stream.of("Batch3-Item1", "Batch3-Item2", "Batch3-Item3", "Batch3-Item4")
        );

        // Goal: Convert List<Stream<String>> into a single List<String>
    }

    // 10 - Flatten nested Lists of Integers
    @Test
    public void flattenNestedIntegerLists() {
        // Data: Matrix-like structure of integers
        List<List<Integer>> nestedIntegerLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6, 7),
                List.of(8, 9),
                List.of(10, 11, 12, 13, 14)
        );

        // Goal: Convert List<List<Integer>> into List<Integer>
    }

    // 11 - Flatten Optional of List
    @Test
    public void flattenOptionalOfList() {
        // Data: List of Optional lists (some empty, some with lists)
        List<Optional<List<String>>> optionalLists = List.of(
                Optional.of(List.of("A", "B", "C")),
                Optional.empty(),
                Optional.of(List.of("D", "E")),
                Optional.of(List.of("F")),
                Optional.empty(),
                Optional.of(List.of("G", "H", "I", "J"))
        );

        // Goal: Convert List<Optional<List<String>>> into List<String>
    }

    // 12 - Flatten a List of Arrays
    @Test
    public void flattenListOfArrays() {
        // Data: List of string arrays
        List<String[]> listOfArrays = List.of(
                new String[]{"Apple", "Banana", "Cherry"},
                new String[]{"Date", "Elderberry"},
                new String[]{"Fig", "Grape", "Honeydew", "Ice Apple"}
        );

        // Goal: Convert List<String[]> into List<String>
    }

    // 13 - Flatten List of StringBuilders
    @Test
    public void flattenListOfStringBuilders() {
        // Data: List of StringBuilders with text content
        List<StringBuilder> stringBuilders = List.of(
                new StringBuilder("Hello"),
                new StringBuilder("World"),
                new StringBuilder("Stream"),
                new StringBuilder("API"),
                new StringBuilder("FlatMap")
        );

        // Goal: Convert List<StringBuilder> containing multiple strings into List<String>
    }

    // 14 - Flatten nested List of Optionals
    @Test
    public void flattenNestedListOfOptionals() {
        // Data: Complex nested structure with Optionals
        List<List<Optional<String>>> nestedOptionals = List.of(
                List.of(Optional.of("Data1"), Optional.empty(), Optional.of("Data2")),
                List.of(Optional.empty(), Optional.of("Data3")),
                List.of(Optional.of("Data4"), Optional.of("Data5"), Optional.empty()),
                List.of(Optional.empty(), Optional.empty(), Optional.of("Data6"))
        );

        // Goal: Convert List<List<Optional<String>>> into List<String>
    }

    // 15 - Flatten Set of Lists
    @Test
    public void flattenSetOfLists() {
        // Data: Set of Lists (order doesn't matter in Set)
        Set<List<String>> setOfLists = Set.of(
                List.of("Red", "Green", "Blue"),
                List.of("Yellow", "Purple"),
                List.of("Orange", "Pink", "Black", "White"),
                List.of("Gray", "Brown")
        );

        // Goal: Convert Set<List<String>> into List<String>
    }

    // 16 - Flatten List of Optional Lists
    @Test
    public void flattenListOfOptionalLists() {
        // Data: List of Optional lists with different states
        List<Optional<List<String>>> listOfOptionalLists = List.of(
                Optional.of(List.of("ProjectA", "ProjectB")),
                Optional.empty(),
                Optional.of(List.of("ProjectC")),
                Optional.of(List.of("ProjectD", "ProjectE", "ProjectF")),
                Optional.empty(),
                Optional.of(List.of("ProjectG"))
        );

        // Goal: Convert List<Optional<List<String>>> into List<String>
    }

    // 17 - Flatten nested List of Maps
    @Test
    public void flattenNestedListOfMaps() {
        // Data: List of Maps containing configuration data
        List<Map<String, String>> listOfMaps = List.of(
                Map.of("key1", "value1", "key2", "value2"),
                Map.of("key3", "value3", "key4", "value4", "key5", "value5"),
                Map.of("key6", "value6"),
                Map.of("key7", "value7", "key8", "value8")
        );

        // Goal: Convert List<Map<String, String>> into List<String> of all values
    }

    // 18 - Flatten List of Optionals with filter
    @Test
    public void flattenOptionalsWithFilter() {
        // Data: List of Optional strings with various lengths
        List<Optional<String>> optionalsWithFilter = List.of(
                Optional.of("Java"),
                Optional.of("Hi"),
                Optional.of("Stream"),
                Optional.empty(),
                Optional.of("API"),
                Optional.of("Programming"),
                Optional.of("No"),
                Optional.of("FlatMapOperations"),
                Optional.empty()
        );

        // Goal: Flatten List<Optional<String>> and keep only non-empty strings longer than 3 chars
    }

    // 19 - Flatten List of Lists and transform elements
    @Test
    public void flattenAndTransform() {
        // Data: List of Lists with mixed case strings
        List<List<String>> listsToTransform = List.of(
                List.of("apple", "banana", "cherry"),
                List.of("date", "elderberry"),
                List.of("fig", "grape", "honeydew"),
                List.of("ice apple", "jackfruit")
        );

        // Goal: Flatten List<List<String>> and convert all strings to uppercase
    }

    // 20 - Flatten nested structure and remove duplicates
    @Test
    public void flattenAndRemoveDuplicates() {
        // Data: List of Lists with duplicate elements
        List<List<String>> listsWithDuplicates = List.of(
                List.of("Java", "Python", "Java"),
                List.of("C++", "Python", "JavaScript"),
                List.of("Java", "Go", "Rust"),
                List.of("Python", "TypeScript", "Java")
        );

        // Goal: Flatten List<List<String>> and return distinct elements only
    }

    // 1 - Flatten all car names
    @Test
    public void flattenCarNames() {
        // Goal: Flatten a List<List<String>> of car names (if cars had multiple names per brand)
    }

    // 2 - Flatten people emails
    @Test
    public void flattenPeopleEmails() {
        // Goal: Flatten List<Optional<String>> of emails into List<String>
    }

    // 3 - Flatten all first and last names of people
    @Test
    public void flattenPeopleFullNames() {
        // Goal: Flatten first and last names of each person into a single List<String>
    }

    // 4 - Flatten all car features
    @Test
    public void flattenCarFeatures() {
        // Goal: Each Car has a List<String> features; flatten all features into a single List<String>
    }

    // 5 - Flatten people phone numbers
    @Test
    public void flattenPeoplePhoneNumbers() {
        // Goal: Each Person may have multiple phone numbers; flatten into a single List<String>
    }

    // 6 - Flatten people with optional middle names
    @Test
    public void flattenPeopleMiddleNames() {
        // Goal: Flatten List<Optional<String>> middle names into List<String>
    }

    // 7 - Flatten all car colors
    @Test
    public void flattenCarColors() {
        // Goal: Each Car has multiple colors; flatten all colors into a single List<String>
    }

    // 8 - Flatten nested lists of people addresses
    @Test
    public void flattenPeopleAddresses() {
        // Goal: Each Person has List<List<String>> addresses; flatten into a single List<String>
    }

    // 9 - Flatten people hobbies
    @Test
    public void flattenPeopleHobbies() {
        // Goal: Each Person has List<String> hobbies; flatten all hobbies into a single list
    }

    // 10 - Flatten all car owners
    @Test
    public void flattenCarOwners() {
        // Goal: Each Car may have multiple owners (List<Person>); flatten all owners into a single List<Person>
    }
}

