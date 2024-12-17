package com.example.manpro.Furnitur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.example.manpro.Komponen.DetailKomponen;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailFurnitur{
    private Integer idFurnitur;
    private String namaFurnitur;
    private List<DetailKomponen> detailKomponen;
    private String ukuran;
    private int jumlah;
    private double harga;
}