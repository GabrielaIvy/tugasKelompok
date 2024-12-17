package com.example.manpro.Keranjang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.manpro.Furnitur.DetailFurnitur;
import com.example.manpro.Komponen.DetailKomponen;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcKeranjangRepository implements KeranjangRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate nParameterJdbcTemplate;

    @Override
    public List<DetailFurnitur> findItemsById(Integer idU){
        String sql = "SELECT * FROM itemKeranjang WHERE idU = :idU";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idU", idU);
        Map<Integer, DetailFurnitur> furniturMap = new HashMap<>();

        nParameterJdbcTemplate.query(sql, params, rs -> {
            int idItem = rs.getInt("idFurnitur");
    
            DetailFurnitur furnitur = furniturMap.get(idItem);
            if (furnitur == null) {
                furnitur = new DetailFurnitur();
                furnitur.setIdFurnitur(idItem);
                furnitur.setNamaFurnitur(rs.getString("namaFurnitur"));
                furnitur.setUkuran(rs.getString("ukuran"));
                furnitur.setJumlah(rs.getInt("jumlah"));
                furnitur.setHarga(rs.getDouble("harga"));
                furnitur.setDetailKomponen(new ArrayList<>());
                furniturMap.put(idItem, furnitur);
            }
    
            //detail komponen
            DetailKomponen komponen = new DetailKomponen();
            komponen.setNamaKomponen(rs.getString("namaKomponen"));
            komponen.setMaterial(rs.getString("material"));
            komponen.setWarna(rs.getString("warna"));

            //cek apakah detail komponen yang sama persis sudah ada
            boolean komponenExists = furnitur.getDetailKomponen().stream().anyMatch(k -> 
                (k.getNamaKomponen() != null && k.getNamaKomponen().equals(komponen.getNamaKomponen())) &&
                (k.getMaterial() != null && k.getMaterial().equals(komponen.getMaterial())) &&
                (k.getWarna() != null && k.getWarna().equals(komponen.getWarna()))
            );


            // Jika detail komponen belum ada, tambah ke detailKomponen
            if (!komponenExists) {
                furnitur.getDetailKomponen().add(komponen);
            }
        });
    
        return new ArrayList<>(furniturMap.values());
    }


    @Override
    public boolean inputFurnitur(Integer idU, Integer idFurnitur, int jumlah){
        String sql = "INSERT INTO keranjangFurnitur (idU, idFurnitur, jumlah) VALUES (?,?,?)";
        try{
            jdbcTemplate.update(sql, idU, idFurnitur, jumlah);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean inputKomponen(Integer idU, Integer idKomponen, int jumlah){
        String sql = "INSERT INTO keranjangKomponen (idU, idKomponen, jumlah) VALUES (?,?,?)";
        try{
            jdbcTemplate.update(sql, idU, idKomponen, jumlah);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeFurnitur(Integer idU, Integer idFurnitur, int jumlah){
        String sql = "DELETE FROM keranjangFurnitur WHERE idU = ? AND idFurnitur = ? AND jumlah = ?";
        int rowsAffected = jdbcTemplate.update(sql, idU, idFurnitur, jumlah);
        return (rowsAffected>0);
    }

    @Override
    public boolean removeKomponen(Integer idU, Integer idKomponen, int jumlah){
        String sql = "DELETE FROM keranjangKomponen WHERE idU = ? AND idKomponen = ? AND jumlah = ?";
        int rowsAffected = jdbcTemplate.update(sql, idU, idKomponen, jumlah);
        return (rowsAffected>0);
    }
}
