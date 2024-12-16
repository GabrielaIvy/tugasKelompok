package com.example.manpro.Pelanggan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.HashMap;


@Repository
public class JdbcPesananRepository implements PesananRepostiory{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer tambahPesanan(Integer idPelanggan){
        try{
            SimpleJdbcInsert insertPesanan = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Pesanan").usingGeneratedKeyColumns("idpesanan");
            Map<String, Object> params = new HashMap<>();
            params.put("tglPesanan", java.time.LocalDate.now());
            params.put("idPelanggan", idPelanggan);

            Number idPesanan = insertPesanan.executeAndReturnKey(params);
            return idPesanan.intValue();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean tambahPesanKomponen(Integer idPesanan, Integer idKomponen, int jumlah){
        String sql = "INSERT INTO pesanKomponen (idPesanan, idKomponen, jumlah) VALUES (?,?,?)";
        try{
            jdbcTemplate.update(sql, idPesanan, idKomponen, jumlah);
            String updateStok = "UPDATE Komponen SET stok = stok - ? WHERE id = ?";
            jdbcTemplate.update(updateStok, jumlah, idKomponen);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean tambahPesanFurnitur(Integer idPesanan, Integer idFurnitur, int jumlah){
        String sql = "INSERT INTO pesanFurnitur (idPesanan, idFurnitur, jumlah) VALUES (?,?,?)";
        try{
            jdbcTemplate.update(sql, idPesanan, idFurnitur, jumlah);
            String updateStok = "UPDATE Furnitur SET stok = stok - ? WHERE id = ?";
            jdbcTemplate.update(updateStok, jumlah, idFurnitur);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
