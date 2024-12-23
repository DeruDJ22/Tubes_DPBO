package com.sayurbox.sayurbox.controllers;

import com.sayurbox.sayurbox.models.User;
import com.sayurbox.sayurbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

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
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        User user = userService.authenticate(email, password);
        if (user != null) {
            session.setAttribute("user", user); // Simpan objek User
            session.setAttribute("username", user.getUsername()); // Masih boleh jika Anda butuh username saja
            session.setAttribute("role", user.getRole());
            return "redirect:/home";
        } else {
            System.out.println("Login gagal untuk email: " + email);
            model.addAttribute("error", "Email atau password salah.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@RequestParam String username, @RequestParam String email,
            @RequestParam String password, Model model) {
        if (userService.emailExists(email)) {
            model.addAttribute("error", "Email sudah digunakan.");
            return "registration";
        }

        User newUser = new User(username, email, password, "user"); // Default role "user"
        userService.addUser(newUser);

        return "redirect:/login";
    }
}
