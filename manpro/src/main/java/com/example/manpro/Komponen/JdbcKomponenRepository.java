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
}