package com.example.manpro.Pemilik;

import lombok.Data;
import java.util.List;

@Data
public class LaporanPenjualan {
    private final Integer idPesanan;
    private final String tglPesanan;
    private final List<PenjualanFurnitur> listFurnitur;
    private final double totalHarga;
}
