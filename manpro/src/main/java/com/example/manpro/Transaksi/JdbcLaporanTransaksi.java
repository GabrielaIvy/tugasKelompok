package com.example.manpro.Transaksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcLaporanTransaksi implements LaporanTransaksiRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<LaporanTransaksi> findAll() {
        String sql = "SELECT * FROM LaporanTransaksi";
        return jdbcTemplate.query(sql, this::mapRowToTransaksi);
    }

    private LaporanTransaksi mapRowToTransaksi (ResultSet resultSet, int rowNum) throws SQLException{
        return new LaporanTransaksi(
            resultSet.getInt("id"),
            resultSet.getDate("tanggal"),
            resultSet.getString("nama_furnitur"),
            resultSet.getString("nama_komponen"),
            resultSet.getInt("stok")
        );
    }
    
}
