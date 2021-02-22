package com.example.demo.repository;

import com.example.demo.model.Pasien;
import com.example.demo.model.Report;

import java.util.List;

public interface ReportRepository {
    void saveReport(Report report);
    List<Report> findAllReport();
    Report findByIdReportRepository(String idTransaction);
    List<Report> findAllReportStatus();
    void updateStatusRepositoryReport(Report report);
    void updateListRepositoryReport(Report report);
    List<Report> findAllReportWithPaging(int page, int limit);
    void deleteReportRepositorybyId(String idTransaction);

}
