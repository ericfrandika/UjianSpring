package com.example.demo.repository;


import com.example.demo.model.BiayaObat;

import java.util.List;

public interface BiayaObatRepository {
    List<BiayaObat> findAllBiayaObatRepository();
    List<BiayaObat> findAllBiayaObatRepositorytrue();

    void saveBiayaObatRepository(BiayaObat biayaObat);

    void updateBiayaObatRepository(BiayaObat biayaObat);

    void deleteAllBiayaObatRepository();

    int deleteBiayaObatRepositorybyId(String idObat);

    BiayaObat findByIdBiayaObatRepository(String idObat);

    BiayaObat findByNameBiayaObatRepository(String namaObat);

    void updateStatusRepositoryBiayaObat(BiayaObat biayaObat);

    List<BiayaObat> findAllBiayaObatWithPaging(int page, int limit);


}
