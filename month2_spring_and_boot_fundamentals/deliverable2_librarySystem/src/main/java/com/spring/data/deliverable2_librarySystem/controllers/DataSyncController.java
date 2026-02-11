package com.spring.data.deliverable2_librarySystem.controllers;


import com.spring.data.deliverable2_librarySystem.services.DataSyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sync")
public class DataSyncController {

    private final DataSyncService dataSyncService;

    public DataSyncController(DataSyncService dataSyncService) {
        this.dataSyncService = dataSyncService;
    }

    // check the sync service status without triggering synchronization
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getSyncStatus() {

        try {
            Map<String, Object> syncStatus = dataSyncService.getSyncStatus();
            return ResponseEntity.ok(syncStatus);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    Map.of("error", e.getMessage())
            );
        }
    }

    // now we will trigger the sync manually from MySql to H2
    @PostMapping("/now")
    public ResponseEntity<Map<String, Object>> triggerManualSync() {

        try {
            String result = dataSyncService.manualSync();
            return ResponseEntity.ok(
                    Map.of(
                            "message", "Sync initiated successfully",
                            "details", result,
                            "timestamp", java.time.LocalDateTime.now().toString()
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    Map.of("error","Sync failed" + e.getMessage())
            );
        }
    }


    // health check for service
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "service", "DataSyncService",
                "status", "RUNNING",
                "autoSync", "ENABLED (runs on startup)",
                "manualSync", "AVAILABLE via POST /api/sync/now"
        ));
    }

    // Detailed information about the sync process
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getSyncInfo() {
        Map<String, Object> info = Map.of(
                "purpose", "Synchronize data from MySQL (persistent) to H2 (reporting cache)",
                "sourceDatabase", Map.of(
                        "type", "MySQL",
                        "purpose", "Permanent storage of all transactions",
                        "tables", "authors, books, members, loans"
                ),
                "targetDatabase", Map.of(
                        "type", "H2 In-Memory",
                        "purpose", "Fast reporting and analytics",
                        "tables", "book_report (aggregated borrow counts)"
                ),
                "syncProcess", Map.of(
                        "step1", "Read all loans from MySQL",
                        "step2", "Calculate borrow counts per book",
                        "step3", "Clear H2 book_report table",
                        "step4", "Insert aggregated data into H2"
                ),
                "whenItRuns", List.of(
                        "Automatically on application startup",
                        "Manually via API call"
                ),
                "typicalUseCase", "After restarting the app, H2 is empty. This sync repopulates it from MySQL data."
        );

        return ResponseEntity.ok(info);
    }












}
