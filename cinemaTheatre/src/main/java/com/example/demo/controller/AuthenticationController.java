package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authenticate")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User authenticateUserLogin(@RequestBody User user){
        return authenticationService.authenticateUser(user.getEmail(), user.getPassword());
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String registerUser(@RequestBody User user){
        return authenticationService.registerUser(user);
    }

}

