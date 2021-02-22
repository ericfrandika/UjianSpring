package com.example.demo.controller;

import com.example.demo.model.BiayaObat;
import com.example.demo.service.BiayaObatService;
import com.example.demo.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/klinik/master")
public class BiayaObatController {
    public static final Logger logger = LoggerFactory.getLogger(BiayaObatController.class);
    @Autowired
    BiayaObatService biayaObatService;

    ///CONTROLLER UNTUK SEMUA PASIEN

    //(1)----------OKE-------------------------CREAT DATA PASIEN---------------------------------
    @RequestMapping(value = "/obat/", method = RequestMethod.POST)
    public ResponseEntity<?> crateObat(@RequestBody BiayaObat biayaObat) {
        if (biayaObatService.isObatExist(biayaObat)) {
            logger.error("Unable to create. A BiayaObat with name {} already exist", biayaObat.getNamaObat());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Obat with name " + biayaObat.getNamaObat() + " already exist."), HttpStatus.CONFLICT);
        }
        else {
            logger.info("Creating Obat : {}", biayaObat);
            biayaObatService.saveBiayaObatService(biayaObat);
            return new ResponseEntity<>(biayaObat, HttpStatus.OK);
        }

    }

    //(2)--------OKE------------------------Find ALl DATA Pasien----------------------------------

    @RequestMapping(value = "/obat/", method = RequestMethod.GET)
    public ResponseEntity<List<BiayaObat>> listAllObat() {
        List<BiayaObat> biayaObatList = biayaObatService.findAllBiayaObatService();
        if (biayaObatList.isEmpty()) {
            return new ResponseEntity<>(biayaObatList, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(biayaObatList, HttpStatus.OK);
        }
    }
    //(2)--------OKE------------------------Find ALl DATA Pasien status True----------------------------------

    @RequestMapping(value = "/obat/true/", method = RequestMethod.GET)
    public ResponseEntity<List<BiayaObat>> listAllObatTrue() {
        List<BiayaObat> biayaObatList = biayaObatService.findAllBiayaObatServicetrue();
        if (biayaObatList.isEmpty()) {
            return new ResponseEntity<>(biayaObatList, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(biayaObatList, HttpStatus.OK);
        }
    }

    //(3)-----OKE---------------------------Find By ID-----------------------------------------------

    @RequestMapping(value = "/obat/{idObat}", method = RequestMethod.GET)
    public ResponseEntity<?> getObatId(@PathVariable("idObat") String idObat) {
        logger.info("Fetching Obat with id {}", idObat);
        BiayaObat biayaObat = biayaObatService.findByIdBiayaObatService(idObat);
        if (biayaObat == null) {
            logger.error("Obat with id {} not found.", idObat);
            return new ResponseEntity<>(new CustomErrorType("Obat with id " + idObat  + " not found"), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(biayaObat, HttpStatus.OK);
        }
    }

    //(4)-----------------Oke-------------------FIND BY NAME-------------------------------------------------

    @RequestMapping(value = "/obat/name/{namaObat}", method = RequestMethod.GET)
    public ResponseEntity<?> getObatnama(@PathVariable("namaObat") String namaObat) {
        logger.info("Fetching Pasien with name {}", namaObat);
        BiayaObat biayaObat = biayaObatService.findByNameBiayaObatService(namaObat);
        if (biayaObat == null) {
            logger.error("Pasien with nama {} not found.", namaObat);
            return new ResponseEntity<>(new CustomErrorType("Obat with name " + namaObat  + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(biayaObat, HttpStatus.OK);
    }

    //-(5)-----OKE-------------------------------------Update Bye Id------------------------------------------


    @RequestMapping(value = "/obat/{idObat}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateObat(@PathVariable("idObat") String idObat
            , @RequestBody BiayaObat biayaObat) {

        logger.info("Updating Pasien with id {}", idObat);
        BiayaObat currentBiayaObat = biayaObatService.findByIdBiayaObatService(idObat);
        if (currentBiayaObat == null) {
            logger.error("Unable to update. Pasien with id {} not found.", idObat);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Obat with id " + idObat + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            currentBiayaObat.setNamaObat(biayaObat.getNamaObat());
            currentBiayaObat.setQty(biayaObat.getQty());
            currentBiayaObat.setHarga(biayaObat.getHarga());
            if(biayaObat.getQty() == 0){
                currentBiayaObat.setStatus(false);
            }
            else{
                currentBiayaObat.setStatus(true);
            }
            biayaObatService.updateBiayaObatService(currentBiayaObat);
            return new ResponseEntity<>(currentBiayaObat, HttpStatus.OK);
        }
    }


    //(6)----------OKE----------------------------Update Status Obat----------------------------------------------------
    @RequestMapping(value = "/obat/status/{idObat}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatusObat(@PathVariable("idObat") String idObat, @RequestBody BiayaObat biayaObat) {
        logger.info("Updating Status BiayaObat with id {}", idObat);

        BiayaObat currentBiayaObat = biayaObatService.findByIdBiayaObatService(idObat);

        if (currentBiayaObat == null) {
            logger.error("Unable to update. BiayaObat with id {} not found.", idObat);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. BiayaObat with id " + idObat + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            currentBiayaObat.setStatus(biayaObat.isStatus());
            biayaObatService.updateStatusServiceBiayaObat(currentBiayaObat);
            return new ResponseEntity<>(currentBiayaObat, HttpStatus.OK);
        }
    }

//--(7)----------------OKE-------------------DELETE ALL Obat---------------------------------------------

    @RequestMapping(value = "/obat/", method = RequestMethod.DELETE)
    public ResponseEntity<BiayaObat> deleteAllObat() {
        logger.info("Deleting All Pasien");
        biayaObatService.deleteAllBiayaObatService();
        return new ResponseEntity<BiayaObat>(HttpStatus.NO_CONTENT);
    }

    //--(8)----------Oke----------------------------Delete ID Obat-------------------------------------------


    @RequestMapping(value = "/obat/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteObatbyId(@PathVariable("id") String idObat) {
        logger.info("Fetching & Deleting Pasien with id {}", idObat);

        BiayaObat biayaObat = biayaObatService.findByIdBiayaObatService(idObat);
        if (biayaObat == null) {
            logger.error("Unable to delete. Pasien with id {} not found.", idObat);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Obat with id " + idObat + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            biayaObatService.deleteBiayaObatServicebyId(idObat);
            return new ResponseEntity<BiayaObat>(HttpStatus.NO_CONTENT);
        }
    }

    //(9)--------------------------------Find All With Pagination---------------------
    @RequestMapping(value = "/obat/paging/", method = RequestMethod.GET)
    public ResponseEntity<?>getPasienWithPagin(@RequestParam int page, @RequestParam int limit){
        List<BiayaObat>biayaObatList = biayaObatService.findAllBiayaObatWithPaging(page,limit);
        if(biayaObatList.isEmpty()){
            return new ResponseEntity<>(biayaObatList,HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(biayaObatList  ,HttpStatus.OK);
        }


    }
}
