package com.example.demo.service;

import com.example.demo.model.BiayaObat;
import com.example.demo.model.Report;
import com.example.demo.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reportService")
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportRepository reportRepository;


    @Override
    public void saveReport(Report report) {
        synchronized (this) {
            try {
                reportRepository.saveReport(report);
            }catch (Exception e){
                System.out.println(e);
            }
        }

    }

    @Override
    public List<Report> findAllReport() {
    List<Report>reportList = reportRepository.findAllReport();
    return reportList;
    }

    @Override
    public Report findByIdReport(String idTransaction) {
        Report obj;
        try {
            obj = reportRepository.findByIdReportRepository(idTransaction);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println(e);
            obj = null;
        }
        return  obj;
    }

    @Override
    public List<Report> findAllReporttrue() {
        List<Report>reportList = reportRepository.findAllReportStatus();
        return reportList;
    }

    @Override
    public void updateStatusServiceReport(Report report) {
        synchronized (this){
            reportRepository.updateStatusRepositoryReport(report);
        }
    }

    @Override
    public void updateListServiceReport(Report report) {
        synchronized (this){
            reportRepository.updateListRepositoryReport(report);
        }
    }

    @Override
    public List<Report> findAllReportWithPaging(int page, int limit) {
        return reportRepository.findAllReportWithPaging(page , limit);
    }

    @Override
    public void deleteReportServicebyId(String idTransaction) {
        synchronized (this){
            reportRepository.deleteReportRepositorybyId(idTransaction);
        }
    }

}
