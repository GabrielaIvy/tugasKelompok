package com.example.manpro.Keranjang;

import java.util.List;

import com.example.manpro.Furnitur.DetailFurnitur;

public interface KeranjangRepository {
    List<DetailFurnitur> findItemsById(Integer idU);
    boolean inputFurnitur(Integer idU, Integer idFurnitur, int jumlah);
    boolean inputKomponen(Integer idU, Integer idKomponen, int jumlah);
    boolean removeFurnitur(Integer idU, Integer idFurnitur, int jumlah);
    boolean removeKomponen(Integer idU, Integer idKomponen, int jumlah);
}
