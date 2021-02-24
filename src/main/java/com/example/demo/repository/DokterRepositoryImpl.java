package com.example.demo.repository;

import com.example.demo.model.Dokter;
import com.example.demo.model.Pasien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("DokterRepository")

public class DokterRepositoryImpl implements DokterRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Dokter> findAllDokterRepository() {
        return jdbcTemplate.query("select * from dokter",
                (rs,rowNum)->
                        new Dokter(
                                rs.getString("idDokter"),
                                rs.getString("namaDokter"),
                                rs.getString("tglPraktek"),
                                rs.getString("gender"),
                                rs.getString("noTelp"),
                                rs.getString("alamat"),
                                rs.getBoolean("status")
                        )
        );
    }

    @Override
    public List<Dokter> findAllDokterRepositorytrue() {
        return jdbcTemplate.query("select * from dokter where status =1",
                (rs,rowNum)->
                        new Dokter(
                                rs.getString("idDokter"),
                                rs.getString("namaDokter"),
                                rs.getString("tglPraktek"),
                                rs.getString("gender"),
                                rs.getString("noTelp"),
                                rs.getString("alamat"),
                                rs.getBoolean("status")
                        )
        );
    }

    @Override

        public void saveDokterRepository (Dokter dokter){

            UUID idDokter = UUID.randomUUID();
            jdbcTemplate.update("INSERT INTO dokter(idDokter, namaDokter,tglPraktek,gender,noTelp,alamat,status)values(?,?,?,?,?,?,?)",
                    idDokter.toString(),
                    dokter.getNamaDokter(), dokter.getTglPraktek(),
                    dokter.getGender(), dokter.getNoTelp(),
                    dokter.getAlamat(),
                    dokter.isStatus());

    }

    @Override
    public void updateDokterRepository(Dokter dokter) {
        jdbcTemplate.update("update dokter set namaDokter=? , tglPraktek=? ,gender=?,noTelp=?,alamat=?,status=? where idDokter=?",
                dokter.getNamaDokter(),dokter.getTglPraktek(),
                dokter.getGender(),dokter.getNoTelp(),
               dokter.getAlamat(),
                dokter.isStatus(),dokter.getIdDokter());
    }

    @Override
    public void deleteAllDokterRepository() {
        jdbcTemplate.update("delete from dokter ");
    }

    @Override
    public int deleteDokterRepositorybyId(String idDokter) {
        return jdbcTemplate.update("delete from dokter where idDokter = ?", idDokter);
    }

    @Override
    public Dokter findByIdDokterRepository(String idDokter) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from dokter where idDokter = ?",
                    new Object[]{idDokter},
                    (rs, rowNum) ->
                            new Dokter(
                                    rs.getString("idDokter"),
                                    rs.getString("namaDokter"),
                                    rs.getString("tglPraktek"),
                                    rs.getString("gender"),
                                    rs.getString("noTelp"),
                                    rs.getString("alamat"),
                                    rs.getBoolean("status")
                            ));
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Dokter findByNameDokterRepository(String namaDokter) {
        return jdbcTemplate.queryForObject(
                "select * from dokter where namaDokter like ?",
                new Object[]{"%"+namaDokter},
                (rs, rowNum) ->
                        new Dokter(
                                rs.getString("idDokter"),
                                rs.getString("namaDokter"),
                                rs.getString("tglPraktek"),
                                rs.getString("gender"),
                                rs.getString("noTelp"),
                                rs.getString("alamat"),
                                rs.getBoolean("status")
                        )

        );
    }

    @Override
    public void updateStatusRepositoryDokter(Dokter dokter) {
        jdbcTemplate.update("update dokter set status=? where idDokter=?",
                dokter.isStatus(),dokter.getIdDokter());
    }

    @Override
    public List<Dokter> findAllDokterWithPaging(int page, int limit) {
        int numPages;
        numPages = jdbcTemplate.query("SELECT COUNT(*) as count FROM dokter",
                (rs, rowNum) -> rs.getInt("count")).get(0);

        //validate page
        if (page < 1) page = 1;
        if (page > numPages) page = numPages;
        int start = (page - 1) * limit;
        List<Dokter> dokterList = jdbcTemplate.query("SELECT * FROM dokter LIMIT " + start + "," + limit + ";",
                (rs, rowNum) ->
                        new Dokter(

                                rs.getString("idDokter"),
                                rs.getString("namaDokter"),
                                rs.getString("tglPraktek"),
                                rs.getString("gender"),
                                rs.getString("noTelp"),
                                rs.getString("alamat"),
                                rs.getBoolean("status")
                        )
        );
        return dokterList;
    }
}
