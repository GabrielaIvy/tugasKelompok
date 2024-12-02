package com.example.manpro.HistoriPesanan;

import lombok.Data;
import java.util.List;

import com.example.manpro.Furnitur.DetailFurnitur;

@Data
public class HistoriPesanan {
    private final Integer idPesanan;
    private final String tglPesanan;
    private final List<DetailFurnitur> listFurnitur;
    private final double totalHarga;
}
