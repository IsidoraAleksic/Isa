package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserTierScale;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = "application/json")
    public String editUserInformation(@RequestBody User user){
        //TODO: get logged in user(find by verification token). Then find by id.
        User loggedIn = authenticationService.getLoggedInUser();
        if(loggedIn!=null){
            if(user.getPhone()!=null)
                loggedIn.setPhone(user.getPhone());
            if(user.getCity()!=null)
                loggedIn.setCity(user.getCity());
            if(user.getLastName()!=null)
                loggedIn.setLastName(user.getLastName());
            if(user.getFirstName()!=null)
                loggedIn.setFirstName(user.getFirstName());
            if(user.getPassword()!=null)
                loggedIn.setPassword(user.getPassword());

            userService.saveUser(loggedIn);
            return "ok";
        }
        return "nok";
    }

}
