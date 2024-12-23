package com.sayurbox.sayurbox.controllers;

import com.sayurbox.sayurbox.models.Courier;
import com.sayurbox.sayurbox.services.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/couriers")
public class CourierController {

    @Autowired
    private CourierService courierService;

    // Menampilkan daftar kurir
    @GetMapping
    public String getAllCouriers(Model model) {
        List<Courier> couriers = courierService.getAllCouriers();
        model.addAttribute("couriers", couriers);
        model.addAttribute("courier", new Courier()); // Untuk form
        return "courier"; // Pastikan ini sesuai dengan nama file 'courier.html'
    }

    // Menampilkan detail kurir
    @GetMapping("/{id}")
    public String getCourierById(@PathVariable Long id, Model model) {
        model.addAttribute("couriers", courierService.getAllCouriers());
        model.addAttribute("courier", courierService.getCourierById(id).orElse(null));
        return "courier"; // Kembali ke file 'courier.html' dengan data detail
    }

    // Menyimpan data kurir baru
    @PostMapping
    public String saveCourier(@ModelAttribute Courier courier) {
        courierService.saveCourier(courier);
        return "redirect:/couriers";
    }

    // Menghapus kurir
    @GetMapping("/delete/{id}")
    public String deleteCourier(@PathVariable Long id) {
        courierService.deleteCourier(id);
        return "redirect:/couriers";
    }
}
