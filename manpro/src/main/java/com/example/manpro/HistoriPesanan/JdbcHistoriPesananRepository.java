package com.example.manpro.HistoriPesanan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.manpro.Furnitur.DetailFurnitur;
import com.example.manpro.Komponen.DetailKomponen;

@Repository
public class JdbcHistoriPesananRepository implements HistoriPesananRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HistoriPesanan> findAll(Integer idPelanggan){
        String sql = "SELECT idPesanan, tglPesanan, idFurnitur, namaFurnitur, namaKomponen, warna, material, ukuran, jumlah, harga, totalHarga FROM DetailPesananFinal WHERE idPelanggan=?";
        Map<Integer, HistoriPesanan> historiMap = new HashMap<>(); //key = idPesanan, value = pesanan

        jdbcTemplate.query(sql, new Object[]{idPelanggan}, (resultSet) -> {
            Integer idPesanan = resultSet.getInt("idPesanan");
            Integer idFurnitur = resultSet.getInt("idFurnitur");

            //buat pesanan baru jika idPesanannya belum ada
            historiMap.putIfAbsent(idPesanan, new HistoriPesanan(
                idPesanan,
                resultSet.getString("tglPesanan"),
                new ArrayList<DetailFurnitur>(), //list furnitur
                resultSet.getDouble("totalHarga")
            ));

            HistoriPesanan histori = historiMap.get(idPesanan);
            
            //cek apakah furnitur sudah ada dalam daftar pesanan
            DetailFurnitur furnitur = histori.getListFurnitur().stream().filter(f -> f.getIdFurnitur() == idFurnitur).findFirst().orElse(null);

            if(furnitur == null || idFurnitur == -1){ //furnitur belum ada
                furnitur = new DetailFurnitur(
                    idFurnitur,
                    resultSet.getString("namaFurnitur"),
                    new ArrayList<DetailKomponen>(), //detail komponen
                    resultSet.getString("ukuran"),
                    resultSet.getInt("jumlah"),
                    resultSet.getDouble("harga")
                );
                histori.getListFurnitur().add(furnitur);
            }

            //tambah detail komponen
            String namaKomponen = resultSet.getString("namaKomponen");
            if(namaKomponen != null && !namaKomponen.isEmpty()){
                DetailKomponen detail = new DetailKomponen(
                    namaKomponen,
                    resultSet.getString("warna"),
                    resultSet.getString("material")
                );
                furnitur.getDetailKomponen().add(detail);
            }
        });

        return new ArrayList<>(historiMap.values());
    }
}
