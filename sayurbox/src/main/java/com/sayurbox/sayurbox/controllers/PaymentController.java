package com.sayurbox.sayurbox.controllers;

import com.sayurbox.sayurbox.models.Order;
import com.sayurbox.sayurbox.models.Payment;
import com.sayurbox.sayurbox.repository.PaymentRepository;
import com.sayurbox.sayurbox.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public String paymentPage(@PathVariable String orderId, HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/login";
        }

        Order order = orderService.getOrderById(orderId);

        if (order == null) {
            return "redirect:/no-orders";
        }

        model.addAttribute("order", order);
        return "payment"; // Return the payment HTML page
    }

    // @PutMapping("/{orderId}")
    // public String processPayment(
    // @PathVariable String orderId) {

    // // Ambil data dari database
    // Order existingOrder = orderService.getOrderById(orderId);
    // try {

    // } catch (Exception e) {
    // // TODO: handle exception
    // }
    // if (existingOrder == null) {
    // return "payment";
    // }

    // // Update data order
    // existingOrder.setPaymentStatus("SUDAH_BAYAR");

    // // Simpan ke database
    // orderService.updateOrder(existingOrder);

    // // Kembalikan respons sukses
    // return "payment";
    // }

    @PostMapping("/{orderId}")
    public String processPayment(@PathVariable String orderId,
            @RequestParam String paymentMethod,
            @RequestParam Double amount,
            HttpSession session,
            Model model) {

        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        try {
            Order order = orderService.getOrderById(orderId);
            if (order == null) {
                model.addAttribute("message", "Payment Failed! Order not found.");
                return "payment";
            }

            if (amount == null || amount <= 0) {
                model.addAttribute("message", "Invalid payment amount.");
                return "payment";
            }

            Payment payment = new Payment();
            payment.setAmount(String.valueOf(amount));
            payment.setStatus("Paid");

            paymentRepository.save(payment);
            order.setPaymentStatus("SUDAH_BAYAR");
            orderService.updateOrder(order);

            model.addAttribute("success", true);
            model.addAttribute("payment", payment);
            model.addAttribute("order", order);

        } catch (Exception e) {
            // TODO: handle exception
            model.addAttribute("message", "An error occurred while processing payment: " + e.getMessage());
            return "payment";
        }
        return "payment";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
        return "home"; // Redirect to home page after payment
    }
}
