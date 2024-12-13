package com.sayurbox.sayurbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

// import model yang nantinya digunakan untuk menyambungkan dengan ui
import com.sayurbox.sayurbox.models.User;
import com.sayurbox.sayurbox.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        //TODO: process POST request
        User user = userService.authenticate(email, password);

        if (user != null){
            return "redirect:/";
        } else {
            model.addAttribute("error", "data yang dimasukan salah");
            return "login";
        }
        
    }
    
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
}
