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
    public String findTerlaris(){
        String sql = "SELECT * FROM KomponenTerlaris";
        return jdbcTemplate.queryForObject(sql, this::mapRow);
    }

    private String mapRow(ResultSet resultSet, int rowNum) throws SQLException{
        String nama = resultSet.getString("nama");
        Integer jumlah = resultSet.getInt("totalPesanan");
        return nama + " --- " + jumlah + " pesanan";
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
    public Komponen findById(Integer id){
        String sql = "SELECT * FROM komponen WHERE id=?";
        List<Komponen> komponen = jdbcTemplate.query(sql, new Object[]{id}, this::mapRowToKomponen);
        if(komponen == null || komponen.isEmpty()){
            return null;
        }else{
            return komponen.get(0);
        }
    }

    @Override
    public List<String> findMaterial(Integer id){
        String sql = "SELECT nama FROM KomponenMaterial WHERE idKomponen=?";
        return jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> rs.getString("nama"));
    }

    @Override
    public List<String> findWarna(Integer id){
        String sql = "SELECT nama FROM KomponenWarna WHERE idKomponen=?";
        return jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> rs.getString("nama"));
    }

    @Override
    public int cekStok(Integer id){
        String sql = "SELECT stok FROM Komponen WHERE id=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }

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
}