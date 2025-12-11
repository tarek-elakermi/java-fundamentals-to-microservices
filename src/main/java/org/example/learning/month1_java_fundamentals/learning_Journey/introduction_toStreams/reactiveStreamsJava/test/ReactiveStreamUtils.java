package org.example.learning.month1_java_fundamentals.learning_Journey.introduction_toStreams.reactiveStreamsJava.test;

import org.example.learning.month1_java_fundamentals.learning_Journey.parallelStreams.datamocking.Mock;
import org.example.learning.month1_java_fundamentals.learning_Journey.parallelStreams.entities.Student;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.util.stream.Stream;

public class ReactiveStreamUtils {

    public static Flux<String> getReactiveStudentNames() throws IOException {
        Stream<String> stream = Mock.getStudents()
                .stream()
                .limit(100)
                .map(Student::getFirstName);
        return null;
    }

    public static Flux<String> worldReactiveStream(){

        return Flux.just("fun","learning","selenium","express")
                .skip(2)
                .delayElements(Duration.ofMillis(2000));


    }

}
