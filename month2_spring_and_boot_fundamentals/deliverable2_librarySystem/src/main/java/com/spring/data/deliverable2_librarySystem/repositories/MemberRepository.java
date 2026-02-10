package com.spring.data.deliverable2_librarySystem.repositories;

import com.spring.data.deliverable2_librarySystem.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByEmail(String email);
    List<Member> findByLastName(String lastName);
}
