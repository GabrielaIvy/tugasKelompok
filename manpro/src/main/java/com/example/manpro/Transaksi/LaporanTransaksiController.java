package com.example.manpro.Transaksi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequestMapping("/laporanTransaksi")
public class LaporanTransaksiController {
    @Autowired
    private LaporanTransaksiRepository transaksiRepository;

    @GetMapping()
    public String index(Model model) {
        List<LaporanTransaksi> transaksi = transaksiRepository.findAll();
        model.addAttribute("laporanTransaksi", transaksi);
        return "PemilikPage/laporanTransaksi";
    }
}
