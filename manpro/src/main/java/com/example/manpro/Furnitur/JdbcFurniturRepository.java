package com.example.manpro.Furnitur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

    @Override
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
    public void updateStock(String name, String size, int newStock) {
        String sql = "UPDATE furnitur SET stok = ? WHERE nama = ? AND ukuran = ?";
        jdbcTemplate.update(sql, newStock, name, size);
    }
}