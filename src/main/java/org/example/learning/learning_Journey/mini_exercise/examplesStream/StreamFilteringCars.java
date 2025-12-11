package org.example.learning.learning_Journey.mini_exercise.examplesStream;

import org.example.learning.learning_Journey.mini_exercise.datamocking.MockData;
import org.example.learning.learning_Journey.mini_exercise.entities.Car;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public class StreamFilteringCars {


    // 1 - find cars list with price smaller than 20k and with color Yellow
    @Test
    public void filter() throws IOException {
        List<Car> cars = MockData.getCars();
        cars.stream()
                .filter(car -> car.getPrice() < 20_000)
                .filter(car -> car.getColor().equalsIgnoreCase("yellow"))
                .forEachOrdered(System.out::println);
    }


    // 1 - Test dropWhile: skip elements from the stream while the condition is true,
    //     then return the rest. Example goal: drop numbers while they are < 5.
    @Test
    public void dropWhile(){
//        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12).filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        System.out.println("using dropWhile");
        Stream.of(2, 4, 6, 8, 9, 10, 12).dropWhile(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
    }

    // 2 - Test takeWhile: take elements from the stream while the condition is true,
    //     then stop. Example goal: take numbers while they are < 5.
    @Test
    public void takeWhile(){
        // using filter
        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12).filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();
        System.out.println("using take while");
        Stream.of(2, 4, 6, 8, 9, 10, 12).takeWhile(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
    }


    // 3 - Test findFirst: find the first element in the stream.
    //     Example goal: get the first even number from the array.
    @Test
    public void findFirst(){
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int asInt = Arrays.stream(numbers).filter(n -> n % 2 == 0).findFirst().orElse(0);
        System.out.println(asInt);
    }

    // 4 - Test findAny: find any element matching a condition (used often with parallel streams).
    //     Example goal: find any number equal to 9 in the array.
    @Test
    public void findAny(){
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10};
        int i = Arrays.stream(numbers).filter(n -> n == 9).findAny().orElse(0);
        System.out.println(i);
    }

    // 5 - Test allMatch: check if all elements match a condition.
    //     Example goal: verify all numbers are even.
    @Test
    public void allMatch(){
        int[] even = {2, 4, 6, 8, 10};
        boolean b = Arrays.stream(even).allMatch(n -> n % 2 == 0);
        System.out.println(b);
    }


    // 6 - Test anyMatch: check if at least one element matches a condition.
    //     Example goal: check if the array contains at least one odd number.
    @Test
    public void anyMatch(){
        int[] evenAndOneOdd = {2, 4, 6, 8, 10, 11};
        boolean res = Arrays.stream(evenAndOneOdd).anyMatch(n -> n % 2 != 0);
        System.out.println(res);
    }



    // 1 - Basic filters with the mock data

    //Filter cars with price > 50_000
    @Test
    public void priceMoreThan() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> list = cars.stream().filter(car -> car.getPrice() > 50_000)
                .toList();
        list.forEach(System.out::println);

    }

    //Filter cars with year < 2000
    @Test
    public void yearLessThan() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> lessThan = cars.stream().filter(car -> car.getYear() < 2000)
                .toList();
        long count = lessThan.size();
        System.out.println(count);
        lessThan.forEach(System.out::println);
    }

    //Filter cars with make "Toyota"
    @Test
    public void make() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> makeCars = cars.stream().filter(car -> car.getMake().equalsIgnoreCase("toyota"))
                .toList();
        makeCars.forEach(System.out::println);
//        System.out.println(makeCars.get(1).getMake() == makeCars.get(2).getMake());

    }

    //Filter cars with model starting with "M"
    @Test
    public void startWithLetter() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> carsStartWith = cars.stream().filter(car -> car.getModel().startsWith("M"))
                .toList();
        System.out.println(carsStartWith.size());
        carsStartWith.forEach(System.out::println);

    }

    //Filter cars with id > 100
    @Test
    public void cardWithId() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> carsIds = cars.stream()
                .filter(car -> car.getId() < 100)
                .toList();
        carsIds.forEach(System.out::println);
    }

    //Filter cars whose color is not "Blue"
    @Test
    public void carWithColorNot() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> carsWithColorNot = cars.stream()
                .filter(car -> !car.getColor().equalsIgnoreCase("Blue"))
                .toList();
        System.out.println(carsWithColorNot.size());
        carsWithColorNot.forEach(System.out::println);

    }

    // 2 - Combined filters with the mock data f() --> means method filter

    // Filter Ford cars with year > 2005
    @Test
    public void filter1() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f1 = cars.stream()
                .filter(car -> car.getMake().equalsIgnoreCase("Ford"))
                .filter(car -> car.getYear() > 2005)
                .toList();
        System.out.println(f1.size());
        f1.forEach(System.out::println);
    }
    // Filter Toyota cars with price < 30_000
    @Test
    public void filter2() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f2 = cars.stream()
                .filter(car -> car.getMake().equalsIgnoreCase("Toyota"))
                .filter(cp -> cp.getPrice() < 30_000)
                .toList();
        System.out.println(f2.size());
        f2.forEach(System.out::println);
    }
    // Filter cars with year between 2000 and 2010
    @Test
    public void filter3() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f3 = cars.stream()
                .filter(car -> car.getYear() >= 2_000 && car.getYear() <= 2_010)
                .toList();
        System.out.println(f3.size());
        f3.forEach(System.out::println);
    }
    // Filter cars with price between 20_000 and 60_000
    @Test
    public void filter4() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f4 = cars.stream()
                .filter(carp -> carp.getPrice() >= 20_000)
                .filter(carp -> carp.getPrice() <= 60_000)
                .toList();
        System.out.println(f4.size());
        f4.forEach(System.out::println);
    }
    // Filter blue cars made before 2005
    @Test
    public void filter5() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f5 = cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase("blue") && car.getYear() < 2005)
                .toList();
        System.out.println(f5.size());
        f5.forEach(System.out::println);
    }
    // Filter Lexus cars with color "Pink"
    @Test
    public void filter6() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f6 = cars.stream()
                .filter(car -> car.getMake().equalsIgnoreCase("Lexus") && car.getColor().equalsIgnoreCase("pink"))
                .toList();
        System.out.println(f6.size());
        f6.forEach(System.out::println);
    }
    // Filter Audi cars with price > 40_000 and year < 2010
    @Test
    public void filter7() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f7 = cars.stream()
                .filter(car -> car.getMake().equalsIgnoreCase("audi") && car.getPrice() <= 40_000)
                .filter(car -> car.getYear() < 2010)
                .toList();
        System.out.println(f7.size());
        f7.forEach(System.out::println);
    }
    // Filter cars with make "BMW" or "Mercedes-Benz"
    @Test
    public void filter8() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f8 = cars.stream()
                .filter(car -> car.getMake().equalsIgnoreCase("bmw") || car.getMake().equalsIgnoreCase("Mercedes-Benz"))
                .toList();
        System.out.println(f8.size());
        f8.forEach(System.out::println);
    }
    // Filter Mitsubishi cars whose model contains "Gal"
    @Test
    public void filter9() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f9 = cars.stream()
                .filter(car -> car.getModel().contains("Gal"))
                .toList();
        System.out.println(f9.size());
        f9.forEach(System.out::println);
    }
    // Filter cars with price < 10_000 or year < 1990
    @Test
    public void filter10() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f10 = cars.stream()
                .filter(car -> car.getPrice() < 10_000 || car.getYear() < 1990 )
                .toList();
        System.out.println(f10.size());
        f10.forEach(System.out::println);
    }
    // Filter cars with model length > 10 characters
    @Test
    public void filter11() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f11 = cars.stream()
                .filter(car -> car.getModel().length() > 10)
                .toList();
        System.out.println(f11.size());
        f11.forEach(System.out::println);
    }
    // Filter cars where color is "Teal" AND year is 1995
     @Test
    public void filter12() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f12 = cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase("Teal") && car.getYear() == 1995)
                .toList();
         System.out.println(f12.size());
         f12.forEach(System.out::println);
    }


    // 3 - Advanced filters

    // Filter cars where make starts with "C"
    @Test
    public void af1() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f = cars.stream()
                .filter(car -> car.getMake().startsWith("C"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);

    }

    // Filter cars where model ends with "X"
    @Test
    public void af2() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f = cars.stream()
                .filter(car -> car.getMake().endsWith("W"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter cars whose price string ends with ".54"
    @Test
    public void af3() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f = cars.stream()
                .filter(car -> String.valueOf(car.getPrice()).endsWith(".54"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter cars with color in ("Red", "Blue", "Green")
    @Test
    public void af4() throws IOException {
        List<Car> cars = MockData.getCars();
        List<String> bool =  List.of("Red", "Blue", "Green");
        List<Car> f = cars.stream()
                .filter(car -> bool.contains(car.getColor()))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter cars whose make is NOT ("Ford", "Toyota")
    @Test
    public void af5() throws IOException {
        List<Car> cars = MockData.getCars();
        List<String> notPool = List.of("Ford", "Toyota");
        List<Car> f = cars.stream()
                .filter(car -> !notPool.contains(car.getMake()))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter cars with year % 2 == 0 (even year)
    @Test
    public void af6() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f = cars.stream()
                .filter(car -> car.getYear() % 2 == 0)
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }

    // Filter cars with id divisible by 5
    @Test
    public void af8() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f = cars.stream()
                .filter(car -> car.getId() % 5 == 0)
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter cars where model contains a space
    @Test
    public void af9() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f = cars.stream()
                .filter(car -> car.getMake().contains(" "))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter cars whose color contains the letter 'u'
     @Test
    public void af10() throws IOException {
        List<Car> cars = MockData.getCars();
        List<Car> f = cars.stream()
                .filter(car -> car.getColor().contains("u"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }

    @Test
    public void af11() throws IOException {
        List<Car> cars = MockData.getCars();

        Optional<String> opt = cars.stream()
                .filter(car -> "Blue".equalsIgnoreCase(car.getColor()))
                .findFirst()
                .map(Car::getModel);

        System.out.println(opt);
    }



}
