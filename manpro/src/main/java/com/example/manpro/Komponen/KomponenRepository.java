package com.example.manpro.Komponen;

import java.util.List;

public interface KomponenRepository {
    List<Komponen> findAll();
    List<Komponen> findByName(String keyword);
}
