package com.example.demo.controller;

import com.example.demo.entity.NhanVien;
import com.example.demo.services.NhanVienService;
import com.example.demo.services.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nhanviens")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private PhongBanService phongBanService;
    @GetMapping
    public String showAllNhanVien(Model model){
        List<NhanVien> nhanViens = nhanVienService.getAllNhanVien();
        model.addAttribute("nhanviens", nhanViens);
        return "nhanvien/list";
    }
    @GetMapping("/add")
    public String addNhanVienForm(Model model){
        model.addAttribute("nhanvien",new NhanVien());
        model.addAttribute("PhongBans", phongBanService.getAllPhongBan());
        return "nhanvien/add";
    }
    @PostMapping("/add")
    public String addNhanVien(@Valid @ModelAttribute("nhanvien") NhanVien nhanVien, BindingResult result, Model model){
        // check lỗi
        if(result.hasErrors()){
            model.addAttribute("PhongBans", phongBanService.getAllPhongBan());
            return "nhanvien/add";
        }
        nhanVienService.addNhanVien(nhanVien);
        return "redirect:/nhanviens";
    }
    @GetMapping("/delete/{Ma_NV}")
    public String deleteNhanVien(@PathVariable("Ma_NV") String Ma_NV){
        nhanVienService.deleteNhanVien(Ma_NV);
        return "redirect:/nhanviens";
    }
    @GetMapping("edit/{Ma_NV}")
    public String editNhanVienForm(@PathVariable("Ma_NV")String Ma_NV, Model model){
        NhanVien editNhanVien = nhanVienService.getNhanVienId(Ma_NV);
        if (editNhanVien != null ){
            model.addAttribute("nhanvien",editNhanVien);
            model.addAttribute("PhongBans", phongBanService.getAllPhongBan());
            return "nhanvien/edit";
        }else {
            return "redirect:/nhanviens";
        }
    }

    @PostMapping("edit/{Ma_NV}")
    public String editNhanVien(@PathVariable("Ma_NV")String Ma_NV, @ModelAttribute("nhanvien") @Valid NhanVien editNhanVien, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("PhongBans", phongBanService.getAllPhongBan());
            return "nhanvien/edit";
        }else {
            NhanVien existingNhanVien = nhanVienService.getNhanVienId(Ma_NV);
            if ( existingNhanVien != null){
                existingNhanVien.setTen_NV(editNhanVien.getTen_NV());
                existingNhanVien.setPhai(editNhanVien.getPhai());
                existingNhanVien.setNoi_Sinh(editNhanVien.getNoi_Sinh());
                existingNhanVien.setLuong(editNhanVien.getLuong());
                existingNhanVien.setPhongBan((editNhanVien.getPhongBan()));
                nhanVienService.updateNhanVien(existingNhanVien);
            }
            return "redirect:/nhanviens";
        }
    }

}
