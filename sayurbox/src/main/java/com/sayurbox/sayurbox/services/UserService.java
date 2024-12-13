package com.sayurbox.sayurbox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayurbox.sayurbox.models.User;
import com.sayurbox.sayurbox.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    public User authenticate(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password);
    }
}
