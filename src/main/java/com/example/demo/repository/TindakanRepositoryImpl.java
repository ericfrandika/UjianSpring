package com.example.demo.repository;


import com.example.demo.model.Dokter;
import com.example.demo.model.Tindakan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("TindakanRepository")
public class TindakanRepositoryImpl implements TindakanRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tindakan> findAllTindakanRepository() {
        return jdbcTemplate.query("select *from tindakan",
                (rs,rowNum)->
                        new Tindakan(
                                rs.getString("idTindakan"),
                                rs.getString("namaTindakan"),
                                rs.getInt("biayaTindakan"),
                                rs.getBoolean("status")
                        )
        );
    }

    @Override
    public void saveTindakanRepository(Tindakan tindakan) {
        UUID idTindakan = UUID.randomUUID();
        jdbcTemplate.update("INSERT INTO tindakan(idTindakan, namaTindakan, biayaTindakan, status)values(?,?,?,?)",
                idTindakan.toString(),tindakan.getNamaTindakan(),
                tindakan.getBiayaTindakan(), tindakan.isStatus());
    }

    @Override
    public void updateTindakanRepository(Tindakan tindakan) {
        jdbcTemplate.update("update tindakan set namaTindakan=? , biayaTindakan=? ,status=?  where idTindakan=?",
                tindakan.getNamaTindakan(), tindakan.getBiayaTindakan(),
                tindakan.isStatus(),tindakan.getIdTindakan());


    }

    @Override
    public void deleteAllTindakanRepository() {
        jdbcTemplate.update("delete from tindakan ");

    }

    @Override
    public int deleteTindakanRepositorybyId(String idTindakan) {
        return jdbcTemplate.update("delete from tindakan where idTindakan = ?", idTindakan);
    }

    @Override
    public Tindakan findByIdTindakanRepository(String idTindakan) {
        return jdbcTemplate.queryForObject(
                "select * from tindakan where idTindakan = ?",
                new Object[]{idTindakan},
                (rs, rowNum) ->
                        new Tindakan(
                                rs.getString("idTindakan"),
                                rs.getString("namaTindakan"),
                                rs.getInt("biayaTindakan"),
                                rs.getBoolean("status")
                        )
        );
    }

    @Override
    public Tindakan findByNameTindakanRepository(String namaTindakan) {
        return jdbcTemplate.queryForObject(
                "select * from tindakan where namaTindakan like ?",
                new Object[]{"%"+namaTindakan},
                (rs, rowNum) ->
                        new Tindakan(
                                rs.getString("idTindakan"),
                                rs.getString("namaTindakan"),
                                rs.getInt("biayaTindakan"),
                                rs.getBoolean("status")
                        )

        );    }

    @Override
    public void updateTindakanRepositoryPasien(Tindakan tindakan) {
        jdbcTemplate.update("update tindakan set status=? where idTindakan=?",
                tindakan.isStatus(),tindakan.getIdTindakan());
    }

    @Override
    public List<Tindakan> findAllTindakanWithPaging(int page, int limit) {
        int numPages;
        numPages = jdbcTemplate.query("SELECT COUNT(*) as count FROM tindakan",
                (rs, rowNum) -> rs.getInt("count")).get(0);

        //validate page
        if (page < 1) page = 1;
        if (page > numPages) page = numPages;
        int start = (page - 1) * limit;
        List<Tindakan> tindakanList = jdbcTemplate.query("SELECT * FROM tindakan LIMIT " + start + "," + limit + ";",
                (rs, rowNum) ->
                        new Tindakan(
                                rs.getString("idTindakan"),
                                rs.getString("namaTindakan"),
                                rs.getInt("biayaTindakan"),
                                rs.getBoolean("status")
                        )
        );
        return tindakanList;
    }
}
