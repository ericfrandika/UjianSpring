package com.example.demo.service;


import com.example.demo.model.BiayaObat;
import com.example.demo.repository.BiayaObatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("biayaObatService")
public class BiayaObatServiceImpl implements BiayaObatService {
    @Autowired
    BiayaObatRepository biayaObatRepository;


    @Override
    public List<BiayaObat> findAllBiayaObatService() {
        List<BiayaObat> biayaObatList = biayaObatRepository.findAllBiayaObatRepository();
        return biayaObatList;
    }

    @Override
    public List<BiayaObat> findAllBiayaObatServicetrue() {
        List<BiayaObat>biayaObatList = biayaObatRepository.findAllBiayaObatRepositorytrue();
        return biayaObatList;
    }

    @Override
    public void saveBiayaObatService(BiayaObat biayaObat) {
        synchronized (this) {
            biayaObatRepository.saveBiayaObatRepository(biayaObat);
        }
    }

    @Override
    public void updateBiayaObatService(BiayaObat biayaObat) {
        synchronized (this){
            biayaObatRepository.updateBiayaObatRepository(biayaObat);
        }
    }

    @Override
    public void deleteAllBiayaObatService() {
        synchronized (this) {
            biayaObatRepository.deleteAllBiayaObatRepository();
        }
    }

    @Override
    public void deleteBiayaObatServicebyId(String idObat) {
        synchronized (this){
            biayaObatRepository.deleteBiayaObatRepositorybyId(idObat);
        }

    }

    @Override
    public BiayaObat findByIdBiayaObatService(String idObat) {
        BiayaObat obj;
        try {
            obj = biayaObatRepository.findByIdBiayaObatRepository(idObat);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println(e);
            obj = null;
        }
        return  obj;
        }

    @Override
    public BiayaObat findByNameBiayaObatService(String namaObat) {
        BiayaObat obj;
        return obj = biayaObatRepository.findByNameBiayaObatRepository(namaObat);
    }

    @Override
    public List<BiayaObat> findAllBiayaObatWithPaging(int page, int limit) {
        return biayaObatRepository.findAllBiayaObatWithPaging(page, limit);
    }

    @Override
    public void updateStatusServiceBiayaObat(BiayaObat biayaObat) {
        synchronized (this){
            try {
                biayaObatRepository.updateStatusRepositoryBiayaObat(biayaObat);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public boolean isObatExist(BiayaObat obat) {
        return findByNameBiayaObatService(obat.getNamaObat()) !=null;
    }
    public boolean isObatExistId(BiayaObat obat){
        return findByIdBiayaObatService(obat.getIdObat()) !=null;
    }
}
