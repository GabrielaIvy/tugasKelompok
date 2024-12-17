package com.example.manpro.Komponen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/dataKomponen")
public class KomponenController {
    
    @Autowired
    private KomponenRepository repo;

    @GetMapping()
    public String dataFurnitur(Model model, @RequestParam(value = "filter", required = false) String filter){
        List<Komponen> komponen;

        if (filter != null && !filter.isEmpty()) {
            komponen = repo.findByName(filter);
        } else {
            komponen = repo.findAll();
        }

        model.addAttribute("filter", filter);
        model.addAttribute("komponen", komponen);
        return "PemilikPage/dataKomponen";
    }

    @PostMapping("/addKomponen")
    public String addKomponen(
        @RequestParam("nama") String nama,
        @RequestParam("ukuran") String ukuran,
        @RequestParam("harga") double harga,
        @RequestParam("gambar") String gambar,
        @RequestParam("materials[]") List<String> materials,
        @RequestParam("colors[]") List<String> colors
    ) {
        if (nama.isEmpty() || ukuran.isEmpty() || harga < 0 || materials.isEmpty() || colors.isEmpty()) {
            throw new IllegalArgumentException("Input tidak valid");
        }

        repo.addKomponen(nama, ukuran, harga, gambar);
        Komponen komponen = repo.findByNameAndSize(nama, ukuran);

        for (int i = 0; i < materials.size(); i++) {
            Integer idMaterial = repo.findMaterialIdByName(materials.get(i));
            Integer idWarna = repo.findColorIdByName(colors.get(i));
            repo.insertKomponenMaterialWarna(komponen.getId(), idMaterial, idWarna);
        }

        return "redirect:/dataKomponen";
    }

    public String addKomponenForm(Model model) {
        List<String> materials = repo.findAllMaterials();
        List<String> colors = repo.findAllColors();

        model.addAttribute("materials", materials);
        model.addAttribute("colors", colors);
        return "PemilikPage/addKomponen"; 
    }

    @PostMapping("/updateHargaKomponen")
    public String updateHarga(
        @RequestParam("nama") String nama,
        @RequestParam("ukuran") String ukuran,
        @RequestParam("harga") double harga) {


        if (nama.isEmpty() || ukuran.isEmpty() || harga < 0) {
            throw new IllegalArgumentException("Input tidak valid");
        }

        repo.updateHargaByNameAndSize(nama, ukuran, harga); 
        return "redirect:/dataKomponen"; 
    }


    @GetMapping("/updateHargaKomponen")
    public String updateHargaForm(
        @RequestParam("nama") String nama, 
        @RequestParam("ukuran") String ukuran, 
        Model model) {
        Komponen komponen = repo.findByNameAndSize(nama, ukuran);
        if (komponen == null) {
            throw new IllegalArgumentException("Furnitur dengan nama dan ukuran tersebut tidak ditemukan");
        }
        model.addAttribute("komponen", komponen); 
        return "PemilikPage/updateHargaKomponen";
    }

    

}
