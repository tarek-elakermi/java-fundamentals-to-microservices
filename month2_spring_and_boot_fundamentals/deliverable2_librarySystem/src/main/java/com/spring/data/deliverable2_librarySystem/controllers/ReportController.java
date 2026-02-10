package com.spring.data.deliverable2_librarySystem.controllers;


import com.spring.data.deliverable2_librarySystem.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/most-borrowed")
    public ResponseEntity<List<Map<String, Object>>> getMostBorrowedBooks() {
        try {
            List<Map<String, Object>> result = reportService.getMostBorrowedBooks();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    List.of(Map.of("error", e.getMessage()))
            );
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<List<Map<String, Object>>> getBookStats() {
        try {
            List<Map<String, Object>> result = reportService.getBookStats();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    List.of(Map.of("error", e.getMessage()))
            );
        }
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getDatabaseStatus() {
        try {
            Map<String, Object> status = reportService.getDataBaseStatus();
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    Map.of("error", e.getMessage())
            );
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        try {
            boolean isHealthy = reportService.checkTableExists();

            if (isHealthy) {
                return ResponseEntity.ok(Map.of(
                        "status", "UP",
                        "database", "H2 Reporting",
                        "message", "H2 reporting database is operational"
                ));
            } else {
                return ResponseEntity.status(503).body(Map.of(
                        "status", "DOWN",
                        "database", "H2 Reporting",
                        "message", "H2 reporting table not found"
                ));
            }
        } catch (Exception e) {
            return ResponseEntity.status(503).body(Map.of(
                    "status", "ERROR",
                    "database", "H2 Reporting",
                    "message", e.getMessage()
            ));
        }
    }

    @GetMapping("/sample")
    public ResponseEntity<Map<String, Object>> getSampleReport() {
        Map<String, Object> sample = Map.of(
                "description", "Sample H2 Report Data Structure",
                "tables", List.of("book_report"),
                "example_record", Map.of(
                        "book_id", 1,
                        "title", "Sample Book",
                        "borrow_count", 5
                ),
                "endpoints", List.of(
                        "/api/reports/most-borrowed",
                        "/api/reports/stats",
                        "/api/reports/status",
                        "/api/reports/health"
                )
        );

        return ResponseEntity.ok(sample);
    }








}
