package com.example.manpro.HistoriPesanan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/historiPesanan")
public class HistoriPesananController {
    
    @Autowired
    private HistoriPesananRepository repo;

    @GetMapping()
    public String historiPesanan(@SessionAttribute("idUser") Integer idUser, Model model){
        List<HistoriPesanan> historiPesanan = this.repo.findAll(idUser);

        model.addAttribute("historiPesanan", historiPesanan);
        return "PembeliPage/historiPesanan";
    }
}
