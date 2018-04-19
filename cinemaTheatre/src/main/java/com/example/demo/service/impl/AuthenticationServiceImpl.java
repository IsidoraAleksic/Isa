package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;



    public User authenticateUser(String email, String password){
        User user = userRepository.findByEmail(email);
        if(user==null)
            return null;
        if(user.getPassword().matches(password))
            return user;
        return null;
    }

    @Override
    public String registerUser(User user){

        if(user.getPassword() == null ||user.getEmail() == null || user.getFirstName() == null ||
                user.getLastName() == null || user.getCity() == null || user.getPhone() == null )
            return null;

        if(userRepository.findByEmail(user.getEmail())!=null)
            return "exists";

        user.setRole(UserType.GUEST);
        user.setPoints(0);
        return "registered";
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByConfirmationToken(String token) {
        return userRepository.findByConfirmationToken(token);
    }


    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void setLoggedInUser(User user) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        Authentication authentication = new PreAuthenticatedAuthenticationToken(user.getId(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication.getName());
        if(authentication==null)
            return null;
        if(authentication.getName().equals("anonymousUser"))
            return null;
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getCredentials());
            Long id = Long.parseLong(authentication.getName());
        User user = userRepository.getById( id);

        return user;
    }


}
