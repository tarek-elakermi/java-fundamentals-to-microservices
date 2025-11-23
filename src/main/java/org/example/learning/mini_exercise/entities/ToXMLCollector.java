package org.example.learning.mini_exercise.entities;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToXMLCollector implements Collector<Person, StringBuffer, String> {

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



    @Override
    public Supplier<StringBuffer> supplier() {
        return StringBuffer::new;
    }

    @Override
    public BiConsumer<StringBuffer, Person> accumulator() {
        return (sb, p) -> sb.append(
                String.format(
                        xmlTemplate,
                        p.getId(),
                        p.getFirstName(),
                        p.getLastName(),
                        p.getEmail(),
                        p.getGender(),
                        p.getAge())
        );
    }

    @Override
    public BinaryOperator<StringBuffer> combiner() {
        return (sb1,sb2)-> sb1.append(sb2.toString());
    }

    @Override
    public Function<StringBuffer, String> finisher() {
        return sb-> String.format(
                "<persons> %s \n </persons>", sb.toString()
        );
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}
