package com.sayurbox.sayurbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sayurbox.sayurbox.models.User;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    User findByUsername(String username);
}
