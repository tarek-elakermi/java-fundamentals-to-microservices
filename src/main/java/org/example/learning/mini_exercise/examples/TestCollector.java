package org.example.learning.mini_exercise.examples;

import org.example.learning.mini_exercise.datamocking.MockData;
import org.example.learning.mini_exercise.entities.Person;
import org.example.learning.mini_exercise.entities.ToXMLCollector;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestCollector {

    final String xmlTemplate =
            """
           
             <person pid= '%s'>\t
             <firestName>%s</firestName>\t
             <lastName>%s</lastName>\t
             <email>%s</email>\t
             <gender>%s</gender>\t
             <age>%s</age>\t
             </person>
           """;


    @Test
    public void toXMLTemplate() throws IOException {
        List<Person> people = MockData.getPeople();

        String collect = people.parallelStream()
                .limit(3)
                .collect(new ToXMLCollector());

        System.out.println(collect);
    }



    @Test
    public void toXMLTemplateByStaticCollectorMethods() throws IOException {
        List<Person> people = MockData.getPeople();

        String collect = people.parallelStream()
                .limit(3)
                .collect(
                        Collector.of(
                                StringBuffer::new, // Supplier
                                (sb, e) -> sb.append(  // BiConsumer
                                        String.format(
                                                xmlTemplate,
                                                e.getId(),
                                                e.getFirstName(),
                                                e.getLastName(),
                                                e.getEmail(),
                                                e.getGender(),
                                                e.getAge())
                                ),
                                (sb1, sb2) -> sb1.append(sb2.toString()), // BinaryOperator
                                sb -> sb.insert(0, "<employees>") // Function
                                        .append("\n</employees>").toString(),
                                Collector.Characteristics.CONCURRENT) // Characteristics
                );

        System.out.println(collect);
    }

    // Collect to other Collection other than List or HashSet
    @Test
    public void collectToNewCollection() throws IOException {
        List<Person> people = MockData.getPeople();
        TreeSet<String> collect = people.stream()
                .limit(10)
                .map(Person::getFirstName)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println(collect);

    }

    // this collect call may raise an IllegalStateException if the key is duplicated
    // in the data source or applying the keyMapper will make a duplication
    // so i changed 2 person first name to be the same name but one is lowerCase
    @Test
    public void collectToMap() throws IOException {
        List<Person> people = MockData.getPeople();
        Map<String, Integer> collect = people.stream()
                .sorted(Comparator.comparing(Person::getId))
                .limit(5)
                .map(Person::getFirstName)
                .collect(Collectors.toMap(String::toUpperCase, String::length));
        System.out.println(collect);
    }

    // try to avoid the duplicate key
    // don't forget to make a duplication in data set to test this method
    @Test
    public void collectToMapNonDuplicate() throws IOException {
        List<Person> people = MockData.getPeople();
        Map<String, Integer> collect = people.stream()
                .sorted(Comparator.comparing(Person::getId))
                .limit(5)
                .map(Person::getFirstName)
                .collect(Collectors.toMap(String::toUpperCase, String::length, (k1,k2) -> k1));
        System.out.println(collect);
    }
}
