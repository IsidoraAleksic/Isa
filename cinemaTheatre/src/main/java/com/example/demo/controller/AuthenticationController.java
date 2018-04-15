package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/authenticate")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/getUser",method = RequestMethod.GET, produces = "application/json")
    public User getLoggedInUser(){
        User user = authenticationService.getLoggedInUser();
        if(user==null)
            return null;

        return user;

    }
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain", consumes = "application/json")
    public String authenticateUserLogin( @RequestBody User user){
        User loggedIn = authenticationService.authenticateUser(user.getEmail(), user.getPassword());
        if(loggedIn== null)
            return "nok";
        else {
            if (loggedIn.getEnabled()) {
                authenticationService.setLoggedInUser(loggedIn);
                return "ok";
            }else
                return "not enabled";
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "ok";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST, produces = "text/plain", consumes = "application/json")
    public String registerUser(@Valid @RequestBody User user, HttpServletRequest request){
        String registered = authenticationService.registerUser(user);
        if (registered==null)
            return "nok";
        else if(registered.equals("exists"))
            return "exists";
        String url = request.getScheme() + "://" + request.getServerName() + ":9080/authenticate/confirm";
        user.setConfirmationToken(UUID.randomUUID().toString());
        user.setEnabled(false);
        authenticationService.saveUser(user);
        url = url + "/"+user.getConfirmationToken();
        try {
            emailService.sendEmailVerification(user.getEmail(),url);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        if(user.getEnabled())
            return "ok";
        return "not confirmed";
    }

    @RequestMapping(value = "/confirm/{token}", method = RequestMethod.GET)
    public void confirmEmail(@PathVariable String token){
        User user = authenticationService.findByConfirmationToken(token);
        if(user!=null) {
            authenticationService.deleteUser(user);
            if(!user.getEnabled()) {
                user.setEnabled(true);
                authenticationService.saveUser(user);
            }
        }

    }

}


//toastr
//jwt json web
