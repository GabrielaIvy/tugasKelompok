package com.example.manpro.Furnitur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/dataFurnitur")
public class FurniturController {
    
    @Autowired
    private FurniturRepository repo;

    @GetMapping()
    public String dataFurnitur(Model model, @RequestParam(value = "filter", required = false) String filter){
        List<Furnitur> furnitur;

        if (filter != null && !filter.isEmpty()) {
            furnitur = repo.findByName(filter);
        } else {
            furnitur = repo.findAll();
        }

        model.addAttribute("filter", filter);
        model.addAttribute("furnitur", furnitur);
        return "PemilikPage/dataFurnitur";
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
        return "PemilikPage/addFurnitur"; // Pastikan ini sesuai dengan lokasi file template HTML
    }
}
