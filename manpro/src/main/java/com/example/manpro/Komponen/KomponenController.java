package com.example.manpro.Komponen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import jakarta.servlet.http.HttpSession;
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
    public String dataKomponen(@SessionAttribute("idUser") Integer idUser, Model model, 
    @RequestParam(value = "filter", required = false) String filter){
        if(idUser == 0) model.addAttribute("pemilik", true);
        else model.addAttribute("pemilik", false);
        
        List<Komponen> komponen;

        if (filter != null && !filter.isEmpty()) {
            komponen = repo.findByName(filter);
        } else {
            komponen = repo.findAll();
        }

        model.addAttribute("filter", filter);
        model.addAttribute("komponen", komponen);
        return "User/dataKomponen";
    }

    @PostMapping("/addKomponen")
    public String addKomponen(
        @RequestParam("nama") String nama,
        @RequestParam("ukuran") String ukuran,
        @RequestParam("harga") double harga, 
        @RequestParam("gambar") String gambar
    ){
        if (nama.isEmpty() || ukuran.isEmpty()|| harga < 0) {
            throw new IllegalArgumentException("Input tidak valid");
        }

        repo.addKomponen(nama, ukuran, harga, gambar);
        return "redirect:/dataKomponen";
    }

    @GetMapping("/addKomponen")
    public String addKomponenForm() {
        return "PemilikPage/addKomponen";
    }

    @GetMapping("/pesanKomponen")
    public String detailKomponen(@RequestParam("id") Integer id, Model model, HttpSession session){
        Komponen komponen = this.repo.findById(id);
        if(komponen == null){
            return "User/dataKomponen";
        }

        session.setAttribute("komponen", komponen);

        List<String> material = this.repo.findMaterial(id);
        model.addAttribute("material", material);

        List<String> warna = this.repo.findWarna(id);
        model.addAttribute("warna", warna);

        model.addAttribute("komponen", komponen);
        return "PelangganPage/pesanKomponen";
    }
}
