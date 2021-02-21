package com.example.demo.service;

import com.example.demo.model.Pasien;
import com.example.demo.repository.PasienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pasienService")
public class PasienServiceImpl implements PasienService{

    @Autowired
    PasienRepository pasienRepository;


    @Override
    public List<Pasien> findAllPasienService() {
        List<Pasien> pasienList = pasienRepository.findAllPasienRepository();
        return pasienList;
    }

    @Override
    public void savePasienService(Pasien pasien) {
        synchronized (this) {
            pasienRepository.savePasienRepository(pasien);
        }
    }

    @Override
    public void updatePasienService(Pasien pasien) {
        synchronized (this){
            pasienRepository.updatePasienRepository(pasien);
        }

    }

    @Override
    public void deleteAllPasienService() {
        synchronized (this) {
            pasienRepository.deleteAllPasienRepository();
        }
    }

    @Override
    public void deletePasienServicebyId(String idPasien) {
        synchronized (this){
        pasienRepository.deletePasienRepositorybyId(idPasien);
        }
    }

    @Override
    public Pasien findByIdPasienService(String idPasien) {
        Pasien obj;
        try {
            obj = pasienRepository.findByIdPasienRepository(idPasien);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            obj = null;
        }
        return obj;
        }

    @Override
    public Pasien findByNamePasienService(String namaPasien) {
        Pasien obj;
        try {
            obj = pasienRepository.findByNamePasienRepository(namaPasien);
        }catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            obj = null;
        }
         return obj;
        }

    @Override
    public List<Pasien> findAllPasienWithPagingPasien(int page, int limit) {
        return pasienRepository.findAllPasienWithPaging(page, limit);
    }

    @Override
    public void updateStatusServicePasien(Pasien pasien) {
        synchronized (this){
            pasienRepository.updateStatusRepositoryPasien(pasien);
        }
    }

}
