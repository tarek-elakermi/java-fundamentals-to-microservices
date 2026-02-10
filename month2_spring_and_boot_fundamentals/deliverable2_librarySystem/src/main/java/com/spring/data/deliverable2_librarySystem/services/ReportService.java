package com.spring.data.deliverable2_librarySystem.services;


import com.spring.data.deliverable2_librarySystem.dao.ReportDao;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final ReportDao reportDao;

    public ReportService(ReportDao reportDao) {
        this.reportDao = reportDao;
        System.out.println("ReportService created");
    }


    public List<Map<String, Object>> getMostBorrowedBooks() {
        return reportDao.getMostBorrowBooks();
    }

    public List<Map<String, Object>> getBookStats() {
        return reportDao.getBookStats();
    }

    public boolean checkTableExists() {
        return reportDao.checkTableExists();
    }

    public Map<String, Object> getDataBaseStatus() {
        boolean tableExists = checkTableExists();

        List<Map<String, Object>> stats = getBookStats();

        Map<String, Object> status = new HashMap<>();
        status.put("h2TableExists", tableExists);

        if(!stats.isEmpty()) {
            status.put("totalBooksInReport", stats.get(0).get("total_books"));
            status.put("totalBorrows", stats.get(0).get("total_borrows"));
            status.put("maxBorrows", stats.get(0).get("max_borrows"));
        }

        status.put("timestamp", LocalDateTime.now().toString());

        return status;
    }








}
