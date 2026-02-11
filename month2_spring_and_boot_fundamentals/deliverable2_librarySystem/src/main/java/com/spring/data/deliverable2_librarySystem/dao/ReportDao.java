package com.spring.data.deliverable2_librarySystem.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReportDao {

    private final JdbcTemplate jdbcTemplate;

    public ReportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        System.out.println(" => ReportDao created with H2 JdbcTemplate");
    }

    // Update borrow count when a book is borrowed
    public void incrementBorrowCount(Long bookId, String title) {
        String sql = """
                     MERGE INTO book_report (book_id, title, borrow_count)
                     KEY(book_id)
                     Values (?, ?,COALESCE((SELECT borrow_count FROM book_report WHERE book_id =?),0) +1)
                     """;
        jdbcTemplate.update(sql, bookId, title, bookId);
        System.out.println(" => Updated borrow count for book ID: " + bookId);
    }

    // Get most borrowed books
    public List<Map<String, Object>> getMostBorrowBooks() {
        String sql = """
                SELECT * FROM book_report ORDER BY borrow_count DESC
                """;
        return jdbcTemplate.queryForList(sql);
    }

    // Check if table exists
    public boolean checkTableExists() {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM book_report", Integer.class
            );
            System.out.println(" book_report table has " + count + " rows");
            return true;

        }catch (Exception e) {
            System.out.println(" book_report table not found: " + e.getMessage());
            return false;
        }

    }

    public List<Map<String, Object>> getBookStats() {
        checkTableExists();
        String sql = """
            SELECT 
                COUNT(*) as total_books,
                SUM(borrow_count) as total_borrows,
                MAX(borrow_count) as max_borrows
            FROM book_report
            """;
        return jdbcTemplate.queryForList(sql);
    }

    public void clearReportData() {
        jdbcTemplate.execute("""
                DELETE FROM book_report
                """);
    }

    public void insertBookReport(Long bookId, String title, int borrowCount) {
        String sql = """
                MERGE INTO book_report (book_id, title, borrow_count)
                KEY(book_id)
                VALUES(?,?,?)
                """;
        jdbcTemplate.update(sql,bookId,title,borrowCount);
    }









}
