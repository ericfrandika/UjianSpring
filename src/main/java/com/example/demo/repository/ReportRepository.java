package com.example.demo.repository;

import com.example.demo.model.Report;

import java.util.List;

public interface ReportRepository {
    void saveReport(Report report);

    List<Report> findAllReport();
}
