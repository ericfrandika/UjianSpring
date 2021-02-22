package com.example.demo.service;

import com.example.demo.model.Pasien;
import com.example.demo.model.Tindakan;
import com.example.demo.repository.TindakanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("tindakanService")

public class TindakanServiceImpl implements TindakanService{
    @Autowired
    TindakanRepository tindakanRepository;


    @Override
    public List<Tindakan> findAllTindakanService() {
        List<Tindakan> tindakanList = tindakanRepository.findAllTindakanRepository();
        return tindakanList;
    }

    @Override
    public List<Tindakan> findAllTindakanServicetrue() {
        List<Tindakan> tindakanList = tindakanRepository.findAllTindakanRepositorytrue();
        return tindakanList;
    }

    @Override
    public void saveTindakanService(Tindakan tindakan) {
        synchronized (this) {
            tindakanRepository.saveTindakanRepository(tindakan);
        }

    }

    @Override
    public void updateTindakanService(Tindakan tindakan) {
        synchronized (this){
            tindakanRepository.updateTindakanRepository(tindakan);
        }

    }

    @Override
    public void deleteAllTindakanService() {
        synchronized (this) {
            tindakanRepository.deleteAllTindakanRepository();
        }

    }

    @Override
    public void deleteTindakanServicebyId(String idTindakan) {
        synchronized (this){
            tindakanRepository.deleteTindakanRepositorybyId(idTindakan);
        }

    }

    @Override
    public Tindakan findByIdTindakanService(String idTindakan) {
        Tindakan obj;
        try{
        obj = tindakanRepository.findByIdTindakanRepository(idTindakan);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            obj = null ;
        }
        return  obj;
        }

    @Override
    public Tindakan findByNameTindakanService(String namaTindakan) {
        Tindakan obj;
        try {
            obj = tindakanRepository.findByNameTindakanRepository(namaTindakan);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            obj = null;
        }
        return obj;
    }
    @Override
    public List<Tindakan> findAllTindakanWithPaging(int page, int limit) {
        return tindakanRepository.findAllTindakanWithPaging(page, limit);
    }

    @Override
    public void updateStatusServiceTindakan(Tindakan tindakan) {
        synchronized (this){
            tindakanRepository.updateTindakanRepositoryPasien(tindakan);
        }
    }
}
