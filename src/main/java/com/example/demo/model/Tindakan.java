package com.example.demo.model;

public class Tindakan {
    private String idTindakan;
    private String namaTindakan;
    private int biayaTindakan;
    private boolean status;

    public Tindakan(String idTindakan, String namaTindakan, int biayaTindakan, boolean status) {
        this.idTindakan = idTindakan;
        this.namaTindakan = namaTindakan;
        this.biayaTindakan = biayaTindakan;
        this.status = status;
    }

    public String getIdTindakan() {
        return idTindakan;
    }

    public void setIdTindakan(String idTindakan) {
        this.idTindakan = idTindakan;
    }

    public String getNamaTindakan() {
        return namaTindakan;
    }

    public void setNamaTindakan(String namaTindakan) {
        this.namaTindakan = namaTindakan;
    }

    public int getBiayaTindakan() {
        return biayaTindakan;
    }

    public void setBiayaTindakan(int biayaTindakan) {
        this.biayaTindakan = biayaTindakan;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tindakan{" +
                "idTindakan='" + idTindakan + '\'' +
                ", namaTindakan='" + namaTindakan + '\'' +
                ", biayaTindakan=" + biayaTindakan +
                ", status=" + status +
                '}';
    }
}