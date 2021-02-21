package com.example.demo.service;

import com.example.demo.model.Pasien;

import java.util.List;

public interface PasienService {
    List<Pasien> findAllPasienService();
    void savePasienService(Pasien pasien);
    void updatePasienService(Pasien pasien);
    void deleteAllPasienService();
    void deletePasienServicebyId(String idPasien);
    Pasien findByIdPasienService(String idPasien);
    Pasien findByNamePasienService(String namaPasien);
    List<Pasien> findAllPasienWithPagingPasien(int page, int limit);
    void updateStatusServicePasien(Pasien pasien);
}
