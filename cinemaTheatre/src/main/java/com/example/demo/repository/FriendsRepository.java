package com.example.demo.repository;

import com.example.demo.model.Friends;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FriendsRepository extends JpaRepository<Friends,Long>{
    public List<Friends> findUsersByUserOneOrUserTwo(User userOne, User userTwo);
    public Friends findUserByUserOneAndUserTwo(User userOne, User userTwo);
    public List<Friends> findByUserOneAndUserTwoOrUserTwoAndUserOne(User userOne, User userTwo,User user2, User user1);
    public List<Friends> findUsersByUserOne(User user);
    public List<Friends> findUsersByUserTwo(User user);
    public void delete(Friends friends);
    public List<Friends> findAll();



}
