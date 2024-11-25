package com.example.manpro.Furnitur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/dataFurnitur")
public class FurniturController {
    
    @Autowired
    private FurniturRepository repo;

    @GetMapping()
    public String dataFurnitur(Model model){
        List<Furnitur> furnitur = this.repo.findAll();

        model.addAttribute("furnitur", furnitur);
        return "PemilikPage/dataFurnitur";
    }
}
