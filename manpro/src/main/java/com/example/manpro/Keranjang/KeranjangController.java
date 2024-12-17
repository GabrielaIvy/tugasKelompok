package com.example.manpro.Keranjang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.manpro.Furnitur.DetailFurnitur;
import com.example.manpro.Furnitur.Furnitur;
import com.example.manpro.Furnitur.FurniturKomponen;
import com.example.manpro.Furnitur.FurniturRepository;
import com.example.manpro.Komponen.Komponen;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/pelanggan")
public class KeranjangController {

    @Autowired
    private KeranjangRepository repo;

    @Autowired
    private FurniturRepository furniturRepo;
    
    @GetMapping("/keranjang")
    public String keranjang(@SessionAttribute("idUser") Integer idUser, Model model){
        List<DetailFurnitur> items = this.repo.findItemsById(idUser);
        model.addAttribute("keranjang", items);
        return "PelangganPage/keranjang";
    }

    @PostMapping("/keranjangFurnitur")
    public String keranjangFurnitur(@SessionAttribute("idUser") Integer idUser,
        @RequestParam("jumlah") int jumlah, HttpSession session, Model model){
            Furnitur furnitur = (Furnitur)session.getAttribute("furnitur");
            boolean sukses = this.repo.inputFurnitur(idUser, furnitur.getId(), jumlah);
            if(sukses){
                return "redirect:/pelanggan/keranjang";
            }

            model.addAttribute("error", "Gagal input ke keranjang");
            model.addAttribute("furnitur", furnitur);
            List<FurniturKomponen> komponen = this.furniturRepo.findKomponen(furnitur.getId());
            model.addAttribute("komponen", komponen);
            return "PelangganPage/pesanFurnitur";
    }

    @PostMapping("/keranjangKomponen")
    public String keranjangKomponen(@SessionAttribute("idUser") Integer idUser,
        @RequestParam("jumlah") int jumlah, HttpSession session, Model model){
            Komponen komponen = (Komponen)session.getAttribute("komponen");
            boolean sukses = this.repo.inputKomponen(idUser, komponen.getId(), jumlah);
            if(sukses){
                return "redirect:/pelanggan/keranjang";
            }

            model.addAttribute("error", "Gagal input ke keranjang");
            model.addAttribute("komponen", komponen);
            return "PelangganPage/pesanKomponen";
    }

    @PostMapping("/checkoutKeranjang")
    public String checkoutKeranjang(){
        return "redirect:/historiPesanan";
    }
}
