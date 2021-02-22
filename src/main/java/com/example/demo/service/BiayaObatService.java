package com.example.demo.service;


import com.example.demo.model.BiayaObat;

import java.util.List;

public interface BiayaObatService {
    List<BiayaObat> findAllBiayaObatService();

    List<BiayaObat> findAllBiayaObatServicetrue();

    void saveBiayaObatService(BiayaObat biayaObat);

    void updateBiayaObatService(BiayaObat biayaObat);

    void deleteAllBiayaObatService();

    void deleteBiayaObatServicebyId(String idObat);

    BiayaObat findByIdBiayaObatService(String idObat);

    BiayaObat findByNameBiayaObatService(String namaObat);

    List<BiayaObat> findAllBiayaObatWithPaging(int page, int limit);

    void updateStatusServiceBiayaObat(BiayaObat biayaObat);

    boolean isObatExist(BiayaObat obat);
    boolean isObatExistId(BiayaObat obat);

}


