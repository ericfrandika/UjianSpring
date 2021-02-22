package com.example.demo.service;

import com.example.demo.model.Tindakan;

import java.util.List;

public interface TindakanService {
    List<Tindakan> findAllTindakanService();
    List<Tindakan> findAllTindakanServicetrue();

    void saveTindakanService(Tindakan tindakan);
    void updateTindakanService(Tindakan tindakan);
    void deleteAllTindakanService();
    void deleteTindakanServicebyId(String idTindakan);
    Tindakan findByIdTindakanService(String idTindakan);
    Tindakan findByNameTindakanService(String namaTindakan);
    List<Tindakan> findAllTindakanWithPaging(int page, int limit);
    void updateStatusServiceTindakan(Tindakan tindakan);

}
