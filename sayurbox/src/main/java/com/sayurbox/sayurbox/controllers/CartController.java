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
import java.util.Optional;

@Controller
public class CartController {

    // Menampilkan keranjang
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cartItems = getCartFromSession(session);

        // Hitung total harga
        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "cart"; // Template Thymeleaf cart.html
    }

    // Menambahkan item ke keranjang
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("id") int id,
                            @RequestParam("name") String name,
                            @RequestParam("price") double price,
                            @RequestParam("image") String image,
                            HttpSession session) {

        // Ambil keranjang dari session
        List<CartItem> cartItems = getCartFromSession(session);

        // Cek jika item sudah ada di keranjang
        Optional<CartItem> existingItem = cartItems.stream()
                .filter(item -> item.getId() == id)
                .findFirst();

        if (existingItem.isPresent()) {
            // Jika item sudah ada, tambahkan quantity
            existingItem.get().setQuantity(existingItem.get().getQuantity() + 1);
        } else {
            // Jika belum, tambahkan item baru
            cartItems.add(new CartItem(id, name, 1, price, image));
        }

        // Simpan kembali ke session
        session.setAttribute("cart", cartItems);
        System.out.println("Item ditambahkan ke keranjang: " + name);

        // Redirect ke halaman cart
        return "redirect:/cart";
    }

    // Utility: Ambil keranjang dari session
    private List<CartItem> getCartFromSession(HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cart", cartItems);
        }
        return cartItems;
    }
}
