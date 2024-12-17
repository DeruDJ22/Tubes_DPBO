package com.sayurbox.sayurbox.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Courier {
    @Id
    private Long kurirId;
    private String namaKurir;
    private String nomorKurir;

    // Getter dan Setter
    public Long getKurirId() {
        return kurirId;
    }

    public void setKurirId(Long kurirId) {
        this.kurirId = kurirId;
    }

    public String getNamaKurir() {
        return namaKurir;
    }

    public void setNamaKurir(String namaKurir) {
        this.namaKurir = namaKurir;
    }

    public String getNomorKurir() {
        return nomorKurir;
    }

    public void setNomorKurir(String nomorKurir) {
        this.nomorKurir = nomorKurir;
    }
}
