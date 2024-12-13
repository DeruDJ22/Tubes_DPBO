package com.sayurbox.sayurbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sayurbox.sayurbox.models.User;

import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
}
