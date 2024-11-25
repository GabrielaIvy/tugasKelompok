package com.example.manpro.Furnitur;

import lombok.Data;

@Data
public class Furnitur {
    private final Integer id;
    private final String nama;
    private final String ukuran;
    private final int stok;
    private final double harga;
    private final String gambar;
}
