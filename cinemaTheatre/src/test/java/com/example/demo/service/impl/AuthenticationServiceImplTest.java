package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.service.AuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceImplTest {

    @Autowired
    AuthenticationService authenticationService;

    @Test
    public void authenticateUser() {

        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        user.setRole(UserType.GUEST);
       User auth =  authenticationService.authenticateUser(user.getEmail(),user.getPassword());
        assertThat(auth).isNotNull();
        assertThat(auth.getRole()).isEqualTo(UserType.GUEST);
        assertThat(auth.getEmail()).isEqualTo(user.getEmail());
        assertThat(auth.getPassword()).isEqualTo(user.getPassword());

    }

    @Test
    public void registerUser() {
        User user = new User();
        user.setEmail("pera@gmail.com");
        user.setPassword("pera");
        user.setRole(UserType.GUEST);
        user.setFirstName("pera");
        user.setLastName("pera");
        user.setCity("Nis");
        user.setPhone("032");
        user.setEnabled(true);
        user.setPoints(0);
        user.setConfirmationToken("110");
        String ret  = authenticationService.registerUser(user);
        assertThat(ret).isEqualTo("registered");

    }

    @Test
    public void saveUser() {

        User user = new User();
        user.setEmail("majaMajic@gmail.com");
        user.setPassword("maja");
        user.setRole(UserType.GUEST);
        user.setFirstName("Maja");
        user.setLastName("majic");
        user.setCity("Nis");
        user.setPhone("032");
        user.setEnabled(true);
        user.setPoints(0);
        user.setConfirmationToken("150");
        authenticationService.saveUser(user);
        User saved = authenticationService.findByConfirmationToken("150");


        assertThat(saved).isNotNull();
        assertThat(saved.getRole()).isEqualTo(UserType.GUEST);
        assertThat(saved.getEmail()).isEqualTo(user.getEmail());
        assertThat(saved.getPassword()).isEqualTo(user.getPassword());
        assertThat(saved.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(saved.getLastName()).isEqualTo(user.getLastName());
    }

    @Test
    public void setLoggedInUser() {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        user.setRole(UserType.GUEST);
        user.setId(1L);
        assertThat(user).isNotNull();
        authenticationService.setLoggedInUser(user);
    }

    @Test
    public void getLoggedInUserNull() {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        user.setRole(UserType.GUEST);
        User auth = authenticationService.getLoggedInUser();
        assertThat(auth).isNull();
    }
    @Test
    public void getLoggedInUserNotNull() {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        user.setId(1L);
        user.setRole(UserType.GUEST);
        authenticationService.setLoggedInUser(user);
        User auth = authenticationService.getLoggedInUser();
        assertThat(auth).isNotNull();
    }

    @Test
    public void updatePassword() {
    }
}