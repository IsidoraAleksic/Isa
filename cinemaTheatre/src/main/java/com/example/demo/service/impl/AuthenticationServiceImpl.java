package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
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


    private static final String SUCCESSFULLY_UPDATED_PW = "Successfully updated password";
    private static final String UNSUCCESSFULLY_UPDATED_PW = "Unsuccessfully updated password";

    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return null;
        if (user.getPassword().matches(password))
            return user;
        return null;
    }

    @Override
    public String registerUser(User user) {

        if (user.getPassword() == null || user.getEmail() == null || user.getFirstName() == null ||
                user.getLastName() == null || user.getCity() == null || user.getPhone() == null)
            return null;

        if (userRepository.findByEmail(user.getEmail()) != null)
            return "exists";

        user.setRole(UserType.GUEST);
        user.setPoints(0);

        // user.setRole(user.getRole());
        //user.setRole(UserType.GUEST   );

//        userRepository.save(user);

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

        System.out.println(authentication.getName());
        if (authentication.getName().equals("anonymousUser"))
                    return null;
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getCredentials());
        Long id = Long.parseLong(authentication.getName());
        User user = userRepository.getById(id);
        return user;
    }
    //kupim userId i provevaravam da li se prvi put ulogovao


    @Override
    public String updatePassword(UserDTO userDTO) {
        User user = userRepository.getById(userDTO.getUserId());
        if (userDTO == null) {
            //return ERRORR_CREATE_AD;
            return null;
        }
        if (user.getPassword().equals(userDTO.getPasswordOld())) {
            user.setPassword(userDTO.getPasswordNew());
            user.setFirstLogin(false);
            userRepository.save(user);
            return SUCCESSFULLY_UPDATED_PW;
        }
        return UNSUCCESSFULLY_UPDATED_PW;
    }

}
