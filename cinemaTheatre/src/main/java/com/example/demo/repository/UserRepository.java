package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    public List<User> getUserByRole(UserType role);
//    public User findByUsername(String username);
    public User findByEmail(String email);
    public User getUserById(String id);
    public User getById(Long id);
//    public User findUserById(String id);
    public User findByConfirmationToken(String token);
    public List<User> findUsersByFirstName(String firstName);
    public List<User> findUsersByLastName(String lastName);
//    public List<User> findUsersByFirstNameOrLastName(String firstName, String lastName);
//    public List<User> findUsersByFirstNameIsLikeOrLastNameIsLike(String firstName, String lastName);
    public List<User> findUsersByFirstNameContainsOrLastNameContains(String firstName, String lastName);
}
