package com.example.demo.controller;

import com.example.demo.model.Tindakan;
import com.example.demo.service.TindakanService;
import com.example.demo.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TindakanController {
    public static final Logger logger = LoggerFactory.getLogger(TindakanController.class);
    @Autowired
    TindakanService tindakanService;


    ///BELOM TESTER-------------------
    ///CONTROLLER UNTUK SEMUA PASIEN

    //(1)----------OKE-------------------------CREAT DATA Tindakan---------------------------------
    @RequestMapping(value = "/tindakan/", method = RequestMethod.POST)
    public ResponseEntity<?> crateTindakan(@RequestBody Tindakan tindakan) {
        logger.info("Creating Tindakan : {}", tindakan);
        tindakanService.saveTindakanService(tindakan);
        return new ResponseEntity<>(tindakan, HttpStatus.OK);

    }

    //(2)--------OKE------------------------Find ALl DATA Pasien----------------------------------

    @RequestMapping(value = "/tindakan/", method = RequestMethod.GET)
    public ResponseEntity<List<Tindakan>> listAllTindakan() {
        List<Tindakan> tindakanList = tindakanService.findAllTindakanService();
        if (tindakanList.isEmpty()) {
            return new ResponseEntity<>(tindakanList, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(tindakanList, HttpStatus.OK);
        }
    }

    //(3)-----OKE---------------------------Find By ID-----------------------------------------------

    @RequestMapping(value = "/tindakan/{idTindakan}", method = RequestMethod.GET)
    public ResponseEntity<?> getTindakanId(@PathVariable("idTindakan") String idTindakan) {
        logger.info("Fetching Tindakan with id {}", idTindakan);
        Tindakan tindakan = tindakanService.findByIdTindakanService(idTindakan);
        if (tindakan == null) {
            logger.error("Tindakan with id {} not found.", idTindakan);
            return new ResponseEntity<>(new CustomErrorType("Tindakan with id " + idTindakan  + " not found"), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(tindakan, HttpStatus.OK);
        }
    }

    //(4)-----------------Oke-------------------FIND BY NAME-------------------------------------------------

    @RequestMapping(value = "/tindakan/name/{namaTindakan}", method = RequestMethod.GET)
    public ResponseEntity<?> getTindakanNama(@PathVariable("namaTindakan") String namaTindakan) {
        logger.info("Fetching Tindakan with name {}", namaTindakan);
        Tindakan tindakan = tindakanService.findByNameTindakanService(namaTindakan);
        if (tindakan == null) {
            logger.error("Tindakan with nama {} not found.", namaTindakan);
            return new ResponseEntity<>(new CustomErrorType("Tindakan with name " + namaTindakan  + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tindakan, HttpStatus.OK);
    }

    //-(5)-----OKE-------------------------------------Update Bye Id------------------------------------------


    @RequestMapping(value = "/tindakan/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTindakan(@PathVariable("id") String idTindakan, @RequestBody Tindakan tindakan) {
        logger.info("Updating Tindakan with id {}", idTindakan);

        Tindakan currentTindakan = tindakanService.findByIdTindakanService(idTindakan);

        if (currentTindakan == null) {
            logger.error("Unable to update. Tindakan with id {} not found.", idTindakan);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Tindakan with id " + idTindakan + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            currentTindakan.setNamaTindakan(tindakan.getNamaTindakan());
            currentTindakan.setBiayaTindakan(tindakan.getBiayaTindakan());
            currentTindakan.setStatus(tindakan.isStatus());
            tindakanService.updateTindakanService(currentTindakan);
            return new ResponseEntity<>(currentTindakan, HttpStatus.OK);
        }
    }


    //(6)----------OKE----------------------------Update Status Pasien----------------------------------------------------
    @RequestMapping(value = "/tindakan/status/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatusTindakan(@PathVariable("id") String idTindakan, @RequestBody Tindakan tindakan) {
        logger.info("Updating Status Tindakan with id {}", idTindakan);

        Tindakan currentTindakan = tindakanService.findByIdTindakanService(idTindakan);

        if (currentTindakan == null) {
            logger.error("Unable to update. Tindakan with id {} not found.", idTindakan);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Tindakan with id " + idTindakan + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            currentTindakan.setStatus(tindakan.isStatus());
            tindakanService.updateStatusServiceTindakan(currentTindakan);
            return new ResponseEntity<>(currentTindakan, HttpStatus.OK);
        }
    }

//--(7)----------------OKE-------------------DELETE ALL PASIEN---------------------------------------------

    @RequestMapping(value = "/tindakan/", method = RequestMethod.DELETE)
    public ResponseEntity<Tindakan> deleteAllTindakan() {
        logger.info("Deleting All Tindakan");
        tindakanService.deleteAllTindakanService();
        return new ResponseEntity<Tindakan>(HttpStatus.NO_CONTENT);
    }

    //--(8)----------Oke----------------------------Delete ID PASIEN-------------------------------------------


    @RequestMapping(value = "/tindakan/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTindakanbyId(@PathVariable("id") String idTindakan) {
        logger.info("Fetching & Deleting Tindakan with id {}", idTindakan);

        Tindakan tindakan = tindakanService.findByIdTindakanService(idTindakan);
        if (tindakan == null) {
            logger.error("Unable to delete. Tindakan with id {} not found.", idTindakan);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Tindakan with id " + idTindakan + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            tindakanService.deleteTindakanServicebyId(idTindakan);
            return new ResponseEntity<Tindakan>(HttpStatus.NO_CONTENT);
        }
    }

    //(9)--------------------------------Find All With Pagination---------------------
    @RequestMapping(value = "/tindakan/paging/", method = RequestMethod.GET)
    public ResponseEntity<?>getPasienWithPagin(@RequestParam int page, @RequestParam int limit){
        List<Tindakan>tindakanList = tindakanService.findAllTindakanWithPaging(page,limit);
        if(tindakanList.isEmpty()){
            return new ResponseEntity<>(tindakanList,HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(tindakanList  ,HttpStatus.OK);
        }

    }
}
