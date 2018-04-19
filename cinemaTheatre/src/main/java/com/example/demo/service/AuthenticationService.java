package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService  {

    User authenticateUser(String username, String password);
    String registerUser(User user);
    void saveUser(User user);
    User findByConfirmationToken(String token);
    void deleteUser(User user);
    void setLoggedInUser(User user);
    User getLoggedInUser();
    String updatePassword(UserDTO userDTO);
}
