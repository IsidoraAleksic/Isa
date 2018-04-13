package com.example.demo.service.impl;

import com.example.demo.model.Friends;
import com.example.demo.model.User;
import com.example.demo.repository.FriendsRepository;
import com.example.demo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendsRepository friendsRepository;

    @Override
    public List<Friends> findUsersByUserOneOrUserTwo(User userOne, User userTwo) {
        return friendsRepository.findUsersByUserOneOrUserTwo(userOne,userTwo);
    }

    @Override
    public List<Friends> findByUserOneAndUserTwoOrUserTwoAndUserOne(User userOne, User userTwo, User user2, User user1) {
        return friendsRepository.findByUserOneAndUserTwoOrUserTwoAndUserOne(userOne,userTwo,user2,user1);
    }

    @Override
    public void delete(Friends friends) {
        friendsRepository.delete(friends);
    }

//    @Override
//    public void saveFriends(Friends friends){
//            friendsRepository.saveFriends(friends);
//    }

    @Override
    public List<Friends> findAll(){
        return friendsRepository.findAll();
    }

    @Override
    public List<Friends> findUsersByUserOne(User user) {
        return friendsRepository.findUsersByUserOne(user);
    }

    @Override
    public Friends findUserByUserOneAndUserTwo(User userOne, User userTwo) {
        return friendsRepository.findUserByUserOneAndUserTwo(userOne,userTwo);
    }

    @Override
    public List<Friends> findUsersByUserTwo(User user) {
        return friendsRepository.findUsersByUserTwo(user);
    }
}
