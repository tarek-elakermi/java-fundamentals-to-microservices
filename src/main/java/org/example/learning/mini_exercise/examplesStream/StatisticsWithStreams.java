package org.example.learning.mini_exercise.examplesStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Car;
import org.junit.Test;

import java.util.List;

public class StatisticsWithStreams {

    private static final ObjectMapper mapper = new ObjectMapper();


    // 8 - Test count: count how many cars match a condition.
    //     Example: count cars with price > 50_000.
    @Test
    public void count() throws Exception {
        List<Car> cars = MockData.getCars();
    }

    // 9 - Test min: find the car with minimum value using a comparator.
    //     Example: find the cheapest car (min price).
    @Test
    public void min() throws Exception {
        List<Car> cars = MockData.getCars();
    }

    // 10 - Test max: find the car with maximum value using a comparator.
    //      Example: find the most expensive car (max price).
    @Test
    public void max() throws Exception {
        List<Car> cars = MockData.getCars();
    }

    // 11 - Test average: compute the average of a numeric field.
    //      Example: calculate the average price of all cars.
    @Test
    public void average() throws Exception {
        List<Car> cars = MockData.getCars();
    }

    // 12 - Test sum: calculate the sum of a numeric field.
    //      Example: sum all car prices.
    @Test
    public void sum() throws Exception {
        List<Car> cars = MockData.getCars();
    }

    // 13 - Test statistics: return full summary statistics (count, sum, min, max, avg)
    //      Example: get DoubleSummaryStatistics for car prices.
    @Test
    public void statistics() throws Exception {
        List<Car> cars = MockData.getCars();
    }
}
