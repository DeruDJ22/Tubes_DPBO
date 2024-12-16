package com.sayurbox.sayurbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sayurbox.sayurbox.models.User;
import com.sayurbox.sayurbox.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
            HttpSession session) {
        // Autentikasi pengguna
        User user = userService.authenticate(email, password);

        if (user != null) {
            // Jika autentikasi berhasil, simpan email dan username ke session
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("username", user.getUsername()); // Menyimpan username ke session
            return "redirect:/home"; // Redirect ke halaman home setelah login berhasil
        } else {
            // Jika email atau password salah, berikan pesan error
            if (!userService.findEmail(email)) {
                model.addAttribute("errorEmail", "Email yang dimasukan salah");
            } else if (!userService.findPassword(password)) {
                model.addAttribute("errorPass", "Password yang dimasukan salah");
            }
            return "login"; // Kembali ke halaman login dengan pesan error
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView addRegistration(@RequestParam("name") String name, @RequestParam("email") String email,
            @RequestParam("password") String password, Model model) {
        // Menambahkan pengguna baru
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        userService.addUsers(user);

        // Redirect ke halaman home setelah registrasi berhasil
        ModelAndView mav = new ModelAndView("redirect:/home");
        mav.addObject("user", user);
        return mav;
    }
}
