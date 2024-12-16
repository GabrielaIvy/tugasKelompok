package com.example.manpro.Furnitur;

import java.util.List;

public interface FurniturRepository {
    List<Furnitur> findAll();
    List<Furnitur> findByName(String keyword);
    String findTerlaris();
    void addFurnitur (String nama, String ukuran, double harga, String gambar);
    void updateStock(String namaBarang, String ukuran, int jumlah);
    Furnitur findByNameAndSize(String nama, String ukuran);
    Furnitur findById(Integer id);
    List<FurniturKomponen> findKomponen(Integer id);
    int cekStok(Integer id);
}
