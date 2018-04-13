package com.example.demo.service;

import com.example.demo.model.Friends;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FriendService {

    public List<Friends> findUsersByUserOne(User user);
    public Friends findUserByUserOneAndUserTwo(User userOne, User userTwo);
    public List<Friends> findUsersByUserTwo(User user);
    public List<Friends> findUsersByUserOneOrUserTwo(User userOne, User userTwo);
    public List<Friends> findByUserOneAndUserTwoOrUserTwoAndUserOne(User userOne, User userTwo,User user2, User user1);
    public void delete(Friends friends);
    public List<Friends> findAll();
}
