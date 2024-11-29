package com.example.manpro.Pemilik;

import com.example.manpro.Furnitur.FurniturRepository;
import com.example.manpro.Komponen.KomponenRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/dashboardPemilik")
public class PemilikController {
    
    @Autowired
    private LaporanPenjualanRepository laporanRepo;

    @Autowired
    private FurniturRepository furniturRepo;

    @Autowired
    private KomponenRepository komponenRepo;

    @GetMapping()
    public String loginPage(Model model){
        double penjualan = this.laporanRepo.totalPenjualan();
        int totalPesanan = this.laporanRepo.totalPesanan();
        String furniturTerlaris = this.furniturRepo.findTerlaris();
        String komponenTerlaris = this.komponenRepo.findTerlaris();
    
        model.addAttribute("penjualan", penjualan);
        model.addAttribute("totalPesanan", totalPesanan);
        model.addAttribute("furniturTerlaris", furniturTerlaris);
        model.addAttribute("komponenTerlaris", komponenTerlaris);
        return "PemilikPage/dashboardPemilik";
    }
}
