package com.developer.avengers.istiqomahin.act_rapor;

public class Rapor {
    private String id, namaIbadah, tercapai, target, status, tanggal;

    public Rapor() {
    }

    public Rapor(String id, String namaIbadah, String tercapai, String target, String status, String tanggal) {
        this.id = id;
        this.namaIbadah = namaIbadah;
        this.tercapai = tercapai;
        this.target = target;
        this.status = status;
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaIbadah() {
        return namaIbadah;
    }

    public void setNamaIbadah(String namaIbadah) {
        this.namaIbadah = namaIbadah;
    }

    public String getTercapai() {
        return tercapai;
    }

    public void setTercapai(String tercapai) {
        this.tercapai = tercapai;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
