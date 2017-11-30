package com.developer.avengers.istiqomahin.act_ibadah;

public class Data {
    private String id, nama, kategori, tipe, satuan, target, user_id;

    public Data() {
    }

    public Data(String id, String nama, String kategori, String tipe, String satuan, String target, String user_id) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.tipe = tipe;
        this.satuan = satuan;
        this.target = target;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
