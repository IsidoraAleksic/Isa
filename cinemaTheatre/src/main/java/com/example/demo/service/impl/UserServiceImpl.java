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

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAll(UserType userType){
        return userRepository.getUserByRole(userType);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getById(Long id){
        return userRepository.getById(id);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return userRepository.findUsersByFirstName(firstName);
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return userRepository.findUsersByLastName(lastName);
    }

    @Override
    public List<User> findUsersByFirstNameContainsOrLastNameContains(String firstName, String lastName) {
        return userRepository.findUsersByFirstNameContainsOrLastNameContains(firstName,lastName);
    }


}
