package com.example.demo.controller;
import com.example.demo.model.Pasien;
import com.example.demo.service.PasienService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.util.CustomErrorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/klinik/master")
public class PasienController {
    public static final Logger logger = LoggerFactory.getLogger(PasienController.class);
    @Autowired
    PasienService pasienService;

    ///CONTROLLER UNTUK SEMUA PASIEN

    //(1)----------OKE-------------------------CREAT DATA PASIEN---------------------------------
    @RequestMapping(value = "/pasien/", method = RequestMethod.POST)
    public ResponseEntity<?> cratePasien(@RequestBody Pasien pasien) {
        logger.info("Creating Pasien : {}", pasien);
            pasienService.savePasienService(pasien);
            return new ResponseEntity<>(pasien, HttpStatus.OK);

    }

    //(2)--------OKE------------------------Find ALl DATA Pasien----------------------------------

    @RequestMapping(value = "/pasien/", method = RequestMethod.GET)
    public ResponseEntity<List<Pasien>> listAllPasien() {
        List<Pasien> pasienList = pasienService.findAllPasienService();
        if (pasienList.isEmpty()) {
            return new ResponseEntity<>(pasienList, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(pasienList, HttpStatus.OK);
        }
    }
    //(2)--------OKE------------------------Find ALl DATA Pasien True----------------------------------

    @RequestMapping(value = "/pasien/true/", method = RequestMethod.GET)
    public ResponseEntity<List<Pasien>> listAllPasienTrue() {
        List<Pasien> pasienList = pasienService.findAllPasienServicetrue();
        if (pasienList.isEmpty()) {
            return new ResponseEntity<>(pasienList, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(pasienList, HttpStatus.OK);
        }
    }

    //(3)-----OKE---------------------------Find By ID-----------------------------------------------

    @RequestMapping(value = "/pasien/{idPasien}", method = RequestMethod.GET)
    public ResponseEntity<?> getPasienId(@PathVariable("idPasien") String idPasien) {
        logger.info("Fetching Pasien with id {}", idPasien);
        Pasien pasien = pasienService.findByIdPasienService(idPasien);
        if (pasien == null) {
            logger.error("Pasien with id {} not found.", idPasien);
            return new ResponseEntity<>(new CustomErrorType("Pasien with id " + idPasien  + " not found"), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(pasien, HttpStatus.OK);
        }
    }

    //(4)-----------------Oke-------------------FIND BY NAME-------------------------------------------------

    @RequestMapping(value = "/pasien/name/{namaPasien}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductnama(@PathVariable("namaPasien") String namaPasien) {
        logger.info("Fetching Pasien with name {}", namaPasien);
        Pasien pasien = pasienService.findByNamePasienService(namaPasien);
        if (pasien == null) {
            logger.error("Pasien with nama {} not found.", namaPasien);
            return new ResponseEntity<>(new CustomErrorType("Pasien with name " + namaPasien  + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pasien, HttpStatus.OK);
    }

    //-(5)-----OKE-------------------------------------Update Bye Id------------------------------------------


    @RequestMapping(value = "/pasien/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePasien(@PathVariable("id") String idPasien, @RequestBody Pasien pasien) {
        logger.info("Updating Pasien with id {}", idPasien);

        Pasien currentPasien = pasienService.findByIdPasienService(idPasien);

        if (currentPasien == null) {
            logger.error("Unable to update. Pasien with id {} not found.", idPasien);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Pasien with id " + idPasien + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            currentPasien.setNamaPasien(pasien.getNamaPasien());
            currentPasien.setTempat(pasien.getTempat());
            currentPasien.setTglLahir(pasien.getTglLahir());
            currentPasien.setGender(pasien.getGender());
            currentPasien.setNoTelp(pasien.getNoTelp());
            currentPasien.setAlamat(pasien.getAlamat());
            currentPasien.setStatus(pasien.isStatus());
            pasienService.updatePasienService(currentPasien);
            return new ResponseEntity<>(currentPasien, HttpStatus.OK);
        }
    }


//(6)----------OKE----------------------------Update Status Pasien----------------------------------------------------
@RequestMapping(value = "/pasien/status/{id}", method = RequestMethod.PUT)
public ResponseEntity<?> updateStatusPasien(@PathVariable("id") String idPasien, @RequestBody Pasien pasien) {
    logger.info("Updating Status Pasien with id {}", idPasien);

    Pasien currentPasien = pasienService.findByIdPasienService(idPasien);

    if (currentPasien == null) {
        logger.error("Unable to update. Pasien with id {} not found.", idPasien);
        return new ResponseEntity<>(new CustomErrorType("Unable to update. Pasien with id " + idPasien + " not found."),
                HttpStatus.NOT_FOUND);
    }
    else {
        currentPasien.setStatus(pasien.isStatus());
        pasienService.updateStatusServicePasien(currentPasien);
        return new ResponseEntity<>(currentPasien, HttpStatus.OK);
    }
}

//--(7)----------------OKE-------------------DELETE ALL PASIEN---------------------------------------------

    @RequestMapping(value = "/pasien/", method = RequestMethod.DELETE)
    public ResponseEntity<Pasien> deleteAllPasien() {
        logger.info("Deleting All Pasien");
        pasienService.deleteAllPasienService();
        return new ResponseEntity<Pasien>(HttpStatus.NO_CONTENT);
    }

    //--(8)----------Oke----------------------------Delete ID PASIEN-------------------------------------------


    @RequestMapping(value = "/pasien/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePasienbyId(@PathVariable("id") String idPasien) {
        logger.info("Fetching & Deleting Pasien with id {}", idPasien);

        Pasien pasien = pasienService.findByIdPasienService(idPasien);
        if (pasien == null) {
            logger.error("Unable to delete. Pasien with id {} not found.", idPasien);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Pasien with id " + idPasien + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            pasienService.deletePasienServicebyId(idPasien);
            return new ResponseEntity<Pasien>(HttpStatus.NO_CONTENT);
        }
    }

    //(9)--------------------------------Find All With Pagination---------------------
    @RequestMapping(value = "/pasien/paging/", method = RequestMethod.GET)
    public ResponseEntity<?>getPasienWithPagin(@RequestParam int page, @RequestParam int limit){
        List<Pasien>pasienList = pasienService.findAllPasienWithPagingPasien(page,limit);
        if(pasienList.isEmpty()){
            return new ResponseEntity<>(pasienList,HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(pasienList  ,HttpStatus.OK);
        }


    }
}
