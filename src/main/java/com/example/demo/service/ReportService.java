package com.example.demo.service;

import com.example.demo.model.Dokter;
import com.example.demo.model.Report;

import java.util.List;

public interface ReportService {
    void saveReport(Report report);
    List<Report> findAllReport();
    Report findByIdReport(String idTransaction);
    List<Report> findAllReporttrue();
    void updateStatusServiceReport(Report report);
    void updateListServiceReport(Report report);


}
