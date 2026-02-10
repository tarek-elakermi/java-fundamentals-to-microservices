package com.spring.data.deliverable2_librarySystem.services;


import com.spring.data.deliverable2_librarySystem.dao.ReportDao;
import com.spring.data.deliverable2_librarySystem.entities.*;
import com.spring.data.deliverable2_librarySystem.repositories.BookRepository;
import com.spring.data.deliverable2_librarySystem.repositories.LoanRepository;
import com.spring.data.deliverable2_librarySystem.repositories.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final ReportDao reportDao;


    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, MemberRepository memberRepository, ReportDao reportDao) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.reportDao = reportDao;
    }

    @Transactional
    public Loan borrowBook(Long memberId, Long bookId) {

        // get entities
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // check if book is available
        boolean bookAlreadyBorrowed = !loanRepository.findByBookIdAndStatus(
                bookId, LoanStatus.ACTIVE).isEmpty();

        if (bookAlreadyBorrowed) {
            throw new RuntimeException("Book is already borrowed");
        }

        if (    member.getStatus().equals(MembershipStatus.SUSPENDED)
             || member.getStatus().equals(MembershipStatus.EXPIRED)) {
            throw new RuntimeException("Member can't do a loan");
        }

        // create the loan
        Loan loan = new Loan(book,member);
        Loan savedLoan = loanRepository.save(loan);

        //update the h2 reporting database
        reportDao.incrementBorrowCount(bookId, book.getTitle());

        return savedLoan;
    }

    @Transactional
    public Loan returnBook(Long loanId, Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.returnBook();

        if (loan.isOverdue()){
            member.setStatus(MembershipStatus.SUSPENDED);
            memberRepository.save(member);
        }



        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<Loan> getActiveLoans() {
        return loanRepository.findByStatus(LoanStatus.ACTIVE);
    }

    public List<Loan> getMemberLoans(Long memberId) {
        return loanRepository.findByMemberId(memberId);
    }

    public List<Loan> getOverdueLoans() {
        List<Loan> activeLoans = loanRepository.findByStatus(LoanStatus.ACTIVE);
        return activeLoans.stream()
                .filter(loan -> LocalDate.now().isAfter(loan.getDueDate()))
                .toList();
    }
















}
