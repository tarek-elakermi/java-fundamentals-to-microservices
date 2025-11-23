package org.example.learning.mini_exercise.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Car;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class GroupingData {


    private <T> void toJson(Map<String, List<T>> str) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(str);
        System.out.println(json);
    }

    private <T> void toStringMap(Map<String, List<T>> str) {
        str.forEach((k, v) -> {
            System.out.println(k + ":");
            v.forEach(car -> System.out.println("  " + car));
        });
    }

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
        Map<String, List<Car>> groups = cars.stream()
                .filter(car -> car.getYear() > 2005)
                .collect(Collectors.groupingBy(Car::getMake));

        //toJson(groups);
        toStringMap(groups);



        //System.out.println(groups);


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
        Map<String, Long> collect = names.stream()
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));
        System.out.println(collect);
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
        Map<String, Long> collect = cars.stream()
                .collect(Collectors.groupingBy(Car::getMake, Collectors.counting()));
        collect.forEach((k,v) -> {
            System.out.println(k + "==" + v);
        });
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
        Map<Integer, Double> collect = cars.stream()
                .collect(Collectors.groupingBy(Car::getYear, Collectors.averagingDouble(Car::getPrice)));
        collect.forEach((k,v) -> {
            System.out.println(k + "==" + v);
        });

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
        Map<String, List<String>> collect = cars.stream()
                .collect(Collectors.groupingBy(Car::getMake, Collectors.mapping(Car::getModel, Collectors.toList())));

        //System.out.println(collect);
        toJson(collect);
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
        Map<String, Map<Integer, List<Car>>> collect = cars.stream()
                .collect(Collectors.groupingBy(Car::getMake, Collectors.groupingBy(Car::getYear, Collectors.toList())));
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        collect.forEach((k,v) -> {
            System.out.println("the make is: " + k);
            v.forEach((y,l) -> {
                try {
                    String json = mapper.writeValueAsString(l);
                    System.out.println(y + "==" + json);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    @Test
    public void partitioning() throws Exception {
        List<Car> cars = MockData.getCars();
        Map<Boolean, List<Car>> collect = cars.stream()
                .limit(10)
                .collect(
                        Collectors.partitioningBy(car -> "Blue".equalsIgnoreCase(car.getColor())));
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        collect.forEach((k,v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
        });
    }

    @Test
    public void summaryFactory() throws Exception {
        List<Car> cars = MockData.getCars();
        Map<String, DoubleSummaryStatistics> collect = cars.stream()
                .collect(Collectors.groupingBy(Car::getMake, Collectors.summarizingDouble(Car::getPrice)));

        DoubleSummaryStatistics naData = collect.get("Toyota"); // one of keys the maker
        System.out.printf("""
                        No of cars: %d
                        Highest car: %f
                        Average car cost: %f
                        Total traded amt: %f
                        """,
                naData.getCount(), naData.getMax(), naData.getAverage(), naData.getSum());

        //System.out.println(collect);

    }

    @Test
    public void collectingAndThen() throws Exception {
        List<Car> cars = MockData.getCars();

        //solution 1
        Map<String, Optional<Car>> collect = cars.stream()
                .limit(5)
                .collect(Collectors.groupingBy(
                        Car::getMake, Collectors.maxBy(Comparator.comparing(Car::getPrice))
                ));

        collect.forEach((k,v) -> {
            System.out.println(k);
            System.out.println(v);
        });
        System.out.println();

        // solution 2
        Map<String, Car> collect1 = cars.stream()
                .limit(5)
                .collect(Collectors.groupingBy(
                        Car::getMake, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Car::getPrice)), Optional::get)
                ));
                collect1.forEach((k,v) -> {
            System.out.println(k);
            System.out.println(v);
        });

    }

    @Test
    public void mapping() throws Exception {
        List<Car> cars = MockData.getCars();
        Map<String, Set<Double>> collect = cars.stream()
                .limit(5)
                .collect(Collectors.groupingBy(
                        Car::getMake, Collectors.mapping(Car::getPrice, Collectors.toSet())
                ));
        collect.forEach((k,v) -> {
            System.out.println(k);
            System.out.println(v);
        });

    }
}

