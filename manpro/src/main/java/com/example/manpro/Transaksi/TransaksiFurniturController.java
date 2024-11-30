package com.example.manpro.Transaksi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.manpro.Furnitur.Furnitur;
import com.example.manpro.Furnitur.FurniturRepository;
import com.example.manpro.Komponen.Komponen;
import com.example.manpro.Komponen.KomponenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/transaksi")
public class TransaksiFurniturController {

    @Autowired
    private FurniturRepository furniturRepo;

    @GetMapping()
    public String index(Model model, @RequestParam(value = "filter", required = false) String filter) {
        List<Furnitur> furnitur;

        if (filter != null && !filter.isEmpty()) {
            furnitur = furniturRepo.findByName(filter);
        } else {
            furnitur = furniturRepo.findAll();
        }

        model.addAttribute("filter", filter);
        model.addAttribute("furniturList", furnitur);
        return "PemilikPage/transaksi";
    }

    @PostMapping("/addStockFurnitur")
    public String addStockFur(
        @RequestParam("namaBarang") String namaBarang,
        @RequestParam("jumlah") int jumlah,
        @RequestParam("ukuran") String ukuran
    ) {
        Furnitur furnitur = furniturRepo.findByNameAndSize(namaBarang, ukuran);
        if (furnitur != null) {
            furniturRepo.updateStock(namaBarang, ukuran, furnitur.getStok() + jumlah);
        }
        return "redirect:/transaksi";
    }

}
