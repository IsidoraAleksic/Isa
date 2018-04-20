package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="reservationsMerchandise")
public class ReservationMerchandise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchandise_id")
    private Merchandise merch;

/*
    @Version
    private Long version;
*/

    public ReservationMerchandise(){

    }

    public ReservationMerchandise(User user, Merchandise merch) {
        this.user = user;
        this.merch = merch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Merchandise getMerch() {
        return merch;
    }

    public void setMerch(Merchandise merch) {
        this.merch = merch;
    }
/*

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
*/

    @Override
    public String toString() {
        return "ReservationMerchandise{" +
                "id=" + id +
                ", user=" + user +
                ", merch=" + merch +
                '}';
    }
}