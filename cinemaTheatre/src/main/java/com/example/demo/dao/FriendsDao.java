package com.example.demo.dao;

import com.example.demo.model.User;

public interface FriendsDao {

    public boolean create(final User userOne, User userTwo, int status);
    public boolean update(final User userOne, User userTwo, int status);
}
