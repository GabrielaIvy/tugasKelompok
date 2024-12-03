package com.example.manpro.Transaksi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.manpro.Furnitur.Furnitur;
import com.example.manpro.Furnitur.FurniturRepository;

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
        @RequestParam("ukuran") String ukuran,
        @RequestParam("tanggal") String tanggal
    ) {
        Furnitur furnitur = furniturRepo.findByNameAndSize(namaBarang, ukuran);
        int idFurnitur = furnitur.getId();
        int prevStok = furnitur.getStok();
        java.sql.Date sqlTanggal = java.sql.Date.valueOf(tanggal);
        
        if (furnitur != null) {
            furniturRepo.updateStock(namaBarang, ukuran, prevStok + jumlah, sqlTanggal, idFurnitur, prevStok);
        }
        return "redirect:/transaksi";
    }

}
