package com.example.demo.repository;

import com.example.demo.model.BiayaObat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("BiayaObatRepository")

public class BiayaObatRepositoryImpl implements BiayaObatRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BiayaObat> findAllBiayaObatRepository() {
        return jdbcTemplate.query("select *from obat",
                (rs,rowNum)->
                        new BiayaObat(
                                rs.getString("idObat"),
                                rs.getString("namaObat"),
                                rs.getInt("qty"),
                                rs.getInt("harga"),
                                0,
                                rs.getBoolean("status")

                        )
        );
    }

    @Override
    public List<BiayaObat> findAllBiayaObatRepositorytrue() {
        return jdbcTemplate.query("select *from obat where status=1",
                (rs,rowNum)->
                        new BiayaObat(
                                rs.getString("idObat"),
                                rs.getString("namaObat"),
                                rs.getInt("qty"),
                                rs.getInt("harga"),
                                0,
                                rs.getBoolean("status")
                        )
        );
    }

    @Override
    public void saveBiayaObatRepository(BiayaObat biayaObat) {
        UUID idObat = UUID.randomUUID();
        jdbcTemplate.update("insert into obat(idObat,namaObat,qty,harga,status)values(?,?,?,?,?)",
                idObat.toString(),
                biayaObat.getNamaObat(),biayaObat.getQty(),
                biayaObat.getHarga(),biayaObat.isStatus());
    }

    @Override
    public void updateBiayaObatRepository(BiayaObat biayaObat) {
        jdbcTemplate.update("update obat set namaObat=? , qty=? ,harga=?,status=? where idObat=?",
                biayaObat.getNamaObat(),biayaObat.getQty(),
                biayaObat.getHarga(),biayaObat.isStatus(),biayaObat.getIdObat());

    }

    @Override
    public void deleteAllBiayaObatRepository() {
        jdbcTemplate.update("delete from obat ");

    }

    @Override
    public int deleteBiayaObatRepositorybyId(String idObat) {
        return jdbcTemplate.update("delete from obat where idObat = ?", idObat);
    }

    @Override
    public BiayaObat findByIdBiayaObatRepository(String idObat) {
        return jdbcTemplate.queryForObject(
                "select * from obat where idObat = ?",
                new Object[]{idObat},
                (rs, rowNum) ->
                        new BiayaObat(
                                rs.getString("idObat"),
                                rs.getString("namaObat"),
                                rs.getInt("qty"),
                                rs.getInt("harga"),
                                0,
                                rs.getBoolean("status")
                        ));
    }

    @Override
    public BiayaObat findByNameBiayaObatRepository(String namaObat) {
        return jdbcTemplate.queryForObject(
                "select * from obat where namaObat like ?",
                new Object[]{"%"+namaObat},
                (rs, rowNum) ->
                        new BiayaObat(
                                rs.getString("idObat"),
                                rs.getString("namaObat"),
                                rs.getInt("qty"),
                                rs.getInt("harga"),
                                0,
                                rs.getBoolean("status")
                        )

        );
    }

    @Override
    public void updateStatusRepositoryBiayaObat(BiayaObat biayaObat) {
        jdbcTemplate.update("update obat set status=? where idObat=?",
                biayaObat.isStatus(),biayaObat.getIdObat());
    }

    @Override
    public List<BiayaObat> findAllBiayaObatWithPaging(int page, int limit) {
        int numPages;
        numPages = jdbcTemplate.query("SELECT COUNT(*) as count FROM obat",
                (rs, rowNum) -> rs.getInt("count")).get(0);

        if (page < 1) page = 1;
        if (page > numPages) page = numPages;
        int start = (page - 1) * limit;
        List<BiayaObat> biayaObatList = jdbcTemplate.query("SELECT * FROM obat LIMIT " + start + "," + limit + ";",
                (rs, rowNum) ->
                        new BiayaObat(
                                rs.getString("idObat"),
                                rs.getString("namaObat"),
                                rs.getInt("qty"),
                                rs.getInt("harga"),
                                0,
                                rs.getBoolean("status")
                        )
        );
        return biayaObatList;
    }

}
