package com.spring.boot.jpa_docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring.boot.jpa_docs.config.AppConfig;
import com.spring.boot.jpa_docs.dao.StudentDAO;
import com.spring.boot.jpa_docs.entities.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaDocsApplication {

	public static void main(String[] args) {
//        AnnotationConfigApplicationContext container =
//                new AnnotationConfigApplicationContext(AppConfig.class);

        ApplicationContext container = SpringApplication.run(JpaDocsApplication.class, args);



        StudentDAO studentDao = container.getBean("studentDAO",StudentDAO.class);

        Student student = new Student();
        student.setStudentName("tarek");
        student.setMobile(4523);
        student.setAddress("regueb");
        student.setEmail("tarek@email.com");
        student.setScore(60);
        student.setBirthDate(new Date());
        studentDao.save(student);
        //System.out.println(studentDao.getReferenceById(1L));

        List<Student> allStudent = studentDao.findAll();
        allStudent.forEach(System.out::println);

    }


    /** JSON Mapper Bean**/
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // register Java 8 Date/Time module
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // optional: serialize dates as ISO strings
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
    /********/



    /*** Insertion using a manual bean ****/
//    @Bean
//    CommandLineRunner runner(ObjectMapper objectMapper) {
//        return args -> {
//            Person p = personRepository.findByEmail("tarek.benali@mail.com");
//            String json = objectMapper.writeValueAsString(p);
//            //System.out.println(json);
//            System.out.println("*****************");
//            List<Person> personList = personRepository.findByNameEndsWith("Trabelsi");
//            String json1 = objectMapper.writeValueAsString(personList);
//            //System.out.println(json1);
//            System.out.println("*****************");
//            Page<Person> people = personRepository.findByName("Tarek Ben Ali", PageRequest.of(0, 2));
//            String json2 = objectMapper.writeValueAsString(people);
//            System.out.println(json2);
//
//
//            System.out.println("***** For student *****");
//                  //            studentRepository.save(new Student(null,"Tarek","tarek@gmail.com",new Date(), 45));
////            studentRepository.save(new Student(null,"Elakemri","elakemri@gmail.com",new Date(), 60));
////            studentRepository.save(new Student(null,"Iman","iman@gmail.com",new Date(), 60));
////            studentRepository.findAll().forEach(
////                    std -> {
////                        try {
////                            String jsonStd = objectMapper.writeValueAsString(std);
////                            System.out.println(jsonStd);
////                        } catch (JsonProcessingException e) {
////                            throw new RuntimeException(e);
////                        }
////                    }
////            );
////
////            Student std = studentRepository.findById(1L).orElseThrow();
////            System.out.println("*** retrieve *****");
////            String jsonStd = objectMapper.writeValueAsString(std);
////            System.out.println(jsonStd);
////
////            System.out.println("***************");
////            List<Student> std1 = studentRepository.findByScore(60);
////            std1.forEach(
////                    s -> {
////                        try {
////                            String st = objectMapper.writeValueAsString(s);
////                            System.out.println(st);
////                        } catch (JsonProcessingException e) {
////                            throw new RuntimeException(e);
////                        }
////
////                    }
////            );
//            /********************/
//
//        };
//    }


    /**********/
}
