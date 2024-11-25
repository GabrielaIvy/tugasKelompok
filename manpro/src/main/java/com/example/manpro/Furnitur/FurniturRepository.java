package com.example.manpro.Furnitur;

import java.util.List;

public interface FurniturRepository {
    List<Furnitur> findAll();
    List<Furnitur> findByName(String keyword);
}
