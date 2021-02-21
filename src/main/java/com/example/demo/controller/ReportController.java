package com.example.demo.controller;
import com.example.demo.model.Report;
import com.example.demo.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReportController {
    public static final Logger logger = LoggerFactory.getLogger(ReportController.class);

@Autowired
ReportService reportService;

//    --------------------------------------------FIND ALL REPORT-----------------------------
    @RequestMapping(value = "/report/", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> listAllReport() {
        List<Report> reportList = reportService.findAllReport();
        if (reportList.isEmpty()) {
            return new ResponseEntity<>(reportList, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(reportList, HttpStatus.OK);
        }
        }



    // -------------------Create a Report-------------------------------------------
    @RequestMapping(value = "/report/", method = RequestMethod.POST)
    public ResponseEntity<?> createReport(@RequestBody Report report) {
        logger.info("Creating Report : {}", report);
        reportService.saveReport(report);
        return new ResponseEntity<>(report, HttpStatus.CREATED);

    }


}
