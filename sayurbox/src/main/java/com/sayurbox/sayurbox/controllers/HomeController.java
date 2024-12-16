package com.sayurbox.sayurbox.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.sayurbox.sayurbox.models.*;
import com.sayurbox.sayurbox.services.ProductService;



@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String showAllProduct(Model model) {
        List<Product> product = productService.getAllProducts();
        model.addAttribute("product", product);
        
        return "home";
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    
}
