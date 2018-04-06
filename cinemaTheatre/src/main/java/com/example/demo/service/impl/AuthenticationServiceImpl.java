package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    @Autowired
    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return (UserDetails) userRepository.findByUsername(email);
//    }

    public User authenticateUser(String email, String password){
        User user = userRepository.findByEmail(email);
        if(user==null)
            return null;
        if(user.getPassword().matches(password))
            return user;
        return null;
    }



    @Override
    public void saveUser(User user) {
        //TODO: implementiraj
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(1);
    }

    @Override
    public String registerUser(User user) {
        if(user.getPassword() == null || user.getPassword().isEmpty())
            return "password cannot be empty";
        if( user.getEmail() == null  || user.getEmail().isEmpty())
            return "email cannot be empty";
        if(user.getFirstName() == null || user.getFirstName().isEmpty())
            return "first name cannot be empty";
        if( user.getLastName() == null || user.getLastName().isEmpty())
            return "last name cannot be empty";
        if( user.getCity() == null || user.getCity().isEmpty())
            return "city cannot be empty";
        if(user.getPhone() == null || user.getPhone().isEmpty() )
            return "phone cannot be empty";
        if(user.getUsername() == null || user.getUsername().isEmpty() )
            return "username cannot be empty";
        if(userRepository.findByEmail(user.getEmail())!=null)
            return "user exists";

        user.setRole(UserType.GUEST);
        userRepository.save(user);

        return "user registered";
    }


}
