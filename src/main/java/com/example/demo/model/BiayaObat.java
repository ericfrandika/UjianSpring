package com.example.demo.model;

public class BiayaObat {
    private String idObat;
    private String namaObat;
    private int qty;
    private int harga;
    private int totalHarga;

    private boolean status;


    public BiayaObat(){

    }

    public BiayaObat(String idObat, String namaObat, int qty, int harga, int totalHarga, boolean status) {
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.qty = qty;
        this.harga = harga;
        this.totalHarga = totalHarga;
        this.status = status;
    }

    public BiayaObat(String idObat, String namaObat, int qty, int harga, boolean status) {
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.qty = qty;
        this.harga = harga;
        this.status = status;
    }
    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BiayaObat{" +
                "idObat='" + idObat + '\'' +
                ", namaObat='" + namaObat + '\'' +
                ", qty=" + qty +
                ", harga=" + harga +
                ", totalHarga=" + totalHarga +
                ", status=" + status +
                '}';
    }
}

