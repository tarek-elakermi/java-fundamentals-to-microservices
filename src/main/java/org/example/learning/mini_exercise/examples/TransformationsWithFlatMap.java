package org.example.learning.mini_exercise.examples;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransformationsWithFlatMap {

    private static final List<List<String>> arrayListOfNames = List.of(
            List.of("Mariam", "Alex", "Ismail"),
            List.of("John", "Alesha", "Andre"),
            List.of("Susy", "Ali")
    );

//    @Before
//    void setUp() {
//        System.out.println(arrayListOfNames);
//    }

    // 1 - Test flattening without flatMap:
    //     Manually merge all inner lists into one list of names without using flatMap.
    //     Expected: [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]
    @Test
    public void withoutFlatMap() throws Exception {
        List<String> names = new ArrayList<>();
    }

    // 2 - Test flatMap: use flatMap to flatten all inner lists into a single stream.
    //     Expected: [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]
    @Test
    public void withFlatMap() throws Exception {
    }

    // 3 - Test flatMap with Optional: unwrap each Optional<String> and collect values.
    //     Example goal: turn List<Optional<String>> into List<String>.
    @Test
    public void flatMapWithOptionals() {
        List<Optional<String>> optionals = List.of(
                Optional.of("Amigos"),
                Optional.of("Code")
        );
    }
}

