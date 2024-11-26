package com.example.manpro.Furnitur;

import java.util.List;

public interface FurniturRepository {
    List<Furnitur> findAll();
    List<Furnitur> findByName(String keyword);
    void addFurnitur (String nama, String ukuran, double harga, String gambar);
}
