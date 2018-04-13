package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_one_id" , "user_two_id"})})
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userOne;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userTwo;

    private int status; //1-accepted, 0-sent, -1- initial

    public Friends() {
        status = -1;
    }

    public Friends(User userOne, User userTwo, int status) {
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserOne() {
        return userOne;
    }

    public void setUserOne(User userOne) {
        this.userOne = userOne;
    }

    public User getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(User userTwo) {
        this.userTwo = userTwo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
