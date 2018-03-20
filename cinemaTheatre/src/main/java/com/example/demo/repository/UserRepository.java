package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    public List<User> getUserByRole(UserType role);

    public User getUserById(String id);
}
