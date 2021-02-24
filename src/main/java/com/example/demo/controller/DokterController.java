package com.example.demo.controller;

import com.example.demo.model.Dokter;
import com.example.demo.model.Pasien;
import com.example.demo.service.DokterService;
import com.example.demo.util.CustomErrorType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/klinik/master")
public class DokterController {
    public static final Logger logger = (Logger) LoggerFactory.getLogger(DokterController.class);

    @Autowired
    DokterService dokterService;

    ///CONTROLLER UNTUK SEMUA DOKTER  BELOM DI TESTER....

    //(1)-------(OKE)--------------------------CREAT DATA DOKTER---------------------------------

    @RequestMapping(value = "/dokter/", method = RequestMethod.POST)
    public ResponseEntity<?> cratePasien(@RequestBody Dokter dokter) {
        logger.info("Creating Dokter : {}", dokter);
            dokterService.saveDokterService(dokter);
            return new ResponseEntity<>(dokter, HttpStatus.OK);

    }

    //(2)--------OKE------------------------Find ALl DATA DOKTER----------------------------------

    @RequestMapping(value = "/dokter/", method = RequestMethod.GET)
    public ResponseEntity<List<Dokter>> listAllDokter() {
        List<Dokter> dokterList = dokterService.findAllDokterService();
        if (dokterList.isEmpty()) {
            return new ResponseEntity<>(dokterList, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(dokterList, HttpStatus.OK);
        }
    }
    //(2)--------OKE------------------------Find ALl DATA DOKTER----------------------------------

    @RequestMapping(value = "/dokter/true/", method = RequestMethod.GET)
    public ResponseEntity<List<Dokter>> listAllDokterTrue() {
        List<Dokter> dokterList = dokterService.findAllDokterServicetrue();
        if (dokterList.isEmpty()) {
            return new ResponseEntity<>(dokterList, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(dokterList, HttpStatus.OK);
        }
    }

    //(3)-----OKE---------------------------Find By ID-----------------------------------------------

    @RequestMapping(value = "/dokter/{idDokter}", method = RequestMethod.GET)
    public ResponseEntity<?> geDokterId(@PathVariable("idDokter") String idDokter) {
        logger.info("Fetching Dokter with id {}", idDokter);
        Dokter dokter = dokterService.findByIdDokterService(idDokter);
        if (dokter == null) {
            logger.error("Dokter with id {} not found.", idDokter);
            return new ResponseEntity<>(new CustomErrorType("Dokter with id " + idDokter  + " not found"), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(dokter, HttpStatus.OK);
        }
    }

    //(4)-----------------Oke-------------------FIND BY NAME-------------------------------------------------

    @RequestMapping(value = "/dokter/name/{namaDokter}", method = RequestMethod.GET)
    public ResponseEntity<?> getDokternama(@PathVariable("namaDokter") String namaDokter) {
        logger.info("Fetching Dokter with name {}", namaDokter);
        Dokter dokter = dokterService.findByNameDokterService(namaDokter);
        if (dokter == null) {
            logger.error("Dokter with nama {} not found.", namaDokter);
            return new ResponseEntity<>(new CustomErrorType("Dokter with name " + namaDokter  + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dokter, HttpStatus.OK);
    }

    //-(5)-----OKE-------------------------------------Update Bye Id------------------------------------------


    @RequestMapping(value = "/dokter/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDokter(@PathVariable("id") String idDokter, @RequestBody Dokter dokter) {
        logger.info("Updating Dokter with id {}", idDokter);

        Dokter currentDokter = dokterService.findByIdDokterService(idDokter);

        if (currentDokter == null) {
            logger.error("Unable to update. Dokter with id {} not found.", idDokter);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Dokter with id " + idDokter + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            currentDokter.setNamaDokter(dokter.getNamaDokter());
            currentDokter.setTglPraktek(dokter.getTglPraktek());
            currentDokter.setGender(dokter.getGender());
            currentDokter.setNoTelp(dokter.getNoTelp());
            currentDokter.setAlamat(dokter.getAlamat());
            currentDokter.setStatus(dokter.isStatus());
            dokterService.updateDokterService(currentDokter);
            return new ResponseEntity<>(currentDokter, HttpStatus.OK);
        }
    }


    //(6)----------OKE----------------------------Update Status Dokter----------------------------------------------------
    @RequestMapping(value = "/dokter/status/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatusDokter(@PathVariable("id") String idDokter, @RequestBody Dokter dokter) {
        logger.info("Updating Status Dokter with id {}", idDokter);

        Dokter currentDokter = dokterService.findByIdDokterService(idDokter);

        if (currentDokter == null) {
            logger.error("Unable to update. Dokter with id {} not found.", idDokter);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Dokter with id " + idDokter + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            currentDokter.setStatus(dokter.isStatus());
            dokterService.updateStatusServiceDokter(currentDokter);
            return new ResponseEntity<>(currentDokter, HttpStatus.OK);
        }
    }

//--(7)----------------OKE-------------------DELETE ALL DOKTER---------------------------------------------

    @RequestMapping(value = "/dokter/", method = RequestMethod.DELETE)
    public ResponseEntity<Dokter> deleteAllDokter() {
        logger.info("Deleting All Pasien");
        dokterService.deleteAllDokterService();
        return new ResponseEntity<Dokter>(HttpStatus.NO_CONTENT);
    }

    //--(8)----------Oke----------------------------Delete ID DOKTER-------------------------------------------


    @RequestMapping(value = "/dokter/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDokterbyId(@PathVariable("id") String idDokter) {
        logger.info("Fetching & Deleting Dokter with id {}", idDokter);

        Dokter dokter = dokterService.findByIdDokterService(idDokter);
        if (dokter == null) {
            logger.error("Unable to delete. Dokter with id {} not found.", idDokter);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Dokter with id " + idDokter + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            dokterService.deleteDokterServicebyId(idDokter);
            return new ResponseEntity<Pasien>(HttpStatus.NO_CONTENT);
        }
    }

    //(9)--------------------------------Find All With Pagination---------------------
    @RequestMapping(value = "/dokter/paging/", method = RequestMethod.GET)
    public ResponseEntity<?>getPasienWithPagin(@RequestParam int page, @RequestParam int limit){
        List<Dokter>dokterList = dokterService.findAllDokterWithPaging(page,limit);
        if(dokterList.isEmpty()){
            return new ResponseEntity<>(dokterList,HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(dokterList  ,HttpStatus.OK);
        }


    }
}


