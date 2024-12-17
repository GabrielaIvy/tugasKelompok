package com.example.manpro.Komponen;

import java.sql.Date;
import java.util.List;

public interface KomponenRepository {
    List<Komponen> findByName(String keyword);
    String findTerlaris();
    void addKomponen (String nama, String ukuran, double harga, String gambar);
    Komponen findByNameAndSize(String nama, String ukuran);
    void updateStock(String namaBarang, String ukuran, int newStok, Date tanggal, int idKomponen, int prevStok);
    void updateHargaByNameAndSize(String nama, String ukuran, double harga);
    List<String> findAllMaterials();
    List<String> findAllColors();
    Integer findMaterialIdByName(String namaMaterial);
    Integer findColorIdByName(String namaWarna);
    void insertKomponenMaterialWarna(int idKomponen, int idMaterial, int idWarna);
    //void addKomponenMaterialWarna(int idKomponen, int idMaterial, int idWarna);
    //int addKomponenAndReturnId(String nama, String ukuran);
    //int getKomponenId(String nama, String ukuran) ;
    //boolean isKomponenExists(String nama, String ukuran);
    List<Komponen> findAll();
}
