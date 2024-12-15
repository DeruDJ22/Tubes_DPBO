package com.sayurbox.sayurbox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeliveryController {

    @GetMapping("/delivery")
    public String paymentPage(Model model) {
        return "delivery";
    }
}
