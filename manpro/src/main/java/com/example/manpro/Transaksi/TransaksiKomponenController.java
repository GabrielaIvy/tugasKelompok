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
@RequestMapping("/transaksiKomponen")
public class TransaksiKomponenController {

    @Autowired
    private KomponenRepository komponenRepo;

    @GetMapping()
    public String index(Model model, @RequestParam(value = "filter", required = false) String filter) {
        List<Komponen> komponen;

        if (filter != null && !filter.isEmpty()) {
            komponen = komponenRepo.findByName(filter);
        } else {
            komponen = komponenRepo.findAll();
        }

        model.addAttribute("filter", filter);
        model.addAttribute("komponenList", komponen);
        return "PemilikPage/transaksiKomponen";
    }

    @PostMapping("/addStockKomponen")
    public String addStockKomp(
        @RequestParam("namaBarang") String namaBarang,
        @RequestParam("jumlah") int jumlah,
        @RequestParam("ukuran") String ukuran
    ) {
        Komponen komponen = komponenRepo.findByNameAndSize(namaBarang, ukuran);
        if (komponen != null) {
            komponenRepo.updateStock(namaBarang, ukuran, komponen.getStok() + jumlah);
        }
        return "redirect:/transaksiKomponen";
    }
}
