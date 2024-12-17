package com.example.manpro.Komponen;

import java.sql.Date;
import java.util.List;

public interface KomponenRepository {
    List<Komponen> findByName(String keyword);
    String findTerlaris();
    void addKomponen (String nama, String ukuran, double harga, String gambar);
    Komponen findByNameAndSize(String nama, String ukuran);
    Komponen findById(Integer id);
    List<String> findMaterial(Integer id);
    List<String> findWarna(Integer id);
    int cekStok(Integer id);
    void updateStock(String namaBarang, String ukuran, int newStok, Date tanggal, int idKomponen, int prevStok);
    void updateHargaByNameAndSize(String nama, String ukuran, double harga);
    List<String> findAllMaterials();
    List<String> findAllColors();
    Integer findMaterialIdByName(String namaMaterial);
    Integer findColorIdByName(String namaWarna);
    void insertKomponenMaterialWarna(int idKomponen, int idMaterial, int idWarna);
    List<Komponen> findAll();
}
