package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService  {

    public User authenticateUser(String username, String password);
    public void saveUser(User user);
    public String registerUser(User user);
}
