package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    public List<User> getAll(UserType userType);
    public User findByEmail(String email);
    public User getById(Long id);
    public void saveUser(User user);
    public List<User> findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);
//    public List<User> findByName(String name);
public List<User> findUsersByFirstNameContainsOrLastNameContains(String firstName, String lastName);
}
