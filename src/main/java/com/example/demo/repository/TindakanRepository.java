package com.example.demo.repository;
import com.example.demo.model.Report;
import com.example.demo.model.Tindakan;

import java.util.List;

public interface TindakanRepository {

    List<Tindakan> findAllTindakanRepository();

    List<Tindakan> findAllTindakanRepositorytrue();

    void saveTindakanRepository(Tindakan tindakan);

    void updateTindakanRepository(Tindakan tindakan);

    void deleteAllTindakanRepository();

    int deleteTindakanRepositorybyId(String idTindakan);

    Tindakan findByIdTindakanRepository(String idTindakan);

    Tindakan findByNameTindakanRepository(String namaTindakan);

    void updateTindakanRepositoryPasien(Tindakan tindakan);

    List<Tindakan> findAllTindakanWithPaging(int page, int limit);

}


