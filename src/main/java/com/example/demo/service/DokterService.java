package com.example.demo.service;

import com.example.demo.model.Dokter;

import java.util.List;

public interface DokterService {
    List<Dokter> findAllDokterService();
    List<Dokter> findAllDokterServicetrue();
    void saveDokterService(Dokter dokter);
    void updateDokterService(Dokter dokter);
    void deleteAllDokterService();
    void deleteDokterServicebyId(String idDokter);
    Dokter findByIdDokterService(String idDokter);
    Dokter findByNameDokterService(String namaDokter);
    List<Dokter> findAllDokterWithPaging(int page, int limit);
    void updateStatusServiceDokter(Dokter dokter);
}
