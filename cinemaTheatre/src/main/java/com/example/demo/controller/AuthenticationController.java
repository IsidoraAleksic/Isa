package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.impl.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/authenticate")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    EmailService emailService;

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain", consumes = "application/json")
    public String authenticateUserLogin(@RequestBody User user){
        User logedIn = authenticationService.authenticateUser(user.getEmail(), user.getPassword());
        if(logedIn== null)
            return "nok";
        else
            if(logedIn.isEnabled())
                return "ok";
            else
                return "not enabled";
//        if(authenticationService.authenticateUser(user.getEmail(), user.getPassword()) == null)
//            return new User();
//        else
//            return authenticationService.authenticateUser(user.getEmail(), user.getPassword());
    }

    @RequestMapping(value="/register", method = RequestMethod.POST, produces = "text/plain", consumes = "application/json")
    public String registerUser(@RequestBody User user, HttpServletRequest request){
        String registered = authenticationService.registerUser(user);
        if (registered==null)
            return "nok";
        else if(registered.equals("exists"))
            return "exists";
        String url = request.getScheme() + "://" + request.getServerName() + ":9080/authenticate/confirm";
        System.out.println(url);
        user.setConfirmationToken(UUID.randomUUID().toString());
        user.setEnabled(false);
        authenticationService.saveUser(user);
        url = url + "/"+user.getConfirmationToken();
        System.out.println(url);

        try {
            emailService.sendEmailVerification(user.getEmail(),url);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        if(user.isEnabled())
            return "ok";

        return "not confirmed";
    }

    @RequestMapping(value = "/confirm/{token}", method = RequestMethod.GET)
    public void confirmEmail(@PathVariable("token") String token){
        User user = authenticationService.findByConfirmationToken(token);
        authenticationService.deleteUser(user);
        if(user!=null) {
            user.setEnabled(true);
            authenticationService.saveUser(user);
        }


    }

}

