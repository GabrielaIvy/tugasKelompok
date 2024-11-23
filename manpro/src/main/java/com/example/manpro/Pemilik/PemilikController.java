package com.example.manpro.Pemilik;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/dashboardPemilik")
public class PemilikController {
    
    @GetMapping()
    public String loginPage(){
        return "PemilikPage/dashboardPemilik";
    }
}
