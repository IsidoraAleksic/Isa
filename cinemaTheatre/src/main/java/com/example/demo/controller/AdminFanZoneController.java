package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;
import java.util.List;

@RestController
@RequestMapping("/adminfz")
public class AdminFanZoneController {

    @Autowired //uvek se autowire-uje interfejs.SOLID princip neki
    UserService userService;

    @RequestMapping("/all")
    public List<User> getAll(){
        return userService.getAll(UserType.ADMIN);
    }
    @RequestMapping("{id}")
    public User getAdminFz(@PathVariable("id") String id){
        return userService.getById(id);
    }
}
