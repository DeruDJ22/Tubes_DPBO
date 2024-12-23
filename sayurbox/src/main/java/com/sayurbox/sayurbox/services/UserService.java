package com.sayurbox.sayurbox.services;

import com.sayurbox.sayurbox.models.User;
import com.sayurbox.sayurbox.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    // Autentikasi User
    public User authenticate(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password);
    }

    // Mengecek apakah email sudah terdaftar
    public boolean emailExists(String email) {
        return userRepo.existsByEmail(email);
    }

    // Menambah user baru
    public User addUser(User user) {
        return userRepo.save(user);
    }

    // Menambahkan default admin jika belum ada
    @PostConstruct
    public void createDefaultAdmin() {
        if (!userRepo.existsByEmail("admin@example.com")) {
            User admin = new User("Admin", "admin@example.com", "admin123", "admin");
            userRepo.save(admin);
        }
    }
}
