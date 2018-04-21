package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void getAll() {
        List<User> saved = userRepository.findAll();
        assertThat(saved).isNotNull();
    }

    @Test
    public void findByEmail() {
        User user = userRepository.findByEmail("isamejl811@gmail.com");
        assertThat(user).isNotNull();
        assertThat(user.getRole()).isEqualTo(UserType.GUEST);
    }

    @Test
    public void getById() {
        User user = userRepository.getById(1L);
        assertThat(user).isNotNull();
        assertThat(user.getRole()).isEqualTo(UserType.ADMIN);

    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setId(5L);
        user.setEmail("maja@gmail.com");
        user.setPassword("maja");
        user.setRole(UserType.GUEST);
        user.setFirstName("Maja");
        user.setLastName("majic");
        user.setCity("Nis");
        user.setPhone("032");
        user.setEnabled(true);
        user.setPoints(0);
        user.setConfirmationToken("100");
        userRepository.save(user);
        User saved = userRepository.findByEmail("maja@gmail.com");
        assertThat(saved).isNotNull();
        assertThat(saved.getRole()).isEqualTo(UserType.GUEST);
        assertThat(saved.getEmail()).isEqualTo(user.getEmail());
        assertThat(saved.getPassword()).isEqualTo(user.getPassword());
        assertThat(saved.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(saved.getLastName()).isEqualTo(user.getLastName());
    }

    @Test
    public void findByFirstName() {
        List<User> saved = userRepository.findUsersByFirstName("Maja");
        assertThat(saved).isNotNull();
    }

    @Test
    public void findByLastName() {
        List<User> saved = userRepository.findUsersByLastName("Majic");
        assertThat(saved).isNotNull();
    }

    @Test
    public void findUsersByFirstNameContainsOrLastNameContains() {
        List<User> saved = userRepository.findUsersByFirstNameContainsOrLastNameContains("Maja","Ma");
        assertThat(saved).isNotNull();
    }
}