package com.example.manpro.Pemilik;

import lombok.Data;
import java.util.List;

@Data
public class PenjualanFurnitur{
    private final Integer idFurnitur;
    private final String namaFurnitur;
    private final List<DetailKomponen> detailKomponen;
    private final String ukuran;
    private final int jumlah;
    private final double harga;
}