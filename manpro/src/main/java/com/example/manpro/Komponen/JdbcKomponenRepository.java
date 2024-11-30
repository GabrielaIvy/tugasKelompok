package com.example.manpro.Komponen;

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
    public void updateStock(String name, String size, int newStock) {
        String sql = "UPDATE komponen SET stok = ? WHERE nama = ? AND ukuran = ?";
        jdbcTemplate.update(sql, newStock, name, size);
    }
}