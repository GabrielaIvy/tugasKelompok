package com.example.manpro.Pelanggan;

public interface PesananRepostiory {
    Integer tambahPesanan(Integer idPelanggan);
    boolean tambahPesanKomponen(Integer idPesanan, Integer idKomponen, int jumlah);
    boolean tambahPesanFurnitur(Integer idPesanan, Integer idFurnitur, int jumlah);
}
