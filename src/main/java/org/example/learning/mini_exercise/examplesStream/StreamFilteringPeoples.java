package org.example.learning.mini_exercise.examplesStream;


import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Person;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class StreamFilteringPeoples {
    
    
    
    // 1 - Basic filters

    // Filter people older than 50
    @Test
    public void f1() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getAge() > 50)
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    
    // Filter female people
    @Test
    public void f2() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getGender().equalsIgnoreCase("Female"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter male people
    @Test
    public void f3() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getGender().equalsIgnoreCase("Male"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people aged < 10
    @Test
    public void f4() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getAge() < 10)
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose firstName starts with "A"
    @Test
    public void f5() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getFirstName().startsWith("A"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose lastName ends with "n"
    @Test
    public void f6() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getLastName().endsWith("n"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people with id > 100
    @Test
    public void f7() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getId() > 10)
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose email contains "google"
    @Test
    public void f8() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getEmail().contains("google"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    
    // 2 - Combined filters

    // Filter female people older than 70
    @Test
    public void f9() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getGender().equalsIgnoreCase("Female") && p.getAge() >= 70)
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter males between ages 30 and 40
    @Test
    public void f10() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getGender().equalsIgnoreCase("Male") && (p.getAge() > 30 && p.getAge() < 40))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people younger than 5 AND gender "Male"
    @Test
    public void f11() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getAge() < 5 && p.getGender().equalsIgnoreCase("Male"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people aged > 60 OR gender "Female"
    @Test
    public void f12() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getAge() > 60 || p.getGender().equalsIgnoreCase("female"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose firstName length > 6
    @Test
    public void f13() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getFirstName().length() > 6)
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose email ends with ".ru"
    @Test
    public void f14() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getEmail().endsWith(".ru"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose lastName contains "son"
    @Test
    public void f15() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getLastName().contains("son"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter males aged exactly 38
    @Test
    public void f16() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getAge() == 38)
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter females aged between 20 and 40
    @Test
    public void f17() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getGender().equalsIgnoreCase("female") && (p.getAge() > 20 && p.getAge() < 40))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people with firstName starting with vowel
    @Test
    public void f18() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getFirstName().matches("(?i)^[AEIOU].*"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people with age % 2 == 1 (odd ages)
    @Test
    public void f19() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getAge() % 2 == 1)
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose email provider is gmail.com (contains “gmail”)
    @Test
    public void f20() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getEmail().contains("gmail.com"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // 3 - Advanced filters

    // Filter people where full name length > 15
    @Test
    public void f21() throws IOException {
        List<Person> persons = MockData.getPeople();
        List<Person> f = persons.stream()
                .filter(p -> p.getFirstName().concat(" " + p.getLastName()).length() > 15)
                .toList();
        Function<Person, String> map = person -> person.getFirstName().concat(" " + person.getLastName());
//        List<String> f = persons.stream()
//                .map(map)
//                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose lastName starts with same letter as firstName
    @Test
    public void f22() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getLastName().startsWith(String.valueOf(p.getFirstName().charAt(0))))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose email username (before '@') has more than 8 characters
    @Test
    public void f23() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getEmail().substring(0, p.getEmail().indexOf('@')).length() > 8)
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose age is the oldest age in the list (you find max first)
    @Test
    public void f24() throws IOException {
        List<Person> cars = MockData.getPeople();
        int maxAge = cars.stream()
                .mapToInt(Person::getAge)
                .max().orElseThrow();

        List<Person> f = cars.stream()
                .filter(p -> p.getAge() == maxAge )
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose gender != "Male"
    @Test
    public void f25() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> !p.getGender().equalsIgnoreCase("male"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose firstName contains repeated letters
    @Test
    public void f26() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getFirstName().chars()
                        .distinct()
                        .count() < p.getFirstName().length())
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people with lastName containing an apostrophe '
    @Test
    public void f27() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getLastName().contains("'"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people with ids divisible by 3
    @Test
    public void f28() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getId() % 3 == 0)
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people whose age is a prime number
    @Test
    public void f29() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getAge() > 1)
                .filter(p -> IntStream.rangeClosed(2, (int) Math.sqrt(p.getAge())).allMatch(n -> p.getAge() % n != 0))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
    // Filter people with email domain ending in .edu
    @Test
    public void f30() throws IOException {
        List<Person> cars = MockData.getPeople();
        List<Person> f = cars.stream()
                .filter(p -> p.getEmail().endsWith(".edu"))
                .toList();
        System.out.println(f.size());
        f.forEach(System.out::println);
    }
     
}
