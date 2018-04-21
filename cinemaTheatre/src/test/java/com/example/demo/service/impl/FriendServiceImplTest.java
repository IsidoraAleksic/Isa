package com.example.demo.service.impl;

import com.example.demo.model.Friends;
import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.repository.FriendsRepository;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Role;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendServiceImplTest {

    @Autowired
    private FriendsRepository friendsRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUsersByUserOneOrUserTwo() {
        User userOne =userRepository.getById(1L);
        User userTwo = userRepository.getById(2L);
        List<Friends> friends =  friendsRepository.findUsersByUserOneOrUserTwo(userOne,userTwo);
        assertThat(friends).isNotNull();

    }

    @Test
    public void findByUserOneAndUserTwoOrUserTwoAndUserOne() {
        User userOne =userRepository.getById(1L);
        User userTwo = userRepository.getById(2L);
        List<Friends> friends =  friendsRepository.findByUserOneAndUserTwoOrUserTwoAndUserOne(userOne,userTwo,userOne,userTwo);
        assertThat(friends).isNotNull();
    }



    @Test
    public void findAll() {
        List<Friends> friends =  friendsRepository.findAll();
        assertThat(friends).isNotNull();

    }

    @Test
    public void findUsersByUserOne() {
        User userOne =userRepository.getById(1L);
        User userTwo = userRepository.getById(2L);
        List<Friends> friends =  friendsRepository.findUsersByUserOne(userOne);
        assertThat(friends).isNotNull();
    }

    @Test
    public void findUserByUserOneAndUserTwo() {
        User userOne =userRepository.getById(1L);
        User userTwo = userRepository.getById(2L);
        Friends friends =  friendsRepository.findUserByUserOneAndUserTwo(userOne,userTwo);
        assertThat(friends).isNull();
    }

    @Test
    public void findUsersByUserTwo() {
        User userOne =userRepository.getById(1L);
        User userTwo = userRepository.getById(2L);
        List<Friends> friends =  friendsRepository.findUsersByUserTwo(userTwo);
        assertThat(friends).isNotNull();
    }

    @Test
    @Transactional
    @Rollback(true)
    public void delete() {
        User userOne =userRepository.getById(1L);
        User userTwo = userRepository.getById(3L);
        List<Friends> friends =  friendsRepository.findByUserOneAndUserTwoOrUserTwoAndUserOne(userOne,userTwo,userOne,userTwo);
        assertThat(friends).isNotNull();
        friendsRepository.delete(friends.get(0));
    }
}