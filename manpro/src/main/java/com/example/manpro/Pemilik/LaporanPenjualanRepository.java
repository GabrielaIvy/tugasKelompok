package com.example.manpro.Pemilik;

import java.util.List;

public interface LaporanPenjualanRepository {
    List<LaporanPenjualan> findAll();
    double totalPenjualan();
    int totalPesanan();
}
