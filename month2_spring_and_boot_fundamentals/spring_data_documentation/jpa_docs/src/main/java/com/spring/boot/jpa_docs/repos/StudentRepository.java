package com.spring.boot.jpa_docs.repos;

import com.spring.boot.jpa_docs.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByScore(int score);
}
