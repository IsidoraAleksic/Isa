package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> getUserByRole(UserType role);
    User findByUsername(String username);
    User findByEmail(String email);
    User getUserById(String id);
    User getById(Long id);
    User findByConfirmationToken(String token);
}
