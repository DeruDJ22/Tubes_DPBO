package com.sayurbox.sayurbox.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sayurbox.sayurbox.models.*;
import com.sayurbox.sayurbox.services.ProductService;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/product")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }
    
}
