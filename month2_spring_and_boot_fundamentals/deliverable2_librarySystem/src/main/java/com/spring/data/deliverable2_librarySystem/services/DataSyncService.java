package com.spring.data.deliverable2_librarySystem.services;


import com.spring.data.deliverable2_librarySystem.dao.ReportDao;
import com.spring.data.deliverable2_librarySystem.entities.Book;
import com.spring.data.deliverable2_librarySystem.entities.Loan;
import com.spring.data.deliverable2_librarySystem.repositories.BookRepository;
import com.spring.data.deliverable2_librarySystem.repositories.LoanRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataSyncService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final ReportDao reportDao;

    public DataSyncService(BookRepository bookRepository, LoanRepository loanRepository, ReportDao reportDao) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.reportDao = reportDao;
        System.out.println(" 3/ => DataSyncService is created");
    }

    // run this method AFTER the bean is created using the @PostConstruct
    @PostConstruct
    public void syncOnStartup() {
        System.out.println("\n ===================================");
        System.out.println(" STARTING DATA SYNC: MySQL → H2");
        System.out.println(" ===================================");

        try {

            // 1- Calculate book borrow counts From MySql
            Map<Long, Integer> bookBorrowCounts = calculateBookBorrowCounts();

            // 2- Populate H2 with data
            populateH2WithBookData(bookBorrowCounts);

            // 3- logs
            logSyncSummary(bookBorrowCounts);

        } catch (Exception e) {
            System.err.println(" Error during data sync: " + e.getMessage());
            e.printStackTrace();
        }



    }

    // get all loans and count loans per book
    public Map<Long, Integer> calculateBookBorrowCounts() {
        // get all loans:
        List<Loan> allLoans = loanRepository.findAll();

        // store the booId -> borrow count in a map
        Map<Long,Integer> borrowCounts = new HashMap<>();
//        Map<Long, Long> borrowCountsStream = allLoans.stream()
//                .map(loan -> loan.getBook().getId())
//                .collect(
//                        Collectors.groupingBy(
//                                x -> x,
//                                Collectors.counting())
//                );
        for (Loan loan: allLoans) {
            Long bookId = loan.getBook().getId();
            // if a book exist already in map, increment count, else start from 1
            borrowCounts.put(bookId, borrowCounts.getOrDefault(bookId,0) +1);

        }
        return borrowCounts;
    }

    // clearing H2 before fresh sync
    private void clearH2Data() {
        reportDao.clearReportData();
    }

    // now after getting the counts of loans we insert them in h2
    // also we need to get book titles from MySql and store them into h2

    private void populateH2WithBookData(Map<Long, Integer> bookBorrowCounts) {
        // clear any data existing in H2 to ensure starting fresh
        clearH2Data();

        int booksAdded = 0;

        for (Map.Entry<Long, Integer> entry: bookBorrowCounts.entrySet()) {
            Long bookId = entry.getKey();
            Integer borrowCount = entry.getValue();

            // get book details from MySql (the title here we need)
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("book not found:" + bookId));

            // insert into H2 database using the reportDao
            for (int i = 0; i < borrowCount; i++) {
                reportDao.incrementBorrowCount(bookId, book.getTitle());
            }

            booksAdded++;

            // log in console to see if it works
            if(booksAdded % 5 == 0) {
                System.out.println("   Processed " + booksAdded + " books...");
            }
        }
    }

    private void logSyncSummary(Map<Long, Integer> bookBorrowCounts) {

        int totalBorrows = bookBorrowCounts
                .values().stream()
                .mapToInt(Integer::intValue).sum();

        double averageBorrows = bookBorrowCounts.isEmpty() ? 0 : (double) totalBorrows / bookBorrowCounts.size();

        // most 3 borrowed books
        bookBorrowCounts.entrySet().stream()
                .sorted((a,b) -> b.getValue().compareTo(a.getValue()))
                .limit(3)
                .forEach(
                        entry -> {
                            Long bookId = entry.getKey();
                            Integer count = entry.getValue();
                            Book book = bookRepository.findById(bookId).orElse(null);
                            String title = (book != null) ? book.getTitle() : "Unknown book";
                        }
                );

    }

    public String manualSync() {
        syncOnStartup();
        return "Manual sync completed successfully! Check server logs for details. and chek report api for result";
    }

    public Map<String, Object> getSyncStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("service", "DataSyncService");
        status.put("description", "Synchronizes MySQL data to H2 reporting database");
        status.put("autoSyncOnStartup", true);
        status.put("lastSyncTime", java.time.LocalDateTime.now().toString());

        // Get some stats
        long totalBooks = bookRepository.count();
        long totalLoans = loanRepository.count();

        status.put("mysqlBooksCount", totalBooks);
        status.put("mysqlLoansCount", totalLoans);
        status.put("mysqlDatabase", "libraryDB");
        status.put("h2Database", "reportdb (in-memory)");

        return status;
    }









}
