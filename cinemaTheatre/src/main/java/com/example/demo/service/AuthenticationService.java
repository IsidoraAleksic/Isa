package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService  {

    public User authenticateUser(String username, String password);
    public String registerUser(User user);
    public void saveUser(User user);
    public User findByConfirmationToken(String token);
    public void deleteUser(User user);
}
