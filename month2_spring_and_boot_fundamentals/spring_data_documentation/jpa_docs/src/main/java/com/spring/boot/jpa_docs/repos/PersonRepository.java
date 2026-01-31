package com.spring.boot.jpa_docs.repos;

import com.spring.boot.jpa_docs.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {

    Person save(Person person);
    Optional<Person> findById(long id);

//    @Query("""
//            Select p from Person p where p.email = ?1
//            """)
//    Person findByEmail(String emailAddress);


    @Query("""
            Select p from Person p where p.name like %?1
            """)
    List<Person> findByNameEndsWith(String name);


    @NativeQuery("""
            select * from person where email = ?1
            """)
    Person findByEmail(String email);


    @NativeQuery(value = """
            select * from Person where name = ?1
            """,
            countQuery = """
            select count(*) from Person where name = ?1
            """)
    Page<Person> findByName(String name, Pageable pageable);
}
