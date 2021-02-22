package com.example.demo.repository;

import com.example.demo.model.*;
import com.example.demo.service.DokterService;
import com.example.demo.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("reportRepository")
public class ReportRepositoryImpl implements ReportRepository {

@Autowired
    PasienService pasienService;
@Autowired
    DokterService dokterService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void saveReport(Report report) {
        UUID reportID = UUID.randomUUID();
        jdbcTemplate.update("INSERT INTO report(idTransaction,idPasien,idDokter,tglTransaction,status) VALUES (?,?,?,?,?)",
                reportID.toString(),
                report.getIdPasien(),
                report.getIdDokter(),
                report.getTglTransaction(),
                report.isStatus()
        );
        List<BiayaObat> biayaObatList = report.getBiayaObatList();
        for (int i = 0; i < biayaObatList.size(); i++) {
            UUID idDetailObat = UUID.randomUUID();
                jdbcTemplate.update(
                        "INSERT INTO detailobat(idDetailObat,idTransaction,idObat,qty)" +
                                "VALUES (?,?,?,?)",
                        idDetailObat.toString(),
                        reportID.toString(),
                        biayaObatList.get(i).getIdObat(),
                        biayaObatList.get(i).getQty()
                );
            }


        List<Tindakan> tindakanList = report.getTindakanList();
        for (int i = 0; i < tindakanList.size(); i++) {
            UUID idDetailTindakan = UUID.randomUUID();
            jdbcTemplate.update(
                    "INSERT INTO detailtindakan(idDetailTindakan,idTransaction,idTindakan)" +
                            "VALUES (?,?,?)",
                    idDetailTindakan.toString(),
                    reportID.toString(),
                    tindakanList.get(i).getIdTindakan()
            );

        }


    }

    @Override
    public List<Report> findAllReport() {
        List<Report> reportList = jdbcTemplate.query("select *from report",
                (rs, rowNum) ->
                        new Report(
                                rs.getString("idTransaction"),
                                rs.getString("idPasien"),
                                null,
                                rs.getString("idDokter"),
                                null,
                                rs.getString("tglTransaction"),
                                rs.getBoolean("status"),
                                null,
                                0,
                                0,
                                0,
                                null,
                                0,
                                0,
                                0,
                                0
                        )
        );

        for (Report report : reportList) {
            report.setBiayaObatList(jdbcTemplate.query("select ob.idObat, ob.namaObat, db.qty, ob.harga ,ob.status,db.qty*ob.harga as totalHarga  from " +
                            "detailobat db Join obat ob ON db.idObat = ob.idObat where db.idTransaction=? ",
                    preparedStatement -> preparedStatement.setString(1, report.getIdTransaction()),
                    (rs, rowNum) -> new BiayaObat(
                            rs.getString("idObat"),
                            rs.getString("namaObat"),
                            rs.getInt("qty"),
                            rs.getInt("harga"),
                            rs.getInt("totalHarga"),
                            rs.getBoolean("status")

                    )
                    )
            );
            report.setTindakanList(jdbcTemplate.query("select td.idTindakan, td.namaTindakan, td.biayaTindakan,td.status from " +
                            "detailtindakan dt Join tindakan td ON dt.idTindakan = td.idTindakan where dt.idTransaction=? ",
                    preparedStatement -> preparedStatement.setString(1, report.getIdTransaction()),
                    (rs, rowNum) -> new Tindakan(
                            rs.getString("idTindakan"),
                            rs.getString("namaTindakan"),
                            rs.getInt("biayaTindakan"),
                         rs.getBoolean("status")

                    )
            )
            );
        report.setTotalHargaObat();
        report.setPpnObat();
        report.setTotalPembayaranObat();
        report.setTotalHargaTindakan();
        report.setPpnTindakan();
        report.setTotalTindakan();
        report.setTotalAll();
        report.setDokter(dokterService.findByIdDokterService(report.getIdDokter()));
        report.setPasien(pasienService.findByIdPasienService(report.getIdPasien()));

        }
        return reportList;

    }



    @Override
    public Report findByIdReportRepository(String idTransaction) {
        Report report;
      report = jdbcTemplate.queryForObject("select *from report where  idTransaction =?",
                new Object[]{idTransaction},
                (rs, rowNum) ->
                        new Report(
                                rs.getString("idTransaction"),
                                rs.getString("idPasien"),
                                null,
                                rs.getString("idDokter"),
                                null,
                                rs.getString("tglTransaction"),
                                rs.getBoolean("status"),
                                null,
                                0,
                                0,
                                0,
                                null,
                                0,
                                0,
                                0,
                                0
                        )
        );

            report.setBiayaObatList(jdbcTemplate.query("select ob.idObat, ob.namaObat, db.qty, ob.harga ,ob.status,db.qty*ob.harga as totalHarga  from " +
                            "detailobat db Join obat ob ON db.idObat = ob.idObat where db.idTransaction=? ",
                    preparedStatement -> preparedStatement.setString(1, report.getIdTransaction()),
                    (rs, rowNum) -> new BiayaObat(
                            rs.getString("idObat"),
                            rs.getString("namaObat"),
                            rs.getInt("qty"),
                            rs.getInt("harga"),
                            rs.getInt("totalHarga"),
                            rs.getBoolean("status")

                    )
                    )
            );
            report.setTindakanList(jdbcTemplate.query("select td.idTindakan, td.namaTindakan, td.biayaTindakan,td.status from " +
                            "detailtindakan dt Join tindakan td ON dt.idTindakan = td.idTindakan where dt.idTransaction=? ",
                    preparedStatement -> preparedStatement.setString(1, report.getIdTransaction()),
                    (rs, rowNum) -> new Tindakan(
                            rs.getString("idTindakan"),
                            rs.getString("namaTindakan"),
                            rs.getInt("biayaTindakan"),
                            rs.getBoolean("status")

                    )
                    )
            );
            report.setTotalHargaObat();
            report.setPpnObat();
            report.setTotalPembayaranObat();
            report.setTotalHargaTindakan();
            report.setPpnTindakan();
            report.setTotalTindakan();
            report.setDokter(dokterService.findByIdDokterService(report.getIdDokter()));
            report.setPasien(pasienService.findByIdPasienService(report.getIdPasien()));
             report.setTotalAll();


        return report;
    }

    @Override
    public List<Report> findAllReportStatus() {
        List<Report> reportList = jdbcTemplate.query("select *from report where status = 1",
                (rs, rowNum) ->
                        new Report(
                                rs.getString("idTransaction"),
                                rs.getString("idPasien"),
                                null,
                                rs.getString("idDokter"),
                                null,
                                rs.getString("tglTransaction"),
                                rs.getBoolean("status"),
                                null,
                                0,
                                0,
                                0,
                                null,
                                0,
                                0,
                                0,
                                0
                        )
        );

        for (Report report : reportList) {
            report.setBiayaObatList(jdbcTemplate.query("select ob.idObat, ob.namaObat, db.qty, ob.harga ,ob.status,db.qty*ob.harga as totalHarga  from " +
                            "detailobat db Join obat ob ON db.idObat = ob.idObat where db.idTransaction=? ",
                    preparedStatement -> preparedStatement.setString(1, report.getIdTransaction()),
                    (rs, rowNum) -> new BiayaObat(
                            rs.getString("idObat"),
                            rs.getString("namaObat"),
                            rs.getInt("qty"),
                            rs.getInt("harga"),
                            rs.getInt("totalHarga"),
                            rs.getBoolean("status")

                    )
                    )
            );
            report.setTindakanList(jdbcTemplate.query("select td.idTindakan, td.namaTindakan, td.biayaTindakan,td.status from " +
                            "detailtindakan dt Join tindakan td ON dt.idTindakan = td.idTindakan where dt.idTransaction=? ",
                    preparedStatement -> preparedStatement.setString(1, report.getIdTransaction()),
                    (rs, rowNum) -> new Tindakan(
                            rs.getString("idTindakan"),
                            rs.getString("namaTindakan"),
                            rs.getInt("biayaTindakan"),
                            rs.getBoolean("status")

                    )
                    )
            );
            report.setTotalHargaObat();
            report.setPpnObat();
            report.setTotalPembayaranObat();
            report.setTotalHargaTindakan();
            report.setPpnTindakan();
            report.setTotalTindakan();
            report.setDokter(dokterService.findByIdDokterService(report.getIdDokter()));
            report.setPasien(pasienService.findByIdPasienService(report.getIdPasien()));
            report.setTotalAll();

        }
        return reportList;
    }

    @Override
    public void updateStatusRepositoryReport(Report report) {
            jdbcTemplate.update("update report set status=? where idTransaction=?",
                    report.isStatus(),report.getIdPasien());
    }

    @Override
    public void updateListRepositoryReport(Report report) {
            jdbcTemplate.update("update report set idPasien=? , idDokter=? ,tglTransaction=? where idTransaction=?",
                    report.getIdPasien(),report.getIdDokter(),
                    report.getTglTransaction(),report.getIdTransaction());
        jdbcTemplate.update("delete from detailobat where idTransaction=?", report.getIdTransaction());
        jdbcTemplate.update("delete from detailtindakan where idTransaction=?", report.getIdTransaction());
        List<BiayaObat> biayaObatList = report.getBiayaObatList();
        for (int i = 0; i < biayaObatList.size(); i++) {
            UUID idDetailObat = UUID.randomUUID();
            jdbcTemplate.update(
                    "INSERT INTO detailobat(idDetailObat,idTransaction,idObat,qty)" +
                            "VALUES (?,?,?,?)",
                    idDetailObat.toString(),
                    report.getIdTransaction(),
                    biayaObatList.get(i).getIdObat(),
                    biayaObatList.get(i).getQty()
            );
        }

        List<Tindakan> tindakanList = report.getTindakanList();
        for (int i = 0; i < tindakanList.size(); i++) {
            UUID idDetailTindakan = UUID.randomUUID();
            jdbcTemplate.update(
                    "INSERT INTO detailtindakan(idDetailTindakan,idTransaction,idTindakan)" +
                            "VALUES (?,?,?)",
                    idDetailTindakan.toString(),
                    report.getIdTransaction(),
                    tindakanList.get(i).getIdTindakan()
            );

        }
    }

    public List<Report> findAllReportWithPaging(int page, int limit) {
        int numPages;
        numPages = jdbcTemplate.query("SELECT COUNT(*) as count FROM report",
                (rs, rowNum) -> rs.getInt("count")).get(0);

        //validate page
        if (page < 1) page = 1;
        if (page > numPages) page = numPages;
        int start = (page - 1) * limit;
        List<Report> reportList = jdbcTemplate.query("SELECT * FROM report LIMIT " + start + "," + limit + ";",
                (rs, rowNum) ->
                        new Report(
                                rs.getString("idTransaction"),
                                rs.getString("idPasien"),
                                null,
                                rs.getString("idDokter"),
                                null,
                                rs.getString("tglTransaction"),
                                rs.getBoolean("status"),
                                null,
                                0,
                                0,
                                0,
                                null,
                                0,
                                0,
                                0,
                                0
                        )
        );

        for (Report report : reportList) {
            report.setBiayaObatList(jdbcTemplate.query("select ob.idObat, ob.namaObat, db.qty, ob.harga ,ob.status,db.qty*ob.harga as totalHarga  from " +
                            "detailobat db Join obat ob ON db.idObat = ob.idObat where db.idTransaction=? ",
                    preparedStatement -> preparedStatement.setString(1, report.getIdTransaction()),
                    (rs, rowNum) -> new BiayaObat(
                            rs.getString("idObat"),
                            rs.getString("namaObat"),
                            rs.getInt("qty"),
                            rs.getInt("harga"),
                            rs.getInt("totalHarga"),
                            rs.getBoolean("status")

                    )
                    )
            );
            report.setTindakanList(jdbcTemplate.query("select td.idTindakan, td.namaTindakan, td.biayaTindakan,td.status from " +
                            "detailtindakan dt Join tindakan td ON dt.idTindakan = td.idTindakan where dt.idTransaction=? ",
                    preparedStatement -> preparedStatement.setString(1, report.getIdTransaction()),
                    (rs, rowNum) -> new Tindakan(
                            rs.getString("idTindakan"),
                            rs.getString("namaTindakan"),
                            rs.getInt("biayaTindakan"),
                            rs.getBoolean("status")

                    )
                    )
            );
            report.setTotalHargaObat();
            report.setPpnObat();
            report.setTotalPembayaranObat();
            report.setTotalHargaTindakan();
            report.setPpnTindakan();
            report.setTotalTindakan();
            report.setDokter(dokterService.findByIdDokterService(report.getIdDokter()));
            report.setPasien(pasienService.findByIdPasienService(report.getIdPasien()));
            report.setTotalAll();


        }
        return reportList;
    }

    @Override
    public void deleteReportRepositorybyId(String idTransaction) {
        jdbcTemplate.update("delete from detailobat where idTransaction = ?",idTransaction);
        jdbcTemplate.update("delete from detailtindakan where idTransaction = ?",idTransaction);
         jdbcTemplate.update("delete from report where idTransaction = ?", idTransaction);


    }


}
