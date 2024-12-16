package com.example.manpro.Pelanggan;

import com.example.manpro.Furnitur.Furnitur;
import com.example.manpro.Furnitur.FurniturKomponen;
import com.example.manpro.Furnitur.FurniturRepository;
import com.example.manpro.Komponen.Komponen;
import com.example.manpro.Komponen.KomponenRepository;
import com.example.manpro.User.User;
import com.example.manpro.User.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pelanggan")
public class PelangganController {
    @Autowired
    private FurniturRepository furniturRepo;

    @Autowired
    private KomponenRepository komponenRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PesananRepostiory pesananRepo;

    @GetMapping()
    public String home(Model model){
        List<Furnitur> furnitur = this.furniturRepo.findAll();
        List<Komponen> komponen = this.komponenRepo.findAll();

        model.addAttribute("loggedIn", true);
        model.addAttribute("furnitur", furnitur);
        model.addAttribute("komponen", komponen);

        return "HomePage/index";
    }

    @GetMapping("/profile")
    public String profile(@SessionAttribute("idUser") Integer idUser, Model model){
        User user = this.userRepo.findById(idUser);
        String[] domisili = this.userRepo.domilisiUser(idUser);
        model.addAttribute("user", user);
        model.addAttribute("kelurahan", domisili[0]);
        model.addAttribute("kecamatan", domisili[1]);
        return "PelangganPage/profile";
    }

    @GetMapping("/keranjang")
    public String keranjang(){
        return "PelangganPage/keranjang";
    }

    @PostMapping("/beliKomponen")
    public String beliKomponen(@SessionAttribute("idUser") Integer idUser, HttpSession session,
        @RequestParam("jumlah") int jumlah, Model model){
            Komponen komponen = (Komponen)session.getAttribute("komponen");
            int stok = this.komponenRepo.cekStok(komponen.getId());
            if(jumlah > stok || jumlah <= 0){
                model.addAttribute("error", "Pesanan gagal, jumlah tidak valid");
                model.addAttribute("komponen", komponen);
                return "PelangganPage/pesanKomponen";
            }
            Integer idPesanan = this.pesananRepo.tambahPesanan(idUser);
            boolean sukses = false;
            if(idPesanan != null){
                sukses = this.pesananRepo.tambahPesanKomponen(idPesanan, komponen.getId(), jumlah);
                if(sukses){
                    return "redirect:/historiPesanan";
                }
            }

            model.addAttribute("error", "Pesanan gagal");
            return "redirect:/dataKomponen/pesanKomponen?id=" + komponen.getId();
    }

    @PostMapping("/beliFurnitur")
    public String beliFurnitur(@SessionAttribute("idUser") Integer idUser, HttpSession session,
        @RequestParam("jumlah") int jumlah, Model model){
            Furnitur furnitur = (Furnitur)session.getAttribute("furnitur");
            int stok = this.furniturRepo.cekStok(furnitur.getId());
            if(jumlah > stok || jumlah <= 0){
                model.addAttribute("error", "Pesanan gagal, jumlah tidak valid");
                model.addAttribute("furnitur", furnitur);
                List<FurniturKomponen> komponen = this.furniturRepo.findKomponen(furnitur.getId());
                model.addAttribute("komponen", komponen);
                return "PelangganPage/pesanFurnitur";
            }
            Integer idPesanan = this.pesananRepo.tambahPesanan(idUser);
            boolean sukses = false;
            if(idPesanan != null){
                sukses = this.pesananRepo.tambahPesanFurnitur(idPesanan, furnitur.getId(), jumlah);
                if(sukses){
                    return "redirect:/historiPesanan";
                }
            }

            model.addAttribute("error", "Pesanan gagal");
            return "redirect:/dataKomponen/pesanFurnitur?id=" + furnitur.getId();
    }
}
