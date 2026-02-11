package com.spring.data.deliverable2_librarySystem.repositories;

import com.spring.data.deliverable2_librarySystem.entities.Loan;
import com.spring.data.deliverable2_librarySystem.entities.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByMemberId(Long id);
    List<Loan> findByBookId(Long bookId);
    List<Loan> findByStatus(LoanStatus status);
    List<Loan> findByBookIdAndStatus(Long bookId, LoanStatus status);

    Optional<Loan> findByIdAndMemberId(Long id, Long memberId);
}
