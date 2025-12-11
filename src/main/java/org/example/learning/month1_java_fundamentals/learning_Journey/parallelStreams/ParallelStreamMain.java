package org.example.learning.month1_java_fundamentals.learning_Journey.parallelStreams;

import org.example.learning.month1_java_fundamentals.learning_Journey.parallelStreams.datamocking.Mock;
import org.example.learning.month1_java_fundamentals.learning_Journey.parallelStreams.entities.Student;

import java.io.IOException;
import java.util.List;

public class ParallelStreamMain {

    public static void main(String[] args) throws IOException {
        /**** Data ***/
        List<Student> students = Mock.getStudents();
        /********************/

        /* See how many cores do I have */
        System.out.println("Am working with :" + Runtime.getRuntime().availableProcessors() + " Cores");
        System.out.println("*********************************************");

        /**** Example work on students data ****/
        //        long startTime = System.currentTimeMillis();
//        students.stream()
//                .limit(50)
//                .filter(std -> std.getAge() > 10)
//                .count();
//        long endTime = System.currentTimeMillis();
//        System.out.println("time taken by sequential stream is :" + (endTime - startTime)+ "ms");
//
//        System.out.println("*********************************************");
//        long startTime1 = System.currentTimeMillis();
//        students.stream()
//                .parallel()
//                .limit(50)
//                .filter(std -> std.getAge() > 10)
//                .count();
//        long endTime1 = System.currentTimeMillis();
//
//        System.out.println("time taken by parallel stream is :" + (endTime1 - startTime1)+ "ms");
        /***************/

        /***************/
        //        students
//                .stream()
//                .parallel()
//                .limit(12)
//                .filter(std -> {
//                    System.out.println("filter log : applying filter on student :"
//                    + std.getFirstName() + " processed by thread:" + Thread.currentThread().getName());
//                    return std.getAge() > 12;
//                })
//                .map(std -> {
//                    System.out.println("map log : applying map on student :"
//                            + std.getFirstName() + " processed by thread:" + Thread.currentThread().getName());
//                    return std.getFirstName();
//                })
//                .forEachOrdered(name -> {
//                    System.out.println("for each log : applying for each on student :"
//                            + name + " processed by thread:" + Thread.currentThread().getName());
//                    System.out.println(name);
//                });
        /***************/

        /*** Thread safety in parallel Stream *****/
        //        // ArrayList is not Thread Safe
//        ArrayList<Integer> listOfIntegers = new ArrayList<>();
//        // to make the lis thread safe we can use
//        //List<Integer> listOfIntegers = Collections.synchronizedList(new ArrayList<>());
//        // or use a thread safe collection like :
//         //List<Integer> listOfIntegers = new CopyOnWriteArrayList<>();
//
//        for (int i = 1; i <=5 ; i++) {
//            listOfIntegers.clear();
//            Stream.iterate(1, n -> n <= 1_000_000, n -> n + 1)
//                    .parallel()
//                    .forEachOrdered(listOfIntegers::add);
//            //System.out.println(listOfIntegers);
//            System.out.println(listOfIntegers.size());
//        }
        /***********/

        /*************/
        //        long startTime = System.currentTimeMillis();
//        long sum = LongStream.rangeClosed(0, 1_000_000_000)
//                //.sum();
//                        .reduce(0,(a,b) -> a + b );
//
//        System.out.println("sequential time taking to compute is:" + (System.currentTimeMillis() - startTime));
//        System.out.println(sum);
//
//
//
//        long startTime1 = System.currentTimeMillis();
//        long sum1 = LongStream.rangeClosed(0, 1_000_000_000)
//                .parallel()
//                //.sum();
//                        .reduce(0, Long::sum);
//
//        System.out.println("parallel time taking to compute is:" + (System.currentTimeMillis() - startTime1));
//        System.out.println(sum1);
        /*************/

        /*** Collect in ParallelStream ***/
        //ArrayList<Integer> integerList = new ArrayList<>(); // not thread safe

//        for (int i = 0; i <= 10 ; i++) {
//            List<Integer> integerList =
//                    Stream.iterate(0, no -> no + 1)
//                    .parallel()
//                    .limit(20)
//                    //.forEach(integerList::add);
//                    .collect(ArrayList::new,
//                            (list,b) -> list.add(b),
//                            (l1,l2) -> l1.addAll(l2)
//                            );
//            System.out.println(integerList);
//            System.out.println(integerList.size());
//        }
        /**************/

        /*** Filter and takewhile *****/

        students
                .stream()
                .limit(50)
                .parallel()
                .takeWhile(std -> { // stateful intermediate operation
                    System.out.println("processing student : " + std + "by thread :"
                    + Thread.currentThread().getName());
                    return std.getAge() >= 18;
                })
                .map(Student::getFirstName)
                .forEach(System.out::println);





    }
}
