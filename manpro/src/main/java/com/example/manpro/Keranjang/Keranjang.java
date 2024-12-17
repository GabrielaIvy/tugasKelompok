package com.example.manpro.Keranjang;

import lombok.Data;
import java.util.List;

import com.example.manpro.Furnitur.DetailFurnitur;
import com.example.manpro.Komponen.DetailKomponen;

@Data
public class Keranjang {
    private final Integer idU;
    private final List<DetailFurnitur> listFurnitur;
    // private final double totalHarga;
    private final List<DetailKomponen> listKomponen;
}
