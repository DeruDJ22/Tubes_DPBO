package com.sayurbox.sayurbox.controllers;

import com.sayurbox.sayurbox.models.Delivery;
import com.sayurbox.sayurbox.models.Courier;
import com.sayurbox.sayurbox.services.DeliveryService;
import com.sayurbox.sayurbox.services.CourierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private CourierService courierService;

    @GetMapping("/trackDelivery")
    public String trackDelivery(@RequestParam(value = "deliveryId", defaultValue = "0")  Long deliveryId, Model model) {
        // Ambil data Delivery
        Delivery delivery = deliveryService.getDeliveryById(deliveryId).orElse(null);

        if (delivery != null) {
            // Ambil data kurir berdasarkan kurirId di Delivery
            Courier courier = courierService.getCourierById(delivery.getKurirId());
            model.addAttribute("delivery", delivery);
            model.addAttribute("courier", courier);
        } else {
            model.addAttribute("message", "Delivery tidak ditemukan");
        }

        return "trackDelivery"; // Halaman baru untuk menampilkan detail pengiriman
    }
}
