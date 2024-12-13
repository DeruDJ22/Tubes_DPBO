package com.sayurbox.sayurbox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }@GetMapping("/login")
    public String login() {
        return "login";
    }@GetMapping("/registration")
    public String registration() {
        return "registration";
    }@GetMapping("/cart")
    public String cart() {
        return "cart";
    }
    
    
}
