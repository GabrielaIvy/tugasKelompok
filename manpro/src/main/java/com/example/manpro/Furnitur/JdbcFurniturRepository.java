package com.example.manpro.Furnitur;

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
}