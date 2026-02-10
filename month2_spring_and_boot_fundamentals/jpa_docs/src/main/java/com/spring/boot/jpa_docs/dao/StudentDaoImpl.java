package com.spring.boot.jpa_docs.dao;

import com.spring.boot.jpa_docs.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Repository
//public class StudentDaoImpl {
//
//    //@PersistenceContext(unitName = "mysqldb")
//    @Autowired
//    EntityManager entityManager;
//
//
//    @Transactional
//    //@Override
//    public void saveStudent(Student std) {
//        entityManager.persist(std);
//        System.out.println("Record inserted");
//    }
//
//    public List<Student> findAllStudent() {
//        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);
//
//        return query.getResultList();
//    }
//}
