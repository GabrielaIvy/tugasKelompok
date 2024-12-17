package com.example.manpro.Furnitur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.manpro.Komponen.Komponen;
import com.example.manpro.Komponen.KomponenRepository;

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

    @Autowired
    private KomponenRepository komponenRepo;

    @GetMapping()
    public String dataFurnitur(Model model, @RequestParam(value = "filter", required = false) String filter){
        List<Furnitur> furnitur;

        if (filter != null && !filter.isEmpty()) {
            furnitur = repo.findByName(filter);
        } else {
            furnitur = repo.findAll();
        }

        model.addAttribute("filter", filter);
        model.addAttribute("furnitur", furnitur);
        return "PemilikPage/dataFurnitur";
    }

    @PostMapping("/addFurnitur")
    public String addFurnitur(
        @RequestParam("nama") String nama,
        @RequestParam("ukuran") String ukuran,
        @RequestParam("harga") double harga,
        @RequestParam("gambar") String gambar,
        @RequestParam("komponenList[]") List<Integer> komponenList // Ambil ID komponen dari form
    ) {
        if (nama.isEmpty() || ukuran.isEmpty() || harga < 0 || komponenList.isEmpty()) {
            throw new IllegalArgumentException("Input tidak valid");
        }

        // Tambahkan furnitur baru ke database
        repo.addFurnitur(nama, ukuran, harga, gambar);

        // Dapatkan furnitur yang baru saja ditambahkan untuk mengambil id-nya
        Furnitur furnitur = repo.findByNameAndSize(nama, ukuran);

        if (furnitur == null) {
            throw new IllegalStateException("Furnitur tidak berhasil disimpan");
        }

        int idFurnitur = furnitur.getId();

        // Tambahkan komponen untuk furnitur tersebut (abaikan jumlah)
        for (int i = 0; i < komponenList.size(); i++) {
            int idKomponen = komponenList.get(i);

            // Simpan data ke tabel komponenFurnitur (satu kali per komponen)
            repo.insertKomponenFurnitur(idFurnitur, idKomponen);
        }

        return "redirect:/dataFurnitur"; // Kembali ke halaman furnitur
    }


    @GetMapping("/addFurnitur")
    public String addFurniturForm(Model model) {
        List<Komponen> komponenList = komponenRepo.findAll(); 
        model.addAttribute("komponenList", komponenList);
        return "PemilikPage/addFurnitur"; 
    }

    @PostMapping("/updateHarga")
    public String updateHarga(
        @RequestParam("nama") String nama,
        @RequestParam("ukuran") String ukuran,
        @RequestParam("harga") double harga) {


        if (nama.isEmpty() || ukuran.isEmpty() || harga < 0) {
            throw new IllegalArgumentException("Input tidak valid");
        }
        
        repo.updateHargaByNameAndSize(nama, ukuran, harga); // Perbarui harga di database
        return "redirect:/dataFurnitur"; 
    }

    @GetMapping("/updateHarga")
    public String updateHargaForm(
        @RequestParam("nama") String nama, 
        @RequestParam("ukuran") String ukuran, 
        Model model) {
        Furnitur furnitur = repo.findByNameAndSize(nama, ukuran);
        if (furnitur == null) {
            throw new IllegalArgumentException("Furnitur dengan nama dan ukuran tersebut tidak ditemukan");
        }
        model.addAttribute("furnitur", furnitur); // Pastikan furnitur di-set ke model
        return "PemilikPage/updateHarga";
    }




}
