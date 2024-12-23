package com.sayurbox.sayurbox.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.sayurbox.sayurbox.models.Product;
import com.sayurbox.sayurbox.services.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductService productService;

    // Mapping untuk halaman index
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Mapping untuk halaman home, hanya jika user sudah login
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        List<Product> product = productService.getAllProducts();
        model.addAttribute("product", product);
        model.addAttribute("username", username);
        return "home";
    }

}
