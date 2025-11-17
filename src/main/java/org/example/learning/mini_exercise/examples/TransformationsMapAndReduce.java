package org.example.learning.mini_exercise.examples;

import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Car;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TransformationsMapAndReduce {

    // 1 - Test map(): transform each element into another form.
    //     Example goal: map cars to their model names or map numbers to squared values.
    @Test
    public void yourFirstTransformationWithMap() {

    }

    // 2 - Test mapToDouble(): convert a field to double and compute a result.
    //     Example goal: map each car's price to double and calculate the average price.
    @Test
    public void mapToDoubleAndFindAverageCarPrice() throws IOException {
        List<Car> cars = MockData.getCars();
    }

    // 3 - Test reduce(): combine all elements to a single result.
    //     Example goal: reduce integers to their total sum.
    @Test
    public void reduce() {
        int[] integers = {1, 2, 3, 4, 99, 100, 121, 1302, 199};
    }
}
