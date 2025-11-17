package org.example.learning.mini_exercise.examples;

import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Car;
import org.junit.Test;

import java.util.List;

public class GroupingData {

    /**
     * This test should demonstrate SIMPLE GROUPING using Collectors.groupingBy().
     *
     * Example with cars:
     * - Group cars by make (e.g., Toyota, Honda, Ford...)
     * - Or group by color, year, or any Car field
     *
     * Purpose:
     * Understand how groupingBy() produces a Map<Key, List<Value>>
     * Example result:
     * {
     *   "Toyota" -> [Car1, Car2, ...],
     *   "BMW"    -> [Car5, Car6, ...]
     * }
     */
    @Test
    public void simpleGrouping() throws Exception {
        List<Car> cars = MockData.getCars();
        // Use Collectors.groupingBy(Car::getMake) or similar
    }


    /**
     * This test should show grouping + counting for simple strings.
     *
     * Example input:
     * John, John, Mariam, Alex, Mohammado, Mohammado, Vincent, Alex, Alex
     *
     * Expected output:
     * John -> 2
     * Mariam -> 1
     * Alex -> 3
     * Mohammado -> 2
     * Vincent -> 1
     *
     * Purpose:
     * Use Collectors.groupingBy(name -> name, Collectors.counting())
     * to count occurrences.
     */
    @Test
    public void groupingAndCounting() throws Exception {
        List<String> names = List.of(
                "John",
                "John",
                "Mariam",
                "Alex",
                "Mohammado",
                "Mohammado",
                "Vincent",
                "Alex",
                "Alex"
        );
        // groupingBy(name -> name, counting())
    }


    // ---------------------------------------------------------------
    // BONUS: Additional grouping tests you should practice
    // ---------------------------------------------------------------

    /**
     * BONUS:
     * Group cars by make AND count how many cars per make.
     *
     * Example:
     * Toyota -> 5
     * Honda -> 2
     */
    @Test
    public void groupingCarsAndCounting() throws Exception {
        List<Car> cars = MockData.getCars();
        // groupingBy(Car::getMake, counting())
    }


    /**
     * BONUS:
     * Group cars by year AND compute average price for each year.
     *
     * Example:
     * 2010 -> 15,500
     * 2011 -> 17,200
     *
     * Purpose:
     * Learn Collectors.averagingDouble()
     */
    @Test
    public void groupingAndAveraging() throws Exception {
        List<Car> cars = MockData.getCars();
        // groupingBy(Car::getYear, averagingDouble(Car::getPrice))
    }


    /**
     * BONUS:
     * Group cars by make, but instead of storing Car objects,
     * store only the model names in the grouped list.
     *
     * Example:
     * Toyota -> ["Camry", "Corolla"]
     * BMW -> ["X5", "M3"]
     *
     * Purpose:
     * Learn Collectors.mapping() inside groupingBy()
     */
    @Test
    public void groupingAndMapping() throws Exception {
        List<Car> cars = MockData.getCars();
        // groupingBy(Car::getMake, mapping(Car::getModel, toList()))
    }


    /**
     * BONUS:
     * Multi-level grouping:
     * Group cars first by make, then by year.
     *
     * Example:
     * Toyota:
     *     2010 -> [car1, car2]
     *     2011 -> [car3]
     * BMW:
     *     2012 -> [car4]
     *
     * Purpose:
     * Learn nested groupingBy()
     */
    @Test
    public void multiLevelGrouping() throws Exception {
        List<Car> cars = MockData.getCars();
        // groupingBy(Car::getMake, groupingBy(Car::getYear))
    }
}

