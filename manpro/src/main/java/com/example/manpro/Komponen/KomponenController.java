package com.example.manpro.Komponen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/dataKomponen")
public class KomponenController {
    
    @Autowired
    private KomponenRepository repo;

    @GetMapping()
    public String dataFurnitur(Model model, @RequestParam(value = "keyword", required = false) String filter){
        List<Komponen> komponen;

        if (filter != null && !filter.isEmpty()) {
            komponen = repo.findByName(filter);
        } else {
            komponen = repo.findAll();
        }

        model.addAttribute("filter", filter);
        model.addAttribute("komponen", komponen);
        return "PemilikPage/dataKomponen";
    }

}
