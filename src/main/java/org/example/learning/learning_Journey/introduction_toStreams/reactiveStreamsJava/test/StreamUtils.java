package org.example.learning.learning_Journey.introduction_toStreams.reactiveStreamsJava.test;

import org.example.learning.learning_Journey.parallelStreams.datamocking.Mock;
import org.example.learning.learning_Journey.parallelStreams.entities.Student;

import java.io.IOException;
import java.util.stream.Stream;

public class StreamUtils {
    
    
    public static Stream<String> getStudentNames() throws IOException {
        Stream<String> stream = Mock.getStudents()
                .stream()
                .limit(100)
                .map(Student::getFirstName);
        return stream;
    }

    public static Stream<String> worldStream(){
        
        return  Stream.of("hello","world","fun","learning");
    }
}
