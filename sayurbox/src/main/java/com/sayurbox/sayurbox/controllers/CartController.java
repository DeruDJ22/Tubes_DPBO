package com.sayurbox.sayurbox.controllers;

import com.sayurbox.sayurbox.models.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    // Menampilkan keranjang
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        // Ambil keranjang dari session atau buat baru jika belum ada
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        // Hitung total harga
        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        // Kirim data ke Thymeleaf
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "cart";
    }

    // Menambahkan item ke keranjang
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("id") int id,
                            @RequestParam("name") String name,
                            @RequestParam("price") double price,
                            @RequestParam("image") String image,
                            HttpSession session) {

        // Ambil keranjang dari session
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        // Cek apakah item sudah ada di keranjang
        boolean itemExists = false;
        for (CartItem item : cartItems) {
            if (item.getId() == id) {
                item.setQuantity(item.getQuantity() + 1);
                itemExists = true;
                break;
            }
        }

        // Jika item belum ada, tambahkan item baru
        if (!itemExists) {
            cartItems.add(new CartItem(id, name, 1, price, image));
        }

        // Simpan kembali keranjang ke session
        session.setAttribute("cart", cartItems);

        // Redirect ke halaman keranjang
        return "redirect:/cart";
    }
}
