package com.example.manpro.Furnitur;

import lombok.Data;
import java.util.List;

import com.example.manpro.Komponen.DetailKomponen;

@Data
public class DetailFurnitur{
    private final Integer idFurnitur;
    private final String namaFurnitur;
    private final List<DetailKomponen> detailKomponen;
    private final String ukuran;
    private final int jumlah;
    private final double harga;
}