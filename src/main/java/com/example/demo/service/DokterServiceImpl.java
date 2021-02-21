package com.example.demo.service;

import com.example.demo.model.Dokter;
import com.example.demo.model.Pasien;
import com.example.demo.repository.DokterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dokterService")
public class DokterServiceImpl  implements DokterService{
    @Autowired
    DokterRepository dokterRepository;


    @Override
    public List<Dokter> findAllDokterService() {
        List<Dokter> dokterList = dokterRepository.findAllDokterRepository();
        return dokterList;
    }

    @Override
    public void saveDokterService(Dokter dokter) {
        synchronized (this) {
            dokterRepository.saveDokterRepository(dokter);
        }

    }

    @Override
    public void updateDokterService(Dokter dokter) {
        synchronized (this){
            dokterRepository.updateDokterRepository(dokter);
        }

    }

    @Override
    public void deleteAllDokterService() {
        synchronized (this) {
            dokterRepository.deleteAllDokterRepository();
        }
    }

    @Override
    public void deleteDokterServicebyId(String idDokter) {
        synchronized (this){
            dokterRepository.deleteDokterRepositorybyId(idDokter);
        }

    }

    @Override
    public Dokter findByIdDokterService(String idDokter) {
        Dokter obj;

        return obj = dokterRepository.findByIdDokterRepository(idDokter);
    }

    @Override
    public Dokter findByNameDokterService(String namaDokter) {
        Dokter obj;
        try{
            obj = dokterRepository.findByNameDokterRepository(namaDokter);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            obj = null;
        }
        return obj;
    }

    @Override
    public List<Dokter> findAllDokterWithPaging(int page, int limit) {
        return dokterRepository.findAllDokterWithPaging(page, limit);
    }

    @Override
    public void updateStatusServiceDokter(Dokter dokter) {
        synchronized (this){
            dokterRepository.updateStatusRepositoryDokter(dokter);
        }
    }
}
