package com.spring.data.deliverable2_librarySystem.controllers;


import com.spring.data.deliverable2_librarySystem.entities.Loan;
import com.spring.data.deliverable2_librarySystem.services.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<Loan> borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        return ResponseEntity.ok(loanService.borrowBook(memberId, bookId));
    }

    @PostMapping("/return/{loanId}/{memberId}")
    public ResponseEntity<Loan> returnBook(@PathVariable Long loanId, @PathVariable Long memberId) {
        return ResponseEntity.ok(loanService.returnBook(loanId, memberId));
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Loan>> getActiveLoans() {
        return ResponseEntity.ok(loanService.getActiveLoans());
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Loan>> getMemberLoans(@PathVariable Long memberId) {
        return ResponseEntity.ok(loanService.getMemberLoans(memberId));
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Loan>> getOverdueLoans() {
        return ResponseEntity.ok(loanService.getOverdueLoans());
    }



}
