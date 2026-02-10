package com.spring.boot.jpa_docs.dao;

import com.spring.boot.jpa_docs.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDAO extends JpaRepository<Student, Long> {
}
