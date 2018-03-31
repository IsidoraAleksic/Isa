package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAll(UserType userType){
        return userRepository.getUserByRole(userType);
    }

    public User getById(String id){
        return userRepository.getUserById(id);
    }


}
