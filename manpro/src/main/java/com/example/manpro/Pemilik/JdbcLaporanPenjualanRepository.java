package com.example.manpro.Pemilik;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcLaporanPenjualanRepository implements LaporanPenjualanRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<LaporanPenjualan> findAll(){
        String sql = "SELECT * FROM LaporanPenjualan";
        Map<Integer, LaporanPenjualan> laporanMap = new HashMap<>(); //key = idPesanan, value = pesanan

        jdbcTemplate.query(sql, (resultSet) -> {
            Integer idPesanan = resultSet.getInt("idPesanan");
            Integer idFurnitur = resultSet.getInt("idFurnitur");

            //buat pesanan baru jika idPesanannya belum ada
            laporanMap.putIfAbsent(idPesanan, new LaporanPenjualan(
                idPesanan,
                resultSet.getString("tglPesanan"),
                new ArrayList<PenjualanFurnitur>() //list furnitur
            ));

            LaporanPenjualan laporan = laporanMap.get(idPesanan);
            
            //cek apakah furnitur sudah ada dalam daftar pesanan
            PenjualanFurnitur furnitur = laporan.getListFurnitur().stream().filter(f -> f.getIdFurnitur() == idFurnitur).findFirst().orElse(null);

            if(furnitur == null || idFurnitur == -1){ //furnitur belum ada
                furnitur = new PenjualanFurnitur(
                    idFurnitur,
                    resultSet.getString("namaFurnitur"),
                    new ArrayList<DetailKomponen>(), //detail komponen
                    resultSet.getString("ukuran"),
                    resultSet.getInt("jumlah"),
                    resultSet.getDouble("harga")
                );
                laporan.getListFurnitur().add(furnitur);
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

        return new ArrayList<>(laporanMap.values());
    }
}
