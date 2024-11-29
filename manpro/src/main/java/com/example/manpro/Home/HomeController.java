package com.example.manpro.Home;
import com.example.manpro.Komponen.KomponenRepository;
import com.example.manpro.Furnitur.FurniturRepository;
import com.example.manpro.Komponen.Komponen;
import com.example.manpro.Furnitur.Furnitur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/furniturKustom")
public class HomeController {
    
    @Autowired
    private FurniturRepository furniturRepo;

    @Autowired
    private KomponenRepository komponenRepo;

    @GetMapping()
    public String home(Model model){
        List<Furnitur> furnitur = this.furniturRepo.findAll();
        List<Komponen> komponen = this.komponenRepo.findAll();

        model.addAttribute("furnitur", furnitur);
        model.addAttribute("komponen", komponen);

        return "HomePage/index";
    }
}