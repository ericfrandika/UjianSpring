package com.example.demo.model;

import java.util.Date;
import java.util.List;

public class Report {
    private String idTransaction;
    private String idPasien;
    private String idDokter;
    private Date tglTransaction;
    List<BiayaObat>biayaObatList;
    private double totalHargaObat;
    private double ppnObat;
    private double totalPembayaranObat;
    List<Tindakan>tindakanList;
    private double totalHargaTindakan;
    private double ppnTindakan;
    private double totalTindakan;

    public Report(String idTransaction, String idPasien, String idDokter, Date tglTransaction, List<BiayaObat> biayaObatList, double totalHargaObat, double ppnObat, double totalPembayaranObat, List<Tindakan> tindakanList, double totalHargaTindakan, double ppnTindakan, double totalTindakan) {
        this.idTransaction = idTransaction;
        this.idPasien = idPasien;
        this.idDokter = idDokter;
        this.tglTransaction = tglTransaction;
        this.biayaObatList = biayaObatList;
        this.totalHargaObat = totalHargaObat;
        this.ppnObat = ppnObat;
        this.totalPembayaranObat = totalPembayaranObat;
        this.tindakanList = tindakanList;
        this.totalHargaTindakan = totalHargaTindakan;
        this.ppnTindakan = ppnTindakan;
        this.totalTindakan = totalTindakan;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public Date getTglTransaction() {
        return tglTransaction;
    }

    public void setTglTransaction(Date tglTransaction) {
        this.tglTransaction = tglTransaction;
    }

    public List<BiayaObat> getBiayaObatList() {
        return biayaObatList;
    }

    public void setBiayaObatList(List<BiayaObat> biayaObatList) {
        this.biayaObatList = biayaObatList;
    }

    public List<Tindakan> getTindakanList() {
        return tindakanList;
    }

    public void setTindakanList(List<Tindakan> tindakanList) {
        this.tindakanList = tindakanList;
    }

    ////////////////////Hitung Total Obat-----------------------


    public double getTotalHargaObat() {
        return totalHargaObat;
    }

    public void setTotalHargaObat() {
        double totalHargaObat = 0;
        for (int i = 0; i < biayaObatList.size(); i++) {
            totalHargaObat += biayaObatList.get(i).getTotalHarga();
        }
        this.totalHargaObat = totalHargaObat;
    }

    public double getPpnObat() {
        return ppnObat;
    }

    public void setPpnObat() {
        double ppnObat = 0;
        ppnObat = (getTotalHargaObat()*0.1);
        this.ppnObat = ppnObat;
    }

    public double getTotalPembayaranObat() {
        return totalPembayaranObat;
    }

    public void setTotalPembayaranObat() {
        double totalPembayaranObat = 0;
        totalPembayaranObat = (getTotalHargaObat()+getPpnObat());
        this.totalPembayaranObat = totalPembayaranObat;
    }

    ///--------------------------Hitung Total Pembayaran tindakan---------------------

    public double getTotalHargaTindakan() {
        return totalHargaTindakan;
    }

    public void setTotalHargaTindakan() {
        double totalHargaTindakan = 0;
        for (int i = 0; i < tindakanList.size(); i++) {
            totalHargaTindakan += tindakanList.get(i).getBiayaTindakan();
        }
        this.totalHargaTindakan = totalHargaTindakan;
    }

    public double getPpnTindakan() {
        return ppnTindakan;
    }

    public void setPpnTindakan() {
        double ppnTindakan = 0;
        ppnTindakan = (getTotalHargaTindakan()*0.1);
        this.ppnTindakan = ppnTindakan;
    }

    public double getTotalTindakan() {
        return totalTindakan;
    }

    public void setTotalTindakan() {
        double totalTindakan = 0;
        totalTindakan = (getTotalHargaTindakan()+getPpnTindakan());
        this.totalTindakan = totalTindakan;
    }
}
