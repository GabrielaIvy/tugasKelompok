package com.example.manpro.Komponen;

import java.util.List;

public interface KomponenRepository {
    List<Komponen> findAll();
    List<Komponen> findByName(String keyword);
    String findTerlaris();
    void addKomponen (String nama, String ukuran, double harga, String gambar);
    Komponen findByNameAndSize(String nama, String ukuran);
    void updateStock(String namaBarang, String ukuran, int newStok);
    Komponen findById(Integer id);
    List<String> findMaterial(Integer id);
    List<String> findWarna(Integer id);
    int cekStok(Integer id);
}
