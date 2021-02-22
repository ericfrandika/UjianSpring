package com.example.demo.controller;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.GYearType;
import com.example.demo.model.*;
import com.example.demo.service.*;
import com.example.demo.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/klinik/master")
public class ReportController {
    public static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    ReportService reportService;

    @Autowired
    DokterService dokterService;

    @Autowired
    PasienService pasienService;

    @Autowired
    BiayaObatService biayaObatService;

    @Autowired
    TindakanService tindakanService;

    //    ---(1)-----------------------------------------FIND ALL REPORT-----------------------------
    @RequestMapping(value = "/report/", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> listAllReport() {
        List<Report> reportList = reportService.findAllReport();
        if (reportList.isEmpty()) {
            return new ResponseEntity<>(reportList, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reportList, HttpStatus.OK);
        }
    }


    // ------(2)------------------------------------------Create a Report WITH VALIDATION----------------------------------------------

    @RequestMapping(value = "/report/", method = RequestMethod.POST)
    public ResponseEntity<?> createReport(@RequestBody Report report) throws Exception {

        //-----------------------------------------------validation Obat-------------------------------------------------------------------------
        //VALIDATION ID OBAT !=TRUE AND QTY BUY OBAT < QTY STOCK

        List<BiayaObat> biayaObatList = report.getBiayaObatList();
        for (int i = 0; i < biayaObatList.size(); i++) {
            BiayaObat biayaObat = biayaObatService.findByIdBiayaObatService(report.getBiayaObatList().get(i).getIdObat());
            if (biayaObat == null) {
                logger.error("idObat is Not found");
                return new ResponseEntity<>(new CustomErrorType("Unable to create . A report with id Obat " + report.getBiayaObatList().get(i).getIdObat() + " No Found"), HttpStatus.NOT_FOUND);
            }
            for (int j = 0; j < biayaObatList.size(); j++){
                if (i != j){
                    if (biayaObatList.get(j).getIdObat().equals(biayaObat.getIdObat())){
                        return new ResponseEntity<>(new CustomErrorType("Unable to create . A report with id Obat " +
                                biayaObat.getIdObat()+"Already exist"), HttpStatus.CONFLICT);
                    }
                }
            }
            if (biayaObat.isStatus() != true) {
                logger.error("idObat not Avaliable");
                return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with IdObat " + report.getBiayaObatList().get(i).getIdObat() + " already not Avaliable."), HttpStatus.CONFLICT);
            }
            if (biayaObat.getQty() < report.getBiayaObatList().get(i).getQty()) {
                logger.error("idObat not Avaliable");
                return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with IdObat " + report.getBiayaObatList().get(i).getQty() + " Over in Qty"), HttpStatus.CONFLICT);
            }

        }

        //--------------------------------VALIDATION ID TINDAKAN ! = TRUE pada java---------------------------------------
        List<Tindakan> tindakanList = report.getTindakanList();
        for (int i = 0; i < tindakanList.size(); i++) {
            Tindakan tindakan = tindakanService.findByIdTindakanService(report.getTindakanList().get(i).getIdTindakan());
            if (tindakan == null) {
                logger.error("idDokter not Found in Praktek");
                return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with Id Tindakan " + report.getTindakanList().get(i).getIdTindakan() + " already not Found."), HttpStatus.FOUND);
            }
            for (int j = 0; j < tindakanList.size(); j++){
                if (i != j){
                    if (tindakanList.get(j).getIdTindakan().equals(tindakan.getIdTindakan())){
                        return new ResponseEntity<>(new CustomErrorType("Unable to create . A report with id Tindakan " +
                                tindakan.getIdTindakan()+"Already exist"), HttpStatus.CONFLICT);
                    }
                }
            }
            if (tindakan.isStatus() != true) {
                logger.error("idTindakan not Avaliable");
                return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with idTindakan " + report.getBiayaObatList().get(i).getIdObat() + " already not Avaliable."), HttpStatus.CONFLICT);
            }
        }

        // ------------------------------------------VALIDATION ID DOKTER AND STATUS---------------------------------
        Dokter dokter = dokterService.findByIdDokterService(report.getIdDokter());
        if (dokter == null) {
            logger.error("idDokter not Found in Praktek");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with IdPraktek " + report.getIdDokter() + " already not Found."), HttpStatus.FOUND);
        }
        if (!(dokter.getTglPraktek().equalsIgnoreCase(report.getTglTransaction()))) {
            logger.error("TanggalTransaction not same in Praktek");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with TglPraktek Dokter " + report.getTglTransaction() + " already not same."), HttpStatus.FOUND);
        }
        if (dokter.isStatus() != true) {
            logger.error("idDokter not Avaliable in Praktek");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with IdPraktek " + report.getIdDokter() + " already not avaliable."), HttpStatus.CONFLICT);
        }


/////------------------------------------------------------VALIDATION PASIEN---------------------------
        Pasien pasien = pasienService.findByIdPasienService(report.getIdPasien());
        if (pasien == null) {
            logger.error("idPasien not Avaliable");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with Id " + report.getIdPasien() + " already not Found."), HttpStatus.NOT_FOUND);
        }
        if (pasien.isStatus() != true) {
            logger.error("idPasien not Avaliable");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with Id " + report.getIdPasien() + " already not Avaliable."), HttpStatus.CONFLICT);
        } else {
            logger.info("Creating Report : {}", report);
            reportService.saveReport(report);
            return new ResponseEntity<>(report, HttpStatus.CREATED);
        }

    }


    //(3)-----OKE---------------------------Find By ID-----------------------------------------------

    @RequestMapping(value = "/report/{idTransaction}", method = RequestMethod.GET)
    public ResponseEntity<?> getfindId(@PathVariable("idTransaction") String idTransaction) {
        logger.info("Fetching Transaction with id {}", idTransaction);
        Report report = reportService.findByIdReport(idTransaction);
        if (report == null) {
            logger.error("Transaction with id {} not found.", idTransaction);
            return new ResponseEntity<>(new CustomErrorType("Transaction with id " + idTransaction + " not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(report, HttpStatus.OK);
        }
    }

    //  (4)  --------------------------------------------FIND ALL REPORT True-----------------------------
    @RequestMapping(value = "/report/true/", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> listAlltrue() {
        List<Report> reportList = reportService.findAllReporttrue();
        if (reportList.isEmpty()) {
            return new ResponseEntity<>(reportList, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reportList, HttpStatus.OK);
        }
    }

    //(5)-------------------------------------------UPDATE STATUS TRUE/FALSE------------------------------
    @RequestMapping(value = "/report/status/{idTransaction}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatusPasien(@PathVariable("idTransaction") String idTransaction, @RequestBody Report report) {
        logger.info("Updating Status Report Transaction with id {}", idTransaction);

        Report currentReport = reportService.findByIdReport(idTransaction);

        if (currentReport == null) {
            logger.error("Unable to update. Report with id {} not found.", idTransaction);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Report with id " + idTransaction + " not found."),
                    HttpStatus.NOT_FOUND);
        } else {
            currentReport.setStatus(report.isStatus());
            reportService.updateStatusServiceReport(currentReport);
            return new ResponseEntity<>(currentReport, HttpStatus.OK);
        }

    }
    //--------(6)-------------------------UPDATE REPORT ----------------------------------------------------

    @RequestMapping(value = "/report/{idTransaction}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSales(@PathVariable("idTransaction") String idTransaction, @RequestBody Report report) {
        logger.info("Updating Sales with idTransaction {}", idTransaction);

  //---------------UPDATE-------VALIDATION ID OBAT !=TRUE AND QTY BUY OBAT < QTY STOCK-------------

        List<BiayaObat> biayaObatList = report.getBiayaObatList();
        for (int i = 0; i < biayaObatList.size(); i++) {
            BiayaObat biayaObat = biayaObatService.findByIdBiayaObatService(report.getBiayaObatList().get(i).getIdObat());

            if (biayaObat == null) {
                logger.error("idObat is Not found");
                return new ResponseEntity<>(new CustomErrorType("Unable to create . A report with id Obat " + report.getBiayaObatList().get(i).getIdObat() + " No Found"), HttpStatus.NOT_FOUND);
            }
            for (int j = 0; j < biayaObatList.size(); j++){
                if (i != j){
                    if (biayaObatList.get(j).getIdObat().equals(biayaObat.getIdObat())){
                        return new ResponseEntity<>(new CustomErrorType("Unable to create . A report with id Obat " +
                                biayaObat.getIdObat()+"Already exist"), HttpStatus.CONFLICT);
                    }
                }
            }

            if (biayaObat.isStatus() != true) {
                logger.error("idObat not Avaliable");
                return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with IdObat " + report.getBiayaObatList().get(i).getIdObat() + " already not Avaliable."), HttpStatus.CONFLICT);
            }
            if (biayaObat.getQty() < report.getBiayaObatList().get(i).getQty()) {
                logger.error("idObat not Avaliable");
                return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with IdObat " + report.getBiayaObatList().get(i).getQty() + " Over in Qty"), HttpStatus.CONFLICT);
            }
        }

        //------------------------------UPDATE--VALIDATION ID TINDAKAN ! = TRUE pada java---------------------------------------
        List<Tindakan> tindakanList = report.getTindakanList();
        for (int i = 0; i < tindakanList.size(); i++) {
            Tindakan tindakan = tindakanService.findByIdTindakanService(report.getTindakanList().get(i).getIdTindakan());
            if (tindakan == null) {
                logger.error("idDokter not Found in Praktek");
                return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with Id Tindakan " + report.getTindakanList().get(i).getIdTindakan() + " already not Found."), HttpStatus.FOUND);
            }
            for (int j = 0; j < tindakanList.size(); j++){
                if (i != j){
                    if (tindakanList.get(j).getIdTindakan().equals(tindakan.getIdTindakan())){
                        return new ResponseEntity<>(new CustomErrorType("Unable to create . A report with id Tindakan " +
                                tindakan.getIdTindakan()+"Already exist"), HttpStatus.CONFLICT);
                    }
                }
            }
            if (tindakan.isStatus() != true) {
                logger.error("idTindakan not Avaliable");
                return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with idTindakan " + report.getBiayaObatList().get(i).getIdObat() + " already not Avaliable."), HttpStatus.CONFLICT);
            }
        }

        // ------------------------------------------VALIDATION UPDATE ID DOKTER AND STATUS---------------------------------
        Dokter dokter = dokterService.findByIdDokterService(report.getIdDokter());
        if (dokter == null) {
            logger.error("idDokter not Found in Praktek");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with IdPraktek " + report.getIdDokter() + " already not Found."), HttpStatus.FOUND);
        }
        if (!(dokter.getTglPraktek().equalsIgnoreCase(report.getTglTransaction()))) {
            logger.error("TanggalTransaction not same in Praktek");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with TglPraktek Dokter " + report.getTglTransaction() + " already not same."), HttpStatus.FOUND);
        }
        if (dokter.isStatus() != true) {
            logger.error("idDokter not Avaliable in Praktek");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with IdPraktek " + report.getIdDokter() + " already not avaliable."), HttpStatus.CONFLICT);
        }


/////------------------------------------------------------VALIDATION PASIEN---------------------------
        Pasien pasien = pasienService.findByIdPasienService(report.getIdPasien());
        if (pasien == null) {
            logger.error("idPasien not Avaliable");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with Id " + report.getIdPasien() + " already not Found."), HttpStatus.NOT_FOUND);
        }
        if (pasien.isStatus() != true) {
            logger.error("idPasien not Avaliable");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Report with Id " + report.getIdPasien() + " already not Avaliable."), HttpStatus.CONFLICT);
        }
        Report currentReport = reportService.findByIdReport(idTransaction);

        if (currentReport == null) {
            logger.error("Unable to update. Report with idTransaction {} not found.", idTransaction);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Report with id " + idTransaction + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentReport.setIdPasien(report.getIdPasien());
        currentReport.setIdDokter(report.getIdDokter());
        currentReport.setIdTransaction(report.getIdTransaction());
        reportService.updateListServiceReport(report);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    //----------(7)----------------------Find All With Pagination---------------------
    @RequestMapping(value = "/report/paging/", method = RequestMethod.GET)
    public ResponseEntity<?>getrreportWithPaging(@RequestParam int page, @RequestParam int limit){
        List<Report>reportList = reportService.findAllReportWithPaging(page,limit);
        if(reportList.isEmpty()){
            return new ResponseEntity<>(reportList,HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(reportList ,HttpStatus.OK);
        }
    }
    //-------------(8)--------------------------DELETE BY ID-------------------------------------
    @RequestMapping(value = "/report/{idTransaction}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReportbyId(@PathVariable("idTransaction") String idTransaction) {
        logger.info("Fetching & Deleting Report with id {}", idTransaction);

        Report report = reportService.findByIdReport(idTransaction);
        if (report == null) {
            logger.error("Unable to delete. Pasien with id {} not found.", idTransaction);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Report with id " + idTransaction + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            reportService.deleteReportServicebyId(idTransaction);
            return new ResponseEntity<Pasien>(HttpStatus.NO_CONTENT);
        }
    }
}
