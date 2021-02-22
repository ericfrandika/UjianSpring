package com.example.demo.model;

import java.util.Date;

public class Dokter {
    private String idDokter;
    private String namaDokter;
    private String tglPraktek;
    private String gender;
    private String noTelp;
    private String alamat;
    private boolean status;


    public Dokter(String idDokter, String namaDokter, String tglPraktek, String gender, String noTelp, String alamat, boolean status) {
        this.idDokter = idDokter;
        this.namaDokter = namaDokter;
        this.tglPraktek = tglPraktek;
        this.gender = gender;
        this.noTelp = noTelp;
        this.alamat = alamat;
        this.status = status;
    }

    public String getTglPraktek() {
        return tglPraktek;
    }

    public void setTglPraktek(String tglPraktek) {
        this.tglPraktek = tglPraktek;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}