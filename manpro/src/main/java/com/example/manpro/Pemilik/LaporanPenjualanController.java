package com.example.manpro.Pemilik;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/laporanPenjualan")
public class LaporanPenjualanController {
    
    @Autowired
    private LaporanPenjualanRepository laporanRepo;

    @GetMapping()
    public String dataFurnitur(Model model){
        List<LaporanPenjualan> laporanPenjualan = this.laporanRepo.findAll();
        double pendapatan = this.laporanRepo.totalPendapatan();

        model.addAttribute("laporanPenjualan", laporanPenjualan);
        model.addAttribute("pendapatan", pendapatan);
        return "PemilikPage/laporanPenjualan";
    }
}
