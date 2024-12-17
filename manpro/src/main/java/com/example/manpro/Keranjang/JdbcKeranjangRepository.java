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

    // @Override
    // public Keranjang findAll(Integer idU){
    //     String sql = "SELECT * FROM itemKeranjang WHERE idU=?";
    //     Map<Integer, DetailFurnitur> furniturMap = new HashMap<>(); 

    //     jdbcTemplate.query(sql, new Object[]{idU}, resultSet -> {
    //         Integer idFurnitur = resultSet.getInt("idFurnitur");
    //         DetailFurnitur furnitur = furniturMap.get(idFurnitur);

    //         if (furnitur == null) {
    //             furnitur = new DetailFurnitur(
    //                 idFurnitur,
    //                 resultSet.getString("namaFurnitur"),
    //                 new ArrayList<DetailKomponen>(), //detail komponen
    //                 resultSet.getString("ukuran"),
    //                 resultSet.getInt("jumlah"),
    //                 resultSet.getDouble("harga")
    //             );
    //             furniturMap.put(idFurnitur, furnitur);
    //         }

    //         String namaKomponen = resultSet.getString("namaKomponen");
    //         if(namaKomponen != null && !namaKomponen.isEmpty()){
    //             DetailKomponen detail = new DetailKomponen(
    //                 namaKomponen,
    //                 resultSet.getString("warna"),
    //                 resultSet.getString("material")
    //             );
    //             furnitur.getDetailKomponen().add(detail);
    //         }

    //     });

    //     return new Keranjang(idU, new ArrayList<>(furniturMap.values()));
    // }

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
            }else{
                furnitur.setJumlah(furnitur.getJumlah() + rs.getInt("jumlah"));
            }
    
            //detail komponen
            DetailKomponen komponen = new DetailKomponen();
            komponen.setNamaKomponen(rs.getString("namaKomponen"));
            komponen.setMaterial(rs.getString("material"));
            komponen.setWarna(rs.getString("warna"));

            //cek apakah detail komponen yang sama persis sudah ada
            boolean komponenExists = furnitur.getDetailKomponen().stream().anyMatch(k ->
                k.getNamaKomponen().equals(komponen.getNamaKomponen()) &&
                k.getMaterial().equals(komponen.getMaterial()) &&
                k.getWarna().equals(komponen.getWarna())
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
}
