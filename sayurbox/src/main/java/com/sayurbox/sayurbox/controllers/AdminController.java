package com.sayurbox.sayurbox.controllers;

import com.sayurbox.sayurbox.models.User;
import com.sayurbox.sayurbox.models.Product;
import com.sayurbox.sayurbox.services.UserService;
import com.sayurbox.sayurbox.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    // Menampilkan halaman admin
    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("product", new Product());
        return "admin"; // Nama file admin.html
    }

    // Menambahkan admin baru
    @PostMapping("/add-admin")
    public String addAdmin(@ModelAttribute User user, Model model) {
        user.setRole("admin"); // Set role admin
        userService.addUser(user);
        model.addAttribute("successAdmin", "Admin berhasil ditambahkan!");
        return "redirect:/admin";
    }

    // Menambahkan barang baru
    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product, Model model) {
        productService.addProduct(product);
        model.addAttribute("successProduct", "Barang berhasil ditambahkan!");
        return "redirect:/admin";
    }
}
