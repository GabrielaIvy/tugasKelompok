package com.example.manpro.Furnitur;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcFurniturRepository implements FurniturRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Furnitur> findAll(){
        String sql = "SELECT * FROM Furnitur";
        return jdbcTemplate.query(sql, this::mapRowToFurnitur);
    }

    private Furnitur mapRowToFurnitur(ResultSet resultSet, int rowNum) throws SQLException{
        return new Furnitur(
            resultSet.getInt("id"),
            resultSet.getString("nama"),
            resultSet.getString("ukuran"),
            resultSet.getInt("stok"),
            resultSet.getDouble("harga"),
            resultSet.getString("gambar")
        );
    }

    @Override
    public List<Furnitur> findByName(String keyword){
        String sql = "SELECT * FROM furnitur WHERE nama ILIKE ?";
        return jdbcTemplate.query(sql, this::mapRowToFurnitur, "%" + keyword + "%");
    }

    @Override
    public String findTerlaris(){
        String sql = "SELECT * FROM FurniturTerlaris";
        return jdbcTemplate.queryForObject(sql, this::mapRow);
    }

    private String mapRow(ResultSet resultSet, int rowNum) throws SQLException{
        String nama = resultSet.getString("nama");
        Integer jumlah = resultSet.getInt("totalPesanan");
        return nama + " --- " + jumlah + " pesanan";
    }

    @Override
    public void addFurnitur (String nama, String ukuran, double harga, String gambar){
        String sql = "INSERT INTO furnitur (nama, ukuran, harga, gambar) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, nama, ukuran, harga, gambar);
    }

    @Override
    public Furnitur findByNameAndSize(String nama, String ukuran) {
        String sql = "SELECT * FROM furnitur WHERE nama ILIKE ? AND ukuran ILIKE ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToFurnitur, "%" + nama + "%", "%" + ukuran + "%");    
    }

    @Override
    public void updateStock(String name, String size, int newStock, Date tanggal, int idFurnitur, int prevStok) {
        String sql = "UPDATE furnitur SET stok = ? WHERE nama = ? AND ukuran = ?";
        jdbcTemplate.update(sql, newStock, name, size);

        String insert = "INSERT INTO transaksi (idFurnitur, stok, tanggal) VALUES (?,?,?)";
        jdbcTemplate.update(insert, idFurnitur, newStock-prevStok, tanggal);
    }

    @Override
    public Furnitur findById(Integer id){
        String sql = "SELECT * FROM furnitur WHERE id=?";
        List<Furnitur> furnitur = jdbcTemplate.query(sql, new Object[]{id}, this::mapRowToFurnitur);
        if(furnitur == null || furnitur.isEmpty()){
            return null;
        }else{
            return furnitur.get(0);
        }
    }

    @Override
    public List<FurniturKomponen> findKomponen(Integer id){
        String sqlKomponen = "SELECT id, nama FROM GetKomponen WHERE idFurnitur=?";
        String sqlMaterial = "SELECT nama FROM KomponenMaterial WHERE idKomponen=?";
        String sqlWarna = "SELECT nama FROM KomponenWarna WHERE idKomponen=?";

        return jdbcTemplate.query(sqlKomponen, new Object[]{id}, (rs, rowNum) -> {
            Integer idKomponen = rs.getInt("id");
            String nama = rs.getString("nama");

            List<String> material = jdbcTemplate.query(
                sqlMaterial,
                new Object[]{idKomponen},
                (rsMaterial, rowNumMaterial) -> rsMaterial.getString("nama")
            );

            List<String> warna = jdbcTemplate.query(
                sqlWarna,
                new Object[]{idKomponen},
                (rsWarna, rowNumWarna) -> rsWarna.getString("nama")
            );

            return new FurniturKomponen(idKomponen, nama, material, warna);
        });
    }

    @Override
    public int cekStok(Integer id){
        String sql = "SELECT stok FROM Furnitur WHERE id=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }

    public void updateHargaByNameAndSize(String nama, String ukuran, double harga) {
        String sql = "UPDATE furnitur SET harga = ? WHERE nama = ? AND ukuran = ?";
        jdbcTemplate.update(sql, harga, nama, ukuran);
    }

    @Override
    public void insertKomponenFurnitur(int idFurnitur, int idKomponen){
        String sql = "INSERT INTO komponenFurnitur (idFurnitur, idKomponen) VALUES (?,?)";
        jdbcTemplate.update(sql, idFurnitur, idKomponen);
    }
}