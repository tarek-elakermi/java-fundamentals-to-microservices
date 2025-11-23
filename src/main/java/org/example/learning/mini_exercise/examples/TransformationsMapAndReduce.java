package org.example.learning.mini_exercise.examples;

import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Car;
import org.example.learning.mini_exercise.entities.Person;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TransformationsMapAndReduce {

    // 1 - Test map(): transform each element into another form.
    //     Example goal: map cars to their model names or map numbers to squared values.
    @Test
    public void yourFirstTransformationWithMap() throws IOException {
        List<Car> cars = MockData.getCars();
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

    // 1 - Map cars to their model names.
    @Test
    public void mapCarToModel() throws IOException {
        List<Car> cars = MockData.getCars();
    }

    // 2 - Map cars to prices and compute average.
    @Test
    public void averageCarPrice() throws IOException {
        List<Car> cars = MockData.getCars();
    }

    // 3 - Reduce integers to a total sum.
    @Test
    public void reduceToSum() {
        int[] integers = {1, 2, 3, 4, 99, 100};
    }

    // 4 - Normalize person names (trim + lowercase + remove duplicates).
    @Test
    public void normalizePersonNames() throws IOException {
        List<Person> people = MockData.getPeople();
    }

    // 5 - Map Car → CarDTO using map().
    @Test
    public void mapCarsToDTOs() throws IOException {
        List<Car> cars = MockData.getCars();
    }

    // 6 - Find the most expensive car using reduce().
    @Test
    public void mostExpensiveCar() throws IOException {
        List<Car> cars = MockData.getCars();
    }

    // 7 - Build a CSV string from person names using reduce().
    @Test
    public void concatenatePersonNames() throws IOException {
        List<Person> people = MockData.getPeople();
    }

    // 8 - Compute factorial using IntStream + reduce().
    @Test
    public void factorialWithReduce() {
        int n = 7;
    }

    // 9 - Extract email domains from people.
    @Test
    public void extractEmailDomains() throws IOException {
        List<Person> people = MockData.getPeople();
    }

    // 10 - Map cars to a list of discounted prices.
    @Test
    public void discountedPrices() throws IOException {
        List<Car> cars = MockData.getCars();
    }

    // 11 - Reduce car list to count total mileage.
    @Test
    public void totalMileage() throws IOException {
        List<Car> cars = MockData.getCars();
    }

    // 12 - Map persons to their ages.
    @Test
    public void mapToAges() throws IOException {
        List<Person> people = MockData.getPeople();
    }

    // 13 - Reduce people to find the oldest person.
    @Test
    public void oldestPerson() throws IOException {
        List<Person> people = MockData.getPeople();
    }

    // 14 - Map cars to a sorted list of brands (distinct).
    @Test
    public void sortedBrands() throws IOException {
        List<Car> cars = MockData.getCars();
    }

    // 15 - Reduce to find the longest person name.
    @Test
    public void longestPersonName() throws IOException {
        List<Person> people = MockData.getPeople();
    }

    // 16 - Map persons to initials.
    @Test
    public void personInitials() throws IOException {
        List<Person> people = MockData.getPeople();
    }

    // 17 - Reduce cars to compute total value (sum(price)).
    @Test
    public void totalCarValue() throws IOException {
        List<Car> cars = MockData.getCars();
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
    }

    // 20 - Reduce people list into a Map<Gender, Count>.
    @Test
    public void genderCountHistogram() throws IOException {
        List<Person> people = MockData.getPeople();
    }
}
