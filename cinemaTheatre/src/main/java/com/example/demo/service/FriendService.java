package com.example.demo.service;

import com.example.demo.model.Friends;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FriendService {

    List<Friends> findUsersByUserOne(User user);

    Friends findUserByUserOneAndUserTwo(User userOne, User userTwo);

    List<Friends> findUsersByUserTwo(User user);

    List<Friends> findUsersByUserOneOrUserTwo(User userOne, User userTwo);

    List<Friends> findByUserOneAndUserTwoOrUserTwoAndUserOne(User userOne, User userTwo, User user2, User user1);

    void delete(Friends friends);

    List<Friends> findAll();
}
