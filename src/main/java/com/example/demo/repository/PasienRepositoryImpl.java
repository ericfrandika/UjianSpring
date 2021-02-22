package com.example.demo.repository;

import com.example.demo.model.Pasien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("PasienRepository")
public class PasienRepositoryImpl implements PasienRepository{


    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Pasien> findAllPasienRepository() {
            return jdbcTemplate.query("select *from pasien",
                    (rs,rowNum)->
                            new Pasien(
                                    rs.getString("idPasien"),
                                    rs.getString("namaPasien"),
                                    rs.getString("tempat"),
                                    rs.getDate("tglLahir"),
                                    rs.getString("gender"),
                                    rs.getString("noTelp"),
                                    rs.getString("alamat"),
                                    rs.getBoolean("status")

                            )
            );
    }

    @Override
    public List<Pasien> findAllPasienRepositorytrue() {
        return jdbcTemplate.query("select *from pasien where status = 1",
                (rs,rowNum)->
                        new Pasien(
                                rs.getString("idPasien"),
                                rs.getString("namaPasien"),
                                rs.getString("tempat"),
                                rs.getDate("tglLahir"),
                                rs.getString("gender"),
                                rs.getString("noTelp"),
                                rs.getString("alamat"),
                                rs.getBoolean("status")

                        )
        );
    }

    @Override
    public void savePasienRepository(Pasien pasien) {
        UUID idPasien = UUID.randomUUID();
        jdbcTemplate.update("INSERT INTO pasien(idPasien, namaPasien,tempat,tglLahir,gender,noTelp,alamat,status)values(?,?,?,?,?,?,?,?)",
                idPasien.toString(),
                pasien.getNamaPasien(),pasien.getTempat(),
                pasien.getTglLahir(),pasien.getGender(),
                pasien.getNoTelp(),pasien.getAlamat(),
                pasien.isStatus());
    }

    @Override
    public void updatePasienRepository(Pasien pasien) {
        jdbcTemplate.update("update pasien set namaPasien=? , tempat=? ,tglLahir=? ,gender=?,noTelp=?,alamat=?,status=? where idPasien=?",
                pasien.getNamaPasien(),pasien.getTempat(),
                pasien.getTglLahir(),pasien.getGender(),
                pasien.getNoTelp(),pasien.getAlamat(),
                pasien.isStatus(),pasien.getIdPasien());
    }

    @Override
    public void deleteAllPasienRepository() {
        jdbcTemplate.update("delete from pasien ");
    }

    @Override
    public int deletePasienRepositorybyId(String idPasien) {
        return jdbcTemplate.update("delete from pasien where idPasien = ?", idPasien);
    }

    @Override
    public Pasien findByIdPasienRepository(String idPasien) {
        return jdbcTemplate.queryForObject(
                "select * from pasien where idPasien = ?",
                new Object[]{idPasien},
                (rs, rowNum) ->
                        new Pasien(
                                rs.getString("idPasien"),
                                rs.getString("namaPasien"),
                                rs.getString("tempat"),
                                rs.getDate("tglLahir"),
                                rs.getString("gender"),
                                rs.getString("noTelp"),
                                rs.getString("alamat"),
                                rs.getBoolean("status")
                        ));
    }

    @Override
    public Pasien findByNamePasienRepository(String namaPasien) {
        return jdbcTemplate.queryForObject(
                "select * from pasien where namaPasien like ?",
                new Object[]{"%"+namaPasien},
                (rs, rowNum) ->
                        new Pasien(
                                rs.getString("idPasien"),
                                rs.getString("namaPasien"),
                                rs.getString("tempat"),
                                rs.getDate("tglLahir"),
                                rs.getString("gender"),
                                rs.getString("noTelp"),
                                rs.getString("alamat"),
                                rs.getBoolean("status")
                        )

        );
    }

    @Override
    public void updateStatusRepositoryPasien(Pasien pasien) {
        jdbcTemplate.update("update pasien set status=? where idPasien=?",
               pasien.isStatus(),pasien.getIdPasien());
    }

    @Override
    public List<Pasien> findAllPasienWithPaging(int page, int limit) {
        int numPages;
        numPages = jdbcTemplate.query("SELECT COUNT(*) as count FROM pasien",
              (rs, rowNum) -> rs.getInt("count")).get(0);

     //validate page
      if (page < 1) page = 1;
       if (page > numPages) page = numPages;
       int start = (page - 1) * limit;
       List<Pasien> pasienList = jdbcTemplate.query("SELECT * FROM pasien LIMIT " + start + "," + limit + ";",
                        (rs, rowNum) ->
                               new Pasien(
                                       rs.getString("idPasien"),
                                       rs.getString("namaPasien"),
                                       rs.getString("tempat"),
                                       rs.getDate("tglLahir"),
                                       rs.getString("gender"),
                                       rs.getString("noTelp"),
                                       rs.getString("alamat"),
                                       rs.getBoolean("status")
                               )
               );
    return pasienList;
    }
}
