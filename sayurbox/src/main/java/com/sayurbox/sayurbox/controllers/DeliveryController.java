package com.sayurbox.sayurbox.controllers;

import com.sayurbox.sayurbox.models.Delivery;
import com.sayurbox.sayurbox.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/delivery") // Prefix untuk semua mapping terkait pengiriman
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    // Endpoint untuk daftar pengiriman
    @GetMapping
    public String deliveryPage(Model model) {
        model.addAttribute("deliveries", deliveryService.getAllDeliveries());
        return "delivery"; // Mengacu ke file delivery.htm
    }

    // Endpoint untuk menambahkan pengiriman baru
    @PostMapping("/add")
    public String addDelivery(@ModelAttribute Delivery delivery) {
        deliveryService.saveDelivery(delivery);
        return "redirect:/delivery"; // Redirect ke halaman daftar pengiriman
    }

    // Endpoint untuk detail pengiriman berdasarkan ID
    @GetMapping("/{id}")
    public String getDeliveryById(@PathVariable Integer id, Model model) {
        model.addAttribute("delivery", deliveryService.getDeliveryById(id).orElse(null));
        return "delivery-details"; // Pastikan ada file delivery-details.htm
    }
}
