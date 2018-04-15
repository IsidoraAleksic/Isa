package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAll(UserType userType);

    User findByEmail(String email);

    User getById(Long id);

    void saveUser(User user);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    List<User> findUsersByFirstNameContainsOrLastNameContains(String firstName, String lastName);
}
