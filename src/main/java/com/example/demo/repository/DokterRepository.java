package com.example.demo.repository;

import com.example.demo.model.Dokter;


import java.util.List;

public interface DokterRepository {

    List<Dokter> findAllDokterRepository();

    void saveDokterRepository(Dokter dokter);

    void updateDokterRepository(Dokter dokter);

    void deleteAllDokterRepository();

    int deleteDokterRepositorybyId(String idDokter);

    Dokter findByIdDokterRepository(String idDokter);

    Dokter findByNameDokterRepository(String namaDokter);

    void updateStatusRepositoryDokter(Dokter dokter);

    List<Dokter> findAllDokterWithPaging(int page, int limit);

}
