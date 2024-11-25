package com.example.manpro.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class UserController {
    
    @Autowired
    private UserRepository repo;

    @GetMapping("/login")
    public String loginPage(){
        return "HomePage/login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String passwords, Model model){
        boolean valid = this.repo.authenticateUser(username, passwords);
        if(valid){
            String role = this.repo.getUserRole(username);
            if(role.equals("PemilikToko")){
                return "redirect:/dashboardPemilik";
            }else{
                return "redirect:/dashboardPengguna";
            }
        }else{
            model.addAttribute("error", "Invalid username or password");
            return "HomePage/login";
        }
    }

    @GetMapping("/register")
    public String register(Model model){
        List<Kecamatan> kecamatan = this.repo.findAllKecamatan();
        List<Kelurahan> kelurahan = this.repo.findAllKelurahan();

        model.addAttribute("kecamatan", kecamatan);
        model.addAttribute("kelurahan", kelurahan);
        
        return "HomePage/register";
    }

    @PostMapping("/register")
    public String handleRegister(){
        return "redirect:/home";
    }

    @GetMapping("/getKelurahanByKecamatan")
    @ResponseBody
    public List<Kelurahan> getKelurahanByKecamatan(@RequestParam("idKecamatan") Integer idKecamatan){
        List<Kelurahan> kelurahan;
        if(idKecamatan == null) {
            kelurahan = this.repo.findAllKelurahan();
        }else{
            kelurahan = this.repo.findByKecamatan(idKecamatan);
        }
        return kelurahan;
    }
}
