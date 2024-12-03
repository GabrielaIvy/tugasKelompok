package com.example.manpro.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/furniturKustom")
@SessionAttributes("idUser")
public class UserController {
    
    @Autowired
    private UserRepository repo;

    @GetMapping("/login")
    public String loginPage(){
        return "HomePage/login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String passwords, Model model){
        User user = this.repo.authenticateUser(username, passwords);
        if(user != null){
            String role = user.getRoles();
            if(role.equals("PemilikToko")){
                model.addAttribute("idUser", "0");
                return "redirect:/dashboardPemilik";
            }else{
                model.addAttribute("idUser", user.getId());
                return "redirect:/dashboardPelanggan";
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
    public String handleRegister(@RequestParam("nama") String nama, @RequestParam("username") String username,
    @RequestParam("passwords") String passwords, @RequestParam("alamat") String alamat, @RequestParam("noHP") String noHP,
    @RequestParam("email") String email, @RequestParam("idKelurahan") Integer idKelurahan, Model model){
        String role = "Pelanggan";
        User user = new User(null, nama, username, passwords, role, alamat, noHP, email, idKelurahan);
        boolean success = this.repo.register(user);
        if(success){
            user = this.repo.authenticateUser(username, passwords);
            model.addAttribute("idPelanggan", user.getId());
            return "redirect:/dashboardPelanggan";
        }else{
            model.addAttribute("error", "Registrasi gagal");
            return "redirect:/furniturKustom";
        }
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
