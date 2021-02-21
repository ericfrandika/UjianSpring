package com.example.demo.service;

import com.example.demo.model.BiayaObat;
import com.example.demo.model.Report;
import com.example.demo.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
