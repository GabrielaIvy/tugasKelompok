package com.example.manpro.Furnitur;

import com.example.manpro.Komponen.KomponenRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dataFurnitur")
public class FurniturController {
    
    @Autowired
    private FurniturRepository repo;

    @Autowired
    private KomponenRepository komponenRepo;

    @GetMapping()
    public String dataFurnitur(@SessionAttribute("idUser") Integer idUser, Model model, 
    @RequestParam(value = "filter", required = false) String filter){
        if(idUser == 0) model.addAttribute("pemilik", true);
        else model.addAttribute("pemilik", false);

        List<Furnitur> furnitur;

        if (filter != null && !filter.isEmpty()) {
            furnitur = repo.findByName(filter);
        } else {
            furnitur = repo.findAll();
        }

        model.addAttribute("filter", filter);
        model.addAttribute("furnitur", furnitur);
        return "User/dataFurnitur";
    }

    @PostMapping("/addFurnitur")
    public String addFurnitur(
        @RequestParam("nama") String nama,
        @RequestParam("ukuran") String ukuran,
        @RequestParam("harga") double harga, 
        @RequestParam("gambar") String gambar
    ){
        if (nama.isEmpty() || ukuran.isEmpty()|| harga < 0) {
            throw new IllegalArgumentException("Input tidak valid");
        }

        repo.addFurnitur(nama, ukuran, harga, gambar);
        return "redirect:/dataFurnitur";
    }

    @GetMapping("/addFurnitur")
    public String addFurniturForm() {
        return "PemilikPage/addFurnitur";
    }

    @GetMapping("/pesanFurnitur")
    public String detailKomponen(@RequestParam("id") Integer id, Model model){
        Furnitur furnitur = this.repo.findById(id);
        if(furnitur == null){
            return "User/dataFurnitur";
        }

        List<FurniturKomponen> komponen = this.repo.findKomponen(id);
        model.addAttribute("komponen", komponen);

        // List<String> material = this.komponenRepo.findMaterial(id);
        // model.addAttribute("material", material);

        // List<String> warna = this.komponenRepo.findWarna(id);
        // model.addAttribute("warna", warna);

        model.addAttribute("furnitur", furnitur);
        return "PembeliPage/pesanFurnitur";
    }
}
