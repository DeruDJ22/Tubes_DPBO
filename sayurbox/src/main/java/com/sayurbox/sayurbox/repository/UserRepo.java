package com.sayurbox.sayurbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sayurbox.sayurbox.models.User;

import org.springframework.stereotype.Repository;



@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByPassword(String password);
    boolean existsByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
