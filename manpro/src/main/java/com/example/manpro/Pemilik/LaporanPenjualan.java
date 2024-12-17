package com.example.manpro.Pemilik;

import lombok.Data;
import java.util.List;

import com.example.manpro.Furnitur.DetailFurnitur;

@Data
public class LaporanPenjualan {
    private final Integer idPesanan;
    private final String tglPesanan;
    private final List<DetailFurnitur> listFurnitur;
    private final double totalHarga;
}
