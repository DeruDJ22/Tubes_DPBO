package com.sayurbox.sayurbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// import model yang nantinya digunakan untuk menyambungkan dengan ui
import com.sayurbox.sayurbox.models.User;
import com.sayurbox.sayurbox.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
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
            if(!userService.findEmail(email)){
                model.addAttribute("errorEmail", "Email yang dimasukan salah");
            } else if(!userService.findPassword(password)){
                model.addAttribute("errorPass", "Password yang dimasukan salah");
            }
            // model.addAttribute("error", "email atau password salah");
            return "login";
        }
        
    }
    
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView addRegistration(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        userService.addUsers(user);

        ModelAndView mav = new ModelAndView("home"); // Nama file HTML sukses
        mav.addObject("user", user);
        return mav;
    }
}
