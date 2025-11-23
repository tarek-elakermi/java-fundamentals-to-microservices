package org.example.learning.mini_exercise.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Car;
import org.example.learning.mini_exercise.entities.Person;
import org.junit.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;


public class Sorting {

    private static final ObjectMapper mapper = new ObjectMapper();

    // 1 - Test sorting a stream of simple elements (Strings, ints, etc.)
    //     Example goal: sort people by their first names alphabetically.
    @Test
    public void sortingSteamOfElements() throws IOException {
        List<Person> people = MockData.getPeople();
        List<Person> list = people.stream()
                .sorted(Comparator.comparing(Person::getFirstName)).toList();
        list.forEach(p -> System.out.println(p.getFirstName()));
    }

    // 2 - Test sorting simple elements in reverse order.
    //     Example goal: sort people by their first names in descending order.
    @Test
    public void sortingSteamOfElementsReverse() throws IOException {
        List<Person> people = MockData.getPeople();
        List<Person> list = people.stream()
                .sorted(Comparator.comparing(Person::getFirstName).reversed()).toList();
        list.forEach(p -> System.out.println(p.getFirstName()));
    }

    // 3 - Test sorting complex objects using Comparator.
    //     Example goal: sort Person objects by age or by lastName.
    @Test
    public void sortingSteamOfObjets() throws IOException {
        List<Person> people = MockData.getPeople();
        List<Person> list = people.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .toList();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        list.forEach(s -> {
            try {
                String json = mapper.writeValueAsString(s);
                System.out.println(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });


    }

    // 4 - Test sorting objects, filtering, and limiting the result.
    //     Example goal: get the top 10 most expensive blue cars ordered by price (descending).
    @Test
    public void topTenMostExpensiveBlueCars() throws IOException {

        List<Car> cars = MockData.getCars();
        List<Car> list = cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase("blue"))
                .sorted(Comparator.comparing(Car::getPrice, Comparator.reverseOrder()))
                .limit(10)
                .toList();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        list.forEach(s -> {
            try {
                String json = mapper.writeValueAsString(s);
                System.out.println(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void compareNatural() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Person> people = MockData.getPeople();
        List<Person> list = people.stream()
                .sorted().toList();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        list.forEach(s -> {
            try {
                String json = mapper.writeValueAsString(s);
                System.out.println(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void comparePrimitive(){
        Stream.of(-10,31,16,3,-5,2)
                .sorted(Comparator.comparingInt(i -> Math.abs(i)))
                .forEach(System.out::println);

    }

    @Test
    public void thenCompare() throws IOException {
        List<Person> people = MockData.getPeople();
        people.stream()
                .sorted(Comparator.comparing(Person::getId).thenComparing(Person::getFirstName))
                .limit(5).forEach(System.out::println);


    }

}

