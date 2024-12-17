package com.example.manpro.Furnitur;

import java.sql.Date;
import java.util.List;

public interface FurniturRepository {
    List<Furnitur> findAll();
    List<Furnitur> findByName(String keyword);
    String findTerlaris();
    void addFurnitur (String nama, String ukuran, double harga, String gambar);
    void updateStock(String namaBarang, String ukuran, int jumlah, Date tanggal, int idFurnitur, int prevStok);
    Furnitur findByNameAndSize(String nama, String ukuran);
    void updateHargaByNameAndSize(String nama, String ukuran, double harga);
    void insertKomponenFurnitur(int idFurnitur, int idKomponen);

}
