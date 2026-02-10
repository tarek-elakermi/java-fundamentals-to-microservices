package com.spring.data.deliverable2_librarySystem.repositories;

import com.spring.data.deliverable2_librarySystem.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
