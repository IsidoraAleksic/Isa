package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAll(UserType.ADMIN);
    }
}
