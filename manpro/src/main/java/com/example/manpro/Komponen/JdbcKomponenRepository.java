package com.example.manpro.Komponen;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcKomponenRepository implements KomponenRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Komponen> findAll(){
        String sql = "SELECT * FROM Komponen";
        return jdbcTemplate.query(sql, this::mapRowToKomponen);
    }

    private Komponen mapRowToKomponen(ResultSet resultSet, int rowNum) throws SQLException{
        return new Komponen(
            resultSet.getInt("id"),
            resultSet.getString("nama"),
            resultSet.getString("ukuran"),
            resultSet.getInt("stok"),
            resultSet.getDouble("harga"),
            resultSet.getString("gambar")
        );
    }

    @Override
    public List<Komponen> findByName(String keyword){
        String sql = "SELECT * FROM komponen WHERE nama ILIKE ?";
        return jdbcTemplate.query(sql, this::mapRowToKomponen, "%" + keyword + "%");
    }

    @Override
    public void addKomponen (String nama, String ukuran, double harga, String gambar){
        String sql = "INSERT INTO komponen (nama, ukuran, harga, gambar) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, nama, ukuran, harga, gambar);
    }

    @Override
    public Komponen findByNameAndSize(String nama, String ukuran) {
        String sql = "SELECT * FROM komponen WHERE nama ILIKE ? AND ukuran ILIKE ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToKomponen, "%" + nama + "%", "%" + ukuran + "%");
    }
    
    @Override
    public void updateStock(String name, String size, int newStock, Date tanggal, int idKomponen, int prevStok) {
        String sql = "UPDATE komponen SET stok = ? WHERE nama = ? AND ukuran = ?";
        jdbcTemplate.update(sql, newStock, name, size);

        String insert = "INSERT INTO transaksi (idKomponen, stok, tanggal) VALUES (?,?,?)";
        jdbcTemplate.update(insert, idKomponen, newStock-prevStok, tanggal);
    }

    @Override
    public void updateHargaByNameAndSize(String nama, String ukuran, double harga) {
        String sql = "UPDATE komponen SET harga = ? WHERE nama = ? AND ukuran = ?";
        jdbcTemplate.update(sql, harga, nama, ukuran);
    }

    @Override
    public List<String> findAllMaterials() {
        String sql = "SELECT nama FROM Material";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getString("nama"));
    }

    @Override
    public List<String> findAllColors() {
        String sql = "SELECT nama FROM Warna";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getString("nama"));
    }

    @Override
    public Integer findMaterialIdByName(String namaMaterial) {
        String sql = "SELECT id FROM Material WHERE nama = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, namaMaterial);
    }

    @Override
    public Integer findColorIdByName(String namaWarna) {
        String sql = "SELECT id FROM Warna WHERE nama = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, namaWarna);
    }

    @Override
    public void insertKomponenMaterialWarna(int idKomponen, int idMaterial, int idWarna) {
        String sql = "INSERT INTO komponenMaterialWarna (idKomponen, idMaterial, idWarna) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, idKomponen, idMaterial, idWarna);
    }


    // Cek apakah komponen sudah ada
    // @Override
    // public boolean isKomponenExists(String nama, String ukuran) {
    //     String sql = "SELECT COUNT(*) FROM komponen WHERE nama = ? AND ukuran = ?";
    //     Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nama, ukuran);
    //     return count != null && count > 0;
    // }

    // @Override
    // // Ambil id komponen berdasarkan nama dan ukuran
    // public int getKomponenId(String nama, String ukuran) {
    //     String sql = "SELECT id FROM komponen WHERE nama = ? AND ukuran = ?";
    //     return jdbcTemplate.queryForObject(sql, Integer.class, nama, ukuran);
    // }

    // @Override
    // public int addKomponenAndReturnId(String nama, String ukuran) {
    //     String sql = "INSERT INTO komponen (nama, ukuran, stok, harga, gambar) VALUES (?, ?, 0, 0.0, NULL) RETURNING id";
    //     return jdbcTemplate.queryForObject(sql, Integer.class, nama, ukuran);
    // }
    

    // // Tambah ke komponenMaterialWarna
    // @Override
    // public void addKomponenMaterialWarna(int idKomponen, int idMaterial, int idWarna) {
    //     String checkSql = "SELECT COUNT(*) FROM komponenMaterialWarna WHERE id_komponen = ? AND id_material = ? AND id_warna = ?";
    //     Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, idKomponen, idMaterial, idWarna);

    //     if (count == null || count == 0) {
    //         String insertSql = "INSERT INTO komponenMaterialWarna (id_komponen, id_material, id_warna) VALUES (?, ?, ?)";
    //         jdbcTemplate.update(insertSql, idKomponen, idMaterial, idWarna);
    //     } else {
    //         throw new IllegalArgumentException("Kombinasi komponen, material, dan warna sudah ada.");
    //     }
    // }

    
    
    

}