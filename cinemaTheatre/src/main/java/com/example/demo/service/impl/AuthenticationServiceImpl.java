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
    @Autowired
    private EmailService emailService;


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
    public String registerUser(User user){

        if(user.getPassword() == null ||user.getEmail() == null || user.getFirstName() == null ||
                user.getLastName() == null || user.getCity() == null || user.getPhone() == null || user.getUsername() == null)
            return null;

        if(userRepository.findByEmail(user.getEmail())!=null)
            return "exists";

        user.setRole(UserType.OBICAN);


//        userRepository.save(user);

        return "registered";
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByConfirmationToken(String token) {
        return null;
    }


    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }


}
