package org.example.learning.mini_exercise.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Car;
import org.example.learning.mini_exercise.entities.Person;
import org.example.learning.mini_exercise.entities.PersonDTO;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TransformationsMapAndReduce {


    final ObjectMapper mapper = new ObjectMapper();

    // 1 - Test map(): transform each element into another form.
    //     Example goal: map cars to their model names or map numbers to squared values.
    @Test
    public void yourFirstTransformationWithMap() throws IOException {
        List<Car> cars = MockData.getCars();
        List<String> list = cars.stream()
                .limit(10)
                .map(Car::getModel)
                .toList();
        System.out.println(list);

    }

    // 2 - Test mapToDouble(): convert a field to double and compute a result.
    //     Example goal: map each car's price to double and calculate the average price.
    @Test
    public void mapToDoubleAndFindAverageCarPrice() throws IOException {
        List<Car> cars = MockData.getCars();
        double average = cars.stream()
                .limit(10)
                .mapToDouble(Car::getPrice)
                .average().orElse(0);
        System.out.println(average);
    }

    // 3 - Test reduce(): combine all elements to a single result.
    //     Example goal: reduce integers to their total sum.
    @Test
    public void reduce() {
        int[] integers = {1, 2, 3, 4, 99, 100, 121, 1302, 199};
        int sum = Arrays.stream(integers)
                .reduce(Integer::sum).orElse(0);
        System.out.println(sum);
    }

    // 4 - Normalize person names (trim + lowercase + remove duplicates).
    @Test
    public void normalizePersonNames() throws IOException {
        List<Person> people = MockData.getPeople();
        List<String> list = people.stream()
                .limit(20)
                .map(p -> p.getFirstName().trim().toLowerCase())
                .distinct()
                .toList();
        System.out.println(list);
    }

    // 5 - Map Person → PersonDTO using map().
    @Test
    public void mapPersonToDTOs() throws IOException {
        List<Person> people = MockData.getPeople();
        List<PersonDTO> list = people.stream()
                .limit(5)
                .map(PersonDTO::map)
                .toList();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(list);
        System.out.println(json);
    }

    // 6 - Find the most expensive car using reduce().
    @Test
    public void mostExpensiveCar() throws IOException {
        List<Car> cars = MockData.getCars();
        Car car = cars.stream()
                .reduce((c1, c2) -> c1.getPrice() > c2.getPrice() ? c1 : c2)
                .orElse(null);

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(car);
        //System.out.println(json);

        // using max
        Car car1 = cars.stream()
                .max(Comparator.comparing(Car::getPrice))
                .orElse(null);

        String json1 = mapper.writeValueAsString(car1);
        //System.out.println(json1);

        // Using the summarize feature
        double max = cars.stream()
                .collect(Collectors.summarizingDouble(Car::getPrice))
                .getMax();
        Car car2 = cars.stream()
                .filter(c -> c.getPrice() == max)
                .findFirst()
                .orElse(null);

        String json2 = mapper.writeValueAsString(car2);
        //System.out.println(json2);

        // or
        DoubleSummaryStatistics doubleSummaryStatistics = cars.stream()
                .collect(Collectors.summarizingDouble(Car::getPrice));
        Car car3 = cars.stream()
                .filter(c -> c.getPrice() == doubleSummaryStatistics.getMax())
                .findFirst().orElse(null);
        String json3 = mapper.writeValueAsString(car3);
        System.out.println(json3);


    }

    // 7 - Build a CSV string from person names using reduce().
    @Test
    public void concatenatePersonNames() throws IOException {
        List<Person> people = MockData.getPeople();
        String str = people.stream()
                .limit(10)
                .map(p -> p.getFirstName() + " " + p.getLastName())
                .reduce((p1, p2) -> p1 + "," + p2)
                .orElse(null);
        System.out.println(str);
        Map<String, Person> collect = people.stream()
                .limit(10)
                .collect(Collectors.toMap(
                        p -> p.getFirstName() + " " + p.getLastName(),
                        p -> p
                ));
        collect.forEach((k,v) -> {
            System.out.println(k + " ==> his complete person is:");
            try {
                String json3 = mapper.writeValueAsString(v);
                System.out.println(json3);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        // or simply
        String collect1 = people.stream()
                .limit(10)
                .map(p -> p.getFirstName() + " " + p.getLastName())
                .collect(Collectors.joining(",", "[", "]"));

        System.out.println(collect1);
    }

    // 8 - Compute factorial using IntStream + reduce().
    @Test
    public void factorialWithReduce() {
        int n = 4;
        int reduce = IntStream.rangeClosed(1, n)
                .reduce(1, (x, y) -> x * y);
        System.out.println(reduce);
    }

    // 9 - Extract email domains from people.
    @Test
    public void extractEmailDomains() throws IOException {
        List<Person> people = MockData.getPeople();
        List<String> list = people.stream()
                .limit(10)
                .map(p -> p.getEmail().substring(p.getEmail().indexOf("@") +1 ))
                .toList();
        System.out.println(list);
    }

    // 10 - Map cars to a list of discounted prices.
    @Test
    public void discountedPrices() throws IOException {
        List<Car> cars = MockData.getCars();
        double discountRate = 1 - 0.20;
        Map<Double, Double> collect = cars.stream()
                .limit(10)
                .collect(Collectors.toMap(
                        Car::getPrice,
                        car -> Math.round(car.getPrice() * discountRate * 1000.0) / 1000.0
                ));
        System.out.println(collect);
    }

    // 11 - Reduce car list to count total id sum.
    @Test
    public void totalSumId() throws IOException {
        List<Car> cars = MockData.getCars();
        Integer reduce = cars.stream()
                .map(Car::getId)
                .reduce(0, Integer::sum);
        System.out.println(reduce);

    }

    // 12 - Map persons to their ages.
    @Test
    public void mapToAges() throws IOException {
        List<Person> people = MockData.getPeople();
        List<Integer> list = people.stream()
                .limit(10)
                .map(Person::getAge)
                .toList();

        System.out.println(list);
    }

    // 13 - Reduce people to find the oldest person.
    @Test
    public void oldestPerson() throws IOException {
        List<Person> people = MockData.getPeople();
        Person person = people.stream()
                .reduce((p1, p2) -> {
                    Assert.assertNotNull(p1);
                    if (p1.getAge() > p2.getAge()) {
                        return p1;
                    }
                    return p2;
                })
                .orElse(null);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(person);
        System.out.println(json);
    }

    // 14 - Map cars to a sorted list of brands (distinct).
    @Test
    public void sortedBrands() throws IOException {
        List<Car> cars = MockData.getCars();
        List<String> list = cars.stream()
                .limit(10)
                .sorted(Comparator.comparing(Car::getMake))
                .map(Car::getMake)
                .distinct()
                .toList();
        System.out.println(list);
    }

    // 15 - Reduce to find the longest person name.
    @Test
    public void longestPersonName() throws IOException {
        List<Person> people = MockData.getPeople();
        String s = people.stream()
                .limit(10)
                .map(Person::getFirstName)
                .reduce((p1, p2) -> p1.length() > p2.length() ? p1 : p2)
                .orElse(null);

        System.out.println(s);
    }

    // 16 - Map persons to initials.
    @Test
    public void personInitials() throws IOException {
        List<Person> people = MockData.getPeople();
//        List<String> list = people.stream()
//                .limit(10)
//                .map(p -> p.getFirstName().substring(0, 1))
//                .toList();
//        System.out.println(list);

        Map<String, String> collect = people.stream()
                .limit(10)
                .collect(Collectors.toMap(
                        Person::getFirstName,
                        car -> car.getFirstName().substring(0, 1)
                ));
        System.out.println(collect);

    }

    // 17 - Reduce cars to compute total value (sum(price)).
    @Test
    public void totalCarValue() throws IOException {
        List<Car> cars = MockData.getCars();
        Double reduce = cars.stream()
                .limit(10)
                .map(Car::getPrice)
                .reduce(Double::sum).orElse(0.0);
        System.out.println(reduce);
    }

    // 18 - Map cars to power-to-weight ratio.
    @Test
    public void powerToWeightRatio() throws IOException {
        List<Car> cars = MockData.getCars();
    }

    // 19 - Use reduce() to find the cheapest car.
    @Test
    public void cheapestCar() throws IOException {
        List<Car> cars = MockData.getCars();
        Car car = cars.stream()
                .reduce((c1, c2) -> c1.getPrice() > c2.getPrice() ? c2 : c1).orElse(null);
        System.out.println(car);

        // or
        double cheapestCar = cars.stream().map(Car::getPrice).reduce(Double::min).orElse(0.0);
        Car car1 = cars.stream()
                .filter(c -> c.getPrice() == cheapestCar)
                .findFirst().orElse(null);
        System.out.println(car1);

        // or using summaryStatistics
        double min = cars.stream().collect(Collectors.summarizingDouble(Car::getPrice)).getMin();
        Car car2 = cars.stream().filter(c -> c.getPrice() == min).findAny().orElse(null);
        System.out.println(car2);
    }

    // 20 - Reduce people list into a Map<Gender, Count>.
    @Test
    public void genderCountHistogram() throws IOException {
        List<Person> people = MockData.getPeople();
        Map<String, Long> collect = people.stream()
                .limit(10)
                .collect(Collectors.groupingBy(Person::getGender, Collectors.counting()));

        System.out.println(collect);


        HashMap<String, Long> reduce = people.stream()
                .limit(10)
                .reduce(
                        new HashMap<>(),   // identity: empty map
                        (map, person) -> {             // accumulator
                            map.merge(person.getGender(), 1L, Long::sum);
                            return map;
                        },
                        (map1, map2) -> {              // combiner (for parallel streams)
                            map2.forEach((k, v) -> map1.merge(k, v, Long::sum));
                            return map1;
                        }
                );

        System.out.println(reduce);
    }
}
