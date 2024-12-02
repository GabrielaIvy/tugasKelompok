package com.example.manpro.HistoriPesanan;

import java.util.List;

public interface HistoriPesananRepository {
    List<HistoriPesanan> findAll(Integer idPelanggan);
}
