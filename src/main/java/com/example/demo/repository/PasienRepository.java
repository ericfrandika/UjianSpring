package com.example.demo.repository;


import com.example.demo.model.Pasien;

import java.util.List;

public interface PasienRepository {
    List<Pasien> findAllPasienRepository();
    List<Pasien> findAllPasienRepositorytrue();
    void savePasienRepository(Pasien pasien);
    void updatePasienRepository(Pasien pasien);

    void deleteAllPasienRepository();

    int deletePasienRepositorybyId(String idPasien);

    Pasien findByIdPasienRepository(String idPasien);

   Pasien findByNamePasienRepository(String namaPasien);

    void updateStatusRepositoryPasien(Pasien pasien);

    List<Pasien> findAllPasienWithPaging(int page, int limit);

}

