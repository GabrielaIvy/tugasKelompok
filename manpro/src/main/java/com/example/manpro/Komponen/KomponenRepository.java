package com.example.manpro.Komponen;

import java.util.List;

public interface KomponenRepository {
    List<Komponen> findAll();
    String findTerlaris();
}
