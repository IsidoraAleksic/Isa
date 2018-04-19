package com.example.demo.model;

import javax.persistence.*;

@Entity(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private short discount;

    @ManyToOne
    private Projection projection;

    @OneToOne
    private Seat seat;
//
//    @ManyToOne
//    private User user;


    private int status; //0-not active, 1-active, 2-invited, 3- confirmed

    public int getStatus() {
        return status;
    }


//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public short getDiscount() {
        return discount;
    }

    public void setDiscount(short discount) {
        this.discount = discount;
    }

    public Projection getProjection() {
        return projection;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ticket(Long id) {
        this.id = id;
    }

    public Ticket() {
    }
}
