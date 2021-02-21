package com.example.demo.service;

import com.example.demo.model.Report;

import java.util.List;

public interface ReportService {
    void saveReport(Report report);
    List<Report> findAllReport();
}
