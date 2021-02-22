package com.example.demo.model;

import java.util.Date;

public class Pasien {
    private String idPasien ;
    private String namaPasien ;
    private String tempat ;
    private Date tglLahir ;
//    private String umur ;
    private String gender ;
    private String noTelp;
    private String alamat ;
    private boolean status ;

    public Pasien(){}


    public Pasien(String idPasien, String namaPasien, String tempat, Date tglLahir, String gender, String noTelp, String alamat, boolean status) {
        this.idPasien = idPasien;
        this.namaPasien = namaPasien;
        this.tempat = tempat;
        this.tglLahir = tglLahir;
        this.gender = gender;
        this.noTelp = noTelp;
        this.alamat = alamat;
        this.status = status;

    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

//    public String getUmur() {
//        return umur;
//    }
//
//    public void setUmur(String umur) {
//        this.umur = umur;
//    }

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

    @Override
    public String toString() {
        return "Pasien{" +
                "idPasien='" + idPasien + '\'' +
                ", namaPasien='" + namaPasien + '\'' +
                ", tempat='" + tempat + '\'' +
                ", tglLahir=" + tglLahir +
                ", gender='" + gender + '\'' +
                ", noTelp='" + noTelp + '\'' +
                ", alamat='" + alamat + '\'' +
                ", status=" + status +
                '}';
    }
}
