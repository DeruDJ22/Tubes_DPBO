package com.sayurbox.sayurbox.controllers;

import com.sayurbox.sayurbox.models.Order;
import com.sayurbox.sayurbox.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    // Menampilkan halaman pembayaran
    @GetMapping("/payment")
    public String paymentPage() {
        return "payment";
    }
    

    @PostMapping("/payment")
    public String paymentPage(
            @RequestParam("totalItems") Integer totalItems,
            @RequestParam("totalPrice") Double totalPrice,
            Model model) {

        Order order = orderService.createOrder(totalItems, totalPrice);
        model.addAttribute("orderId", order.getOrderId());
        model.addAttribute("totalItems", order.getTotalItems());
        model.addAttribute("totalPrice", order.getTotalPrice());

        return "payment";
    }

    // Redirect ke halaman pengiriman
    @PostMapping("/delivery")
    public String deliveryPage(@RequestParam("orderId") String orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "delivery";
    }
}
