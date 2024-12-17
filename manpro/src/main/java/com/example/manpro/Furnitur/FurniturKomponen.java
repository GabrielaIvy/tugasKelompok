package com.example.manpro.Furnitur;

import lombok.Data;
import java.util.List;

@Data
public class FurniturKomponen {
    private final Integer id;
    private final String nama;
    private final List<String> material;
    private final List<String> warna;
}
