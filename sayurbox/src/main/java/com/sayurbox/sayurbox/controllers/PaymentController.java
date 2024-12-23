package com.sayurbox.sayurbox.controllers;

import com.sayurbox.sayurbox.models.Order;
import com.sayurbox.sayurbox.models.Payment;
import com.sayurbox.sayurbox.repository.PaymentRepository;
import com.sayurbox.sayurbox.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public String paymentPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/login";
        }

        Order order = orderService.getOrdersByUsername(username).stream().findFirst().orElse(null);

        if (order == null) {
            return "redirect:/no-orders";
        }

        model.addAttribute("order", order);
        return "payment"; // Return the payment HTML page
    }

    @PostMapping
    public String processPayment(@RequestParam String orderId, @RequestParam String paymentMethod,
            @RequestParam Double amount, Model model) {
        long parsedOrderId = 0;

        // Cek apakah orderId dapat diproses
        try {
            // Hapus "ORD-" jika ada, karena hanya angka yang perlu dikonversi
            String numericOrderId = orderId.replace("ORD-", "");
            parsedOrderId = Long.parseLong(numericOrderId); // Konversi ke long
        } catch (NumberFormatException e) {
            // Tangani jika ID tidak valid
            model.addAttribute("message", "Order ID tidak valid. Harus berupa angka.");
            return "payment-error"; // Ganti dengan halaman error sesuai aplikasi Anda
        }

        // Ambil order berdasarkan ID yang sudah dikonversi
        Order order = orderService.getOrderById(parsedOrderId);

        if (order != null) {
            // Update status pembayaran
            order.setPaymentStatus("SUDAH_BAYAR");

            // Buat objek pembayaran
            Payment payment = new Payment();
            payment.setAmount(String.valueOf(amount));
            payment.setStatus("Paid");

            // Simpan pembayaran dan update order
            paymentRepository.save(payment);
            orderService.updateOrder(order);

            model.addAttribute("message", "Payment Successful!");
            model.addAttribute("payment", payment);
            return "payment";
        }

        model.addAttribute("message", "Payment Failed! Order not found.");
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
