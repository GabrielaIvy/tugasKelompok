package com.example.manpro.Transaksi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class LaporanTransaksi {
    private final Integer idTransaksi;
    private final Date tglTransaksi;
    private final String nama_furnitur;
    private final String nama_komponen;
    private final Integer stok;
}
